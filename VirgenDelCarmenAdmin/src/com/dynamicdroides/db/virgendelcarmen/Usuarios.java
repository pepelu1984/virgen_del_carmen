package com.dynamicdroides.db.virgendelcarmen;

/**
 * Usuarios entity. @author MyEclipse Persistence Tools
 */
public class Usuarios extends AbstractUsuarios implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Usuarios() {
	}

	/** full constructor */
	public Usuarios(String nombre, String apellidos, String user,
			String password) {
		super(nombre, apellidos, user, password);
	}

}
