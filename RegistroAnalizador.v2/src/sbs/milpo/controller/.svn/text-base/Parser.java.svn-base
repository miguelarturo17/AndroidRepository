package sbs.milpo.controller;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import sbs.milpo.model.bl.sinc.Request;
import sbs.milpo.model.bl.sinc.SincronizacionInicial;
import sbs.milpo.model.bl.sinc.SincronizacionLectura;
import sbs.milpo.model.dal.Usuario;

public class Parser {
    /** Metodos de parseo en Android para el formato Json. */

	
	
	/** el objeto obtenido ya debe estar previamente dividido mediante el metodo 
    parseJsonString(json, "response")*/
    public static SincronizacionInicial parseToSincronizacion(String jsonReceived){
    	SincronizacionInicial jsonParsedClass=null;
    	Gson gsonReceived = new Gson();
    	try {
    		jsonParsedClass =  gsonReceived.fromJson(jsonReceived,SincronizacionInicial.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return jsonParsedClass;
    };
    
    /** el objeto obtenido ya debe estar previamente dividido mediante el metodo 
    parseJsonString(json, "response")*/
    public static SincronizacionLectura parseToSincronizacionLectura(String jsonReceived){
    	SincronizacionLectura jsonParsedClass=null;
    	Gson gsonReceived = new Gson();
    	try {
    		jsonParsedClass =  gsonReceived.fromJson(jsonReceived,SincronizacionLectura.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return jsonParsedClass;
    };
    
    
    /** el objeto obtenido ya debe estar previamente dividido mediante el metodo 
    parseJsonString(json, "request")*/
    public static Request parseToRequest(String jsonReceived){
    	Request jsonParsedClass=null;
    	Gson gsonReceived = new Gson();
    	try {
    		jsonParsedClass =  gsonReceived.fromJson(jsonReceived,Request.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return jsonParsedClass;
    };
    
    /**devuelve el json de Request como un objeto*/
    public static Request parseFinalRequest(String str){
    	
    	JSONObject json=null;
    	Request request= new Request();
		try {
    		json=new JSONObject(str);
    		request= Parser.parseToRequest((Parser.parseJsonString(json, "request")));
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return request;
    }
    
    
    /** el objeto obtenido ya debe estar previamente dividido mediante el metodo 
    parseJsonString(json, "response")*/
    public static Usuario parseToUsuario(String jsonReceived){
    	Usuario jsonParsedClass=null;
    	Gson gsonReceived = new Gson();
    	try {
    		jsonParsedClass =  gsonReceived.fromJson(jsonReceived,Usuario.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return jsonParsedClass;
    };
    
    
    public static String parseJsonString(JSONObject jObj, String headerBodyLabel){
    	String jsonParsed=null;
    	try {
    		jsonParsed= jObj.getString(headerBodyLabel);
	        
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return jsonParsed;
    }
    
    public static JSONObject parseJsonObject(JSONObject jObj, String headerBodyLabel){
    	JSONObject jsonParsed=null;//=new JSONObject();
    	try {
    		jsonParsed=jObj.getJSONObject(headerBodyLabel);
	        
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return jsonParsed;
    }
    
    
    
    

}