package cn.gridlife.xiaobei.fangdaicalculator.activities.calculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.gridlife.generallibrary.activities.BActivity;
import cn.gridlife.xiaobei.fangdaicalculator.R;
import cn.gridlife.xiaobei.fangdaicalculator.utils.ArithmeticUtils;
import cn.gridlife.xiaobei.fangdaicalculator.utils.SharePreferenceUtil;
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

    //    private LinearLayout llLenddingRate;
    private LinearLayout llLendingRateGJJ;
    private LinearLayout llLendingRateSY;
    private LinearLayout llMortgageYear;
    private LinearLayout llLendingRatio;
    private LinearLayout llHousePerPrice;
    private LinearLayout llHouseMeasure;
    private LinearLayout llLendingPrice;
    private LinearLayout llLendingPriceGJJ;
    private LinearLayout llLendingPriceSY;
    private LinearLayout llHousePrice;
    private EditText etHousePrice;
    private EditText etLendingPrice;
    private EditText etLendingPriceGJJ;
    private EditText etLendingPriceSY;
    private EditText etHouseMeasure;
    private EditText etHousePerPrice;
    private View div;
    private TextView tvLoanRatio;
    private TextView tvLendingRateGJJ;
    private TextView tvLendingRateSY;
    private TextView tvMortgageYear;
    private EditText etMortgageYear, etLendingRateGJJ, etLendingRateSY, etLoanRatio;
    private ArrayList<String> options1Items;
    private ArrayList<ArrayList<String>> options2Items;
    private ArrayList<ArrayList<ArrayList<String>>> options3Items;
    private ArrayList<String> list;
    private Button btnCalculate;
    int defGJJLessFiveYearPosition, defGJJMoreFiveYearPosition, defSYLessOneYearPosition, defSYLessFiveYearPosition, defSYMoreFiveYearPosition, defMortgagePosition, defLoanRatioPosition;

    boolean showGjjFwze = false;
    boolean showGjjDkze = false;
    boolean showGjjFwmj = false;
    boolean showSyFwze = false;
    boolean showSyDkze = false;
    boolean showSyFwmj = false;
    boolean showZH = false;

    enum SelectDataType {

        MORTGAGE_YEARS,//贷款年限
        LOAN_RATIO,//贷款比例

        LENDING_RATE_GJJ,//公积金贷款利率>5年 3.25
        LENDING_RATE_GJJ_LESS_FIVE_YEAR,//公积金贷款利率<5年 2.75

        LENDING_RATE_SY,//商业贷款利率>5年  4.90
        LENDING_RATE_SY_LESS_ONE_YEAR,//商业贷款利率<1年  4.35
        LENDING_RATE_SY_LESS_FIVE_YEAR,//商业贷款利率1 < 年<5年  4.75


    }

    private int getStatusBarHeight() {
        Resources resources = this.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Status height:" + height);
        return height;
    }

    @Override
    protected void initView() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup systemContent = findViewById(android.R.id.content);
            View statusBarView = new View(this);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight());
            statusBarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            systemContent.getChildAt(0).setFitsSystemWindows(true);
            systemContent.addView(statusBarView, 0, lp);
        }


        //状态栏 @ 顶部
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
//导航栏 @ 底部
//    getWindow().
//
//    addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//B
//这个加在哪个布局，该布局就会相应的向上（配置A）或者向下（配置B）或者向上下（同时配置AB）扩展
//        android:fitsSystemWindows="true"
//今天遇到类似问题，已解决。

        btnCalculate = (Button) findViewById(R.id.btn_calculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                boolean showGjjFwze=false;
//                boolean showGjjDkze=false;
//                boolean showGjjFwmj=false;
//                boolean showSyFwze=false;
//                boolean showSyDkze=false;
//                boolean showSyFwmj=false;
//                boolean showZH=false;
                double fwze = checkViewText(etHousePrice);
                double dkze = checkViewText(etLendingPrice);
                double fwmj = checkViewText(etHouseMeasure);
                double mpmdj = checkViewText(etHousePerPrice);
                double dkbl = checkViewText(etLoanRatio);
                double ajns = checkViewText(etMortgageYear);
                double gjjdze = checkViewText(etLendingPriceGJJ);
                double gjjlv = checkViewText(etLendingRateGJJ);
                double sydkze = checkViewText(etLendingPriceSY);
                double sydklv = checkViewText(etLendingRateSY);

                double ylvgjj = ArithmeticUtils.p(gjjlv/100, 12, 8);
                double ylvsy = ArithmeticUtils.p(sydklv/100, 12, 8);
                int months = (int) (ajns * 12);
                //按房屋总额
                double daikuanzonge_1 = ArithmeticUtils.mul(fwze* 10000, dkbl / 100) ;
                //按贷款总额
                double daikuanzonge_2 = dkze * 10000;
                //按房屋面积
                double daikuanzonge_3 = ArithmeticUtils.mul(dkbl / 100, ArithmeticUtils.mul(fwmj, mpmdj));
                ///////////////////////////////////////////////////////////////////////////
                // 等额本息-公积金
                ///////////////////////////////////////////////////////////////////////////
                //每月月供额
                double myyge = ArithmeticUtils.p(ArithmeticUtils.mul(     ArithmeticUtils.mul(daikuanzonge_1, ylvgjj), ArithmeticUtils.pow(1 + ylvgjj, months).doubleValue()    ), ArithmeticUtils.pow( 1 + ylvgjj,  months).doubleValue() - 1,2);
                //总还款额
                double zhke = ArithmeticUtils.p(ArithmeticUtils.mul(     ArithmeticUtils.mul(daikuanzonge_1, ylvgjj), ArithmeticUtils.pow(1 + ylvgjj, months).doubleValue()    ), ArithmeticUtils.pow( 1 + ylvgjj,  months).doubleValue() - 1,2)*months;
                //支付利息
                double zflx=zhke-daikuanzonge_1;

                //分期数 months

                ///////////////////////////////////////////////////////////////////////////
                // 等额本金-公积金
                ///////////////////////////////////////////////////////////////////////////
                //每月还本金
                double myhbj=ArithmeticUtils.p(daikuanzonge_1,months);
                //第一个月还款金额
                double dygyhkje=myhbj+ArithmeticUtils.mul(daikuanzonge_1,ylvgjj);
                //每月减少
                double myjs=ArithmeticUtils.mul(myhbj,ylvgjj);

                //等额本金贷款总利息
                double debjdkzlx=(myjs+ArithmeticUtils.mul(daikuanzonge_1,ylvgjj))*months/2;


                Toast.makeText(FangDaiCalculatorActivity.this, ""+zhke+"  ---" + debjdkzlx, Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(FangDaiCalculatorActivity.this)
                        .setTitle("月还款额")
                        .setMessage("等额本息月还款额："+myyge + "  "+"等额本金总利息："+debjdkzlx+"每月还本金"+myhbj+"  每月减少"+myjs+"  第一个月还款金额"+dygyhkje).setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                builder.show();

            }
        });

        mDecorView = getWindow().getDecorView();

        mTabLayout1 = ViewFindUtils.find(mDecorView, R.id.stl_1);
        mTabLayout2 = ViewFindUtils.find(mDecorView, R.id.stl_2);
        mTabLayout3 = ViewFindUtils.find(mDecorView, R.id.stl_3);

        div = (View)

                findViewById(R.id.div);
        //组合贷款布局
        llLendingPriceGJJ = (LinearLayout) findViewById(R.id.ll_loan_price_gjj);
        llLendingPriceSY = (LinearLayout) findViewById(R.id.ll_loan_price_sy);
        llLendingRateGJJ = (LinearLayout) findViewById(R.id.ll_lending_rate_gjj);
        llLendingRateSY = (LinearLayout) findViewById(R.id.ll_lending_rate_sy);

        tvLendingRateGJJ = (TextView) findViewById(R.id.tv_lending_rate_gjj);
        etLendingRateGJJ = (EditText) findViewById(R.id.et_lending_rate_gjj);

        tvLendingRateSY = (TextView) findViewById(R.id.tv_lending_rate_sy);
        etLendingRateSY = (EditText) findViewById(R.id.et_lending_rate_sy);

        etLendingPriceGJJ = (EditText) findViewById(R.id.et_loan_price_gjj);
        etLendingPriceSY = (EditText) findViewById(R.id.et_loan_price_sy);

        //房屋总额计算
        llHousePrice = (LinearLayout) findViewById(R.id.ll_house_price);
        etHousePrice = (EditText) findViewById(R.id.et_house_price);
        //贷款总额计算
        llLendingPrice = (LinearLayout) findViewById(R.id.ll_loan_price);
        etLendingPrice = (EditText) findViewById(R.id.et_loan_price);
        //房屋面积
        llHouseMeasure = (LinearLayout) findViewById(R.id.ll_house_measure);
        llHousePerPrice = (LinearLayout) findViewById(R.id.ll_house_per_price);
        etHousePerPrice = (EditText) findViewById(R.id.et_house_per_price);
        etHouseMeasure = (EditText) findViewById(R.id.et_house_measure);
        //贷款比率
        llLendingRatio = (LinearLayout) findViewById(R.id.ll_loan_ratio);
        tvLoanRatio = (TextView) findViewById(R.id.tv_loan_ratio);
        etLoanRatio = (EditText) findViewById(R.id.et_loan_ratio);
        //按揭年数
        llMortgageYear = (LinearLayout) findViewById(R.id.ll_mortgage_year);
        tvMortgageYear = (TextView) findViewById(R.id.tv_mortgage_year);
        etMortgageYear = (EditText) findViewById(R.id.et_mortgage_year);

        //贷款利率
