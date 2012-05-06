package sbs.milpo.model.dal.bl;

import sbs.milpo.controller.Sistema;
import sbs.milpo.database.DataBaseConeccion;
import sbs.milpo.entity.CIElementoUnidadAdapter;
import sbs.milpo.entity.UnidadMineraAdapter;
import sbs.milpo.model.dal.ElementoUnidad;
import sbs.milpo.model.dal.UnidadMinera;
import android.content.Context;
import android.database.Cursor;


public class MUnidadMinera {
	Context context;
	DataBaseConeccion db;
	
	public MUnidadMinera(Context contextParent){
		context = contextParent;
		db = new DataBaseConeccion(context);
	}

	
	
	/**OBTIENE LA UNIDAD MINERA HABILITADA*/
    public UnidadMinera getUnidadMinera(){
		
		UnidadMineraAdapter adapter= new UnidadMineraAdapter();
		//DataBaseConeccion db= new DataBaseConeccion(context);
		UnidadMinera oUnidad=null;
    	Cursor cur=null;
    	
    	try {
    		db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria
	    	cur= adapter.fetchByEstado(db.getDataBase(), Sistema.HABILITADO);
	    	if (cur!=null){
	    		cur.moveToFirst();
			     oUnidad= new UnidadMinera();
			     oUnidad.setId(cur.getString(0));
			     oUnidad.setCodigo_unidadMinera(cur.getString(1));
			     oUnidad.setCodigo_empresa(cur.getString(2));
			     oUnidad.setDescripcion(cur.getString(4));
			     oUnidad.setEstado(cur.getString(5));
			     cur.close();
	    	}
    	} catch (Exception e) {
    		
    	}finally {
            db.close();
        }
    	return oUnidad;
	}
	
	
	/**OBTIENE UN ARRAY CON LOS VALORES DE LA TABLA ELEMENTO_UNIDAD*/
    public ElementoUnidad[] getElementosUnidad(String CODI_EMPRE, String CODI_UNMI ){
    	
    	CIElementoUnidadAdapter adapter= new CIElementoUnidadAdapter();
    	ElementoUnidad[] oElemUnidad=null;// new ElementoUnidad();
    	//DataBaseConeccion db= new DataBaseConeccion(context);
    	try {
    		db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria
    		Cursor cur=null;
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
    
    
    /**OBTIENE TODAS LAS UNIDADES MINERAS DE LA TABLA UNIDA_MINERA*/
    public UnidadMinera[] getListUnidadMinera(){
		
		UnidadMinera oUnidad[]= null;
		UnidadMineraAdapter adapter= new UnidadMineraAdapter();
		//DataBaseConeccion db= new DataBaseConeccion(context);
    	
    	try {
    		db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria
        	Cursor cur=null;
    		cur= adapter.fetchAll(db.getDataBase());
	    	if (cur!=null){
	    		oUnidad = new UnidadMinera[cur.getCount()];
	    		cur.moveToFirst();
	    			int i=0;
			         while (cur.isAfterLast() == false) {
			        	 
			        	 oUnidad[i]= new UnidadMinera();
			        	 oUnidad[i].setId(cur.getString(0));
			        	 oUnidad[i].setCodigo_unidadMinera(cur.getString(1));
			        	 oUnidad[i].setCodigo_empresa(cur.getString(2));
			        	 oUnidad[i].setDescripcion(cur.getString(4));
			        	 oUnidad[i].setEstado(cur.getString(5));
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
    	return oUnidad;
	}
    

    /** Registra las tablas estaticas obtenidas*/
    public boolean actualizarUnidadMinera( UnidadMinera[] unidadMinera, int escogido){
    	
    	//DataBaseConeccion db= new DataBaseConeccion(context);
    	UnidadMineraAdapter unidadMineraAdapter= new UnidadMineraAdapter();
    	boolean check=false;
    	
    	try {
    		db.open(Sistema.TIPO_ACCESO_BD);
        	db.beginTransaction();
        	
    		for (int i=0;i<unidadMinera.length;i++){
    			if(escogido!=i){
    			check= unidadMineraAdapter.update(db.getDataBase(), 
    					String.valueOf(i+1),
	    				"",//CODI_UNMI, 
	    				"",//CODI_EMPR, 
	    				"",//UNIDAD_MINERA, 
	    				"",//UNIDAD_MINERA_DSC, 
	    				Sistema.INHABILITADO,//ESTADO, 
	    				"",//FECHA_MODIFICACION, 
	    				"",//FECHA_CREACION, 
	    				"",//USUA_MODIFICACION, 
	    				""//USUA_CREACION
	    				);
    			}
    			if(escogido==i){
    			check= unidadMineraAdapter.update(db.getDataBase(), 
        					String.valueOf(i+1),
    	    				"",//CODI_UNMI, 
    	    				"",//CODI_EMPR, 
    	    				"",//UNIDAD_MINERA, 
    	    				"",//UNIDAD_MINERA_DSC, 
    	    				Sistema.HABILITADO,//ESTADO, 
    	    				"",//FECHA_MODIFICACION, 
    	    				"",//FECHA_CREACION, 
    	    				"",//USUA_MODIFICACION, 
    	    				""//USUA_CREACION
    	    				);	
    			}
    		}
    		db.setSuccessful();
    	}
    	finally {
            db.finishTransaction();
            db.close();
        }
    	return check;
    }
  
}
