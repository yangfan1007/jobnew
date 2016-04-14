package com.job.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

public class resumemanagement extends Activity{

	public static ArrayList<HrBean> mHrBean;
	 public static  Activity instance;
	ListView listView;
	public List<HrBean> hrsList;
	private Activity mContext;
	protected static int CHflag=0;
	  private HrsApater listViewAdapter;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = resumemanagement.this;
		instance = this;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.resumemanagement);
		  listView=(ListView)this.findViewById(R.id.fragment_resume_listview);
		   AsyncTask<Void,Void,List<HrBean>> ATgetTask = new AsyncTask<Void, Void, List<HrBean>>() {
	          
				private String TAG="resumemanagement";
				private Activity activity=resumemanagement.this;
				private HrsList list=new HrsList();
				private HrsList hrslist= new HrsList();
				private ArrayList<HashMap<Object, Object>> rdata = new ArrayList<HashMap<Object,Object>>();
				
				@Override
	            protected List<HrBean> doInBackground(Void... voids) {

	                try{
	       //         	UserBean userNull = new UserBean();
	       
	                	
						
						NowUser user = (NowUser) getApplication();
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
			           
			           Log.i(TAG, itemData.toString());
			            newscount++;
			            rdata.add(itemData);
			        
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
				                Intent intent = new Intent(resumemanagement.this,showhr.class);
				                HrBean hr =list.getHrslist().get(position);
				                Bundle jobshowBundle = new Bundle();
				                jobshowBundle.putSerializable("HrBean", hr);
/*							                intent.putExtra("name", job.getName());
				                intent.putExtra("id", job.getId());
				                intent.putExtra("company", job.getCompany());
				                intent.putExtra("Profession", job.getProfession());
				                intent.putExtra("Require", job.getRequire());
				                intent.putExtra("TP", job.getTp());
				                intent.putExtra("Address", job.getAddress());*/
				                intent.putExtras(jobshowBundle);
				               
				                startActivity(intent);

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
    
        	  	new AlertDialog.Builder(resumemanagement.this)  
	      		   
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
            	Intent i1 = new Intent(resumemanagement.this,newhr.class);
            	CHflag=1;
		          ////启动 
		          startActivity(i1);
		          listViewAdapter.notifyDataSetChanged();
            }
        });
 }
}