package sbs.milpo.view;

import greendroid.app.GDActivity;
import greendroid.graphics.drawable.ActionBarDrawable;
import greendroid.widget.ActionBarItem;
import greendroid.widget.NormalActionBarItem;
import greendroid.widget.QuickAction;
import greendroid.widget.QuickActionBar;
import greendroid.widget.QuickActionWidget;
import greendroid.widget.QuickActionWidget.OnQuickActionClickListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.BasicKeyboard.R;
import sbs.android.info.AboutActivity;
import sbs.milpo.controller.SincEnvioController;
import sbs.milpo.controller.SincRecepcionController;
import sbs.milpo.controller.Sistema;
import sbs.milpo.database.DataBaseUtils;
import sbs.milpo.model.dal.Lectura;
import sbs.milpo.model.dal.Zona;
import sbs.milpo.model.dal.bl.MLectura;
import sbs.milpo.model.dal.bl.MZona;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
	 
/**
 * Purpose: Lista simple que muestra los datos en forma de grid
 * @author SIMBIOSYS SOFTWARE S.A.C.   
 * @version 1.0 4/20/11
 * @see proyecto: SIMBIOSYS SOFTWARE LIBRARY.  
 */
public class ListViewGrid extends GDActivity {
	    /** Called when the activity is first created. */
		private QuickActionWidget mQuickBar;
		private SimpleAdapter adapter;
		private ListView lv;
		private String TIPO_ZONA="", COD_UNIDAD ="", COD_EMPRESA ="", COD_ZONA ="", msg_waiting="Por favor espere...";
		private Zona[] zona = null;
		Lectura[] lecturaEnvio, lecturaEliminacion; 
		
		//MANEJAMOS LOS MENUS DE LA LISTA
		static int  count=0, rowAction=0, TIPO_LECTURA=0, TIPO_FAJA=0, TIPO_PERFORACION=1 ;
		int quickCreacion= -1, quickModificacion= -1, quickEliminacion= -1;
		
		MLectura mLectura;
		MZona mZona;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setActionBarContentView(R.layout.list_grid_view);
	        setAtributesIntent();
	        
	        mLectura = new MLectura(this);
	        mZona= new MZona(this);
	        
			zona = mZona.getZonas(COD_UNIDAD, COD_EMPRESA);
			if(zona!=null)
				cargarSpinner(zona);
	        
