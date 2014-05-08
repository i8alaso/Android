package org.das.sportsgestion;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentLista extends ListFragment {

	public interface IListFragmentListener {
		void onItemSelected(String item);
	}
	
	private IListFragmentListener iLista;
	private ArrayList<String> datos;
	private ArrayAdapter<String> adaptador;
	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		iLista.onItemSelected(adaptador.getItem(position).toString());	
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			
		datos = new ArrayList<String>();
		adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, datos);
		setListAdapter(adaptador);
	}

		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_fragmentolista, container, false);
	}
	

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		try{
			iLista = (IListFragmentListener) activity;
		} catch (ClassCastException e) {}
		}


	/**
	 * Busca todos los polideportivos que tengan como localidad y deporte pLocalidad y pDeporte
	 * 
	 * @return void
	 */
	
	public void updateList(String pLocalidad, String pDeporte) {
		Cursor aCursor = LaBD.getMiBD(getActivity()).buscarPolideportivo(pLocalidad, pDeporte);
		adaptador.clear();
		String nombre = "";
		
		if(aCursor.moveToFirst()) {
			do {
				nombre = aCursor.getString(0);
				adaptador.add("POLIDEPORTIVO " + nombre);
			} while(aCursor.moveToNext());
		}
	}
	
	
	/**
	 * Busca todos los polideportivos que est�n en la BD
	 * 
	 * @return void
	 */
	
	public void mostrarTodos(){
		Cursor aCursor = LaBD.getMiBD(getActivity()).seleccionarPolideportivo(null);
		adaptador.clear();
		String nombre = "";
		
		if(aCursor.moveToFirst()) {
			do {
				nombre = aCursor.getString(0);
				adaptador.add("POLIDEPORTIVO " + nombre);
			} while(aCursor.moveToNext());
		}
	}
}
	
	

