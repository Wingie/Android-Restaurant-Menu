package com.example.testtab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.testtab.ActionItem;
import com.example.testtab.QuickAction;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


/**
 * 
 * so basically this class is for the second page or the listing of all selected items for the user
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
	public Button button_back,button_menu;
	public ListView lv;
	ArrayList<BaseItem> myitemlist;
	public int n=0;
	public String strng="default";
	private SimpleAdapter arrayAdapter;
	List<Map<String, String>> items;
	delListener mCallback;
	numListener numCallback;
	private static final int ID_UP     = 1;
	private static final int ID_DOWN   = 2;
	
	 // Container Activity must implement these interfaces
		// this listener is to inform the parent activity that an item has been deleted from the menu
	    public interface delListener {
	        public void onDelClick(int i);
	    }
	    
		// this listener is to inform the parent activity that an item's number 
	    public interface numListener {
	        public void onNumChange(int i,int p);
	    }
	    
	    @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        try {
	            mCallback = (delListener) activity;
	            numCallback = (numListener) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString()
	                    + " must implement Listeners!!");
	        }
	    }
	
	    // 2 constructsors 
	public DetailFrag()
	{
		
	}
	
	DetailFrag(ArrayList<BaseItem> s)
	{
		myitemlist=s;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// first prepare the list view to accept the items
        final View view = inflater.inflate(R.layout.detail, container, false);
        button_back =(Button) view.findViewById(R.id.button1);
        
        
        button_menu =(Button) view.findViewById(R.id.button2);
        ActionItem nextItem 	= new ActionItem(ID_DOWN, "Next", getResources().getDrawable(R.drawable.menu_down_arrow));
		ActionItem prevItem 	= new ActionItem(ID_UP, "Prev", getResources().getDrawable(R.drawable.menu_up_arrow));
        
		prevItem.setSticky(true);
        nextItem.setSticky(true);
        final QuickAction quickAction = new QuickAction(getActivity(), QuickAction.VERTICAL);
		
		//add action items into QuickAction
        quickAction.addActionItem(nextItem);
		quickAction.addActionItem(prevItem);
        
		//Set listener for action item clicked
				quickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {			
					@Override
					public void onItemClick(QuickAction source, int pos, int actionId) {				
						ActionItem actionItem = quickAction.getActionItem(pos);
		                 
						//here we can filter which action item was clicked with pos or actionId parameter
						if (actionId == ID_UP) {
							Toast.makeText(getActivity(), "SOme action can be customized here.", Toast.LENGTH_SHORT).show();
						} else if (actionId == ID_DOWN) {
							Toast.makeText(getActivity(), "Clear/Menu/Call Waiter/ anything/..", Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(getActivity(), actionItem.getTitle() + " selected", Toast.LENGTH_SHORT).show();
						}
					}
				});
		
        
        lv = (ListView)view.findViewById(R.id.listview1);
        
        String[] arr1 = new String[myitemlist.size()];
        String[] arr2 = new String[myitemlist.size()];
        String[] arr3 = new String[myitemlist.size()];
        for(int i=0;i<myitemlist.size();i++)
        {	
        	arr1[i]=myitemlist.get(i).title;
        	arr2[i]=myitemlist.get(i).price;
        	arr3[i]="Qty: "+Integer.toString(myitemlist.get(i).num);
        }
        
        String[] from = new String[] { "str" , "price","numbs"};
    	int[] to = new int[] { R.id.textp1,R.id.textp2,R.id.textp3};
    	items =  new ArrayList<Map<String, String>>();

    	for ( int i = 0; i < arr1.length; i++ )
    	{
    	    Map<String, String> map = new HashMap<String, String>();
    	    map.put( "str", String.format( "%s", arr1[i] ) );
    	    map.put( "price", String.format( "%s", arr2[i] ) );
    	    map.put("numbs", String.format( "%s", arr3[i] ));
    	    items.add( map );
    	}
    	
    	arrayAdapter = new SimpleAdapter( getActivity(), items,R.layout.menulist, from, to );

        lv.setAdapter(arrayAdapter);
       
        // this is the single click listener.. :D inside it lies 
       lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View mview,
                    final int position, long id) {
            	Context mContext = getActivity();
            	LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            	View layout = inflater.inflate(R.layout.dialog,(ViewGroup) mview.findViewById(R.id.layout_root));
            	final NumberPicker np = (NumberPicker) layout.findViewById(R.id.numberPicker1);
                np.setMaxValue(10);
                np.setMinValue(1);
                np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
                
            	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            	builder.setView(layout);
            	builder.setCancelable(true);
            	//builder.setIcon(R.drawable.dialog_question);
            	builder.setTitle("Change Number of items.");
            	builder.setInverseBackgroundForced(true);
            	// this is the dialog element that implements the changing number shz
            	builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            	  @Override
            	  public void onClick(DialogInterface dialog, int which) {
            		  
            		  numCallback.onNumChange(np.getValue(),position);
            		  Map<String, String> mss = items.get(position);
            		  mss.put("numbs", String.format( "Qty: %s", Integer.toString(np.getValue()) ));
            		  items.set(position, mss);
            		  arrayAdapter.notifyDataSetChanged();
            		  setbill(view);
            	  }
            	});
            	
            	AlertDialog alert = builder.create();
            	alert.show();

            	
                }
              });
       
       // long click to delete code
       lv.setLongClickable(true);
       lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
    	    @Override
    	    public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
    	    //	Toast.makeText(getActivity(), Integer.toString(pos), Toast.LENGTH_SHORT).show();
    	    	items.remove(pos);
    	    	myitemlist.remove(pos);
    	    	arrayAdapter.notifyDataSetChanged();
    	    	setbill(view);
    	    	mCallback.onDelClick(pos);
    	        return true;
    	    }
    	});

        // GTF back
        button_back.setOnClickListener(new OnClickListener() {
        
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
            
        });
        
     // GTF back
        button_menu.setOnClickListener(new OnClickListener() {
        
            @Override
            public void onClick(View v) {
            	quickAction.show(v);
            }
            
        });
        setbill(view);
        
        
        
        return view;
	}
	
	public void setbill(View view)
	{
		TextView child = (TextView)view.findViewById(R.id.bill_amount);
//        /Log.d();
        int sum = 0;
        for(int i=0;i<myitemlist.size();i++)
        {
        	int p = Integer.parseInt(myitemlist.get(i).price);
        	sum+= myitemlist.get(i).num*p;
        }
        if(child!=null){
        	child.setText(Integer.toString(sum));
        	Log.d("XXX","fdfdffdfdfdf");
        }
        	
	}
}
