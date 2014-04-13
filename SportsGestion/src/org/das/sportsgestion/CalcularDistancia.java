package org.das.sportsgestion;

import java.security.Provider;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalcularDistancia extends Activity{
	
	private Button butCalcular, butPosicion;
	private EditText edLatitud, edLongitud;
	private TextView txtDistancia;
	
	private LocationManager managerGPS;
	private Location locList;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calcular_distancia);
		
		edLatitud = (EditText) findViewById(R.id.editLatitud);
		edLongitud = (EditText) findViewById(R.id.editLongitud);
		txtDistancia = (TextView) findViewById(R.id.textDistancia);
		
		managerGPS = (LocationManager) getSystemService(LOCATION_SERVICE);
		locList = new Location("");

		
		//Pulsando este botón nos dirá cuál es nuestra posicion actual
		butPosicion = (Button) findViewById(R.id.butPosicion);
		butPosicion.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Comprobamos que el GPS esté conectado en ese momento
				// Y si no lo está, mostramos la pantalla de los ajustes del GPS
				
				if (!managerGPS.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
					Intent i= new Intent (android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					startActivity(i);
				}
				else{
					Location pos = managerGPS.getLastKnownLocation(LocationManager.GPS_PROVIDER);
					
					if(pos != null){
						edLatitud.setText(String.valueOf(pos.getLatitude()));
						edLongitud.setText(String.valueOf(pos.getLongitude()));
					}
					else{
						txtDistancia.setText(R.string.NoCoordenadas);
					}
					
				}			
			}
		});
		
		butCalcular = (Button) findViewById(R.id.butCalcular);
		butCalcular.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				txtDistancia.setText(calcularDistancia());
			}
		});
		
	}
	
	private String calcularDistancia(){
		//String nombre = getIntent().getExtras().getString("Nombre");
		
		// Para probar si calcula la distancia bien
		String nombre = "DISTANCIA";
		
		
		Float distanciaTotal;
		Double longitud = 0.0; 
		Double latitud = 0.0;
		Location localizPersona, localizPolidep;
		
		Cursor aCursor = LaBD.getMiBD(getApplicationContext()).seleccionarPolideportivo(nombre);
		if(aCursor.moveToFirst()) {
			do {
				longitud = aCursor.getDouble(4);
				latitud = aCursor.getDouble(5);		
			} while(aCursor.moveToNext());
		}
		
		localizPersona = new Location ("Pers");
		localizPersona.setLatitude(Double.parseDouble(edLatitud.getText().toString()));
		localizPersona.setLongitude(Double.parseDouble(edLongitud.getText().toString()));
		
		localizPolidep = new Location ("Poli");
		localizPolidep.setLatitude(latitud);
		localizPolidep.setLongitude(longitud);
		
		distanciaTotal = localizPolidep.distanceTo(localizPersona);

		return distanciaTotal.toString();
	}
	

}
