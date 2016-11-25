package com.dynamicdroides.db.virgendelcarmen;

import java.util.Date;

/**
 * AbstractRegComportamiento entity provides the base persistence definition of
 * the RegComportamiento entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractRegComportamiento implements java.io.Serializable {

	// Fields

	private Integer idregcompor;
	private Integer idalumno;
	private String tipo;
	private String valor;
	private Date fechadesde;
	private Date fechahasta;
	private String observaciones;

	// Constructors

	/** default constructor */
	public AbstractRegComportamiento() {
	}

	/** full constructor */
	public AbstractRegComportamiento(Integer idalumno, String tipo,
			String valor, Date fechadesde, Date fechahasta, String observaciones) {
		this.idalumno = idalumno;
		this.tipo = tipo;
		this.valor = valor;
		this.fechadesde = fechadesde;
		this.fechahasta = fechahasta;
		this.observaciones = observaciones;
	}

	// Property accessors

	public Integer getIdregcompor() {
		return this.idregcompor;
	}

	public void setIdregcompor(Integer idregcompor) {
		this.idregcompor = idregcompor;
	}

	public Integer getIdalumno() {
		return this.idalumno;
	}

	public void setIdalumno(Integer idalumno) {
		this.idalumno = idalumno;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Date getFechadesde() {
		return this.fechadesde;
	}

	public void setFechadesde(Date fechadesde) {
		this.fechadesde = fechadesde;
	}

	public Date getFechahasta() {
		return this.fechahasta;
	}

	public void setFechahasta(Date fechahasta) {
		this.fechahasta = fechahasta;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}