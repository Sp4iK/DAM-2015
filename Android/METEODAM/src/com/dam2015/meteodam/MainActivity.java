package com.dam2015.meteodam;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	AlarmManager alarmManager;
	SharedPreferences sharedPrefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		boolean fromNotification = getIntent().getBooleanExtra("from_notification", false);
		
		createTabs();
		
		if (fromNotification) {
			NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
			notificationManager.cancel("weather_notification", 1);
		}
		
		alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
	}
	
	private void createTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		Tab tabData = actionBar.newTab();
		Tab tabMap = actionBar.newTab();
		
		tabData.setText(R.string.tabData)
			   .setIcon(android.R.drawable.ic_menu_info_details)
			   .setTabListener(new TabListener<tabData>(this, R.id.fragmentContainer, tabData.class));
		
		tabMap.setText(R.string.tabMap)
		  	  .setIcon(android.R.drawable.ic_menu_mapmode)
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
		int id = item.getItemId();
		
		if (id == R.id.action_settings) {
			final Dialog dialog = new Dialog(MainActivity.this);
			dialog.setTitle("PREFERENCIAS");
			dialog.setContentView(R.layout.preferences);
			dialog.setCancelable(false);
			dialog.show();
			
			final Switch switchUpdates = (Switch) dialog.findViewById(R.id.switchUpdates);
			final TextView txtInterval = (TextView) dialog.findViewById(R.id.txtInterval);
			Button btnPrefsOK = (Button) dialog.findViewById(R.id.btnPrefsOK);
			
			sharedPrefs = getSharedPreferences("preferencias", Activity.MODE_PRIVATE);
			
			switchUpdates.setChecked(sharedPrefs.getBoolean("alarmOn", false));
			txtInterval.setEnabled(switchUpdates.isChecked());
			txtInterval.setText(Long.toString(sharedPrefs.getLong("interval", 0)));
			
			
			
			btnPrefsOK.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					SharedPreferences.Editor editor = sharedPrefs.edit();
					
					if(switchUpdates.isChecked()) {
						long interval = Long.parseLong((txtInterval.getText().toString()));
						
						setAlarm(interval);

						editor.putBoolean("alarmOn", true);
						editor.putLong("interval", interval);
						
					} else {
						Intent intentToFire = new Intent("android.intent.action.WEATHER_UPDATE");
						PendingIntent alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intentToFire, 0);
						alarmManager.cancel(alarmIntent);
						
						editor.putBoolean("alarmOn", false);
					}
					
					editor.apply();
					dialog.hide();
				}
			});
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void setAlarm(long interval) {
		Intent intentToFire = new Intent("android.intent.action.WEATHER_UPDATE");
		PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 0, intentToFire, 0);	
	
		//alarmManager.set(AlarmManager.ELAPSED_REALTIME, interval*60*1000, alarmIntent);
		alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, interval*60*1000, interval*60*1000, alarmIntent);
	}
}
