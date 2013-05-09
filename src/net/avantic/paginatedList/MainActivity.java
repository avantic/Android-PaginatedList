package net.avantic.paginatedList;

import net.avantic.paginatedList.adapter.PagerAdapter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends FragmentActivity implements OnPageChangeListener, OnClickListener {

	private ViewPager pager;
	
	private RadioGroup radioGroup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pager = (ViewPager) findViewById(R.id.pager);
		PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), getResources().getDisplayMetrics());
		pager.setAdapter(pagerAdapter);
		pager.setOnPageChangeListener(this);
		
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		updateViewRadioGroup();
	}
	
	private void updateViewRadioGroup() {
		radioGroup.removeAllViews();
		
		int pagerCount = pager.getAdapter().getCount();
		if (pagerCount == 1)
			return;
		
		for(int i = 0; i < pagerCount; i++)
			radioGroup.addView(getRadioButton(i));
	}
	
	private RadioButton getRadioButton(int i) {
		RadioButton radioButton = new RadioButton(getApplicationContext());
		radioButton.setOnClickListener(this);
		radioButton.setId(i);
		if (i == 0)
			radioButton.setChecked(true);
		
		return radioButton;
	}
	
	@Override
	public void onPageScrollStateChanged(int state) {
		radioGroup.setVisibility(View.VISIBLE);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		radioGroup.setVisibility(View.INVISIBLE);
	}
	
	@Override
	public void onPageSelected(int position) {
		RadioButton radioButton = (RadioButton) radioGroup.getChildAt(position);
		radioButton.setChecked(true);
	}
	
	@Override
	public void onClick(View view) {
		pager.setCurrentItem(view.getId());
	}

}
