package com.job.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.job.R;
import com.job.adpater.NewsApater;
import com.job.app.NowUser;
import com.job.bean.JobBean;
import com.job.bean.NewsList;
import com.job.common.ApiClient;
import com.job.util.Url;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

public class collection extends Activity {
	protected static final String TAG = "yf";
	ArrayList<HashMap<Object, Object>> data = new ArrayList<HashMap<Object, Object>> ();
	protected List<JobBean> newsList;
	protected NewsApater listViewAdapter;
	protected NewsList list;
	protected NewsList listp;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.collection);
		  AsyncTask<Void,Void,List<JobBean>> ATgetTask = new AsyncTask<Void, Void, List<JobBean>>() {
	            @Override
	            protected List<JobBean> doInBackground(Void... voids) {

	                try{
	       //         	UserBean userNull = new UserBean();
	     
	                	NowUser user = (NowUser) getApplication(); 
						list = ApiClient.getNewsList(Url.Url_scollection, false,user);
				        newsList=list.getNewslist();
				        

	                } catch (NullPointerException e){}
	                
					return newsList;
	            }
	            protected void onPostExecute(List<JobBean> newsList){
	            	geneItems();
	            	init();
				
	            }
				private void init() {
					ListView listView = (ListView) findViewById(R.id.fragment_news_listview);
					 
					 listViewAdapter = new NewsApater( collection.this,data);
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
				            		Intent intent = new Intent(collection.this, showwork.class);
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
				                Log.i("yf",intent.toString());
				                startActivity(intent);
				                }
				     });
					
				}
				private void geneItems() {
					// TODO Auto-generated method stub
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
	        };
	        ATgetTask.execute();
		setListener();
		}
	private void setListener() {
		 
        View mBack=(ImageButton)findViewById(R.id.collection_retuen);
		mBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                    finish();
            }
        });
 }
}
