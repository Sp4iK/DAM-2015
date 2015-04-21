package com.dam2015.alarmas;

import android.support.v7.app.ActionBarActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btnSetAlarm = (Button) this.findViewById(R.id.btnSetAlarm);
		Button btnSetRepAlarm = (Button) this.findViewById(R.id.btnSetRepAlarm);
		
		btnSetAlarm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setAlarm();
			}
		});
		
		btnSetRepAlarm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setInexactRepeatingAlarm();
			}
		});
	}
	
	private void setAlarm() {	

		//	Get	a	reference	to	the	Alarm	Manager	
		AlarmManager alarmManager =	(AlarmManager)getSystemService(Context.ALARM_SERVICE);	

		//	Set	the	alarm	to	wake	the	device	if	sleeping.	
		int	alarmType = AlarmManager.ELAPSED_REALTIME_WAKEUP;	

		//	Trigger	the	device	in	10	seconds.	
		long timeOrLengthofWait = 10000;	

		//	Create	a	Pending	Intent	that	will	broadcast	and	action	
		String ALARM_ACTION = "ALARM_ACTION";	
		Intent intentToFire = new Intent(ALARM_ACTION);	
		PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 0, intentToFire, 0);	

		//	Set	the	alarm	
		alarmManager.set(alarmType,	timeOrLengthofWait,	alarmIntent);	
		alarmManager.cancel(alarmIntent);	
	}
	
	private void setInexactRepeatingAlarm() {	

		//Get	a	reference	to	the	Alarm	Manager	
		AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);	

		//Set	the	alarm	to	wake	the	device	if	sleeping.	
		int alarmType = AlarmManager.ELAPSED_REALTIME_WAKEUP;

		//Schedule	the	alarm	to	repeat	every	half	hour.	
		long timeOrLengthofWait = AlarmManager.INTERVAL_HALF_HOUR;	

		//Create	a	Pending	Intent	that	will	broadcast	and	action	
		String ALARM_ACTION = "ALARM_ACTION";	
		Intent intentToFire = new Intent(ALARM_ACTION);	
		PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 0, intentToFire, 0);	

		//Wake	up	the	device	to	fire	an	alarm	in	half	an	hour,	and	every		
		//half-hour	after	that.	
		alarmManager.setInexactRepeating(alarmType,	timeOrLengthofWait, timeOrLengthofWait, alarmIntent);
	}

}
