package com.ufgov.zc.client.zc;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import org.apache.log4j.Logger;

import com.ufgov.smartclient.common.UIUtilities;
import com.ufgov.zc.client.common.AsOptionMeta;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.zc.ztb.activex.WordPane;
import com.ufgov.zc.common.commonbiz.publish.IBaseDataServiceDelegate;
import com.ufgov.zc.common.system.MimeMapping;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.model.AsFile;

public class WordFileUtil {
  private static final Logger logger = Logger.getLogger(WordFileUtil.class);

   static IBaseDataServiceDelegate baseDataServiceDelegate = (IBaseDataServiceDelegate) ServiceFactory.create(IBaseDataServiceDelegate.class,
    "baseDataServiceDelegate");

   static RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

   static BigDecimal maxSizeM = new BigDecimal(10);

   static boolean sizeLimit = true;

   static String tempDir = ZcUtil.getZcFileTempDir();

   static String dir;
   

	  static String fileExtName = ".doc";

	static String defaultModelFileId="OPT_ZC_BULLTIN_DEFAULT_FILE_ID";

  public static void setDir(String subDir) {
    if (subDir != null && subDir.length() > 0)
      dir = tempDir + "\\" + subDir + "\\";
    else
      dir = tempDir + "\\";
  }

  public static String getDir() {
    if (dir == null)
      return tempDir;
    return dir;
  }

  /**    
   * 删除文件，可以是单个文件或文件夹    
   * @param   fileName    待删除的文件名    
   * @return 文件删除成功返回true,否则返回false    
   */
  public static boolean delete(String fileName) {
    File file = new File(fileName);
    if (!file.exists()) {
      return false;
    } else {
      if (file.isFile()) {

        return deleteFile(fileName);
      } else {
        return deleteDirectory(fileName);
      }
    }
  }

  /**    
   * 删除单个文件    
   * @param   fileName    被删除文件的文件名    
   * @return 单个文件删除成功返回true,否则返回false    
   */
  public static boolean deleteFile(String fileName) {
    File file = new File(fileName);
    if (file.isFile() && file.exists()) {
      file.delete();
      return true;
    } else {
      return false;
    }
  }

