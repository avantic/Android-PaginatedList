package net.avantic.paginatedList.adapter;

import net.avantic.paginatedList.ListFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.DisplayMetrics;

public class PagerAdapter extends FragmentStatePagerAdapter  {
	
    protected DisplayMetrics displayMetrics;
	
	
    public PagerAdapter(FragmentManager fm, DisplayMetrics displayMetrics) {
        super(fm);
        this.displayMetrics = displayMetrics;
    }

    @Override
    public Fragment getItem(int position) {
        return new ListFragment(position);
    }

    @Override
    public int getCount() {
        return ListFragment.getTotalPages(displayMetrics);
    }
    
}