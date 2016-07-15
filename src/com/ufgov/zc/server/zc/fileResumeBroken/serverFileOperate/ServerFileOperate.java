package com.ufgov.zc.server.zc.fileResumeBroken.serverFileOperate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.ufgov.zc.common.system.model.AsFile;
import com.ufgov.zc.common.zc.model.DataExchangeRedo;
import com.ufgov.zc.server.system.SpringContext;
import com.ufgov.zc.server.system.service.IAsFileService;
import com.ufgov.zc.server.zc.service.IDataExchangeService;

public class ServerFileOperate {

  private IAsFileService asFileService;

  private String fileId;

  private String transStatus;

  private String errContent;

  private int serverFileLen;

  public String getFileId() {
    return fileId;
  }

  public void setFileId(String fileId) {
    this.fileId = fileId;
  }

  public String getTransStatus() {
    return transStatus;
  }

  public void setTransStatus(String transStatus) {
    this.transStatus = transStatus;
  }

  public String getErrContent() {
    return errContent;
  }

  public void setErrContent(String errContent) {
    this.errContent = errContent;
  }

  public int getServerFileLen() {
    return serverFileLen;
  }

  public void setServerFileLen(int serverFileLen) {
    this.serverFileLen = serverFileLen;
  }

  public void outPutStream(Map parameterMap, byte[] buff, String sequenceOrder) {
    String filePath = (String) parameterMap.get("SERVERFILEPATH");
    String parentPath = filePath.substring(0, filePath.lastIndexOf("/"));
    String thisFileId = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
    String offset = (String) parameterMap.get("BLOCK_OFFSET");
    if (offset == null) {
      transStatus = "PARA_MISS";
      errContent = "缺少参数：BLOCK_OFFSET";
      return;
    }
    int blockOffset = Integer.parseInt(offset);
    createFilePath(parentPath);

    try {
      FileAccessI oSavedFile = new FileAccessI(filePath, blockOffset);
      oSavedFile.write(buff, 0, buff.length);
    } catch (Exception e) {
      e.printStackTrace();
      this.transStatus = "SERVER_WRITE_ERR";
      this.errContent = e.getMessage();
      return;
    }
    this.transStatus = "TS_OK";
    IfFileTransSuccess ifFileTransSuccess = IfFileTransSuccess.getInstance((String) parameterMap.get("USERID"));
    ifFileTransSuccess.setServerFileTotalLen(ifFileTransSuccess.getServerFileTotalLen() + buff.length);
    ifFileTransSuccess.setLocalFileTotalLen(Integer.parseInt((String) parameterMap.get("LOCALFILETOTALLEN")));
    this.serverFileLen = ifFileTransSuccess.getServerFileTotalLen();
    if (ifFileTransSuccess.getServerFileTotalLen() == ifFileTransSuccess.getLocalFileTotalLen()) {
      ifFileTransSuccess.setServerFileTotalLen(0);//传输成功，此用户对应的本次传输文件在服务器端已传输的长度置为0
      //将文件插入数据库中
      AsFile asFile = new AsFile();
      asFile.setFileName((String) parameterMap.get("FILENAME"));
      System.out.println("fileName:" + (String) parameterMap.get("FILENAME"));
      asFile.setFilePath(filePath);
      asFile.setFileId(thisFileId);

      asFileService = (IAsFileService) SpringContext.getBean("asFileService");
      fileId = asFileService.insertAsFileDirectory(asFile);
    } else if (ifFileTransSuccess.getServerFileTotalLen() > ifFileTransSuccess.getLocalFileTotalLen()) {
      //如果服务器端的文件比客户端的文件大，那么肯定是在续传时选择了错误的文件，这时候应该将缓存信息以及服务器端的文件一同清理掉
      //如果MD5码校验发现不同时成功拦截了，这个分支是不会进入的
      this.transStatus = "SERVER_WRITE_ERR";
      this.errContent = "文件上传过程中出现未知错误，请重新开始...";
      ifFileTransSuccess.setLocalFileTotalLen(Integer.parseInt((String) parameterMap.get("LOCALFILETOTALLEN")));
      ifFileTransSuccess.setServerFileTotalLen(0);

    }
  }

