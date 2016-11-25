package com.dynamicdroides.vdc.services.login.controllers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

import com.dynamicdroides.db.virgendelcarmen.Alumnos;
import com.dynamicdroides.db.virgendelcarmen.AlumnosDAO;
import com.dynamicdroides.db.virgendelcarmen.Cursos;
import com.dynamicdroides.db.virgendelcarmen.CursosDAO;
import com.dynamicdroides.db.virgendelcarmen.RegAsistencia;
import com.dynamicdroides.db.virgendelcarmen.Usuarios;
import com.dynamicdroides.db.virgendelcarmen.UsuariosDAO;
import com.dynamicdroides.vdc.services.business.AsistenciaBusiness;
import com.dynamicdroides.vdc.services.business.CursosHelper;
import com.dynamicdroides.vdc.services.business.WordExtractor;

public class AsistenciaController extends IControlador{

	private WordExtractor extractor;
	private String pathToWords;
	private AsistenciaBusiness asistenciaBusiness;
	private AlumnosDAO alumnosDAO;
	private UsuariosDAO usuariosDAO;

	public UsuariosDAO getUsuariosDAO() {
		return usuariosDAO;
	}
	public void setUsuariosDAO(UsuariosDAO usuariosDAO) {
		this.usuariosDAO = usuariosDAO;
	}
	public AlumnosDAO getAlumnosDAO() {
		return alumnosDAO;
	}
	public void setAlumnosDAO(AlumnosDAO alumnosDAO) {
		this.alumnosDAO = alumnosDAO;
	}
	public AsistenciaBusiness getAsistenciaBusiness() {
		return asistenciaBusiness;
	}
	public void setAsistenciaBusiness(AsistenciaBusiness asistenciaBusiness) {
		this.asistenciaBusiness = asistenciaBusiness;
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
			List<Cursos> cursos=CursosHelper.getAllCursos();
			myModel.put("cursos",cursos);
			
			if(request.getParameter("event")!=null){
				if(request.getParameter("event").equals("report")){
					
					Integer idalumno=Integer.parseInt(request.getParameter("idalumno"));
					myModel.put("idalumno", idalumno);

					boolean isalumno=false;
					if(request.getParameter("isalumno")!=null && request.getParameter("isalumno").equals("true")){
						isalumno=true;
					}

					RegAsistencia instance=new RegAsistencia();
					instance.setIdalumno(idalumno=Integer.parseInt(request.getParameter("idalumno")));
					instance.setIsalumno(isalumno);					
							
					List<RegAsistencia> listRegAsistencias=asistenciaBusiness.getDao().findByExample(instance);									
					
					String calendarRegAsistencias=generateJSRegAsistenciaCalendarEvents(request.getContextPath(),listRegAsistencias);
					myModel.put("events", calendarRegAsistencias);

					
					view2=new InternalResourceView("jsp/asistencia.jsp");
					return new ModelAndView(view2, "model", myModel);

				}else if(request.getParameter("event").equals("curso")){
					
					Integer idcurso=Integer.parseInt(request.getParameter("idcurso"));
										
					List<RegAsistencia> listRegAsistencias=asistenciaBusiness.findByCurso(idcurso);
					if(listRegAsistencias!=null){
						String calendarRegAsistencias=generateJSRegAsistenciaAllCalendarEvents(request.getContextPath(),listRegAsistencias);
						myModel.put("events", calendarRegAsistencias);
					}else{
						myModel.put("events", "");
						
					}
					myModel.put("idcurso", idcurso);
					String cursoName=CursosHelper.getCourseName(idcurso);
					myModel.put("cursoName", cursoName);
					
					view2=new InternalResourceView("jsp/asistenciareport.jsp");
					return new ModelAndView(view2, "model", myModel);
					
				}else if(request.getParameter("event").equals("educador")){
					
					Integer iduser=Integer.parseInt(request.getParameter("educador"));
										
					List<RegAsistencia> listRegAsistencias=asistenciaBusiness.findByUser(iduser);
					if(listRegAsistencias!=null){
						String calendarRegAsistencias=generateJSRegAsistenciaAllCalendarEvents(request.getContextPath(),listRegAsistencias);
						myModel.put("events", calendarRegAsistencias);
					}else{
						myModel.put("events", "");
						
					}
					myModel.put("iduser", iduser);
					List<Usuarios> usuarios=usuariosDAO.findAll();
					myModel.put("usuarios", usuarios);
					view2=new InternalResourceView("jsp/asistenciareport.jsp");
					return new ModelAndView(view2, "model", myModel);
						
					
				}else if(request.getParameter("event").equals("index")){
					//

					List<RegAsistencia> listRegAsistencias=asistenciaBusiness.getDao().findAll();
					
					String calendarRegAsistencias=generateJSRegAsistenciaAllCalendarEvents(request.getContextPath(),listRegAsistencias);
					myModel.put("events", calendarRegAsistencias);

					List<Usuarios> usuarios=usuariosDAO.findAll();
					myModel.put("usuarios", usuarios);
					view2=new InternalResourceView("jsp/asistenciareport.jsp");
					return new ModelAndView(view2, "model", myModel);
					
					
				}else{
					String event=request.getParameter("event");
					processRequest(request);
					
				}
				
			}

			
			}catch(Exception e){
				e.printStackTrace();
				
				
			}
		
