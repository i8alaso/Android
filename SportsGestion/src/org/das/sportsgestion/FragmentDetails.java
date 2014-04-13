package org.das.sportsgestion;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentDetails extends Fragment{
	
	
	private TextView txtNombre, txtCalle, txtLocalidad, txtPrecio, txtDeporte;
	private Button butDistancia;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_fragmentodetalles, container, false);
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		butDistancia = (Button) getView().findViewById(R.id.butDistancia);
		butDistancia.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intentDistancia = new Intent(getActivity(), CalcularDistancia.class);
				startActivity(intentDistancia);
			}
		});
	}

	
	public void actualizarCampos(String pNombre){
	
		txtNombre = (TextView) getView().findViewById(R.id.txtNombre);
		txtCalle = (TextView) getView().findViewById(R.id.txtCalle);
		txtDeporte = (TextView) getView().findViewById(R.id.txtInstalaciones);
		txtLocalidad = (TextView) getView().findViewById(R.id.txtLocalidad);
		txtPrecio = (TextView) getView().findViewById(R.id.txtPrecio);
		
		Cursor aCursor = LaBD.getMiBD(getActivity()).seleccionarPolideportivo(pNombre);
		if(aCursor.moveToFirst()) {
			do {
				txtNombre.setText("POLIDEPORTIVO " + aCursor.getString(0).toString());
				txtCalle.setText(aCursor.getString(2).toString());
				txtLocalidad.setText(aCursor.getString(1).toString());
				txtPrecio.setText(Double.toString(aCursor.getDouble(6)) + " €");
				txtDeporte.setText(aCursor.getString(3));
				
			} while(aCursor.moveToNext());
		}
	}
}
