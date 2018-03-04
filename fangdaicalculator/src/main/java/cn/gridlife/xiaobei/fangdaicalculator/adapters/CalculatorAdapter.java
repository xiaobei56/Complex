package cn.gridlife.xiaobei.fangdaicalculator.adapters;

import android.support.annotation.Nullable;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gridlife.xiaobei.fangdaicalculator.R;
import cn.gridlife.xiaobei.fangdaicalculator.entries.CalculatorItem;

/**
 * Created by BZB on 2018/3/1.
 */

public class CalculatorAdapter extends BaseQuickAdapter<CalculatorItem, BaseViewHolder> {
    public CalculatorAdapter(int layoutResId, @Nullable List<CalculatorItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CalculatorItem item) {
        helper.setImageBitmap(R.id.iv, item.getBitmap());
        helper.setText(R.id.tv_title, item.getTitle());
    }
}
