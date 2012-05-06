package sbs.milpo.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class GeolitologiaAdapter {
	
	 public static final String KEY_ROWID = "_id"; 
	 public static final String KEY_CODI_GLIT = "CODI_GLIT";
	 public static final String KEY_CODI_EMPR = "CODI_EMPR";
	 public static final String KEY_CODI_UNMI = "CODI_UNMI";
	 public static final String KEY_LITOLOGIA = "LITOLOGIA";
	 public static final String KEY_LITOLOGIA_DESC = "LITOLOGIA_DSC";
	 public static final String KEY_ESTADO = "ESTADO";
	 
	 private static final String DATABASE_TABLE = "GEO_LITOLOGIA";
	 
	 /*** metodo insert.*/
	    public long insert(SQLiteDatabase mDb, String CODI_GLIT, String CODI_EMPR ,String CODI_UNMI,String LITOLOGIA , String LITOLOGIA_DSC, String ESTADO ) {
	    	
		        ContentValues initialValues = new ContentValues();
		        initialValues.put(KEY_CODI_GLIT , CODI_GLIT);
		        initialValues.put(KEY_CODI_EMPR, CODI_EMPR);
		        initialValues.put(KEY_CODI_UNMI, CODI_UNMI);
		        initialValues.put(KEY_LITOLOGIA, LITOLOGIA);
		        initialValues.put(KEY_LITOLOGIA_DESC, LITOLOGIA_DSC);
		        initialValues.put(KEY_ESTADO, ESTADO);
		        
	        return mDb.insert(DATABASE_TABLE, null, initialValues);
	        
	    }
	    
	    /*** metodo update*/
	    public boolean update(SQLiteDatabase mDb, String ROWID, String CODI_GLIT, String CODI_EMPR ,String CODI_UNMI,String LITOLOGIA , String LITOLOGIA_DSC, String ESTADO ) {
	    	
	        ContentValues args = new ContentValues();
		        if(!CODI_GLIT.equals(""))
		        	args.put(KEY_CODI_GLIT, CODI_GLIT);
		        if(!CODI_EMPR.equals(""))
		        	args.put(KEY_CODI_EMPR, CODI_EMPR);
		        if(!CODI_UNMI.equals(""))
		        	args.put(KEY_CODI_UNMI, CODI_UNMI);
		        if(!LITOLOGIA.equals(""))
		        	args.put(KEY_LITOLOGIA, LITOLOGIA);
		        if(!LITOLOGIA_DSC.equals(""))
		        	args.put(KEY_LITOLOGIA_DESC, LITOLOGIA_DSC);
		        if(! ESTADO.equals(""))
		        	args.put(KEY_ESTADO, ESTADO);
		        
		        
	        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + ROWID, null) > 0;
	    }

	    /*** metodo delete
	     */
	    public boolean delete(SQLiteDatabase mDb, String CODI_GLIT) {

	        return mDb.delete(DATABASE_TABLE, KEY_CODI_GLIT + "='" + CODI_GLIT+"'", null) > 0;
	    }
	    
	    public boolean deleteAllRows(SQLiteDatabase mDb) {

	        return mDb.delete(DATABASE_TABLE, null, null) > 0;
	    }

	    /*** Metodo para obtener todas las filas de la tabla
	    */
	    public Cursor fetchAll(SQLiteDatabase mDb) {

	        return mDb.query(DATABASE_TABLE, 
				        		new String[] {KEY_ROWID,   
	        								KEY_CODI_GLIT,
	        								KEY_CODI_EMPR,
	        								KEY_CODI_UNMI,
	        								KEY_LITOLOGIA,	
	        								KEY_LITOLOGIA_DESC,
	        								KEY_ESTADO
				    	        			},
	        		null, null, null, null, null);
	    }

	    /***Metodo para obtener una fila mediante un id*/
	    public Cursor fetch(SQLiteDatabase mDb, String CODI_GLIT) throws SQLException {

	        Cursor mCursor = mDb.query(true, DATABASE_TABLE, 
				        				new String[] {KEY_ROWID,
							        		KEY_CODI_GLIT,
											KEY_CODI_EMPR,
											KEY_CODI_UNMI,
											KEY_LITOLOGIA,	
											KEY_LITOLOGIA_DESC,
											KEY_ESTADO	
							    			},
									    KEY_CODI_GLIT + "='" + CODI_GLIT+"'", null, null, null, null, null);
	        if (mCursor != null) {
	            mCursor.moveToFirst();
	        }
	        return mCursor;
	    }
	    
	    /***Metodo para obtener una fila mediante un id*/
	    public Cursor fetchByEmpresaUnidad(SQLiteDatabase mDb, String CODIGO_EMPRESA,String CODIGO_UNIDAD ) throws SQLException {

	    	String strWhere=KEY_CODI_EMPR + "='" + CODIGO_EMPRESA+"' and "+KEY_CODI_UNMI+ "='" +CODIGO_UNIDAD+"'";
	        Cursor mCursor = mDb.query(true, DATABASE_TABLE, 
				        				new String[] {KEY_ROWID,
							        		KEY_CODI_GLIT,
											KEY_CODI_EMPR,
											KEY_CODI_UNMI,
											KEY_LITOLOGIA,	
											KEY_LITOLOGIA_DESC,
											KEY_ESTADO	
							    			},
							    			strWhere, null, null, null, KEY_LITOLOGIA_DESC, null);
	        if (mCursor != null) {
	            mCursor.moveToFirst();
	        }
	        return mCursor;
	    }

	 
}
