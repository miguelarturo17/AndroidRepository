package sbs.milpo.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class UnidadMineraAdapter {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_CODI_UNMI = "CODI_UNMI";
    public static final String KEY_CODI_EMPR = "CODI_EMPR";
    public static final String KEY_UNIDAD_MINERA = "UNIDAD_MINERA";
    public static final String KEY_UNIDAD_MINERA_DSC = "UNIDAD_MINERA_DSC";
    public static final String KEY_ESTADO = "ESTADO";
    public static final String KEY_FECHA_MODIFICACION = "FECHA_MODIFICACION";
    public static final String KEY_FECHA_CREACION = "FECHA_CREACION";
    public static final String KEY_USUA_MODIFICACION = "USUA_MODIFICACION";
    public static final String KEY_USUA_CREACION = "USUA_CREACION";  
    
    private static final String DATABASE_TABLE = "UNIDAD_MINERA";


    /*** metodo insert. */
    public long insert(SQLiteDatabase mDb, String CODI_UNMI,String CODI_EMPR,String UNIDAD_MINERA,String UNIDAD_MINERA_DSC,String ESTADO,
    		String FECHA_MODIFICACION,String FECHA_CREACION,String USUA_MODIFICACION,String USUA_CREACION) {
    	
	        ContentValues initialValues = new ContentValues();
	        initialValues.put(KEY_CODI_UNMI,CODI_UNMI);
	        initialValues.put(KEY_CODI_EMPR,CODI_EMPR);
	        initialValues.put(KEY_UNIDAD_MINERA,UNIDAD_MINERA);
	        initialValues.put(KEY_UNIDAD_MINERA_DSC,UNIDAD_MINERA_DSC);
	        initialValues.put(KEY_ESTADO,ESTADO);
	        initialValues.put(KEY_FECHA_MODIFICACION,FECHA_MODIFICACION);
	        initialValues.put(KEY_FECHA_CREACION,FECHA_CREACION);
	        initialValues.put(KEY_USUA_MODIFICACION,USUA_MODIFICACION);
	        initialValues.put(KEY_USUA_CREACION,USUA_CREACION);
	        
        return mDb.insert(DATABASE_TABLE, null, initialValues);
        
    }
    
    /*** metodo update.  */
    public boolean update(SQLiteDatabase mDb,String ROWID, String CODI_UNMI, String CODI_EMPR,String UNIDAD_MINERA,String UNIDAD_MINERA_DSC,String ESTADO,
    		String FECHA_MODIFICACION,String FECHA_CREACION,String USUA_MODIFICACION,String USUA_CREACION) {
    	
        ContentValues args = new ContentValues();
	        if(!CODI_EMPR.equals(""))
	        	args.put(KEY_CODI_EMPR,CODI_EMPR);
	        if(!UNIDAD_MINERA.equals(""))
	        	args.put(KEY_UNIDAD_MINERA,UNIDAD_MINERA);
	        if(!UNIDAD_MINERA_DSC.equals(""))
	        	args.put(KEY_UNIDAD_MINERA_DSC,UNIDAD_MINERA_DSC);
	        if(!ESTADO.equals(""))
	        	args.put(KEY_ESTADO,ESTADO);
	        if(!FECHA_MODIFICACION.equals(""))
	        	args.put(KEY_FECHA_MODIFICACION,FECHA_MODIFICACION);
	        if(!FECHA_CREACION.equals(""))
	        	args.put(KEY_FECHA_CREACION,FECHA_CREACION);
	        if(!USUA_MODIFICACION.equals(""))
	        	args.put(KEY_USUA_MODIFICACION,USUA_MODIFICACION);
	        if(!USUA_CREACION.equals(""))
	        	args.put(KEY_USUA_CREACION,USUA_CREACION);
	        
        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + ROWID, null) > 0;
    }

    /*** metodo delete.  */
    public boolean delete(SQLiteDatabase mDb, String CODI_UNMI) {

        return mDb.delete(DATABASE_TABLE, KEY_CODI_UNMI + "='" + CODI_UNMI+"'", null) > 0;
    }
    
    /*** metodo delete para eliminar todas las filas. */
    public boolean deleteAllRows(SQLiteDatabase mDb) {

        return mDb.delete(DATABASE_TABLE, null, null) > 0;
    }

    /*** Metodo para obtener todas las filas de la tabla. */
    public Cursor fetchAll(SQLiteDatabase mDb) {

        return mDb.query(DATABASE_TABLE, 
			        		new String[] {KEY_ROWID,   
									    KEY_CODI_UNMI,
									    KEY_CODI_EMPR,
						    	        KEY_UNIDAD_MINERA,
						    	        KEY_UNIDAD_MINERA_DSC,
						    	        KEY_ESTADO,
						    	        KEY_FECHA_MODIFICACION,
						    	        KEY_FECHA_CREACION,
						    	        KEY_USUA_MODIFICACION,
						    	        KEY_USUA_CREACION
			    	        			},
        		null, null, null, null, null);
    }
    
    /*** Metodo para obtener todas las filas de la tabla. */
    public Cursor fetchByEstado(SQLiteDatabase mDb, String ESTADO) {

        return mDb.query(DATABASE_TABLE, 
			        		new String[] {KEY_ROWID,   
									    KEY_CODI_UNMI,
									    KEY_CODI_EMPR,
						    	        KEY_UNIDAD_MINERA,
						    	        KEY_UNIDAD_MINERA_DSC,
						    	        KEY_ESTADO,
						    	        KEY_FECHA_MODIFICACION,
						    	        KEY_FECHA_CREACION,
						    	        KEY_USUA_MODIFICACION,
						    	        KEY_USUA_CREACION
			    	        			},
			    	        			KEY_ESTADO + "='" + ESTADO+"'", null, null, null, null);
    }

    /***Metodo para obtener una fila mediante un id.  */
    public Cursor fetch(SQLiteDatabase mDb, String CODI_UNMI) throws SQLException {

        Cursor mCursor = mDb.query(true, DATABASE_TABLE, 
			        				new String[] {KEY_ROWID,
								        		KEY_CODI_UNMI,
								        		KEY_CODI_EMPR,
								    	        KEY_UNIDAD_MINERA,
								    	        KEY_UNIDAD_MINERA_DSC,
								    	        KEY_ESTADO,
								    	        KEY_FECHA_MODIFICACION,
								    	        KEY_FECHA_CREACION,
								    	        KEY_USUA_MODIFICACION,
								    	        KEY_USUA_CREACION       		
						    			},
								    KEY_CODI_UNMI + "='" + CODI_UNMI+"'", null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    
}
