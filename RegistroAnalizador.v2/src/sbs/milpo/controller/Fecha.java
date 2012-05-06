package sbs.milpo.controller;

import java.util.Calendar;
import java.util.Date;

import android.content.Context;


public class Fecha {

	private  static String fecha_dia = "";
	private  static String fecha_mes = "";
    private  static String fecha_año = "";
    private  static String fecha_hora = "";
    private  static String fecha_minuto = "";
    private  static String fecha_segundo = "";
	

    /**DEVUELVE UN STRING CON LA HORA ACTUAL DEL SISTEMA*/     
    public static String getFechaActual(Context context){
     		
     		String fechaString="";
     		try {
     			Date date = new Date();
                java.text.DateFormat dateFormat =
                    android.text.format.DateFormat.getDateFormat(context.getApplicationContext());
                dateFormat.format(date);
                Calendar calDate= dateFormat.getCalendar();
                int month=(int)calDate.get(calDate.MONTH);
                month=month+1;
                
                Date d = new Date();
                Calendar calTime = Calendar.getInstance();
                calTime.setTime(d);

                fechaString=String.valueOf(calDate.get(calDate.DAY_OF_MONTH))+"/"+
                String.valueOf(month)+"/"+
                String.valueOf(calDate.get(calDate.YEAR))+" "+
            	String.valueOf(calTime.get(calTime.HOUR_OF_DAY))+":"+
            	String.valueOf(calTime.get(calTime.MINUTE))+":"+
            	String.valueOf(calTime.get(calTime.SECOND));
             	
     		} catch (Exception e) {
     		}
     		
     		return fechaString;
     	}  
    
    
    /**DEVUELVE UN STRING CON LA HORA ACTUAL DEL SISTEMA FORMATEADA PARA ARCHIVOS*/     
    public static String getFechaActualFormateada(Context context){
     		
     		String fechaString="";
     		try {
     			Date date = new Date();
                java.text.DateFormat dateFormat =
                    android.text.format.DateFormat.getDateFormat(context.getApplicationContext());
                dateFormat.format(date);
                Calendar calDate= dateFormat.getCalendar();
                int month=(int)calDate.get(calDate.MONTH);
                month=month+1;
                
                Date d = new Date();
                Calendar calTime = Calendar.getInstance();
                calTime.setTime(d);

                fechaString=String.valueOf(calDate.get(calDate.DAY_OF_MONTH))+"-"+
                String.valueOf(month)+"-"+
                String.valueOf(calDate.get(calDate.YEAR))+" "+
            	String.valueOf(calTime.get(calTime.HOUR_OF_DAY))+"h "+
            	String.valueOf(calTime.get(calTime.MINUTE))+"m "+
            	String.valueOf(calTime.get(calTime.SECOND))+"s";
             	
     		} catch (Exception e) {
     		}
     		
     		return fechaString;
     	}  
    
    
    /**DEVUELVE UN STRING CON LA HORA ACTUAL DEL SISTEMA*/     
    public static void cargarFechaActual(Context context){
     		
     		try {
     			Date date = new Date();
                java.text.DateFormat dateFormat =
                    android.text.format.DateFormat.getDateFormat(context.getApplicationContext());
                dateFormat.format(date);
                Calendar calDate= dateFormat.getCalendar();
                int month=(int)calDate.get(calDate.MONTH);
                month=month+1;
                
                Date d = new Date();
                Calendar calTime = Calendar.getInstance();
                calTime.setTime(d);

                fecha_dia = String.valueOf(calDate.get(calDate.DAY_OF_MONTH));
            	fecha_mes = String.valueOf(month);
                fecha_año = String.valueOf(calDate.get(calDate.YEAR));
                fecha_hora = String.valueOf(calTime.get(calTime.HOUR_OF_DAY));
                fecha_minuto = String.valueOf(calTime.get(calTime.MINUTE));
                fecha_segundo = String.valueOf(calTime.get(calTime.SECOND));
                
     		} catch (Exception e) {
     		}
     		
     	}  
    
    
    /**VALIDA SI ES QUE LA FECHA INGRESADA ES MENOR QUE LA FECHA ACTUAL*/
	public static boolean isFechaMenor(Context context,Date modDate){
		
		boolean checker=false;
		Fecha.cargarFechaActual(context);
		int iniYear= Integer.parseInt(Fecha.getFecha_año());
		int iniMonth= Integer.parseInt(Fecha.getFecha_mes())-1;
		int iniDay= Integer.parseInt(Fecha.getFecha_dia());
		int iniHour= Integer.parseInt(Fecha.getFecha_hora());
		int iniMinute= Integer.parseInt(Fecha.getFecha_minuto());
		Date dateBefore= new Date(iniYear, iniMonth, iniDay, iniHour, iniMinute);
		if(!modDate.after(dateBefore)){
			checker=true;
		}
		return checker;
	}
    
	/**
	 * @return the fecha_dia
	 */
	public static String getFecha_dia() {
		return fecha_dia;
	}
	/**
	 * @param fecha_dia the fecha_dia to set
	 */
	public static void setFecha_dia(String fecha_dia) {
		Fecha.fecha_dia = fecha_dia;
	}
	/**
	 * @return the fecha_mes
	 */
	public static String getFecha_mes() {
		return fecha_mes;
	}
	/**
	 * @param fecha_mes the fecha_mes to set
	 */
	public static void setFecha_mes(String fecha_mes) {
		Fecha.fecha_mes = fecha_mes;
	}
	/**
	 * @return the fecha_año
	 */
	public static String getFecha_año() {
		return fecha_año;
	}
	/**
	 * @param fecha_año the fecha_año to set
	 */
	public static void setFecha_año(String fecha_año) {
		Fecha.fecha_año = fecha_año;
	}
	/**
	 * @return the fecha_hora
	 */
	public static String getFecha_hora() {
		return fecha_hora;
	}
	/**
	 * @param fecha_hora the fecha_hora to set
	 */
	public static void setFecha_hora(String fecha_hora) {
		Fecha.fecha_hora = fecha_hora;
	}
	/**
	 * @return the fecha_minuto
	 */
	public static String getFecha_minuto() {
		return fecha_minuto;
	}
	/**
	 * @param fecha_minuto the fecha_minuto to set
	 */
	public static void setFecha_minuto(String fecha_minuto) {
		Fecha.fecha_minuto = fecha_minuto;
	}
	/**
	 * @return the fecha_segundo
	 */
	public static String getFecha_segundo() {
		return fecha_segundo;
	}
	/**
	 * @param fecha_segundo the fecha_segundo to set
	 */
	public static void setFecha_segundo(String fecha_segundo) {
		Fecha.fecha_segundo = fecha_segundo;
	}
	
  
    
}
