package com.demo.boot.core.util;

public class StringUtil {

    public static String nullToEmpty(String string) {
        return string == null ? "" : string;
    }

}
