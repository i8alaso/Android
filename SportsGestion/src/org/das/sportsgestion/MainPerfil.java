package org.das.sportsgestion;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainPerfil extends FragmentActivity{

	private EditText edUsuario, edPassword, edRepPassword, edPolideportivo, edLocalidad, edCalle;
	private Button butBorrar, butAceptar;
	private TextView txtPassword, txtRepPassword;
	private String pUsuario, botonPulsado;
	private String usuario, calle, localidad, password, polideportivo, passRep; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_perfil);
		
		txtPassword = (TextView) findViewById(R.id.txtPerfPwd);
		txtRepPassword = (TextView) findViewById(R.id.txtPerfRepPwd);
		
		edUsuario = (EditText) findViewById(R.id.edPerfNombre);
		edPassword = (EditText) findViewById(R.id.edPerfPwd);
		edRepPassword = (EditText) findViewById(R.id.edPerfRepPwd);
		edPolideportivo = (EditText) findViewById(R.id.edPerfPolideportivo);
		edLocalidad = (EditText) findViewById(R.id.edPerfLocalidad);
		edCalle = (EditText) findViewById(R.id.edPerfCalle);
		
		butAceptar = (Button) findViewById(R.id.butPerfOk);
		butBorrar = (Button) findViewById(R.id.butPerfBorrar);
		
		botonPulsado = getIntent().getExtras().getString("Boton");
		pUsuario = getIntent().getExtras().getString("Usuario");
		
		if(botonPulsado.compareTo("Aceptar") == 0){
			llenarCamposPerfil();
			edPassword.setVisibility(View.INVISIBLE);
			txtPassword.setVisibility(View.INVISIBLE);
			edRepPassword.setVisibility(View.INVISIBLE);
			txtRepPassword.setVisibility(View.INVISIBLE);
		}
		else{
			butBorrar.setVisibility(View.INVISIBLE);
		}
		
		butAceptar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(botonPulsado.equals("Aceptar")){
					MainPerfil.this.finish();
				}
				else{
					if(comprobarCamposLLenos()){
						inicializarDatos();
						if(comprobarNombre(pUsuario)){
							if(password.equals(passRep)){
								LaBD.getMiBD(getApplicationContext()).insertarUsuario(usuario, localidad, calle, polideportivo, password);
								MainPerfil.this.finish();
								Toast.makeText(getApplicationContext(), R.string.MensajeInsercion, 2500).show();
							}
							else{
								Toast.makeText(getApplicationContext(), R.string.ContraseniaNoCoinciden, 2500).show();
							}
						}
						else{
							Toast.makeText(getApplicationContext(), R.string.UsuarioElegido, 2500).show();
						}
					}
					else{
						Toast.makeText(getApplicationContext(), R.string.CampoVacio, 2500).show();
					}
				}
				
			}
		});
		
		butBorrar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LaBD.getMiBD(getApplicationContext()).eliminarUsuario(pUsuario);
				Toast.makeText(getApplicationContext(), R.string.BorrarUsuario, 2500).show();
				MainPerfil.this.finish();
				
			}
		});
		
	}

	private void inicializarDatos(){
		usuario = edUsuario.getText().toString();
		localidad = edLocalidad.getText().toString();
		calle = edCalle.getText().toString();
		polideportivo = edPolideportivo.getText().toString();
		password = edPassword.getText().toString();
		passRep = edRepPassword.getText().toString();

	}
	
	private boolean comprobarNombre(String pNombre){
		boolean disponible = false;
		
		Cursor unCursor = LaBD.getMiBD(getApplicationContext()).comprobarSiExisteUsuario(pUsuario);
		
		if(unCursor.moveToFirst()){
			if(unCursor.getInt(0) == 0){
				disponible = true;
			}
		}
		return disponible;
	}
	
	private boolean comprobarCamposLLenos(){
		boolean todoLleno = false;
		if(edUsuario.getText().toString().compareTo("") != 0 && edCalle.getText().toString().compareTo("") != 0 &&
			edPolideportivo.getText().toString().compareTo("") != 0 && edLocalidad.getText().toString().compareTo("") != 0 &&
			edPassword.getText().toString().compareTo("") != 0 && edRepPassword.getText().toString().compareTo("") != 0)
		{
			todoLleno = true;
		}
		return todoLleno;
	}
	
	private void llenarCamposPerfil(){		
		Cursor aCursor = LaBD.getMiBD(getApplicationContext()).buscarUsuario(pUsuario);
		
		if(aCursor.moveToFirst()) {
			do {
				edUsuario.setText(aCursor.getString(0).toString());
				edLocalidad.setText(aCursor.getString(2).toString());
				edPolideportivo.setText(aCursor.getString(4).toString());
				edCalle.setText(aCursor.getString(3).toString());
				
			} while(aCursor.moveToNext());
		}
	}
}