  /**    
   * 删除目录（文件夹）以及目录下的文件    
   * @param   dir 被删除目录的文件路径    
   * @return  目录删除成功返回true,否则返回false    
   */
  public static boolean deleteDirectory(String dir) {
    //如果dir不以文件分隔符结尾，自动添加文件分隔符      
    if (!dir.endsWith(File.separator)) {
      dir = dir + File.separator;
    }
    File dirFile = new File(dir);
    //如果dir对应的文件不存在，或者不是一个目录，则退出      
    if (!dirFile.exists() || !dirFile.isDirectory()) {
      return false;
    }
    boolean flag = true;
    //删除文件夹下的所有文件(包括子目录)      
    File[] files = dirFile.listFiles();
    for (int i = 0; i < files.length; i++) {
      //删除子文件      
      if (files[i].isFile()) {
        flag = deleteFile(files[i].getAbsolutePath());
        if (!flag) {
          break;
        }
      }
      //删除子目录      
      else {
        flag = deleteDirectory(files[i].getAbsolutePath());
        if (!flag) {
          break;
        }
      }
    }

    if (!flag) {
      System.out.println("删除目录失败");
      return false;
    }

    //删除当前目录      
    if (dirFile.delete()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * 通过文件全名和文件id写文件
   * @param fullFileName
   * @param fileID
   * @return
   * @throws Exception
   */
  public static void createFile(String fullFileName, String fileID) throws Exception {
    File file = new File(fullFileName);
    if (!file.getParentFile().exists()) {
      file.mkdirs();
    }
    byte[] content = getFileContent(fileID);
    FileOutputStream fos = null;
    try {
      delete(fullFileName);

      fos = new FileOutputStream(fullFileName);

      fos.write(content);

    } catch (Exception ex) {
      fullFileName = "";
      JOptionPane.showMessageDialog(null, "从服务器载入文件(" + fullFileName + ")内容失败,检查服务器上此文件是否存在！", "错误", JOptionPane.ERROR_MESSAGE);
      throw ex;
    } finally {
      if (fos != null) {
        try {
          fos.close();
        } catch (IOException e) {
          logger.error(e.getMessage(), e);
        }
      }
    }
  }

  /**
   * 创建文件
   * @param fileName：文件名称
   * @param content：文件内容
   * @return
   * @throws Exception
   */
  public static String createFile(String fileName, byte[] content) throws Exception {

    String fullFileName = dir + fileName;

    File file = new File(dir);
    if (!file.exists()) {
      file.mkdirs();
    }
    deleteOldFile(file);
    FileOutputStream fos = null;
    try {
      delete(fullFileName);

      fos = new FileOutputStream(fullFileName);

      fos.write(content);

    } catch (Exception ex) {
      fullFileName = "";
      JOptionPane.showMessageDialog(null, "从服务器载入文件(" + fileName + ")内容失败,检查服务器上此文件是否存在！", "错误", JOptionPane.ERROR_MESSAGE);
    } finally {
      if (fos != null) {
        try {
          fos.close();
        } catch (IOException e) {
          logger.error(e.getMessage(), e);
        }
      }
    }
    return fullFileName;
  }

  /**
   * 删除一个月前的文件
   * @param dir
   */
  private static void deleteOldFile(File dir) {
	  File[] files=dir.listFiles();
	  Calendar rightNow = Calendar.getInstance(),fileDate = Calendar.getInstance();
	  rightNow.setTime(new Date());
	  
	  for(int i=0;i<files.length;i++){
		  Long lastModified=files[i].lastModified();
		  fileDate.setTimeInMillis(lastModified);
		  fileDate.add(Calendar.MONTH,1);
		  if(rightNow.getTimeInMillis()>fileDate.getTimeInMillis()){
			  files[i].delete();
		  }
	  }
}

public static boolean createFile(String path, String filename, JPanel panel, byte[] content) {
    boolean isSucceed = true;
    File file = new File(path);
    if (!file.exists()) {
      // 创建文件夹
      file.mkdirs();
    }
    FileOutputStream fos = null;
    OutputStreamWriter ow = null;
    try {
      // 先删除文件
      deleteFile(filename);
      // 创建文件
      fos = new FileOutputStream(filename);
      ow = new OutputStreamWriter(fos, "UTF-8");
      String cc = new String(content, "UTF-8");
      ow.write(cc);
      ow.flush();
    } catch (Exception ex) {
      isSucceed = false;
      logger.error("载入文件失败" + ex.getMessage(), ex);
      if (panel != null) {
        UIUtilities.showStaickTraceDialog(ex, panel, "载入文件失败", ex.getMessage());
      }
    } finally {
      if (ow != null) {
        try {
          ow.close();
        } catch (IOException e) {
          logger.error(e.getMessage(), e);
        }
      }
      if (fos != null) {
        try {
          fos.close();
        } catch (IOException e) {
          logger.error(e.getMessage(), e);
        }
      }
    }
    return isSucceed;
  }

  /**
   * 获得AS_FILE中的文件内容
   * @param fileID：  as_file表中的FILE_ID
   * @return 
   */
  public static byte[] getFileContent(String fileID) {
    byte[] fileContent = null;
    try {
      AsFile asFile = baseDataServiceDelegate.downloadFile(fileID, requestMeta);

      if (asFile != null && asFile.getFileContent() != null) {
        fileContent = asFile.getFileContent();
      }
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    return fileContent;
  }

  /**
   * 载入文件
   * @param fileID：  as_file表中的FILE_ID
   * @return
   */
  public static String loadMold(String fileID) {
    String fullFileName = "";
     
    try {
      AsFile asFile = baseDataServiceDelegate.downloadFile(fileID, requestMeta);

      byte[] fileContent = null;
      if (asFile != null && asFile.getFileContent() != null) {
        fileContent = asFile.getFileContent();
      }

      //下载到本地时,使用原来的fileID做新的文件名称
//      fullFileName = createFile(fileID + fileExtName, fileContent);
      fullFileName = createFile(System.currentTimeMillis() + fileExtName, fileContent);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    return fullFileName;
  }

  public static String loadDefaultMold() {
    String defaultFileID = AsOptionMeta.getOptVal(defaultModelFileId);
    return loadMold(defaultFileID);
  }

  public static String loadFundReportDefaultMold() {
    String defaultFileID = AsOptionMeta.getOptVal("OPT_ZC_FUND_REPORT_FILE_ID");
    return loadMold(defaultFileID);
  }

  public static String getExtension(File f) {
    String ext = null;
    String s = f.getName();
    int i = s.lastIndexOf('.');

    if (i > 0 && i < s.length() - 1) {
      ext = s.substring(i + 1).toLowerCase();
    }
    return ext;
  }

  /**
   * 上传文件内容
   * @param fileID：  as_file表中的FILE_ID
   * @param fileName：as_file表中的FILE_NAME
   * @return
   */
  public static boolean updateAsFileContent(String fileName, String fileID) {
    boolean flag = false;
    FileInputStream fis = null;
    File file = null;
    try {
      file = new File(fileName);
      fis = new FileInputStream(file);
      BigDecimal available = new BigDecimal(fis.available());
      BigDecimal mByte = new BigDecimal(1024 * 1024);
      BigDecimal resultSize = available.divide(mByte, 2, BigDecimal.ROUND_HALF_UP);

      if (sizeLimit && resultSize.compareTo(maxSizeM) > 0) {
        JOptionPane.showMessageDialog(null, "文件限制在" + maxSizeM + "m以内,此文件大于这个数不能上传！", "提示", JOptionPane.INFORMATION_MESSAGE);
        return flag;
      }

      byte[] content = new byte[fis.available()];
      fis.read(content);
      AsFile asFile = baseDataServiceDelegate.getAsFileById(fileID, requestMeta);
      asFile.setFileContent(content);
      asFile.setMimeType(MimeMapping.getMimeType(getExtension(file)));
      baseDataServiceDelegate.updateAsFileById(asFile, requestMeta);
      flag = true;
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      JOptionPane.showMessageDialog(null, "更新文件失败！\n" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
    } finally {
      if (fis != null) {
        try {
          fis.close();
        } catch (IOException e) {
          logger.error(e.getMessage(), e);
        }
      }
    }
    return flag;
  }

  public static String uploadWordFile(String fileName, String fileId) {
    if (fileId != null && fileId.trim().length() > 0) {
      updateAsFileContent(fileName, fileId);
    } else {
      fileId = insertAsFileContent(fileName);
    }
    return fileId;
  }

  public static String insertAsFileContent(String fileName) {
    // TCJLODO Auto-generated method stub

    String fileId = "";
    FileInputStream fis = null;
    File file = null;
    try {
      //fileName = fileName;
      file = new File(fileName);
      fis = new FileInputStream(file);
      BigDecimal available = new BigDecimal(fis.available());
      BigDecimal mByte = new BigDecimal(1024 * 1024);
      BigDecimal resultSize = available.divide(mByte, 2, BigDecimal.ROUND_HALF_UP);

      if (sizeLimit && resultSize.compareTo(maxSizeM) > 0) {
        JOptionPane.showMessageDialog(null, "文件限制在" + maxSizeM + "m以内,此文件大于这个数不能上传！", "提示", JOptionPane.INFORMATION_MESSAGE);
        return "";
      }

      byte[] content = new byte[fis.available()];
      fis.read(content);
      AsFile asFile = new AsFile();
      asFile.setFileContent(content);
      asFile.setFileName(file.getName());
      asFile.setMimeType(MimeMapping.getMimeType(getExtension(file)));
      fileId = baseDataServiceDelegate.uploadFile(asFile, requestMeta);
      asFile.setFileId(fileId);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      JOptionPane.showMessageDialog(null, "上传文件失败！\n" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
    } finally {
      if (fis != null) {
        try {
          fis.close();
        } catch (IOException e) {
          logger.error(e.getMessage(), e);
        }
      }
    }
    return fileId;
  }

  /**
   * 上传文件内容
   * @param fileID：  as_file表中的FILE_ID
   * @param fileName：as_file表中的FILE_NAME
   * @return
   */
  public static String uploadBulletinWordConstent(String fileName) {
    String fileId = "";
    FileInputStream fis = null;
    File file = null;
    try {
      //fileName = fileName;
      file = new File(fileName);
      fis = new FileInputStream(file);
      BigDecimal available = new BigDecimal(fis.available());
      BigDecimal mByte = new BigDecimal(1024 * 1024);
      BigDecimal resultSize = available.divide(mByte, 2, BigDecimal.ROUND_HALF_UP);

      if (sizeLimit && resultSize.compareTo(maxSizeM) > 0) {
        JOptionPane.showMessageDialog(null, "文件限制在" + maxSizeM + "m以内,此文件大于这个数不能上传！", "提示", JOptionPane.INFORMATION_MESSAGE);
        return "";
      }

      byte[] content = new byte[fis.available()];
      fis.read(content);
      AsFile asFile = new AsFile();
      asFile.setFileContent(content);
      asFile.setFileName(file.getName());
      asFile.setMimeType(MimeMapping.getMimeType(getExtension(file)));
      fileId = baseDataServiceDelegate.uploadFile(asFile, requestMeta);
      asFile.setFileId(fileId);
      //setToolTipText(file.getPath());
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      JOptionPane.showMessageDialog(null, "上传文件失败！\n" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
    } finally {
      if (fis != null) {
        try {
          fis.close();
        } catch (IOException e) {
          logger.error(e.getMessage(), e);
        }
      }
    }
    return fileId;
  }

  /**
   * 获得转换后的全网页文件名称
   * @param htmlFileName
   * @return 带路径的网页文件名称
   */
  public static String getHtmlFileName(String htmlFileName) {
    String htmlFilePath = AsOptionMeta.getOptVal("OPT_ZC_HTML_FILE_TEMP");
    File file = new File(htmlFilePath);
    if (!file.exists()) {
      file.mkdirs();
    }
    return htmlFilePath + htmlFileName;
  }

  //  /**
  //   * 获得下载到本地的全招标文件名称
  //   * @param attachFileName
  //   * @return 带路径的招标文件名称
  //   */
  //  public static String getAttachFileName(String attachFileName){    
  //     return AsOptionMeta.getOptVal("OPT_ZC_HTML_FILE_TEMP")+"//"+attachFileName;
  //  }
  //    
  /**
   * 获得发布到门户时招标文件附件的相对路径文件名称
   * @param fileName
   * @return
   */
  public static String getPortalAttachPathFileName(String fileName) {
    return AsOptionMeta.getOptVal("OPT_ZC_PORTAL_ATTACH_PATH") + "//" + fileName;
  }

  /**
   * 获得发布到门户时html的相对路径文件名称
   * @param fileName
   * @return
   */
  public static String getPortalHref(String fileName) {
    return AsOptionMeta.getOptVal("OPT_ZC_PORTAL_HREF_PATH") + fileName + ZcSettingConstants.HTML_FILE_SUFFIX;
  }

  public static String doSaveWordFile(String fileName, WordPane selfWordPane, Component self) {
    JFileChooser chooser = new JFileChooser();
    chooser.setDialogType(JFileChooser.SAVE_DIALOG);
    chooser.setDialogTitle("保存公告到:");
    String path = "c:/" + fileName;

    WordFileFilter wordFileter = new WordFileFilter();
    chooser.addChoosableFileFilter(wordFileter);

    chooser.setSelectedFile(new File(path));
    int r = chooser.showSaveDialog(self);
    if (r == JFileChooser.APPROVE_OPTION) {
      path = chooser.getSelectedFile().getPath();
    }

    selfWordPane.save(path);
    return new File(".").getAbsolutePath();
  }

  public static class WordFileFilter extends FileFilter {
    public String getDescription() {
      return "*.doc";
    }

    public boolean accept(File file) {
      String name = file.getName();
      return name.toLowerCase().endsWith(".doc");
    }
  }
}
