package com.dynamicdroides.db.virgendelcarmen;

/**
 * AbstractCursos entity provides the base persistence definition of the Cursos
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCursos implements java.io.Serializable {

	// Fields

	private Integer idcurso;
	private String nombre;
	private String responsable;
	private Boolean isprimaria;

	// Constructors

	/** default constructor */
	public AbstractCursos() {
	}

	/** minimal constructor */
	public AbstractCursos(String nombre) {
		this.nombre = nombre;
	}

	/** full constructor */
	public AbstractCursos(String nombre, String responsable, Boolean isprimaria) {
		this.nombre = nombre;
		this.responsable = responsable;
		this.isprimaria = isprimaria;
	}

	// Property accessors

	public Integer getIdcurso() {
		return this.idcurso;
	}

	public void setIdcurso(Integer idcurso) {
		this.idcurso = idcurso;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getResponsable() {
		return this.responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public Boolean getIsprimaria() {
		return this.isprimaria;
	}

	public void setIsprimaria(Boolean isprimaria) {
		this.isprimaria = isprimaria;
	}

}