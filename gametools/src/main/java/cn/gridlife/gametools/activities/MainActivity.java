package cn.gridlife.gametools.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import cn.gridlife.gametools.R;
import cn.gridlife.gametools.fragments.navFunction.AssistantWkFragment;
import cn.gridlife.gametools.fragments.navFunction.CommunityWkFragment;
import cn.gridlife.gametools.fragments.navFunction.FaceWkFragment;
import cn.gridlife.gametools.fragments.navFunction.NetGamingWkFragment;
import cn.gridlife.generallibrary.activities.BActivity;
import cn.gridlife.generallibrary.activities.BFragmentActivity;

/**
 * MainActivity
 *
 * @author BZB
 */
public class MainActivity extends BFragmentActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.nv_main)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    private AssistantWkFragment assistantWkFragment = new AssistantWkFragment();
    private CommunityWkFragment communityWkFragment = new CommunityWkFragment();
    private FaceWkFragment faceWkFragment = new FaceWkFragment();
    private NetGamingWkFragment netGamingWkFragment = new NetGamingWkFragment();
    private Fragment currentFragment = new Fragment();
    private TextView tvTitle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * init data before initView
     */
    @Override
    protected void initData() {
        BindButterKnife();
    }

    /**
     * init View after initData
     */
    @Override
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        tvTitle = (TextView) findViewById(R.id.tv_title);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nv_main);
//        Resources resource=(Resources)getBaseContext().getResources();
//        ColorStateList csl=(ColorStateList)resource.getColorStateList(R.color.navigation_menu_item_color);
//        navigationView.setItemTextColor(csl);
///**设置MenuItem默认选中项**/
//        navigationView.getMenu().getItem(0).setChecked(true);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//                //将侧边栏顶部延伸至status bar
//                drawer.setFitsSystemWindows(true);
//                //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
//                drawer.setClipToPadding(false);
//            }
//
//        }

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };
        toggle.syncState();
        drawer.addDrawerListener(toggle);

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_assistant);
        switchFragment(assistantWkFragment).commit();
        setTitle(getString(R.string.text_wk_assistant));

    }

    protected void setTitle(String str) {
        if (tvTitle == null)
            tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(str == null ? "" : str);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private FragmentTransaction switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.fragment, targetFragment, targetFragment.getClass().getName());
        } else {
            transaction.hide(currentFragment)
                    .show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_assistant:
                //TODO
                switchFragment(assistantWkFragment).commit();
                setTitle(getString(R.string.text_wk_assistant));
                break;
            case R.id.nav_face_to_face:
                //TODO
                switchFragment(faceWkFragment).commit();
                setTitle(getString(R.string.text_wk_face_to_face));
                break;
            case R.id.nav_net_game:
                //TODO
                switchFragment(netGamingWkFragment).commit();
                setTitle(getString(R.string.text_wk_net_game));
                break;
            case R.id.nav_community:
                //TODO
                switchFragment(communityWkFragment).commit();
                setTitle(getString(R.string.text_wk_community));
                break;
            case R.id.nav_share:
                //TODO
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

    }
}
