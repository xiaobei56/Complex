package cn.gridlife.xiaobei.fangdaicalculator.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import cn.gridlife.generallibrary.activities.BActivity;
import cn.gridlife.xiaobei.fangdaicalculator.R;
import cn.gridlife.xiaobei.fangdaicalculator.entries.TabEntity;
import cn.gridlife.xiaobei.fangdaicalculator.fragments.CalculatorFragment;
import cn.gridlife.xiaobei.fangdaicalculator.fragments.CommunicateFragment;
import cn.gridlife.xiaobei.fangdaicalculator.fragments.JingPinFragment;
import cn.gridlife.xiaobei.fangdaicalculator.utils.ViewFindUtils;

public class MainActivity extends BActivity {
    private CommonTabLayout mTabLayout_1;
    private View mDecorView;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"计算", "精品", "交流"};
    private int[] mIconUnSelectIds = {R.mipmap.ic_calculator, R.mipmap.ic_jingpin, R.mipmap.ic_communicate};
    private int[] mIconSelectIds = {R.mipmap.ic_calculator_selected, R.mipmap.ic_jingpin_selected, R.mipmap.ic_communicate_selected};
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private CalculatorFragment calculatorFragment;
    private CommunicateFragment communicateFragment;
    private JingPinFragment jingPinFragment;

    @Override
    protected void initData() {
        initBottomTab();
    }

    private void initBottomTab() {
        calculatorFragment = new CalculatorFragment();
        communicateFragment = new CommunicateFragment();
        jingPinFragment = new JingPinFragment();
        fragments.add(calculatorFragment);
        fragments.add(jingPinFragment);
        fragments.add(communicateFragment);
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnSelectIds[i]));
        }
        mDecorView = getWindow().getDecorView();
        mTabLayout_1 = ViewFindUtils.find(mDecorView, R.id.tl_1);
        mTabLayout_1.setTabData(mTabEntities, this, R.id.fl_change, fragments);
        mTabLayout_1.setCurrentTab(0);
        mTabLayout_1.showDot(2);
        mTabLayout_1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
//                Toast.makeText(MainActivity.this, "onTabSelect", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabReselect(int position) {
//                Toast.makeText(MainActivity.this, "onTabReselect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("是否退出")
                .setMessage("是否推出应用")
                .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                })
                .show();
    }

    @Override
    protected void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
