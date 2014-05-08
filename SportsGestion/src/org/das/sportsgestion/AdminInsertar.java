package org.das.sportsgestion;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminInsertar extends Activity {
	
	private EditText edNombre, edCalle, edLocalidad, edDeporte, edLongitud, edLatitud, edPrecio; 
	private Button butAceptar, butModificar;
	
	private String nombre, localidad, calle, deporte, pClave;
	private Double longitud, latitud, precio;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insertar);
		
		edNombre = (EditText) findViewById(R.id.edInsNombre);
		edCalle = (EditText) findViewById(R.id.edInsCalle);
		edLocalidad = (EditText) findViewById(R.id.edInsLocalidad);
		edDeporte = (EditText) findViewById(R.id.edInsDeporte);
		edLongitud = (EditText) findViewById(R.id.edInsLongitud);
		edLatitud = (EditText) findViewById(R.id.edInsLatitud);
		edPrecio = (EditText) findViewById(R.id.edInsPrecio);
		
		butAceptar = (Button) findViewById(R.id.butInsGuardar);
		butModificar = (Button) findViewById(R.id.butInsModificar);
		
		pClave = getIntent().getExtras().getString("NombreGestion");
		
		if(getIntent().getExtras().getString("Tipo").equals("Insertar")){
			butModificar.setVisibility(View.INVISIBLE);
		}
		else{
			butAceptar.setVisibility(View.INVISIBLE);
			llenarCampos();
		}
		
		/**
		 * 	
		 */
		butAceptar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(comprobarCamposLLenos()){
					inicializarDatos();
					if(comprobarNombre(nombre)){
						insertarPolideportivo(nombre, localidad, calle, deporte, longitud, latitud, precio);
						Toast.makeText(getApplicationContext(), R.string.MensajeInsercion, 5000).show();
						AdminInsertar.this.finish();									
					}
					else{
						Toast.makeText(getApplicationContext(), R.string.PolideportivoElegido, 5000).show();
					}
				}
				else{
					Toast.makeText(getApplicationContext(), R.string.CampoVacio, 5000).show();
				}
			}

			/**
			 * Llama a la BD para insertar el nuevo polideportivo	
			 */
			private void insertarPolideportivo(String pNombre, String pLocalidad, String pCalle, String pDeporte, Double pLongitud, Double pLatitud, Double pPrecio) {
				
				LaBD.getMiBD(getApplicationContext()).insertarPolideportivo(pNombre, pLocalidad, pCalle, pDeporte, pLongitud, pLatitud, pPrecio);;
				
			}
		});
		
		butModificar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(comprobarCamposLLenos()){
					inicializarDatos();
					if(comprobarNombre(nombre)){
						modificarPolideportivo(nombre, localidad, calle, deporte, longitud, latitud, precio);
						Toast.makeText(getApplicationContext(), R.string.MensajeModificacion, 5000).show();
						AdminInsertar.this.finish();
					}
					else{
						Toast.makeText(getApplicationContext(), R.string.PolideportivoElegido, 5000).show();
					}
				}
				else{
					Toast.makeText(getApplicationContext(), R.string.CampoVacio, 5000).show();
				}
			}
			
			/**
			 * Llama a la BD para que modifique  	
			 */
			private void modificarPolideportivo(String pNombre, String pLocalidad, String pCalle, String pDeporte, Double pLongitud, Double pLatitud, Double pPrecio) {
				
				LaBD.getMiBD(getApplicationContext()).modificarPolideportivo(pClave, pNombre, pLocalidad, pCalle, pDeporte, pLongitud, pLatitud, pPrecio);
				
			}
		});
		
		
	}

	
	private void inicializarDatos(){
		nombre = edNombre.getText().toString();
		localidad = edLocalidad.getText().toString();
		calle = edCalle.getText().toString();
		deporte = edDeporte.getText().toString();
		longitud = Double.parseDouble(edLongitud.getText().toString());
		latitud = Double.parseDouble(edLatitud.getText().toString());
		precio = Double.parseDouble(edPrecio.getText().toString());
	}
	
	private boolean comprobarNombre(String pNombre){
		boolean disponible = false;
		
		Cursor unCursor = LaBD.getMiBD(getApplicationContext()).comprobarSiExiste(pNombre);
		
		if(unCursor.moveToFirst()){
			if(unCursor.getInt(0) == 0){
				disponible = true;
			}
		}
		return disponible;
	}
	
	private boolean comprobarCamposLLenos(){
		boolean todoLleno = false;
		if(edNombre.getText().toString().compareTo("") != 0 && edCalle.getText().toString().compareTo("") != 0 &&
			edDeporte.getText().toString().compareTo("") != 0 && edLocalidad.getText().toString().compareTo("") != 0 &&
			edLatitud.getText().toString().compareTo("") != 0 && edLongitud.getText().toString().compareTo("") != 0 &&
			edPrecio.getText().toString().compareTo("") != 0)
		{
			todoLleno = true;
		}
		return todoLleno;
	}
	
	private void llenarCampos(){		
		Cursor aCursor = LaBD.getMiBD(getApplicationContext()).seleccionarPolideportivo(pClave);
		
		if(aCursor.moveToFirst()) {
			do {
				edNombre.setText(aCursor.getString(0).toString());
				edLocalidad.setText(aCursor.getString(1).toString());
				edDeporte.setText(aCursor.getString(3).toString());
				edCalle.setText(aCursor.getString(2).toString());
				edPrecio.setText(Double.toString(aCursor.getDouble(6)));
				edLatitud.setText(Double.toString(aCursor.getDouble(4)));
				edLongitud.setText(Double.toString(aCursor.getDouble(5)));
				
			} while(aCursor.moveToNext());
		}
	}
}
