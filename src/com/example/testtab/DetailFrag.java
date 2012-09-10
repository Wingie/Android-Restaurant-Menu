package com.example.testtab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.util.Log;
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
                
            	//Log.d("com.example.texttab",Integer.toString(imview.getWidth()));
               
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = fm.findFragmentById(R.id.right_frag_container); 
                Log.d("com.example.testtab",fragment.toString());
               
               // FragmentTransaction ft = fm.beginTransaction();
                fm.popBackStack();
               // ft.replace(R.id.right_frag_container, new DetailFrag());
               // ft.addToBackStack(null);
            //    ft.commit();

            }
            
        });
        
        return view;
	}
}
