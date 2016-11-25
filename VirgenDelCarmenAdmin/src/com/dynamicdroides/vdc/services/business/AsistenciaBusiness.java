package com.dynamicdroides.vdc.services.business;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dynamicdroides.db.virgendelcarmen.RegAsistencia;
import com.dynamicdroides.db.virgendelcarmen.RegAsistenciaDAO;
import com.dynamicdroides.db.virgendelcarmen.RegComportamiento;

public class AsistenciaBusiness {

	private RegAsistenciaDAO dao;
	public List<RegAsistencia> findByUser(int iduser){
		
		Session s=dao.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction t=s.beginTransaction();
		List<RegAsistencia> elements=s.createQuery("select model from RegAsistencia model,Usuariosalumnos usuarios where model.idalumno=usuarios.idalumno AND usuarios.idusuario="+iduser+" ORDER BY model.fecha ASC").list();
		t.commit();
		if(elements.size()>0){
			return elements;
		}else{
			return null;
		}

		
	}
	
	public List<RegAsistencia> findByCurso(int idcurso){
		
		Session s=dao.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction t=s.beginTransaction();
		List<RegAsistencia> elements=s.createQuery("select model from RegAsistencia model,Alumnos alumnos where model.idalumno=alumnos.idalumno AND alumnos.curso="+idcurso+" ORDER BY model.fecha ASC").list();
		t.commit();
		if(elements.size()>0){
			return elements;
		}else{
			return null;
		}

		
	}
	public RegAsistenciaDAO getDao() {
		return dao;
	}

	public void setDao(RegAsistenciaDAO dao) {
		this.dao = dao;
	}
	
}
