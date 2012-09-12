package com.example.testtab;


import java.io.IOException;
import java.io.InputStream;


import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * This fragment is for the RightSideBar.
 * bloody v4 compat library wasted 30seconds of my life
 * @author WINDAdmin
 *
 */
public class MainFrag extends Fragment  {
	private Button button_add;
	private ImageView imview;
	private TextView tv,tv_descrip;
	//DetailFrag det_frag = new DetailFrag();
	
	private callListener mCallback;
	private Button button_view;
    
	 // Container Activity must implement this interface
	    public interface callListener {
	        public void onButtonClick(BaseItem abc);
	    }
	    
	    @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        try {
	            mCallback = (callListener) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString()
	                    + " must implement OnHeadlineSelectedListener");
	        }
	    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mainpagefrag, container, false);

        button_add = (Button) view.findViewById(R.id.fragment_button_center);
        button_view = (Button) view.findViewById(R.id.fragment_button_left);
        
        imview = (ImageView) view.findViewById(R.id.test_image);
        tv = (TextView) view.findViewById(R.id.title);
        tv_descrip = (TextView) view.findViewById(R.id.description);
        button_add.setOnClickListener(new OnClickListener() {
        	
        	/**
        	 * This function is getting called whenever the button on the right side is called.
        	 * a new fragment is created and  the xml resource of the currently selected item sent to it..
        	 */
            @Override
            public void onClick(View v) {
                   /* FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.right_frag_container, det_frag);
                    ft.addToBackStack(null);
                    ft.commit(); */
            	if(item!=null)
            		mCallback.onButtonClick(item);
                    
            }
            
        });
        
        button_view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            		mCallback.onButtonClick(null);
                    
            }
            
        });
        
        return view;
    }
    //public XmlResourceParser xItem;
    public BaseItem item=null;
    public void update(XmlResourceParser xmlItem)
    {
    	//Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    	//imview.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadeout));
    	
    	String s = xmlItem.getAttributeValue(null,"pic");
    	String t = xmlItem.getAttributeValue(null,"title");
    	String d = xmlItem.getAttributeValue(null,"description");
    	String p = xmlItem.getAttributeValue(null,"price");
    	item = new BaseItem(s,t,d,p);
    	loadDataFromAsset(s);
    	
    	tv.setText(t);
    	tv_descrip.setText(d);
    	//button.setText(s);
    	imview.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));
    	tv.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_left));
    }
    
    public void loadDataFromAsset(String s) {
    	// load image
    	try {
	    	// get input stream
	    	InputStream ims = getActivity().getAssets().open(s);
	    	// load image as Drawable
	    	Drawable d = Drawable.createFromStream(ims, null);
	    	// set image to ImageView
	    	imview.setImageDrawable(d);
	    	//button.setText("OK");
	    	
    	}
    	catch(IOException ex) {
    		button_add.setText("ERRROR?");
    		return;
    	}
    
    }
}