package com.dynamicdroides.vdc.services.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Transaction;

import com.dynamicdroides.db.virgendelcarmen.Alumnos;
import com.dynamicdroides.db.virgendelcarmen.AlumnosDAO;
import com.dynamicdroides.db.virgendelcarmen.Profesores;
import com.dynamicdroides.db.virgendelcarmen.ProfesoresDAO;
import com.dynamicdroides.vdc.services.business.CursosHelper;

public class ExcelReader {

	public HSSFSheet getHojaAlumnos() {
		return hojaAlumnos;
	}
	public void setHojaAlumnos(HSSFSheet hojaAlumnos) {
		this.hojaAlumnos = hojaAlumnos;
	}
	
	private HSSFSheet hojaAlumnos;

	private AlumnosDAO dao;
	
	private ProfesoresDAO profdao;
	
	public ProfesoresDAO getProfdao() {
		return profdao;
	}
	public void setProfdao(ProfesoresDAO profdao) {
		this.profdao = profdao;
	}
	public AlumnosDAO getDao() {
		return dao;
	}
	public void setDao(AlumnosDAO dao) {
		this.dao = dao;
	}
	public ExcelReader(){
		
	}

	public boolean readAndSaveAll(String path,String nameFile,int idcurso) throws IOException{
		//Aqui, en base al excel cargado, creamos los registros en bbdd
		File fichero=new File(path+nameFile);
		FileInputStream fileInputStream = new FileInputStream(fichero);
		HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
		HSSFSheet worksheet = workbook.getSheetAt(0);
		int i=1;
		HSSFRow row=null;
		HSSFCell celda=null;
		String nombre="";
		String apellidos1="";
		String apellidos2="";
		String curso="";
		String correopadres="";
		List<Alumnos> alumnos=new ArrayList<Alumnos>();
		Alumnos alumno=null;
		try{
			
		while(worksheet.getRow(i)!=null){
			alumno=new Alumnos();
			row=worksheet.getRow(i);
			celda=row.getCell(0);
			nombre=celda.getStringCellValue();
			celda=row.getCell(1);
			apellidos1=celda.getStringCellValue();
			celda=row.getCell(2);
			if(celda!=null && celda.getStringCellValue()!=null)
				apellidos2=celda.getStringCellValue();
			else
				apellidos2="";
			celda=row.getCell(3);
			
			curso=""+celda.getStringCellValue();
			
			celda=row.getCell(4);
			if(celda!=null && celda.getStringCellValue()!=null)
				correopadres=celda.getStringCellValue();
			else
				correopadres="";

			celda=row.getCell(5);
			if(celda!=null && celda.getStringCellValue()!=null)
				correopadres=correopadres+","+celda.getStringCellValue();

			
			alumno.setNombre(nombre.trim().toUpperCase());
			alumno.setApellidos(apellidos1.trim().toUpperCase()+" "+apellidos2.trim().toUpperCase());
			List<Alumnos> lista=dao.findByExample(alumno);
		
			if(lista!=null && lista.size()>0){
				alumno=(Alumnos)lista.get(0);
				
				//alumno.setCurso(""+CursosHelper.getCourseID(curso));
				if(correopadres!=null && !correopadres.trim().equals("")){
					alumno.setCorreopadres(correopadres);
				}
				alumnos.add(alumno);
			}else{
				
				alumno.setCurso(""+CursosHelper.getCourseID(curso));
				alumno.setCorreopadres(correopadres);
				
				alumnos.add(alumno);
			}
			
			
			i++;
		}
		}catch(Exception e){
			e.printStackTrace();
		}

		saveAlumnos(alumnos);
		return true;
	}
	
	public boolean readAndSaveAllProfesores(String path,String nameFile) throws IOException{
		//Aqui, en base al excel cargado, creamos los registros en bbdd
		File fichero=new File(path+nameFile);
		FileInputStream fileInputStream = new FileInputStream(fichero);
		HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
		HSSFSheet worksheet = workbook.getSheetAt(0);
		int i=1;
		HSSFRow row=null;
		HSSFCell celda=null;
		String nombre="";
		String apellidos1="";
		String apellidos2="";
		String curso="";
		String correo="";
		String telefono="";
		
		
		List<Profesores> profes=new ArrayList<Profesores>();
		Profesores prof=null;
		try{
			
		while(worksheet.getRow(i)!=null){
			prof=new Profesores();
			row=worksheet.getRow(i);
			celda=row.getCell(0);
			if(celda==null || celda.getStringCellValue()==null)break;
			nombre=celda.getStringCellValue();
			celda=row.getCell(1);
			apellidos1=celda.getStringCellValue();
			celda=row.getCell(2);
			if(celda!=null && celda.getStringCellValue()!=null)
				apellidos2=celda.getStringCellValue();
			else
				apellidos2="";
			
			
			celda=row.getCell(3);
			if(celda!=null && celda.getStringCellValue()!=null)
				correo=celda.getStringCellValue();
			else
				correo="";

			celda=row.getCell(4);
			if(celda!=null && celda.getStringCellValue()!=null)
				telefono=celda.getStringCellValue();
			else
				telefono="";
			
			prof.setNombre(nombre.toUpperCase());
			prof.setApellidos(apellidos1.toUpperCase()+" "+apellidos2.toUpperCase());
			List<Profesores> lista=profdao.findByExample(prof);
		
			if(lista!=null && lista.size()>0){
				prof=(Profesores)lista.get(0);
				
				prof.setEmail(correo);
				prof.setTelefono(telefono);
				
				
				profes.add(prof);
			}else{
				
				prof.setEmail(correo);
				prof.setTelefono(telefono);
				profes.add(prof);
			}
			
			
			i++;
		}
		}catch(Exception e){
			e.printStackTrace();
		}

		saveProfesores(profes);
		return true;
	}
	private void saveProfesores(List<Profesores> lstProfesores){
		
		for(Profesores prof:lstProfesores){
			
			Transaction t=profdao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
			if(prof.getIdprofesor()!=null){
				profdao.getHibernateTemplate().getSessionFactory().getCurrentSession().update(prof);
				
			}else{
				profdao.getHibernateTemplate().getSessionFactory().getCurrentSession().save(prof);

			}
			t.commit();
		}
	}
	
	private void saveAlumnos(List<Alumnos> lstAlumnos){
		for(Alumnos alu:lstAlumnos){
			
			Transaction t=dao.getHibernateTemplate().getSessionFactory().getCurrentSession().beginTransaction();
			if(alu.getIdalumno()!=null){
				dao.getHibernateTemplate().getSessionFactory().getCurrentSession().update(alu);
				
			}else{
				dao.getHibernateTemplate().getSessionFactory().getCurrentSession().save(alu);

			}
			t.commit();
		}
	}

}
