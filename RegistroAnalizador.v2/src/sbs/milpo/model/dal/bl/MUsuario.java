package sbs.milpo.model.dal.bl;

import sbs.milpo.controller.Sistema;
import sbs.milpo.database.DataBaseConeccion;
import sbs.milpo.entity.UsuarioAdapter;
import sbs.milpo.model.dal.Usuario;
import android.content.Context;
import android.database.Cursor;

	
	
	public class MUsuario {
		private Context context;
		DataBaseConeccion db;
		
	public MUsuario(Context contextParent){
		//ESTABLECEMOS LA CONEXION Y LAS PROPIEDADES DE LA INTERFAZ
		context= contextParent;
		db= new DataBaseConeccion(context);
			
	}
	
	
		/***OBTIENE TODOS LOS USUARIOS EN LA BD*/
	    public Usuario[] getUsuariosDataBase(){
	    	
	    	UsuarioAdapter adapter= new UsuarioAdapter();
	    	Usuario[] oUsuario=null; 
	    	Cursor cur=null;
	    	
	    	try {
	    		db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria
	    		cur= adapter.fetchAll(db.getDataBase());
		    	if (cur.getCount()!=0){
		    		 oUsuario= new Usuario[cur.getCount()];
			    	 cur.moveToFirst();
			    	 int i=0;
			         while (cur.isAfterLast() == false) {
			        	 oUsuario[i]= new Usuario();
			        	 oUsuario[i].setId(cur.getString(0) );
			        	 oUsuario[i].setCodigo_usuario(cur.getString(1) );
			        	 oUsuario[i].setCodigo_trabajador(cur.getString(2) );
			        	 oUsuario[i].setUsuario(cur.getString(3) );
			        	 oUsuario[i].setPassword(cur.getString(4) );
			        	 oUsuario[i].setEstado(cur.getString(5) );
			        	 //oUsuario[i].setAdministrador(cur.getString(11) );
			        	 cur.moveToNext();
			        	 i++;
			         }
			         cur.close();
		    	}
	    	} catch (Exception e) {
			}finally {
	            db.close();
	        }
	    	
	    	return oUsuario;
	    }
	
	    
	    /***VERIFICA LAS  CREDENCIALES EN LA BD PARA UN USUARIO*/
	    public Usuario getCredenciales(String usuario, String password){
	    	
	    	UsuarioAdapter adapter= new UsuarioAdapter();
	    	//DataBaseConeccion db= new DataBaseConeccion(context);
	    	Usuario uSesion=null; 
	    	Sistema.SESION_USUARIO= usuario;
			Sistema.SESION_PASSWORD= password;
	    	
	    	try {
	    		db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria
	    		Cursor cur=null;
	    		cur= adapter.fetchByPassword(db.getDataBase(), usuario,  password);
		    	if (cur.getCount()!=0){
		    	 cur.moveToFirst();
		    	 	 uSesion= new Usuario();
		        	 uSesion.setId(cur.getString(0) );
		        	 uSesion.setCodigo_usuario(cur.getString(1) );
		        	 uSesion.setCodigo_trabajador(cur.getString(2) );
		        	 uSesion.setUsuario(cur.getString(3) );
		        	 uSesion.setPassword(cur.getString(4) );
		        	 uSesion.setEstado(cur.getString(5) );
		        	 uSesion.setAdministrador(cur.getString(11) );
		         cur.close();
		    	}
	    	} catch (Exception e) {
	    	}finally {
	            db.close();
	        }
	    	
	    	return uSesion;
	    }
	    
	    
	  
	}
