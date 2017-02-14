package com.liwy.liwyfinalutils;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.liwy.anything.view.gridview.GridItem;
import com.liwy.liwyfinalutils.cache.CacheTestActivity;
import com.liwy.liwyfinalutils.gridview.LiwyGridAdapter;
import com.liwy.liwyfinalutils.indicator.TabActivity;
import com.liwy.liwyfinalutils.popupwindow.PopupWindowActivity;
import com.liwy.liwyfinalutils.webview.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private List<GridItem> list;
    private LiwyGridAdapter adapter;
    private GridView gridView;

    public String[] img_text = {"webview", "导航栏", "标题栏", "缓存", "popupwindow", "彩票",
            "当面付", "亲密付", "机票",};
    public int[] imgs = {R.mipmap.app_transfer, R.mipmap.app_fund,
            R.mipmap.app_phonecharge, R.mipmap.app_creditcard,
            R.mipmap.app_movie, R.mipmap.app_lottery,
            R.mipmap.app_facepay, R.mipmap.app_close, R.mipmap.app_plane};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"点击了那啥",Toast.LENGTH_SHORT).show();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
        initData();
        initView();
    }

    private void initData(){
        list = new ArrayList<GridItem>();
        for (int i = 0; i < 9; i++){
            GridItem g = new GridItem(img_text[i],imgs[i]);
            list.add(g);
        }
    }
    private void initView(){
        gridView = (GridView)findViewById(R.id.gridView);
        adapter = new LiwyGridAdapter(list,this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        switch (position){
            case 0:
                intent = new Intent(MainActivity.this,WebViewActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(MainActivity.this,TabActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(MainActivity.this,TopActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(MainActivity.this,CacheTestActivity.class);
                startActivity(intent);
                break;
            case 4:
                intent = new Intent(MainActivity.this,PopupWindowActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(this,list.get(position).getName(),Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
