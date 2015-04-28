package com.dam2015.meteodam;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		createTabs();
		
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().add(R.id.detailFragContainer, new DetailFragment()).commit();
		
//		final TextView txtLocalization = (TextView) this.findViewById(R.id.txtLocalization);
//		Button btnOK = (Button) this.findViewById(R.id.btnOK);
//		
//		btnOK.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				String loc = (String) txtLocalization.getText();
//			}
//		});
	}
	
	private void createTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		Tab tabData = actionBar.newTab();
		Tab tabMap = actionBar.newTab();
		
		tabData.setText(R.string.tabData)
			  .setIcon(android.R.drawable.star_on)
			  .setTabListener(new TabListener<tabData>(this, R.id.fragmentContainer, tabData.class));
		
		tabMap.setText(R.string.tabMap)
		  .setIcon(android.R.drawable.star_on)
		  .setTabListener(new TabListener<tabMap>(this, R.id.fragmentContainer, tabMap.class));
		
		actionBar.addTab(tabData);
		actionBar.addTab(tabMap);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
