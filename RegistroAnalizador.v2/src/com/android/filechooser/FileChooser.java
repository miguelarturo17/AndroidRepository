package com.android.filechooser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import sbs.milpo.controller.Sistema;
import sbs.milpo.view.R;
import sbs.milpo.view.MilpoLogin;
//import java.util.Stack;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FileChooser extends ListActivity {
	
    private File currentDir;
    private FileArrayAdapter adapter;
    private ArrayAdapter<Option> adapter2;
    private static String vFolder="Carpeta";
    private static String vFile="Archivo Size: ";
    private static String vParent="parent directory";
    public static String pathSelected="";
    Dialog dialog;
    ListView lv,lv2;
    int i=0;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Explorador de Archivos");
        //setContentView(R.layout.ubicate_vehiculo_alertas);
        
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.ubicate_vehiculo_alertas);
	    dialog.setTitle("ESCOGE UNA RUTA");
	    
	    //dialog.setCancelable(true);
	    lv= (ListView)dialog.findViewById(R.id.ListAlertas);
	    //lv2= (ListView)findViewById(R.id.ListAlertas);
	    
        try {
			File externalStorage = Environment.getExternalStorageDirectory();
			String pathRoot= externalStorage.getAbsolutePath();
			currentDir = new File(pathRoot);
	        fill(currentDir);
	        
    	} catch (Exception e) {
    	}
    	i++;
    }
    
    
    private void fill(File f)
    {
    	 File[]dirs = f.listFiles();
		 this.setTitle("Current Dir: "+f.getName());
		 List<Option>dir = new ArrayList<Option>();
		 List<Option>fls = new ArrayList<Option>();
		 try{
			 for(File ff: dirs)
			 {
				if(ff.isDirectory())
					dir.add(new Option(ff.getName(),vFolder,ff.getAbsolutePath()));
				else
					fls.add(new Option(ff.getName(),vFile+ff.length(),ff.getAbsolutePath()));
			 }
		 }catch(Exception e){
		 }
		 Collections.sort(dir);
		 Collections.sort(fls);
		 dir.addAll(fls);
		 if(!f.getName().equalsIgnoreCase("sdcard"))
			 dir.add(0,new Option("Regresar", vParent, f.getParent()));
		 
		 adapter = new FileArrayAdapter(FileChooser.this, R.layout.file_chooser_view, dir);
		 this.setListAdapter(adapter);
		 
		 	
		 	/*dialog.setContentView(R.layout.file_chooser_view);
		    dialog.setTitle("ESCOGE UNA RUTA");
		    dialog.setCancelable(true);*/
		    
		 	
			//ArrayAdapter<String> adapter = new ArrayAdapter<String> (this,R.layout.ubicate_row_alerta, R.id.label , Vehiculo.arrayAlertas);
		    /////lv.setAdapter(adapter2);
		    //if(i==0)
		    	dialog.show();
		  // else
		    	//dialog.
			//ListView lv= (ListView)dialog.findViewById(R.id.ListAlertas);
			//ArrayAdapter<String> adapter = new ArrayAdapter<String> (this,R.layout.file_chooser_view, R.id.label ,THIS);
		    lv.setAdapter(adapter);
    }
    
    
    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		super.onListItemClick(l, v, position, id);
		Option o = adapter.getItem(position);
		if(o.getData().equalsIgnoreCase(vFolder)||o.getData().equalsIgnoreCase(vParent)){
				currentDir = new File(o.getPath());
				fill(currentDir);
		}
		else
		{
			onFileClick(o);
		}
	}
    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK){
			Option o = adapter.getItem(0);
			if(o.getName().equals(vParent)||o.getData().equals(vParent)){
				if(o.getData().equalsIgnoreCase(vFolder)||o.getData().equalsIgnoreCase(vParent)){
					currentDir = new File(o.getPath());
					fill(currentDir);
				}
			}else{
				Toast.makeText(this, "Advertencia: No se escogio ningún archivo", Toast.LENGTH_SHORT).show();
				finish();
			}

		}
		return false;
	}
    
    
    private void onFileClick(Option o)
    {
    	//pathSelected=o.getPath();
    	//DataBaseConeccion db= new DataBaseConeccion(this);
    	Sistema.DATABASE_PATH= o.getPath();
    	Sistema.DATABASE_NAME_SDCARD= "";
       /* if(!db.existeDataBase(Sistema.accesoBD)){
        	finish();
        	startActivity(new Intent(this, FileChooser.class));
        }*/ //deberia validar que el archivo escogido sirva
    	Toast.makeText(this, "File Clicked: "+o.getPath(), Toast.LENGTH_SHORT).show();
    	finish();
    	startActivity(new Intent(this, MilpoLogin.class));
    }
}