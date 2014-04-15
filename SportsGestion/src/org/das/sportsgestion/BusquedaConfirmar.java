package org.das.sportsgestion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;










import android.animation.AnimatorSet.Builder;
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
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BusquedaConfirmar extends FragmentActivity {
	
	private static TextView txtDeporte;

	private static TextView txtNombre, txtCalle, txtLocalidad, txtPrecio;

	private Button butDistancia;
	private Button butPagar;
	
	private static String nombre;
	
	private Intent intentTicket;

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
		
		nombre = getIntent().getExtras().getString("Nombre");

		Toast.makeText(getApplicationContext(), nombre, 4511).show();
		actualizarCampos();
		
//		butDistancia = (Button) findViewById(R.id.butDistancia);
//		butDistancia.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
////				Intent intentDistancia = new Intent(getApplicationContext(), CalcularDistancia.class);
//				Toast.makeText(getApplicationContext(), nombre, 4000).show();
////				intentDistancia.putExtra("NombreP", nombre);
////				startActivity(intentDistancia);
//			}
//		});	
		
//		butPagar = (Button) findViewById(R.id.butPagar);
//		butPagar.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				AlertDialog.Builder dialogTicket = new AlertDialog.Builder(BusquedaConfirmar.this);
//				dialogTicket.setTitle(R.string.Ticket);
//				dialogTicket.setMessage(R.string.Ticket);
//				dialogTicket.setCancelable(false);
//				
//				dialogTicket.setPositiveButton(R.string.Si, new DialogInterface.OnClickListener() {
//					
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						
//						String contenido = escribirTexto();
//						imprimirTicket(contenido);
//						notificar();
//					}
//				});
//				
//				dialogTicket.setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
//					
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						Toast.makeText(BusquedaConfirmar.this, R.string.TicketNo, 5000).show();
//					}
//				});
//				
//				dialogTicket.show();
//			}
//		});
	}
	
	private void actualizarCampos(){
		//nombre = getIntent().getExtras().getString("Nombre");
		
		FragmentDetails details = (FragmentDetails) getSupportFragmentManager().findFragmentById(R.id.fragment2);
		details.actualizarCampos(nombre);
	}


	
	
//	
//	public static void avisarConNotificacion(){
//		AlertDialog.Builder dialogTicket = new AlertDialog.Builder(BusquedaConfirmar.this);
//		dialogTicket.setTitle(R.string.Ticket);
//		dialogTicket.setMessage(R.string.Ticket);
//		dialogTicket.setCancelable(false);
//		
//		dialogTicket.setPositiveButton(R.string.Si, new DialogInterface.OnClickListener() {
//			
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				
//				String contenido = escribirTexto();
//				imprimirTicket(contenido);
//				notificar();
//			}
//		});
//		
//		dialogTicket.setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
//			
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				Toast.makeText(BusquedaConfirmar.this, R.string.TicketNo, 5000).show();
//			}
//		});
//		
//		dialogTicket.show();
//	}
//
//	
//	
//	
//	private static String escribirTexto(){
//		String texto = "";
//		String deporte, nombre, localidad, precio;
//		
//		deporte = txtDeporte.getText().toString();
//		nombre = txtNombre.getText().toString();
//		localidad = txtLocalidad.getText().toString();
//		precio = txtPrecio.getText().toString();
//		
//		texto = "SU TICKET ES:\nPOLIDEPORTIVO: " + nombre + "\nTIPO DE DEPORTE A REALIZAR: " + deporte 
//				+ "\nLOCALIDAD: " + localidad + "\nPRECIO: "+precio ;
//		
//		return texto;
//	}
//	
//	private static void imprimirTicket(String pContenido){
//		String estado = Environment.getExternalStorageState();
//		boolean Disponible,Escritura;
//		
//		if (estado.equals(Environment.MEDIA_MOUNTED)){
//			Disponible = true;
//			Escritura = true;
//		}
//		else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
//			Disponible = true;
//			Escritura= false;
//	}
//		else{
//			Disponible = false;
//			Escritura = false;
//		}
//		
//		if(Disponible && Escritura){
//			
//			try {
//				File path = Environment.getExternalStorageDirectory();
//				File f = new File(path.getAbsolutePath(), "TicketPolideportivo.txt");
//				OutputStreamWriter fich = new OutputStreamWriter( new FileOutputStream(f));
//				fich.write(pContenido);
//				fich.close();
//			} catch (IOException e) {
//				
//				Log.e("Error", e.toString());
//			}
//		}	
//	}
//	
//	private static void notificar(){
//		intentTicket = new Intent();
//		PendingIntent intentEnNoti = PendingIntent.getActivity(getApplicationContext(), 0, intentTicket, PendingIntent.FLAG_CANCEL_CURRENT);
//		NotificationCompat.Builder aBuilder = new NotificationCompat.Builder(BusquedaConfirmar.this);
//		
//		aBuilder.setSmallIcon(android.R.drawable.stat_notify_missed_call);
//		//aBuilder.setLargeIcon(((BitmapDrawable)getResources().getDrawable(R.drawable.onin)).getBitmap());
//		aBuilder.setContentTitle(Integer.toString(R.string.TicketImprimido));
//		aBuilder.setTicker(Integer.toString(R.string.TicketImprOk));
//		aBuilder.setContentIntent(intentEnNoti);
//		aBuilder.setDefaults(Notification.DEFAULT_ALL);
//		aBuilder.addAction(R.drawable.tick, "SÍ", intentEnNoti);
//		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//		mNotificationManager.notify(1,aBuilder.build());
//		aBuilder.setAutoCancel(true);
//	}
}
	

