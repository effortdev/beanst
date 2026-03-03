package com.util;

import java.io.InputStream;
import java.util.Properties;
import jakarta.servlet.ServletContext;

public class SqlManager {

	private static Properties props = new Properties();

	public static void load(ServletContext context) {
		try {
			InputStream input = context.getResourceAsStream("/WEB-INF/config/sql.xml");
			/*
			 * System.out.println("input null 여부: " + (input == null));
			 * System.out.println("실제 경로: " +
			 * context.getRealPath("/WEB-INF/config/sql.xml"));
			 */

			if (input == null) {
				throw new RuntimeException("sql.xml 파일을 찾을 수 없습니다.");
			}

			props.loadFromXML(input);

		} catch (Exception e) {
			throw new RuntimeException("SqlManager 초기화 실패", e);
		}
	}

	public static String get(String key) {
		return props.getProperty(key);
	}
}