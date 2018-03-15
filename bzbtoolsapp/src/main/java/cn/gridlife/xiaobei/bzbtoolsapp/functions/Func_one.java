package cn.gridlife.xiaobei.bzbtoolsapp.functions;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;

import cn.gridlife.xiaobei.bzbtoolsapp.R;

/**
 * Created by BZB on 2018/3/14.
 * Project: Complex.
 * Package：cn.gridlife.xiaobei.bzbtoolsapp.functions.
 */

public class Func_one extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_func_one);
        final EditText editText = (EditText) findViewById(R.id.et_input);
        final ArrayList<String> list = new ArrayList<String>();
        list.add("abcdedfdf");
        list.add("bcadfads");
        list.add("fdasfdasf");
        list.add("dfadfa");
        list.add("abcdffdadsf");
        list.add("abcfdsfab");

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                CustomPopUpWindow customPopUpWindow = new CustomPopUpWindow(Func_one.this);
                customPopUpWindow.initPopupWindow(editText.getWidth());
                customPopUpWindow.showCandiDate(editText, list, (String) s.toString());

            }
        });
    }
}
