package com.dynamicdroides.beans;

import java.io.Serializable;

public class ComidasBean implements Serializable{
	private String ensalda;
	private String plato1;
	private String plato2;
	private String postre;
	public String getEnsalda() {
		return ensalda;
	}
	public void setEnsalda(String ensalda) {
		this.ensalda = ensalda;
	}
	public String getPlato1() {
		return plato1;
	}
	public void setPlato1(String plato1) {
		this.plato1 = plato1;
	}
	public String getPlato2() {
		return plato2;
	}
	public void setPlato2(String plato2) {
		this.plato2 = plato2;
	}
	public String getPostre() {
		return postre;
	}
	public void setPostre(String postre) {
		this.postre = postre;
	}

}
