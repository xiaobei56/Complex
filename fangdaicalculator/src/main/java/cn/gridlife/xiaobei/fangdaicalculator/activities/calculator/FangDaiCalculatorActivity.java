package cn.gridlife.xiaobei.fangdaicalculator.activities.calculator;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
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

    private LinearLayout llLenddingRate;
    private LinearLayout llLenddingRateGJJ;
    private LinearLayout llLenddingRateSY;
    private LinearLayout llMorgageYear;
    private LinearLayout llLoanRatio;
    private LinearLayout llHousePerPrice;
    private LinearLayout llHouseMeasure;
    private LinearLayout llLoanPrice;
    private LinearLayout llLoanPriceGJJ;
    private LinearLayout llLoanPriceSY;
    private LinearLayout llHousePrice;
    private EditText etHousePrice;
    private EditText etLoanPrice;
    private EditText etLoanPriceGJJ;
    private EditText etLoanPriceSY;
    private EditText etHouseMeasure;
    private EditText etHousePerPrice;

    private TextView tvLoanRatio;
    private TextView tvLoanRatioGJJ;
    private TextView tvLoanRatioSY;
    private TextView tvMorgageYear;
    private TextView tvLenddingRate;


    @Override
    protected void initView() {
        mDecorView = getWindow().getDecorView();
        mTabLayout1 = ViewFindUtils.find(mDecorView, R.id.stl_1);
        mTabLayout2 = ViewFindUtils.find(mDecorView, R.id.stl_2);
        mTabLayout3 = ViewFindUtils.find(mDecorView, R.id.stl_3);


        //组合贷款布局
        llLoanPriceGJJ = (LinearLayout) findViewById(R.id.ll_loan_price_gjj);
        llLoanPriceSY = (LinearLayout) findViewById(R.id.ll_loan_price_sy);
        llLenddingRateGJJ = (LinearLayout) findViewById(R.id.ll_lending_rate_gjj);
        llLenddingRateSY = (LinearLayout) findViewById(R.id.ll_lending_rate_sy);
        tvLoanRatioGJJ = (TextView) findViewById(R.id.tv_loan_ratio);
        tvLoanRatioSY = (TextView) findViewById(R.id.tv_loan_ratio);
        etLoanPriceGJJ = (EditText) findViewById(R.id.et_loan_price_gjj);
        etLoanPriceSY = (EditText) findViewById(R.id.et_loan_price_sy);


        //房屋总额计算
        llHousePrice = (LinearLayout) findViewById(R.id.ll_house_price);
        etHousePrice = (EditText) findViewById(R.id.et_house_price);

        //贷款总额计算
        llLoanPrice = (LinearLayout) findViewById(R.id.ll_loan_price);
        etLoanPrice = (EditText) findViewById(R.id.et_loan_price);
        //房屋面积
        llHouseMeasure = (LinearLayout) findViewById(R.id.ll_house_measure);
        llHousePerPrice = (LinearLayout) findViewById(R.id.ll_house_per_price);
        etHousePerPrice = (EditText) findViewById(R.id.et_house_per_price);
        etHouseMeasure = (EditText) findViewById(R.id.et_house_measure);

        //贷款比率
        llLoanRatio = (LinearLayout) findViewById(R.id.ll_loan_ratio);
        tvLoanRatio = (TextView) findViewById(R.id.tv_loan_ratio);
        //按揭年数
        llMorgageYear = (LinearLayout) findViewById(R.id.ll_mortgage_year);
        tvMorgageYear = (TextView) findViewById(R.id.tv_mortgage_year);
        //贷款利率
        llLenddingRate = (LinearLayout) findViewById(R.id.ll_lending_rate);
        tvLenddingRate = (TextView) findViewById(R.id.tv_lending_rate);


        showHousePrice();
        showGJJ();
        hideZH();

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
                    int tab2 = mTabLayout2.getCurrentTab();
                    switch (position) {
                        case 0:

                            showGJJ();
                            if (tab2 == 0) {
                                showHousePrice();
                            } else if (tab2 == 1) {
                                showLoanPrice();
                            } else {
                                showHouseMeasure();
                            }
                            break;
                        case 1:
                            showSY();
                            if (tab2 == 0) {
                                showHousePrice();
                            } else if (tab2 == 1) {
                                showLoanPrice();
                            } else {
                                showHouseMeasure();
                            }
                            break;
                        case 2:
                            showZH();
                            Toast.makeText(FangDaiCalculatorActivity.this, "计算方式仅可使用：贷款总额", Toast.LENGTH_SHORT).show();
                            mTabLayout2.setCurrentTab(1);
                            break;
                    }

                }

                @Override
                public void onTabReselect(int position) {

                }
            });
            mTabLayout2.setOnTabSelectListener(new OnTabSelectListener() {
                @Override
                public void onTabSelect(int position) {
                    switch (position) {
                        case 0:
                            showHousePrice();
                            if (mTabLayout1.getCurrentTab() == 2) {
                                Toast.makeText(FangDaiCalculatorActivity.this, "不可使用组合贷款", Toast.LENGTH_SHORT).show();
                                mTabLayout1.setCurrentTab(0);
                            }
                            break;
                        case 1:
                            showLoanPrice();
                            break;
                        case 2:
                            showHouseMeasure();
                            if (mTabLayout1.getCurrentTab() == 2) {
                                Toast.makeText(FangDaiCalculatorActivity.this, "不可使用组合贷款", Toast.LENGTH_SHORT).show();
                                mTabLayout1.setCurrentTab(0);
                            }
                            break;
                    }
                }

                @Override
                public void onTabReselect(int position) {

                }
            });
            mTabLayout3.setOnTabSelectListener(new OnTabSelectListener() {
                @Override
                public void onTabSelect(int position) {
                    switch (position) {
                        case 0:
                            Toast.makeText(FangDaiCalculatorActivity.this, "等额本息", Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            Toast.makeText(FangDaiCalculatorActivity.this, "等额本金", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onTabReselect(int position) {
                }
            });
        } else {
            Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
        }
    }

    private void showSY() {
        //TODO 更改为： 商业贷款利率
    }

    private void showGJJ() {
        //TODO 更改为：公积金贷款利率数据

    }

    private void showHouseMeasure() {
        hideZH();
        hideHousePrice();
        hideLoanPrice();
        llHouseMeasure.setVisibility(View.VISIBLE);
        llHousePerPrice.setVisibility(View.VISIBLE);
        llLoanRatio.setVisibility(View.VISIBLE);
    }


    private void showLoanPrice() {
        hideZH();
        hideHousePrice();
        hideHouseMeasure();
        llLoanPrice.setVisibility(View.VISIBLE);
    }

    private void showHousePrice() {
        hideZH();
        hideLoanPrice();
        hideHouseMeasure();
        llHousePrice.setVisibility(View.VISIBLE);
        llLoanRatio.setVisibility(View.VISIBLE);
    }

    private void hideLoanPrice() {
        llLoanPrice.setVisibility(View.GONE);
    }

    private void hideHousePrice() {
        llHousePrice.setVisibility(View.GONE);
        llLoanRatio.setVisibility(View.GONE);
    }

    private void hideHouseMeasure() {
        llHouseMeasure.setVisibility(View.GONE);
        llHousePerPrice.setVisibility(View.GONE);
        llLoanRatio.setVisibility(View.GONE);
    }


    private void hideZH() {
        llLoanPriceGJJ.setVisibility(View.GONE);
        llLoanPriceSY.setVisibility(View.GONE);
        llLenddingRateGJJ.setVisibility(View.GONE);
        llLenddingRateSY.setVisibility(View.GONE);
        llLenddingRate.setVisibility(View.VISIBLE);
    }

    private void showZH() {
        hideHousePrice();
        hideHouseMeasure();
        hideLoanPrice();
        llLoanPriceGJJ.setVisibility(View.VISIBLE);
        llLoanPriceSY.setVisibility(View.VISIBLE);
        llLenddingRateGJJ.setVisibility(View.VISIBLE);
        llLenddingRateSY.setVisibility(View.VISIBLE);
        llLenddingRate.setVisibility(View.GONE);
        //TODO 更改为  商业贷款利率 与 公积金贷款利率
    }


    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_calculator_fangdai;
    }
}
