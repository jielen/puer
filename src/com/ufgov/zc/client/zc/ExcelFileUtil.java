package com.ufgov.zc.client.zc;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.apache.log4j.Logger;

import com.ufgov.zc.client.zc.ztb.activex.WordPane;
import com.ufgov.zc.common.system.model.AsFile;

public class ExcelFileUtil extends WordFileUtil {
	private static final Logger logger = Logger.getLogger(ExcelFileUtil.class);

	static String fileExtName = ".xls";

	static String defaultModelFileId = "OPT_SF_BLANK_EXCEL_FILE_ID";
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
//	      fullFileName = createFile(fileID + fileExtName, fileContent);
	      fullFileName = createFile(System.currentTimeMillis() + ".xls", fileContent);
	    } catch (Exception e) {
	      logger.error(e.getMessage(), e);
	    }
	    return fullFileName;
	  }
	public static String doSaveExcelFile(String fileName,
			WordPane selfWordPane, Component self) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		chooser.setDialogTitle("保存公告到:");
		String path = "c:/" + fileName;

		ExcelFileFilter wordFileter = new ExcelFileFilter();
		chooser.addChoosableFileFilter(wordFileter);

		chooser.setSelectedFile(new File(path));
		int r = chooser.showSaveDialog(self);
		if (r == JFileChooser.APPROVE_OPTION) {
			path = chooser.getSelectedFile().getPath();
		}

		selfWordPane.save(path);
		return new File(".").getAbsolutePath();
	}

	public static class ExcelFileFilter extends FileFilter {
		public String getDescription() {
			return "*.xls";
		}

		public boolean accept(File file) {
			String name = file.getName();
			return name.toLowerCase().endsWith(".xls");
		}
	}
}
