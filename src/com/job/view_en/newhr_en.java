package com.job.view_en;


import java.util.ArrayList;
import java.util.List;


import com.example.job.R;
import com.job.app.NowUser;
import com.job.bean.HrBean;
import com.job.bean.JobBean;
import com.job.common.ApiClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class newhr_en extends Activity{
	public static int  hr_num=0;
	public String nameText = null,professionText = null,addressText = null,requireText = null;
	public String tpText = null,companyText = null;

	int i=0;
	private EditText mhr_name;
	private int j=0;
	JobBean JobNew=null;
	private EditText mhr_profession;
	private EditText mhr_address;
	private EditText mhr_require;
	private EditText mhr_tp;
	private EditText mhr_company;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);		
		setContentView(R.layout.newhr_en);
		initView();
		setListener();

		}
	private void setListener() {
		 
        View mBack=(ImageButton)findViewById(R.id.newhr_return);

		mBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                    finish();
            }
        });

 }
	private void initView() {
		Button bt_save=(Button) findViewById(R.id.bt_save);
		mhr_name=(EditText) findViewById(R.id.hr_name);
		mhr_profession=(EditText) findViewById(R.id.hr_profession);
		mhr_address =(EditText) findViewById(R.id.hr_address);
		mhr_require=(EditText) findViewById(R.id.hr_require);
		mhr_tp=(EditText) findViewById(R.id.hr_tp);
		mhr_company=(EditText) findViewById(R.id.hr_company);

		  bt_save.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
	      			nameText=mhr_name.getText().toString();
	      			Log.i("yf",nameText+"12344");
	      			professionText=mhr_profession.getText().toString();
	      			Log.i("yf",professionText.toString()+"12344");
	      			addressText=mhr_address.getText().toString();
	      			Log.i("yf",addressText+"12344");
	      			requireText=mhr_require.getText().toString();
	      			tpText=mhr_tp.getText().toString();
	      			companyText=mhr_company.getText().toString();
	      		
	      		if(nameText.equals("") ||professionText.equals("") ||
	      			addressText.equals("") ||requireText.equals("") ||tpText.equals("") ||companyText.equals("") ){
  				  	new AlertDialog.Builder(newhr_en.this)  
  	      		   
	                   .setTitle("通知")
	   
	                   .setMessage("请输入你相关信息")
	   
	                   .setPositiveButton("确定", null)
	   
	                   .show();
   				  	
				 
			       	return;
	      		  
	      		   	}
	      		  else{
	      		
	      			
	      			  AsyncTask<Void,Void,Boolean> ATgetTask = new AsyncTask<Void, Void,Boolean>() {
				            private String user_id;
							@Override
				            protected Boolean doInBackground(Void... voids) {
				            	NowUser myword = (NowUser) getApplication(); 
				            	user_id=myword.getUserName();

		      	          return  ApiClient.insertJ(nameText,professionText,
		      	      			addressText,requireText,tpText,companyText,user_id);
				            	
				            	
				            }
				            protected void onPostExecute(Boolean bool){
				            	if(bool){
				            	new AlertDialog.Builder(newhr_en.this)  
					      		   
	      		                   .setTitle("通知")
	      		   
	      		                   .setMessage("已保存")
	      		   
	      		                   .setPositiveButton("确定",new 
		  	   		                		DialogInterface.OnClickListener(){

		  										@Override
		  										public void onClick(DialogInterface dialog,  
		  			                                    int which) {
		  											finish();
		  											
		  										} 
		  	   		                	   
		  	   		                   })
	      		   
	      		                   .show();
	      		   
							
				            }else{new AlertDialog.Builder(newhr_en.this)  
				      		   
   		                   .setTitle("通知")
   		   
   		                   .setMessage("网络错误")
   		   
   		                   .setPositiveButton("确定",null)
   		   
   		                   .show();}
				            	
				            }
				        };
			     		  ATgetTask.execute();
				
	      		  }
	      	   }} );
		
}
	public void onClick(View v){
		
		
		switch(v.getId()){
		/*case R.id.hr_username:
			usernameText=mhr_username.getText().toString();
		if(!TextUtils.isEmpty(usernameText)){
			i+=1;
		}
		case R.id.hr_age:
			
			ageText=mhr_age.getText().toString();
			if(!TextUtils.isEmpty(ageText)){
				i+=1;
			}
		case R.id.hr_study:
            studyText=mhr_study.getText().toString();
			if(!TextUtils.isEmpty(studyText)){
				i+=1;
			}
		case R.id.hr_job:
			jobText=mhr_job.getText().toString();
			if(!TextUtils.isEmpty(jobText)){
				i+=1;*/
			}
		

		 

		
				
				}
			}
	
	     
			
	




