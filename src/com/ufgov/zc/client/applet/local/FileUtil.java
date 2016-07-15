package com.ufgov.zc.client.applet.local;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class FileUtil {

	public static String readFile(File f) throws Exception {
		if (f != null && f.exists()) {
			BufferedInputStream bs = null;
			try {
				bs = new BufferedInputStream(new FileInputStream(f));
				ByteArrayOutputStream bi = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int length = bs.read(buffer);
				while (length > 0) {
					bi.write(buffer, 0, length);
					length = bs.read(buffer);
				}
				return new String(bi.toByteArray(), "UTF-8");
			} finally {
				try {
					bs.close();
				} catch (IOException e) {
					// TCJLODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			return "";
		}
	}

	public static boolean deleteFolder(File file) {
		boolean flag = false;
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				return deleteFile(file);
			} else { // 为目录时调用删除目录方法
				return deleteDirectory(file);
			}
		}
	}

	public static boolean deleteFolder(String fileName) {
		boolean flag = false;
		File file = new File(fileName);
		return deleteFolder(file);
	}

	public synchronized static String getFileDegest(File f) {
		try {
			InputStream source = new FileInputStream(f);
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			BufferedInputStream bSource = new BufferedInputStream(source);
			byte[] buffer = new byte[1024];
			int length = bSource.read(buffer);
			while (length > 0) {
				bo.write(buffer, 0, length);
				length = bSource.read(buffer);
			}
			bSource.close();
			String result = DigestUtil.digest(bo.toByteArray());
			return result;
		} catch (Exception ex) {
			return "-1";
		}
	}

	private static boolean deleteFile(File file) {
		boolean flag = false;
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	private static boolean deleteDirectory(File dirFile) {

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
				flag = deleteFile(files[i]);
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i]);
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}
}