		return null;
	}
	public String generateJSRegAsistenciaCalendarEvents(String contextPath,List<RegAsistencia> RegAsistencias){
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat formater=new SimpleDateFormat("yyyy-MM-dd");
		String start="";
		String end="";
		String title="";
		String events="";
		//String url=contextPath+"/RegAsistencia.vdc?event=edit&idRegAsistencia=";
		String url="javascript:editRegAsistencia(";
		String strAsistencia="";
		
		
		for(RegAsistencia regAsistencia:RegAsistencias){
			
				start=formater.format(regAsistencia.getFecha());
				end=formater.format(regAsistencia.getFecha());
				if(regAsistencia.getObservaciones()!=null)
					strAsistencia="SI - "+regAsistencia.getObservaciones();
				else
					strAsistencia="SI";
					
				events=events+"{"+
				"id:'"+regAsistencia.getRowId()+"',"+
				"title:'"+strAsistencia+"',"+
				"start:'"+start+"',"+
				"end:'"+end+"',"+
				"url:'"+url+regAsistencia.getRowId()+")',"+
				"},\r\n";
			
		}
		/**
		 * {
	                title: 'My Event',
	                start: '2014-02-01',
	                url: ''
	            },
	            {
	                title: '2 My Event',
	                start: '2014-02-02',
	                url: ''
	            }
	            
		 */
		return events;
		
	}


	
	public String generateJSRegAsistenciaAllCalendarEvents(String contextPath,List<RegAsistencia> regAsistencias){
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat formater=new SimpleDateFormat("yyyy-MM-dd");
		String start="";
		String end="";
		String title="";
		String events="";
		String list="";
		//String url=contextPath+"/RegAsistencia.vdc?event=edit&idRegAsistencia=";
		String url="javascript:editRegAsistencia(";
		String strAsistencia="";
		Date datePrevious=new Date();
		int contadorAlumnos=0;
		int contadorProfesores=0;
		String coursePrevius="";
		//Alumnos alu=null;	
		datePrevious=null;
		List listaCursos=CursosHelper.getAllCursos();
		//TODO para EMILIO
		List<Alumnos> lstAlumnos=alumnosDAO.findAll();
		HashMap<Integer,Alumnos> mapaAlumnos=new HashMap<Integer, Alumnos>();
		for(Alumnos alu:lstAlumnos){
			mapaAlumnos.put(alu.getIdalumno(), alu);
		}
		//CursosHelper.getCourseID(name);
		//por cada id de curso, por un dia en concreto, un contador de alumnos
		HashMap<Integer,Integer> mapaAsistenciaAlumnosPorCursos=new HashMap<Integer, Integer>();
		Alumnos alu=null;
		int n=0;
		for(RegAsistencia regAsistencia:regAsistencias){		
			alu=mapaAlumnos.get(regAsistencia.getIdalumno());
			if(alu==null)continue;
			if(mapaAsistenciaAlumnosPorCursos.get(CursosHelper.getCourseID(alu.getCurso()))!=null){
				n=mapaAsistenciaAlumnosPorCursos.get(CursosHelper.getCourseID(alu.getCurso()));
				n++;
				mapaAsistenciaAlumnosPorCursos.put(CursosHelper.getCourseID(alu.getCurso()),n);				
			}else{
				mapaAsistenciaAlumnosPorCursos.put(CursosHelper.getCourseID(alu.getCurso()),1);
				
			}
			
		}
				
		for(RegAsistencia regAsistencia:regAsistencias){	
					//linea de recuperacion de datos de alumno es
				//alu=alumnosDAO.findById(regAsistencia.getIdalumno());
				System.out.println("id" + regAsistencia.getIdalumno() + "fecha" + regAsistencia.getFecha());
				
				if(datePrevious==null || regAsistencia.getFecha().equals(datePrevious)){
							//antes de escribir por primera vez, o cuando no cambia la fecha
							datePrevious=regAsistencia.getFecha();
							if(regAsistencia.getIsalumno()){					
								contadorAlumnos++;
							}else{
								contadorProfesores++;
							}
					//regAsistencia.getIdalumno()					
					//TODO EMILIO Aqui tienes que cargar el hashmap	
						
					mapaAsistenciaAlumnosPorCursos.put(CursosHelper.getCourseID(alu.getCurso()),contadorAlumnos);							
					
				}else{
					start=formater.format(datePrevious);
					end=formater.format(datePrevious);
					//TODO Emilio esto tiene que cambiarse para resultar algo mo :
					// 1PRIA 7 alumnos \r\n
					//2PRIA 10		
					
					Iterator it = mapaAsistenciaAlumnosPorCursos.entrySet().iterator();
					while (it.hasNext()) {
						Map.Entry e = (Map.Entry)it.next();						
						strAsistencia=strAsistencia+ "Curso:"+ e.getValue().toString() +contadorAlumnos + "alumnos" + "\r\n";
					}			
					
					strAsistencia="Número de alumnos:"+contadorAlumnos;					
					strAsistencia+=" / Número de profesores:"+contadorProfesores;
					events=events+"{"+
					"id:'"+regAsistencia.getRowId()+"',"+
					"title:'"+strAsistencia+"',"+
					"start:'"+start+"',"+
					"end:'"+end+"',"+
					"url:'"+url+regAsistencia.getRowId()+")',"+
					"},\r\n";									
					
					datePrevious=regAsistencia.getFecha();
					if(regAsistencia.getIsalumno()){
						contadorAlumnos=1;
					}else{
						contadorProfesores=1;
					}
					mapaAsistenciaAlumnosPorCursos.clear();		
					mapaAsistenciaAlumnosPorCursos.put(CursosHelper.getCourseID(alu.getCurso()),contadorAlumnos);			
				}			
		}		
				
				Iterator it = mapaAsistenciaAlumnosPorCursos.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry e = (Map.Entry)it.next();
					
					strAsistencia=strAsistencia + "Curso:"+ e.getValue().toString() +contadorAlumnos + "alumnos" + "\r\n";
				}		
				
				
				//strAsistencia=strAsistencia + "Curso:"+ e.getValue().toString() +contadorAlumnos + "alumnos" + "\r\n";
		
			 	start=formater.format(datePrevious);
				end=formater.format(datePrevious);
				strAsistencia="Número de alumnos:"+contadorAlumnos;
				
				strAsistencia+=" / Número de profesores:"+contadorProfesores;				
				String id="";
				if(regAsistencias.size()>0){
						id=regAsistencias.get(regAsistencias.size()-1).getRowId()+"";							
						
						events=events+"{"+
						"id:'"+id+"',"+
						"title:'"+strAsistencia+"',"+
						"start:'"+start+"',"+
						"end:'"+end+"',"+
						"url:'"+url+id+")',"+
						"},\r\n";
					
					if(regAsistencias.get(regAsistencias.size()-1).getIsalumno() )
						contadorAlumnos=1;
					else
						contadorProfesores=1;
					
				}
					
		System.out.println("El contenido es:"+events+ "y ya esta");
		return events;
		
	}
	
	

	public String generateJSRegAsistenciaAllCalendarEvents3(String contextPath,List<RegAsistencia> regAsistencias){
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat formater=new SimpleDateFormat("yyyy-MM-dd");
		String start="";
		String end="";
		String title="";
		String events="";
		//String url=contextPath+"/RegAsistencia.vdc?event=edit&idRegAsistencia=";
		String url="javascript:editRegAsistencia(";
		String strAsistencia="";		
		Date datePrevious=new Date();
		int contadorAlumnos=0;
		int contadorProfesores=0;
		//TODO para EMILIO
		CursosHelper.getCourseName(1);
		//por cada id de curso, por un dia en concreto, un contador de alumnos
		HashMap<Integer,Integer> mapaAsistenciaAlumnosPorCursos=new HashMap<Integer, Integer>();
		
		Alumnos alu=null;
		for(RegAsistencia regAsistencia:regAsistencias){

			
					//linea de recuperacion de datos de alumno es
				alu=alumnosDAO.findById(regAsistencia.getIdalumno());

				if(datePrevious==null || regAsistencia.getFecha().equals(datePrevious)){
					//antes de escribir por primera vez, o cuando no cambia la fecha
					datePrevious=regAsistencia.getFecha();
					if(regAsistencia.getIsalumno())
						contadorAlumnos++;
					else
						contadorProfesores++;
					
					//TODO EMILIO Aqui tienes que cargar el hashmap
				}else{
					start=formater.format(datePrevious);
					end=formater.format(datePrevious);
					//TODO Emilio esto tiene que cambiarse para resultar algo mo :
					// 1PRIA 7 alumnos \r\n
					//2PRIA 10
					
					strAsistencia="Número de alumnos:"+contadorAlumnos;
					
					strAsistencia+=" / Número de profesores:"+contadorProfesores;
					events=events+"{"+
					"id:'"+regAsistencia.getRowId()+"',"+
					"title:'"+strAsistencia+"',"+
					"start:'"+start+"',"+
					"end:'"+end+"',"+
					"url:'"+url+regAsistencia.getRowId()+")',"+
					"},\r\n";
					
					datePrevious=regAsistencia.getFecha();
					if(regAsistencia.getIsalumno())
						contadorAlumnos=1;
					else
						contadorProfesores=1;
					
					mapaAsistenciaAlumnosPorCursos.clear();
					
				}
			
		}
		start=formater.format(datePrevious);
		end=formater.format(datePrevious);
		strAsistencia="Número de alumnos:"+contadorAlumnos;
		String id="";
		
		if(regAsistencias.size()>0){
			id=regAsistencias.get(regAsistencias.size()-1).getRowId()+"";
			
		strAsistencia+=" / Número de profesores:"+contadorProfesores;
		events=events+"{"+
		"id:'"+id+"',"+
		"title:'"+strAsistencia+"',"+
		"start:'"+start+"',"+
		"end:'"+end+"',"+
		"url:'"+url+id+")',"+
		"},\r\n";
		
		if(regAsistencias.get(regAsistencias.size()-1).getIsalumno() )
			contadorAlumnos=1;
		else
			contadorProfesores=1;
			
		}
		
		/**
		 * {
	                title: 'My Event',
	                start: '2014-02-01',
	                url: ''
	            },
	            {
	                title: '2 My Event',
	                start: '2014-02-02',
	                url: ''
	            }
	            
		 */
		return events;
		
	}
	

	public String generateJSRegAsistenciaAllCalendarEvents2(String contextPath,List<RegAsistencia> regAsistencias){
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat formater=new SimpleDateFormat("yyyy-MM-dd");
		String start="";
		String end="";
		String title="";
		String events="";
		String list="";
		//String url=contextPath+"/RegAsistencia.vdc?event=edit&idRegAsistencia=";
		String url="javascript:editRegAsistencia(";
		String strAsistencia="";
		Date datePrevious=new Date();
		int contadorAlumnos=0;
		int contadorProfesores=0;
		//TODO para EMILIO
		CursosHelper.getCourseName(1);
		//CursosHelper.getCourseID(name);
		//por cada id de curso, por un dia en concreto, un contador de alumnos
		HashMap<Integer,Integer> mapaAsistenciaAlumnosPorCursos=new HashMap<Integer, Integer>();
		String coursePrevius="";
		Alumnos alu=null;		
		for(RegAsistencia regAsistencia:regAsistencias){			
					//linea de recuperacion de datos de alumno es
				alu=alumnosDAO.findById(regAsistencia.getIdalumno());
				if(datePrevious==null || regAsistencia.getFecha().equals(datePrevious)){
							//antes de escribir por primera vez, o cuando no cambia la fecha
							datePrevious=regAsistencia.getFecha();
							if(regAsistencia.getIsalumno()){					
								contadorAlumnos++;
							}else{
								contadorProfesores++;
							}
					//regAsistencia.getIdalumno()
					
					//TODO EMILIO Aqui tienes que cargar el hashmap
						if(coursePrevius=="" || coursePrevius==alu.getCurso()){
								coursePrevius=alu.getCurso();
								mapaAsistenciaAlumnosPorCursos.put(contadorAlumnos,CursosHelper.getCourseID(alu.getCurso()));
								
						}else{
								coursePrevius=alu.getCurso();
								mapaAsistenciaAlumnosPorCursos.put(contadorAlumnos,CursosHelper.getCourseID(alu.getCurso()));
								contadorAlumnos=1;
						}
				}else{
					start=formater.format(datePrevious);
					end=formater.format(datePrevious);
					//TODO Emilio esto tiene que cambiarse para resultar algo mo :
					// 1PRIA 7 alumnos \r\n
					//2PRIA 10		
					
					Iterator it = mapaAsistenciaAlumnosPorCursos.entrySet().iterator();
					while (it.hasNext()) {
						Map.Entry e = (Map.Entry)it.next();
						//System.out.println(e.getKey() + " " + e.getValue());
						/*
						events=events+"{"+
								CursosHelper.getCourseName(Integer.parseInt(e.getValue().toString()))+e.getKey().toString()+" alumnos" + 							
								"},\r\n";
						*/
						strAsistencia=strAsistencia+ "Curso:"+ e.getValue().toString() +contadorAlumnos + "alumnos";
					}			
					
					strAsistencia="Número de alumnos:"+contadorAlumnos;					
					strAsistencia+=" / Número de profesores:"+contadorProfesores;
					events=events+"{"+
					"id:'"+regAsistencia.getRowId()+"',"+
					"title:'"+strAsistencia+"',"+
					"start:'"+start+"',"+
					"end:'"+end+"',"+
					"url:'"+url+regAsistencia.getRowId()+")',"+
					"},\r\n";									
					
					datePrevious=regAsistencia.getFecha();
					if(regAsistencia.getIsalumno()){
						contadorAlumnos=1;
					}else{
						contadorProfesores=1;
					}
					mapaAsistenciaAlumnosPorCursos.clear();
					
				}
			
		}		
		/****
				 for (Integer  value : mapaAsistenciaAlumnosPorCursos.values()) {
		             System.out.println(value);
		             //strAsistencia=strAsistencia + "Curso:"+ e.getValue().toString() +contadorAlumnos + "alumnos";
		             strAsistencia=strAsistencia + CursosHelper.getCourseName(value)+ " alumnos" ;
				 }	
		****/ 
				Iterator it = mapaAsistenciaAlumnosPorCursos.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry e = (Map.Entry)it.next();
					
					strAsistencia=strAsistencia + "Curso:"+ e.getValue().toString() +contadorAlumnos + "alumnos";
				}	
	
					
			 	start=formater.format(datePrevious);
				end=formater.format(datePrevious);
				strAsistencia="Número de alumnos:"+contadorAlumnos;
				strAsistencia+=" / Número de profesores:"+contadorProfesores;				
				String id="";
				if(regAsistencias.size()>0){
						id=regAsistencias.get(regAsistencias.size()-1).getRowId()+"";							
						
						events=events+"{"+
						"id:'"+id+"',"+
						"title:'"+strAsistencia+"',"+
						"start:'"+start+"',"+
						"end:'"+end+"',"+
						"url:'"+url+id+")',"+
						"},\r\n";
					
					if(regAsistencias.get(regAsistencias.size()-1).getIsalumno() )
						contadorAlumnos=1;
					else
						contadorProfesores=1;
					
				}
			
		
		/**
		 * {
	                title: 'My Event',
	                start: '2014-02-01',
	                url: ''
	            },
	            {
	                title: '2 My Event',
	                start: '2014-02-02',
	                url: ''
	            }
	            
		 */
		System.out.println("El contenido es:"+events+ "y ya esta");
		return events;
		
	}	
	
	
	public void processRequest(HttpServletRequest request) throws Exception{
		String idCurso="";
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart){
			// Create a factory for disk-based file items
			FileItemFactory factory = new DiskFileItemFactory();
			
			// Configure a repository (to ensure a secure temp location is used)
			ServletContext servletContext = request.getSession().getServletContext();
			File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
			
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// Parse the request
			
			List<FileItem> items = upload.parseRequest(request);
			 
			int read = 0;
			String fileName="";//fields.getDescText().replace(" ", "");
			
			byte[] bytes = new byte[1024];
			DiskFileItem fileItem=null;
			String fileNames="";
			String pathWord="";
			for(int i=0;i<items.size();i++){
				if (items.get(i).getFieldName().equals("upWord")){
						fileItem=(DiskFileItem)items.get(i);
						log.debug("Writting to :"+getRealPath());
						
						File uploadedFile = new File(pathToWords  + fileItem.getName());
						//File uploadedFile = new File(getRealPath() + "/images/" + fileItem.getName());
						pathWord=pathToWords  + fileItem.getName();
						uploadedFile.createNewFile();
						System.out.println(uploadedFile.getAbsolutePath());
						if(!fileNames.equals("")){
							fileNames=fileNames+","+fileItem.getName();
						}else{
							fileNames=fileItem.getName();
						}
						items.get(i).write(uploadedFile);
						fileName=fileItem.getName();
				  }
				
			}
			//fields.setImage(fileNames);
			extractor.readAndSave(pathWord);
			//uploadExceltoDatabase(pathToWords, fileName, Integer.parseInt(idCurso));

		}

		
		
	}
	public String getPathToWords() {
		return pathToWords;
	}
	public void setPathToWords(String pathToWords) {
		this.pathToWords = pathToWords;
	}
	public WordExtractor getExtractor() {
		return extractor;
	}
	public void setExtractor(WordExtractor extractor) {
		this.extractor = extractor;
	}

	
}
