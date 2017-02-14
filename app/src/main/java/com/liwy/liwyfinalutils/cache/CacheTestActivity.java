package com.liwy.liwyfinalutils.cache;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.liwy.anything.liwycache.LiwyImageCache;
import com.liwy.anything.liwycache.OnFirstLoadResultListener;
import com.liwy.liwyfinalutils.R;

public class CacheTestActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView imageView;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private LiwyImageCache imageCache;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache_test);
        initView();
    }

    private void initView(){
        imageCache = new LiwyImageCache();
        imageView = (ImageView)findViewById(R.id.iv_cache_content);
        btn1 = (Button)findViewById(R.id.btn_1);
        btn2 = (Button)findViewById(R.id.btn_2);
        btn3 = (Button)findViewById(R.id.btn_3);
        btn4 = (Button)findViewById(R.id.btn_4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                imageCache.displayImage("http://www.baidu.com/img/baidu_logo.gif",imageView);
                break;
            case R.id.btn_2:
                imageCache.displayImage("http://cache.soso.com/30d/img/web/logo.gif",imageView);
                break;
            case R.id.btn_3:
                imageCache.displayImage("http://images.cnblogs.com/logo_small.gif",imageView);
                break;
            case R.id.btn_4:
                imageCache.displayImage("http://csdnimg.cn/www/images/csdnindex_logo.gif",imageView);
                break;
            default:
                break;
        }
    }
}
