package com.dam2015.todolistwithfragments;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	public interface ListFragment {
		public void receiveItem (String item);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public static void addItem (String text) {
		list_fragment.receiveItem(text);
	}

}
