package com.job.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.job.R;
import com.job.adpater.NewsApater;
import com.job.app.NowUser;
import com.job.bean.JobBean;
import com.job.bean.NewsList;
import com.job.bean.UserBean;
import com.job.common.ApiClient;
import com.job.util.*;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public final class Activity2 extends Fragment {

	
	public static final String TAG = "a2";

	ListView listView;

	protected NewsList list;

	protected List<JobBean> newsList;

	ArrayList<HashMap<Object, Object>> data = new ArrayList<HashMap<Object, Object>> ();

	protected NewsApater listViewAdapter;

	private static Activity activity;
    public static Activity getThisActivity() {
        return activity;
    }
    public void onAttach(Activity activity) {

        this.activity = activity;
        super.onAttach(activity);
    	}
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {


	  
      final View news = inflater.inflate(R.layout.recruitment, container, false);
     
	  AsyncTask<Void,Void,List<JobBean>> ATgetTask = new AsyncTask<Void, Void, List<JobBean>>() {
            @Override
            protected List<JobBean> doInBackground(Void... voids) {

                try{
                	NowUser myword = (NowUser) getActivity().getApplication(); 
                	list = ApiClient.getNewsList(Url.Url_Id_hr,false,myword);
			        newsList=list.getNewslist();
			     //   Log.i(TAG,list.getNewslist().get(0).getName());
			       
			        
					
                } catch (NullPointerException e){}
                
				return newsList;
            }
            protected void onPostExecute(List<JobBean> newsList){
				int newscount = 0;
				try{
				if(data!=null ) data=new ArrayList<HashMap<Object, Object>>();
		        while(newscount!=list.getNewsCount()){
		        	
		        	Log.i(TAG,"1234"+list.getNewslist().get(0).getName());
		            HashMap<Object,Object> itemData=new HashMap<Object, Object>();
		            itemData.put("name",list.getNewslist().get(newscount).getName());
		            itemData.put("com",list.getNewslist().get(newscount).getCompany());
		            itemData.put("adr", list.getNewslist().get(newscount).getAddress());
		            Log.i(TAG, list.getNewslist().get(newscount).toString());
		           Log.i(TAG, itemData.toString());
		           newscount++;
		            data.add(itemData);
		        }		        
		        }catch (NullPointerException e){
		        	e.toString();
		        }
		        
		        listView=(ListView)getView().findViewById(R.id.fragment_recruitment_listview);
				 listViewAdapter = new NewsApater(getActivity(),data);
			        listView.setAdapter(listViewAdapter);
			        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			            @Override
			            public void onItemClick(AdapterView<?> parent, View view,
			                                    int position, long id) {
			                Intent intent = new Intent(activity, showwork_style.class);
			                JobBean job =list.getNewslist().get(position);
			                Bundle jobshowBundle = new Bundle();
			                jobshowBundle.putSerializable("JobBean", job);
/*							                intent.putExtra("name", job.getName());
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
        };
        ATgetTask.execute();
        Log.d("123", "finsihshowtask");
		return news;
 }
}




