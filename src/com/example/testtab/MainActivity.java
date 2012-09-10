package com.example.testtab;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Window;


import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.TitlePageIndicator.IndicatorStyle;
/**
 * This is the launcher activity page.
 * we extend BaseSampleActivity which extends FragmentActivity.
 * Base Sample Activity has some code for the menu's only and seems redundant.
 * @author WINDAdmin
 *
 */
public class MainActivity extends BaseSampleActivity implements TestFragment.fragListener {
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
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.right_frag_container); 
        
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
    
	@Override
	public void onItemClick(XmlResourceParser xmlItem) {
		// TODO Auto-generated method stub
		//xmlItem.getAttributeValue(null,"title");
		//Toast.makeText(MainActivity.this  ,xmlItem.getAttributeValue(null,"title"), Toast.LENGTH_LONG).show();
		FragmentManager fm = getSupportFragmentManager();
        MainFrag f = (MainFrag)fm.findFragmentById(R.id.right_frag_container);
		f.update(xmlItem);
	}
}