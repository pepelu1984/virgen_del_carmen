package com.dynamicdroides.db.virgendelcarmen;

import java.util.Date;

/**
 * AbstractMenu entity provides the base persistence definition of the Menu
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMenu implements java.io.Serializable {

	// Fields

	private Integer idmenu;
	private String ensalada;
	private String plato1;
	private String plato2;
	private String postre;
	private Date fecha;

	// Constructors

	/** default constructor */
	public AbstractMenu() {
	}

	/** minimal constructor */
	public AbstractMenu(String ensalada, String plato1, String postre,
			Date fecha) {
		this.ensalada = ensalada;
		this.plato1 = plato1;
		this.postre = postre;
		this.fecha = fecha;
	}

	/** full constructor */
	public AbstractMenu(String ensalada, String plato1, String plato2,
			String postre, Date fecha) {
		this.ensalada = ensalada;
		this.plato1 = plato1;
		this.plato2 = plato2;
		this.postre = postre;
		this.fecha = fecha;
	}

	// Property accessors

	public Integer getIdmenu() {
		return this.idmenu;
	}

	public void setIdmenu(Integer idmenu) {
		this.idmenu = idmenu;
	}

	public String getEnsalada() {
		return this.ensalada;
	}

	public void setEnsalada(String ensalada) {
		this.ensalada = ensalada;
	}

	public String getPlato1() {
		return this.plato1;
	}

	public void setPlato1(String plato1) {
		this.plato1 = plato1;
	}

	public String getPlato2() {
		return this.plato2;
	}

	public void setPlato2(String plato2) {
		this.plato2 = plato2;
	}

	public String getPostre() {
		return this.postre;
	}

	public void setPostre(String postre) {
		this.postre = postre;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}