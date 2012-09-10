package com.example.testtab;


import java.io.IOException;
import java.io.InputStream;

import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This fragment is for the RightSideBar.
 * bloody v4 compat library wasted 30seconds of my life
 * @author WINDAdmin
 *
 */
public class MainFrag extends Fragment {
	Button button_add;
	ImageView imview;
	TextView tv,tv_descrip;
	static DetailFrag det_frag = new DetailFrag();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mainpagefrag, container, false);

        button_add = (Button) view.findViewById(R.id.fragment_button3);
        
        
        imview = (ImageView) view.findViewById(R.id.test_image);
        tv = (TextView) view.findViewById(R.id.title);
        tv_descrip = (TextView) view.findViewById(R.id.description);
        button_add.setOnClickListener(new OnClickListener() {
        
            @Override
            public void onClick(View v) {
                
            		//Log.d("com.example.texttab",Integer.toString(imview.getWidth()));
                    Toast.makeText(getActivity(),"some string goes here", Toast.LENGTH_LONG).show();
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    Fragment fragment = fm.findFragmentById(R.id.right_frag_container); 
                    Log.d("com.example.testtab",fragment.toString());
                   
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.right_frag_container, det_frag);
                    ft.addToBackStack(null);
                    ft.commit(); 

            }
            
        });
        
        
        
        return view;
    }

    public void update(XmlResourceParser xmlItem)
    {
    	//Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    	//imview.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadeout));
    	String s = xmlItem.getAttributeValue(null,"pic");
    	String t = xmlItem.getAttributeValue(null,"title");
    	String d = xmlItem.getAttributeValue(null,"description");
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