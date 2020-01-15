package cn.bx.bid.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
	private StringUtil() {
	}

	public static boolean isBlank(String s) {
		return s == null || s.length() == 0;

	}

	private static SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd");

	public static Date toDate(String s) {
		try {
			return DF.parse(s);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			// e.printStackTrace();
		}
		return null;
	}
}