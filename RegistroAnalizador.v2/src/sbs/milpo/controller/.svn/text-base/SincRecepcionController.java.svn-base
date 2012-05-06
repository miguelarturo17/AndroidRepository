
package sbs.milpo.controller;
import org.json.JSONObject;

import sbs.milpo.database.DataBaseConeccion;
import sbs.milpo.entity.CIDetalleLecturaAdapter;
import sbs.milpo.entity.CIElementoUnidadAdapter;
import sbs.milpo.entity.CILecturaAdapter;
import sbs.milpo.entity.CISincronizacionLecturaAdapter;
import sbs.milpo.entity.CITipoReconocimientoAdapter;
import sbs.milpo.entity.TipoZonaAdapter;
import sbs.milpo.entity.UsuarioAdapter;
import sbs.milpo.entity.ElementoQuimicoAdapter;
import sbs.milpo.entity.GeoCuerpoAdapter;
import sbs.milpo.entity.GeolitologiaAdapter;
import sbs.milpo.entity.UnidadMineraAdapter;
import sbs.milpo.entity.ZonaAdapter;
import sbs.milpo.model.bl.sinc.AuthenticationHeader;
import sbs.milpo.model.bl.sinc.Request;
import sbs.milpo.model.bl.sinc.SincronizacionInicial;
import sbs.milpo.model.dal.ElementoQuimico;
import sbs.milpo.model.dal.ElementoUnidad;
import sbs.milpo.model.dal.GeoCuerpo;
import sbs.milpo.model.dal.Geolitologia;
import sbs.milpo.model.dal.TipoReconocimiento;
import sbs.milpo.model.dal.TipoZona;
import sbs.milpo.model.dal.UnidadMinera;
import sbs.milpo.model.dal.Usuario;
import sbs.milpo.model.dal.Zona;
import sbs.milpo.view.R;
import sbs.milpo.view.R.layout;
import greendroid.app.GDActivity;
import android.content.Context;
import android.os.Bundle;

public class SincRecepcionController extends GDActivity {
	public static String txtErrorSinc="";
	public static int ID_DIALOG_SEARCHING=1;
	public static Context con;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarContentView(R.layout.main2);
        
