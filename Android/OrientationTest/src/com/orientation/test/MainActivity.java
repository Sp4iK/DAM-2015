package com.orientation.test;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView txtViewPortrait;
	private TextView txtViewLandscape;
	private int dato;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("Llamada a 'onCreate'");
	
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			setContentView(R.layout.landscape);
		} else {
			setContentView(R.layout.portrait);
		}

		if (savedInstanceState != null) {
			dato = savedInstanceState.getInt("datoSalvado");
		} else {
			dato = 0;
		}
		System.out.println("datoSalvado: " + dato);
	}
	
	//	Called	after	onCreate	has	finished,	use	to	restore	UI	state
	@Override	
	public	void onRestoreInstanceState(Bundle	savedInstanceState)	{	
		 super.onRestoreInstanceState(savedInstanceState);	
		 System.out.println("Llamada a 'onRestoreInstanceState'");
		 if (savedInstanceState != null) {
				dato = savedInstanceState.getInt("datoSalvado");
			}
		 System.out.println("datoSalvado: " + dato);
		 //	Restore	UI	state	from	the	savedInstanceState.	
		 //	This	bundle	has	also	been	passed	to	onCreate.	
	}
	
	//	Called	before	subsequent	visible	lifetimes for an Activity process.	
	@Override	
	public	void onRestart(){	
		 super.onRestart();
		 System.out.println("Llamada a 'onRestart'");
		 //	Load	changes	knowing	that	the	Activity	has	already	
		 //	been	visible	within	this	process.	
	}
	
	//	Called	at	the	start	of	the	visible	lifetime.
	@Override	
	public	void onStart(){	
		 super.onStart();
		 System.out.println("Llamada a 'onStart'");
		 //	Apply	any	required	UI	change	now	that	
		 //	the	Activity	is	visible.	
	}
	
	//	Called	at	the	start	of	the	active	lifetime.
	@Override	
	public	void	onResume(){	
		 super.onResume();	
		 System.out.println("Llamada a 'onResume'");
		 //	Resume	any	paused	UI	updates,	threads,	or	processes	required	
		 //	by	the	Activity	but	suspended	when	it	was	inactive.	
	}	
	
	//	Called	to	save	UI	state	changes	at the end of the active lifecycle.	
	@Override	
	public	void onSaveInstanceState(Bundle	savedInstanceState)	{	
		 //	Save	UI	state	changes	to	the	savedInstanceState.	
		 //	This	bundle	will	be	passed	to	onCreate	and	
		 //	onRestoreInstanceState	if	the	process	is	
		 //	killed	and	restarted	by	the	run	time.	
		 super.onSaveInstanceState(savedInstanceState);
		 dato++;
		 savedInstanceState.putInt("datoSalvado", dato);
		 System.out.println("Llamada a 'onSaveInstanceState' | datoSalvado: " + dato);
	}
	
	//	Called	at	the	end	of	the	active	lifetime.
	@Override	
	public	void	onPause(){	
		 //	Suspend	UI	updates,	threads,	or	CPU	intensive	processes	
		 //	that	don’t	need	to	be	updated	when	the	Activity	isn’t	
		 //	the	active	foreground	Activity.	
		 super.onPause();
		 System.out.println("Llamada a 'onPause'");
	}	
	
	//	Called	at	the	end	of	the	visible	lifetime.	
	@Override	
	public	void	onStop(){	
		 //	Suspend	remaining	UI	updates,	threads,	or	processing	
		 //	that	aren’t	required	when	the	Activity	isn’t	visible.	
		 //	Persist	all	edits	or	state	changes	
		 //	as	after	this	call	the	process	is	likely	to	be	killed.	
		 super.onStop();
		 System.out.println("Llamada a 'onStop'");
	}
	
	//	Sometimes	called	at	the	end	of	the	full	lifetime.
	@Override	
	public	void	onDestroy(){	
		 //	Clean	up	any	resources	including	ending	threads,	
		 //	closing	database	connections	etc.	
		 super.onDestroy();	
		 System.out.println("Llamada a 'onDestroy'");
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);

	    // Checks the orientation of the screen
	    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
	    	setContentView(R.layout.landscape);
	        Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
	        txtViewLandscape = (TextView) findViewById(R.id.textViewLandscape);
	        txtViewLandscape.setText("Device is in landscape mode");
	    } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
	    	setContentView(R.layout.portrait);
	        Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
	        txtViewPortrait = (TextView) findViewById(R.id.textViewPortrait);
	        txtViewPortrait.setText("Device is in portrait mode");
	    }
	}
}