	        addActionBarItem(getActionBar()
	                .newActionBarItem(NormalActionBarItem.class)
	                .setDrawable(new ActionBarDrawable(getResources(), R.drawable.ic_action_bar_info)), R.id.gd_action_bar_view_info);

	        
	        lv= (ListView)findViewById(R.id.listview);
	        lv.setOnItemClickListener(new OnItemClickListener() {
	        	    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
	        	    	rowAction = position;  
	        	    	lecturaEnvio = mLectura.getLecturasZona(COD_ZONA);
	        	    	if(TIPO_LECTURA!=TIPO_FAJA || !lecturaEnvio[rowAction].getEstado().equals(Sistema.HABILITADO)){
		        	    	prepareQuickActionBar();
		        	    	mQuickBar.show(view);
	        	    	}		
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
	    
	    
	    /**INICIALIZAMOS EL COMPONENTE QUICKACTION*/
	    private void prepareQuickActionBar() {
	    	
	        mQuickBar = new QuickActionBar(this);
	        int i=0;
	        
	        //solo se puede clonar para perforacion
	        if(TIPO_LECTURA!=TIPO_FAJA){
	        	quickCreacion=i;i++;
	        mQuickBar.addQuickAction(new 
	        		MyQuickAction(this, R.drawable.gd_action_bar_add, R.string.label_new));
	        }
	        //solo se puede modificar las lecturas no confirmadas
	        if(!lecturaEnvio[rowAction].getEstado().equals(Sistema.HABILITADO)){
	        	quickModificacion=i;i++;
	        	mQuickBar.addQuickAction(new 
	        			MyQuickAction(this, R.drawable.gd_action_bar_edit, R.string.label_edit));
	        }
	        //solo se puede modificar las lecturas no confirmadas
	        if(!lecturaEnvio[rowAction].getEstado().equals(Sistema.HABILITADO)){
	        	quickEliminacion=i;
		        mQuickBar.addQuickAction(new 
		        		MyQuickAction(this, R.drawable.gd_action_bar_share, R.string.label_delete));
	        }
	        mQuickBar.setOnQuickActionClickListener(mActionListener);
	        
	    }
	    
	    
	    /**LISTENER DEL QUICKACTION*/
	    private OnQuickActionClickListener mActionListener = new OnQuickActionClickListener() {
	        public void onQuickActionClicked(QuickActionWidget widget, int position) {
	        	Lectura[] lectura = mLectura.getLecturasZona(COD_ZONA);
	        	
	        	if(quickCreacion==position){
	        		finish();
	        		PerforacionClonacion.setLectura(lectura[rowAction]);
	        		//INICIAMOS LA NUEVA INTERFAZ 'PerforacionClonacion'
	        		String title=zona[Sistema.SPINNER_SELECTED_ZONA].getDescripcion().toString()+": CLONACION";
	        		personalizarIntent(title, PerforacionClonacion.class);
	        	}
	        	
	        	if(quickModificacion==position){
	        		finish();
	        		if(TIPO_LECTURA==TIPO_FAJA){
        	    		FajaModificacion.setLectura(lectura[rowAction]);
        	    		//INICIAMOS LA NUEVA INTERFAZ 'FajaModificacion'
        	    		String title=zona[Sistema.SPINNER_SELECTED_ZONA].getDescripcion().toString()+": MODIFICACION";
    	        		personalizarIntent(title, FajaModificacion.class);
        	    		
        	    	}else{
        	    		PerforacionModificacion.setLectura(lectura[rowAction]);
        	    		//INICIAMOS LA NUEVA INTERFAZ 'PerforacionModificacion'
        	    		String title=zona[Sistema.SPINNER_SELECTED_ZONA].getDescripcion().toString()+": MODIFICACION";
    	        		personalizarIntent(title, PerforacionModificacion.class);
        	    	}

	        	}
	        	
	        	if(quickEliminacion==position){
	        		lecturaEliminacion= lectura;
	        		eliminacionDialog();
	        	}
	        }
	    };
	    
	    
	    /**PERSONALIZA LOS ATRIBUTOS DE UN INTENT PARA ENVIAR A OTRA INTERFAZ*/
	    private void personalizarIntent(String iTitle, Class<?> klassOut) {
	       
	        Intent intent = new Intent(ListViewGrid.this, klassOut);
    		intent.putExtra(greendroid.app.ActionBarActivity.GD_ACTION_BAR_TITLE, iTitle);
	    	intent.putExtra("CODIGO_UNIDAD", COD_UNIDAD);
			intent.putExtra("CODIGO_EMPRESA", COD_EMPRESA);
			intent.putExtra("CODIGO_ZONA", COD_ZONA);
			startActivity(intent);	
	    }
	    
	    
	    /**REDEFINIMOS LA CLASE QUICKACTION PARA PERSONALIAR NUESTRO MENU PARA LA GRILLA*/
	    private static class MyQuickAction extends QuickAction {
	        
	        private static final ColorFilter BLACK_CF = new LightingColorFilter(Color.BLACK, Color.BLACK);
	        public MyQuickAction(Context ctx, int drawableId, int titleId) {
	            super(ctx, buildDrawable(ctx, drawableId), titleId);
	        }
	        private static Drawable buildDrawable(Context ctx, int drawableId) {
	            Drawable d = ctx.getResources().getDrawable(drawableId);
	            d.setColorFilter(BLACK_CF);
	            return d;
	        }
	        
	    }
	    
	    /**CUADRO DE DIALOGO DE ELIMINACION*/
	    public void eliminacionDialog(){
	    	
	    	AlertDialog alert =	new AlertDialog.Builder(this).setMessage(R.string.delete_dialog).create();
			alert.setTitle(R.string.title_delete);
			alert.setIcon(R.drawable.icon_recycle);
			alert.setCancelable(false);
			alert.setButton("SI",new DialogInterface.OnClickListener() {
				 public void onClick(DialogInterface dialog, int which) {
					 
					 boolean check = mLectura.eliminacionLogicaLectura(lecturaEliminacion[rowAction]);
		        		if(check){
		        			Toast.makeText(ListViewGrid.this, R.string.succes_delete, Toast.LENGTH_SHORT).show();
		        			lecturaEliminacion = mLectura.getLecturasZona(COD_ZONA);
		        			
		        			if(TIPO_LECTURA==TIPO_FAJA){
		        				cargarListaFaja(lecturaEliminacion);
		        				
		        			}else cargarListaPerforacion(lecturaEliminacion);
		        			
		        		}else showAlerta(R.string.title_error, R.string.error_operacion);
				     return;
				      }});
			alert.setButton2("NO", new DialogInterface.OnClickListener() {
				 public void onClick(DialogInterface dialog, int which) {
				    return;
				 }});
			alert.show();
	    }
	    
	    
	    /**EQUIVALENTE A LA OPCION SALIR, CUANDO SE PRESIONA EL BOTON FISICO SALIR*/
	    @Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {

			if (keyCode == KeyEvent.KEYCODE_BACK){

				AlertDialog alert =	new AlertDialog.Builder(this).create();
				alert.setTitle(R.string.exit_dialog);
				alert.setCancelable(false);
				alert.setIcon(R.drawable.icon_exit);
				alert.setButton("SI",new DialogInterface.OnClickListener() {
					 public void onClick(DialogInterface dialog, int which) {
						 finish();
					     return;
					      }});
				alert.setButton2("NO", new DialogInterface.OnClickListener() {
					 public void onClick(DialogInterface dialog, int which) {
					    return;
					 }});
				alert.show();
			}
			return false;
		}

	    
	    /**ESTABLECEMOS LA ACCION PARA EL BOTON DE AYUDA*/
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
	    
	    
	    /**REINICIA LA APLICACION*/
	    public void restartApplication(){
	    	
	    	AlertDialog alert =	new AlertDialog.Builder(this).setMessage(R.string.label_restart).create();
			alert.setTitle(R.string.title_success);
			alert.setCancelable(false);
			alert.setIcon(R.drawable.icon_data_act);
			alert.setButton("ACEPTAR",new DialogInterface.OnClickListener() {
			      public void onClick(DialogInterface dialog, int which) {
			    	  try {
			    		  	finish();
					    	Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(Sistema.PACKAGE_RESTART);
					        //.getLaunchIntentForPackage( getBaseContext().getPackageName() );//Reiniciara la misma ventana
							i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(i);
					    } catch (Exception e) {
						}
			          return;
			      }});
			alert.show();
	    	
	     }
	    
	    
	    /**PREPARA UN SPINNER DE ZONAS*/
	    public void cargarSpinner(Zona[] sZona){
		    try {
		    	Spinner spinner = (Spinner)findViewById(R.id.spinner1);
		    	spinner.setPrompt("Ubicacion");
		    	String[] stringsZona= new String[sZona.length];
		    	for(int l=0; l<sZona.length;l++){
					stringsZona[l]= new String(sZona[l].getDescripcion().toString());
				}
		    	
		    	ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
		    			android.R.layout.simple_spinner_item, stringsZona);  
		    	spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
		    	spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    	    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		    	    	
		    	    	//nos sirve para preparar el registro.
		    	    	Sistema.SPINNER_SELECTED_ZONA=position;
		    	    	COD_ZONA =zona[position].getCodigo_zona();
		    	    	TIPO_ZONA= zona[position].getCodigo_tipoZona();
		    	    	Lectura[] lectura = mLectura.getLecturasZona(COD_ZONA);
	    	    		if(TIPO_ZONA.equals(Sistema.ZONA_FAJA)){
		    	    		cargarListaFaja(lectura);
		    	    	} else cargarListaPerforacion(lectura);
		    	    }
		    	    public void onNothingSelected(AdapterView<?> parent) {
		    	    }
		    	});
	
