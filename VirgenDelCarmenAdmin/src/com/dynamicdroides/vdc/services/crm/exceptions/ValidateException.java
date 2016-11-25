package com.dynamicdroides.vdc.services.crm.exceptions;


/**
 * Clase que extiende de Exception, para recoger excepciones del tipo validaciones
 * @author Pipe
 *
 */
public class ValidateException extends Exception{

	private String myexception;

	public String getMyexception() {
		return myexception;
	}

	public void setMyexception(String myexception) {
		this.myexception = myexception;
	}
	
	
}
