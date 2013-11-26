package com.evmtv.epg.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/**
 * 复制文件夹
 * @author fangzhu@evmtv.com
 * @time 2013-7-1 上午10:47:46
 * @project EAMS
 * @package com.evmtv.epg.utils 
 * @fileName CopyFolder.java
 * @enclosing_type 
 * @type_name CopyFolder TODO
 */
public class CopyFolder {
	/**
	 * 复制文件
	 * @param sourceFile 源文件
	 * @param targetFile 目标文件
	 * @throws IOException
	 */
	public static void copyFile(File sourceFile, File targetFile)
			throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			outBuff.flush();
		} finally {
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}

	/**
	 * 复制文件夹
	 * @param sourceDir 源文件夹
	 * @param targetDir 目标文件夹
	 * @throws IOException
	 */
	public static void copyDirectiory(String sourceDir, String targetDir)
			throws IOException {
		(new File(targetDir)).mkdirs();
		File[] files = (new File(sourceDir)).listFiles();
		for(File file : files){
			if (file.isFile()) {
				File sourceFile = file;
				File targetFile = new File(
						new File(targetDir).getAbsolutePath() + File.separator
								+ file.getName());
				copyFile(sourceFile, targetFile);
			}else if (file.isDirectory()) {
				String dir1 = sourceDir + "/" + file.getName();
				String dir2 = targetDir + "/" + file.getName();
				copyDirectiory(dir1, dir2);
			}
		}
	}

	/**
	 * 复制文件
	 * @param srcFileName 源文件
	 * @param destFileName 目标文件
	 * @param srcCoding 源编码
	 * @param destCoding 目标编码
	 * @throws IOException
	 */
	public static void copyFile(File srcFileName, File destFileName,
			String srcCoding, String destCoding) throws IOException {// ���ļ�ת��ΪGBK�ļ�
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					srcFileName), srcCoding));
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(destFileName), destCoding));
			char[] cbuf = new char[1024 * 5];
			int len = cbuf.length;
			int off = 0;
			int ret = 0;
			while ((ret = br.read(cbuf, off, len)) > 0) {
				off += ret;
				len -= ret;
			}
			bw.write(cbuf, 0, off);
			bw.flush();
		} finally {
			if (br != null)
				br.close();
			if (bw != null)
				bw.close();
		}
	}

	/**
	 * 删除文件目录
	 * @param filepath 文件路径
	 * @throws IOException
	 */
	public static void del(String filepath) throws IOException {
		File f = new File(filepath);
		if (f.exists() && f.isDirectory()) {
			if (f.listFiles().length == 0) {
				f.delete();
			} else {
				File[] delFile = f.listFiles();
				for (File file : delFile) {
					if (file.isDirectory()) {
						del(file.getAbsolutePath());
					}
					file.delete();
				}
			}
		}
	}
}