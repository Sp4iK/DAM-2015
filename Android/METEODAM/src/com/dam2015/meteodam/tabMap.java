package com.dam2015.meteodam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class tabMap extends Fragment implements OnMapReadyCallback {
	
	private View vista;
	private Context contexto;
	
	TextView lblMapTabTitle;
	TextView lblMapTabDetail;
	
	FragmentManager fragmentManager;
	LocationManager locationManager;
	Location lastLocation;
	
	Bitmap img;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
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
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		vista = inflater.inflate(R.layout.map_layout, container, false);
		contexto = getActivity().getApplicationContext();
		
		lblMapTabTitle = (TextView) vista.findViewById(R.id.lblMapTabTitle);
		lblMapTabDetail = (TextView) vista.findViewById(R.id.lblMapTabDetail);

		//GMaps fragment
		MapFragment fragment = new MapFragment().newInstance();
		fragmentManager = getChildFragmentManager();
		fragmentManager.beginTransaction().add(R.id.mapFrame, fragment, "map_frag").commit();
		
		//System.out.println(getChildFragmentManager().findFragmentByTag("map_frag"));
		//MapFragment mapFragment = (MapFragment) getChildFragmentManager().findFragmentByTag("map_frag");//(R.id.mapFrame);
		fragment.getMapAsync(this);

		return vista;
	}

	@Override
	public void onMapReady(GoogleMap map) {
		LatLng loc = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 13));
        
        String locality = reverseGeocode(lastLocation);
        lblMapTabTitle.append(locality);
        
        getData(locality);

        System.out.println("onMapReady - img="+img);
        map.addMarker(new MarkerOptions()
                .title(locality)
                //.icon(BitmapDescriptorFactory.fromBitmap(img))
                .position(loc));
		
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
			Weather weather = new Weather();
			String data = ((new WeatherHttpClient()).getWeatherData(params[0]));

			try {
				weather = JSONWeatherParser.getWeather(data);

				// Let's retrieve the icon
				weather.iconData = ( (new WeatherHttpClient()).getImage(weather.currentCondition.getIcon()));

			} catch (JSONException e) {				
				e.printStackTrace();
			}
			return weather;
		}
		
		protected void onPostExecute(Weather weather) {
			super.onPostExecute(weather);
			
//			if (weather.iconData != null && weather.iconData.length > 0) {
//				img = BitmapFactory.decodeByteArray(weather.iconData, 0, weather.iconData.length);
//			}
			
			lblMapTabDetail.setText("Descripción: "+weather.currentCondition.getCondition()+"\n"+
									  "Temperatura: "+weather.temperature.getTemp()+"ºC\n"+
									  "\tMínima: "+weather.temperature.getMinTemp()+"ºC\n"+
									  "\tMáxima: "+weather.temperature.getMaxTemp()+"ºC\n"+
									  "Humedad: "+weather.currentCondition.getHumidity()+"%\n"+
									  "Presión: "+weather.currentCondition.getPressure()+"mBar\n"+
									  "Viento "+weather.wind.getSpeed()+"km/h\n"+
									  "Precipitación: "+weather.rain.getAmmount()+"mm.");
		}
	}
	
	@Override
	public void onDestroy() {
		Fragment fragment = getActivity().getFragmentManager().findFragmentById(R.id.mapFrame);
		System.out.println("onDestroy - fragment="+fragment);
		
		if (fragment.isResumed()) {
			getActivity().getFragmentManager().beginTransaction().remove(fragment).commit();
		}
		
		super.onDestroy();
	}
	
}
