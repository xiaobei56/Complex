package cn.gridlife.xiaobei.fangdaicalculator.activities.calculator;

import android.view.View;
import android.widget.Toast;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import cn.gridlife.generallibrary.activities.BActivity;
import cn.gridlife.xiaobei.fangdaicalculator.R;
import cn.gridlife.xiaobei.fangdaicalculator.utils.ViewFindUtils;

/**
 * Created by BZB on 2018/3/5.
 */

public class FangDaiCalculatorActivity extends BActivity {

    private String[] mTitles_1 = {"公积金贷款", "商业贷款", "组合贷款"};
    private String[] mTitles_2 = {"按房屋总额", "按贷款总额", "按房屋面积"};
    private String[] mTitles_3 = {"等额本息", "等额本金"};
    private View mDecorView;
    private SegmentTabLayout mTabLayout1, mTabLayout2, mTabLayout3;

    @Override
    protected void initData() {

    }
    @Override
    protected void initView() {
        mDecorView = getWindow().getDecorView();
        mTabLayout1 = ViewFindUtils.find(mDecorView, R.id.stl_1);
        mTabLayout2 = ViewFindUtils.find(mDecorView, R.id.stl_2);
        mTabLayout3 = ViewFindUtils.find(mDecorView, R.id.stl_3);
//        mTabLayout1= (SegmentTabLayout) findViewById(R.id.tl_1);
//        mTabLayout2= (SegmentTabLayout) findViewById(R.id.tl_2);
//        mTabLayout3= (SegmentTabLayout) findViewById(R.id.tl_3);
        if (mTabLayout1 != null && mTabLayout2 != null && mTabLayout3 != null) {
            mTabLayout1.setTabData(mTitles_1);
            mTabLayout2.setTabData(mTitles_2);
            mTabLayout3.setTabData(mTitles_3);

            mTabLayout1.setOnTabSelectListener(new OnTabSelectListener() {
                @Override
                public void onTabSelect(int position) {

                }

                @Override
                public void onTabReselect(int position) {

                }
            });
            mTabLayout2.setOnTabSelectListener(new OnTabSelectListener() {
                @Override
                public void onTabSelect(int position) {

                }

                @Override
                public void onTabReselect(int position) {

                }
            });
            mTabLayout3.setOnTabSelectListener(new OnTabSelectListener() {
                @Override
                public void onTabSelect(int position) {
                    Toast.makeText(FangDaiCalculatorActivity.this, position + "", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onTabReselect(int position) {
                }
            });
        } else {
            Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_calculator_fangdai;
    }
}
