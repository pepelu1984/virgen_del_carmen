package com.dynamicdroides.vdc.services.login.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

import com.dynamicdroides.db.virgendelcarmen.Alumnos;
import com.dynamicdroides.db.virgendelcarmen.Cursos;
import com.dynamicdroides.vdc.services.business.VirgenDelCarmenBusiness;
import com.dynamicdroides.vdc.services.business.CursosHelper;
public class PromocionesController extends IControlador{
	
	private VirgenDelCarmenBusiness business;
	private CursosHelper orden;
	
	public VirgenDelCarmenBusiness getBusiness() {
		return business;
	}

	public void setBusiness(VirgenDelCarmenBusiness business) {
		this.business = business;
	}
	private List<Cursos> getCursosBeans(){
		
		List listaCursos=business.getCursos();
		
		return listaCursos;
	}

	private HashMap<Integer,String> namecursos=new HashMap<Integer, String>();
	
	public String getComboFromValores(List<Cursos> listaValores,String name,String idSelected,String functionChange){
		String dato="";
		int i=0;
		if(idSelected!=null && !idSelected.equals("") && idSelected.equals(""+listaValores.get(i).getIdcurso())){
			//dato="<select  width='300' style='width:400px;left:80 px;' multiple size='10' class='cselect' id='"+name+"' name='"+name+"'>";
			dato="<select  width='300' style='width:300px;margin-left:80 px;' class='cselect' id='"+name+"' name='"+name+"' onchange='javascript:searchAlumnosByCurso4()'>";
		}else{
			//dato="<select  width='300' style='width:400px;left:80 px;' multiple size='10' class='cselect' id='"+name+"' name='"+name+"'>";
			dato="<select  width='300' style='width:300px;margin-left:80 px;' class='cselect' id='"+name+"' name='"+name+"' onchange='javascript:searchAlumnosByCurso4()'>";
		}
		if(listaValores==null){		
			dato=dato+"</select>";
			return dato;
		}
		dato=dato+"<option value=''><a href='#'>Seleccione un registro...";
		dato=dato+"</a></option>";
		for(i=0;i<listaValores.size();i++){
			
			if(idSelected!=null && !idSelected.equals("") && idSelected.equals(""+listaValores.get(i).getIdcurso())){
				dato=dato+"<option selected value='"+listaValores.get(i).getIdcurso()+"'>"+listaValores.get(i).getNombre();
			}else{
				dato=dato+"<option value='"+listaValores.get(i).getIdcurso()+"'>"+listaValores.get(i).getNombre();
			}
			dato=dato+"</option>";
		}
		dato=dato+"</select>";
		return dato;		
}
	
	
	// Pinta un boton para promocionar curso***************
	 public String getBottom(){
		 String dato="<br></br><input type='button' onClick='javascript:promocionarCurso()' value='Promocionar Curso'/>";
		 return dato;		
	 }
	 
	 
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				//return null;
		super.init(request);
		
		View view2=null;
		Map<String, Object> myModel = new HashMap<String, Object>();
		//updateModel(myModel,request);
		try{

			log.error("*******************");
			if(request.getParameter("event")!=null){
				
				String evento=""+request.getParameter("event");
				
				if(evento.equals("index")){		
					 List<Cursos> cursos=null;					
					 List<Cursos> lista=null;
					
					 lista=getCursosBeans();	
					 //cursos=orden.OrderedByPosition(lista);		
					 cursos=CursosHelper.OrderedByPosition(lista);
					
					for(Cursos course:cursos){
						namecursos.put(course.getIdcurso(), course.getNombre());						
					}				
						myModel.put("cursos",cursos);
						view2=new InternalResourceView("jsp/admin/Promociones.jsp");
						return new ModelAndView(view2, "model", myModel);									
				}else if (evento.equals("asign")){	
					 List<Cursos> cursos=null;					
					 List<Cursos> lista=null;
					
					 lista=getCursosBeans();	
					 //cursos=orden.OrderedByPosition(lista);		
					 cursos=CursosHelper.OrderedByPosition(lista);
					
					for(Cursos course:cursos){
						namecursos.put(course.getIdcurso(), course.getNombre());						
					}				
						myModel.put("cursos",cursos);
						view2=new InternalResourceView("jsp/admin/alergiasalumnos.jsp");
						return new ModelAndView(view2, "model", myModel);				
				}else if (evento.equals("listcursos")){	
					//String slcurso=request.getParameter("slCursos");
					
					List<Cursos> cursos=business.getCursos();					
					String data=getComboFromValores(cursos, "divCmbCursos", null, "");;				
					//data=data + getBottom();				
									
					myModel.put("divCmbCursos", data);
					
					return new ModelAndView("//ajax", "model", myModel);					
					
				}else if (evento.equals("promocionar")){		
					// Codigo para cambiarlo. Para pasar alumnos de un curso a otro.
								
					String curso=request.getParameter("curso");
					String cursosiguiente=request.getParameter("cursosiguiente");
					
					String data="Alumnos promocionados!!";
					
					business.promoteCurso(Integer.parseInt(curso), Integer.parseInt(cursosiguiente));
					
					myModel.put("txtMensaje", data);
					return new ModelAndView("//ajax", "model", myModel);	
					
				}
				
				else{
						String data="Error de envio";
						myModel.put("txtMensaje", data);
						return new ModelAndView("//ajax", "model", myModel);	
					}
				
			}				
						view2=new InternalResourceView("jsp/index.jsp");
						return new ModelAndView(view2, "model", myModel);
			
	    		
		}catch(Exception e){
			e.printStackTrace();
			return returnErrorControl(myModel, e);
		}finally{			
			
			
		}	
		
		
	}
}


