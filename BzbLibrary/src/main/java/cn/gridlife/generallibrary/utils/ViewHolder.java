package cn.gridlife.generallibrary.utils;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by BZB on 2018/3/30.
 * Project: Complex.
 * Package：cn.gridlife.generallibrary.utils.
 */
//
// if(convertView == null){
//         // 布局泵获取itemView的布局
//         convertView = inflater.inflate(R.layout.listview_test, null);
//         }
//
//         // 使用Viewholder类的静态方法缓存控件，并返回缓存后的控件
//         TextView tv_test = (TextView) ViewHolder.get(convertView, R.id.tv_test);
//         // 设置内容
//         tv_test.setText(list.get(position));
//
//         return convertView;

///////////////////////////////////////////////////////////////////////////
// https://blog.csdn.net/csdnzouqi/article/details/53817776
///////////////////////////////////////////////////////////////////////////

public class ViewHolder {
    // 添加私有构造函数防止外部实例化
    private ViewHolder() {
    }

    /**
     * 用来缓存控件，优化加载
     *
     * @param view itemView的布局
     * @param id   itemView布局中需要缓存控件的id
     * @return 缓存后的控件（textView、imageView...等控件）
     */
    @SuppressWarnings("unchecked")
    public static View get(View view, int id) {
        // 获取itemView的ViewHolder对象，并将其转型为SparseArray<View>
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            // 如果viewHolder为空，就新建一个
            viewHolder = new SparseArray<View>();
            // 给view设置tag标签
            view.setTag(viewHolder);
        }
        // 根据控件的id获取itemView布局的控件
        View childView = viewHolder.get(id);
        if (childView == null) {
            // 如果还没有缓存该控件，那么就根据itemView找到该控件
            childView = view.findViewById(id);
            // 缓存该控件
            viewHolder.put(id, childView);
        }
        // 返回缓存好的控件
        return childView;
    }

}
