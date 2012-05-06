package sbs.milpo.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class UsuarioAdapter {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_CODI_CIUS = "CODI_CIUS";
    public static final String KEY_CODI_TRAB = "CODI_TRAB";
    public static final String KEY_USUARIO = "USUARIO";
    public static final String KEY_PASSWD = "PASSWD";
    public static final String KEY_ESTADO = "ESTADO";
    public static final String KEY_USUA_CREACION = "USUA_CREACION";
    public static final String KEY_FECHA_CREACION = "FECHA_CREACION";
    public static final String KEY_USUA_MODIFICACION = "USUA_MODIFICACION";
    public static final String KEY_FECHA_MODIFICACION = "FECHA_MODIFICACION";
    public static final String KEY_SESION = "SESION";
    public static final String KEY_ADMINISTRADOR = "ADMINISTRADOR";

    private static final String DATABASE_TABLE = "CI_USUARIO";
  


    /*** metodo insert. */
    public long insert(SQLiteDatabase mDb, String CODI_CIUS, String CODI_TRAB,String USUARIO,String PASSWD,String ESTADO,
    	     String USUA_CREACION,String FECHA_CREACION,String USUA_MODIFICACION,String FECHA_MODIFICACION, String SESION, String ADMINISTRADOR) {
    	
	        ContentValues initialValues = new ContentValues();
	        initialValues.put(KEY_CODI_CIUS,CODI_CIUS);
	        initialValues.put(KEY_CODI_TRAB,CODI_TRAB);
	        initialValues.put(KEY_USUARIO,USUARIO);
	        initialValues.put(KEY_PASSWD,PASSWD);
	        initialValues.put(KEY_ESTADO,ESTADO);
	        initialValues.put(KEY_USUA_CREACION,USUA_CREACION);
	        initialValues.put(KEY_FECHA_CREACION,FECHA_CREACION);
	        initialValues.put(KEY_USUA_MODIFICACION,USUA_MODIFICACION);
	        initialValues.put(KEY_FECHA_MODIFICACION,FECHA_MODIFICACION);
	        initialValues.put(KEY_SESION,SESION);
	        initialValues.put(KEY_ADMINISTRADOR, ADMINISTRADOR);
	        

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }
    
    /*** metodo update. */
    public boolean update(SQLiteDatabase mDb, String CODI_CIUS, String CODI_TRAB,String USUARIO,String PASSWD,String ESTADO,
   	     String USUA_CREACION,String FECHA_CREACION,String USUA_MODIFICACION,String FECHA_MODIFICACION, String SESION, String ADMINISTRADOR) {
    	
        ContentValues args = new ContentValues();
	        if(!CODI_TRAB.equals(""))
	        	args.put(KEY_CODI_TRAB,CODI_TRAB);
	        if(!USUARIO.equals(""))
	        	args.put(KEY_USUARIO,USUARIO);
	        if(!PASSWD.equals(""))
	        	args.put(KEY_PASSWD,PASSWD);
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
	        if(!SESION.equals(""))
		        args.put(KEY_SESION,SESION);
	        if(!ADMINISTRADOR.equals(""))
	        	args.put(KEY_ADMINISTRADOR, ADMINISTRADOR);

        return mDb.update(DATABASE_TABLE, args, KEY_CODI_CIUS + "='" + CODI_CIUS+"'", null) > 0;
    }

    /*** metodo delete*/
    public boolean delete(SQLiteDatabase mDb, String CODI_CIUS) {

        return mDb.delete(DATABASE_TABLE, KEY_CODI_CIUS + "='" + CODI_CIUS+"'", null) > 0;
    }
    
    /*** metodo delete para eliminar todas las filas*/
    public boolean deleteAllRows(SQLiteDatabase mDb) {

        return mDb.delete(DATABASE_TABLE, null, null) > 0;
    }

    /*** Metodo para obtener todas las filas de la tabla */
    public Cursor fetchAll(SQLiteDatabase mDb) {

        return mDb.query(DATABASE_TABLE, 
			        		new String[] {KEY_ROWID, 
        					KEY_CODI_CIUS,
							KEY_CODI_TRAB,
			        		KEY_USUARIO, 
			        		KEY_PASSWD,
			        		KEY_ESTADO,
			        		KEY_USUA_CREACION,
			        		KEY_FECHA_CREACION,
			        		KEY_USUA_MODIFICACION,
			        		KEY_FECHA_MODIFICACION,
			        		KEY_SESION,
			        		KEY_ADMINISTRADOR}, 
        		null, null, null, null, null);
    }

    /***Metodo para obtener una fila mediante un id */
    public Cursor fetch(SQLiteDatabase mDb, String CODI_CIUS) throws SQLException {

        Cursor mCursor = mDb.query(true, DATABASE_TABLE, 
			        				new String[] {KEY_ROWID,
        										KEY_CODI_CIUS,
        										KEY_CODI_TRAB,
								        		KEY_USUARIO, 
								        		KEY_PASSWD,
								        		KEY_ESTADO,
								        		KEY_USUA_CREACION,
								        		KEY_FECHA_CREACION,
								        		KEY_USUA_MODIFICACION,
								        		KEY_FECHA_MODIFICACION,
								        		KEY_SESION,
								        		KEY_ADMINISTRADOR}, 
								    KEY_CODI_CIUS + "='" + CODI_CIUS+"'", null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }
    
    /***Metodo para obtener una fila mediante un usuario y password */
    public Cursor fetchByPassword(SQLiteDatabase mDb, String USUARIO,String PASSWD) throws SQLException {

    	String strWhere=KEY_USUARIO + "='" + USUARIO+"' and "+KEY_PASSWD+ "='" +PASSWD+"'";
        Cursor mCursor = mDb.query(true, DATABASE_TABLE, 
			        				new String[] {KEY_ROWID,
        										KEY_CODI_CIUS,
        										KEY_CODI_TRAB,
								        		KEY_USUARIO, 
								        		KEY_PASSWD,
								        		KEY_ESTADO,
								        		KEY_USUA_CREACION,
								        		KEY_FECHA_CREACION,
								        		KEY_USUA_MODIFICACION,
								        		KEY_FECHA_MODIFICACION,
								        		KEY_SESION,
								        		KEY_ADMINISTRADOR}, 
								    strWhere, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }
    
    
}
