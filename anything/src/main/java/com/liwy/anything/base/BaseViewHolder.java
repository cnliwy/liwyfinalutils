package com.liwy.anything.base;

import android.util.SparseArray;
import android.view.View;

/**
 * a ViewHolder that you can user it everywhere
 * Created by liwy on 16/7/15.
 */
public class BaseViewHolder {
    // get child view which you want to get from the parent view
    public static <T extends View> T get(View view,int id){
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null){
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null){
            childView = view.findViewById(id);
            viewHolder.put(id,childView);
        }
        return (T)childView;
    }
}
