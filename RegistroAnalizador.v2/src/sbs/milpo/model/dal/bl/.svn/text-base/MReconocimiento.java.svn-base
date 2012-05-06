package sbs.milpo.model.dal.bl;

import sbs.milpo.controller.Sistema;
import sbs.milpo.database.DataBaseConeccion;
import sbs.milpo.entity.CITipoReconocimientoAdapter;
import sbs.milpo.model.dal.TipoReconocimiento;
import android.content.Context;
import android.database.Cursor;


public class MReconocimiento {
	Context context;
	DataBaseConeccion db;
	
	public MReconocimiento(Context contextParent){
		context= contextParent;
		db= new DataBaseConeccion(context);
	}

	/**OBTENEMOS UN ARRAY CON LOS VALORES DE LA TABLA TIPO_RECONOCIMIENTO*/
    public TipoReconocimiento[] getTipoReconocimiento(){
    	
	    CITipoReconocimientoAdapter adapter= new CITipoReconocimientoAdapter();
	    TipoReconocimiento[] oTipo=null;
    	try {
    		db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria
        	Cursor cur=null;
    		cur= adapter.fetchAll(db.getDataBase());
	    	if (cur.getCount()!=0){
	    		oTipo= new TipoReconocimiento[cur.getCount()];
	    		cur.moveToFirst();
	    		int i=0;
			         while (cur.isAfterLast() == false) {
			        	 oTipo[i]= new TipoReconocimiento();
			        	 oTipo[i].setId(cur.getString(0));
			        	 oTipo[i].setCodigo_TipoReconocimiento(cur.getString(1));
			        	 oTipo[i].setEstado(cur.getString(2));
			        	 oTipo[i].setTipo_Reconocimiento_Desc(cur.getString(3));
			        	 cur.moveToNext();
			        	 i++;
			         }
			         cur.close();
	    	}
    	} catch (Exception e) {
    	}finally {
            db.close();
        }
    	return oTipo;
    }

  
}
