package com.dam2015.persistence;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		TextView lblDatosSecAct = (TextView) this.findViewById(R.id.lblDatosSecAct);

		String datoRecuperado = MainActivity.sharedPrefs.getString("dato", null);
		
		lblDatosSecAct.setText(datoRecuperado);
	}

}
