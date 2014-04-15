package org.das.sportsgestion;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class LaBD extends SQLiteOpenHelper{

	private static LaBD miLaBD;
	private SQLiteDatabase db = getWritableDatabase();
//	private ArrayList<String> listaPolideportivos;
	
	private LaBD(Context context, String name, CursorFactory factory, int version)  {
		super(context, name, factory, version);
	}
	
	public static LaBD getMiBD(Context context) {
		if(miLaBD == null) {
			miLaBD = new LaBD(context, "aDataBase", null, 1);
		 
		}
		
		return miLaBD;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
//		listaPolideportivos = new ArrayList<String>();
		
		db.execSQL("CREATE TABLE Polideportivos ('Nombre' TEXT PRIMARY KEY,'Localidad' TEXT, 'Calle' TEXT, 'Deporte' TEXT, 'Latitud' DOUBLE, 'Longitud' DOUBLE, 'Precio' DOUBLE)");
		db.execSQL("CREATE TABLE Coche ('Codigo' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'Nombre' TEXT)");
		
		db.execSQL("INSERT INTO 'Polideportivos' (Nombre, Localidad, Calle, Deporte, Latitud, Longitud, Precio) values ('TXURDINAGA', 'Bilbao', 'Calle Circo Amateur del Club ', 'Futbol', 43.2503252, -2.90823,4.79)" );
		db.execSQL("INSERT INTO 'Polideportivos' (Nombre, Localidad, Calle, Deporte, Latitud, Longitud, Precio) values ('FANGO', 'Bilbao', 'Errekalde-Larraskitu Errepidea, 52', 'Futbol', 43.2462907, -2.9341461, 5.54)" );
		db.execSQL("INSERT INTO 'Polideportivos' (Nombre, Localidad, Calle, Deporte, Latitud, Longitud, Precio) values ('SAN IGNACIO', 'Bilbao', 'Calle Orixe', 'Futbol', 43.280619, -2.95973, 3.58)" );
		db.execSQL("INSERT INTO 'Polideportivos' (Nombre, Localidad, Calle, Deporte, Latitud, Longitud, Precio) values ('SAN MAMES', 'Bilbao', 'Calle de Rafael Moreno Pichichi', 'Futbol', 43.2637653,-2.9489244, 25.0)" );
		db.execSQL("INSERT INTO 'Polideportivos' (Nombre, Localidad, Calle, Deporte, Latitud, Longitud, Precio) values ('MENDIZORROZA', 'Gasteiz', 'Paseo de Cervantes', 'Futbol', 42.837182,-2.688043, 18.50)" );
		
		db.execSQL("INSERT INTO 'Polideportivos' (Nombre, Localidad, Calle, Deporte, Latitud, Longitud, Precio) values ('BILBAO ARENA', 'Bilbao', '', 'Baloncesto', 42.86434, -2.64391, 25.0)" );
		db.execSQL("INSERT INTO 'Polideportivos' (Nombre, Localidad, Calle, Deporte, Latitud, Longitud, Precio) values ('ILLUMBE', 'Donosti', 'Paseo de Miramón, 2', 'Baloncesto', 43.2985502,-1.9706287, 35.0)" );
		db.execSQL("INSERT INTO 'Polideportivos' (Nombre, Localidad, Calle, Deporte, Latitud, Longitud, Precio) values ('BUESA ARENA', 'Gasteiz', 'Calle Portal de Zurbano', 'Baloncesto', 42.86434,-2.64391, 37.0)" );
		
//		listaPolideportivos.add("TXURDINAGA");
//		listaPolideportivos.add("FANGO");
//		listaPolideportivos.add("SAN MAMES");
//		listaPolideportivos.add("SAN IGNACIO");
//		listaPolideportivos.add("MENDIZORROZA");
//		listaPolideportivos.add("ILLUMBE");
//		listaPolideportivos.add("BILBAO ARENA");
//		listaPolideportivos.add("BUESA ARENA");
		
		
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	public void insertarPolideportivo(String pNombre, String pLocalidad, String pCalle, String pDeporte, Double pLongitud, Double pLatitud, Double pPrecio){
		String sql = "INSERT INTO 'Polideportivos' (Nombre, Localidad, Calle, Deporte, Latitud, Longitud, Precio) VALUES ('" + pNombre +"','"
					+ pLocalidad + "', '" + pCalle + "','" + pDeporte + "'," + pLatitud + ","+ pLongitud + ","+ pPrecio +")";
		this.db.execSQL(sql);
		//listaPolideportivos.add("pNombre");
		
	}
	
	public void modificarPolideportivo(String pClave, String pNombre, String pLocalidad, String pCalle, String pDeporte, Double pLongitud, Double pLatitud, Double pPrecio){
		String sql = "UPDATE 'Polideportivos' SET Nombre = '" + pNombre + "', Localidad='"+ pLocalidad +"', Calle ='" + pCalle + "', Deporte = '"+ pDeporte +
				"', Latitud=" + pLatitud + ", Longitud=" + pLongitud + ", Precio = " + pPrecio + " WHERE Nombre = '" + pClave +"'";
		this.db.execSQL(sql);
	}
	
	
	
	public Cursor buscarPolideportivo(String pLocalidad, String pDeporte){
		String sql = "SELECT * FROM Polideportivos WHERE Localidad = '" + pLocalidad + "' AND Deporte = '" + pDeporte + "'" ;
		return db.rawQuery(sql, null);
	}
	
	public Cursor seleccionarPolideportivo(String pNombre){
		String sql = "SELECT * FROM Polideportivos";

		if(pNombre != null){
			sql = "SELECT * FROM Polideportivos WHERE Nombre = '" + pNombre + "'";			
		}
		return db.rawQuery(sql, null);
	}
	
	public Cursor comprobarSiExiste(String pNombre){
		String sql = "SELECT COUNT (*) FROM Polideportivos WHERE Nombre = '" + pNombre + "'"; 
		return db.rawQuery(sql, null);
	}
	
	

	public void eliminar(String pNombre) {
		String sql = "DELETE FROM Polideportivos WHERE Nombre ='" + pNombre + "'";
		boolean encontrado = false;
		int cont = 0;
		
//		while (cont < listaPolideportivos.size()-1 && !encontrado){
//			if(listaPolideportivos.get(cont) == pNombre){
//				encontrado = true;
//			}
//			else{
//				cont++;
//			}
//		}
//		
//		listaPolideportivos.remove(cont);
		
		this.db.execSQL(sql);
	}
	
	public void eliminarTodo(){
		String sql = "DELETE FROM Polideportivos";
		this.db.execSQL(sql);
		
//		listaPolideportivos.clear();
	}

	public void obtenerCoordenadas(String pNombre) {
		String sql = "SELECT Longitud,Latitud FROM Polideportivos WHERE Nombre = '" + pNombre + "'";
		this.db.execSQL(sql);
		
	}
}
