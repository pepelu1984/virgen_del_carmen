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

import com.dynamicdroides.db.virgendelcarmen.AlumnoBean;
import com.dynamicdroides.db.virgendelcarmen.Alumnos;
import com.dynamicdroides.vdc.services.business.VirgenDelCarmenBusiness;
import com.dynamicdroides.virgendelcarmen.manager.VirgenDelCarmenManager;

public class SearchController  extends IControlador{
	 static Logger log = Logger.getLogger(IndexController.class);
	 private VirgenDelCarmenBusiness business;
	 

		@Override
		public ModelAndView handleRequest(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			// TODO Auto-generated method stub
			super.init(request);
			request.setCharacterEncoding("UTF-8"); 		
			
			View view2=null;
			Map<String, Object> myModel = new HashMap<String, Object>();
			//updateModel(myModel,request);
			try{

				log.error("*******************");
				if(request.getParameter("event")!=null){
					String evento=""+request.getParameter("event");
					if(evento.equals("list")){
						String curso=""+request.getParameter("idparent");
						List<Alumnos> alumnos=business.getAlumnos(Integer.parseInt(curso));
				    	
						String data=this.getOnlineJsonData(alumnos);
						alumnos=null;
						request.setAttribute("datoJSON", data);
					
						return new ModelAndView("//ajax", "model", myModel);
				
					}else if(evento.equals("curso")){
						String curso=""+request.getParameter("idcurso");
				    	myModel.put("idcurso", curso);
				    	
						view2=new InternalResourceView("jsp/alumnos.jsp");
						return new ModelAndView(view2, "model", myModel);
						
					}else{
						
						
					}
				}
				
		    	view2=new InternalResourceView("jsp/index.jsp");
				return new ModelAndView(view2, "model", myModel);
		    		
			}catch(Exception e){
				e.printStackTrace();
				return returnErrorControl(myModel, e);
			}finally{
				//este codigo queremos que se ejecute si o si
				
				
			}
		}
		
		public String getOnlineJsonData(List<Alumnos> alumnos) throws Exception{
			com.dynamicdroides.vdc.services.beans.JSONBean bean=new com.dynamicdroides.vdc.services.beans.JSONBean();
			AlumnoBean beans[]=new AlumnoBean[alumnos.size()];
			AlumnoBean alumno=null;
			int i=0;
			for(Alumnos alu:alumnos){
				alumno=new AlumnoBean();
				alumno.setApellidos(alu.getApellidos());
				//alumno.setApellidos(new String(alu.getApellidos().getBytes("ISO-8859-1")));
				alumno.setCorreopadres(alu.getCorreopadres());
				//INI SEGUNDO CORREO
				if(alumno.getCorreopadres().contains(",")){
					alumno.setCorreopadres2(alumno.getCorreopadres().substring(alumno.getCorreopadres().indexOf(",")));
					if(!(alumno.getCorreopadres2().length()>1) ){
						alumno.setCorreopadres2("No tiene");
					}
				}else{
					alumno.setCorreopadres2("No tiene");
				}				
				//FIN SEGUNDO CORREO
				alumno.setCurso(alu.getCurso());
				alumno.setIdalumno(alu.getIdalumno());
				//alumno.setNombre(new String(alu.getNombre().getBytes("ISO-8859-1")));
				alumno.setNombre(alu.getNombre());
				alumno.setListaalergias(alu.getListaalergias());
				beans[i]=alumno;
				      i++;
			}
			
			bean.setRows(beans);
			bean.setPage(1);
			bean.setRecords(alumnos.size());
			bean.setTotal(alumnos.size());

			return serializeBean(bean);
			
		}

		public VirgenDelCarmenBusiness getBusiness() {
			return business;
		}

		public void setBusiness(VirgenDelCarmenBusiness business) {
			this.business = business;
		}
	
	}
