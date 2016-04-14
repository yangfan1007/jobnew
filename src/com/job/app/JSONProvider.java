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
	 * ����
	 * 
	 * @throws JSONException
	 */
	private static ArrayList<HashMap<String, Object>> Analysis(String jsonStr)
			throws JSONException {
		/******************* ���� ***********************/
		JSONArray jsonArray = null;
		// ��ʼ��list�������
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		jsonArray = new JSONArray(jsonStr);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			// ��ʼ��map�������
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
	        //��������������������ĵ�ַ
	        String validateUrl = url;
	        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
	        
	        //���ö�ȡ��ʱ
	        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
	        HttpPost httpRequst = new HttpPost(validateUrl);

	        //׼�����������
	        List<BasicNameValuePair> params1 = new ArrayList<BasicNameValuePair>();
	        
	        
	        params1.add(new BasicNameValuePair("user", ""));
	        params1.add(new BasicNameValuePair("password",""));
	     
	        try
	        {

	                  //��������
	        	   
	                  httpRequst.setEntity(new UrlEncodedFormEntity(params1, HTTP.UTF_8));
	                  //�õ���Ӧ
	                  HttpResponse response = httpClient.execute(httpRequst);
	                  
	           
	                  //����ֵ���Ϊ200�Ļ���֤���ɹ��ĵõ�������
	                  if(response.getStatusLine().getStatusCode() == 200)
	                  {
	                
	                	  String result;
						try{ 
	                            //���õ������ݽ��н���
	                            BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	                            for(String s =buffer.readLine(); s!= null; s = buffer.readLine())
	                            {
	                                      builder.append(s);
	                                      
	                            }
	                            
	                            if(builder.toString() != null){
	                            //�õ�Json����

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