//        llLenddingRate = (LinearLayout) findViewById(R.id.ll_lending_rate);
//        tvLenddingRate = (TextView) findViewById(R.id.et_lending_rate);

        options1Items = new ArrayList<>();
        options2Items = new ArrayList<>();
        options3Items = new ArrayList<>();
        list = new ArrayList<>();

        llLendingRatio.setOnClickListener(v ->

                ShowPickerView(getRatioList(), SelectDataType.LOAN_RATIO));

        llMortgageYear.setOnClickListener(v ->

                ShowPickerView(getMortgageList(), SelectDataType.MORTGAGE_YEARS));


//        llLendingRate.setOnClickListener(v -> ShowPickerView(getRateList((float) 4.9), SelectDataType.LENDING_RATE));
        llLendingRateGJJ.setOnClickListener(v -> ShowPickerView(getRateList((float) 3.25), SelectDataType.LENDING_RATE_GJJ));
//        llLendingRateGJJ.setOnClickListener(v -> ShowPickerView(getRateList((float) 2.75), SelectDataType.LENDING_RATE_GJJ_LESS_FIVE_YEAR));
        llLendingRateSY.setOnClickListener(v -> ShowPickerView(getRateList((float) 4.9), SelectDataType.LENDING_RATE_SY));
//        llLendingRateSY.setOnClickListener(v -> ShowPickerView(getRateList((float) 4.75), SelectDataType.LENDING_RATE_SY_LESS_FIVE_YEAR));
//        llLendingRateSY.setOnClickListener(v -> ShowPickerView(getRateList((float) 4.35), SelectDataType.LENDING_RATE_SY_LESS_ONE_YEAR));

        show_GJJ_FWZE();

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
                    int year = Integer.parseInt(etMortgageYear.getText().toString());
                    initRateData(year);
                    switch (position) {
                        case 0:
                            if (tab2 == 0) {
                                show_GJJ_FWZE();
                            } else if (tab2 == 1) {
                                show_GJJ_DKZE();
                            } else {
                                show_GJJ_FWMJ();
                            }

                            break;
                        case 1:
                            showSY();
                            if (tab2 == 0) {
                                show_SY_FWZE();
                            } else if (tab2 == 1) {
                                show_SY_DKZE();
                            } else {
                                show_SY_FWMJ();
                            }
                            break;
                        case 2:
                            show_ZU_DKZE();
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
                    int tab1 = mTabLayout1.getCurrentTab();
                    switch (position) {
                        case 0:
                            if (tab1 == 0)
                                show_GJJ_FWZE();
                            else if (tab1 == 1) {
                                show_SY_FWZE();
                            } else if (mTabLayout1.getCurrentTab() == 2) {
                                Toast.makeText(FangDaiCalculatorActivity.this, "不可使用组合贷款", Toast.LENGTH_SHORT).show();
                                mTabLayout1.setCurrentTab(0);
                                show_GJJ_FWZE();
                            }
                            break;
                        case 1:
                            if (tab1 == 0)
                                show_GJJ_DKZE();
                            else if (tab1 == 1) {
                                show_SY_DKZE();
                            } else if (tab1 == 2) {
                                show_ZU_DKZE();
                            }
                            break;
                        case 2:
                            if (tab1 == 0)
                                show_GJJ_FWMJ();
                            else if (tab1 == 1) {
                                show_SY_FWMJ();
                            } else if (tab1 == 2) {
                                Toast.makeText(FangDaiCalculatorActivity.this, "不可使用组合贷款", Toast.LENGTH_SHORT).show();
                                show_ZU_DKZE();
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
//                            TODO
                            break;
                        case 1:
//                            TODO
                            break;
                    }
                }

                @Override
                public void onTabReselect(int position) {
                }
            });
        } else

        {
            Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
        }
        etLoanRatio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Toast.makeText(FangDaiCalculatorActivity.this, "beforeTextChanged", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Toast.makeText(FangDaiCalculatorActivity.this, "onTextChanged", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText(FangDaiCalculatorActivity.this, "afterTextChanged", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Double checkViewText(View view) {
        String text;
        if (((EditText) view).getText() != null && !((TextView) view).getText().toString().equals("")) {
            return Double.parseDouble(((TextView) view).getText().toString().trim());
        } else return Double.parseDouble("0");
    }

    private void initRateData(int year) {
        if (year <= 1) {
            llLendingRateSY.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowPickerView(getRateList((float) 4.35), SelectDataType.LENDING_RATE_SY_LESS_ONE_YEAR);
                    tvLendingRateSY.setText(getRateList((float) 4.35).get(defSYLessOneYearPosition));
                }
            });
            tvLendingRateSY.setText("2018年商业贷款基准利率(4.35%)");
            etLendingRateSY.setText("4.35");
            llLendingRateGJJ.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowPickerView(getRateList((float) 2.75), SelectDataType.LENDING_RATE_GJJ_LESS_FIVE_YEAR);
                    tvLendingRateGJJ.setText(getRateList((float) 2.75).get(defGJJLessFiveYearPosition));
                }
            });
            tvLendingRateGJJ.setText("2018年公积金贷款基准利率(2.75%)");
            etLendingRateGJJ.setText("2.75");
        } else if (year > 1 && year <= 5) {
            llLendingRateSY.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowPickerView(getRateList((float) 4.75), SelectDataType.LENDING_RATE_SY_LESS_FIVE_YEAR);
                    tvLendingRateSY.setText(getRateList((float) 4.75).get(defSYLessFiveYearPosition));
                }
            });
            llLendingRateGJJ.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowPickerView(getRateList((float) 2.75), SelectDataType.LENDING_RATE_GJJ_LESS_FIVE_YEAR);
                    tvLendingRateGJJ.setText(getRateList((float) 2.75).get(defGJJLessFiveYearPosition));
                }
            });
            tvLendingRateSY.setText("2018年商业贷款基准利率(4.75%)");
            etLendingRateSY.setText(" 4.75");
            tvLendingRateGJJ.setText("2018年公积金贷款基准利率(2.75%)");
            etLendingRateGJJ.setText("2.75");
        } else {
            llLendingRateSY.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowPickerView(getRateList((float) 4.9), SelectDataType.LENDING_RATE_SY);
                    tvLendingRateSY.setText(getRateList((float) 4.9).get(defSYMoreFiveYearPosition));
                }
            });
            llLendingRateGJJ.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowPickerView(getRateList((float) 3.25), SelectDataType.LENDING_RATE_GJJ);
                    tvLendingRateGJJ.setText(getRateList((float) 3.25).get(defGJJMoreFiveYearPosition));
                }
            });
            tvLendingRateSY.setText("2018年商业贷款基准利率(4.9%)");
            etLendingRateSY.setText(" 4.9");
            tvLendingRateGJJ.setText("2018年公积金贷款基准利率(3.25)");
            etLendingRateGJJ.setText("3.25");
        }
        llLendingRateGJJ.invalidate();
        llLendingRateSY.invalidate();
    }

    private ArrayList<String> getRatioList() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; ) {
            i += 10;
            list.add("房屋总价的" + i + "%");
        }
        return list;
    }

    private ArrayList<String> getMortgageList() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            list.add(i + "年（" + i * 12 + "期）");
        }
        return list;
    }

    private ArrayList<String> getRateList(float baseRate) {
        ArrayList<String> list = new ArrayList<>();
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.0000");

        for (int i = -30; i < 40; i += 5) {

            float lessBase = (float) (1 + (i * 0.01));
            list.add("基准利率的" + lessBase + "倍" + "(" + df.format(baseRate * lessBase) + ")");

//            if (baseRate - (8 - i) * 0.5 > 0)
//                list.add();
//            list.add("基准利率的" + 0.7 + i * 0.5 + "倍" + "(" + 4.9 * (0.7 + i * 0.5) + ")");
        }
        list.remove(6);
        list.add(6, "2018年最新基准利率(" + baseRate + "%)");
        return list;
    }

    private void ShowPickerView(ArrayList<String> strings, SelectDataType sdt) {// 弹出选择器

        switch (sdt) {
            case MORTGAGE_YEARS:
                //TODO 按揭年数
                initPickView(sdt, "按揭年数", tvMortgageYear, etMortgageYear, strings, defMortgagePosition);
                break;
            case LOAN_RATIO:
                //TODO: 贷款比例
                initPickView(sdt, "贷款比例", tvLoanRatio, etLoanRatio, strings, defLoanRatioPosition);
                break;
            case LENDING_RATE_GJJ:
                //TODO：公积金 贷款利率
                initPickView(sdt, "公积金贷款利率", tvLendingRateGJJ, etLendingRateGJJ, strings, defGJJMoreFiveYearPosition);
                break;
            case LENDING_RATE_GJJ_LESS_FIVE_YEAR:
                //TODO: <5年 公积金贷款利率
                initPickView(sdt, "公积金贷款利率(少于5年)", tvLendingRateGJJ, etLendingRateGJJ, strings, defGJJLessFiveYearPosition);
                break;
            case LENDING_RATE_SY:
                //TODO: 商业 贷款利率
                initPickView(sdt, "商业贷款利率", tvLendingRateSY, etLendingRateSY, strings, defSYMoreFiveYearPosition);
                break;
            case LENDING_RATE_SY_LESS_ONE_YEAR:
                //TODO: 一年内商业贷款利率
                initPickView(sdt, "公积金贷款利率(<1年)", tvLendingRateSY, etLendingRateSY, strings, defSYLessOneYearPosition);
                break;
            case LENDING_RATE_SY_LESS_FIVE_YEAR:
                //TODO: 1年—5年商业贷款利率
                initPickView(sdt, "公积金贷款利率(1-5年)", tvLendingRateSY, etLendingRateSY, strings, defSYLessFiveYearPosition);
                break;
            default:
                break;

        }

    }

    /**
     * @param strings
     */
    private void initPickView(SelectDataType sdt, String title, TextView tvDetail, EditText num, ArrayList<String> strings, int defPosition) {
        OptionsPickerView pvOptions;
        pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = strings.get(options1);
                String temp;
                Pattern p;
                Matcher m;
                tvDetail.setText(tx);
                String regEx = "[^0-9|.]";
                switch (sdt) {
                    case MORTGAGE_YEARS:
                        //TODO 按揭年数
                        temp = tx.substring(0, 2);
                        p = Pattern.compile(regEx);
                        m = p.matcher(temp);
                        num.setText(m.replaceAll(""));
                        SharePreferenceUtil.setConfigValue(FangDaiCalculatorActivity.this, "MORTGAGE_YEARS", options1 + "");
                        initRateData(options1 + 1);
                        break;
                    case LOAN_RATIO:
                        //TODO: 贷款比例
                        temp = tx.substring(tx.length() - 5, tx.length());
                        p = Pattern.compile(regEx);
                        m = p.matcher(temp);
                        num.setText(m.replaceAll(""));
                        SharePreferenceUtil.setConfigValue(FangDaiCalculatorActivity.this, options1 + "", "LOAN_RATIO");
                        break;
                    case LENDING_RATE_GJJ:
                        //TODO：公积金 贷款利率
                        temp = tx.substring(tx.length() - 8, tx.length());
                        p = Pattern.compile(regEx);
                        m = p.matcher(temp);
                        num.setText(m.replaceAll(""));
                        SharePreferenceUtil.setConfigValue(FangDaiCalculatorActivity.this, options1 + "", "LENDING_RATE_GJJ");
                        break;
                    case LENDING_RATE_GJJ_LESS_FIVE_YEAR:
                        //TODO: <5年 公积金贷款利率
                        temp = tx.substring(tx.length() - 8, tx.length());
                        p = Pattern.compile(regEx);
                        m = p.matcher(temp);
                        num.setText(m.replaceAll(""));
                        SharePreferenceUtil.setConfigValue(FangDaiCalculatorActivity.this, options1 + "", "LENDING_RATE_GJJ_LESS_FIVE_YEAR");
                        break;
                    case LENDING_RATE_SY:
                        //TODO: 商业 贷款利率
                        temp = tx.substring(tx.length() - 8, tx.length());
                        p = Pattern.compile(regEx);
                        m = p.matcher(temp);
                        num.setText(m.replaceAll(""));
                        SharePreferenceUtil.setConfigValue(FangDaiCalculatorActivity.this, options1 + "", "LENDING_RATE_SY");
                        break;
                    case LENDING_RATE_SY_LESS_ONE_YEAR:
                        //TODO: 一年内商业贷款利率
                        temp = tx.substring(tx.length() - 8, tx.length());
                        p = Pattern.compile(regEx);
                        m = p.matcher(temp);
                        num.setText(m.replaceAll(""));
                        SharePreferenceUtil.setConfigValue(FangDaiCalculatorActivity.this, options1 + "", "LENDING_RATE_SY_LESS_ONE_YEAR");
                        break;
                    case LENDING_RATE_SY_LESS_FIVE_YEAR:
                        //TODO: 1年—5年商业贷款利率
                        temp = tx.substring(tx.length() - 8, tx.length());
                        p = Pattern.compile(regEx);
                        m = p.matcher(temp);
                        num.setText(m.replaceAll(""));
                        SharePreferenceUtil.setConfigValue(FangDaiCalculatorActivity.this, options1 + "", "LENDING_RATE_SY_LESS_FIVE_YEAR");
                        break;
                    default:
                        break;

                }
            }
        })
                .setTitleText(title)
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLUE) //设置选中项文字颜色
                .setContentTextSize(18)
                .build();

        pvOptions.setPicker(strings);//一级选择器
        pvOptions.setSelectOptions(defPosition);
