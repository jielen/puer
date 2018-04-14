/**
 * 
 */
package com.ufgov.zc.client.ftp.apache.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.table.DefaultTableModel;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 */
public class FtpUtils {

  private static Logger logger=Logger.getLogger(FtpUtils.class);
  
  /** 本地字符编码 */
  private static String LOCAL_CHARSET = "GBK";

  // FTP协议里面，规定文件名编码为iso-8859-1
  private static String SERVER_CHARSET = "ISO-8859-1";

  // 枚举类UploadStatus代码

  public enum UploadStatus {
    Create_Directory_Fail, // 远程服务器相应目录创建失败
    Create_Directory_Success, // 远程服务器闯将目录成功
    Upload_New_File_Success, // 上传新文件成功
    Upload_New_File_Failed, // 上传新文件失败
    File_Exits, // 文件已经存在
    Remote_Bigger_Local, // 远程文件大于本地文件
    Upload_From_Break_Success, // 断点续传成功
    Upload_From_Break_Failed, // 断点续传失败
    Delete_Remote_Faild; // 删除远程文件失败
  }

  // 枚举类DownloadStatus代码
  public enum DownloadStatus {
    Remote_File_Noexist, // 远程文件不存在
    Local_Bigger_Remote, // 本地文件大于远程文件
    Download_From_Break_Success, // 断点下载文件成功
    Download_From_Break_Failed, // 断点下载文件失败
    Download_New_Success, // 全新下载文件成功
    Download_New_Failed; // 全新下载文件失败
  }

  // 枚举类DeleteStatus代码
  public enum DeleteStatus {
    Remote_File_Noexist, // 远程文件不存在 
    Delete_Success, // 删除成功
    Delete_Failed; // 删除失败 
  }

  // 枚举类DeleteStatus代码
  public enum OtherStatus {
    Remote_File_Noexist, // 远程文件不存在 
    Rename_Success, // 重命名成功
    Rename_Have_Same_File, //有同名文件
    Rename_Have_Same_Dir, //有同名目录
    Rename_Failed; // 重命名失败 
  }

  public FTPClient createFtpClient(FtpConfig ftpConfig) throws IOException{
    if(ftpConfig==null)return null;
    return createFtpClient(ftpConfig.getServer(), ftpConfig.getPort(), ftpConfig.getUsername(), ftpConfig.getPassword(),ftpConfig.getRoot());
  }
  /**
   * ftp用户登陆后，有对应的根目录，如果希望当前用户用这个程序时，只能在其ftp根目录下的目标目录里活动，则可以指定这个值
   * ftp根目录中没有对应的目标目录，会创建这个目录
   * @param hostname
   * @param port
   * @param username
   * @param password
   * @param targetDir 允许访问的目标目录，如/test/abc/
   * @return
   * @throws IOException
   */
  public FTPClient createFtpClient(String hostname, int port, String username, String password,String targetDir) throws IOException {
    FTPClient ftpClient = new FTPClient();
    // 设置将过程中使用到的命令输出到控制台
    ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
    boolean sucess=false;
      // 连接到FTP服务器
      ftpClient.connect(hostname, port);
      // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
      if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {

        if (ftpClient.login(username, password)) {
          // 设置字符编码
          if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {// 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
            LOCAL_CHARSET = "UTF-8";
          }
          ftpClient.setControlEncoding(LOCAL_CHARSET);
          ftpClient.enterLocalPassiveMode();// 设置被动模式  
          ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

          ftpClient.setBufferSize(1024*1024*10);
          if(targetDir!=null){
            boolean chdir=ftpClient.changeWorkingDirectory(targetDir);
            if(!chdir){//没有指定的根目录，尝试在ftp用户的根目录下，创建对应的目录
              createDir(targetDir, ftpClient);
            }
          }
          return ftpClient;
        }
      }
      if(!sucess){
        logger.error("创建ftp链接失败"+hostname+" "+port+" "+username );
      }
 
    return null;
  } 

 
     
    //本方法用于下载FTP上的目录结构到本地中  
  
