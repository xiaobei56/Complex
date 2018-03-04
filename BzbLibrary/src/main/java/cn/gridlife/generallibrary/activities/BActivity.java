package cn.gridlife.generallibrary.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;

/**
 * @author BZB
 * @date 2017/12/19
 * project Speach
 */

public abstract class BActivity extends AppCompatActivity {
    boolean showTitle = true;
    boolean showStatus = true;
    boolean showTitleBar = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isFullScreen(isShowTitle(), isShowStatus(), isShowTitleBar());
        setContentView(getLayoutId());
        initData();
        initView();
    }

    /**
     * 如果使用ButterKnife 必须调用此方法绑定
     */
    protected void BindButterKnife() {
        ButterKnife.bind(this);
    }


    public boolean isShowTitleBar() {
        return showTitleBar;
    }
    public boolean isShowTitle() {
        return showTitle;
    }

    public boolean isShowStatus() {
        return showStatus;
    }

    protected void isFullScreen(boolean isShowTitle, boolean isShowStatus,boolean isShowTitleBar) {
        if (!isShowTitle) {
            //取消标题栏
            requestWindowFeature(Window.FEATURE_NO_TITLE);

        }
        if (!isShowStatus) {
            //取消状态栏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        if(!isShowTitleBar){
            getSupportActionBar().hide();
        }
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
