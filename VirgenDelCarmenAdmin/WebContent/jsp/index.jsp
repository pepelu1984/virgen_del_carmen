<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dynamicdroides.db.virgendelcarmen.*" %>
<%@ page import="java.util.*" %>

<html>
<head>
<meta http-equiv="cache-control" content="max-age">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<META HTTP-EQUIV="EXPIRES" CONTENT="max-age">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administración Virgen del Carmen</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ddroides-ajax.js"></script>
<script src="<%=request.getContextPath()%>/js/ckeditor/ckeditor.js"></script>
<%

Map model=(Map)request.getAttribute("model");

List<Cursos> cursos=(List)model.get("cursos");
List<Usuarios> educadores=(List)model.get("educadores");
List<String> listaImagenes=(List)model.get("imagenes");
%>
<script>
var strControllerSearchLocation="<%=request.getContextPath()%>/search.vdc";
var strControllerSearchEducadoresLocation="<%=request.getContextPath()%>/educadores.vdc";
var strControllerSearchComportamientosLocation="<%=request.getContextPath()%>/comportamiento.vdc";
var strControllerAlumnosLocation="<%=request.getContextPath()%>/manageAlumno.vdc";
var strControllerEducadoresLocation="<%=request.getContextPath()%>/educadores.vdc";
var strControllerPlantillaLocation="<%=request.getContextPath()%>/plantilla.vdc";
var strControllerCursosLocation="<%=request.getContextPath()%>/cursos.vdc";
var strControllerMenuLocation="<%=request.getContextPath()%>/menu.vdc";
var strControllerAlergiasLocation="<%=request.getContextPath()%>/alergias.vdc";
var strControllerProfesoresLocation="<%=request.getContextPath()%>/profesores.vdc";
var strControllerDatosGeneralesLocation="<%=request.getContextPath()%>/generaldata.vdc";
var strControllerSendMessageLocation="<%=request.getContextPath()%>/sendMessage.vdc";
var strControllerObservacionesLocation="<%=request.getContextPath()%>/observaciones.vdc";
var strControllerAsistenciaLocation="<%=request.getContextPath()%>/asistencia.vdc";
var strControllerPromociones="<%=request.getContextPath()%>/promociones.vdc";

function promocionarCurso(){
	var cmbCursos=document.forms["PromocionesForm"]["slCursos"];	
	var cmbCursosiguiente=document.forms["PromocionesForm"]["divCmbCursos"];
	var curso=1;	
	curso=cmbCursos.options[cmbCursos.options.selectedIndex].value;
	cursosiguiente=cmbCursosiguiente.options[cmbCursosiguiente.options.selectedIndex].value;
	//alert(cursosiguiente);
	AjaxUtils.loadIntoOnEdit("content",strControllerPromociones+"?event=promocionar&curso="+curso+"&cursosiguiente="+cursosiguiente,null,function(dataBack) {
		var datos;
		var error=false;
		data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");

		if(data2=='{"'){	
			datos=eval('(' + dataBack + ')');
			error=false;
			alert('hecho');	
			
			return;
		}else{
			
			
		}	
	});			
}
function searchCursos(){	
	var cmbCursos=document.forms["PromocionesForm"]["slCursos"];	
	var curso=1;	
	curso=cmbCursos.options[cmbCursos.options.selectedIndex].value;
	AjaxUtils.loadIntoOnEdit("content",strControllerPromociones+"?event=listcursos&curso="+curso,null,function(dataBack) {
		var datos;
		var error=false;
		data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");

		if(data2=='{"'){	
			datos=eval('(' + dataBack + ')');
			error=false;
			//alert(datos.model.cmbAlumnosPorCurso);
			$("#divCmbCursos").empty();
			$("#divCmbCursos").html("");
			
			$("#divCmbCursos").html("<p>Seleccione el curso destino al que quiere promocionarles</p>"+datos.model.divCmbCursos);
			
			$("#btnAsignar").attr("style", "visibility: visible");
			$("#btnAsignar2").attr("style", "visibility: visible");
			
			return;
		}else{
			
			
		}	
	});		
}
	//**************************************************************************************************************
	function searchAlergias(){	
	//var cmbCursos=document.forms["PromocionesForm"]["slCursos"];	
	//var curso=1;	
	//curso=cmbCursos.options[cmbCursos.options.selectedIndex].value;
	AjaxUtils.loadIntoOnEdit("content",strControllerAlergiasLocation+"?event=listar",null,function(dataBack) {
		var datos;
		var error=false;
		data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");

		if(data2=='{"'){	
			datos=eval('(' + dataBack + ')');
			error=false;
			//alert(datos.model.cmbAlumnosPorCurso);
			$("#divCmbAlergias").empty();
			$("#divCmbAlergias").html("");
			
			$("#divCmbAlergias").html("<p>Seleccione la alergia</p>"+datos.model.divCmbAlergias);
			
			$("#btnAsignar").attr("style", "visibility: visible");
			$("#btnAsignar2").attr("style", "visibility: visible");
			
			return;
		}else{
			
			
		}	
	});		
}
	
	//***************************************************************************************************************

function searchByEducador(){
	//var msidn=document.forms["frmSearchMSIDN"]["searchText"].value;
	var cmbEducadores=document.forms[0]["slEducadores"];
	var educador;	
	var nameeducador;	
	educador=cmbEducadores[cmbEducadores.options.selectedIndex].value;
	nameeducador=cmbEducadores[cmbEducadores.options.selectedIndex].text;
	AjaxUtils.loadIntoOnEdit("content",strControllerSearchEducadoresLocation+"?event=educador&ideducador="+educador+"&nameeducador="+nameeducador,null,function(dataBack) {
		var datos;
		var error=false;
		data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");

		if(data2=='{"'){	
			datos=eval('(' + dataBack + ')');
			error=false;
			alert(datos.model.mensajeUsuario);
			return;
		}else{
			
			
			$("#results").empty();
			$("#results").html("");
			$("#results").html(dataBack);	
		}
	
	});
	
}

