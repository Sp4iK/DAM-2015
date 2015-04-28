package com.dam2015.notifications;

import com.dam2015.notifications.R;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btnNotification = (Button) this.findViewById(R.id.btnNotification);
		Button btnCancelNotification = (Button) this.findViewById(R.id.btnCancelNotification);
		
		final Notification.Builder notificationBuilder = new Notification.Builder(MainActivity.this);
		
		final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		final int NOTIFICATION_REF = 1;
		
		btnNotification.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				notificationBuilder.setSmallIcon(android.R.drawable.ic_notification_overlay)
					.setTicker("Notification")
					.setContentTitle("NOTIFICATIONS")
					.setWhen(System.currentTimeMillis())
					.setLights(Color.RED, 0, 1);
			
				Notification notification = notificationBuilder.getNotification();
				
				notificationManager.notify(NOTIFICATION_REF, notification);
			}
		});
		
		btnCancelNotification.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				notificationManager.cancel(NOTIFICATION_REF);
			}
		});
	}

}
