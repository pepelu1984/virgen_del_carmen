package com.dynamicdroides.vdc.services.login.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

import com.dynamicdroides.db.virgendelcarmen.Observaciones;
import com.dynamicdroides.db.virgendelcarmen.ObservacionesDAO;

public class ObservacionesController  extends IControlador{
	 static Logger log = Logger.getLogger(IndexController.class);
	 private ObservacionesDAO dao;
	 
	public ObservacionesDAO getDao() {
		return dao;
	}
	public void setDao(ObservacionesDAO dao) {
		this.dao = dao;
	}
		private HashMap<Integer,String> namecursos=new HashMap<Integer, String>();
		@Override
		public ModelAndView handleRequest(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			// TODO Auto-generated method stub
			super.init(request);
		
			View view2=null;
			Map<String, Object> myModel = new HashMap<String, Object>();
			//updateModel(myModel,request);
			try{

				log.error("*******************");
				if(request.getParameter("event")!=null){
					if(request.getParameter("event").equals("index")){
						
						
						view2=new InternalResourceView("jsp/admin/observaciones.jsp");
						return new ModelAndView(view2, "model", myModel);
//				    	
//						String data=this.getOnlineJsonData(educadores);
//						request.setAttribute("datoJSON", data);
//
//						return new ModelAndView("//ajax", "model", myModel);
//					
					}else if(request.getParameter("event").equals("list")){
						
						List<Observaciones> observaciones= dao.findAll();
						com.dynamicdroides.vdc.services.beans.JSONBean bean=new com.dynamicdroides.vdc.services.beans.JSONBean();
						Object[] li=(Object[])observaciones.toArray();
						bean.setRows(li);
						bean.setPage(1);
						bean.setRecords(observaciones.size());
						bean.setTotal(observaciones.size());

						String data= serializeBean(bean);
						request.setAttribute("datoJSON", data);

						return new ModelAndView("//ajax", "model", myModel);
						
					}else if(request.getParameter("event").equals("add")){
						
						
						view2=new InternalResourceView("jsp/admin/add_observaciones.jsp");
						return new ModelAndView(view2, "model", myModel);
					}else if(request.getParameter("event").equals("save")){

						Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
						
						Observaciones observ=new Observaciones();
						String observacion=request.getParameter("txtObservacion");
						observ.setObservaciones(observacion);
						dao.getHibernateTemplate().getSessionFactory().getCurrentSession().save(observ);
						t.commit();
						
						return new ModelAndView("//ajax", "model", myModel);
					}else if(request.getParameter("event").equals("save_edit")){

						Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
						Integer idobservacion=Integer.parseInt(request.getParameter("idobservacion"));
						Observaciones observ=new Observaciones();
						observ.setIdobservacion(idobservacion);
						String observacion=request.getParameter("txtObservacion");
						observ.setObservaciones(observacion);
						dao.getHibernateTemplate().getSessionFactory().getCurrentSession().save(observ);
						t.commit();
						
						return new ModelAndView("//ajax", "model", myModel);
						
					}else if(request.getParameter("event").equals("edit")){
						
						view2=new InternalResourceView("jsp/admin/edit_observaciones.jsp");
						return new ModelAndView(view2, "model", myModel);
					}else if(request.getParameter("event").equals("delete")){
						
						Integer idobservacion=Integer.parseInt(request.getParameter("idobservacion"));
						Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
						
						Observaciones observ=new Observaciones();
						//observ.setIdobservacion(idobservacion);
						observ=dao.findById(idobservacion);
						dao.getHibernateTemplate().getSessionFactory().getCurrentSession().delete(observ);
						t.commit();
						
						return new ModelAndView("//ajax", "model", myModel);
						
					}
						
				}
					
					
				view2=new InternalResourceView("jsp/admin/observaciones.jsp");
				return new ModelAndView(view2, "model", myModel);
		    		
			}catch(Exception e){
				e.printStackTrace();
				return returnErrorControl(myModel, e);
			}finally{
				//este codigo queremos que se ejecute si o si
				
				
			}
		}
			

}
