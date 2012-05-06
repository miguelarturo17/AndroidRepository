package sbs.milpo.model.dal.bl;

import sbs.milpo.controller.Sistema;
import sbs.milpo.database.DataBaseConeccion;
import sbs.milpo.entity.ConfiguracionAdapter;
import sbs.milpo.model.dal.Configuracion;
import android.content.Context;
import android.database.Cursor;

	
	
	public class MConfiguracion {
		private Context context;
		DataBaseConeccion db;
		
	public MConfiguracion(Context contextParent){
			//ESTABLECEMOS LA CONEXION Y LAS PROPIEDADES DE LA INTERFAZ
			context= contextParent;
			db= new DataBaseConeccion(context);
	}
	

	    
	    /**OBTIENE EL VALOR EN EL CAMPO CONFIGURACION DE ACUERDO A LA CLAVE INGRESADA*/
	    public String getValorConfiguracion(String clave){
			
			//DataBaseConeccion db= new DataBaseConeccion(context);
			ConfiguracionAdapter adapter= new ConfiguracionAdapter();
			String strURL="";
			
			try {
				db.open(Sistema.TIPO_ACCESO_BD);
	     		Cursor cur= adapter.fetch(db.getDataBase(),clave);
	 	    	if (cur.getCount()!=0){
	 	    		cur.moveToFirst();
	 			         while (cur.isAfterLast() == false) {
	 			        	strURL=cur.getString(3);//conf.setValor(cur.getString(3));
	 			        	cur.moveToNext();
	 			         }
	 			         cur.close();
	 	    	}
	     	} catch (Exception e) {
	     	}finally {
	            db.close();
	        }
			return strURL;
		}
	    
	    
	    /**REGISTRA LOS CAMBIOS EN LA TABLA CONFIGURACION*/
	    public boolean guardarValorConfiguracion(String clave, Configuracion config) {
	    	
	    	//DataBaseConeccion db= new DataBaseConeccion(this);
	    	ConfiguracionAdapter confAdapter= new ConfiguracionAdapter();
	    	boolean check=false;
	    	try {
	    		db.open(Sistema.TIPO_ACCESO_BD);
	    		check= confAdapter.update(db.getDataBase(),
	    					clave, 
	    					"",//Descripcion 
	    					"",//Label
	    					config.getValor(),//Valor 
	    					""//Estado
	    					);
	    	}catch (Exception e) {
	    	}finally {
	            db.close();
	        }
	    	return check;
		}
	    
	  
	}