function searchAlumnosByCurso(){

	var cmbCursos=document.forms["frmalumnoUsers"]["slCursosUsers"];
	var idUsuario=document.forms["frmalumnoUsers"]["ideducador"];
	
	var curso=1;	
	curso=cmbCursos.options[cmbCursos.options.selectedIndex].value;
	AjaxUtils.loadIntoOnEdit("content",strControllerSearchEducadoresLocation+"?event=cursouser&idcurso="+curso+"&iduser="+idUsuario,null,function(dataBack) {
		var datos;
		var error=false;
		data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");

		if(data2=='{"'){	
			datos=eval('(' + dataBack + ')');
			error=false;
			//alert(datos.model.cmbAlumnosPorCurso);
			$("#divCmbAlumnos").empty();
			$("#divCmbAlumnos").html("");
			
			$("#divCmbAlumnos").html(datos.model.cmbAlumnosPorCurso);
			
			$("#btnAsignar").attr("style", "visibility: visible");
			$("#btnAsignar2").attr("style", "visibility: visible");
			
			return;
		}else{
			
			
		}
	
	});
	
}
	function searchAlumnosByCurso2(){

		var cmbCursos=document.forms["sendMessageForm"]["slCursosUsers"];
		var idUsuario=document.forms["sendMessageForm"]["ideducador"];
		var curso=1;	
		curso=cmbCursos.options[cmbCursos.options.selectedIndex].value;
		AjaxUtils.loadIntoOnEdit("content",strControllerSearchEducadoresLocation+"?event=cursouser&idcurso="+curso+"&iduser="+idUsuario,null,function(dataBack) {
			var datos;
			var error=false;
			data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");

			if(data2=='{"'){	
				datos=eval('(' + dataBack + ')');
				error=false;
				//alert(datos.model.cmbAlumnosPorCurso);
				$("#divCmbAlumnos").empty();
				$("#divCmbAlumnos").html("");
				
				$("#divCmbAlumnos").html(datos.model.cmbAlumnosPorCurso);
				
				$("#btnAsignar").attr("style", "visibility: visible");
				$("#btnAsignar2").attr("style", "visibility: visible");
				
				return;
			}else{
				
				
			}
		
		});	
	}
		
	function searchAlumnosByCurso3(){

		var cmbCursos=document.forms["PromocionesForm"]["slCursos"];
		var idUsuario=document.forms["PromocionesForm"]["ideducador"];
		//var idUsuario=document.forms["AsignAlergiaForm"]["slCursos"];
		var curso=1;	
		curso=cmbCursos.options[cmbCursos.options.selectedIndex].value;
		AjaxUtils.loadIntoOnEdit("content",strControllerSearchEducadoresLocation+"?event=cursouser&idcurso="+curso+"&iduser="+idUsuario,null,function(dataBack) {
			var datos;
			var error=false;
			data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");

			if(data2=='{"'){	
				datos=eval('(' + dataBack + ')');
				error=false;
				//alert(datos.model.cmbAlumnosPorCurso);
				$("#divCmbAlumnos").empty();
				$("#divCmbAlumnos").html("");
				
				$("#divCmbAlumnos").html(datos.model.cmbAlumnosPorCurso);
				
				$("#btnAsignar").attr("style", "visibility: visible");
				$("#btnAsignar2").attr("style", "visibility: visible");
				
				return;
			}else{
				
				
			}
		
		});	
	}	


	function searchAlumnosByCurso4(){

		var cmbCursos=document.forms["PromocionesForm"]["divCmbCursos"];
		var idUsuario=document.forms["PromocionesForm"]["ideducador"];
		
		
		var curso=1;	
		curso=cmbCursos.options[cmbCursos.options.selectedIndex].value;
		AjaxUtils.loadIntoOnEdit("content",strControllerSearchEducadoresLocation+"?event=cursouser2&idcurso="+curso+"&iduser="+idUsuario,null,function(dataBack) {
			var datos;
			var error=false;
			data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");

			if(data2=='{"'){	
				datos=eval('(' + dataBack + ')');
				error=false;
				//alert(datos.model.cmbAlumnosPorCurso);
				$("#divCmbAlumnos2").empty();
				$("#divCmbAlumnos2").html("");
				
				$("#divCmbAlumnos2").html(datos.model.cmbAlumnosPorCurso2);
				
				$("#btnAsignar").attr("style", "visibility: visible");
				$("#btnAsignar2").attr("style", "visibility: visible");
				
				return;
			}else{
				
				
			}
		
		});	
	}		

	function searchAlumnosByCurso5(){

		var cmbCursos=document.forms["PromocionesForm"]["slCursos"];
		var idUsuario=document.forms["PromocionesForm"]["ideducador"];
		//var idUsuario=document.forms["AsignAlergiaForm"]["slCursos"];
		var curso=1;	
		curso=cmbCursos.options[cmbCursos.options.selectedIndex].value;
		AjaxUtils.loadIntoOnEdit("content",strControllerSearchEducadoresLocation+"?event=cursouser&idcurso="+curso+"&iduser="+idUsuario,null,function(dataBack) {
			var datos;
			var error=false;
			data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");

			if(data2=='{"'){	
				datos=eval('(' + dataBack + ')');
				error=false;
				//alert(datos.model.cmbAlumnosPorCurso);
				$("#CmbAlumnos").empty();
				$("#CmbAlumnos").html("");
				
				$("#CmbAlumnos").html(datos.model.cmbAlumnosPorCurso);
				
				$("#btnAsignar").attr("style", "visibility: visible");
				$("#btnAsignar2").attr("style", "visibility: visible");
				
				return;
			}else{
				
				
			}
		
		});	
	}	
		
	function botonpromocion(){

		var cmbCursos=document.forms["PromocionesForm"]["divBotonPromo"];
		var idUsuario=document.forms["PromocionesForm"]["ideducador"];
		var curso=1;	
		curso=cmbCursos.options[cmbCursos.options.selectedIndex].value;
		AjaxUtils.loadIntoOnEdit("content",strControllerSearchEducadoresLocation+"?event=promo",null,function(dataBack) {
			var datos;
			var error=false;
			data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");

			if(data2=='{"'){	
				datos=eval('(' + dataBack + ')');
				error=false;
				//alert(datos.model.cmbAlumnosPorCurso);
				$("#divBotonPromo").empty();
				$("#divBotonPromo").html("");
				
				$("#divBotonPromo").html(datos.model.divBotonPromo);
				
				$("#btnAsignar").attr("style", "visibility: visible");
				$("#btnAsignar2").attr("style", "visibility: visible");
				
				return;
			}else{
				
				
			}
		
		});	
	}			
