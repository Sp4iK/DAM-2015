package com.dam2015.todolistwithfragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class top_fragment extends Fragment {

	TextView textToAdd;
	Button btnAdd;

	// Called once the Fragment has been created in order for it to create its user interface.
	@Override
	public View onCreateView(LayoutInflater	inflater, ViewGroup	container, Bundle savedInstanceState) {
		//	Create,	or	inflate	the	Fragment's	UI,	and	return	it.
		//	If	this	Fragment	has	no	UI	then	return	null.
		return	inflater.inflate(R.layout.top_fragment, container,	false);
	}

	public void onActivityCreated (Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		textToAdd = (TextView) getActivity().findViewById(R.id.editText);
		btnAdd = (Button) getActivity().findViewById(R.id.buttonAdd);
		addEventListeners();
	}

	private void addEventListeners() {
		btnAdd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String text = textToAdd.getText().toString();
				textToAdd.setText("");

				System.out.println("top_fragment - onClick | text: " + text);

				MainActivity.addItem(text);
			}
		});
	}

}
