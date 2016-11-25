package com.dynamicdroides.db.virgendelcarmen;

/**
 * Alergias entity. @author MyEclipse Persistence Tools
 */
public class Alergias extends AbstractAlergias implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Alergias() {
	}

	/** minimal constructor */
	public Alergias(Integer idalergia) {		
		super(idalergia);
	}

	/** full constructor */
	public Alergias(Integer idalergia, String descripcion, String listacomidas) {		//
		super(idalergia, descripcion, listacomidas);
	}

}
