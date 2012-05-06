
package sbs.milpo.controller;
import org.json.JSONObject;

import sbs.milpo.database.DataBaseConeccion;
import sbs.milpo.entity.CIDetalleLecturaAdapter;
import sbs.milpo.entity.CILecturaAdapter;
import sbs.milpo.entity.CISincronizacionLecturaAdapter;
import sbs.milpo.model.bl.sinc.AuthenticationHeader;
import sbs.milpo.model.bl.sinc.Request;
import sbs.milpo.model.bl.sinc.SincronizacionLectura;
import sbs.milpo.model.dal.DetalleLectura;
import sbs.milpo.model.dal.Lectura;
import sbs.milpo.view.R;
import sbs.milpo.view.R.layout;
import greendroid.app.GDActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

public class SincEnvioController extends GDActivity {
	
	public static String txtErrorSinc="";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarContentView(R.layout.main2);
        
        
        /* MODELO BASICO DE HILO CON PROGRESS DIALOG
		final ProgressDialog pd2 = ProgressDialog.show(this,
		"Sincronizando ","Cargando. Porfavor espere...",true, false);
		pd2.setCancelable(true);

		new Thread(new Runnable(){
		public void run(){
			try {
				 SincEnvioController.SincronizarAction(ListViewGrid.this);
				 pd2.dismiss();
        		} catch (Exception e) {
        			pd2.dismiss();
        		}
			}
		}).start();
		*/

    }

    /**PROCEDIMIENTO ORDENADO PARA SINCRONIZAR*/
    public static boolean SincronizarAction(Context context){
    	
    	//String 
    	boolean resultError=true;
    	try {
        	//obtener la lectura.
        	SincronizacionLectura sincLectura=sincronizacionLecturas(context);
			//on methodPost
        	String sc= sincronizarTablasLectura(context, sincLectura);
        	//deberia validar que la respuesta no sea un string vacio.
        	if(!sc.equals(""))
        	{
	        	Request req= Parser.parseFinalRequest(sc);
	        	/**segunda etapa de la sincronizacion*/
	        	
	        	if(req.getCodeStatus().equals(Sistema.OK)){
	        		
	    			//guardamos valores obtenidos de las CLAVES primarias enviadas.
	        		SincronizacionLectura sincLect= parseTablasLecturaId(sc);
	        		Lectura[] LecturaCommit=sincLect.getLecturaList();
	        		
	        		//obtenemos la tabla sincronizacionLectura, para confirmar la insercion
	        		SincronizacionLectura sincLectResponse= registrarClavesSincronizacionLectura(context, sincLect);
	        		
	        		//Tercera etapa confirmar la insercion
	        		String respConfirmacion= sincronizarTablaSincronizacionLectura(context, sincLectResponse);
	        		req= Parser.parseFinalRequest(respConfirmacion);
	        		
	        		/**Verificamos la respuesta del servidor*/
	        		if(req.getCodeStatus().equals(Sistema.OK)){
	        			//VOLVEMOS A CARGAS LAS LECTURAS A SINCLECT
	        			sincLect.setLecturaList(LecturaCommit);
	        			//REGISTRAMOS EL ESTADO A CONFIRMADO
	        			registrarConfirmacionLectura(context, sincLect);
	        			//CONFIRMAMOS QUE NO HAY ERROR
	        			resultError=false;
	        		}else if(req.getCodeStatus().equals(Sistema.ERROR)){
	        			try {txtErrorSinc= req.getUserMessage();} catch (Exception e) {}
	        			resultError=true;
		    		}
	        				
	    		}else if(req.getCodeStatus().equals(Sistema.ERROR)){
	    			try {txtErrorSinc= req.getUserMessage();} catch (Exception e) {}
	    			resultError=true;
	    		}
        	}
		} catch (Exception e) {
			resultError=true;
		}
		
		return resultError;
    }
    
    /**Metodo que permite realizar el post autenticado de envio de lecturas*/
    public static String sincronizarTablasLectura(Context context, SincronizacionLectura sincLectura){
    	
    	AuthenticationHeader authentication= new AuthenticationHeader();
    	authentication.setUsuario(Sistema.SESION_USUARIO);
    	authentication.setPassword(Sistema.SESION_PASSWORD);
    	
    	String URL= Sistema.URL_SINCRONIZAR_LECTURA;
    	RestfullController restClient= new RestfullController();
    	String str= "";
    	if(sincLectura!=null)
    		str= restClient.onMethodPost(URL, authentication,sincLectura );
		
		return str;
    };
    
    /**Segundo post de la sincronizacion donde se confirma la llegada de lecturas*/
    public static String sincronizarTablaSincronizacionLectura(Context context, SincronizacionLectura sLectura ){
    	
    	AuthenticationHeader authentication= new AuthenticationHeader();
    	authentication.setUsuario(Sistema.SESION_USUARIO);
    	authentication.setPassword(Sistema.SESION_PASSWORD);
    	
    	String URL=Sistema.URL_SINCRONIZAR_LECTURA_CONFIRMACION;
    	RestfullController restClient= new RestfullController();
    	String str= restClient.onMethodPost(URL, authentication, sLectura);
		
		return str;
    };
    
    
    /** Obtiene un objeto SincronizacionLectura a partir de un jsonString*/
    public static SincronizacionLectura parseTablasLecturaId(String str){
    	
    	JSONObject json=null;
    	SincronizacionLectura sincLectura= new SincronizacionLectura();
    	try {
    		json=new JSONObject(str);
    		sincLectura= Parser.parseToSincronizacionLectura((Parser.parseJsonString(json, "response")));
		} catch (Exception e) {
		}

    	return sincLectura;
    }

    
    /*** Metodo de envio de las lecturas realizadas localmente.*/
    public static SincronizacionLectura sincronizacionLecturas(Context context){
    	
    	CILecturaAdapter lecturaAdapter= new CILecturaAdapter();
    	CIDetalleLecturaAdapter detalleLecturaAdapter= new CIDetalleLecturaAdapter();
    	DataBaseConeccion db= new DataBaseConeccion(context);
    	Lectura[] oLect=null;
    	try {
    		db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria
    		db.beginTransaction();
        	Cursor cur=null;
    		cur= lecturaAdapter.fetchByEstadoPorConfirmar(db.getDataBase(), Sistema.PENDIENTE, Sistema.PENDIENTE_ELIMINADO);
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
			        	 //oLect[i].setCodigo_sincronizacionLectura(cur.getString(4));
			        	 oLect[i].setCodigo_unidadMinera(cur.getString(4));
			        	 oLect[i].setCodigo_turno(cur.getString(5));
			        	 //
			        	 oLect[i].setCodigo_zona(cur.getString(7));
			        	 oLect[i].setPeso(cur.getString(8));
			        	 oLect[i].setNroTaladro(cur.getString(9));
			        	 oLect[i].setProfundidad(cur.getString(10));
			        	 oLect[i].setEstado(cur.getString(11));
			        	 //
			        	 oLect[i].setFechaCreacion(cur.getString(13));
			        	 //
			        	 oLect[i].setFechaModificacion(cur.getString(15));
			        	 //
			        	 oLect[i].setTipoReconocimiento(cur.getString(17));
			        	 oLect[i].setValor(cur.getString(18));
			        	 oLect[i].setDesde(cur.getString(19));
			        	 oLect[i].setHasta(cur.getString(20));
			        	 oLect[i].setLito(cur.getString(21));
			        	 oLect[i].setCodigo_cuerpo(cur.getString(22));
			        	 oLect[i].setFecha(cur.getString(23));
			        	 /////////////////////////////////////////
			        	 
			        	 try {
			         		Cursor dlCur= detalleLecturaAdapter.fetchByLectura(db.getDataBase(),oLect[i].getId());
			     	    	if (dlCur.getCount()!=0){
			     	    		oLect[i].detalleLecturaList= new DetalleLectura[dlCur.getCount()];	
			     	    		dlCur.moveToFirst();
			     	    		int j=0;
			     			         while (dlCur.isAfterLast() == false) {
			     	    		
			     			        	 oLect[i].detalleLecturaList[j]= new DetalleLectura();
			     			        	 oLect[i].detalleLecturaList[j].setId(dlCur.getString(0));
			     			        	 oLect[i].detalleLecturaList[j].setCodigo_detalleLectura(dlCur.getString(2));
			     			        	 oLect[i].detalleLecturaList[j].setCodigo_lectura(dlCur.getString(3));
			     			        	 oLect[i].detalleLecturaList[j].setCodigo_elemento(dlCur.getString(4));
			     			        	 oLect[i].detalleLecturaList[j].setCodigo_unidadMedida(dlCur.getString(5));
			     			        	 oLect[i].detalleLecturaList[j].setValor(dlCur.getString(6));
			     			        	 oLect[i].detalleLecturaList[j].setEstado(dlCur.getString(7));
			     			        	 dlCur.moveToNext();
			     			        	 j++;
			     			         }
			     			         dlCur.close();
			     	    	}
			              
			         	} catch (Exception e) {
			         	}
			        	 cur.moveToNext();
			        	 i++;
			         }
			         cur.close();
	    	}
         
    	} catch (Exception e) {
    	} finally {
            db.finishTransaction();
            db.close();
        }
    	
    	SincronizacionLectura sincLectura=null;
    	if(oLect!=null){
	    	sincLectura= new SincronizacionLectura();
	    	sincLectura.setLecturaList(oLect);
    	}
    	
    	return sincLectura;
    }
    
    /**Registra  los ids correlativos, de las  lecturas en la base de datos*/
    public static SincronizacionLectura registrarClavesSincronizacionLectura(Context context, SincronizacionLectura sincLect){
    	
    	DataBaseConeccion db= new DataBaseConeccion(context); 	
    	CISincronizacionLecturaAdapter sincLecturaAdapter= new CISincronizacionLecturaAdapter();
    	CIDetalleLecturaAdapter detalleLecturaAdapter= new CIDetalleLecturaAdapter();
    	CILecturaAdapter lecturaAdapter= new CILecturaAdapter();
    	SincronizacionLectura sincLectura= sincLect;
    	Lectura[] lectura=sincLect.getLecturaList();
    	long numeroInsercion=0;

    	
    	try { 
    		db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria   	
    		db.beginTransaction();
    		numeroInsercion= sincLecturaAdapter.insert(db.getDataBase(), 
    				sincLect.getCodigo_sincronizacionLectura(),//CODI_CILS, 
    				sincLect.getCodigo_usuario(),//CODI_CIUS, 
    				sincLect.getEstado(),//ESTADO, 
    				Sistema.SESION_USUARIO,//USUA_CREACION, 
    				sincLect.getFecha(),//FECHA_CREACION, 
    				"", //USUA_MODIFICACION 
    				""//FECHA_MODIFICACION
    				);
    	
    	
	    	String lectura_sincronizacionId="";
	    	try {
	    		lectura_sincronizacionId= String.valueOf(numeroInsercion);
			} catch (Exception e) {}
		
    	
    		for (int i=0;i<lectura.length;i++){
    			lecturaAdapter.update(db.getDataBase(), 
    					lectura[i].getId(),//ROWID, 
    					lectura[i].getCodigo_lectura(),//CODI_LECT, 
    					lectura[i].getCodigo_usuario(),//CODI_CIUS, 
    					"",//CODI_EMPR, 
    					"",//CODI_UNMI, 
    					"",//CODI_TURN, 
    					"",//TIPO, 
    					"",//CODI_ZONA, 
    					"",//PESO, 
    					"",//NRO_TALADRO, 
    					"",//PROFUNDIDAD, 
    					"",//ESTADO, 
    					"",//USUA_CREACION, 
    					"",//FECHA_CREACION, 
    					Sistema.SESION_USUARIO,//USUA_MODIFICACION, 
    					Fecha.getFechaActual(context),//FECHA_MODIFICACION, 
    					lectura_sincronizacionId,//COSI_SICE
    					"",//TIPO_RECONOCIMIENTO
    					"",//VALOR
    					"",//DESDE
    					"",//HASTA
    					"",//LITO
    					"",//geocuerpo
    					""
    					);
    			
    			
    		    	for(int j=0; j<lectura[i].detalleLecturaList.length;j++){
    		    		detalleLecturaAdapter.update(db.getDataBase(),
    		    				lectura[i].detalleLecturaList[j].getId(),//ROWID, 
    		    				lectura[i].getId(),//FK_LECTURA, 
    		    				lectura[i].detalleLecturaList[j].getCodigo_detalleLectura(),//CODI_DLEC, 
    		    				lectura[i].detalleLecturaList[j].getCodigo_lectura(),//CODI_LECT, 
    		    				"",//CODI_ELEM, 
    		    				"",//CODI_UNME, 
    		    				"",//VALOR, 
    		    				"",//ESTADO, 
    		    				"",//USUA_CREACION, 
    		    				"",//FECHA_CREACION, 
    		    				Sistema.SESION_USUARIO,//USUA_MODIFICACION, 
    		    				Fecha.getFechaActual(context)//FECHA_MODIFICACION, 
    		    				);
    		    	}
    		}
    		
 		   db.setSuccessful();
    	}catch(Exception e){
    	} finally {
            db.finishTransaction();
            db.close();
        }
    	
    	try {
    		sincLectura.setLecturaList(null);
		} catch (Exception e) {}
    	
    	return sincLectura;
    }
    
    /**Finalmente actualiza los registro de lectura con el estado confirmado*/
    public static SincronizacionLectura registrarConfirmacionLectura(Context context, SincronizacionLectura sincLect){
    	
    	CISincronizacionLecturaAdapter sincLecturaAdapter= new CISincronizacionLecturaAdapter();
    	CILecturaAdapter lecturaAdapter= new CILecturaAdapter();
    	DataBaseConeccion db= new DataBaseConeccion(context);
    	Lectura[] lectura=sincLect.getLecturaList();
    	try {
    		db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria
    		db.beginTransaction();
    		sincLecturaAdapter.update(db.getDataBase(), 
    				sincLect.getCodigo_sincronizacionLectura(),//CODI_CILS, 
    				"",//CODI_CIUS, 
    				Sistema.HABILITADO,//ESTADO, 
    				"",//USUA_CREACION, 
    				"",//FECHA_CREACION, 
    				Sistema.SESION_USUARIO,//USUA_MODIFICACION, 
    				Fecha.getFechaActual(context)//FECHA_MODIFICACION, 
    				);
    	
    		for (int i=0;i<lectura.length;i++){
    			lecturaAdapter.update(db.getDataBase(), 
    					lectura[i].getId(),//ROWID, 
    					"",//CODI_LECT, 
    					"",//CODI_CIUS, 
    					"",//CODI_EMPR, 
    					"",//CODI_UNMI, 
    					"",//CODI_TURN, 
    					"",//TIPO, 
    					"",//CODI_ZONA, 
    					"",//PESO, 
    					"",//NRO_TALADRO, 
    					"",//PROFUNDIDAD, 
    					lectura[i].getEstado(),//ESTADO, 
    					"",//USUA_CREACION, 
    					"",//FECHA_CREACION, 
    					Sistema.SESION_USUARIO,//USUA_MODIFICACION, 
    					Fecha.getFechaActual(context),//FECHA_MODIFICACION, 
    					"",//COSI_SICE
    					"",//TIPO_RECONOCIMIENTO
    					"",//VALOR
    					"",//DESDE
    					"",//HASTA
    					"",//LITO
    					"",//geocuerpo
    					""
    					);
    		}
    		
    		db.setSuccessful();
    	}catch(Exception e){
    	} finally {
            db.finishTransaction();
            db.close();
        }
    	
    	try {
    		sincLect.setLecturaList(null);
		} catch (Exception e) {}
    	
    	return sincLect;
    }
    
    
    
    
}


