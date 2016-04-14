package com.job.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.job.app.NowUser;
import com.job.bean.HrBean;
import com.job.bean.HrsList;
import com.job.bean.JobBean;
import com.job.bean.NewsList;
import com.job.bean.UserBean;
import com.job.util.StringUtils;
import com.job.util.Url;

import android.util.Log;




/**
 * API客户端接口：用于访问网络数据
 * 
 * @version 1.0
 * @created 2012-3-21
 */
public class ApiClient {

	public static final String UTF_8 = "UTF-8";
	public static final String DESC = "descend";
	public static final String ASC = "ascend";

	private final static int TIMEOUT_CONNECTION = 8000;
	private final static int TIMEOUT_SOCKET = 8000;
	private final static int RETRY_TIME = 2;
	private static final String TAG = "yf";

	private static String appCookie;
	private static String appUserAgent;
	private static boolean bool;
	private static String url;



	

	private static String _MakeURL(String p_url, Map<String, Object> params) {
		StringBuilder url = new StringBuilder(p_url);
		if (url.indexOf("?") < 0)
			url.append('?');

		for (String name : params.keySet()) {
			url.append('&');
			url.append(name);
			url.append('=');
			url.append(String.valueOf(params.get(name)));
			// 不做URLEncoder处理
			// url.append(URLEncoder.encode(String.valueOf(params.get(name)),
			// UTF_8));
		}

		return url.toString().replace("?&", "?");
	}

	private static String http_get2(String url,NowUser user){
	    HttpClient httpClient = new DefaultHttpClient();
        StringBuilder builder = new StringBuilder(); 
        //这里是你与服务器交互的地址
 
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
        
        //设置读取超时
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
        HttpPost httpRequst = new HttpPost(url);

        //准备传输的数据
        List<BasicNameValuePair> params1 = new ArrayList<BasicNameValuePair>();
        
        
        params1.add(new BasicNameValuePair("user",user.getUserName()));
        params1.add(new BasicNameValuePair("password",user.getPassword()));
        params1.add(new BasicNameValuePair("user_id",String.valueOf(user.getId())));
        
     
        try
        {

                  //发送请求
        	   
                  httpRequst.setEntity(new UrlEncodedFormEntity(params1, HTTP.UTF_8));
                  String TAG="yf";
				Log.i(TAG, params1.toString());
                  //得到响应
                  HttpResponse response = httpClient.execute(httpRequst);
                  
                  Log.i(TAG, "3");
                  Log.i(TAG,String.valueOf(response.getStatusLine().getStatusCode()));
                 
                  //返回值如果为200的话则证明成功的得到了数据
                  if(response.getStatusLine().getStatusCode() == 200)
                  {
                	  Log.i(TAG, "2");
                	  try{ 
                            //将得到的数据进行解析
                            BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                            Log.i(TAG, builder.toString());
                            for(String s =buffer.readLine(); s!= null; s = buffer.readLine())
                            {
                                      builder.append(s);
                                      
                            } 
                            Log.i(TAG, builder.toString());
                            return builder.toString();
                	  	}catch (Exception e){
  	                          e.printStackTrace();
  	                        return "";
                	  	}
                 }
        }catch (Exception e){
                       e.printStackTrace();
                       return "";
       }
        return "";
		
 }





	/*
	 * 获取据列表
	 * 
	 * @return
	 * @throws AppException
	 */
	public static NewsList getNewsList(String url,boolean bool,NowUser user)
			 {
		try {
 if(bool)
 {
			
			Log.i(TAG,"newlist.parse"+NewsList.parse(new JSONArray(http_get1(url))).getNewslist().get(0).toString());
			return NewsList.parse(new JSONArray(http_get1(url)));
			}else{
				Log.i(TAG,"newlist.parse"+NewsList.parse(new JSONArray(http_get2(url,user))).getNewslist().get(0).toString());
				return NewsList.parse(new JSONArray(http_get2(url,user)));
			}
		} catch (Exception e) {

			System.out.println(e);
			return null;
		}
		
		
	}

