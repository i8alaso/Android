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
		db.execSQL("CREATE TABLE Usuarios ('idUsuario' TEXT PRIMARY KEY, 'Password' TEXT, 'Localidad' TEXT, 'Calle' TEXT, 'PolideportivoFavorito' TEXT, FOREIGN KEY (PolideportivoFavorito) REFERENCES Polideportivos(Nombre))");
	
		db.execSQL("INSERT INTO 'Polideportivos' (Nombre, Localidad, Calle, Deporte, Latitud, Longitud, Precio) values ('TXURDINAGA', 'Bilbao', 'Circo Amateur ', 'Futbol', 43.2503252, -2.90823,4.79)" );
		db.execSQL("INSERT INTO 'Polideportivos' (Nombre, Localidad, Calle, Deporte, Latitud, Longitud, Precio) values ('FANGO', 'Bilbao', 'Errekalde Errepidea, 52', 'Futbol', 43.2462907, -2.9341461, 5.54)" );
		db.execSQL("INSERT INTO 'Polideportivos' (Nombre, Localidad, Calle, Deporte, Latitud, Longitud, Precio) values ('SAN IGNACIO', 'Bilbao', 'Calle Orixe', 'Futbol', 43.280619, -2.95973, 3.58)" );
		db.execSQL("INSERT INTO 'Polideportivos' (Nombre, Localidad, Calle, Deporte, Latitud, Longitud, Precio) values ('SAN MAMES', 'Bilbao', 'Calle Pichichi', 'Futbol', 43.2637653,-2.9489244, 25.0)" );
		db.execSQL("INSERT INTO 'Polideportivos' (Nombre, Localidad, Calle, Deporte, Latitud, Longitud, Precio) values ('MENDIZORROZA', 'Gasteiz', 'Paseo de Cervantes', 'Futbol', 42.837182,-2.688043, 18.50)" );
		
		db.execSQL("INSERT INTO 'Polideportivos' (Nombre, Localidad, Calle, Deporte, Latitud, Longitud, Precio) values ('BILBAO ARENA', 'Bilbao', 'Miribilla', 'Baloncesto', 42.86434, -2.64391, 25.0)" );
		db.execSQL("INSERT INTO 'Polideportivos' (Nombre, Localidad, Calle, Deporte, Latitud, Longitud, Precio) values ('ILLUMBE', 'Donosti', 'Paseo de Miramón, 2', 'Baloncesto', 43.2985502,-1.9706287, 35.0)" );
		db.execSQL("INSERT INTO 'Polideportivos' (Nombre, Localidad, Calle, Deporte, Latitud, Longitud, Precio) values ('BUESA ARENA', 'Gasteiz', 'Calle Portal', 'Baloncesto', 42.86434,-2.64391, 37.0)" );
		
		db.execSQL("INSERT INTO 'Usuarios' (idUsuario, Password, Localidad, Calle, PolideportivoFavorito) values ('admin', 'admin', 'null', 'null', 'null')" );
		db.execSQL("INSERT INTO 'Usuarios' (idUsuario, Password, Localidad, Calle, PolideportivoFavorito) values ('user', 'user', 'Barakaldo', 'c/ Juan', 'Miribilla')" );
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	public void insertarPolideportivo(String pNombre, String pLocalidad, String pCalle, String pDeporte, Double pLongitud, Double pLatitud, Double pPrecio){
		String sql = "INSERT INTO 'Polideportivos' (Nombre, Localidad, Calle, Deporte, Latitud, Longitud, Precio) VALUES ('" + pNombre +"','"
					+ pLocalidad + "', '" + pCalle + "','" + pDeporte + "'," + pLatitud + ","+ pLongitud + ","+ pPrecio +")";
		this.db.execSQL(sql);	
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
		this.db.execSQL(sql);
	}
	
	public void eliminarTodo(){
		String sql = "DELETE FROM Polideportivos";
		this.db.execSQL(sql);

	}

	public void obtenerCoordenadas(String pNombre) {
		String sql = "SELECT Longitud,Latitud FROM Polideportivos WHERE Nombre = '" + pNombre + "'";
		this.db.execSQL(sql);
		
	}
	
	public Cursor buscarUsuario(String pUsuario){
		String sql = "SELECT * FROM Usuarios WHERE idUsuario = '" + pUsuario + "'" ;
		return db.rawQuery(sql, null);
	}
	
	public Cursor seleccionarContraseña(String pUsuario){
		String sql = "SELECT Password FROM Usuarios WHERE idUsuario = '" + pUsuario + "'" ;
		return db.rawQuery(sql, null);
	}
	
	public Cursor comprobarSiExisteUsuario(String pNombre){
		String sql = "SELECT COUNT (*) FROM Usuarios WHERE idUsuario = '" + pNombre + "'"; 
		return db.rawQuery(sql, null);
	}
	
	public void insertarUsuario(String pNombre, String pLocalidad, String pCalle, String pPolideportivo, String pPassword){
		String sql = "INSERT INTO 'Usuarios' (IdUsuario, Password, Localidad, Calle, PolideportivoFavorito) VALUES ('" + pNombre +"','"
				+ pPassword + "','" + pLocalidad + "', '" + pCalle + "','" + pPolideportivo + "')";
		this.db.execSQL(sql);
	}
	
	public void eliminarUsuario(String pNombre) {
		String sql = "DELETE FROM Usuarios WHERE idUsuario ='" + pNombre + "'";	
		this.db.execSQL(sql);
	}
	
	
	
}
