package com.dynamicdroides.vdc.services.login.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.dynamicdroides.db.virgendelcarmen.Alumnos;
import com.dynamicdroides.vdc.services.business.CursosHelper;

/**
 * Esta clase se encarga de gestionar la generacion de la descarga de un excel para suscripciones o para transacciones
 * 
 * @author Pipe
 *
 */
public class ExcelController extends AbstractExcelView {

	
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook wb, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		if(model.get("alumnos")!=null){
			
			List<Alumnos> lista=(List<Alumnos>)model.get("alumnos");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition","attachment; filename=alumnos.xls"); 
	        HSSFSheet sheet = (HSSFSheet)wb.createSheet("Suscripciones");
	        int fila=0;
	        HSSFRow row=sheet.createRow(fila);
        	HSSFCell celda0=row.createCell(0);
        	celda0.setCellValue("Id Alumno");
        	
        	HSSFCell celda1=row.createCell(1);
        	celda1.setCellValue("Nombre");

        	HSSFCell celda2=row.createCell(2);
        	celda2.setCellValue("Apellidos");

        	HSSFCell celda3=row.createCell(3);
        	celda3.setCellValue("Curso");

        	HSSFCell celda4=row.createCell(4);
        	celda4.setCellValue("CorreoPadres");

        	HSSFCell celda5=row.createCell(5);
        	celda5.setCellValue("Tiene comedor?");

        
        	
        	fila=1;
	        for(Alumnos alumno:lista){
	        	
	        	row=sheet.createRow(fila);
	        	celda4=row.createCell(0);
	        	celda4.setCellValue(""+alumno.getIdalumno());

	        	celda0=row.createCell(1);
	        	celda0.setCellValue(""+alumno.getNombre());
	        	
	        	celda1=row.createCell(2);
	        	celda1.setCellValue(""+alumno.getApellidos());

	        	celda2=row.createCell(3);
	        	celda2.setCellValue(""+CursosHelper.getCourseName(Integer.parseInt(alumno.getCurso())));

	        	celda3=row.createCell(4);
	        	celda3.setCellValue(""+alumno.getCorreopadres());

	        	celda4=row.createCell(5);
	        	celda4.setCellValue(""+alumno.getComedor());
	        	
	        	fila++;
	        	
	        }
		}
        System.out.println("******************build");
//
//        
//        String file = "salida.xlsx";
//        FileOutputStream out = new FileOutputStream(file);
//        wb.write(out);
//        out.close();
        
        
		
		
		
	}
	
	
	
	

	
}
