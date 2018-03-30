package cn.gridlife.xiaobei.bzbtoolsapp.functions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import cn.gridlife.xiaobei.bzbtoolsapp.R;

public class NameAdapter extends BaseAdapter {

    private ArrayList<String> list = new ArrayList<String>();
    private Context context = null;


    /**
     * 自定义构造方法
     *
     * @param context context
     * @param list    list
     */
    public NameAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            //下拉项布局
            convertView = LayoutInflater.from(context).inflate(R.layout.func_one_option_item, null);
            holder.textView = (TextView) convertView.findViewById(R.id.item_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(list.get(position));
        return convertView;
    }
    private OnItemDeleteListener mOnItemDeleteListener;
    /**
     * 删除按钮的监听接口
     */
    public interface OnItemDeleteListener {
        void onDeleteClick(int i);
    }
    public void setOnItemDeleteClickListener(OnItemDeleteListener mOnItemDeleteListener) {
        this.mOnItemDeleteListener = mOnItemDeleteListener;
    }

    class ViewHolder {
        TextView textView;
    }
}

