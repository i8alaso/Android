package org.das.sportsgestion;

import org.das.sportsgestion.FragmentLista.IListFragmentListener;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MenuGestionar extends FragmentActivity implements IListFragmentListener{
	
	
	private Button butInsertar, butModificar, butBorrar, butBorrarTodo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_gestionar);
		mostrarTodos();
		
		butInsertar = (Button) findViewById(R.id.butInsertar);
		butInsertar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intInsertar = new Intent(getApplicationContext(), AdminInsertar.class);
				intInsertar.putExtra("Tipo", "Insertar");
				startActivity(intInsertar);
			}
		});
				
		butModificar = (Button) findViewById(R.id.butModificar);
		butModificar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mostrarMensaje(1);
			}
		});
		butBorrar = (Button) findViewById(R.id.butBorrar);
		butBorrar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mostrarMensaje(2);
				
			}
		});
		
		butBorrarTodo = (Button) findViewById(R.id.butBorrarTodo);
		butBorrarTodo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LaBD.getMiBD(getApplicationContext()).eliminarTodo();
				
			}
		});
		
	}

	@Override
	public void onItemSelected(String item) {

	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_admin, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.Actualizar:
				mostrarTodos();
			return true;

			
			default:
			return super.onOptionsItemSelected(item);
		}
	}

	
	private void mostrarTodos() {
		FragmentLista listaPolid = (FragmentLista) getSupportFragmentManager().findFragmentById(R.id.fragment1);
		listaPolid.mostrarTodos();
		
	}


	private void mostrarMensaje(int pCual){
		
		AlertDialog.Builder mensaje = new AlertDialog.Builder(this);
		
		if(pCual == 1){
			mensaje.setTitle(R.string.ModificarPolideportivo);
			mensaje.setMessage(R.string.MensajeCualModificar);
			
			final EditText txtNombrePoli = new EditText(getApplicationContext());
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
			txtNombrePoli.setLayoutParams(lp);
			mensaje.setView(txtNombrePoli);
			
			mensaje.setPositiveButton(R.string.Aceptar, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int whichButton) {
					Intent intModificar = new Intent(getApplicationContext(), AdminInsertar.class);
					intModificar.putExtra("Tipo", "Modificar");
					intModificar.putExtra("NombreGestion",txtNombrePoli.getText().toString());
					startActivity(intModificar);
				}
			});
			
		}
		else{	
			mensaje.setTitle(R.string.BorrarPolideportivo);
			mensaje.setMessage(R.string.MensajeCualBorrar);
			
			final EditText txtNombrePoli = new EditText(this);			
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
			txtNombrePoli.setLayoutParams(lp);	
			mensaje.setView(txtNombrePoli);
			
			mensaje.setPositiveButton(R.string.Aceptar, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int whichButton) {
					LaBD.getMiBD(getApplicationContext()).eliminar(txtNombrePoli.getText().toString());
				}
			});
			
		}
		mensaje.show();
		mostrarTodos();
	}

}
