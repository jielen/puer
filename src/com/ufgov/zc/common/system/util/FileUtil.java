package com.ufgov.zc.common.system.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

public class FileUtil {

	public final static int BUFFER_SIZE = 4096;

	/**
	 * 文件解压缩 ztb
	 * 
	 * @param zipFile
	 * @param destination
	 */
	public static void unzipFileToDestDir(ZipFile zipFile, String destination)
			throws Exception {
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		Enumeration emu = zipFile.getEntries();
		try {
			while (emu.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) emu.nextElement();
				if (entry.isDirectory()) {
					new File(destination + File.separator + entry.getName())
							.mkdirs();
					continue;
				}
				bis = new BufferedInputStream(zipFile.getInputStream(entry));
				File file = new File(destination + File.separator
						+ entry.getName());
				File parent = file.getParentFile();
				if (parent != null && (!parent.exists())) {
					parent.mkdirs();
				}
				fos = new FileOutputStream(file);
				bos = new BufferedOutputStream(fos, BUFFER_SIZE);
				int count;
				byte data[] = new byte[BUFFER_SIZE];
				while ((count = bis.read(data, 0, BUFFER_SIZE)) != -1) {
					bos.write(data, 0, count);
				}
				bos.flush();
				bos.close();
				bis.close();
			}
			zipFile.close();
		} catch (Exception e) {
			e.printStackTrace();
			if (bos != null) {
				bos.close();
			}
			if (fos != null) {
				fos.close();
			}
			if (bis != null) {
				bis.close();
			}
			throw new Exception(e.getMessage());
		}
	}
}
