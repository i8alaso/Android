package org.das.sportsgestion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentDetails extends Fragment{
	
	
	private TextView txtNombre, txtCalle, txtLocalidad, txtPrecio, txtDeporte;
	private Button butDistancia, butPagar;
	private String nombre;
	
	private Intent intentTicket;
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_fragmentodetalles, container, false);
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		butDistancia = (Button) getView().findViewById(R.id.butDistancia);
		butDistancia.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {				
				Intent intentDistancia = new Intent(getActivity(), CalcularDistancia.class);
				intentDistancia.putExtra("NombreP",nombre);
				startActivity(intentDistancia);
			}
		});
		
		butPagar = (Button) getView().findViewById(R.id.butPagar);
		butPagar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder dialogTicket = new AlertDialog.Builder(getActivity());
				dialogTicket.setTitle(R.string.Ticket);
				dialogTicket.setMessage(R.string.Ticket);
				dialogTicket.setCancelable(false);
				
				dialogTicket.setPositiveButton(R.string.Si, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						String contenido = escribirTexto();
						imprimirTicket(contenido);
						notificar();
					}
				});
				
				dialogTicket.setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(getActivity(), R.string.TicketNo, 5000).show();
					}
				});
				
				dialogTicket.show();
			}
		});
	}

	
	public void actualizarCampos(String pNombre){
		
		nombre = pNombre;
		
		txtNombre = (TextView) getView().findViewById(R.id.txtNombree);
		txtCalle = (TextView) getView().findViewById(R.id.txtCalle);
		txtDeporte = (TextView) getView().findViewById(R.id.txtInstalaciones);
		txtLocalidad = (TextView) getView().findViewById(R.id.txtLocalidad);
		txtPrecio = (TextView) getView().findViewById(R.id.txtPrecio);
		
		Cursor aCursor = LaBD.getMiBD(getActivity()).seleccionarPolideportivo(pNombre);
		if(aCursor.moveToFirst()) {
			do {
				txtNombre.setText("POLIDEPORTIVO " + nombre);
				txtCalle.setText(aCursor.getString(2).toString());
				txtLocalidad.setText(aCursor.getString(1).toString());
				txtPrecio.setText(Double.toString(aCursor.getDouble(6)) + " €");
				txtDeporte.setText(aCursor.getString(3));
				
			} while(aCursor.moveToNext());
		}
	}
	
	
	private String escribirTexto(){
		String texto = "";
		String deporte, nombre, localidad, precio;
		
		deporte = txtDeporte.getText().toString();
		nombre = txtNombre.getText().toString();
		localidad = txtLocalidad.getText().toString();
		precio = txtPrecio.getText().toString();
		
		texto = "SU TICKET ES:\nPOLIDEPORTIVO: " + nombre + "\nTIPO DE DEPORTE A REALIZAR: " + deporte 
				+ "\nLOCALIDAD: " + localidad + "\nPRECIO: "+precio ;
		
		return texto;
	}
	
	private void imprimirTicket(String pContenido){
		String estado = Environment.getExternalStorageState();
		boolean Disponible,Escritura;
		
		if (estado.equals(Environment.MEDIA_MOUNTED)){
			Disponible = true;
			Escritura = true;
		}
		else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
			Disponible = true;
			Escritura= false;
	}
		else{
			Disponible = false;
			Escritura = false;
		}
		
		if(Disponible && Escritura){
			
			try {
				File path = Environment.getExternalStorageDirectory();
				File f = new File(path.getAbsolutePath(), "TicketPolideportivo.txt");
				OutputStreamWriter fich = new OutputStreamWriter( new FileOutputStream(f));
				fich.write(pContenido);
				fich.close();
			} catch (IOException e) {
				
				Log.e("Error", e.toString());
			}
		}	
	}
	
	private void notificar(){
		intentTicket = new Intent();
		PendingIntent intentEnNoti = PendingIntent.getActivity(getActivity(), 0, intentTicket, PendingIntent.FLAG_CANCEL_CURRENT);
		NotificationCompat.Builder aBuilder = new NotificationCompat.Builder(getActivity());
		
		aBuilder.setSmallIcon(android.R.drawable.stat_notify_sdcard);
		aBuilder.setContentTitle("Ticket");
		aBuilder.setTicker("Tu ticket se ha imprimido correctamente");
		aBuilder.setContentIntent(intentEnNoti);
		aBuilder.setDefaults(Notification.DEFAULT_ALL);
		//aBuilder.addAction(R.drawable.tick, "SÍ", intentEnNoti);
		
		
		NotificationManager mNotificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(1,aBuilder.build());
		aBuilder.setAutoCancel(true);
	}
}
