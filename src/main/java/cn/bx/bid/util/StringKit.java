package cn.bx.bid.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringKit {
    private StringKit() {

    }

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public static Date toDate(String str) {
        try {
            return df.parse(str);
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return null;
    }

    public static int toInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return 0;
    }

    public static Long toLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return 0L;
    }

    public static String convertNull(Object o) {
        if (o == null)// || o.equals("null"))
            return "";
        return String.valueOf(o);
    }

    public static boolean isNotNull(Object data) {
        return data != null;
    }
}
