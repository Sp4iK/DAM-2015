package com.dam2015.meteodam;

import java.util.ArrayList;
import java.util.Calendar;
import org.json.JSONException;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class tabData extends Fragment {
	private View vista;
	private Context contexto;
	
	static Weather weather;
	
	TextView txtLocalization;
	ListView list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		vista = inflater.inflate(R.layout.data_layout, container, false);
		contexto = getActivity().getApplicationContext();
		
		txtLocalization = (TextView) vista.findViewById(R.id.txtLocalization);
		Button btnOK = (Button) vista.findViewById(R.id.btnOK);
		list = (ListView) vista.findViewById(R.id.detailListView);
		
		btnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String loc = (String) txtLocalization.getText().toString();
				getData(loc);
			}
		});
		
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(contexto, DetailActivity.class);
				intent.putExtra("day_num", position);
				
				startActivity(intent);
			}
			
		});
		
		return vista;
	}
	
	private void getData(String loc) {
		JSONWeatherTask task = new JSONWeatherTask();
		task.execute(new String[]{loc});
	}
	
	private String getDate(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time*1000);
		String date = DateFormat.format("dd-MM-yyyy", cal).toString();
		return date;
	}
	
	private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

		@Override
		protected Weather doInBackground(String... params) {
			String data = ((new WeatherHttpClient()).getWeatherData(params[0]));

			try {
				weather = JSONWeatherParser.getWeather(data);

				// Let's retrieve the icon
				if(weather.location.getReturnCode() != 404 && weather != null) {
					for(int i=0;i<weather.dailyWeather.length;i++) {
						weather.dailyWeather[i].iconData = ((new WeatherHttpClient()).getImage(weather.dailyWeather[i].getIcon()));
					}
				}

				return weather;

			} catch (JSONException e) {				
				e.printStackTrace();
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Weather weather) {

			if(weather != null) {
				ArrayList<ForecastItem> arrayList = new ArrayList<ForecastItem>();
				ForecastItemAdapter adapter = new ForecastItemAdapter(contexto, arrayList);
				list.setAdapter(adapter);
				
				int codigo = weather.location.getReturnCode();
	
				if(codigo == 200) {
					
					for(int i=0; i<weather.dailyWeather.length; i++) {			
						Bitmap icon = weather.dailyWeather[i].iconData;
						String description = weather.dailyWeather[i].getDescr();
						String date = getDate(weather.dailyWeather[i].getDate());
						String temperature = String.valueOf(Math.round(weather.dailyWeather[i].temperature.getTemp()));
						
						arrayList.add(new ForecastItem(icon, description, date, temperature));
					}
	
					adapter.notifyDataSetChanged();
				} else {
					Toast.makeText(contexto, "¡Ciudad erronea!", Toast.LENGTH_LONG).show();
					txtLocalization.setText("");
				}
			} else {
				Toast.makeText(contexto, "Error de conexión: No se ha podido establecer la conexión con el servidor.", Toast.LENGTH_LONG).show();
				txtLocalization.setText("");
			}
		}
	}
}
