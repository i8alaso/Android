package org.sportsplan.sportsplan;

import java.util.Locale;

import org.sportsplan.sportsplan.FragmentLista.IListFragmentListener;

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

public class MainSportsPlan extends FragmentActivity{
	
	private Button btnBuscar;
	private Button btnDescuentos;
	private Button btnReservas;
	private Button btnCuenta;
	private Button btnSalir;
	
	private Intent intBuscar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_sports_plan);
		

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
				MainSportsPlan.this.finish();
				idioma();
				
				
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
				Intent intCuenta = new Intent(MainSportsPlan.this, MainLogin.class);
				startActivity(intCuenta);
				
			}
		});
		
		btnSalir = (Button) findViewById(R.id.buttonSalir);
		btnSalir.setText(R.string.Salir);
		btnSalir.setBackgroundColor(Color.GRAY);
		btnSalir.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder salirBuilder = new AlertDialog.Builder(MainSportsPlan.this);
				salirBuilder.setTitle(R.string.MensajeSalirAplicacion);
				salirBuilder.setPositiveButton(R.string.Si, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						MainSportsPlan.this.finish();
						
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_sports_plan, menu);
		return true;
	}

	
	private void idioma(){
		Intent intLang = new Intent(getApplicationContext(), MainSportsPlan.class);
		intLang.putExtra("Lenguaje", "eu");
		startActivity(intLang);
		
	}


}
