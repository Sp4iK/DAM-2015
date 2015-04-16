package com.dam2015.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Action: " + intent.getAction(), Toast.LENGTH_SHORT).show();
		if (intent.getAction().equals("SCREEN_ON")) {
			System.out.println("Screen is on!");
		} else if (intent.getAction().equals("SCREEN_OFF")) {
			System.out.println("Screen is off!");
		}
	}

}
