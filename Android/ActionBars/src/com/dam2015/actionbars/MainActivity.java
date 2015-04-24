package com.dam2015.actionbars;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		Tab tabOne = actionBar.newTab();
		Tab tabTwo = actionBar.newTab();
		
		tabOne.setText("TAB 1")
			  .setContentDescription("This is the first tab")
			  .setIcon(android.R.drawable.star_on)
			  .setTabListener(new TabListener<tabOneFrag>(this, R.id.fragmentContainer, tabOneFrag.class));
		
		tabTwo.setText("TAB 2")
		  .setContentDescription("This is the second tab")
		  .setIcon(android.R.drawable.star_on)
		  .setTabListener(new TabListener<tabTwoFrag>(this, R.id.fragmentContainer, tabTwoFrag.class));
		
		actionBar.addTab(tabOne);
		actionBar.addTab(tabTwo);
	}

}
