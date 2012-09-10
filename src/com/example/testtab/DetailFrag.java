package com.example.testtab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DetailFrag extends Fragment {
	Button button_back;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {	
        View view = inflater.inflate(R.layout.detail, container, false);
        button_back =(Button) view.findViewById(R.id.button1);
        
        button_back.setOnClickListener(new OnClickListener() {
        
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
            
        });
        
        return view;
	}
}
