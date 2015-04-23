package com.dam2015.mediaplayer;

import android.support.v7.app.ActionBarActivity;
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

		Button btnStart = (Button) this.findViewById(R.id.btnStart);
		Button btnStop = (Button) this.findViewById(R.id.btnStop);

		btnStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startService();
			}
		});

		btnStop.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				stopService();
			}
		});
	}

	private void startService() {
		Intent intent = new Intent(this, ForegroundService.class);
		intent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
		startService(intent);
	}

	private void stopService() {
		Intent intent = new Intent(this, ForegroundService.class);
		intent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
		startService(intent);
	}

}
