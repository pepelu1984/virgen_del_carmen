package com.dynamicdroides.vdc.services.business;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.cache.annotation.Cacheable;

import com.dynamicdroides.db.virgendelcarmen.Alumnos;
import com.dynamicdroides.db.virgendelcarmen.AlumnosDAO;
import com.dynamicdroides.db.virgendelcarmen.Cursos;
import com.dynamicdroides.db.virgendelcarmen.CursosDAO;
import com.dynamicdroides.db.virgendelcarmen.RegComidas;
import com.dynamicdroides.db.virgendelcarmen.RegComidasDAO;
import com.dynamicdroides.db.virgendelcarmen.RegComportamiento;
import com.dynamicdroides.db.virgendelcarmen.RegComportamientoDAO;
import com.dynamicdroides.db.virgendelcarmen.Usuarios;
import com.dynamicdroides.db.virgendelcarmen.UsuariosDAO;
import com.dynamicdroides.db.virgendelcarmen.Usuariosalumnos;
import com.dynamicdroides.db.virgendelcarmen.UsuariosalumnosDAO;
import com.dynamicdroides.services.tools.SpringApplicationContext;
import com.dynamicdroides.tools.MailTool;

public class VirgenDelCarmenBusiness {

	private CursosDAO cursosDAO;
	private AlumnosDAO alumnosDAO;
	private RegComidasDAO comidasDAO;
	private RegComportamientoDAO comportamientoDAO;
	private UsuariosDAO usuarioDAO;
	private UsuariosalumnosDAO usuarioAlumnoDAO;
	
