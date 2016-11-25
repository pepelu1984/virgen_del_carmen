package com.dynamicdroides.db.virgendelcarmen;

import java.util.Date;

/**
 * AbstractRegAsistencia entity provides the base persistence definition of the
 * RegAsistencia entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractRegAsistencia implements java.io.Serializable {

	// Fields

	private Integer rowId;
	private Integer idalumno;
	private Date fecha;
	private String observaciones;
	private Boolean isalumno;

	// Constructors

	/** default constructor */
	public AbstractRegAsistencia() {
	}

	/** minimal constructor */
	public AbstractRegAsistencia(Integer idalumno, Date fecha) {
		this.idalumno = idalumno;
		this.fecha = fecha;
	}

	/** full constructor */
	public AbstractRegAsistencia(Integer idalumno, Date fecha,
			String observaciones, Boolean isalumno) {
		this.idalumno = idalumno;
		this.fecha = fecha;
		this.observaciones = observaciones;
		this.isalumno = isalumno;
	}

	// Property accessors

	public Integer getRowId() {
		return this.rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
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

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Boolean getIsalumno() {
		return this.isalumno;
	}

	public void setIsalumno(Boolean isalumno) {
		this.isalumno = isalumno;
	}

}