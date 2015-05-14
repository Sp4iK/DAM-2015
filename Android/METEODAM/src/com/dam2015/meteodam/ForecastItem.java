package com.dam2015.meteodam;

import android.graphics.Bitmap;

public class ForecastItem {
	
	private Bitmap icon;
	private String description;
	private String date;
	private String temperature;
	
	public ForecastItem(Bitmap icon, String description, String date, String temperature) {
		super();
		
		this.icon = icon;
		this.description = description;
		this.date = date;
		this.temperature = temperature;
	}
	
	public Bitmap getIcon() {
		return icon;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getTemperature() {
		return temperature;
	}

}
