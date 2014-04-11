package org.das.sportsgestion;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class LaBD extends SQLiteOpenHelper{

	private static LaBD miLaBD;
	private SQLiteDatabase db = getWritableDatabase();
	
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
		db.execSQL("CREATE TABLE Polideportivos ('Nombre' TEXT PRIMARY KEY,'Localidad' TEXT, 'Calle' TEXT, 'Deporte' TEXT, 'Latitud' DOUBLE, 'Longitud' DOUBLE, 'Precio' DOUBLE)");
		db.execSQL("CREATE TABLE Coche ('Codigo' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'Nombre' TEXT)");
		
		db.execSQL("INSERT INTO 'Polideportivos' (Nombre, Localidad, Calle, Deporte, Precio) values ('TXURDINAGA', 'Bilbao 48004', 'c/ Radio', 'Futbol', 5.54)" );
		db.execSQL("INSERT INTO 'Polideportivos' (Nombre, Localidad, Calle, Deporte) values ('FANGO REKALDE', 'Bilbao', '', 'Futbol')" );
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	public void insertarPolideportivo(String pNombre, String pLocalidad, String pCalle, String pDeporte, Double pLongitud, Double pLatitud, Double pPrecio){
		String sql = "INSERT INTO 'Polideportivos' (Nombre, Localidad, Calle, Deporte, Latitud, Longitud, Precio) VALUES ('" + pNombre +"','"
					+ pLocalidad + "', '" + pCalle + "'," + "', '" + pDeporte + "'," + pLatitud + ","+ pLongitud + ","+ pPrecio +")";
		this.db.execSQL(sql);
	}
	
	
	
	public Cursor buscarPolideportivo(String pLocalidad, String pDeporte){
		String sql = "SELECT * FROM Polideportivos WHERE Localidad = '" + pLocalidad + "' AND Deporte = '" + pDeporte + "'" ;
		return db.rawQuery(sql, null);
	}
	
	public Cursor seleccionarPolideportivo(String pNombre){
		String sql = "SELECT * FROM Polideportivos WHERE Nombre = '" + pNombre + "'";
		return db.rawQuery(sql, null);
	}
	
	
	

	public void eliminar(String pNombre) {
		String sql = "DELETE FROM Polideportivos WHERE Nombre ='" + pNombre + "'";
		this.db.execSQL(sql);
	}
	
	public void eliminarTodo(){
		String sql = "DELETE * FROM Polideportivos";
		this.db.execSQL(sql);
	}
}