  /**
   * 下载远程目录
   * @param ftpClient
   * @param remoteDir 远程对象，可以是目录，也可以是文件
   * @param localPath 本地存放的目录
   * @throws IOException
   */
    public void downloadDir(FTPClient ftpClient,String remoteDir,String localPath,FtpProgressObservable observable) throws IOException{  
        if (remoteDir!=null && !remoteDir.equals("")) { 
          /*if(!remoteDir.startsWith("/")){
            remoteDir="/"+remoteDir;
          }*/
          /*if(!remoteDir.equals("/") && remoteDir.endsWith("/")){
            remoteDir=remoteDir.substring(0,remoteDir.length()-1);
          }*/
         
            if(observable.getProcessArg()==null){
              ProgressArg pArg=new ProgressArg(0, 0, 0);
              observable.setProcessArg(pArg);
            }
            if(observable.getProcessArg().getMax()<=0){
              observable.getProcessArg().setMax(getRemoteSize(ftpClient, remoteDir));
            }
            
            //在本地建立一个相同的文件目录  
            File localPathFile = new File(localPath);  
            if(!localPathFile.isDirectory()){
              localPathFile.mkdirs();  
            }
            //获得目录在本地的绝对路径  
            localPath = localPathFile.getAbsolutePath();  
//            System.out.println(localPath);  
            //获得FTPFile对象数组  
            FTPFile[] ftpFiles = ftpClient.listFiles(codeLocalToServer(remoteDir));  
            if (ftpFiles!=null && ftpFiles.length>0) {  
                for (int i = 0; i < ftpFiles.length; i++) {  
                    FTPFile subFile = ftpFiles[i];  
                    if(subFile.getName().equals(".") || subFile.getName().equals("..")){
                      if(subFile.getName().equals(".")){ 
                          localPath+="\\"+remoteDir;
                          localPathFile = new File(localPath);  
                              if(!localPathFile.isDirectory()){
                                localPathFile.mkdirs();  
                              }
                         
                      }
                      continue;
                    }
                    //调用自身方法，进行下一层级目录循环  
                  ftpClient.changeWorkingDirectory(codeLocalToServer(remoteDir)); 
                    //判断是否为目录结构  
                    if (subFile.isDirectory()) {  
                        //如果为目录结构  
                        downloadDir(ftpClient,subFile.getName(), localPath,observable);  
                    } else {  
                        //如果不为目录结构,为文件类型  
                      File file=new File(localPath+"\\"+subFile.getName());
                      if(file.exists()){
                        //应该支持续传下载，暂时未实行
                        file.delete();
                      } 
                        _downloadFile(ftpClient, subFile.getName(), localPath+"/"+subFile.getName(),observable); 
                    } 
                    ftpClient.cdup();
                }  
            }  
        }  
    } 
    
   

