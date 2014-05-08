package org.das.sportsgestion;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


public class BusquedaConfirmar extends FragmentActivity {
	
	private static String nombre;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_busquedaconfirmar);		
		nombre = getIntent().getExtras().getString("Nombre");
		actualizarCampos();
	}
	
	private void actualizarCampos(){	
		FragmentDetails details = (FragmentDetails) getSupportFragmentManager().findFragmentById(R.id.fragment2);
		details.actualizarCampos(nombre);
	}
}
	

