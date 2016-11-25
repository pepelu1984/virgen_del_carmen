package com.dynamicdroides.vdc.services.business;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dynamicdroides.db.virgendelcarmen.Menu;
import com.dynamicdroides.db.virgendelcarmen.MenuDAO;
import com.dynamicdroides.db.virgendelcarmen.RegComportamiento;

public class MenuBusiness {
	

	private MenuDAO dao;

	public List<Menu> getMenus(){
		
		
		return dao.findAll();
	}
	public Menu findByID(int idmenu){
		Session s=dao.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction t=s.beginTransaction();
		List<Menu> elements=s.createQuery("select model from Menu model where model.idmenu="+idmenu+"").list();
		t.commit();
		if(elements!=null && elements.size()>0){
			return elements.get(0);
		}else{
			return null;
		}
		
	}
	public boolean update(Menu menu){
		
		Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
		dao.getHibernateTemplate().getSessionFactory().getCurrentSession().update(menu);
		try{
			t.commit();
		}catch(Exception e){
			
		}
		return true;
		
	}
	public MenuDAO getDao() {
		return dao;
	}

	public void setDao(MenuDAO dao) {
		this.dao = dao;
	}

}
