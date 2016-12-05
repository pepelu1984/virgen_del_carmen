package com.dynamicdroides.virgendelcarmen.manager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.dynamicdroides.db.virgendelcarmen.Alumnos;
import com.dynamicdroides.db.virgendelcarmen.Cursos;

public class ExcelLoader {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		//..
		FileInputStream file=null;
		try {
			file = new FileInputStream(new File("C://produccion//VirgenDelCarmen//FELIPE.xls"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		             
		//Get the workbook instance for XLS file 
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet=workbook.getSheetAt(0);
			boolean isFinished=false;
			VirgenDelCarmenManager manager=new VirgenDelCarmenManager();
			List<Cursos> cursos=manager.getCursos();
			HashMap<String,Integer> mapa=new HashMap<String,Integer>();
			for(Cursos cu:cursos){
				mapa.put(cu.getNombre(), cu.getIdcurso());
			}
			String curso="";
			
			int i=1;
			Alumnos alumno=null;

			while(!isFinished){
				
				HSSFRow row=sheet.getRow(i);
				if(row.getCell(0).getStringCellValue().equals("")){
					isFinished=true;
				}else{
					alumno=new Alumnos();
					alumno.setNombre(row.getCell(0).getStringCellValue());
					String apellido1="";
					String apellido2="";
					apellido1=""+row.getCell(1).getStringCellValue();
					if(row.getCell(2)!=null)apellido2=row.getCell(2).getStringCellValue();
					
					alumno.setApellidos(apellido2);
					curso=""+row.getCell(3).getStringCellValue();
					if(mapa.get(curso)!=null){
						alumno.setCurso(""+mapa.get(curso));
					}else{
						int idcurso=manager.addCurso(curso);
						mapa.put(curso,idcurso);
						alumno.setCurso(""+idcurso);
						
					}
					if(row.getCell(5)!=null)
						alumno.setCorreopadres(row.getCell(5).getStringCellValue());
					else
						alumno.setCorreopadres("");
						
					//alumno.setNombre(row.getCell(5).getStringCellValue());
					
					
					
					manager.addAlumno(alumno.getNombre(), row.getCell(1).getStringCellValue(), apellido2,""+alumno.getCurso(), alumno.getCorreopadres());
					
				}
				i++;
				
			}
			
			
			
			
			
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
