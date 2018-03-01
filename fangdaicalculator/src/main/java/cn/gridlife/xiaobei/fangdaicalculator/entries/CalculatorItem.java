package cn.gridlife.xiaobei.fangdaicalculator.entries;

import android.graphics.Bitmap;

/**
 * Created by BZB on 2018/3/1.
 */

public class CalculatorItem {
    private Bitmap bitmap;
    private String title;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
