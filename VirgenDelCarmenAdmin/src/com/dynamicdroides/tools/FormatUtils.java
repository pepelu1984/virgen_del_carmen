package com.dynamicdroides.tools;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
/**
 * Clase de utilidades para manejo de cadenas, fechas y numeros
 * @author Pipe
 *
 */
public class FormatUtils {

	private static String PATTERN_NUMBER = "###,###,###.##";
	
	/**
	 * Devuelve una fecha Date, en base a un timestamp
	 * @param timestamp
	 * @return object of type Date
	 */
	public static Date getDateFromTimestamp(Timestamp timestamp){
	
		Date d=new Date(timestamp.getTime());
		
		return d;
		
	}
	
	/**
	 * Formatea una cadena de numeros, para una representacion de miles y millones con puntos como separadores.
	 * @param strVal
	 * @return
	 */
	public static String formatToThousands(String strVal){
		String StrOrign=""+strVal;
		
		try{
			strVal=strVal.replaceAll(",", "");
			
			if(strVal.indexOf(".")>-1){
				String strVal2=strVal.substring(0,strVal.indexOf("."));	
				String strVal3=strVal.substring(strVal.indexOf("."));
				
				
				if(strVal2.length()>3 && strVal2.length()<7){
						strVal2=strVal2.substring(0,strVal2.length()-3) + "," +strVal2.substring(strVal2.length()-3);
				}else if(strVal.length()>6){
					
					strVal2=strVal2.substring(0,strVal2.length()-6) + "," + strVal2.substring(strVal2.length()-6,strVal2.length()-3) + "," +strVal2.substring(strVal2.length()-3);
					
				}else{
		
				}
			return strVal2+strVal3;
		
			}else{
		
			if(strVal.length()>3 && strVal.length()<7){
				strVal=strVal.substring(0,strVal.length()-3) + "," +strVal.substring(strVal.length()-3);
			}else if(strVal.length()>6){
				strVal=strVal.substring(0,strVal.length()-6) + "," + strVal.substring(strVal.length()-6,strVal.length()-3) + "," +strVal.substring(strVal.length()-3);
				
			}
			return strVal;
		}

	}catch(Exception e){
		System.out.println("ERROR formattothousands...string - "+strVal);
		return StrOrign;
		
	}
	
}
	/**
	 * Devuelve true si la cadenas pasada por parametro es un numero
	 * @param inputData
	 * @return
	 */
	public static boolean isNumeric(String inputData) {
		  Scanner sc = new Scanner(inputData);
		  return sc.hasNextInt();
		}
	
	/**
	 * 
	 * @param cadena
	 * @param prefijo
	 * @param longitudMaxima
	 * @return
	 */
	public static String getFullString(String cadena, String prefijo,
			int longitudMaxima) {
		StringBuffer res = new StringBuffer();
		StringBuffer cadena2= new StringBuffer();
		if (cadena!=null) {
			cadena2=new StringBuffer(cadena.trim());
		}
		if (cadena2.length()>=longitudMaxima) {
			cadena2.delete(0, cadena2.length()-longitudMaxima);
		} else {
			for (int i = 0; i < (longitudMaxima - cadena2.length()); i++) {
				res.append(prefijo);
			}
		}
		res.append(cadena2);
		return res.toString();
	}

	/**
	 * Compara una fecha con la actual, si es el mismo dia, devuelve True
	 * @param fechaToCompare
	 * @return
	 */
	 public static boolean isSameDay(Date fechaToCompare){
		 Date hoy=new Date();
		 Calendar cal=Calendar.getInstance();
		 Calendar calHoy=Calendar.getInstance();
		 cal.setTime(fechaToCompare);
		 cal.setTime(fechaToCompare);
		 int mes=cal.get(Calendar.DATE);
		 int anho=cal.get(Calendar.DATE);
		 int dia=cal.get(Calendar.DATE);
		 int mesHoy=cal.get(Calendar.DATE);
		 int anhoHoy=cal.get(Calendar.DATE);
		 int diaHoy=cal.get(Calendar.DATE);
		 if(anho==anhoHoy && mes==mesHoy && anho==anhoHoy){
			 return true;
						 
		 }else{
			 return false;
			 
		 }

	 }
	 
