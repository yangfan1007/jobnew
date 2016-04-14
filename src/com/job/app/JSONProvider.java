package com.job.app;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.util.Log;

public class JSONProvider {
	private static final String TAG = "yf";
	private static String result;

	/**
	 * 解析
	 * 
	 * @throws JSONException
	 */
	private static ArrayList<HashMap<String, Object>> Analysis(String jsonStr)
			throws JSONException {
		/******************* 解析 ***********************/
		JSONArray jsonArray = null;
		// 初始化list数组对象
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		jsonArray = new JSONArray(jsonStr);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			// 初始化map数组对象
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("title", jsonObject.getString("title"));

			list.add(map);
		}
		return list;
	}

	public static String getJSONData(String url)
			throws ClientProtocolException, IOException {
		
		 HttpClient httpClient = new DefaultHttpClient();
	        StringBuilder builder = new StringBuilder(); 
	        //这里是你与服务器交互的地址
	        String validateUrl = url;
	        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
	        
	        //设置读取超时
	        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
	        HttpPost httpRequst = new HttpPost(validateUrl);

	        //准备传输的数据
	        List<BasicNameValuePair> params1 = new ArrayList<BasicNameValuePair>();
	        
	        
	        params1.add(new BasicNameValuePair("user", ""));
	        params1.add(new BasicNameValuePair("password",""));
	     
	        try
	        {

	                  //发送请求
	        	   
	                  httpRequst.setEntity(new UrlEncodedFormEntity(params1, HTTP.UTF_8));
	                  //得到响应
	                  HttpResponse response = httpClient.execute(httpRequst);
	                  
	           
	                  //返回值如果为200的话则证明成功的得到了数据
	                  if(response.getStatusLine().getStatusCode() == 200)
	                  {
	                
	                	  String result;
						try{ 
	                            //将得到的数据进行解析
	                            BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	                            for(String s =buffer.readLine(); s!= null; s = buffer.readLine())
	                            {
	                                      builder.append(s);
	                                      
	                            }
	                            
	                            if(builder.toString() != null){
	                            //得到Json对象

	                        /*    JSONArray jsonArr= new JSONArray(builder.toString());
	                            int joblength = jsonArr.length();*/

	                           
	                          
	                            	result =builder.toString();
	                            	Log.i(TAG,"buidler"+builder.toString());
	                            	return result;
	                            }

	                           result = "";
	                        
	                       
	                  } catch (Exception e)
		                {
		                          e.printStackTrace();

		                }
	                  }
			
	        	} catch (Exception e) { 
	        		e.printStackTrace(); 
	        	} 
			return result;

	}


}