    public DownloadStatus downloadFile(FTPClient ftpClient,String remoteFile, String localPath, FtpProgressObservable observable) throws IOException  {     
      //获取远程文件名称
      String fileName=remoteFile;
      if(remoteFile.lastIndexOf("/")>0){
        fileName=remoteFile.substring(remoteFile.lastIndexOf("/")+1);
      } 
      localPath+="/"+fileName;
    return _downloadFile(ftpClient, remoteFile, localPath,observable);
    }
    /**
     * 下载指定文件
     * @param ftpClient
     * @param remoteFile 远程文件
     * @param localFile  本地文件
     * @return
     * @throws IOException
     */
  private DownloadStatus _downloadFile(FTPClient ftpClient,String remoteFile, String localFile,FtpProgressObservable observable) throws IOException {
 
    // 下载状态
    DownloadStatus result;

    // 本地文件列表
    File f = new File(localFile);

    // 检查远程文件是否存在
    FTPFile[] files = ftpClient.listFiles(new String(remoteFile.getBytes(LOCAL_CHARSET), SERVER_CHARSET));

    if (files.length != 1) {
      System.out.println("远程文件不存在");
      return DownloadStatus.Remote_File_Noexist;
    }  
    ProgressArg pArg=observable.getProcessArg();
    if(pArg==null){
      pArg=new ProgressArg(0, 0, 0);
      observable.setProcessArg(pArg);
    }
    if(pArg.getMax()<=0){
      pArg.setMax(files[0].getSize());
    }
    /*
    ProgressArg pArg=observable.getProcessArg();
        if(observable.getProcessArg()==null){
          pArg=new ProgressArg(0, 0, 0);
          pArg.setMax(getRemoteSize(ftpClient, remoteFile));
          observable.setProcessArg(pArg);
        }*/

    // 获得远端文件大小
    long lRemoteSize = files[0].getSize();

    // 构建输出对象
    OutputStream out = null;

    // 本地存在文件，进行断点下载 ；不存在则新下载
    if (f.exists()) {

      // 构建输出对象
      out = new FileOutputStream(f, true);

      // 本地文件大小
      long localSize = f.length();

      System.out.println("本地文件大小为:" + localSize);

      // 判定本地文件大小是否大于远程文件大小
      if (localSize >= lRemoteSize) {
        System.out.println("本地文件大于远程文件，下载中止");
        return DownloadStatus.Local_Bigger_Remote;
      }

      // 否则进行断点续传，并记录状态
      ftpClient.setRestartOffset(localSize); // 该方法尝试把指针移动到远端文件的指定字节位置

      InputStream in = ftpClient.retrieveFileStream(new String(remoteFile.getBytes(LOCAL_CHARSET), SERVER_CHARSET));

      byte[] bytes = new byte[1024];
      long step = lRemoteSize / 100;

      // 存放下载进度
      long process = localSize / step;
      int c;
      long agM=1024*1024*10,ag=0l;
      while ((c = in.read(bytes)) != -1) {
        out.write(bytes, 0, c);
        ag+=c;
        if(c<1024 || ag>=agM){
          pArg.setValue(ag+pArg.getValue());        
          observable.setProcessArg(pArg);
          ag=0l;
        }       
        /*localSize += c;
        long nowProcess = localSize / step;
        if (nowProcess > process) {
          process = nowProcess;
          if (process % 2 == 0)
            System.out.println("下载进度：" + process);
          // TODO更新文件下载进度,值存放在process变量中
        }*/
      }
      // 下载完成关闭输入输出流对象
      in.close();
      out.close();
      boolean isDo = ftpClient.completePendingCommand();
      if (isDo) {
        result = DownloadStatus.Download_From_Break_Success;
      } else {
        result = DownloadStatus.Download_From_Break_Failed;
      }

    } else {
      out = new FileOutputStream(f);
      InputStream in = ftpClient.retrieveFileStream(new String(remoteFile.getBytes(LOCAL_CHARSET), SERVER_CHARSET));
      byte[] bytes = new byte[1024];
      long step = lRemoteSize / 100;
      long process = 0;
      long localSize = 0L;
      int c;
      long agM=1024*1024*10,ag=0l;
      while ((c = in.read(bytes)) != -1) {
        out.write(bytes, 0, c);
        ag+=c;
        if(c<1024 || ag>=agM){
          pArg.setValue(ag+pArg.getValue());        
          observable.setProcessArg(pArg);
          ag=0l;
        }       
        /*localSize += c;
        long nowProcess = localSize / step;
        if (nowProcess > process) {
          process = nowProcess;
          if (process % 10 == 0)
            System.out.println("下载进度：" + process);
          // TODO更新文件下载进度,值存放在process变量中
        }*/
      }
      in.close();
      out.close();
      boolean upNewStatus = ftpClient.completePendingCommand();
      if (upNewStatus) {
        result = DownloadStatus.Download_New_Success;
      } else {
        result = DownloadStatus.Download_New_Failed;
      }
    }
    return result;
  }
   