  public void outPutStream_(Map parameterMap, byte[] buff, String sequenceOrder) {
    String filePath = (String) parameterMap.get("SERVERFILEPATH");
    String parentPath = filePath.substring(0, filePath.lastIndexOf("/"));
    String thisFileId = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
    String offset = (String) parameterMap.get("BLOCK_OFFSET");
    if (offset == null) {
      transStatus = "PARA_MISS";
      errContent = "缺少参数：BLOCK_OFFSET";
      return;
    }
    createFilePath(parentPath);
    File file = new File(filePath);

    FileOutputStream fileOutputStream = null;
    try {
      fileOutputStream = new FileOutputStream(file, true);
      fileOutputStream.write(buff);
      fileOutputStream.close();
    } catch (Exception e) {
      e.printStackTrace();
      transStatus = "SERVER_WRITE_ERR";
      errContent = "写文件出错：" + e.getMessage();
      if (fileOutputStream != null) {
        try {
          fileOutputStream.close();
        } catch (IOException e1) {
          errContent += "文件输出流关闭出错" + e1.getMessage();
          e1.printStackTrace();
        }
      }
      return;
    }

    IfFileTransSuccess ifFileTransSuccess = IfFileTransSuccess.getInstance((String) parameterMap.get("USERID"));
    ifFileTransSuccess.setServerFileTotalLen(ifFileTransSuccess.getServerFileTotalLen() + buff.length);
    ifFileTransSuccess.setLocalFileTotalLen(Integer.parseInt((String) parameterMap.get("LOCALFILETOTALLEN")));
    if (ifFileTransSuccess.getServerFileTotalLen() == ifFileTransSuccess.getLocalFileTotalLen()) {
      ifFileTransSuccess.setServerFileTotalLen(0);//传输成功，此用户对应的本次传输文件在服务器端已传输的长度置为0
      //将文件插入数据库中
      AsFile asFile = new AsFile();
      asFile.setFileName((String) parameterMap.get("FILENAME"));
      System.out.println("fileName:" + (String) parameterMap.get("FILENAME"));
      asFile.setFilePath(filePath);
      asFile.setFileId(thisFileId);

      asFileService = (IAsFileService) SpringContext.getBean("asFileService");
      fileId = asFileService.insertAsFileDirectory(asFile);
    } else if (ifFileTransSuccess.getServerFileTotalLen() > ifFileTransSuccess.getLocalFileTotalLen()) {
      //如果服务器端的文件比客户端的文件大，那么肯定是在续传时选择了错误的文件，这时候应该将缓存信息以及服务器端的文件一同清理掉
      //如果MD5码校验发现不同时成功拦截了，这个分支是不会进入的
      System.out.println("客户端续传时选择了错误的文件...");
      ifFileTransSuccess.setLocalFileTotalLen(Integer.parseInt((String) parameterMap.get("LOCALFILETOTALLEN")));
      ifFileTransSuccess.setServerFileTotalLen(0);
      file.delete();
    }
  }

  public void deleteFile(File file) {
    if (file.isFile() && file.exists())
      file.delete();
  }

  /**
   * 下载大文件
   * @param fileId
   */
  public void downLoadFile(String fileId) {
    asFileService = (IAsFileService) SpringContext.getBean("asFileService");
    AsFile asFile = asFileService.getLargeAsFileById(fileId);
    System.out.println("得到对象");
    InputStream in = asFile.getLargeFileStream();
    System.out.println("读流成功!");

    File file = new File("fileUploads/" + asFile.getFileName());
    try {
      FileOutputStream out = new FileOutputStream(file);
      byte[] buff = new byte[1024 * 1024];
      long start = System.currentTimeMillis();
      while (in.read(buff) != -1) {
        out.write(buff);
      }
      in.close();
      out.close();
      long end = System.currentTimeMillis();
      System.out.println("写文件用了:" + (end - start) / 1000 + "s");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void saveDataExchangeRedo(DataExchangeRedo dataExchangeRedo) {
    IDataExchangeService dataExchangeService = (IDataExchangeService) SpringContext.getBean("dataExchangeService");
    dataExchangeService.saveRedo(dataExchangeRedo);
  }

  public void createFilePath(String filePath) {
    File file = new File(filePath);
    if (!file.exists()) {
      file.mkdirs();
    }
  }
}
