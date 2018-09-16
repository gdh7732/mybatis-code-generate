package com.mnt.generate.utils.generate;

public class GenerateNameUtils {
    public static String getClassFileName(String tableName) {
        String[] nameArrs = tableName.split("_");

        StringBuilder sbStr = new StringBuilder("");
        String[] arrayOfString1;
        int j = (arrayOfString1 = nameArrs).length;
        for (int i = 0; i < j; i++) {
            String str = arrayOfString1[i];
            if (isAppend(str)) {
                sbStr.append(upperFristStr(str));
            }
        }

        return sbStr.toString();
    }


    public static String getJavaName(String tableName) {
        String[] nameArrs = tableName.split("_");

        if (nameArrs.length < 2) {
            return tableName;
        }

        StringBuilder sbStr = new StringBuilder("");
        String[] arrayOfString1;
        int j = (arrayOfString1 = nameArrs).length;
        for (int i = 0; i < j; i++) {
            String str = arrayOfString1[i];
            if (isAppend(str)) {
                if (sbStr.length() == 0) {
                    sbStr.append(str);
                } else {
                    sbStr.append(upperFristStr(str));
                }
            }
        }

        return sbStr.toString();
    }


    private static String upperFristStr(String str) {
        if ((str == null) || ("t".equals(str)) || (str.isEmpty())) {
            return "";
        }

        if (str.length() < 2) {
            return str;
        }

        String fristStr = str.substring(0, 1);
        String otherStr = str.substring(1, str.length());

        return fristStr.toUpperCase() + otherStr;
    }


    private static boolean isAppend(String str) {
        if ((str == null) || (str.isEmpty()) || (str.equals("t"))) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        System.err.println(getClassFileName("user_info"));
        System.err.println(getJavaName("user_info"));
    }
}
