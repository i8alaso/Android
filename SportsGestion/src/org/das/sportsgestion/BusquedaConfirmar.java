package org.das.sportsgestion;

import java.lang.reflect.Array;
import java.util.ArrayList;






import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BusquedaConfirmar extends FragmentActivity {
	
	private TextView txtDeporte, txtNombre, txtCalle, txtLocalidad, txtPrecio;

	private Button butDistancia;
	private Button butPagar;
	
	private String nombre;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main_busquedaconfirmar);
		
		txtNombre = (TextView) findViewById(R.id.txtNombre);
		txtDeporte = (TextView) findViewById(R.id.txtInstalaciones);
		txtDeporte.setBackgroundColor(Color.LTGRAY);
		txtDeporte.setTextColor(Color.WHITE);
		txtLocalidad = (TextView) findViewById(R.id.txtLocalidad);
		txtCalle = (TextView) findViewById(R.id.txtCalle);
		txtCalle.setBackgroundColor(Color.GRAY);
		txtPrecio = (TextView) findViewById(R.id.txtPrecio);

		actualizarCampos();
		
		butDistancia = (Button) findViewById(R.id.butDistancia);
		butDistancia.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intentDistancia = new Intent(getApplicationContext(), CalcularDistancia.class);
				startActivity(intentDistancia);
			}
		});	
		
		butPagar = (Button) findViewById(R.id.butPagar);
		butPagar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentTicket = new Intent();
				PendingIntent intentEnNoti = PendingIntent.getActivity(getApplicationContext(), 0, intentTicket, PendingIntent.FLAG_CANCEL_CURRENT);
				PendingIntent intentNO = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), PendingIntent.FLAG_CANCEL_CURRENT);
				
				NotificationCompat.Builder aBuilder = new NotificationCompat.Builder(BusquedaConfirmar.this);
				
//				Intent intentTicket = new Intent();
//				
//				PendingIntent intentEnNoti = PendingIntent.getActivity(this, 0, intentTicket, PendingIntent.FLAG_NO_CREATE);
//				//PendingIntent intentNO = PendingIntent.getActivity(this, 0, new Intent(), PendingIntent.FLAG_CANCEL_CURRENT);
//				
//				
				aBuilder.setSmallIcon(android.R.drawable.stat_notify_missed_call);
				//aBuilder.setLargeIcon(((BitmapDrawable)getResources().getDrawable(R.drawable.onin)).getBitmap());
				aBuilder.setContentTitle("Pincha si quieres el ticket");
				aBuilder.setTicker("¿Quieres el ticket?");
				aBuilder.setContentIntent(intentEnNoti);
				aBuilder.setDefaults(Notification.DEFAULT_ALL);


				aBuilder.addAction(R.drawable.tick, "SÍ", intentEnNoti);
				aBuilder.addAction(R.drawable.x, "NO", intentNO);
//				
//				//Sobra
//				//getApplicationContext();		
				NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				mNotificationManager.notify(1,aBuilder.build());
				aBuilder.setAutoCancel(true);
//				
			}
		});
	}
	
	private void actualizarCampos(){
		nombre = getIntent().getExtras().getString("Nombre");
		
		FragmentDetails details = (FragmentDetails) getSupportFragmentManager().findFragmentById(R.id.fragment2);
		details.actualizarCampos(nombre);
		
		
		//BORRAR
//		Cursor aCursor = LaBD.getMiBD(getApplicationContext()).seleccionarPolideportivo(nombre);
//		if(aCursor.moveToFirst()) {
//			do {
//				txtNombre.setText("POLIDEPORTIVO " + aCursor.getString(0).toString());
//				txtCalle.setText(aCursor.getString(2).toString());
//				txtLocalidad.setText(aCursor.getString(1).toString());
//				txtPrecio.setText(aCursor.getString(6).toString() + " €");
//				txtDeporte.setText(aCursor.getString(3));
//				
//			} while(aCursor.moveToNext());
//		}
	}
}
	

