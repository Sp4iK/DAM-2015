package com.dam2015.sensors;

import java.util.List;
import android.support.v7.app.ActionBarActivity;
import android.annotation.TargetApi;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final TextView lblSensores = (TextView) this.findViewById(R.id.lblSensores);

		SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);

		final SensorEventListener sensorEventListener = new SensorEventListener() {

			@Override
			public void onSensorChanged(SensorEvent sensorEvent) {
				switch (sensorEvent.sensor.getType()) {
				case Sensor.TYPE_ACCELEROMETER:
					float xAxis_lateralA = sensorEvent.values[0];
					float yAxis_longitudinalA = sensorEvent.values[1];
					float zAxis_verticalA = sensorEvent.values[2];

					lblSensores.setText("");
					lblSensores.append("Accelerometro: " + "\n");
					lblSensores.append("X: "+xAxis_lateralA+" | Y: "+yAxis_longitudinalA+" | Z: "+zAxis_verticalA);
					break;
				case Sensor.TYPE_AMBIENT_TEMPERATURE:

				}
			}

			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				// TODO Auto-generated method stub
			}
		};

		for (Sensor sensor:listaSensores) {
			sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
//			lblSensores.append(sensor.getName().toString() + "\n");
		}
	}

}
