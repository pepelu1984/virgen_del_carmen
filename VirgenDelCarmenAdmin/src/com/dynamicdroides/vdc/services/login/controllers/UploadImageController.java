package com.dynamicdroides.vdc.services.login.controllers;

import java.io.File;
import java.io.IOException;
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
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.dynamicdroides.vdc.services.business.VirgenDelCarmenBusiness;
import com.dynamicdroides.vdc.services.tools.ExcelReader;

public class UploadImageController   extends IControlador{
	 static Logger log = Logger.getLogger(IndexController.class);
	 private static String pathToImages;

	 //private static String pathToExcels="/opt/files/";

	private VirgenDelCarmenBusiness business;
		
		@Override
		public ModelAndView handleRequest(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			// TODO Auto-generated method stub
			super.init(request);
			request.setCharacterEncoding("UTF-8"); 		
			View view2=null;
			Map<String, Object> myModel = new HashMap<String, Object>();
			processRequest(request);
			
			
			return null;
		}

		public VirgenDelCarmenBusiness getBusiness() {
			return business;
		}

		public void setBusiness(VirgenDelCarmenBusiness business) {
			this.business = business;
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
				for(int i=0;i<items.size();i++){
					if (items.get(i).getFieldName().equals("upImage")){
							fileItem=(DiskFileItem)items.get(i);
							log.debug("Writting to :"+getRealPath());
							
							File uploadedFile = new File(pathToImages + fileItem.getName());
							//File uploadedFile = new File(getRealPath() + "/images/" + fileItem.getName());
							
							uploadedFile.createNewFile();
							System.out.println(uploadedFile.getAbsolutePath());
							if(!fileNames.equals("")){
								fileNames=fileNames+","+fileItem.getName();
							}else{
								fileNames=fileItem.getName();
							}
							items.get(i).write(uploadedFile);
							fileName=fileItem.getName();
					}else if (items.get(i).getFieldName().equals("upImageinf")){
								fileItem=(DiskFileItem)items.get(i);
								log.debug("Writting to :"+getRealPath());
								
								File uploadedFile = new File(pathToImages + fileItem.getName());
								//File uploadedFile = new File(getRealPath() + "/images/" + fileItem.getName());
								
								uploadedFile.createNewFile();
								System.out.println(uploadedFile.getAbsolutePath());
								if(!fileNames.equals("")){
									fileNames=fileNames+","+fileItem.getName();
								}else{
									fileNames=fileItem.getName();
								}
								items.get(i).write(uploadedFile);
								fileName=fileItem.getName();

					}else if(items.get(i).getFieldName().equals("idcurso")){
						  idCurso=items.get(i).getString();
						  
						  
					  }
					
				}
				//fields.setImage(fileNames);
				//uploadExceltoDatabase(pathToImages, fileName, Integer.parseInt(idCurso));

			}

			
			
		}
		
		String nameApp="VirgenDelCarmenAdmin";
		
		public String getRealPath(){
			java.net.URL r = this.getClass().getClassLoader().getResource("/");
		    String filePath = r.getFile();
		    String pathReal = new File(new File(new File(filePath).getParent()).getParent()).getParent();

		    return pathReal+"\\"+nameApp+"\\";
		}
		
}