  /** */
  /**
   * 上传文件到FTP服务器，支持断点续传
   * 
   * @param localFile
   *            本地文件名称，绝对路径
   * @param remoteFile
   *            远程文件路径，使用/home/directory1/subdirectory/file.ext或是
   *            http://www.guihua.org /subdirectory/file.ext
   *            按照Linux上的路径指定方式，支持多级目录嵌套，支持递归创建不存在的目录结构
   * @return 上传结果
   * @throws IOException
   */
  private UploadStatus uploadFile(FTPClient ftpClient,String localFile, String remoteFile,FtpProgressObservable observable) throws IOException {
    // 设置PassiveMode传输
    ftpClient.enterLocalPassiveMode();
    // 设置以二进制流的方式传输
    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    // ftpClient.setControlEncoding("GBK");
    UploadStatus result;
    // 对远程目录的处理
    String remoteFileName = remoteFile;
    if (remoteFile.contains("/")) {
      remoteFileName = remoteFile.substring(remoteFile.lastIndexOf("/") + 1);
      // 创建服务器远程目录结构，创建失败直接返回
      String remotePath=remoteFile.substring(0, remoteFile.lastIndexOf("/"));
      if (createDir(remotePath, ftpClient) == UploadStatus.Create_Directory_Fail) {
        return UploadStatus.Create_Directory_Fail;
      }
    }

    // 检查远程是否存在文件
    FTPFile[] files = ftpClient.listFiles(new String(remoteFileName.getBytes(LOCAL_CHARSET), SERVER_CHARSET));
    if (files.length == 1) {
      long remoteSize = files[0].getSize();
      File f = new File(localFile);
      long localSize = f.length();
      if (remoteSize == localSize) {
        return UploadStatus.File_Exits;
      } else if (remoteSize > localSize) {
        return UploadStatus.Remote_Bigger_Local;
      }

      // 尝试移动文件内读取指针,实现断点续传
      result = uploadFile(remoteFileName, f, ftpClient, remoteSize,observable);

      // 如果断点续传没有成功，则删除服务器上文件，重新上传
      if (result == UploadStatus.Upload_From_Break_Failed) {
        if (!ftpClient.deleteFile(remoteFileName)) {
          return UploadStatus.Delete_Remote_Faild;
        }
        result = uploadFile(remoteFileName, f, ftpClient, 0,observable);
      }
    } else {
      result = uploadFile(remoteFileName, new File(localFile), ftpClient, 0,observable);
    }
    return result;
  }
   
     
   
  /**
   * 递归创建远程服务器目录
   * 
   * @param remote
   *            远程服务器文件绝对路径
   * @param ftpClient
   *            FTPClient 对象
   * @return 目录创建是否成功
   * @throws IOException
   */
  public UploadStatus createDir(String remote, FTPClient ftpClient) throws IOException {
    UploadStatus status = UploadStatus.Create_Directory_Success;
    if(!remote.endsWith("/")){
      remote+="/";
    }
    String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
    if (!directory.equalsIgnoreCase("/") && !ftpClient.changeWorkingDirectory(new String(directory.getBytes(LOCAL_CHARSET), SERVER_CHARSET))) {
      // 如果远程目录不存在，则递归创建远程服务器目录
      int start = 0;
      int end = 0;
      if (directory.startsWith("/")) {
        start = 1;
      } else {
        start = 0;
      }
      end = directory.indexOf("/", start);
      while (true) {
        String subDirectory = new String(remote.substring(start, end).getBytes(LOCAL_CHARSET), SERVER_CHARSET);
        if (!ftpClient.changeWorkingDirectory(subDirectory)) {
          if (ftpClient.makeDirectory(subDirectory)) {
            ftpClient.changeWorkingDirectory(subDirectory);
          } else {
            logger.error("创建目录"+remote.substring(start, end)+"失败");
//            System.out.println("创建目录失败");
            return UploadStatus.Create_Directory_Fail;
          }
        }

        start = end + 1;
        end = directory.indexOf("/", start);

        // 检查所有目录是否创建完毕
        if (end <= start) {
          break;
        }
      }
    }
    return status;
  }

