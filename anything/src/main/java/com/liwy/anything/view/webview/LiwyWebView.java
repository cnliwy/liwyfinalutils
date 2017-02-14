package com.liwy.anything.view.webview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * Created by liwy on 16/7/10.
 */
public class LiwyWebView extends WebView{
    private static String TAG = "LiwyWebView";
    private SwipeRefreshLayout refreshLayout;//用于下拉刷新webview
    private ProgressBar progressBar;//页面加载进度条
    private LiwyWebChromeClient liwyWebChromeClient;
    private LiwyWebViewClient liwyWebViewClient;



    public LiwyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context){
        // 初始化进度条
        progressBar = new ProgressBar(context,null,android.R.attr.progressBarStyleHorizontal);
        // 设置进度条的风格
        progressBar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 5, 0, 0));
        addView(progressBar);
        setWebChromeClient(new LiwyWebChromeClient());
        setWebViewClient(new LiwyWebViewClient());
        getSettings().setJavaScriptEnabled(true);
        getSettings().setSupportZoom(true);//允许缩放
        getSettings().setUseWideViewPort(true);//任意比例缩放
    }


    //WebChromeClient
    public class LiwyWebChromeClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100){
                progressBar.setVisibility(GONE);
            }else{
                if (progressBar.getVisibility() == GONE){
                    progressBar.setVisibility(VISIBLE);
                }
                progressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }


    }

    //geter and setter
    public SwipeRefreshLayout getRefreshLayout() {
        return refreshLayout;
    }

    public void setRefreshLayout(SwipeRefreshLayout refreshLayout) {
        this.refreshLayout = refreshLayout;
    }

    public LiwyWebChromeClient getLiwyWebChromeClient() {
        return liwyWebChromeClient;
    }

    public void setLiwyWebChromeClient(LiwyWebChromeClient liwyWebChromeClient) {
        this.liwyWebChromeClient = liwyWebChromeClient;
    }

    public LiwyWebViewClient getLiwyWebViewClient() {
        return liwyWebViewClient;
    }

    public void setLiwyWebViewClient(LiwyWebViewClient liwyWebViewClient) {
        this.liwyWebViewClient = liwyWebViewClient;
    }
}
