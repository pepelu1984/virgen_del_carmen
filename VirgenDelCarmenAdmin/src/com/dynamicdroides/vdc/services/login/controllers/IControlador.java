package com.dynamicdroides.vdc.services.login.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.dynamicdroides.vdc.services.beans.JSONBean;
import com.dynamicdroides.vdc.services.crm.exceptions.ValidateException;

import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;


import flexjson.JSONSerializer;

/**
 * Esta clase (abstracta) controla muchas funcionalidades de toda la aplicaci�n.
 * Hay muchos m�todos declarados aqui que son reutilizados por el resto de objetos controller    
 * @author Felipe Escobar (fescobar)
 *  
 */
public abstract class IControlador implements Controller{
	
	protected boolean isLogged=false;
	public String getRealPath(){
		java.net.URL r = this.getClass().getClassLoader().getResource("/");
	    String filePath = r.getFile();
	    String pathReal = new File(new File(new File(filePath).getParent()).getParent()).getParent();

	    return pathReal+"/"+nameApp+"/";
	}
	 private static final String nameApp="VirgenDelCarmenAdmin";

	public String getRealPath(HttpServletRequest request){
		try{
			java.net.URL r = this.getClass().getClassLoader().getResource("/");
			String filepath2=request.getSession().getServletContext().getRealPath("/");
			System.out.println(filepath2);
		    String pathReal = new File(new File(new File(filepath2).getParent()).getParent()).getParent();

		    return filepath2;
			
		}catch(Exception e){
			System.out.println("ERRRO EN GETREALPATH");
			e.printStackTrace();
			return "";
			
		}
	}
	protected final Log log = LogFactory.getLog(getClass());
	
