package com.dynamicdroides.virgendelcarmen.manager;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.dynamicdroides.db.virgendelcarmen.Alumnos;


@XmlSeeAlso(Alumnos.class)
public class DataService {
	VirgenDelCarmenManager manager=new VirgenDelCarmenManager();
	
	public List<Alumnos> getAlumnos(int curso,String user,String password){
		
		return manager.getAlumnos(curso,user,password);
		
	}

}
