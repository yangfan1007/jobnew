package com.job.bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import com.job.app.NowUser;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

public class UserBean {
    protected static final String TAG = "yf";
	private int id ;
    private int flag;
    private String userName;
    private String password ;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserBean id=" + id + ", flag=" + flag + ", userName="
				+ userName + ", password=" + password + "]";
	}
	/**
	 * ��application NowUserת��ΪUserBean
	 * @return
	 */
	public UserBean changeUser(NowUser nowUser){
		UserBean user = null;
		user.setId(nowUser.getId());
		user.setPassword(nowUser.getPassword());
		user.setFlag(nowUser.getFlag());
		user.setUserName(nowUser.getUserName());
		return user;
		
	}

}
	/**
	 * δʵ��
	 * �����û���¼
	 * ͨ��������ʷ����û���Ϣ
	 * @param name
	 * @param passwd
	 * @return userbean
	 */
/*    public static UserBean getUser(String  userName,String passwd){
    	 HttpClient httpClient = new DefaultHttpClient();
         StringBuilder builder = new StringBuilder(); 
         //��������������������ĵ�ַ
         String validateUrl = "http://121.42.178.137/server.php";
         
         //���ö�ȡ��ʱ
         httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
         
         //���ö�ȡ��ʱ
         httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
         
         HttpPost httpRequst = new HttpPost(validateUrl);

         //׼�����������
         List<NameValuePair> params1 = new ArrayList<NameValuePair>();
         
         
         params1.add(new BasicNameValuePair("user", userName));
         params1.add(new BasicNameValuePair("password", passwd));
         Log.i(TAG, params1.toString());
    	  //AsyncTask<Void,Void,UserBean> ATgetTask = new AsyncTask<Void, Void,UserBean >() {


		//	protected UserBean doInBackground(Void... params) {
               
                try
                {

                          //��������
                          httpRequst.setEntity(new UrlEncodedFormEntity(params1, HTTP.UTF_8));
                         
                          //�õ���Ӧ
                          HttpResponse response = httpClient.execute(httpRequst);
                          
                          Log.i(TAG, "3");
                          //����ֵ���Ϊ200�Ļ���֤���ɹ��ĵõ�������
                         
                          if(response.getStatusLine().getStatusCode() == 200)
                          {
                        	  Log.i(TAG, "2");

                        	  
                                    
                                    //���õ������ݽ��н���
                                    BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                                    
                                   
                                    	
                                    for(String s =buffer.readLine(); s!= null; s = buffer.readLine())
                                    {
                                              builder.append(s);
                                              
                                    }
                                    
                                   

                                    if(builder.toString() != null){
                                    //�õ�Json����
                                    JSONObject jsonObj = new JSONObject(builder.toString());
                                  
                                    Log.i(TAG, "123");
                                    
                                    //ͨ���õ���ֵ�Եķ�ʽ�õ�ֵ
                                   int id = jsonObj.getInt("user_id");
                                   int flag = jsonObj.getInt("user_flag");
                                   String userName = jsonObj.getString("user_name");
                                   String password = jsonObj.getString("user_password");
                                   Log.i(TAG, "��ȡ������ ");
                                   
                                   Log.i(TAG, "RequestResult:"+userName);
                                   
                                   Log.i(TAG, "UserName:"+password);
                               	//toast("id"+id);
                                   //���߳����ж��Ƿ�õ��ɹ��ӷ������õ�����
                                  
                           
                          }else{
                        	  Log.e(TAG, "���ӳ�ʱ ");
                          }
                          }  
                } catch (Exception e)
                {

                          e.printStackTrace();
                          Log.e(TAG, "������� ");
                          Log.e(TAG, e.getMessage());
                 
                }
				return null;
                
            
				
			}
	//		 protected void onPostExecute(UserBean user){
				 
			// }
    	//  };
//		return null;
    }
    
//}
*/