	static{
		//init();
		//BasicConfigurator.configure();
	}
	public IControlador(){
		
	}
	public String generateDetailLink(String texto,String value){
		String link=texto;
		//String link="<a href='javascript:loadDetail(\""+value+ "\")'>"+texto+"</a>";
		
		return link;
	}
	
	
	public void init(HttpServletRequest request){
		
		log.debug("init()");
		//Device deviceInfo = DeviceUtils.getCurrentDevice(request);
		/*
		User userLogged=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Set<GrantedAuthority> grants=(Set<GrantedAuthority>)userLogged.getAuthorities();

		Iterator<GrantedAuthority> ite=grants.iterator();
		List<SimpleGrantedAuthority> auth=new ArrayList<SimpleGrantedAuthority>();

		while (ite.hasNext()){
			GrantedAuthority elem=ite.next();
			String auth2=((SimpleGrantedAuthority)elem).getAuthority();
			
			if(auth2.equals("ROLE_ES")){
				
				request.getSession().setAttribute("user_name", userLogged.getUsername());
				request.getSession().setAttribute("user_obj", userLogged.getUsername());
				//IDIOMA por defecto
				request.getSession().setAttribute("lang", "ESN");
				
				request.getSession().setAttribute("role_user", auth2);
				
				String ip=getClientIpAddr(request);
				System.out.println(new Date()+" - CLIENT IP Access:"+ip);
				log.info("CLient IP Access:"+ip);
			}
		
		}
		
		*/
		/*
		if((deviceInfo.isMobile() || deviceInfo.isTablet()) && request.getParameter("isapp")!=null){
			request.getSession().setAttribute("isTablet", true);
		}else{
			request.getSession().setAttribute("isTablet", false);			
		}
		*/
		
		
	}
	public static String getClientIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public ModelAndView returnErrorControl(Map<String, Object> myModel,Exception e){
		ModelAndView vista=new ModelAndView();
		myModel.clear();
		
		if(e instanceof ValidateException){
			
			myModel.put("mensajeSistema","Error en la aplicaci&oacute;n:"+((ValidateException)e).getMyexception());
			myModel.put("mensajeUsuario","Error en la aplicaci&oacute;n:"+((ValidateException)e).getMyexception());			
			vista = new ModelAndView("//ajax", "model", myModel);
			return vista;
		}else if(e instanceof Exception){
			
			myModel.put("mensajeSistema","Error en la aplicaci&oacute;n:"+e.getMessage());
			myModel.put("mensajeUsuario","Error en la aplicaci&oacute;n:"+e.getMessage());			
			vista = new ModelAndView("//ajax", "model", myModel);
			return vista;
		 
		}else{
			myModel.put("mensajeSistema","Error en la aplicaci&oacute;n:"+e.getMessage());
			myModel.put("mensajeUsuario","Error en la aplicaci&oacute;n:"+e.getMessage());
			vista = new ModelAndView("//ajax/", "model", myModel);
			return vista;
		}
	}
//	public String getComboFromValores(List<ListaValoresBean> listaValores,String name,String idSelected,String functionChange,boolean isCombo){
//		if(isCombo){
//			return getComboFromValores(listaValores,name,idSelected,functionChange);
//		}else{
//			//aqui poner solo un input text
//			return "";
//		}
//	}
//		public String getComboFromValores(List<ListaValoresBean> listaValores,String name,String idSelected,String functionChange){
//		String dato="<select class='cselect' id='"+name+"' name='"+name+"' onChange='javascript:"+functionChange+"()'>";
//		if(listaValores==null){
//		
//			dato=dato+"</select>";
//			return dato;
//		}
//		dato=dato+"<option value=''><a href='#'>Seleccione un registro...";
//		dato=dato+"</a></option>";
//		for(int i=0;i<listaValores.size();i++){
//			
//			if(idSelected!=null && !idSelected.equals("") && idSelected.equals(""+listaValores.get(i).getLic())){
//				dato=dato+"<option selected value='"+listaValores.get(i).getLic()+"'>"+listaValores.get(i).getValue();
//			}else{
//				dato=dato+"<option value='"+listaValores.get(i).getLic()+"'>"+listaValores.get(i).getValue();
//			}
//			dato=dato+"</option>";
//		}
//		dato=dato+"</select>";
//		return dato;
//		
//		
//	}
//	public String getMultiComboFromValores(List<ListaValoresBean> listaValores,String name,String idSelected){
//		
//		String dato="<select multiple id='"+name+"' name='"+name+"'>";
//		for(int i=0;i<listaValores.size();i++){
//			if(idSelected!=null && !idSelected.equals("") && idSelected.equals(""+listaValores.get(i).getRowId())){
//				dato=dato+"<option selected value='"+listaValores.get(i).getRowId()+"'>"+listaValores.get(i).getDescTex();
//			}else{
//				dato=dato+"<option value='"+listaValores.get(i).getRowId()+"'>"+listaValores.get(i).getDescTex();
//			}
//			dato=dato+"</option>";
//		}
//		dato=dato+"</select>";
//		return dato;
//		
//		
//	}
	public String getColumnsJSONFormat(){
		
		//colNames:['name','surname1','surname2']
		String cadenaColumnas="[";
		
		
		return "";
		
	}

	public String serializeBeans(Object[] objectos){
		JSONSerializer serializer = new JSONSerializer();
		String jsondata=serializer.deepSerialize(objectos);
		jsondata=jsondata.replaceFirst("[", "");
		jsondata=jsondata.substring(0,jsondata.length()-4)+ jsondata.substring(jsondata.length()-4).replace("]", "");
		
		
		return jsondata;
	}
	
	public String serializeBean(Object objecto){
		JSONSerializer serializer = new JSONSerializer();
		String jsondata=serializer.deepSerialize(objecto);
		jsondata=jsondata.replace("[", "");
		return jsondata;
	}
	
	public Boolean checkIFNotNull(Object obj){
		if (obj!=null){
			String s=(String)obj;
			if ((s.trim().equals("null")) || (s.trim().equals("")) ){
				return false;
			}
			else return true;
		}
		else{
			return false;
		
		}
	}

	

