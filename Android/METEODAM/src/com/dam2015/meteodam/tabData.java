package com.dam2015.meteodam;

import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class tabData extends Fragment {
	private View vista;
	private Context contexto;
	
	JSONArray lista;
	
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
				try {
					intent.putExtra("datos", (lista.getJSONObject(position)).toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	
	private class JSONWeatherTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {

			Weather weather = new Weather();
			String data = ((new WeatherHttpClient()).getWeatherData(params[0]));

			try {
				weather = JSONWeatherParser.getWeather(data);

				// Let's retrieve the icon
				weather.iconData = ( (new WeatherHttpClient()).getImage(weather.currentCondition.getIcon()));

			} catch (JSONException e) {				
				e.printStackTrace();
			}
//			return weather;
			return data;
		}
		
		protected void onPostExecute(String data) {
			JSONObject jObj;
			
			ArrayList<String> aList = new ArrayList<String>();
			ArrayAdapter<String> adapter =  new ArrayAdapter<String>(contexto, android.R.layout.simple_list_item_1, aList);
			list.setAdapter(adapter);
			
			try {
				jObj = new JSONObject(data);
				String codigo = jObj.getString("cod");
				String res;
				String fecha;
				
				if(codigo.equals("200")) {
					Integer previsiones = jObj.getInt("cnt");
					lista = jObj.getJSONArray("list");
				
					for(int i=0; i<previsiones; i++) {
						res = "";
						
						fecha = lista.getJSONObject(i).getString("dt");
						res = getDate(Long.parseLong(fecha));
						res += " | ";
						
						JSONObject jo = lista.getJSONObject(i);
						JSONArray ja = jo.getJSONArray("weather");
						JSONObject jo_in = ja.getJSONObject(0);
						
						res += jo_in.getString("description");
						
						aList.add(i, res);
					}
					
					adapter.notifyDataSetChanged();
				} else {
					Toast.makeText(contexto, "Ciudad erronea!", Toast.LENGTH_LONG).show();
					txtLocalization.setText("");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
