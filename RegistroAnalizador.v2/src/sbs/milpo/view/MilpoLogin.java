package sbs.milpo.view;

import com.BasicKeyboard.R;
import sbs.android.info.AboutActivity;
import sbs.milpo.controller.Sistema;
import sbs.milpo.database.DataBaseConeccion;
import sbs.milpo.database.DataBaseUtils;
import sbs.milpo.model.dal.UnidadMinera;
import sbs.milpo.model.dal.Usuario;
import sbs.milpo.model.dal.bl.MConfiguracion;
import sbs.milpo.model.dal.bl.MUnidadMinera;
import sbs.milpo.model.dal.bl.MUsuario;
import greendroid.app.GDActivity;
import greendroid.graphics.drawable.ActionBarDrawable;
import greendroid.widget.ActionBarItem;
import greendroid.widget.NormalActionBarItem;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MilpoLogin extends GDActivity {
	
	private EditText txtPassword;
	private TextView lblMensaje;
	private Spinner spinner;
	private MUsuario mUsuario;
	private MConfiguracion mConfiguracion;
	private MUnidadMinera mUnidad;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarContentView(R.layout.login_view);
        
		txtPassword = (EditText)findViewById(R.id.TxtPassword);
		txtPassword.addTextChangedListener(filterTextWatcher);
		lblMensaje = (TextView)findViewById(R.id.LblMensaje1);
		
		//siempre revisara al inicio que no haya problemas en la bd.
		if(Sistema.TIPO_ACCESO_BD == 2) 
			inicializarDataBase();
		
		//asignamos un listener al boton.
        Button registrar= (Button)findViewById( R.id.BtnAceptarLogin);
        registrar.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
        			accionLogin();
        	}
        });
        
        //inicializamos las clases que acceden a datos
        mConfiguracion= new MConfiguracion(this);
        mUsuario= new MUsuario(this);
        mUnidad= new MUnidadMinera(this);
        
        
      //siempre revisara al inicio las direcciones para conectarse al servidor.
		inicializarConexionParams();
		
        try {
        	Usuario[] oUsuario = mUsuario.getUsuariosDataBase();
        	if(oUsuario!=null) cargarSpinnerUsuario(oUsuario);
		} catch (Exception e) {
		}
        
        addActionBarItem(getActionBar() .newActionBarItem(NormalActionBarItem.class)
          .setDrawable(new ActionBarDrawable(getResources(), R.drawable.ic_action_bar_info)), R.id.gd_action_bar_view_info);

    }

    
    /**ACTION LISTENER DE LOS EDITTEXT*/
    private TextWatcher filterTextWatcher = new TextWatcher() {
	    
    	@Override
	    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	    }
	    
	    @Override
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
	    	lblMensaje.setText("");
	    }
	    
	    @Override
	    public void afterTextChanged(Editable s) {
	    }
	};
    
	/**ICIALIZAMOS LAS VARIABLES DE CONEXION CON EL SERVIDOR*/
    private void inicializarConexionParams(){
    	
    	//OBTENER LAS DIRECCIONES DE PROXY DE LA BASE DE DATOS
        try {
        	String nuevaPROXY= mConfiguracion.getValorConfiguracion("2");
            if(!nuevaPROXY.equals(""))
            	Sistema.URL_PROXY=nuevaPROXY;
            
            String nuevoPUERTO= mConfiguracion.getValorConfiguracion("3");
            if(!nuevoPUERTO.equals(""))
            	Sistema.URL_PUERTO=nuevoPUERTO;
            
		} catch (Exception e) {
		}

		//OBTENER LA DIRECCION URL DE LA BASE DE DATOS
		try {
			String nuevaURL = mConfiguracion.getValorConfiguracion("1");
			String nuevaDIR = mConfiguracion.getValorConfiguracion("6");
			//1 representa la URL de conexion
	        if(!nuevaURL.equals("") && !nuevaDIR.equals("")){
	        	Sistema.URL_BASE = nuevaURL;
	        	Sistema.URL_DIRECTORIO_ACTIVO= nuevaDIR;
	        	Sistema.actualizarURL(nuevaURL, nuevaDIR);
	        }
		} catch (Exception e) {
		}
    }
    
    
    /**Permite activar el boton en la parte superior derecha del ActionBar*/
    @Override
    public boolean onHandleActionBarItemClick(ActionBarItem item, int position) {
        switch (item.getItemId()) {
        
           case R.id.gd_action_bar_view_info:
        	   startActivity(new Intent(this, AboutActivity.class));
                return true;
                
            default:
                return super.onHandleActionBarItemClick(item, position);
        }
    }
    
    
    /**carga un spinner de usuarios en el login*/
    public void cargarSpinnerUsuario(Usuario[] usuario){
    	try {
	    	spinner = (Spinner)findViewById(R.id.spinnerUsuario);
	    	spinner.setPrompt("USUARIOS");
	    	String[] stringsUsuario= new String[usuario.length];
	    	
	    	for(int l=0; l<usuario.length;l++){
	    		try {
	    			stringsUsuario[l]= new String(usuario[l].getUsuario().toString());
				} catch (Exception e) {
				}
			}
	    	
	    	ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
	    			android.R.layout.simple_spinner_item, stringsUsuario);    
	    	spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
	    	spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
	    	    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
	    	    	lblMensaje.setText("");
	    	    }
	    	    public void onNothingSelected(AdapterView<?> parent) {
	    	    }
	    	});
	
	    	spinner.setAdapter(spinnerAdapter);
    	
		} catch (Exception e) {
		}
    };
    
    
    /**CARGAMOS LOS ATRIBUTOS DEL INTENT, CUANDO INICIA LA INTERFAZ*/
    private void setAtributesIntentGrilla(){
    	
    	try {//ENVIAMOS PARAMETROS A LA CLASE QUE VAMOS A INICIALIZAR
			UnidadMinera oUnidad= mUnidad.getUnidadMinera();
			Sistema.TITULO_GRILLA= oUnidad.getDescripcion()+": Lecturas";
			
			Intent intent = new Intent(this, ListViewGrid.class);
			intent.putExtra(greendroid.app.ActionBarActivity.GD_ACTION_BAR_TITLE, Sistema.TITULO_GRILLA);
			intent.putExtra("CODIGO_UNIDAD", oUnidad.getCodigo_unidadMinera());
			intent.putExtra("CODIGO_EMPRESA", oUnidad.getCodigo_empresa());
	        startActivity(intent);
	        
	        if(oUnidad.getCodigo_unidadMinera().equals("")){
	        	Toast.makeText(this, R.string.error_unmi, Toast.LENGTH_SHORT).show();
	        }
		} catch (Exception e) {
		}
    }
    
    /**Procedimiento para guardar a partir del click en el boton*/
    private void accionLogin(){
    	
    	String usuario= spinner.getSelectedItem().toString();
    	String clave= txtPassword.getText().toString();
		Usuario user= mUsuario.getCredenciales( usuario, clave);
		
		if (!(user==null)&&!user.getUsuario().equals(""))
		{
			Sistema.SESION_USUARIO= user.getUsuario();
			Sistema.SESION_PASSWORD= user.getPassword();
			Sistema.SESION_ADMINISTRADOR=user.getAdministrador();
			
			setAtributesIntentGrilla();
			this.finish();
		}else{
			//if(getDataBaseVacia()==null){//Si la base de datos esta vacia hacemos varias pruebas
			if(DataBaseUtils.getDataBaseVacia(this)== true){//Si la base de datos esta vacia hacemos varias pruebas
				new AlertDialog.Builder(this).setTitle(R.string.title_error).setMessage(R.string.error_conexion_bd).show();
				
			}else Toast.makeText(this, R.string.title_error_login, Toast.LENGTH_SHORT).show();
			
			lblMensaje.setText("Vuelva a intentarlo...");
		}
    }
    
    
    /***VERIFICA LOS POSIBLES PROBLEMAS DE CONEXION DE LA BASE DE DATOS*/
    public void inicializarDataBase(){
    	
    	DataBaseConeccion db= new DataBaseConeccion(this);
    	DataBaseUtils dbUtil= new DataBaseUtils(this);
    	
    	/*Al abrir la aplicación SI se encuentra la BD pero NO SE PUEDE ABRIR (caso improbable)*/
    	if(db.existeDataBase() == true && dbUtil.isDataBaseBlocked() == true){
	    	try {
	    		dbUtil.setDataBaseBackup();
	    		dbUtil.exportDataBase();
			} catch (Exception e) {
			}
    	}
    	
    	/*en caso la aplicación detecte que existe una BD instalada y la versión de esta no es la correcta
    	  hará un backup de la existente e instalará la nueva versión, para que pueda funcionar correctamente.*/
    	if(db.existeDataBase() == true && dbUtil.isOlderVersion() == true){
	    	try {
	    		dbUtil.setDataBaseBackup();
	    		dbUtil.exportDataBase();
			} catch (Exception e) {
			}
    	}
    	
    	/*Al abrir la aplicación NO se encuentra la BD (aplicación instalada por primera vez)
    	En este caso la aplicación creara el directorio SIMBIOSYS y dentro de el la base de datos.*/
    	if(!db.existeDataBase() ){//falta  revisar el archivo fisico
	    	try {
				dbUtil.exportDataBase();
			} catch (Exception e) {
			}
    	}
    	
    }
    
    
    /***OBTIENE TODOS LOS USUARIOS EN LA BD*/
   /* public Usuario[] getUsuariosDataBase(){
    }*/
    
    /***VERIFICA LAS  CREDENCIALES EN LA BD PARA UN USUARIO*/
   /* public Usuario getCredencialDataBase(String usuario, String password){
    } */
    
    /**OBTIENE LA UNIDAD MINERA HABILITADA*/
   /* public UnidadMinera getUnidadMinera(){
	}*/
    	
    /**OBTIENE LA URL DE CONEXION DE LA BASE DE DATOS*/	
    /*public static String getValorConfiguracionDataBase(Context context, String clave){
    }*/
    	
    	

}