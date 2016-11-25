package com.dynamicdroides.vdc.services.login.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

/**
 * Controlador para la pagina de login
 * @author Pipe
 *
 */
public class LoginController extends IControlador{
	private ModelAndView vista;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		
		if (request.isUserInRole("ROLE_ES")) {
	        // code here
			log.debug("Usuario LOGADO");
	    }
		View view = null;
		Map<String, Object> myModel = new HashMap<String, Object>();
		try{
			vista = new ModelAndView("jsp/login.jsp","model", myModel);
			view = new InternalResourceView("jsp/login.jsp");
			vista.setView(view);
			return vista;
		}catch(Exception e){
			return returnErrorControl(myModel, e);
			
		}
	}

}