	 /**
	  * Devuelve un String /cadena , en base a una fecha, y con parametros acerca del formato devuelto.
	  * @param fecha
	  * @param prefijo
	  * @param withDay
	  * @param withMonth
	  * @param withYear
	  * @param withHour
	  * @param sepDateHour
	  * @param sepDate
	  * @param sepHour
	  * @return
	  */
	public static String getStringDate(Date fecha, String prefijo,
			boolean withDay, boolean withMonth, boolean withYear,boolean withHour, String sepDateHour, String sepDate, String sepHour) {
		if (fecha==null) {
			return "";
		} else {
			DateTime fechaFormat = new DateTime(fecha);
			StringBuffer cadFecha = new StringBuffer();
			if (withDay) {
				cadFecha.append(getFullString(new Integer(fechaFormat.getDayOfMonth()).toString(), prefijo, 2)).append(sepDate);
			}
			if (withMonth) {
				cadFecha.append(getFullString(new Integer(fechaFormat.getMonthOfYear()).toString(), prefijo, 2)).append(sepDate);
			}
			if (withYear) {
				cadFecha.append(getFullString(new Integer(fechaFormat.getYear()).toString(), prefijo, 4)).append(sepDateHour);
			}
			if (withHour) {
				cadFecha.append(getFullString(new Integer(fechaFormat.getHourOfDay()).toString(), prefijo, 2));
				cadFecha.append(sepHour).append(getFullString(new Integer(fechaFormat.getMinuteOfHour()).toString(), prefijo, 2));
			}
			return cadFecha.toString();
		}
	}
	/**
	 * Devuelve un String /cadena , en base a una fecha, y con parametros acerca del formato devuelto.
	 * @param fecha
	 * @param prefijo
	 * @param withDay
	 * @param withMonth
	 * @param withYear
	 * @return
	 */
	public static String getStringDate(Date fecha, String prefijo,
			boolean withDay, boolean withMonth, boolean withYear) {
		return getStringDate(fecha,prefijo,withDay,withMonth,withYear,false);
	}
	/**
	 *   * Devuelve un String /cadena , en base a una fecha, y con parametros acerca del formato devuelto.
	 * @param fecha
	 * @param prefijo
	 * @param withDay
	 * @param withMonth
	 * @param withYear
	 * @param withHour
	 * @return
	 */
	public static String getStringDate(Date fecha, String prefijo,
			boolean withDay, boolean withMonth, boolean withYear,boolean withHour) {
		return getStringDate(fecha,prefijo,withDay,withMonth,withYear,withHour," ","/",":");
	}
/**
 * Devuelve un String /cadena , en base a una fecha, y con parametros acerca del formato devuelto.
 * @param fecha
 * @param prefijo
 * @param sepDate
 * @return
 */
	public static String getStringOnlyDate(Date fecha, String prefijo, String sepDate) {
		return getStringDate(fecha,prefijo,true,true,true,false," ",sepDate,":");
	}
/**
 *   * Devuelve un String /cadena , en base a una fecha, y con parametros acerca del formato devuelto.
	
 * @param fecha
 * @return
 */
	public static String getStringOnlyDate(Date fecha) {
		return getStringDate(fecha,"0",true,true,true,false);
	}
/**
 * 	 Devuelve un String /cadena , en base a una fecha
 * @param fecha
 * @return
 */
	public static String getStringCompletedDate(Date fecha) {
		return getStringDate(fecha,"0",true,true,true,true);
	}
	
	public static String getNumberAsString(Integer value){

		  DecimalFormat myFormatter = new DecimalFormat(PATTERN_NUMBER);
		  return myFormatter.format(value);
		  
	}
	/**
	 * Devuelve un String con un formato decimal en base al parametro value
	 * @param value
	 * @return
	 */
	public static String getNumberAsString(String value){

		  DecimalFormat myFormatter = new DecimalFormat(PATTERN_NUMBER);
		  return myFormatter.format(value);
		  
	}
	/**
	 * Devuelve un numero doble, en formato String
	 * @param value
	 * @return
	 */
	public static String getNumberAsString(Double value){

		  DecimalFormat myFormatter = new DecimalFormat(PATTERN_NUMBER);
		  return myFormatter.format(value);
		  
	}
	
	/**
	 * 
	 * @param number
	 * @return
	 */
	public static String getNumberForProg(Double number) {
		DecimalFormat myFormatter = new DecimalFormat(PATTERN_NUMBER);
		return myFormatter.format(number);
	}

	//Este metodo no se usa en la app
	public static String getNumberInteger(Double number){
		NumberFormat nf=NumberFormat.getInstance(Locale.US);
		nf.setMaximumFractionDigits(0);
		nf.setMinimumIntegerDigits(1);
		nf.setMaximumIntegerDigits(10);
		nf.setGroupingUsed(false);
		return nf.format(number);
	}
	
