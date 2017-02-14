package com.liwy.liwyfinalutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liwy.anything.view.topbar.ConfigTop;
import com.liwy.anything.view.topbar.LiwyTop;

public class TopActivity extends AppCompatActivity {
    private LiwyTop liwyTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        initView();
    }


    private void initView(){
        liwyTop = (LiwyTop)findViewById(R.id.liwytop);
        //配置类(配置控件样式,可配置详情可查看ConfigTop类)
        ConfigTop config = new ConfigTop();
        config.setLeftButtonImg(R.mipmap.ic_launcher);//设置左按钮图片
        //需先传入ConfigTop的实例对象.然后再调用setLiwyTop方法初始化标题栏.
        liwyTop.setConfig(config);
        //左按钮文字,传Null的话不显示;标题文字;右按钮文字,传Null的话不显示
        liwyTop.setLiwyTop("返回","首页","提交");
        // 传入当前类实例对象,设置默认左按钮点击事件:销毁当前页面
        liwyTop.setAppCompatActivity(this);
    }
}
