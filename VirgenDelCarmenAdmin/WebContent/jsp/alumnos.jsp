<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dynamicdroides.db.virgendelcarmen.*" %>
<%
request.setCharacterEncoding("UTF-8");
Map model=(Map)request.getAttribute("model");
String idcurso=""+model.get("idcurso");
//System.out.println("Buscando..."+selFiltro);
//Map model=(Map)request.getAttribute("model");
String ideducador=""+model.get("ideducador");
String nameeducador=""+model.get("nameeducador");
List<Usuarios> educadores=(List)model.get("educadores");

//System.out.println("Buscando..."+selFiltro);
List<Cursos> cursosEducadores=(List)model.get("cursos");
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.transit.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/styles_upload.css" />

<script>


(function() {
    
var bar = $('.bar');
var percent = $('.percent');
var status = $('#status');

$('#frmUpload').ajaxForm({
    beforeSend: function() {
        status.empty();
        var percentVal = '0%';
        bar.width(percentVal)
        percent.html(percentVal);
    },
    uploadProgress: function(event, position, total, percentComplete) {
        var percentVal = percentComplete + '%';
        bar.width(percentVal);
        percent.html(percentVal);
    },
    success: function() {
		//alert("success");
        var percentVal = '100%';
        bar.width(percentVal);
        percent.html(percentVal);
    },
	complete: function(xhr) {
		alert("complete");
		
		//status.html(xhr.responseText);
	}
}); 
       
})();       

//Declaro un array con el nombre de las columnas que se mostrarán por pantalla
function asistenciaformatter(cellvalue, options, rowObject) {
    var cellPrefix = '';
    //alert(rowObject.name);
     return cellPrefix + "<a href='javascript:seeAsistencia(\"" +rowObject.idalumno+ "\",true)'>Asistencia</a>";
    	
}

function editerformatter(cellvalue, options, rowObject) {
    var cellPrefix = '';
    //alert(rowObject.name);
     return cellPrefix + "<a href='javascript:editAlumno(\"" +rowObject.idalumno+ "\")'>Editar</a>";
    	
}
function sendcomportamientoformatter(){
	
    var cellPrefix = '';
    //alert(rowObject.name);
    return cellPrefix + "<a href='javascript:sendComportamiento(\"" +rowObject.idcomportamiento+ "\",\""+rowObject.curso+"\")'>Enviar</a>";
	
}

function deleteformatter(cellvalue, options, rowObject) {
    var cellPrefix = '';
    //alert(rowObject.name);
    return cellPrefix + "<a href='javascript:deleteAlumno(\"" +rowObject.idalumno+ "\",\""+rowObject.curso+"\",\""+rowObject.nombre+"\",\""+rowObject.apellidos+"\",\""+rowObject.correopadres+"\")'><img src='<%=request.getContextPath()%>/images/remove.png' height=20px border=0/></a>";
    	
}
function comportamientoformatter(cellvalue, options, rowObject) {
    var cellPrefix = '';
    //alert(rowObject.name);
    if(rowObject.state!="Terminado"){
        return cellPrefix + "<a href='javascript:searchComportamientosByAlumno(\"" +rowObject.idalumno+ "\",\""+rowObject.curso+"\",\""+rowObject.nombre+"\",\""+rowObject.apellidos+"\",\""+rowObject.correopadres+"\")'>Comportamientos</a>";
    }else{
    	return "";
    }
    	
}
function comidasformatter(cellvalue, options, rowObject) {
    var cellPrefix = '';
    //alert(rowObject.name);
    if(rowObject.state!="Terminado"){
        return cellPrefix + "<a href='javascript:seeComidas(\"" +rowObject.idalumno+ "\")'>Comidas</a>";
    }else{
    	return "";
    }
    	
}


var nomColsMsidn=new Array();
//columnsName -> nomCols
//nomColsMsidn= ['AC.ID', 'Nombre', 'ID', 'MSIDN', 'Cantidad','Estado','VAT','Intervalo','Frencuencia','Operador','Ref.Opera','Proveedor','Service Name','Service Category','Dar de Baja'];
nomColsMsidn= ['  ','Borrar','Comportamientos','Asistencia', 'Apellidos','Nombre','Correo Padres 1','Correo Padres 2','List de Alergias' ];

