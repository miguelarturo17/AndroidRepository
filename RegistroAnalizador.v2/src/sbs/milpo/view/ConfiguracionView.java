package sbs.milpo.view;

import sbs.milpo.controller.Sistema;
import sbs.milpo.model.dal.UnidadMinera;
import sbs.milpo.model.dal.bl.MConfiguracion;
import sbs.milpo.model.dal.bl.MUnidadMinera;
import greendroid.app.GDActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class ConfiguracionView extends GDActivity {
	
	TextView txtURL, txtPROXY, txtPUERTO, txtUNMI, txtDIR; 
	static String COD_UNIDAD = "", COD_EMPRESA = "";
	private MConfiguracion mConfiguracion;
	private MUnidadMinera mUnidadMinera;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarContentView(R.layout.configurar_view);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setAtributesIntent();
		
        mConfiguracion= new MConfiguracion(this);
        mUnidadMinera= new MUnidadMinera(this);
        
        //OBTENEMOS LAS CAJAS DE TEXTO
        txtURL = (TextView)findViewById(R.id.TxtURL);
    	txtPROXY = (TextView)findViewById(R.id.TxtPROXY);
    	txtPUERTO = (TextView)findViewById(R.id.TxtPUERTO);
    	/*
    	  txtDIR = (TextView)findViewById(R.id.TxtDIR);
    	 */
    	txtUNMI = (TextView)findViewById(R.id.TxtUNMI);
    	
    	
        /**OBTENER LAS DIRECCIONES  DE LA BASE DE DATOS*/
    	String nuevaURL= mConfiguracion.getValorConfiguracion("1");
    	//1 representa la URL de conexion
        if(!nuevaURL.equals(""))
        	txtURL.setText(nuevaURL);
        
        String nuevaPROXY= mConfiguracion.getValorConfiguracion("2");
      //2 representa la URL de proxy
        if(!nuevaPROXY.equals(""))
        	txtPROXY.setText(nuevaPROXY);
        
        String nuevaDIR= mConfiguracion.getValorConfiguracion("6");
        //2 representa la URL de proxy
          if(!nuevaDIR.equals(""))
          	txtDIR.setText(nuevaDIR);
        
        String nuevoPUERTO= mConfiguracion.getValorConfiguracion("3");
      //3 representa la URL de puerto
        if(!nuevoPUERTO.equals(""))
        	txtPUERTO.setText(nuevoPUERTO);
        
        if(!COD_UNIDAD.equals(""))
        try {
        	UnidadMinera[] unidadMineraArray = mUnidadMinera.getListUnidadMinera();
            if(unidadMineraArray!=null)
            for(int l=0; l<unidadMineraArray.length;l++){
        		if(Sistema.HABILITADO.equals(unidadMineraArray[l].getEstado())){
    				txtUNMI.setText(unidadMineraArray[l].getDescripcion());
    			}
    		} 
		} catch (Exception e) {
		}
        
        
    }
   
    /**CARGAMOS LOS ATRIBUTOS DEL INTENT, CUANDO INICIA LA INTERFAZ*/
    private void setAtributesIntent(){
    	
        try {//obtenemos los parametros enviados por la clase anterior.
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

    
  /*  public static String getURLDataBase(Context context, String clave){
	}*/
    
    
   /* public UnidadMinera[] getListUnidadMinera(){
	}*/
    
}