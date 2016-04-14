package com.job.view;
import com.job.view_en.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.job.R;
import com.job.app.NowUser;
import com.job.bean.JobBean;
import com.job.bean.UserBean;
import com.job.util.Url;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * TODO : LoginActivity.java--��¼
 * 
 * @author yangfan
 * 
 */
public class LoginActivity extends Activity implements OnClickListener {
	private static final String TAG="yf";
	private Button btnLogin;
	private Button btnReg;
	private EditText etUsername;
	private EditText etPassword;
	private String username;
	private String password;
	private int log=0;
	private Button btnfpw;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.login);
		btnLogin = (Button) findViewById(R.id.btn_login);
		btnReg = (Button) findViewById(R.id.btn_register);
		btnfpw = (Button) findViewById(R.id.btn_findpasswd);
		etUsername = (EditText) findViewById(R.id.et_username);
		etPassword = (EditText) findViewById(R.id.et_password);
		btnLogin.setOnClickListener(this);
		btnReg.setOnClickListener(this);
		btnfpw.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		// ��½
		case R.id.btn_login:
			username = etUsername.getText().toString();
			password = etPassword.getText().toString();
			if (username.equals("") || password.equals("")) {
				toast("��������ȷ���˺ź�����");
				break;
			} else {
				toast("��֤��...");
			AsyncTask<Void,Void,UserBean> ATgetTask = new AsyncTask<Void, Void, UserBean>() {

					@Override
					protected UserBean doInBackground(Void... params) {
						// TODO Auto-generated method stub
						
						return SendRequest(username,password);
					}
					protected void onPostExecute(UserBean userBean){
						if(userBean!=null){
							System.out.println(String.valueOf(userBean.getFlag()));
							if(userBean.getFlag()==0){
								Intent toHome = new Intent(LoginActivity.this,
	    								MainActivity.class);
								NowUser myword = (NowUser) getApplication();
								myword.setId(userBean.getId());
								myword.setFlag(userBean.getFlag());
								myword.setUserName(userBean.getUserName());
								myword.setPassword(userBean.getPassword());
								toast("�û���");
	    						startActivity(toHome);
	    						finish();
							}else if(userBean.getFlag()==1){
								Intent toHome = new Intent(LoginActivity.this,
	    								com.job.view_en.MainActivity_en.class);
								NowUser myword = (NowUser) getApplication();
								myword.setId(userBean.getId());
								myword.setFlag(userBean.getFlag());
								myword.setUserName(userBean.getUserName());
								myword.setPassword(userBean.getPassword());
								toast("��ҵ��");
	    						startActivity(toHome);
	    						finish();
							}else{
								toast("��¼����");
							}

						}else{
							
							new AlertDialog.Builder(LoginActivity.this)  
				      		   
   		                   .setTitle("����")
   		   
   		                   .setMessage("�˺Ż���������������µ�¼")
   		   
   		                   .setPositiveButton("ȷ��", null)
   		   
   		                   .show();

						}
					}				
				};
				ATgetTask.execute();
			}
			
			break;
			

		case R.id.btn_register:
			Intent toReg = new Intent(LoginActivity.this,
					RegisterActivity.class);
			startActivity(toReg);
			break;
		case R.id.btn_findpasswd:
			Intent tofp = new Intent(LoginActivity.this,
					Findpw.class);
			startActivity(tofp);
			break;
		default:
			break;

		}
	}

	    	/*				
	    						Intent toHome = new Intent(LoginActivity.this,
	    								MainActivity.class);
	    						startActivity(toHome);
	    						finish();
*/
	public void toast(String toast) {
		// TODO Auto-generated method stub
		  Toast toast1 = Toast.makeText(getApplicationContext(), toast,  
                  Toast.LENGTH_LONG); 
		  toast1.setDuration(1);
		  toast1.show();
		  
	};
	  public  UserBean SendRequest(String username, String password)
      {
                UserBean result = null;
                
                
                HttpClient httpClient = new DefaultHttpClient();
                StringBuilder builder = new StringBuilder(); 
                //��������������������ĵ�ַ
                String validateUrl = Url.Url_Login;
                
                //���ö�ȡ��ʱ
                httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
                
                //���ö�ȡ��ʱ
                httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
                
                HttpPost httpRequst = new HttpPost(validateUrl);

                //׼�����������
                List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
                
                
                params.add(new BasicNameValuePair("user", username));
                params.add(new BasicNameValuePair("password", password));
                Log.i(TAG, params.toString());
                try
                {

                          //��������
                          httpRequst.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                         
                          //�õ���Ӧ
                          HttpResponse response = httpClient.execute(httpRequst);
                          
                          Log.i(TAG, "3");
                          //����ֵ���Ϊ200�Ļ���֤���ɹ��ĵõ�������
                         
                          if(response.getStatusLine().getStatusCode() == 200)
                          {
                        	  Log.i(TAG, "2");

                        	  
                                    
                                    //���õ������ݽ��н���
                                    BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                                    
                                
                                   
                                    	  
                                 
                                              builder.append(buffer.readLine());
                                              
                                    
                                     Log.i(TAG, "builder"+builder.toString());
                                   
                                 
                                 	UserBean userBean=new UserBean();
                                    //�õ�Json����
                                   JSONObject jsonObj = new JSONObject(builder.toString());
                                   Log.i(TAG, "builder"+jsonObj.getInt("user_id"));
                                    //ͨ���õ���ֵ�Եķ�ʽ�õ�ֵ
                                   Log.i(TAG, "builder"+jsonObj.getInt("user_flag"));
                                   userBean.setId(jsonObj.getInt("user_id"));
                                   Log.i(TAG, "builder"+userBean.getId());
                                   userBean.setFlag( jsonObj.getInt("user_flag"));
                                   userBean.setUserName(jsonObj.getString("user_name"));
                                   userBean.setPassword(jsonObj.getString("user_password"));
                                   Log.i(TAG, "��ȡ������ ");
                                   Log.i(TAG, "builder"+jsonObj.getInt("user_flag"));
                                   Log.i(TAG, "RequestResult:"+userBean.getUserName());
                                   
 
                               	//toast("id"+id);
                                   //���߳����ж��Ƿ�õ��ɹ��ӷ������õ�����
                                   result = userBean;

                          }else{
                        	  Log.e(TAG, "���ӳ�ʱ ");
                          }
                          
                } catch (Exception e)
                {
                	e.printStackTrace();
                	
                }
                
                return result;
                
      }


}


