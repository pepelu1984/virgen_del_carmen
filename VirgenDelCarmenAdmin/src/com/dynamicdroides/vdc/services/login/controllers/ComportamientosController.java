package com.dynamicdroides.vdc.services.login.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

import com.dynamicdroides.beans.ComportamientoBean;
import com.dynamicdroides.db.virgendelcarmen.RegComportamiento;
import com.dynamicdroides.vdc.services.business.VirgenDelCarmenBusiness;

public class ComportamientosController extends IControlador {
	 private VirgenDelCarmenBusiness business;
		
	public VirgenDelCarmenBusiness getBusiness() {
		return business;
	}

	public void setBusiness(VirgenDelCarmenBusiness business) {
		this.business = business;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		super.init(request);
		
		View view2=null;
		Map<String, Object> myModel = new HashMap<String, Object>();
		
		
		
		try{
			log.error("*******************");
			if(request.getParameter("idalumno")!=null){
				int idalumno=0;
				String nombre="";
				String apellidos="";
				
				idalumno=Integer.parseInt(request.getParameter("idalumno"));
				
				myModel.put("idalumno", idalumno);
				myModel.put("namealumno", request.getParameter("nombre")+ " "+request.getParameter("apellidos"));
				myModel.put("curso", request.getParameter("curso"));
				myModel.put("correopadres", request.getParameter("correopadres"));
				
				myModel.put("events", generateJSCalendarEvents(request.getContextPath(),business.getComportamientos(idalumno)));
				
				//TODO agregar aqui toda la informacion de comportamiento, para generar los eventos javascript del calendario
				/**
				 * {
			                title: 'My Event',
			                start: '2014-02-01',
			                url: ''
			            },
			            {
			                title: '2 My Event',
			                start: '2014-02-02',
			                url: ''
			            }
			            
				 */
				
				
				view2=new InternalResourceView("jsp/alumnos_comportamientos.jsp");
				return new ModelAndView(view2, "model", myModel);
			}
			if(request.getParameter("event")!=null){
				String evento=""+request.getParameter("event");
				if(evento.equals("list")){
					String idalumno2=""+request.getParameter("idparent");
					Date lunes=new Date();
					Date domingo=new Date();
					Calendar cal=Calendar.getInstance();
					cal.setTime(lunes);
					cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
					lunes=cal.getTime();
					cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek()+6);
					domingo=cal.getTime();
					
					List<RegComportamiento> comportamientos=business.findComportamientosByAlumno(Integer.parseInt(idalumno2),lunes,domingo);
					
					String data=this.getOnlineJsonData(comportamientos);
					request.setAttribute("datoJSON", data);
				
					return new ModelAndView("//ajax", "model", myModel);
			
					
				}else if(evento.equals("detail")){
					
					
				}else if(evento.equals("save_edit")){
					
				}else if(evento.equals("save")){
					
					
					
				}else{
					
					
				}
			}
			
			
	    	view2=new InternalResourceView("jsp/comportamientos.jsp");
			return new ModelAndView(view2, "model", myModel);
	    		
		}catch(Exception e){
			e.printStackTrace();
			return returnErrorControl(myModel, e);
		}finally{
			//este codigo queremos que se ejecute si o si
			
			
		}
		
	}
	public String generateJSCalendarEvents(String contextPath,List<RegComportamiento> comportamientos){
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat formater=new SimpleDateFormat("yyyy-MM-dd");
		String start="";
		String end="";
		String title="";
		String events="";
		String url=contextPath+"/comportamiento.vdc?event=detail&idcomportamiento=";
		String datosTotal="";
		Date startDate=null;
		boolean timeHasChanged=true;
		if(comportamientos==null || comportamientos.size()==0)return "";
		for(RegComportamiento reg:comportamientos){
			
			if(startDate!=null && startDate.before(reg.getFechadesde())){
				datosTotal=datosTotal+reg.getTipo()+":"+reg.getValor()+" - ";
				start=formater.format(reg.getFechadesde());
				end=formater.format(reg.getFechahasta());
				events=events+"{"+
				"title:'"+datosTotal+"',"+
				"start:'"+start+"',"+
				"end:'"+end+"',"+
				"url:'"+url+"',"+
				"},\r\n";
				startDate=reg.getFechadesde();
				datosTotal="";
			}else{
				datosTotal=datosTotal+reg.getTipo()+":"+reg.getValor()+" - ";
				startDate=reg.getFechadesde();
			}
		}
		/**
		 * {
	                title: 'My Event',
	                start: '2014-02-01',
	                url: ''
	            },
	            {
	                title: '2 My Event',
	                start: '2014-02-02',
	                url: ''
	            }
	            
		 */
		return events;
		
	}
	public String getOnlineJsonData(List<RegComportamiento> comportamientos) throws Exception{
		com.dynamicdroides.vdc.services.beans.JSONBean bean=new com.dynamicdroides.vdc.services.beans.JSONBean();
		ComportamientoBean beans[]=new ComportamientoBean[comportamientos.size()];
		ComportamientoBean regBean=null;
		int i=0;
		SimpleDateFormat formater= new SimpleDateFormat("dd-MM-yyyy");
		
		
		for(RegComportamiento reg:comportamientos){
			regBean=new ComportamientoBean();
			regBean.setFechadesde(formater.format(reg.getFechadesde()));
			regBean.setFechahasta(formater.format(reg.getFechahasta()));
			regBean.setIdalumno(reg.getIdalumno());
			regBean.setIdregcompor(reg.getIdregcompor());
			regBean.setObservaciones(reg.getObservaciones());
			regBean.setTipo(reg.getTipo());
			regBean.setValor(reg.getValor());
			beans[i]=regBean;
			      i++;
		}
		
		bean.setRows(beans);
		bean.setPage(1);
		bean.setRecords(comportamientos.size());
		bean.setTotal(comportamientos.size());

		return serializeBean(bean);
		
	}
}
