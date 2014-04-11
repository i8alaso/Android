package org.das.sportsgestion;



import org.das.sportsgestion.FragmentLista.IListFragmentListener;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class ResultadoConLista extends FragmentActivity implements IListFragmentListener {

	private WindowManager mWindowManager;
	private Display mDisplay;
	private Button butDistancia;
	
	private String localidad,  deporte;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

//		butDistancia = (Button) findViewById(R.id.butDistancia);
//		butDistancia.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				Intent intentDistancia = new Intent(getApplicationContext(), CalcularDistancia.class);
//				startActivity(intentDistancia);
//			}
//		});	
		
		
		setContentView(R.layout.activity_mainbusqueda_lista);
		mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
		mDisplay = mWindowManager.getDefaultDisplay();
		actualizarLista();
		
	}

	@Override
	public void onItemSelected(String item) {
		
		if(mDisplay.getRotation() != Surface.ROTATION_0 &&
				mDisplay.getRotation() != Surface.ROTATION_180) {
				
//				FragmentoPrueba frag = (FragmentoPrueba) getSupportFragmentManager().findFragmentById(R.id.fragmentasd);
//				frag.startActivity(new Intent());
			
				FragmentDetails details = (FragmentDetails) getSupportFragmentManager().findFragmentById(R.id.fragment2);
				details.updateData(item);
				
		} else {
			Intent intConf = new Intent(ResultadoConLista.this, BusquedaConfirmar.class);
			intConf.putExtra("nombre", item);
			startActivity(intConf);
		}
	}

	private void actualizarLista() {
		
		localidad = getIntent().getExtras().getString("Localidad");
		deporte = getIntent().getExtras().getString("Deporte");
		
		FragmentLista listaPolid = (FragmentLista) getSupportFragmentManager().findFragmentById(R.id.fragment1);
		listaPolid.updateList(localidad, deporte);
			
	}

}
