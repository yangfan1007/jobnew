package com.job.view;

import com.example.job.R;
import com.job.app.NowUser;
import com.job.bean.UserBean;
import com.job.common.ApiClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * 
 * @author impself
 * 更改密码
 */

public class ChangePassword extends Activity {
	private EditText changepw1;
	private EditText changepw2;
	private Button btn_changewd;
	private String pw1;
	private String pw2;
	private String username;
	private String password;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.changepw);
		NowUser myword = (NowUser) getApplication(); 
		username=myword.getUserName();
		btn_changewd = (Button) findViewById(R.id.btn_changewd);
		changepw1 = (EditText) findViewById(R.id.changepw1);
		changepw2 = (EditText) findViewById(R.id.changepw2);
		System.out.println("123");
		setListener();
		btn_changewd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				pw1 = changepw1.getText().toString();
				pw2 = changepw2.getText().toString();
				Log.i("yf",pw1+pw2);
				if (pw1.equals("") || pw1.equals("")||!pw1.equals(pw2)) {
					toast("请输入新密码");
					
				} else {
					password=pw1;
					toast("验证中...");
				AsyncTask<Void,Void,Boolean> ATgetTask = new AsyncTask<Void, Void, Boolean>() {

						@Override
						protected Boolean doInBackground(Void... params) {
							// TODO Auto-generated method stub
							
							
							return ApiClient.changepw(username,password);
						}
						protected void onPostExecute(Boolean Bool){
							if(Bool==true){
								
								new AlertDialog.Builder(ChangePassword.this)  
					      		   
	   		                   .setTitle("通知")
	   		   
	   		                   .setMessage("密码修改成功")
	   		   
	   		                   .setPositiveButton("确定", new 
	  	   		                		DialogInterface.OnClickListener(){

	  										@Override
	  										public void onClick(DialogInterface dialog,  
	  			                                    int which) {
	  											finish();
	  											
	  										} 
	  	   		                	   
	  	   		                   }).show();
							

							}else{
								
								new AlertDialog.Builder(ChangePassword.this)  
					      		   
	   		                   .setTitle("通知")
	   		   
	   		                   .setMessage("密码修改失败")
	   		   
	   		                   .setPositiveButton("确定", null)
	   		   
	   		                   .show();


							}
						}				
					};
					ATgetTask.execute();
				}
				
			}
		});
		

	}

	private void setListener() {
		 
        View mBack=(ImageButton)findViewById(R.id.change_return);
		mBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                    finish();
            }
        });
 }
	public void toast(String toast) {
		// TODO Auto-generated method stub
		  Toast toast1 = Toast.makeText(getApplicationContext(), toast,  
                  Toast.LENGTH_LONG); 
		  toast1.setDuration(1);
		  toast1.show();
		  
	};

}
