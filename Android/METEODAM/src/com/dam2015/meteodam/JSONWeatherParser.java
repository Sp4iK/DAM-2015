/*
 * Based on sample code from Surviving with Android (http://www.survivingwithandroid.com)
 * Modified to fit app properties
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
package com.dam2015.meteodam;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dam2015.meteodam.Location;
import com.dam2015.meteodam.Weather;

public class JSONWeatherParser {

	public static Weather getWeather(String data) throws JSONException  {

		try {
			// We create out JSONObject from the data
			JSONObject jObj = new JSONObject(data);
			
			// and check if data is valid
			if(getInt("cod", jObj) != 404) {
				
				// Now we get the number of forecasting days
				int days = getInt("cnt", jObj);
				
				// and create the Weather object
				Weather weather = new Weather(days);
				
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
				
				// Get the each day's data from the array
				JSONArray jArrDays = jObj.getJSONArray("list");
				
				for(int i=0;i<jArrDays.length();i++) {
					// Get the weather info (array) and use only the first value
					JSONObject jObjDay = jArrDays.getJSONObject(i);

					// We get weather info (This is an array)
					JSONArray jArr = jObjDay.getJSONArray("weather");

					// We use only the first value
					JSONObject JSONWeather = jArr.getJSONObject(0);

					weather.dailyWeather[i].setDate(getInt("dt", jObjDay));
					weather.dailyWeather[i].setWeatherId(getInt("id", JSONWeather));
					weather.dailyWeather[i].setDescr(getString("description", JSONWeather));
					weather.dailyWeather[i].setCondition(getString("main", JSONWeather));
					weather.dailyWeather[i].setIcon(getString("icon", JSONWeather));
					weather.dailyWeather[i].setHumidity(getInt("humidity", jObjDay));
					weather.dailyWeather[i].setPressure(getInt("pressure", jObjDay));

					JSONObject tObj = jObjDay.getJSONObject("temp");
					weather.dailyWeather[i].temperature.setMaxTemp(getFloat("max", tObj));
					weather.dailyWeather[i].temperature.setMinTemp(getFloat("min", tObj));
					weather.dailyWeather[i].temperature.setTemp(getFloat("day", tObj));

					// Wind
					weather.dailyWeather[i].wind.setSpeed(getFloat("speed", jObjDay));
					weather.dailyWeather[i].wind.setDeg(getFloat("deg", jObjDay));

					// Clouds
					weather.dailyWeather[i].clouds.setPerc(getInt("clouds", jObjDay));

					// Rain (this is variable, can be or cannot)
					weather.dailyWeather[i].rain.setAmmount(getFloat("rain", jObjDay));
				}
				
				return weather;
				
			} else {
				System.out.println("JSONWeatherParser->getWeather: cod404");
				Weather weather = new Weather(0);
				Location loc = new Location();
				loc.setReturnCode(getInt("cod", jObj));
				weather.location = loc;

				return weather;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	private static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
		JSONObject subObj = jObj.getJSONObject(tagName);
		return subObj;
	}
	
	private static String getString(String tagName, JSONObject jObj) throws JSONException {
		return jObj.getString(tagName);
	}

	private static float getFloat(String tagName, JSONObject jObj) throws JSONException {
		try {
			return (float) jObj.getDouble(tagName);
		} catch (Exception e) {}
		
		return 0;
	}
	
	private static int getInt(String tagName, JSONObject jObj) throws JSONException {
		return jObj.getInt(tagName);
	}
	
}
