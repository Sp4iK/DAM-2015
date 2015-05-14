package com.dam2015.meteodam;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.widget.Toast;

public class WeatherUpdateReceiver extends BroadcastReceiver {
	
	private Context contexto;
	private String locality;

	@Override
	public void onReceive(Context context, Intent intent) {
		
		contexto = context;
		
		// GPS location
		LocationManager locationManager = (LocationManager) contexto.getSystemService(Context.LOCATION_SERVICE);
		
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		criteria.setAltitudeRequired(false);
		criteria.setSpeedRequired(false);
		
		String bestProvider = locationManager.getBestProvider(criteria, true);
		//lastLocation = locationManager.getLastKnownLocation(bestProvider);
		//lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		Location lastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		
        locality = reverseGeocode(lastLocation);
        getData(locality);
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
				if(weather != null) {
					weather.dailyWeather[0].iconData = ((new WeatherHttpClient()).getImage(weather.dailyWeather[0].getIcon()));
				}
				
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
				Notification.Builder notificationBuilder = new Notification.Builder(contexto);
				NotificationManager notificationManager = (NotificationManager) contexto.getSystemService(Context.NOTIFICATION_SERVICE);
				
				Intent launchIntent = new Intent(contexto, MainActivity.class);
				launchIntent.putExtra("from_notification", true);
				PendingIntent pendingIntent = PendingIntent.getActivity(contexto, 0, launchIntent, 0);
				
				notificationBuilder.setSmallIcon(android.R.drawable.ic_notification_overlay)
									.setLargeIcon(weather.dailyWeather[0].iconData)
									.setTicker("METEODAM")
									.setContentTitle("METEODAM")
									.setContentText(locality)
									.setSubText(weather.dailyWeather[0].getDescr())
									.setContentInfo(String.valueOf(Math.round(weather.dailyWeather[0].temperature.getTemp()))+"º")
									.setWhen(System.currentTimeMillis())
									.setContentIntent(pendingIntent);
	
				Notification notification = notificationBuilder.getNotification();
				
				notificationManager.notify("weather_notification", 1, notification);
			} else {
				Toast.makeText(contexto, "Error de conexión: No se ha podido establecer la conexión con el servidor.", Toast.LENGTH_LONG).show();
			}
		}
	}

}
