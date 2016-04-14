package com.job.adpater;

import java.util.HashMap;

import com.example.job.R;
import com.job.bean.JobBean;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NewAapter extends BaseAdapter {

	
	private static final String TAG = "yf";
	View[] itemViews;

	public NewAapter(JobBean[] job) {
		int joblength = 0;
		itemViews = new View[joblength];
		for (int i = 1; i < itemViews.length; i++) {
			
			itemViews[i] = makeItemView(job[i]);
			Log.i(TAG, job.toString());
		}
	}






	public int getCount() {
		return itemViews.length;
	}

	public View getItem(int position) {
		return itemViews[position];
	}

	public long getItemId(int position) {
		return position;
	}

 private View makeItemView(JobBean job) {
		LayoutInflater inflater = (LayoutInflater) getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// 使用View的对象itemView与R.layout.item关联
		View itemView = inflater.inflate(R.layout.listnews, null);

		// 通过findViewById()方法实例R.layout.item内各组件
		TextView title = (TextView) itemView.findViewById(R.id.news_listview_title);
		title.setText(job.getName());
		TextView text = (TextView) itemView.findViewById(R.id.news_listview_time);
		text.setText(job.getProfession());
		TextView source1 = (TextView) itemView.findViewById(R.id.news_listview_source);
		text.setText(job.getCompany());
		
		return itemView;
	}

	private Context getActivity() {
	// TODO Auto-generated method stub
	return null;
}



	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null)
			return itemViews[position];
		return convertView;
	}






	public void add(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		
	}







	}


