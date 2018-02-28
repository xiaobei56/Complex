package cn.gridlife.generallibrary.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by BZB on 2018/1/30.
 * project Speach
 */

/**
 * v4包中的Fragment在Activity的布局中是可以使用<fragment>标签的
 */
public abstract class BFragment extends Fragment {

    /**
     * 是否是第一次可见
     */
    private boolean isFirstVisible = true;
    /**
     * 是否用户第一次不可见
     */
    private boolean isFirstInvisible = true;
    /**
     * 是否prepared
     */
    private boolean isPrepared = false;
    protected String TAG;
    protected FragmentActivity currentActivity;

    private String title;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        TAG = this.getClass().getSimpleName();
        currentActivity = (FragmentActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (getContentViewLayoutID() != 0) {
            return inflater.inflate(getContentViewLayoutID(), null);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        ButterKnife.bind(this, view);
        initViewsAndEvents(view);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPrepare();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        DestroyViewAndThing();
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    private synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
        } else {
            isPrepared = true;
        }
    }

    /**
     * Fragment懒加载- 提高Fragment的效率
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        ///////////////////////////////////////////////////////////////////////////
        // 就是说如果你没有用Fragment懒加载的话而是用的viewpager的预加载，如果没有自己定义默认预加载个数的话
        // 那么默认肯定是会提前加载的，加入你当前fragment相邻的fragment里面有动画或者视频播放的话，
        // 切换到当前fragment时候下个fragment里面的动画或者视频就已经开始执行了
        ///////////////////////////////////////////////////////////////////////////
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false;
                initPrepare();
            } else {
                onUserVisible();
            }
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false;
                onFirstUserInvisible();
            } else {
                onUserInvisible();
            }
        }
    }

    /**
     * 第一次界面用户可见: 初始化View的数据
     */
    protected abstract void onFirstUserVisible();

    /**
     * 当用户可见时
     */
    protected abstract void onUserVisible();

    /**
     * 第一次用户不可见
     */
    private void onFirstUserInvisible() {
    }

    /**
     * 当用户不可见时
     */
    protected abstract void onUserInvisible();

    /**
     * 初始化View 及 Event
     * onViewCreated
     *
     * @param view 布局View
     */
    protected abstract void initViewsAndEvents(View view);

    /**
     * 获取View Layout ID
     *
     * @return layoutID
     */
    protected abstract int getContentViewLayoutID();

    /**
     * OnDestroy do something
     */
    protected abstract void DestroyViewAndThing();

    /**
     * 打开一个Activity 默认 不关闭当前activity
     */
    public void gotoActivity(Class<?> clz) {
        gotoActivity(clz, false, null);
    }

    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity) {
        gotoActivity(clz, isCloseCurrentActivity, null);
    }

    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity, Bundle ex) {
        Intent intent = new Intent(currentActivity, clz);
        if (ex != null) {
            intent.putExtras(ex);
        }
        startActivity(intent);
        if (isCloseCurrentActivity) {
            currentActivity.finish();
        }
    }
}