	//formatera un Date como sólo fecha
	public static String getDateAsString(Date fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		if(fecha==null){
			fecha=new Date();
		}
		return formato.format(fecha);
		
	}
	
	/**
	 * Devuelve la hora en formato String, en base a una fecha pasada por parametro
	 * @param fecha
	 * @return
	 */
	public static String getHourFromDate(Date fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("hh:MM");
		return formato.format(fecha);
		
	}
	
	/**
	 * formatea un String en formato dd/MM/yyyy o yyyy-MM-dd  y devuelve un objeto Date 
	 * @param fecha
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateFromString(String fecha) throws ParseException {
		
		SimpleDateFormat formatter=new SimpleDateFormat();
		Calendar calendar=Calendar.getInstance();
		if(fecha.indexOf("/")>-1)
			formatter.applyPattern("dd/MM/yyyy");
		else
			formatter.applyPattern("yyyy-MM-dd");
			
		Date initDate=formatter.parse(fecha);
		return initDate;
		
		
	}
	/**
	 * formatea un String en formato dd/MM/yyyy HH:mm:ss o yyyy-MM-dd HH:mm:ss  y devuelve un objeto Date 
	 * @param fecha
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateTimeFromString(String fecha) throws ParseException {
		
		SimpleDateFormat formatter=new SimpleDateFormat();
		Calendar calendar=Calendar.getInstance();
		if(fecha.indexOf("/")>-1)
			formatter.applyPattern("dd/MM/yyyy HH:mm:ss");
		else
			formatter.applyPattern("yyyy-MM-dd HH:mm:ss");
			
		Date initDate=formatter.parse(fecha);
		return initDate;
		
		
	}
	/**
	 * Devuelve true si el valor pasado por parametro es un numero entero
	 * 
	 * @param strValor
	 * @return
	 */
	public static boolean isInteger(String strValor){
		try {
			Long.parseLong(strValor);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}

	/** 
	 * Devuelve true si la cadena no es vacia
	 * 
	 * 
	 * @param strValor
	 * @return
	 */
	public static boolean withContent(String strValor){
		if (strValor!=null && !strValor.trim().equals("") && !strValor.trim().equals("") && !strValor.trim().equals("null") ) {
			return true;
		}
		return false;
	}
	
	public static Date getDateFromStr(String strDate, boolean withHour){
		Date resultDate=null;
		String pattern="dd/MM/yyyy";
		if (strDate!=null && !strDate.equals("")) {
			SimpleDateFormat formatter=new SimpleDateFormat();
			if(strDate.indexOf("/")>-1){
				if (withHour) {
					pattern="dd/MM/yyyy HH:mm";
				} else {
					pattern="dd/MM/yyyy";
				}
			}
			else{
				if (withHour) {
					pattern="dd-MM-yyyy HH:mm";
				} else {
					pattern="dd-MM-yyyy";
				}		
			}
			formatter.applyPattern(pattern);
			try{
			resultDate=formatter.parse(strDate);
			} catch (ParseException pe){
				
			}
		}
		return resultDate;
	}
	
	public static String getTextForMessage(String cadena, int longitudMaxima) {
		StringBuffer cadena2= new StringBuffer();
		if (cadena!=null) {
			cadena=cadena.replace("'", "");
			cadena=cadena.replace("\"", "");
			cadena2=new StringBuffer(cadena.trim());
		}else{
			return cadena;
		}
		if (cadena2.length()>=longitudMaxima) {
			cadena2.delete(0, cadena2.length()-longitudMaxima);
		}
		return cadena2.toString();
	}
	public static String getTextForMessage(String cadena) {
		return getTextForMessage(cadena, 1000);
	}
	
	public static String getStringDateTogether(Date fecha, String prefijo, boolean withYear, boolean withMonth, boolean withDay,boolean withHour, int yearDigits) {
		if (fecha==null) {
			return "";
		} else {
			DateTime fechaFormat = new DateTime(fecha);
			StringBuffer cadFecha = new StringBuffer();
			if (withYear) {
				String year="" + fechaFormat.getYear();
				cadFecha.append(getFullString(year.substring(year.length()-yearDigits), prefijo, yearDigits));
			}
			if (withMonth) {
				cadFecha.append(getFullString(new Integer(fechaFormat.getMonthOfYear()).toString(), prefijo, 2));
			}
			if (withDay) {
				cadFecha.append(getFullString(new Integer(fechaFormat.getDayOfMonth()).toString(), prefijo, 2));
			}
			if (withHour) {
				cadFecha.append(getFullString(new Integer(fechaFormat.getHourOfDay()).toString(), prefijo, 2));
				cadFecha.append(getFullString(new Integer(fechaFormat.getMinuteOfHour()).toString(), prefijo, 2));
			}
			return cadFecha.toString();
		}
	}
	
	public static Object getNumberFromObj(Object objValor) {
		String strValor = String.valueOf(objValor);
		return getNumberFromStr(strValor);
	}
	
	public static Object getNumberFromObj(Object objValor, boolean default0) {
		String strValor = String.valueOf(objValor);
		return getNumberFromStr(strValor, default0);
	}
	
	public static Double getNumberFromStr(String strValor) {
		return getNumberFromStr(strValor, true);
	}
	
	//obtener un número a partir de texto
	public static Double getNumberFromStr(String strValor, boolean default0) {
		Double valor=(default0?0.0:null);
		String strValorFinal=null;
		if (!withContent(strValor)) {
			return valor;
		}
		//borrar todas las comas (número en formato punto decimal, java)
		strValorFinal=strValor.replace(",", "");
		try {
			valor=Double.parseDouble(strValorFinal);
		} catch (NumberFormatException nfe){
			valor=(default0?0.0:null);
		}
		return valor;
	}
	
	public static String getTextForIntegration(String cadena, int longitudMaxima) {
		StringBuffer cadena2= new StringBuffer();
		if (cadena!=null) {
			cadena=cadena.replace("Ã¡", "á");
			cadena=cadena.replace("Ã©", "é");
			cadena=cadena.replace("Ã*", "í");
			cadena=cadena.replace("Ã³", "ó");
			cadena=cadena.replace("Ãº", "ú");
			cadena2=new StringBuffer(cadena.trim());
		}else{
			return cadena;
		}
		if (cadena2.length()>=longitudMaxima) {
			cadena2.delete(0, cadena2.length()-longitudMaxima);
		}
		return cadena2.toString();
	}
	
	public static String getTextForIntegration(String cadena) {
		return getTextForIntegration(cadena, 1000);
	}
	
	public static Date addTimeToDate(Date fecha, Integer numDays, Integer numHours, Integer numMinutes, Integer numSeconds) {
		if (fecha==null) {
			return fecha;
		} else {
			DateTime fechaAux = new DateTime(fecha);
			if (numDays!=null) {
				fechaAux=fechaAux.plusDays(numDays);
			}
			if (numHours!=null) {
				fechaAux=fechaAux.plusHours(numHours);
			}
			if (numMinutes!=null) {
				fechaAux=fechaAux.plusMinutes(numMinutes);
			}
			if (numSeconds!=null) {
				fechaAux=fechaAux.plusSeconds(numSeconds);
			}
			return fechaAux.toDate();
		}
	}
	
	public static Date addTimeToDateWithOutWheekend(Date fecha, Integer numDays, Integer numHours, Integer numMinutes, Integer numSeconds) {
		if (fecha==null) {
			return fecha;
		} else {
			DateTime fechaAux = new DateTime(fecha);
			DateTime aux1 = new DateTime(fecha);
			
			int numDaysToAdd=0;
			if (numDays!=null) {
				fechaAux=fechaAux.plusDays(numDays);
			}
			if (numHours!=null) {
				fechaAux=fechaAux.plusHours(numHours);
			}
			if (numMinutes!=null) {
				fechaAux=fechaAux.plusMinutes(numMinutes);
			}
			if (numSeconds!=null) {
				fechaAux=fechaAux.plusSeconds(numSeconds);
			}
			
			DateTime aux2 = aux1.plusDays(1);
			//saturdays and sundays
			while (aux2.isBefore(fechaAux)) {
				if (DateTimeConstants.SATURDAY==aux2.getDayOfWeek() || DateTimeConstants.SUNDAY==aux2.getDayOfWeek()) {
					numDaysToAdd++;
				}
				aux2 = aux2.plusDays(1);
			}
			fechaAux=fechaAux.plusDays(numDaysToAdd);
			return fechaAux.toDate();
		}
	}
	
	public static boolean isWheekend(Date fecha){
		boolean result=false;
		if (fecha!=null) {
			DateTime aux1 = new DateTime(fecha);
			if (DateTimeConstants.SATURDAY==aux1.getDayOfWeek() || DateTimeConstants.SUNDAY==aux1.getDayOfWeek()) {
				result=true;
			}
		}
		return result;
	}
}
