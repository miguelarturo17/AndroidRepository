package sbs.milpo.model.dal.bl;

import sbs.milpo.controller.Fecha;
import sbs.milpo.controller.Sistema;
import sbs.milpo.database.DataBaseConeccion;
import sbs.milpo.entity.CIDetalleLecturaAdapter;
import sbs.milpo.entity.CILecturaAdapter;
import sbs.milpo.model.dal.DetalleLectura;
import sbs.milpo.model.dal.Lectura;
import android.content.Context;
import android.database.Cursor;

	
	
	public class MLectura {
		private Context context;
		DataBaseConeccion db;
		
	public MLectura(Context contextParent){
		//ESTABLECEMOS LA CONEXION Y LAS PROPIEDADES DE LA INTERFAZ
		context= contextParent;
		db= new DataBaseConeccion(context);
			
	}
	
	/** Registramos la perforacion en la tabla Lectura y DetalleLectura*/
    public boolean saveLecturaPerforacion(Lectura lectura){
    	
    	CILecturaAdapter lAdapter= new CILecturaAdapter();
    	CIDetalleLecturaAdapter dlAdapter= new CIDetalleLecturaAdapter();
    	long numeroInsercion=-1;
    	boolean checker=false;
    	
    	try {
    		db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria
    		db.beginTransaction();
    		numeroInsercion= lAdapter.insert(db.getDataBase(), 
    				"",//CODI_LECT, 
	    			"",//CODI_CIUS, 
	    			lectura.getCOD_EMPRESA(),//CODI_EMPR, 
	    			lectura.getCOD_UNIDAD(),//CODI_UNMI, 
	    			"",//CODI_TURN, 
	    			"TAL",//TIPO, 
	    			lectura.getCOD_ZONA(),//CODI_ZONA, //////////////////////////////////
	    			lectura.getPeso(), 
	    			lectura.getNroTaladro(),//NRO_TALADRO, 
	    			lectura.getProfundidad(),//PROFUNDIDAD, 
	    			Sistema.PENDIENTE, 
	    			Sistema.SESION_USUARIO,//USUA_CREACION, 
	    			lectura.getFechaCreacion(),//fechaString,//FECHA_CREACION, 
	    			Sistema.SESION_USUARIO,//USUA_MODIFICACION, 
	    			lectura.getFechaModificacion(),//FECHA_MODIFICACION, 
	    			"",//COSI_SICE
	    			lectura.getTipoReconocimiento(),//tipoReconocimiento = "";
	    			lectura.getValor(),//valor = "";
	    			lectura.getDesde(),//desde = "";
	    			lectura.getHasta(),//hasta = "";
	    			lectura.getLito(),//lito = "";
	    			lectura.getCodigo_cuerpo(),//geolocalizacion
	    			lectura.getFecha()//fechaEliminacion
	    			);

    	String lectura_id="";
    	try {
    		lectura_id= String.valueOf(numeroInsercion);
		} catch (Exception e) {
		}
    	
		    for(int i=0; i<lectura.detalleLecturaList.length;i++){
		    	dlAdapter.insert(db.getDataBase(),
		    			lectura_id,//fk
		    			"",//CODI_DLEC, 
		        		"",//CODI_LECT, 
		        		lectura.detalleLecturaList[i].getCodigo_elemento(),//eUnidad[i].getCodigo_elementoQuimico(),//CODI_ELEM, 
		        		lectura.detalleLecturaList[i].getCodigo_unidadMedida(),//eUnidad[i].getCodigo_unidadMedida(),//CODI_UNME, 
		        		lectura.detalleLecturaList[i].getValor(),//VALOR, 
		        		Sistema.PENDIENTE, 
		        		Sistema.SESION_USUARIO,//USUA_CREACION, 
		        		lectura.getFechaCreacion(),//FECHA_CREACION, 
		        		Sistema.SESION_USUARIO,//USUA_MODIFICACION, 
		        		lectura.getFechaModificacion()//FECHA_MODIFICACION
		        	);
		    	}
		    
		    db.setSuccessful();
		} catch (Exception e) {
		}finally {
			db.finishTransaction();
	        db.close();
	    }
    		
		if(numeroInsercion!=-1){
			checker=true;
		}
		
	return checker;
	
    }
	
		
    
    /** Registramos la perforacion en la tabla Lectura y DetalleLectura*/
    public boolean updateLecturaPerforacion(Lectura lectura){
    	
    	CILecturaAdapter lAdapter= new CILecturaAdapter();
    	CIDetalleLecturaAdapter dlAdapter= new CIDetalleLecturaAdapter();
    	//DataBaseConeccion db= new DataBaseConeccion(this);
    	boolean checker=false;//long numeroInsercion=0;
    	try {
    		db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria
    		db.beginTransaction();
    		checker= lAdapter.update(db.getDataBase(), 
    				lectura.getId(),
    				lectura.getCodigo_lectura(),//CODI_LECT, 
    				lectura.getCodigo_usuario(),//CODI_CIUS, 
    				"",//lectura.getCodigo_empresa(),//CODI_EMPR, 
    				"",//lectura.getCodigo_unidadMinera(),//CODI_UNMI, 
    				"",//lectura.getCodigo_turno(),//CODI_TURN, 
	    			"TAL",//TIPO, 
	    			lectura.getCOD_ZONA(),//CODI_ZONA, //////////////////////////////////
	    			lectura.getPeso(), 
	    			lectura.getNroTaladro(),//NRO_TALADRO, 
	    			lectura.getProfundidad(),//PROFUNDIDAD, 
	    			Sistema.PENDIENTE, 
	    			"",//USUA_CREACION, 
	    			"",//fechaString,//FECHA_CREACION, 
	    			Sistema.SESION_USUARIO,//USUA_MODIFICACION, 
	    			lectura.getFechaModificacion(),//FECHA_MODIFICACION, 
	    			"",//COSI_SICE
	    			lectura.getTipoReconocimiento(),//tipoReconocimiento = "";
	    			lectura.getValor(),//valor = "";
	    			lectura.getDesde(),//desde = "";
	    			lectura.getHasta(),//hasta = "";
	    			lectura.getLito(),//lito = "";
	    			lectura.getCodigo_cuerpo(),//geolocalizacion
	    			lectura.getFecha()
	    			);
    		
    		
    		try {
    			for(int i=0; i<lectura.detalleLecturaList.length;i++){
	    			dlAdapter.update(db.getDataBase(), 
	    					lectura.detalleLecturaList[i].getId(),
	    					lectura.getId(),//fk
	    					lectura.detalleLecturaList[i].getCodigo_detalleLectura(),//CODI_DLEC, 
	    					lectura.detalleLecturaList[i].getCodigo_lectura(),//CODI_LECT, 
	        				lectura.detalleLecturaList[i].getCodigo_elemento(),
	        				lectura.detalleLecturaList[i].getCodigo_unidadMedida(),
	        				lectura.detalleLecturaList[i].getValor(),//VALOR, 
	        				Sistema.PENDIENTE, 
	        				"",//USUA_CREACION, 
	        				"",//FECHA_CREACION, 
	        				Sistema.SESION_USUARIO,//USUA_MODIFICACION, 
	        				lectura.getFechaModificacion()//FECHA_MODIFICACION
	        				);
    			}
			} catch (Exception e) {
			}
	    	
			db.setSuccessful();
		} catch (Exception e) {
		}finally {
			db.finishTransaction();
			db.close();
        }
		
		return checker;
    }
	    
    
    /**REGISTRA LAS LECTURAS DE FAJA*/
    public boolean saveLecturaFaja(Lectura lectura){
    	
    	CILecturaAdapter lAdapter= new CILecturaAdapter();
    	CIDetalleLecturaAdapter dlAdapter= new CIDetalleLecturaAdapter();
    	//DataBaseConeccion db= new DataBaseConeccion(this);
    	long numeroInsercion=-1;
    	boolean checker=false;
    	
    	try {
    		db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria
    		db.beginTransaction();
	    	numeroInsercion= lAdapter.insert(db.getDataBase(), 
	    			"",//CODI_LECT, 
	    			"",//CODI_CIUS, 
	    			lectura.getCOD_EMPRESA(),//CODI_EMPR, 
	    			lectura.getCOD_UNIDAD(),//CODI_UNMI, 
	    			"",//CODI_TURN, 
	    			"FAJA",//TIPO, 
	    			lectura.getCOD_ZONA(),//CODI_ZONA, //////////////////////////////////
	    			lectura.getPeso(), 
	    			lectura.getNroTaladro(),//NRO_TALADRO, 
	    			lectura.getProfundidad(),//PROFUNDIDAD, 
	    			Sistema.PENDIENTE, 
	    			Sistema.SESION_USUARIO,//USUA_CREACION, 
	    			lectura.getFechaCreacion(),//fechaString,//FECHA_CREACION, 
	    			Sistema.SESION_USUARIO,//USUA_MODIFICACION, 
	    			lectura.getFechaModificacion(),//FECHA_MODIFICACION, 
	    			"",//COSI_SICE
	    			"",//tipoReconocimiento = "";
	    			"",//lito = "";
	    			"",//desde = "";
	    			"",//hasta = "";
	    			"",//valor = "";
	    			"",//geolocalizacion
	    			lectura.getFecha()//fecha de eliminacion 
	    			);
    
	    	String lectura_id="";
	    	try {
	    		lectura_id= String.valueOf(numeroInsercion);
			} catch (Exception e) {}
    	
		    for(int i=0; i<lectura.detalleLecturaList.length;i++){
		    			dlAdapter.insert(db.getDataBase(), 
		    			lectura_id,//fk
		    			"",//CODI_DLEC, 
		        		"",//CODI_LECT, 
		        		lectura.detalleLecturaList[i].getCodigo_elemento(),
		        		lectura.detalleLecturaList[i].getCodigo_unidadMedida(),
		        		lectura.detalleLecturaList[i].getValor(),//VALOR, 
		        		Sistema.PENDIENTE, 
		        		Sistema.SESION_USUARIO,//USUA_CREACION, 
		        		lectura.getFechaCreacion(),//FECHA_CREACION, 
		        		Sistema.SESION_USUARIO,//USUA_MODIFICACION, 
		        		lectura.getFechaModificacion()//FECHA_MODIFICACION
		        		);
		    	}
		    
		    db.setSuccessful();
		} catch (Exception e) {
		}finally {
			db.finishTransaction();
	        db.close();
	    }
		
		if(numeroInsercion!=-1){
			checker=true;
		}
		
		return checker;
    }
    
    
    /**REGISTRA LAS LECTURAS DE FAJA*/
    public boolean updateLecturaFaja(Lectura lectura){
    	
    	CILecturaAdapter lAdapter= new CILecturaAdapter();
    	CIDetalleLecturaAdapter dlAdapter= new CIDetalleLecturaAdapter();
    	//DataBaseConeccion db= new DataBaseConeccion(this);
    	boolean checker=false;
    	
    	try {
    		db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria
    		db.beginTransaction();
    		checker= lAdapter.update(db.getDataBase(), 
	    			lectura.getId(),
	    			lectura.getCodigo_lectura(),//CODI_LECT, 
	    			lectura.getCodigo_usuario(),//CODI_CIUS, 
	    			"",//lectura.getCodigo_empresa(),//COD_EMPRESA,//CODI_EMPR, 
	    			"",//lectura.getCodigo_unidadMinera(),//CODI_UNMI, 
	    			"",//lectura.getCodigo_turno(),//CODI_TURN, 
	    			"FAJA",//TIPO, 
	    			lectura.getCOD_ZONA(),//CODI_ZONA, //////////////////////////////////
	    			lectura.getPeso(), 
	    			lectura.getNroTaladro(),//NRO_TALADRO, 
	    			lectura.getProfundidad(),//PROFUNDIDAD, 
	    			Sistema.PENDIENTE, 
	    			"",//USUA_CREACION, 
	    			"",//fechaString,//FECHA_CREACION, 
	    			Sistema.SESION_USUARIO,//USUA_MODIFICACION, 
	    			lectura.getFechaModificacion(),//Fecha.getFechaActual(this),//FECHA_MODIFICACION, 
	    			"",//COSI_SICE
	    			"",//tipoReconocimiento = "";
	    			"",//lito = "";
	    			"",//desde = "";
	    			"",//hasta = "";
	    			"",//valor = "";
	    			"",//geolocalizacion
	    			""//fecha de eliminacion 
	    			);

    		try{
		    	for(int i=0; i<lectura.detalleLecturaList.length;i++){
		    		dlAdapter.update(db.getDataBase(), 
		    		lectura.detalleLecturaList[i].getId(),
		    		lectura.getId(),//fk
		    		lectura.detalleLecturaList[i].getCodigo_detalleLectura(),//CODI_DLEC, 
		    		lectura.detalleLecturaList[i].getCodigo_lectura(),//CODI_LECT, 
		        	lectura.detalleLecturaList[i].getCodigo_elemento(),
		        	lectura.detalleLecturaList[i].getCodigo_unidadMedida(),
		        	lectura.detalleLecturaList[i].getValor(),//VALOR, 
		        	Sistema.PENDIENTE, 
		        	"",//USUA_CREACION, 
		        	"",//FECHA_CREACION, 
		        	Sistema.SESION_USUARIO,//USUA_MODIFICACION, 
		        	lectura.getFechaModificacion()//Fecha.getFechaActual(this)//FECHA_MODIFICACION
		        	);
		    	}
    		}catch(Exception e){
    		}
    		db.setSuccessful();	
		} catch (Exception e) {
		}finally {
		   db.finishTransaction();
	       db.close();
	    }
		
		return checker;
    }
    
    
    /**OBTIENE  UNA ARRAY DE ELEMENTOS DE LA TABLA ELEMENTO_UNIDAD*/
    public DetalleLectura[] getDetalleLectura(String LECTURA_ID){
    	
    	CIDetalleLecturaAdapter adapter= new CIDetalleLecturaAdapter();
    	DetalleLectura[] dLectura=null;// new ElementoUnidad();
    	//DataBaseConeccion db= new DataBaseConeccion(context);
    	
    	try {
    		db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria
        	Cursor cur=null;
    		cur= adapter.fetchByLectura(db.getDataBase(), LECTURA_ID);
	    	if (cur!=null){
	    		dLectura= new DetalleLectura[cur.getCount()];
	    		cur.moveToFirst();
	    		int i=0;
			         while (cur.isAfterLast() == false) {
			        	 dLectura[i]= new DetalleLectura();
			        	 dLectura[i].setId(cur.getString(0));
			        	 dLectura[i].setCodigo_detalleLectura(cur.getString(2));
			        	 dLectura[i].setCodigo_lectura(cur.getString(3));
			        	 dLectura[i].setCodigo_elemento(cur.getString(4));
			        	 dLectura[i].setCodigo_unidadMedida(cur.getString(5));
			        	 dLectura[i].setValor(cur.getString(6));
			        	 dLectura[i].setEstado(cur.getString(7));
			        	 cur.moveToNext();
			        	 i++;
			         }
			         cur.close();
	    	}
         
    	} catch (Exception e) {
    	}finally {
            db.close();
        }
    	return dLectura;
    }
    
    
    /*** VERIFICAR SI EXISTEN LECTURAS SIN CONFIRMAR.*/
    public boolean existeLecturaSinConfirmar(){
    	
    	CILecturaAdapter lecturaAdapter= new CILecturaAdapter();
    	//DataBaseConeccion db= new DataBaseConeccion(context);
    	boolean checker=false;
    	Lectura[] oLect=null;
    	
    	try {
    		db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria
    		Cursor cur=null;
    		cur= lecturaAdapter.fetchByEstadoPorConfirmar(db.getDataBase(), Sistema.PENDIENTE, Sistema.PENDIENTE_ELIMINADO);
	    	if (cur.getCount()!=0){
	    		oLect= new Lectura[cur.getCount()];	
	    		cur.moveToFirst();
	    		int i=0;
			         while (cur.isAfterLast() == false){
			        	 oLect[i]= new Lectura();
			        	 cur.moveToNext();
			        	 i++;
			         }
			         cur.close();
	    	}
    	} catch (Exception e) {
    	}finally {
            db.close();
        }
    	
    	if(oLect!=null){
	    	checker=true;
    	}
    	
    	return checker;
    }
    
    
    /*** obtiene un conjunto de lecturas a partir de una zona*/
    public Lectura[] getLecturasZona(String CODI_ZONA){
    	
    	CILecturaAdapter adapter= new CILecturaAdapter();
    	//DataBaseConeccion db= new DataBaseConeccion(this);
    	Lectura[] oLect=null;///= new Lectura();
    	
    	try {
    		db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria
    		Cursor cur=null;
    		cur= adapter.fetchByZonaGrilla(db.getDataBase(), CODI_ZONA);
	    	if (cur.getCount()!=0){
	    		oLect= new Lectura[cur.getCount()];	
	    		cur.moveToFirst();
	    		int i=0;
			         while (cur.isAfterLast() == false) {
			        	 oLect[i]= new Lectura();
			        	 oLect[i].setId(cur.getString(0));
			        	 oLect[i].setCodigo_lectura(cur.getString(1));
			        	 oLect[i].setCodigo_usuario(cur.getString(2));
			        	 oLect[i].setCodigo_empresa(cur.getString(3));
			        	 oLect[i].setCodigo_sincronizacionLectura(cur.getString(4));
			        	 oLect[i].setCodigo_unidadMinera(cur.getString(5));
			        	 oLect[i].setCodigo_turno(cur.getString(6));
			        	 oLect[i].setCodigo_zona(cur.getString(7));
			        	 oLect[i].setPeso(cur.getString(8));
			        	 oLect[i].setNroTaladro(cur.getString(9));
			        	 oLect[i].setProfundidad(cur.getString(10));
			        	 oLect[i].setEstado(cur.getString(11));
			        	 oLect[i].setFechaCreacion(cur.getString(13));//13 para fechaCreacion
			        	 oLect[i].setFechaModificacion(cur.getString(15));//15 para fechaModificacion
			        	 oLect[i].setTipoReconocimiento(cur.getString(17));//tipoReconocimiento = "";
			        	 oLect[i].setValor(cur.getString(18));//valor = "";
			        	 oLect[i].setDesde(cur.getString(19));//desde = "";
			        	 oLect[i].setHasta(cur.getString(20));//hasta = "";
			        	 oLect[i].setLito(cur.getString(21));//lito = "";
			        	 oLect[i].setCodigo_cuerpo(cur.getString(21));//codigo_cuerpo = "";
			        	 oLect[i].setFecha(cur.getString(23));//para fechaEliminacion
			        	 
			        	 oLect[i].setExtraCU(cur.getString(24));
			        	 oLect[i].setExtraAG(cur.getString(25));
			        	 oLect[i].setExtraPB(cur.getString(26));
			        	 oLect[i].setExtraZN(cur.getString(27));
			        	 oLect[i].setExtraFE(cur.getString(28));
			        	 oLect[i].setLito(cur.getString(29));
			        	 oLect[i].setCodigo_cuerpo(cur.getString(30));
			        	 
			        	 cur.moveToNext();
			        	 i++;
			         }
			         cur.close();
	    	}
         
    	} catch (Exception e) {
    	}finally {
            db.close();
        }
    	
    	return oLect;
    }
    
    
    /**ELIMINACION LOGICA DE UNA LECTURA */
    public Boolean eliminacionLogicaLectura(Lectura lectura){
    	
    	CILecturaAdapter lAdapter= new CILecturaAdapter();
    	//DataBaseConeccion db= new DataBaseConeccion(this);
    	boolean checker=false;//long numeroInsercion=0;
    	try {
    		db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria
    		checker= lAdapter.update(db.getDataBase(), 
    				lectura.getId(),
    				"",//CODI_LECT, 
    				"",//CODI_CIUS, 
    				"",//lectura.getCodigo_empresa(),//CODI_EMPR, 
    				"",//lectura.getCodigo_unidadMinera(),//CODI_UNMI, 
    				"",//lectura.getCodigo_turno(),//CODI_TURN, 
	    			"",//TIPO, 
	    			"",//CODI_ZONA, //////////////////////////////////
	    			"",//lectura.getPeso(), 
	    			"",//lectura.getNroTaladro(),//NRO_TALADRO, 
	    			"",//lectura.getProfundidad(),//PROFUNDIDAD, 
	    			Sistema.PENDIENTE_ELIMINADO, 
	    			"",//USUA_CREACION, 
	    			"",//fechaString,//FECHA_CREACION, 
	    			Sistema.SESION_USUARIO,//USUA_MODIFICACION, 
	    			Fecha.getFechaActual(context),//FECHA_MODIFICACION, 
	    			"",//COSI_SICE
	    			"",//lectura.getTipoReconocimiento(),//tipoReconocimiento = "";
	    			"",//lectura.getValor(),//valor = "";
	    			"",//lectura.getDesde(),//desde = "";
	    			"",//lectura.getHasta(),//hasta = "";
	    			"",//lectura.getLito(),//lito = "";
	    			"",//lectura.getCodigo_cuerpo(),//geolocalizacion
	    			""//fecha
	    			);
		} catch (Exception e) {
		}finally {
            db.close();
        }
		
		return checker;
    }
    
	  
}
