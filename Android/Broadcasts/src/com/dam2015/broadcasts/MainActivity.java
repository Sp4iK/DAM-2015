package com.dam2015.broadcasts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView lblScreenOnData;
	TextView lblScreenOffData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lblScreenOnData = (TextView) this.findViewById(R.id.lblScreenOnData);
		lblScreenOffData = (TextView) this.findViewById(R.id.lblScreenOffData);
	}

}