  /** */
  /**
   * 上传文件到服务器,新上传和断点续传
   * 
   * @param remoteFile
   *            远程文件名，在上传之前已经将服务器工作目录做了改变
   * @param localFile
   *            本地文件 File句柄，绝对路径
   * @param processStep
   *            需要显示的处理进度步进值
   * @param ftpClient
   *            FTPClient 引用
   * @return
   * @throws IOException
   */
  private UploadStatus uploadFile(String remoteFile, File localFile, FTPClient ftpClient, long remoteSize,FtpProgressObservable observable) throws IOException {
    UploadStatus status;
    
    ProgressArg pArg=observable.getProcessArg();
    pArg.setMin(pArg.getMin()+remoteSize);
    observable.setProcessArg(pArg);
    
    // 显示进度的上传
    long step = localFile.length() / 100;
    long process = 0;
    long localreadbytes = 0L;
    RandomAccessFile raf = new RandomAccessFile(localFile, "r");
    OutputStream out = ftpClient.appendFileStream(new String(remoteFile.getBytes(LOCAL_CHARSET), SERVER_CHARSET));
    // 断点续传
    if (remoteSize > 0) {
      ftpClient.setRestartOffset(remoteSize);
      process = remoteSize / step;
      raf.seek(remoteSize);
      localreadbytes = remoteSize;
    }
    byte[] bytes = new byte[1024];
    int c;
    long agM=1024*1024*10,ag=0l;
    while ((c = raf.read(bytes)) != -1) {
      out.write(bytes, 0, c);
      ag+=c;
      if(c<1024 || ag>=agM){
        pArg.setValue(ag+pArg.getValue());        
        observable.setProcessArg(pArg);
        ag=0l;
      }       
//      if(pArg.getValue()==500l){
//        System.out.println(pArg.getPer());
//      }
      
      /*localreadbytes += c;       
      if (localreadbytes / step != process) {
        process = localreadbytes / step;
        System.out.println("上传进度:" + process);
        // TODO 汇报上传状态
      }*/
    }
    out.flush();
    raf.close();
    out.close();
    boolean result = ftpClient.completePendingCommand();
    if (remoteSize > 0) {
      status = result ? UploadStatus.Upload_From_Break_Success
          : UploadStatus.Upload_From_Break_Failed;
    } else {
      status = result ? UploadStatus.Upload_New_File_Success
          : UploadStatus.Upload_New_File_Failed;
    }
    return status;
  }

  /**
   * 获取当前的ftp目录
   * 
   * @param ftpClient
   * @return
   * @throws IOException 
   */
  public String pwd(FTPClient ftpClient) throws IOException {
    String rtn=null;
    String[] rt; 
      rt = ftpClient.doCommandAsStrings("pwd", "");
      for(int i=0;i<rt.length;i++){
        rt[i]=new String(rt[i].getBytes(SERVER_CHARSET), LOCAL_CHARSET);
      }
      Pattern p = Pattern.compile("\"(.*?)\"");
      Matcher m = p.matcher(rt[0]);
      if (m.find()) {
        rtn= m.group(0).replace("\"", "");
//        System.out.println(rtn);
      }
      
      
//      for (int i = 0; i < rt.length; i++) {
//        System.out.println(rt[i]);
//      }  

    
    return rtn;

  }
    
    
  /**
   * 上传整个目录到FTP的指定目录中  ,包括目录下面的子目录和文件
   * @param ftpClient
   * @param localName 本地文件夹或者文件
   * @param remotePath 服务器目录
   * @param observable 传输进度观察,用于观察当前的传输进度
   * @throws IOException
   */
    public void upload(FTPClient ftpClient,String localName,String remotePath,FtpProgressObservable observable) throws IOException{  
        if (localName!=null && !localName.equals("")) {  
            //建立上传目录的File对象  
            File dirFile = new File(localName);  
            ProgressArg pArg=observable.getProcessArg();
            if(pArg==null){
              pArg=new ProgressArg(0, 0, 0);
              observable.setProcessArg(pArg);
            }
            if(pArg.getMax()<=0){
              pArg.setMax(getLocalSize(dirFile));
            }
            //判断File对象是否为目录类型  
            if (dirFile.isDirectory()) {  
                //跳转到FTP的根目录层级  
              ftpClient.changeWorkingDirectory("/");   
              if(remotePath==null || remotePath.trim().length()==0 || remotePath.trim().equals("/")){
                remotePath="";
              }
                UploadStatus status=createDir(remotePath+"/"+dirFile.getName(), ftpClient);
                if(UploadStatus.Create_Directory_Fail.equals(status)){
                  logger.error("上传目录失败");
                  return;
                }
                //获得File对象中包含的子目录数组  
                File[] subFiles = dirFile.listFiles();  
                //路径  
                String newRemotePath = remotePath+"/"+dirFile.getName();   
                //判断数组是否为空  
                if (subFiles!=null && subFiles.length>0) {  
                    //遍历整个File数组  
                    for (int i = 0; i < subFiles.length; i++) {  
                        //判断是否为目录类型  
                        if (subFiles[i].isDirectory()) {  
                            upload(ftpClient,subFiles[i].getAbsolutePath(), newRemotePath,observable);  
                        } else {   
                            uploadFile(ftpClient, subFiles[i].getAbsolutePath(), newRemotePath+"/"+subFiles[i].getName(),observable);
                        }  
                    }  
                }  
            } else {   
                uploadFile(ftpClient, dirFile.getAbsolutePath(), remotePath+"/"+dirFile.getName(),observable);
                 
            }  
        }  
    }  
    
