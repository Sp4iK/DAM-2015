package com.dam2015.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class FragDiag extends DialogFragment {
	
	public static FragDiag newInstance() {
		FragDiag fragDiag = new FragDiag();
		return fragDiag;
	}
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
		View dlgView = layoutInflater.inflate(R.layout.frag_diag, null);
		
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
		alertDialog.setTitle("FRAGMENT DIALOG");
		//alertDialog.setView(R.layout.frag_diag);
		alertDialog.setMessage("Ésto es un cuadro de diálogo de tipo fragment.");

		return alertDialog.create();
	}
	
}
