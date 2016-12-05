package com.dynamicdroides.virgendelcarmen.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dynamicdroides.db.virgendelcarmen.Cursos;
import com.dynamicdroides.db.virgendelcarmen.CursosDAO;
import com.dynamicdroides.party.standalone.SpringApplicationContext;


public class CursosHelper {
	private static HashMap<Integer,String> namecursos=new HashMap();
	private static HashMap<Integer,Cursos> lstcursos=new HashMap();
	private static List<Cursos> cursos=new ArrayList();
	
	static{
		CursosDAO cursosdao=(CursosDAO)SpringApplicationContext.getBean("cursosDAO");
		List<Cursos> cursos=cursosdao.findAll();
		for(Cursos course:cursos){
			namecursos.put(course.getIdcurso(), course.getNombre());
			lstcursos.put(course.getIdcurso(), course);
		}		
	}
	public static String getCourseName(int id){
		return namecursos.get(id);
	}
	public static List<Cursos> getAll(){
		return cursos;
	}
	public static boolean isCourseSemanal(int id){
		Cursos curso=lstcursos.get(id);
		if(curso.getIsprimaria()){
			return false;
		}else{
			return true;
			
		}
	}
		
}
