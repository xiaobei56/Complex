package cn.gridlife.generallibrary.thread;


import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

/**
 * Created by BZB on 2018/1/19.
 * project Speach
 */

public class MainThreadExecutor implements Executor {
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(Runnable runnable) {
        handler.post(runnable);
    }

}
