package org.das.sportsgestion;


import org.das.sportsgestion.FragmentLista.IListFragmentListener;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

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

	/**
	 * Comprueba la orientación del móvil
	 * 	Si está en horizontal se actualiza los detalles del polideportivo
	 * 	Si está en vertical se crea una nueva actividad con los detalles del polideportivo
	 * 
	 * @return void
	 */
	
	@Override
	public void onItemSelected(String item) {
		String nombrePolid = item.substring(14);

		//Como
		if(mDisplay.getRotation() != Surface.ROTATION_0 && mDisplay.getRotation() != Surface.ROTATION_180) {
				FragmentDetails details = (FragmentDetails) getSupportFragmentManager().findFragmentById(R.id.fragment2);
				details.actualizarCampos(nombrePolid);
				
		} else {
				Intent intConf = new Intent(ResultadoConLista.this, BusquedaConfirmar.class);
				intConf.putExtra("Nombre", nombrePolid);
				startActivity(intConf);
		}
	}

	
	/**
	 * Actualiza los polideportivos que cumplan con la busqueda que se ha hecho en la actividad anterior
	 * 	Por ello obtenemos la localidad y el deporte insertado anteriormente
	 * 
	 * @return void
	 */
	
	private void actualizarLista() {
		
		localidad = getIntent().getExtras().getString("Localidad");
		deporte = getIntent().getExtras().getString("Deporte");

		FragmentLista listaPolid = (FragmentLista) getSupportFragmentManager().findFragmentById(R.id.fragment1);
		listaPolid.updateList(localidad, deporte);
			
	}
	

}
