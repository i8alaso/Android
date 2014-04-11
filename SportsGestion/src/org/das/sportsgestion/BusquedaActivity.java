package org.das.sportsgestion;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BusquedaActivity extends Activity{
	
	private TextView txtView;
	
	private EditText txtDeporte;
	private EditText txtLocalidad;

	
	private Button butBuscar;
	private Button butRellenar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_busqueda);
		
		txtView = (TextView) findViewById(R.id.QueBuscas);
		txtView.setBackgroundColor(Color.GRAY);
		txtView.setTextColor(Color.WHITE);
				
		txtDeporte = (EditText) findViewById(R.id.edBuscar);
		txtDeporte.setBackgroundColor(Color.WHITE);
		txtDeporte.setTextColor(Color.GRAY);
		txtDeporte.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				txtDeporte.setText("");
				txtDeporte.setTextColor(Color.BLACK);
				
			}
		});
		
		txtLocalidad = (EditText) findViewById(R.id.edLocalidad);
		txtLocalidad.setBackgroundColor(Color.WHITE);
		txtLocalidad.setTextColor(Color.GRAY);
		txtLocalidad.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				txtLocalidad.setText("");
				txtLocalidad.setTextColor(Color.BLACK);
				
			}
		});

		
		butBuscar = (Button) findViewById(R.id.buttonBuscar);
		butBuscar.setBackgroundColor(Color.DKGRAY);
		butBuscar.setTextColor(Color.WHITE);
		
		butRellenar = (Button) findViewById(R.id.button2);
		butRellenar.setBackgroundColor(Color.GRAY);
		butRellenar.setTextColor(Color.WHITE);
		butRellenar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				txtDeporte.setText("Futbol");
				txtLocalidad.setText("Bilbao 48004");
			}
		});
		
		butBuscar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//if(txtProvincia.getText().toString() != null){
					Intent i = new Intent(getApplicationContext(), ResultadoConLista.class);
					i.putExtra("Localidad", txtLocalidad.getText().toString());
					i.putExtra("Deporte", txtDeporte.getText().toString());
					startActivity(i);
				//}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.busqueda, menu);
		return true;
	}



}
