package com.dynamicdroides.virgendelcarmen.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dynamicdroides.db.virgendelcarmen.AlumnoBean;
import com.dynamicdroides.db.virgendelcarmen.Alumnos;
import com.dynamicdroides.db.virgendelcarmen.AlumnosDAO;
import com.dynamicdroides.db.virgendelcarmen.CursoBean;
import com.dynamicdroides.db.virgendelcarmen.Cursos;
import com.dynamicdroides.db.virgendelcarmen.CursosDAO;
import com.dynamicdroides.db.virgendelcarmen.Menu;
import com.dynamicdroides.db.virgendelcarmen.MenuDAO;
import com.dynamicdroides.db.virgendelcarmen.ObservacionesDAO;
import com.dynamicdroides.db.virgendelcarmen.Profesores;
import com.dynamicdroides.db.virgendelcarmen.ProfesoresDAO;
import com.dynamicdroides.db.virgendelcarmen.RegAsistencia;
import com.dynamicdroides.db.virgendelcarmen.RegAsistenciaDAO;
import com.dynamicdroides.db.virgendelcarmen.RegComidas;
import com.dynamicdroides.db.virgendelcarmen.RegComidasDAO;
import com.dynamicdroides.db.virgendelcarmen.RegComportamiento;
import com.dynamicdroides.db.virgendelcarmen.RegComportamientoDAO;
import com.dynamicdroides.db.virgendelcarmen.Usuarios;
import com.dynamicdroides.db.virgendelcarmen.UsuariosDAO;
import com.dynamicdroides.party.standalone.SpringApplicationContext;
import com.dynamicdroides.tools.MailTool;

/**
 * 
 * @author Pipe
 *
 */
public class VirgenDelCarmenManager {

