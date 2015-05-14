package com.dam2015.meteodam;

import java.util.Calendar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends Activity {
	Weather weather;
	
	TextView lblForecastDate, lblForecastDetails;
	ImageView imgForecastIcon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_layout);
	
		int day = getIntent().getIntExtra("day_num", 0);
		weather = tabData.weather;
		
		lblForecastDate = (TextView) this.findViewById(R.id.lblForecastDate);
		lblForecastDetails = (TextView) this.findViewById(R.id.lblForecastDetails);
		imgForecastIcon = (ImageView) this.findViewById(R.id.imgForecastIcon);
		
		parsearDatos(day);
	}
	
	private void parsearDatos(int day) {
		String fecha;

		fecha = getDate(weather.dailyWeather[day].getDate());
		lblForecastDate.append(fecha);

		lblForecastDetails.setText("Descripci�n: "+weather.dailyWeather[day].getCondition()+"\n"+
				"Temperatura: "+weather.dailyWeather[day].temperature.getTemp()+"�C\n"+
				"\tM�nima: "+weather.dailyWeather[day].temperature.getMinTemp()+"�C\n"+
				"\tM�xima: "+weather.dailyWeather[day].temperature.getMaxTemp()+"�C\n"+
				"Humedad: "+weather.dailyWeather[day].getHumidity()+"%\n"+
				"Presi�n: "+weather.dailyWeather[day].getPressure()+"mBar\n"+
				"Viento "+weather.dailyWeather[day].wind.getSpeed()+"km/h\n"+
				"Precipitaci�n: "+weather.dailyWeather[day].rain.getAmmount()+"mm.");

		imgForecastIcon.setImageBitmap((weather.dailyWeather[day].iconData).copy(Bitmap.Config.ARGB_8888, true));

	}
	
	private String getDate(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time*1000);
		String date = DateFormat.format("dd-MM-yyyy", cal).toString();
		return date;
	}
}
