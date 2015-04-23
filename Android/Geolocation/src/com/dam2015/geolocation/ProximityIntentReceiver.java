package com.dam2015.geolocation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.widget.Toast;

public class ProximityIntentReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Boolean entering = intent.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING, false);

		if (entering) {
			System.out.println("Has entrado en la zona marcada!");
			Toast.makeText(context, "Has entrado en la zona marcada!", Toast.LENGTH_LONG).show();
		}
	}

}
