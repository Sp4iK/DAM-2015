package com.dam2015.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentMenu extends Fragment {

	Class <? extends Activity> viewName;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_menu, container);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		Button btnHome = (Button) getActivity().findViewById(R.id.btnHomeFragMenu);
		Button btnData = (Button) getActivity().findViewById(R.id.btnDataFragMenu);
		Button btnHelp = (Button) getActivity().findViewById(R.id.btnHelpFragMenu);

		viewName = getActivity().getClass();

		btnHome.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(viewName.equals(MainActivity.class)) {
					MainActivity.loadFragmentsOnFrame(R.id.fragmentContainerMain, new FragmentHome(), true);
				} else {
					SecondActivity.loadFragmentsOnFrame(R.id.fragmentContainerSecond, new FragmentHome(), true);
				}
			}
		});

		btnData.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(viewName.equals(MainActivity.class)) {
					MainActivity.loadFragmentsOnFrame(R.id.fragmentContainerMain, new FragmentData(), true);
				} else {
					SecondActivity.loadFragmentsOnFrame(R.id.fragmentContainerSecond, new FragmentData(), true);
				}
			}
		});

		btnHelp.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(viewName.equals(MainActivity.class)) {
					MainActivity.loadFragmentsOnFrame(R.id.fragmentContainerMain, new FragmentHelp(), true);
				} else {
					SecondActivity.loadFragmentsOnFrame(R.id.fragmentContainerSecond, new FragmentHelp(), true);
				}
			}
		});
	}

}
