package com.dynamicdroides.vdc.services.login.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

import com.dynamicdroides.vdc.services.business.VirgenDelCarmenBusiness;

public class PlantillaController extends IControlador {

	static Logger log = Logger.getLogger(PlantillaController.class);

	private String pathImagenes;
	public String getPathImagenes() {
		return pathImagenes;
	}
	public void setPathImagenes(String pathImagenes) {
		this.pathImagenes = pathImagenes;
	}
	public String getPathPlantilla() {
		return pathPlantilla;
	}
	public void setPathPlantilla(String pathPlantilla) {
		this.pathPlantilla = pathPlantilla;
	}
	public String getPathPlantillaDiaria() {
		return pathPlantillaDiaria;
	}
	public void setPathPlantillaDiaria(String pathPlantillaDiaria) {
		this.pathPlantillaDiaria = pathPlantillaDiaria;
	}

	private String pathPlantilla;
	private String pathPlantillaDiaria;
//	 private String pathImagenes="http://178.32.68.44:8080/VirgenDelCarmenAdmin/img/jack/";
//		private String pathPlantilla="/opt/weblogic/dominio/files/fichasemanal.html";
//		private String pathPlantillaDiaria="/opt/weblogic/dominio/files/fichadiaria.html";
	
	 //private String pathPlantilla="/opt/apache-tomcat-7.0.39/bin/files/fichasemanal.html";
	 private VirgenDelCarmenBusiness business;
	 
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
						List<String> results = new ArrayList<String>();
						
						String pathImagenes=getRealPath()+"/img/jack";
						System.out.println("Reading:"+pathImagenes);
						File[] files = new File(pathImagenes).listFiles();

						for (File file : files) {
						    if (file.isFile() && file.getName().toUpperCase().indexOf(".JPG")>-1) {
						        results.add(file.getName());
						    }
						}
						myModel.put("imagenes", results);

