package com.dam2015.todolistwithfragments;

import com.dam2015.todolistwithfragments.top_fragment.addItemListener;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity implements addItemListener {

	public interface ListFragment {
		public void receiveItem (String item);
	}

	private ListFragment listFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//listFragment = (ListFragment) this;
	}

	public void addItem (String text) {
		System.out.println("MainActivity - addItemListener | item: " + text);

		listFragment.receiveItem(text);
	}

}
