package org.das.sportsgestion;

import org.das.sportsgestion.FragmentLista.IListFragmentListener;

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
	private EditText txtProvincia;
	private EditText txtLocalidad;
	private EditText txtLugar;
	private EditText Hora;
	
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
		
		txtLugar = (EditText) findViewById(R.id.edCentro);
		txtLugar.setBackgroundColor(Color.WHITE);
		txtLugar.setTextColor(Color.GRAY);
		txtLugar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				txtLugar.setText("");
				txtLugar.setTextColor(Color.BLACK);
				
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
		
		txtProvincia = (EditText) findViewById(R.id.edProvincia);
		txtProvincia.setBackgroundColor(Color.WHITE);
		txtProvincia.setTextColor(Color.GRAY);
		txtProvincia.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				txtProvincia.setText("");
				txtProvincia.setTextColor(Color.BLACK);
				
			}
		});
		
		Hora = (EditText) findViewById(R.id.edHora);
		Hora.setBackgroundColor(Color.WHITE);
		Hora.setTextColor(Color.GRAY);
		Hora.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Hora.setText("");
				Hora.setTextColor(Color.BLACK);
				
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
				txtDeporte.setText("TENIS");
				txtProvincia.setText("BIZKAIA");
				txtLocalidad.setText("BILBAO");
				txtLugar.setText("POLIDEPORTIVO TXURDINAGA");
				Hora.setText("16:15-17:45");
				
			}
		});
		
		butBuscar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//if(txtProvincia.getText().toString() != null){
					Intent i = new Intent(getApplicationContext(), ResultadoConMapa.class);
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
