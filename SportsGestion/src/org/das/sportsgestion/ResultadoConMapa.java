package org.das.sportsgestion;


import org.das.fragmento.Fragmento2;
import org.das.fragmento.R;
import org.das.sportsgestion.FragmentLista.IListFragmentListener;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Surface;

public class ResultadoConMapa extends FragmentActivity implements IListFragmentListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_mainbusquedamapa);
		
		
	}

	@Override
	public void onItemSelected(String item) {
		
		if(mDisplay.getRotation() != Surface.ROTATION_0 &&
				mDisplay.getRotation() != Surface.ROTATION_180) {
				
//				FragmentoPrueba frag = (FragmentoPrueba) getSupportFragmentManager().findFragmentById(R.id.fragmentasd);
//				frag.startActivity(new Intent());
				Fragmento2 details = (Fragmento2) getSupportFragmentManager().findFragmentById(R.id.fragment2);
				details.updateData(item);
		} else {
			Intent intConf = new Intent(ResultadoConMapa.this, BusquedaConfirmar.class);
			intConf.putExtra("nombre", item);
			startActivity(intConf);
		}
	}


}
