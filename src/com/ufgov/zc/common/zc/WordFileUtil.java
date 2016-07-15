package com.ufgov.zc.common.zc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.ufgov.zc.common.system.model.AsFile;

public class WordFileUtil {
	private static Logger logger = Logger.getLogger(WordFileUtil.class);

	/**
	 * 删除文件，可以是单个文件或文件夹
	 * 
	 * @param fileName
	 *            待删除的文件名
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
	 * 
	 * @param fileName
	 *            被删除文件的文件名
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
	 * 
	 * @param dir
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true,否则返回false
	 */
	public static boolean deleteDirectory(String dir) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator)) {
			dir = dir + File.separator;
		}
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
			// 删除子目录
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

		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 创建文件
	 * 
	 * @param fileName
	 *            ：文件名称
	 * @param content
	 *            ：文件内容
	 * @return
	 * @throws Exception
	 */
	public static String createFile(String dir, String fileName, byte[] content)
			throws Exception {

		String fullFileName = dir + fileName;

		File file = new File(dir);
		if (!file.exists()) {
			file.mkdirs();
		}

		FileOutputStream fos = null;
		try {
			delete(fullFileName);

			fos = new FileOutputStream(fullFileName);

			fos.write(content);

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
	 * 获得AS_FILE中的文件内容
	 * 
	 * @param fileID
	 *            ： as_file表中的FILE_ID
	 * @return
	 */
	public static byte[] getFileContent(AsFile asFile) {
		byte[] fileContent = null;
		try {

			if (asFile != null && asFile.getFileContent() != null) {
				fileContent = asFile.getFileContent();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return fileContent;
	}

}