	private static String http_get1(String url) {
		// TODO Auto-generated method stub
		  HttpClient httpClient = new DefaultHttpClient();
	        StringBuilder builder = new StringBuilder(); 
	        //这里是你与服务器交互的地址
	        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);	        
	        //设置读取超时
	        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
	        HttpPost httpRequst = new HttpPost(url);
	        //准备传输的数据
	        List<BasicNameValuePair> params1 = new ArrayList<BasicNameValuePair>();       
	        params1.add(new BasicNameValuePair("user",""));
	        params1.add(new BasicNameValuePair("password",""));
	        try
	        {

	                  //发送请求
	        	   
	                  httpRequst.setEntity(new UrlEncodedFormEntity(params1, HTTP.UTF_8));
	                  String TAG="yf";
					Log.i(TAG, params1.toString());
	                  //得到响应
	                  HttpResponse response = httpClient.execute(httpRequst);
	                  
	                  Log.i(TAG, "3");
	                  Log.i(TAG,String.valueOf(response.getStatusLine().getStatusCode()));
	                 
	                  //返回值如果为200的话则证明成功的得到了数据
	                  if(response.getStatusLine().getStatusCode() == 200)
	                  {
	                	  Log.i(TAG, "2");
	                	  try{ 
	                            //将得到的数据进行解析
	                            BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	                            for(String s =buffer.readLine(); s!= null; s = buffer.readLine())
	                            {
	                                      builder.append(s);
	                                      
	                            } 
	                            Log.i(TAG, builder.toString());
	                            return builder.toString();
	                	  	}catch (Exception e){
	  	                          e.printStackTrace();
	  	                        return "";
	                	  	}
	                 }
	        }catch (Exception e){
	                       e.printStackTrace();
	                       return "";
	       }
	        return "";
		
	}
	/**
	 * 
	 * @param url
	 * @param j_id
	 * @param r_id
	 * @param user_id
	 * @return
	 */
	public static boolean icollection(int j_id,int user_id){
		return http_get3(Url.Url_icollection, j_id, 0, user_id);
		
	}
	private static boolean http_get3(String url, int j_id, int r_id,int user_id) {

		  HttpClient httpClient = new DefaultHttpClient();
	        StringBuilder builder = new StringBuilder(); 
	        //这里是你与服务器交互的地址
	        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
	        
	        //设置读取超时
	        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
	        HttpPost httpRequst = new HttpPost(url);

	        //准备传输的数据
	        List<BasicNameValuePair> params1 = new ArrayList<BasicNameValuePair>();
	
	        params1.add(new BasicNameValuePair("user_id",String.valueOf(user_id)));
	        params1.add(new BasicNameValuePair("r_id",String.valueOf(r_id)));
	        params1.add(new BasicNameValuePair("j_id",String.valueOf(j_id)));
	        
	        try
	        {

	                  //发送请求
	        	   
	                  httpRequst.setEntity(new UrlEncodedFormEntity(params1, HTTP.UTF_8));
	                  String TAG="yf";
					Log.i(TAG, params1.toString());
	                  //得到响应
	                  HttpResponse response = httpClient.execute(httpRequst);
	                  
	                  Log.i(TAG, "3");
	                  Log.i(TAG,String.valueOf(response.getStatusLine().getStatusCode()));
	                 
	                  //返回值如果为200的话则证明成功的得到了数据
	                  if(response.getStatusLine().getStatusCode() == 200)
	                  {
	                	  Log.i(TAG, "2");
	                	  try{ 
	                            //将得到的数据进行解析
	                            BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	                            for(String s =buffer.readLine(); s!= null; s = buffer.readLine())
	                            {
	                                      builder.append(s);
	                                      
	                            } 
	                            Log.i(TAG, builder.toString());
	                            if(builder.toString()=="1 record added")

	  	                        return true;

	
	                	  	}catch (Exception e){
	                		  e.printStackTrace();
	                		  return false;
	                	  	}
	                  }
	               
	        }catch (Exception e){
      		  e.printStackTrace();
      		  return false;
      	  	}
			return false;
		
	   }
	
	public static HrsList getHrsList(String url, boolean bool,NowUser user) {
		// TODO Auto-generated method stub\
		try {
			 if(bool)
			 {
						
						Log.i(TAG,"hrlist.parse"+HrsList.parse(new JSONArray(http_get1(url))).getHrslist().get(0).toString());
						return HrsList.parse(new JSONArray(http_get1(url)));
						}else{
							Log.i(TAG,"hrlist.parse"+HrsList.parse(new JSONArray(http_get2(url,user))).getHrslist().get(0).toString());
							return HrsList.parse(new JSONArray(http_get2(url,user)));
						}
					} catch (Exception e) {

						System.out.println(e);
						return null;
					}
	}

	public static int getFlag(String url_Search_Flag, int j_id,int user_id) {
		// TODO Auto-generated method stub

		return http_getFlag(url_Search_Flag, j_id,user_id);
	}
	public static boolean hrpass(String r_id,String j_id,String status) {
		// TODO Auto-generated method stub

		return http_hrpass(r_id,j_id,status);
	}

	private static boolean http_hrpass(String r_id,String j_id,String status) {
		// TODO Auto-generated method stub
		 HttpClient httpClient = new DefaultHttpClient();
	        StringBuilder builder = new StringBuilder(); 
	        //这里是你与服务器交互的地址
	        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
	        
	        //设置读取超时
	        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
	        HttpPost httpRequst = new HttpPost(Url.Url_HrPass);

	        //准备传输的数据
	        List<BasicNameValuePair> params1 = new ArrayList<BasicNameValuePair>();
	
	        params1.add(new BasicNameValuePair("r_id",String.valueOf(r_id)));
	        params1.add(new BasicNameValuePair("j_id",String.valueOf(j_id)));
	        params1.add(new BasicNameValuePair("status",String.valueOf(status)));
	        
	        try
	        {

	                  //发送请求
	        	   
	                  httpRequst.setEntity(new UrlEncodedFormEntity(params1, HTTP.UTF_8));
	                  String TAG="yf";
					Log.i(TAG, params1.toString());
	                  //得到响应
	                  HttpResponse response = httpClient.execute(httpRequst);
	                  
	                  Log.i(TAG, "3");
	                  Log.i(TAG,String.valueOf(response.getStatusLine().getStatusCode()));
	                 
	                  //返回值如果为200的话则证明成功的得到了数据
	                  if(response.getStatusLine().getStatusCode() == 200)
	                  {
	                	  Log.i(TAG, "2");
	                	  try{ 
	                            //将得到的数据进行解析
	                            BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	                            for(String s =buffer.readLine(); s!= null; s = buffer.readLine())
	                            {
	                                      builder.append(s);
	                                      
	                            } 
	                            Log.i("yf", "2"+builder.toString());
	                            if(builder.toString().equals("right"))

	  	                        return true;

	
	                	  	}catch (Exception e){
	                		  e.printStackTrace();
	                		  return false;
	                	  	}
	                  }
	               
	        }catch (Exception e){
   		  e.printStackTrace();
   		  return false;
   	  	}
			return false;
	}

	public static boolean changepw(String user_name, String user_passwd) {
		// TODO Auto-generated method stub

		return http_changepw(user_name,user_passwd);
	}

	private static boolean http_changepw(String user_name, String user_passwd) {
		// TODO Auto-generated method stub
		 HttpClient httpClient = new DefaultHttpClient();
	        StringBuilder builder = new StringBuilder(); 
	        //这里是你与服务器交互的地址
	        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
	        
	        //设置读取超时
	        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
	        HttpPost httpRequst = new HttpPost(Url.Url_ChangeWord);

	        //准备传输的数据
	        List<BasicNameValuePair> params1 = new ArrayList<BasicNameValuePair>();
	
	        params1.add(new BasicNameValuePair("user_name",String.valueOf(user_name)));
	        params1.add(new BasicNameValuePair("user_password",String.valueOf(user_passwd)));
	      
	        
	        try
	        {

	                  //发送请求
	        	   
	                  httpRequst.setEntity(new UrlEncodedFormEntity(params1, HTTP.UTF_8));
	                  String TAG="yf";
					Log.i(TAG, params1.toString());
	                  //得到响应
	                  HttpResponse response = httpClient.execute(httpRequst);
	                  
	                  Log.i(TAG, "3");
	                  Log.i(TAG,String.valueOf(response.getStatusLine().getStatusCode()));
	                 
	                  //返回值如果为200的话则证明成功的得到了数据
	                  if(response.getStatusLine().getStatusCode() == 200)
	                  {
	                	  Log.i(TAG, "2");
	                	  try{ 
	                            //将得到的数据进行解析
	                            BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	                            for(String s =buffer.readLine(); s!= null; s = buffer.readLine())
	                            {
	                                      builder.append(s);
	                                      
	                            } 
	                            Log.i("yf", "2"+builder.toString());
	                            if(builder.toString().equals("right"))

	  	                        return true;

	
	                	  	}catch (Exception e){
	                		  e.printStackTrace();
	                		  return false;
	                	  	}
	                  }
	               
	        }catch (Exception e){
   		  e.printStackTrace();
   		  return false;
   	  	}
			return false;
	}

	private static int http_getFlag(String url, int j_id,int user_id) {
		// TODO Auto-generated method stub
		 HttpClient httpClient = new DefaultHttpClient();
	        StringBuilder builder = new StringBuilder(); 
	        //这里是你与服务器交互的地址
	        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
	        
	        //设置读取超时
	        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
	        HttpPost httpRequst = new HttpPost(url);

	        //准备传输的数据
	        List<BasicNameValuePair> params1 = new ArrayList<BasicNameValuePair>();
	
	        params1.add(new BasicNameValuePair("user_id",String.valueOf(user_id)));
	        params1.add(new BasicNameValuePair("j_id",String.valueOf(j_id)));
	        
	        try
	        {

	                  //发送请求
	        	   
	                  httpRequst.setEntity(new UrlEncodedFormEntity(params1, HTTP.UTF_8));

	                  //得到响应
	                  HttpResponse response = httpClient.execute(httpRequst);

	                  Log.i(TAG,String.valueOf(response.getStatusLine().getStatusCode()));
	                 
	                  //返回值如果为200的话则证明成功的得到了数据
	                  if(response.getStatusLine().getStatusCode() == 200)
	                  {
	                	  try{ 
	                            //将得到的数据进行解析
	                            BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	                            for(String s =buffer.readLine(); s!= null; s = buffer.readLine())
	                            {
	                                      builder.append(s);
	                                      
	                            } 
	                            System.out.println("A_flag"+builder.toString());
	                            JSONObject jsonObj = new JSONObject(builder.toString());
                       
                                 //通过得到键值对的方式得到值
                                
                                int flag = jsonObj.getInt("status");
                                System.out.println("A_flag"+flag);

	  	                        return flag;

	
	                	  	}catch (Exception e){
	                		  e.printStackTrace();
	                		  return 3;
	                	  	}
	                  }
	               
	        }catch (Exception e){
   		  e.printStackTrace();
   		  return 3;
   	  	}
			return 3;
		
	}

	public static boolean insertR(int j_id, int r_id,int user_id) {
		Log.i(TAG,String.valueOf(http_get3(Url.Url_Insert_Hr,j_id,r_id,user_id)));
		return http_get3(Url.Url_Insert_Hr,j_id,r_id,user_id);
        
		// TODO Auto-generated method stub
		
	}
	public static boolean insertR(String mhr_username,String mhr_sex,
			String mhr_birth,String mhr_profession,
			String	mhr_school,String mhr_degree,
			String	mhr_elevel,String mhr_po,
			String	mhr_photo, String mhr_experience,String user_id) {
		
		return http_InsertR(mhr_username,mhr_sex,
    				mhr_birth,mhr_profession,
  				mhr_school,mhr_degree,
  				mhr_elevel,mhr_po,
  				mhr_photo,mhr_experience,user_id);
        
		// TODO Auto-generated method stub
		
	}
	private static boolean http_InsertR(String mhr_username,String mhr_sex,
			String mhr_birth,String mhr_profession,
				String mhr_school, String mhr_degree,
				String mhr_elevel,String mhr_po,
				String mhr_photo,String mhr_experience,String user_id) {
		  HttpClient httpClient = new DefaultHttpClient();
	        StringBuilder builder = new StringBuilder(); 
	        //这里是你与服务器交互的地址
	        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
	        url=Url.Url_Insert_Resume;
	        //设置读取超时
	        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
	        HttpPost httpRequst = new HttpPost(url);

	        //准备传输的数据
	        List<BasicNameValuePair> params1 = new ArrayList<BasicNameValuePair>();
	        
	        params1.add(new BasicNameValuePair("user_id",user_id));
	        params1.add(new BasicNameValuePair("username",mhr_username));
	        params1.add(new BasicNameValuePair("sex",mhr_sex));
	        params1.add(new BasicNameValuePair("school",mhr_school));
	        params1.add(new BasicNameValuePair("profession",mhr_profession));
	        params1.add(new BasicNameValuePair("degree",mhr_degree));
	        params1.add(new BasicNameValuePair("elevel",mhr_elevel));
	        params1.add(new BasicNameValuePair("experience",mhr_experience));
	        params1.add(new BasicNameValuePair("po",mhr_po));
	        params1.add(new BasicNameValuePair("photo",mhr_photo));
	        params1.add(new BasicNameValuePair("birth",mhr_birth));
	      
	        try
	        {

	                  //发送请求
	        	   
	                  httpRequst.setEntity(new UrlEncodedFormEntity(params1, HTTP.UTF_8));
	                  String TAG="yf";
					Log.i(TAG, params1.toString());
	                  //得到响应
	                  HttpResponse response = httpClient.execute(httpRequst);
	                  
	                  Log.i(TAG, "3");
	                  Log.i(TAG,String.valueOf(response.getStatusLine().getStatusCode()));
	                 
	                  //返回值如果为200的话则证明成功的得到了数据
	                  if(response.getStatusLine().getStatusCode() == 200)
	                  {
	                	  Log.i(TAG, "2");
	                	  try{ 
	                            //将得到的数据进行解析
	                            BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	                            for(String s =buffer.readLine(); s!= null; s = buffer.readLine())
	                            {
	                                      builder.append(s);
	                                      
	                            } 
	                            Log.i(TAG, builder.toString());
	                            if(builder.toString().equals("1 record added"))

	  	                        return true;

	
	                	  	}catch (Exception e){
	                		  e.printStackTrace();
	                		  return false;
	                	  	}
	                  }
	               
	        }catch (Exception e){
  		  e.printStackTrace();
  		  return false;
  	  	}
			return false;

	}

	public static boolean insertJ(String nameText,String professionText,
			String addressText,String requireText,String tpText,String companyText, String user_id) {
		
		return http_InsertJ(nameText,professionText,
				addressText, requireText, tpText, companyText,user_id);
        
		// TODO Auto-generated method stub
		
	}

	private static boolean http_InsertJ(String nameText,String professionText,
			String addressText,String requireText,String tpText,String companyText,String user_id) {
		  HttpClient httpClient = new DefaultHttpClient();
	        StringBuilder builder = new StringBuilder(); 
	        //这里是你与服务器交互的地址
	        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
	        url=Url.Url_Insert_Job;
	        //设置读取超时
	        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
	        HttpPost httpRequst = new HttpPost(url);

	        //准备传输的数据
	        List<BasicNameValuePair> params1 = new ArrayList<BasicNameValuePair>();
	
	        params1.add(new BasicNameValuePair("user_id",String.valueOf(user_id)));
	        params1.add(new BasicNameValuePair("name",String.valueOf(nameText)));
	        params1.add(new BasicNameValuePair("tp",String.valueOf(tpText)));
	        params1.add(new BasicNameValuePair("profession",String.valueOf(professionText)));
	        params1.add(new BasicNameValuePair("require",String.valueOf(requireText)));
	        params1.add(new BasicNameValuePair("company",String.valueOf(companyText)));
	        params1.add(new BasicNameValuePair("address",String.valueOf(addressText)));

	        
	        
	        try
	        {

	                  //发送请求
	        	   
	                  httpRequst.setEntity(new UrlEncodedFormEntity(params1, HTTP.UTF_8));
	                  String TAG="yf";
					Log.i(TAG, params1.toString());
	                  //得到响应
	                  HttpResponse response = httpClient.execute(httpRequst);
	                  
	                  Log.i(TAG, "3");
	                  Log.i(TAG,String.valueOf(response.getStatusLine().getStatusCode()));
	                 
	                  //返回值如果为200的话则证明成功的得到了数据
	                  if(response.getStatusLine().getStatusCode() == 200)
	                  {
	                	  Log.i(TAG, "2");
	                	  try{ 
	                            //将得到的数据进行解析
	                            BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	                            for(String s =buffer.readLine(); s!= null; s = buffer.readLine())
	                            {
	                                      builder.append(s);
	                                      
	                            } 
	                            Log.i(TAG, builder.toString());
	                            if(builder.toString().equals("added"))

	  	                        return true;

	
	                	  	}catch (Exception e){
	                		  e.printStackTrace();
	                		  return false;
	                	  	}
	                  }
	               
	        }catch (Exception e){
    		  e.printStackTrace();
    		  return false;
    	  	}
			return false;

	}

	public static Boolean newuser(String user_name, String user_password, int userflag) {

		return http_newuser(user_name, user_password, userflag);
	}

	private static Boolean http_newuser(String user_name, String user_passwd,
			int userflag) {
		 HttpClient httpClient = new DefaultHttpClient();
	        StringBuilder builder = new StringBuilder(); 
	        //这里是你与服务器交互的地址
	        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
	        
	        //设置读取超时
	        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
	        HttpPost httpRequst = new HttpPost(Url.Url_Insert_User);

	        //准备传输的数据
	        List<BasicNameValuePair> params1 = new ArrayList<BasicNameValuePair>();
	
	        params1.add(new BasicNameValuePair("user_id",String.valueOf(user_name)));
	        params1.add(new BasicNameValuePair("user_flag",String.valueOf(userflag)));
	        params1.add(new BasicNameValuePair("user_password",String.valueOf(user_passwd)));
	      
	        
	        try
	        {

	                  //发送请求
	        	   
	                  httpRequst.setEntity(new UrlEncodedFormEntity(params1, HTTP.UTF_8));
	                  String TAG="yf";
					Log.i(TAG, params1.toString());
	                  //得到响应
	                  HttpResponse response = httpClient.execute(httpRequst);
	                  
	                  Log.i(TAG, "3");
	                  Log.i(TAG,String.valueOf(response.getStatusLine().getStatusCode()));
	                 
	                  //返回值如果为200的话则证明成功的得到了数据
	                  if(response.getStatusLine().getStatusCode() == 200)
	                  {
	                	  Log.i(TAG, "2");
	                	  try{ 
	                            //将得到的数据进行解析
	                            BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	                            for(String s =buffer.readLine(); s!= null; s = buffer.readLine())
	                            {
	                                      builder.append(s);
	                                      
	                            } 
	                            Log.i(TAG, "wpde"+builder.toString());
	                            if(builder.toString().equals("right")){
	  	                        return true;
	                            }
	
	                	  	}catch (Exception e){
	                		  e.printStackTrace();
	                		  return false;
	                	  	}
	                  }
	               
	        }catch (Exception e){
		  e.printStackTrace();
		  return false;
	  	}
			return false;
		// TODO Auto-generated method stub

	}

	public static HrsList getHrsList_E(String url_Job_Hr, int j_id) {
		// TODO Auto-generated method stub
		

			try {
				return HrsList.parse(new JSONArray(http_HrsList_E(url_Job_Hr, j_id)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return null;
	}

	private static String http_HrsList_E(String url, int j_id) {
		  HttpClient httpClient = new DefaultHttpClient();
	        StringBuilder builder = new StringBuilder(); 
	        //这里是你与服务器交互的地址
	        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
	        
	        //设置读取超时
	        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
	        HttpPost httpRequst = new HttpPost(url);

	        //准备传输的数据
	        List<BasicNameValuePair> params1 = new ArrayList<BasicNameValuePair>();
	        
	        
	        params1.add(new BasicNameValuePair("j_id",String.valueOf(j_id)));

	        
	     
	        try
	        {

	                  //发送请求
	        	   
	                  httpRequst.setEntity(new UrlEncodedFormEntity(params1, HTTP.UTF_8));
	                  String TAG="yf";
					Log.i(TAG, params1.toString());
	                  //得到响应
	                  HttpResponse response = httpClient.execute(httpRequst);
	                  
	                  Log.i(TAG, "3");
	                  Log.i(TAG,String.valueOf(response.getStatusLine().getStatusCode()));
	                 
	                  //返回值如果为200的话则证明成功的得到了数据
	                  if(response.getStatusLine().getStatusCode() == 200)
	                  {
	                	  Log.i(TAG, "2");
	                	  try{ 
	                            //将得到的数据进行解析
	                            BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	                            for(String s =buffer.readLine(); s!= null; s = buffer.readLine())
	                            {
	                                      builder.append(s);
	                                      
	                            } 
	                            Log.i(TAG, builder.toString());
	                            return builder.toString();
	                	  	}catch (Exception e){
	  	                          e.printStackTrace();
	  	                        return "";
	                	  	}
	                 }
	        }catch (Exception e){
	                       e.printStackTrace();
	                       return "";
	       }
	        return "";	}

	public static NewsList getNewsList_en(String url,NowUser user) throws IOException, JSONException {
		
		return NewsList.parse(new JSONArray(http_getjob_en(url,user)));
	}

	private static String http_getjob_en(String url, NowUser user) {
		// TODO Auto-generated method stub
		 HttpClient httpClient = new DefaultHttpClient();
	        StringBuilder builder = new StringBuilder(); 
	        //这里是你与服务器交互的地址
	        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
	        
	        //设置读取超时
	        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
	        HttpPost httpRequst = new HttpPost(url);

	        //准备传输的数据
	        List<BasicNameValuePair> params1 = new ArrayList<BasicNameValuePair>();
	        
	        
	        params1.add(new BasicNameValuePair("user_id",user.getUserName()));


	        
	     
	        try
	        {

	                  //发送请求
	        	   
	                  httpRequst.setEntity(new UrlEncodedFormEntity(params1, HTTP.UTF_8));
	                  String TAG="yf";
					Log.i(TAG, params1.toString());
	                  //得到响应
	                  HttpResponse response = httpClient.execute(httpRequst);
	                  
	                  Log.i(TAG, "3");
	                  Log.i(TAG,String.valueOf(response.getStatusLine().getStatusCode()));
	                 
	                  //返回值如果为200的话则证明成功的得到了数据
	                  if(response.getStatusLine().getStatusCode() == 200)
	                  {
	                	  Log.i(TAG, "2");
	                	  try{ 
	                            //将得到的数据进行解析
	                            BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	                            for(String s =buffer.readLine(); s!= null; s = buffer.readLine())
	                            {
	                                      builder.append(s);
	                                      
	                            } 
	                            Log.i(TAG, builder.toString());
	                            return builder.toString();
	                	  	}catch (Exception e){
	  	                          e.printStackTrace();
	  	                        return "";
	                	  	}
	                 }
	        }catch (Exception e){
	                       e.printStackTrace();
	                       return "";
	       }
	        return "";

	}

	public static Boolean changepass(String user_id, String j_id, String status) {
		// TODO Auto-generated method stub
		return http_changepass(user_id, j_id, status);
	}

	private static Boolean http_changepass(String user_id, String j_id,
			String status) {
		
		 HttpClient httpClient = new DefaultHttpClient();
	        StringBuilder builder = new StringBuilder(); 
	        //这里是你与服务器交互的地址
	        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
	        
	        //设置读取超时
	        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
	        HttpPost httpRequst = new HttpPost(Url.Url_HrPass);

	        //准备传输的数据
	        List<BasicNameValuePair> params1 = new ArrayList<BasicNameValuePair>();
	
	        params1.add(new BasicNameValuePair("user_id",String.valueOf(user_id)));
	        params1.add(new BasicNameValuePair("j_id",String.valueOf(j_id)));
	        params1.add(new BasicNameValuePair("status",String.valueOf(status)));

	        
	        try
	        {

	                  //发送请求
	        	   
	                  httpRequst.setEntity(new UrlEncodedFormEntity(params1, HTTP.UTF_8));
	                  String TAG="yf";
					Log.i(TAG, params1.toString());
	                  //得到响应
	                  HttpResponse response = httpClient.execute(httpRequst);
	                  
	                  Log.i(TAG, "3");
	                  Log.i(TAG,String.valueOf(response.getStatusLine().getStatusCode()));
	                 
	                  //返回值如果为200的话则证明成功的得到了数据
	                  if(response.getStatusLine().getStatusCode() == 200)
	                  {
	                	  Log.i(TAG, "2");
	                	  try{ 
	                            //将得到的数据进行解析
	                            BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	                            for(String s =buffer.readLine(); s!= null; s = buffer.readLine())
	                            {
	                                      builder.append(s);
	                                      
	                            } 
	                            Log.i("yf", "2"+builder.toString());
	                            if(builder.toString().equals("right"))

	  	                        return true;

	
	                	  	}catch (Exception e){
	                		  e.printStackTrace();
	                		  return false;
	                	  	}
	                  }
	               
	        }catch (Exception e){
		  e.printStackTrace();
		  return false;
	  	}
			return false;
	}

	public static int getstatus(int r_id, int j_id) {
		// TODO Auto-generated method stub
		return http_getstatus(r_id,j_id);
	}
	private static int http_getstatus(int r_id,int j_id) {
		// TODO Auto-generated method stub
		 HttpClient httpClient = new DefaultHttpClient();
	        StringBuilder builder = new StringBuilder(); 
	        //这里是你与服务器交互的地址
	        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
	        url=Url.Url_Search_Status;
	        //设置读取超时
	        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
	        HttpPost httpRequst = new HttpPost(url);

	        //准备传输的数据
	        List<BasicNameValuePair> params1 = new ArrayList<BasicNameValuePair>();
	        Log.i("yf","r_id"+String.valueOf(j_id));
	        Log.i("yf","j_id"+String.valueOf(r_id));
	        params1.add(new BasicNameValuePair("r_id",String.valueOf(r_id)));
	        params1.add(new BasicNameValuePair("j_id",String.valueOf(j_id)));
	        
	        try
	        {

	                  //发送请求
	        	   
	                  httpRequst.setEntity(new UrlEncodedFormEntity(params1, HTTP.UTF_8));

	                  //得到响应
	                  HttpResponse response = httpClient.execute(httpRequst);

	                  Log.i(TAG,String.valueOf(response.getStatusLine().getStatusCode()));
	                 
	                  //返回值如果为200的话则证明成功的得到了数据
	                  if(response.getStatusLine().getStatusCode() == 200)
	                  {
	                	  try{ 
	                            //将得到的数据进行解析
	                            BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	                            for(String s =buffer.readLine(); s!= null; s = buffer.readLine())
	                            {
	                                      builder.append(s);
	                                      
	                            } 
	                            System.out.println("A_flag"+builder.toString());
	                            JSONObject jsonObj = new JSONObject(builder.toString());
                       
                                 //通过得到键值对的方式得到值
                                
                                int flag = jsonObj.getInt("status");
                                System.out.println("A_flag"+flag);

	  	                        return flag;

	
	                	  	}catch (Exception e){
	                		  e.printStackTrace();
	                		  return 3;
	                	  	}
	                  }
	               
	        }catch (Exception e){
   		  e.printStackTrace();
   		  return 3;
   	  	}
			return 3;
		
	}

	public static NewsList getAllList() {
		// TODO Auto-generated method stub
		try {
			return NewsList.parse(new JSONArray(http_get1(Url.Url_Search_News)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}



}
