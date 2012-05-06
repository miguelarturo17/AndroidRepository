package sbs.milpo.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ConfiguracionAdapter {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_DESCRIPCION = "DESCRIPCION";
    public static final String KEY_LABEL = "LABEL";
    public static final String KEY_VALOR = "VALOR ";
    public static final String KEY_ESTADO = "ESTADO";
    
    private static final String DATABASE_TABLE = "CONFIGURACION";
    

    /*** metodo insert. */
    public long insert(SQLiteDatabase mDb, String DESCRIPCION,String LABEL,String VALOR,String ESTAD0) {
    	
	        ContentValues initialValues = new ContentValues();
	        initialValues.put(KEY_DESCRIPCION, DESCRIPCION);
	        initialValues.put(KEY_LABEL, LABEL);
	        initialValues.put(KEY_VALOR, VALOR);
	        initialValues.put(KEY_ESTADO,ESTAD0);
	        
	        
        return mDb.insert(DATABASE_TABLE, null, initialValues);
        
    }
    
    /*** metodo update */
    public boolean update(SQLiteDatabase mDb, String ROWID, String DESCRIPCION,String LABEL,String VALOR,String ESTAD0) {
    	
        ContentValues args = new ContentValues();
	        if(!DESCRIPCION.equals(""))
	        	args.put(KEY_DESCRIPCION,DESCRIPCION);
	        if(!LABEL.equals(""))
	        	args.put(KEY_LABEL,LABEL);
	        //if(!VALOR.equals(""))
	        	args.put(KEY_VALOR,VALOR);
	        if(!ESTAD0.equals(""))
	        	args.put(KEY_ESTADO,ESTAD0);

        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + ROWID, null) > 0;
    }

    /*** metodo delete.  */
    public boolean delete(SQLiteDatabase mDb, String ROWID) {

        return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + ROWID, null) > 0;
    }
    
    /*** metodo delete para eliminar todas las filas*/
    public boolean deleteAllRows(SQLiteDatabase mDb) {

        return mDb.delete(DATABASE_TABLE, null, null) > 0;
    }

    /*** Metodo para obtener todas las filas de la tabla. */
    public Cursor fetchAll(SQLiteDatabase mDb) {

        return mDb.query(DATABASE_TABLE, 
			        		new String[] {KEY_ROWID,   
						        		KEY_DESCRIPCION,
						        		KEY_LABEL,
						        		KEY_VALOR,
						    	        KEY_ESTADO
			    	        			},
        		null, null, null, null, null);
        
    }

    /***Metodo para obtener una fila mediante un id.  */
    public Cursor fetch(SQLiteDatabase mDb, String ROWID) throws SQLException {

        Cursor mCursor = mDb.query(true, DATABASE_TABLE, 
			        				new String[] {KEY_ROWID,
							        		KEY_DESCRIPCION,
							        		KEY_LABEL,
							        		KEY_VALOR,
							    	        KEY_ESTADO
						    			},
								    KEY_ROWID + "='" + ROWID+"'", null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }
    

    
}
