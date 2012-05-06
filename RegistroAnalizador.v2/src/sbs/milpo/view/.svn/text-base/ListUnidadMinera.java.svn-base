package sbs.milpo.view;

import greendroid.app.GDListActivity;
import sbs.milpo.controller.Sistema;
import sbs.milpo.database.DataBaseConeccion;
import sbs.milpo.entity.UnidadMineraAdapter;
import sbs.milpo.model.dal.UnidadMinera;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Purpose: Muestra una lista en su forma mas elemental. Funcionalidades basicas.
 * @author SIMBIOSYS SOFTWARE S.A.C.   
 * @version 1.0 4/20/11
 * @see proyecto: SIMBIOSYS SOFTWARE LIBRARY.  
 */

public class ListUnidadMinera extends GDListActivity {
	//TextView selection;
	UnidadMinera oUnidad[]= new UnidadMinera[1000]; 
	
    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		try {
			
			fillDataList(getListUnidadMinera());
		} catch (Exception e) {
			new AlertDialog.Builder(this).setMessage(R.string.error_View).setTitle("Exception").show();
		}
	}
	
	public void onListItemClick(ListView parent, View v, int position,long id) {
		
		/*Intent intent = new Intent(this, SincRecepcionController.class);
		startActivity(intent);*/
		try {
			Intent intent = new Intent(this, ListViewGrid.class);
			intent.putExtra(greendroid.app.ActionBarActivity.GD_ACTION_BAR_TITLE, "Lecturas");
			intent.putExtra("CODIGO_UNIDAD", oUnidad[position].getCodigo_unidadMinera());
			intent.putExtra("CODIGO_EMPRESA", oUnidad[position].getCodigo_empresa());
	        startActivity(intent);
        
		} catch (Exception e) {
			new AlertDialog.Builder(this).setMessage("no se pudo cargar ciertos componentes").setTitle("Exception").show();
		}
		
		
		
		
    }
	

	public Cursor getListUnidadMinera(){
		
		UnidadMineraAdapter adapter= new UnidadMineraAdapter();
		DataBaseConeccion db= new DataBaseConeccion(this);
    	Cursor cur=null;
    	
    	try {
    		db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria
    		cur= adapter.fetchAll(db.getDataBase());
	    	if (cur!=null){
	    		
	    		cur.moveToFirst();
	    			int i=0;
			         while (cur.isAfterLast() == false) {
			        	 
			        	 oUnidad[i]= new UnidadMinera();
			        	 oUnidad[i].setId(cur.getString(0));
			        	 oUnidad[i].setCodigo_unidadMinera(cur.getString(1));
			        	 oUnidad[i].setCodigo_empresa(cur.getString(2));
			        	 oUnidad[i].setDescripcion(cur.getString(3));
			        	 oUnidad[i].setEstado(cur.getString(4));
			        	 i++;
			        	 cur.moveToNext();
			         }
			         cur.close();
			         
			         //volvemos a cargas los datos para enviarlos a la lista en la interfaz
			         cur= adapter.fetchAll(db.getDataBase());
	    	}
         
    	} catch (Exception e) {
    	}finally {
            db.close();
        }
    	
    	return cur;
		
	}
	
	private void fillDataList(Cursor c) {
		startManagingCursor(c);

		String[] fromCol = new String[] {UnidadMineraAdapter.KEY_UNIDAD_MINERA_DSC };
		int[] toRow = new int[] { R.id.textRow };
	        
		SimpleCursorAdapter items = new SimpleCursorAdapter(this, R.layout.list_row, c, fromCol, toRow);
		setListAdapter(items);
	}


}
