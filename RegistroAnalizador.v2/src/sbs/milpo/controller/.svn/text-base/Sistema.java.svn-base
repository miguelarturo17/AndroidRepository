package sbs.milpo.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

public class Sistema {
	
	//VARIABLES DE SESION
	public static String SESION_USUARIO="";
	public static String SESION_PASSWORD="";
	public static String SESION_ADMINISTRADOR="";
	public static String CODIGO_UNIDAD="";
    public static String CODIGO_EMPRESA="";
    //ruta que nos permite reiniciar la aplicacion
    public static String PACKAGE_RESTART="sbs.milpo.prototipo";
    
    //TITULOS
    public static String TITULO_GRILLA="";
    
    //VARIABLES PARA MANEJAR LA BASE DE DATOS
	public static int  TIPO_ACCESO_BD= 2;//1 para acceso a memoria 2 para acceso en disco.
	public static String DATABASE_PATH= "";
	public static String DATABASE_FOLDER = "/SIMBIOSYS/DATA";
    public static String DATABASE_NAME_SDCARD = "/datamilpo.db";
    public static String DATABASE_NAME_MEMORY = "data";
    public static String DATABASE_VERSION= "1";

	//SPINNERS QUE SE USAN DE MANERA ESTATICA EN LISTVIEW Y CONFIGURACIONVIEW
	public static int SPINNER_SELECTED_ZONA= 0;
	public static int SPINNER_SELECTED_UNMI= 0;
	
	//LISTA DE ESTADOS  DE MILPO
    public static String ELIMINADO = "0003";
    public static String HABILITADO = "0001";
    public static String ANULADO = "0107";
    public static String PENDIENTE = "0004"; 
    public static String PENDIENTE_ELIMINADO = "0854";
    public static String INHABILITADO = "0007";
    public static String OK = "200";//Code status para HttpPost y HttpGet
    public static String ERROR = "500";//Code status para HttpPost y HttpGet    
        
        
    //VARIABLES DE URL PARA LA SINCRONIZACION 
    //public static String URL_BASE="10.30.1.31";///192.168.1.15
    public static String URL_BASE= "";//10.30.1.31
    public static String URL_PROXY= "";//10.30.19.2
    public static String URL_PUERTO= "";//3128
    public static String URL_SINCRONIZAR_TEST_CONECTION= "";
    public static String URL_SINCRONIZAR_TABLAS_ESTATICAS= "";
    public static String URL_SINCRONIZAR_LECTURA= "";
    public static String URL_SINCRONIZAR_LECTURA_CONFIRMACION= "";
    public static String URL_DIRECTORIO_ACTIVO= "MILPO.RO.ANDROID.WSASP";
    public static String UNIDAD_MINERA= "";

    //ELEMENTOS QUIMICOS UTILIZADOS
    public static String ELEMENTO_CU="LI001";
    public static String ELEMENTO_ZN="LI002";
    public static String ELEMENTO_PB="LI003";
    public static String ELEMENTO_AG="LI004";
    public static String ELEMENTO_FE="LI006";
    public static String TIPO_RECONOCIMIENTO_MALLA = "LX00000001";
    public static String TIPO_RECONOCIMIENTO_PARADA = "LX00000002";
    public static String ZONA_FAJA="026";
         
        
         
    
    /**VERIFICA LA CONEXION A INTERNET y RED*/
 	public static boolean testConnectionNetwork(Context context){
			//REVISAMOS LA CONEXION AL SERVIDOR
			boolean test=false;
			try {
	        	ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    		NetworkInfo cnxInternet = cm.getActiveNetworkInfo();
	    		 if (cnxInternet != null && cnxInternet.isConnected()){
	    			 test=true;
	    		 }
	    	} catch (Exception e) {}
				
			return test;
			
		}
     

 	/**VERIFICA LA CONEXION AL SERVIDOR*/
 	public static boolean testConnectionServer(){
 		boolean testServer=false;
 		try {
 			String rsp= RestfullController.onMethodGet(URL_SINCRONIZAR_TEST_CONECTION);
 	 		if(rsp.equals("200")){
 	 			testServer=true;
 	 		}
		} catch (Exception e) {
		}
 		
 		return testServer;
 	}
 	
 	
 	/**APAGA O ENCIENDE EL WIFI*/
    public static void turnWifiState(Context context, boolean turnOn){
    	
    	try {
    		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
	    	if(wifiManager.isWifiEnabled() && turnOn==false){
	    	    wifiManager.setWifiEnabled(false);
	    	}else if(!wifiManager.isWifiEnabled() && turnOn==true){
	    	    wifiManager.setWifiEnabled(true);
	    	}
		} catch (Exception e) {
		}
    	
    }
 	
     /**VERIFICA LAS URLS CADA VEZ QUE SE CAMBIAR EL IP DEL SERVIDOR*/
     public static void actualizarURL(String strURL, String strDir){
    	 
    	 URL_BASE= strURL;
    	 URL_DIRECTORIO_ACTIVO= strDir;
    	 URL_SINCRONIZAR_TEST_CONECTION= "http://" + URL_BASE + "/" + URL_DIRECTORIO_ACTIVO + "/testconnection.aspx";
    	 URL_SINCRONIZAR_TABLAS_ESTATICAS= "http://" + URL_BASE + "/" + URL_DIRECTORIO_ACTIVO + "/inicial.aspx";
         URL_SINCRONIZAR_LECTURA= "http://" + URL_BASE + "/" + URL_DIRECTORIO_ACTIVO + "/lecturasincronizar.aspx";
         URL_SINCRONIZAR_LECTURA_CONFIRMACION= "http://" + URL_BASE + "/" + URL_DIRECTORIO_ACTIVO + "/lecturaconfirmar.aspx";
 	
     }     
         
	
}