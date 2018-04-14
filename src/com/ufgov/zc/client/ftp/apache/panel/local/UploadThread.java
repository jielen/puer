
package com.ufgov.zc.client.ftp.apache.panel.local;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Queue;

import javax.swing.Timer;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import com.ufgov.zc.client.ftp.apache.panel.ftp.FtpPanel;
import com.ufgov.zc.client.ftp.apache.panel.queue.DownloadPanel;
import com.ufgov.zc.client.ftp.apache.panel.queue.UploadPanel;
import com.ufgov.zc.client.ftp.apache.utils.FtpProgressObservable;
import com.ufgov.zc.client.ftp.apache.utils.FtpProgressObserver;
import com.ufgov.zc.client.ftp.apache.utils.FtpUtils;
import com.ufgov.zc.client.ftp.apache.utils.MyFtpFile;
import com.ufgov.zc.client.ftp.apache.utils.ProgressArg;

/**
 * FTP文件管理模块的本地文件上传队列的线程
 */
class UploadThread extends Thread {
 private static Logger logger=Logger.getLogger(UploadThread.class);
 
	private LocalPanel localPanel;
	String path = "";// 上传文件的本地相对路径
	String selPath;// 选择的本地文件的路径
	private boolean conRun = true; // 线程的控制变量
	private FTPClient ftpClient; // FTP控制类
	private Object[] queueValues; // 队列任务数组
  FtpUtils fu = new FtpUtils();

	 /**
	  * 创建上传队列线程的构造方法
	  * @param localPanel
	  */
	public UploadThread(LocalPanel localPanel) {
	  this.localPanel=localPanel;
    try {
      ftpClient = fu.createFtpClient(localPanel.getFtpConfig());
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
      logger.error("上传失败！", e1);
      return;
    }
    if (ftpClient == null) {
      logger.error("上传失败，无法获得ftp活动链接！");
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
    }.start();
    */

    // 以30秒为间隔与服务器保持通讯
    final Timer timer = new Timer(30000, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try { 
          if(ftpClient!=null && ftpClient.isConnected()){
            ftpClient.noop(); 
          }
        } catch (IOException e1) {
          logger.error("上传线程的ftp链接异常:"+e1.getMessage(), e1);
        }
      }
    });
    timer.start();
	} 

	public void stopThread() { // 停止线程的方法
		conRun = false;
	}
	 

	/**
	 * 线程的主体方法
	 */
	public void run() { // 线程的主体方法
		while (conRun) {
			try {
				Thread.sleep(2000); // 线程休眠1秒
				Queue<Object[]> queue = localPanel.queue; // 获取本地面板的队列对象
				queueValues = queue.peek(); // 获取队列首的对象
				if (queueValues == null) { // 如果该对象为空
					continue; // 进行下一次循环
				}

		    /*Object[] args = localPanel.queue.peek();
		    // 判断队列顶是不是上一个处理的任务。
		    if (queueValues == null || args == null || !queueValues[0].equals(args[0])){
		      return;
		    }*/
		      
		    
				File file = (File) queueValues[0]; // 获取队列中的本队文件对象
				MyFtpFile ftpFile = (MyFtpFile) queueValues[1]; // 获取队列中的FTP文件对象
				if (file != null) {
				  //上传进度观察者
	        FtpProgressObserver observer=new FtpProgressObserver();
	        
	        FtpProgressObservable observable=new FtpProgressObservable();
	        observable.addObserver(observer);

	        ProgressArg progressArg = new ProgressArg(0, 0, 0); //进度参数 
	        observable.setProcessArg(progressArg);
	        
	        /*String sizeString="";
	        if(file.isDirectory()){
	          sizeString="<DIR>";
	        }else{
	          sizeString=fu.parseLength(file.length());
	        }*/
	        Object[] row = new Object[] { file.getAbsoluteFile(), ftpFile.getSize(), "上传", progressArg };

	        UploadPanel uploadPanel = localPanel.frame.getUploadPanel();//上传面板
	         
	        uploadPanel.addRow(row);  //添加行
				  
				  fu.upload(ftpClient, file.getAbsolutePath(), ftpFile.getPath(), observable);  
				  
					FtpPanel ftpPanel = localPanel.frame.getFtpPanel();
					ftpPanel.refreshCurrentFolder(); // 刷新FTP面板中的资源
				}
				Object[] args = queue.peek();
				// 判断队列顶是否为处理的上一个任务。
				if (queueValues == null || args == null
						|| !queueValues[0].equals(args[0])) {
					continue;
				}
				queue.remove(); // 移除队列首元素
			} catch (Exception e) {
//				e.printStackTrace();
				logger.error("上传异常:"+e.getMessage(),e);
			}
		}
	}
}