	private MailTool mailTool;
	public boolean sendMensajeGeneral(String mensaje,String subject,String listaDestinatarios){
		//TODO al final quitar esto
		//Esta lisytta, se supone que son IDs de alumno
		//el problema primero es que llega a NULL!!!!!!!!
		//segundo, no habias quitado la lista a pelo !!!!!!!!!!!!!!!
		
		
		MailTool mailTool=(MailTool)SpringApplicationContext.getBean("mailTool");
		
		String from="mail_comedor@colegiovirgendelcarmen.net";
		ArrayList<String> listaPAdres=new ArrayList<String>();
		String[] lstIdsAlumnos=listaDestinatarios.split(",");
		for(int i=0;i<lstIdsAlumnos.length;i++){
			Alumnos alumno=alumnosDAO.findById(Integer.parseInt(lstIdsAlumnos[i]));
			
			listaPAdres.add(alumno.getCorreopadres());
			
			
		}
		
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
		
		String to="";
		to=listaDestinatarios;
			
		//String cc=null;
		
		//String cc="felipe.escobar@gmail.com";
		//String cc="director@colegiovirgendelcarmen.net";
		//String bcc="director@colegiovirgendelcarmen.net";
		
		try {
			for(int x=0;x<listaPAdres.size();x++){
				//ME CAGO EN TODO, ten cuidado de mandar los correos a los padres 
				//aqui tienes que poner a pelo el correo nuestro.
				if(listaPAdres.get(x).indexOf(",")>-1){
					String lista[]=listaPAdres.get(x).split(",");
					for(int y=0;y<lista.length;y++){
						mailTool.sendMail(from, lista[y], subject, "<body>"+mensaje+"</body>");
						
					}
				}else{
					mailTool.sendMail(from, listaPAdres.get(x), subject, "<body>"+mensaje+"</body>");
					
				}
				//mailTool.sendMail(from, listaPAdres.get(x), subject, msg);
							
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
		
		
		
	}
	public boolean sendMail(String from,String to,String subject,String msg) throws UnsupportedEncodingException, MessagingException{
		
		mailTool.sendMail(from, to, subject, msg);
		return true;
		
	}
	
	public MailTool getMailTool() {
		return mailTool;
	}
	public void setMailTool(MailTool mailTool) {
		this.mailTool = mailTool;
	}
	public UsuariosalumnosDAO getUsuarioAlumnoDAO() {
		return usuarioAlumnoDAO;
	}
	public void setUsuarioAlumnoDAO(UsuariosalumnosDAO usuarioAlumnoDAO) {
		this.usuarioAlumnoDAO = usuarioAlumnoDAO;
	}
	public UsuariosDAO getUsuarioDAO() {
		return usuarioDAO;
	}
	public void setUsuarioDAO(UsuariosDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	public RegComidasDAO getComidasDAO() {
		return comidasDAO;
	}
	public void setComidasDAO(RegComidasDAO comidasDAO) {
		this.comidasDAO = comidasDAO;
	}
	public RegComportamientoDAO getComportamientoDAO() {
		return comportamientoDAO;
	}
	public void setComportamientoDAO(RegComportamientoDAO comportamientoDAO) {
		this.comportamientoDAO = comportamientoDAO;
	}
	public AlumnosDAO getAlumnosDAO() {
		return alumnosDAO;
	}
	public void setAlumnosDAO(AlumnosDAO alumnosDAO) {
		this.alumnosDAO = alumnosDAO;
	}
	public CursosDAO getCursosDAO() {
		return cursosDAO;
	}
	public void setCursosDAO(CursosDAO cursosDAO) {
		this.cursosDAO = cursosDAO;
	}
	public List<RegComidas> getComidas(int idAlumno){
		return comidasDAO.findByIdalumno(idAlumno);
	}
	public List<RegComportamiento> getComportamientos(int idAlumno){
		Session s=alumnosDAO.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction t=s.beginTransaction();
		List<RegComportamiento> elements=s.createQuery("select model from RegComportamiento model where model.idalumno="+idAlumno+" ORDER BY model.fechadesde ASC").list();
		t.commit();
		if(elements.size()>0){
			return elements;
		}else{
			return null;
		}
	
		//return comportamientoDAO.findByIdalumno(idAlumno);
		
	}
	public List<RegComportamiento> getComportamientos(int idAlumno,Date fechaInicio,Date fechaFin){
		Session s=alumnosDAO.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction t=s.beginTransaction();
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
		
		List<RegComportamiento> elements=null;
		
		Query query=s.createQuery("select model from RegComportamiento model where model.idalumno="+idAlumno+" where model.fechadesde=:dateFrom AND model.fechahasta=:dateTo ORDER BY model.fechadesde ASC");
		query.setParameter("dateFrom", fechaInicio.getTime());
		query.setParameter("dateTo", fechaFin.getTime());
		elements=query.list();
		t.commit();
		if(elements.size()>0){
			return elements;
		}else{
			return null;
		}
	
		//return comportamientoDAO.findByIdalumno(idAlumno);
		
	}
	public boolean promoteCurso(int idCursoOrigen,int idCursoDestino){
		String strLstAlumnos="";
		List<Alumnos> lstAlumnos= getAlumnosDAO().findByProperty("curso", idCursoOrigen);
		for(Alumnos alu:lstAlumnos){
			if(strLstAlumnos.equals(""))
				strLstAlumnos=""+alu.getIdalumno();
			else
				strLstAlumnos=strLstAlumnos+","+alu.getIdalumno();
				
		}
		Session s=getAlumnosDAO().getHibernateTemplate().getSessionFactory().getCurrentSession();
		//strLstAlumnos="13,17";
		/** Emilio; comentado hasta que confirmemos que la info es correcta
		*/
		Transaction t=s.beginTransaction();
		//Query queryDelete=s.createSQLQuery("delete from Alumnos where CURSO="+idCursoDestino+"");
		Query query=s.createSQLQuery("update ALUMNOS set CURSO='"+idCursoDestino+"' where IDALUMNO in("+strLstAlumnos+")");
							
		int nrows=query.executeUpdate();
		if(nrows==0)return false;
		
		t.commit();
		return true;
	}
	
	public List<Usuarios> getUsuarios(){
		return usuarioDAO.findAll();
	}
	public boolean deleteUser(int idusuario){
		Usuarios instance=new Usuarios();
		instance.setIduser(idusuario);
		Usuarios user=(Usuarios)usuarioDAO.findById(idusuario);
		Transaction t=usuarioDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		
		usuarioDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().delete(user);
		t.commit();
		return true;
	}
	public boolean deleteAsignaAlumnoUser(int idusuario, int idalumno){
		Usuariosalumnos instance=new Usuariosalumnos();
		instance.setIdalumno(idalumno);
		instance.setIdusuario(idusuario);
		List<Usuariosalumnos> usuarios= usuarioAlumnoDAO.findByExample(instance);
		if(usuarios.size()>0){
			for(Usuariosalumnos alu:usuarios){
				Transaction t=usuarioAlumnoDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
				usuarioAlumnoDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().delete(alu);
				t.commit();
			}
		}else{
			return false;
		}
		return true;
	}
	public boolean asignAlumnoToUsuario(int idAlumno,int idUsuario,int idCurso){
		Usuariosalumnos userAlumno=new Usuariosalumnos();
		userAlumno.setIdalumno(idAlumno);
		userAlumno.setIdusuario(idUsuario);
		userAlumno.setIdcurso(idCurso);
		List results=usuarioAlumnoDAO.findByExample(userAlumno);
		if(results.size()==0)
			usuarioAlumnoDAO.save(userAlumno);
		return true;
	}
	public boolean reasignAlumnoToUsuario(int idUsuario,int idUsuarioDestino){
		Usuariosalumnos userAlumno=new Usuariosalumnos();
		userAlumno.setIdusuario(idUsuario);
		List<Usuariosalumnos> results=usuarioAlumnoDAO.findByExample(userAlumno);
		
		if(results.size()>0){
			for(Usuariosalumnos usualu:results){
				usualu.setIdusuario(idUsuarioDestino);
				Transaction t=usuarioAlumnoDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
				usuarioAlumnoDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().update(usualu);
				t.commit();
			}
			
		}
		
		return true;
	}
	public boolean deleteAlumnoToUsuario(int idUsuario,int idalumno){
		Usuariosalumnos userAlumno=new Usuariosalumnos();
		userAlumno.setIdusuario(idUsuario);
		userAlumno.setIdalumno(idalumno);
		List<Usuariosalumnos> results=usuarioAlumnoDAO.findByExample(userAlumno);
		
		if(results.size()>0){
			for(Usuariosalumnos usualu:results){
				Transaction t=usuarioAlumnoDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
				usuarioAlumnoDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().delete(usualu);
				t.commit();
			}
			
		}
		
		return true;
	}
	
	public List<Alumnos> findAlumnosByUser(int iduser){
		Session s=alumnosDAO.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction t=s.beginTransaction();
		List<Alumnos> elements=s.createQuery("select model from Alumnos model,Usuariosalumnos useralu where useralu.idusuario="+iduser+" AND model.idalumno=useralu.idalumno").list();
		try{
			t.rollback();
		}catch(Exception e){
			
		}
		if(elements.size()>0){
			return elements;
		}else{
			return null;
		}
		
	}
	@Cacheable(value="profesores.data")
	public List<Usuarios> findUsers(){
		Session s=alumnosDAO.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction t=s.beginTransaction();
		List<Usuarios> elements=s.createQuery("select model from Usuarios model").list();
		try{
			t.rollback();
		}catch(Exception e){
			
		}
		if(elements.size()>0){
			return elements;
		}else{
			return null;
		}
		
	}
	public Usuarios findUser(int idUser){
		Session s=usuarioDAO.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction t=s.beginTransaction();
		List<Usuarios> elements=s.createQuery("select model from Usuarios model where model.iduser="+idUser).list();
		try{
			t.rollback();
		}catch(Exception e){
			
		}
		if(elements.size()>0){
			return elements.get(0);
		}else{
			return null;
		}
		
	}
	public List<RegComportamiento> findComportamientosByAlumno(int idalumno,Date lunes,Date domingo){
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
		String desde=format.format(lunes);
		String hasta=format.format(domingo);
		
		Session s=alumnosDAO.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction t=s.beginTransaction();
		List<RegComportamiento> elements=s.createQuery("select model from RegComportamiento model where model.idalumno="+idalumno+" AND model.fechadesde='"+desde+"' AND model.fechahasta='"+hasta+"'").list();
		try{
			t.rollback();
		}catch(Exception e){
			
		}
		if(elements.size()>0){
			return elements;
		}else{
			return null;
		}
		
	}
	public Alumnos findAlumno(int idAlumno){
		Session s=alumnosDAO.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction t=s.beginTransaction();
		List elements=s.createQuery("from Alumnos as model where model.idalumno="+idAlumno).list();
		try{
			t.rollback();
		}catch(Exception e){
			
		}
		if(elements.size()>0){
			return (Alumnos)elements.get(0);
		}else{
			return null;
		}
		
	}
	public boolean editAlumno(Alumnos alumno){
		Session s=alumnosDAO.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction t=s.beginTransaction();
		alumnosDAO.getHibernateTemplate().update(alumno);
		try{
			t.commit();
		}catch(Exception e){
			
		}
		
		return true;
		
	}
	public boolean editUser(Usuarios user){
		Session s=usuarioDAO.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction t=s.beginTransaction();
		
		usuarioDAO.getHibernateTemplate().update(user);
		
		try{
			t.commit();
		}catch(Exception e){
			
		}
		
		return true;
		
	}
	
	public boolean addAlumno(Alumnos alumno){
		Transaction t=alumnosDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		alumnosDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().save(alumno);
		try{
			t.commit();
		}catch(Exception e){
			
		}
		return true;
		
	}
	public boolean addUser(Usuarios user){
		Transaction t=usuarioDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		usuarioDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().save(user);
		try{
			t.commit();
		}catch(Exception e){
			
		}
		
		return true;
		
	}
	
	public boolean addAlumno(String nombre, String apellido1,String apellido2,String curso, String mail){
	
		Alumnos alumno=new Alumnos();
		alumno.setNombre(nombre);
		alumno.setApellidos(apellido1 + " "+apellido2);
		alumno.setCurso(curso.toUpperCase());
		alumno.setCorreopadres(mail);
		
		return addAlumno(alumno);
		
	}
	public boolean checkUser(String user,String password){
		
		UsuariosDAO usersdao=(UsuariosDAO)SpringApplicationContext.getBean("usuariosDAO");
		
		Usuarios instance=new Usuarios();
		instance.setUser(user);
		instance.setPassword(password);
		List<Usuarios> users=usersdao.findByExample(instance);
		if(users.size()>0){
			return true;
		}
		return false;
	}
	public Usuarios checkUserAccess(String user,String password){
		
		UsuariosDAO usersdao=(UsuariosDAO)SpringApplicationContext.getBean("usuariosDAO");
		
		Usuarios instance=new Usuarios();
		instance.setUser(user);
		instance.setPassword(password);
		List<Usuarios> users=usersdao.findByExample(instance);
		if(users.size()>0){
			return users.get(0);
		}
		return null;
	}

	
	public List<Cursos> getCursos(){
		return CursosHelper.getAllCursos();
	}
	public List<Alumnos> getAlumnos(){
		return alumnosDAO.findAll();
	}
	@Cacheable(value="alumnos.data",key="#idCurso")
	public ArrayList<Alumnos> getAlumnos(int idCurso){
		AlumnosDAO dao=(AlumnosDAO)SpringApplicationContext.getBean("alumnosDAO");
		ArrayList<Alumnos> lista=new ArrayList();
		Cursos curso=new Cursos();
		curso.setIdcurso(idCurso);
		List<Alumnos> lstAlumnos=dao.findByProperty("curso", ""+idCurso);
		Alumnos bean=new Alumnos();
			for(Alumnos alumno:lstAlumnos){
				bean=new Alumnos();
				bean.setApellidos(alumno.getApellidos());
				bean.setCurso(alumno.getCurso());
				bean.setIdalumno(alumno.getIdalumno());
				bean.setNombre(alumno.getNombre());
				bean.setComedor(alumno.getComedor());
				bean.setCorreopadres(alumno.getCorreopadres());
				bean.setListaalergias(alumno.getListaalergias());
				lista.add(bean);
			}
					
		lstAlumnos=null;	
		return lista;
	
	}
	@Cacheable(value="alumnos.data",key="#idCurso,#iduser,#user")
	public ArrayList<Alumnos> getAlumnosByUser(int idCurso,int idUser,String user){
		AlumnosDAO dao=(AlumnosDAO)SpringApplicationContext.getBean("alumnosDAO");
		ArrayList<Alumnos> lista=new ArrayList();
		Cursos curso=new Cursos();
		curso.setIdcurso(idCurso);
		Alumnos instance=new Alumnos();
		instance.setCurso(idCurso+"");
		
		List<Alumnos> lstAlumnos=dao.findByExample(instance);
		
		Alumnos bean=new Alumnos();
			for(Alumnos alumno:lstAlumnos){
				bean=new Alumnos();
				bean.setApellidos(alumno.getApellidos());
				bean.setCurso(alumno.getCurso());
				bean.setIdalumno(alumno.getIdalumno());
				bean.setNombre(alumno.getNombre());
				lista.add(bean);
			}
					
		lstAlumnos=null;	
		return lista;
	
	}
	
}
