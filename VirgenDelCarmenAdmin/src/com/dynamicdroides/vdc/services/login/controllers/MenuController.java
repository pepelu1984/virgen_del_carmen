package com.dynamicdroides.vdc.services.login.controllers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

import com.dynamicdroides.db.virgendelcarmen.Menu;
import com.dynamicdroides.vdc.services.business.MenuBusiness;
import com.dynamicdroides.vdc.services.business.WordExtractor;

public class MenuController extends IControlador{

	public MenuBusiness getMenuBusiness() {
		return menuBusiness;
	}
	public void setMenuBusiness(MenuBusiness menuBusiness) {
		this.menuBusiness = menuBusiness;
	}
	private WordExtractor extractor;
	private String pathToWords;
	private MenuBusiness menuBusiness;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		super.init(request);
		request.setCharacterEncoding("UTF-8"); 		
		View view2=null;
		Map<String, Object> myModel = new HashMap<String, Object>();
		//updateModel(myModel,request);
		try{

			log.error("*******************");
			if(request.getParameter("event")!=null){
				if(request.getParameter("event").equals("index")){
					

				}else if(request.getParameter("event").equals("edit")){
					int idmenu=Integer.parseInt(request.getParameter("idmenu"));
					
					Menu menu=menuBusiness.findByID(idmenu);
					myModel.put("menu", menu);
					view2=new InternalResourceView("jsp/admin/edit_menu.jsp");
					return new ModelAndView(view2, "model", myModel);

				}else if(request.getParameter("event").equals("save_edit")){
					
					int idmenu=Integer.parseInt(request.getParameter("idmenu"));
					String ensalada=request.getParameter("ensalada");
					String plato1=request.getParameter("plato1");
					String plato2=request.getParameter("plato2");
					String postre=request.getParameter("postre");
					
					Menu menu=menuBusiness.findByID(idmenu);
					menu.setEnsalada(ensalada);
					menu.setPlato1(plato1);
					menu.setPlato2(plato2);
					menu.setPostre(postre);
					menuBusiness.update(menu);
					
					myModel.put("mensajeUsuario", "Registro modificado correctamente");
					
					return new ModelAndView("//ajax", "model", myModel);
				}else if(request.getParameter("event").equals("menulist")){
					
					List<Menu> listMenus=menuBusiness.getMenus();
					String calendarMenus=generateJSMenuCalendarEvents(request.getContextPath(),listMenus);
					myModel.put("events", calendarMenus);

			    	view2=new InternalResourceView("jsp/admin/menu.jsp");
					return new ModelAndView(view2, "model", myModel);

				}else{
					//INI NUEVO WORD
					String event=request.getParameter("event");
					processRequest(request);
//					List<Menu> listMenus=menuBusiness.getMenus();
//					String calendarMenus=generateJSMenuCalendarEvents(request.getContextPath(),listMenus);
//					myModel.put("events", calendarMenus);
					return new ModelAndView("//ajax", "model", myModel);
					//FIN NUEVO WORD
					
				}
				
			}

			
			}catch(Exception e){
				e.printStackTrace();
				
				
			}
		
		return null;
	}
	public String generateJSMenuCalendarEvents(String contextPath,List<Menu> menus){
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat formater=new SimpleDateFormat("yyyy-MM-dd");
		String start="";
		String end="";
		String title="";
		String events="";
		//String url=contextPath+"/menu.vdc?event=edit&idmenu=";
		String url="javascript:editMenu(";
		String menuDia="";
		for(Menu menu:menus){
				menuDia="";
				start=formater.format(menu.getFecha());
				end=formater.format(menu.getFecha());
				//INI NUEVO WORD
				if(menu.getEnsalada()!=null && !menu.getEnsalada().equals("")){
					menuDia=menu.getEnsalada()+" - ";
				}
				if(menu.getPlato1()!=null && !menu.getPlato1().equals("")){
					menuDia=menuDia+menu.getPlato1()+" - ";
				}
				if(menu.getPlato2()!=null && !menu.getPlato2().equals("")){
					menuDia=menuDia+menu.getPlato2()+" - ";
				}
				if(menu.getPostre()!=null && !menu.getPostre().equals("")){
					menuDia=menuDia+menu.getPostre()+"   * ";
				}
				if(menu.getDescripcion1()!=null && !menu.getDescripcion1().equals("")){
					menuDia=menuDia+menu.getDescripcion1()+"  ";
				}
				if(menu.getDescripcion2()!=null && !menu.getDescripcion2().equals("")){
					menuDia=menuDia+menu.getDescripcion2()+"  ";
				}
				//FIN NUEVO WORD				
				events=events+"{"+
				"id:'"+menu.getIdmenu()+"',"+
				"title:'"+menuDia+"',"+
				"start:'"+start+"',"+
				"end:'"+end+"',"+
				"url:'"+url+menu.getIdmenu()+")',"+
				"},\r\n";
			
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

	
	public void processRequest(HttpServletRequest request) throws Exception{
		String idCurso="";
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart){
			// Create a factory for disk-based file items
			FileItemFactory factory = new DiskFileItemFactory();
			
			// Configure a repository (to ensure a secure temp location is used)
			ServletContext servletContext = request.getSession().getServletContext();
			File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
			
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// Parse the request
			
			List<FileItem> items = upload.parseRequest(request);
			 
			int read = 0;
			String fileName="";//fields.getDescText().replace(" ", "");
			
			byte[] bytes = new byte[1024];
			DiskFileItem fileItem=null;
			String fileNames="";
			String pathWord="";
			for(int i=0;i<items.size();i++){
				if (items.get(i).getFieldName().equals("upWord")){
						fileItem=(DiskFileItem)items.get(i);
						log.debug("Writting to :"+getRealPath());
						
						File uploadedFile = new File(pathToWords  + fileItem.getName());
						//File uploadedFile = new File(getRealPath() + "/images/" + fileItem.getName());
						pathWord=pathToWords  + fileItem.getName();
						uploadedFile.createNewFile();
						System.out.println(uploadedFile.getAbsolutePath());
						if(!fileNames.equals("")){
							fileNames=fileNames+","+fileItem.getName();
						}else{
							fileNames=fileItem.getName();
						}
						items.get(i).write(uploadedFile);
						fileName=fileItem.getName();
				  }
				
			}
			//fields.setImage(fileNames);
			extractor.readAndSave(pathWord);
			//uploadExceltoDatabase(pathToWords, fileName, Integer.parseInt(idCurso));

		}

		
		
	}
	public String getPathToWords() {
		return pathToWords;
	}
	public void setPathToWords(String pathToWords) {
		this.pathToWords = pathToWords;
	}
	public WordExtractor getExtractor() {
		return extractor;
	}
	public void setExtractor(WordExtractor extractor) {
		this.extractor = extractor;
	}
}
