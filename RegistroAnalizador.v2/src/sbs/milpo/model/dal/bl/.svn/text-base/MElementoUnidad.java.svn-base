package sbs.milpo.model.dal.bl;

import sbs.milpo.controller.Sistema;
import sbs.milpo.database.DataBaseConeccion;
import sbs.milpo.entity.CIElementoUnidadAdapter;
import sbs.milpo.model.dal.ElementoUnidad;
import android.content.Context;
import android.database.Cursor;


public class MElementoUnidad {
	Context context;
	DataBaseConeccion db;
	
	public MElementoUnidad(Context contextParent){
		context= contextParent;
		db= new DataBaseConeccion(context);
	}

    /**OBTENEMOS UN ARRAY CON LOS VALORES DE LA TABLA ELEMENTO_UNIDAD*/
    public ElementoUnidad[] getElementoUnidad(String CODI_EMPRE, String CODI_UNMI ){
    	
    	CIElementoUnidadAdapter adapter= new CIElementoUnidadAdapter();
    	ElementoUnidad[] oElemUnidad=null;// new ElementoUnidad();
    	//DataBaseConeccion db= new DataBaseConeccion(context);
    	db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria
    	Cursor cur=null;
    	try {
    		cur= adapter.fetchByEmpresaUnidad(db.getDataBase(), CODI_EMPRE, CODI_UNMI);
	    	
	    	if (cur.getCount()!=0){
	    		oElemUnidad= new ElementoUnidad[cur.getCount()];
	    		cur.moveToFirst();
	    		int i=0;
			         while (cur.isAfterLast() == false) {
			        	 oElemUnidad[i]= new ElementoUnidad();
			        	 oElemUnidad[i].setId(cur.getString(0));
			        	 oElemUnidad[i].setCodigo_empresa(cur.getString(2));
			        	 oElemUnidad[i].setCodigo_unidadMinera(cur.getString(3));
			        	 oElemUnidad[i].setOrden(cur.getString(4));
			        	 oElemUnidad[i].setCodigo_elementoQuimico(cur.getString(5));
			        	 oElemUnidad[i].setCodigo_unidadMedida(cur.getString(6));
			        	 oElemUnidad[i].setEstado(cur.getString(7));
			        	 oElemUnidad[i].setValorMaximo(cur.getString(12));
			        	 oElemUnidad[i].setValorMinimo(cur.getString(13));
			        	 oElemUnidad[i].setValorEntero(cur.getString(14));
			        	 oElemUnidad[i].setValorDecimal(cur.getString(15));
			        	 cur.moveToNext();
			        	 i++;
			         }
			         cur.close();
	    	}
         
    	} catch (Exception e) {
    	}finally {
            db.close();
        }
    	
    	return oElemUnidad;
    }

  
}
