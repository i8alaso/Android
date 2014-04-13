package org.das.sportsgestion;


import org.das.sportsgestion.FragmentLista.IListFragmentListener;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;

public class ResultadoConLista extends FragmentActivity implements IListFragmentListener {

	private WindowManager mWindowManager;
	private Display mDisplay;
	
	private String localidad,  deporte, nombre;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_mainbusqueda_lista);
		mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
		mDisplay = mWindowManager.getDefaultDisplay();
		
		// Para que se muestren los polideportivos en el f1
		actualizarLista();
		
	}

	@Override
	public void onItemSelected(String item) {
		
		if(mDisplay.getRotation() != Surface.ROTATION_0 && mDisplay.getRotation() != Surface.ROTATION_180) {
				FragmentDetails details = (FragmentDetails) getSupportFragmentManager().findFragmentById(R.id.fragment2);
				details.actualizarCampos(nombre);
				
		} else {
			
			//Como el movil está en vertical llamamos a la siguiente actividad para mostrar los detalles
			Intent intConf = new Intent(ResultadoConLista.this, BusquedaConfirmar.class);
			
			intConf.putExtra("Nombre", nombre);
			startActivity(intConf);
		}
	}

	private void actualizarLista() {
		
		// Obtenemos la localidad y el deporte que hemos introducido en la busqueda anterior
		localidad = getIntent().getExtras().getString("Localidad");
		deporte = getIntent().getExtras().getString("Deporte");
		
		// 
		FragmentLista listaPolid = (FragmentLista) getSupportFragmentManager().findFragmentById(R.id.fragment1);
		nombre = listaPolid.updateList(localidad, deporte);
			
	}

}
