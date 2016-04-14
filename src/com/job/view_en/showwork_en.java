package com.job.view_en;

import com.example.job.R;
import com.job.bean.JobBean;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;
/**
 * 
 * @author yf
 *TODO 展示招聘信息
 *
 */
public class showwork_en extends Activity{
	
	private static final String TAG = "yf";
	JobBean Jobshow = new JobBean();
	 public static  Activity instance;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.showwork_en);
		Intent intent = getIntent();
		instance = this;
		   Jobshow= (JobBean)intent.getSerializableExtra("JobBean");
		   Log.i(TAG,"Jobshow"+Jobshow.toString());
		show()/*titles,texts,sources)*/;
		setListener();
		}
	public void show()/*String courseName, String examTime, String examPlace)*/  {
 
    TextView H = (TextView)findViewById(R.id.ViewH);

    TextView M = (TextView)findViewById(R.id.ViewM);
  
    TextView D = (TextView)findViewById(R.id.ViewD);
   
    //	view.setMinimumHeight(dip2px(this,classes * 50));
 

	/* Jobshow.setName(intent.getExtras().getString("name"));
	 Jobshow.setName(intent.getExtras().getString("id"));
	 Jobshow.setName(intent.getExtras().getString("company"));
	 Jobshow.setName(intent.getExtras().getString("profession"));
	 Jobshow.setName(intent.getExtras().getString("Rrofession"));
	 Jobshow.setName(intent.getExtras().getString("Require"));
	 Jobshow.setName(intent.getExtras().getString("TP"));
	 Jobshow.setName(intent.getExtras().getString("Address"));*/
	 
	 String Name = Jobshow.getName();
	 String Time = Jobshow.getAddress()+"\t"+Jobshow.getCompany();
	 String	More = Jobshow.getProfession()+"\n"+Jobshow.getRequire()+"\n"+Jobshow.getTp();
	  H.setText(Name);
	  M.setText(Time);
	  D.setText(More);
	 Log.i(TAG,"more"+More);
    	
        
        //按键的实现方法
    ImageButton  mImageView01=(ImageButton) findViewById(R.id.btn_care);
        mImageView01.setOnClickListener(new Button.OnClickListener() {
     	   public void onClick(View v) {
 	new AlertDialog.Builder(showwork_en.this)  
      		   
      		                   .setTitle("通知")
      		   
      		                   .setMessage("已收藏，您可以在收藏中查找")
      		   
      		                   .setPositiveButton("确定", null)
      		   
      		                   .show();

      	   }
      	  });
        



       
	}
	private void setListener() {
		 
        View mBack=(ImageButton)findViewById(R.id.showwork_return);
		mBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                    finish();
            }
        });
 }
	
}
