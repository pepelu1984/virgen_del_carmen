package com.dynamicdroides.vdc.services.login.controllers;

import java.io.File;
import java.util.ArrayList;
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

import com.dynamicdroides.db.virgendelcarmen.Profesores;
import com.dynamicdroides.vdc.services.business.ProfesoresBusiness;
import com.dynamicdroides.vdc.services.tools.ExcelReader;

public class ProfesoresController extends IControlador{

	private ProfesoresBusiness business;
	private ExcelReader reader;
	
	 public ExcelReader getReader() {
		return reader;
	}

	public void setReader(ExcelReader reader) {
		this.reader = reader;
	}

	private String pathToExcels;
	 public String getPathToExcels() {
		return pathToExcels;
	}

	public void setPathToExcels(String pathToExcels) {
		this.pathToExcels = pathToExcels;
	}

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
			String event=request.getParameter("event");
			if(event!=null && event.equals("index")){
				
				view2=new InternalResourceView("jsp/admin/profesores.jsp");
				return new ModelAndView(view2, "model", myModel);
				
			}else if(event!=null && event.equals("list")){
				
				List<Profesores> profesores= business.getDao().findAll();
				String data=this.getOnlineJsonData(profesores);
				request.setAttribute("datoJSON", data);
			
				return new ModelAndView("//ajax", "model", myModel);
				
			}else if(event!=null && event.equals("add")){

				view2=new InternalResourceView("jsp/admin/add_profesor.jsp");
				return new ModelAndView(view2, "model", myModel);

			}else if(event!=null && event.equals("edit")){

				String idprofesor=request.getParameter("idprofesor");
				Profesores profesor= business.getDao().findById(Integer.parseInt(idprofesor));
				
				myModel.put("profesor", profesor);
				view2=new InternalResourceView("jsp/admin/edit_profesor.jsp");
				return new ModelAndView(view2, "model", myModel);

			}else if(event!=null && event.equals("save")){
				//String idprofesor=request.getParameter("idprofesor");
				String nombre=request.getParameter("txtName");
				String apellidos=request.getParameter("txtApellidos");
				String email=request.getParameter("txtEmail");
				String telefono=request.getParameter("txtTlf");
				
				Profesores profesor=new Profesores();
				//profesor.setIdprofesor(Integer.parseInt(idprofesor));
				profesor.setApellidos(apellidos);
				profesor.setNombre(nombre);
				profesor.setEmail(email);
				profesor.setTelefono(telefono);
				business.save(profesor);
				

			}else if(event!=null && event.equals("save_edit")){
				String idprofesor=request.getParameter("idprofesor");
				String nombre=request.getParameter("txtName");
				String apellidos=request.getParameter("txtApellidos");
				String email=request.getParameter("txtEmail");
				String telefono=request.getParameter("txtTlf");
				
				Profesores profesor=new Profesores();
				profesor.setIdprofesor(Integer.parseInt(idprofesor));
				profesor.setApellidos(apellidos);
				profesor.setNombre(nombre);
				profesor.setEmail(email);
				profesor.setTelefono(telefono);
				business.update(profesor);
				
				myModel.put("plantilla", "");	
				//
				return new ModelAndView("//ajax", "model", myModel);
			}else if(event!=null && event.equals("delete")){
				String idprofesor=request.getParameter("idprofesor");
				Profesores profesor=new Profesores();
				profesor.setIdprofesor(Integer.parseInt(idprofesor));
				profesor=business.getDao().findById(profesor.getIdprofesor());
				business.delete(profesor);
				
			}else{
				processRequest(request);
				
				
			}
			List<String> results = new ArrayList<String>();

			
			view2=new InternalResourceView("jsp/admin/profesores.jsp");
			return new ModelAndView(view2, "model", myModel);
			
    		
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
			return returnErrorControl(myModel, e);
		}finally{
			//este codigo queremos que se ejecute si o si
			
			
		}
		
	}
	public String getOnlineJsonData(List<Profesores> profesores) throws Exception{
		com.dynamicdroides.vdc.services.beans.JSONBean bean=new com.dynamicdroides.vdc.services.beans.JSONBean();
		Profesores beans[]=new Profesores[profesores.size()];
		int i=0;
		for(Profesores prof:profesores){
			beans[i]=prof;
   	      i++;
		}
		
		bean.setRows(beans);
		bean.setPage(1);
		bean.setRecords(profesores.size());
		bean.setTotal(profesores.size());

		return serializeBean(bean);
		
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
				if (items.get(i).getFieldName().equals("upExcel")){
						fileItem=(DiskFileItem)items.get(i);
						log.debug("Writting to :"+getRealPath());
						
						File uploadedFile = new File(pathToExcels  + fileItem.getName());
						//File uploadedFile = new File(getRealPath() + "/images/" + fileItem.getName());
						pathWord=pathToExcels  + fileItem.getName();
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
			reader.readAndSaveAllProfesores(pathToExcels, fileName);
			
			//uploadExceltoDatabase(pathToWords, fileName, Integer.parseInt(idCurso));

		}

		
		
	}
	public ProfesoresBusiness getBusiness() {
		return business;
	}

	public void setBusiness(ProfesoresBusiness business) {
		this.business = business;
	}

}
