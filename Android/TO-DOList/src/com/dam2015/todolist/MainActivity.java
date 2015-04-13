package com.dam2015.todolist;

import java.util.ArrayList;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView textToAdd;
	Button btnAdd;
	ListView list;
	ArrayAdapter adapter;
	ArrayList aList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textToAdd = (TextView) findViewById(R.id.editText);
		btnAdd = (Button) findViewById(R.id.buttonAdd);
		list = (ListView) findViewById(R.id.listView);
		adapter =  new ArrayAdapter(this, android.R.layout.simple_list_item_1, aList);
		list.setAdapter(adapter);
	}

	private void addEventListeners() {
		btnAdd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String text = textToAdd.getText().toString();
				textToAdd.setText("");

				addItem(text);
			}
		});
	}

	private void addItem (String text) {
		aList.add(0, text);
		adapter.notifyDataSetChanged();
	}
}
