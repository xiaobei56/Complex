package cn.gridlife.xiaobei.fangdaicalculator;

import com.tencent.bugly.crashreport.CrashReport;

import cn.gridlife.generallibrary.BApp;

/**
 * Created by BZB on 2018/2/27.
 * Project: Complex.
 * Package：cn.gridlife.xiaobei.fangdaicalculator.
 */

public class App extends BApp {
    @Override
    protected void initData() {
//        CrashReport.initCrashReport(getApplicationContext()); Manifest中注册bugly；
        CrashReport.initCrashReport(getApplicationContext(), "0b67172a39", false);
    }
}
