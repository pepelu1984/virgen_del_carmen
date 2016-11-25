package com.dynamicdroides.beans;

import java.util.Date;


public class ComportamientoBean implements java.io.Serializable{

	private Integer idregcompor;
	private Integer idalumno;
	private String tipo;
	private String valor;
	private String fechadesde;
	private String fechahasta;
	public Integer getIdregcompor() {
		return idregcompor;
	}
	public void setIdregcompor(Integer idregcompor) {
		this.idregcompor = idregcompor;
	}
	public Integer getIdalumno() {
		return idalumno;
	}
	public void setIdalumno(Integer idalumno) {
		this.idalumno = idalumno;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getFechadesde() {
		return fechadesde;
	}
	public void setFechadesde(String fechadesde) {
		this.fechadesde = fechadesde;
	}
	public String getFechahasta() {
		return fechahasta;
	}
	public void setFechahasta(String fechahasta) {
		this.fechahasta = fechahasta;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	private String observaciones;

}
