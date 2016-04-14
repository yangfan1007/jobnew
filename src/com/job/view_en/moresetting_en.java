package com.job.view_en;

import com.example.job.R;
import com.job.view.LoginActivity;
import com.job.view.moresetting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class moresetting_en extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.morestting);
		Intent intent = getIntent();
		setListener();
		}
	private void setListener() {
		 
        View mBack=findViewById(R.id.morestting_return);
        View maboutus=findViewById(R.id.aboutus);
        View mchangepw=findViewById(R.id.changepw);
        View mupate=findViewById(R.id.upate);
        
        View mbacklogin=findViewById(R.id.backLogin);
		mBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                    finish();
            }
        });
		mbacklogin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                    Intent intent=new Intent(moresetting_en.this,LoginActivity.class);
                    startActivity(intent);
            }
        });
		mchangepw.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
            	   Intent intent = new Intent(moresetting_en.this,ChangePassword_en.class);
            	   startActivity(intent);
                    
            }
        });
		maboutus.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
            	
				new AlertDialog.Builder(moresetting_en.this)  
	      		   
                  .setTitle("�����ǵ�����")
  
                  .setMessage("лл�㣬ʹ�����ǵ�app")
  
                  .setPositiveButton("ȷ��", null)
  
                  .show();
            }
        });
		mupate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
            	
				new AlertDialog.Builder(moresetting_en.this)  
	      		   
                  .setTitle("�汾���")
  
                  .setMessage("�����������°棬�������")
  
                  .setPositiveButton("ȷ��", null)
  
                  .show();
            }
        });
 }
}