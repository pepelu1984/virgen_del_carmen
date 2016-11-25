package com.dynamicdroides.vdc.services.business;

import java.util.List;

import org.hibernate.Transaction;

import com.dynamicdroides.db.virgendelcarmen.Menu;
import com.dynamicdroides.db.virgendelcarmen.MenuDAO;

public class PlantillaBusiness {

	private MenuDAO dao;

	public MenuDAO getDao() {
		return dao;
	}

	public void setDao(MenuDAO dao) {
		this.dao = dao;
	}
	public boolean save(Menu menu){

		Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		dao.getHibernateTemplate().getSessionFactory().getCurrentSession().save(menu);
//		t.commit();
		
		return true;
	}
	public boolean update(Menu menu){
		
		Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		dao.getHibernateTemplate().getSessionFactory().getCurrentSession().update(menu);
//		t.commit();
		
		return true;
	}
}
