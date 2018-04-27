package cn.gridlife.xiaobei.bzbtoolsapp.functions.one;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import cn.gridlife.xiaobei.bzbtoolsapp.R;


/**
 * Created by BZB on 16-4-22.
 * Project: hezhang_android.
 * Package：${PACKAGE_NAME}.
 */
public class CandidatePopUpWindow extends PopupWindow {
    //    private ListView listView;
    private RecyclerView recyclerView;
    private PopupWindow selectPopupWindow;
    private Context mContext;
    private OnPopItemClickListener popItemClickListener;
    private MyRecyclerAdapter adapter;

    public CandidatePopUpWindow(Context context, OnPopItemClickListener popItemClickListener) {
        mContext = context;
        this.popItemClickListener = popItemClickListener;
    }

    public CandidatePopUpWindow(Context context) {
        mContext = context;
    }

    public void initPopupWindow(int width) {

        //PopupWindow浮动下拉框布局
        View loginWindow = LayoutInflater.from(mContext).inflate(R.layout.func_one_options, null);
//        listView = (ListView) loginWindow.findViewById(R.id.list);
        recyclerView = (RecyclerView) loginWindow.findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayout.HORIZONTAL));
        //设置自定义Adapter
        this.setFocusable(false);
        selectPopupWindow = new PopupWindow(loginWindow, width, LinearLayout.LayoutParams.WRAP_CONTENT, true);

        selectPopupWindow.setOutsideTouchable(true);

        //当点击屏幕其他部分及Back键时PopupWindow会消失
        selectPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        selectPopupWindow.setFocusable(false);
        selectPopupWindow.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
    }

    private CustomPopuWindowItemDeleteClickListener itemDeleteClickListener;

    public interface CustomPopuWindowItemDeleteClickListener {
        void onPopupWindowItemDeleteClick(int i);
    }

    public void setOnPopItemDeleteClickListener(CustomPopuWindowItemDeleteClickListener itemDeleteClickListener) {
        this.itemDeleteClickListener = itemDeleteClickListener;
    }

    public void setOnPopItemClickListener(OnPopItemClickListener popItemClickListener) {
        this.popItemClickListener = popItemClickListener;
    }

    View view;
    ArrayList<ItemBean> candidateList = new ArrayList<>();

    /**
     * 显示PopupWindow窗口
     *
     * @param v
     */
    final String TAG = "CustomPopupWind";

    public void popupWindowShowing(View v) {
        Log.i(TAG, "popupWindowShowing: popupWindowShow");
        if (selectPopupWindow != null) {
            selectPopupWindow.showAsDropDown(v, 20, 15);
            //设置可以获取焦点，否则弹出菜单中的EditText是无法获取输入的
//            this.setFocusable(false);
            //这句是为了防止弹出菜单获取焦点之后，点击activity的其他组件没有响应
            v.requestFocus();
        }
    }

    public void refreshData(final View v, ArrayList<ItemBean> list, String s) {
        if (list != null && list.size() > 0 && s != null && s.length() > 0) {
            for (ItemBean item : list) {
                if (item.getName().length() > s.length() && s.equals(item.getName().substring(0, s.length()))) {
                    candidateList.add(new ItemBean(item.getName()));
                }
            }
            if (adapter == null) {
                adapter = new MyRecyclerAdapter(R.layout.func_one_option_item, candidateList);
            } else {
                adapter.notifyDataSetChanged();
            }
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    if (popItemClickListener != null) {
                        popItemClickListener.onClick(view, position);

                    }
                }
            });
            setOnPopItemClickListener(new OnPopItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    hidePopUpWindow();
                    ((EditText) v).setText(candidateList.get(position).getName());
                }
            });
            adapter.notifyDataSetChanged();
            popupWindowShowing(v);
            v.requestFocus();
        }

    }

    public static class ItemBean {
        private String name;

        public ItemBean(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    class MyRecyclerAdapter extends BaseQuickAdapter<ItemBean, BaseViewHolder> {
        public MyRecyclerAdapter(int layoutResId, @Nullable List<ItemBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ItemBean item) {
            helper.setText(R.id.item_text, item.getName());
        }
    }


    public void hidePopUpWindow() {
        if (selectPopupWindow != null) {
            selectPopupWindow.dismiss();
        }
    }

    public interface OnPopItemClickListener {
        void onClick(View view, int position);
    }


}