//        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
//        pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器
        pvOptions.show();
    }

    private void showSY() {
        //TODO 更改为： 商业贷款利率
    }

    private void showGJJ() {
        //TODO 更改为：公积金贷款利率数据

    }

    /**
     * 公积金+ 房屋总额
     */

    private void show_GJJ_FWZE() {
//        房屋总价
        llHousePrice.setVisibility(View.VISIBLE);
        llLendingPrice.setVisibility(View.GONE);
        llHouseMeasure.setVisibility(View.GONE);
        llHousePerPrice.setVisibility(View.GONE);
        llLendingRatio.setVisibility(View.VISIBLE);
        llMortgageYear.setVisibility(View.VISIBLE);
        llLendingPriceGJJ.setVisibility(View.GONE);
        llLendingRateGJJ.setVisibility(View.VISIBLE);
        llLendingPriceSY.setVisibility(View.GONE);
        llLendingRateSY.setVisibility(View.GONE);
        div.setVisibility(View.GONE);
        showGjjFwze = true;
        showGjjDkze = false;
        showGjjFwmj = false;
        showSyFwze = false;
        showSyDkze = false;
        showSyFwmj = false;
        showZH = false;

    }

    /**
     * 公积金+ 贷款总额
     */
    private void show_GJJ_DKZE() {
        llHousePrice.setVisibility(View.GONE);
        llLendingPrice.setVisibility(View.VISIBLE);
        llHouseMeasure.setVisibility(View.GONE);
        llHousePerPrice.setVisibility(View.GONE);
        llLendingRatio.setVisibility(View.GONE);
        llMortgageYear.setVisibility(View.VISIBLE);
        llLendingPriceGJJ.setVisibility(View.GONE);
        llLendingRateGJJ.setVisibility(View.VISIBLE);
        llLendingPriceSY.setVisibility(View.GONE);
        llLendingRateSY.setVisibility(View.GONE);
        div.setVisibility(View.GONE);
        showGjjFwze = false;
        showGjjDkze = true;
        showGjjFwmj = false;
        showSyFwze = false;
        showSyDkze = false;
        showSyFwmj = false;
        showZH = false;
    }

    /**
     * 公积金+ 房屋面积
     */
    private void show_GJJ_FWMJ() {
        llHousePrice.setVisibility(View.GONE);
        llLendingPrice.setVisibility(View.GONE);

        llHouseMeasure.setVisibility(View.VISIBLE);
        llHousePerPrice.setVisibility(View.VISIBLE);

        llLendingRatio.setVisibility(View.VISIBLE);
        llMortgageYear.setVisibility(View.VISIBLE);
        llLendingPriceGJJ.setVisibility(View.GONE);
        llLendingRateGJJ.setVisibility(View.VISIBLE);
        llLendingPriceSY.setVisibility(View.GONE);
        llLendingRateSY.setVisibility(View.GONE);
        div.setVisibility(View.GONE);
        showGjjFwze = false;
        showGjjDkze = false;
        showGjjFwmj = true;
        showSyFwze = false;
        showSyDkze = false;
        showSyFwmj = false;
        showZH = false;
    }

    /**
     * 商贷+ 房屋总额
     */
    private void show_SY_FWZE() {
        llHousePrice.setVisibility(View.VISIBLE);
        llLendingPrice.setVisibility(View.GONE);
        llHouseMeasure.setVisibility(View.GONE);
        llHousePerPrice.setVisibility(View.GONE);
        llLendingRatio.setVisibility(View.VISIBLE);
        llMortgageYear.setVisibility(View.VISIBLE);
        llLendingPriceGJJ.setVisibility(View.GONE);
        llLendingRateGJJ.setVisibility(View.GONE);
        llLendingPriceSY.setVisibility(View.GONE);
        llLendingRateSY.setVisibility(View.VISIBLE);
        div.setVisibility(View.GONE);
        showGjjFwze = false;
        showGjjDkze = false;
        showGjjFwmj = false;
        showSyFwze = true;
        showSyDkze = false;
        showSyFwmj = false;
        showZH = false;
    }

    /**
     * 商贷+ 贷款总额
     */
    private void show_SY_DKZE() {
        llHousePrice.setVisibility(View.GONE);
        llLendingPrice.setVisibility(View.VISIBLE);
        llHouseMeasure.setVisibility(View.GONE);
        llHousePerPrice.setVisibility(View.GONE);
        llLendingRatio.setVisibility(View.GONE);
        llMortgageYear.setVisibility(View.VISIBLE);
        llLendingPriceGJJ.setVisibility(View.GONE);
        llLendingRateGJJ.setVisibility(View.GONE);
        llLendingPriceSY.setVisibility(View.GONE);
        llLendingRateSY.setVisibility(View.VISIBLE);
        div.setVisibility(View.GONE);

        showGjjFwze = false;
        showGjjDkze = false;
        showGjjFwmj = false;
        showSyFwze = false;
        showSyDkze = true;
        showSyFwmj = false;
        showZH = false;
    }

    /**
     * 商贷+ 房屋面积
     */
    private void show_SY_FWMJ() {
        llHousePrice.setVisibility(View.GONE);
        llLendingPrice.setVisibility(View.GONE);

        llHouseMeasure.setVisibility(View.VISIBLE);
        llHousePerPrice.setVisibility(View.VISIBLE);

        llLendingRatio.setVisibility(View.VISIBLE);
        llMortgageYear.setVisibility(View.VISIBLE);
        llLendingPriceGJJ.setVisibility(View.GONE);
        llLendingRateGJJ.setVisibility(View.GONE);
        llLendingPriceSY.setVisibility(View.GONE);
        llLendingRateSY.setVisibility(View.VISIBLE);
        div.setVisibility(View.GONE);

        showGjjFwze = false;
        showGjjDkze = false;
        showGjjFwmj = false;
        showSyFwze = false;
        showSyDkze = false;
        showSyFwmj = true;
        showZH = false;
    }

    /**
     * 组合+ 贷款总额
     */
    private void show_ZU_DKZE() {
        llHousePrice.setVisibility(View.GONE);
        llLendingPrice.setVisibility(View.GONE);

        llHouseMeasure.setVisibility(View.GONE);
        llHousePerPrice.setVisibility(View.GONE);

        llLendingRatio.setVisibility(View.GONE);
        llMortgageYear.setVisibility(View.VISIBLE);

        llLendingPriceGJJ.setVisibility(View.VISIBLE);
        llLendingRateGJJ.setVisibility(View.VISIBLE);
        llLendingPriceSY.setVisibility(View.VISIBLE);
        llLendingRateSY.setVisibility(View.VISIBLE);
        div.setVisibility(View.VISIBLE);

        showGjjFwze = false;
        showGjjDkze = false;
        showGjjFwmj = false;
        showSyFwze = false;
        showSyDkze = false;
        showSyFwmj = false;
        showZH = true;
    }


    @Override
    protected void initData() {
        defGJJLessFiveYearPosition = Integer.parseInt(SharePreferenceUtil.getConfigValue(FangDaiCalculatorActivity.this, "LENDING_RATE_GJJ_LESS_FIVE_YEAR", "6") == null ? "6" : SharePreferenceUtil.getConfigValue(FangDaiCalculatorActivity.this, "LENDING_RATE_GJJ_LESS_FIVE_YEAR", "6"));
        defGJJMoreFiveYearPosition = Integer.parseInt(SharePreferenceUtil.getConfigValue(FangDaiCalculatorActivity.this, "LENDING_RATE_GJJ", "6") == null ? "6" : SharePreferenceUtil.getConfigValue(FangDaiCalculatorActivity.this, "LENDING_RATE_GJJ", "6"));
        defSYLessOneYearPosition = Integer.parseInt(SharePreferenceUtil.getConfigValue(FangDaiCalculatorActivity.this, "LENDING_RATE_SY_LESS_ONE_YEAR", "6") == null ? "6" : SharePreferenceUtil.getConfigValue(FangDaiCalculatorActivity.this, "LENDING_RATE_SY_LESS_ONE_YEAR", "6"));
        defSYLessFiveYearPosition = Integer.parseInt(SharePreferenceUtil.getConfigValue(FangDaiCalculatorActivity.this, "LENDING_RATE_SY_LESS_FIVE_YEAR", "6") == null ? "6" : SharePreferenceUtil.getConfigValue(FangDaiCalculatorActivity.this, "LENDING_RATE_SY_LESS_FIVE_YEAR", "6"));
        defSYMoreFiveYearPosition = Integer.parseInt(SharePreferenceUtil.getConfigValue(FangDaiCalculatorActivity.this, "MORTGAGE_YEARS", "6") == null ? "6" : SharePreferenceUtil.getConfigValue(FangDaiCalculatorActivity.this, "MORTGAGE_YEARS", "6"));
        defMortgagePosition = Integer.parseInt(SharePreferenceUtil.getConfigValue(FangDaiCalculatorActivity.this, "MORTGAGE_YEARS", "19") == null ? "19" : SharePreferenceUtil.getConfigValue(FangDaiCalculatorActivity.this, "MORTGAGE_YEARS", "19"));
        defLoanRatioPosition = Integer.parseInt(SharePreferenceUtil.getConfigValue(FangDaiCalculatorActivity.this, "LOAN_RATIO", "6") == null ? "6" : SharePreferenceUtil.getConfigValue(FangDaiCalculatorActivity.this, "LOAN_RATIO", "6"));


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_calculator_fangdai;
    }
}
