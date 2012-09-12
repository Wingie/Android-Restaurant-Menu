package com.example.testtab;

import java.util.ArrayList;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;

import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.TitlePageIndicator.IndicatorStyle;
/**
 * This is the launcher activity page.
 * we extend BaseSampleActivity which extends FragmentActivity.
 * Base Sample Activity has some code for the menu's only and seems redundant.
 * @author WINDAdmin
 *
 */
public class MainActivity extends BaseSampleActivity implements TestFragment.fragListener,MainFrag.callListener,DetailFrag.delListener {
	FragmentManager fm;
	Fragment fragment;
	ArrayList<BaseItem> myitemlist = new ArrayList<BaseItem>();
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(0);
        setContentView(R.layout.main_activity);
        
	       
        // this gets the swiping menu fragment set up and running
        mAdapter = new TestTitleFragmentAdapter(getSupportFragmentManager());
        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        TitlePageIndicator indicator = (TitlePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        indicator.setFooterIndicatorStyle(IndicatorStyle.Triangle);
        mIndicator = indicator;
        
        
        // this sets up the right bar fragment and connects MainFrag to it.
        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.right_frag_container); 
        
        if (fragment == null) {

            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.right_frag_container, new MainFrag());
            ft.commit(); 
                               
        }
        
            
        //in case a listener is required that activates on every page change. This listener can send the messages to the rightFrag
        mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //Toast.makeText(MainActivity.this, "Changed to page " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
	
	/***
	 * This click is a receiver for an implementation in the left_PAGER.
	 * it receives a nice XML item that works properly.
	 * everything is cool.
	 */
	MainFrag f;
	@Override
	public void onItemClick(XmlResourceParser xmlItem) {
		FragmentManager fm = getSupportFragmentManager();
		if(fm.getBackStackEntryCount()==0){
	        f = (MainFrag)fm.findFragmentById(R.id.right_frag_container);
		}
		else{
			fm.popBackStack();
			fm.executePendingTransactions();
		}  
		
		f.update(xmlItem);
	}
	
	/**
	 * This interface is a fuckhead that switches the right fragment activity with the details fragment.
	 * fucking acts like a dickhead and wont work nicely with the XMLparser
	 * so because im lazy (stfu), halfway through i created (for lack of a fucking better name) a baseitem class
	 * to handle the fucking menudata that this fucking SDK required me to fucking bob around like a fucking basketball..
	 * fuck javadoc sucks. 
	 */
	@Override
	public void onButtonClick(BaseItem item) {
		// TODO Auto-generated method stub
		myitemlist.add(item);
		
	//	Log.d("XXX",item.title);
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.right_frag_container, new DetailFrag(myitemlist));
		ft.addToBackStack(null);
		ft.commit();
	}

	/**
	 * This interface is coming from the delete fragment and is supposed to handle the deletion of the listiew i
	 */
	@Override
	public void onDelClick(int i) {
		// TODO Auto-generated method stub
		//Toast.makeText(MainActivity.this, Integer.toString(i), Toast.LENGTH_SHORT).show();
		myitemlist.remove(i);
	}
	
	
	
}