						view2=new InternalResourceView("jsp/admin/plantilla.jsp");
						return new ModelAndView(view2, "model", myModel);
				}else if(event!=null && event.equals("ckeditor")){
						view2=new InternalResourceView("jsp/editor.jsp");
						return new ModelAndView(view2, "model", myModel);
						
				}else if(event!=null && event.equals("edit")){
						String nameImage=request.getParameter("nameImage");
						
						if(changePlantillaSemanal(nameImage)){
							//TODO mensaje ok
							
						}
				}else if(event!=null && event.equals("change")){
					String tipo=request.getParameter("tipo");
					String observaciones="";
					if(request.getParameter("observaciones")!=null){
						observaciones=request.getParameter("observaciones");
					}else if(request.getParameter("txtInptSemanal")!=null){
						observaciones=request.getParameter("txtInptSemanal");
							
					}
					
					
					String resultado="";
					if(changeTextoPlantillaSemanal(tipo, observaciones)){
						resultado="Cambiado Correctamente";
					}else{
						resultado="Ha ocurrido un problema. Contacte con el administrador";						
					}
					myModel.put("resultado", resultado);
					return new ModelAndView("//ajax", "model", myModel);
					
				}else if(event!=null && event.equals("watch")){
						
						String tipo=request.getParameter("name");
						String strpathPlantilla="";
						if(tipo.equals("diaria")){
							strpathPlantilla=pathPlantillaDiaria;
						}else{
							strpathPlantilla=pathPlantilla;
						}
						String htmlSemanal=getPlantilla(strpathPlantilla);
						myModel.put("plantilla", htmlSemanal);	
						//
						return new ModelAndView("//ajax", "model", myModel);
					}else{
						
						
					}
					List<String> results = new ArrayList<String>();

					String pathImagenes=getRealPath()+"/img/jack";
					System.out.println("Reading:"+pathImagenes);
					File[] files = new File(pathImagenes).listFiles();

					for (File file : files) {
					    if (file.isFile() && file.getName().toUpperCase().indexOf(".JPG")>-1) {
					        results.add(file.getName());
					    }
					}
					myModel.put("imagenes", results);

					
					view2=new InternalResourceView("jsp/admin/plantilla.jsp");
					return new ModelAndView(view2, "model", myModel);
					
		    		
			}catch(Exception e){
				e.printStackTrace();
				log.error(e);
				return returnErrorControl(myModel, e);
			}finally{
				//este codigo queremos que se ejecute si o si
				
				
			}
			
		}
	 	@SuppressWarnings("finally")
		private String getPlantilla(String pathnameHtmls) throws IOException{
	 		String everything="";
			BufferedReader br = new BufferedReader(new FileReader(pathnameHtmls));
		    try {
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();

		        while (line != null) {
		            //sb.append("");
		            sb.append(line);
		            line = br.readLine();
		        }
		        everything = sb.toString();
		        return everything;
		    } finally {
		        br.close();
		 		return everything;
		    }

	 	}
	 	@SuppressWarnings("finally")
		private List<String> getPlantillaList(String pathnameHtmls) throws IOException{
	 		String everything="";
	 		List<String> lista=new ArrayList<String>();
	 		System.out.println("este es" + pathnameHtmls);
			BufferedReader br = new BufferedReader(new FileReader(pathnameHtmls));
		    try {
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();

		        while (line != null) {
		            sb.append(line);
		            //sb.append("");
		            lista.add(line);
		            line = br.readLine();
		        }
		        br.close();
		        everything = sb.toString();
		        return lista;
		    } finally {
		        br.close();
		 		return lista;
		    }

	 	}
		private boolean changePlantillaSemanal(String nameImage) throws IOException{
			//
			String search="<div style=\"float:left;position:relative;width:15%\" >";
			String contenidoTotal="";
			
			List<String> htmlSemanal=getPlantillaList(pathPlantilla);
			for(int i=0;i<htmlSemanal.size();i++){
				if(htmlSemanal.get(i)!=null && htmlSemanal.get(i).equals(search)){
					htmlSemanal.remove(i+1);
					htmlSemanal.add(i+1, "<img src=\""+pathImagenes+nameImage.replace(" ", "%20")+"\" height='150px'/>");
					contenidoTotal=contenidoTotal+htmlSemanal.get(i)+"\r\n";
					continue;
				}
				if(htmlSemanal.get(i)!=null)
					contenidoTotal=contenidoTotal+htmlSemanal.get(i)+"\r\n";
				
			}
			rewriteHtml(contenidoTotal,pathPlantilla);
			return true;
		}
		private boolean changeTextoPlantillaSemanal(String tipo,String textoObservaciones) throws IOException{
			//
			String search="Observaciones generales";
			String contenidoTotal="";
			List<String> htmlSemanal=null;
			if(tipo.equals("diaria")){
				htmlSemanal=getPlantillaList(pathPlantillaDiaria);
			}else{
				htmlSemanal=getPlantillaList(pathPlantilla);
			}
			for(int i=0;i<htmlSemanal.size();i++){
				if(htmlSemanal.get(i)!=null && htmlSemanal.get(i).indexOf(search)>-1){
					htmlSemanal.remove(i+1);
					htmlSemanal.add(i+1, "<p style='text-align:justify'><b>"+textoObservaciones+"</b></p>");
					contenidoTotal=contenidoTotal+htmlSemanal.get(i)+"\r\n";
					continue;
				}
				if(htmlSemanal.get(i)!=null){
					contenidoTotal=contenidoTotal+htmlSemanal.get(i)+"\r\n";
				}
				
			}
			if(tipo.equals("diaria")){
				rewriteHtml(contenidoTotal,pathPlantillaDiaria);
			}else{
				rewriteHtml(contenidoTotal,pathPlantilla);
			}
			return true;
		}

		public VirgenDelCarmenBusiness getBusiness() {
			return business;
		}

		public void setBusiness(VirgenDelCarmenBusiness business) {
			this.business = business;
		}

		public void rewriteHtml(String content,String nameFile) throws IOException{
			File file = new File(nameFile);
			 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			
		}
}
