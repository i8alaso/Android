package org.das.sportsgestion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;










import android.animation.AnimatorSet.Builder;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BusquedaConfirmar extends FragmentActivity {
	
	private static TextView txtDeporte;

	private static TextView txtNombre, txtCalle, txtLocalidad, txtPrecio;

	
	private static String nombre;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_busquedaconfirmar);
		
//		txtNombre = (TextView) findViewById(R.id.txtNombre);
//		txtDeporte = (TextView) findViewById(R.id.txtInstalaciones);
//		txtDeporte.setBackgroundColor(Color.LTGRAY);
//		txtDeporte.setTextColor(Color.WHITE);
//		txtLocalidad = (TextView) findViewById(R.id.txtLocalidad);
//		txtCalle = (TextView) findViewById(R.id.txtCalle);
//		txtCalle.setBackgroundColor(Color.GRAY);
//		txtPrecio = (TextView) findViewById(R.id.txtPrecio);
		
		nombre = getIntent().getExtras().getString("Nombre");

		Toast.makeText(getApplicationContext(), nombre, 4511).show();
		actualizarCampos();
	}
	
	private void actualizarCampos(){	
		FragmentDetails details = (FragmentDetails) getSupportFragmentManager().findFragmentById(R.id.fragment2);
		details.actualizarCampos(nombre);
	}
}
	

