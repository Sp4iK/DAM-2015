/**
 * This is a tutorial source code 
 * provided "as is" and without warranties.
 *
 * For any question please visit the web site
 * http://www.survivingwithandroid.com
 *
 * or write an email to
 * survivingwithandroid@gmail.com
 *
 */
package com.dam2015.meteodam;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dam2015.meteodam.Location;
import com.dam2015.meteodam.Weather;

/*
 * Copyright (C) 2013 Surviving with Android (http://www.survivingwithandroid.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class JSONWeatherParser {

	public static Weather getWeather(String data) throws JSONException  {
		Weather weather = new Weather();

		// We create out JSONObject from the data
		JSONObject jObj = new JSONObject(data);
		
		// We start extracting the info
		Location loc = new Location();
		
		loc.setReturnCode(getInt("cod", jObj));
		JSONObject cityObj = getObject("city", jObj);
		loc.setCountry(getString("country", cityObj));
		//loc.setSunrise(getInt("sunrise", cityObj));
		//loc.setSunset(getInt("sunset", cityObj));
		loc.setCity(getString("name", cityObj));
		
		JSONObject coordObj = getObject("coord", cityObj);
		loc.setLatitude(getFloat("lat", coordObj));
		loc.setLongitude(getFloat("lon", coordObj));
		
		weather.location = loc;
		
		// Get the first day from the array
		JSONArray jArrDays = jObj.getJSONArray("list");
		JSONObject jObjDay = jArrDays.getJSONObject(0);
		
		// We get weather info (This is an array)
		JSONArray jArr = jObjDay.getJSONArray("weather");
		
		// We use only the first value
		JSONObject JSONWeather = jArr.getJSONObject(0);
		weather.currentCondition.setWeatherId(getInt("id", JSONWeather));
		weather.currentCondition.setDescr(getString("description", JSONWeather));
		weather.currentCondition.setCondition(getString("main", JSONWeather));
		weather.currentCondition.setIcon(getString("icon", JSONWeather));
		
		weather.currentCondition.setHumidity(getInt("humidity", jObjDay));
		weather.currentCondition.setPressure(getInt("pressure", jObjDay));
		
		JSONObject tObj = jObjDay.getJSONObject("temp");
		weather.temperature.setMaxTemp(getFloat("max", tObj));
		weather.temperature.setMinTemp(getFloat("min", tObj));
		weather.temperature.setTemp(getFloat("day", tObj));
		
		// Wind
		//JSONObject wObj = getObject("wind", jObj);
		weather.wind.setSpeed(getFloat("speed", jObjDay));
		weather.wind.setDeg(getFloat("deg", jObjDay));
		
		// Clouds
		//JSONObject cObj = getObject("clouds", jObj);
		weather.clouds.setPerc(getInt("clouds", jObjDay));
		
		// Rain
		weather.rain.setAmmount(getFloat("rain", jObjDay));
		
		// We download the icon to show
		
		return weather;
	}
	
	
	private static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
		JSONObject subObj = jObj.getJSONObject(tagName);
		return subObj;
	}
	
	private static String getString(String tagName, JSONObject jObj) throws JSONException {
		return jObj.getString(tagName);
	}

	private static float  getFloat(String tagName, JSONObject jObj) throws JSONException {
		return (float) jObj.getDouble(tagName);
	}
	
	private static int  getInt(String tagName, JSONObject jObj) throws JSONException {
		return jObj.getInt(tagName);
	}
	
}