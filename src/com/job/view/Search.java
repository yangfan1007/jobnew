package com.job.view;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.example.job.R;
import com.job.adpater.NewsApater;
import com.job.app.NowUser;
import com.job.bean.JobBean;
import com.job.bean.NewsList;
import com.job.common.ApiClient;
import com.job.util.Url;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.Toast;

public class Search extends Activity {
	private static final String TAG = null;
	protected static final EditText eSearch = null;
	private ImageView ivDeleteText;
	private EditText etSearch;
	protected List<JobBean> newsList;
	protected NewsApater listViewAdapter;
	protected NewsList list;
	protected NewsList listp;
	protected Intent intent;
	ArrayList<HashMap<Object, Object>> data = new ArrayList<HashMap<Object, Object>> ();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        ivDeleteText = (ImageView) findViewById(R.id.ivDeleteText);
        etSearch = (EditText) findViewById(R.id.etSearch);
		  AsyncTask<Void,Void,List<JobBean>> ATgetTask = new AsyncTask<Void, Void, List<JobBean>>() {
	            @Override
	            protected List<JobBean> doInBackground(Void... voids) {

	                try{
	       //         	UserBean userNull = new UserBean();

						list = ApiClient.getAllList();
				        newsList=list.getNewslist();
				        

	                } catch (NullPointerException e){}
	                
					return newsList;
	            }
	            protected void onPostExecute(List<JobBean> newsList){
	            	init();
	            	 ivDeleteText.setOnClickListener(new OnClickListener() {
	         			
	         			public void onClick(View v) {
	         				etSearch.setText("");
	         			}
	         		});
	            	  etSearch.addTextChangedListener(new TextWatcher() {
	          			
	          			public void onTextChanged(CharSequence s, int start, int before, int count) {
	          				// TODO Auto-generated method stub
	          				
	          			}
	          			
	          			public void beforeTextChanged(CharSequence s, int start, int count,
	          					int after) {
	          				// TODO Auto-generated method stub
	          				
	          			}
	          			
	          			public void afterTextChanged(Editable s) {
	          				new Handler().post(eChanged);
	          				if (s.length() == 0) {
	          					ivDeleteText.setVisibility(View.GONE);
	          				} else {
	          					ivDeleteText.setVisibility(View.VISIBLE);
	          				}
	          			}
	          		});
	            	 
	            }
	        };
	        ATgetTask.execute();

        
      
        
      
    }
    Runnable eChanged = new Runnable() {
        
        @Override
        public void run() {

			// TODO Auto-generated method stub

                 
       
              data.clear();
                 
              getmDataSub(data,etSearch.getText().toString() );
                 
              listViewAdapter.notifyDataSetChanged();
                 
        }
    };
    private void getmDataSub( ArrayList<HashMap<Object,Object>> data2, String mData)
    {
         int length = newsList.size();
         for(int i = 0; i < length; ++i){
               if(newsList.get(i).getName().contains(mData) ||newsList.get(i).getName().contains(mData)){
                   HashMap<Object,Object> itemData=new HashMap<Object, Object>();
   	            itemData.put("name",list.getNewslist().get(i).getName());
   	            itemData.put("com",list.getNewslist().get(i).getCompany());
   	            itemData.put("adr", list.getNewslist().get(i).getAddress());
   	            data.add(itemData);
                }
         }
    }
	 public void init(){
		 ListView listView = (ListView) findViewById(R.id.fragment_news_listview);
		 
		 listViewAdapter = new NewsApater(this,data);
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
	            		intent = new Intent(Search.this, showwork.class);
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




