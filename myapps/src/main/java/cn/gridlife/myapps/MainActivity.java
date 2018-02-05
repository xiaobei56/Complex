package cn.gridlife.myapps;

import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import cn.gridlife.myapps.services.ButtonService;

public class MainActivity extends AppCompatActivity {
    float x, y;
    TextView tvShowClickPoint, tvStart;
    Button btnShow, btnHide;
    private int width;
    private int height;
    private Point point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        tvShowClickPoint = (TextView) findViewById(R.id.tv_clicked_point);
        tvStart = (TextView) findViewById(R.id.tv_start);
        btnShow = (Button) findViewById(R.id.btn_show_flow_window);
        btnHide = (Button) findViewById(R.id.btn_hide_flow_window);

        btnShow.setOnClickListener(v -> initPermission());
        tvStart.setOnClickListener(v -> autoClick(x, y));

        initData();
    }

    private void initData() {

    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (Settings.canDrawOverlays(MainActivity.this)) {
                Intent intent = new Intent(MainActivity.this, ButtonService.class);
                startService(intent);
                finish();
            } else {
                //若没有权限，提示获取.
//                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                Toast.makeText(MainActivity.this, "需要取得权限以使用悬浮窗", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        } else {
            //SDK在23以下，不用管.
            Intent intent = new Intent(MainActivity.this, ButtonService.class);
            startService(intent);
            finish();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


    }

    private void autoClick(float width, float height) {
        if (width == 0) {
            width = point.x;
            height = point.y;
        }
        //每10s产生一次点击事件，点击的点坐标为(0.2W - 0.8W,0.2H - 0.8 H),W/H为手机分辨率的宽高.
        float finalWidth = width;
        float finalHeight = height;
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
                        "" + x + 20,
                        "" + x + 20,
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


    /**
     * 打印点击的点的坐标
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();
        tvShowClickPoint.setText(x + "  " + y);
//        Toast.makeText(this, "X at " + x + ";Y at " + y, Toast.LENGTH_SHORT).show();
        return true;
    }

}
