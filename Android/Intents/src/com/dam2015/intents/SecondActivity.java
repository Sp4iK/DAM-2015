package com.dam2015.intents;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity);

		final TextView txtDato = (TextView) this.findViewById(R.id.txtDato);
		Button btnOK = (Button) this.findViewById(R.id.btnOKSecAct);

		btnOK.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				long valor = Long.parseLong(txtDato.getText().toString());

				final Intent result = new Intent();
				result.putExtra("result", valor);

				setResult(RESULT_OK, result);
				finish();
			}
		});
	}



}
