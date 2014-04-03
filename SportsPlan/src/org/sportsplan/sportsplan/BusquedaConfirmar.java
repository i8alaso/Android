package org.sportsplan.sportsplan;

import java.lang.reflect.Array;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BusquedaConfirmar extends Activity {
	
	private TextView txtDeporte;
	private TextView tDeporte;
	private TextView txtHora;
	private TextView tHora;
	private TextView txtIncluido;
	private TextView tIncluido;
	private TextView txtLugar;
	private TextView tLugar;
	private TextView txtDescripcion;
	private TextView tPrecio;
	
	private Button butExtras;
	private Button butPagar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main_busquedaconfirmar);
		
		tDeporte = (TextView) findViewById(R.id.tDeporte);
		tDeporte.setText("TENIS                     ");
		tDeporte.setBackgroundColor(Color.LTGRAY);
		tDeporte.setTextColor(Color.WHITE);
		tHora = (TextView) findViewById(R.id.tHora);
		tHora.setText("16:15-17:15");
		tHora.setBackgroundColor(Color.GRAY);
		tIncluido = (TextView) findViewById(R.id.tIncluido);
		tIncluido.setText("ENTRADAS \n DUCHAS \n LUZ");
		tLugar = (TextView) findViewById(R.id.tLugar);
		tLugar.setText("CLUB DE TENIS");
		tPrecio = (TextView) findViewById(R.id.tPrecio);
		tPrecio.setText("5,50" + "€");
		
		
		butExtras = (Button) findViewById(R.id.butExtras);
		butExtras.setText(R.string.Extras);
		butExtras.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(BusquedaConfirmar.this);
				dialog.setTitle(R.string.TituloExtras);
				final ArrayList listaExtras = new ArrayList();
				final CharSequence [] extras = {"Entrada     1.00€", "Luz       2.00€", "Material", "Toallas"};
				final boolean [] elegidos = {true, true, false,false};
				
				dialog.setMultiChoiceItems(extras, elegidos, new DialogInterface.OnMultiChoiceClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int indexSelected, boolean isChecekd) {
						if(isChecekd){
							listaExtras.add(indexSelected);
						}
						else if(listaExtras.contains(indexSelected)){
							listaExtras.remove(Integer.valueOf(indexSelected));
						}
						
					}
				});
				
				dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
						
					}
				});
				dialog.show();
			}
		});
	}
	
	

}
