package com.dynamicdroides.vdc.services.business;

import org.hibernate.Transaction;

import com.dynamicdroides.db.virgendelcarmen.Profesores;
import com.dynamicdroides.db.virgendelcarmen.ProfesoresDAO;

public class ProfesoresBusiness {
	private ProfesoresDAO dao;
	public void save(Profesores profesor){
		Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		dao.getHibernateTemplate().getSessionFactory().getCurrentSession().save(profesor);
		t.commit();
	}
	public void update(Profesores profesor){
		Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		dao.getHibernateTemplate().getSessionFactory().getCurrentSession().update(profesor);
		t.commit();
	}

	public void delete(Profesores profesor){
		Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		dao.getHibernateTemplate().getSessionFactory().getCurrentSession().delete(profesor);
		t.commit();
	}
	
	public ProfesoresDAO getDao() {
		return dao;
	}

	public void setDao(ProfesoresDAO dao) {
		this.dao = dao;
	}
	

}
