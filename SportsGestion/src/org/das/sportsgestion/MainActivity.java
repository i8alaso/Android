package org.das.sportsgestion;

import java.util.Locale;


import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity{
	
	private Button btnBuscar;
	private Button btnDescuentos;
	private Button btnReservas;
	private Button btnCuenta;
	private Button btnSalir;
	
	private String idiomaSeleccionado;
	
	private Intent intBuscar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		Bundle extras = getIntent().getExtras();

		if(extras != null){
//			String idioma = savedInstanceState.getString("eu");
//			int valor = savedInstanceState.getInt("eu"); 
			
			String leng = extras.getString("Lenguaje");

			Locale nuevaloc = new Locale(leng);
			Locale.setDefault(nuevaloc);
			Configuration config = new Configuration();
			config.locale = nuevaloc;
			getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
			
		}

		
		btnBuscar = (Button) findViewById(R.id.buttonBuscar);
		btnBuscar.setText(R.string.BUSCAR);
		btnBuscar.setBackgroundColor(Color.GRAY);
		btnBuscar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intBuscar = new Intent(getApplicationContext(), BusquedaActivity.class);
				startActivity(intBuscar);
			}
		});
		
		btnDescuentos = (Button) findViewById(R.id.buttonDescuentos);
		btnDescuentos.setText(R.string.DESCUENTOS);
		btnDescuentos.setBackgroundColor(Color.GRAY);
		btnDescuentos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity.this.finish();
				idioma("");
				
				
			}
		});
//		butDescuentos.setBackgroundColor(Color.RED);
		
		btnReservas = (Button) findViewById(R.id.buttonReserva);
		btnReservas.setText(R.string.MisReservas);
		btnReservas.setBackgroundColor(Color.WHITE);
		
		btnCuenta = (Button) findViewById(R.id.buttonCuenta);
		btnCuenta.setText(R.string.MiCuenta);
		btnCuenta.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intCuenta = new Intent(MainActivity.this, MainLogin.class);
				startActivity(intCuenta);
				
			}
		});
		
		btnSalir = (Button) findViewById(R.id.buttonSalir);
		btnSalir.setText(R.string.Salir);
		btnSalir.setBackgroundColor(Color.GRAY);
		btnSalir.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder salirBuilder = new AlertDialog.Builder(MainActivity.this);
				salirBuilder.setTitle(R.string.MensajeSalirAplicacion);
				salirBuilder.setPositiveButton(R.string.Si, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						MainActivity.this.finish();
						
					}
				});
				salirBuilder.setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
						
					}
				});
				salirBuilder.show();
				
			}
		});		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_castellano:
				idiomaSeleccionado = "es";
				idioma("eu");
			return true;
			
			case R.id.menu_euskera:
				idiomaSeleccionado = "eu";
				idioma("eu");
			return true;
			
			case R.id.menu_ingles:
				idiomaSeleccionado = "en";
			return true;
			
			case R.id.Anadir:			
			return true;
			
			case R.id.Guardar:		
			return true;
			
			default:
			return super.onOptionsItemSelected(item);
		}
	}

	
	private void idioma(String pIdioma){
		Intent intLang = new Intent(getApplicationContext(), MainActivity.class);
		intLang.putExtra("Lenguaje", pIdioma);
		startActivity(intLang);
		
	}


}
