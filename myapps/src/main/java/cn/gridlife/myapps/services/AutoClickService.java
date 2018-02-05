package cn.gridlife.myapps.services;

import android.content.Intent;
import android.graphics.Point;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.gridlife.generallibrary.services.BaseService;
import cn.gridlife.myapps.MainActivity;

/**
 * Created by BZB on 2018/1/1.
 * project Speach
 */

public class AutoClickService extends BaseService {

    private Point point;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    private void autoClick(float width, float height) {
//        if (width == 0) {
//            width = point.x;
//            height = point.y;
//        }
        //每10s产生一次点击事件，点击的点坐标为(0.2W - 0.8W,0.2H - 0.8 H),W/H为手机分辨率的宽高.
        float finalWidth = width;
        float finalHeight = height;
        ExecutorService fixedThreadPool= Executors.newFixedThreadPool(3);
        for (int i = 0; i < 30; i++) {
            final int finalI = i;
            Runnable runnable = new Runnable(){
                @Override
                public void run() {
                    SystemClock.sleep(3000);
                    Log.d("google_lenve_fb", "run: "+ finalI);
                }
            };
            fixedThreadPool.execute(runnable);
        }

        new Thread(() -> {
            int count = 0;
            while (true) {
                //生成点击坐标
                int x = (int) (Math.random() * finalWidth * 0.6 + finalWidth * 0.2);
                int y = (int) (Math.random() * finalHeight * 0.6 + finalHeight * 0.2);
                //利用ProcessBuilder执行shell命令
                String[] order = {
                        "input",
                        "swipe",
                        "" + x,
                        "" + x,
                        "" + x,
                        "" + x,
                        "2000"
                };
                try {
                    new ProcessBuilder(order).start();
                    count++;
                    Log.e("点击次数", count + "");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //线程睡眠10s
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        autoClick(200, 200);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