//************************************nueva funcion**************************
 function sendMenssenger(){
	
	var idsAlumno=getSelectValues(document.forms["sendMessageForm"]["cmbAlumnosPorCurso"]);
	//var Mensaje=document.forms["sendMessageForm"]["txtMensaje"].String;	
	var Mensaje=encodeURI(CKEDITOR.instances.txtMensaje.getData());
	var Subject=document.forms["sendMessageForm"]["txtSubject"].value;
	
	if(!confirm("¿Esta seguro que desea enviar el mensaje?"))return;	
	
	AjaxUtils.loadIntoOnEdit("content",strControllerSendMessageLocation+"?event=send&idsuser="+idsAlumno+"&mensaje="+Mensaje+"&Subject="+Subject,null,function(dataBack) {
		var datos;
		var error=false;
		data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");

		if(data2=='{"'){	
			datos=eval('(' + dataBack + ')');
			alert(datos.model.txtMensaje);
		}else{			
			
		}
	
	});	
} 	

function botonasignAlergia(){
	//alert("holaaaaaaa");
	var idsAlergia=getSelectValues(document.forms["PromocionesForm"]["divCmbAlergias"]);	
	//var idsAlumnos=getSelectValues(document.forms["PromocionesForm"]["CmbAlumnos"]);
	var idsAlumnos=getSelectValues(document.forms["PromocionesForm"]["cmbAlumnosPorCurso"]);
	
	//var idsAlumno=cmbCursos.options[cmbCursos.options.selectedIndex].value;	
	//curso=cmbCursos.options[cmbCursos.options.selectedIndex].value;
	//var Mensaje=document.forms["sendMessageForm"]["txtMensaje"].String;	
	//var Mensaje=encodeURI(CKEDITOR.instances.txtMensaje.getData());
	//var Subject=document.forms["sendMessageForm"]["txtSubject"].value;
	//getSelectValues
	//alert("holaaaaaaaaaaaaaaaaaaaa" + idsAlergia + "hola" + idsAlumnos );
	if(!confirm("¿Esta seguro que desea asignar estas alergias?"))return;	
	
	AjaxUtils.loadIntoOnEdit("content",strControllerAlergiasLocation+"?event=asignaralergia&idsalergia="+idsAlergia+"&idsalumno="+idsAlumnos,null,function(dataBack) {
		var datos;
		var error=false;
		data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");

		if(data2=='{"'){	
			datos=eval('(' + dataBack + ')');
			//alert(datos.model.divCmbAlergias);
			alert("Alergia/as asignada/as");
		}else{			
			
		}
	
	});	
} 	
	
function calendarByUser(){
	var cmbUsers=document.forms["frmAsistencia"]["slUsersCalendar"];

	var user=cmbUsers.options[cmbUsers.options.selectedIndex].value;
	AjaxUtils.loadIntoOnEdit("content",strControllerAsistenciaLocation+"?event=educador&educador="+user+"",null,function(dataBack) {
			$("#results").empty();
			$("#results").html("");
			$("#results").html(dataBack);
	
	});

}
function editRegAsistencia(){
	alert("en construccion");
	
}
function calendarByCurso(){
	var cmbCursos=document.forms["frmAsistencia"]["slCursosCalendar"];

	curso=cmbCursos.options[cmbCursos.options.selectedIndex].value;
	AjaxUtils.loadIntoOnEdit("content",strControllerAsistenciaLocation+"?event=curso&idcurso="+curso+"",null,function(dataBack) {
			$("#results").empty();
			$("#results").html("");
			$("#results").html(dataBack);
	
	});

}


function searchComportamientosByAlumno(idalumno,curso,nombre,apellidos,correopadres){
	
	
	AjaxUtils.loadIntoOnEdit("content",strControllerSearchComportamientosLocation+"?idalumno="+idalumno+"&curso="+curso+"&nombre="+nombre+"&apellidos="+apellidos+"&correopadres="+correopadres,null,function(dataBack) {
		var datos;
		var error=false;
		data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");

		if(data2=='{"'){	
			datos=eval('(' + dataBack + ')');
			error=false;
			alert(datos.model.mensajeUsuario);
			return;
		}else{
			
		
			$("#results").empty();
			$("#results").html("");
			$("#results").html(dataBack);	
		}
	
	});
}
function searchByCurso(){
	//var msidn=document.forms["frmSearchMSIDN"]["searchText"].value;
	var cmbCursos=document.forms[0]["slCursos"];
	var curso=1;	
	curso=cmbCursos.options[cmbCursos.options.selectedIndex].value;
	AjaxUtils.loadIntoOnEdit("content",strControllerSearchLocation+"?event=curso&idcurso="+curso+"",null,function(dataBack) {
		var datos;
		var error=false;
		data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");

		if(data2=='{"'){	
			datos=eval('(' + dataBack + ')');
			error=false;
			alert(datos.model.mensajeUsuario);
			return;
		}else{
			
		
			$("#results").empty();
			$("#results").html("");
			$("#results").html(dataBack);	
			/*
			if((""+window.location).indexOf("cursos")==-1){
				window.location=window.location+"#cursos";			
			}*/
			
		}
	
	});
	
}

