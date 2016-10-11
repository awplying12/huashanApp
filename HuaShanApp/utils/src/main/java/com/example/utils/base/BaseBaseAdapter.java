package com.example.utils.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/5.
 */
public abstract class BaseBaseAdapter<DataType> extends BaseAdapter {

    private Context context;
    private ArrayList<DataType> list = new ArrayList<>();
    private LayoutInflater inflater;

    public BaseBaseAdapter(Context context, ArrayList<DataType> list) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getViewBase(position, convertView, parent);
    }

    public abstract View getViewBase(int position, View convertView, ViewGroup parent);

    public Context getContext() {
        return this.context;
    }

    public ArrayList<DataType> getList() {
        return this.list;
    }

    public void setList(ArrayList<DataType> list) {
        this.list = list;

    }
}
