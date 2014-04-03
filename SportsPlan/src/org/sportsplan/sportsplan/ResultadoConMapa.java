package org.sportsplan.sportsplan;


import org.sportsplan.sportsplan.FragmentLista.IListFragmentListener;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class ResultadoConMapa extends FragmentActivity implements IListFragmentListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_mainbusquedamapa);
		
		
	}

	@Override
	public void onItemSelected(String item) {
		Intent intConf = new Intent(ResultadoConMapa.this, BusquedaConfirmar.class);
		intConf.putExtra("nombre", item);
		startActivity(intConf);
		
	}


}
