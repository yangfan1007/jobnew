package com.job.view_en;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;

import com.example.job.R;
import com.job.adpater.HrsApater;
import com.job.adpater.NewsApater;
import com.job.app.NowUser;
import com.job.bean.HrBean;
import com.job.bean.HrsList;
import com.job.bean.JobBean;
import com.job.bean.NewsList;
import com.job.common.ApiClient;
import com.job.util.Url;
import com.job.view.showhr;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class resumemanagement_en extends Activity{

	public static ArrayList<JobBean> mJobBean;
	ListView listView;
	public List<JobBean> hrsList;
	private Activity mContext;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = resumemanagement_en.this;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.newsmanagement);
		  listView=(ListView)this.findViewById(R.id.fragment_resume_listview);
		   AsyncTask<Void,Void,List<JobBean>> ATgetTask = new AsyncTask<Void, Void, List<JobBean>>() {
	            private NewsApater listViewAdapter;
				private String TAG="resumemanagement_en";
				private Activity activity=resumemanagement_en.this;
				private NewsList list=new NewsList();
				private NewsList hrslist= new NewsList();
				private ArrayList<HashMap<Object, Object>> jdata = new ArrayList<HashMap<Object,Object>>();
				
				@Override
	            protected List<JobBean> doInBackground(Void... voids) {

	                try{
	       //         	UserBean userNull = new UserBean();
	       
	                	
						
						NowUser user = (NowUser) getApplication();
						 list=new NewsList();
						list = ApiClient.getNewsList_en(Url.Url_Search_Job, user);
						
						
				        hrsList=list.getNewslist();
				        System.out.println("list"+ApiClient.getNewsList_en(Url.Url_Search_Job, user).getCompany());
	                } catch (NullPointerException e){} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                
					return hrsList;
	            }
	            protected void onPostExecute(List<JobBean> jobsList){
					int newscount = 0;
					//if(jdata!=null ) jdata=new ArrayList<HashMap<Object, Object>>();
					try{
			        while(newscount!=list.getNewsCount()){
			            HashMap<Object,Object> itemData=new HashMap<Object, Object>();
			            itemData.put("name",list.getNewslist().get(newscount).getName());
			            itemData.put("com",list.getNewslist().get(newscount).getProfession());
			            itemData.put("adr",list.getNewslist().get(newscount).getCompany());
			           
			           Log.i(TAG, itemData.toString());
			            newscount++;
			            jdata.add(itemData);
			            Log.d("123",jdata.toString());
			          // System.out.println("123"+rdata.size() );
			        }
			        
			        }catch (NullPointerException e){
			        	e.toString();
			        }
			        
	
					 listViewAdapter = new NewsApater(mContext,jdata);
				     listView.setAdapter(listViewAdapter);
				     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				            

							@Override
				            public void onItemClick(AdapterView<?> parent, View view,
				                                    int position, long id) {
				                Intent intent = new Intent(resumemanagement_en.this,showwork_en.class);
				                JobBean job =list.getNewslist().get(position);
				                Bundle jobshowBundle = new Bundle();
				                jobshowBundle.putSerializable("JobBean", job);
/*							                intent.putExtra("name", job.getName());
				                intent.putExtra("id", job.getId());
				                intent.putExtra("company", job.getCompany());
				                intent.putExtra("Profession", job.getProfession());
				                intent.putExtra("Require", job.getRequire());
				                intent.putExtra("TP", job.getTp());
				                intent.putExtra("Address", job.getAddress());*/
				                intent.putExtras(jobshowBundle);
				               
				                startActivity(intent);
				                
				                init();
				                setListener();
				            }
				        });
					
	            }
	        };
	        ATgetTask.execute();
	        init();
	        setListener();
	        Log.d("123", "finsihshowtask");

	 }

 
              private void init() {
            	  if(false){
    
        	  	new AlertDialog.Builder(resumemanagement_en.this)  
	      		   
                   .setTitle("通知")
   
                   .setMessage("请输入你相关信息")
   
                   .setPositiveButton("确定", null)
   
                   .show();
            	  }
          	    }
	         



	private void setListener() {
		 
        View mBack=(ImageButton)findViewById(R.id.resumemanagement_return);
        View mand=(ImageButton)findViewById(R.id.and_resume);
		mBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                    finish();
            }
        });
		mand.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
            	Intent i1 = new Intent(resumemanagement_en.this,newhr_en.class);

		          ////启动 
		          startActivity(i1);
            }
        });
 }
}