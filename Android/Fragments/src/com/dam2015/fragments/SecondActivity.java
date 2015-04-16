package com.dam2015.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SecondActivity extends Activity {

	static FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		int id = getIntent().getIntExtra("fragmentId", 0);

		fragmentManager = getFragmentManager();

		loadFragmentsOnFrame(R.id.fragmentContainerSecond, fragmentManager.findFragmentById(id), false);
	}

	public static void loadFragmentsOnFrame(int id, Fragment fragment, Boolean replace) {

		if(replace) {
			fragmentManager.beginTransaction().replace(id, fragment).commit();
		} else {
			fragmentManager.beginTransaction().add(id, fragment).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
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
