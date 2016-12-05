package com.dynamicdroides.db.virgendelcarmen;

/**
 * AbstractAlumnos entity provides the base persistence definition of the
 * Alumnos entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAlumnos implements java.io.Serializable {

	// Fields

	private Integer idalumno;
	private String nombre;
	private String apellidos;
	private String curso;
	private String correopadres;
	private Boolean comedor;
	private Boolean hasalergias;
	private String listaalergias;

	// Constructors

	/** default constructor */
	public AbstractAlumnos() {
	}

	/** full constructor */
	public AbstractAlumnos(String nombre, String apellidos, String curso,
			String correopadres, Boolean comedor, Boolean hasalergias,
			String listaalergias) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.curso = curso;
		this.correopadres = correopadres;
		this.comedor = comedor;
		this.hasalergias = hasalergias;
		this.listaalergias = listaalergias;
	}

	// Property accessors

	public Integer getIdalumno() {
		return this.idalumno;
	}

	public void setIdalumno(Integer idalumno) {
		this.idalumno = idalumno;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCurso() {
		return this.curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getCorreopadres() {
		return this.correopadres;
	}

	public void setCorreopadres(String correopadres) {
		this.correopadres = correopadres;
	}

	public Boolean getComedor() {
		return this.comedor;
	}

	public void setComedor(Boolean comedor) {
		this.comedor = comedor;
	}

	public Boolean getHasalergias() {
		return this.hasalergias;
	}

	public void setHasalergias(Boolean hasalergias) {
		this.hasalergias = hasalergias;
	}

	public String getListaalergias() {
		return this.listaalergias;
	}

	public void setListaalergias(String listaalergias) {
		this.listaalergias = listaalergias;
	}

}