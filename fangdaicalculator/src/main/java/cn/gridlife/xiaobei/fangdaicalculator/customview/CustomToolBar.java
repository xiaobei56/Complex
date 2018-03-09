package cn.gridlife.xiaobei.fangdaicalculator.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.gridlife.xiaobei.fangdaicalculator.R;

/**
 * Created by BZB on 2017/11/30.
 * Project: hezhang_android.
 * Package：com.syberos.hezhang.luodian.view.
 */

public class CustomToolBar extends Toolbar {
    private LayoutInflater mInflater;
    private LinearLayout llBack;
    private ImageView ivLeft;
    private TextView tvLeft;
    private TextView tvTitle;
    private LinearLayout llMnu;
    private ImageView ivRight;
    private View view;
    private String leftViewText;

    public CustomToolBar(Context context) {
//        super(context);
        this(context, null);
    }

    public CustomToolBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("RestrictedApi")
    public CustomToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();

        setContentInsetsRelative(10, 10);

        if (attrs != null) {
            //读写自定义的属性，如果不会自己写的时候，就看看官方文档是怎么写的哈
            /**
             * 下面是摘自官方文档
             * final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
             R.styleable.Toolbar, defStyleAttr, 0);

             mTitleTextAppearance = a.getResourceId(R.styleable.Toolbar_titleTextAppearance, 0);
             mSubtitleTextAppearance = a.getResourceId(R.styleable.Toolbar_subtitleTextAppearance, 0);
             mGravity = a.getInteger(R.styleable.Toolbar_android_gravity, mGravity);
             mButtonGravity = Gravity.TOP;
             mTitleMarginStart = mTitleMarginEnd = mTitleMarginTop = mTitleMarginBottom =
             a.getDimensionPixelOffset(R.styleable.Toolbar_titleMargins, 0);

             final int marginStart = a.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginStart, -1);
             if (marginStart >= 0) {
             mTitleMarginStart = marginStart;
             }

             *
             */
            final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                    R.styleable.CustomToolBarStyle, defStyleAttr, 0);
// <declare-styleable name="CustomToolBarStyle">
//        <attr name="leftButtonIcon" format="boolean" />
//        <attr name="leftButtonText" format="reference"/>
//        <!--<attr name="isShowMenu" format="boolean" />-->
//        <attr name="rightMenuIcon" format="reference"/>
//    </declare-styleable>
//            left Text
            boolean leftIconAndText = a.getBoolean(R.styleable.CustomToolBarStyle_leftIconAndText, false);
            if (leftIconAndText) {
                hideLeftViewText();
            } else {
                String leftViewText = a.getString(R.styleable.CustomToolBarStyle_LeftViewText);
                setLeftViewText(leftViewText);
            }
//            left ICON
            final Drawable leftIcon = a.getDrawable(R.styleable.CustomToolBarStyle_leftButtonIcon);
            if (leftIcon != null) {
                setLeftButtonIcon(leftIcon);
            }
//            right ICON
            final Drawable rightIcon = a.getDrawable(R.styleable.CustomToolBarStyle_rightMenuIcon);
            if (rightIcon != null) {
                setRightButtonIcon(rightIcon);
            }


//            boolean isShowSearchView = a.getBoolean(R.styleable.LetToolBar_isShowSearchView, false);

            //如果要显示searchView的时候
//            if (isShowSearchView) {
//                showSearchView();
//                hideTitleView();
//            }
            //资源的回收
            a.recycle();
        }
    }


    /**
     * 初始化View
     */
    private void initView() {
        if (view == null) {
            //初始化
            mInflater = LayoutInflater.from(getContext());
            //添加布局文件
            view = mInflater.inflate(R.layout.custom_view_toolbar, null);

            //绑定控件
            llBack = view.findViewById(R.id.ll_toolbar_back);
            llMnu = view.findViewById(R.id.ll_toolbar_menu);
            ivLeft = view.findViewById(R.id.iv_toolbar_back);
            tvLeft = view.findViewById(R.id.tv_toolbar_back);
            tvTitle = view.findViewById(R.id.tv_toolbar_title);
            ivRight = view.findViewById(R.id.iv_toolbar_menu);
            //然后使用LayoutParams把控件添加到子view中
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
            addView(view, lp);

        }
    }
    private void hideLeftViewText() {
        if (tvLeft != null) {
            tvLeft.setVisibility(GONE);
        }
    }

    public void setLeftViewText(String leftViewText) {
        if (tvLeft != null) {
            tvLeft.setText(leftViewText);
            tvLeft.setVisibility(VISIBLE);
        }
    }

    public void setLeftButtonIcon(Drawable leftButtonIcon) {
        if (ivLeft != null) {
            ivLeft.setImageDrawable(leftButtonIcon);
        }
    }

    public void setRightButtonIcon(Drawable rightButtonIcon) {
        if (ivRight != null) {
            ivRight.setImageDrawable(rightButtonIcon);
            ivRight.setVisibility(VISIBLE);
        }
    }
    //设置右侧按钮监听事件
    public void setRightButtonOnClickLinster(OnClickListener linster) {
        llMnu.setOnClickListener(linster);
    }

    //设置左侧按钮监听事件
    public void setLeftButtonOnClickLinster(OnClickListener linster) {
        llBack.setOnClickListener(linster);
    }

    @Override
    public void setTitle(int resId) {
        if(tvTitle!=null){
            tvTitle.setText(getResources().getString(resId));
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        if(tvTitle!=null){
            tvTitle.setText(title);
        }
    }
}
