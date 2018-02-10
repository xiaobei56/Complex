package cn.gridlife.gametools.fragments.navFunction;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import cn.gridlife.gametools.R;
import cn.gridlife.generallibrary.fragments.BFragment;

/**
 * @author BZB
 * @date 2018/1/30
 * project Speach
 */

public class AssistantWkFragment extends BFragment {
    Activity activity;

    /**
     * 第一次界面用户可见: 初始化View的数据
     */
    @Override
    protected void onFirstUserVisible() {
        activity = super.currentActivity;
        Toast.makeText(activity, "onFirstUserVisible", Toast.LENGTH_SHORT).show();

    }

    @Override
    public String setTile() {
        return "狼杀助手";
    }

    /**
     * 当用户可见时
     */
    @Override
    protected void onUserVisible() {
        Toast.makeText(activity, "onUserVisible", Toast.LENGTH_SHORT).show();
    }

    /**
     * 当用户不可见时
     */
    @Override
    protected void onUserInvisible() {
        Toast.makeText(activity, "onUserInvisible", Toast.LENGTH_SHORT).show();
    }

    /**
     * 初始化View 及 Event
     * onViewCreated
     *
     * @param view 布局View
     */
    @Override
    protected void initViewsAndEvents(View view) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_main_assistance;
    }

    /**
     * OnDestroy do something
     */
    @Override
    protected void DestroyViewAndThing() {

    }
}
