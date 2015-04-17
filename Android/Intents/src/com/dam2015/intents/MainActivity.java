package com.dam2015.intents;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	static final int SECOND_ACTIVITY = 1;
	long datoDevuelto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btnOK = (Button) this.findViewById(R.id.btnToSecondAct);
		final Intent intent = new Intent(MainActivity.this, SecondActivity.class);

		btnOK.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivityForResult(intent, SECOND_ACTIVITY);
			}
		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode,	resultCode,	data);

		switch (requestCode) {
			case (SECOND_ACTIVITY):
				if (resultCode == Activity.RESULT_OK) {datoDevuelto = data.getLongExtra("result", 0);}
				Toast.makeText(this, "Valor devuelto: "+datoDevuelto, Toast.LENGTH_LONG).show();
				break;
			default:
				break;
		}

	}

}
