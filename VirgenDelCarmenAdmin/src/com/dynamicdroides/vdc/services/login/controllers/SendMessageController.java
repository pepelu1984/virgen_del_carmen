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
import com.dynamicdroides.db.virgendelcarmen.Cursos;
import com.dynamicdroides.vdc.services.business.VirgenDelCarmenBusiness;

public class SendMessageController   extends IControlador{
	 static Logger log = Logger.getLogger(IndexController.class);
	 private VirgenDelCarmenBusiness business;
	 private HashMap<Integer,String> namecursos=new HashMap<Integer, String>();
		
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
				if(request.getParameter("event")!=null){
					String evento=""+request.getParameter("event");
					if(evento.equals("index")){
						
						List<Cursos> cursos=getCursosBeans();
						for(Cursos course:cursos){
							namecursos.put(course.getIdcurso(), course.getNombre());
							
						}
						myModel.put("cursos",cursos);
						
						view2=new InternalResourceView("jsp/admin/sendMessage.jsp");
						return new ModelAndView(view2, "model", myModel);
						
					}else if(evento.equals("send")){
					
						//TODO emilio : recibir una lista de alumnos(ids de alumno), y un subject, y un mensaje que estara en el ckeditor
						boolean enviado=true;
						//slCursos
						String listaDestinatarios=""+request.getParameter("idsuser");						
						String subject=""+request.getParameter("Subject");						
						String mensaje=""+request.getParameter("mensaje");	
						
						
						enviado=business.sendMensajeGeneral(mensaje, subject, listaDestinatarios);
						if(enviado){
						//view2=new InternalResourceView("jsp/admin/sendMessage.jsp");
						//return new ModelAndView(view2, "model", myModel);	
							String data="Los mensajes se han enviado correctamente";
						    myModel.put("txtMensaje", data);
						   return new ModelAndView("//ajax", "model", myModel);	
							
						}else{
							String data="Error de envio";
							myModel.put("txtMensaje", data);
							return new ModelAndView("//ajax", "model", myModel);	
						}
						
					}
					
					else if(evento.equals("edit")){

						view2=new InternalResourceView("jsp/addAlumno.jsp");
						return new ModelAndView(view2, "model", myModel);
						
					}else{
						
						
					}
				}
				List<Cursos> cursos=getCursosBeans();
				myModel.put("cursos",cursos);
				
		    	view2=new InternalResourceView("jsp/index.jsp");
				return new ModelAndView(view2, "model", myModel);
		    		
			}catch(Exception e){
				e.printStackTrace();
				return returnErrorControl(myModel, e);
			}finally{
				//este codigo queremos que se ejecute si o si
				
				
			}
		}
		
		private String getComboFromValores(List<Alumnos> alumnos,
				String string, Object object, String string2) {
			// TODO Auto-generated method stub
			return null;
		}

		public String getOnlineJsonData(List<Alumnos> alumnos) throws Exception{
			com.dynamicdroides.vdc.services.beans.JSONBean bean=new com.dynamicdroides.vdc.services.beans.JSONBean();
			AlumnoBean beans[]=new AlumnoBean[alumnos.size()];
			AlumnoBean alumno=null;
			int i=0;
			for(Alumnos alu:alumnos){
				alumno=new AlumnoBean();
				alumno.setApellidos(alu.getApellidos());
				alumno.setCorreopadres(alu.getCorreopadres());
				alumno.setCurso(alu.getCurso());
				alumno.setIdalumno(alu.getIdalumno());
				alumno.setNombre(alu.getNombre());
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

		private List<Cursos> getCursosBeans(){
			
			List listaCursos=business.getCursos();
			
			return listaCursos;
		}



}
