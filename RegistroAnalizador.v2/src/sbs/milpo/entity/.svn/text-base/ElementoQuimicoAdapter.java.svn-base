package sbs.milpo.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ElementoQuimicoAdapter {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_CODI_ELEM = "CODI_ELEM";
    public static final String KEY_TIPO = "TIPO";
    public static final String KEY_ESTADO = "ESTADO";
    public static final String KEY_ABREVIATURA = "ABREVIATURA";
    public static final String KEY_ELEMENTO = "ELEMENTO";
    public static final String KEY_CODIGO_COMERCIAL = "CODIGO_COMERCIAL";
    public static final String KEY_NOMENCLATURA = "NOMENCLATURA";
    public static final String KEY_USUA_MODIFICACION = "USUA_MODIFICACION";
    public static final String KEY_USUA_CREACION = "USUA_CREACION";
    public static final String KEY_FECHA_MODIFICACION = "FECHA_MODIFICACION";
    public static final String KEY_FECHA_CREACION = "FECHA_CREACION";
    
    private static final String DATABASE_TABLE = "ELEMENTO_QUIMICO";


    /*** metodo insert. */
    public long insert(SQLiteDatabase mDb, String CODI_ELEM,String TIPO,String ESTADO,String ABREVIATURA,String ELEMENTO,
    		String CODIGO_COMERCIAL,String NOMENCLATURA,String USUA_MODIFICACION,String USUA_CREACION,String FECHA_MODIFICACION, String FECHA_CREACION) {
    	
	        ContentValues initialValues = new ContentValues();
	        initialValues.put(KEY_CODI_ELEM,CODI_ELEM);
	        initialValues.put(KEY_TIPO,TIPO);
	        initialValues.put(KEY_ESTADO,ESTADO);
	        initialValues.put(KEY_ABREVIATURA,ABREVIATURA);
	        initialValues.put(KEY_ELEMENTO,ELEMENTO);
	        initialValues.put(KEY_CODIGO_COMERCIAL,CODIGO_COMERCIAL);
	        initialValues.put(KEY_NOMENCLATURA,NOMENCLATURA);
	        initialValues.put(KEY_USUA_MODIFICACION,USUA_MODIFICACION);
	        initialValues.put(KEY_USUA_CREACION,USUA_CREACION);
	        initialValues.put(KEY_FECHA_MODIFICACION,FECHA_MODIFICACION);
	        initialValues.put(KEY_FECHA_CREACION,FECHA_CREACION);
	        
        return mDb.insert(DATABASE_TABLE, null, initialValues);
        
    }
    
    /*** metodo update. */
    public boolean update(SQLiteDatabase mDb, String CODI_ELEM,String TIPO,String ESTADO,String ABREVIATURA,String ELEMENTO,
    		String CODIGO_COMERCIAL,String NOMENCLATURA,String USUA_MODIFICACION,String USUA_CREACION,String FECHA_MODIFICACION, String FECHA_CREACION) {
    	
        ContentValues args = new ContentValues();
	        if(!CODI_ELEM.equals(""))
	        	args.put(KEY_CODI_ELEM,CODI_ELEM);
	        if(!TIPO.equals(""))
	        	args.put(KEY_TIPO,TIPO);
	        if(!ESTADO.equals(""))
	        	args.put(KEY_ESTADO,ESTADO);
	        if(!ABREVIATURA.equals(""))
	        	args.put(KEY_ABREVIATURA,ABREVIATURA);
	        if(!ELEMENTO.equals(""))
	        	args.put(KEY_ELEMENTO,ELEMENTO);
	        if(!CODIGO_COMERCIAL.equals(""))
	        	args.put(KEY_CODIGO_COMERCIAL,CODIGO_COMERCIAL);
	        if(!NOMENCLATURA.equals(""))
	        	args.put(KEY_NOMENCLATURA,NOMENCLATURA);
	        if(!USUA_MODIFICACION.equals(""))
	        	args.put(KEY_USUA_MODIFICACION,USUA_MODIFICACION);
	        if(!USUA_CREACION.equals(""))
	        	args.put(KEY_USUA_CREACION,USUA_CREACION);
	        if(!FECHA_MODIFICACION.equals(""))
	        	args.put(KEY_FECHA_MODIFICACION,FECHA_MODIFICACION);
	        if(!FECHA_CREACION.equals(""))
	        	args.put(KEY_FECHA_CREACION,FECHA_CREACION);
	        
        return mDb.update(DATABASE_TABLE, args, KEY_CODI_ELEM + "='" + CODI_ELEM+"'", null) > 0;
    }

    
    /*** metodo delete.  */
    public boolean delete(SQLiteDatabase mDb, String CODI_ELEM) {

        return mDb.delete(DATABASE_TABLE, KEY_CODI_ELEM + "='" + CODI_ELEM+"'", null) > 0;
    }
    
    /*** metodo delete para eliminar todas las filas. */
    public boolean deleteAllRows(SQLiteDatabase mDb) {

        return mDb.delete(DATABASE_TABLE, null, null) > 0;
    }

    /*** Metodo para obtener todas las filas de la tabla. */
    public Cursor fetchAll(SQLiteDatabase mDb) {

        return mDb.query(DATABASE_TABLE, 
			        		new String[] {KEY_ROWID,   
									    KEY_CODI_ELEM,
						    	        KEY_TIPO,
						    	        KEY_ESTADO,
						    	        KEY_ABREVIATURA,
						    	        KEY_ELEMENTO,
						    	        KEY_CODIGO_COMERCIAL,
						    	        KEY_NOMENCLATURA,
						    	        KEY_USUA_MODIFICACION,
						    	        KEY_USUA_CREACION,
						    	        KEY_FECHA_MODIFICACION,
						    	        KEY_FECHA_CREACION
			    	        			},
        		null, null, null, null, null);
    }

    /***Metodo para obtener una fila mediante un id.  */
    public Cursor fetch(SQLiteDatabase mDb, String CODI_ELEM) throws SQLException {

        Cursor mCursor = mDb.query(true, DATABASE_TABLE, 
			        				new String[] {KEY_ROWID,
								        		KEY_CODI_ELEM,
								    	        KEY_TIPO,
								    	        KEY_ESTADO,
								    	        KEY_ABREVIATURA,
								    	        KEY_ELEMENTO,
								    	        KEY_CODIGO_COMERCIAL,
								    	        KEY_NOMENCLATURA,
								    	        KEY_USUA_MODIFICACION,
								    	        KEY_USUA_CREACION,
								    	        KEY_FECHA_MODIFICACION,
								    	        KEY_FECHA_CREACION       		
						    			},
								    KEY_CODI_ELEM + "='" + CODI_ELEM+"'", null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    
}
