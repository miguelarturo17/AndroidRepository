package sbs.milpo.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CITipoReconocimientoAdapter {
	
	 public static final String KEY_ROWID = "_id"; 
	 public static final String KEY_CODI_CITR = "CODI_CITR";
	 public static final String KEY_ESTADO = "ESTADO";
	 public static final String KEY_TIPO_RECONOCIMIENTO_DSC = "TIPO_RECONOCIMIENTO_DSC";
	 
	 private static final String DATABASE_TABLE = "CI_TIPO_RECONOCIMIENTO";

	 
	 /*** metodo insert.
	     */
	    public long insert(SQLiteDatabase mDb, String CODI_CITR, String ESTADO, String TIPO_RECONOCIMIENTO_DSC) {
	    	
		        ContentValues initialValues = new ContentValues();
		        initialValues.put(KEY_CODI_CITR, CODI_CITR);
		        initialValues.put(KEY_ESTADO, ESTADO);
		        initialValues.put(KEY_TIPO_RECONOCIMIENTO_DSC,TIPO_RECONOCIMIENTO_DSC);
		        
	        return mDb.insert(DATABASE_TABLE, null, initialValues);
	        
	    }
	    
	    /*** metodo update
	     */
	    public boolean update(SQLiteDatabase mDb, String ROWID, String CODI_CITR, String ESTADO, String TIPO_RECONOCIMIENTO_DSC) {
	    	
	        ContentValues args = new ContentValues();
		        if(!CODI_CITR.equals(""))
		        	args.put(KEY_CODI_CITR, CODI_CITR);
		        if(! ESTADO.equals(""))
		        	args.put(KEY_ESTADO, ESTADO);
		        if(!TIPO_RECONOCIMIENTO_DSC.equals(""))
		        	args.put(KEY_TIPO_RECONOCIMIENTO_DSC, TIPO_RECONOCIMIENTO_DSC);

	        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + ROWID, null) > 0;
	    }

	    /*** metodo delete. */
	    public boolean delete(SQLiteDatabase mDb, String CODI_CITR) {

	        return mDb.delete(DATABASE_TABLE, KEY_CODI_CITR + "='" + CODI_CITR+"'", null) > 0;
	    }
	    
	    /*** metodo delete para eliminar todas las filas*/
	    public boolean deleteAllRows(SQLiteDatabase mDb) {

	        return mDb.delete(DATABASE_TABLE, null, null) > 0;
	    }

	    /*** Metodo para obtener todas las filas de la tabla
	    */
	    public Cursor fetchAll(SQLiteDatabase mDb) {

	        return mDb.query(DATABASE_TABLE, 
				        		new String[] {KEY_ROWID,   
	        								KEY_CODI_CITR, 
							    	        KEY_ESTADO,
									        KEY_ESTADO,
									        KEY_TIPO_RECONOCIMIENTO_DSC
				    	        			},
	        		null, null, null, null, null);
	    }

	    /***Metodo para obtener una fila mediante un id
	     */
	    public Cursor fetch(SQLiteDatabase mDb, String CODI_CITR) throws SQLException {

	        Cursor mCursor = mDb.query(true, DATABASE_TABLE, 
				        				new String[] {KEY_ROWID,
							        		KEY_CODI_CITR, 		        		
							        		KEY_ESTADO,
									        KEY_ESTADO,
									        KEY_TIPO_RECONOCIMIENTO_DSC
							    			},
									    KEY_CODI_CITR + "='" + CODI_CITR+"'", null, null, null, null, null);
	        if (mCursor != null) {
	            mCursor.moveToFirst();
	        }
	        return mCursor;

	    }
	    
}
