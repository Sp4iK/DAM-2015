package com.dam2015.todolistwithfragments;

import java.util.ArrayList;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class list_fragment extends Fragment {

	ListView list;
	static ArrayAdapter adapter;
	static ArrayList aList;

	//	Called	once	the	Fragment	has	been	created	in	order	for	it	to	create	its	user	interface.
	@Override
	public View onCreateView(LayoutInflater	inflater, ViewGroup	container, Bundle savedInstanceState) {
		//	Create,	or	inflate	the	Fragment's	UI,	and	return	it.
		//	If	this	Fragment	has	no	UI	then	return	null.
		return	inflater.inflate(R.layout.list_fragment, container,	false);
	}

	public void onActivityCreated (Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		list = (ListView) getActivity().findViewById(R.id.listView);
		aList = new ArrayList();
		adapter =  new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, aList);
		list.setAdapter(adapter);
	}

	public static void receiveItem (String text) {
		aList.add(0, text);
		adapter.notifyDataSetChanged();
	}

}
