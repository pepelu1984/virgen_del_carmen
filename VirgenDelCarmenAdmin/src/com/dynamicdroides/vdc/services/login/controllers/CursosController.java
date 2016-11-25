package com.dynamicdroides.vdc.services.login.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

import com.dynamicdroides.db.virgendelcarmen.AlumnoBean;
import com.dynamicdroides.db.virgendelcarmen.Alumnos;
import com.dynamicdroides.db.virgendelcarmen.CursoBean;
import com.dynamicdroides.db.virgendelcarmen.Cursos;
import com.dynamicdroides.db.virgendelcarmen.Usuarios;
import com.dynamicdroides.vdc.services.business.CursosHelper;
import com.dynamicdroides.vdc.services.business.VirgenDelCarmenBusiness;

public class CursosController extends IControlador{

	 static Logger log = Logger.getLogger(IndexController.class);
	 private VirgenDelCarmenBusiness business;
	 public void deleteCursos(Cursos curso){
			
			Transaction t=business.getCursosDAO().getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
			business.getCursosDAO().getHibernateTemplate().getSessionFactory().getCurrentSession().delete(curso);
			t.commit();
			
		}
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
					if(evento.equals("add")){

						//view2=new InternalResourceView("jsp/addAlumno.jsp");
						//return new ModelAndView(view2, "model", myModel);
					}else if(request.getParameter("event")!=null && request.getParameter("event").equals("excel")){
						
						
					}else if(evento.equals("edit")){
						Integer cursoID=Integer.parseInt(request.getParameter("idcurso"));
						
						Cursos curso=business.getCursosDAO().findById(cursoID);
						myModel.put("curso", curso);
						view2=new InternalResourceView("jsp/admin/editCurso.jsp");
						return new ModelAndView(view2, "model", myModel);
						
					}else if(evento.equals("edit")){
						
					}else if(evento.equals("save_edit")){
						
						Integer cursoID=Integer.parseInt(request.getParameter("curso"));
						String nombre=request.getParameter("namecurso");
						
						Cursos curso=business.getCursosDAO().findById(cursoID);
						curso.setNombre(nombre);
						
						Transaction t=business.getCursosDAO().getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
						business.getCursosDAO().getHibernateTemplate().getSessionFactory().getCurrentSession().update(curso);
						
						t.commit();
						
					}else if(evento.equals("save")){

					}else if(evento.equals("delete")){
					
						Integer cursoID=Integer.parseInt(request.getParameter("curso"));
						Cursos curso=business.getCursosDAO().findById(cursoID);
						deleteCursos(curso);
							
					}
				}
				List<Cursos> cursos=getCursosBeans();
				myModel.put("cursos",cursos);
		    	view2=new InternalResourceView("jsp/cursos.jsp");
				return new ModelAndView(view2, "model", myModel);
		    		
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

		public VirgenDelCarmenBusiness getBusiness() {
			return business;
		}

		public void setBusiness(VirgenDelCarmenBusiness business) {
			this.business = business;
		}	
		private List<Usuarios> getEducadoresBeans(){
			
			List<Usuarios> listaEducadores=business.getUsuarios();
			
			return listaEducadores;
		}

		private List<Cursos> getCursosBeans(){
			
			List listaCursos=business.getCursos();
			
			return listaCursos;
		}

}
