package com.example.testtab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
/**
 * SOme reason the only way to get this nasty thing working is to use this class seperately to generate and keep track\
 * of the menu items possible in the scrolling sidebar. 0.o 
 *  retardness... testfragment -> testfragmentadapter
 * @author WINDAdmin
 *
 */
class TestFragmentAdapter extends FragmentPagerAdapter {
    protected static final String[] CONTENT = new String[] { "Starters", "Indian", "Main Course", "Desserts"};
    protected static final int[] XML = {R.xml.list_a,R.xml.list_b,R.xml.list_c,R.xml.list_d};
    private int mCount = CONTENT.length;

    public TestFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return TestFragment.newInstance(CONTENT[position % CONTENT.length],XML[position]);
    }

    @Override
    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        if (count > 0 && count <= 10) {
            mCount = count;
            notifyDataSetChanged();
        }
    }
}