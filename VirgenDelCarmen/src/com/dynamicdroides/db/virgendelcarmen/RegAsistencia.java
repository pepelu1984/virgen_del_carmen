package com.dynamicdroides.db.virgendelcarmen;

import java.util.Date;

/**
 * RegAsistencia entity. @author MyEclipse Persistence Tools
 */
public class RegAsistencia extends AbstractRegAsistencia implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public RegAsistencia() {
	}

	/** minimal constructor */
	public RegAsistencia(Integer idalumno, Date fecha) {
		super(idalumno, fecha);
	}

	/** full constructor */
	public RegAsistencia(Integer idalumno, Date fecha, String observaciones,
			Boolean isalumno) {
		super(idalumno, fecha, observaciones, isalumno);
	}

}