function logOut(){
	if(confirm("¿Desea salir del sistema?")){
		window.location="<%=request.getContextPath()%>/j_spring_security_logout";
	}
		
}

function addObservacion(){
	
	
	AjaxUtils.loadIntoOnEdit("content",strControllerObservacionesLocation+"?event=add&",null,function(dataBack) {
		var datos;
		var error=false;
		data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");

		if(data2=='{"'){	
			datos=eval('(' + dataBack + ')');
			error=false;
			alert(datos.model.mensajeUsuario);
			return;
		}else{
			
			//searchByCurso();
			$("#tabAddObservacion").empty();
			$("#tabAddObservacion").html("");
			$("#tabAddObservacion").html(dataBack);	
		}
	
		
	});
	
}

function addAlumno(){
	
	
	AjaxUtils.loadIntoOnEdit("content",strControllerAlumnosLocation+"?event=add&",null,function(dataBack) {
		var datos;
		var error=false;
		data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");

		if(data2=='{"'){	
			datos=eval('(' + dataBack + ')');
			error=false;
			alert(datos.model.mensajeUsuario);
			return;
		}else{
			
			//searchByCurso();
			$("#newalumno").empty();
			$("#newalumno").html("");
			$("#newalumno").html(dataBack);	
		}
	
		
	});
	
}
	

	
function addProfesor(){
	
	
	AjaxUtils.loadIntoOnEdit("content",strControllerProfesoresLocation+"?event=add&",null,function(dataBack) {
		var datos;
		var error=false;
		data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");

		if(data2=='{"'){	
			datos=eval('(' + dataBack + ')');
			error=false;
			alert(datos.model.mensajeUsuario);
			return;
		}else{
			
			//searchByCurso();
			$("#tabAddProfesor").empty();
			$("#tabAddProfesor").html("");
			$("#tabAddProfesor").html(dataBack);	
		}
	
		
	});
	
}

	function getSelectValues(select) {
	  var result = null;
	  var options = select && select.options;
	  var opt;

	  for (var i=0, iLen=options.length; i<iLen; i++) {
	    opt = options[i];

	    if (opt.selected) {
	    	if(result==null){
	  	      result=""+opt.value;
	    	}else{
	  	      result=result+","+opt.value;

	    	}
	    }
	  }
	  return result;
}
function asignAlumnosToEducador(gridElement){
	
	$("#loading").html("<img src='<%=request.getContextPath()%>/images/loading.gif' width='50px' height='50px' border='0'/>");
	var cmbCursos=document.forms["frmalumnoUsers"]["slCursosUsers"];
	var idsAlumno=getSelectValues(document.forms["frmalumnoUsers"]["cmbAlumnosPorCurso"]);
	var idUsuario=document.forms["frmalumnoUsers"]["ideducador"].value;
	var curso=1;	
	curso=cmbCursos.options[cmbCursos.options.selectedIndex].value;
	alert("El proceso tarda unos momentos,.......");
	AjaxUtils.loadIntoOnEdit("content",strControllerEducadoresLocation+"?event=addalumnouser&idcurso="+curso+"&idalumno="+idsAlumno+"&idUsuario="+idUsuario,null,function(dataBack) {
		var datos;
		var error=false;
		data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");

		if(data2=='{"'){	
			datos=eval('(' + dataBack + ')');
			error=false;
			//$("#"+gridElement).trigger("reloadGrid",[{page:1}]);
			$("#loading").empty();
			$("#loading").html("");
			searchByEducador();
			//$("#loading").empty();
			//$("#loading").html("");
			return;
		}else{
			$("#loading").empty();
			$("#loading").html("");
			
			
			$("#results").empty();
			$("#results").html("");
			$("#results").html(dataBack);	
		}
	
	});
	
	
}
function editAlumno(idalumno){
	
	
	AjaxUtils.loadIntoOnEdit("content",strControllerAlumnosLocation+"?event=edit&idalumno="+idalumno,null,function(dataBack) {

		
		$("#results").toggle();
		$("#results2").toggle();
		$("#results2").empty();
		$("#results2").html("");
		$("#results2").html(dataBack);
		//searchByCurso();
	});
}
function editAlergia(idalergia){
	
	
	AjaxUtils.loadIntoOnEdit("content",strControllerAlergiasLocation+"?event=edit&idalergia="+idalergia,null,function(dataBack) {
		$("#results").toggle();
		$("#results2").toggle();
		$("#results2").empty();
		$("#results2").html("");
		$("#results2").html(dataBack);
	});
	
}

//*****************************************************************
function addAlergia(){	
	AjaxUtils.loadIntoOnEdit("content",strControllerAlergiasLocation+"?event=add&",null,function(dataBack) {
		//var datos;
		//var error=false;
		//data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");

		//if(data2=='{"'){	
		//	datos=eval('(' + dataBack + ')');
		//	error=false;
		//	alert(datos.model.mensajeUsuario);
		//	return;
		//}else{
			
			//searchByCurso();
		//	$("#tabAddAlergia").empty();
		//	$("#tabAddAlergia").html("");
		//	$("#tabAddAlergia").html(dataBack);	
		//}
		//$("#tabListaEducadores").toggle();
		$("#tabAddAlergia").empty();
		$("#tabAddAlergia").html("");
		$("#tabAddAlergia").html(dataBack);
	});	
}	
function guardaAddAlergia(){	
	var datos=$("#frmNewAlergia").serialize();
	AjaxUtils.loadIntoOnEdit("content",strControllerAlergiasLocation+"?event=save&"+datos,null,function(dataBack) {
		$("#results").empty();
		$("#results").html("");		
		callAlergias();
	});	
}

