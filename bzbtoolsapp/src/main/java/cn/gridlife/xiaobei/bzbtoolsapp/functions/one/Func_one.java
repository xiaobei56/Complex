package cn.gridlife.xiaobei.bzbtoolsapp.functions.one;

import android.app.Activity;
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
    final String TAG = "func_one";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_func_one);
        final EditText editText = (EditText) findViewById(R.id.et_input);
        final ArrayList<String> list = new ArrayList<String>();
        final ArrayList<CandidatePopUpWindow.ItemBean> itemBeans = new ArrayList<CandidatePopUpWindow.ItemBean>();
        itemBeans.add(new CandidatePopUpWindow.ItemBean("abcdedfdf"));
        itemBeans.add(new CandidatePopUpWindow.ItemBean("bcadfads"));
        itemBeans.add(new CandidatePopUpWindow.ItemBean("fdasfdasf"));
        itemBeans.add(new CandidatePopUpWindow.ItemBean("dfadfa"));
        itemBeans.add(new CandidatePopUpWindow.ItemBean("abcdffdadsf"));
        itemBeans.add(new CandidatePopUpWindow.ItemBean("abcfdsfab"));


        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                CandidatePopUpWindow candidatePopUpWindow = new CandidatePopUpWindow(Func_one.this);
                candidatePopUpWindow.initPopupWindow(editText.getWidth());
                candidatePopUpWindow.refreshData(editText, itemBeans, s.toString());

                editText.setSelection(s.length());
            }
        });
    }
}