		    	spinner.setAdapter(spinnerAdapter);
		    	spinner.setSelection(Sistema.SPINNER_SELECTED_ZONA);
		    } catch (Exception e) {
			}
	    };
	    
	    
	    
	    
	    /**PREPARA LA INFORMACION PARA  MOSTRAR LA LISTA DE FAJAS*/
	    public void cargarListaFaja(Lectura[] lect){
	    	
	    	// create the grid item mapping
	    	//cargando las columnas
	    	TIPO_LECTURA=TIPO_FAJA;
	        String[] fromCol = new String[] {"rowid", "c1", "c2", "c3", "c4","c5","c6","c7","c8"};
	        TextView header1= (TextView)findViewById( R.id.header_item1);
	        header1.setText("");
	        TextView header2= (TextView)findViewById( R.id.header_item2);
	        header2.setText("Fecha");
	        TextView header3= (TextView)findViewById( R.id.header_item3);
	        header3.setText("%Cu");
	        TextView header4= (TextView)findViewById( R.id.header_item4);
	        header4.setText("%Ag");
	        TextView header5= (TextView)findViewById( R.id.header_item5);
	        header5.setText("%Pb");
	        TextView header6= (TextView)findViewById( R.id.header_itemFe);
	        header6.setText("%Zn");
	        TextView headerFe= (TextView)findViewById( R.id.header_item6);
	        headerFe.setText("%Fe");
	        TextView header7= (TextView)findViewById( R.id.header_item7);
	        header7.setText("Peso(t)");
	        TextView header8= (TextView)findViewById( R.id.header_item8);
	        header8.setText("");
	        
	        TextView header20= (TextView)findViewById( R.id.header_item20);
	        header20.setText("");
	        TextView header21= (TextView)findViewById( R.id.header_item21);
	        header21.setText("");
	        TextView header22= (TextView)findViewById( R.id.header_item22);
	        header22.setText("");
	        TextView header23= (TextView)findViewById( R.id.header_item23);
	        header23.setText("");
	        
	        //cargando las filas
	        int[] toRow = new int[] { 
		        		R.id.list_grid_item1, 
		        		R.id.list_grid_item2, 
		        		R.id.list_grid_item3, 
		        		R.id.list_grid_item4, 
		        		R.id.list_grid_item5,
		        		R.id.list_grid_itemFe,
		        		R.id.list_grid_item6,
		        		R.id.list_grid_item7,
	        		};
	 
	        // prepare the list of all records
	        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
	        if(lect!=null)
	        for(int i = 0; i < lect.length; i++){
	            HashMap<String, String> map = new HashMap<String, String>();
	            
	            try {
		            map.put(fromCol[0], "| " + (i+1));
		        } catch (Exception e) {
				}
	            try {
	            	if(lect[i].getFecha()!=null)
		            map.put(fromCol[1], "| "+lect[i].getFecha());
	            } catch (Exception e) {
				}
	            try {
	            	if(lect[i].getExtraCU()!=null)
	            	map.put(fromCol[2], "| "+lect[i].getExtraCU());
	            } catch (Exception e) {
				}
	            try {
	            	if(lect[i].getExtraAG()!=null)
		            map.put(fromCol[3], "| "+lect[i].getExtraAG());
	            } catch (Exception e) {
				}
	            try {
	            	if(lect[i].getExtraPB()!=null)
		            map.put(fromCol[4], "| "+lect[i].getExtraPB());
	            } catch (Exception e) {
				}
	            try {
	            	if(lect[i].getExtraZN()!=null)
		            map.put(fromCol[5], "| "+lect[i].getExtraZN());
				} catch (Exception e) {
				}
				try {
	            	if(lect[i].getExtraFE()!=null)
		            map.put(fromCol[6], "| "+lect[i].getExtraFE());
				} catch (Exception e) {
				}
				try {
					map.put(fromCol[7], "|  " + lect[i].getPeso());
				} catch (Exception e) {
				}
	            
	            fillMaps.add(map);
	        }
	 
	        adapter = new SimpleAdapter(this, fillMaps, R.layout.list_grid_item, fromCol, toRow);
	        lv.setAdapter(adapter);
	        
	    }
	    
	    
	    /**PREPARA LA INFORMACION PARA  MOSTRAR LA LISTA DE PERFORACIONES*/
	    public void cargarListaPerforacion(Lectura[] lect){
		    	
		    	//cargando las columnas
	    		TIPO_LECTURA=TIPO_PERFORACION;
		        String[] fromCol = new String[] {"rowid", "c1", "c2", "c3", "c4","c5","c6","c7","c8", /**/"c9","c10","c11","c12"};
		        TextView header1= (TextView)findViewById( R.id.header_item1);
		        header1.setText("");
		        TextView header2= (TextView)findViewById( R.id.header_item2);
		        header2.setText("Fecha");
		        TextView header3= (TextView)findViewById( R.id.header_item3);
		        header3.setText("TAL");
		        TextView header4= (TextView)findViewById( R.id.header_item4);
		        header4.setText("TR");
		        TextView header5= (TextView)findViewById( R.id.header_item5);
		        header5.setText("%Cu");
		        TextView header6= (TextView)findViewById( R.id.header_itemFe);
		        header6.setText("%Ag");
		        TextView header7= (TextView)findViewById( R.id.header_item6);
		        header7.setText("%Pb");
		        TextView header8= (TextView)findViewById( R.id.header_item7);
		        header8.setText("%Zn");
		        TextView header9= (TextView)findViewById( R.id.header_item8);
		        header9.setText("%Fe");
		        
		        TextView header20= (TextView)findViewById( R.id.header_item20);
		        header20.setText("Litologia");
		        TextView header21= (TextView)findViewById( R.id.header_item21);
		        header21.setText("Cuerpo");
		        TextView header22= (TextView)findViewById( R.id.header_item22);
		        header22.setText("Desde");
		        TextView header23= (TextView)findViewById( R.id.header_item23);
		        header23.setText("Hasta");
		        
		        //cargando las filas
		        int[] toRow = new int[] { 
			        		R.id.list_grid_item1, 
			        		R.id.list_grid_item2, 
			        		R.id.list_grid_item3, 
			        		R.id.list_grid_item4, 
			        		R.id.list_grid_item5,
			        		R.id.list_grid_itemFe,
			        		R.id.list_grid_item6,
			        		R.id.list_grid_item7,
			        		R.id.list_grid_item8,
			        		R.id.list_grid_item20,
			        		R.id.list_grid_item21,
			        		R.id.list_grid_item22,
			        		R.id.list_grid_item23
		        		};
		 
		        // prepare the list of all records
		        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
		        if(lect!=null)
		        for(int i = 0; i < lect.length; i++){
		            HashMap<String, String> map = new HashMap<String, String>();
		            
		            try {
			            map.put(fromCol[0], "| " + (i+1));
			        } catch (Exception e) {
					}
		            try {
		            	if(lect[i].getFecha()!=null)
			            map.put(fromCol[1], "| "+lect[i].getFecha());
		            } catch (Exception e) {
					}
		            try {
						map.put(fromCol[2], "| "+lect[i].getNroTaladro());
					} catch (Exception e) {
					}
					try {
						map.put(fromCol[3], "| "+lect[i].getValor());
					} catch (Exception e) {
					}
		            try {
		            	if(lect[i].getExtraCU()!=null)
		            	map.put(fromCol[4], "| "+lect[i].getExtraCU());
		            } catch (Exception e) {
					}
		            try {
		            	if(lect[i].getExtraAG()!=null)
			            map.put(fromCol[5], "| "+lect[i].getExtraAG());
		            } catch (Exception e) {
					}
		            try {
		            	if(lect[i].getExtraPB()!=null)
			            map.put(fromCol[6], "| "+lect[i].getExtraPB());
		            } catch (Exception e) {
					}
		            try {
		            	if(lect[i].getExtraZN()!=null)
			            map.put(fromCol[7], "| "+lect[i].getExtraZN());
					} catch (Exception e) {
					}
					///////////////////////
					try {
		            	if(lect[i].getExtraFE()!=null)
			            map.put(fromCol[8], "| "+lect[i].getExtraFE());
					} catch (Exception e) {
					}
		            /////////////////////////////
					try {
		            	if(lect[i].getLito()!=null)
			            map.put(fromCol[9], "| "+lect[i].getLito());
		            } catch (Exception e) {
					}
		            try {
		            	if(lect[i].getCodigo_cuerpo()!=null)
			            map.put(fromCol[10], "| "+lect[i].getCodigo_cuerpo());
		            } catch (Exception e) {
					}
		            try {
		            	if(lect[i].getDesde()!=null)
			            map.put(fromCol[11], "| "+lect[i].getDesde());
					} catch (Exception e) {
					}
					///////////////////////
					try {
		            	if(lect[i].getHasta()!=null)
			            map.put(fromCol[12], "| "+lect[i].getHasta());
					} catch (Exception e) {
					}
					//////////////////////////////
					
		            fillMaps.add(map);
		        }
		 
		        // fill in the grid_item layout
		        adapter = new SimpleAdapter(this, fillMaps, R.layout.list_grid_item, fromCol, toRow);
		        lv.setAdapter(adapter);
		    }
	    
	    
	    public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.milpo_menu, menu);
	        return true;
	    }
	    
	    
	  //activa los botones del menu general
	    public boolean onOptionsItemSelected(MenuItem item) {    
	    	switch (item.getItemId()) {
	    		case R.id.menu_item1:  
	    			//MENU PARA ESCOGER LA VENTANA DEL ITEM DE REGISTRO DE PERFORACION O FAJA
	    			finish();
	    			if(TIPO_ZONA.equals(Sistema.ZONA_FAJA)){
	    				try {
	    					finish();
	    					//INICIAMOS LA NUEVA INTERFAZ 'FajaRegistro'
	        	    		String title=zona[Sistema.SPINNER_SELECTED_ZONA].getDescripcion().toString()+": Registro Faja";
	    	        		personalizarIntent(title, FajaRegistro.class);
	    				} catch (Exception e) {}
		    		}else{
		    			try {
		    				finish();
		    				//INICIAMOS LA NUEVA INTERFAZ 'PerforacionRegistro'
	        	    		String title=zona[Sistema.SPINNER_SELECTED_ZONA].getDescripcion().toString()+": Registro Perforacion";
	    	        		personalizarIntent(title, PerforacionRegistro.class);
		    			} catch (Exception e) {}
		    		}
	        		break;
	        		
	    		case R.id.menu_item2: 
	    			//ENCENDEMOS EL WIFI PARA INICIAR LA SINCRONIZACION
	    			if(mLectura.existeLecturaSinConfirmar())//si es que hay lecturas sin confirmar
	    				new AddWifiSincTask().execute();
	    			else showAlerta(R.string.title_warning, R.string.error_no_lecturas);
	    			
	    			break;
	    			
	    		case R.id.menu_item3:  
	    			//ENCENDEMOS EL WIFI PARA INICIAR LA SINCRONIZACION
	    				new AddWifiActTask().execute();
	    			
	        		break;	
	        		
	    		case R.id.menu_item4:  
	    			//MENU PARA ESCOGER LA VENTANA DEL ITEM CONFIGURACION
	    			this.finish();
	    			if(Sistema.SESION_ADMINISTRADOR.equals("1")){
	    				//INICIAMOS LA NUEVA INTERFAZ 'ConfiguracionEdit'
        	    		String title="CONFIGURACION";
    	        		personalizarIntent(title, ConfiguracionEdit.class);
	    			}else{
	    				//INICIAMOS LA NUEVA INTERFAZ 'ConfiguracionView'
        	    		String title="CONFIGURACION";
    	        		personalizarIntent(title, ConfiguracionView.class);
	    			}
	    			
	        		break;
	    	}
	    	return false;
	    }
	    
	    
	    /**MUESTRA UN MENSAJE PERSONALIZADO DE ALERTA*/
	    public void showAlerta(int msg_title,int idMensaje){
	    	
	    	new AlertDialog.Builder(ListViewGrid.this).setIcon(R.drawable.icon_alerta)
			.setTitle(msg_title).setMessage(idMensaje).show();
	    };
	    public void showAlerta(int msg_title,String Mensaje){
	    	
	    	new AlertDialog.Builder(ListViewGrid.this).setIcon(R.drawable.icon_alerta)
			.setTitle(msg_title).setMessage(Mensaje).show();
	    };
	    
	    
	    /**EVITA QUE SE REINICEN LAS APLICACIONES CUANDO LA PANTALLA ROTE */
	    @Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
	    }
	    
	    
	    /**WIFI: CLASE QUE SINCRONIZA UN HILO CON EL CONTENEDOR DE LA VISTA */
	    class AddWifiSincTask extends AsyncTask<Void, Void,  Void> {
	    	private ProgressDialog dialog;
	    	private boolean conexionWifiExitosa=true;
	    	private int nroIntentos= 6;
	    	
			@Override
			protected Void doInBackground(Void... unused) {
				//necesitamos inicializar la conexion de wifi mediante un hilo de espera
				
				try {
					turnWifiState(true);
				} catch (Exception e) {
				}
				
				int i=0;
				while(!Sistema.testConnectionNetwork(ListViewGrid.this)){
					try {
						if(i== nroIntentos && !Sistema.testConnectionNetwork(ListViewGrid.this)) 
							conexionWifiExitosa=false;
						if(i== nroIntentos) break;
						
	                    Thread.sleep(15000);
	                    i++;
	                } catch (InterruptedException e) {
	                }
				}
				return (null);
			}
			@Override
			protected void onPreExecute() {
				
				dialog = ProgressDialog.show(ListViewGrid.this, "Activando Wifi ", msg_waiting,true, false);
				dialog.setIcon(R.drawable.icon_wifi);
				dialog.setCancelable(true);
			};
			@Override
			protected void onProgressUpdate(Void... unused) {
			}
			@Override
			protected void onPostExecute(Void unused) {
				//VERIFICAMOS QUE LA CONEXION A WIFI SEA EXITOSA
				if(conexionWifiExitosa){
					
					dialog.dismiss();
					//MENU PARA ESCOGER LA VENTANA DEL ITEM SINCRONIZACION DE LECTURAS
	    			if(DataBaseUtils.testConnectionDB(ListViewGrid.this)){
	    				
		    			//verificamos conexion a la bd
	    				if(mLectura.existeLecturaSinConfirmar()){
	    					
	    					//si es que hay lecturas sin confirmar
			    			if(Sistema.testConnectionNetwork(ListViewGrid.this)){
			    				
	    		    			//verificamos conexion a la red
	    		    				new AddSincronizacionTask().execute();
	    		    				
			    			}else showAlerta(R.string.title_error, R.string.error_conexion_network);
			    			
	    				}else showAlerta(R.string.title_warning, R.string.error_no_lecturas);
	    				
		    		}else showAlerta(R.string.title_error, R.string.error_conexion_bd);

				}else {
					dialog.dismiss();
					showAlerta(R.string.title_error, R.string.error_no_wifi);
					
				}
			}
		}
	    
	    
	    /**WIFI: CLASE QUE SINCRONIZA UN HILO CON EL CONTENEDOR DE LA VISTA */
	    class AddWifiActTask extends AsyncTask<Void, Void,  Void> {
	    	private ProgressDialog dialog;
	    	private boolean conexionWifiExitosa=true;
	    	private int nroIntentos= 6;
	    	
			@Override
			protected Void doInBackground(Void... unused) {
				//necesitamos inicializar la conexion de wifi mediante un hilo de espera
				try {
					turnWifiState(true);
				} catch (Exception e) {
				}int i=0;
				while(!Sistema.testConnectionNetwork(ListViewGrid.this)){
					try {
						if(i== nroIntentos && !Sistema.testConnectionNetwork(ListViewGrid.this)) 
							conexionWifiExitosa=false;
						if(i== nroIntentos) break;
	                    Thread.sleep(15000);
	                    i++;
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
				}
				return (null);
			}
			@Override
			protected void onPreExecute() {
				
				dialog = ProgressDialog.show(ListViewGrid.this,"Activando Wifi ",msg_waiting,true, false);
				dialog.setIcon(R.drawable.icon_wifi);
				dialog.setCancelable(true);
			};
			@Override
			protected void onProgressUpdate(Void... unused) {
			}
			@Override
			protected void onPostExecute(Void unused) {
				//VERIFICAMOS QUE LA CONEXION A WIFI SEA EXITOSA
				if(conexionWifiExitosa){
					
					dialog.dismiss();
					//MENU PARA ESCOGER LA VENTANA DEL ITEM SINCRONIZACION DE TABLAS ESTATICAS
	    			AlertDialog alert =	new AlertDialog.Builder(ListViewGrid.this).setMessage(R.string.label_actualizacion).create();
	    			alert.setTitle("Actualizacion");
	    			alert.setCancelable(false);
	    			alert.setIcon(R.drawable.icon_data_act);
	    			alert.setButton("SI",new DialogInterface.OnClickListener() {
	    			      public void onClick(DialogInterface dialog, int which) {
	    			    	  
	    		    			if(DataBaseUtils.testConnectionDB(ListViewGrid.this)){
	    		    				//verificamos conexion a la bd
	    		    				if(Sistema.testConnectionNetwork(ListViewGrid.this)){
		    		    				//verificamos conexion a la red
		    		    					new AddActualizacionTask().execute();
		    		    					
	    		    				}else showAlerta(R.string.title_error, R.string.error_conexion_network);
	    		    				
	    		    			}else showAlerta(R.string.title_error, R.string.error_conexion_bd);
	    			          return;
	    			          
	    			      }});
	    			alert.setButton2("NO", new DialogInterface.OnClickListener() {
	    			      public void onClick(DialogInterface dialog, int which) {	
	    			    	  turnWifiState(false);
	    			          return;
	    			      }});
	    			alert.show();
    			
				}else {
					dialog.dismiss();
					showAlerta(R.string.title_error, R.string.error_no_wifi);
					
				}
			}
		}
	    
	    
	    /**SINCRONIZACION: CLASE QUE SINCRONIZA UN HILO CON EL CONTENEDOR DE LA VISTA */
	    class AddSincronizacionTask extends AsyncTask<Void, Void,  Void> {
	    	private ProgressDialog dialog;
	    	private boolean resultError=false;
	    	private boolean conexion=true;
			@Override
			protected Void doInBackground(Void... unused) {
				
				if(Sistema.testConnectionServer()){
					//verificamos conexion al servidor
					resultError= SincEnvioController.SincronizarAction(ListViewGrid.this);
				}else{
					conexion=false;
    			}
				return (null);
			}
			@Override
			protected void onPreExecute() {
				dialog = ProgressDialog.show(ListViewGrid.this,"Sincronizando ", msg_waiting,true, false);
				dialog.setIcon(R.drawable.icon_data_sinc);
				dialog.setCancelable(true);
			};
			@Override
			protected void onProgressUpdate(Void... unused) {
			}
			@Override
			protected void onPostExecute(Void unused) {
				dialog.dismiss();
				if(conexion==true){
					if(resultError==false)
						Toast.makeText(ListViewGrid.this, R.string.succes_sincronizacion, Toast.LENGTH_SHORT).show();
					if(resultError==true){
						if(SincEnvioController.txtErrorSinc.equals(""))
							showAlerta(R.string.title_error, R.string.error_operacion);
						else
							showAlerta(R.string.title_error, SincEnvioController.txtErrorSinc);
					}
				}else showAlerta(R.string.title_error, R.string.error_conexion_server);
				turnWifiState(false);
			}
		}
	    
	    
	    /**SINCRONIZACION: CLASE QUE SINCRONIZA UN HILO CON EL CONTENEDOR DE LA VISTA */
	    class AddActualizacionTask extends AsyncTask<Void, Void,  Void> {
	    	private ProgressDialog dialog;
	    	private boolean resultError=true;
	    	private boolean conexion=true;
			@Override
			protected Void doInBackground(Void... unused) {
				//TEM SINCRONIZACION DE LECTURAS
				boolean error=true;
				if(Sistema.testConnectionServer()){
					//verificamos conexion al servidor
						if(mLectura.existeLecturaSinConfirmar()){
							error= SincEnvioController.SincronizarAction(ListViewGrid.this);
						}
						else{//si es que no hay lecturas que sincronizar de todos modos actualiza
							error=false;
						}
					//ITEM SINCRONIZACION DE TABLAS ESTATICAS
					if(error==false)
						resultError= SincRecepcionController.SincronizarAction(ListViewGrid.this);
				}else{
					conexion=false;
    			}
				
				return (null);
			}
			@Override
			protected void onPreExecute() {
				dialog = ProgressDialog.show(ListViewGrid.this,"Actualizando ", msg_waiting,true, false);
				dialog.setIcon(R.drawable.icon_data_act);
				dialog.setCancelable(true);
			};
			@Override
			protected void onProgressUpdate(Void... unused) {
			}
			@Override
			protected void onPostExecute(Void unused) {
				dialog.dismiss();
				if(conexion==true){
					if(resultError==false){
						restartApplication();
						
					}else if(resultError==true){
						try {
							if(SincRecepcionController.txtErrorSinc.equals("")) 
								showAlerta(R.string.title_error, R.string.error_operacion);
							else 
								showAlerta(R.string.title_error, SincRecepcionController.txtErrorSinc);
						} catch (Exception e) {
						}
					}
				}else showAlerta(R.string.title_error, R.string.error_conexion_server);
				
				turnWifiState(false);
			}
	    }
	    
	    
	    /**APAGA O ENCIENDE EL WIFI*/
	    public void turnWifiState( boolean turnOn){
	    	
	    	try {
	    		WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
		    	if(wifiManager.isWifiEnabled() && turnOn==false){
		    	    wifiManager.setWifiEnabled(false);
		    	}else if(!wifiManager.isWifiEnabled() && turnOn==true){
		    	    wifiManager.setWifiEnabled(true);
		    	}
			} catch (Exception e) {
			}
	    	
	    }
	    
	    
	    /**METODOS USADOS*/
	    
	    /*public static boolean existeLecturaSinConfirmar(Context context){
	    }*/
	    
	   /* public Lectura[] getLecturasZona(String CODI_ZONA){
	    }*/
	    
	   /* public Zona[] getZonas(String CODIGO_UNIDAD, String CODIGO_EMPRESA){
	    }*/
	    
	    /*public String getTipoZona(String CODI_TIZO){
	    }*/
	    
	    /*public Boolean eliminacionLogicaLectura(Lectura lectura){
	    };*/
	    
	    
	    /**PROCESO ANTERIOR DE SINCRONIACION*/
	  //MENU PARA ESCOGER LA VENTANA DEL ITEM SINCRONIZACION DE LECTURAS
		/*if(Sistema.testConnectionDB(this)){
			//verificamos conexion a la bd
			if(existeLecturaSinConfirmar(this)){
				//si es que hay lecturas sin confirmar
    			////////////////////if(Sistema.testConnectionNetwork(this)){
	    			//verificamos conexion a la red
	    				new AddSincronizacionTask().execute();
	    				
    			///////////////////}else showAlerta(R.string.title_error, R.string.error_conexion_network);
    			
			}else showAlerta(R.string.title_warning, R.string.error_no_lecturas);
			
		}else showAlerta(R.string.title_error, R.string.error_conexion_bd);
		*/
		//APAGAMOS EL WIFI AL FINALIZAR LA SINCRONIZACION
		//turnWifiState(false);
	    
	    
	    /**PROCESO ANTERIOR DE ACTUALIZACION*/
	  //MENU PARA ESCOGER LA VENTANA DEL ITEM SINCRONIZACION DE TABLAS ESTATICAS
		/*AlertDialog alert =	new AlertDialog.Builder(this).setMessage(R.string.label_actualizacion).create();
		alert.setTitle("Actualizacion");
		alert.setCancelable(false);
		alert.setIcon(R.drawable.data_act);
		alert.setButton("SI",new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int which) {
		    	  
		    	  	//ENCENDEMOS EL WIFI PARA INICIAR LA SINCRONIZACION
		    	  
	    			//turnWifiState(true);
	    			
	    			if(Sistema.testConnectionDB(ListViewGrid.this)){
	    				//verificamos conexion a la bd
	    				if(Sistema.testConnectionNetwork(ListViewGrid.this)){
		    				//verificamos conexion a la red
		    					new AddActualizacionTask().execute();
		    					
	    				}else showAlerta(R.string.title_error, R.string.error_conexion_network);
	    				
	    			}else showAlerta(R.string.title_error, R.string.error_conexion_bd);
	    			
	    			//APAGAMOS EL WIFI AL FINALIZAR LA SINCRONIZACION
	    			///turnWifiState(false);
		          return;
		          
		      }});
		alert.setButton2("NO", new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int which) {	    	  
		          return;
		      }});
		alert.show();*/
	    
	  
		
	    
	}