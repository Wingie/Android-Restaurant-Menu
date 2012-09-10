package com.example.testtab;


import java.io.IOException;
import java.io.InputStream;

import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * This fragment is for the RightSideBar.
 * bloody v4 compat library wasted 30seconds of my life
 * @author WINDAdmin
 *
 */
public class MainFrag extends Fragment {
	Button button;
	ImageView imview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mainpagefrag, container, false);

        button = (Button) view.findViewById(R.id.fragment_button);
        imview = (ImageView) view.findViewById(R.id.test_image);
        button.setOnClickListener(new OnClickListener() {
        
            @Override
            public void onClick(View v) {
                
            		Log.d("com.example.texttab",Integer.toString(imview.getWidth()));
                    Toast.makeText(getActivity(),"some string goes here", Toast.LENGTH_LONG).show();
              
            }
            
        });
        
        return view;
    }

    public void update(XmlResourceParser xmlItem)
    {
    	//Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    	//imview.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadeout));
    	String s = xmlItem.getAttributeValue(null,"pic");
    	loadDataFromAsset(s);
    	//button.setText(s);
    	imview.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));
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
	    	button.setText("OK");
	    	
    	}
    	catch(IOException ex) {
    		button.setText("ERRROR?");
    		return;
    	}
    
    }
}