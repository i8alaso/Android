package org.das.sportsgestion;


import java.util.Random;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;


public class Servicio extends Service {

	private Intent intentDescuento;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		final Handler handler = new Handler();
		Runnable run = new Runnable() {
			
			@Override
			public void run() {
				lanzarNotificacion();
				handler.postDelayed(this, 300000);
			}
				
		};
		
		run.run();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		return START_STICKY;
	}
	
	public void obtenerLista(){
		
	}
	private  void lanzarNotificacion(){
		
		String descuento = "";
		descuento = elegirDescuento();
		
		intentDescuento = new Intent();
		PendingIntent intentEnNoti = PendingIntent.getActivity(getApplicationContext(), 0, intentDescuento, PendingIntent.FLAG_CANCEL_CURRENT);
		NotificationCompat.Builder aBuilder = new NotificationCompat.Builder(getApplicationContext());
		
		aBuilder.setSmallIcon(android.R.drawable.stat_notify_missed_call);
		aBuilder.setContentTitle("Descuento");
		aBuilder.setTicker(descuento);
		aBuilder.setContentIntent(intentEnNoti);
		aBuilder.setDefaults(Notification.DEFAULT_ALL);
		aBuilder.addAction(R.drawable.tick, "OK", intentEnNoti);
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(1,aBuilder.build());
		aBuilder.setAutoCancel(true);
	}

	private String  elegirDescuento() {
		Random rDesc = new Random();
		int porcentaje = (rDesc.nextInt(13)+1)*5;
		
		return ("Usted tiene un ") + porcentaje + ("% en el polideportivo que quieras");
	}
}		


