package com.liwy.anything.view.tabindicator;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * 自定义导航栏.可单独使用.也可结合ViewPager+fragment使用.
 * 单独使用时可重写点击事件mOnTabClickListener
 * 结合fragment时可重写页面切换事件mListener和点击事件mOnTabClickListener
 * Created by liwy on 16/7/19.
 */
public class LiwyIndicator extends LinearLayout implements ITabIndicator{
    private List<TabBean> tabsList;
    private ViewPager viewPager;
    private ViewPager.OnPageChangeListener mPageListener;
    private LinearLayout mTabLayout;
    private int tabWidth;
    private int currentIndex;
    private OnTabClickListener mOnTabClickListener;
    // 默认点击事件
    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            TabView tabView = (TabView)v;
            int newIndex = tabView.getIndex();
            if (currentIndex != newIndex){
                setCurrentItem(newIndex);
            }
            if (mOnTabClickListener != null)
                mOnTabClickListener.onClick(v);
        }
    };


    public LiwyIndicator(Context context) {
        this(context,null);
    }

    public LiwyIndicator(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LiwyIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setHorizontalScrollBarEnabled(false);
        mTabLayout = new LinearLayout(context,null);
        mTabLayout.setOrientation(LinearLayout.HORIZONTAL);
//        mTabLayout.setGravity(Gravity.CENTER_VERTICAL);
        addView(mTabLayout, new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int childCount = mTabLayout.getChildCount();
        if (childCount > 0 && (mode == MeasureSpec.EXACTLY||mode == MeasureSpec.AT_MOST)){
            tabWidth = getMeasuredWidth()/tabsList.size();
        }else{
            tabWidth = -1;
        }
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = dip2px(getContext(),TabConfig.parentHeight);
        Log.e("TAG", "onMeasure: 底部栏的高度=" + heightSize );
        int newHeightMeasureSpect =  MeasureSpec.makeMeasureSpec(heightSize,heightMode);
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpect);
    }

    @Override
    public void setTabAndViewPager(List<TabBean> list, ViewPager view) {
        if (view == null)
            throw new IllegalStateException("ViewPager is null,however it is necessary");
        viewPager = view;
        viewPager.addOnPageChangeListener(this);
        setTabs(list);
    }

    @Override
    public void setTabs(List<TabBean> list) {
        if (list == null)
            throw new IllegalStateException("Tab's list is null,however it is necessary");
        tabsList = list;
        notifyDataSetChanged();
    }

    @Override
    public void setCurrentItem(int item) {
        if (viewPager != null)viewPager.setCurrentItem(item,false);
        currentIndex = item;
        final int tabCount = mTabLayout.getChildCount();
        for (int i = 0; i < tabCount; i++) {
            final TabView child = (TabView)mTabLayout.getChildAt(i);
            final boolean isSelected = (i == item);
            child.setSelected(isSelected);
            if (isSelected) {
                //设置选中后的背景色
                if (TabConfig.selectedTabColor != 0) {
                    child.setBackgroundColor(getResources().getColor(TabConfig.selectedTabColor));
                }
                //设置选中后的背景图片
                if (tabsList.get(i).getResIconSelected() != 0){
                    child.setIcon(tabsList.get(i).getResIconSelected());
                }
                if (TabConfig.selectedTextColor != 0)
                    child.setTextColor(getResources().getColor(TabConfig.selectedTextColor));

            }else{
                //设置默认背景色
                if (TabConfig.defTabColor != 0)
                    child.setBackgroundColor(getResources().getColor(TabConfig.defTabColor));
                if (tabsList.get(i).getResIconNormal() != 0)
                    child.setIcon(tabsList.get(i).getResIconNormal());
                if (TabConfig.defTextColor != 0)
                    child.setTextColor(getResources().getColor(TabConfig.defTextColor));
            }
        }
    }

    @Override
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mPageListener = listener;
    }

    @Override
    public void notifyDataSetChanged() {
        if (tabsList == null || tabsList.size() < 1)return;
        int childCount = tabsList.size();
        for (int i = 0; i < childCount; i++){
            TabBean bean = tabsList.get(i);
            addTab(i,bean.getName(),bean.getResIconNormal());
        }
        setCurrentItem(0);
        requestLayout();
    }

    private void addTab(int index,CharSequence text, int resId){
        final TabView tabView = new TabView(getContext());
        tabView.setText(text);
        tabView.setIndex(index);
        tabView.setGravity(Gravity.CENTER);
        tabView.setTextSize(TabConfig.textSize);
        tabView.setPadding(0,10,0,2);
        tabView.setCompoundDrawablePadding(2);
        tabView.setOnClickListener(mOnClickListener);
        if (resId != 0){
            tabView.setIcon(resId);
        }
        if (TabConfig.defTextColor != 0)
            tabView.setTextColor(getResources().getColor(TabConfig.defTextColor));
        mTabLayout.addView(tabView, new LayoutParams(0, MATCH_PARENT, 1));
    }



    /**
     * OnPageChangeListener的实现方法,onPageScrolled,onPageSelected,onPageScrollStateChanged
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mPageListener != null){
            mPageListener.onPageScrolled(position,positionOffset,positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        setCurrentItem(position);
        if (mPageListener != null){
            mPageListener.onPageSelected(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mPageListener != null){
            mPageListener.onPageScrollStateChanged(state);
        }
    }


    public void setmOnTabClickListener(OnTabClickListener mOnTabClickListener) {
        this.mOnTabClickListener = mOnTabClickListener;
    }

    public void setmOnClickListener(OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }


    public class TabView extends TextView{
        private int index;
        private int iconWidth =  dip2px(getContext(), TabConfig.tabWidth);
        private int iconHeight = dip2px(getContext(), TabConfig.tabHeight);
        public TabView(Context context) {
            this(context,null,0);
        }

        public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            if (index > 0){
                super.onMeasure(MeasureSpec.makeMeasureSpec(tabWidth,MeasureSpec.EXACTLY),heightMeasureSpec);
            }
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public void setIcon(int resId){
            if (resId != 0){
                Drawable drawable = getResources().getDrawable(resId);
                Log.e("TAG", "TabView: w="+ iconWidth + ",h=" + iconHeight);
                drawable.setBounds(0,0,iconWidth,iconHeight);
                setCompoundDrawables(null,drawable,null,null);
            }
        }
    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
