package com.dam2015.test;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDescarga = (Button) this.findViewById(R.id.button2);

        btnDescarga.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				iniciarDescarga();
			}
		});

    }

    private void iniciarDescarga() {
    	String	serviceString = Context.DOWNLOAD_SERVICE;
    	DownloadManager	downloadManager;
    	downloadManager	= (DownloadManager)getSystemService(serviceString);
    	Uri	uri	=	Uri.parse("http://developer.android.com/shareables/icon_templates-v4.0.zip");

    	DownloadManager.Request	request	= new Request(uri);
    	request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
    	request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "descarga_test.zip");
    	final long reference = downloadManager.enqueue(request);

    	IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);

        BroadcastReceiver receiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				long receivedReference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
				if (reference == receivedReference) {
					Toast.makeText(context, "DESCARGA COMPLETADA!", Toast.LENGTH_LONG).show();
				}
			}
		};

		registerReceiver(receiver, filter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
