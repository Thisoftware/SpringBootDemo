package com.demo.boot.api.util;

public class StringUtil {

    public static String nullToEmpty(String string) {
        return string == null ? "" : string;
    }

}
