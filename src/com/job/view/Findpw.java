package com.job.view;

import com.example.job.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * 
 * @author impself
 *�һ��˺�
 */
public class Findpw extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.findpassword);
		 ((Button)findViewById(R.id.btn_find)).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					  	new AlertDialog.Builder(Findpw.this)  
	        		   
	                   .setTitle("֪ͨ")
	   
	                   .setMessage("��������Ѿ��ύ�����ǻᾡ��ظ�")
	   
	                   .setPositiveButton("ȷ��", null)
	   
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