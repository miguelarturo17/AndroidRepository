package sbs.milpo.model.dal;


public class TipoReconocimiento {

    private String id = "";
    private String codigo_TipoReconocimiento = "";
    private String tipo_Reconocimiento_Desc = "";
    private String estado = "";
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the codigo_TipoReconocimiento
	 */
	public String getCodigo_TipoReconocimiento() {
		return codigo_TipoReconocimiento;
	}
	/**
	 * @param codigo_TipoReconocimiento the codigo_TipoReconocimiento to set
	 */
	public void setCodigo_TipoReconocimiento(String codigo_TipoReconocimiento) {
		this.codigo_TipoReconocimiento = codigo_TipoReconocimiento;
	}
	/**
	 * @return the tipo_Reconocimiento_Desc
	 */
	public String getTipo_Reconocimiento_Desc() {
		return tipo_Reconocimiento_Desc;
	}
	/**
	 * @param tipo_Reconocimiento_Desc the tipo_Reconocimiento_Desc to set
	 */
	public void setTipo_Reconocimiento_Desc(String tipo_Reconocimiento_Desc) {
		this.tipo_Reconocimiento_Desc = tipo_Reconocimiento_Desc;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
    
    
	

    
}
