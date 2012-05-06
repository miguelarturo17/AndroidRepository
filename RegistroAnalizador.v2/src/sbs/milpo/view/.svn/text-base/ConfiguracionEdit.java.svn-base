package sbs.milpo.view;

import com.BasicKeyboard.R;

import sbs.milpo.controller.Sistema;
import sbs.milpo.model.dal.Configuracion;
import sbs.milpo.model.dal.UnidadMinera;
import sbs.milpo.model.dal.bl.MConfiguracion;
import sbs.milpo.model.dal.bl.MUnidadMinera;
import greendroid.app.GDActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ConfiguracionEdit extends GDActivity {
	
	EditText txtURL;
	EditText txtPROXY;
	EditText txtPUERTO; 
	static String COD_UNIDAD ="";
	static String COD_EMPRESA ="";
	UnidadMinera[] unidadMineraArray=null;
	private MConfiguracion mConfiguracion;
	private MUnidadMinera mUnidadMinera;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarContentView(R.layout.configurar_edit);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setAtributesIntent();
       
       
        mConfiguracion= new MConfiguracion(this);
        mUnidadMinera= new MUnidadMinera(this);
        
        //OBTENEMOS LAS CAJAS DE TEXTO
        txtURL = (EditText)findViewById(R.id.TxtURL);
    	txtPROXY = (EditText)findViewById(R.id.TxtPROXY);
    	txtPUERTO = (EditText)findViewById(R.id.TxtPUERTO);
    	
        /**OBTENER LAS DIRECCIONES  DE LA BASE DE DATOS*/
    	String nuevaURL= mConfiguracion.getValorConfiguracion("1");
    	//1 representa la URL de conexion
        if(!nuevaURL.equals(""))
        	txtURL.setText(nuevaURL);
        
        String nuevaPROXY= mConfiguracion.getValorConfiguracion("2");
        //2 representa la URL de proxy
        if(!nuevaPROXY.equals(""))
        	txtPROXY.setText(nuevaPROXY);
        
        String nuevoPUERTO= mConfiguracion.getValorConfiguracion("3");
        //3 representa la URL de puerto
        if(!nuevoPUERTO.equals(""))
        	txtPUERTO.setText(nuevoPUERTO);
        
        if(!COD_UNIDAD.equals(""))
        try {	
        	unidadMineraArray= mUnidadMinera.getListUnidadMinera();
            if(unidadMineraArray!=null)
            	this.cargarSpinnerUnidadMinera();
		} catch (Exception e) {
		}
        
        Button registrar= (Button)findViewById( R.id.BtnGuardarURL);
        registrar.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
        		onClickGuardar();
        		retroceder();
        	}
        });
        
    }
   
    /**CARGAMOS LOS ATRIBUTOS DEL INTENT, CUANDO INICIA LA INTERFAZ*/
    private void setAtributesIntent(){
    	
        try {
        	//obtenemos los parametros enviados por la clase anterior.
        	Bundle extras = getIntent().getExtras();
        	COD_UNIDAD = extras.getString("CODIGO_UNIDAD");
        	COD_EMPRESA = extras.getString("CODIGO_EMPRESA");
		} catch (Exception e) {
		}
    }
    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK){
			retroceder();
		}
		return false;
	}
    
    
  
    public void retroceder()
    {
    	finish();
    	Intent intent = new Intent(this, ListViewGrid.class);
    	intent.putExtra(greendroid.app.ActionBarActivity.GD_ACTION_BAR_TITLE, Sistema.TITULO_GRILLA);
		intent.putExtra("CODIGO_UNIDAD", COD_UNIDAD);
		intent.putExtra("CODIGO_EMPRESA", COD_EMPRESA);
		startActivity(intent);
    	
    }
    
    
    public void onClickGuardar(){
    	
		Configuracion configuracion= new Configuracion();
		
		try {
				configuracion.setValor(txtPROXY.getText().toString().trim());
				if(mConfiguracion.guardarValorConfiguracion("2", configuracion)){
					Sistema.URL_PROXY = txtPROXY.getText().toString().trim();
				};
		} catch (Exception e) {
		}
		
		try {
				configuracion.setValor(txtPUERTO.getText().toString().trim());
				if(mConfiguracion.guardarValorConfiguracion("3", configuracion)){
					Sistema.URL_PUERTO = txtPUERTO.getText().toString().trim();
				};
		} catch (Exception e) {
		}
		
		try {
			if(!txtURL.getText().toString().trim().equals("")){
				
				configuracion.setValor(txtURL.getText().toString().trim());
				if(mConfiguracion.guardarValorConfiguracion("1", configuracion)){
					
					Sistema.URL_BASE= txtURL.getText().toString().trim();
					Sistema.actualizarURL(Sistema.URL_BASE, Sistema.URL_DIRECTORIO_ACTIVO);//ACTUALIZAMOS LAS URLS
					
					showAlerta(R.string.title_success, R.string.succes_register_configuration, false);
					
				}else showAlerta(R.string.title_error, R.string.error_configuracion, true);
				
			}else showAlerta(R.string.title_error, R.string.error_url_vacia, true);
			
		} catch (Exception e) {
		}
    }
    
    
    /**MUESTRA UN MENSAJE PERSONALIZADO DE ALERTA*/
    public void showAlerta(int msg_title,int idMensaje, boolean flag_alerta){
    	if(flag_alerta)
	    	new AlertDialog.Builder(ConfiguracionEdit.this).setIcon(R.drawable.icon_alerta)
			.setTitle(msg_title).setMessage(idMensaje).show();
    	else
    		new AlertDialog.Builder(ConfiguracionEdit.this).setIcon(R.drawable.item_check)
    		.setTitle(msg_title).setMessage(idMensaje).show();
    };
    
    
    public void cargarSpinnerUnidadMinera()
    {
    	try {
	    	Spinner spinner = (Spinner)findViewById(R.id.spinnerUnidadMinera);
	    	spinner.setPrompt("Unidad Minera");
	    	String[] stringsUnidadMinera= new String[unidadMineraArray.length];
	    	for(int l=0; l<unidadMineraArray.length;l++){
	    		stringsUnidadMinera[l]= new String(unidadMineraArray[l].getDescripcion().toString());
	    		if(Sistema.HABILITADO.equals(unidadMineraArray[l].getEstado())){
					Sistema.SPINNER_SELECTED_UNMI=l;
				}
			} 	
	    	ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
	    			android.R.layout.simple_spinner_item,stringsUnidadMinera);  
	    	spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
	    	spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
	    	    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
	    	    
	    	    	mUnidadMinera.actualizarUnidadMinera(unidadMineraArray, position);
	    	    	COD_UNIDAD = unidadMineraArray[position].getCodigo_unidadMinera();
	    	    	
	    	    }
	    	    public void onNothingSelected(AdapterView<?> parent) {
	    	    }
	    	});
	    	spinner.setAdapter(spinnerAdapter);
	    	spinner.setSelection(Sistema.SPINNER_SELECTED_UNMI);
    	
    	} catch (Exception e) {
		}
    }
    
    
    /**METODOS QUE SE USARON*/
   /* private boolean guardarConfiguracion(String clave,Configuracion config) {
	}*/
    
   /* public static String getURLDataBase(Context context, String clave){
	}*/

    /*public UnidadMinera[] getListUnidadMinera(){
	}*/
    
   /* public static boolean guardarUnidadMineraSeleccionada(Context context, UnidadMinera[] unidadMinera, int escogido){
    }*/

    
}