//tabAsignarAlergia
function asignarAlergia(){	
	//var datos=$("#frmNewAlergia").serialize();
	AjaxUtils.loadIntoOnEdit("content",strControllerPromociones+"?event=asign&",null,function(dataBack) {
		
		$("#asignaralergias").empty();
	    $("#asignaralergias").html("");	
	    //$("#results").html("datos.model.cursos");	
	    $("#asignaralergias").html(dataBack);		
		//callAlergias();		
		//tabAlergias;slCursos
	});	
}
//***************************************************************************************************
function editProfesor(idprofesor){
	
	
	AjaxUtils.loadIntoOnEdit("content",strControllerProfesoresLocation+"?event=edit&idprofesor="+idprofesor,null,function(dataBack) {

		
		$("#results").toggle();
		$("#results2").toggle();
		$("#results2").empty();
		$("#results2").html("");
		$("#results2").html(dataBack);
		//searchByCurso();
	});
	
}

function editEducador(iduser){
	
	
	AjaxUtils.loadIntoOnEdit("content",strControllerSearchEducadoresLocation+"?event=edit&iduser="+iduser,null,function(dataBack) {
		//reseteamos la busqueda	
		$("#results").empty();
		$("#results").html("");
		$("#results").html(dataBack);
		
	});
	
}

function editObservacion(idobservacion){
	
	
	AjaxUtils.loadIntoOnEdit("content",strControllerObservacionesLocation+"?event=edit&idobservacion="+idobservacion,null,function(dataBack){
		//reseteamos la busqueda	
		$("#results").empty();
		$("#results").html("");
		$("#results").html(dataBack);
		
	});
	
}



function addEducador(){
	
	AjaxUtils.loadIntoOnEdit("content",strControllerSearchEducadoresLocation+"?event=add",null,function(dataBack) {
		//reseteamos la busqueda	
		$("#tabListaEducadores").toggle();
		$("#tabAddEducador").empty();
		$("#tabAddEducador").html("");
		$("#tabAddEducador").html(dataBack);
		
	});	
}


function backFromAddEditEducadores(){
	$("#addEducadores").empty();
	$("#addEducadores").html("");
	$("#tabListaEducadores").toggle();
}

function guardaAlumno(){
	
	
	var datos=$("#frmAdd").serialize();
	AjaxUtils.loadIntoOnEdit("content",strControllerAlumnosLocation+"?event=save&"+datos,null,function(dataBack) {
		
		
		$("#results").empty();
		$("#results").html("");
		searchByCurso();
		//$("#results").html(dataBack);
	});
}
function guardaEditProfesor(){
	
	var datos=$("#frmEditProfesor").serialize();
	AjaxUtils.loadIntoOnEdit("content",strControllerProfesoresLocation+"?event=save_edit&"+datos,null,function(dataBack) {
		$("#results").empty();
		$("#results").html("");
		callProfesores();
		//$("#results").html(dataBack);
	});
}


function guardaAddObservacion(){
	
	
	var datos=$("#frmNewObservacion").serialize();
	AjaxUtils.loadIntoOnEdit("content",strControllerObservacionesLocation+"?event=save&"+datos,null,function(dataBack) {
		$("#results").empty();
		$("#results").html("");
		callObservaciones();
		//$("#results").html(dataBack);
	});
}
function guardaEditObservacion(){
	
	
	var datos=$("#frmEditObservacion").serialize();
	AjaxUtils.loadIntoOnEdit("content",strControllerObservacionesLocation+"?event=save_edit&"+datos,null,function(dataBack) {
		$("#results").empty();
		$("#results").html("");
		callObservaciones();
		//$("#results").html(dataBack);
	});
}


function guardaAddProfesor(){
	
	
	var datos=$("#frmNewProfesor").serialize();
	AjaxUtils.loadIntoOnEdit("content",strControllerProfesoresLocation+"?event=save&"+datos,null,function(dataBack) {
		$("#results").empty();
		$("#results").html("");
		callProfesores();
		//$("#results").html(dataBack);
	});
}

function deleteProfesor(idprofesor){

	if(!confirm("¿Esta seguro que desea eliminar este profesor y todos sus datos?"))return;
	
	AjaxUtils.loadIntoOnEdit("content",strControllerProfesoresLocation+"?event=delete&idprofesor="+idprofesor,null,function(dataBack) {
		$("#results").empty();
		$("#results").html("");
		//$("#results").html(dataBack);
		callProfesores();
	});

}
function deleteAlergia(idalergia){

	if(!confirm("¿Esta seguro que desea eliminar este registro de alergias?"))return;
	
	AjaxUtils.loadIntoOnEdit("content",strControllerProfesoresLocation+"?event=delete&idprofesor="+idprofesor,null,function(dataBack) {
		$("#results").empty();
		$("#results").html("");
		//$("#results").html(dataBack);
		callAlergias();
	});

}


function deleteAlumno(idalumno){

	var datos=$("#frmAdd").serialize();
	if(!confirm("¿Esta seguro que desea eliminar este alumno y todos sus datos?"))return;
	
	AjaxUtils.loadIntoOnEdit("content",strControllerAlumnosLocation+"?event=delete&idalumno="+idalumno,null,function(dataBack) {
		
		
		$("#results").empty();
		$("#results").html("");
		//$("#results").html(dataBack);
		searchByCurso();
	});

}
function deleteObservacion(idobservacion){

	if(!confirm("¿Esta seguro que desea eliminar esta observacion?"))return;
	
	AjaxUtils.loadIntoOnEdit("content",strControllerObservacionesLocation+"?event=delete&idobservacion="+idobservacion,null,function(dataBack) {
		
		//$("#results").html(dataBack);
		callObservaciones();
	});

}

