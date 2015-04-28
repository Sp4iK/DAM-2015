package com.dam2015.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class FragDiag extends DialogFragment {
	
	public static FragDiag newInstance() {
		FragDiag fragDiag = new FragDiag();
		return fragDiag;
	}
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
		alertDialog.setTitle("FRAGMENT ALERT DIALOG");
		alertDialog.setMessage("Ésto es un cuadro de diálogo de tipo alerta dentro de un fragment.");

		return alertDialog.create();
	}
	
}