    /**
     * 获取本地文件或者文件夹的大小，以字节为单位进行统计
     * @param dirFile
     * @return
     */
  public long getLocalSize(File file) {
    long total=0l;
    if(!file.isDirectory()){
      total=total+file.length();
    }else{
      File[] childs=file.listFiles();
      for(int i=0;i<childs.length;i++){
        total+=getLocalSize(childs[i]);
      }
    }
    return total;
  }
  public void disconnect(FTPClient ftpClient) throws IOException {
    if (ftpClient.isConnected()) {
      ftpClient.disconnect();
    }
  }
   
    /** 
     * 判断指定文件中是否存在相同名称的文件 
     *  
     * @param remotePath :FTP上的远程目录 
     * @param fileName:文件名称 
     * @return boolean :判断是否存在相同名称 
     * @throws IOException 
     *  
     */  
    public boolean isSameName(FTPClient ftpClient,String name) throws IOException {  
        boolean bool = false;  
            FTPFile[] ftpFiles = ftpClient.listFiles(); 
            for (int i = 0; i < ftpFiles.length; i++) {  
                if (name.equals(ftpFiles[i].getName())) {  
//                    System.out.println("存在和指定文件相同名称的文件");  
                    bool = true;  
                } else {  
                    bool = false;  
                }  
            }  
        return bool;  
    } 

    /**
     * 删除指定文件
     * 
     * @param filePath 文件路径(含文件名)
     * @see [类、类#方法、类#成员]
     * @return SUCCESS:成功 其他:失败信息
     * @throws IOException 
     */
    public DeleteStatus removeFile(FTPClient ftpClient,String filePath) throws IOException
    {
         
//            if (StringUtils.isNotEmpty(filePath))
        if (isNotEmpty(filePath))
            {
                if (!ftpClient.deleteFile(codeLocalToServer(filePath)))
                {
                    return DeleteStatus.Delete_Failed ;
                }
            }
        
        return DeleteStatus.Delete_Success;
    }
    
    /**
     * 删除Ftp上的文件夹 包括其中的文件 <功能详细描述>
     * 
     * @param client Ftp对象
     * @param pathName 文件夹路径
     * @return SUCCESS:成功 其他:失败
     * @throws IOException 
     * @see [类、类#方法、类#成员] Create Author:<> Time:<Aug 18, 2014> Ver:< >
     */
    public DeleteStatus removeDirectory(FTPClient ftpClient,String pathName) throws IOException
    {
      if (pathName==null || pathName.equals("/")||pathName.trim().equals("")) {
      return DeleteStatus.Delete_Success;
    }
      /*if(!pathName.startsWith("/")){
        pathName="/"+pathName;
      } */
            FTPFile[] files = ftpClient.listFiles(codeLocalToServer(pathName));
            if (null != files && files.length > 0)
            {
              ftpClient.changeWorkingDirectory(codeLocalToServer(pathName));
                for (FTPFile file : files)
                {
                    if (file.isDirectory())
                    {
                      if(file.getName().equals(".") || file.getName().equals("..")){
                        continue;
                      }  
                        removeDirectory(ftpClient,file.getName()); 
                    }
                    else
                    {
                        if (!ftpClient.deleteFile(codeLocalToServer(file.getName())))
                        {
                          logger.error("删除指定文件夹" + file.getName() + "失败.");
                            return DeleteStatus.Delete_Failed;
                        } 
                    }
                }
                ftpClient.cdup();
                if (!ftpClient.removeDirectory(codeLocalToServer(pathName)))
                {
                  logger.error("删除指定文件夹" + pathName + "失败.");
                    return DeleteStatus.Delete_Failed;
                } 
                
            } 
            
            return DeleteStatus.Delete_Success;
         
    }
     
