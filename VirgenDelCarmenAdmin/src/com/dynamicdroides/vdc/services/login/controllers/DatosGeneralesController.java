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

import com.dynamicdroides.db.virgendelcarmen.Cursos;
import com.dynamicdroides.vdc.services.business.VirgenDelCarmenBusiness;

public class DatosGeneralesController  extends IControlador{

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
					if(evento.equals("index")){

						
				    	view2=new InternalResourceView("jsp/admin/sendMessage.jsp");
				    	return new ModelAndView(view2, "model", myModel);

					}else if(request.getParameter("event")!=null && request.getParameter("event").equals("send")){
						//TODO enviar mensaje
						//Aqui tienes que recibir los datos de la pagina
						//y llamar al sendmensae
						
						return new ModelAndView("//ajax", "model", myModel);
					}else{
				    	view2=new InternalResourceView("jsp/admin/general_admin.jsp");
				    	return new ModelAndView(view2, "model", myModel);

					}
//				List<Cursos> cursos=getCursosBeans();
//				myModel.put("cursos",cursos);
					
				}else{
			    	view2=new InternalResourceView("jsp/admin/general_admin.jsp");
			    	return new ModelAndView(view2, "model", myModel);
					
				}
				
			}catch(Exception e){
				e.printStackTrace();
				return returnErrorControl(myModel, e);
			}finally{
				//este codigo queremos que se ejecute si o si
				
				
			}
		}
		
		public String getOnlineJsonData(List<Cursos> cursos) throws Exception{
			com.dynamicdroides.vdc.services.beans.JSONBean bean=new com.dynamicdroides.vdc.services.beans.JSONBean();
			Cursos beans[]=new Cursos[cursos.size()];
			Cursos curso=null;
			int i=0;
			for(Cursos alu:cursos){
				curso=new Cursos();
				curso.setIdcurso(alu.getIdcurso());
				curso.setNombre(alu.getNombre());
				curso.setResponsable(alu.getResponsable());
				beans[i]=curso;
				      i++;
			}
			
			bean.setRows(beans);
			bean.setPage(1);
			bean.setRecords(cursos.size());
			bean.setTotal(cursos.size());

			return serializeBean(bean);
			
		}


		private List<Cursos> getCursosBeans(){
			
			List listaCursos=business.getCursos();
			
			return listaCursos;
		}

}
