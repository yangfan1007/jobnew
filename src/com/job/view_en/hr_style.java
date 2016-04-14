package com.job.view_en;

import com.example.job.R;
import com.job.bean.HrBean;
import com.job.common.ApiClient;
import com.job.view.ChangePassword;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class hr_style extends Activity{
		private static final String TAG = "yf";
		HrBean Hrshow = new HrBean();
		private TextView hr_username;
		private TextView hr_sex;
		private TextView hr_school;
		private TextView hr_profession;
		private TextView hr_degree;
		private TextView hr_elevel;
		private TextView hr_experience;
		private TextView hr_po;
		private TextView hr_photo;
		private TextView hr_birth;
		private String user_id;
		private String r_id;
		private String status;
		private String j_id;
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.hrpass);
			Intent intent = getIntent();
			   Hrshow= (HrBean)intent.getSerializableExtra("HrBean");
			   j_id=intent.getStringExtra("j_id");
			   
			   show()/*titles,texts,sources)*/;
			setListener();
			}
		public void show()/*String courseName, String examTime, String examPlace)*/  {
	  
			hr_username=(TextView) findViewById(R.id.hr_username);
			hr_sex=(TextView) findViewById(R.id.hr_sex);
			hr_school =(TextView) findViewById(R.id.hr_school);
			hr_profession=(TextView) findViewById(R.id.hr_profession);

			hr_elevel=(TextView) findViewById(R.id.hr_elevel);
			hr_experience=(TextView) findViewById(R.id.hr_experience);
			hr_po=(TextView) findViewById(R.id.hr_po);
			hr_photo=(TextView) findViewById(R.id.hr_photo);
			hr_birth=(TextView) findViewById(R.id.hr_birth);
			hr_username.setText(Hrshow.getName());
			hr_sex.setText(Hrshow.getSex());
			hr_school.setText(Hrshow.getSchool());
			hr_profession.setText(Hrshow.getProfession());
			hr_elevel.setText(Hrshow.getElevel());
			hr_experience.setText(Hrshow.getExperience());
			hr_po.setText(Hrshow.getPo());
			hr_photo.setText(Hrshow.getPhone());
			hr_birth.setText(Hrshow.getBirth());}
			
		
	    //	view.setMinimumHeight(dip2px(this,classes * 50));
	 

		/* Jobshow.setName(intent.getExtras().getString("name"));
		 Jobshow.setName(intent.getExtras().getString("id"));
		 Jobshow.setName(intent.getExtras().getString("company"));
		 Jobshow.setName(intent.getExtras().getString("profession"));
		 Jobshow.setName(intent.getExtras().getString("Rrofession"));
		 Jobshow.setName(intent.getExtras().getString("Require"));
		 Jobshow.setName(intent.getExtras().getString("TP"));
		 Jobshow.setName(intent.getExtras().getString("Address"));*/
		 
		/* String Name = Hrshow.getName();
		 String Time = Hrshow.getAddress();//+"\t"+Hrshow.getCompany();
		 String	More = Hrshow.getProfession();//+"\n"+Jobshow.getRequire()+"\n"+Jobshow.getTp();
		  M.setText(Time);
		  D.setText(More);
		 Log.i(TAG,More);*/
	    	
	        
	        //按键的实现方法

	          
		private void setListener() {
			 
	        View mBack=(ImageButton)findViewById(R.id.newhr_return);
			mBack.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                    finish();
	            }
	        });
			Button mpass=(Button)findViewById(R.id.hr_pass);
				mpass.setOnClickListener(new OnClickListener() {

		            @Override
		            public void onClick(View v) {
						AsyncTask<Void,Void,Boolean> ATgetTask = new AsyncTask<Void, Void, Boolean>() {


							@Override
							protected Boolean doInBackground(Void... params) {
								// TODO Auto-generated method stub
								r_id=String.valueOf(Hrshow.getId());
								Log.i("yf","r_id"+r_id);
								status="1";
								return ApiClient.hrpass(r_id,j_id,status);
							}
							protected void onPostExecute(Boolean Bool){
								if(Bool==true){
									
									new AlertDialog.Builder(hr_style.this)  
						      		   
		   		                   .setTitle("通知")
		   		   
		   		                   .setMessage("简历通过")
		   		   
		   		                   .setPositiveButton("确定", new 
		  	   		                		DialogInterface.OnClickListener(){

		  										@Override
		  										public void onClick(DialogInterface dialog,  
		  			                                    int which) {
		  											finish();
		  											Intent intent = new Intent(hr_style.this,hr_showstyle.class);
		  											 Bundle jobshowBundle = new Bundle();
		  						 					 jobshowBundle.putSerializable("HrBean", Hrshow);					          
		  						 					 intent.putExtras(jobshowBundle);
		  						 					 Log.i("yf","12status"+status);
		  						 					 intent.putExtra("status", String.valueOf(status));
		  						 					 startActivity(intent);
		  										} 
		  	   		                	   
		  	   		                   }).show();
								

								}else{
									
									new AlertDialog.Builder(hr_style.this)  
						      		   
		   		                   .setTitle("通知")
		   		   
		   		                   .setMessage("网络异常")
		   		   
		   		                   .setPositiveButton("确定", null)
		   		   
		   		                   .show();


								}
							}				
						};
						ATgetTask.execute();
					}
					

		            
				});
				Button mnopass=(Button)findViewById(R.id.hr_nopass);
					mnopass.setOnClickListener(new OnClickListener() {

			            @Override
			            public void onClick(View v) {
							AsyncTask<Void,Void,Boolean> ATgetTask = new AsyncTask<Void, Void, Boolean>() {
								

								@Override
								protected Boolean doInBackground(Void... params) {
									// TODO Auto-generated method stub
									r_id=String.valueOf(Hrshow.getId());
									Log.i("yf","r_id"+r_id);
									status="2";
									return ApiClient.hrpass(r_id,j_id,status);
								}
								protected void onPostExecute(Boolean Bool){
									if(Bool==true){
										
										new AlertDialog.Builder(hr_style.this)  
							      		   
			   		                   .setTitle("通知")
			   		   
			   		                   .setMessage("简历被淘汰")
			   		   
			   		                   .setPositiveButton("确定", new 
			  	   		                		DialogInterface.OnClickListener(){

			  										@Override
			  										public void onClick(DialogInterface dialog,  
			  			                                    int which) {
			  											finish();
			  											
			  										} 
			  	   		                	   
			  	   		                   }).show();
									

									}else{
										
										new AlertDialog.Builder(hr_style.this)  
							      		   
			   		                   .setTitle("通知")
			   		   
			   		                   .setMessage("网络异常")
			   		   
			   		                   .setPositiveButton("确定", null)
			   		   
			   		                   .show();


									}
								}				
							};
							ATgetTask.execute();
						}
						

			            }
			        );
	 }

}