    /**
     * 获取当前远程目录下的远程文件夹或文件的大小
     * @param ftpClient
     * @param target 远程文件夹或文件 
     * @return
     * @throws IOException 
     */
    private long getRemoteSize(FTPClient ftpClient,String target) throws IOException
    {
      long rtn=0l;  
            FTPFile[] files = ftpClient.listFiles(codeLocalToServer(target));
            if (null != files && files.length > 0)
            {
                for (FTPFile subFile : files)
                {
                    if (subFile.isDirectory())
                    {
                      if(subFile.getName().equals(".") || subFile.getName().equals("..")){
                        continue;
                      } 
                        //调用自身方法，进行下一层级目录循环  
                        ftpClient.changeWorkingDirectory(codeLocalToServer(target)); 
                        rtn+= getRemoteSize(ftpClient,subFile.getName()); 
                        ftpClient.cdup();
                    }
                    else
                    {
                      rtn+= subFile.getSize(); 
                    }
                }
            }          
        return rtn;
    }
    /**
     * 重命名
     * @param ftpClient
     * @param srcFname
     * @param targetFname
     * @return
     * @throws IOException 
     */
    public  boolean rename(FTPClient ftpClient,String srcFname,String targetFname) throws IOException{  
        boolean flag = false; 
            flag = ftpClient.rename(codeLocalToServer(srcFname),codeLocalToServer(targetFname));  
            return flag;   
    }
    
    /**
   * 将本地编码，转换为服务器码
   * @param str
   * @return
   */
    private String codeServerToLocal(String str) {
    try {
      return new String(str.getBytes(SERVER_CHARSET),LOCAL_CHARSET);
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      StringBuilder sb=new StringBuilder();
      sb.append("字符串").append(str).append("从").append(SERVER_CHARSET).append("转码为").append(LOCAL_CHARSET).append("异常.n/");
      sb.append(e.getMessage());
      logger.error(sb.toString(),e);
    }
    return str;
  }
    
  /**
   * 将本地编码，转换为服务器码
   * @param str
   * @return
   */
    private String codeLocalToServer(String str) {
    try {
      return new String(str.getBytes(LOCAL_CHARSET),SERVER_CHARSET);
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      StringBuilder sb=new StringBuilder();
      sb.append("字符串").append(str).append("从").append(LOCAL_CHARSET).append("转码为").append(SERVER_CHARSET).append("异常.n/");
      sb.append(e.getMessage());
      logger.error(sb.toString(),e);
    }
    return str;
  }


