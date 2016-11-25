package com.dynamicdroides.vdc.services.business;

import org.hibernate.Transaction;

import com.dynamicdroides.db.virgendelcarmen.GeneralData;
import com.dynamicdroides.db.virgendelcarmen.GeneralDataDAO;

public class DatosGeneralesBusiness {

	private GeneralDataDAO dao;

	public GeneralDataDAO getDao() {
		return dao;
	}

	public void setDao(GeneralDataDAO dao) {
		this.dao = dao;
	}
	
	public GeneralData getElement(Integer id){
		
		return dao.findById(id);
	}
	public void save(GeneralData data){
		Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		dao.getHibernateTemplate().getSessionFactory().getCurrentSession().save(data);
		
		t.commit();
	}
	public void update(GeneralData data){
		Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		dao.getHibernateTemplate().getSessionFactory().getCurrentSession().update(data);
		
		t.commit();
	}
	public void delete(GeneralData data){
		Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		dao.getHibernateTemplate().getSessionFactory().getCurrentSession().delete(data);
		
		t.commit();
	}
}
