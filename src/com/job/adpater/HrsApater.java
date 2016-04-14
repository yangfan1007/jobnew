package com.job.adpater;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.job.R;

public class HrsApater extends BaseAdapter {
	private ArrayList<HashMap<Object,Object>> newdata=new ArrayList<HashMap<Object,Object>>();
   // private List<JobBean> jobs ;
    //private User user;
    LayoutInflater mInflater;
    boolean isInTime;
	private LayoutInflater inflater;

    public HrsApater(ArrayList<HashMap<Object,Object>> data,Activity mContext) {
		// TODO Auto-generated constructor stub
    	inflater = mContext.getLayoutInflater();
		
		this.newdata = data;
		
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
	            convertView = inflater.inflate(R.layout.listnews,parent,false);
	           
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
		// TODO Auto-generated method stub

		return newdata.size();

	}
   
       
    

    private static class ViewHolder{
        private TextView name;
        private TextView com;
        private TextView adr;

    }
}
