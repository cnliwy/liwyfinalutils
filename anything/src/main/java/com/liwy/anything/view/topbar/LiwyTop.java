package com.liwy.anything.view.topbar;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 标题栏
 * 备注:需先传入ConfigTop的实例对象.然后再调用setLiwyTop方法初始化标题栏.
 * Created by liwy on 16/7/25.
 */
public class LiwyTop extends LinearLayout {
    private static String TAG = "TAG";
    private Button btnLeft;
    private Button btnRight;
    private TextView tvTitle;
    private AppCompatActivity appCompatActivity;
    private Activity activity;
    private OnLeftClickListener onLeftClickListener;
    private OnRightClickListener onRightClickListener;
    // 标题栏配置类
    private ConfigTop config;

    /**
     * 用于调用类实现的左按钮点击事件
     */
    private interface OnLeftClickListener{
        public void onClick(View v);
    }

    /**
     * 用于调用类实现的右按钮点击事件
     */
    private interface OnRightClickListener{
        public void onClick(View v);
    }

    //左侧按钮点击事件
    private OnClickListener LeftButtonOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onLeftClickListener != null){
                onLeftClickListener.onClick(btnLeft);
            }else{
                //销毁当前页面
                if (activity != null) activity.finish();
                if (appCompatActivity != null) appCompatActivity.finish();
            }
        }
    };
    // 右侧按钮点击事件
    private OnClickListener RightButtonOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onRightClickListener != null)
                onRightClickListener.onClick(btnRight);
        }
    };

    public LiwyTop(Context context) {
        this(context,null);
    }

    public LiwyTop(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LiwyTop(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        config = new ConfigTop();
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setHorizontalScrollBarEnabled(false);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 配置Linearlayout参数
        if (config.getBackgroundColor() != 0){
            setBackgroundColor(config.getBackgroundColor());
        }
    }


    /**
     * 改方法尚待完善,勿用
     * @param btnLeft
     * @param tvTitle
     * @param btnRight
     */
    public void  setTop(Button btnLeft,TextView tvTitle,Button btnRight){
        //设置控件
        if (btnLeft != null){
            this.btnLeft = btnLeft;

        }else{
            Button btnLeftTemp = new Button(getContext());
            this.btnLeft = btnLeftTemp;
        }
        if (tvTitle != null){
            this.tvTitle = tvTitle;

        }else{
            TextView tvTitleTemp = new Button(getContext());
            this.tvTitle = tvTitleTemp;
        }
        if (btnRight != null){
            this.btnRight = btnRight;

        }else{
            Button btnRightTemp = new Button(getContext());
            this.btnRight = btnRightTemp;
        }

        addView(this.btnLeft,new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1));
        addView(this.tvTitle,new LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT,1));
        addView(this.btnRight,new LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT,1));
    }

    /**
     * 初始化标题栏内容
     * @param strLeft 左按钮文字,传Null的话不显示
     * @param strTitle  标题文字
     * @param strRight  右按钮文字,传Null的话不显示
     */
    public void setLiwyTop(String strLeft,String strTitle,String strRight){
        //实例化控件
        if (this.btnLeft == null) this.btnLeft = new Button(getContext());
        if (this.tvTitle == null)this.tvTitle = new Button(getContext());
        if (this.btnRight == null) this.btnRight = new Button(getContext());

        //设置控件
        if (strLeft != null){
            btnLeft = processLeftButton(btnLeft,strLeft);
        }

        if (strTitle != null){
           tvTitle = proceeTitleView(tvTitle,strTitle);
        }

        if (strRight != null){
            btnRight = processRightButton(btnRight,strRight);
        }
        //添加至组件
        addView(this.btnLeft,new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1));
        addView(this.tvTitle,new LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT,2));
        addView(this.btnRight,new LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT,1));
    }

    /**
    * 加工左侧按钮
    * @author liwy
    * @created at 16/7/28 下午5:23
    */
    private Button processLeftButton(Button left,String strLeft){
        left.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
        left.setText(strLeft);
        Drawable d;
        if (config.getLeftButtonImg() != 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                d = getResources().getDrawable(config.getLeftButtonImg(), null);
            } else {
                d = getResources().getDrawable(config.getLeftButtonImg());
            }
            left.setCompoundDrawablesWithIntrinsicBounds(d,null,null,null);//设置左侧按钮
        }
        left.setTextColor(config.getTitleTextColor());
        left.setPadding(20,0,0,0);//按钮内容向左移动20
        left.setTextSize(config.getButtonTitleSize());
        left.setBackgroundColor(ConfigTop.transprent);
        left.setOnClickListener(LeftButtonOnClickListener);
        return left;
    }

    /**
     * 加工标题
     * @param title
     * @param strTitle
     * @return
     */
    private TextView proceeTitleView(TextView title,String strTitle){
        title.setText(strTitle);
        title.setTextColor(config.getTitleTextColor());
        title.setTextSize(config.getTitleSize());
        title.setBackgroundColor(ConfigTop.transprent);
        return title;
    }

    /**
    * 加工右侧按钮
    * @author liwy
    * @created at 16/7/28 下午5:23
    */
    private Button processRightButton(Button right,String strRight){
        right.setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);
        right.setText(strRight);
        Drawable d;
        if (config.getRightButtonImg() != 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                d = getResources().getDrawable(config.getRightButtonImg(),null);
            } else {
                d = getResources().getDrawable(config.getRightButtonImg());
            }
            right.setCompoundDrawablesWithIntrinsicBounds(null,null,d,null);//设置左侧按钮
        }
        right.setPadding(0,0,20,0);//按钮内容向左移动20
        right.setTextColor(config.getTitleTextColor());
        right.setTextSize(config.getButtonTitleSize());
        right.setBackgroundColor(ConfigTop.transprent);
        right.setOnClickListener(RightButtonOnClickListener);
        return right;
    }


    public Button getBtnLeft() {
        return btnLeft;
    }

    public void setBtnLeft(Button btnLeft) {
        this.btnLeft = btnLeft;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    public Button getBtnRight() {
        return btnRight;
    }

    public void setBtnRight(Button btnRight) {
        this.btnRight = btnRight;
    }

    public ConfigTop getConfig() {
        return config;
    }

    public void setConfig(ConfigTop config) {
        this.config = config;
        requestLayout();
    }

    public AppCompatActivity getAppCompatActivity() {
        return appCompatActivity;
    }

    public void setAppCompatActivity(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public OnLeftClickListener getOnLeftClickListener() {
        return onLeftClickListener;
    }

    public void setOnLeftClickListener(OnLeftClickListener onLeftClickListener) {
        this.onLeftClickListener = onLeftClickListener;
    }

    public OnRightClickListener getOnRightClickListener() {
        return onRightClickListener;
    }

    public void setOnRightClickListener(OnRightClickListener onRightClickListener) {
        this.onRightClickListener = onRightClickListener;
    }
}