    /**
     * 将指定目录的内容列出，并转化为tableModel
     * 如果没有指定目录，则列出当前连接的目录下的内容
     * 如果指定了目录，则在当前连接下，查看是否有指定的文件夹，如果有，则列出其内容。
     * @param ftpClient
     * @param remoteDir
     * @param tableModel 
     * @return
     * @throws IOException
     */
    public DefaultTableModel getRemoteListToModel(FTPClient ftpClient, String remoteDir, DefaultTableModel tableModel) throws IOException {
      FTPFile[] ftpFiles;
      String path=pwd(ftpClient);
      if (remoteDir == null || remoteDir.trim().length()==0 || remoteDir.trim().equals("/")
          || remoteDir.trim().equals(".") || remoteDir.trim().equals("./")
          ||"..".equals(remoteDir)) {
        
        if("..".equals(remoteDir)){
          ftpClient.cdup();
          path=pwd(ftpClient);
          remoteDir=null;
        } 
        
        ftpFiles = ftpClient.listFiles();
      } else {
        /*if (!remoteDir.startsWith("/")) {
          remoteDir = "/" + remoteDir;
        }*/
        if (remoteDir.endsWith("/")) {
          remoteDir = remoteDir.substring(0, remoteDir.length() - 1);
        }
        ftpFiles = ftpClient.listFiles(codeLocalToServer(remoteDir));  
      }

      if (ftpFiles != null && ftpFiles.length > 0) {  

        if(remoteDir!=null && !"..".equals(remoteDir)){
          if(path.endsWith("/")){
            ftpClient.changeWorkingDirectory(codeLocalToServer(path+remoteDir));             
          }else{
            ftpClient.changeWorkingDirectory(codeLocalToServer(path+"/"+remoteDir));            
          }
          path=pwd(ftpClient);
        } 
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
//        DefaultTableModel model=new DefaultTableModel(new Object[][] {}, new String[] { "文件名", "大小", "日期" });
        tableModel.setRowCount(0);//清除了历史数据
        for (int i = 0; i < ftpFiles.length; i++) {
          FTPFile subFile = ftpFiles[i];
          MyFtpFile myFtpFile=new MyFtpFile();
          if(subFile.isDirectory()){
            myFtpFile.setDir(true);
          }else{
            myFtpFile.setLongSize(subFile.getSize());
          }
          myFtpFile.setLastDate(df.format(subFile.getTimestamp().getTime()));
          myFtpFile.setName(subFile.getName()); 
          myFtpFile.setPath(path);
          // 将文件信息添加到表格中
          tableModel.addRow(new Object[] { myFtpFile, myFtpFile.getSize(),myFtpFile.getLastDate() });
          
        }
        
        return tableModel;
      } 
      return null;
    }
    
    public final static double K=1024;
    public final static double M=K*1024;
    public final static double G=M*1024;
    
    /**
     * 将长度转换为可读的中文
     * @param len
     * @return
     */
    public String parseLength(long len){
      double dl=len;
      DecimalFormat df = new DecimalFormat("0.00");
      if(dl>G){ 
        return df.format(dl/G)+"G";
      }
      if(dl>M){
        return df.format(dl/M)+"M";
      }
      if(dl>K){
        return df.format(dl/K)+"K";
      }
      return ""+len+"B";
    }
    
    public static String parseDate(Date date,String partern){
      if(partern==null || partern.trim().length()==0){
        partern="yyyy-MM-dd";
      }
      SimpleDateFormat df = new SimpleDateFormat(partern);
      return date== null ? null : df.format(date);
    }
    public static String parseDate(Date date) {
      return parseDate(date,null);
    }
    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    public  boolean deleteLocal(File file) {
        if (file.isDirectory()) {
          File[] children = file.listFiles(); 
            for (int i=0; i<children.length; i++) {
//                return deleteLocal(new File(file, children[i])); 
              deleteLocal(children[i]); 
            }
        }
        // 目录此时为空，可以删除
        return file.delete();
    }
    
    /**
     * 截取一个字符串里不同部分
     * @param subStr
     * @param sourceString
     * @return
     */
    public static String subTailStr(String subStr,String sourceString){ 
      if(subStr==null || subStr.trim().equals("/")){
        return sourceString;
      }
      if(!subStr.startsWith("/")){
        subStr="/"+subStr;
      }
      if(!subStr.endsWith("/")){
        subStr+="/";
      }
      if(sourceString==null || sourceString.trim().equals("/")){
        return sourceString;
      }
      if(!sourceString.startsWith("/")){
        sourceString="/"+subStr;
      }
      if(!sourceString.endsWith("/")){
        sourceString+="/";
      }
      if(sourceString.length()<=subStr.length()){
        return sourceString;
      }
      if(sourceString.startsWith(subStr)){
        sourceString=sourceString.substring(subStr.length()-1,sourceString.length()-1);
      } 
      return sourceString;
    }
    public boolean isNotEmpty(String str){
      if(str==null || str.trim().length()==0){
        return false;
      }
      return true;
    }
}
