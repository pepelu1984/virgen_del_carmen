package com.dynamicdroides.virgendelcarmen.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.hibernate.Session;
import org.hibernate.Transaction;

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
import com.dynamicdroides.services.tools.SpringApplicationContext;


public class VirgenDelCarmenManager {

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
	public ArrayList<Cursos> getCursos(int iduser,String user,String password){
		if(!checkUser(user,password))return null;
		CursosDAO dao=(CursosDAO)SpringApplicationContext.getBean("cursosDAO");
		ArrayList<Cursos> lista=new ArrayList();
		
		Session s=dao.getSessionFactory().getCurrentSession();
		Transaction t=s.beginTransaction();
		List elements=s.createQuery("select c FROM Cursos c,Usercursos uscur where c.idcurso = uscur.idcurso AND uscur.iduser="+iduser+"").list();
		t.commit();
		
		Cursos instance=new Cursos();
		List<Cursos> lstCursos=dao.findByExample(instance);
		Cursos bean=new Cursos();
			for(Cursos curso:lstCursos){
				bean=new Cursos();
				bean.setIdcurso(curso.getIdcurso());
				bean.setNombre(curso.getNombre());
				bean.setResponsable(curso.getResponsable());
				lista.add(bean);
			}
					
			try {
				JAXBContext context = JAXBContext.newInstance(Cursos.class);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return lista;
	}
	public ArrayList<Alumnos> getAlumnos(int idCurso,String user,String password){
		if(!checkUser(user,password))return null;
		AlumnosDAO dao=(AlumnosDAO)SpringApplicationContext.getBean("alumnosDAO");
		ArrayList<Alumnos> lista=new ArrayList();
		Cursos curso=new Cursos();
		curso.setIdcurso(idCurso);
		ArrayList<Alumnos> lstAlumnos=(ArrayList)dao.findByProperty("curso", ""+idCurso);
		
		return lstAlumnos;
	
	}
	
	public boolean addComportamiento(String tipo, String valor,int idalumno,Date fechadesde,Date fechahasta,String user,String password){
		return addComportamiento1(tipo, valor,idalumno,fechadesde,fechahasta,user,password,"");
	}
	public boolean addComportamiento1(String tipo, String valor,int idalumno,Date fechadesde,Date fechahasta,String user,String password,String observaciones){
		if(!checkUser(user,password))return false;
		RegComportamientoDAO regdao=(RegComportamientoDAO)SpringApplicationContext.getBean("regComportamientoDAO");
		
		RegComportamiento comportamiento=new RegComportamiento();
		comportamiento.setIdalumno(idalumno);
		comportamiento.setFechadesde(fechadesde);
		comportamiento.setFechahasta(fechahasta);
		comportamiento.setTipo(tipo);
		comportamiento.setValor(valor);
		
		regdao.save(comportamiento);
		
		return true;
		
	}
	public boolean addComidas(String tipo, String valor,int idalumno,Date fecha,String user,String password){
		return addComidas1(tipo, valor,idalumno,fecha,user,password,"");
	}

	public boolean addComidas1(String tipo, String valor,int idalumno,Date fecha,String user,String password,String observaciones){
		if(!checkUser(user,password))return false;
		RegComidasDAO regdao=(RegComidasDAO)SpringApplicationContext.getBean("regComidasDAO");
		
		RegComidas comida=new RegComidas();
		comida.setFecha(fecha);
		comida.setIdalumno(idalumno);
		comida.setTipocomida(tipo);
		comida.setValor(valor);
		regdao.save(comida);
		
		return true;
		
	}
	
	
	public boolean addAlumno(String nombre, String apellido1,String apellido2,String curso, String mail){
		AlumnosDAO alumnosdao=(AlumnosDAO)SpringApplicationContext.getBean("alumnosDAO");
		
		Alumnos alumno=new Alumnos();
		alumno.setNombre(nombre);
		alumno.setApellidos(apellido1 + " "+apellido2);
		alumno.setCurso(curso.toUpperCase());
		alumno.setCorreopadres(mail);
		alumnosdao.save(alumno);
		return true;
		
	}
	
	public List getCursos(){
		CursosDAO cursosdao=(CursosDAO)SpringApplicationContext.getBean("cursosDAO");
		
		return cursosdao.findAll();
		
	}
	public int addCurso(String curso){
		
		CursosDAO cursosdao=(CursosDAO)SpringApplicationContext.getBean("cursosDAO");
		Cursos cursoBean=new Cursos();
		cursoBean.setNombre(curso);
		int idcurso=cursosdao.save(cursoBean);
		return idcurso;
	}
}
