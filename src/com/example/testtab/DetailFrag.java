package com.example.testtab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.testtab.TestFragment.fragListener;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


/**
 * 
 * so basically his class is for the second page or the listing of all selected items for the user
 * thought i could keep an instance of this fragment statically bound to the parent class 
 * but that was bat shit crazy with some wierd garbagecollector madness and some nullpointer exceptions that were not cool
 * so a fucking asshole of an implementation was done, as to why there is a fucking ArrayList that will be populated everytime
 * this class gets fucking created and this bitch will have to copy that fucking arraylist to a string array before it can attach it to the
 * fucking listview class...
 * why? because fuck you thats why.
 * @author WINDAdmin
 *
 */
public class DetailFrag extends Fragment {
	public Button button_back;
	public ListView lv;
	ArrayList<BaseItem> myitemlist;
	public int n=0;
	public String strng="default";
	private SimpleAdapter arrayAdapter;
	List<Map<String, String>> items;
	delListener mCallback;
    
	 // Container Activity must implement this interface
	    public interface delListener {
	        public void onDelClick(int i);
	    }
	    
	    @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        try {
	            mCallback = (delListener) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString()
	                    + " must implement OnHeadlineSelectedListener");
	        }
	    }
	
	public DetailFrag()
	{
		
	}
	
	DetailFrag(ArrayList<BaseItem> s)
	{
		myitemlist=s;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {	
        View view = inflater.inflate(R.layout.detail, container, false);
        button_back =(Button) view.findViewById(R.id.button1);
        lv = (ListView)view.findViewById(R.id.listview1);
     
     //   cb.setText(myitemlist.get(0).title);
        String[] arr1 = new String[myitemlist.size()];//myitemlist.toArray(new String[0]);
        String[] arr2 = new String[myitemlist.size()];
        for(int i=0;i<myitemlist.size();i++)
        {	
        	Log.d("XXX",Integer.toString(i)+myitemlist.get(i).title);
        	arr1[i]=myitemlist.get(i).title;
        	arr2[i]=myitemlist.get(i).price;
        }
        
        String[] from = new String[] { "str" , "price"};
    	int[] to = new int[] { R.id.textp1,R.id.textp2 };
    	items =  new ArrayList<Map<String, String>>();

    	for ( int i = 0; i < arr1.length; i++ )
    	{
    	    Map<String, String> map = new HashMap<String, String>();
    	    map.put( "str", String.format( "%s", arr1[i] ) );
    	    map.put( "price", String.format( "%s", arr2[i] ) );
    	    items.add( map );
    	}
    	
    	
//      /  arrayAdapter = new ArrayAdapter<Object>(getActivity(), android.R.layout.simple_list_item_1, arr2);
    	arrayAdapter = new SimpleAdapter( getActivity(), items,R.layout.menulist, from, to );

        lv.setAdapter(arrayAdapter);
        
       //Button imv =(Button) view.findViewById(R.id.butt01);
      
       lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
            	TextView tv_l = (TextView)view.findViewById(R.id.textp1);
            	Toast.makeText(getActivity(), tv_l.getText(), Toast.LENGTH_SHORT).show();
                }
              });
       
       lv.setLongClickable(true);
       lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
    	    @Override
    	    public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
    	    //	Toast.makeText(getActivity(), Integer.toString(pos), Toast.LENGTH_SHORT).show();
    	    	items.remove(pos);
    	    	arrayAdapter.notifyDataSetChanged();
    	    	mCallback.onDelClick(pos);
    	        return true;
    	    }
    	});

        
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
