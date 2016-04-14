package com.job.adpater;

import android.content.Context;
import android.view.View;

import com.example.job.R;
import com.jobs.bean.JobBean;

import java.util.List;



public class SearchAdapter extends CommonAdapter<JobBean>{

    public SearchAdapter(Context context, List<JobBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, int position) {
        holder.setImageResource(R.id.item_search_iv_icon,mData.get(position).getId())
                .setText(R.id.item_search_tv_title,mData.get(position).getName())
                .setText(R.id.item_search_tv_content,mData.get(position).getCompany())
                .setText(R.id.item_search_tv_comments,mData.get(position).getRequire());
    }
}