	private final String path_app="http://dynamicdroides.com:8080/VirgenDelCarmen/";

	
	public void finishEnvioAsistencia(String lstAlumnosIds2,String lstProfesoresIds2,Date fecha){

	    RegAsistenciaDAO asistenciaDAO = (RegAsistenciaDAO)SpringApplicationContext.getBean("regAsistenciaDAO");
	    AlumnosDAO aluDAO = (AlumnosDAO)SpringApplicationContext.getBean("alumnosDAO");
	    CursosDAO cursosDAO = (CursosDAO)SpringApplicationContext.getBean("cursosDAO");
	    AlumnosBusiness alumnosBusiness=(AlumnosBusiness)SpringApplicationContext.getBean("alumnosBusiness");
	    
	    int nasistentes = 0;
	    
	    List<String> lstAlumnosIds = new ArrayList();
	    List<String> lstProfesoresIds = new ArrayList();
	    
	    String[] arrAlu = lstAlumnosIds2.split(",");
	    HashMap<String, Integer> cursosNumeroAlumnos = new HashMap();
	    //Alumnos alu = null;
	    
	    String totalAlergias = "Listado de alergias:";
	    Transaction t2 = null;
	    		
		  String lstIDS="";
	    for (int i = 0; i < arrAlu.length; i++){
	    	if(lstIDS.equals("")){
	    		lstIDS=arrAlu[i];
	    	}else{
	    		lstIDS=lstIDS+","+arrAlu[i];
	    		
	    	}
		}
	    /*
        t2 = asistenciaDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
	    List<Alumnos> lstAlumnos=aluDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select distinct alumnos from Alumnos alumnos where alumnos.idalumno in("+lstIDS+")").list();
	    t2.commit();
	    */
	    List<Alumnos> lstAlumnos=alumnosBusiness.getAlumnosByList(lstIDS);
	    
	    
	    for (Alumnos alu:lstAlumnos)
	    {
	      if (cursosNumeroAlumnos.get(alu.getCurso()) != null)
	      {
	    	
	        if ((alu.getListaalergias() != null) && (!alu.getListaalergias().equals(""))) {
	          totalAlergias = totalAlergias + "<br/>" + alu.getNombre() + " " + alu.getApellidos() + "|" + "Curso:" + CursosHelper.getCourseName(Integer.parseInt(alu.getCurso())) + "|" + alu.getListaalergias();
	        }
	        Integer nAlumnosporCurso = (Integer)cursosNumeroAlumnos.get(alu.getCurso());
	        if(nAlumnosporCurso!=null){
	        	nAlumnosporCurso = Integer.valueOf(nAlumnosporCurso.intValue() + 1);
	        }else{
	        	nAlumnosporCurso = 1;
	        }
	        	
	        cursosNumeroAlumnos.put(alu.getCurso(), nAlumnosporCurso);
	      }
	      else
	      {
	        if ((alu.getListaalergias() != null) && (!alu.getListaalergias().equals(""))) {
	          totalAlergias = totalAlergias + "<br/>" + alu.getNombre() + " " + alu.getApellidos() + "|" + "Curso:" + CursosHelper.getCourseName(Integer.parseInt(alu.getCurso())) + "|" + alu.getListaalergias();
	        }
	        
	        Integer nAlumnosporCurso = Integer.valueOf(1);
	        cursosNumeroAlumnos.put(alu.getCurso(), nAlumnosporCurso);
	      }
	    }
	    
	    List<Cursos> cursos = CursosHelper.getAll();
	    
	    String totalAlumnosCurso = "";
	    for (Cursos curso : cursos) {
	      if (cursosNumeroAlumnos.get(""+curso.getIdcurso()) != null)
	      {
	        Integer numAlumnosCurso = (Integer)cursosNumeroAlumnos.get(""+curso.getIdcurso());
	        totalAlumnosCurso = totalAlumnosCurso + "<br/>" + "Curso:" + curso.getNombre() + ":" + numAlumnosCurso;
	      }
	    }
	    String[] arrProf = lstProfesoresIds2.split(",");
	    for (int i = 0; i < arrProf.length; i++) {
	      lstProfesoresIds.add(arrProf[i]);
	    }
	    RegAsistencia asistencia = new RegAsistencia();
	    if ((arrAlu != null) && (arrAlu.length > 0)) {
	      for (int i = 0; i < arrAlu.length; i++) {
	        if ((arrAlu[i] != null) && (!arrAlu[i].equals("")))
	        {
	          asistencia = new RegAsistencia();
	          asistencia.setFecha(fecha);
	          asistencia.setIsalumno(Boolean.valueOf(true));
	          asistencia.setIdalumno(Integer.valueOf(Integer.parseInt(arrAlu[i])));
	          Transaction t = null;
	          t = asistenciaDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
	          try
	          {
	            asistenciaDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().save(asistencia);
	            t.commit();
	          }
	          catch (Exception e)
	          {
	            t.rollback();
	          }
	        }
	      }

	    }
	    if ((lstProfesoresIds != null) && (lstProfesoresIds.size() > 0)) {
	      for (int i = 0; i < lstProfesoresIds.size(); i++) {
	        if ((lstProfesoresIds.get(i) != null) && (!((String)lstProfesoresIds.get(i)).equals("")))
	        {
	          asistencia = new RegAsistencia();
	          asistencia.setFecha(fecha);
	          asistencia.setIsalumno(Boolean.valueOf(false));
	          asistencia.setIdalumno(Integer.valueOf(Integer.parseInt((String)lstProfesoresIds.get(i))));
	          Transaction t = null;
	          t = asistenciaDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
	          try
	          {
	            asistenciaDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().save(asistencia);
	            t.commit();
	          }
	          catch (Exception e)
	          {
	            t.rollback();
	          }
	        }
	      }
	    }
        
	    MailTool mailTool = (MailTool)SpringApplicationContext.getBean("mailTool");
	    
	    String from = "mail_comedor@colegiovirgendelcarmen.net";
	    
	    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	    
	    String to = "";
	    String subject = "Listado de asistentes al comedor- Fecha:" + format.format(new Date());
	    

	    to = "cocinera@colegiovirgendelcarmen.net,encargadadecomedor@colegiovirgendelcarmen.net,coordinadoraeducadoras@colegiovirgendelcarmen.net";
	    
	    String msg = "<body>";
	    msg = msg + 
	      "<br/><h1>Fecha:" + format.format(new Date()) + "</h1><br/>" + 
	      "<h3>Numero de alumnos:" + lstAlumnos.size() + 
	      "<br/>" + totalAlumnosCurso + 
	      "<br/>" + totalAlergias + 
	      "</h3>" + 
	      "<h3>Numero de profesores:" + lstProfesoresIds.size() + 
	      "</h3>" + 
	      "</body>";
	    


	    String cc = "director@colegiovirgendelcarmen.net";
	    try
	    {
	      mailTool.sendMail(from, to, subject, msg, cc, null);
	    }
	    catch (UnsupportedEncodingException e)
	    {
	      e.printStackTrace();
	    }
	    catch (MessagingException e)
	    {
	      e.printStackTrace();
	    }
		
	}
	public List<Profesores> getProfesores(String admin,String password){
		if(!checkUser(admin,password))return null;
		
		ProfesoresDAO profesoresDAO=(ProfesoresDAO)SpringApplicationContext.getBean("profesoresDAO");
		
		return profesoresDAO.findAll();
		
	}
	
	
	@SuppressWarnings("deprecation")
	public void finishEnvioComportamiento(int idalumno,Date fechaInicio,Date fechaFin) throws IOException{
		//System.out.println("Finish:"+idalumno+"-------------------------fechaInicio:"+fechaInicio);
		System.out.println("Finish:"+idalumno+"-------------------------fechaFin:"+fechaFin);
		
		Calendar cal=Calendar.getInstance();
		Calendar cal2=Calendar.getInstance();
		cal.setTime(fechaInicio);
		cal.set(Calendar.HOUR, 0);
		cal2.setTime(fechaFin);
		cal2.set(Calendar.AM_PM,0);
		cal2.set(Calendar.HOUR, 0);
		cal2.set(Calendar.MINUTE, 0);
		cal2.set(Calendar.MILLISECOND, 0);
		cal2.set(Calendar.SECOND, 0);
			
		System.out.println("Finisrrh:"+idalumno+"-------------------------fechaInicio:"+cal.getTime());
		System.out.println("Finish:"+idalumno+"-------------------------fechaFin:"+cal2.getTime());

		RegComportamientoDAO daoComportamiento=(RegComportamientoDAO)SpringApplicationContext.getBean("regComportamientoDAO");
		
		AlumnosDAO daoAlumnos=(AlumnosDAO)SpringApplicationContext.getBean("alumnosDAO");
		Alumnos alumnos=daoAlumnos.findById(idalumno);
		
		boolean isWeekly=false;

		if(CursosHelper.isCourseSemanal(Integer.parseInt(alumnos.getCurso()))){
			isWeekly=true;

		}else{
			isWeekly=false;
		}
		
		Transaction t2 = daoComportamiento.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		 List<RegComportamiento> regComportamientos=(List<RegComportamiento>) daoComportamiento.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select comportamiento from RegComportamiento comportamiento where comportamiento.idalumno where comportamiento.fechadesde=:FECHADESDE AND comportamiento.fechadesde=:FECHAHASTA")
				 .setDate(1, cal.getTime())
				 .setDate(2, cal2.getTime())
		 		 .list();
		 t2.commit();
		    
		    
		    
		try {
			//Construimos el mensaje en base al alumno,su periocidad, y la lista de comportamientos
			String mensaje=buildMessage(regComportamientos,alumnos,isWeekly,cal,cal2);
			
			SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
			
			
			MailTool mailTool=(MailTool)SpringApplicationContext.getBean("mailTool");
			
			String from="mail_comedor@colegiovirgendelcarmen.net";
			
			String to="";
			String subject="Datos de comportamiento- Fecha:"+format.format(fechaInicio);
			if(alumnos.getCorreopadres()!=null && !alumnos.getCorreopadres().trim().equals("")){
				//to="felipe.escobar@gmail.com";
				to=""+alumnos.getCorreopadres();
				
			}else{
				to="director@colegiovirgendelcarmen.net";
				subject="Reenvio por falta de correo de padres.";
			}
			
			String msg=mensaje;
			String cc=null;
			
			//String cc="director@colegiovirgendelcarmen.net";
			if(to.indexOf(",")==-1)
				mailTool.sendMail(from, to, subject, msg, cc,null);
			else{
				String[] tos=to.split(",");
				for(int i=0;i<tos.length;i++){
					mailTool.sendMail(from, tos[i], subject, msg, cc,null);
				}
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private String readHTML(String type) throws IOException{
		String classpath = System.getProperty("user.dir");
		 System.out.println(classpath);
		
		 /*** USO en servidor*/
		String pathnameHtmlsWeekly=classpath+"//files//fichasemanal.html";
		String pathnameHtmlsDaily=classpath+"//files//fichadiaria.html";
		
		/**Uso local
		 String pathnameHtmlsWeekly="C://produccion//Virgen del Carmen//ficha//fichasemanal.html";
		String pathnameHtmlsDaily="C://produccion//Virgen del Carmen//ficha//fichadiaria.html";
		**/
		 
			
		File fichero=null;
		String everything = "";
		if(type.equals("SEMANAL")){
			BufferedReader br = new BufferedReader(new FileReader(pathnameHtmlsWeekly));
		    try {
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();

		        while (line != null) {
		            sb.append(line);
		            //sb.append("");
		            line = br.readLine();
		        }
		        everything = sb.toString();
		        return everything;
		    } finally {
		        br.close();
		    }
			
		}else if(type.equals("DIARIO")){
			BufferedReader br = new BufferedReader(new FileReader(pathnameHtmlsDaily));
		    try {
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();

		        while (line != null) {
		            sb.append(line);
		            sb.append("");
		            line = br.readLine();
		        }
		        everything = sb.toString();
		        return everything;
		    } finally {
		        br.close();
		    }
			
		}
		return everything;
		
	}
	/**
	 * contento
		contentoTexto (campo de texto)
		suenno
		suennoTexto (campo de texto)
		esfinter
		esfinterTexto (campo de texto)

		- Los que uso en addComportamiento
		ensalada
		platos
		postre 
		fila
		comedor
		juegos
		lavado
	 * @author Pipe
	 *-
	 */
	public enum Comportamientos {
		 ENSALADA, PLATO1, PLATO2,PLATOS,POSTRE_S,COMEDOR,
		 CONTENTO,CONTENTOTEXTO,SUENNO,SUENNOTEXTO,FILA,
		 ESFINTER,ESFINTERTEXTO,JUEGOS,ASEO,ASEOTEXTO,ROPA,ROPATEXTO,OBSERVACIONES;  //; is optional
		 
		 @Override
		  public String toString() {
		       switch (this) {
		         case ENSALADA:
		             return("ENSALADA");
		         case PLATO1:
		        	 return("PLATO1");
		         case COMEDOR:
		        	 return("COMEDOR");
		         case PLATO2:
		        	 return("PLATO2");
		         case PLATOS:
		        	 return("PLATOS");
		         case POSTRE_S:
		        	 return("POSTRE");
		         case CONTENTO:
		        	 return("CONTENTO");
		         case ROPA:
		        	 return("ROPACAMBIO");
		         case ROPATEXTO:
		        	 return("ROPACAMBIOTEXTO");
		         case FILA:
		        	 return("FILA");
		         case CONTENTOTEXTO:
		        	 return("CONTENTOTEXTO");
		         case SUENNO:
		        	 return("SUENNO");
		         case SUENNOTEXTO:
		        	 return("SUENNOTEXTO");
		         case ESFINTER:
		        	 return("ESFINTER");
		         case ESFINTERTEXTO:
		        	 return("ESFINTERTEXTO");
		         case ASEO:
		        	 return("BOLSAASEO");
		         case ASEOTEXTO:
		        	 return("BOLSALASEOTEXTO");
		         case OBSERVACIONES:
		        	 return("OBSERVACIONES");
		        	 
		        }
		  return super.toString();
		 }


	}
	
	private String buildMessage(List<RegComportamiento> regComportamientos,Alumnos alumno,boolean isSemanal,Calendar calIni,Calendar calFin) throws IOException{
		System.out.print("build Message:----------------------------------------"+calIni.getTime().toString()+" - "+calFin.getTime().toString());
		//#NOMBRE
		//#CURSO
		//#NOMBRE
		String mensaje="";
		if(!isSemanal){
			mensaje=readHTML("SEMANAL");
		}else{
			mensaje=readHTML("DIARIO");
		}
		SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
		System.out.print("----------------------------------Fecha inicio:"+calIni.getTime());
		Menu menu=getMenu(calIni);
		//calFin.add(Calendar.DATE, 1);
		Menu menuManhana=null;
		int maxdiasfestivos=10;
		int ndias=0;
		while(menuManhana==null && (ndias<maxdiasfestivos || (calFin.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY || (calFin.get(Calendar.DAY_OF_WEEK))==Calendar.SUNDAY))){
			calFin.add(Calendar.DATE, 1);
			System.out.print("----------------------------------Buscando menu en:"+calFin.getTime());
			menuManhana=getMenu(calFin);
			ndias++;
		}
		System.out.print("----------------------------------Fecha cambiada:"+calFin.getTime());
		
		mensaje=mensaje.replace("#DIA1",calIni.get(Calendar.DATE)+"");
		mensaje=mensaje.replace("#DIA2",calFin.get(Calendar.DATE)+"");
		mensaje=mensaje.replace("#MES",calIni.getDisplayName(Calendar.MONTH, 1, Locale.getDefault())+"");
		mensaje=mensaje.replace("#ANHO",calFin.get(Calendar.YEAR)+"");
		if(menu!=null){
			mensaje=mensaje.replace("#DIA_ENSALADA",menu.getEnsalada()+"");
			mensaje=mensaje.replace("#DIA_PLATO1",menu.getPlato1()+"");
			mensaje=mensaje.replace("#DIA_PLATO2",menu.getPlato2()+"");
			mensaje=mensaje.replace("#DIA_PLATOS",menu.getPlato1()+" / "+menu.getPlato2()+"");
			mensaje=mensaje.replace("#DIA_POSTRE",menu.getPostre()+"");
		}else{
			mensaje=mensaje.replace("#DIA_ENSALADA","");
			mensaje=mensaje.replace("#DIA_PLATO1","");
			mensaje=mensaje.replace("#DIA_PLATO2","");
			mensaje=mensaje.replace("#DIA_PLATOS","");
			mensaje=mensaje.replace("#DIA_POSTRE","");
			
		}
		if(menuManhana!=null){
			System.out.print("----------------------------------Menu mañana NO !!! Nulo para fecha:"+calIni.getTime());
			
			mensaje=mensaje.replace("#DIA_SIGUIENTE_ENSALADA",menuManhana.getEnsalada()+"");
			mensaje=mensaje.replace("#DIA_SIGUIENTE_PLATO1",menuManhana.getPlato1()+"");
			mensaje=mensaje.replace("#DIA_SIGUIENTE_PLATO2",menuManhana.getPlato2()+"");
			mensaje=mensaje.replace("#DIA_SIGUIENTE_PLATOS",menuManhana.getPlato1()+" / "+menuManhana.getPlato2()+"");
			mensaje=mensaje.replace("#DIA_SIGUIENTE_POSTRE",menuManhana.getPostre()+"");
		}else{
			System.out.print("----------------------------------Menu mañana Nulo para fecha:"+calIni.getTime());
			
			mensaje=mensaje.replace("#DIA_SIGUIENTE_ENSALADA","");
			mensaje=mensaje.replace("#DIA_SIGUIENTE_PLATO1","");
			mensaje=mensaje.replace("#DIA_SIGUIENTE_PLATO2","");
			mensaje=mensaje.replace("#DIA_SIGUIENTE_PLATOS","");
			mensaje=mensaje.replace("#DIA_SIGUIENTE_POSTRE","");
			
		}
		

		
		
		mensaje=mensaje.replace("#NOMBRE",alumno.getNombre());
		mensaje=mensaje.replace("#APELLIDOS",alumno.getApellidos());
		mensaje=mensaje.replace("#CURSO",CursosHelper.getCourseName(Integer.parseInt(alumno.getCurso())));
		
		System.out.println("**************************************-------");
		System.out.println("**************************************-------info comportamiento:"+regComportamientos.size());
		
		for(RegComportamiento reg:regComportamientos){

			String fechaDesde=formatter.format(reg.getFechadesde());
			String fechaHasta=formatter.format(reg.getFechahasta());
			mensaje=processTipo(reg, !isSemanal, mensaje);
		}
		
		return mensaje;
		
	}
	private Menu getMenu(Calendar cal){
		MenuDAO menuDAO=(MenuDAO)SpringApplicationContext.getBean("menuDAO");
		List<Menu> menus=menuDAO.findByProperty("fecha", cal.getTime());
		if(menus!=null && menus.size()>0){
			return menus.get(0);
		}else{
			return null;
		}
		
		
	}
	private String processTipo(RegComportamiento reg,boolean isSemanal,String mensaje){
		System.out.println("Procesando......"+reg.getTipo()+" - "+reg.getValor());
		String resultado="";
		if(isSemanal){
			//SEMANAL
			if(reg.getTipo().toUpperCase().equals(Comportamientos.ENSALADA+"")){
				mensaje=mensaje.replace("#ENSALADA",reg.getValor());
			}else if(reg.getTipo().toUpperCase().equals(Comportamientos.PLATOS+"")){
				mensaje=mensaje.replace("#PLATOS",reg.getValor());
			}else if(reg.getTipo().toUpperCase().equals(Comportamientos.PLATO1+"")){
				mensaje=mensaje.replace("#PLATO1",reg.getValor());
			}else if(reg.getTipo().toUpperCase().equals(Comportamientos.PLATO2+"")){
				mensaje=mensaje.replace("#PLATO2",reg.getValor());
				
			}else if(reg.getTipo().toUpperCase().equals(Comportamientos.POSTRE_S+"")){
				mensaje=mensaje.replace("#POSTRE",reg.getValor());
			}else if(reg.getTipo().toUpperCase().equals(Comportamientos.COMEDOR+"")){
				mensaje=mensaje.replace("#COMEDOR",reg.getValor());
			}else if(reg.getTipo().toUpperCase().equals(Comportamientos.JUEGOS+"")){
				mensaje=mensaje.replace("#JUEGOS",reg.getValor());
			}else if(reg.getTipo().toUpperCase().equals("ASEO")){
				mensaje=mensaje.replace("#ASEO",reg.getValor());
			}else if(reg.getTipo().toUpperCase().equals(Comportamientos.FILA+"")){
				mensaje=mensaje.replace("#FILA",reg.getValor());
			}else{
				
				
			}
			
		}else{
			//DIARIO
			if(reg.getTipo().toUpperCase().equals(Comportamientos.ENSALADA+"")){
				mensaje=mensaje.replace("#ENSALADA",reg.getValor());
			}else if(reg.getTipo().toUpperCase().equals(Comportamientos.PLATO1+"")){
				mensaje=mensaje.replace("#PLATO1",reg.getValor());
			}else if(reg.getTipo().toUpperCase().equals(Comportamientos.PLATO2+"")){
				mensaje=mensaje.replace("#PLATO2",reg.getValor());
			}else if(reg.getTipo().toUpperCase().equals(Comportamientos.POSTRE_S+"")){
				mensaje=mensaje.replace("#POSTRE",reg.getValor());
			}else if(reg.getTipo().toUpperCase().equals(Comportamientos.CONTENTO+"")){
				if(reg.getValor().toUpperCase().equals("SÍ")){
					mensaje=mensaje.replace("#"+Comportamientos.JUEGOS+"_SI","<img src='"+path_app+"/img/si.jpg' height='50px' alt=''/>");
					mensaje=mensaje.replace("#"+Comportamientos.JUEGOS+"_NO","");
				}else{
					mensaje=mensaje.replace("#"+Comportamientos.JUEGOS+"_NO","<img src='"+path_app+"/img/no.jpg' height='50px' alt=''/>");
					mensaje=mensaje.replace("#"+Comportamientos.JUEGOS+"_SI","");
					
				}
			}else if(reg.getTipo().toUpperCase().equals(Comportamientos.SUENNO+"")){
				if(reg.getValor().toUpperCase().equals("SÍ")){
					mensaje=mensaje.replace("#SIESTA_SI","<img src='"+path_app+"/img/si.jpg' height='50px' alt=''/>");
					mensaje=mensaje.replace("#SIESTA_NO","");
				}else{
					mensaje=mensaje.replace("#SIESTA_NO","<img src='"+path_app+"/img/no.jpg' height='50px' alt=''/>");
					mensaje=mensaje.replace("#SIESTA_SI","");
					
				}
			}else if(reg.getTipo().toUpperCase().equals(Comportamientos.ESFINTER+"")){
				if(reg.getValor().toUpperCase().equals("SÍ")){
					mensaje=mensaje.replace("#ESFINTER_SI","<img src='"+path_app+"/img/si.jpg' height='50px' alt=''/>");
					mensaje=mensaje.replace("#ESFINTER_NO","");
				}else{
					mensaje=mensaje.replace("#ESFINTER_NO","<img src='"+path_app+"/img/no.jpg' height='50px' alt=''/>");
					mensaje=mensaje.replace("#ESFINTER_SI","");
					
				}
			}else if(reg.getTipo().toUpperCase().equals(Comportamientos.ASEO+"")){
				if(reg.getValor().toUpperCase().equals("SÍ")){
					mensaje=mensaje.replace("#ASEO_SI","<img src='"+path_app+"/img/si.jpg' height='50px' alt=''/>");
					mensaje=mensaje.replace("#ASEO_NO","");
				}else{
					mensaje=mensaje.replace("#ASEO_NO","<img src='"+path_app+"/img/no.jpg' height='50px' alt=''/>");
					mensaje=mensaje.replace("#ASEO_SI","");
					
				}
			}else if(reg.getTipo().toUpperCase().equals(Comportamientos.ROPA+"")){
				if(reg.getValor().toUpperCase().equals("SÍ")){
					mensaje=mensaje.replace("#"+Comportamientos.ROPA+"_SI","<img src='"+path_app+"/img/si.jpg'  height='50px' alt=''/>");
					mensaje=mensaje.replace("#"+Comportamientos.ROPA+"_NO","");
				}else{
					mensaje=mensaje.replace("#"+Comportamientos.ROPA+"_NO","<img src='"+path_app+"/img/no.jpg'  height='50px' alt=''/>");
					mensaje=mensaje.replace("#"+Comportamientos.ROPA+"_SI","");
					
				}
			}else if(reg.getTipo().toUpperCase().equals(Comportamientos.FILA+"")){
				mensaje=mensaje.replace("#FILA",reg.getValor());
			}else{
				
				
			}

		
		
		
		}
		if(reg.getTipo().toUpperCase().equals(Comportamientos.OBSERVACIONES+"")){
			
			mensaje=mensaje.replace("#OBSERVACIONES","Observaciones del educador: "+reg.getValor());
		}
		return mensaje;
		
	}
	public void finishEnvioComidas(int idalumno,Date fechaComienzo,Date fechaInicio){
		
		
	}
	public Usuarios checkUserAccess(String user,String password){
		System.out.println("Usuario tratando de hacer login:"+user+" con pwd:"+password);
		UsuariosDAO usersdao=(UsuariosDAO)SpringApplicationContext.getBean("usuariosDAO");
		if((user==null || user.equals("")) || (password==null || password.equals("")))return null;
			
			Usuarios instance=new Usuarios();
			instance.setUser(user);
			instance.setPassword(password);
			List<Usuarios> users=usersdao.findByExample(instance);
			try {
				JAXBContext context = JAXBContext.newInstance(Usuarios.class);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	public Usuarios checkAndGetUser(String user,String password,boolean isAdmin){
		
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
	
	public ArrayList getAlumnosByDate(int idCurso,String user,String password,Date fechaInicio,Date fechaFin){
		//if(!checkUser(user,password))return null;
		System.out.println("-getAlumnosByDate**************************************- user:"+user+"- password:"+password+" **************************************");
		Usuarios usuario=checkAndGetUser(user,password,false);
		if(usuario==null){
			System.out.println("-**************************************- usuario nulo- **************************************");
		}
		AlumnosDAO dao=(AlumnosDAO)SpringApplicationContext.getBean("alumnosDAO");
		ArrayList<AlumnoBean> lista=new ArrayList();
		Cursos curso=new Cursos();
		curso.setIdcurso(idCurso);
		Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		//Recogemos los comportamientos asociados al curso0
	    List<Alumnos> lstAlumnComporta = dao.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select distinct alumnos from Alumnos alumnos,RegAsistencia asistencia,RegComportamiento comportamientos,Usuariosalumnos useralumnos where useralumnos.idcurso=" + idCurso + " AND useralumnos.idusuario=" + usuario.getIduser() + " AND useralumnos.idalumno=alumnos.idalumno AND  alumnos.curso=" + idCurso + " AND alumnos.idalumno=comportamientos.idalumno AND comportamientos.fechadesde>= ?")
	    		.setDate(0, fechaInicio).list();
	    		
		t.commit();
		/**
		List<Alumnos> lstAlumnComporta=(List)dao.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select distinct alumnos from Alumnos alumnos,RegComportamiento comportamientos where alumnos.curso="+idCurso+" AND alumnos.idalumno=comportamientos.idalumno AND comportamientos.fechadesde>= ?")
				.setDate(0, fechaInicio).list();
		t.commit();
		*/
		
		HashMap<Integer,Alumnos> mapaComport=new HashMap();
		HashMap<Integer,String> mapaComportamientos=new HashMap();

    	for(int i=0;i<lstAlumnComporta.size();i++){
    		
    		Alumnos aluBean=lstAlumnComporta.get(i);

    		mapaComport.put(aluBean.getIdalumno(), aluBean);
			
		}

    	
    	
			Session s=dao.getHibernateTemplate().getSessionFactory().getCurrentSession();
			Transaction t2=s.beginTransaction();
			SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
			
			List<RegComportamiento> elements=null;
			
			Query query=s.createQuery("select model from RegComportamiento model,Alumnos alumnos where model.fechadesde>=? AND model.fechahasta<=? AND alumnos.idalumno=model.idalumno AND alumnos.curso="+idCurso+" ORDER BY model.fechadesde ASC");
			query.setDate(0, fechaInicio);
			query.setDate(1, fechaFin);
			
			//query.setParameter("dateFrom", fechaInicio.getTime());
			//query.setParameter("dateTo", fechaFin.getTime());
			elements=query.list();
			String comportamientos="";
			for(int i=0;i<elements.size();i++){
				comportamientos="";
	    		RegComportamiento regComportamientos=elements.get(i);
	    		
	    		if(mapaComportamientos.get(regComportamientos.getIdalumno())!=null){
	    			comportamientos=mapaComportamientos.get(regComportamientos.getIdalumno());
	    			comportamientos=comportamientos+" - "+regComportamientos.getTipo()+":"+regComportamientos.getValor()+"|";
	    			mapaComportamientos.put(regComportamientos.getIdalumno(), comportamientos);
	    		}else{
	    			comportamientos=regComportamientos.getTipo()+":"+regComportamientos.getValor()+"|";
	    			mapaComportamientos.put(regComportamientos.getIdalumno(), comportamientos);
	    			
	    		}
				
			}

			
			t2.commit();
    	
		
		//AlumnoBean bean=new AlumnoBean();
		//Recogemos la lista total de alumnos
		List<AlumnoBean> listaFinal=getAlumnosPriv(idCurso, usuario.getIduser());

    	
    	    	
    	ArrayList<AlumnoBean> listatotal=new ArrayList();
    	for(AlumnoBean alumno:listaFinal){
    		if(mapaComport.get(alumno.getIdalumno())!=null){
    			Alumnos al2=mapaComport.get(alumno.getIdalumno());
				listatotal.add(getAluBeanFromAlumno(al2,fechaInicio,fechaFin,true,mapaComportamientos.get(al2.getIdalumno())));
    		
    		}else{
    			
    			alumno.setComportamientos(".");
    			alumno.setFechaInicio(fechaInicio);
    			
    			alumno.setFechaFin(fechaFin);
    			listatotal.add(alumno);
    		}
    	}
	
	
	 
		try {
			JAXBContext context = JAXBContext.newInstance(com.dynamicdroides.db.virgendelcarmen.AlumnoBean.class);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listatotal;
	
	}

	
	public ArrayList getAlumnosForComedor(String user,String password,Date fechaInicio,Date fechaFin){
	
		if(!checkUser(user,password))return null;
		System.out.println("-getAlumnosForComedor**************************************- user:"+user+"- password:"+password+" **************************************");
		Usuarios usuario=checkAndGetUser(user,password,false);
		if(usuario==null){
			System.out.println("-**************************************- usuario nulo- **************************************");
		}
		
		AlumnosDAO dao=(AlumnosDAO)SpringApplicationContext.getBean("alumnosDAO");
		ArrayList<AlumnoBean> lista=new ArrayList();
		Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		//Recogemos los comportamientos asociados al curso0
		
		
		List<Alumnos> lstAlumnComporta=(List)dao.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select distinct alumnos from Alumnos alumnos,RegComportamiento comportamientos,Usuariosalumnos useralumnos where useralumnos.idusuario="+usuario.getIduser()+" AND useralumnos.idalumno=alumnos.idalumno AND  alumnos.idalumno=comportamientos.idalumno AND comportamientos.fechadesde>= ?")
				.setDate(0, fechaInicio).list();
		t.commit();
		/**
		List<Alumnos> lstAlumnComporta=(List)dao.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select distinct alumnos from Alumnos alumnos,RegComportamiento comportamientos where alumnos.curso="+idCurso+" AND alumnos.idalumno=comportamientos.idalumno AND comportamientos.fechadesde>= ?")
				.setDate(0, fechaInicio).list();
		t.commit();
		*/
		
		HashMap<Integer,Alumnos> mapaComport=new HashMap();
		HashMap<Integer,String> mapaComportamientos=new HashMap();

    	for(int i=0;i<lstAlumnComporta.size();i++){
    		
    		Alumnos aluBean=lstAlumnComporta.get(i);

    		mapaComport.put(aluBean.getIdalumno(), aluBean);
			
		}

    	
    	
		Session s=dao.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction t2=s.beginTransaction();
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
		
		List<RegComportamiento> elements=null;
		
		Query query=s.createQuery("select model from RegComportamiento model,Alumnos alumnos where model.fechadesde>=? AND model.fechahasta<=? AND alumnos.idalumno=model.idalumno ORDER BY model.fechadesde ASC");
		query.setDate(0, fechaInicio);
		query.setDate(1, fechaFin);
		
		//query.setParameter("dateFrom", fechaInicio.getTime());
		//query.setParameter("dateTo", fechaFin.getTime());
		elements=query.list();
		String comportamientos="";
		for(int i=0;i<elements.size();i++){
			comportamientos="";
    		RegComportamiento regComportamientos=elements.get(i);
    		
    		if(mapaComportamientos.get(regComportamientos.getIdalumno())!=null){
    			comportamientos=mapaComportamientos.get(regComportamientos.getIdalumno());
    			comportamientos=comportamientos+" - "+regComportamientos.getTipo()+":"+regComportamientos.getValor()+"|";
    			mapaComportamientos.put(regComportamientos.getIdalumno(), comportamientos);
    		}else{
    			comportamientos=regComportamientos.getTipo()+":"+regComportamientos.getValor()+"|";
    			mapaComportamientos.put(regComportamientos.getIdalumno(), comportamientos);
    			
    		}
			
		}

		
		t2.commit();
	
		
		//AlumnoBean bean=new AlumnoBean();
		//Recogemos la lista total de alumnos
		List<AlumnoBean> listaFinal=getAlumnosPriv(usuario.getIduser());

    	
    	    	
    	ArrayList<AlumnoBean> listatotal=new ArrayList();
    	for(AlumnoBean alumno:listaFinal){
    		if(mapaComport.get(alumno.getIdalumno())!=null){
    			Alumnos al2=mapaComport.get(alumno.getIdalumno());
    			AlumnoBean bean=getAluBeanFromAlumno(al2,fechaInicio,fechaFin,true,mapaComportamientos.get(al2.getIdalumno()));
    			bean.setCurso(""+CursosHelper.isCourseSemanal(Integer.parseInt(alumno.getCurso())));
    			bean.setCursoname(""+CursosHelper.getCourseName(Integer.parseInt(alumno.getCurso())));
				listatotal.add(bean);
    		
    		}else{
    			AlumnoBean bean=new AlumnoBean();
    			bean.setApellidos(alumno.getApellidos());
    			bean.setNombre(alumno.getNombre());
    			bean.setCorreopadres(alumno.getCorreopadres());
    			bean.setIdalumno(alumno.getIdalumno());
    			bean.setComportamientos(".");
    			bean.setFechaInicio(fechaInicio);
    			//alumno.setCurso(""+CursosHelper.getCourseName(Integer.parseInt(alumno.getCurso())));
    			bean.setCurso(""+CursosHelper.isCourseSemanal(Integer.parseInt(alumno.getCurso())));
    			bean.setCursoname(""+CursosHelper.getCourseName(Integer.parseInt(alumno.getCurso())));
    			bean.setFechaFin(fechaFin);
    			listatotal.add(bean);
    		}
    	}
	
	
	 
		try {
			JAXBContext context = JAXBContext.newInstance(com.dynamicdroides.db.virgendelcarmen.AlumnoBean.class);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listatotal;

		
	}
	public ArrayList getAlumnosBy(String user,String password,Date fechaInicio,Date fechaFin){
		if(!checkUser(user,password))return null;
		System.out.println("-getAlumnosBy**************************************- user:"+user+"- password:"+password+" **************************************");
		Usuarios usuario=checkAndGetUser(user,password,false);
		if(usuario==null){
			System.out.println("-**************************************- usuario nulo- **************************************");
		}
		AlumnosDAO dao=(AlumnosDAO)SpringApplicationContext.getBean("alumnosDAO");
//		Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		//Recogemos los comportamientos asociados al curso0
		/*
		List<Alumnos> lstAlumnComporta=(List)dao.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select distinct alumnos from Alumnos alumnos,RegComportamiento comportamientos,Usuariosalumnos useralumnos where useralumnos.idusuario="+usuario.getIduser()+" AND useralumnos.idalumno=alumnos.idalumno AND  alumnos.idalumno=comportamientos.idalumno AND comportamientos.fechadesde>= ?")
				.setDate(0, fechaInicio).list();
		t.commit();
		*/
		/**
		List<Alumnos> lstAlumnComporta=(List)dao.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select distinct alumnos from Alumnos alumnos,RegComportamiento comportamientos where alumnos.curso="+idCurso+" AND alumnos.idalumno=comportamientos.idalumno AND comportamientos.fechadesde>= ?")
				.setDate(0, fechaInicio).list();
		t.commit();
		*/
		/*
		HashMap<Integer,Alumnos> mapaComport=new HashMap();
		HashMap<Integer,String> mapaComportamientos=new HashMap();

    	for(int i=0;i<lstAlumnComporta.size();i++){
    		
    		Alumnos aluBean=lstAlumnComporta.get(i);

    		mapaComport.put(aluBean.getIdalumno(), aluBean);
			
		}

    	
		Session s=dao.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction t2=s.beginTransaction();
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
		
		List<RegComportamiento> elements=null;
		
		Query query=s.createQuery("select model from RegComportamiento model,Alumnos alumnos where model.fechadesde>=? AND model.fechahasta<=? AND alumnos.idalumno=model.idalumno ORDER BY model.fechadesde ASC");
		query.setDate(0, fechaInicio);
		query.setDate(1, fechaFin);
		
		//query.setParameter("dateFrom", fechaInicio.getTime());
		//query.setParameter("dateTo", fechaFin.getTime());
		elements=query.list();
		String comportamientos="";
		for(int i=0;i<elements.size();i++){
			comportamientos="";
    		RegComportamiento regComportamientos=elements.get(i);
    		
    		if(mapaComportamientos.get(regComportamientos.getIdalumno())!=null){
    			comportamientos=mapaComportamientos.get(regComportamientos.getIdalumno());
    			comportamientos=comportamientos+" - "+regComportamientos.getTipo()+":"+regComportamientos.getValor()+"|";
    			mapaComportamientos.put(regComportamientos.getIdalumno(), comportamientos);
    		}else{
    			comportamientos=regComportamientos.getTipo()+":"+regComportamientos.getValor()+"|";
    			mapaComportamientos.put(regComportamientos.getIdalumno(), comportamientos);
    			
    		}
			
		}

		
		t2.commit();
    	*/
	
		
		//AlumnoBean bean=new AlumnoBean();
		//Recogemos la lista total de alumnos
		List<AlumnoBean> listaFinal=getAlumnosPriv2(usuario.getIduser(),fechaInicio);

    	
		
    	ArrayList<AlumnoBean> listatotal=new ArrayList();
    	for(AlumnoBean alumno:listaFinal){
//    		if(mapaComport.get(alumno.getIdalumno())!=null){
//    			Alumnos al2=mapaComport.get(alumno.getIdalumno());
//    			AlumnoBean bean=getAluBeanFromAlumno(al2,fechaInicio,fechaFin,true,mapaComportamientos.get(al2.getIdalumno()));
//    			bean.setCurso(""+CursosHelper.isCourseSemanal(Integer.parseInt(alumno.getCurso())));
//    			bean.setCursoname(""+CursosHelper.getCourseName(Integer.parseInt(alumno.getCurso())));
//				listatotal.add(bean);
//    		
//    		}else{
    			AlumnoBean bean=new AlumnoBean();
    			bean.setApellidos(alumno.getApellidos());
    			bean.setNombre(alumno.getNombre());
    			bean.setCorreopadres(alumno.getCorreopadres());
    			bean.setIdalumno(alumno.getIdalumno());
    			bean.setComportamientos(".");
    			bean.setFechaInicio(fechaInicio);
    			//alumno.setCurso(""+CursosHelper.getCourseName(Integer.parseInt(alumno.getCurso())));
    			bean.setCurso(""+CursosHelper.isCourseSemanal(Integer.parseInt(alumno.getCurso())));
    			bean.setCursoname(""+CursosHelper.getCourseName(Integer.parseInt(alumno.getCurso())));
    			bean.setFechaFin(fechaFin);
    			listatotal.add(bean);
//    		}
    	}
	
	
	 
		try {
			JAXBContext context = JAXBContext.newInstance(com.dynamicdroides.db.virgendelcarmen.AlumnoBean.class);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listatotal;
	
	}

	private ArrayList getAlumnosPriv(int idUser){
		AlumnosDAO dao=(AlumnosDAO)SpringApplicationContext.getBean("alumnosDAO");
		ArrayList<AlumnoBean> lista=new ArrayList();
		//List<Alumnos> lstAlumnos=dao.findByProperty(AlumnosDAO.CURSO, ""+idCurso);
		
		Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		//Recogemos los comportamientos asociados al curso0
		List<Alumnos> lstAlumnos=(List)dao.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select distinct alumnos from Alumnos alumnos,Usuariosalumnos useralumnos,RegAsistencia asistencia where useralumnos.idusuario="+idUser+" AND useralumnos.idalumno=alumnos.idalumno")
				.list();
		t.commit();

		
		AlumnoBean bean=new AlumnoBean();
			for(Alumnos alumno:lstAlumnos){
				bean=new AlumnoBean();
				bean.setApellidos(alumno.getApellidos());
				bean.setCurso(alumno.getCurso());
				bean.setIdalumno(alumno.getIdalumno());
				bean.setNombre(alumno.getNombre());
				bean.setComportamientos("N/A");
				bean.setComedor(alumno.getComedor());
				bean.setCorreopadres(alumno.getCorreopadres());
				lista.add(bean);
			}
			
			
			
			try {
				JAXBContext context = JAXBContext.newInstance(com.dynamicdroides.db.virgendelcarmen.AlumnoBean.class);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return lista;
	
	}
	
	private ArrayList getAlumnosPriv2(int idUser,Date fecha){
		AlumnosDAO dao=(AlumnosDAO)SpringApplicationContext.getBean("alumnosDAO");
		ArrayList<AlumnoBean> lista=new ArrayList();
		//List<Alumnos> lstAlumnos=dao.findByProperty(AlumnosDAO.CURSO, ""+idCurso);
		
		Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		//Recogemos los comportamientos asociados al curso0
		List<Alumnos> lstAlumnos=(List)dao.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select distinct alumnos from Alumnos alumnos,Usuariosalumnos useralumnos,where useralumnos.idusuario="+idUser+" AND useralumnos.idalumno=alumnos.idalumno")
				.list();
		t.commit();

		
		AlumnoBean bean=new AlumnoBean();
			for(Alumnos alumno:lstAlumnos){
				bean=new AlumnoBean();
				bean.setApellidos(alumno.getApellidos());
				bean.setCurso(alumno.getCurso());
				bean.setIdalumno(alumno.getIdalumno());
				bean.setNombre(alumno.getNombre());
				bean.setComportamientos("N/A");
				bean.setComedor(alumno.getComedor());
				bean.setCorreopadres(alumno.getCorreopadres());
				lista.add(bean);
			}
			
			
			
			try {
				JAXBContext context = JAXBContext.newInstance(com.dynamicdroides.db.virgendelcarmen.AlumnoBean.class);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return lista;
	
	}

	

	
	
	public ArrayList getAlumnosByCurso(int idcurso,String user,String password,Date fechaInicio,Date fechaFin){
		if(!checkUser(user,password))return null;
		System.out.println("-getAlumnosByCurso**************************************- user:"+user+"- password:"+password+" **************************************");
		Usuarios usuario=checkAndGetUser(user,password,false);
		if(usuario==null){
			System.out.println("-**************************************- usuario nulo- **************************************");
		}
		AlumnosDAO dao=(AlumnosDAO)SpringApplicationContext.getBean("alumnosDAO");
		ArrayList<AlumnoBean> lista=new ArrayList();
		Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		//Recogemos los comportamientos asociados al curso0
		List<Alumnos> lstAlumnComporta=(List)dao.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select distinct alumnos from Alumnos alumnos,RegComportamiento comportamientos,Usuariosalumnos useralumnos where useralumnos.idusuario="+usuario.getIduser()+" AND useralumnos.idalumno=alumnos.idalumno AND alumnos.curso="+idcurso+" alumnos.idalumno=comportamientos.idalumno AND comportamientos.fechadesde>= ?")
				.setDate(0, fechaInicio).list();
		t.commit();
		/**
		List<Alumnos> lstAlumnComporta=(List)dao.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select distinct alumnos from Alumnos alumnos,RegComportamiento comportamientos where alumnos.curso="+idCurso+" AND alumnos.idalumno=comportamientos.idalumno AND comportamientos.fechadesde>= ?")
				.setDate(0, fechaInicio).list();
		t.commit();
		*/
		
		HashMap<Integer,Alumnos> mapaComport=new HashMap();
		HashMap<Integer,String> mapaComportamientos=new HashMap();

    	for(int i=0;i<lstAlumnComporta.size();i++){
    		
    		Alumnos aluBean=lstAlumnComporta.get(i);

    		mapaComport.put(aluBean.getIdalumno(), aluBean);
			
		}

    	
    	
		Session s=dao.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction t2=s.beginTransaction();
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
		
		List<RegComportamiento> elements=null;
		
		Query query=s.createQuery("select model from RegComportamiento model,Alumnos alumnos where model.fechadesde>=? AND model.fechahasta<=? AND alumnos.idalumno=model.idalumno ORDER BY model.fechadesde ASC");
		query.setDate(0, fechaInicio);
		query.setDate(1, fechaFin);
		
		//query.setParameter("dateFrom", fechaInicio.getTime());
		//query.setParameter("dateTo", fechaFin.getTime());
		elements=query.list();
		String comportamientos="";
		for(int i=0;i<elements.size();i++){
			comportamientos="";
    		RegComportamiento regComportamientos=elements.get(i);
    		
    		if(mapaComportamientos.get(regComportamientos.getIdalumno())!=null){
    			comportamientos=mapaComportamientos.get(regComportamientos.getIdalumno());
    			comportamientos=comportamientos+" - "+regComportamientos.getTipo()+":"+regComportamientos.getValor()+"|";
    			mapaComportamientos.put(regComportamientos.getIdalumno(), comportamientos);
    		}else{
    			comportamientos=regComportamientos.getTipo()+":"+regComportamientos.getValor()+"|";
    			mapaComportamientos.put(regComportamientos.getIdalumno(), comportamientos);
    			
    		}
			
		}

		
		t2.commit();
	
		
		//AlumnoBean bean=new AlumnoBean();
		//Recogemos la lista total de alumnos
		List<AlumnoBean> listaFinal=getAlumnosPriv2(usuario.getIduser());

    	
    	    	
    	ArrayList<AlumnoBean> listatotal=new ArrayList();
    	for(AlumnoBean alumno:listaFinal){
    		if(mapaComport.get(alumno.getIdalumno())!=null){
    			Alumnos al2=mapaComport.get(alumno.getIdalumno());
    			AlumnoBean bean=getAluBeanFromAlumno(al2,fechaInicio,fechaFin,true,mapaComportamientos.get(al2.getIdalumno()));
    			bean.setCurso(""+CursosHelper.isCourseSemanal(Integer.parseInt(alumno.getCurso())));
				listatotal.add(bean);
    		
    		}else{
    			
    			alumno.setComportamientos(".");
    			alumno.setFechaInicio(fechaInicio);
    			alumno.setCurso(""+CursosHelper.isCourseSemanal(Integer.parseInt(alumno.getCurso())));
    			alumno.setFechaFin(fechaFin);
    			listatotal.add(alumno);
    		}
    	}
	
	
	 
		try {
			JAXBContext context = JAXBContext.newInstance(com.dynamicdroides.db.virgendelcarmen.AlumnoBean.class);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listatotal;
	
	}

	
	public AlumnoBean getAluBeanFromAlumno(Alumnos al2,Date fechaInicio,Date fechaFin,boolean hasComportamiento,String comportamientos){
		AlumnoBean bean2=new AlumnoBean();
		bean2.setApellidos(al2.getApellidos());
		bean2.setNombre(al2.getNombre());
		bean2.setApellidos(al2.getApellidos());
		bean2.setComedor(al2.getComedor());
		bean2.setCorreopadres(al2.getCorreopadres());
		bean2.setCurso(al2.getCurso());
		bean2.setFechaFin(fechaFin);
		bean2.setFechaInicio(fechaInicio);
		bean2.setIdalumno(al2.getIdalumno());
		bean2.setComedor(al2.getComedor());
		bean2.setComportamientoFilled(hasComportamiento);
		bean2.setComportamientos(comportamientos);

		return bean2;
		
	}
	private ArrayList getAlumnosPriv(int idCurso,int idUser){
		AlumnosDAO dao=(AlumnosDAO)SpringApplicationContext.getBean("alumnosDAO");
		ArrayList<AlumnoBean> lista=new ArrayList();
		Cursos curso=new Cursos();
		curso.setIdcurso(idCurso);
		//List<Alumnos> lstAlumnos=dao.findByProperty(AlumnosDAO.CURSO, ""+idCurso);
		
		Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		//Recogemos los comportamientos asociados al curso0
		List<Alumnos> lstAlumnos=(List)dao.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select distinct alumnos from Alumnos alumnos,Usuariosalumnos useralumnos where useralumnos.idcurso="+idCurso+" AND useralumnos.idusuario="+idUser+" AND useralumnos.idalumno=alumnos.idalumno AND  alumnos.curso="+idCurso+"")
				.list();
		t.commit();

		
		AlumnoBean bean=new AlumnoBean();
			for(Alumnos alumno:lstAlumnos){
				bean=new AlumnoBean();
				bean.setApellidos(alumno.getApellidos());
				bean.setCurso(alumno.getCurso());
				bean.setIdalumno(alumno.getIdalumno());
				bean.setNombre(alumno.getNombre());
				bean.setComportamientos("N/A");
				bean.setComedor(alumno.getComedor());
				bean.setCorreopadres(alumno.getCorreopadres());
				lista.add(bean);
			}
			
			
			
			try {
				JAXBContext context = JAXBContext.newInstance(com.dynamicdroides.db.virgendelcarmen.AlumnoBean.class);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return lista;
	
	}
	public ArrayList getAlumnosAsisten(String user,String password,Date fecha,Date fechaFin){
		AlumnosDAO dao=(AlumnosDAO)SpringApplicationContext.getBean("alumnosDAO");
		ArrayList<AlumnoBean> lista=new ArrayList();
		//List<Alumnos> lstAlumnos=dao.findByProperty(AlumnosDAO.CURSO, ""+idCurso);
		Usuarios usuario=checkAndGetUser(user,password,false);

		
		Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		//Recogemos los comportamientos asociados al curso0
		List<Alumnos> lstAlumnos=(List)dao.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select distinct alumnos from Alumnos alumnos,Usuariosalumnos useralumnos,RegAsistencia regasis where useralumnos.idusuario="+usuario.getIduser()+" AND useralumnos.idalumno=alumnos.idalumno AND regasis.idalumno=alumnos.idalumno AND regasis.fecha=?")
				.setDate(0, fecha)
				.list();
		t.commit();

		
		AlumnoBean bean=new AlumnoBean();
			for(Alumnos alumno:lstAlumnos){
				bean=new AlumnoBean();
				bean.setApellidos(alumno.getApellidos());
				bean.setCurso(alumno.getCurso());
				bean.setIdalumno(alumno.getIdalumno());
				bean.setNombre(alumno.getNombre());
				bean.setComportamientos("N/A");
				bean.setComedor(alumno.getComedor());
				bean.setCorreopadres(alumno.getCorreopadres());
				lista.add(bean);
			}
			
			
			
			try {
				JAXBContext context = JAXBContext.newInstance(com.dynamicdroides.db.virgendelcarmen.AlumnoBean.class);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return lista;
	
	}

	private ArrayList getAlumnosPriv2(int idUser){
		AlumnosDAO dao=(AlumnosDAO)SpringApplicationContext.getBean("alumnosDAO");
		ArrayList<AlumnoBean> lista=new ArrayList();
		//List<Alumnos> lstAlumnos=dao.findByProperty(AlumnosDAO.CURSO, ""+idCurso);
		
		Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		//Recogemos los comportamientos asociados al curso0
		List<Alumnos> lstAlumnos=(List)dao.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select distinct alumnos from Alumnos alumnos,Usuariosalumnos useralumnos where useralumnos.idusuario="+idUser+" AND useralumnos.idalumno=alumnos.idalumno")
				.list();
		t.commit();

		
		AlumnoBean bean=new AlumnoBean();
			for(Alumnos alumno:lstAlumnos){
				bean=new AlumnoBean();
				bean.setApellidos(alumno.getApellidos());
				bean.setCurso(alumno.getCurso());
				bean.setIdalumno(alumno.getIdalumno());
				bean.setNombre(alumno.getNombre());
				bean.setComportamientos("N/A");
				bean.setComedor(alumno.getComedor());
				bean.setCorreopadres(alumno.getCorreopadres());
				lista.add(bean);
			}
			
			
			
			try {
				JAXBContext context = JAXBContext.newInstance(com.dynamicdroides.db.virgendelcarmen.AlumnoBean.class);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return lista;
	
	}
	public ArrayList getAlumnos(int idCurso,String user,String password){
		if(!checkUser(user,password))return null;
		AlumnosDAO dao=(AlumnosDAO)SpringApplicationContext.getBean("alumnosDAO");
		ArrayList<AlumnoBean> lista=new ArrayList();
		Cursos curso=new Cursos();
		curso.setIdcurso(idCurso);
		List<Alumnos> lstAlumnos=dao.findByProperty("curso", ""+idCurso);
		AlumnoBean bean=new AlumnoBean();
			for(Alumnos alumno:lstAlumnos){
				bean=new AlumnoBean();
				bean.setApellidos(alumno.getApellidos());
				bean.setCurso(alumno.getCurso());
				bean.setIdalumno(alumno.getIdalumno());
				bean.setNombre(alumno.getNombre());
				bean.setComportamientos("N/A");
				bean.setComedor(alumno.getComedor());
				bean.setCorreopadres(alumno.getCorreopadres());
				lista.add(bean);
			}
			
			
			
			try {
				JAXBContext context = JAXBContext.newInstance(com.dynamicdroides.db.virgendelcarmen.AlumnoBean.class);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return lista;
	
	}
	public List getObservaciones(String user,String password){
		ObservacionesDAO dao=(ObservacionesDAO)SpringApplicationContext.getBean("observacionesDAO");
		
		try {
			JAXBContext context = JAXBContext.newInstance(com.dynamicdroides.db.virgendelcarmen.Observaciones.class);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dao.findAll();
	}
	public List getCurso(int iduser,String user,String password){
		if(!checkUser(user,password))return null;
		CursosDAO dao=(CursosDAO)SpringApplicationContext.getBean("cursosDAO");
		ArrayList<CursoBean> lista=new ArrayList();
		
		Session s=dao.getSessionFactory().getCurrentSession();
		Transaction t=s.beginTransaction();
		List<Cursos> elements=s.createQuery("select distinct c FROM Cursos c,Usuariosalumnos usualu where c.idcurso = usualu.idcurso AND usualu.idusuario="+iduser+"").list();
		t.commit();
		try {
			JAXBContext context = JAXBContext.newInstance(Cursos.class);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return elements;
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
		comportamiento.setObservaciones(observaciones);
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
		comida.setObservaciones(observaciones);
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
	
	public boolean isDataComidasFilled(int idAlumno,Date fechaActualiza,String user,String password){
		
		if(!checkUser(user,password))return false;
		RegComidasDAO dao=(RegComidasDAO)SpringApplicationContext.getBean("regComidasDAO");
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
		
				//03/07/2013 - ksanoja: Se comenta el código para el Bug con Id 2 del excel: Bug Tracker AutanaCRM@
				//String queryString = "select a from Activity a order by a.lastUpd DESC";
				String queryString = "select reg from RegComidas reg where reg.idalumno="+idAlumno+" AND  reg.fecha>='"+format.format((fechaActualiza))+"'";
				Transaction t= dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
				Query queryObject = dao.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(queryString);
//				return queryObject.setFetchSize(20).list();
				
				try {
					JAXBContext context = JAXBContext.newInstance(boolean.class);
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(queryObject!=null && queryObject.list()!=null && queryObject.list().size()>0){
					return true;
				}else{
					return false;
				}
		
	}
	public boolean isDataComportamientoFilled(int idAlumno,Date fechaActualiza,String user,String password){
		if(!checkUser(user,password))return false;
		RegComportamientoDAO dao=(RegComportamientoDAO)SpringApplicationContext.getBean("regComportamientoDAO");
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
		
				//03/07/2013 - ksanoja: Se comenta el código para el Bug con Id 2 del excel: Bug Tracker AutanaCRM@
				//String queryString = "select a from Activity a order by a.lastUpd DESC";
				String queryString = "select reg from RegComportamiento reg where reg.fecha>='"+format.format(fechaActualiza).toString()+"'";
				Transaction t= dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
				Query queryObject = dao.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(queryString);
//				return queryObject.setFetchSize(20).list();
				
				try {
					JAXBContext context = JAXBContext.newInstance(Alumnos.class);
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(queryObject!=null && queryObject.list()!=null && queryObject.list().size()>0){
					return true;
				}else{
					return false;
				}
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
