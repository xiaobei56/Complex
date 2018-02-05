package cn.gridlife.generallibrary.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import cn.gridlife.generallibrary.utils.LogUtils;

/**
 *
 * @author BZB
 * @date 2018/1/2
 * project Speech
 */

public class BaseService extends Service {
    final String TAG = getClass().getName();

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.i(TAG, "onCreate() ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.i(TAG, "onStartCommand() ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i(TAG, "onDestroy()");
    }

    @Override
    public void onLowMemory() {

        super.onLowMemory();
        LogUtils.i(TAG, "onLowMemory()");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtils.i(TAG, "onUnbind(Intent intent)");
        return super.onUnbind(intent);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.i(TAG, "onBind(Intent intent)");
        return null;
    }
}
