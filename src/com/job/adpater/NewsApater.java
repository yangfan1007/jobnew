package com.job.adpater;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


import com.example.job.R;
import com.job.bean.JobBean;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NewsApater extends BaseAdapter{
    private static final String TAG = "yf";
	private ArrayList<HashMap<Object,Object>> newdata=new ArrayList<HashMap<Object,Object>>();
    Activity activity;
   // private List<JobBean> jobs ;
    //private User user;
    LayoutInflater mInflater;
    boolean isInTime;
	public NewsApater(Context context, ArrayList<HashMap<Object,Object>> listViewList) {
		this.mInflater = LayoutInflater.from(context);
		this.newdata = listViewList;
		
	}
    public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public void setSeclection(int position) {
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		 ViewHolder holder = null;
	        if (convertView == null) {

	            holder=new ViewHolder();

	            convertView = mInflater.inflate(R.layout.listnews, null);
	            holder.name=(TextView)convertView.findViewById(R.id.news_listview_title);
	            holder.com=(TextView)convertView.findViewById(R.id.news_listview_time);
	            holder.adr=(TextView)convertView.findViewById(R.id.news_listview_source);
	          
	            convertView.setTag(holder);

	        }else {

	            holder = (ViewHolder)convertView.getTag();
	        }
	        try{
	        	holder.name.setText(newdata.get(position).get("name").toString());
		        holder.com.setText(newdata.get(position).get("com").toString());
		        holder.adr.setText(newdata.get(position).get("adr").toString());
	    		}catch (Exception e){
	    			e.printStackTrace();
	    		}
	    		
	        

	        return convertView;
		
	}



	@Override
	public int getCount() {
	
		return newdata.size();
	
	}
   
       
    

    private static class ViewHolder{
        private TextView name;
        private TextView com;
        private TextView adr;

    }
}
