package com.job.view_en;
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
import android.widget.TextView;

	import com.example.job.R;
import com.job.adpater.HrsApater;
import com.job.app.NowUser;
import com.job.bean.HrBean;
import com.job.bean.HrsList;
import com.job.bean.JobBean;
import com.job.common.ApiClient;
import com.job.util.Url;
import com.job.view.showhr;

	public class resumetochoose_en extends Activity {
		private ListView listView;
		Thread thread;
		private String TAG="choosehr";
		public List<HrBean> hrsList;
		private int j_id=0;
		private int r_id=0;
		private NowUser user;
		private JobBean Jobnow;
		private HrBean hr;
		private int status;
		private Activity mContext=resumetochoose_en.this;
		 public static  Activity instance;
		 public static  int CHflag=0;
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.choosemanagement_en);
			instance = this;
			Intent intent = getIntent();
			   Jobnow= (JobBean)intent.getSerializableExtra("JobBean");
			   Log.i(TAG,"Jobhow"+Jobnow.toString());
			   j_id=Jobnow.getId();
			   Log.i("yf","r_id"+String.valueOf(j_id));
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
							list = ApiClient.getHrsList_E(Url.Url_Job_Hr, j_id);
							
							
					        hrsList=list.getHrslist();
					    //    System.out.println("yf"+ApiClient.getHrsList(Url.Url_Search_Hr, false,user).getCount());
		                } catch (NullPointerException e){}
		                
						return hrsList;
		            }
		            protected void onPostExecute(List<HrBean> hrsList){
						int newscount = 0;
					//if(rdata!=null ) rdata=new ArrayList<HashMap<Object, Object>>();
						try{
				        while(newscount!=list.getCount()){
				            HashMap<Object,Object> itemData=new HashMap<Object, Object>();
				            itemData.put("name",list.getHrslist().get(newscount).getName());
				            itemData.put("com",list.getHrslist().get(newscount).getProfession());
				            itemData.put("adr",list.getHrslist().get(newscount).getSchool());
				            
				           Log.i(TAG,"r_id"+r_id);
				            newscount++;
				            rdata.add(itemData);
				    //        Log.d("123",rdata.toString());
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
					            	hr =list.getHrslist().get(position);
					            	r_id=hr.getId();
					 			   AsyncTask<Void,Void,Integer> ATgetTask1 = new AsyncTask<Void, Void,Integer>() {
					 				protected Integer doInBackground(Void... voids) {
					 					Log.i("yf","r"+ApiClient.getstatus(r_id, j_id));
					 					
					            	return ApiClient.getstatus(r_id, j_id);
					 				}
					 				 protected void onPostExecute(Integer status){
					 					Intent intent;
					 			if(status==0){
					 					 intent=new Intent(resumetochoose_en.this,hr_style.class);
						 	
					 					 Bundle jobshowBundle = new Bundle();
					 					 jobshowBundle.putSerializable("HrBean", hr);					          
					 					 intent.putExtras(jobshowBundle);
					 					 intent.putExtra("j_id", String.valueOf(j_id));
					 					 Log.i("yf","j_id"+j_id);
					 					 startActivity(intent);

					 			   }else {
					 				  intent=new Intent(resumetochoose_en.this,hr_showstyle.class);
									 	
					 					 Bundle jobshowBundle = new Bundle();
					 					 jobshowBundle.putSerializable("HrBean", hr);					          
					 					 intent.putExtras(jobshowBundle);
					 					 intent.putExtra("status", String.valueOf(status));
					 					 startActivity(intent);

					 			   }
					 					}
					 				   };
					 				  ATgetTask1.execute();
					            }
					        });
						
		            }
		        };
		        ATgetTask.execute();

		        setListener();
		        Log.d("123", "finsihshowtask");

		 }
		  

	 
		         



		private void setListener() {
			 
	        View mBack=(ImageButton)findViewById(R.id.resumemanagement_return);
	       
			mBack.setOnClickListener(new OnClickListener() {

	            @Override
	            public void onClick(View v) {
	                    finish();
	            }
	        });
			
	 }

		        	
	}               
	               
