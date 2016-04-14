package com.job.view;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.example.job.R;
import com.job.adpater.NewsApater;
import com.job.app.NowUser;
import com.job.bean.HrBean;
import com.job.bean.JobBean;
import com.job.common.ApiClient;
import com.job.util.Url;
import com.job.view_en.newhr_en;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class newhr extends Activity{

	private String mhr_username;
	private String mhr_sex;
	private String mhr_school;
	private String mhr_profession;
	private String mhr_degree;
	private String mhr_elevel;
	private String mhr_experience;
	private String mhr_po;
	private String mhr_photo;
	private String mhr_birth;
	private HrBean hrnew;
	private EditText hr_username;
	private EditText hr_sex;
	private EditText hr_school;
	private EditText hr_profession;
	private EditText hr_degree;
	private EditText hr_elevel;
	private EditText hr_experience;
	private EditText hr_po;
	private EditText hr_photo;
	private EditText hr_birth;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);		
		setContentView(R.layout.newhr);
		setListener();
		initView();
		}
	private void setListener() {
		 
        View mBack=(ImageButton)findViewById(R.id.newhr_return);

		mBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                    finish();
            }
        });
		initView();
 }
	private void initView() {
		Button bt_save=(Button) findViewById(R.id.bt_save);
		hr_username=(EditText) findViewById(R.id.hr_username);
		hr_sex=(EditText) findViewById(R.id.hr_sex);
		hr_school =(EditText) findViewById(R.id.hr_school);
		hr_profession=(EditText) findViewById(R.id.hr_profession);
		hr_degree=(EditText) findViewById(R.id.hr_degree);
		hr_elevel=(EditText) findViewById(R.id.hr_elevel);
		hr_experience=(EditText) findViewById(R.id.hr_experience);
		hr_po=(EditText) findViewById(R.id.hr_po);
		hr_photo=(EditText) findViewById(R.id.hr_photo);
		hr_birth=(EditText) findViewById(R.id.hr_birth);
	

		  bt_save.setOnClickListener(new Button.OnClickListener() {
	      	   public void onClick(View v) {
	      		   mhr_username=hr_username.getText().toString();
	      			mhr_sex=hr_sex.getText().toString();
	      			mhr_school=hr_school.getText().toString();
	      			mhr_profession=hr_profession.getText().toString();
	      			mhr_degree=hr_degree.getText().toString();
	      			Log.i("yf",mhr_degree);
	      			mhr_elevel=hr_elevel.getText().toString();
	      			mhr_experience=hr_experience.getText().toString();
	      			mhr_po=hr_po.getText().toString();
	      			mhr_photo=hr_photo.getText().toString();
	      			mhr_birth=hr_birth.getText().toString();
	      		if(mhr_username.equals("")||mhr_sex.equals("")||
	      				mhr_birth.equals("")||mhr_profession.equals("")||
	      				mhr_school.equals("")||mhr_degree.equals("")||
	      				mhr_elevel.equals("")||mhr_po.equals("")||
	      				mhr_photo.equals("")||mhr_experience.equals("")){
  				  	new AlertDialog.Builder(newhr.this)  
		
	                   .setTitle("通知")
	   
	                   .setMessage("请输入你相关信息")
	   
	                   .setPositiveButton("确定", null)
	   
	                   .show();
   				  	
				 
			       	return;
	      		  
	      		   	}
	      		  else{
	      			
	     		  AsyncTask<Void,Void,Boolean> ATgetTask = new AsyncTask<Void, Void, Boolean>() {
	      	            @Override
	      	            protected Boolean doInBackground(Void... voids) {
	      	            	NowUser myword = (NowUser)getApplication(); 
	      	            	String user_id = myword.getUserName();
	      	          return  ApiClient.insertR(mhr_username,mhr_sex,
	  	      				mhr_birth,mhr_profession,
		      				mhr_school,mhr_degree,
		      				mhr_elevel,mhr_po,
		      				mhr_photo,mhr_experience,user_id);
	      	            }
	      	            protected void onPostExecute(Boolean Bool){
	      	          	if(Bool){
			            	new AlertDialog.Builder(newhr.this)  
				      		   
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
      		   
						
			            }else{new AlertDialog.Builder(newhr.this)  
			      		   
		                   .setTitle("通知")
		   
		                   .setMessage("网络错误")
		   
		                   .setPositiveButton("确定",null)
		   
		                   .show();}
	      	            }
	      	            
	     		  };
	     		  ATgetTask.execute();
	      		  }
	      	   }});}}
	      					

					   
						
				



