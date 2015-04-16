package com.dam2015.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
			Toast.makeText(context, "Device is charging!", Toast.LENGTH_SHORT).show();
		} else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
			Toast.makeText(context, "Device is discharging!", Toast.LENGTH_SHORT).show();
		}
	}

}
