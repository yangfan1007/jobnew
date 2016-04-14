package com.job.view; 

import com.example.job.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Activity3 extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		View parentView = inflater.inflate(R.layout.me, container, false);
		View iresumemanagement= parentView.findViewById(R.id.resumemanagement);
		iresumemanagement.setOnClickListener(new View.OnClickListener(){ 
		    @Override 
		    public void onClick(View v){ 
		    	Intent i1 = new Intent(getActivity(),resumemanagement.class);

		          ////启动 
		          startActivity(i1);
		    } 
		});
		View icollection= parentView.findViewById(R.id. collection);
		icollection.setOnClickListener(new View.OnClickListener(){ 
		    @Override 
		    public void onClick(View v){ 
		    	Intent i2 = new Intent(getActivity(),collection.class);

		          ////启动 
		          startActivity(i2);
		    } 
		});
		View ifeedback= parentView.findViewById(R.id.feedback);
		ifeedback.setOnClickListener(new View.OnClickListener(){ 
		    @Override 
		    public void onClick(View v){ 
		    	Intent i3= new Intent(getActivity(),feedback.class);

		          ////启动 
		          startActivity(i3);
		    } 
		});
		View imoresetting= parentView.findViewById(R.id.moresetting);
		imoresetting.setOnClickListener(new View.OnClickListener(){ 
		    @Override 
		    public void onClick(View v){ 
		    	Intent i4= new Intent(getActivity(),moresetting.class);

		          ////启动 
		          startActivity(i4);
		    } 
		});
		
		 return parentView;
	}
}

