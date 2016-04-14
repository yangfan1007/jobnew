package com.job.view_en;

import com.example.job.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class feedback_en extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.feedback);
		 ((Button)findViewById(R.id.sendback)).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					  	new AlertDialog.Builder(feedback_en.this)  
	        		   
	                   .setTitle("通知")
	   
	                   .setMessage("你的意见已经提交，我们会尽快解决")
	   
	                   .setPositiveButton("确定", null)
	   
	                   .show();
				}
			});
		 setListener();
	}
	
		private void setListener() {
			 
		        View mBack=(ImageButton)findViewById(R.id.feedback_return);
				mBack.setOnClickListener(new OnClickListener() {

		            @Override
		            public void onClick(View v) {
		                    finish();
		            }
		        });
		 }
	}
		

	