function seeAsistencia(idalumno,isalumno){
	
	AjaxUtils.loadIntoOnEdit("content",strControllerAsistenciaLocation+"?event=report&idalumno="+idalumno+"&isalumno="+isalumno,null,function(dataBack) {
		$("#results").html(dataBack);
	});
	
	
}

function seeAsistenciaReport(){
	
	AjaxUtils.loadIntoOnEdit("content",strControllerAsistenciaLocation+"?event=index",null,function(dataBack) {
		$("#results").html(dataBack);
	});
	
	
}




function deleteEducador(iduser){

	if(!confirm("¿Esta seguro que desea eliminar este educador y todos sus datos?"))return;
	
	AjaxUtils.loadIntoOnEdit("content",strControllerSearchEducadoresLocation+"?event=delete&iduser="+iduser,null,function(dataBack) {
		
		var datos;
		var error=false;
		data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");

		if(data2=='{"'){	
			datos=eval('(' + dataBack + ')');
			error=false;
			alert("Borrado correcto");
			callEducadores();
		}else{
		
		}
	});

}


function guardaEditAlumno(){
	
		
		var datos=$("#frmEdit").serialize();
		AjaxUtils.loadIntoOnEdit("content",strControllerAlumnosLocation+"?event=save_edit&"+datos,null,function(dataBack) {
			$("#results2").empty();
			$("#results2").html("");
			alert("Registro modificado");
			$("#results").toggle();
			$("#results2").toggle();
			searchByCurso();			
	});
}
function goBack(){
	
	$("#results").toggle();
	$("#results2").empty();
	$("#results2").html("");
	$("#results2").toggle();
	
}
function cleanScreen(){
	$("#results").css("display","block");
	$("#results").empty();
	$("#results").html("");
	$("#results2").empty();
	$("#results2").html("");
	
	
}
function sendComportamiento(idcomportamiento,idalumno,idcurso){
	
	AjaxUtils.loadIntoOnEdit("content",strControllerSearchComportamientosLocation+"?event=send&idalumno="+idalumno,+"&idcomportamiento="+idcomportamiento,null,function(dataBack) {
		gridElement.trigger("reloadGrid",[{page:1}]);
	});
	
	
}
function quitarAlumnoEducador(idalumno,iduser,gridElement){
	
	//alert("1");	
	if(!confirm("?Esta seguro que desea deasignar este alumno a este educador??"))return;
	
	AjaxUtils.loadIntoOnEdit("content",strControllerEducadoresLocation+"?event=deleteasign&idalumno="+idalumno+"&iduser="+iduser,null,function(dataBack) {
	
		searchByEducador()
		//$("#results").empty();
		//$("#results").html("");
		//$("#results").html(dataBack);
		
	});
	//alert("2");	
	
}


$(document).ready(function() {
	$('#searchText').keypress(function (e) {
		if ((e.which && e.which == 13)) {
	    	//searchByMSIDN();
	    	return false;
	    } else {
	        return true;
	    }
	});
	$( "#tabs" ).tabs({
        activate: function (event, ui) {
			selected = ui.newTab.context.id;
            if (selected == "tabs-3") {
            	callEducadores();
            }



        }
    });
	
	$("#edutab").on("click", function() {
	    alert("Tab Clicked!");
	});
// 	$('#tabs').click('tabsselect', function (event, ui) {
// 		alert("dentro1");	
//     	callEducadores();
// 	     var selectedTab = $("#tabs").tabs('option','active');
// 	});
	//var selected = $( "#tabs" ).tabs('option', 'active');

    //alert(selected);

	
});

	

function downloadExcel(){
	window.open("<%=request.getContextPath()%>/excel/plantillaalumnos.xls","plantilla alumnos excel","menubar=1,resizable=1,width=1,height=1");
}
function downloadExcelProfesores(){
	window.open("<%=request.getContextPath()%>/excel/plantillaprofes.xls","plantilla profesores excel","menubar=1,resizable=1,width=1,height=1");
}


function reasignAlumnos(){
	
	var ideducadorfi=getSelectValues(document.getElementById("slasignEducadores"));
	var  ideducadororig=document.forms["frmalumnoUsers"]["ideducador"].value;
	
	AjaxUtils.loadIntoOnEdit("content",strControllerEducadoresLocation+"?event=reasign&ideducadororigen="+ideducadororig+"&ideducadorfin="+ideducadorfi,null,function(dataBack) {
		var datos;
		var error=false;
		data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");
		datos=eval('(' + dataBack + ')');
		error=false;
		alert(datos.model.mensajeUsuario);

		searchByEducador();
	
	});
	
}
function reasignAlumnos(){
	
	var ideducadorfi=getSelectValues(document.getElementById("slasignEducadores"));
	var  ideducadororig=document.forms["frmalumnoUsers"]["ideducador"].value;
	
	AjaxUtils.loadIntoOnEdit("content",strControllerEducadoresLocation+"?event=reasign&ideducadororigen="+ideducadororig+"&ideducadorfin="+ideducadorfi,null,function(dataBack) {
		var datos;
		var error=false;
		data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");
		datos=eval('(' + dataBack + ')');
		error=false;
		alert(datos.model.mensajeUsuario);

		searchByEducador();
	
	});
	
}

