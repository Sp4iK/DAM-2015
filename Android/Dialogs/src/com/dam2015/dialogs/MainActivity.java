package com.dam2015.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
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
		
		Button btnDiag = (Button) this.findViewById(R.id.btnDiag);
		Button btnAlertDiag = (Button) this.findViewById(R.id.btnAlertDiag);
		Button btnFragDiag = (Button) this.findViewById(R.id.btnFragDiag);
		
		btnDiag.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Dialog dialog = new Dialog(MainActivity.this);
				dialog.setTitle("DIALOG");
				dialog.setContentView(R.layout.dialog);
				dialog.show();
			}
		});
		
		btnAlertDiag.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				creaAlertDialog();
			}
		});
		
		btnFragDiag.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				loadFragDiag();
			}
		});
	}

	private void creaAlertDialog() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle("ALERT DIALOG");
		alertDialog.setMessage("Ésto es un cuadro de diálogo de tipo alerta.");
		
		alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		
		alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		
		// Para que no se cierre al clicar fuera.
		alertDialog.setCancelable(false);
		
		alertDialog.show();
	}
	
	private void loadFragDiag() {
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().add(R.layout.frag_diag, new FragDiag()).commit();
	}
	
}
