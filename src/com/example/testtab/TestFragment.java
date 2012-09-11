package com.example.testtab;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * the LEFT side panel implementation.
 * reads an XML file and quietly does some shit.
 * yeah!
 * @author WINDAdmin
 *
 */
public final class TestFragment extends Fragment {
    private static final String KEY_CONTENT = "TestFragment:Content";
    static TextView tv;
    fragListener mCallback;
    
 // Container Activity must implement this interface
    public interface fragListener {
        public void onItemClick(XmlResourceParser abc);
    }
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (fragListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
    
    /** this function is called to create a new instance of this SAME class, then this class's view function can be 
     *  called in return for the actual styling implementation.
     * @param content
     * @return
     */
    public static TestFragment newInstance(String content, int pos) {
        TestFragment fragment = new TestFragment();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            builder.append(content);
        }
        builder.deleteCharAt(builder.length() - 1);
        fragment.mContent = builder.toString();
        fragment.xml_select = pos;
        return fragment;
    }

    private String mContent = "???";
    private int xml_select=0;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getString(KEY_CONTENT);
        }
    }
    
    private String[] lv_arr_tit = {};
    private String[] lv_arr_pric = {};
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	//construct the list view that would create the scrolling Menu 
    	ListView v = new ListView( getActivity() );
    	ArrayList<String> listTitle;
    	ArrayList<String> listPrice;
    	
    	
    	listTitle = PrepareListFromXml("title",xml_select);
    	listPrice = PrepareListFromXml("price",xml_select);  	
    	
    	lv_arr_tit = (String[]) listTitle.toArray(new String[0]);
    	lv_arr_pric = (String[]) listPrice.toArray(new String[0]);
    	/*v.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_2, lv_arr));*/
    	
    	String[] from = new String[] { "str" , "price"};
    	int[] to = new int[] { android.R.id.text1 ,android.R.id.text2 };
    	List<Map<String, String>> items =  new ArrayList<Map<String, String>>();
    	
    	for ( int i = 0; i < lv_arr_tit.length; i++ )
    	{
    	    Map<String, String> map = new HashMap<String, String>();
    	    map.put( "str", String.format( "%s", lv_arr_tit[i] ) );
    	    map.put( "price", String.format( "%s", lv_arr_pric[i] ) );
    	    items.add( map );
    	}
    	SimpleAdapter adapter = new SimpleAdapter( getActivity(), items,android.R.layout.simple_list_item_2, from, to );
    	
    	v.setAdapter( adapter );
    	     
        v.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
            	XmlResourceParser abc = findXML(position,xml_select);
               // Send the event to the host activity
                  mCallback.onItemClick(abc);
                }
              });
        
        return v;
        
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CONTENT, mContent);
    }
    //    /.getAttributeValue(null,"id"); XmlResourceParser
    public XmlResourceParser findXML(int val, int xml) {
		XmlResourceParser itemname = null;
		
		XmlResourceParser todolistXml = getResources().getXml(xml);
		int eventType = -1;
		while (eventType != XmlResourceParser.END_DOCUMENT) {
			if (eventType == XmlResourceParser.START_TAG) {
				String strNode = todolistXml.getName();
				if (strNode.equals("item")) {
					itemname = todolistXml;;
					if(Integer.parseInt(itemname.getAttributeValue(null,"id"))==val){
						//Toast.makeText(getActivity(),Integer.toString(val), Toast.LENGTH_LONG).show();
						//Log.d("com.example.testtab",todolistXml.getAttributeValue(null, "id"));
						return itemname;
					}
				}
			
			}
			try {
				eventType = todolistXml.next();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}


	public ArrayList<String> PrepareListFromXml(String val, int xml) {
		ArrayList<String> itemname = new ArrayList<String>();
		
		XmlResourceParser todolistXml = getResources().getXml(xml);

		int eventType = -1;
		while (eventType != XmlResourceParser.END_DOCUMENT) {
			if (eventType == XmlResourceParser.START_TAG) {
				String strNode = todolistXml.getName();
				if (strNode.equals("item")) {
					itemname.add(todolistXml.getAttributeValue(null, val));
				}
			
			}

			try {
				eventType = todolistXml.next();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return itemname;
	}
}


/*
 * This bunch of code is how to manually add shit to the fragment view class.
 * TextView text = new TextView(getActivity());
text.setGravity(Gravity.CENTER);
text.setText(mContent);
text.setTextSize(20 * getResources().getDisplayMetrics().density);
text.setPadding(20, 20, 20, 20);

LinearLayout layout = new LinearLayout(getActivity());
layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
layout.setGravity(Gravity.CENTER);
layout.addView(text);
return layout;*/

/* View myFragmentView = inflater.inflate(R.layout.fragment_layout, container, false);
tv=(TextView)myFragmentView.findViewById(R.id.tv);
tv.setText(mContent);
return myFragmentView;*/

/*String[] from = new String[] { "str" };
int[] to = new int[] { android.R.id.text1 };
List<Map<String, String>> items =  new ArrayList<Map<String, String>>();
for ( int i = 0; i < 20; i++ )
{
    Map<String, String> map = new HashMap<String, String>();
    map.put( "str", String.format( "%s %d", mContent ,i + 1 ) );
    items.add( map );
}
SimpleAdapter adapter = new SimpleAdapter( getActivity(), items,android.R.layout.simple_list_item_1, from, to );

v.setAdapter( adapter );*/