package cn.gridlife.generallibrary.utils;



public class NoDoubleClickUtil {
    public static final int DELAY = 800;
    private static long lastClickTime = 0;


    public static boolean isNotFastClick() {
        long currentTime = System.currentTimeMillis();
        long time = Math.abs(currentTime - lastClickTime);
        if (time > DELAY) {
            lastClickTime = currentTime;
            return true;
        } else {
            return false;
        }
    }
}