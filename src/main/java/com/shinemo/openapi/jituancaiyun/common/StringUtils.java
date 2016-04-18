package com.shinemo.openapi.jituancaiyun.common;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StringUtils {
	/**
	 * InputStream -> String(输入流转换字符串)
	 */
	public static String getStringFromStream(InputStream input) {
		String body = null;

		try {
			ByteArrayOutputStream bao = new ByteArrayOutputStream(1024);
			byte[] bb = new byte[1024];
			int len = 0;
			while ((len = input.read(bb)) > 0) {
				bao.write(bb, 0, len);
			}

			body = new String(bao.toByteArray(), "UTF-8");
		} catch (Exception e) {
			body = "";
			throw new RuntimeException("", e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
					throw new RuntimeException(
							"Cannot close input stream. cause:", e);
				}
			}
		}

		return body;
	}
}
