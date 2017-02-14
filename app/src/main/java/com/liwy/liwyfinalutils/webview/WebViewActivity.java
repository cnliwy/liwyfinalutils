package com.liwy.liwyfinalutils.webview;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;

import com.liwy.anything.view.webview.LiwyWebView;
import com.liwy.anything.view.webview.LiwyWebViewClient;
import com.liwy.liwyfinalutils.R;

public class WebViewActivity extends AppCompatActivity {
    private static String TAG = "66666";
    private LiwyWebView webView;
    private SwipeRefreshLayout refreshLayout;
    public static String WEB_URL = "http://www.baidu.com";
    public static String WEB_URL2 = "http://www.sina.com.cn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initView();
    }
    private void initView(){
        initWebView();
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e(TAG, "onRefresh: 重新加载中....");
                webView.loadUrl(WEB_URL2);
            }
        });


    }

    private void initWebView(){
        webView = (LiwyWebView)findViewById(R.id.wv_test);
        webView.setRefreshLayout(refreshLayout);//传入下拉刷新组件
        webView.loadUrl(WEB_URL);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);//允许缩放
        webView.getSettings().setUseWideViewPort(true);//任意比例缩放

        webView.setWebViewClient(new LiwyWebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                refreshLayout.setRefreshing(false);
            }
        });


//        mWebSettings.setSupportZoom(true);          //允许缩放
//        mWebSettings.setBuiltInZoomControls(true);  //原网页基础上缩放
//        mWebSettings.setUseWideViewPort(true);      //任意比例缩放
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode==KeyEvent.KEYCODE_BACK)&&webView.canGoBack()) {
            //如果可以回退
            webView.goBack() ;
            return true ;
        }
        return super.onKeyDown(keyCode,event) ;
    }
}
