package com.example.liplop.myapplication.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * recycleviewadapter基类
 * Created by hzj on 2017/5/10.
 */

public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<BaseRecycleViewHolder> {
    protected ArrayList<T> mData;

    public BaseRecycleViewAdapter(List<T> mData) {
        this.mData = new ArrayList<>(mData);
    }

    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(getItemLayoutId(),parent,false);
        BaseRecycleViewHolder viewHolder = new BaseRecycleViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseRecycleViewHolder holder, int position) {
        convert(holder,mData.get(position),position);
    }

    @Override
    public int getItemCount() {
        return null == mData?0:mData.size();
    }

    protected abstract int getItemLayoutId();

    /**
     * 留给调用者去实现
     *
     * @param holder
     * @param t
     */
    public abstract void convert(BaseRecycleViewHolder holder, T t, int position) ;

    public void setmData(List<T> data) {
        this.mData = new ArrayList<>(data);
        notifyDataSetChanged();
    }

    public List<T> getmData() {
        return mData;
    }

    public void addData(List<T> data){
        mData.addAll(data);
    }

}