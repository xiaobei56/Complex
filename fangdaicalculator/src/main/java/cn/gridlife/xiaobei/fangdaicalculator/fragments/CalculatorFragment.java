package cn.gridlife.xiaobei.fangdaicalculator.fragments;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.gridlife.generallibrary.fragments.BFragment;
import cn.gridlife.xiaobei.fangdaicalculator.R;
import cn.gridlife.xiaobei.fangdaicalculator.activities.calculator.FangDaiCalculatorActivity;
import cn.gridlife.xiaobei.fangdaicalculator.adapters.CalculatorAdapter;
import cn.gridlife.xiaobei.fangdaicalculator.entries.CalculatorItem;

/**
 * Created by BZB on 2018/2/27.
 */

public class CalculatorFragment extends BFragment {

    private RecyclerView recyclerView;
    private CalculatorAdapter adapter;
    private List<CalculatorItem> itemDatas;
    private String[] titles = {"房贷计算", "计算器"};
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
//        Bitmap bitmap2 = ((BitmapDrawable) currentActivity.getApplication().getResources().getDrawable(R.mipmap.icon_shangye)).getBitmap();
        Bitmap bitmap3 = ((BitmapDrawable) currentActivity.getApplication().getResources().getDrawable(R.mipmap.icon_zuhe)).getBitmap();
        bitmaps = new Bitmap[]{bitmap1, bitmap3};
        recyclerView = (RecyclerView) view.findViewById(R.id.rl_function);
        recyclerView.setLayoutManager(new GridLayoutManager(currentActivity, 3));
        adapter = new CalculatorAdapter(R.layout.calculator_item, getItemDatas());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(currentActivity, FangDaiCalculatorActivity.class));
                        break;
                    case 1:
                    {
//                        AlertDialog alertDialog = builder.create();
//                        alertDialog.show();
//                        TextView confirm = inflate.findViewById(R.id.confirm);
//                        confirm.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                dialogSelectData.getSelectResult(dialogListAdapter.getSelectData());
//                                alertDialog.dismiss();
//                            }
//                        });
                    }
                }
            }

        });
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
        CalculatorItem item;
        for (int i = 0; i < titles.length; i++) {
            item = new CalculatorItem();
            item.setTitle(titles[i]);
            item.setBitmap(bitmaps[i]);
            itemDatas.add(item);
        }
        return itemDatas;
    }

}
