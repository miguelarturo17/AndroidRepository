package sbs.milpo.database;

import java.io.File;

import sbs.milpo.controller.Sistema;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class DataBaseConeccion {


    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private final Context mCtx;
    
    private String	path= Sistema.DATABASE_PATH;
    private String SDCARD_FOLDER = Sistema.DATABASE_FOLDER;
    private String DATABASE_NAME_SDCARD = Sistema.DATABASE_NAME_SDCARD;
    private static String DATABASE_NAME_MEMORY = Sistema.DATABASE_NAME_MEMORY;
    private static final int DATABASE_VERSION = 2;
    

    public DataBaseConeccion(Context ctx) {
        this.mCtx = ctx;
    }
    
    /*** seccion en la que cargamos la base de datos, hay dos modos, uno en memoria del celular, y otro en la tarjeta SD.
     * @return this (self reference, allowing this to be chained in an initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public DataBaseConeccion open(int tipoAcceso) throws SQLException {

    	if(tipoAcceso==1){//en memoria interna.
	        mDbHelper = new DatabaseHelper(mCtx);
	        mDb = mDbHelper.getWritableDatabase();
    	}
    	if(tipoAcceso==2){ //en tarjeta SD.
	    		try {
	    			//if(path.equals("")){//en caso no haya ruta asignada.
		    			File externalStorage = Environment.getExternalStorageDirectory();
		    			path= externalStorage.getAbsolutePath();
	    			//}
	        	} catch (Exception e) {
	        	}
              
              try {
        			//File SdCard = new File(path+DATABASE_NAME2);
        			//@SuppressWarnings("unused")
        			//SQLiteDatabase managerDBSdCard = SQLiteDatabase.openOrCreateDatabase(SdCard,null);
        			SQLiteDatabase managerDBSdCard  = SQLiteDatabase.openDatabase(path + SDCARD_FOLDER +DATABASE_NAME_SDCARD, 
        					null, SQLiteDatabase.OPEN_READWRITE);
        			mDb = managerDBSdCard;
        			path= managerDBSdCard.getPath();
        		} catch (Exception e) {
        			mDb=null;
        			
        		}
    		
        	
    	}
        return this;
    }
    
    /***VERIFICA SI ESTAMOS CONECTADOS A LA BD*/
    public Boolean existeDataBase(){
    	
    	//indica que accedemos a la tarjeta SD, 1 es para memoria
    	open(Sistema.TIPO_ACCESO_BD);
    	boolean check_existeDataBase=false;
    	if(mDb!=null){
    		check_existeDataBase=true;
    	}
    	return check_existeDataBase;
    }
    
    
    
    public SQLiteDatabase getDataBase(){
    	return mDb;
    }
    
    public void beginTransaction(){
    	mDb.beginTransaction();
    }
    
    public void setSuccessful(){
    	mDb.setTransactionSuccessful();
    }
    
    public void finishTransaction(){
    	mDb.endTransaction();
    }
    
    public void close() {
    	if ( mDb != null ){
    		mDb.close();
        }
    		
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME_MEMORY ,null , DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            /*Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS notes");
            onCreate(db);*/
        }
    }

    
    
}