function selectImage(nameImage){
	
	
	AjaxUtils.loadIntoOnEdit("content",strControllerPlantillaLocation+"?event=edit&nameImage="+nameImage,null,function(dataBack) {
		var datos;
		var error=false;
		//alert("Plantilla actualizada");
		
	});
}

function selectImageDiaria(nameImage){
	
	
	AjaxUtils.loadIntoOnEdit("content",strControllerPlantillaLocation+"?event=edit&nameImage="+nameImage,null,function(dataBack) {
		var datos;
		var error=false;
		//alert("Plantilla actualizada");
		
	});
}
function watchPlantilla(){
	
	AjaxUtils.loadIntoOnEdit("content",strControllerPlantillaLocation+"?event=watch&name=semanal",null,function(dataBack) {
		var datos;
		var error=false;
		datos=eval('(' + dataBack + ')');
		error=false;
		//$("#results").empty();
		//$("#results").html("");
		
		$("#tabsSemanal").tabs();
		//$("#plantilla").dialog();
		//$("#plantillainf").html();
		$("#plantilla").html(datos.model.plantilla);
		
// 		if((""+window.location).indexOf("plantilla")==-1){
// 			window.location=window.location+"#plantilla";			
// 		}
	});
	
}

function watchPlantillaDiaria(){
	
	AjaxUtils.loadIntoOnEdit("content",strControllerPlantillaLocation+"?event=watch&name=diaria",null,function(dataBack) {
		var datos;
		var error=false;
		datos=eval('(' + dataBack + ')');
		error=false;
		//$("#results").empty();
		//$("#results").html("");
		//$("#plantilla").dialog();
		
		
		$("#tabsDiaria").tabs();
		//$("#plantilla").html();
		$("#plantillainf").html(datos.model.plantilla);
// 		if((""+window.location).indexOf("plantilla")==-1){
// 			window.location=window.location+"#plantilla";			
// 		}
	});
	
}

function editCurso(){
	var cmbCursos=document.forms["frmSearch"]["slCursos"];
	var curso=1;	
	curso=cmbCursos.options[cmbCursos.options.selectedIndex].value;

	AjaxUtils.loadIntoOnEdit("content",strControllerCursosLocation+"?event=edit&idcurso="+curso,null,function(dataBack) {
		var datos;
		var error=false;
		$("#results").empty();
		$("#results").html("");
		$("#results").html(dataBack);
	});

}
function deleteCurso(){
	if(!confirm("Esta seguro que desea borrar este curso?"))return;
	
	//alert("en construccion");
	var cmbCursos=document.forms["frmSearch"]["slCursos"];
	var curso=1;	
	curso=cmbCursos.options[cmbCursos.options.selectedIndex].value;
	

	AjaxUtils.loadIntoOnEdit("content",strControllerCursosLocation+"?event=delete&idcurso="+curso,null,function(dataBack) {
		var datos;
		var error=false;
		
		window.location.reload();
		
	});
	
	
	
}
function saveCurso(){
	if(!confirm("Esta seguro que desea editar este curso?"))return;
	
	var idcurso=document.forms["editCursos"]["idcurso"].value;
	var name=document.forms["editCursos"]["nameCurso"].value;
	AjaxUtils.loadIntoOnEdit("content",strControllerCursosLocation+"?event=save_edit&idcurso="+idcurso+"&name="+name,null,function(dataBack) {
		
		window.location.reload();
		
	});
	
}

function callEducadores(){
	cleanScreen();
	AjaxUtils.loadIntoOnEdit("content",strControllerSearchEducadoresLocation+"?event=index",null,function(dataBack) {
		var datos;
		var error=false;
		data2=clearspaces(replaceAll(dataBack.substr(0,2)," ","")).replace(/\r\n/g, "");

		if(data2=='{"'){	
			datos=eval('(' + dataBack + ')');
			error=false;
			alert(datos.model.mensajeUsuario);
			return;
		}else{
			
		
			$("#results").empty();
			$("#results").html("");
			$("#results").html(dataBack);	
//				if((window.location+"").indexOf("educadores")==-1){
//					window.location=window.location+"#educadores";			
//				}

		}
	
	});
	
}

function callPromociones(){	
	cleanScreen();
	AjaxUtils.loadIntoOnEdit("content",strControllerPromociones+"?event=index",null,function(dataBack) {
		$("#results").html(dataBack);	
	});			
}

function callPlantillas(){
	cleanScreen();
	
	AjaxUtils.loadIntoOnEdit("content",strControllerPlantillaLocation+"?event=index",null,function(dataBack) {
		$("#results").html(dataBack);
	});
	
}
function callMenu(){
	cleanScreen();
	
	AjaxUtils.loadIntoOnEdit("content",strControllerMenuLocation+"?event=menulist",null,function(dataBack) {
		$("#results").html(dataBack);
	});
	
}
function callAlergias(){
	cleanScreen();
	
	AjaxUtils.loadIntoOnEdit("content",strControllerAlergiasLocation+"?event=index",null,function(dataBack) {
		$("#results").html(dataBack);
	});
	
}
function callProfesores(){
	cleanScreen();
	
	AjaxUtils.loadIntoOnEdit("content",strControllerProfesoresLocation+"?event=index",null,function(dataBack) {
		$("#results").html(dataBack);
	});
	
}


function callObservaciones(){
	cleanScreen();
	
	AjaxUtils.loadIntoOnEdit("content",strControllerObservacionesLocation+"?event=index",null,function(dataBack) {
		$("#results").html(dataBack);
	});
	
}

function callDatosGenerales(){
	cleanScreen();
	
	AjaxUtils.loadIntoOnEdit("content",strControllerDatosGeneralesLocation+"?event=index",null,function(dataBack) {
		$("#results").html(dataBack);
	});
	
}

