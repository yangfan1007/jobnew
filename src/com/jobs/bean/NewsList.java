package com.jobs.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

//import com.demo.app.AppException;

public class NewsList extends JobBean {
	private static final String TAG = "yf";
	private int catalog;
	private int pageSize;
	private int newsCount;
	private List<JobBean> newslist = new ArrayList<JobBean>();

	public int getCatalog() {
		return catalog;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getNewsCount() {
		return newsCount;
	}

	public List<JobBean> getNewslist() {
		return newslist;
	}
//   public JobBean getJobBean(){
//	return null;
//   }
	public static NewsList parse(JSONArray obj) throws IOException,
			 JSONException {
		NewsList newslist = new NewsList();
		if (null != obj) {
			newslist.newsCount = obj.length();
			
			for (int i = 0; i < obj.length(); i++) {
				JSONObject jsonObj = obj.getJSONObject(i);
				JobBean news = new JobBean();
				
				news.setName(jsonObj.getString("j_name"));
				news.setCompany(jsonObj.getString("j_company"));
				news.setProfession(jsonObj.getString("j_profession"));
				news.setRequire(jsonObj.getString("j_require"));
				news.setId(jsonObj.getInt("j_id"));
				news.setAddress(jsonObj.getString("j_address"));
				news.setTp(jsonObj.getInt("j_tp"));
				Log.i(TAG,news.toString());
				newslist.newslist.add(news);
			}
		}
		return newslist;
	}


}
