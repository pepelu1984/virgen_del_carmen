package com.dynamicdroides.vdc.services.login.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

import com.dynamicdroides.db.virgendelcarmen.Cursos;
import com.dynamicdroides.db.virgendelcarmen.Usuarios;
import com.dynamicdroides.vdc.services.business.CursosHelper;
import com.dynamicdroides.vdc.services.business.VirgenDelCarmenBusiness;

/**
 * Controller principal que recibe la llamada inicial y carga el index.jsp
 * @author Pipe
 *
 */
public class IndexController extends IControlador{
	 static Logger log = Logger.getLogger(IndexController.class);
	 
	 //TODO cambiar

	 private VirgenDelCarmenBusiness business;
	 
	public VirgenDelCarmenBusiness getBusiness() {
		return business;
	}

	public void setBusiness(VirgenDelCarmenBusiness business) {
		this.business = business;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		super.init(request);
	
		View view2=null;
		Map<String, Object> myModel = new HashMap<String, Object>();
		//updateModel(myModel,request);
		try{

			log.error("*******************");
			
			String evento=""+request.getParameter("event");
			List<Cursos> cursos=getCursosBeans();
			myModel.put("cursos",cursos);
			
			List<Usuarios> educadores=getEducadoresBeans();
			myModel.put("educadores",educadores);
							
		
			List<String> results = new ArrayList<String>();
			
			String pathImagenes=getRealPath()+"/img/jack";
			System.out.println("Reading:"+pathImagenes);
			File[] files = new File(pathImagenes).listFiles();

			for (File file : files) {
			    if (file.isFile() && file.getName().toUpperCase().indexOf(".JPG")>-1) {
			        results.add(file.getName());
			    }
			}
			myModel.put("imagenes", results);

			
			
	    	view2=new InternalResourceView("jsp/index.jsp");
			return new ModelAndView(view2, "model", myModel);
	    		
		}catch(Exception e){
			e.printStackTrace();
			return returnErrorControl(myModel, e);
		}finally{
			//este codigo queremos que se ejecute si o si
			
			
		}
	}
	private List<Usuarios> getEducadoresBeans(){
		
		List<Usuarios> listaEducadores=business.getUsuarios();
		
		return listaEducadores;
	}

private List<Cursos> getCursosBeans(){
	
	List listaCursos=CursosHelper.getAllCursos();
	
	return listaCursos;
}
}