        /* MODELO BASICO DE HILO CON PROGRESS DIALOG
        //if(existeLecturaSinConfirmar(this)){
		
		//new AddActualizacionTask().execute();
		//menuActualizacion();
		/*}else{
			if(Sistema.testConnection(this)){
				final ProgressDialog pd = ProgressDialog.show(this,
        		"Actualizando ","Cargando. Porfavor espere...",true, false);
				pd.setCancelable(true);
        		new Thread(new Runnable(){
        		public void run(){
        			try {
        				count= SincRecepcionController.SincronizarAction(ListViewGrid.this);
        				pd.dismiss();
		        		} catch (Exception e) {
		        			pd.dismiss();
		        		}
        		}
        		}).start();
			 }
		}*/

    }
    
  
    /**PROCEDIMIENTO ORDENADO PARA SINCRONIZAR*/
    public static boolean SincronizarAction(Context context){

    	boolean resultError=true;
    	int count=0;
    	try {
    		String sc= sincronizarTablasEstaticas();
    		
    		//obtenemos el objeto de la Clase SincronizacionInicial.
    		SincronizacionInicial sincIni= parseTablasEstaticas(sc);
    		//obtenemos la respuesta del servidor
    		Request req= Parser.parseFinalRequest(sc);
    		
    		if(req.getCodeStatus().equals(Sistema.OK)){
    			//guardamos valores obtenidos de las webservices.
	        	count= guardarTablasSincronizadas(context, sincIni);
	        	if(count!=0)
	        		resultError=false;
    		}
    		if(req.getCodeStatus().equals(Sistema.ERROR)){
    			try {txtErrorSinc= req.getUserMessage();} catch (Exception e) {}
    			resultError=true;
    		}
        	
		} catch (Exception e) {
			resultError=true;
		}
			
			return resultError;
    }
    
    
    /**Metodo que permite realizar el post para la recepcion de tablas*/
    public static String sincronizarTablasEstaticas(){
    	
    	String URL= Sistema.URL_SINCRONIZAR_TABLAS_ESTATICAS;
    	AuthenticationHeader auth= new AuthenticationHeader();
    	auth.setUsuario(Sistema.SESION_USUARIO);
    	auth.setPassword(Sistema.SESION_PASSWORD);
    	
    	RestfullController restClient= new RestfullController();
    	String str= restClient.onMethodPost(URL,auth);
		
		return str;
    };
    
    /** Obtiene un objeto SincronizacionInicial a partir de un jsonString*/
    public static SincronizacionInicial parseTablasEstaticas(String str){
    	
    	JSONObject json=null;
    	SincronizacionInicial sincInicial= new SincronizacionInicial();
    	try {
    		json=new JSONObject(str);
        	sincInicial= Parser.parseToSincronizacion((Parser.parseJsonString(json, "response")));
		} catch (Exception e) {
			// TODO: handle exception
		}

    	return sincInicial;
    }
    
    /** Registra las tablas estaticas obtenidas*/
    public static int guardarTablasSincronizadas(Context context, SincronizacionInicial sincInicial){
    	
    	DataBaseConeccion db= new DataBaseConeccion(context);
    	ZonaAdapter zonaAdapter= new ZonaAdapter();
    	TipoZonaAdapter tipoZonaAdapter= new TipoZonaAdapter();
    	UsuarioAdapter usuarioAdapter= new UsuarioAdapter();
    	CIElementoUnidadAdapter elementoUnidadAdapter= new CIElementoUnidadAdapter();
    	ElementoQuimicoAdapter elementoQuimicoAdapter= new ElementoQuimicoAdapter();
    	UnidadMineraAdapter unidadMineraAdapter= new UnidadMineraAdapter();
    	GeolitologiaAdapter geolitologiaAdapter= new GeolitologiaAdapter();
    	CITipoReconocimientoAdapter tipoReconocimientoAdapter= new CITipoReconocimientoAdapter();
    	GeoCuerpoAdapter geoCuerpoAdapter= new GeoCuerpoAdapter();
    	int countTable=0;
    	
    	
    	
    	try {
    		db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria
    		db.beginTransaction();
    		
    		try {
        		CIDetalleLecturaAdapter lectDetalleAdapter=new CIDetalleLecturaAdapter();
            	lectDetalleAdapter.delete(db.getDataBase());
            	CILecturaAdapter lectAdapter=new CILecturaAdapter();
            	lectAdapter.delete(db.getDataBase());
            	CISincronizacionLecturaAdapter sincLectAdapter=new CISincronizacionLecturaAdapter();
            	sincLectAdapter.delete(db.getDataBase());
    		} catch (Exception e) {
    		}
    		
    		
	    	Zona[] zona=sincInicial.getZonaList();
	    		zonaAdapter.deleteAllRows(db.getDataBase());
	    		for (int i=0;i<zona.length;i++){
	    			zonaAdapter.insert(db.getDataBase(), 
	    						zona[i].getCodigo_zona(), 
	    						zona[i].getCodigo_tipoZona(),//CODI_TIZO, 
			    				zona[i].getCodigo_empresa(),//CODI_EMPR, 
			    				zona[i].getCodigo_unidadMinera(),//CODI_UNMI, 
			    				zona[i].getDescripcion(),//ZONA, 
			    				zona[i].getEstado()//ESTAD0);
			    				);
	    		}
	    		countTable++;
	    	
	    	TipoZona[] tipoZona= sincInicial.getTipoZonaList();
	    		tipoZonaAdapter.deleteAllRows(db.getDataBase());
	    		for (int i=0;i<tipoZona.length;i++){
	    			tipoZonaAdapter.insert(db.getDataBase(), 
	    					tipoZona[i].getCodigo_tipoZona(),//CODI_TIZO, 
	    					"",//CODIGO, 
	    					"",//CODI_EMPR, 
	    					"",//CODI_UNMI, 
	    					tipoZona[i].getDescripcion(),//TIPO_ZONA, 
	    					"",//TIPO_ZONA_DSC, 
	    					tipoZona[i].getEstado(),//ESTADO, 
	    					"",//USUA_CREACION, //como se maneja el usuario
	    					"",//FECHA_CREACION, //como se maneja la creacion
	    					"",//USUA_MODIFICACION, 
	    					""//FECHA_MODIFICACION
	    					);
	    		}
	    		countTable++;
	    	
	    	Usuario[] usuario= sincInicial.getUsuarioList();
	    		usuarioAdapter.deleteAllRows(db.getDataBase());
	    		for (int i=0;i<usuario.length;i++){
	    			usuarioAdapter.insert(db.getDataBase(),usuario[i].getCodigo_usuario(),//CODI_CIUS, 
	    					usuario[i].getCodigo_trabajador(),//CODI_TRAB, 
	    					usuario[i].getUsuario(),//USUARIO, 
	    					usuario[i].getPassword(),//PASSWD, 
	    					usuario[i].getEstado(),//ESTADO, 
	    					"",//USUA_CREACION, 
	    					"",//FECHA_CREACION, 
	    					"",//USUA_MODIFICACION, 
	    					"",//FECHA_MODIFICACION, 
	    					"",//SESION
	    					usuario[i].getAdministrador()
	    					);
	    		}
	    		countTable++;
	    	
	    	ElementoUnidad[] eUnidad= sincInicial.getElementoUnidadList();
	    		elementoUnidadAdapter.deleteAllRows(db.getDataBase());
	    		for (int i=0;i<eUnidad.length;i++){
	    			elementoUnidadAdapter.insert(db.getDataBase(), 
	    					eUnidad[i].getCodigo_elementoUnidad(),//CODI_CIEU, 
	    					eUnidad[i].getCodigo_empresa(),//CODI_EMPR, 
	    					eUnidad[i].getCodigo_unidadMinera(),//CODI_UNMI, 
	    					eUnidad[i].getOrden(),//ORDEN, 
	    					eUnidad[i].getCodigo_elementoQuimico(),//CODI_ELEM, 
	    					eUnidad[i].getCodigo_unidadMedida(),//CODI_UNME, 
	    					eUnidad[i].getEstado(),//ESTADO, 
	    					"",//USUA_CREACION, 
	    					"",//FECHA_CREACION, 
	    					"",//USUA_MODIFICACION, 
	    					"",//FECHA_MODIFICACION, 
	    					eUnidad[i].getValorMaximo(),//VALOR_MAXIMO, 
	    					eUnidad[i].getValorMinimo(),//VALOR_MINIMO
	    					eUnidad[i].getValorEntero(),//VALOR_MAXIMO, 
	    					eUnidad[i].getValorDecimal()//VALOR_MINIMO
	    					);
	    		}
	    		countTable++;
	    	
	    	ElementoQuimico[] eQuimico= sincInicial.getElementoQuimicoList();
	    		elementoQuimicoAdapter.deleteAllRows(db.getDataBase());
	    		for (int i=0;i<eQuimico.length;i++){
	    			elementoQuimicoAdapter.insert(db.getDataBase(), eQuimico[i].getCodigo_elementoQuimico(),//CODI_ELEM, 
	    					"",//TIPO, 
	    					eQuimico[i].getEstado(),//ESTADO, 
	    					eQuimico[i].getAbreviatura(),//ABREVIATURA, 
	    					eQuimico[i].getElemento(),//ELEMENTO, 
	    					"",//CODIGO_COMERCIAL, 
	    					eQuimico[i].getNomenclatura(),//NOMENCLATURA, 
	    					"",//USUA_MODIFICACION, 
	    					"",//USUA_CREACION, 
	    					"",//FECHA_MODIFICACION, 
	    					""//FECHA_CREACION
	    					);
	    		}
	    		countTable++;
	    	
	    	UnidadMinera[] uMinera= sincInicial.getUnidadMineraList();
	    		unidadMineraAdapter.deleteAllRows(db.getDataBase());
	    		for (int i=0;i<uMinera.length;i++){
	    			unidadMineraAdapter.insert(db.getDataBase(), 
	    					uMinera[i].getCodigo_unidadMinera(),//CODI_UNMI, 
	    					uMinera[i].getCodigo_empresa(),//CODI_EMPR, 
	    					"",//UNIDAD_MINERA, 
	    					uMinera[i].getDescripcion(),//UNIDAD_MINERA_DSC, 
	    					uMinera[i].getEstado(),//ESTADO, 
	    					"",//FECHA_MODIFICACION, 
	    					"",//FECHA_CREACION, 
	    					"",//USUA_MODIFICACION, 
	    					""//USUA_CREACION
	    					);
	    		}
	    		countTable++;
	    		
	    	Geolitologia[] uGeo= sincInicial.getGeoLitologiaList();
	    		geolitologiaAdapter.deleteAllRows(db.getDataBase());
	    		for (int i=0;i<uGeo.length;i++){
	    			geolitologiaAdapter.insert(db.getDataBase(),
	    					uGeo[i].getCodigo_GeoLitologia(),//CODI_GLIT, 
	    					uGeo[i].getCodigoEmpresa(),//CODI_EMPR, 
	    					uGeo[i].getCodigoUnidadMinera(),//CODI_UNMI, 
	    					uGeo[i].getLitologia(),//LITOLOGIA, 
	    					uGeo[i].getLitologiaDesc(),//LITOLOGIA_DSC, 
	    					uGeo[i].getEstado()//ESTADO
	    					);
	    		}
	    		countTable++;
	    		
	    	TipoReconocimiento[] tReconocimiento= sincInicial.getTipoReconocimientoList();
	    		tipoReconocimientoAdapter.deleteAllRows(db.getDataBase());
	    		for (int i=0;i<tReconocimiento.length;i++){
	    			tipoReconocimientoAdapter.insert(db.getDataBase(),
	    					tReconocimiento[i].getCodigo_TipoReconocimiento(),//CODI_CITR, 
	    					tReconocimiento[i].getEstado(),//ESTADO, 
	    					tReconocimiento[i].getTipo_Reconocimiento_Desc()//TIPO_RECONOCIMIENTO_DSC
	    					);
	    		}
	    		countTable++;
    		
	    	GeoCuerpo[] gCuerpo= sincInicial.getCuerpoList();
	    		geoCuerpoAdapter.deleteAllRows(db.getDataBase());
	    		for (int i=0;i<gCuerpo.length;i++){
	    			geoCuerpoAdapter.insert( db.getDataBase(), 
	    					gCuerpo[i].getCodigo_cuerpo(),//CODI_GCUE, 
	    					gCuerpo[i].getCodigo_empresa(),//CODI_EMPR, 
	    					gCuerpo[i].getCodigo_unidadminera(),//CODI_UNMI, 
	    					gCuerpo[i].getNro_cuerpo(),//CUERPO_NRO, 
	    					"",//CUERPO, 
	    					"",//CUERPO_DSC, 
	    					gCuerpo[i].getEstado()//ESTAD0
	    					);
	    		}
	    		countTable++;
	    		
    		db.setSuccessful();
    	}catch(Exception e){
    	}finally {
            db.finishTransaction();
            db.close();
        }

    	return countTable;
    }
    
}


