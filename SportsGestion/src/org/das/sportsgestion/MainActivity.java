package org.das.sportsgestion;

import java.util.Locale;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends FragmentActivity{
	
	private Button btnBuscar, btnGestion, btnCuenta, btnDescuentos, btnSalir;
	private String idiomaSeleccionado;
	private Intent intBuscar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		Bundle extras = getIntent().getExtras();

		//Comprobamos que exista extras para poder asignarle la configuración del idioma
		if(extras != null){
			String leng = extras.getString("Lenguaje");

			Locale nuevaloc = new Locale(leng);
			Locale.setDefault(nuevaloc);
			Configuration config = new Configuration();
			config.locale = nuevaloc;
			getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
			
		}

		
		btnBuscar = (Button) findViewById(R.id.butBuscar);
		btnBuscar.setText(R.string.Buscar);
		btnBuscar.setBackgroundColor(Color.GRAY);
		btnBuscar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intBuscar = new Intent(getApplicationContext(), BusquedaActivity.class);
				startActivity(intBuscar);
			}
		});
		
		btnGestion = (Button) findViewById(R.id.butGestionPolid);
		btnGestion.setText(R.string.GestionarPolid);
		btnGestion.setVisibility(View.INVISIBLE);
		btnGestion.setBackgroundColor(Color.GRAY);
		btnGestion.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intAdmin = new Intent(MainActivity.this, MenuGestionar.class);
				startActivity(intAdmin);
				
				
			}
		});
		
		btnCuenta = (Button) findViewById(R.id.buttonCuenta);
		btnCuenta.setText(R.string.MiCuenta);
		btnCuenta.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intCuenta = new Intent(MainActivity.this, MainLogin.class);
				startActivity(intCuenta);
				
			}
		});
		
		btnDescuentos = (Button) findViewById(R.id.butDescuentos);
		btnDescuentos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intDescuentos = new Intent(MainActivity.this, Servicio.class);
				startService(intDescuentos);
				
			}
		});
		
		
		btnSalir = (Button) findViewById(R.id.butSalir);
		btnSalir.setText(R.string.Salir);
		btnSalir.setBackgroundColor(Color.GRAY);
		btnSalir.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder salirBuilder = new AlertDialog.Builder(MainActivity.this);
				salirBuilder.setTitle(R.string.Salir);
				salirBuilder.setMessage(R.string.MensajeSalirAplicacion);
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_castellano:
				idiomaSeleccionado = "es";
				idioma(idiomaSeleccionado);
			return true;
			
			case R.id.menu_euskera:
				idiomaSeleccionado = "eu";
				idioma(idiomaSeleccionado);
			return true;
			
			case R.id.menu_ingles:
				idiomaSeleccionado = "en";
				idioma(idiomaSeleccionado);
			return true;
			
			case R.id.Ayuda:
				ayuda();				
			return true;
			
			case R.id.menu_admin:
				administrador();
			return true;
			
			default:
			return super.onOptionsItemSelected(item);
		}
	}

	
	private void idioma(String pIdioma){
		MainActivity.this.finish();
		Intent intLang = new Intent(getApplicationContext(), MainActivity.class);
		intLang.putExtra("Lenguaje", pIdioma);
		startActivity(intLang);
		
	}
	
	private void ayuda(){
		AlertDialog.Builder aDialog = new AlertDialog.Builder(MainActivity.this);
		aDialog.setTitle("Ayuda");
		aDialog.setMessage(R.string.Ayuda);
		aDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		aDialog.show();	
	}

	private void administrador() {
		AlertDialog.Builder adminAlert = new AlertDialog.Builder(this);
		
		adminAlert.setTitle(R.string.IdentifAdmin);
		adminAlert.setMessage(R.string.Contrasena);
		
		final EditText input = new EditText(this);

		
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT);
		input.setLayoutParams(lp);
		input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

		adminAlert.setView(input);
		
		adminAlert.setPositiveButton(R.string.Aceptar, new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int whichButton) {
				
				if(!input.getText().toString().equals("")){
					if(input.getText().toString().equals("admin")){
						btnGestion.setVisibility(View.VISIBLE);
					}
					else{
						Toast.makeText(getBaseContext(), "LA CONTRASEÑA ES INCORRECTA", Toast.LENGTH_LONG).show();
					}	
				}
				else{
					Toast.makeText(getBaseContext(), "INSERTE LA CONTRASEÑA", Toast.LENGTH_LONG).show();
				}
			}
		});
		adminAlert.show();
		
	}
}
