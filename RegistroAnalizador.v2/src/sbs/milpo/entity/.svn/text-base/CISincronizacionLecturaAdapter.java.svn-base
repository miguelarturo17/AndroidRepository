package sbs.milpo.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CISincronizacionLecturaAdapter {
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_CODI_CISL = "CODI_CISL";
	public static final String KEY_CODI_CIUS = "CODI_CIUS";
	public static final String KEY_ESTADO = "ESTADO";
	public static final String KEY_USUA_CREACION = "USUA_CREACION";
	public static final String KEY_FECHA_CREACION = "FECHA_CREACION";
	public static final String KEY_USUA_MODIFICACION = "USUA_MODIFICACION";
	public static final String KEY_FECHA_MODIFICACION = "FECHA_MODIFICACION";
	
	private static final String DATABASE_TABLE = "CI_SINCRONIZACION_LECTURA";


    public long insert(SQLiteDatabase mDb, String CODI_CILS,	String CODI_CIUS, String ESTADO,  String USUA_CREACION,  
    		String FECHA_CREACION,  String USUA_MODIFICACION,  String FECHA_MODIFICACION) {
    	
	        ContentValues initialValues = new ContentValues();
	        initialValues.put(KEY_CODI_CISL,CODI_CILS);
	        initialValues.put(KEY_CODI_CIUS,CODI_CIUS);
	        initialValues.put(KEY_ESTADO,ESTADO);
	        initialValues.put(KEY_USUA_CREACION,USUA_CREACION);
	        initialValues.put(KEY_FECHA_CREACION,FECHA_CREACION);
	        initialValues.put(KEY_USUA_MODIFICACION,USUA_MODIFICACION);
	        initialValues.put(KEY_FECHA_MODIFICACION,FECHA_MODIFICACION);

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    public boolean update(SQLiteDatabase mDb, String CODI_CISL,	String CODI_CIUS, String ESTADO,  String USUA_CREACION,  
    		String FECHA_CREACION,  String USUA_MODIFICACION,  String FECHA_MODIFICACION) {
    	
        ContentValues args = new ContentValues();
	        if(!CODI_CIUS.equals(""))
	        	args.put(KEY_CODI_CIUS,CODI_CIUS);
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
	        

        return mDb.update(DATABASE_TABLE, args, KEY_CODI_CISL + "='" + CODI_CISL+"'", null) > 0;
    }

    public boolean delete(SQLiteDatabase mDb, String CODI_CISL) {

        return mDb.delete(DATABASE_TABLE, KEY_CODI_CISL + "='" + CODI_CISL+"'", null) > 0;
    }
    
    public boolean delete(SQLiteDatabase mDb) {

        return mDb.delete(DATABASE_TABLE, null, null) > 0;
    }


    public Cursor fetchAll(SQLiteDatabase mDb) {

        return mDb.query(DATABASE_TABLE, 
			        		new String[] {KEY_ROWID,
        					KEY_CODI_CISL,
			        		KEY_CODI_CIUS,
			        		KEY_ESTADO,
			        		KEY_USUA_CREACION,
			        		KEY_FECHA_CREACION,
			        		KEY_USUA_MODIFICACION,
			        		KEY_FECHA_MODIFICACION
			        		}, 
        		null, null, null, null, null);
    }


    public Cursor fetch(SQLiteDatabase mDb, String ROWID) throws SQLException {

        Cursor mCursor = mDb.query(true, DATABASE_TABLE, 
			        				new String[] {KEY_ROWID,
        							KEY_CODI_CISL,
					        		KEY_CODI_CIUS,
					        		KEY_ESTADO,
					        		KEY_USUA_CREACION,
					        		KEY_FECHA_CREACION,
					        		KEY_USUA_MODIFICACION,
					        		KEY_FECHA_MODIFICACION
					        		}, 
					        		KEY_ROWID + "=" + ROWID, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    
}
