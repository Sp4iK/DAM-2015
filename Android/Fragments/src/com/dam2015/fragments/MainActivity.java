package com.dam2015.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	static FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Intent intent = new Intent(MainActivity.this, SecondActivity.class);

		Button btn = (Button) findViewById(R.id.btn1MainActivity);

		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				loadOtherActivity(intent);
			}
		});

		fragmentManager = getFragmentManager();

		loadFragmentsOnFrame(R.id.fragmentContainerMain, new FragmentHome(), false);
	}

	private void loadOtherActivity (Intent intent) {

		Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainerMain);

		intent.putExtra("fragmentId", fragment.getId());

		startActivity(intent);
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
