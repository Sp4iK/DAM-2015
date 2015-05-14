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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class WeatherHttpClient {

	private static String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?units=metric&lang=es&cnt=7&q=";
	private static String IMG_URL = "http://api.openweathermap.org/img/w/";
	
	public String getWeatherData(String location) {
		HttpURLConnection con = null ;
		InputStream is = null;

		try {
			con = (HttpURLConnection) ( new URL(BASE_URL + location)).openConnection();
			con.setRequestMethod("GET");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.connect();
			
			// Let's read the response
			StringBuffer buffer = new StringBuffer();
			is = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while (  (line = br.readLine()) != null )
				buffer.append(line + "\r\n");
			
			is.close();
			con.disconnect();
			
			return buffer.toString();
			
	    } catch(Throwable t) {
			t.printStackTrace();
		} finally {
			try { is.close(); } catch(Throwable t) {}
			try { con.disconnect(); } catch(Throwable t) {}
		}

		return null;	
	}
	
	public Bitmap getImage(String code) {
		HttpURLConnection con = null ;
		InputStream is = null;
		
		try {
			con = (HttpURLConnection) (new URL(IMG_URL + code + ".png")).openConnection();
			is = con.getInputStream();
			Bitmap icon = BitmapFactory.decodeStream(is);

			return icon;
			
	    } catch(Throwable t) {
			t.printStackTrace();
		} finally {
			try { is.close(); } catch(Throwable t) {}
			try { con.disconnect(); } catch(Throwable t) {}
		}
		
		return null;
	}
}