package cn.gridlife.myapps.services;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import cn.gridlife.generallibrary.services.BaseService;
import cn.gridlife.generallibrary.utils.LogUtils;
import cn.gridlife.myapps.R;

/**
 * Created by BZB on 2018/1/3.
 * project Speach
 */

public class ButtonService extends BaseService {

    private static final String TAG = "ButtonService";
    ConstraintLayout constraintLayout;
    WindowManager.LayoutParams params;
    WindowManager windowManager;

    Button btnStart, btnStop;

    int statusBarHeight = -1;
    private int mFloatWinWidth;
    private int mFloatWinHeight;
    private int mFloatWinMarginTop;
    private int mFloatWinMarginRight;
    private View mFloatView;
    /**
     * onStartCommand 返回状态
     */
    private int f;

    @Override
    public void onCreate() {
        super.onCreate();
//        createToucher();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createWindowManager();
        createFloatView();
        f = START_STICKY;
        return super.onStartCommand(intent, f, startId);
    }

    private void createWindowManager() {
        //获取系统窗体
        windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        //计算得出悬浮窗体
        DisplayMetrics metrics = new DisplayMetrics();
        //C 语言写法，给metrics赋值
        windowManager.getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        mFloatWinWidth = (int) (screenWidth * 0.8 / 3);
        mFloatWinHeight = mFloatWinWidth * 4 / 3;
        mFloatWinMarginTop = (int) this.getResources().getDimension(R.dimen.dp_10);
        mFloatWinMarginRight = (int) this.getResources().getDimension(R.dimen.dp_10);
        LogUtils.i("mFloatWinWidth" + mFloatWinWidth + "mFloatWinHeight" + mFloatWinHeight + "mFloatWinMarginTop" + mFloatWinMarginTop + "mFloatWinMarginRight" + mFloatWinMarginRight);
        //窗体的布局样式
        //获取LayoutParams对象
        params = new WindowManager.LayoutParams();
        //确定悬浮窗类型，表示所有应用程序之上，但在状态栏之下
        params.type = WindowManager.LayoutParams.TYPE_TOAST;
        params.format = PixelFormat.RGBA_8888;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        //悬浮窗对其方式
        params.gravity = Gravity.CENTER;
        //悬浮窗的位置
        params.x = mFloatWinMarginRight;
        params.y = mFloatWinMarginTop;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
    }

    private int mLastX = 0, mLastY = 0;
    private int mStartX = 0, mStartY = 0;

    /**
     * 创建悬浮窗
     */
    @SuppressLint("ClickableViewAccessibility")
    private void createFloatView() {
        LayoutInflater inflater = LayoutInflater.from(ButtonService.this);
        mFloatView = inflater.inflate(R.layout.flow_view, null);
        btnStart = mFloatView.findViewById(R.id.btn_start);
        btnStop = mFloatView.findViewById(R.id.btn_stop);
        btnStart.setOnClickListener(v -> {
            startService(new Intent(this, AutoClickService.class));
        });
        btnStop.setOnClickListener(v -> Toast.makeText(this, "btnStop", Toast.LENGTH_SHORT).show());

        windowManager.addView(mFloatView, params);

        mFloatView.setOnTouchListener((v, event) -> {
            int action = event.getAction();
            if (MotionEvent.ACTION_DOWN == action) {
                mStartX = mLastX = (int) event.getRawX();
                mStartY = mLastY = (int) event.getRawY();

            } else if (MotionEvent.ACTION_UP == action) {
                int dx = (int) (event.getRawX() - mStartX);
                int dy = (int) (event.getRawY() - mStartY);
                if (Math.abs(dx) > 5 || Math.abs(dy) > 5) {
                    return true;
                }
            } else if (MotionEvent.ACTION_MOVE == action) {
                int dx = (int) (event.getRawX() - mLastX);
                int dy = (int) (event.getRawY() - mLastY);
                params.x = params.x - dx;
                params.y = params.y + dy;
                windowManager.updateViewLayout(mFloatView, params);
                mLastX = (int) event.getRawX();
                mLastY = (int) event.getRawY();
            }
            return false;
        });
    }

    private void removeFloatView() {
        if (mFloatView != null && windowManager != null) {
            windowManager.removeView(mFloatView);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void createToucher() {
        //赋值WindowManager&LayoutParam.
        params = new WindowManager.LayoutParams();
        windowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
        //设置type.系统提示型窗口，一般都在应用程序窗口之上.
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG;
        //设置效果为背景透明.
        params.format = PixelFormat.RGBA_8888;
        //设置flags.不可聚焦及不可使用按钮对悬浮窗进行操控.
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        //设置窗口初始停靠位置.
        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.x = 100;
        params.y = 100;

        //设置悬浮窗口长宽数据.
        //注意，这里的width和height均使用px而非dp.这里我偷了个懒
        //如果你想完全对应布局设置，需要先获取到机器的dpi
        //px与dp的换算为px = dp * (dpi / 160).
        params.width = 300;
        params.height = 800;

        LayoutInflater inflater = LayoutInflater.from(getApplication());
        //获取浮动窗口视图所在布局.
        constraintLayout = (ConstraintLayout) inflater.inflate(R.layout.flow_view, null);
        //添加toucherlayout
        windowManager.addView(constraintLayout, params);

        LogUtils.i(TAG, "toucherlayout-->left:" + constraintLayout.getLeft());
        LogUtils.i(TAG, "toucherlayout-->right:" + constraintLayout.getRight());
        LogUtils.i(TAG, "toucherlayout-->top:" + constraintLayout.getTop());
        LogUtils.i(TAG, "toucherlayout-->bottom:" + constraintLayout.getBottom());

        //主动计算出当前View的宽高信息.
        constraintLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        //用于检测状态栏高度.
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        LogUtils.i(TAG, "状态栏高度为:" + statusBarHeight);

        //浮动窗口按钮.
        //浮动窗口按钮.
        btnStart = (Button) constraintLayout.findViewById(R.id.btn_start);

        btnStart.setOnClickListener(new View.OnClickListener() {
            long[] hints = new long[2];

            @Override
            public void onClick(View v) {
                LogUtils.i(TAG, "点击了");
                System.arraycopy(hints, 1, hints, 0, hints.length - 1);
                hints[hints.length - 1] = SystemClock.uptimeMillis();
                if (SystemClock.uptimeMillis() - hints[0] >= 700) {
                    LogUtils.i(TAG, "要执行");
                    Toast.makeText(ButtonService.this, "连续点击两次以退出", Toast.LENGTH_SHORT).show();
                } else {
                    LogUtils.i(TAG, "即将关闭");
                    stopSelf();
                }
            }
        });

        btnStart.setOnTouchListener((v, event) -> {
            params.x = (int) event.getRawX() - 150;
            params.y = (int) event.getRawY() - 150 - statusBarHeight;
            windowManager.updateViewLayout(constraintLayout, params);
            return false;
        });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        if (btnStart != null) {
            windowManager.removeView(constraintLayout);
        }
        super.onDestroy();
    }
}
