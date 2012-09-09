package com.example.testtab;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * This fragment is for the RightSideBar.
 * bloody v4 compat library wasted 30seconds of my life
 * @author WINDAdmin
 *
 */
public class MainFrag extends Fragment {
	Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mainpagefrag, container, false);

        button = (Button) view.findViewById(R.id.fragment_button);
        button.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                
                    Toast.makeText(getActivity(),"some string goes here", Toast.LENGTH_LONG).show();
              
            }
            
        });
        
        return view;
    }

    public void updatebutt(CharSequence str)
    {
    	Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    	button.setText(str);
    }
    
}