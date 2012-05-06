package sbs.milpo.model.dal.bl;

import sbs.milpo.controller.Sistema;
import sbs.milpo.database.DataBaseConeccion;
import sbs.milpo.entity.GeolitologiaAdapter;
import sbs.milpo.model.dal.Geolitologia;
import android.content.Context;
import android.database.Cursor;


public class MGeolitologia {
	Context context;
	DataBaseConeccion db;
	
	public MGeolitologia(Context contextParent){
		context= contextParent;
		db= new DataBaseConeccion(context);
	}

	/**OBTENEMOS UN ARRAY CON LOS VALORES DE LA TABLA GEO_LITOLOGIA*/
    public Geolitologia[] getGeolitologia(String CODIGO_UNIDAD, String CODIGO_EMPRESA){
    	
    	GeolitologiaAdapter adapter= new GeolitologiaAdapter();
    	Geolitologia[] oLito=null;
    	//DataBaseConeccion db= new DataBaseConeccion(context);
    	try {
    		db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria
    		Cursor cur=null;
    		cur= adapter.fetchByEmpresaUnidad(db.getDataBase(), CODIGO_EMPRESA, CODIGO_UNIDAD );
	    	if (cur.getCount()!=0){
	    		oLito= new Geolitologia[cur.getCount()];
	    		cur.moveToFirst();
	    		int i=0;
			         while (cur.isAfterLast() == false) {
			        	 oLito[i]= new Geolitologia();
			        	 oLito[i].setId(cur.getString(0));
			        	 oLito[i].setCodigo_GeoLitologia(cur.getString(1));
			        	 oLito[i].setCodigoEmpresa(cur.getString(2));
			        	 oLito[i].setCodigoUnidadMinera(cur.getString(3));
			        	 oLito[i].setLitologia(cur.getString(4));
			        	 oLito[i].setLitologiaDesc(cur.getString(5));
			        	 oLito[i].setEstado(cur.getString(6));
			        	 cur.moveToNext();
			        	 i++;
			         }
			         cur.close();
	    	}
    	} catch (Exception e) {
    	}finally {
            db.close();
        }
    	
    	return oLito;
    }

  
}
