package com.example.liplop.myapplication;

import android.widget.TextView;

import com.example.liplop.myapplication.base.BaseRecycleViewAdapter;
import com.example.liplop.myapplication.base.BaseRecycleViewHolder;

import java.util.List;

/**
 * Created by hzj on 2018/5/15.
 */
public class TextAdapter extends BaseRecycleViewAdapter<String> {
    public TextAdapter(List<String> mData) {
        super(mData);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_text;
    }

    @Override
    public void convert(BaseRecycleViewHolder holder, String s, int position) {
        TextView textView = holder.getView(R.id.tv_item);
        textView.setText(s);
    }
}
