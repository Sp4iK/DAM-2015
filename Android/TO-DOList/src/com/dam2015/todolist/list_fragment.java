package com.dam2015.todolist;

import java.util.ArrayList;

import com.dam2015.todolist.MainActivity.ListFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class list_fragment extends Fragment implements ListFragment {

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

	public void receiveItem (String text) {
		aList.add(0, text);
		adapter.notifyDataSetChanged();
	}

}
