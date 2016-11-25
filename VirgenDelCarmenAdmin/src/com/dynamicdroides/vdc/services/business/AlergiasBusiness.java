package com.dynamicdroides.vdc.services.business;

import org.hibernate.Transaction;

import com.dynamicdroides.db.virgendelcarmen.Alergias;
import com.dynamicdroides.db.virgendelcarmen.AlergiasDAO;
import com.dynamicdroides.db.virgendelcarmen.Profesores;

public class AlergiasBusiness {

	private AlergiasDAO dao;
	public void save(Alergias alergia){
		Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		dao.getHibernateTemplate().getSessionFactory().getCurrentSession().save(alergia);
		t.commit();
	}
	public void update(Alergias alergia){
		Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		dao.getHibernateTemplate().getSessionFactory().getCurrentSession().update(alergia);
		t.commit();
	}

	public void delete(Alergias alergia){
		Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		dao.getHibernateTemplate().getSessionFactory().getCurrentSession().delete(alergia);
		t.commit();
	}

	
	public AlergiasDAO getDao() {
		return dao;
	}

	public void setDao(AlergiasDAO dao) {
		this.dao = dao;
	}
	
}
