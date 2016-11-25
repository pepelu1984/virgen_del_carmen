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
import com.dynamicdroides.db.virgendelcarmen.Cursos;
import com.dynamicdroides.db.virgendelcarmen.Usuarios;
import com.dynamicdroides.vdc.services.business.CursosHelper;
import com.dynamicdroides.vdc.services.business.VirgenDelCarmenBusiness;

public class AlumnosController  extends IControlador{
	 static Logger log = Logger.getLogger(AlumnosController.class);
	 private VirgenDelCarmenBusiness business;
		public void deleteAlumno(Alumnos alumno){
			
			Transaction t=business.getAlumnosDAO().getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
			business.getAlumnosDAO().getHibernateTemplate().getSessionFactory().getCurrentSession().delete(alumno);
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
						List<Cursos> cursos=getCursosBeans();
						myModel.put("cursos",cursos);
						
						view2=new InternalResourceView("jsp/addAlumno.jsp");
						return new ModelAndView(view2, "model", myModel);
					}else if(request.getParameter("event")!=null && request.getParameter("event").equals("excel")){
						System.out.println("***************************************");
						
						String curso=""+request.getParameter("idcurso");
						List<Alumnos> alumnos=business.getAlumnos(Integer.parseInt(curso));
						request.setAttribute("downloadFile",true);
						response.setContentType("application/vnd.ms-excel");
						response.setHeader("Content-disposition","attachment; filename=Suscripciones.xls"); 
						
						return new ModelAndView("ExcelSummary","alumnos",alumnos);
					}else if(evento.equals("delete")){

						String idAlumno=request.getParameter("idalumno");
						
						Alumnos alumno=business.getAlumnosDAO().findById(Integer.parseInt(idAlumno));
						deleteAlumno(alumno);
						return new ModelAndView("//ajax", "model", myModel);
						
					}else if(evento.equals("edit")){
						List<Cursos> cursos=getCursosBeans();
						myModel.put("cursos",cursos);
						String idAlumno=request.getParameter("idalumno");
						Alumnos alumno=business.findAlumno(Integer.parseInt(idAlumno));
						myModel.put("alumno", alumno);
						
						view2=new InternalResourceView("jsp/editAlumnos.jsp");
						return new ModelAndView(view2, "model", myModel);
						
					}else if(evento.equals("save_edit")){
						if(request.getParameter("slCursos")!=null){
							
							int idCurso=Integer.parseInt(""+request.getParameter("slCursos"));
							int idAlumno=Integer.parseInt(""+request.getParameter("idAlumno"));
							String nombre=new String((request.getParameter("txtName")).getBytes("ISO-8859-1"),"UTF-8");
							String txtApellidos=new String(request.getParameter("txtApellidos").getBytes("ISO-8859-1"),"UTF-8");
							String mailPadres=request.getParameter("mailPadres");
							String mailPadres2=request.getParameter("mailPadres2");
							String hasComedor=request.getParameter("hasComedor");
							
							Alumnos alumno=null;
							alumno=business.findAlumno(idAlumno);
							if(hasComedor!=null && hasComedor.equals("1")){
								alumno.setComedor(true);
							}else
								alumno.setComedor(false);
							alumno.setApellidos(txtApellidos);
							alumno.setCorreopadres(mailPadres);
							if(mailPadres2!=null){
								alumno.setCorreopadres(mailPadres+","+mailPadres2);
							}
							alumno.setCurso(""+idCurso);
							alumno.setNombre(nombre);
							boolean result=business.editAlumno(alumno);
							
							return new ModelAndView("//ajax", "model", myModel);
						
						}
					}else if(evento.equals("save")){
						
						if(request.getParameter("slCursos")!=null){
							
							int idCurso=Integer.parseInt(""+request.getParameter("slCursos"));
							String nombre=request.getParameter("txtName");
							String txtApellido1=request.getParameter("txtApellido1");
							String txtApellido2=request.getParameter("txtApellido2");
							String mailPadres=request.getParameter("mailPadres");
							//INI SEGUNDO CORREO
							String mailPadres2=request.getParameter("mailPadres2");
							//FIN SEGUNDO CORREO
							String hasComedor=request.getParameter("hasComedor");
							Alumnos alumno=new Alumnos();
							alumno.setApellidos(txtApellido1+" "+txtApellido2);
							
							alumno.setCorreopadres(mailPadres);
							if(mailPadres2!=null){
								alumno.setCorreopadres(mailPadres+","+mailPadres2);
							}
							alumno.setCurso(""+idCurso);
							alumno.setNombre(nombre);
							if(hasComedor!=null && hasComedor.equals("1")){
								alumno.setComedor(true);
							}else
								alumno.setComedor(false);
							boolean result=business.addAlumno(alumno);
							
						}
						
					}
				}
				List<Cursos> cursos=getCursosBeans();
				myModel.put("cursos",cursos);
				List<Usuarios> educadores=getEducadoresBeans();
				myModel.put("educadores",educadores);
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
				alumno.setCorreopadres(alu.getCorreopadres());
				alumno.setCurso(CursosHelper.getCourseName(Integer.parseInt(alu.getCurso())));
				alumno.setIdalumno(alu.getIdalumno());
				alumno.setListaalergias(alu.getListaalergias());
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
		private List<Usuarios> getEducadoresBeans(){
			
			List<Usuarios> listaEducadores=business.getUsuarios();
			
			return listaEducadores;
		}

		private List<Cursos> getCursosBeans(){
			
			List listaCursos=business.getCursos();
			
			return listaCursos;
		}

}
