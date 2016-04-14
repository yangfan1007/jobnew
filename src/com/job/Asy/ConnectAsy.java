package com.job.Asy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.job.R;
import com.job.adpater.NewsApater;
import com.job.bean.JobBean;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class ConnectAsy extends AsyncTask<Integer,Integer,String>   {
	JobBean[] job = new JobBean[50];
	private static final String TAG = "yf";

	@Override
	protected String doInBackground(Integer... params) {
        HttpClient httpClient = new DefaultHttpClient();
        StringBuilder builder = new StringBuilder(); 
        //这里是你与服务器交互的地址
        String validateUrl = "http://121.42.178.137/sjob.php";
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
                            
                            if(builder.toString() != null){
                            //得到Json对象
                            	 Log.i(TAG, builder.toString());
                            JSONArray jsonArr = new JSONArray(builder.toString());
                            int joblength = jsonArr.length();
                            Log.i(TAG,String.valueOf(joblength));
                            boolean result;
							if (joblength > 0) { 
                            for (int i = 1; i < joblength; i++) {
                          
                                JSONObject jsonObj= jsonArr.getJSONObject(i);
                                Log.i(TAG,jsonArr.toString());
                          
							job[i] = new JobBean();
                         
                            //通过得到键值对的方式得到值   
                           job[i].setName(jsonObj.getString("j_name"));
                           job[i].setProfession(jsonObj.getString("j_profession"));
                           job[i].setRequire(jsonObj.getString("j_require"));
                           job[i].setId(jsonObj.getInt("j_id"));
                           job[i].setAddress(jsonObj.getString("j_address"));
                           job[i].setTp(jsonObj.getInt("j_tp"));
                           Log.i(TAG,"12345");
                           job[i].setCompany(jsonObj.getString("j_company"));
                           Log.i(TAG, "读取到数据 ");
                           
                            }

                           result = true;
                        }else 
                        {
                        	result  = false;
                        }
                            }
                  } catch (Exception e)
	                {
	                          e.printStackTrace();
	                          Log.e(TAG, "请求错误 ");
	                          Log.e(TAG, e.getMessage());  
	                }
                  }
		
        	} catch (Exception e) { 
        		e.printStackTrace(); 
        	} 
		return job.toString();
	}

	 protected void onPostExecute(String result) {//任务执行完后执行
/*		 listView=(ListView)parentView.findViewById(R.id.fragment_news_listview);
         NewsAapter mnewlist = new NewsAapter(getActivity(), map);getActivity(), null, getActivity(), null, null);
         listView.setAdapter(mnewlist);
		 HashMap<Object, Object> map = new HashMap<Object, Object>();
			map.put("name", job[1].getName());
			map.put("price", job[1].getProfession());
			map.put("price", job[1].getCompany());*/
			
			//mnewlist.add(map);
     }

}
