package cn.gridlife.generallibrary.utils;


import cn.sdisframework.utils.LogUtil;

public class NoDoubleClickUtil {
    public static final int DELAY = 800;
    private static long lastClickTime = 0;


    public static boolean isNotFastClick() {
        long currentTime = System.currentTimeMillis();
        long time = Math.abs(currentTime - lastClickTime);
        LogUtil.d("currentTime - lastClickTime =" + time);
        if (time > DELAY) {
            lastClickTime = currentTime;
            return true;
        } else {
            return false;
        }
    }
}