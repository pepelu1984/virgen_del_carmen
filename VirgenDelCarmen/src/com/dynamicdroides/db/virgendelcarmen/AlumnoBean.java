package com.dynamicdroides.db.virgendelcarmen;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(AlumnoBean.class)
public class AlumnoBean extends AbstractAlumnos implements java.io.Serializable {
	private boolean isComportamientoFilled;
	private Date fechaInicio;
	private Date fechaFin;
	private String cursoname;
	
	public String getCursoname() {
		return cursoname;
	}
	public void setCursoname(String cursoname) {
		this.cursoname = cursoname;
	}
	private String comportamientos;
	
	public String getComportamientos() {
		return comportamientos;
	}
	public void setComportamientos(String comportamientos) {
		this.comportamientos = comportamientos;
	}
	public boolean isComportamientoFilled() {
		return isComportamientoFilled;
	}
	public void setComportamientoFilled(boolean isComportamientoFilled) {
		this.isComportamientoFilled = isComportamientoFilled;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
		
}
