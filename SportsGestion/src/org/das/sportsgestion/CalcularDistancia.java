package org.das.sportsgestion;

import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalcularDistancia extends Activity{
	
	private Button butCalcular, butPosicion;
	private EditText edLatitud, edLongitud;
	private TextView txtDistancia;
	
	private LocationManager managerGPS;
	private Location localizPersona, localizPolidep;
	private String nombre;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calcular_distancia);

		nombre = getIntent().getExtras().getString("NombreP");

		edLatitud = (EditText) findViewById(R.id.editLatitud);
		edLongitud = (EditText) findViewById(R.id.editLongitud);
		txtDistancia = (TextView) findViewById(R.id.textDistancia);
		
		managerGPS = (LocationManager) getSystemService(LOCATION_SERVICE);


		
		//Pulsando este botón nos dirá cuál es la última posición que se guardo
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
					List<String> providers = managerGPS.getProviders(true);
					Location pos = calcularMiPosicion(managerGPS, providers);
					//Location pos = managerGPS.getLastKnownLocation(LocationManager.GPS_PROVIDER);
					
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
				if(comprobarCamposLLenos()){
					txtDistancia.setText(calcularDistancia() + " KM");					
				}
				else{
					Toast.makeText(getApplicationContext(), R.string.CampoVacio, 4000).show();

				}
			}
		});
		
	}
	
	private String calcularDistancia(){
		Float distanciaTotal;
		Double longitud = 0.0; 
		Double latitud = 0.0;
		
		
		Cursor aCursor = LaBD.getMiBD(getApplicationContext()).seleccionarPolideportivo(nombre);
		if(aCursor.moveToFirst()) {
			do {
				longitud = aCursor.getDouble(5);
				latitud = aCursor.getDouble(4);		
			} while(aCursor.moveToNext());
		}
		
		localizPersona = new Location ("Pers");
		if(edLatitud.getText().toString() != "" && edLongitud.getText().toString() != ""){
			localizPersona.setLatitude(Double.parseDouble(edLatitud.getText().toString()));
			localizPersona.setLongitude(Double.parseDouble(edLongitud.getText().toString()));			
		}
		else{
			
		}
		
		localizPolidep = new Location ("Poli");
		localizPolidep.setLatitude(latitud);
		localizPolidep.setLongitude(longitud);
		
		distanciaTotal = localizPolidep.distanceTo(localizPersona);
		distanciaTotal = distanciaTotal/1000;
				
		return distanciaTotal.toString();
	}
		
	private Location calcularMiPosicion(LocationManager location, List<String> providers) {
		Location localizPersona = null;
		boolean salir = false;
		int cont = providers.size()-1;
		
		while(cont >= 0 && !salir ){
			localizPersona = location.getLastKnownLocation(providers.get(cont));
			
			if (localizPersona != null){
				salir = true;
			}
		}

		return localizPersona;
	}
	
	private boolean comprobarCamposLLenos(){
		boolean todoLleno = false;
		if(	edLatitud.getText().toString().compareTo("") != 0 && edLongitud.getText().toString().compareTo("") != 0) {
			todoLleno = true;
		}
		return todoLleno;
	}

}
