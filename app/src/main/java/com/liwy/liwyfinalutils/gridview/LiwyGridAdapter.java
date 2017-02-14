package com.liwy.liwyfinalutils.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liwy.anything.base.BaseViewHolder;
import com.liwy.anything.view.gridview.GridItem;
import com.liwy.liwyfinalutils.R;

import java.util.List;

/**
 * Created by liwy on 16/7/15.
 */
public class LiwyGridAdapter extends BaseAdapter{
    private List<GridItem> list;
    private Context context;

    public LiwyGridAdapter(List<GridItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.main_grid_item,parent,false);
        }
        ImageView itemIv = BaseViewHolder.get(convertView,R.id.iv_item);
        TextView itemTv = BaseViewHolder.get(convertView,R.id.tv_item);
        itemIv.setBackgroundResource(list.get(position).getId());
        itemTv.setText(list.get(position).getName());
        return convertView;
    }
}
