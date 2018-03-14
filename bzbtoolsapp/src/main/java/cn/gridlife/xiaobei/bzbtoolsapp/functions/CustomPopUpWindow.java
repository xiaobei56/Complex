package cn.gridlife.xiaobei.bzbtoolsapp.functions;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;

import cn.gridlife.xiaobei.bzbtoolsapp.R;


/**
 * Created by BZB on 16-4-22.
 * Project: hezhang_android.
 * Package：${PACKAGE_NAME}.
 */
public class CustomPopUpWindow extends PopupWindow {
    private ListView listView;
    private PopupWindow selectPopupWindow;
    private Context mContext;
    private OnPopItemClickListener popItemClickListener;
    private NameAdapter adapter;

    public CustomPopUpWindow(Context context, OnPopItemClickListener popItemClickListener) {
        mContext = context;
        this.popItemClickListener = popItemClickListener;
    }

    public CustomPopUpWindow(Context context) {
        mContext = context;
    }

    public void initPopupWindow(int width) {

        //PopupWindow浮动下拉框布局
        View loginWindow = LayoutInflater.from(mContext).inflate(R.layout.func_one_options, null);
        listView = (ListView) loginWindow.findViewById(R.id.list);

        //设置自定义Adapter

        selectPopupWindow = new PopupWindow(loginWindow, width, LinearLayout.LayoutParams.WRAP_CONTENT, true);

        selectPopupWindow.setOutsideTouchable(true);

        //当点击屏幕其他部分及Back键时PopupWindow会消失
        selectPopupWindow.setBackgroundDrawable(new BitmapDrawable());
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
    ArrayList<String> candidateList = new ArrayList<>();

    /**
     * 显示PopupWindow窗口
     *
     * @param v
     */
    public void popupWindowShowing(View v) {
        this.view = v;
        if (selectPopupWindow != null) {
            selectPopupWindow.showAsDropDown(v, 20, 15);
            //设置可以获取焦点，否则弹出菜单中的EditText是无法获取输入的
            this.setFocusable(false);
            //这句是为了防止弹出菜单获取焦点之后，点击activity的其他组件没有响应
//            this.setBackgroundDrawable(new BitmapDrawable());
            ((EditText) v).requestFocus();
            ((EditText) v).setFocusable(true);
        }
    }

    public void showCandiDate(final View v, ArrayList<String> list, String s) {
        view = v;
        if (list != null && list.size() > 0 && s != null && s.length() > 0) {
            for (String str : list) {
                if (str.length() > s.length() && s.equals(str.substring(0, s.length()))) {
                    candidateList.add(str);
                }
            }
            if (adapter == null) {
                adapter = new NameAdapter(mContext, list);
            } else {
                adapter.notifyDataSetChanged();
            }
            adapter = new NameAdapter(mContext, candidateList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    hidePopUpWindow();
                    if (popItemClickListener != null)
                        popItemClickListener.onClick(parent, view, position, id);
                }
            });
            setOnPopItemClickListener(new OnPopItemClickListener() {
                @Override
                public void onClick(AdapterView<?> parent, View view, int position, long id) {
                    ((EditText) v).setText(candidateList.get(position));
                    hidePopUpWindow();
                }
            });
            popupWindowShowing(view);

        }


    }

    public void hidePopUpWindow() {
        if (selectPopupWindow != null) {
            selectPopupWindow.dismiss();
        }
    }

    public interface OnPopItemClickListener {
        void onClick(AdapterView<?> parent, View view, int position, long id);
    }


}
