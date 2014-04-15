package org.das.sportsgestion;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainLogin extends Activity {

	EditText edLogUser, edLogPwd;
	
	Button butLogAceptar, butLogReg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_login);
		
		edLogUser = (EditText) findViewById(R.id.edUsuario);
		edLogPwd = (EditText) findViewById(R.id.edContrasenia);
		
		butLogAceptar = (Button) findViewById(R.id.butLogin);
		butLogAceptar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(coincidenContrasenias()){
					Toast.makeText(getApplicationContext(), "Coinciden!!", 3000).show();
				}
				else{
					Toast.makeText(getApplicationContext(), "Nooo :(", 3000).show();
				}
			}
		});
	}
	
	
	private boolean coincidenContrasenias(){
		String usuario = edLogUser.getText().toString();
		boolean coinciden = false;
		
		Cursor aCursor = LaBD.getMiBD(getApplicationContext()).seleccionarContraseña(usuario);
		
		if(aCursor.moveToFirst()){
			if(aCursor.getString(0).compareTo(edLogPwd.getText().toString()) == 0 ){
				coinciden = true;
			}
		}
		
		
		return coinciden;
	}
	
	

}
