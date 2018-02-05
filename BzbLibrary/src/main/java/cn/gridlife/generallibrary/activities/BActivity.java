package cn.gridlife.generallibrary.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author BZB
 * @date 2017/12/19
 * project Speach
 */

public abstract class BActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initData();
        initView();
    }

    /**
     * init data before initView
     */
    protected abstract void initData();

    /**
     * init View after initData
     */
    protected abstract void initView();



    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 获取LayoutID
     *
     * @return LayoutID
     */
    public abstract int getLayoutId();

}
