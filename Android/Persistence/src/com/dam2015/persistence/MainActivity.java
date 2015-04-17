package com.dam2015.persistence;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final TextView txtDato = (TextView) this.findViewById(R.id.txtDatosMainAct);
		Button btnGO = (Button) this.findViewById(R.id.btnGO);
		Button btnPrefs = (Button) this.findViewById(R.id.btnPrefs);

		final Intent intent = new Intent(this, SecondActivity.class);
		final Intent prefsIntent = new Intent(this, PrefsActivity.class);

		btnGO.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String dato = txtDato.getText().toString();

				SharedPreferences sharedPrefs = getSharedPreferences("preferencias", Activity.MODE_PRIVATE);

				SharedPreferences.Editor editor = sharedPrefs.edit();

				editor.putString("dato", dato);
				editor.apply();

				startActivity(intent);
			}
		});

		btnPrefs.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivityForResult(prefsIntent, 1);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
