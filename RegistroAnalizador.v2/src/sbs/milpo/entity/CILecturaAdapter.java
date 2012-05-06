package sbs.milpo.entity;

import sbs.milpo.controller.Sistema;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CILecturaAdapter {
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_CODI_LECT = "CODI_LECT";
	public static final String KEY_CODI_CIUS = "CODI_CIUS";
	public static final String KEY_CODI_EMPR = "CODI_EMPR";
	public static final String KEY_CODI_UNMI = "CODI_UNMI";
	public static final String KEY_CODI_TURN = "CODI_TURN";
	public static final String KEY_TIPO = "TIPO";
	public static final String KEY_CODI_ZONA = "CODI_ZONA";
	public static final String KEY_PESO = "PESO";
	public static final String KEY_NRO_TALADRO = "NRO_TALADRO";
	public static final String KEY_PROFUNDIDAD = "PROFUNDIDAD";
	public static final String KEY_ESTADO = "ESTADO";
	public static final String KEY_USUA_CREACION = "USUA_CREACION";
	public static final String KEY_FECHA_CREACION = "FECHA_CREACION";
	public static final String KEY_USUA_MODIFICACION = "USUA_MODIFICACION";
	public static final String KEY_FECHA_MODIFICACION = "FECHA_MODIFICACION";
	
	public static final String KEY_COSI_SICE = "COSI_SICE";
	//agregados en la ultima modificacion
	public static final String KEY_TIPO_RECONOCIMIENTO = "TIPO_RECONOCIMIENTO";
	public static final String KEY_VALOR = "VALOR";
	public static final String KEY_DESDE  = "DESDE";
	public static final String KEY_HASTA  = "HASTA";
	public static final String KEY_LITO  = "LITO";
	public static final String KEY_CODI_GCUE  = "CODI_GCUE";
	public static final String KEY_FECHA = "FECHA";
	
	////campos alternativos solo para simplificar los reportes.
	public static final String KEY_CU = "( Select dl.valor from  CI_DETALLE_LECTURA as dl  inner join  ELEMENTO_QUIMICO as e on dl.CODI_ELEM = e.CODI_ELEM   WHERE e.ELEMENTO='Cu'  and dl.CODI_LECT = l.CODI_LECT ) as CU";
	public static final String KEY_AG = "( Select dl.valor from  CI_DETALLE_LECTURA as dl  inner join  ELEMENTO_QUIMICO as e on dl.CODI_ELEM = e.CODI_ELEM   WHERE e.ELEMENTO='Ag'  and dl.CODI_LECT = l.CODI_LECT ) as AG";
	public static final String KEY_PB = "( Select dl.valor from  CI_DETALLE_LECTURA as dl  inner join  ELEMENTO_QUIMICO as e on dl.CODI_ELEM = e.CODI_ELEM   WHERE e.ELEMENTO='Pb'  and dl.CODI_LECT = l.CODI_LECT ) as PB";
	public static final String KEY_ZN = "( Select dl.valor from  CI_DETALLE_LECTURA as dl  inner join  ELEMENTO_QUIMICO as e on dl.CODI_ELEM = e.CODI_ELEM   WHERE e.ELEMENTO='Zn'  and dl.CODI_LECT = l.CODI_LECT ) as ZN";
	////
	private static final String DATABASE_TABLE = "CI_LECTURA";


    /*** metodo insert. */
    public long insert(SQLiteDatabase mDb, String CODI_LECT,	String CODI_CIUS, 	String CODI_EMPR, 	String CODI_UNMI, String CODI_TURN,
    		String TIPO, String CODI_ZONA, 	String PESO, 	String NRO_TALADRO, String PROFUNDIDAD, String ESTADO,  String USUA_CREACION, 
    		String FECHA_CREACION,  String USUA_MODIFICACION,  String FECHA_MODIFICACION, String COSI_SICE, String TIPO_RECONOCIMIENTO,  
    		String VALOR,  String DESDE, String HASTA, String LITO, String CODI_GCUE, String FECHA) {
    	
	        ContentValues initialValues = new ContentValues();
	        initialValues.put(KEY_CODI_LECT,CODI_LECT);
	        initialValues.put(KEY_CODI_CIUS,CODI_CIUS);
	        initialValues.put(KEY_CODI_EMPR,CODI_EMPR);
	        initialValues.put(KEY_CODI_UNMI,CODI_UNMI);
	        initialValues.put(KEY_CODI_TURN,CODI_TURN);
	        initialValues.put(KEY_TIPO,TIPO);
	        initialValues.put(KEY_CODI_ZONA,CODI_ZONA);
	        initialValues.put(KEY_PESO,PESO);
	        initialValues.put(KEY_NRO_TALADRO,NRO_TALADRO);
	        initialValues.put(KEY_PROFUNDIDAD,PROFUNDIDAD);
	        initialValues.put(KEY_ESTADO,ESTADO);
	        initialValues.put(KEY_USUA_CREACION,USUA_CREACION);
	        initialValues.put(KEY_FECHA_CREACION,FECHA_CREACION);
	        initialValues.put(KEY_USUA_MODIFICACION,USUA_MODIFICACION);
	        initialValues.put(KEY_FECHA_MODIFICACION,FECHA_MODIFICACION);
	        initialValues.put(KEY_COSI_SICE,COSI_SICE);
	        
	        initialValues.put(KEY_TIPO_RECONOCIMIENTO, TIPO_RECONOCIMIENTO);
	        initialValues.put(KEY_VALOR, VALOR);
	        initialValues.put(KEY_DESDE, DESDE);
	        initialValues.put(KEY_HASTA , HASTA );
	        initialValues.put(KEY_LITO,LITO);
	        initialValues.put(KEY_CODI_GCUE,CODI_GCUE);
	        initialValues.put(KEY_FECHA, FECHA);
        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }
    
    /*** metodo update */
    public boolean update(SQLiteDatabase mDb, String ROWID, String CODI_LECT,	String CODI_CIUS, 	String CODI_EMPR, 	String CODI_UNMI, String CODI_TURN,
    		String TIPO, String CODI_ZONA, 	String PESO, 	String NRO_TALADRO, String PROFUNDIDAD,String ESTADO,  String USUA_CREACION,  String FECHA_CREACION,  
    		String USUA_MODIFICACION,  String FECHA_MODIFICACION, String COSI_SICE, String TIPO_RECONOCIMIENTO,  String VALOR,  String DESDE, String HASTA, String LITO, String CODI_GCUE, String FECHA) {
    	
        ContentValues args = new ContentValues();
	        if(!CODI_LECT.equals(""))
	        	args.put(KEY_CODI_LECT,CODI_LECT);
	        if(!CODI_CIUS.equals(""))
	        	args.put(KEY_CODI_CIUS,CODI_CIUS);
	        if(!CODI_EMPR.equals(""))
	        	args.put(KEY_CODI_EMPR,CODI_EMPR);
	        if(!CODI_UNMI.equals(""))
	        	args.put(KEY_CODI_UNMI,CODI_UNMI);
	        if(!CODI_TURN.equals(""))
	        	args.put(KEY_CODI_TURN,CODI_TURN);
	        if(!TIPO.equals(""))
	        	args.put(KEY_TIPO,TIPO);
	        if(!CODI_ZONA.equals(""))
	        	args.put(KEY_CODI_ZONA,CODI_ZONA);
	        if(!PESO.equals(""))
	        	args.put(KEY_PESO,PESO);
	        if(!NRO_TALADRO.equals(""))
	        	args.put(KEY_NRO_TALADRO,NRO_TALADRO);
	        if(!PROFUNDIDAD.equals(""))
	        	args.put(KEY_PROFUNDIDAD,PROFUNDIDAD);
	        if(!ESTADO.equals(""))
	        	args.put(KEY_ESTADO,ESTADO);
	        if(!USUA_CREACION.equals(""))
	        	args.put(KEY_USUA_CREACION,USUA_CREACION);
	        if(!FECHA_CREACION.equals(""))
	        	args.put(KEY_FECHA_CREACION,FECHA_CREACION);
	        if(!USUA_MODIFICACION.equals(""))
	        	args.put(KEY_USUA_MODIFICACION,USUA_MODIFICACION);
	        if(!FECHA_MODIFICACION.equals(""))
	        	args.put(KEY_FECHA_MODIFICACION,FECHA_MODIFICACION);
	        if(!COSI_SICE.equals(""))
	        	args.put(KEY_COSI_SICE,COSI_SICE);
	        
	        
	        if(!TIPO_RECONOCIMIENTO.equals(""))
	        	args.put(KEY_TIPO_RECONOCIMIENTO,TIPO_RECONOCIMIENTO);
	        if(!VALOR.equals(""))
	        	args.put(KEY_VALOR,VALOR);
	        if(!DESDE.equals(""))
	        	args.put(KEY_DESDE,DESDE);
	        if(!HASTA.equals(""))
	        	args.put(KEY_HASTA,HASTA);
	        if(!LITO.equals(""))
	        	args.put(KEY_LITO,LITO);
	        if(!CODI_GCUE.equals(""))
	        	args.put(KEY_CODI_GCUE,CODI_GCUE);
	        if(!FECHA.equals(""))
	        	args.put(KEY_FECHA, FECHA);
	        

        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + ROWID, null) > 0;
    }

    /*** metodo delete. */
    public boolean delete(SQLiteDatabase mDb, String CODI_LECT) {

        return mDb.delete(DATABASE_TABLE, KEY_CODI_LECT + "='" + CODI_LECT+"'", null) > 0;
    }

    public boolean delete(SQLiteDatabase mDb) {

        return mDb.delete(DATABASE_TABLE,null, null) > 0;
    }
    
    /*** Metodo para obtener todas las filas de la tabla. */
    public Cursor fetchAll(SQLiteDatabase mDb) {

        return mDb.query(DATABASE_TABLE, 
			        		new String[] {KEY_ROWID,
        					KEY_CODI_LECT,
			        		KEY_CODI_CIUS,
			        		KEY_CODI_EMPR,
			        		KEY_CODI_UNMI,
			        		KEY_CODI_TURN,
			        		KEY_TIPO,
			        		KEY_CODI_ZONA,
			        		KEY_PESO,
			        		KEY_NRO_TALADRO,
			        		KEY_PROFUNDIDAD,
			        		KEY_ESTADO,
			        		KEY_USUA_CREACION,
			        		KEY_FECHA_CREACION,
			        		KEY_USUA_MODIFICACION,
			        		KEY_FECHA_MODIFICACION,
			        		KEY_COSI_SICE,
			        		//modificado en la ultima version
			        		KEY_TIPO_RECONOCIMIENTO,
			    	        KEY_VALOR, 
			    	        KEY_DESDE, 
			    	        KEY_HASTA , 
			    	        KEY_LITO,
			    	        KEY_CODI_GCUE,
			    	        KEY_FECHA
			        		}, 
        		null, null, null, null, null);
    }

    /**Metodo para obtener una fila mediante un id
     */
    public Cursor fetch(SQLiteDatabase mDb, String CODI_LECT) throws SQLException {

        Cursor mCursor = mDb.query(true, DATABASE_TABLE, 
			        				new String[] {KEY_ROWID,
        							KEY_CODI_LECT,
					        		KEY_CODI_CIUS,
					        		KEY_CODI_EMPR,
					        		KEY_CODI_UNMI,
					        		KEY_CODI_TURN,
					        		KEY_TIPO,
					        		KEY_CODI_ZONA,
					        		KEY_PESO,
					        		KEY_NRO_TALADRO,
					        		KEY_PROFUNDIDAD,
					        		KEY_ESTADO,
					        		KEY_USUA_CREACION,
					        		KEY_FECHA_CREACION,
					        		KEY_USUA_MODIFICACION,
					        		KEY_FECHA_MODIFICACION,
					        		KEY_COSI_SICE,
					        		//modificado en la ultima version
					        		KEY_TIPO_RECONOCIMIENTO,
					    	        KEY_VALOR, 
					    	        KEY_DESDE, 
					    	        KEY_HASTA , 
					    	        KEY_LITO,
					    	        KEY_CODI_GCUE,
					    	        KEY_FECHA
					        		}, 
					        		KEY_CODI_LECT + "='" + CODI_LECT+"'", null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }
    
    
    
    /**Metodo para obtener una fila mediante un id
     */
    public Cursor fetchByEstadoPorConfirmar(SQLiteDatabase mDb, String ESTADO,  String ESTADO2) throws SQLException {
    	String QUERY=KEY_ESTADO + "='" + ESTADO+"' OR "+KEY_ESTADO + "='" + ESTADO2+"'";
        Cursor mCursor = mDb.query(true, DATABASE_TABLE, 
			        				new String[] {KEY_ROWID,
        							KEY_CODI_LECT,
					        		KEY_CODI_CIUS,
					        		KEY_CODI_EMPR,
					        		KEY_CODI_UNMI,
					        		KEY_CODI_TURN,
					        		KEY_TIPO,
					        		KEY_CODI_ZONA,
					        		KEY_PESO,
					        		KEY_NRO_TALADRO,
					        		KEY_PROFUNDIDAD,
					        		KEY_ESTADO,
					        		KEY_USUA_CREACION,
					        		KEY_FECHA_CREACION,
					        		KEY_USUA_MODIFICACION,
					        		KEY_FECHA_MODIFICACION,
					        		KEY_COSI_SICE,
					        		//modificado en la ultima version
					        		KEY_TIPO_RECONOCIMIENTO,
					    	        KEY_VALOR, 
					    	        KEY_DESDE, 
					    	        KEY_HASTA , 
					    	        KEY_LITO,
					    	        KEY_CODI_GCUE,
					    	        KEY_FECHA
					        		}, 
					        		QUERY, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    
    public Cursor fetchByZona1(SQLiteDatabase mDb, String CODI_ZONA) throws SQLException {

    	String sql="Select *,"+
		"  ( Select dl.valor from  CI_DETALLE_LECTURA as dl  inner join  ELEMENTO_QUIMICO as e on dl.CODI_ELEM = e.CODI_ELEM   WHERE e.NOMENCLATURA='Cu'  and dl.id_lectura = l._id ) as CU "+
		", ( Select dl.valor from  CI_DETALLE_LECTURA as dl  inner join  ELEMENTO_QUIMICO as e on dl.CODI_ELEM = e.CODI_ELEM   WHERE e.NOMENCLATURA='Ag'  and dl.id_lectura = l._id ) as AG "+
		", ( Select dl.valor from  CI_DETALLE_LECTURA as dl  inner join  ELEMENTO_QUIMICO as e on dl.CODI_ELEM = e.CODI_ELEM   WHERE e.NOMENCLATURA='Pb'  and dl.id_lectura = l._id ) as PB "+
		", ( Select dl.valor from  CI_DETALLE_LECTURA as dl  inner join  ELEMENTO_QUIMICO as e on dl.CODI_ELEM = e.CODI_ELEM   WHERE e.NOMENCLATURA='Zn'  and dl.id_lectura = l._id ) as ZN "+
		", ( Select dl.valor from  CI_DETALLE_LECTURA as dl  inner join  ELEMENTO_QUIMICO as e on dl.CODI_ELEM = e.CODI_ELEM   WHERE e.NOMENCLATURA='Fe'  and dl.id_lectura = l._id ) as FE "+
		",(SELECT gli.LITOLOGIA_DSC from  GEO_LITOLOGIA as gli where  gli.CODI_GLIT= l.LITO ) as LITOEXTRA "+
		", (SELECT  cu.CUERPO_NRO from  GEO_CUERPO as cu  where  cu.CODI_GCUE= l.CODI_GCUE ) as CUERPOEXTRA"+
		" from  CI_Lectura as l where l.CODI_ZONA='"+CODI_ZONA+"'  ";
    	
        Cursor mCursor = mDb.rawQuery(sql, new String[] {});
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }
    
    public Cursor fetchByZonaGrilla(SQLiteDatabase mDb, String CODI_ZONA) throws SQLException {

    	String sql="Select *,"+
		"  ( Select dl.valor from  CI_DETALLE_LECTURA as dl  inner join  ELEMENTO_QUIMICO as e on dl.CODI_ELEM = e.CODI_ELEM   WHERE e.NOMENCLATURA='Cu'  and dl.id_lectura = l._id ) as CU "+
		", ( Select dl.valor from  CI_DETALLE_LECTURA as dl  inner join  ELEMENTO_QUIMICO as e on dl.CODI_ELEM = e.CODI_ELEM   WHERE e.NOMENCLATURA='Ag'  and dl.id_lectura = l._id ) as AG "+
		", ( Select dl.valor from  CI_DETALLE_LECTURA as dl  inner join  ELEMENTO_QUIMICO as e on dl.CODI_ELEM = e.CODI_ELEM   WHERE e.NOMENCLATURA='Pb'  and dl.id_lectura = l._id ) as PB "+
		", ( Select dl.valor from  CI_DETALLE_LECTURA as dl  inner join  ELEMENTO_QUIMICO as e on dl.CODI_ELEM = e.CODI_ELEM   WHERE e.NOMENCLATURA='Zn'  and dl.id_lectura = l._id ) as ZN "+
		", ( Select dl.valor from  CI_DETALLE_LECTURA as dl  inner join  ELEMENTO_QUIMICO as e on dl.CODI_ELEM = e.CODI_ELEM   WHERE e.NOMENCLATURA='Fe'  and dl.id_lectura = l._id ) as FE "+
		",(SELECT gli.LITOLOGIA_DSC from  GEO_LITOLOGIA as gli where  gli.CODI_GLIT= l.LITO ) as LITOEXTRA "+
		", (SELECT  cu.CUERPO_NRO from  GEO_CUERPO as cu  where  cu.CODI_GCUE= l.CODI_GCUE ) as CUERPOEXTRA"+
		" from  CI_Lectura as l where l.CODI_ZONA='"+CODI_ZONA+"' AND (l.ESTADO='"+Sistema.PENDIENTE+"' OR l.ESTADO='"+Sistema.HABILITADO+"') ";
    	
        Cursor mCursor = mDb.rawQuery(sql, new String[] {});
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    
}
