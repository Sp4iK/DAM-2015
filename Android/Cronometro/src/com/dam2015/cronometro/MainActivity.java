package com.dam2015.cronometro;

import android.support.v7.app.ActionBarActivity;
import com.dam2015.cronometro.Cronometro;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	TextView lblCrono;
	Cronometro crono = new Cronometro();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lblCrono = (TextView) this.findViewById(R.id.lblCrono);
		Button btnIniciar = (Button) this.findViewById(R.id.btnIniciar);
		Button btnParar = (Button) this.findViewById(R.id.btnParar);

		btnIniciar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				iniciarCrono();
			}
		});

		btnParar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				pararCrono();
			}
		});

		crono.setUpdateListener(this);
	}

	private void iniciarCrono() {
		Intent intent = new Intent(this, Cronometro.class);
		startService(intent);
	}

	public void actualizarCronometro (Double crono) {
		lblCrono.setText(crono.toString());
	}

	private void pararCrono() {
		Intent intent = new Intent(this, Cronometro.class);
		stopService(intent);
	}

}
