package cn.gridlife.gametools.fragments.navFunction;

import android.view.View;

import cn.gridlife.gametools.R;
import cn.gridlife.generallibrary.fragments.BFragment;

/**
 * @author BZB
 * @date 2018/1/30
 * project Speach
 */

public class CommunityWkFragment extends BFragment {

    /**
     * 第一次界面用户可见: 初始化View的数据
     */
    @Override
    protected void onFirstUserVisible() {

    }

    /**
     * 当用户可见时
     */
    @Override
    protected void onUserVisible() {

    }

    @Override
    public String getTitle() {
        return getString(R.string.text_wk_community);
    }

    /**
     * 当用户不可见时
     */
    @Override
    protected void onUserInvisible() {

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
        return R.layout.main_fragment_community;
    }

    /**
     * OnDestroy do something
     */
    @Override
    protected void DestroyViewAndThing() {

    }
}