	public String nosci(double d) {
	    if(d < 0){
	        return "-" + nosci(-d);
	    }
	    String javaString = String.valueOf(d);
	    int indexOfE =javaString.indexOf("E"); 
	    if(indexOfE == -1){
	        return javaString;
	    }
	    StringBuffer sb = new StringBuffer();
	    if(d > 1){//big number
	        int exp = Integer.parseInt(javaString.substring(indexOfE + 1));
	        String sciDecimal = javaString.substring(2, indexOfE);
	        int sciDecimalLength = sciDecimal.length();
	        if(exp == sciDecimalLength){
	            sb.append(javaString.charAt(0));
	            sb.append(sciDecimal);              
	        }else if(exp > sciDecimalLength){
	            sb.append(javaString.charAt(0));
	            sb.append(sciDecimal);
	            for(int i = 0; i < exp - sciDecimalLength; i++){
	                sb.append('0');
	            }
	        }else if(exp < sciDecimalLength){
	            sb.append(javaString.charAt(0));
	            sb.append(sciDecimal.substring(0, exp));
	            sb.append('.');
	            for(int i = exp; i < sciDecimalLength ; i++){
	                sb.append(sciDecimal.charAt(i));
	            }
	        }
	      return sb.toString();
	    }else{
	        //for little numbers use the default or you will
	        //loose accuracy
	        return javaString;
	    }       
	}
//	/**
//	 * Metodo para generar un elemento lista en un combo para el grid. 
//	 * Generara un string con este formato:"DIV1:Division1;DIV2:Division2;Div3:Division3;Div4:Division 4"
//	 * @param lstValores
//	 * @return object of type String
//	 */
//	public String getPickLstForGrid(List<ListaValoresBean> lstValores)
//	{
//		String listaValores=":;";
//		for(int i=0;i<lstValores.size();i++){
//			if(i==(lstValores.size()-1)){
//				listaValores=listaValores+""+lstValores.get(i).getLic()+":"+lstValores.get(i).getValue();
//			}else{
//				listaValores=listaValores+""+lstValores.get(i).getLic()+":"+lstValores.get(i).getValue()+";";
//			}
//		}
//
//		return listaValores;
//	}
	

	public void exportToExcel(JSONBean beanList,String pathExcel,String nameFile) throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{

//		
		CSVWriter writer = new CSVWriter(new FileWriter(pathExcel+"/"+nameFile+".csv"), '\t');
		Object[] rows=beanList.getRows();
	     String[] entries2=new String[rows.length];
	     
	     //TODO poner las columnas a escribir, o a quitar.Por ejemplo, class
	     
		for(int i=0;i<rows.length;i++){
			Map<String, Object> properties=BeanUtils.describe(rows[i]);
			Set ll=properties.entrySet();
			Iterator it=ll.iterator();
			Object[] subentries=null;
			while(it.hasNext()){
				String prop=""+it.next();
				//System.out.print(prop);
				subentries= prop.split("=");//dato=valor
				entries2[i]=entries2[i]+","+subentries[1];
			}
			writer.writeNext(entries2);
			//System.out.println("");
		}
		writer.close();

	}
	
	
	public List generateListFromExcel(Reader reader){
		
		ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
		strat.setType(JSONBean.class);
		String[] columns = new String[] {"name", "orderNumber", "id"}; // the fields to bind do in your JavaBean
		strat.setColumnMapping(columns);

		CsvToBean csv = new CsvToBean();
		List list = csv.parse(strat, reader);
		return list;
		
	}

	public Timestamp todaysDate() throws ParseException{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String str = sdf.format(cal.getTime());
		Timestamp today = new Timestamp(new Date().getTime());
		return today;
	}
	
	public String todaysStr() throws ParseException{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String str = sdf.format(cal.getTime());
		return str;
	}
	   
	public String timestampToString(Timestamp t) throws ParseException {
		String fecha="";
		if (t!=null) {
			
			fecha = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(t);
		}
		return fecha;
	}
	
}
