package sbs.milpo.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sbs.milpo.controller.Fecha;
import sbs.milpo.controller.Sistema;
import sbs.milpo.entity.ConfiguracionAdapter;
import sbs.milpo.entity.UsuarioAdapter;
import sbs.milpo.model.dal.Usuario;
import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.widget.Toast;


public class DataBaseUtils {
	
	// public String inputDataBasePath= "/data/data/sbs.milpo.prototipo/databases/databackup.db";
	 public String inputDataBasePath;//Obtiene la bd de la carpeta assets.
	 public String outputDataBasePath;//= "/sdcard/simbiosys/";
	 private Context context;
	 
		public DataBaseUtils(Context contextParent){
			context= contextParent;	
			File externalStorage = Environment.getExternalStorageDirectory();
			outputDataBasePath= externalStorage.getAbsolutePath() + Sistema.DATABASE_FOLDER;
		}

		
	/**EXPORTA LA BASE DE DATOS EN ASSET A UN ARCHIVO*/
	public void exportDataBase(){
		
		boolean EXPORT_SUCCESS=false;
		try {
			//myInput = new FileInputStream(inputDataBasePath);
			
			//obtenemos la base de datos en el asset
			InputStream myInput =  context.getAssets().open("data"); 
			
			//obtenemos la base de datos en la tarjeta SD
		    File directory = new File(outputDataBasePath);
		    if (!directory.exists()){// Create the folder if it doesn't exist:
		        directory.mkdirs();
		    } 
		    
		    //establecemos la ruta completa para el archivo a exportar
		    //outputDataBasePath= directory.getPath()+Sistema.DATABASE_NAME_SDCARD;
		    OutputStream myOutput = new FileOutputStream(outputDataBasePath + Sistema.DATABASE_NAME_SDCARD);
		    
		    byte[] buffer = new byte[1024];
		    int length;
		    while ((length = myInput.read(buffer))>0){
		        myOutput.write(buffer, 0, length);
		    }

		    myOutput.flush();
		    myOutput.close();
		    myInput.close();
		    
		    EXPORT_SUCCESS=true;
		    
		} catch (FileNotFoundException e) {
			Toast.makeText(context, "File Backup Unsuccesfull!", Toast.LENGTH_LONG).show();
			e.printStackTrace();
			
		} catch (IOException ex) {
			Toast.makeText(context, "IO Backup Unsuccesfull!", Toast.LENGTH_LONG).show();
			ex.printStackTrace();
		}
		
		if(EXPORT_SUCCESS)
		Toast.makeText(context, "Backup Done Succesfully!", Toast.LENGTH_LONG).show();
		else
			Toast.makeText(context, "Backup UnSuccesfull!", Toast.LENGTH_LONG).show();
		
		
		
	}
	
	
	/**CONVIERTE UNA BD EN BACKUP RENOMBRANDO EL ARCHIVO CON LA FECHA BACKUP*/
	public void setDataBaseBackup(){
		
		try {
			//outputDataBasePath= outputDataBasePath + Sistema.DATABASE_NAME_SDCARD;
			//File sdcard = new File(outputDataBasePath + Sistema.DATABASE_NAME_SDCARD);
			File from = new File(/*sdcard*/outputDataBasePath+ Sistema.DATABASE_NAME_SDCARD);
			String filename="";
			try {
				String extension=".db";
				String db_name = Sistema.DATABASE_NAME_SDCARD.replace(extension, " ");
				filename= outputDataBasePath + db_name + Fecha.getFechaActualFormateada(context) + extension;
			} catch (Exception e) {
			}
			
			
			//File to = new File(outputDataBasePath + Sistema.DATABASE_NAME_SDCARD + "2");//sdcard, filename);
			File to = new File(filename);
			from.renameTo(to);
		} catch (Exception e) {
		}
		
	}
	 
	/**VERIFICA SI LA BASE DE DATOS ESTE BLOQUEADA*/
	public boolean isDataBaseBlocked(){
		
		UsuarioAdapter uAdapter= new UsuarioAdapter();
		ConfiguracionAdapter cAdapter= new ConfiguracionAdapter();
    	DataBaseConeccion db= new DataBaseConeccion(context);
    	boolean db_blocked=false;
    	Cursor cur=null;
    	
    	try {
    		db.open(Sistema.TIPO_ACCESO_BD);
    		
    		cur= uAdapter.fetchAll(db.getDataBase());
    		//si no encuentra filas entonces esta bloqueada.
	    	if (cur.getCount()==0) db_blocked=true;
	    	cur.close();
	    	
	    	cur= cAdapter.fetchAll(db.getDataBase());
         	//si no encuentra filas entonces esta bloqueada.
     	    if (cur.getCount()==0) db_blocked=true;
     	   cur.close();
	    	
    	} catch (Exception e) {
		}finally {
            db.close();
        }

		return db_blocked;
	}	
	
	/**VERIFICA SI LA BASE DE DATOS TIENE LA VERSION MAS RECIENTE*/
	public boolean isOlderVersion(){
		
		DataBaseConeccion db= new DataBaseConeccion(context);
    	ConfiguracionAdapter adapter= new ConfiguracionAdapter();
    	String DATABASE_PHISICAL_VERSION= "";
    	boolean check_olderVersion=false;
    	try {
    		db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria
         	Cursor cur= adapter.fetch(db.getDataBase(),"5");
     	    if (cur.getCount()!=0){
     	    	cur.moveToFirst();
     			while (cur.isAfterLast() == false) {
     				DATABASE_PHISICAL_VERSION= cur.getString(3);
     			    cur.moveToNext();
     			}
     		cur.close();
     	    }
         } catch (Exception e) {
         }finally {
              db.close();
         }
         
         if(!Sistema.DATABASE_VERSION.equals(DATABASE_PHISICAL_VERSION)){ 
        	 check_olderVersion=true;
         
         }
         
         return check_olderVersion;
	}
	
	
	/***VERIFICA SI HAY FILAS EN LA TABLA USUARIO*/
    public static  boolean getDataBaseVacia(Context c){
    	
    	UsuarioAdapter uAdapter= new UsuarioAdapter();
		ConfiguracionAdapter cAdapter= new ConfiguracionAdapter();
    	DataBaseConeccion db= new DataBaseConeccion(c);
    	boolean db_vacia= false;
    	Cursor cur=null;
    	
    	try {
    		db.open(Sistema.TIPO_ACCESO_BD);
    		
    		cur= uAdapter.fetchAll(db.getDataBase());
    		//si no encuentra filas entonces esta bloqueada.
	    	if (cur.getCount()==0) db_vacia= true;
	    	cur.close();
	    	
	    	cur= cAdapter.fetchAll(db.getDataBase());
         	//si no encuentra filas entonces esta bloqueada.
     	    if (cur.getCount()==0) db_vacia= true;
     	   cur.close();
	    	
    	} catch (Exception e) {
		}finally {
            db.close();
        }

		return db_vacia;
    }
	
	
	
	/**VERIFICA LA CONEXION A LA BASE DE DATOS*/
 	public static boolean testConnectionDB(Context context){
 		//REVISAMOS LA CONEXION A LA BASE DE DATOS
 		boolean test=false;
		DataBaseConeccion db= new DataBaseConeccion(context);
	    DataBaseConeccion a = db.open(Sistema.TIPO_ACCESO_BD);//indica que accedemos a la tarjeta SD, 1 es para memoria
	    if(a.getDataBase()!=null){
	    	test=true;
	    }
		return test;
 	}
  
}
