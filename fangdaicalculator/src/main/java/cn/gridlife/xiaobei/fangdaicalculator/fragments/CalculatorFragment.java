package cn.gridlife.xiaobei.fangdaicalculator.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.gridlife.generallibrary.fragments.BFragment;
import cn.gridlife.xiaobei.fangdaicalculator.R;
import cn.gridlife.xiaobei.fangdaicalculator.adapters.CalculatorAdapter;
import cn.gridlife.xiaobei.fangdaicalculator.entries.CalculatorItem;

/**
 * Created by BZB on 2018/2/27.
 */

public class CalculatorFragment extends BFragment {

    private RecyclerView recyclerView;
    private CalculatorAdapter adapter;
    private List<CalculatorItem> itemDatas;
    private String[] titles = {"公积金贷款", "商业贷款", "组合贷款"};
    private Bitmap[] bitmaps;


    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected void initViewsAndEvents(View view) {
        Bitmap bitmap1 = ((BitmapDrawable) currentActivity.getApplication().getResources().getDrawable(R.mipmap.icon_gongjijin)).getBitmap();
        Bitmap bitmap2 = ((BitmapDrawable) currentActivity.getApplication().getResources().getDrawable(R.mipmap.icon_shangye)).getBitmap();
        Bitmap bitmap3 = ((BitmapDrawable) currentActivity.getApplication().getResources().getDrawable(R.mipmap.icon_zuhe)).getBitmap();
        bitmaps = new Bitmap[]{bitmap1, bitmap2, bitmap3};
        recyclerView = (RecyclerView) view.findViewById(R.id.rl_function);
        recyclerView.setLayoutManager(new GridLayoutManager(currentActivity, 3));
        adapter = new CalculatorAdapter(R.layout.calculator_item, getItemDatas());
        recyclerView.setAdapter(adapter);
//        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                Toast.makeText(currentActivity, "clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_caculator;
    }

    @Override
    protected void DestroyViewAndThing() {

    }

    private List<CalculatorItem> getItemDatas() {
        itemDatas = new ArrayList<>();
        CalculatorItem item = new CalculatorItem();
        for (int i = 0; i < titles.length; i++
                ) {
            item = new CalculatorItem();
            item.setTitle(titles[i]);
            item.setBitmap(bitmaps[i]);
            itemDatas.add(item);
        }
        return itemDatas;
    }

}
