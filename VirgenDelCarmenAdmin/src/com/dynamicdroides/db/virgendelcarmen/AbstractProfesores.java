package com.dynamicdroides.db.virgendelcarmen;

/**
 * AbstractProfesores entity provides the base persistence definition of the
 * Profesores entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractProfesores implements java.io.Serializable {

	// Fields

	private Integer idprofesor;
	private String nombre;
	private String apellidos;
	private String email;
	private String telefono;

	// Constructors

	/** default constructor */
	public AbstractProfesores() {
	}

	/** minimal constructor */
	public AbstractProfesores(String nombre, String apellidos) {
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	/** full constructor */
	public AbstractProfesores(String nombre, String apellidos, String email,
			String telefono) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
	}

	// Property accessors

	public Integer getIdprofesor() {
		return this.idprofesor;
	}

	public void setIdprofesor(Integer idprofesor) {
		this.idprofesor = idprofesor;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}