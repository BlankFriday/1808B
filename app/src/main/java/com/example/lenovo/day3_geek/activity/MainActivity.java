package com.example.lenovo.day3_geek.activity;
//卫晨旭  1808D

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.base.BaseActivity;
import com.example.lenovo.day3_geek.base.Constants;
import com.example.lenovo.day3_geek.fragment.AboutFragment;
import com.example.lenovo.day3_geek.fragment.CollectFragment;
import com.example.lenovo.day3_geek.fragment.GankListFragment;
import com.example.lenovo.day3_geek.fragment.GoldFragment;
import com.example.lenovo.day3_geek.fragment.SettingFragment;
import com.example.lenovo.day3_geek.fragment.V2exFragment;
import com.example.lenovo.day3_geek.fragment.WeChatFragment;
import com.example.lenovo.day3_geek.fragment.ZhiHuDailyNewsFragment;
import com.example.lenovo.day3_geek.presenter.MainPresenter;
import com.example.lenovo.day3_geek.utils.SpUtil;
import com.example.lenovo.day3_geek.utils.ToastUtil;
import com.example.lenovo.day3_geek.view.MainView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

    private static final String TAG = "MainActivity";
    @BindView(R.id.search_view)
    MaterialSearchView mSearch;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fram_container)
    FrameLayout fram;
    @BindView(R.id.nav)
    NavigationView nav;
    @BindView(R.id.dl)
    DrawerLayout dl;
    @BindView(R.id.ll)
    LinearLayout ll;
    private ArrayList<Fragment> fragments;
    private ArrayList<Integer> titles;
    private FragmentManager fragmentManager;
    private int TYPE_ZHIHU = 0;
    private int TYPE_WECHAT = 1;
    private int TYPE_GANK = 2;
    private int TYPE_GOLD = 3;
    private int TYPE_V2EX = 4;
    private int TYPE_COLLECT = 5;
    public static int TYPE_SETTING = 6;
    private int TYPE_ABOUT = 7;
    private int mLastFragmentPosition;
    private MenuItem mSearchItem;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //点击返回键
        if(keyCode==KeyEvent.KEYCODE_BACK){
            //声明弹出对象并初始化
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("提示：");
            builder.setMessage("是否退出?");
            //设置确定按钮
            builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            //设置取消按钮
            builder.setPositiveButton("取消",null);
            //显示弹窗
            builder.show();
        }
        return super.onKeyDown(keyCode,event);
    }




    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    protected void initView() {
        fragmentManager = getSupportFragmentManager();
        toolbar.setTitleTextColor(getResources().getColor(R.color.c_white));

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.app_name, R.string.app_name);
        dl.addDrawerListener(toggle);
        toggle.syncState();

        initFragments();
        initTitles();
        addZhiHuDailyNewsFragment();

        dl.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                ll.setX(dl.getWidth()*slideOffset);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    private void addZhiHuDailyNewsFragment() {
        //如果是因为切换日夜间模式导致activity重建,需要直接进入设置Fragment
        int type = (int) SpUtil.getParam(Constants.DAY_NIGHT_FRAGMENT_POS, TYPE_ZHIHU);
        toolbar.setTitle(titles.get(0));
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fram_container, fragments.get(type));
        transaction.commit();

    }

    private void initTitles() {
        titles = new ArrayList<>();
        titles.add(R.string.zhihu);
        titles.add(R.string.wechat);
        titles.add(R.string.gank);
        titles.add(R.string.gold);
        titles.add(R.string.v2ex);
        titles.add(R.string.collect);
        titles.add(R.string.set);
        titles.add(R.string.about);
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new ZhiHuDailyNewsFragment());
        fragments.add(new WeChatFragment());
        fragments.add(new GankListFragment());
        fragments.add(new GoldFragment());
        fragments.add(new V2exFragment());
        fragments.add(new CollectFragment());
        fragments.add(new SettingFragment());
        fragments.add(new AboutFragment());
    }

    protected void initListener() {
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId != R.id.info_title && itemId != R.id.options_title) {
                    item.setChecked(true);
                    switch (itemId) {
                        case R.id.zhihu:
                            switchFragment(TYPE_ZHIHU);
                            break;
                        case R.id.wechat:
                            switchFragment(TYPE_WECHAT);
                            break;
                        case R.id.gank:
                            switchFragment(TYPE_GANK);
                            break;
                        case R.id.gold:
                            switchFragment(TYPE_GOLD);
                            break;
                        case R.id.v2ex:
                            mSearch.setVisibility(View.GONE);
                            switchFragment(TYPE_V2EX);
                            break;
                        case R.id.collect:
                            switchFragment(TYPE_COLLECT);
                            break;
                        case R.id.setting:
                            switchFragment(TYPE_SETTING);
                            break;
                        case R.id.about:
                            switchFragment(TYPE_ABOUT);
                            break;
                    }
                    dl.closeDrawer(Gravity.LEFT);//自动关闭侧滑栏
                } else {
                    item.setChecked(false);
                }

                return false;
            }
        });
        mSearch.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //按下搜索或者提交的时候回调,
                ToastUtil.showShort("提交的内容:"+query);
                Log.d(TAG, "onQueryTextSubmit: "+query);
                EventBus.getDefault().post(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //搜索框内容发生改变的回调,
                ToastUtil.showShort(newText);
                return false;
            }
        });
        mSearch.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });
    }

    private void switchFragment(int type) {
        Fragment fragment = fragments.get(type);

        Fragment hideFragmet = fragments.get(mLastFragmentPosition);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //如果该Fragment对象被添加到了它的Activity中，那么它返回true，否则返回false。
        if (!fragment.isAdded()) {
            transaction.add(R.id.fram_container, fragment);
        }
        transaction.hide(hideFragmet);
        transaction.show(fragment);
        transaction.commit();
        toolbar.setTitle(titles.get(type));
        mLastFragmentPosition = type;

        //显示和隐藏搜索框
        if (type == TYPE_WECHAT || type == TYPE_GANK){
            mSearchItem.setVisible(true);
        }else {
            mSearchItem.setVisible(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);
        mSearchItem = menu.findItem(R.id.action_search);
        //掩藏搜索框
        mSearchItem.setVisible(false);
        mSearch.setMenuItem(mSearchItem);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mSearch.isSearchOpen()){
            mSearch.closeSearch();
        }else {
            super.onBackPressed();
        }
    }
}
