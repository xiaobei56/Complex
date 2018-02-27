package cn.gridlife.gametools.fragments.navFunction;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import cn.gridlife.gametools.R;
import cn.gridlife.gametools.entrys.TabEntity;
import cn.gridlife.gametools.fragments.wkAssistance.HistoryFragment;
import cn.gridlife.gametools.fragments.wkAssistance.NewGameFragment;
import cn.gridlife.gametools.fragments.wkAssistance.StrategyFragment;
import cn.gridlife.gametools.utils.ViewFindUtils;
import cn.gridlife.generallibrary.fragments.BFragment;

/**
 * @author BZB
 * @date 2018/1/30
 * project Speach
 */

public class AssistantWkFragment extends BFragment {
    FragmentActivity activity;
//    private CommonTabLayout mTabLayout_3;
    private View mDecorView;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private String[] mTitles = {"历史","新局","攻略"};
    private int[] mIconUnSelectIds = {R.drawable.ic_history, R.drawable.ic_new_game, R.drawable.ic_strategy};
    private int[] mIconSelectIds = {R.drawable.ic_history_selected, R.drawable.ic_new_game_selected, R.drawable.ic_strategy_selected};
    private ArrayList<Fragment> fragments = new ArrayList<>();
    HistoryFragment historyFragment;
    NewGameFragment newGameFragment;
    StrategyFragment strategyFragment;

    /**
     * 第一次界面用户可见: 初始化View的数据
     */
    @Override
    protected void onFirstUserVisible() {
        Toast.makeText(activity, "onFirstUserVisible", Toast.LENGTH_SHORT).show();
        Log.e(TAG + "AssistantWkFragment", "onFirstUserVisible: onFirstUserVisible");
    }

    @Override
    public String getTitle() {
        return getString(R.string.text_wk_assistant);
    }

    /**
     * 当用户可见时
     */
    @Override
    protected void onUserVisible() {
        Toast.makeText(activity, "onUserVisible", Toast.LENGTH_SHORT).show();
        Log.e(TAG + "AssistantWkFragment", "onUserVisible: onUserVisible");
    }

    /**
     * 当用户不可见时
     */
    @Override
    protected void onUserInvisible() {
        Toast.makeText(activity, "onUserInvisible", Toast.LENGTH_SHORT).show();
    }

    /**
     * 初始化View 及 Event
     * onViewCreated
     *
     * @param view 布局View
     */
    @Override
    protected void initViewsAndEvents(View view) {
        activity = super.currentActivity;


        historyFragment = new HistoryFragment();
        newGameFragment = new NewGameFragment();
        strategyFragment = new StrategyFragment();
        fragments.add(historyFragment);
        fragments.add(newGameFragment);
        fragments.add(strategyFragment);

//        for (int i = 0; i < mTitles.length; i++) {
//            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnSelectIds[i]));
//        }
//
//        mDecorView = activity.getWindow().getDecorView();
//        /** with Fragments */
//        mTabLayout_3 = ViewFindUtils.find(mDecorView, R.id.tl_3);
//        mTabLayout_3 = view.findViewById(R.id.tl_3);
//        Toast.makeText(activity, "onFirstUserVisible", Toast.LENGTH_SHORT).show();
//        mTabLayout_3.setTabData(mTabEntities);
//        mTabLayout_3.setCurrentTab(1);
//        mTabLayout_3.setOnTabSelectListener(new OnTabSelectListener() {
//            @Override
//            public void onTabSelect(int position) {
//                Toast.makeText(activity, "onTabSelect", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onTabReselect(int position) {
//                Toast.makeText(activity, "onTabReselect", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.main_fragment_assistance;
    }

    /**
     * OnDestroy do something
     */
    @Override
    protected void DestroyViewAndThing() {

    }
}
