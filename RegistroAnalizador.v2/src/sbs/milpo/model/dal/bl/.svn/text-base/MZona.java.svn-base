package sbs.milpo.model.dal.bl;

import sbs.milpo.controller.Sistema;
import sbs.milpo.database.DataBaseConeccion;
import sbs.milpo.entity.ZonaAdapter;
import sbs.milpo.model.dal.Zona;
import android.content.Context;
import android.database.Cursor;


public class MZona {
	Context context;
	DataBaseConeccion db;
	
	public MZona(Context contextParent){
		context= contextParent;
		db= new DataBaseConeccion(context);
	}

	/**Obtiene las zonas por unidad minera y codigo empresa*/
    public Zona[] getZonas(String CODIGO_UNIDAD, String CODIGO_EMPRESA){

    	//DataBaseConeccion db= new DataBaseConeccion(this);
    	ZonaAdapter adapter= new ZonaAdapter();
    	Zona oZona[]= null;
    	try {
    		db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria
    		Cursor cur=null;
    		cur= adapter.fetchByUnidadEmpresa(db.getDataBase(), CODIGO_UNIDAD,  CODIGO_EMPRESA);
	    	if (cur.getCount()!=0){
	    		oZona= new Zona[cur.getCount()];	
	    		cur.moveToFirst();
	    		int i=0;
			         while (cur.isAfterLast() == false) {
			        	 oZona[i]= new Zona();
			        	 oZona[i].setId(cur.getString(0) );
			        	 oZona[i].setCodigo_zona(cur.getString(1) );
			        	 oZona[i].setCodigo_tipoZona(cur.getString(2) );
			        	 oZona[i].setCodigo_empresa(cur.getString(3) );
			        	 oZona[i].setCodigo_unidadMinera(cur.getString(4) );
			        	 oZona[i].setDescripcion(cur.getString(5) );
			        	 oZona[i].setEstado(cur.getString(6) );
			        	 cur.moveToNext();
			        	 i++;
			         }
			         cur.close();
	    	}
    	} catch (Exception e) {
    	}finally {
            db.close();
        }
    
    return oZona;
    
    }

  
}
