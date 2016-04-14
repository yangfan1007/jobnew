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

public class hr_showstyle extends Activity{
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
		private TextView hr_style;
		private String mstatus;
		private int statusi;
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.hrstyle);
			Intent intent = getIntent();
			   Hrshow= (HrBean)intent.getSerializableExtra("HrBean");
			   
			   status= intent.getExtras().getString("status");
			   Log.i("yf","Hrshow"+status);
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
			hr_style=(TextView) findViewById(R.id.hr_style);

			hr_username.setText(Hrshow.getName());
			hr_sex.setText(Hrshow.getSex());
			hr_school.setText(Hrshow.getSchool());
			hr_profession.setText(Hrshow.getProfession());
			hr_elevel.setText(Hrshow.getElevel());
			hr_experience.setText(Hrshow.getExperience());
			hr_po.setText(Hrshow.getPo());
			hr_photo.setText(Hrshow.getPhone());
			hr_birth.setText(Hrshow.getBirth());
			statusi= Integer.parseInt(status);
        if(statusi==1){
        	mstatus="已通过";
        }else if(statusi==2){
        	mstatus="已淘汰";
        }else if(statusi==3){
        	mstatus="已淘汰";
        }
        hr_style.setText(mstatus);
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

}
