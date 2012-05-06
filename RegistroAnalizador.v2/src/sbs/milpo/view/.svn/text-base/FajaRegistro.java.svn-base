package sbs.milpo.view;

import java.util.Date;

import com.BasicKeyboard.KeyPad;
import com.BasicKeyboard.R;
import sbs.milpo.controller.Fecha;
import sbs.milpo.controller.Sistema;
import sbs.milpo.model.dal.DetalleLectura;
import sbs.milpo.model.dal.ElementoUnidad;
import sbs.milpo.model.dal.Lectura;
import sbs.milpo.model.dal.bl.MElementoUnidad;
import sbs.milpo.model.dal.bl.MLectura;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class FajaRegistro extends GDActivity {
    /** Called when the activity is first created. */
	
	@SuppressWarnings("unused")
	private float elementoCu, elementoAg, elementoPb, elementoZn, elementoFe, elementoPeso;
	String COD_UNIDAD ="", COD_EMPRESA ="", COD_ZONA ="";
	private EditText textCu, textAg, textPb, textZn, textFe, textPeso;
	private ImageView ivCu, ivAg, ivPb, ivZn, ivFe, ivPeso;
	
	//PARA MANEJAR LA FECHA Y HORA
	private TextView textFecha, textHora;
	private int mYear, mMonth, mDay, mHour, mMinute;
	static final int TIME_DIALOG_ID = 1,  DATE_DIALOG_ID = 0;
	String fechaCreacion="", fechaModificacion="";
	
	//INICIALIZAMOS NRO PARA EL DIALOGO DEL TECLADO
	public static final int KEYPAD_DIALOG = 2;
	EditText currentText = null;
	//int TITLE_TECLADO=0;
	Dialog dialog;
	
	MElementoUnidad meUnidad;
	MLectura mLectura;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarContentView(R.layout.registro_faja);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setAtributesIntent();
        
        meUnidad= new MElementoUnidad(this);
        mLectura= new MLectura(this);
        
        inicializarElementos();
        cargarRegistroFajaView();
        fechaCreacion= Fecha.getFechaActual(this);
        Button registrar= (Button)findViewById( R.id.buttonAceptar);
        registrar.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
        		boolean checkErrorDatos=false;

                	checkErrorDatos= validarDatosFaja();
                	if(checkErrorDatos){
                		Toast.makeText(FajaRegistro.this, R.string.error_registro, Toast.LENGTH_SHORT).show();
                		
                	}else{
                		Lectura lect= getLecturaFaja();
                		
                		if(mLectura.saveLecturaFaja(lect)){
	                		new AlertDialog.Builder(FajaRegistro.this).setTitle(R.string.title_success).
	                    	setMessage(R.string.succes_register).show();
	                		retroceder();
                		}else Toast.makeText(FajaRegistro.this,R.string.error_operacion, Toast.LENGTH_SHORT).show();
                		
                	}
        	}
        });
        
    }
    
    /**CARGAMOS LOS ATRIBUTOS DEL INTENT, CUANDO INICIA LA INTERFAZ*/
    private void setAtributesIntent(){
    	
    	try {
    		//obtenemos los parametros enviados por la clase anterior.
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
    	ivPeso.setVisibility(View.INVISIBLE);
    	
    }
    
    /**ESTABLECE UN VALOR PREDETERMINADO A LOS ELEMENTOS */
    public void inicializarElementos(){
    	elementoCu=0;
    	elementoAg=0;
    	elementoPb=0;
     	elementoZn=0;
     	elementoFe=0;
     	elementoPeso=0;
    }
    
    
    /**PREPARAMOS LOS VALORES INICIALES DE LA INTERFAZ*/
    public void cargarRegistroFajaView(){
    	
    	TextView tv= (TextView)findViewById(R.id.registrar_faja);
    	tv.setText(R.string.label_registro);
    	
    	//inizializamos los editText
        textCu = (EditText)findViewById(R.id.edit_F_ElemCu);
        textCu.addTextChangedListener(filterTextWatcher);
        EditTextListener listenerCu= new EditTextListener();
        listenerCu.setEditTextListener(this, textCu, R.string.label_registro_cu);

        textAg = (EditText)findViewById(R.id.edit_F_ElemAg);
        textAg.addTextChangedListener(filterTextWatcher);
        EditTextListener listenerAg= new EditTextListener();
        listenerAg.setEditTextListener(this, textAg, R.string.label_registro_ag);
        
        textPb = (EditText)findViewById(R.id.edit_F_ElemPb);
        textPb.addTextChangedListener(filterTextWatcher);
        EditTextListener listenerPb= new EditTextListener();
        listenerPb.setEditTextListener(this, textPb, R.string.label_registro_pb);

        textZn = (EditText)findViewById(R.id.edit_F_ElemZn);
        textZn.addTextChangedListener(filterTextWatcher);
        EditTextListener lZn= new EditTextListener();
        lZn.setEditTextListener(this, textZn, R.string.label_registro_zn);

        textFe = (EditText)findViewById(R.id.edit_F_ElemFe);
        textFe.addTextChangedListener(filterTextWatcher);
        EditTextListener lFe= new EditTextListener();
        lFe.setEditTextListener(this, textFe, R.string.label_registro_fe);

    	
    	textPeso= (EditText)findViewById(R.id.editElemPeso);
    	textPeso.addTextChangedListener(filterTextWatcher);
    	EditTextListener lPeso= new EditTextListener();
        lPeso.setEditTextListener(this, textPeso, R.string.label_registro_peso);

    	
    	Fecha.cargarFechaActual(this);
    	String fechaFinal=Fecha.getFecha_dia()+"/"+Fecha.getFecha_mes()+"/"+Fecha.getFecha_año();
    	//para evitar el problema de la fecha.
    	mYear= Integer.parseInt(Fecha.getFecha_año());
		mMonth= Integer.parseInt(Fecha.getFecha_mes())-1;
		mDay= Integer.parseInt(Fecha.getFecha_dia());
		
    	textFecha= (TextView)findViewById(R.id.editElemFecha);
    	textFecha.setText(fechaFinal);
    	textFecha.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
        		Fecha.cargarFechaActual(FajaRegistro.this);
		        mYear = Integer.parseInt(Fecha.getFecha_año());
        		mMonth = Integer.parseInt(Fecha.getFecha_mes())-1;
        		mDay = Integer.parseInt(Fecha.getFecha_dia());
        		showDialog(DATE_DIALOG_ID);
        	}
        });
    	
    	//manejamos la hora
    	Fecha.cargarFechaActual(this);
    	String horaFinal=Fecha.getFecha_hora()+":"+Fecha.getFecha_minuto()+":00";
    	mHour = Integer.parseInt(Fecha.getFecha_hora());
		mMinute = Integer.parseInt(Fecha.getFecha_minuto());
		
    	textHora= (TextView)findViewById(R.id.editElemHora);
    	textHora.setText(horaFinal);
    	textHora.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
        		Fecha.cargarFechaActual(FajaRegistro.this);
		        mHour = Integer.parseInt(Fecha.getFecha_hora());
        		mMinute = Integer.parseInt(Fecha.getFecha_minuto());
        		showDialog(TIME_DIALOG_ID);
        	}
        });
    	
        ivCu= (ImageView)findViewById(R.id.Img_F_ElemCu);
        ivAg= (ImageView)findViewById(R.id.Img_F_ElemAg);
        ivPb= (ImageView)findViewById(R.id.Img_F_ElemPb);
        ivZn= (ImageView)findViewById(R.id.Img_F_ElemZn);
        ivFe= (ImageView)findViewById(R.id.Img_F_ElemFe);
        ivPeso= (ImageView)findViewById(R.id.ImgElemPeso);

    }
    
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
	
	
	/**PERSONALIZAMOS LA MANERA EN QUE SE MUESTRA EL TECLADO*/
	public void personalizarTeclado(EditText sText, int sLabelTitle){
		
		//seteamos los valores antes de cargar el teclado
		ElementoUnidad[] eUnidad= meUnidad.getElementoUnidad(COD_EMPRESA, COD_UNIDAD);
		EditTextListener.setSizeDigitosTeclado(eUnidad, sLabelTitle);
		EditTextListener.TITLE_TECLADO= sLabelTitle;
		
		currentText = sText;
        showDialog(KEYPAD_DIALOG);
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
				
			case KEYPAD_DIALOG:
	        	dialog = new KeyPad(this, KEYPAD_DIALOG,  currentText, EditTextListener.SIZE_ENTERO, EditTextListener.SIZE_DECIMAL);
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
					boolean esMenor= Fecha.isFechaMenor(FajaRegistro.this, dateAfter);
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
						boolean esMenor= Fecha.isFechaMenor(FajaRegistro.this, dateAfter);
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
    public Lectura getLecturaFaja(){
    	
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
    		lect.setPeso(textPeso.getText().toString());
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
    	if(!textCu.getText().toString().equals(""))checkVacio=false;
    	if(!textAg.getText().toString().equals(""))checkVacio=false;
    	if(!textPb.getText().toString().equals(""))checkVacio=false;
    	if(!textZn.getText().toString().equals(""))checkVacio=false;
    	if(!textFe.getText().toString().equals(""))checkVacio=false;
    	if(!textPeso.getText().toString().equals(""))checkVacio=false;
    	return checkVacio;
    }
    
    
    /**METODO QUE PERMITE VALIDAR LOS CAMPOS DE LA INTERFAZ*/
    public boolean validarDatosFaja(){
    	//comprobar que los datos sean correctos
    	boolean checker=false;
    	float val_elemento=0;
    	float val_minimo=0;
    	float val_maximo=0;
    	
    	try {
        	elementoCu= Float.parseFloat(textCu.getText().toString());
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
        	elementoPeso= Float.parseFloat(textPeso.getText().toString());
    	} catch (NumberFormatException e) {
			ivPeso.setVisibility(View.VISIBLE);
			checker=true;
    	}
    	try {
			
		
    		ElementoUnidad[] oElementoUnidad= meUnidad.getElementoUnidad(COD_EMPRESA, COD_UNIDAD);
    	
    		for (int i=0; i<oElementoUnidad.length;i++){
    			ImageView iv= new ImageView(this);
    			val_minimo= Float.parseFloat(oElementoUnidad[i].getValorMinimo());
    			val_maximo= Float.parseFloat(oElementoUnidad[i].getValorMaximo());
    			
    			if(oElementoUnidad[i].getCodigo_elementoQuimico().equals(Sistema.ELEMENTO_CU)){
    				iv=(ImageView)findViewById(R.id.Img_F_ElemCu);
    				val_elemento=elementoCu;
    			}
    			if(oElementoUnidad[i].getCodigo_elementoQuimico().equals(Sistema.ELEMENTO_AG)){
    				iv=(ImageView)findViewById(R.id.Img_F_ElemAg);
    				val_elemento=elementoAg;
	    		}
    			if(oElementoUnidad[i].getCodigo_elementoQuimico().equals(Sistema.ELEMENTO_PB)){
    				 iv=(ImageView)findViewById(R.id.Img_F_ElemPb);
    				val_elemento=elementoPb;
    			}
    			if(oElementoUnidad[i].getCodigo_elementoQuimico().equals(Sistema.ELEMENTO_ZN)){
    				iv=(ImageView)findViewById(R.id.Img_F_ElemZn);
    				val_elemento=elementoZn;
    			}
    			if(oElementoUnidad[i].getCodigo_elementoQuimico().equals(Sistema.ELEMENTO_FE)){
    				iv=(ImageView)findViewById(R.id.Img_F_ElemFe);
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
    		
    	} catch (Exception e) {
    		//new AlertDialog.Builder(this).setMessage(e.toString()).show();
		}
			
    	return checker;
    }
    
    
    /**METODOS USADOS*/
    
    /*public void registrarLecturaFaja(Lectura lectura){
    }*/
    
    /*public ElementoUnidad[] getElementoUnidad(Context context, String CODI_EMPRE, String CODI_UNMI ){
    }*/
    
    
}