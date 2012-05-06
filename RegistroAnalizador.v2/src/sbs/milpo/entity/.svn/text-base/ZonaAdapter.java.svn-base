package sbs.milpo.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ZonaAdapter {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_CODI_ZONA = "CODI_ZONA";
    public static final String KEY_CODI_TIZO = "CODI_TIZO";
    public static final String KEY_CODI_EMPR = "CODI_EMPR";
    public static final String KEY_CODI_UNMI = "CODI_UNMI";
    public static final String KEY_ZONA = "ZONA";
    public static final String KEY_ESTADO = "ESTADO";
    
    private static final String DATABASE_TABLE = "ZONA";


    /*** metodo insert.  */
    public long insert(SQLiteDatabase mDb, String CODI_ZONA,String CODI_TIZO,String CODI_EMPR,String CODI_UNMI,String ZONA,String ESTAD0) {
    	
	        ContentValues initialValues = new ContentValues();
	        initialValues.put(KEY_CODI_ZONA,CODI_ZONA);
	        initialValues.put(KEY_CODI_TIZO,CODI_TIZO);
	        initialValues.put(KEY_CODI_EMPR,CODI_EMPR);
	        initialValues.put(KEY_CODI_UNMI,CODI_UNMI);
	        initialValues.put(KEY_ZONA,ZONA);
	        initialValues.put(KEY_ESTADO,ESTAD0);
	        
	        
        return mDb.insert(DATABASE_TABLE, null, initialValues);
        
    }
    
    /*** metodo update. */
    public boolean update(SQLiteDatabase mDb, String CODI_ZONA,String CODI_TIZO,String CODI_EMPR,String CODI_UNMI,String ZONA ,String ESTADO) {
    	
        ContentValues args = new ContentValues();
	        if(!CODI_TIZO.equals(""))
	        	args.put(KEY_CODI_TIZO,CODI_TIZO);
	        if(!CODI_EMPR.equals(""))
	        	args.put(KEY_CODI_EMPR,CODI_EMPR);
	        if(!CODI_UNMI.equals(""))
	        	args.put(KEY_CODI_UNMI,CODI_UNMI);
	        if(!ZONA.equals(""))
	        	args.put(KEY_ZONA,ZONA);
	        if(!ESTADO.equals(""))
	        	args.put(KEY_ESTADO,ESTADO);

        return mDb.update(DATABASE_TABLE, args, KEY_CODI_ZONA + "='" + CODI_ZONA+"'", null) > 0;
    }

    /*** metodo delete. */
    public boolean delete(SQLiteDatabase mDb, String CODI_ZONA) {

        return mDb.delete(DATABASE_TABLE, KEY_CODI_ZONA + "='" + CODI_ZONA+"'", null) > 0;
    }
    
    /*** metodo delete para eliminar todas las filas*/
    public boolean deleteAllRows(SQLiteDatabase mDb) {

        return mDb.delete(DATABASE_TABLE, null, null) > 0;
    }

    /*** Metodo para obtener todas las filas de la tabla.  */
    public Cursor fetchAll(SQLiteDatabase mDb) {

        return mDb.query(DATABASE_TABLE, 
			        		new String[] {KEY_ROWID,   
			        					KEY_CODI_ZONA,
						    	        KEY_CODI_TIZO,
						    	        KEY_CODI_EMPR,
						    	        KEY_CODI_UNMI,
						    	        KEY_ZONA,
						    	        KEY_ESTADO
			    	        			},
        		null, null, null, null, null);
    }

    /***Metodo para obtener una fila mediante un id.  */
    public Cursor fetch(SQLiteDatabase mDb, String CODI_ZONA) throws SQLException {

        Cursor mCursor = mDb.query(true, DATABASE_TABLE, 
			        				new String[] {KEY_ROWID,
						        		KEY_CODI_ZONA,
						    	        KEY_CODI_TIZO,
						    	        KEY_CODI_EMPR,
						    	        KEY_CODI_UNMI,
						    	        KEY_ZONA,
						    	        KEY_ESTADO
						    			},
								    KEY_CODI_ZONA + "='" + CODI_ZONA+"'", null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }
    
    
    /**Obtiene las filas por unidadminera y empresa*/
    public Cursor fetchByUnidadEmpresa(SQLiteDatabase mDb, String CODIGO_UNIDAD, String CODIGO_EMPRESA) throws SQLException {

    	String strWhere=KEY_CODI_EMPR + "='" + CODIGO_EMPRESA+"' and "+KEY_CODI_UNMI+ "='" +CODIGO_UNIDAD+"'";
        Cursor mCursor = mDb.query(true, DATABASE_TABLE, 
			        				new String[] {KEY_ROWID,
						        		KEY_CODI_ZONA,
						    	        KEY_CODI_TIZO,
						    	        KEY_CODI_EMPR,
						    	        KEY_CODI_UNMI,
						    	        KEY_ZONA,
						    	        KEY_ESTADO
						    			},
						    			strWhere, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

}
