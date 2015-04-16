package com.dam2015.intents;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity);

		Button btnOK = (Button) this.findViewById(R.id.btnOKSecAct);

		long valor = 1;

		final Intent result = new Intent();
		result.putExtra("result", valor);

		btnOK.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				setResult(RESULT_OK, result);
				finish();
			}
		});
	}



}
