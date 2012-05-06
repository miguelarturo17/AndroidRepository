package sbs.milpo.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.protocol.HTTP;
import sbs.milpo.model.bl.sinc.AuthenticationHeader;
import sbs.milpo.model.bl.sinc.SincronizacionLectura;

import com.google.gson.Gson;


public class RestfullController  {

	
	
	/**Metodo get que se usa para obtener datos*/
	public static  String onMethodGet(String URL)
	{
		HttpClient httpClient = new DefaultHttpClient();
		
		if(!Sistema.URL_PROXY.equals("")&&!Sistema.URL_PUERTO.equals("")){
			try {
				int puerto=Integer.parseInt(Sistema.URL_PUERTO);
				HttpHost proxy = new HttpHost( Sistema.URL_PROXY, puerto, "http");
			    httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
			} catch (Exception e) {
			}
		}
    	HttpGet httpGet = new HttpGet(URL);
    	String jsonStringResponse="";
    	
        BasicHttpResponse httpResponse = null;
		try {
			httpResponse = (BasicHttpResponse) httpClient.execute(httpGet);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ByteArrayOutputStream outstream = new ByteArrayOutputStream();
		try {
			httpResponse.getEntity().writeTo(outstream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// .writeTo(outstream);
		byte[] responseBody = outstream.toByteArray();
    	
    	try {
    		jsonStringResponse = new String(responseBody);
		} catch (Exception e) {
		}
		

		return jsonStringResponse;
		
	}
	
	
	/**Post autenticado*/
    public String onMethodPost(String URL, AuthenticationHeader ah){

    	String jsonStringResponse="";
    	Gson gsonSend = new Gson();
    	String jsonStringSend = "{\"authentication\":"+gsonSend.toJson(ah)+"}";
    	
    	HttpClient httpClient = new DefaultHttpClient();
    	
    	
    	if(!Sistema.URL_PROXY.equals("")&&!Sistema.URL_PUERTO.equals("")){
			try {
				int puerto=Integer.parseInt(Sistema.URL_PUERTO);
				HttpHost proxy = new HttpHost( Sistema.URL_PROXY, puerto, "http");
			    httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
			} catch (Exception e) {
			}
		}
    	//HttpHost proxy = new HttpHost("10.30.19.2",3128,"http");
	   // httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
	    
    	HttpPost  httpPost = new HttpPost(URL);
    	StringEntity se = null;
		try {
			se = new StringEntity(jsonStringSend,HTTP.UTF_8);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //XML as a string
        se.setContentType("text/json"); //declare it as XML
        httpPost.setHeader("Content-Type","application/json;charset=UTF-8");
        httpPost.setEntity(se);

        BasicHttpResponse httpResponse = null;
		try {
			httpResponse = (BasicHttpResponse) httpClient.execute(httpPost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //txtUser.setText(httpResponse.getStatusLine().toString());
		ByteArrayOutputStream outstream = new ByteArrayOutputStream();
		try {
			httpResponse.getEntity().writeTo(outstream);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// .writeTo(outstream);
		
		try {
			byte[] responseBody = outstream.toByteArray();
			jsonStringResponse = new String(responseBody);
		} catch (Exception e) {
			// TODO: handle exception
		}
		

		return jsonStringResponse;
    }
    
    /**Post de sincronizacionLectura*/
    public String onMethodPost(String URL, AuthenticationHeader ah, SincronizacionLectura sincLect){

    	String jsonStringResponse="";
    	Gson gsonSend = new Gson();
    	Gson gsonSendResponse = new Gson();
    	String jsonStringSend = "{" +
    			"\"authentication\":"+gsonSend.toJson(ah)+"," +
    					"\"data\":"+gsonSendResponse.toJson(sincLect)+
    					"}";
    	
    	HttpClient httpClient = new DefaultHttpClient();
    	
    	if(!Sistema.URL_PROXY.equals("")&&!Sistema.URL_PUERTO.equals("")){
			try {
				int puerto=Integer.parseInt(Sistema.URL_PUERTO);
				HttpHost proxy = new HttpHost( Sistema.URL_PROXY, puerto, "http");
			    httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
			} catch (Exception e) {
			}
		}
    	//HttpHost proxy = new HttpHost("10.30.19.2",3128,"http");
	    //httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
	    
    	HttpPost  httpPost = new HttpPost(URL);
    	StringEntity se = null;
		try {
			se = new StringEntity(jsonStringSend,HTTP.UTF_8);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //XML as a string
        se.setContentType("text/json"); //declare it as XML
        httpPost.setHeader("Content-Type","application/json;charset=UTF-8");
        httpPost.setEntity(se);

        BasicHttpResponse httpResponse = null;
		try {
			httpResponse = (BasicHttpResponse) httpClient.execute(httpPost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //txtUser.setText(httpResponse.getStatusLine().toString());
		ByteArrayOutputStream outstream = new ByteArrayOutputStream();
		try {
			httpResponse.getEntity().writeTo(outstream);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// .writeTo(outstream);
		
		try {
			byte[] responseBody = outstream.toByteArray();
			jsonStringResponse = new String(responseBody);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//return jsonStringSend;
		return jsonStringResponse;
    }
    
    
}