package com.example.testtab;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class DetailFrag extends Fragment {
	public Button button_back;
	public ListView lv;
	ArrayList<BaseItem> myitemlist;
	public int n=0;
	public String strng="default";
	private ArrayAdapter arrayAdapter;
	
	DetailFrag(ArrayList<BaseItem> s)
	{
		myitemlist=s;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {	
        View view = inflater.inflate(R.layout.detail, container, false);
        button_back =(Button) view.findViewById(R.id.button1);
        lv = (ListView)view.findViewById(R.id.listview1);
     
     //   cb.setText(myitemlist.get(0).title);
        String[] arr = new String[myitemlist.size()];//myitemlist.toArray(new String[0]);
        for(int i=0;i<myitemlist.size();i++)
        {	
        	Log.d("XXX",Integer.toString(i)+myitemlist.get(i).title);
        	arr[i]=myitemlist.get(i).title;
        }
        
        
        arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arr);

        lv.setAdapter(arrayAdapter);
        
        
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
