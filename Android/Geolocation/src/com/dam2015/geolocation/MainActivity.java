package com.dam2015.geolocation;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import android.support.v7.app.ActionBarActivity;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnMapReadyCallback {
	
	EditText txtLat;
	EditText txtLong;
	EditText txtRadio;
	
	LocationManager locationManager;
	
	GoogleMap map;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final TextView lblLastPos = (TextView) this.findViewById(R.id.lblLastPos);
		final TextView lblActualPos = (TextView) this.findViewById(R.id.lblActualPos);
		Button btnProximityAlert = (Button) this.findViewById(R.id.btnProximityAlert);
		txtLat = (EditText) this.findViewById(R.id.txtLat);
		txtLong = (EditText) this.findViewById(R.id.txtLong);
		txtRadio = (EditText) this.findViewById(R.id.txtRadio);
		
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		criteria.setAltitudeRequired(false);
		criteria.setSpeedRequired(false);
		
		String bestProvider = locationManager.getBestProvider(criteria, true);
		final Location lastLocation = locationManager.getLastKnownLocation(bestProvider);	
		
		// GMaps
		final MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        map = mapFragment.getMap();
		
		LocationListener locationListener = new LocationListener() {
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				//Toast.makeText(getApplicationContext(), "onStatusChanged", Toast.LENGTH_LONG).show();
			}
			
			@Override
			public void onProviderEnabled(String provider) {
				Toast.makeText(getApplicationContext(), "Proveedor GPS activado", Toast.LENGTH_LONG).show();
			}
			
			@Override
			public void onProviderDisabled(String provider) {
				Toast.makeText(getApplicationContext(), "Proveedor GPS desactivado", Toast.LENGTH_LONG).show();
			}
			
			@Override
			public void onLocationChanged(Location location) {
				Double lat = location.getLatitude();
				Double lng = location.getLongitude();
				
				lblLastPos.setText("ÚLTIMA LOCALIZACIÓN:\n");
				lblLastPos.append("\tLat: "+lastLocation.getLatitude()+" | ");
				lblLastPos.append("Long: "+lastLocation.getLongitude());
				
				lblActualPos.setText("LOCALIZACIÓN ACTUAL:\n");
				lblActualPos.append("\tLat: "+lat+" | ");
				lblActualPos.append("Long: "+lng);
				
				LatLng loc = new LatLng(lat, lng);
				//map.clear();
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 13));
				map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_mylocation)).position(loc).flat(true));
				CameraPosition camPos = new CameraPosition.Builder().target(loc).build();
				map.animateCamera(CameraUpdateFactory.newCameraPosition(camPos));
			}
		};
		
		locationManager.requestLocationUpdates(bestProvider, 5, 5f, locationListener);
		
		btnProximityAlert.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setProximityAlert();
			}
		});
	}
	
	private void setProximityAlert() {
		double lat = Double.parseDouble(txtLat.getText().toString());
		double lng = Double.parseDouble(txtLong.getText().toString());
		float radius = Float.parseFloat(txtRadio.getText().toString());
		long expiration = -1; //no expirar
		
		map.addMarker(new MarkerOptions().position(new LatLng(lat, lng)));
		map.addCircle(new CircleOptions().center(new LatLng(lat, lng)).radius(radius));
		
		Intent intent = new Intent("com.dam2015.PROXIMITY_ALERT");	
		PendingIntent proximityIntent = PendingIntent.getBroadcast(this, -1, intent, 0);	

		locationManager.addProximityAlert(lat, lng, radius, expiration, proximityIntent);
		System.out.println("Alerta fijada");
		Toast.makeText(this.getApplicationContext(), "Alerta fijada", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onMapReady(GoogleMap arg0) {
		// TODO Auto-generated method stub
		
	}

}
