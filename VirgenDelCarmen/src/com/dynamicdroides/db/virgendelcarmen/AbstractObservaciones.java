package com.dynamicdroides.db.virgendelcarmen;

/**
 * AbstractObservaciones entity provides the base persistence definition of the
 * Observaciones entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractObservaciones implements java.io.Serializable {

	// Fields

	private Integer idobservacion;
	private String observaciones;

	// Constructors

	/** default constructor */
	public AbstractObservaciones() {
	}

	/** full constructor */
	public AbstractObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	// Property accessors

	public Integer getIdobservacion() {
		return this.idobservacion;
	}

	public void setIdobservacion(Integer idobservacion) {
		this.idobservacion = idobservacion;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}