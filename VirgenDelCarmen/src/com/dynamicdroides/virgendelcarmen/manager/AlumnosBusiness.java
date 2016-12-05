package com.dynamicdroides.virgendelcarmen.manager;

import java.util.List;

import org.hibernate.Transaction;
import org.springframework.cache.annotation.Cacheable;

import com.dynamicdroides.db.virgendelcarmen.Alumnos;
import com.dynamicdroides.db.virgendelcarmen.AlumnosDAO;
import com.dynamicdroides.party.standalone.SpringApplicationContext;

public class AlumnosBusiness {

    private AlumnosDAO alumnosDAO;
    

	@Cacheable(value="alumnos.data",key="#listIds")
	public List<Alumnos> getAlumnosByList(String listIds){
		
		Transaction t2 = alumnosDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		List<Alumnos> lstAlumnos=alumnosDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select distinct alumnos from Alumnos alumnos where alumnos.idalumno in("+listIds+")").list();
	    t2.commit();
	    return lstAlumnos;
	    
		
	}


	public AlumnosDAO getAlumnosDAO() {
		return alumnosDAO;
	}


	public void setAlumnosDAO(AlumnosDAO alumnosDAO) {
		this.alumnosDAO = alumnosDAO;
	}


}
