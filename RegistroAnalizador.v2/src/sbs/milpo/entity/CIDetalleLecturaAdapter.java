package sbs.milpo.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CIDetalleLecturaAdapter {
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_FK_LECTURA = "id_lectura";
	public static final String KEY_CODI_DLEC = "CODI_DLEC";
	public static final String KEY_CODI_LECT = "CODI_LECT";
	public static final String KEY_CODI_ELEM = "CODI_ELEM";
	public static final String KEY_CODI_UNME = "CODI_UNME";
	public static final String KEY_VALOR = "VALOR";
	public static final String KEY_ESTADO = "ESTADO";
	public static final String KEY_USUA_CREACION = "USUA_CREACION";
	public static final String KEY_FECHA_CREACION = "FECHA_CREACION";
	public static final String KEY_USUA_MODIFICACION = "USUA_MODIFICACION";
	public static final String KEY_FECHA_MODIFICACION = "FECHA_MODIFICACION";
	
	private static final String DATABASE_TABLE = "CI_DETALLE_LECTURA";


    /*** metodo insert.*/
    public long insert(SQLiteDatabase mDb, String FK_LECTURA, String CODI_DLEC, String CODI_LECT, String CODI_ELEM, String CODI_UNME, String VALOR,
    		String ESTADO,  String USUA_CREACION,  String FECHA_CREACION,  String USUA_MODIFICACION,  String FECHA_MODIFICACION) {
    	
	        ContentValues initialValues = new ContentValues();
	        initialValues.put(KEY_FK_LECTURA,FK_LECTURA);
	        initialValues.put(KEY_CODI_DLEC,CODI_DLEC);
	        initialValues.put(KEY_CODI_LECT,CODI_LECT);
	        initialValues.put(KEY_CODI_ELEM,CODI_ELEM);
	        initialValues.put(KEY_CODI_UNME,CODI_UNME);
	        initialValues.put(KEY_VALOR,VALOR);
	        initialValues.put(KEY_ESTADO,ESTADO);
	        initialValues.put(KEY_USUA_CREACION,USUA_CREACION);
	        initialValues.put(KEY_FECHA_CREACION,FECHA_CREACION);
	        initialValues.put(KEY_USUA_MODIFICACION,USUA_MODIFICACION);
	        initialValues.put(KEY_FECHA_MODIFICACION,FECHA_MODIFICACION);
	        

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }
    
    /*** metodo update*/
    public boolean update(SQLiteDatabase mDb, String ROWID, String FK_LECTURA, String CODI_DLEC, String CODI_LECT, String CODI_ELEM, String CODI_UNME, String VALOR,
    		String ESTADO,  String USUA_CREACION,  String FECHA_CREACION,  String USUA_MODIFICACION,  String FECHA_MODIFICACION) {
    	
    	String where=KEY_ROWID + "=" + ROWID+" and "+KEY_FK_LECTURA+ "=" +FK_LECTURA;
        ContentValues args = new ContentValues();
            if(!CODI_DLEC.equals(""))
        		args.put(KEY_CODI_DLEC,CODI_DLEC);
	        if(!CODI_LECT.equals(""))
	        	args.put(KEY_CODI_LECT,CODI_LECT);
	        if(!CODI_ELEM.equals(""))
	        	args.put(KEY_CODI_ELEM,CODI_ELEM);
	        if(!CODI_UNME.equals(""))
	        	args.put(KEY_CODI_UNME,CODI_UNME);
	        if(!VALOR.equals(""))
	        	args.put(KEY_VALOR,VALOR);
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

        return mDb.update(DATABASE_TABLE, args, where, null) > 0;
    }

    /*** metodo delete. */
    public boolean delete(SQLiteDatabase mDb, String CODI_DLEC) {

        return mDb.delete(DATABASE_TABLE, KEY_CODI_DLEC + "='" + CODI_DLEC+"'", null) > 0;
    }
    
    public boolean delete(SQLiteDatabase mDb) {

        return mDb.delete(DATABASE_TABLE, null , null) > 0;
    }

    /**Metodo para obtener todas las filas de la tabla*/
    public Cursor fetchAll(SQLiteDatabase mDb) {

        return mDb.query(DATABASE_TABLE, 
			        		new String[] {KEY_ROWID,
        					KEY_FK_LECTURA,
        					KEY_CODI_DLEC,
			        		KEY_CODI_LECT,
			        		KEY_CODI_ELEM,
			        		KEY_CODI_UNME,
			        		KEY_VALOR,
			        		KEY_ESTADO,
			        		KEY_USUA_CREACION,
			        		KEY_FECHA_CREACION,
			        		KEY_USUA_MODIFICACION,
			        		KEY_FECHA_MODIFICACION
			        		}, 
        		null, null, null, null, null);
    }

    /***Metodo para obtener una fila mediante un id*/
    public Cursor fetch(SQLiteDatabase mDb, String CODI_DLEC) throws SQLException {

        Cursor mCursor = mDb.query(true, DATABASE_TABLE, 
			        				new String[] {KEY_ROWID,
        							KEY_FK_LECTURA,
        							KEY_CODI_DLEC,
					        		KEY_CODI_LECT,
					        		KEY_CODI_ELEM,
					        		KEY_CODI_UNME,
					        		KEY_VALOR,
					        		KEY_ESTADO,
					        		KEY_USUA_CREACION,
					        		KEY_FECHA_CREACION,
					        		KEY_USUA_MODIFICACION,
					        		KEY_FECHA_MODIFICACION
					        		}, 
					        		KEY_CODI_DLEC + "='" + CODI_DLEC+"'", null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }
    
    /***Metodo para obtener una fila mediante el id de una lectura*/
    public Cursor fetchByLectura(SQLiteDatabase mDb, String FK_LECTURA) throws SQLException {

        Cursor mCursor = mDb.query(true, DATABASE_TABLE, 
			        				new String[] {KEY_ROWID,
        							KEY_FK_LECTURA,
        							KEY_CODI_DLEC,
					        		KEY_CODI_LECT,
					        		KEY_CODI_ELEM,
					        		KEY_CODI_UNME,
					        		KEY_VALOR,
					        		KEY_ESTADO,
					        		KEY_USUA_CREACION,
					        		KEY_FECHA_CREACION,
					        		KEY_USUA_MODIFICACION,
					        		KEY_FECHA_MODIFICACION
					        		}, 
					        		KEY_FK_LECTURA + "='" + FK_LECTURA+"'", null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

}
