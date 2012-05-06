package sbs.milpo.view;

import sbs.milpo.controller.Sistema;
import sbs.milpo.model.dal.ElementoUnidad;

import com.BasicKeyboard.R;

import android.view.View;
import android.widget.EditText;


public class EditTextListener {

	private PerforacionRegistro pRegistro;
	private PerforacionModificacion pModificacion;
	private PerforacionClonacion pClonacion;
	private FajaRegistro fRegistro;
	private FajaModificacion fModificacion;
	
	//propiedades el current keyboard
	public static int SIZE_ENTERO=2, SIZE_DECIMAL=3, MAX_VALOR=1, TITLE_TECLADO=0;
	public static String PREFIJO="";
	
	private  EditText  cajaEditText;
	private  int cajaLabelText;
	
	
    
	public EditTextListener() {
	}
	
	/**LISTENER SOBREESCRITO DE TECLADO DOBLE, PARA PERFORACION REGISTRO*/
	public void setEditTextListener(PerforacionRegistro  pr, EditText text, int title){
		cajaEditText=text;
		cajaLabelText=title;
		pRegistro=pr;

		cajaEditText.setOnClickListener(new View.OnClickListener() {
        	@Override
			public void  onClick(View v) {
        		cajaEditText.setSelected(true);
        		pRegistro.personalizarDoubleKeyboard(cajaEditText, cajaLabelText);
        		}
        });
		
	}
	
	/**LISTENER SOBREESCRITO DE TECLADO SIMPLE PARA PERFORACION REGISTRO*/
	public void setEditTextListener(PerforacionRegistro  pr, EditText text, int title, String prefijo, int valor){
		cajaEditText= text;
		cajaLabelText= title;
		pRegistro= pr;
		PREFIJO= prefijo;
		MAX_VALOR= valor;
		
		cajaEditText.setOnClickListener(new View.OnClickListener() {
	      	@Override
				public void onClick(View v) {
	      		PREFIJO="";
	      		MAX_VALOR=100;
	      		pRegistro.personalizarSingleKeyboard(cajaEditText, cajaLabelText, PREFIJO, MAX_VALOR);
	      	}
	      });
		
	}
	
	
	
	/**LISTENER SOBREESCRITO DE TECLADO DOBLE PARA MODIFICACION REGISTRO*/
	public void setEditTextListener(PerforacionModificacion  pm, EditText text, int title){
		cajaEditText=text;
		cajaLabelText=title;
		pModificacion=pm;

		cajaEditText.setOnClickListener(new View.OnClickListener() {
        	@Override
			public void onClick(View v) {
        		pModificacion.personalizarDoubleKeyboard(cajaEditText, cajaLabelText);
        		}
        });
	}
	
	/**LISTENER SOBREESCRITO DE TECLADO SIMPLE PARA CLONACION DE REGISTRO */
	public void setEditTextListener(PerforacionModificacion pm, EditText text, int title, String prefijo, int valor){
		cajaEditText= text;
		cajaLabelText= title;
		pModificacion= pm;
		PREFIJO= prefijo;
		MAX_VALOR= valor;
		
		cajaEditText.setOnClickListener(new View.OnClickListener() {
	      	@Override
				public void onClick(View v) {
	      		PREFIJO="";
	      		MAX_VALOR=100;
	      		pModificacion.personalizarSingleKeyboard(cajaEditText, cajaLabelText, PREFIJO, MAX_VALOR);
	      		}
	      });
		
	}
  
	
    
	/**LISTENER SOBREESCRITO PARA MODIFICACION REGISTRO*/
	public void setEditTextListener(PerforacionClonacion  pc, EditText text, int title){
		cajaEditText=text;
		cajaLabelText=title;
		pClonacion=pc;

		cajaEditText.setOnClickListener(new View.OnClickListener() {
        	@Override
			public void onClick(View v) {
        		pClonacion.personalizarDoubleKeyboard(cajaEditText, cajaLabelText);
        		}
        });
	}
	
	/**LISTENER SOBREESCRITO DE TECLADO SIMPLE PARA CLONACION DE REGISTRO */
	public void setEditTextListener(PerforacionClonacion pc, EditText text, int title, String prefijo, int valor){
		cajaEditText= text;
		cajaLabelText= title;
		pClonacion= pc;
		PREFIJO= prefijo;
		MAX_VALOR= valor;
		
		cajaEditText.setOnClickListener(new View.OnClickListener() {
	      	@Override
				public void onClick(View v) {
	      		PREFIJO="";
	      		MAX_VALOR=100;
	      		pClonacion.personalizarSingleKeyboard(cajaEditText, cajaLabelText, PREFIJO, MAX_VALOR);
	      		}
	      });
	}
	
	
	/**LISTENER SOBREESCRITO PARA MODIFICACION REGISTRO*/
	public void setEditTextListener(FajaRegistro  fr, EditText text, int title){
		cajaEditText= text;
		cajaLabelText= title;
		fRegistro= fr;

		cajaEditText.setOnClickListener(new View.OnClickListener() {
        	@Override
			public void onClick(View v) {
        		fRegistro.personalizarTeclado(cajaEditText, cajaLabelText);
        		}
        });
	}

