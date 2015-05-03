package com.dam2015.meteodam;

import java.util.Calendar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;

public class DetailActivity extends Activity {
	TextView lblForecastDate, lblForecastDetails;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_layout);
		
		String datos = getIntent().getStringExtra("datos");
		lblForecastDate = (TextView) this.findViewById(R.id.lblForecastDate);
		lblForecastDetails = (TextView) this.findViewById(R.id.lblForecastDetails);
		
		parsearDatos(datos);
	}
	
	private void parsearDatos(String datos) {
		JSONObject jObj;
		String fecha;
		String precipitacion;
		
		try {
			jObj = new JSONObject(datos);
			JSONArray jArrWeather = jObj.getJSONArray("weather");
			JSONObject jObjWeather = jArrWeather.getJSONObject(0);
			JSONObject jObjTemp = jObj.getJSONObject("temp");
			
			fecha = getDate(Long.parseLong(jObj.getString("dt")));
			lblForecastDate.append(fecha);
			
			try {
				precipitacion = jObj.getString("rain");
			} catch (JSONException e) {
				precipitacion = "0";
			}
			
			lblForecastDetails.setText("Descripción: "+jObjWeather.getString("description")+"\n"+
									  "Temperatura: "+jObjTemp.getString("day")+"ºC\n"+
									  "\tMínima: "+jObjTemp.getString("min")+"ºC\n"+
									  "\tMáxima: "+jObjTemp.getString("max")+"ºC\n"+
									  "Humedad: "+jObj.getString("humidity")+"%\n"+
									  "Presión: "+jObj.getString("pressure")+"mBar\n"+
									  "Viento "+jObj.getString("speed")+"km/h\n"+
									  "Precipitación: "+precipitacion+"mm.");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String getDate(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time*1000);
		String date = DateFormat.format("dd-MM-yyyy", cal).toString();
		return date;
	}
}
