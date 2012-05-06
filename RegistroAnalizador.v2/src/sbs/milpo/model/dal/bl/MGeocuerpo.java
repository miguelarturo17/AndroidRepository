package sbs.milpo.model.dal.bl;

import sbs.milpo.controller.Sistema;
import sbs.milpo.database.DataBaseConeccion;
import sbs.milpo.entity.GeoCuerpoAdapter;
import sbs.milpo.model.dal.GeoCuerpo;
import android.content.Context;
import android.database.Cursor;


public class MGeocuerpo {
	Context context;
	DataBaseConeccion db;
	
	public MGeocuerpo(Context contextParent){
		context= contextParent;
		db= new DataBaseConeccion(context);
	}

	/**OBTENEMOS UN ARRAY CON LOS VALORES DE LA TABLA GEO_CUERPO*/
    public GeoCuerpo[] getGeoCuerpo(String CODIGO_UNIDAD){
    	
    	GeoCuerpoAdapter adapter= new GeoCuerpoAdapter();
    	GeoCuerpo[] oCuerpo=null;
    	//DataBaseConeccion db= new DataBaseConeccion(context);
    	try {
    		db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria
        	Cursor cur=null;
    		cur= adapter.fetchByUnidad(db.getDataBase(), CODIGO_UNIDAD );
	    	if (cur.getCount()!=0){
	    		oCuerpo= new GeoCuerpo[cur.getCount()];
	    		cur.moveToFirst();
	    		int i=0;
			         while (cur.isAfterLast() == false) {
			        	 oCuerpo[i]= new GeoCuerpo();
			        	 oCuerpo[i].setId(cur.getString(0));
			        	 oCuerpo[i].setCodigo_cuerpo(cur.getString(1));
			        	 oCuerpo[i].setCodigo_empresa(cur.getString(2));
			        	 oCuerpo[i].setCodigo_unidadminera(cur.getString(3));
			        	 oCuerpo[i].setNro_cuerpo(cur.getString(4));
			        	 oCuerpo[i].setEstado(cur.getString(5));
			        	 cur.moveToNext();
			        	 i++;
			         }
			         cur.close();
	    	}
    	} catch (Exception e) {
    	}finally {
            db.close();
        }
    	
    	return oCuerpo;
    }

  
}
