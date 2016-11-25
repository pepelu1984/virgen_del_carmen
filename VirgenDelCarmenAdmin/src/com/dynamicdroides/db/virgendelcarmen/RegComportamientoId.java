package com.dynamicdroides.db.virgendelcarmen;

import java.util.Date;

/**
 * RegComportamientoId entity. @author MyEclipse Persistence Tools
 */
public class RegComportamientoId extends AbstractRegComportamientoId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public RegComportamientoId() {
	}

	/** full constructor */
	public RegComportamientoId(Integer idregcompor, Integer idalumno,
			String tipo, String valor, Date fechadesde, Date fechahasta) {
		super(idregcompor, idalumno, tipo, valor, fechadesde, fechahasta);
	}

}
