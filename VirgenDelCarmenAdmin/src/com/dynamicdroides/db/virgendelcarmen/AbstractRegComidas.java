package com.dynamicdroides.db.virgendelcarmen;

import java.util.Date;

/**
 * AbstractRegComidas entity provides the base persistence definition of the
 * RegComidas entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractRegComidas implements java.io.Serializable {

	// Fields

	private Integer idreg;
	private Integer idalumno;
	private Date fecha;
	private String tipocomida;
	private String valor;
	private String observaciones;

	// Constructors

	/** default constructor */
	public AbstractRegComidas() {
	}

	/** minimal constructor */
	public AbstractRegComidas(Integer idalumno) {
		this.idalumno = idalumno;
	}

	/** full constructor */
	public AbstractRegComidas(Integer idalumno, Date fecha, String tipocomida,
			String valor, String observaciones) {
		this.idalumno = idalumno;
		this.fecha = fecha;
		this.tipocomida = tipocomida;
		this.valor = valor;
		this.observaciones = observaciones;
	}

	// Property accessors

	public Integer getIdreg() {
		return this.idreg;
	}

	public void setIdreg(Integer idreg) {
		this.idreg = idreg;
	}

	public Integer getIdalumno() {
		return this.idalumno;
	}

	public void setIdalumno(Integer idalumno) {
		this.idalumno = idalumno;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTipocomida() {
		return this.tipocomida;
	}

	public void setTipocomida(String tipocomida) {
		this.tipocomida = tipocomida;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}