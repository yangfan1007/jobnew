package com.job.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


public class HrsList extends HrBean {

	private int catalog=0;
	private int pageSize=0;
	private int hrsCount=0;
	private static List<HrBean> hrslist = new ArrayList<HrBean>();
	
	public int getCatalog() {
		return catalog;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getCount() {
		return hrsCount;
	}

	public List<HrBean> getHrslist() {
		
		return hrslist;
	}
public static HrsList parse(JSONArray obj) throws IOException,JSONException {
	HrsList hrslist = new HrsList();
if (null != obj) {
	hrslist.hrsCount = obj.length();
	System.out.println( "num"+hrslist.hrsCount);
	for (int i = 0; i < obj.length(); i++) {
		JSONObject jsonObj = obj.getJSONObject(i);
		HrBean news = new HrBean();
		
		news.setName(jsonObj.getString("r_name"));
		news.setSex(jsonObj.getString("r_sex"));
		news.setPo(jsonObj.getString("r_po"));
		news.setBirth(jsonObj.getString("r_birth"));
		news.setElevel(jsonObj.getString("r_elevel"));
		news.setProfession(jsonObj.getString("r_profession"));
		news.setExperience(jsonObj.getString("r_experience"));
		news.setId(jsonObj.getInt("r_id"));
		news.setSchool(jsonObj.getString("r_school"));
		news.setPhone(jsonObj.getString("r_phone"));

		hrslist.hrslist.add(news); 
	}
}
return hrslist;
}
	
}
