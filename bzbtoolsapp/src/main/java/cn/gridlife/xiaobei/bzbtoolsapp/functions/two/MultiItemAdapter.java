package cn.gridlife.xiaobei.bzbtoolsapp.functions.two;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gridlife.xiaobei.bzbtoolsapp.R;

/**
 * Created by BZB on 2018/4/25.
 * Project: Complex.
 * Package：cn.gridlife.xiaobei.bzbtoolsapp.functions.two.
 */

public class MultiItemAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    Context context;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultiItemAdapter(Context context, List<MultipleItem> data) {
        super(data);
        this.context = context;
        addItemType(MultipleItem.TYPE0, R.layout.func_two_type0_layout);
        addItemType(MultipleItem.TYPE1, R.layout.func_two_type1_layout);
        addItemType(MultipleItem.TYPE2, R.layout.func_two_type2_layout);
        addItemType(MultipleItem.TYPE3, R.layout.func_two_type3_layout);
        addItemType(MultipleItem.TYPE4, R.layout.func_two_type4_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (item.getItemType()) {
            case MultipleItem.TYPE0:
                helper.setText(R.id.tv_title, item.getType0Bean().getTitle());
                helper.setText(R.id.tv_content, item.getType0Bean().getContent());
                break;
                case MultipleItem.TYPE1:
                helper.setText(R.id.tv_title, item.getType1Bean().getTitle());
                helper.setText(R.id.tv_content, item.getType1Bean().getContent());
                break;
            case MultipleItem.TYPE2:
                helper.setText(R.id.tv_title, item.getType2Bean().getTitle());
                helper.setText(R.id.tv_content, item.getType2Bean().getContent());
                break;
            case MultipleItem.TYPE3:
                helper.setText(R.id.tv_title, item.getType3Bean().getTitle());
                helper.setText(R.id.tv_content, item.getType3Bean().getContent());
                break;
            case MultipleItem.TYPE4:
                helper.setText(R.id.tv_title, item.getType4Bean().getTitle());
                helper.setText(R.id.tv_content, item.getType4Bean().getContent());
                break;

        }
    }
}
