package cn.gridlife.xiaobei.fangdaicalculator.activities;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import cn.gridlife.generallibrary.activities.BActivity;
import cn.gridlife.xiaobei.fangdaicalculator.R;

/**
 * Created by BZB on 2018/2/27.
 * Project: Complex.
 * Package：cn.gridlife.xiaobei.fangdaicalculator.activities.
 */

public class SplashActivity extends BActivity {

    private TextView tvTimer;

    @Override
    protected void initData() {

        tvTimer = (TextView) findViewById(R.id.tv_timer);

        CountDownTimer timer = new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("跳过（" + (millisUntilFinished / 1000) + "s)");
            }

            @Override
            public void onFinish() {

                tvTimer.setText("跳过（" + 0 + "s)");
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        };
        timer.start();
        tvTimer.setOnClickListener(v->{
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            timer.cancel();
            finish();
        });
    }

    @Override
    public boolean isShowStatus() {
        return false;
    }

    @Override
    public boolean isShowTitle() {
        return false;
    }

    @Override
    public boolean isShowTitleBar() {
        return false;
    }

    @Override
    protected void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }
}
