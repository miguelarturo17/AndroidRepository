package sbs.milpo.view;

import java.util.Date;

import com.BasicKeyboard.KeyPad;
import com.BasicKeyboard.R;
import com.BasicKeyboard.SingleKeyPad;
import sbs.milpo.controller.Fecha;
import sbs.milpo.controller.Sistema;
import sbs.milpo.model.dal.DetalleLectura;
import sbs.milpo.model.dal.ElementoUnidad;
import sbs.milpo.model.dal.GeoCuerpo;
import sbs.milpo.model.dal.Geolitologia;
import sbs.milpo.model.dal.Lectura;
import sbs.milpo.model.dal.TipoReconocimiento;
import sbs.milpo.model.dal.bl.MElementoUnidad;
import sbs.milpo.model.dal.bl.MGeocuerpo;
import sbs.milpo.model.dal.bl.MGeolitologia;
import sbs.milpo.model.dal.bl.MLectura;
import sbs.milpo.model.dal.bl.MReconocimiento;
import greendroid.app.GDActivity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class PerforacionRegistro extends GDActivity {
    /** Called when the activity is first created. */
	
	private float elementoCu=0, elementoAg=0, elementoPb=0, elementoZn=0, elementoFe=0, elementoDesde=0, elementoHasta=0;
	private String COD_UNIDAD = "", COD_EMPRESA = "", COD_ZONA = "";
	private Spinner spinnerLito, spinnerGeoCuerpo;
	private EditText textCu, textAg, textPb, textZn, textFe, textDesde, textHasta, textTaladro, textReconocimiento;
	private ImageView ivCu, ivAg, ivPb, ivZn, ivFe, ivDesde, ivHasta, ivTaladro, ivRec;
	private Geolitologia[] geoLito;
	private GeoCuerpo[] geoCuerpo;
	private boolean tipoRecMalla=false;
	
	//PARA MANEJAR LA FECHA Y HORA
	private EditText textFecha, textHora;
	private int mYear, mMonth, mDay, mHour, mMinute;
	static final int TIME_DIALOG_ID = 1,  DATE_DIALOG_ID = 0;
	String fechaCreacion="", fechaModificacion="";
	
	//INICIALIZAMOS NRO PARA EL DIALOGO DEL TECLADO
	private static final int KEYPAD_DIALOG_DOUBLE= 2, KEYPAD_DIALOG_SINGLE= 3;
	private EditText currentText = null;
	private Dialog dialog;
	
	MElementoUnidad meUnidad;
	MLectura mLectura;
	MGeolitologia mGeolito;
	MGeocuerpo mGeocuerpo;
	MReconocimiento mRec;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarContentView(R.layout.registro_perforacion);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setAtributesIntent();
        
        meUnidad= new MElementoUnidad(this);
        mLectura= new MLectura(this);
        mGeolito= new MGeolitologia(this);
        mGeocuerpo= new MGeocuerpo(this);
        mRec= new MReconocimiento(this);
        
        cargarRegistroPerforacionView();
        fechaCreacion= Fecha.getFechaActual(this);
        //ElementoUnidad[] eUnidad= getElementoUnidad(this, COD_EMPRESA, COD_UNIDAD);
        Button registrar= (Button)findViewById( R.id.buttonAceptar);
        registrar.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
        		boolean checkErrorDatos= false;
                checkErrorDatos= validarDatosPerforacion();
                if(checkErrorDatos){
                	Toast.makeText(PerforacionRegistro.this, R.string.error_registro, Toast.LENGTH_SHORT).show();
                	
                }else{
                	//ElementoUnidad[] eUnidad= getElementoUnidad(PerforacionRegistro.this, COD_EMPRESA, COD_UNIDAD);
                	Lectura lect= getLecturaPerforacion();
                	
                	if(mLectura.saveLecturaPerforacion(lect)){
	                	new AlertDialog.Builder(PerforacionRegistro.this).setTitle(R.string.title_success).
	                	setMessage(R.string.succes_register).show();
	                	retroceder();
                	} else Toast.makeText(PerforacionRegistro.this,R.string.error_operacion, Toast.LENGTH_SHORT).show();
                
                }
        	}
        });
        
    }
  
    /**CARGAMOS LOS ATRIBUTOS DEL INTENT, CUANDO INICIA LA INTERFAZ*/
    private void setAtributesIntent(){
    	
    	try {//obtenemos los parametros enviados por la clase anterior.
        	Bundle extras = getIntent().getExtras();
        	COD_EMPRESA = extras.getString("CODIGO_EMPRESA");
        	COD_UNIDAD = extras.getString("CODIGO_UNIDAD");
        	COD_ZONA= extras.getString("CODIGO_ZONA");
		} catch (Exception e) {
		}
    }
    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK){
			
			if(validarDatosVaciosSalida()){
				retroceder();
			}else{
				AlertDialog alert =	new AlertDialog.Builder(this).setMessage(R.string.label_no_reg).create();
				alert.setTitle(R.string.title_warning);
				alert.setIcon(R.drawable.icon_info);
				alert.setCancelable(false);
				alert.setButton("SI",new DialogInterface.OnClickListener() {
				      public void onClick(DialogInterface dialog, int which) {
				    	  retroceder();
				          return;
				      }});
				alert.setButton2("NO", new DialogInterface.OnClickListener() {
				      public void onClick(DialogInterface dialog, int which) {
				          return;
				      }});
				alert.show();
			}
		}
		return false;
	}
    
    /**ELIMINA LA VENTANA ACTIVA Y REGRESA A  LA ANTERIOR*/
    public void retroceder()
    {
	    finish();
	    Intent intent = new Intent(this, ListViewGrid.class);
	    intent.putExtra(greendroid.app.ActionBarActivity.GD_ACTION_BAR_TITLE, Sistema.TITULO_GRILLA);
		intent.putExtra("CODIGO_UNIDAD", COD_UNIDAD);
		intent.putExtra("CODIGO_EMPRESA", COD_EMPRESA);
		startActivity(intent);

    }
    
    /**MANTENEMOS OCULTOS LOS ICONOS DE ERROR PARA LUEGO SER UTILIZADOS EN LA VERIFICACION*/
    public void ocultarCheckRegistro(){
    	
    	ivCu.setVisibility(View.INVISIBLE);
    	ivAg.setVisibility(View.INVISIBLE);
    	ivPb.setVisibility(View.INVISIBLE);
    	ivZn.setVisibility(View.INVISIBLE);
    	ivFe.setVisibility(View.INVISIBLE);
    	ivDesde.setVisibility(View.INVISIBLE);
    	ivHasta.setVisibility(View.INVISIBLE);
    	ivTaladro.setVisibility(View.INVISIBLE);
    	ivRec.setVisibility(View.INVISIBLE);
    	
    }
    
    /**PREPARAMOS LOS VALORES INICIALES DE LA INTERFAZ*/
    public void cargarRegistroPerforacionView(){

    	TextView tv= (TextView)findViewById(R.id.registrar_perforacion);
    	tv.setText(R.string.label_registro);
    	
        RadioButton rb1= (RadioButton)findViewById(R.id.radio_1);
        if(rb1.isChecked()){
        	tipoRecMalla= false;
    	}
        rb1.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
        	tipoRecMalla= false;
        	textReconocimiento.setText("");
        	}
        });
        
        RadioButton rb2= (RadioButton)findViewById(R.id.radio_2);
        if(rb2.isChecked()){
        	tipoRecMalla=true;
    	}
        rb2.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
        	tipoRecMalla=true;
        	textReconocimiento.setText("");
        	}
        });
        
        //cargamos el spinner de geolitologia
        geoLito= mGeolito.getGeolitologia(COD_UNIDAD, COD_EMPRESA);
        if(geoLito!= null)
        	cargarSpinnerLito(geoLito);
        
        //cargamos el spinner de geolitologia
        geoCuerpo= mGeocuerpo.getGeoCuerpo(COD_UNIDAD);
        if(geoCuerpo!= null)
        	cargarSpinnerGeoCuerpo(geoCuerpo);
        
        textReconocimiento = (EditText)findViewById(R.id.edit_P_Reconocimiento);
        textReconocimiento.addTextChangedListener(filterTextWatcher);
        textReconocimiento.setOnClickListener(new View.OnClickListener() {
        	@Override
			public void onClick(View v) {
        		if(tipoRecMalla){
	        		personalizarSingleKeyboard(textReconocimiento, R.string.title_spinner_malla,"M",100 );
        		}else{
	        		personalizarSingleKeyboard(textReconocimiento, R.string.title_spinner_parada, "P", 11);
        		}
        	}
        });
        
        //cargamos el spinner de taladro
        textTaladro = (EditText)findViewById(R.id.edit_P_Taladro);
        textTaladro.addTextChangedListener(filterTextWatcher);
        EditTextListener listenerTaladro= new EditTextListener();
        listenerTaladro.setEditTextListener(this, textTaladro, R.string.title_spinner_taladro,"",100);
        
        //inizializamos los editText
        textCu = (EditText)findViewById(R.id.edit_P_ElemCu);
        textCu.addTextChangedListener(filterTextWatcher);
        EditTextListener listenerCu= new EditTextListener();
        listenerCu.setEditTextListener(this, textCu, R.string.label_registro_cu);

        textAg = (EditText)findViewById(R.id.edit_P_ElemAg);
        textAg.addTextChangedListener(filterTextWatcher);
        EditTextListener listenerAg= new EditTextListener();
        listenerAg.setEditTextListener(this, textAg, R.string.label_registro_ag);
        
        textPb = (EditText)findViewById(R.id.edit_P_ElemPb);
        textPb.addTextChangedListener(filterTextWatcher);
        EditTextListener listenerPb= new EditTextListener();
        listenerPb.setEditTextListener(this, textPb, R.string.label_registro_pb);

        textZn = (EditText)findViewById(R.id.edit_P_ElemZn);
        textZn.addTextChangedListener(filterTextWatcher);
        EditTextListener lZn= new EditTextListener();
        lZn.setEditTextListener(this, textZn, R.string.label_registro_zn);

        textFe = (EditText)findViewById(R.id.edit_P_ElemFe);
        textFe.addTextChangedListener(filterTextWatcher);
        EditTextListener lFe= new EditTextListener();
        lFe.setEditTextListener(this, textFe, R.string.label_registro_fe);

        textDesde = (EditText)findViewById(R.id.edit_P_ElemDesde);
        textDesde.addTextChangedListener(filterTextWatcher);
        EditTextListener listenerDesde= new EditTextListener();
        listenerDesde.setEditTextListener(this, textDesde, R.string.label_registro_desde);

        textHasta = (EditText)findViewById(R.id.edit_P_ElemHasta);
        textHasta.addTextChangedListener(filterTextWatcher);
        EditTextListener listenerHasta= new EditTextListener();
        listenerHasta.setEditTextListener(this, textHasta, R.string.label_registro_hasta);

        
        //manejamos la fecha
        Fecha.cargarFechaActual(this);
    	String fechaFinal=Fecha.getFecha_dia()+"/"+Fecha.getFecha_mes()+"/"+Fecha.getFecha_año();
    	setFechaActualByDate();
		
        textFecha= (EditText)findViewById(R.id.edit_P_Fecha);
    	textFecha.setText(fechaFinal);
    	textFecha.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
        		setFechaActualByDate();
        		showDialog(DATE_DIALOG_ID);
        	}
        });
    	
    	
    	///manejamos la hora
    	Fecha.cargarFechaActual(this);
    	String horaFinal=Fecha.getFecha_hora()+":"+Fecha.getFecha_minuto()+":00";
    	setFechaActualByTime();
		
    	textHora= (EditText)findViewById(R.id.edit_P_Hora);
    	textHora.setText(horaFinal);
    	textHora.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
        		setFechaActualByTime();
        		showDialog(TIME_DIALOG_ID);
        	}
        });
    	
        //inicializamos las imagenes del checker
        ivCu= (ImageView)findViewById(R.id.Img_P_ElemCu);
    	ivAg= (ImageView)findViewById(R.id.Img_P_ElemAg);
    	ivPb= (ImageView)findViewById(R.id.Img_P_ElemPb);
    	ivZn= (ImageView)findViewById(R.id.Img_P_ElemZn);
    	ivFe= (ImageView)findViewById(R.id.Img_P_ElemFe);
    	ivDesde=(ImageView)findViewById(R.id.Img_P_ElemDesde);
    	ivHasta=(ImageView)findViewById(R.id.Img_P_ElemHasta);
    	ivTaladro=(ImageView)findViewById(R.id.Img_P_Taladro);
    	ivRec=(ImageView)findViewById(R.id.Img_P_Reconocimiento);
	
    }

    
    /**ACTION LISTENER DE LOS EDITTEXT*/
    private TextWatcher filterTextWatcher = new TextWatcher() {
	    @Override
	    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	    }
	    @Override
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
	    	ocultarCheckRegistro();
	    }
	    @Override
	    public void afterTextChanged(Editable s) {
	    }
	};
	
	
	/**PERMITE ACTUALIZAR LA FECHA ACTUAL EN LAS VARIABLES DEL SISTEMA*/
	private void setFechaActualByTime(){
		
		Fecha.cargarFechaActual(PerforacionRegistro.this);
        mHour = Integer.parseInt(Fecha.getFecha_hora());
		mMinute = Integer.parseInt(Fecha.getFecha_minuto());
	}
	
	
	/**PERMITE ACTUALIZAR LA FECHA ACTUAL EN LAS VARIABLES DEL SISTEMA*/
	private void setFechaActualByDate(){
		
		Fecha.cargarFechaActual(PerforacionRegistro.this);
        mYear = Integer.parseInt(Fecha.getFecha_año());
		mMonth = Integer.parseInt(Fecha.getFecha_mes())-1;
		mDay = Integer.parseInt(Fecha.getFecha_dia());
	}
	
	
	/**PERSONALIZAMOS LA MANERA EN QUE SE MUESTRA EL TECLADO SIMPLE*/
	public void personalizarSingleKeyboard(EditText sText, int sLabelTitle, String prefijo, int valorMaximo){
		
		//seteamos los valores antes de cargar el teclado
		EditTextListener.PREFIJO= prefijo;
		EditTextListener.MAX_VALOR= valorMaximo;
		EditTextListener.TITLE_TECLADO= sLabelTitle;
		
		currentText = sText;
        showDialog(KEYPAD_DIALOG_SINGLE);
		dialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.keyboard);
	};
	
	
	/**PERSONALIZAMOS LA MANERA EN QUE SE MUESTRA EL TECLADO DOBLE*/
	public void personalizarDoubleKeyboard(EditText sText, int sLabelTitle){
		
		//seteamos los valores antes de cargar el teclado
		ElementoUnidad[] eUnidad= meUnidad.getElementoUnidad(COD_EMPRESA, COD_UNIDAD);
		EditTextListener.setSizeDigitosTeclado(eUnidad, sLabelTitle);
		EditTextListener.TITLE_TECLADO= sLabelTitle;
		
		currentText = sText;
        showDialog(KEYPAD_DIALOG_DOUBLE);
		dialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.keyboard);
	};
	
	
	/**CREA LOS DIALOGOS PARA FECHA, HORA Y TECLADO*/
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
			case DATE_DIALOG_ID:
				return new DatePickerDialog(this, mDateSetListener,mYear, mMonth, mDay);
				
			case TIME_DIALOG_ID:
				return new TimePickerDialog(this, mTimeSetListener, mHour, mMinute, false);
				
			case KEYPAD_DIALOG_DOUBLE:
	        	dialog = new KeyPad(this, KEYPAD_DIALOG_DOUBLE,  currentText, EditTextListener.SIZE_ENTERO, EditTextListener.SIZE_DECIMAL);
	            dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
	            dialog.setTitle(EditTextListener.TITLE_TECLADO);
	            return dialog;
	            
			case KEYPAD_DIALOG_SINGLE:
				dialog = new SingleKeyPad(this, KEYPAD_DIALOG_SINGLE,  currentText, EditTextListener.MAX_VALOR, EditTextListener.PREFIJO);
	            dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
	            dialog.setTitle(EditTextListener.TITLE_TECLADO);
	            return dialog;
	            
		}
		return null;
	}
	
	
	/** ACCION QUE ACTUALIZA LA FECHA Y HORA QUE ESCOGEMOS EN EL PICKER PARA EL TEXTVIEW*/
	private void updateDisplay(int kindDialog) {
		// Month is 0 based so add 1
		fechaModificacion= Fecha.getFechaActual(this);
		if(kindDialog== DATE_DIALOG_ID){
			textFecha.setText( new StringBuilder().append(pad(mDay)).append("/")
				.append(pad(mMonth + 1)).append("/").append(pad(mYear)).append(""));
		}
		if(kindDialog== TIME_DIALOG_ID){
			textHora.setText( new StringBuilder()
					.append(pad(mHour)).append(":").append(pad(mMinute)).append(":00"));
		}
	}
	
	
	/**ESTABLECE LA NUEVA FECHA ESCOGIDA EN EL DIALOGO*/
	private DatePickerDialog.OnDateSetListener mDateSetListener =
		new DatePickerDialog.OnDateSetListener() {
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				try {//validamos que la fecha no sea mayor que la actual
					Date dateAfter= new Date(year, monthOfYear, dayOfMonth, mHour, mMinute);
					boolean esMenor= Fecha.isFechaMenor(PerforacionRegistro.this, dateAfter);
					if(esMenor){
						mYear = year;
						mMonth = monthOfYear;
						mDay = dayOfMonth;
						updateDisplay(DATE_DIALOG_ID);
					}
				} catch (Exception e) {
				}
				
			}
		};
		
		
	/**ESTABLECE LA NUEVA HORA ESCOGIDA  EN EL DIALOGO*/
	private TimePickerDialog.OnTimeSetListener mTimeSetListener =
			new TimePickerDialog.OnTimeSetListener() {
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					try {//validamos que la fecha no sea mayor que la actual
						Date dateAfter= new Date(mYear, mMonth, mDay, hourOfDay, minute);
						boolean esMenor= Fecha.isFechaMenor(PerforacionRegistro.this, dateAfter);
						if(esMenor){
							mHour = hourOfDay;
							mMinute = minute;
							updateDisplay(TIME_DIALOG_ID);
						}
					} catch (Exception e) {
					}
				}
			};
			
	private static String pad(int c) {
		return String.valueOf(c);
	}
	
    
	/**CARGAMOS LOS VALORES DE LA INTERFAZ A LA CLASE LECTURA*/
    public Lectura getLecturaPerforacion(){
    	
    	Lectura lect= new Lectura();
    	ElementoUnidad[] eUnidad= meUnidad.getElementoUnidad(COD_EMPRESA, COD_UNIDAD);
    	try {
    		lect.detalleLecturaList= new DetalleLectura[eUnidad.length];
		} catch (Exception e) {
			lect.detalleLecturaList= new DetalleLectura[100];
		}
    	for (int i=0; i<eUnidad.length;i++){
	    	try {
	    		if(eUnidad[i].getCodigo_elementoQuimico().equals(Sistema.ELEMENTO_CU)){
			    	lect.detalleLecturaList[0]= new DetalleLectura();
			    	lect.detalleLecturaList[0].setCodigo_elemento(eUnidad[i].getCodigo_elementoQuimico());
			    	lect.detalleLecturaList[0].setCodigo_unidadMedida(eUnidad[i].getCodigo_unidadMedida());
			    	lect.detalleLecturaList[0].setValor(textCu.getText().toString());
	    		}
			} catch (Exception e) {}
			try {
				if(eUnidad[i].getCodigo_elementoQuimico().equals(Sistema.ELEMENTO_AG)){
					lect.detalleLecturaList[1]= new DetalleLectura();
					lect.detalleLecturaList[1].setCodigo_elemento(eUnidad[i].getCodigo_elementoQuimico());
			    	lect.detalleLecturaList[1].setCodigo_unidadMedida(eUnidad[i].getCodigo_unidadMedida());
			    	lect.detalleLecturaList[1].setValor(textAg.getText().toString());
				}
			} catch (Exception e) {}
	    	try {
	    		if(eUnidad[i].getCodigo_elementoQuimico().equals(Sistema.ELEMENTO_PB)){
				    lect.detalleLecturaList[2]= new DetalleLectura();
				    lect.detalleLecturaList[2].setCodigo_elemento(eUnidad[i].getCodigo_elementoQuimico());
			    	lect.detalleLecturaList[2].setCodigo_unidadMedida(eUnidad[i].getCodigo_unidadMedida());
			    	lect.detalleLecturaList[2].setValor(textPb.getText().toString());	
	    		}
	    	} catch (Exception e) {}
	    	try {
	    		if(eUnidad[i].getCodigo_elementoQuimico().equals(Sistema.ELEMENTO_ZN)){
	    		lect.detalleLecturaList[3]= new DetalleLectura();
	    		lect.detalleLecturaList[3].setCodigo_elemento(eUnidad[i].getCodigo_elementoQuimico());
		    	lect.detalleLecturaList[3].setCodigo_unidadMedida(eUnidad[i].getCodigo_unidadMedida());
		    	lect.detalleLecturaList[3].setValor(textZn.getText().toString());
	    		}
	    	} catch (Exception e) {}
	    	try {
	    		if(eUnidad[i].getCodigo_elementoQuimico().equals(Sistema.ELEMENTO_FE)){
				lect.detalleLecturaList[4]= new DetalleLectura();
				lect.detalleLecturaList[4].setCodigo_elemento(eUnidad[i].getCodigo_elementoQuimico());
		    	lect.detalleLecturaList[4].setCodigo_unidadMedida(eUnidad[i].getCodigo_unidadMedida());
		    	lect.detalleLecturaList[4].setValor(textFe.getText().toString());
		    	}
	    	} catch (Exception e) {}
    	}
    	
    	try {
    		lect.setNroTaladro(textTaladro.getText().toString());
    			//lect.setNroTaladro(spinnerTaladro.getSelectedItem().toString());
    	} catch (Exception e) {}
    	try {
    		lect.setDesde(textDesde.getText().toString());
    	} catch (Exception e) {}
    	try {
			lect.setHasta(textHasta.getText().toString());
    	} catch (Exception e) {}
    	try {
    		RadioButton rb1= (RadioButton)findViewById(R.id.radio_1);
    		RadioButton rb2= (RadioButton)findViewById(R.id.radio_2);
    		TipoReconocimiento[] tr= mRec.getTipoReconocimiento();
    		
            if(rb1.isChecked())//TipoReconocimiento("R");
            	lect.setTipoReconocimiento(tr[1].getCodigo_TipoReconocimiento());
            if(rb2.isChecked())//TipoReconocimiento("M");
            	lect.setTipoReconocimiento(tr[0].getCodigo_TipoReconocimiento());
		} catch (Exception e) {}
		try {
			lect.setValor(textReconocimiento.getText().toString());
			//lect.setValor(spinnerReconocimiento.getSelectedItem().toString());
		} catch (Exception e) {}
		try {
			lect.setLito(geoLito[spinnerLito.getSelectedItemPosition()].getCodigo_GeoLitologia() );
		} catch (Exception e) {}
		try {
			lect.setCodigo_cuerpo(geoCuerpo[spinnerGeoCuerpo.getSelectedItemPosition()].getCodigo_cuerpo() );
		} catch (Exception e) {}
		try {
			lect.setFechaCreacion(fechaCreacion);
			if(fechaModificacion.equals("")){
				lect.setFechaModificacion(fechaCreacion);
				lect.setFecha(fechaCreacion);
			}else{
				lect.setFechaModificacion(fechaModificacion);
				lect.setFecha(textFecha.getText().toString() +" "+ textHora.getText().toString());
			}
		} catch (Exception e) {
		}

		lect.setCOD_EMPRESA(COD_EMPRESA);
		lect.setCOD_UNIDAD(COD_UNIDAD);
		lect.setCOD_ZONA(COD_ZONA);
		
    	return lect;
    }
    
    /**SE EJECUTA CUANDO SE DESEA SALIR PARA VERIFICAR SI HAY DATA EN LA PANTALLA*/
    private boolean validarDatosVaciosSalida(){
    	boolean checkVacio=true;
    	
    	if(!textReconocimiento.getText().toString().equals(""))checkVacio=false;
    	if(!textTaladro.getText().toString().equals(""))checkVacio=false;
    	if(!textCu.getText().toString().equals(""))checkVacio=false;
    	if(!textAg.getText().toString().equals(""))checkVacio=false;
    	if(!textPb.getText().toString().equals(""))checkVacio=false;
    	if(!textZn.getText().toString().equals(""))checkVacio=false;
    	if(!textFe.getText().toString().equals(""))checkVacio=false;
    	if(!textDesde.getText().toString().equals(""))checkVacio=false;
    	if(!textHasta.getText().toString().equals(""))checkVacio=false;
    	return checkVacio;
    }
    
    /**METODO QUE PERMITE VALIDAR LOS CAMPOS DE LA INTERFAZ*/
    public boolean validarDatosPerforacion(){
    	
    	boolean checker=false;
    	float val_elemento=0;
    	float val_minimo=0;
    	float val_maximo=0;
    	
    	
    	if(textReconocimiento.getText().toString().equals("")){
    		ivRec.setVisibility(View.VISIBLE);
    		checker=true;
    	}
    	if(textTaladro.getText().toString().equals("")){
    		ivTaladro.setVisibility(View.VISIBLE);
    		checker=true;
    	}
    	
    	
    	//cargamos que los datos del editText.
    	try {
    		elementoCu=Float.parseFloat(textCu.getText().toString());
		} catch (NumberFormatException e) {
			ivCu.setVisibility(View.VISIBLE);
			checker=true;
		}
		try {
    		elementoAg= Float.parseFloat(textAg.getText().toString());
		} catch (NumberFormatException e) {
			ivAg.setVisibility(View.VISIBLE);
			checker=true;
		}
    	try {
	    	elementoPb= Float.parseFloat(textPb.getText().toString());	
    	} catch (NumberFormatException e) {
			ivPb.setVisibility(View.VISIBLE);
			checker=true;
    	}
    	try {
        	elementoZn= Float.parseFloat(textZn.getText().toString());
    	} catch (NumberFormatException e) {
			ivZn.setVisibility(View.VISIBLE);
			checker=true;
    	}
    	try {
	    	elementoFe= Float.parseFloat(textFe.getText().toString());
		} catch (NumberFormatException e) {
			ivFe.setVisibility(View.VISIBLE);
			checker=true;
		}
    	try {
        	elementoDesde= Float.parseFloat(textDesde.getText().toString());
    	} catch (NumberFormatException e) {
			ivDesde.setVisibility(View.VISIBLE);
			checker=true;
    	}
    	try {
    		elementoHasta= Float.parseFloat(textHasta.getText().toString());
		} catch (NumberFormatException e) {
			ivHasta.setVisibility(View.VISIBLE);
			checker=true;
		}
	
    	try {
    		ElementoUnidad[] oElementoUnidad= meUnidad.getElementoUnidad(COD_EMPRESA, COD_UNIDAD);
        	//Tiene que ser dinámico
    		for (int i=0; i<oElementoUnidad.length;i++){
    			ImageView iv= new ImageView(this);
    			val_minimo= Float.parseFloat(oElementoUnidad[i].getValorMinimo());
    			val_maximo= Float.parseFloat(oElementoUnidad[i].getValorMaximo());
    			
    			if(oElementoUnidad[i].getCodigo_elementoQuimico().equals(Sistema.ELEMENTO_CU)){
    				iv=(ImageView)findViewById(R.id.Img_P_ElemCu);
    				val_elemento= elementoCu;
    			}
    			
    			if(oElementoUnidad[i].getCodigo_elementoQuimico().equals(Sistema.ELEMENTO_AG)){
    				iv=(ImageView)findViewById(R.id.Img_P_ElemAg);
    				val_elemento=elementoAg;
	    		}
    			if(oElementoUnidad[i].getCodigo_elementoQuimico().equals(Sistema.ELEMENTO_PB)){
    				 iv=(ImageView)findViewById(R.id.Img_P_ElemPb);
    				val_elemento=elementoPb;
    			}
    			if(oElementoUnidad[i].getCodigo_elementoQuimico().equals(Sistema.ELEMENTO_ZN)){
    				iv=(ImageView)findViewById(R.id.Img_P_ElemZn);
    				val_elemento=elementoZn;
    			}
    			if(oElementoUnidad[i].getCodigo_elementoQuimico().equals(Sistema.ELEMENTO_FE)){
    				iv=(ImageView)findViewById(R.id.Img_P_ElemFe);
    				val_elemento=elementoFe;
    			}
    			
    			try {
    				if(!(val_minimo<=val_elemento && val_elemento<=val_maximo)){
        				iv.setVisibility(View.VISIBLE);
        				checker=true;
        			}
				} catch (Exception e) {
				}
    			//FIN DEL BUCLE
    		}
			if(elementoHasta<=elementoDesde || (elementoHasta==0 && elementoDesde==0) ){
				ivDesde.setVisibility(View.VISIBLE);
				ivHasta.setVisibility(View.VISIBLE);
				checker=true;
			}
			
    	} catch (Exception e) {
    		new AlertDialog.Builder(this).setMessage(e.toString()).show();
		}
    	
    	return checker;
    	
    }
    
    /**carga un spinner de los posibles valores del Taladro*/
    public void cargarSpinnerValorTaladro(String[] mStrings){
    	/*
    	spinnerTaladro = (Spinner)findViewById(R.id.spinnerTaladro);
    	ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
    			android.R.layout.simple_spinner_item, mStrings);    
    	
    	spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
    	spinnerTaladro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    	    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    	    }
    	    public void onNothingSelected(AdapterView<?> parent) {
    	    }
    	});
    	spinnerTaladro.setAdapter(spinnerAdapter);*/
    };
    
    
    /**carga un spinner de los posibles valores del Taladro*/
    public void cargarSpinnerReconocimiento(String[] mStrings, String strPrompt){
    	
    	/*spinnerReconocimiento = (Spinner)findViewById(R.id.spinnerReconocimiento);
    	spinnerReconocimiento.setPrompt(strPrompt);
    	ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
    			android.R.layout.simple_spinner_item,mStrings);  
    	
    	spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
    	spinnerReconocimiento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    	    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    	    }
    	    public void onNothingSelected(AdapterView<?> parent) {
    	    }
    	});
    	spinnerReconocimiento.setAdapter(spinnerAdapter);*/
    };
    
    
    /**carga un spinner de GEOLITOLOGIA*/
    public void cargarSpinnerLito(Geolitologia[] lito){
    	
    	spinnerLito = (Spinner)findViewById(R.id.spinnerLito);
    	String[] stringsLito= new String[lito.length];
        for(int l=0; l<lito.length;l++){
        		try { stringsLito[l]= new String(lito[l].getLitologiaDesc().toString());
    			} catch (Exception e) {
    			}
    	}
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
        			android.R.layout.simple_spinner_item,stringsLito);    
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
    	spinnerLito.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    	    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    	    }
    	    public void onNothingSelected(AdapterView<?> parent) {
    	    }
    	});

    	spinnerLito.setAdapter(spinnerAdapter);
    	spinnerLito.setFocusable(true);
    	//spinner.setSelection(Sistema.SPINNER_SELECTED);
    };
    
    
    /**carga un spinner de GEOCUERPO*/
    public void cargarSpinnerGeoCuerpo(GeoCuerpo[] cuerpo){
    	
    	spinnerGeoCuerpo = (Spinner)findViewById(R.id.spinnerGeoCuerpo);
    	String[] stringsCuerpo= new String[cuerpo.length];
        for(int l=0; l<cuerpo.length;l++){
        		try { 
        			stringsCuerpo[l]= new String(cuerpo[l].getNro_cuerpo().toString());
    			} catch (Exception e) {
    			}
    	}
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
        			android.R.layout.simple_spinner_item, stringsCuerpo);    
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
        spinnerGeoCuerpo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    	    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    	    }
    	    public void onNothingSelected(AdapterView<?> parent) {
    	    }
    	});
        spinnerGeoCuerpo.setAdapter(spinnerAdapter);
    	//spinner.setSelection(Sistema.SPINNER_SELECTED);
    };
    
    
    /**METODOS USADOS*/
    
    /*public void registrarLecturaPerforacion(Lectura lectura){
    }*/
    
    /*public ElementoUnidad[] getElementoUnidad(Context context, String CODI_EMPRE, String CODI_UNMI ){
    }*/
    
    /*public Geolitologia[] getGeolitologia(Context context, String CODIGO_UNIDAD, String CODIGO_EMPRESA){
    }*/
    
    /*public GeoCuerpo[] getGeoCuerpo(Context context, String CODIGO_UNIDAD){
    }*/
    
    /*public TipoReconocimiento[] getTipoReconocimiento(Context context){
    }*/

/*
    static String[] paradaStrings = {"P1", "P2", "P3", "P4", "P5", "P6", "P7", "P8", "P9", "P10"};
    static String[] taladroStrings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", 
    		"91", "92", "93", "94", "95", "96", "97", "98", "99"};*/
 
    
}