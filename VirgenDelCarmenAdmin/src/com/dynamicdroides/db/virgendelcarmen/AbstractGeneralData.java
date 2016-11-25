package com.dynamicdroides.db.virgendelcarmen;

/**
 * AbstractGeneralData entity provides the base persistence definition of the
 * GeneralData entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractGeneralData implements java.io.Serializable {

	// Fields

	private Integer iddata;
	private String description;
	private String name;
	private String value;

	// Constructors

	/** default constructor */
	public AbstractGeneralData() {
	}

	/** minimal constructor */
	public AbstractGeneralData(String name, String value) {
		this.name = name;
		this.value = value;
	}

	/** full constructor */
	public AbstractGeneralData(String description, String name, String value) {
		this.description = description;
		this.name = name;
		this.value = value;
	}

	// Property accessors

	public Integer getIddata() {
		return this.iddata;
	}

	public void setIddata(Integer iddata) {
		this.iddata = iddata;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}