	/**LISTENER SOBREESCRITO PARA MODIFICACION REGISTRO*/
	public void setEditTextListener(FajaModificacion  fm, EditText text, int title){
		cajaEditText= text;
		cajaLabelText= title;
		fModificacion= fm;

		cajaEditText.setOnClickListener(new View.OnClickListener() {
        	@Override
			public void onClick(View v) {
        		fModificacion.personalizarTeclado(cajaEditText, cajaLabelText);
        		}
        });
	}
	
	/**CONTROLAMOS CUANTOS DIGITOS SE PUEDEN INGRESAR DE ACUERDO A LAS PROPIEDADES DEL EDITTEXT*/
	public static void setSizeDigitosTeclado(ElementoUnidad[] eUnidad, int titleTeclado){
		
		if(eUnidad!=null)
		for (int i=0; i<eUnidad.length;i++){
	    	try {
	    		if(eUnidad[i].getCodigo_elementoQuimico().equals(Sistema.ELEMENTO_CU))
		    		if(titleTeclado == R.string.label_registro_cu){
				    	SIZE_ENTERO= Integer.parseInt(eUnidad[i].getValorEntero());
				    	SIZE_DECIMAL= Integer.parseInt(eUnidad[i].getValorDecimal());
		    		}
    			if(eUnidad[i].getCodigo_elementoQuimico().equals(Sistema.ELEMENTO_AG))
	    			if(titleTeclado == R.string.label_registro_ag){
	    				SIZE_ENTERO= Integer.parseInt(eUnidad[i].getValorEntero());
	    				SIZE_DECIMAL= Integer.parseInt(eUnidad[i].getValorDecimal());
	    	    	}
    			if(eUnidad[i].getCodigo_elementoQuimico().equals(Sistema.ELEMENTO_PB))
	    			if(titleTeclado == R.string.label_registro_pb){
	    				SIZE_ENTERO= Integer.parseInt(eUnidad[i].getValorEntero());
	    				SIZE_DECIMAL= Integer.parseInt(eUnidad[i].getValorDecimal());
	        	    }
    			if(eUnidad[i].getCodigo_elementoQuimico().equals(Sistema.ELEMENTO_ZN))
    				if(titleTeclado == R.string.label_registro_zn){
    					SIZE_ENTERO= Integer.parseInt(eUnidad[i].getValorEntero());
    					SIZE_DECIMAL= Integer.parseInt(eUnidad[i].getValorDecimal());
            	    }
    			if(eUnidad[i].getCodigo_elementoQuimico().equals(Sistema.ELEMENTO_FE))
    				if(titleTeclado == R.string.label_registro_fe){
    					SIZE_ENTERO= Integer.parseInt(eUnidad[i].getValorEntero());
    					SIZE_DECIMAL= Integer.parseInt(eUnidad[i].getValorDecimal());
            	    }
    			
			} catch (Exception e) {}
		}
	};
	
	
	/*public void setEditTextListener(PerforacionRegistro  pr, EditText text, int title){
		cajaEditText=text;
		cajaLabelText=title;
		pRegistro=pr;*/
		
		/*cajaEditText.setOnFocusChangeListener( new View.OnFocusChangeListener() {
              @Override
              public void onFocusChange(View arg0, boolean arg1) {
            	  if(arg1==true)
            		  pRegistro.personalizarDoubleKeyboard(cajaEditText, cajaLabelText);
              }
            });*/
		/*cajaEditText.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				pRegistro.personalizarDoubleKeyboard(cajaEditText, cajaLabelText);
				return false;
			}
		});*/
		
		/*cajaEditText.setOnLongClickListener(new View.OnLongClickListener() {
        	@Override
			public boolean  onLongClick(View v) {
        		return false;
        		}
        });*/
		/*cajaEditText.setOnClickListener(new View.OnClickListener() {
        	@Override
			public void  onClick(View v) {
        		cajaEditText.setSelected(true);
        		pRegistro.personalizarDoubleKeyboard(cajaEditText, cajaLabelText);
        		}
        });*/
		
	/*}*/
	
  
}
