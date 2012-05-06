package sbs.milpo.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CIElementoUnidadAdapter {

	public static final String KEY_ROWID = "_id";
	public static final String KEY_CODI_CIEU = "CODI_CIEU";
	public static final String KEY_CODI_EMPR = "CODI_EMPR";
	public static final String KEY_CODI_UNMI = "CODI_UNMI";
	public static final String KEY_ORDEN = "ORDEN";
	public static final String KEY_CODI_ELEM = "CODI_ELEM";
	public static final String KEY_CODI_UNME = "CODI_UNME";
	public static final String KEY_ESTADO = "ESTADO";
	public static final String KEY_USUA_CREACION = "USUA_CREACION";
	public static final String KEY_FECHA_CREACION = "FECHA_CREACION";
	public static final String KEY_USUA_MODIFICACION = "USUA_MODIFICACION";
	public static final String KEY_FECHA_MODIFICACION = "FECHA_MODIFICACION";
	public static final String KEY_VALOR_MAXIMO = "VALOR_MAXIMO";
	public static final String KEY_VALOR_MINIMO = "VALOR_MINIMO";
	public static final String KEY_VALOR_ENTERO = "VALOR_ENTERO";
	public static final String KEY_VALOR_DECIMAL = "VALOR_DECIMAL";
	
	private static final String DATABASE_TABLE = "CI_ELEMENTO_UNIDAD";
	

    /*** metodo insert. */
    public long insert(SQLiteDatabase mDb, String CODI_CIEU, 	String CODI_EMPR,String CODI_UNMI,String ORDEN,String CODI_ELEM,String CODI_UNME,String ESTADO,
    		String USUA_CREACION,String FECHA_CREACION,String USUA_MODIFICACION,String FECHA_MODIFICACION,String VALOR_MAXIMO, String VALOR_MINIMO, String VALOR_ENTERO, String VALOR_DECIMAL) {
    	
	        ContentValues initialValues = new ContentValues();
	        initialValues.put(KEY_CODI_CIEU,CODI_CIEU);
	        initialValues.put(KEY_CODI_EMPR,CODI_EMPR);
	        initialValues.put(KEY_CODI_UNMI,CODI_UNMI);
	        initialValues.put(KEY_ORDEN,ORDEN);
	        initialValues.put(KEY_CODI_ELEM,CODI_ELEM);
	        initialValues.put(KEY_CODI_UNME,CODI_UNME);
	        initialValues.put(KEY_ESTADO,ESTADO);
	        initialValues.put(KEY_USUA_CREACION,USUA_CREACION);
	        initialValues.put(KEY_FECHA_CREACION,FECHA_CREACION);
	        initialValues.put(KEY_USUA_MODIFICACION,USUA_MODIFICACION);
	        initialValues.put(KEY_FECHA_MODIFICACION,FECHA_MODIFICACION);
	        initialValues.put(KEY_VALOR_MAXIMO,VALOR_MAXIMO);
	        initialValues.put(KEY_VALOR_MINIMO,VALOR_MINIMO);
	        initialValues.put(KEY_VALOR_ENTERO,VALOR_ENTERO);
	        initialValues.put(KEY_VALOR_DECIMAL,VALOR_DECIMAL);
	        
        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }
    
    /*** metodo update
     */
    public boolean update(SQLiteDatabase mDb, String CODI_CIEU, String CODI_EMPR,String CODI_UNMI,String ORDEN,String CODI_ELEM,String CODI_UNME,String ESTADO,
    		String USUA_CREACION,String FECHA_CREACION,String USUA_MODIFICACION,String FECHA_MODIFICACION,String VALOR_MAXIMO,String VALOR_MINIMO, String VALOR_ENTERO, String VALOR_DECIMAL) {

        ContentValues args = new ContentValues();
	        if(!CODI_EMPR.equals(""))
	        	args.put(KEY_CODI_EMPR,CODI_EMPR);
	        if(!CODI_UNMI.equals(""))
	        	args.put(KEY_CODI_UNMI,CODI_UNMI);
	        if(!ORDEN.equals(""))
	        	args.put(KEY_ORDEN,ORDEN);
	        if(!CODI_ELEM.equals(""))
	        	args.put(KEY_CODI_ELEM,CODI_ELEM);
	        if(!CODI_UNME.equals(""))
	        	args.put(KEY_CODI_UNME,CODI_UNME);
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
	        if(!VALOR_MAXIMO.equals(""))
	        	args.put(KEY_VALOR_MAXIMO,VALOR_MAXIMO);
	        if(!VALOR_MINIMO.equals(""))
	        	args.put(KEY_VALOR_MINIMO,VALOR_MINIMO);
	        if(!VALOR_ENTERO.equals(""))
	        	args.put(KEY_VALOR_ENTERO,VALOR_ENTERO);
	        if(!VALOR_DECIMAL.equals(""))
	        	args.put(KEY_VALOR_DECIMAL,VALOR_DECIMAL);

        return mDb.update(DATABASE_TABLE, args, KEY_CODI_CIEU + "='" + CODI_CIEU+"'", null) > 0;
    }

    /*** metodo delete. */
    public boolean delete(SQLiteDatabase mDb, String CODI_CIEU) {

        return mDb.delete(DATABASE_TABLE, KEY_CODI_CIEU + "='" +CODI_CIEU+"'", null) > 0;
    }
    
    /*** metodo delete para eliminar todas las filas. */
    public boolean deleteAllRows(SQLiteDatabase mDb) {

        return mDb.delete(DATABASE_TABLE, null, null) > 0;
    }

    /**Metodo para obtener todas las filas de la tabla
    */
    public Cursor fetchAll(SQLiteDatabase mDb) {

        return mDb.query(DATABASE_TABLE, 
			        	new String[] {KEY_ROWID,
		        		KEY_CODI_CIEU,
		    	        KEY_CODI_EMPR,
		    	        KEY_CODI_UNMI,
		    	        KEY_ORDEN,
		    	        KEY_CODI_ELEM,
		    	        KEY_CODI_UNME,
		    	        KEY_ESTADO,
		    	        KEY_USUA_CREACION,
		    	        KEY_FECHA_CREACION,
		    	        KEY_USUA_MODIFICACION,
		    	        KEY_FECHA_MODIFICACION,
		    	        KEY_VALOR_MAXIMO,
		    	        KEY_VALOR_MINIMO,
		    	        KEY_VALOR_ENTERO,
		    	        KEY_VALOR_DECIMAL
		    	        }, 
        		null, null, null, null, null);
    }

    /***Metodo para obtener una fila mediante un id
     */
    public Cursor fetch(SQLiteDatabase mDb, String CODI_CIEU) throws SQLException {

        Cursor mCursor = mDb.query(true, DATABASE_TABLE, 
			        				new String[] {KEY_ROWID,
					        		KEY_CODI_CIEU,
					    	        KEY_CODI_EMPR,
					    	        KEY_CODI_UNMI,
					    	        KEY_ORDEN,
					    	        KEY_CODI_ELEM,
					    	        KEY_CODI_UNME,
					    	        KEY_ESTADO,
					    	        KEY_USUA_CREACION,
					    	        KEY_FECHA_CREACION,
					    	        KEY_USUA_MODIFICACION,
					    	        KEY_FECHA_MODIFICACION,
					    	        KEY_VALOR_MAXIMO,
					    	        KEY_VALOR_MINIMO,
					    	        KEY_VALOR_ENTERO,
					    	        KEY_VALOR_DECIMAL
					    	        }, 
					        		KEY_CODI_CIEU + "='" + CODI_CIEU+"'", null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    
    public Cursor fetchByEmpresaUnidad(SQLiteDatabase mDb, String CODIGO_EMPRESA, String CODIGO_UNIDAD) throws SQLException {

    	String strWhere=KEY_CODI_EMPR + "='" + CODIGO_EMPRESA+"' and "+KEY_CODI_UNMI+ "='" +CODIGO_UNIDAD+"'";
        Cursor mCursor = mDb.query(true, DATABASE_TABLE, 
			        				new String[] {KEY_ROWID,
					        		KEY_CODI_CIEU,
					    	        KEY_CODI_EMPR,
					    	        KEY_CODI_UNMI,
					    	        KEY_ORDEN,
					    	        KEY_CODI_ELEM,
					    	        KEY_CODI_UNME,
					    	        KEY_ESTADO,
					    	        KEY_USUA_CREACION,
					    	        KEY_FECHA_CREACION,
					    	        KEY_USUA_MODIFICACION,
					    	        KEY_FECHA_MODIFICACION,
					    	        KEY_VALOR_MAXIMO,
					    	        KEY_VALOR_MINIMO,
					    	        KEY_VALOR_ENTERO,
					    	        KEY_VALOR_DECIMAL
					    	        }, 
					    	        strWhere, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }
    
}
