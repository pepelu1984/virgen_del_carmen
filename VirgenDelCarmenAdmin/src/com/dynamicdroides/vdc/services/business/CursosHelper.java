package com.dynamicdroides.vdc.services.business;

import java.util.HashMap;
import java.util.List;

import com.dynamicdroides.db.virgendelcarmen.Cursos;
import com.dynamicdroides.db.virgendelcarmen.CursosDAO;
import com.dynamicdroides.services.tools.SpringApplicationContexter;
import com.dynamicdroides.tools.SpringApplicationContext;

public class CursosHelper {
	private static HashMap<Integer,String> namecursos=new HashMap();
	private static HashMap<String,Integer> idcursos=new HashMap();
	private static List<Cursos> cursos=null;
	
	static{
		
		CursosDAO cursosdao=(CursosDAO)SpringApplicationContexter.getBean("cursosDAO","virgin-servlet.xml");
		cursos=cursosdao.findAll();
	
		for(Cursos course:cursos){
			namecursos.put(course.getIdcurso(), course.getNombre());
			idcursos.put(course.getNombre(), course.getIdcurso());
			
		}		
	}
	public static List<Cursos> getAllCursos(){
		return cursos;
	}
	public static String getCourseName(int id){
		return namecursos.get(id);
	}
	public static Integer getCourseID(String name){
		return idcursos.get(name);
	}
	
	public  static List<Cursos> OrderedByPosition(List<Cursos> cursos){
		int a,b, posicion, id,total;
		String nombre,responsable;
		Boolean isprimaria;	
		total=cursos.size();
		if (cursos!=null){	
			for(b=0;b<(total-1);b++){		
				for(a=0;a<(total-b-1);a++){						
					if(cursos.get(a).getPosicion()>cursos.get(a+1).getPosicion()){
						id=cursos.get(a).getIdcurso();
						isprimaria=cursos.get(a).getIsprimaria();
						nombre=cursos.get(a).getNombre();
						posicion=cursos.get(a).getPosicion();
						responsable=cursos.get(a).getResponsable();					
						
						cursos.get(a).setIdcurso(cursos.get(a+1).getIdcurso());				
						cursos.get(a).setIsprimaria(cursos.get(a+1).getIsprimaria());				
						cursos.get(a).setNombre(cursos.get(a+1).getNombre());
						cursos.get(a).setPosicion(cursos.get(a+1).getPosicion());
						cursos.get(a).setResponsable(cursos.get(a+1).getResponsable());	
						
						cursos.get(a+1).setIdcurso(id);
						cursos.get(a+1).setIsprimaria(isprimaria);
						cursos.get(a+1).setNombre(nombre);
						cursos.get(a+1).setPosicion(posicion);
						cursos.get(a+1).setResponsable(responsable);				
						//a=0;						
					}
				}	
			}	
		}		
		return cursos;
	}		
}
