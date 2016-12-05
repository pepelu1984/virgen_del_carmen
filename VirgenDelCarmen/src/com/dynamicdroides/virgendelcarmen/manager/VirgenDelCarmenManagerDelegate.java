	package com.dynamicdroides.virgendelcarmen.manager;

	import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.dynamicdroides.db.virgendelcarmen.AlumnoBean;
import com.dynamicdroides.db.virgendelcarmen.Alumnos;
import com.dynamicdroides.db.virgendelcarmen.CursoBean;
import com.dynamicdroides.db.virgendelcarmen.Cursos;
import com.dynamicdroides.db.virgendelcarmen.Observaciones;
import com.dynamicdroides.db.virgendelcarmen.Profesores;
import com.dynamicdroides.db.virgendelcarmen.Usuarios;

@javax.jws.WebService(
targetNamespace = 
	"http://manager.virgendelcarmen.dynamicdroides.com/"
,
serviceName = 
	"VirgenDelCarmenManagerService"
, 
portName =
	"VirgenDelCarmenManagerPort"
	,wsdlLocation = "WEB-INF/wsdl/VirgenDelCarmenManagerService.wsdl"
)


@XmlSeeAlso({Alumnos.class,AlumnoBean.class,Cursos.class,CursoBean.class,Usuarios.class,Profesores.class,Observaciones.class})
public class VirgenDelCarmenManagerDelegate {

	com.dynamicdroides.virgendelcarmen.manager.VirgenDelCarmenManager virgenDelCarmenManager = new com.dynamicdroides.virgendelcarmen.manager.VirgenDelCarmenManager();

			public void finishEnvioAsistencia(String lstAlumnosIds2, String lstProfesoresIds2, Date fecha)  {		
			virgenDelCarmenManager.finishEnvioAsistencia(lstAlumnosIds2,lstProfesoresIds2,fecha);
		}
				public List<Profesores> getProfesores(String admin, String password)  {		
			return virgenDelCarmenManager.getProfesores(admin,password);
		}
				public void finishEnvioComportamiento(int idalumno, Date fechaInicio, Date fechaFin) throws IOException {		
			virgenDelCarmenManager.finishEnvioComportamiento(idalumno,fechaInicio,fechaFin);
		}
				public void finishEnvioComidas(int idalumno, Date fechaComienzo, Date fechaInicio)  {		
			virgenDelCarmenManager.finishEnvioComidas(idalumno,fechaComienzo,fechaInicio);
		}
				public Usuarios checkUserAccess(String user, String password)  {		
			return virgenDelCarmenManager.checkUserAccess(user,password);
		}
				public boolean checkUser(String user, String password)  {		
			return virgenDelCarmenManager.checkUser(user,password);
		}
				public Usuarios checkAndGetUser(String user, String password, boolean isAdmin)  {		
			return virgenDelCarmenManager.checkAndGetUser(user,password,isAdmin);
		}
				public ArrayList getAlumnosByDate(int idCurso, String user, String password, Date fechaInicio, Date fechaFin)  {		
			return virgenDelCarmenManager.getAlumnosByDate(idCurso,user,password,fechaInicio,fechaFin);
		}
				public ArrayList getAlumnosForComedor(String user, String password, Date fechaInicio, Date fechaFin)  {		
			return virgenDelCarmenManager.getAlumnosForComedor(user,password,fechaInicio,fechaFin);
		}
				public ArrayList getAlumnosBy(String user, String password, Date fechaInicio, Date fechaFin)  {		
			return virgenDelCarmenManager.getAlumnosBy(user,password,fechaInicio,fechaFin);
		}
				public ArrayList getAlumnosByCurso(int idcurso, String user, String password, Date fechaInicio, Date fechaFin)  {		
			return virgenDelCarmenManager.getAlumnosByCurso(idcurso,user,password,fechaInicio,fechaFin);
		}
				public AlumnoBean getAluBeanFromAlumno(Alumnos al2, Date fechaInicio, Date fechaFin, boolean hasComportamiento, String comportamientos)  {		
			return virgenDelCarmenManager.getAluBeanFromAlumno(al2,fechaInicio,fechaFin,hasComportamiento,comportamientos);
		}
				public ArrayList getAlumnosAsisten(String user, String password, Date fecha, Date fechaFin)  {		
			return virgenDelCarmenManager.getAlumnosAsisten(user,password,fecha,fechaFin);
		}
				public ArrayList getAlumnos(int idCurso, String user, String password)  {		
			return virgenDelCarmenManager.getAlumnos(idCurso,user,password);
		}
				public List getObservaciones(String user, String password)  {		
			return virgenDelCarmenManager.getObservaciones(user,password);
		}
				public List getCurso(int iduser, String user, String password)  {		
			return virgenDelCarmenManager.getCurso(iduser,user,password);
		}
				public boolean addComportamiento(String tipo, String valor, int idalumno, Date fechadesde, Date fechahasta, String user, String password)  {		
			return virgenDelCarmenManager.addComportamiento(tipo,valor,idalumno,fechadesde,fechahasta,user,password);
		}
				public boolean addComportamiento1(String tipo, String valor, int idalumno, Date fechadesde, Date fechahasta, String user, String password, String observaciones)  {		
			return virgenDelCarmenManager.addComportamiento1(tipo,valor,idalumno,fechadesde,fechahasta,user,password,observaciones);
		}
				public boolean addComidas(String tipo, String valor, int idalumno, Date fecha, String user, String password)  {		
			return virgenDelCarmenManager.addComidas(tipo,valor,idalumno,fecha,user,password);
		}
				public boolean addComidas1(String tipo, String valor, int idalumno, Date fecha, String user, String password, String observaciones)  {		
			return virgenDelCarmenManager.addComidas1(tipo,valor,idalumno,fecha,user,password,observaciones);
		}
				public boolean addAlumno(String nombre, String apellido1, String apellido2, String curso, String mail)  {		
			return virgenDelCarmenManager.addAlumno(nombre,apellido1,apellido2,curso,mail);
		}
				public boolean isDataComidasFilled(int idAlumno, Date fechaActualiza, String user, String password)  {		
			return virgenDelCarmenManager.isDataComidasFilled(idAlumno,fechaActualiza,user,password);
		}
				public boolean isDataComportamientoFilled(int idAlumno, Date fechaActualiza, String user, String password)  {		
			return virgenDelCarmenManager.isDataComportamientoFilled(idAlumno,fechaActualiza,user,password);
		}
				public List getCursos()  {		
			return virgenDelCarmenManager.getCursos();
		}
				public int addCurso(String curso)  {		
			return virgenDelCarmenManager.addCurso(curso);
		}
	
}