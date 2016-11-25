package com.dynamicdroides.db.virgendelcarmen;

/**
 * AbstractAlergias entity provides the base persistence definition of the
 * Alergias entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAlergias implements java.io.Serializable {

	// Fields

	private Integer idalergia;
	private String descripcion;
	private String listacomidas;

	// Constructors

	/** default constructor */
	public AbstractAlergias() {
	}

	/** minimal constructor */
	public AbstractAlergias(Integer idalergia) {
		this.idalergia = idalergia;		
	}

	/** full constructor */
	public AbstractAlergias(Integer idalergia,String descripcion,
			String listacomidas) {		
		this.idalergia = idalergia;
		this.descripcion = descripcion;
		this.listacomidas = listacomidas;
	}

	// Property accessors

	public Integer getIdalergia() {
		return this.idalergia;
	}

	public void setIdalergia(Integer idalergia) {
		this.idalergia = idalergia;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getListacomidas() {
		return this.listacomidas;
	}

	public void setListacomidas(String listacomidas) {
		this.listacomidas = listacomidas;
	}

}