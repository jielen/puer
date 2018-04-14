package com.ufgov.zc.client.ftp.apache.panel.ftp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

import sun.net.*;

import com.ufgov.zc.client.ftp.apache.panel.queue.*;
import com.ufgov.zc.client.ftp.apache.utils.*;

/**
 * FTP文件管理模块的FTP文件下载队列的线程
 */
public class DownThread extends Thread {

  public static final Logger logger = Logger.getLogger(DownThread.class);

  private FtpPanel ftpPanel = null; // FTP资源管理面板

  private FTPClient ftpClient = null; // FTP控制类

  private boolean conRun = true; // 线程的控制变量

  private String path; // FTP的路径信息

  private Object[] curDownload; // 下载任务的数组

  /**
   * 构造方法
   * @param ftpPanel - FTP资源管理面板
   */
  public DownThread(FtpPanel ftpPanel) {
    this.ftpPanel = ftpPanel;
    FtpUtils fu = new FtpUtils();
    try {
      ftpClient = fu.createFtpClient(ftpPanel.getFtpConfig());
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
      logger.error("下载失败！", e1);
      return;
    }
    if (ftpClient == null) {
      logger.error("下载失败，无法获得ftp活动链接！");
      return;
    }

   /* 
    new Thread() { // 创建保持服务器通讯的线程
      public void run() {
        while (conRun) {
          try {
            Thread.sleep(30000);
            ftpClient.noop(); // 定时向服务器发送消息，保持连接
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    }.start();*/

    // 以30秒为间隔与服务器保持通讯
    final Timer timer = new Timer(30000, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try { 
          if(ftpClient!=null && ftpClient.isConnected()){
            ftpClient.noop(); 
          }
        } catch (IOException e1) {
          logger.error(e1.getMessage(), e1);
        }
      }
    });
    timer.start();
  }

  public void stopThread() {// 停止线程的方法
    conRun = false;
  }

   

  public void run() { // 线程业务方法
    while (conRun) {
      try {
        Thread.sleep(1000);
//        ftpClient.noop();
        curDownload = ftpPanel.queue.peek();
        if (curDownload == null) {
          continue;
        }
        MyFtpFile myFtpFile = (MyFtpFile) curDownload[0];
        File localFolder = (File) curDownload[1];
        if(!localFolder.exists()){
          logger.info("原下载的保存目录("+localFolder+")不存在，存放当前目录中:"+ftpPanel.frame.getLocalPanel().getCurrentFolder());
          localFolder=ftpPanel.frame.getLocalPanel().getCurrentFolder();
        }
        
        FtpUtils fUtils=new FtpUtils();
        
        //下载进度观察者
        FtpProgressObserver observer=new FtpProgressObserver();
        
        FtpProgressObservable observable=new FtpProgressObservable();
        observable.addObserver(observer);

        ProgressArg progressArg = new ProgressArg(0, 0, 0); //进度参数 
        observable.setProcessArg(progressArg);
        
        Object[] row = new Object[] { myFtpFile.getName(), myFtpFile.getSize(), "下载", progressArg};
        
        DownloadPanel downloadPanel = ftpPanel.frame.getDownloadPanel(); //下载队列面板
        downloadPanel.addRow(row);  //添加行
        logger.debug("下载文件:\t"+myFtpFile.getName()+"\t远程地址\t"+myFtpFile.getPath()+"\t存放本地目录\t"+localFolder.getAbsolutePath());
        
        ftpClient.changeWorkingDirectory(myFtpFile.getPath());
        
        if(myFtpFile.isDirectory()){
          fUtils.downloadDir(ftpClient, myFtpFile.getName(), localFolder.getAbsolutePath(), observable);
        }else {
          fUtils.downloadFile(ftpClient, myFtpFile.getName(), localFolder.getAbsolutePath(), observable);
        }
        
        ftpPanel.frame.getLocalPanel().refreshCurrentFolder();
        
        
        Object[] args = ftpPanel.queue.peek();
        // 判断队列顶是否为处理的上一个任务。
        if (curDownload == null || args == null || !curDownload[0].equals(args[0])) {
          continue;
        }
        
        
        ftpPanel.queue.poll();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}