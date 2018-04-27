package cn.gridlife.xiaobei.bzbtoolsapp.functions.two;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @description:
 * @author: ryan
 * @contact: bingh@outlook.com
 * @file: AutoLinearLayoutManager
 * @time: 2018/3/24 16:31
 * 避免内部数据集和外部数据集不一致导致的异常 reference:https://www.jianshu.com/p/2eca433869e9
 */

public class AutoLinearLayoutManager extends LinearLayoutManager {
    public AutoLinearLayoutManager(Context context) {
        super(context);
    }

    public AutoLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public AutoLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
