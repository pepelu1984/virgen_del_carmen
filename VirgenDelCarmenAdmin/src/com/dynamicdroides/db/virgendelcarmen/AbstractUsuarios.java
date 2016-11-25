package com.dynamicdroides.db.virgendelcarmen;

/**
 * AbstractUsuarios entity provides the base persistence definition of the
 * Usuarios entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUsuarios implements java.io.Serializable {

	// Fields

	private Integer iduser;
	private String nombre;
	private String apellidos;
	private String user;
	private String password;
	private String email;
	private Boolean isadmin;

	// Constructors

	/** default constructor */
	public AbstractUsuarios() {
	}

	/** minimal constructor */
	public AbstractUsuarios(String nombre, String apellidos, String user,
			String password) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.user = user;
		this.password = password;
	}

	/** full constructor */
	public AbstractUsuarios(String nombre, String apellidos, String user,
			String password, String email, Boolean isadmin) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.user = user;
		this.password = password;
		this.email = email;
		this.isadmin = isadmin;
	}

	// Property accessors

	public Integer getIduser() {
		return this.iduser;
	}

	public void setIduser(Integer iduser) {
		this.iduser = iduser;
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

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsadmin() {
		return this.isadmin;
	}

	public void setIsadmin(Boolean isadmin) {
		this.isadmin = isadmin;
	}

}