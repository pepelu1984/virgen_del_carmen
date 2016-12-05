package com.dynamicdroides.db.virgendelcarmen;

import java.util.Date;

/**
 * AbstractRegComportamientoId entity provides the base persistence definition
 * of the RegComportamientoId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractRegComportamientoId implements
		java.io.Serializable {

	// Fields

	private Integer idregcompor;
	private Integer idalumno;
	private String tipo;
	private String valor;
	private Date fechadesde;
	private Date fechahasta;

	// Constructors

	/** default constructor */
	public AbstractRegComportamientoId() {
	}

	/** full constructor */
	public AbstractRegComportamientoId(Integer idregcompor, Integer idalumno,
			String tipo, String valor, Date fechadesde, Date fechahasta) {
		this.idregcompor = idregcompor;
		this.idalumno = idalumno;
		this.tipo = tipo;
		this.valor = valor;
		this.fechadesde = fechadesde;
		this.fechahasta = fechahasta;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractRegComportamientoId))
			return false;
		AbstractRegComportamientoId castOther = (AbstractRegComportamientoId) other;

		return ((this.getIdregcompor() == castOther.getIdregcompor()) || (this
				.getIdregcompor() != null && castOther.getIdregcompor() != null && this
				.getIdregcompor().equals(castOther.getIdregcompor())))
				&& ((this.getIdalumno() == castOther.getIdalumno()) || (this
						.getIdalumno() != null
						&& castOther.getIdalumno() != null && this
						.getIdalumno().equals(castOther.getIdalumno())))
				&& ((this.getTipo() == castOther.getTipo()) || (this.getTipo() != null
						&& castOther.getTipo() != null && this.getTipo()
						.equals(castOther.getTipo())))
				&& ((this.getValor() == castOther.getValor()) || (this
						.getValor() != null && castOther.getValor() != null && this
						.getValor().equals(castOther.getValor())))
				&& ((this.getFechadesde() == castOther.getFechadesde()) || (this
						.getFechadesde() != null
						&& castOther.getFechadesde() != null && this
						.getFechadesde().equals(castOther.getFechadesde())))
				&& ((this.getFechahasta() == castOther.getFechahasta()) || (this
						.getFechahasta() != null
						&& castOther.getFechahasta() != null && this
						.getFechahasta().equals(castOther.getFechahasta())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getIdregcompor() == null ? 0 : this.getIdregcompor()
						.hashCode());
		result = 37 * result
				+ (getIdalumno() == null ? 0 : this.getIdalumno().hashCode());
		result = 37 * result
				+ (getTipo() == null ? 0 : this.getTipo().hashCode());
		result = 37 * result
				+ (getValor() == null ? 0 : this.getValor().hashCode());
		result = 37
				* result
				+ (getFechadesde() == null ? 0 : this.getFechadesde()
						.hashCode());
		result = 37
				* result
				+ (getFechahasta() == null ? 0 : this.getFechahasta()
						.hashCode());
		return result;
	}

}