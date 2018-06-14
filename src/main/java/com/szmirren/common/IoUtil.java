package com.szmirren.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * io工具类用于操作io相关
 * 
 * @author duhua
 *
 */
public class IoUtil {
	/**
	 * 将字符串存储为文件
	 * 
	 * @param path
	 * @param materi
	 * @param codeFormat
	 * @throws Exception
	 */
	public static void StrToFile(Path path, String materi, String codeFormat) throws Exception {
		OutputStream out = Files.newOutputStream(path, StandardOpenOption.CREATE);
		OutputStreamWriter writer = new OutputStreamWriter(out, codeFormat);
		writer.write(materi);
		writer.flush();
		out.close();
		writer.close();
	}

	/**
	 * 将字符串存储为文件
	 * 
	 * @param dir
	 *            目录
	 * @param fileName
	 *            文件名称
	 * @param materi
	 *            字符串内容
	 * @param codeFormat
	 *            字符编码方式
	 * @throws Exception
	 */
	public static void StrToFile(Path dir, String fileName, String materi, String codeFormat) throws Exception {
		if (!isExists(dir)) {
			createDir(dir);
		}
		Path path = Paths.get(dir.toString(), fileName);
		OutputStream out = Files.newOutputStream(path, StandardOpenOption.CREATE);
		OutputStreamWriter writer = new OutputStreamWriter(out, codeFormat);
		writer.write(materi);
		writer.flush();
		out.close();
		writer.close();
	}

	/**
	 * 将字符串存储为文件,如果源文件存并且mode==true在则删除
	 * 
	 * @param dir
	 *            目录
	 * @param fileName
	 *            文件名称
	 * @param materi
	 *            字符串内容
	 * @param codeFormat
	 *            字符编码方式
	 * @param mode
	 *            true删除原文件
	 * @throws Exception
	 */
	public static void StrToFile(Path dir, String fileName, String materi, String codeFormat, boolean mode)
			throws Exception {
		if (mode) {
			Path path = Paths.get(dir.toString(), fileName);
			if (Files.exists(path)) {
				Files.delete(path);
			}
		}
		StrToFile(dir, fileName, materi, codeFormat);
	}

	/**
	 * 通过读取文件并转换为utf-8的字符串
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String fileToStr(String path) throws Exception {
		return fileToStr(path, "UTF-8");
	}

	/**
	 * 通过读取文件并转换为字符串
	 * 
	 * @param path
	 *            文件地址
	 * @param charset
	 *            字符编码
	 * @return
	 * @throws Exception
	 */
	public static String fileToStr(String path, String charset) throws Exception {
		Path uri = Paths.get(path);
		InputStream fis = Files.newInputStream(uri);
		int size = fis.available();
		byte[] buffer = new byte[size];
		fis.read(buffer);
		if (fis != null) {
			fis.close();
		}
		return new String(buffer, charset);
	}

	/**
	 * 判断文件或文件夹是否存在
	 * 
	 * @param path
	 * @return
	 */
	public static boolean isExists(String path) {
		return Files.exists(Paths.get(path));
	}

	/**
	 * 判断文件夹是否存在
	 * 
	 * @param path
	 * @return
	 */
	public static boolean isExists(Path path) {
		return Files.exists(path);
	}

	/**
	 * 判断文件或文件夹是否存在
	 * 
	 * @param path
	 * @return
	 */
	public static boolean isExists(Path dir, String fileName) {
		return Files.exists(Paths.get(dir.toString(), fileName));
	}

	/**
	 * 创建文件夹
	 * 
	 * @param dir
	 * @return
	 * @throws IOException
	 */
	public static Path createDir(String dir) throws IOException {
		return Files.createDirectories(Paths.get(dir));
	}

	/**
	 * 创建文件夹
	 * 
	 * @param dir
	 * @return
	 * @throws IOException
	 */
	public static Path createDir(Path path) throws IOException {
		return Files.createDirectories(path);
	}

	/**
	 * 将字符串装换成路径
	 * 
	 * @param str
	 * @return
	 */
	public static String toURL(String str) {
		if (StrUtil.isNullOrEmpty(str)) {
			return "";
		}
		String result = str.replace(".", "/");
		return result;
	}

	/**
	 * 将字符串装换成路径
	 * 
	 * @param str
	 * @return
	 */
	public static String toURL(String... str) {
		if (StrUtil.isNullOrEmpty(str)) {
			return "";
		}
		StringBuffer result = new StringBuffer();
		for (String temp : str) {
			result.append(toURL(temp));
		}
		return result.toString();
	}

}
