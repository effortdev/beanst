package com.util;

import java.util.Properties;
import jakarta.servlet.ServletContext;

public class SqlManager {

    private static Properties props = new Properties();

    public static void load(ServletContext context) {
        try {
            props.load(context.getResourceAsStream(
                "/WEB-INF/config/sql.properties"
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}