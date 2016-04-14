package com.job.view;


import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import com.example.job.R;
import com.job.Asy.ConnectAsy;
import com.job.adpater.NewsApater;
import com.job.adpater.SearchAdapter;
import com.job.app.NowUser;
import com.job.bean.*;
import com.job.common.ApiClient;
import com.job.util.Url;
import com.job.view_en.showwork_en;



import android.app.Activity;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract.Contacts.Data;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public  class Activity1 extends Fragment {
 
	  private static Activity activity;
		public static int joblength;
		ListView listView;
		private int nt=1;
		String TAG="yf";
	    /**
	     * 搜索结果列表view
	     */
	    private ListView lvResults;

	    /**
	     * 搜索view
	     */
	    private SearchView searchView;


	    /**
	     * 热搜框列表adapter
	     */
	    private ArrayAdapter<String> hintAdapter;

	    /**
	     * 自动补全列表adapter
	     */
	    private ArrayAdapter<String> autoCompleteAdapter;

	    /**
	     * 搜索结果列表adapter
	     */
	    private SearchAdapter resultAdapter;

	    private List<JobBean> dbData;

	    /**
	     * 热搜版数据
	     */
	    private List<String> hintData;

	    /**
	     * 搜索过程中自动补全数据
	     */
	    private List<String> autoCompleteData;

	    /**
	     * 搜索结果的数据
	     */
	    private List<com.jobs.bean.JobBean> resultData;

	    /**
	     * 默认提示框显示项的个数
	     */
	    private static int DEFAULT_HINT_SIZE = 4;

	    /**
	     * 提示框显示项的个数
	     */
	    private static int hintSize = DEFAULT_HINT_SIZE;

	    /**
	     * 设置提示框显示项的个数
	     *
	     * @param hintSize 提示框显示个数
	     */
	    public static void setHintSize(int hintSize) {
	        Activity1.hintSize = hintSize;
	    }
	    public static Activity getThisActivity() {
	        return activity;
	    }
	    public void onAttach(Activity activity) {

	        this.activity = activity;
	        super.onAttach(activity);
	    	}
				ArrayList<HashMap<Object, Object>> data = new ArrayList<HashMap<Object, Object>> ();
				protected List<JobBean> newsList;
				protected NewsApater listViewAdapter;
				protected NewsList list;
				protected NewsList listp;
				protected Intent intent;
				protected int num=0;
				protected boolean A1flag=false;
			
			  
				 public View onCreateView(LayoutInflater inflater, ViewGroup container,
						Bundle savedInstanceState) {
					super.onCreate(savedInstanceState);
					
					 View news = inflater.inflate(R.layout.news, container, false);
					  AsyncTask<Void,Void,List<JobBean>> ATgetTask = new AsyncTask<Void, Void, List<JobBean>>() {
				            @Override
				            protected List<JobBean> doInBackground(Void... voids) {

				                try{
				       //         	UserBean userNull = new UserBean();
				     
				                	NowUser user = (NowUser) getActivity().getApplication(); 
									list = ApiClient.getNewsList(Url.Url_Search_nNews, false,user);
							        newsList=list.getNewslist();
							        

				                } catch (NullPointerException e){}
				                
								return newsList;
				            }
				            protected void onPostExecute(List<JobBean> newsList){
				            	geneItems();
				            	init();
							
				            }
				        };
				        ATgetTask.execute();
				        Log.d("123", "finsihshowtask");
				    	View imoresetting= news.findViewById(R.id.search);
						imoresetting.setOnClickListener(new View.OnClickListener(){ 
						    @Override 
						    public void onClick(View v){ 
						    	Intent i4= new Intent(getActivity(),Search.class);

						          ////启动 
						          startActivity(i4);
						    } 
						});
						return news;
				 }
				
				 
				 
/*				 public void onActivityCreated(Bundle savedInstanceState) {
				 super.onActivityCreated(savedInstanceState);
				
			    }*/
				 public void OnClickListener(){
					 
				 }
			
				 public void init(){
					 listView=(ListView) getView().findViewById(R.id.fragment_news_listview);
					 
					 listViewAdapter = new NewsApater(activity,data);
				     listView.setAdapter(listViewAdapter);
				     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				            @Override
				            public void onItemClick(AdapterView<?> parent, View view,
				                                    int position, long id) {
				            	JobBean job =list.getNewslist().get(position);
				           /* 	num=0;
				            	while(num!=listp.getNewsCount()){
				            		if(list.getNewslist().get(position)==listp.getNewslist().get(num))
				            			A1flag=true;
				            		num++;
				            	}*/
				  /*          	System.out.println(String.valueOf(A1flag));
				            	if(A1flag){
				            		 intent = new Intent(activity, showwork_style.class);
				            	}else{*/
				            		intent = new Intent(activity, showwork.class);
				            /*	}*/
				               
				                Bundle jobshowBundle = new Bundle();
				                jobshowBundle.putSerializable("JobBean", job);
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
				                }
				     });
				  }
				 private void geneItems(){
					 int newscount = 0;
					 if(data!=null ) data=new ArrayList<HashMap<Object, Object>>();
					 while(newscount!=list.getNewsCount()){
						 
				            HashMap<Object,Object> itemData=new HashMap<Object, Object>();
				            itemData.put("name",list.getNewslist().get(newscount).getName());
				            itemData.put("com",list.getNewslist().get(newscount).getCompany());
				            itemData.put("adr", list.getNewslist().get(newscount).getAddress());
				            Log.i(TAG, list.getNewslist().get(newscount).toString());
				           Log.i(TAG, itemData.toString());
				            newscount++;
				            data.add(itemData);
				      }
					 Log.i(TAG,data.toString());


			}

			 }

	

