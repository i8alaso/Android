package org.das.sportsgestion;

import java.security.Provider;
import java.util.List;

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
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calcular_distancia);

		nombre = getIntent().getExtras().getString("NombreP");
		Toast.makeText(getApplicationContext(), nombre, 7511).show();

		edLatitud = (EditText) findViewById(R.id.editLatitud);
		edLongitud = (EditText) findViewById(R.id.editLongitud);
		txtDistancia = (TextView) findViewById(R.id.textDistancia);
		
		managerGPS = (LocationManager) getSystemService(LOCATION_SERVICE);


		
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
				Toast.makeText(getApplicationContext(), nombre, 4511).show();
				txtDistancia.setText(calcularDistancia2() + " KM");
			}
		});
		
	}
	
	private String calcularDistanciaa(){
//		String nombre = getIntent().getExtras().getString("Nombre");
		
		// Para probar si calcula la distancia bien
		//String nombre = "DISTANCIA";
		
		
		Float distanciaTotal;
		Double longitud = 0.0; 
		Double latitud = 0.0;
		
		
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
		distanciaTotal = distanciaTotal/1000;
				
		return distanciaTotal.toString();
	}
	
	private String calcularDistancia2 () {  
		        //double earthRadius = 3958.75;//miles  
				double lat1 = 0.0, lng1 = 0.0, lat2, lng2 = 0.0;
				
				Cursor aCursor = LaBD.getMiBD(getApplicationContext()).seleccionarPolideportivo(nombre);
				if(aCursor.moveToFirst()) {
					do {
						lng1 = aCursor.getDouble(4);
						lat1 = aCursor.getDouble(5);		
					} while(aCursor.moveToNext());
				}
				
				
				lat2 = Double.parseDouble(edLatitud.getText().toString());
				lng2 = Double.parseDouble(edLongitud.getText().toString());
		
		        double earthRadius = 6371;//kilometers  
		        double dLat = Math.toRadians(lat2 - lat1);  
		        double dLng = Math.toRadians(lng2 - lng1);  
		        double sindLat = Math.sin(dLat / 2);  
		        double sindLng = Math.sin(dLng / 2);  
		        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
		                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));  
		        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));  
		        double dist = earthRadius * c;  
		  
		        return String.valueOf(dist);  
	}

	
	private Location calcularMiPosicion(LocationManager lm, List<String> providers) {
		Location localizPersona = null;
		for (int i=providers.size()-1; i>=0; i--) {
                localizPersona = lm.getLastKnownLocation(providers.get(i));
                if (localizPersona != null) break;
        }

		return localizPersona;
	}

}