//Declaro un array con el formato de las columnas que se mostrarán por pantalla
var columnsFormat=new Array();
columnsFormat=[
//       	{name:'rowId',index:'rowId', width:60,align:"left",key:true},//, formatter: editerformatter},

 		{name:'idalumno',index:'idalumno', width:20,align:"left", formatter: editerformatter},
 		{name:'idalumno',index:'idalumno', width:25,align:"center", formatter: deleteformatter},
 		{name:'idalumno',index:'idalumno', width:35,align:"left", formatter: comportamientoformatter},
 		{name:'idalumno',index:'idalumno', width:35,align:"left", formatter: asistenciaformatter},
//  		{name:'idalumno',index:'idalumno', width:30,align:"left", formatter: comidasformatter},
 		{name:'apellidos',index:'apellidos', width:110,align:"left"},
 		{name:'nombre',index:'nombre', width:50,align:"left"},
 		//{name:'curso',index:'curso', width:10,align:"left"},
 		{name:'correopadres',index:'correopadres', width:140,align:"left"}, 
 		//INI SEGUNDO CORREO
 		{name:'correopadres2',index:'correopadres2', width:140,align:"left"},
 		//FIN SEGUNDO CORREO
 		{name:'listaalergias',index:'listaalergias', width:110,align:"left"},
 		
 		//{name:'comedor',index:'comedor', width:1,align:"left"},
 		
];


var i=0;
var strControllerSearchLocation="<%=request.getContextPath()%>/search.vdc";

$(document).ready(function(){
	var idcurso="<%=idcurso%>";
	$( "#tabsAlumnos" ).tabs();
	fillIT('listAlumnos',1100,"Listado de alumnos",20,nomColsMsidn,columnsFormat,'<%=request.getContextPath()%>/search.vdc',<%=idcurso%>,false,true,null,false,null,"&selFiltro2=&desde=&hasta=",null,function genCSV2(strUrl){
		window.location="<%=request.getContextPath()%>/manageAlumno.vdc?event=excel&idcurso="+idcurso;
	});
	
	
});
</script>

<br/>
<br/>
<div id="tabsAlumnos">
	<ul>
	    <li><a href="#tabListaAlumnos">Listado de Alumnos para el curso:</a></li>
	    <li><a href="#tabAddAlumno" onClick="javascript:addAlumno()">Agregar Alumno</a></li>	    
	    <li><a href="#tabUpload">Subir Excel de alumnos</a></li>
		<li><a href="#tabAlergias" onClick="javascript:asignarAlergia()">Asignar Alergias</a></li>
		<!-- <li><a href="#tabAlergias">Asignar Alergias</a></li>-->
	  </ul>
	
	<div id="tabListaAlumnos">
				<div align="left" id="btnlistAlumnos" style="margin-left:-21px"> </div>
				<table id="listAlumnos" align="left"></table>
			<div id="plistAlumnos">
			</div>
	</div>
	<div id="tabAddAlumno">
		<div id="newalumno">
		</div>
	</div>
	<div id="tabUpload">
		<p>
			<h1>Plantilla Alumnos:</h1>
			<A href="javascript:downloadExcel()">Descargar Plantilla Excel Alumnos</A>
		</p>
		<br/>
		<br/>
		<h1>Subir Alumnos a traves de Excel:</h1>
		<form id="frmUpload" name="frmUpload" action="<%=request.getContextPath()%>/upexcel.vdc?event=save" method="POST" enctype="multipart/form-data">
			<input type="hidden" id="idcurso" name="idcurso" value="<%=idcurso%>"/>
			<label for="fileselect">Seleccionar archivo excel de alumnos:</label>
			<input type="file" id="upExcel" name="upExcel"/>
			<div id="submitbutton">
				<input type="submit" value="Subir Fichero Excel de Alumnos"/>
			</div>
			<div class="progress">
				<div class="bar"></div >
				<div class="percent">0%</div >
			</div>
			<div id="status"></div>		
		</form>
	</div>
	<div id="tabAlergias">
			<div id="asignaralergias">
			</div>
	</div>
</div>
