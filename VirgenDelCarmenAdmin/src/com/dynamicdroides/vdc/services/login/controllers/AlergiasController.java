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

import com.dynamicdroides.db.virgendelcarmen.Alergias;
import com.dynamicdroides.db.virgendelcarmen.Cursos;
import com.dynamicdroides.vdc.services.business.AlergiasBusiness;
import com.dynamicdroides.db.virgendelcarmen.Alumnos;
import com.dynamicdroides.vdc.services.business.VirgenDelCarmenBusiness;



public class AlergiasController extends IControlador {

	static Logger log = Logger.getLogger(AlergiasController.class);

	private AlergiasBusiness alergiabusiness;
	private VirgenDelCarmenBusiness business;	 
	
	
	public AlergiasBusiness getAlergiabusiness() {
		return alergiabusiness;
	}
	public void setAlergiabusiness(AlergiasBusiness alergiabusiness) {
		this.alergiabusiness = alergiabusiness;
	}
	public VirgenDelCarmenBusiness getBusiness() {
		return business;
	}
	public void setBusiness(VirgenDelCarmenBusiness business) {
		this.business = business;
	}


	private HashMap<Integer,String> namecursos=new HashMap<Integer, String>();
	 
	
	 public String getComboFromValores(List<Alergias> listaValores,String name,String idSelected,String functionChange){
			String dato="";
			int i=0;
			if(idSelected!=null && !idSelected.equals("") && idSelected.equals(""+listaValores.get(i).getIdalergia())){
				dato="<select  width='300' multiple size='6' style='width:300px;margin-left:80 px;' class='cselect' id='"+name+"' name='"+name+"' onchange='javascript:searchAlumnosByCurso4()'>";
			}else{
				dato="<select  width='300' multiple size='6' style='width:300px;margin-left:80 px;' class='cselect' id='"+name+"' name='"+name+"' onchange='javascript:searchAlumnosByCurso4()'>";
			}
			if(listaValores==null){		
				dato=dato+"</select>";
				return dato;
			}
			dato=dato+"<option value=''><a href='#'>Seleccione un registro...";
			dato=dato+"</a></option>";
			for(i=0;i<listaValores.size();i++){
				
				if(idSelected!=null && !idSelected.equals("") && idSelected.equals(""+listaValores.get(i).getIdalergia())){
					dato=dato+"<option selected value='"+listaValores.get(i).getIdalergia()+"'>"+listaValores.get(i).getDescripcion();
				}else{
					dato=dato+"<option value='"+listaValores.get(i).getIdalergia()+"'>"+listaValores.get(i).getDescripcion();
				}
				dato=dato+"</option>";
			}
			dato=dato+"</select>";
			return dato;		
	}
	 
