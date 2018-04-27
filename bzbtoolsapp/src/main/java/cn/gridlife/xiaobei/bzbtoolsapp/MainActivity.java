package cn.gridlife.xiaobei.bzbtoolsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.gridlife.xiaobei.bzbtoolsapp.functions.one.Func_one;
import cn.gridlife.xiaobei.bzbtoolsapp.functions.two.FunTwoActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1= (Button) findViewById(R.id.btn_1);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                startActivity(new Intent(this, Func_one.class));
        }
    }

    /**
     * 滑动隐藏布局
     * @param view
     */
    public void funTwo(View view) {
        startActivity(new Intent(this, FunTwoActivity.class));
    }
}