function callSendMessage(){
	cleanScreen();
	
	AjaxUtils.loadIntoOnEdit("content",strControllerSendMessageLocation+"?event=index",null,function(dataBack) {
		$("#results").html(dataBack);
	});
	
}

function guardaAddUser(){
	var datos=$("#frmNewUser").serialize();

	AjaxUtils.loadIntoOnEdit("content",strControllerEducadoresLocation+"?event=save&"+datos,null,function(dataBack) {
		callEducadores();
	});
	
	
}

function guardaAddProfesor(){
	var datos=$("#frmNewProfesor").serialize();
	
	AjaxUtils.loadIntoOnEdit("content",strControllerProfesoresLocation+"?event=save&"+datos,null,function(dataBack) {
		callProfesores();
	});
	
	
}


function guardaEditProfesor(){
	var datos=$("#frmEditProfesor").serialize();
	
	AjaxUtils.loadIntoOnEdit("content",strControllerProfesoresLocation+"?event=save_edit&"+datos,null,function(dataBack) {
		callProfesores();
	});
	
	
}

function guardaEditUser(){
	var datos=$("#frmEditUser").serialize();
	
	AjaxUtils.loadIntoOnEdit("content",strControllerEducadoresLocation+"?event=save_edit&"+datos,null,function(dataBack) {
		callEducadores();
	});
	
	
}
</script>
<style type="text/css">
.seleccion{
width:30%;
align:left;
 display:inline;
 font-family:Calibri, Myriad Pro,Arial,Verdana;
      
}
</style>
</head>

<body >
<%@ include file="/jsp/jsheaders.jsp" %>
<%@ include file="/jsp/headers.jsp" %>
								
<form name="frmSearch">
<div style="width:100%;height:60px;background: #white  50% 50% no-repeat; color: #363636;}">
	<div style="float:left;position:relative;align:center">
	<b>Colegio Virgen del Carmen C.E.I.P  - Administración</b>
	<img src="http://www.colegiovirgendelcarmen.net/uploads/7/7/2/2/7722568/logos.gif" border="0" WIDTH=65 HEIGHT=40/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<span style="float:right;position:relative;align:right">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:logOut()">Cerrar Sesi&oacute;n</a>	
	</span>	
	</div>

</div>

<div id="tabs">
  <ul>
    <li><a href="#tabs-1" onClick="javascript:cleanScreen()">Cursos/Alumnos</a></li>
    <li><a href="#tabs-2" onClick="javascript:cleanScreen()">Educadores/Alumnos</a></li>
    <li id="edutab"><a href="#tabs-3" onClick="javascript:callEducadores()">Educadores</a></li>
    <li><a href="#tabs-plantillas" onClick="javascript:callPlantillas()">Plantillas</a></li>
    <li><a href="#tabs-menus" onClick="javascript:callMenu()">Menu</a></li>
    <li><a href="#tabs-alergias" onClick="javascript:callAlergias()">Alergias</a></li>
    <li><a href="#tabs-profesores" onClick="javascript:callProfesores()">Profesores</a></li>
    <li><a href="#tabs-observaciones" onClick="javascript:callObservaciones()">Observaciones</a></li>
    <li><a href="#tabs-asistencia" onClick="javascript:seeAsistenciaReport()">Calendario de Asistencia</a></li>

  	<li><a href="#tabs-general" onClick="javascript:callSendMessage()">Envio de mensajes</a></li>
    <li><a href="#tabs-promociones" onClick="javascript:callPromociones()">Promociones</a></li>

  </ul>
  <div id="tabs-1">

	<div id="divCursos" style="left:100px;margin-left:200px;align:left">
	<span style="align:left">
	<label for="slCursos"><a href="#cursos"></a>Seleccione un curso:</label>
		<select id="slCursos" name="slCursos" style="width:300px">
		<% 
		for(int i=0;i<cursos.size();i++){
		%>
			<option value="<%=cursos.get(i).getIdcurso()%>"><%=cursos.get(i).getNombre()%> </option>		
		<%
		}
		%> 
		</select>
	</span>
	&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;
	<input type="button" onClick="javascript:searchByCurso()" value="Ver Listado de Alumnos del curso"/>
	<input type="button" onClick="javascript:editCurso()" value="Editar curso"/>
	<input type="button" onClick="javascript:deleteCurso()" value="Borrar curso"/>
	&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;
		
	</div>

		
  </div>
  <div id="tabs-2">
	<p>
	<div id="divEducadores" style="left:100px;margin-left:200px;">
		<h2><a href="#educadores"></a>Listado de Alumnos/Educador</h2>
		<br/>
		<label for="slEducadores">Seleccione un educador:</label>
		<select id="slEducadores" name="slEducadores">
		<% 
		for(int i=0;i<educadores.size();i++){
		%>
			<option value="<%=educadores.get(i).getIduser()%>"><%=educadores.get(i).getNombre()+" "+educadores.get(i).getApellidos()%></option>		
		<%
		}
		%>
		</select>
		<input type="button" onClick="javascript:searchByEducador()" value="Ver Alumnos por Educador"/>
	</div>
	</p>
  </div>
  <div id="tabs-3">
    <div id="lstUsers">
    
    </div>
  </div>
  
<div id="tabs-plantillas">
		
</div>

<div id="tabs-menus">


</div>

<div id="tabs-alergias">
		
</div>
<div id="tabs-profesores">
		
</div>

<div id="tabs-observaciones">
		
</div>
<div id="tabs-asistencia">


</div>
<div id="tabs-general">
		
</div>
<div id="tabs-promociones">

</div>
	  
</div>

</form>
<div id="results" align="left" style="margin-left:1px;height:100%;overflow:scroll">

</div>
<div id="results2" align="left" style="margin-left:1px;height:100%;overflow:scroll;display:none">

</div>




</body>
</html>