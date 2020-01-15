package cn.bx.bid.util;

import org.apache.log4j.Logger;

public class LogUtil {
    private static Logger Log= Logger.getLogger(LogUtil.class);
    private LogUtil(){}

    public static void debug(Object o){
        if(Log.isDebugEnabled())
            Log.debug(o);
    }
    public static void info(Object o){

        Log.info(o);
    }
    public static void error(Object o){

        Log.error(o);
    }
    public static void error(Object o,Exception e){

        Log.error(o,e);
    }
}
