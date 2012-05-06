package sbs.milpo.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class GeoCuerpoAdapter {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_CODI_GCUE = "CODI_GCUE";
    public static final String KEY_CODI_EMPR = "CODI_EMPR";
    public static final String KEY_CODI_UNMI = "CODI_UNMI";
    public static final String KEY_CUERPO_NRO = "CUERPO_NRO";
    public static final String KEY_CUERPO = "CUERPO";
    public static final String KEY_CUERPO_DSC = "CUERPO_DSC";
    public static final String KEY_ESTADO = "ESTADO";
    
    
    private static final String DATABASE_TABLE = "GEO_CUERPO";


    /*** metodo insert.  */
    public long insert(SQLiteDatabase mDb, String CODI_GCUE, String CODI_EMPR, String CODI_UNMI, String CUERPO_NRO, String CUERPO, String CUERPO_DSC, String ESTAD0) {
    	
	        ContentValues initialValues = new ContentValues();
	        initialValues.put(KEY_CODI_GCUE, CODI_GCUE);
	        initialValues.put(KEY_CODI_EMPR, CODI_EMPR);
	        initialValues.put(KEY_CODI_UNMI, CODI_UNMI);
	        initialValues.put(KEY_CUERPO_NRO, CUERPO_NRO);
	        initialValues.put(KEY_CUERPO,CUERPO);
	        initialValues.put(KEY_CUERPO_DSC,CUERPO_DSC);
	        initialValues.put(KEY_ESTADO,ESTAD0);
	        
	        
        return mDb.insert(DATABASE_TABLE, null, initialValues);
        
    }
    
    /*** metodo update. */
    public boolean update(SQLiteDatabase mDb, String CODI_GCUE, String CODI_EMPR, String CODI_UNMI, String CUERPO_NRO, String CUERPO, String CUERPO_DSC, String ESTAD0) {
    	
        ContentValues args = new ContentValues();
	        
	        if(!CODI_EMPR.equals(""))
	        	args.put(KEY_CODI_EMPR, CODI_EMPR);
	        if(!CODI_UNMI.equals(""))
	        	args.put(KEY_CODI_UNMI, CODI_UNMI);
	        if(!CUERPO_NRO.equals(""))
	        	args.put(KEY_CUERPO_NRO, CUERPO_NRO);
	        if(!CUERPO.equals(""))
	        	args.put(KEY_CUERPO, CUERPO);
	        if(!CUERPO_DSC.equals(""))
	        	args.put(KEY_CUERPO_DSC, CUERPO_DSC);
	        if(!ESTAD0.equals(""))
	        	args.put(KEY_ESTADO, ESTAD0);

        return mDb.update(DATABASE_TABLE, args, KEY_CODI_GCUE + "='" + CODI_GCUE+"'", null) > 0;
    }

    /*** metodo delete. */
    public boolean delete(SQLiteDatabase mDb, String CODI_GCUE) {

        return mDb.delete(DATABASE_TABLE, KEY_CODI_GCUE + "='" + CODI_GCUE+"'", null) > 0;
    }
    
    /*** metodo delete para eliminar todas las filas*/
    public boolean deleteAllRows(SQLiteDatabase mDb) {

        return mDb.delete(DATABASE_TABLE, null, null) > 0;
    }

    /*** Metodo para obtener todas las filas de la tabla.  */
    public Cursor fetchAll(SQLiteDatabase mDb) {

        return mDb.query(DATABASE_TABLE, 
			        		new String[] {KEY_ROWID,   
			        					KEY_CODI_GCUE,
						    	        KEY_CODI_EMPR,
						    	        KEY_CODI_UNMI,
						    	        KEY_CUERPO_NRO,
						    	        KEY_CUERPO,
						    	        KEY_CUERPO_DSC,
						    	        KEY_ESTADO
			    	        			},
        		null, null, null, null, null);
    }

    /***Metodo para obtener una fila mediante un id.  */
    public Cursor fetch(SQLiteDatabase mDb, String CODI_GCUE) throws SQLException {

        Cursor mCursor = mDb.query(true, DATABASE_TABLE, 
			        				new String[] {KEY_ROWID,
								        		KEY_CODI_GCUE,
								    	        KEY_CODI_EMPR,
								    	        KEY_CODI_UNMI,
								    	        KEY_CUERPO_NRO,
								    	        KEY_CUERPO,
								    	        KEY_CUERPO_DSC,
								    	        KEY_ESTADO
						    			},
						    			KEY_CODI_GCUE + "='" + CODI_GCUE+"'", null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }
    
    
    /**Obtiene las filas por unidadminera y empresa*/
    public Cursor fetchByUnidad(SQLiteDatabase mDb, String CODIGO_UNIDAD) throws SQLException {

    	String strWhere=KEY_CODI_UNMI+ "='" +CODIGO_UNIDAD+"'";
        Cursor mCursor = mDb.query(true, DATABASE_TABLE, 
			        				new String[] {KEY_ROWID,
								        		KEY_CODI_GCUE,
								    	        KEY_CODI_EMPR,
								    	        KEY_CODI_UNMI,
								    	        KEY_CUERPO_NRO,
								    	        KEY_CUERPO,
								    	        KEY_CUERPO_DSC,
								    	        KEY_ESTADO
						    			},
						    			strWhere, null, null, null, KEY_CUERPO_NRO, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

}
