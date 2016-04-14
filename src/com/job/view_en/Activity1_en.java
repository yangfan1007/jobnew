package com.job.view_en;


import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import com.example.job.R;
import com.job.Asy.ConnectAsy;
import com.job.adpater.NewsApater;
import com.job.app.NowUser;
import com.job.bean.*;
import com.job.common.ApiClient;
import com.job.util.Url;



import android.app.Activity;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract.Contacts.Data;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public  class Activity1_en extends Fragment {
 
	  private static Activity activity;
		public static int joblength;
		ListView listView;
		private int nt=1;
		String TAG="yf";

	    public static Activity getThisActivity() {
	        return activity;
	    }
	    public void onAttach(Activity activity) {

	        this.activity = activity;
	        super.onAttach(activity);
	    	}
				ArrayList<HashMap<Object, Object>> data = new ArrayList<HashMap<Object, Object>> ();
				protected List<JobBean> newsList;
				protected NewsApater listViewAdapter;
				protected NewsList list;
				protected NewsList listp;
				protected Intent intent;
				protected int num=0;
				protected boolean A1flag=false;
			
			  
				 public View onCreateView(LayoutInflater inflater, ViewGroup container,
						Bundle savedInstanceState) {
					super.onCreate(savedInstanceState);
					
					final View news = inflater.inflate(R.layout.news, container, false);
					  AsyncTask<Void,Void,List<JobBean>> ATgetTask = new AsyncTask<Void, Void, List<JobBean>>() {
				            @Override
				            protected List<JobBean> doInBackground(Void... voids) {

				                try{
				       //         	UserBean userNull = new UserBean();
				     
									NowUser user = null;
									list = ApiClient.getNewsList(Url.Url_Search_News, true,user);
							        newsList=list.getNewslist();
							        

				                } catch (NullPointerException e){}
				                
								return newsList;
				            }
				            protected void onPostExecute(List<JobBean> newsList){
				            	geneItems();
				            	init();
							
				            }
				        };
				        ATgetTask.execute();
				        Log.d("123", "finsihshowtask");
						return news;
				 }
				 
/*				 public void onActivityCreated(Bundle savedInstanceState) {
				 super.onActivityCreated(savedInstanceState);
				
			    }*/
				 public void OnClickListener(){
					 
				 }
			
				 public void init(){
					 listView=(ListView) getView().findViewById(R.id.fragment_news_listview);
					 
					 listViewAdapter = new NewsApater(activity,data);
				     listView.setAdapter(listViewAdapter);
				     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				            @Override
				            public void onItemClick(AdapterView<?> parent, View view,
				                                    int position, long id) {
				            	JobBean job =list.getNewslist().get(position);
				           /* 	num=0;
				            	while(num!=listp.getNewsCount()){
				            		if(list.getNewslist().get(position)==listp.getNewslist().get(num))
				            			A1flag=true;
				            		num++;
				            	}*/
				  /*          	System.out.println(String.valueOf(A1flag));
				            	if(A1flag){
				            		 intent = new Intent(activity, showwork_style.class);
				            	}else{*/
				            		intent = new Intent(activity, showwork_en.class);
				            /*	}*/
				               
				                Bundle jobshowBundle = new Bundle();
				                jobshowBundle.putSerializable("JobBean", job);
				              /*  intent.putExtra("name", job.getName());
				                intent.putExtra("id", job.getId());
				                intent.putExtra("company", job.getCompany());
				                intent.putExtra("Profession", job.getProfession());
				                intent.putExtra("Require", job.getRequire());
				                intent.putExtra("TP", job.getTp());
				                intent.putExtra("Address", job.getAddress());*/
				                intent.putExtras(jobshowBundle);
				                Log.i(TAG,intent.toString());
				                startActivity(intent);
				                }
				     });
				  }
				 private void geneItems(){
					 int newscount = 0;
					 if(data!=null ) data=new ArrayList<HashMap<Object, Object>>();
					 while(newscount!=list.getNewsCount()){
						 
				            HashMap<Object,Object> itemData=new HashMap<Object, Object>();
				            itemData.put("name",list.getNewslist().get(newscount).getName());
				            itemData.put("com",list.getNewslist().get(newscount).getCompany());
				            itemData.put("adr", list.getNewslist().get(newscount).getAddress());
				            Log.i(TAG, list.getNewslist().get(newscount).toString());
				           Log.i(TAG, itemData.toString());
				            newscount++;
				            data.add(itemData);
				      }
					 Log.i(TAG,data.toString());

	
				 }
			}

	

