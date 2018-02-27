package cn.gridlife.xiaobei.fangdaicalculator.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.gridlife.generallibrary.activities.BActivity;
import cn.gridlife.xiaobei.fangdaicalculator.R;

public class MainActivity extends BActivity {


    @Override
    protected void initData() {
        private String[] mTitles = {"历史","新局","攻略"};
        private int[] mIconUnSelectIds = {R.drawable.ic_history, R.drawable.ic_new_game, R.drawable.ic_strategy};
        private int[] mIconSelectIds = {R.drawable.ic_history_selected, R.drawable.ic_new_game_selected, R.drawable.ic_strategy_selected};
    }

    @Override
    protected void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
