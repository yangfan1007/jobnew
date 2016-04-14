package com.job.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.job.R;
import com.job.adpater.HrsApater;
import com.job.app.NowUser;
import com.job.bean.HrBean;
import com.job.bean.HrsList;
import com.job.bean.JobBean;
import com.job.common.ApiClient;
import com.job.util.Url;

public class ChooseHr extends Activity {
	private ListView listView;
	Thread thread;
	private String TAG="choosehr";
	public List<HrBean> hrsList;
	private int j_id=0;
	private int r_id=0;
	private NowUser user;
	private JobBean Jobnow;
	private Activity mContext=ChooseHr.this;
	 public static  Activity instance;
	 public static  int CHflag=0;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.resumemanagement);
		instance = this;
		Intent intent = getIntent();
		   Jobnow= (JobBean)intent.getSerializableExtra("JobBean");
		   Log.i(TAG,"Jobhow"+Jobnow.toString());
		   j_id=Jobnow.getId();
		  listView=(ListView)this.findViewById(R.id.fragment_resume_listview);
		   AsyncTask<Void,Void,List<HrBean>> ATgetTask = new AsyncTask<Void, Void, List<HrBean>>() {
	            private HrsApater listViewAdapter;
				private String TAG="resumemanagement";
				private HrsList list=new HrsList();
				private HrsList hrslist= new HrsList();
				private ArrayList<HashMap<Object, Object>> rdata = new ArrayList<HashMap<Object,Object>>();
				
				
				@Override
	            protected List<HrBean> doInBackground(Void... voids) {
					
	                
					try{
	       //         	UserBean userNull = new UserBean();
	       
	                	
						
						user = (NowUser) getApplication();
						 list=new HrsList();
						list = ApiClient.getHrsList(Url.Url_Search_Hr, false,user);
						
						
				        hrsList=list.getHrslist();
				        System.out.println("list"+ApiClient.getHrsList(Url.Url_Search_Hr, false,user).getCount());
	                } catch (NullPointerException e){}
	                
					return hrsList;
	            }
	            protected void onPostExecute(List<HrBean> hrsList){
					int newscount = 0;
					if(rdata!=null ) rdata=new ArrayList<HashMap<Object, Object>>();
					try{
			        while(newscount!=list.getCount()){
			            HashMap<Object,Object> itemData=new HashMap<Object, Object>();
			            itemData.put("name",list.getHrslist().get(newscount).getName());
			            itemData.put("com",list.getHrslist().get(newscount).getProfession());
			            itemData.put("adr",list.getHrslist().get(newscount).getSchool());
			            r_id=list.getHrslist().get(newscount).getId();
			           Log.i(TAG, itemData.toString());
			            newscount++;
			            rdata.add(itemData);
			            Log.d("123",rdata.toString());
			          // System.out.println("123"+rdata.size() );
			        }
			        
			        }catch (NullPointerException e){
			        	e.toString();
			        }
			        
	
					 listViewAdapter = new HrsApater(rdata,mContext);
				     listView.setAdapter(listViewAdapter);
				     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				            @Override
				            public void onItemClick(AdapterView<?> parent, View view,
				                                    int position, long id) {
				                
				                HrBean hr =list.getHrslist().get(position);

                                thread = new Thread(networkPersonalTask);
                                thread.start();
/*				                Bundle jobshowBundle = new Bundle();
				                jobshowBundle.putSerializable("HrBean", hr);
							                intent.putExtra("name", job.getName());
				                intent.putExtra("id", job.getId());
				                intent.putExtra("company", job.getCompany());
				                intent.putExtra("Profession", job.getProfession());
				                intent.putExtra("Require", job.getRequire());
				                intent.putExtra("TP", job.getTp());
				                intent.putExtra("Address", job.getAddress());
				                intent.putExtras(jobshowBundle);
				               
				                startActivity(intent);*/
				            }
				        });
					
	            }
	        };
	        ATgetTask.execute();
	        init();
	        setListener();
	        Log.d("123", "finsihshowtask");

	 }
	   Handler mHandler1 = new Handler() {
	        @Override
	        public void handleMessage(Message msg) {
	            super.handleMessage(msg);
	            Bundle bundledata = msg.getData();
  		   	new AlertDialog.Builder(ChooseHr.this)  
  		   
  		                   .setTitle("通知")
  		   
  		                   .setMessage("已投递，您可以在已投递中查找")
  		   
  		                   .setPositiveButton("确定", new 
  	   		                		DialogInterface.OnClickListener(){

  										@Override
  										public void onClick(DialogInterface dialog,  
  			                                    int which) {
  							                Intent intent = new Intent(ChooseHr.this, showwork_style.class);
  							                CHflag=1;
  							                Bundle jobshowBundle = new Bundle();
  							                jobshowBundle.putSerializable("JobBean", Jobnow);
  				/*							                intent.putExtra("name", job.getName());
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
  	   		                	   
  	   		                   })
  		   
  		                   .show();
  		   	   
	    /*        tasks = (List<hr>) list.get(0);
	            adapter.notifyDataSetChanged();
	            Toast.makeText(activity, val, Toast.LENGTH_SHORT).show();*/
	            
	            // TODO
	            // UI界面的更新等相关操作
	        }
	    };
	    Runnable networkPersonalTask = new Runnable() {
	        @Override
	        public void run() {
	            // TODO
	            // 在这里进行 http request.网络请求相关操作
	        	
	        	
	            boolean result;
	            Message msg = new Message();
	            Bundle data = new Bundle();
	    
                result = ApiClient.insertR(j_id, r_id, user.getId());
                
	            msg.what=1;
	            
	            mHandler1.sendMessage(msg);

	        }
	    };

 
              private void init() {
            	  if(false){
    
        	  	new AlertDialog.Builder(ChooseHr.this)  
	      		   
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
            	Intent i1 = new Intent(ChooseHr.this,newhr.class);

		          ////启动 
		          startActivity(i1);
            }
        });
 }
}