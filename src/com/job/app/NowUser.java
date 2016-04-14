package com.job.app;

import android.app.Application;

public class NowUser extends Application {
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
}
