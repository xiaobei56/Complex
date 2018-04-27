package cn.gridlife.xiaobei.bzbtoolsapp.functions.two;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.BaseAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.animation.BaseAnimation;

import java.util.ArrayList;

import cn.gridlife.xiaobei.bzbtoolsapp.R;

/**
 * Created by BZB on 2018/4/25.
 * Project: Complex.
 * Package：cn.gridlife.xiaobei.bzbtoolsapp.functions.two.
 */

public class FunTwoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private MultiItemAdapter adapter;
    private MultipleItem multipleItem;
    private MultipleItem multipleItem1;
    private MultipleItem multipleItem2;
    private MultipleItem multipleItem3;
    private MultipleItem multipleItem4;
    private Type0Bean type0Bean = new Type0Bean();

    private Type1Bean type1Bean = new Type1Bean();
    private Type2Bean type2Bean = new Type2Bean();
    private Type3Bean type3Bean = new Type3Bean();
    private Type4Bean type4Bean = new Type4Bean();
    private ArrayList<MultipleItem> dataList;
    private SwipeRefreshLayout swipeRefreshLayout;
    //手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_func_two);

        dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            type1Bean.setTitle("1_Title");
            type1Bean.setContent("1_content");

            type2Bean.setTitle("2_Title");
            type2Bean.setContent("2_content");

            type3Bean.setTitle("3_Title");
            type3Bean.setContent("3_content");

            type4Bean.setTitle("3_Title");
            type4Bean.setContent("3_content");

            multipleItem1 = new MultipleItem(type1Bean, type2Bean, type3Bean, type4Bean, MultipleItem.TYPE1);
            multipleItem2 = new MultipleItem(type1Bean, type2Bean, type3Bean, type4Bean, MultipleItem.TYPE2);
            multipleItem3 = new MultipleItem(type1Bean, type2Bean, type3Bean, type4Bean, MultipleItem.TYPE3);
            multipleItem4 = new MultipleItem(type1Bean, type2Bean, type3Bean, type4Bean, MultipleItem.TYPE4);
//
            dataList.add(multipleItem1);
            dataList.add(multipleItem2);
            dataList.add(multipleItem3);
            dataList.add(multipleItem4);
        }
        type0Bean.setTitle("0");
        type0Bean.setContent("0_content");
        multipleItem = new MultipleItem();
        multipleItem.setType(MultipleItem.TYPE0);
        multipleItem.setType0Bean(type0Bean);

        dataList.add(0, multipleItem);
        adapter = new MultiItemAdapter(this, dataList);
        recyclerView = (RecyclerView) findViewById(R.id.rv_func_two);
        recyclerView.setLayoutManager(new AutoLinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

//        adapter.setDuration(1000);


        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
    }

    class AnimationIn implements BaseAnimation {

        @Override
        public Animator[] getAnimators(View view) {
            return new Animator[]{
                    ObjectAnimator.ofFloat(view, "translationY", 0, view.getMeasuredHeight())};
        }
    }
   class AnimationOut implements BaseAnimation {

        @Override
        public Animator[] getAnimators(View view) {
            return new Animator[]{
                    ObjectAnimator.ofFloat(view, "translationY", view.getMeasuredHeight(),0)};
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float viewHeight=recyclerView.getChildAt(0).getHeight();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = ev.getX();
                y1 = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
//                if (ev.getY() - y1 < 0) {
//                    recyclerView.scrollTo(0, (int) (y1 - ev.getY()));
                if (adapter.getData().get(0).getItemType() != MultipleItem.TYPE0 && ev.getY() - y1 > viewHeight/2) { //向下划显示
//                    recyclerView.scrollBy(0, (int) viewHeight);
                    AutoLinearLayoutManager layoutManager = ((AutoLinearLayoutManager) recyclerView.getLayoutManager());
                    layoutManager.scrollToPositionWithOffset(0, (int) (ev.getY() - y1));
                    adapter.addData(0, multipleItem);
//                    adapter.setDuration(2000);
//                    adapter.openLoadAnimation(new AnimationIn());
                    swipeRefreshLayout.onInterceptTouchEvent(ev);
                    recyclerView.onInterceptTouchEvent(ev);
//                    recyclerView.smoothScrollToPosition(0);
                    Log.i("sssss", "dispatchTouchEvent: add");
                } else if (adapter.getData().get(0).getItemType() == MultipleItem.TYPE0 &&  y1 - ev.getY() > viewHeight/ 2) {//向上划 隐藏
                    adapter.remove(0);
                    AutoLinearLayoutManager layoutManager = ((AutoLinearLayoutManager) recyclerView.getLayoutManager());
                    layoutManager.scrollToPositionWithOffset(0, (int) viewHeight);
                    swipeRefreshLayout.onInterceptTouchEvent(ev);
                    recyclerView.onInterceptTouchEvent(ev);
//                    recyclerView.scrollBy(0, 0);
//                    adapter.setDuration(2000);
//                    adapter.openLoadAnimation(new AnimationOut());
//                    swipeRefreshLayout.onInterceptTouchEvent(ev);
                    Log.i("sssss", "dispatchTouchEvent: remove");
                }
                Log.i("ssss view Height",recyclerView.getChildAt(0).getHeight()/3+"");
                Log.i("ssss y1 - ev.getY()",recyclerView.getChildAt(0).getHeight()+"");
                break;
            case MotionEvent.ACTION_UP:

                break;

        }
        return super.dispatchTouchEvent(ev);
    }
}
