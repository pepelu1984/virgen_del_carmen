package com.dynamicdroides.vdc.services.business;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.dynamicdroides.db.virgendelcarmen.Menu;

public class WordExtractor  {
private PlantillaBusiness plantillaBusiness;


	public PlantillaBusiness getPlantillaBusiness() {
	return plantillaBusiness;
}

public void setPlantillaBusiness(PlantillaBusiness plantillaBusiness) {
	this.plantillaBusiness = plantillaBusiness;
}

	public String readDocument(String path) throws FileNotFoundException, IOException{
		String wholeText="";
	
	    POIFSFileSystem fis = new POIFSFileSystem(new FileInputStream(path));
	    HWPFDocument wdDoc = new HWPFDocument(fis);

	    // list all style names and indexes in stylesheet
	   /*
	    for (int j = 0; j < wdDoc.getStyleSheet().numStyles(); j++) {
	        if (wdDoc.getStyleSheet().getStyleDescription(j) != null) {
	            System.out.println(j + ": " + wdDoc.getStyleSheet().getStyleDescription(j).getName());
	        } else {
	            // getStyleDescription returned null
	            System.out.println(j + ": " + null);
	        }
	    }
	    */
	    // set range for entire document
	    Range r= wdDoc.getRange();
	    wholeText=wdDoc.getText().toString();
	    // loop through all paragraphs in range
	    /*
	    for(int i=0; i<r.numParagraphs(); i++) {
	       Paragraph p = r.getParagraph(i);
	       String text = p.text();
	       if( ! text.contains("What I'm Looking For")) {
	          // Try the next paragraph
	    	   System.out.print(""+text);
	          continue;
	       }else{
	    	   System.out.print(""+text);
	       }

	       if (wdDoc.getStyleSheet().numStyles()>p.getStyleIndex()) {
	          StyleDescription style =
	        	  wdDoc.getStyleSheet().getStyleDescription(p.getStyleIndex());
	          //String styleName = style.getName();
	          //System.out.println(styleName + " -> " + text);
	       }
	       else {
	          // Text has an unknown or invalid style
	    	   
	       }
	    }*/
	    
	    
	    return wholeText;
	    
	}
	
	public boolean readAndSave(String path) throws FileNotFoundException, IOException{
		String total=readDocument(path);
		String separator = System.getProperty( "line.separator" );
		String s2 = total.replaceAll("(\\r|\\n)", "");
		//System.out.println(s2);
		//total=total.replaceAll("\b", "");
		int d=0;
		char r='';
		
		//String[] parrafos=s2.split("(\\b)");
		String[] parrafos=total.split(r+"");
		//List<Menu> listMenu=new ArrayList<Menu>();
		String mesanho=parrafos[parrafos.length-1];
		
		Date fecha=processMonthYear(mesanho.toUpperCase());
		Calendar cal=Calendar.getInstance();
		cal.setTime(fecha);
		int mes=cal.get(Calendar.MONTH);
		
		for(int i=0;i<parrafos.length-1;i++){
			if(!parrafos[i].replaceAll("\r", "").replaceAll("\n", "").replaceAll("\b", "").equals("")){
				//System.out.println(parrafos[i]);				
				processParrafo(parrafos[i],fecha);
				//plantillabusiness.save(menu);
			}else{
				//es vacio, ignoramos
				
			}
			
			
		}
		return true;
	}
	
