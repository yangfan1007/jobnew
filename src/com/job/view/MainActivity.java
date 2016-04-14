package com.job.view;


import com.example.job.R;
import com.job.bean.UserBean;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.TabSpec;

public class MainActivity extends FragmentActivity {

	// FragmentTabHost
	private FragmentTabHost mTabHost;
	private RadioGroup mTabRg;

	private final Class[] fragments = { Activity1.class, Activity2.class,
			Activity3.class};
	private UserBean user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		Intent intent = this.getIntent(); 
		user=(UserBean)intent.getSerializableExtra("user");
	/*	Intent it = new Intent(action1);
        it.putExtra("url", et.getText().toString()); 
        sendBroadcast(it);*/

		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		int count = fragments.length;
		for (int i = 0; i < count; i++) {
			
			TabSpec tabSpec = mTabHost.newTabSpec(i + "").setIndicator(i + "");
			
			mTabHost.addTab(tabSpec, fragments[i], null);
		}

		mTabRg = (RadioGroup) findViewById(R.id.main_radio);
		mTabRg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.mainTabs_radio_home:
					mTabHost.setCurrentTab(0);
					break;
				case R.id.mainTabs_radio_msg:
					mTabHost.setCurrentTab(1);

					break;
				case R.id.mainTabs_radio_selfInfo:

					mTabHost.setCurrentTab(2);
					break;

				default:
					break;
				}
			}
		});

		mTabHost.setCurrentTab(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}




}
