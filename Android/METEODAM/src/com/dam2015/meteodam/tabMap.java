package com.dam2015.meteodam;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class tabMap extends Fragment implements OnMapReadyCallback {
	
	private View vista;
	private Context contexto;
	
	TextView lblMapTabTitle;
	TextView lblMapTabDetail;
	
	FragmentManager fragmentManager;
	MapFragment fragment;
	LocationManager locationManager;
	Location lastLocation;
	
	GoogleMap googleMap;
	
	String locality;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		contexto = getActivity().getApplicationContext();
		
		//GPS location
		locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
		
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		criteria.setAltitudeRequired(false);
		criteria.setSpeedRequired(false);
		
		String bestProvider = locationManager.getBestProvider(criteria, true);
		//lastLocation = locationManager.getLastKnownLocation(bestProvider);
		//lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		lastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		
        locality = reverseGeocode(lastLocation);
        getData(locality);	
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		vista = inflater.inflate(R.layout.map_layout, container, false);
		
		lblMapTabTitle = (TextView) vista.findViewById(R.id.lblMapTabTitle);
		lblMapTabDetail = (TextView) vista.findViewById(R.id.lblMapTabDetail);
		
		lblMapTabTitle.append(locality);

		//GMaps fragment
		fragment = new MapFragment().newInstance();
		fragmentManager = getChildFragmentManager();
		fragmentManager.beginTransaction().add(R.id.mapFrame, fragment, "map_frag").commit();
		
		//System.out.println(getChildFragmentManager().findFragmentByTag("map_frag"));
		//MapFragment mapFragment = (MapFragment) getChildFragmentManager().findFragmentByTag("map_frag");//(R.id.mapFrame);
		fragment.getMapAsync(this);

		return vista;
	}

	@Override
	public void onMapReady(GoogleMap map) {
		googleMap = map;
		LatLng loc = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());

		googleMap.setMyLocationEnabled(true);
		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 13));
	}
	
	private String reverseGeocode(Location location) {
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		String locality="";
		
		List<Address> addresses = null;
		Geocoder gc = new Geocoder(contexto, Locale.getDefault());
		
		try {
			addresses = gc.getFromLocation(latitude, longitude, 1);
			locality = addresses.get(0).getLocality() + " (" + addresses.get(0).getCountryCode() + ")";
		} catch (IOException e) {
			//Log.e(TAG, “IO Exception”, e);
			e.printStackTrace();
		}
		
		return locality;
	}
	
	private void getData(String loc) {
		JSONWeatherTask task = new JSONWeatherTask();
		task.execute(new String[]{loc});
	}
	
	private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

		@Override
		protected Weather doInBackground(String... params) {
			String data = ((new WeatherHttpClient()).getWeatherData(params[0]));

			try {
				Weather weather = JSONWeatherParser.getWeather(data);

				// Let's retrieve the icon
				weather.dailyWeather[0].iconData = ((new WeatherHttpClient()).getImage(weather.dailyWeather[0].getIcon()));
				
				return weather;
				
			} catch (JSONException e) {				
				e.printStackTrace();
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Weather weather) {
			super.onPostExecute(weather);
			
			if(weather != null) {
				lblMapTabDetail.setText("Descripción: "+weather.dailyWeather[0].getCondition()+"\n"+
										  "Temperatura: "+weather.dailyWeather[0].temperature.getTemp()+"ºC\n"+
										  "\tMínima: "+weather.dailyWeather[0].temperature.getMinTemp()+"ºC\n"+
										  "\tMáxima: "+weather.dailyWeather[0].temperature.getMaxTemp()+"ºC\n"+
										  "Humedad: "+weather.dailyWeather[0].getHumidity()+"%\n"+
										  "Presión: "+weather.dailyWeather[0].getPressure()+"mBar\n"+
										  "Viento "+weather.dailyWeather[0].wind.getSpeed()+"km/h\n"+
										  "Precipitación: "+weather.dailyWeather[0].rain.getAmmount()+"mm.");
				
		        if(weather.dailyWeather[0].iconData != null && googleMap != null) {
		        	googleMap.addMarker(new MarkerOptions()
				        //.title(locality)
				        .icon(BitmapDescriptorFactory.fromBitmap(weather.dailyWeather[0].iconData))
				        .anchor(0.5f, 0.5f)
				        .position(new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude())));
		        }
			} else {
				Toast.makeText(contexto, "Error de conexión: No se ha podido establecer la conexión con el servidor.", Toast.LENGTH_LONG).show();
			}
		}
	}
	
//	@Override
//	public void onDestroy() {
////		Fragment fragment = getActivity().getFragmentManager().findFragmentById(R.id.mapFrame);
////		Fragment fragment = getChildFragmentManager().findFragmentById(R.id.mapFrame);
//		System.out.println("onDestroy - fragment="+fragment);
//
//		if (fragment != null && fragment.isResumed()) {
//			getActivity().getFragmentManager().beginTransaction().remove(fragment).commit();
//		}
//		
//		super.onDestroy();
//	}
	
//	public void onDetach() {
//		
//	}
	
}
