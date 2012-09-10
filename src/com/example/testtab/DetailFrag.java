package com.example.testtab;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;


public class DetailFrag extends Fragment {
	public Button button_back;
	public CheckBox cb;
	public BaseItem b;
	public int n=0;
	public String str;
	public void sendmsg(BaseItem b)
	{
		if(b!=null)
		{
			n++;
			Log.d("XXX",b.image);
		}
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {	
        View view = inflater.inflate(R.layout.detail, container, false);
        button_back =(Button) view.findViewById(R.id.button1);
        cb = (CheckBox)view.findViewById(R.id.checkBox1);
        
  
        Log.d("XXX","**"+Integer.toString(n)+" "+str);
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