	private int posicion;
	public Menu processParrafo(String texto,Date fecha){
		Calendar cal=Calendar.getInstance();
		Menu nuevoDia=new Menu();
		//INI NUEVO WORD
//		if(texto.toUpperCase().indexOf("LUNES")>-1 || texto.toUpperCase().indexOf("MARTES")>-1 || (texto.toUpperCase().indexOf("MIERCOLES")>-1 || texto.toUpperCase().indexOf("MIÃ‰RCOLES")>-1) || texto.toUpperCase().indexOf("JUEVES")>-1 || texto.toUpperCase().indexOf("VIERNES")>-1 ){
			posicion=0;
			nuevoDia=new Menu();
			cal.setTime(fecha);
			int dia=0;
			String texto2=texto.substring(0, texto.indexOf("\r"));
			
			
//			if(texto.toUpperCase().indexOf("LUNES")>-1){
//				texto2=texto.toUpperCase().replace("LUNES", "").trim().substring(0,3).replace("\r", "");
//				
//			}else if(texto.toUpperCase().indexOf("MARTES")>-1){
//				texto2=texto.toUpperCase().replace("MARTES", "").trim().substring(0,3).replace("\r", "");
//			}else if(texto.toUpperCase().indexOf("MIERCOLES")>-1){
//				texto2=texto.toUpperCase().replace("MIERCOLES", "").trim().substring(0,3).replace("\r", "");
//			}else if(texto.toUpperCase().indexOf("MIÃ‰RCOLES")>-1){
//				texto2=texto.toUpperCase().replace("MIÃ‰RCOLES", "").trim().substring(0,3).replace("\r", "");
//			}else if(texto.toUpperCase().indexOf("JUEVES")>-1){
//				texto2=texto.toUpperCase().replace("JUEVES", "").trim().substring(0,3).replace("\r", "");
//			}else if(texto.toUpperCase().indexOf("VIERNES")>-1){
//				texto2=texto.toUpperCase().replace("VIERNES", "").trim().substring(0,3).replace("\r", "");
//			}
			String[] strs=texto2.split(" ");
			for(int x=0;x<strs.length;x++){
				try{
					dia=Integer.parseInt(strs[x]);
				}catch(Exception e){
					//e.printStackTrace();
				}
				
			}

			cal.set(Calendar.DATE, dia);
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			nuevoDia.setFecha(cal.getTime());
			/*List<Menu> lista=plantillaBusiness.getDao().findByExample(nuevoDia);
			
			if(lista.size()>0){
				nuevoDia=lista.get(0);
			}*/
//		}
//		if(texto.toUpperCase().indexOf("FESTIVO")>-1){
			if(texto.toUpperCase().length()<5){
			posicion=0;
			cal.setTime(nuevoDia.getFecha());
			System.out.println(cal.getTime()+"DIA FESTIVO!!!");
			//nuevoDia=new Menu();
			return null;
		}
		if(!texto.equals("")){
			posicion++;
			String[] platos=texto.split("\r");
			System.out.println(posicion+":"+texto);
			try{
				nuevoDia=processPlatos(nuevoDia,platos);
			}catch(Exception e){
				e.printStackTrace();
			}
						
		}
		//FIN NUEVO WORD
		
		return nuevoDia;
	}
	private Menu processPlatos(Menu nuevoDia,String[] platos){
//INI NUEVO WORD
		if(platos.length>4){
			//CASO ENSALADA
			int i=3;
			if(platos[2].contains("Ensalada")){
				nuevoDia.setEnsalada(platos[2]);
				boolean nuevoPlato=false;				
				//Por si ocupa varias lineas, busco la linea siguiente que empiece por mayúscula, así sabre que es un nuevo plato.
				while (!nuevoPlato){
					if(platos[i].toCharArray().length==0){
						i++;
					}
					char[] caracteres=platos[i].toCharArray();
					if(Character.isUpperCase(caracteres[0])){
						nuevoPlato = true;
					}else{
						nuevoDia.setEnsalada(nuevoDia.getEnsalada()+" "+platos[i]);
						i++;
					}
				}
				
			}//CASO EN EL QUE EL PRIMER PLATO NO ES ENSALADA
			else{
				nuevoDia.setPlato1(platos[2]);				
				boolean nuevoPlato=false;				
				//Por si ocupa varias lineas, busco la linea siguiente que empiece por mayúscula, así sabre que es un nuevo plato.
				while (!nuevoPlato){
					if(platos[i].toCharArray().length==0){
						i++;
					}
					char[] caracteres=platos[i].toCharArray();
					if(Character.isUpperCase(caracteres[0])){
						nuevoPlato = true;
					}else{
						nuevoDia.setPlato1(nuevoDia.getPlato1()+" "+platos[i]);
						i++;
					}
				}
			}
			if(nuevoDia.getPlato1()==null || nuevoDia.getPlato1().equals("")){
				nuevoDia.setPlato1(platos[i]);
				i++;
				boolean nuevoPlato=false;				
				//Por si ocupa varias lineas, busco la linea siguiente que empiece por mayúscula, así sabre que es un nuevo plato.
				while (!nuevoPlato){
					if(platos[i].toCharArray().length==0){
						i++;
					}
					char[] caracteres=platos[i].toCharArray();
					if(Character.isUpperCase(caracteres[0])){
						nuevoPlato = true;
					}else{
						nuevoDia.setPlato1(nuevoDia.getPlato1()+" "+platos[i]);
						i++;
					}
				}
			}
			//SEGUNDO PLATO
			nuevoDia.setPlato2(platos[i]);
			i++;
			boolean nuevoPlato=false;				
			//Por si ocupa varias lineas, busco la linea siguiente que empiece por mayúscula, así sabre que es un nuevo plato.
			while (!nuevoPlato){
				if(platos[i].toCharArray().length==0){
					i++;
				}
				char[] caracteres=platos[i].toCharArray();
				if(Character.isUpperCase(caracteres[0])){
					nuevoPlato = true;
				}else{
					nuevoDia.setPlato2(nuevoDia.getPlato2()+" "+platos[i]);
					i++;
				}
			}//POSTRE
			//caso en el que no venia el segundo plato y ya estamos en valores nutricionales, significa que el postre se ha metido en el segundo plato y hay que moverlo
			if(platos[i].contains("Energía") || platos[i].contains("Lípidos")){
				nuevoDia.setPostre(nuevoDia.getPlato2());
				nuevoDia.setPlato2("");
			}else{
				nuevoDia.setPostre(platos[i]);
				i++;
				nuevoPlato=false;				
				//Por si ocupa varias lineas, busco la linea siguiente que empiece por mayúscula, así sabre que es un nuevo plato.
				while (!nuevoPlato){
					if(platos[i].toCharArray().length==0){
						i++;
					}
					char[] caracteres=platos[i].toCharArray();				
					if(Character.isUpperCase(caracteres[0])){
						nuevoPlato = true;
					}else{
						nuevoDia.setPostre(nuevoDia.getPostre()+" "+platos[i]);
						i++;
					}
				}
			}
			while (i<platos.length){
				if(platos[i].contains("Energía") || platos[i].contains("Lípidos") ){
					nuevoDia.setDescripcion1(platos[i]);
					i++;
				}else if(platos[i].contains("Proteinas") || platos[i].contains("Carbohidratos")){
					nuevoDia.setDescripcion2(platos[i]);
					i++;
				}else{
					i++;
				}
			}
			
		}else{
			return null;
		}
		//FIN NUEVO WORD
		
//		if(platos.length>7){
//			nuevoDia.setEnsalada(platos[3]);
//			nuevoDia.setPlato1(platos[4]);
//			if(!platos[6].equals(""))
//				nuevoDia.setPlato2(platos[6]);
//			else
//				nuevoDia.setPlato2(platos[5]);
//			if(!platos[7].trim().equals(""))
//				nuevoDia.setPostre(platos[7]);
//			else{
//				if(platos.length>8)
//					nuevoDia.setPostre(platos[8]);
//				else
//					nuevoDia.setPostre("");
//			}
//				
//		}else if(platos.length>6){
//			nuevoDia.setEnsalada(platos[3]);
//			nuevoDia.setPlato1(platos[4]);
//			nuevoDia.setPlato2(platos[5]);
//			nuevoDia.setPostre(platos[6]);
//			SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
//			//cal.setTime(nuevoDia.getFecha());
//			System.out.println(format.format(nuevoDia.getFecha()));
//		}else if(platos.length>5){
//			nuevoDia.setEnsalada(platos[2]);
//			nuevoDia.setPlato1(platos[3]);
//			nuevoDia.setPlato2(platos[4]);
//			nuevoDia.setPostre(platos[5]);
//			
//		}else{
//			if(platos.length>4){
//				nuevoDia.setEnsalada(platos[1]);
//				nuevoDia.setPlato1(platos[2]);
//				nuevoDia.setPlato2(platos[3]);
//				nuevoDia.setPostre(platos[4]);
//			}else{
//				return null;
//			}
//		}
		try{
			plantillaBusiness.save(nuevoDia);
		}catch(Exception e){
			e.printStackTrace();
			plantillaBusiness.update(nuevoDia);
			
		}

		return nuevoDia;
	}
	private Date processMonthYear(String cadena){
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());
		int mes=0;
		if(cadena.indexOf("ENERO")>-1){
			mes=1;
		}else if(cadena.indexOf("FEBRERO")>-1){
			mes=2;
			
		}else if(cadena.indexOf("MARZO")>-1){
			mes=3;
		}else if(cadena.indexOf("ABRIL")>-1 || cadena.indexOf("ABRÃ�L")>-1){
			mes=4;

		}else if(cadena.indexOf("MAYO")>-1){
			mes=5;

		}else if(cadena.indexOf("JUNIO")>-1){
			mes=6;

		}else if(cadena.indexOf("JULIO")>-1){
			mes=7;

		}else if(cadena.indexOf("AGOSTO")>-1){
			mes=8;

		}else if(cadena.indexOf("SEPTIEMBRE")>-1){
			mes=9;

		}else if(cadena.indexOf("OCTUBRE")>-1){
			mes=10;

		}else if(cadena.indexOf("NOVIEMBRE")>-1){
			mes=11;

		}else if(cadena.indexOf("DICIEMBRE")>-1){
			mes=12;

		}else{
			System.out.println("otra linea"+cadena);
			mes=cal.get(Calendar.MONTH);
		}
		String[] strs=cadena.split(" ");
		int anho=0;
		for(int x=0;x<strs.length;x++){
			try{
				anho=Integer.parseInt(strs[x]);
			}catch(Exception e){
				
			}
			
		}
		if(anho==0){
			anho=cal.get(Calendar.YEAR);
		}
		cal.set(Calendar.YEAR, anho);
		cal.set(Calendar.MONTH, mes-1);
		cal.set(Calendar.DATE, 1);
		return cal.getTime();
	}
	
}