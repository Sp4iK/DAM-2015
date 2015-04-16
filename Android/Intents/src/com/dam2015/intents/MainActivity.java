package com.dam2015.intents;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	static final int SHOW_CARSELECT = 1;
	long selectedCar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btnOK = (Button) this.findViewById(R.id.btnToSecondAct);
		final Intent intent = new Intent(MainActivity.this, SecondActivity.class);

		btnOK.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivityForResult(intent, SHOW_CARSELECT);
			}
		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode,	resultCode,	data);

		System.out.println("requestCode: "+requestCode+" | resultCode: "+resultCode+" | data: "+data);

		switch (requestCode) {
			case (SHOW_CARSELECT):
				if (resultCode == Activity.RESULT_OK) {selectedCar = data.getLongExtra("result", 0);}
				System.out.println("selectedCar: "+selectedCar);
				break;
			default:
				break;
		}

	}

}
