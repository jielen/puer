package com.ufgov.zc.client.ftp.apache.utils;


import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
 

public class Test {
	
	private static Logger logger=Logger.getLogger(Test.class);

	public static void main(String[] args) {

		PropertyConfigurator.configure("./log4j.properties");
		
		/*try {
			ContinueFTP continueFTP = new ContinueFTP();
			boolean connect = continueFTP.connect("127.0.0.1", Integer.parseInt("21"), "jielen", "123456");
			if(connect){ 
				continueFTP.pwd(continueFTP.getFtpClient());
				UploadStatus upload=continueFTP.CreateDirecroty("/中文目录/", continueFTP.getFtpClient());
				continueFTP.pwd(continueFTP.getFtpClient());
				System.out.println("upload" + upload);
				UploadStatus upload2 = continueFTP.upload( "D:\\360安全浏览器下载\\中国人民财产保险股份有限公司车险电子保单.zip", "/test/你好/中国人民财产保险股份有限公司车险电子保单.zip");
//				
				continueFTP.pwd(continueFTP.getFtpClient());
				System.out.println("upload" + upload2); 
				 
				
//				continueFTP.download("/中文目录/中国人民财产保险股份有限公司车险电子保单.zip", "D:\\temp\\中国人民财产保险股份有限公司车险电子保单.zip", "P");
				
			}
			
				continueFTP.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		
		
		try {
			 
			FtpUtils fu = new FtpUtils();
			
			//获取一个文件的大小
//			System.out.println(fu.getSize(new File("D:\\360安全浏览器下载\\ftpdata\\中文目录\\中国人民财产保险股份有限公司车险电子保单.zip")));
			//获取一个文件夹的大小
//			System.out.println(fu.getSize(new File("D:\\360安全浏览器下载\\ftpdata\\中文目录")));
			
			FTPClient ftpClient=fu.createFtpClient("127.0.0.1", Integer.parseInt("21"), "jielen", "123456","/");
			 
			if(ftpClient!=null){ 
				/*
				System.out.println(fu.pwd(ftpClient));
				UploadStatus upload=fu._createDirs("/中文目录/", ftpClient);
				System.out.println(fu.pwd(ftpClient));
				System.out.println("upload" + upload);
				UploadStatus upload2 = fu.uploadFile(ftpClient, "D:\\360安全浏览器下载\\中国人民财产保险股份有限公司车险电子保单.zip", "/test/你好/中国人民财产保险股份有限公司车险电子保单.zip");				
				System.out.println(fu.pwd(ftpClient));
				System.out.println("upload" + upload2); 
				*/ 

				FtpProgressObserver observer=new FtpProgressObserver();
				FtpProgressObservable observable=new FtpProgressObservable();
				observable.addObserver(observer);
				
				ftpClient.setBufferSize(1024*1024*10);
				
				//上传一个文件 
//				fu.upload(ftpClient, "D:\\360安全浏览器下载\\ftpdata\\中文目录\\中国人民财产保险股份有限公司车险电子保单.zip", "/",observable);
				//上传一个大文件文件 
//				fu.upload(ftpClient, "D:\\360安全浏览器下载\\金一南从百年沧桑到大国崛起.mp4", "/",observable);
				
				//上传一个目录
//				fu.upload(ftpClient, "D:\\360安全浏览器下载\\ftpdata\\中文目录", "/",observable);
				
				//当前目录下是否有同名
//				System.out.println(fu.isSameName(ftpClient, "中文"));
				 

				//下载指定文件
//				fu.downloadFile(ftpClient,"/金一南从百年沧桑到大国崛起.mp4", "D:\\360安全浏览器下载\\ftpdata\\download",observable);

				//下载指定目录及包含的子文件夹、文件
//				fu.downloadDir(ftpClient,"/中文目录", "D:\\360安全浏览器下载\\ftpdata\\download",observable );

				//删除单个文件
//				System.out.println(fu.removeFile(ftpClient, "中国人民财产保险股份有限公司车险电子保单.zip"));
				
				//删除一个目录
//				System.out.println(fu.removeDirectory(ftpClient, "中文目录"));
				
				//重命名一个目录
//				System.out.println(fu.rename(ftpClient, "中国人民财产保险股份有限公司车险电子保单.zip","电子保单.zip"));

				//获取文件大小
//				logger.debug(fu.getRemoteSize(ftpClient, "金一南从百年沧桑到大国崛起.mp4"));
				//获取文件夹大小
//				logger.debug(fu.getRemoteSize(ftpClient, "中文目录"));
				
				fu.disconnect(ftpClient); 
			} 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			/*String fstr="D:\\360安全浏览器下载\\中国人民财产保险股份有限公司车险电子保单.zip";
			File file=new File(fstr);
			System.out.println(file.getPath());
			System.out.println(file.getName());
			System.out.println(file.getAbsolutePath());*/
		 
	}
}
