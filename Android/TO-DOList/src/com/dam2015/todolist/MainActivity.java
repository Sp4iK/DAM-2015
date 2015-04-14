package com.dam2015.todolist;

import com.dam2015.todolist.top_fragment.addItemListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements addItemListener {

	public interface ListFragment {
		public void receiveItem (String item);
	}

	private ListFragment listFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//listFragment = (ListFragment) MainActivity.this;
	}

	public void addItem (String text) {
		System.out.println("MainActivity - addItemListener | item: " + text);

		listFragment.receiveItem(text);
	}

}
