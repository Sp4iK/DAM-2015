package com.dam2015.cronometro;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

public class Cronometro extends Service {

	private Timer temporizador = new Timer();
	public static MainActivity UPDATE_LISTENER;
	private static final long INTERVALO_ACTUALIZACION = 10; //milisegundos
	private double crono = 0;
	private Handler handler;

	public static void setUpdateListener (MainActivity MAService) {
		UPDATE_LISTENER = MAService;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		iniciarCronometro();
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {UPDATE_LISTENER.actualizarCronometro(crono);}
		};
	}

	private void iniciarCronometro() {
		temporizador.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				crono += 0.01;
				handler.sendEmptyMessage(0);
			}
		}, 0, INTERVALO_ACTUALIZACION);
	}

	@Override
	public void onDestroy() {
		pararCronometro();
		super.onDestroy();
	}

	private void pararCronometro() {
		if (temporizador!=null) temporizador.cancel();
	}

	@Override
	public IBinder onBind (Intent arg0) {
		return null;
	}
}
