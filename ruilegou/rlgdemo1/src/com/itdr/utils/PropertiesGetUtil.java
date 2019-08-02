package com.itdr.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesGetUtil {
    public static String getValue(String key){
        Properties ps = new Properties();
        InputStream in = PropertiesGetUtil.
                class.getClassLoader().getResourceAsStream("const.properties");
        try {
            ps.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String value = ps.getProperty(key);
        return value;
    }

}