	 public String getBottom(){
		 String dato="<br></br><br></br><br></br><input type='button' style='margin-top:50px' onClick='javascript:botonasignAlergia()' value='Asignar alergia'/>";
		 return dato;		
	 }
	 @Override
	 public ModelAndView handleRequest(HttpServletRequest request,
					HttpServletResponse response) throws Exception {
				
				super.init(request);
			
				View view2=null;
				Map<String, Object> myModel = new HashMap<String, Object>();
				
				try{

					log.error("*******************");
					String event=request.getParameter("event");
					if(event!=null && event.equals("index")){					
						view2=new InternalResourceView("jsp/admin/alergias.jsp");
						return new ModelAndView(view2, "model", myModel);							
					}else if(event!=null && event.equals("listar")){
						List<Alergias> alergias= alergiabusiness.getDao().findAll();					
						String data=getComboFromValores(alergias, "divCmbAlergias", null, "");;				
						data=data + getBottom();				
										
						myModel.put("divCmbAlergias", data);
						
						return new ModelAndView("//ajax", "model", myModel);
					}else if(event!=null && event.equals("asignaralergia")){
						String listaAlergias=""+request.getParameter("idsalergia");						
						String idslistaAlergias[]=listaAlergias.split(",");
						String listaFinalAlergias="";
						for(int i=0;i<idslistaAlergias.length;i++){
							
							Alergias alergia=alergiabusiness.getDao().findById(Integer.parseInt(idslistaAlergias[i]));
							if(listaFinalAlergias.equals("")){
								listaFinalAlergias=alergia.getDescripcion();
							}else{
								listaFinalAlergias=listaFinalAlergias+","+alergia.getDescripcion();
							}
						}
						
						String listaAlumnos=""+request.getParameter("idsalumno");
						
						String[] lstAlumnos=listaAlumnos.split(",");
						
						Alumnos alumno=null;			
						for(int y=0;y<lstAlumnos.length;y++){
							
							int b=Integer.parseInt(lstAlumnos[y]);
							alumno=business.findAlumno(b);
							alumno.setListaalergias(listaFinalAlergias);
							business.editAlumno(alumno);
							
						}
							
						
						return new ModelAndView("//ajax", "model", myModel);
						
						
					}else if(event!=null && event.equals("list")){
						
						List<Alergias> alergias= alergiabusiness.getDao().findAll();
						String data=this.getOnlineJsonData(alergias);
						request.setAttribute("datoJSON", data);
					
						return new ModelAndView("//ajax", "model", myModel);
					}else if(event!=null && event.equals("add")){

						view2=new InternalResourceView("jsp/admin/add_alergias.jsp");
						return new ModelAndView(view2, "model", myModel);

					}else if(event!=null && event.equals("edit")){

						String idalergia=request.getParameter("idalergia");
						Alergias alergia= alergiabusiness.getDao().findById(Integer.parseInt(idalergia));
						
						myModel.put("alergia", alergia);
						view2=new InternalResourceView("jsp/admin/edit_alergia.jsp");
						return new ModelAndView(view2, "model", myModel);

					}else if(event!=null && event.equals("save")){
						
						String descripcion=request.getParameter("descripcion");
						
						String listacomidas=request.getParameter("listacomidas");
						
						Alergias alergia=new Alergias();
						alergia.setDescripcion(descripcion);
						
						alergia.setListacomidas(listacomidas);
						
						alergiabusiness.save(alergia);
						

					}else if(event!=null && event.equals("save_edit")){
						String descripcion=request.getParameter("descripcion");
						String idalergia=request.getParameter("idalergia");
						String listacomidas=request.getParameter("listacomidas");
						
						Alergias alergia=new Alergias();
						alergia.setDescripcion(descripcion);
						alergia.setIdalergia(Integer.parseInt(idalergia));
						alergia.setListacomidas(listacomidas);

						alergiabusiness.update(alergia);
						
						myModel.put("mensajeUsuario", "Edicion correcta");	
						//
						return new ModelAndView("//ajax", "model", myModel);
					}else if(event!=null && event.equals("delete")){
						String idalergia=request.getParameter("idalergia");
						Alergias alergia=new Alergias();
						alergia.setIdalergia(Integer.parseInt(idalergia));
						
						alergia=alergiabusiness.getDao().findById(alergia.getIdalergia());
						alergiabusiness.delete(alergia);
						
					}else{
						
						
					}
					List<String> results = new ArrayList<String>();

					view2=new InternalResourceView("jsp/admin/alergias.jsp");
					return new ModelAndView(view2, "model", myModel);
					
		    		
			}catch(Exception e){
				e.printStackTrace();
				log.error(e);
				return returnErrorControl(myModel, e);
			}finally{
				//este codigo queremos que se ejecute si o si
				
				
			}
			
		}
	 
		public String getOnlineJsonData(List<Alergias> alergias) throws Exception{
			com.dynamicdroides.vdc.services.beans.JSONBean bean=new com.dynamicdroides.vdc.services.beans.JSONBean();
			Alergias beans[]=new Alergias[alergias.size()];
			int i=0;
			for(Alergias alergia:alergias){
				beans[i]=alergia;
	   	      i++;
			}
			
			bean.setRows(beans);
			bean.setPage(1);
			bean.setRecords(alergias.size());
			bean.setTotal(alergias.size());

			return serializeBean(bean);
			
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
		private List<String> getPlantillaAlergias(String pathnameHtmls) throws IOException{
	 		String everything="";
	 		List<String> lista=new ArrayList();
	 		
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
		        everything = sb.toString();
		        return lista;
		    } finally {
		        br.close();
		 		return lista;
		    }

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
