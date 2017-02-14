package com.liwy.liwyfinalutils.indicator;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.liwy.anything.view.tabindicator.LiwyIndicator;
import com.liwy.anything.view.tabindicator.OnTabClickListener;
import com.liwy.anything.view.tabindicator.TabBean;
import com.liwy.liwyfinalutils.R;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends AppCompatActivity {
    private List<TabBean> tabs;
    //导航栏
    private LiwyIndicator liwyIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        initTabs();
        initView();
    }

    private void initView(){
//        TabConfig.tabDefColor = R.color.griditems_bg;
//        TabConfig.tabSelectedColor = R.color.colorPrimary;
        liwyIndicator = (LiwyIndicator)findViewById(R.id.liwyTabIndicator);
        liwyIndicator.setTabs(tabs);
        liwyIndicator.setmOnTabClickListener(new OnTabClickListener() {
            @Override
            public void onClick(View v) {
                LiwyIndicator.TabView tab = (LiwyIndicator.TabView)v;
                Toast.makeText(TabActivity.this,"点击了" + tab.getIndex(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initTabs(){
        tabs = new ArrayList<TabBean>();
        TabBean t1 = new TabBean("测试1",R.mipmap.app_aligame,R.mipmap.ic_launcher);
        TabBean t2 = new TabBean("测试2",R.mipmap.app_coupon,R.mipmap.ic_launcher);
        TabBean t3 = new TabBean("测试3",R.mipmap.app_exchange,R.mipmap.ic_launcher);
        tabs.add(t1);
        tabs.add(t2);
        tabs.add(t3);
    }
}
