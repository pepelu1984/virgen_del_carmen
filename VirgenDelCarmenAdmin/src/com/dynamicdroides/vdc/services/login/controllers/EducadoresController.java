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
import com.dynamicdroides.db.virgendelcarmen.UserBean;
import com.dynamicdroides.db.virgendelcarmen.Usuarios;
import com.dynamicdroides.services.security.devices.SecurityUtils;
import com.dynamicdroides.vdc.services.business.VirgenDelCarmenBusiness;


public class EducadoresController  extends IControlador{
	    static Logger log = Logger.getLogger(IndexController.class);
	    private VirgenDelCarmenBusiness business;
		private HashMap<Integer,String> namecursos=new HashMap<Integer, String>();
		// Pinta un boton para promocionar curso
		 public String getBottom(){
			 String dato="<br></br><br></br><br></br><br></br><input type='button' style='margin-top:50px' onClick='javascript:promocionarCurso()' value='Promocionar Curso'/>";
			 return dato;		
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
				if(request.getParameter("event")!=null){
					String evento=""+request.getParameter("event");
					if(evento.equals("list")){
						//String iduser=""+request.getParameter("idparent");
						String iduser=request.getParameter("idparent");
						if(iduser!=null && !iduser.equals("null")){
							List<Alumnos> alumnos=business.findAlumnosByUser(Integer.parseInt(iduser));
					    	
							String data=this.getOnlineJsonDataAlumnos(alumnos);
							request.setAttribute("datoJSON", data);
						}else{
							
							List<Usuarios> educadores=business.findUsers();
										    	
							String data=this.getOnlineJsonData(educadores);
							request.setAttribute("datoJSON", data);
							
						}
					
					
						return new ModelAndView("//ajax", "model", myModel);
					}else if(evento.equals("index")){
						
						view2=new InternalResourceView("jsp/educadores.jsp");
						return new ModelAndView(view2, "model", myModel);
					}else if(evento.equals("add")){
						
						view2=new InternalResourceView("jsp/admin/add_educadores.jsp");
						return new ModelAndView(view2, "model", myModel);
					}else if(evento.equals("edit")){
						
						String idUser=request.getParameter("iduser");
						
						Usuarios user=business.findUser(Integer.parseInt(idUser));
						
						myModel.put("usuario", user);
						view2=new InternalResourceView("jsp/admin/edit_educadores.jsp");
						return new ModelAndView(view2, "model", myModel);
						
					}else if(evento.equals("addalumnouser")){

						String stralumnos=request.getParameter("idalumno");
						int idUsuario=Integer.parseInt(request.getParameter("idUsuario"));
						String[] alumnos=stralumnos.split(",");

						int idCurso=Integer.parseInt(request.getParameter("idcurso"));
						for(int i=0;i<alumnos.length;i++){
							if(alumnos[i]!=null && !alumnos[i].equals(""))
								business.asignAlumnoToUsuario(Integer.parseInt(alumnos[i]), idUsuario, idCurso);
						}
						
						return new ModelAndView("//ajax", "model", myModel);
					}else if(evento.equals("reasign")){
						
						int ideducadororigen=Integer.parseInt(request.getParameter("ideducadororigen"));
						int ideducadorfin=Integer.parseInt(request.getParameter("ideducadorfin"));
						
						business.reasignAlumnoToUsuario(ideducadororigen, ideducadorfin);

						myModel.put("mensajeUsuario","Reasginación correcta");			

						return new ModelAndView("//ajax", "model", myModel);
					}else if(evento.equals("deleteasign")){
						//&idalumno
						int idalumno=Integer.parseInt(request.getParameter("idalumno"));
						int iduser=Integer.parseInt(request.getParameter("iduser"));
						
						//business.reasignAlumnoToUsuario(idalumno, iduser);
						business.deleteAsignaAlumnoUser(iduser, idalumno);
				
						myModel.put("mensajeUsuario","Reasginación correcta");			

						return new ModelAndView("//ajax", "model", myModel);
						
					}else if(evento.equals("delete")){
						
						int idUsuario=0;
						idUsuario=Integer.parseInt(request.getParameter("iduser"));
						
						boolean result=business.deleteUser(idUsuario);
						if(result)
								myModel.put("mensajeUsuario", "Borrado correcto");
						else
							myModel.put("mensajeUsuario", "Hubo un problema en el borrado");
							
						return new ModelAndView("//ajax", "model", myModel);
						
					}else if(evento.equals("save_edit")){
							
						int idUser=Integer.parseInt(""+request.getParameter("idUser"));
						String nombre=new String((request.getParameter("txtName")).getBytes("ISO-8859-1"),"UTF-8");
						String txtApellidos=new String(request.getParameter("txtApellidos").getBytes("ISO-8859-1"),"UTF-8");
						String correo=request.getParameter("txtEmail");
						String password=request.getParameter("pwd1");
						String usuario=request.getParameter("txtUser");
						
						Usuarios user=null;
						user=business.findUser(idUser);
						
						user.setApellidos(txtApellidos);
						user.setNombre(nombre);
						user.setEmail(correo);
						user.setUser(usuario);
						user.setIsadmin(false);
						if(password!=null && !password.equals(""))
							user.setPassword(SecurityUtils.getMd5Data(password));
						else
							throw new Exception("Error en password");

						business.editUser(user);
							
						return new ModelAndView("//ajax", "model", myModel);

					}else if(evento.equals("save")){
						
						String nombre=new String((request.getParameter("txtName")).getBytes("ISO-8859-1"),"UTF-8");
						String txtApellidos=new String(request.getParameter("txtApellidos").getBytes("ISO-8859-1"),"UTF-8");
						String correo=request.getParameter("txtEmail");
						String usuario=request.getParameter("txtUser");
						String password=request.getParameter("pwd1");
						Usuarios user=new Usuarios();
						
						user.setApellidos(txtApellidos);
						user.setNombre(nombre);
						user.setEmail(correo);
						user.setUser(usuario);
						user.setIsadmin(false);
						if(password!=null && !password.equals(""))
							user.setPassword(SecurityUtils.getMd5Data(password));
						else
							throw new Exception("Error en password");
						
						boolean result=business.addUser(user);

						//business.addUser(user);

						return new ModelAndView("//ajax", "model", myModel);
						
					}else if(evento.equals("educador")){
						
						String ideducador=""+request.getParameter("ideducador");
						String nameeducador=""+request.getParameter("nameeducador");
						myModel.put("ideducador", ideducador);
						myModel.put("nameeducador", nameeducador);
						
					}else if(evento.equals("cursouser")){
						
						String ideducador=""+request.getParameter("ideducador");
						String nameeducador=""+request.getParameter("nameeducador");
						myModel.put("ideducador", ideducador);
						myModel.put("nameeducador", nameeducador);
						List<Alumnos> alumnos=business.getAlumnos(Integer.parseInt(request.getParameter("idcurso")));
						String data=getComboFromValores(alumnos, "cmbAlumnosPorCurso", null, "");;
						myModel.put("cmbAlumnosPorCurso", data);
						return new ModelAndView("//ajax", "model", myModel);
						
					}else if(evento.equals("cursouser2")){
						
						String ideducador=""+request.getParameter("ideducador");
						String nameeducador=""+request.getParameter("nameeducador");
						myModel.put("ideducador", ideducador);
						myModel.put("nameeducador", nameeducador);
						List<Alumnos> alumnos=business.getAlumnos(Integer.parseInt(request.getParameter("idcurso")));
						String data=getComboFromValores(alumnos, "cmbAlumnosPorCurso2", null, "");;
						data=data + getBottom();			
						myModel.put("cmbAlumnosPorCurso2", data);
						return new ModelAndView("//ajax", "model", myModel);
						
					}else if(evento.equals("promo")){
						
						String ideducador=""+request.getParameter("ideducador");
						String nameeducador=""+request.getParameter("nameeducador");
						myModel.put("ideducador", ideducador);
						myModel.put("nameeducador", nameeducador);
						//List<Alumnos> alumnos=business.getAlumnos(Integer.parseInt(request.getParameter("idcurso")));
						//String data=getComboFromValores(alumnos, "cmbAlumnosPorCurso2", null, "");;
						String data= getBottom();			
						myModel.put("divBotonPromo", data);
						return new ModelAndView("//ajax", "model", myModel);
					}
					
				}
				List<Cursos> cursos=getCursosBeans();
				for(Cursos course:cursos){
					namecursos.put(course.getIdcurso(), course.getNombre());
					
				}
				myModel.put("cursos",cursos);
				List<Usuarios> educadores=getEducadoresBeans();
				myModel.put("educadores",educadores);								

				view2=new InternalResourceView("jsp/admin/alumnos_educadores.jsp");
				return new ModelAndView(view2, "model", myModel);
				
		    		
			}catch(Exception e){
				e.printStackTrace();
				return returnErrorControl(myModel, e);
			}finally{
				//este codigo queremos que se ejecute si o si
				
				
			}
		}
			
		public String getComboFromValores(List<Alumnos> listaValores,String name,String idSelected,String functionChange){
				String dato="<select width='500' style='width:500px' multiple size='10' class='cselect' id='"+name+"' name='"+name+"'>";
				if(listaValores==null){
				
					dato=dato+"</select>";
					return dato;
				}
				dato=dato+"<option value=''><a href='#'>Seleccione uno o varios registros...";
				dato=dato+"</a></option>";
				for(int i=0;i<listaValores.size();i++){
					
					if(idSelected!=null && !idSelected.equals("") && idSelected.equals(""+listaValores.get(i).getIdalumno())){
						dato=dato+"<option selected value='"+listaValores.get(i).getIdalumno()+"'>"+(listaValores.get(i).getNombre())+" "+(listaValores.get(i).getApellidos());
					}else{
						dato=dato+"<option value='"+listaValores.get(i).getIdalumno()+"'>"+(listaValores.get(i).getNombre())+" "+(listaValores.get(i).getApellidos());
					}
					dato=dato+"</option>";
				}
				dato=dato+"</select>";
				return dato;
				
				
		}
		public String getOnlineJsonDataAlumnos(List<Alumnos> alumnos) throws Exception{
			if(alumnos==null || alumnos.size()==0)return "";
			if(namecursos==null || namecursos.size()==0){
				List<Cursos> cursos=getCursosBeans();
				for(Cursos course:cursos){
					namecursos.put(course.getIdcurso(), course.getNombre());
					
				}
			}
			com.dynamicdroides.vdc.services.beans.JSONBean bean=new com.dynamicdroides.vdc.services.beans.JSONBean();
			AlumnoBean beans[]=new AlumnoBean[alumnos.size()];
			AlumnoBean alumno=null;
			int i=0;
			for(Alumnos alu:alumnos){
				alumno=new AlumnoBean();
				alumno.setApellidos(alu.getApellidos());
				alumno.setCorreopadres(alu.getCorreopadres());
				alumno.setCurso(namecursos.get(Integer.parseInt(alu.getCurso())));
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

		public String getOnlineJsonData(List<Usuarios> users) throws Exception{
			if(namecursos==null || namecursos.size()==0){
				List<Cursos> cursos=getCursosBeans();
				for(Cursos course:cursos){
					namecursos.put(course.getIdcurso(), course.getNombre());
					
				}
			}
			com.dynamicdroides.vdc.services.beans.JSONBean bean=new com.dynamicdroides.vdc.services.beans.JSONBean();
			UserBean beans[]=new UserBean[users.size()];
			UserBean usuario=null;
			int i=0;
			for(Usuarios usu:users){
				usuario=new UserBean();
				usuario.setNombre(usu.getNombre());
				usuario.setApellidos(usu.getApellidos());
				usuario.setEmail(usu.getEmail());
				usuario.setIduser(usu.getIduser());
				usuario.setIsadmin(usu.getIsadmin());
				usuario.setUser(usu.getUser());
				beans[i]=usuario;
				      i++;
			}
			
			bean.setRows(beans);
			bean.setPage(1);
			bean.setRecords(users.size());
			bean.setTotal(users.size());

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
