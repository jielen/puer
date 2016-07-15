package com.ufgov.zc.client.applet.local;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

public class DownLoadManager {
  private static final Logger LOG = Logger.getLogger("DownLoadManager");

  public static final String localResourceUrl = System.getenv("APPDATA") + "/platform/localResource";

  public static final String localJarUrl = System.getenv("APPDATA") + "/platform/jars";

  private static String baseUrl = "";

  private static String token = "";

  private DownLoadManager() {

  }

  private static void init(String baseUrl, String token) {

    DownLoadManager.baseUrl = baseUrl;
    DownLoadManager.token = token;
    File f = new File(localResourceUrl);
    //建立缓存文件夹
    if (!f.exists()) {
      f.mkdirs();
    }
    f = new File(localJarUrl);
    if (!f.exists()) {
      f.mkdirs();
    }
  }

  public synchronized static boolean downResource(String baseUrl, String token) throws RuntimeException {
    init(baseUrl, token);
    Map rMap = getRemoteDegest();
    CountDownLatch lantch = new CountDownLatch(rMap.keySet().size());
    List tasks = new ArrayList(rMap.keySet().size());
    Iterator<String> resourceNs = rMap.keySet().iterator();
    while (resourceNs.hasNext()) {
      String name = resourceNs.next();
      DownLoadTask task = new DownLoadTask(baseUrl, name, (String) rMap.get(name), lantch);
      tasks.add(task);
    }

    boolean result = false;
    try {
      ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
      List<Future<Boolean>> futures = exec.invokeAll(tasks);
      lantch.await();
      exec.shutdown();
      for (Future f : futures) {
        result = result || (Boolean) f.get();
      }
      return result;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static Map getRemoteDegest() {
    Map result = new HashMap();
    BufferedInputStream stream = null;

    try {
      URL u = new URL(baseUrl + "/digest?token=" + token);
      LOG.info(baseUrl + "/digest?token=" + token);
      ByteArrayOutputStream bo = new ByteArrayOutputStream();
      stream = new BufferedInputStream(u.openStream());
      byte[] buffer = new byte[1024];
      int length = stream.read(buffer);
      while (length > 0) {
        bo.write(buffer, 0, length);
        length = stream.read(buffer);
      }
      String backStr = new String(bo.toByteArray(), "UTF-8");
      String[] entrys = backStr.split(",");
      for (int i = 0; i < entrys.length; i++) {
        String[] kv = entrys[i].split(":");
        if (kv.length > 1) {
          result.put(kv[0], kv[1]);
        } else {
          result.put(kv[0], "");
        }
      }
      return result;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    } finally {
      if (stream != null) {
        try {
          stream.close();
        } catch (IOException e) {
          // TCJLODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
  }
}
