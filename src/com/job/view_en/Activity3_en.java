package com.job.view_en; 

import com.example.job.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Activity3_en extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		View parentView = inflater.inflate(R.layout.en_me, container, false);
		View iresumemanagement= parentView.findViewById(R.id.resumemanagement);
		iresumemanagement.setOnClickListener(new View.OnClickListener(){ 
		    @Override 
		    public void onClick(View v){ 
		    	Intent i1 = new Intent(getActivity(),resumemanagement_en.class);

		          ////Æô¶¯ 
		          startActivity(i1);
		    } 
		});

		View ifeedback= parentView.findViewById(R.id.feedback);
		ifeedback.setOnClickListener(new View.OnClickListener(){ 
		    @Override 
		    public void onClick(View v){ 
		    	Intent i3= new Intent(getActivity(),feedback_en.class);

		          ////Æô¶¯ 
		          startActivity(i3);
		    } 
		});
		View imoresetting= parentView.findViewById(R.id.moresetting);
		imoresetting.setOnClickListener(new View.OnClickListener(){ 
		    @Override 
		    public void onClick(View v){ 
		    	Intent i4= new Intent(getActivity(),moresetting_en.class);

		          ////Æô¶¯ 
		          startActivity(i4);
		    } 
		});
		
		 return parentView;
	}
}

