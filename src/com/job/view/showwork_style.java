package com.job.view;

import com.example.job.R;
import com.job.app.NowUser;
import com.job.bean.JobBean;
import com.job.common.ApiClient;
import com.job.util.Url;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

public class showwork_style extends Activity{
	
	protected static final TextView S = null;
	private String TAG;
	private JobBean Jobshow;
	private int flag;
	private String Style;
	private Thread thread;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.showwork_style);
		Intent intent = getIntent();
		   Jobshow= (JobBean)intent.getSerializableExtra("JobBean");
		   
		   Log.i(TAG,"Jobshow"+Jobshow.toString());
		show()/*titles,texts,sources)*/;
		setListener();
        thread = new Thread(networkPersonalTask);
        thread.start();
		}
	   Handler mHandler1 = new Handler() {
	        @Override
	        public void handleMessage(Message msg) {
	            super.handleMessage(msg);
	            Bundle bundledata = msg.getData();
	            TextView S = (TextView)findViewById(R.id.jobsytle);
	            switch(flag){
	      	  case 0:
	      		  Style="已投递";
	      		  break;
	      	  case 1:
	      		  Style="已录取";
	      		  break;
	      	  case 2:
	      		  Style="被淘汰";
	      		  break;
	      	  default: Style="无法显示";     
	      	  }
	      	  S.setText(Style);
	      	  System.out.println(Style);
 		   
	        }
	    };
	    Runnable networkPersonalTask = new Runnable() {
	        private int user_id;

			@Override
	        public void run() {
	            // TODO
	            // 在这里进行 http request.网络请求相关操作
	        	
	        	
	            Message result;
	            Message msg = new Message();
	            Bundle data = new Bundle();
	        	NowUser myword = (NowUser) getApplication();;
	        	user_id=myword.getId();
	        	System.out.println("id_style"+user_id);
	        	flag=ApiClient.getFlag(Url.Url_Search_Flag, Jobshow.getId(), user_id);
               
               
	            msg.what=1;

	            mHandler1.sendMessage(msg);

	        }
	    };

	public void show()/*String courseName, String examTime, String examPlace)*/  {
 //	view.setMinimumHeight(dip2px(this,classes * 50));


	/* Jobshow.setName(intent.getExtras().getString("name"));
	 Jobshow.setName(intent.getExtras().getString("id"));
	 Jobshow.setName(intent.getExtras().getString("company"));
	 Jobshow.setName(intent.getExtras().getString("profession"));
	 Jobshow.setName(intent.getExtras().getString("Rrofession"));
	 Jobshow.setName(intent.getExtras().getString("Require"));
	 Jobshow.setName(intent.getExtras().getString("TP"));
	 Jobshow.setName(intent.getExtras().getString("Address"));*/
	    TextView H = (TextView)findViewById(R.id.sViewH);

	    TextView M = (TextView)findViewById(R.id.sViewM);
	  
	    TextView D = (TextView)findViewById(R.id.sViewD);
	  
	 String Name = Jobshow.getName();
	 String Time = Jobshow.getAddress();//+"\t"+Jobshow.getCompany();
	 String	More = Jobshow.getProfession();//+"\n"+Jobshow.getRequire()+"\n"+Jobshow.getTp();
	 H.setText(Name);
	 M.setText(Time);
	 D.setText(More);

	 Log.i(TAG,More);
 	
     
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
 				new AlertDialog.Builder(showwork_style.this)  
       		   
                   .setTitle("通知")
   
                   .setMessage("已收藏，您可以在收藏中查找")
   
                   .setPositiveButton("确定", null)
   
                   .show();
 			}};
			ATgetTask.execute();

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
