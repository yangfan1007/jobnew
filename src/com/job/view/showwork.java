package com.job.view;

import com.example.job.R;
import com.job.app.NowUser;
import com.job.bean.JobBean;
import com.job.common.ApiClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;

import android.os.AsyncTask;
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
public class showwork extends Activity{
	
	private static final String TAG = "yf";
	JobBean Jobshow = new JobBean();
	 public static  Activity instance;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.showwork);
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
     			AsyncTask<Void,Void,Boolean> ATgetTask = new AsyncTask<Void, Void, Boolean>() {

					@Override
					protected Boolean doInBackground(Void... params) {
						return ApiClient.icollection(Jobshow.getId(), ((NowUser) getApplication()).getId());
						// TODO Auto-generated method stub
					

					}
     			protected void onPostExecute(Boolean Bool){
     				new AlertDialog.Builder(showwork.this)  
           		   
	                   .setTitle("通知")
	   
	                   .setMessage("已收藏，您可以在收藏中查找")
	   
	                   .setPositiveButton("确定", null)
	   
	                   .show();
     			}};
				ATgetTask.execute();
     			}});
        
           Button button1 = (Button)findViewById(R.id.bt_sendnew);

           button1.setOnClickListener(new Button.OnClickListener() {

        	   @Override
        	   
        	   public void onClick(View v) {
        		   Intent intent = new Intent(showwork.this,ChooseHr.class);
          		  //intent.getParcelableExtra(Jobshow);
        		   Bundle jobshowBundle = new Bundle();
	                jobshowBundle.putSerializable("JobBean", Jobshow);
	              /*  intent.putExtra("name", job.getName());
	                intent.putExtra("id", job.getId());
	                intent.putExtra("company", job.getCompany());
	                intent.putExtra("Profession", job.getProfession());
	                intent.putExtra("Require", job.getRequire());
	                intent.putExtra("TP", job.getTp());
	                intent.putExtra("Address", job.getAddress());*/
	                intent.putExtras(jobshowBundle);
	                Log.i(TAG,intent.toString());
          		  startActivity(intent);
        		 /*  	new AlertDialog.Builder(showwork.this)  
        		   
        		                   .setTitle("通知")
        		   
        		                   .setMessage("你的简历已经成功投递，请您耐心等待消息")
        		   
        		                   .setPositiveButton("确定", null)
        		   
        		                   .show();*/

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
