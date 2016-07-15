package com.ufgov.zc.client.applet.local;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

public class DownLoadTask implements Callable<Boolean> {
  private static final Logger LOG = Logger.getLogger("DownLoadTask");

  private final CountDownLatch latch;

  private final String baseUrl;

  private final String resourceName;

  private final String rDigest;

  public DownLoadTask(String baseUrl, String resource, String remoteDigest, CountDownLatch latch) {
    this.baseUrl = baseUrl;
    this.resourceName = resource;
    this.rDigest = remoteDigest;
    this.latch = latch;
  }

  /**
   * 返回是否从服务器端下载数据:下载：true 否： false
   */
  @Override
  public Boolean call() throws Exception {
    BufferedInputStream stream = null;
    try {
      if (needDownload()) {
        String url = "";
        if (resourceName.endsWith(".jar")) {
          deleteLocalFile(DownLoadManager.localJarUrl + "/" + resourceName);
          url = "/applet/";
          downLoadResource(baseUrl + url + resourceName, DownLoadManager.localJarUrl + "/" + resourceName, rDigest);
        } else {
          deleteLocalFile(DownLoadManager.localResourceUrl + "/" + resourceName);
          url = "/resource/";
          downLoadResource(baseUrl + url + resourceName, DownLoadManager.localResourceUrl + "/" + resourceName, rDigest);
        }
        LOG.info(resourceName + " 下载成功！");
        return Boolean.TRUE;
      } else {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      LOG.info(resourceName + "下载失败:" + e.getMessage());
      throw new RuntimeException();
    } finally {
      latch.countDown();
      try {
        if (stream != null) stream.close();
      } catch (Exception ex) {
        LOG.info(ex.getMessage());
      }
    }
  }

  private void deleteLocalFile(String path) {
    // TCJLODO Auto-generated method stub
    LOG.info("delete file:" + path);
    File f = new File(path);
    if (f.exists()) {
      f.delete();
    }
  }

  private void downLoadResource(String remoteSourceUrl, String localSourceUrl, String remoteDigest) throws Exception {
    int downLoadCount = 0;
    try {
      while (downLoadCount < 3) {
        LOG.info(remoteSourceUrl);
        LOG.info(localSourceUrl);
        URL u = new URL(remoteSourceUrl);
        BufferedInputStream stream = new BufferedInputStream(u.openStream());
        File f = new File(localSourceUrl);
        if (f.exists()) f.delete();
        FileOutputStream out = new FileOutputStream(localSourceUrl);
        byte[] buffer = new byte[1024];
        int length = stream.read(buffer);
        while (length > 0) {
          out.write(buffer, 0, length);
          length = stream.read(buffer);
        }
        out.flush();
        out.close();
        f = new File(localSourceUrl);
        String localDigest = FileUtil.getFileDegest(f);
        if (remoteDigest.equals(localDigest)) {
          LOG.info(resourceName + " 下载完整性校验成功!");
          break;
        } else {
          LOG.info(resourceName + " 下载完整性校验失败..." + (downLoadCount + 1) + "次");
          downLoadCount++;
        }
      }
      if (downLoadCount >= 3) { throw new RuntimeException(resourceName + " 下载完整性校验失败!"); }
    } catch (Exception ex) {
      throw ex;
    }
  }

  /**
   * @return
   */
  private boolean needDownload() throws Exception {
    String remoteDigest = this.rDigest;
    /*if ("-1".equals(remoteDigest)) {
    	throw new RuntimeException(resourceName + "服务器端资源不存在!");
    } else if ("-2".equals(remoteDigest)) {
    	throw new RuntimeException("读取" + resourceName + "的摘要错误!");
    }*/
    File f = null;
    if (resourceName.endsWith(".jar")) {
      f = new File(DownLoadManager.localJarUrl + "/" + resourceName);
    } else {
      f = new File(DownLoadManager.localResourceUrl + "/" + resourceName);
    }

    boolean result = false;
    if (!f.exists()) {
      result = true;
    } else {
      String localDigest = getLocalDigest(f);
      result = !localDigest.equals(remoteDigest);
      if (result) {
        LOG.info(resourceName + " 有更新, 需要下载......");
      }
    }

    return result;
  }

  private String getLocalDigest(File f) throws Exception {
    // File localDigestFile = new File(DownLoadManager.digestUrl + "/"
    // + resourceName.substring(0, resourceName.lastIndexOf(".")));
    // return FileUtil.readFile(localDigestFile);
    return FileUtil.getFileDegest(f);
  }
}
