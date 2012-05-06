package sbs.milpo.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TipoZonaAdapter {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_CODI_TIZO = "CODI_TIZO";
    public static final String KEY_CODIGO = "CODIGO";
    public static final String KEY_CODI_EMPR = "CODI_EMPR";
    public static final String KEY_CODI_UNMI = "CODI_UNMI";
    public static final String KEY_TIPO_ZONA = "TIPO_ZONA";
    public static final String KEY_TIPO_ZONA_DSC = "TIPO_ZONA_DSC";
    public static final String KEY_ESTADO = "ESTADO";
    public static final String KEY_USUA_CREACION = "USUA_CREACION";
    public static final String KEY_FECHA_CREACION = "FECHA_CREACION";
    public static final String KEY_USUA_MODIFICACION = "USUA_MODIFICACION";
    public static final String KEY_FECHA_MODIFICACION = "FECHA_MODIFICACION";
    
    private static final String DATABASE_TABLE = "TIPO_ZONA";
    
    
    
    /*** metodo insert. */
    public long insert(SQLiteDatabase mDb, String CODI_TIZO, String CODIGO,String CODI_EMPR,String CODI_UNMI, String  TIPO_ZONA, String  TIPO_ZONA_DSC,
    		String ESTADO, String USUA_CREACION, String FECHA_CREACION,String USUA_MODIFICACION, String FECHA_MODIFICACION) {
    	
	        ContentValues initialValues = new ContentValues();
	        initialValues.put(KEY_CODI_TIZO,CODI_TIZO);
	        initialValues.put(KEY_CODIGO,CODIGO);
	        initialValues.put(KEY_CODI_EMPR,CODI_EMPR);
	        initialValues.put(KEY_CODI_UNMI,CODI_UNMI);
	        initialValues.put(KEY_TIPO_ZONA,TIPO_ZONA);
	        initialValues.put(KEY_TIPO_ZONA_DSC,TIPO_ZONA_DSC);
	        initialValues.put(KEY_ESTADO,ESTADO);
	        initialValues.put(KEY_USUA_CREACION,USUA_CREACION);
	        initialValues.put(KEY_CODI_EMPR,FECHA_CREACION);
	        initialValues.put(KEY_USUA_MODIFICACION,USUA_MODIFICACION);
	        initialValues.put(KEY_FECHA_MODIFICACION,FECHA_MODIFICACION);	        
	        
        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }
    
    /*** metodo update.  */
    public boolean update(SQLiteDatabase mDb, String CODI_TIZO, String CODIGO,String CODI_EMPR,String CODI_UNMI, String  TIPO_ZONA, String  TIPO_ZONA_DSC,
    		String ESTADO, String USUA_CREACION, String FECHA_CREACION,String USUA_MODIFICACION, String FECHA_MODIFICACION) {
    	
        ContentValues args = new ContentValues();
	        if(!CODIGO.equals(""))
	        	args.put(KEY_CODIGO,CODIGO);
	        if(!CODI_EMPR.equals(""))
	        	args.put(KEY_CODI_EMPR,CODI_EMPR);
	        if(!CODI_UNMI.equals(""))
	        	args.put(KEY_CODI_UNMI,CODI_UNMI);
	        if(!TIPO_ZONA.equals(""))
	        	args.put(KEY_TIPO_ZONA,TIPO_ZONA);
	        if(!TIPO_ZONA_DSC.equals(""))
	        	args.put(KEY_TIPO_ZONA_DSC,TIPO_ZONA_DSC);
	        if(!ESTADO.equals(""))
	        	args.put(KEY_ESTADO,ESTADO);
	        if(!USUA_CREACION.equals(""))
	        	args.put(KEY_USUA_CREACION,USUA_CREACION);
	        if(!FECHA_CREACION.equals(""))
	        	args.put(KEY_FECHA_CREACION,FECHA_CREACION);
	        if(!USUA_MODIFICACION.equals(""))
	        	args.put(USUA_MODIFICACION,USUA_MODIFICACION);
	        if(!FECHA_MODIFICACION.equals(""))
	        	args.put(KEY_FECHA_MODIFICACION,FECHA_MODIFICACION);

        return mDb.update(DATABASE_TABLE, args, KEY_CODI_TIZO + "='" + CODI_TIZO+"'", null) > 0;
    }

    /*** metodo delete.  */
    public boolean delete(SQLiteDatabase mDb, String CODI_TIZO) {

        return mDb.delete(DATABASE_TABLE, KEY_CODI_TIZO + "='" + CODI_TIZO+"'", null) > 0;
    }
    
    public boolean deleteAllRows(SQLiteDatabase mDb) {

        return mDb.delete(DATABASE_TABLE, null, null) > 0;
    }

    /*** Metodo para obtener todas las filas de la tabla.  */
    public Cursor fetchAll(SQLiteDatabase mDb) {

        return mDb.query(DATABASE_TABLE, 
			        		new String[] {KEY_ROWID,         		
						        		KEY_CODI_TIZO,
						    	        KEY_CODIGO,
						    	        KEY_CODI_EMPR,
						    	        KEY_CODI_UNMI,
						    	        KEY_TIPO_ZONA,
						    	        KEY_TIPO_ZONA_DSC,
						    	        KEY_ESTADO,
						    	        KEY_USUA_CREACION,
						    	        KEY_CODI_EMPR,
						    	        KEY_USUA_MODIFICACION,
						    	        KEY_FECHA_MODIFICACION
						    	        }, 
        		null, null, null, null, null);
    }

    /***Metodo para obtener una fila mediante un id.  */
    public Cursor fetch(SQLiteDatabase mDb, String CODI_TIZO) throws SQLException {

        Cursor mCursor = mDb.query(true, DATABASE_TABLE, 
			        				new String[] {KEY_ROWID,
								        		KEY_CODI_TIZO,
								    	        KEY_CODIGO,
								    	        KEY_CODI_EMPR,
								    	        KEY_CODI_UNMI,
								    	        KEY_TIPO_ZONA,
								    	        KEY_TIPO_ZONA_DSC,
								    	        KEY_ESTADO,
								    	        KEY_USUA_CREACION,
								    	        KEY_CODI_EMPR,
								    	        KEY_USUA_MODIFICACION,
								    	        KEY_FECHA_MODIFICACION
								    	        },  
								    KEY_CODI_TIZO + "='" + CODI_TIZO+"'", null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    
}
