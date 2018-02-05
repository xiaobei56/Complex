package cn.gridlife.myapplication;

import android.app.Application;
import android.content.Context;

import com.aispeech.AISpeech;

/**
 * Created by BZB on 2017/12/2.
 */

public class APP extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
        AISpeech.init(mContext);
        AISpeech.openLog();
    }
}
