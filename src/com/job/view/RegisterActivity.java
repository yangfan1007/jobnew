package com.job.view;

import com.example.job.R;
import com.job.app.NowUser;
import com.job.common.ApiClient;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends Activity{
	private Button btn_rg;
	private EditText rg_name;
	private EditText rg_passwd1;
	private EditText rg_passwd2;
	private String password;
	private String rg_flag;
	private EditText rg_id;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);
			btn_rg = (Button) findViewById(R.id.btn_rg);
			rg_name= (EditText) findViewById(R.id.rg_name);
			rg_passwd1 = (EditText) findViewById(R.id.rg_passwd1);
			rg_passwd2 = (EditText) findViewById(R.id.rg_passwd2);
			rg_id = (EditText) findViewById(R.id.rg_id);

			setListener() ;
			btn_rg.setOnClickListener(new OnClickListener() {
				
				private String mrg_passwd1;
				private String mrg_passwd2;
				private String mrg_name;
				private String mrg_flag;
				private String mrg_id;
				@Override
				public void onClick(View v) {
					
					// TODO Auto-generated method stub
					mrg_name = rg_name.getText().toString();
					mrg_passwd1 = rg_passwd1.getText().toString();
					mrg_passwd2 = rg_passwd2.getText().toString();
					mrg_id=rg_id.getText().toString();
					System.out.println(mrg_name+mrg_passwd1+mrg_passwd2+mrg_id);
					if (mrg_passwd1.equals("") ||mrg_passwd2.equals("")||mrg_name.equals("")||!mrg_passwd1.equals(mrg_passwd2)) {
						toast("请正确信息");
						
					} else {
						
						password=mrg_passwd1;
						toast("注册中...");
					AsyncTask<Void,Void,Boolean> ATgetTask = new AsyncTask<Void, Void, Boolean>() {

							private int userflag=0;
							@Override
							protected Boolean doInBackground(Void... params) {
								// TODO Auto-generated method stub
								
								if(mrg_id.equals("110"))
									userflag=1;
								Log.i("yf","1122"+mrg_name+password+userflag);
								return ApiClient.newuser(mrg_name,password,userflag);

							}
							protected void onPostExecute(Boolean Bool){
								Log.i("yf","123"+String.valueOf(Bool));
								if(Bool==true){
									
									new AlertDialog.Builder(RegisterActivity.this)  
						      		   
		   		                   .setTitle("通知")
		   		   
		   		                   .setMessage("注册成功")
		   		   
		   		                   .setPositiveButton("确定",new 
		  	   		                		DialogInterface.OnClickListener(){

		  										@Override
		  										public void onClick(DialogInterface dialog,  
		  			                                    int which) {
		  											finish();
		  										}})
		  										
		   		   
		   		                   .show();
									

								}else{
									
									new AlertDialog.Builder(RegisterActivity.this)  
						      		   
		   		                   .setTitle("通知")
		   		   
		   		                   .setMessage("注册失败")
		   		   
		   		                   .setPositiveButton("确定", null)
		   		   
		   		                   .show();


								}
							}				
						};
						ATgetTask.execute();
					}
					
				}
			});}
			

		
	
		
	public void toast(String toast) {
		// TODO Auto-generated method stub
		  Toast toast1 = Toast.makeText(getApplicationContext(), toast,  
                  Toast.LENGTH_LONG); 
		  toast1.setDuration(1);
		  toast1.show();
		  
	}
	private void setListener() {
		 
        View mBack=(ImageButton)findViewById(R.id.register_return);
		mBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                    finish();
            }
        });
		
        }
 
}

