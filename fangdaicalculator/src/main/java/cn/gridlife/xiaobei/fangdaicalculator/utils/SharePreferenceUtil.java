package cn.gridlife.xiaobei.fangdaicalculator.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by BZB on 2017/11/30.
 */

public class SharePreferenceUtil {
    public static final String MY_PREFERENCE = "pf";
    /**
     * 配置文件标识
     */
    private final static String CONFIG_SIGN = "SW_Settings";

    /**
     * 存储信息
     *
     * @param context Activity的上下文
     * @param value   对象值
     */
    public static void setConfigValue(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(CONFIG_SIGN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 获取配置信息
     *
     * @param context Activity的上下文
     * @param key     对象类型
     * @return 对象值
     */
    public static String getConfigValue(Context context, String key, String def) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(CONFIG_SIGN, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, def);
    }

    private static void paraCheck(SharedPreferences sp, String key) {
        if (sp == null) {
            throw new IllegalArgumentException();
        }
        if (TextUtils.isEmpty(key)) {
            throw new IllegalArgumentException();
        }
    }

    public static boolean setObjectToShare(Context context, Object object, String key) {
        // TODO Auto-generated method stub
        SharedPreferences share = PreferenceManager
                .getDefaultSharedPreferences(context);
        if (object == null) {
            SharedPreferences.Editor editor = share.edit().remove(key);
            return editor.commit();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        // 将对象放到OutputStream中
        // 将对象转换成byte数组，并将其进行base64编码
        String objectStr = new String(Base64.encode(baos.toByteArray(),
                Base64.DEFAULT));
        try {
            baos.close();
            oos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SharedPreferences.Editor editor = share.edit();
        // 将编码后的字符串写到base64.xml文件中
        editor.putString(key, objectStr);
        return editor.commit();
    }

    public static Object getObjectFromShare(Context context, String key) {
        SharedPreferences sharePre = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            String wordBase64 = sharePre.getString(key, "");
            // 将base64格式字符串还原成byte数组
            if (wordBase64 == null || wordBase64.equals("")) { // 不可少，否则在下面会报java.io.StreamCorruptedException
                return null;
            }
            byte[] objBytes = Base64.decode(wordBase64.getBytes(),
                    Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(objBytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            // 将byte数组转换成product对象
            Object obj = ois.readObject();
            bais.close();
            ois.close();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isObjectEqual(Context context, String key,
                                        Object newValue) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG_SIGN,
                Context.MODE_PRIVATE);
        paraCheck(sp, key);
        if (newValue == null) {
            return false;
        } else {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(baos);
                oos.writeObject(newValue);
            } catch (IOException e1) {
                e1.printStackTrace();
                return false;
            }
            String newValueBase64 = new String(Base64.encode(
                    baos.toByteArray(), Base64.DEFAULT));
            String oldValueBase64 = sp.getString(key, "");
            return newValueBase64.equals(oldValueBase64);
        }
    }
}