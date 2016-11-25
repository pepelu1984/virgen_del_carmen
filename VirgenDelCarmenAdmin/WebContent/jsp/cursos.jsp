<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
request.setCharacterEncoding("UTF-8");
Map model=(Map)request.getAttribute("model");
String idcurso=""+model.get("idcurso");
//System.out.println("Buscando..."+selFiltro);

%>

<br/>
<script>

//Declaro un array con el nombre de las columnas que se mostrarán por pantalla
function editerformatter(cellvalue, options, rowObject) {
    var cellPrefix = '';
    //alert(rowObject.name);
     return cellPrefix + "<a href='javascript:editCurso(\"" +rowObject.idCurso+ "\")'>Editar</a>";
    	
}
function sendcomportamientoformatter(){
	
    var cellPrefix = '';
    //alert(rowObject.name);
    return cellPrefix + "<a href='javascript:sendComportamiento(\"" +rowObject.idcomportamiento+ "\",\""+rowObject.curso+"\")'>Enviar</a>";
	
}

function deleteformatter(cellvalue, options, rowObject) {
    var cellPrefix = '';
    //alert(rowObject.name);
    return cellPrefix + "<a href='javascript:deleteCurso(\"" +rowObject.idCurso+ "\",\""+rowObject.curso+"\",\""+rowObject.nombre+"\",\""+rowObject.apellidos+"\",\""+rowObject.correopadres+"\")'>Comportamientos</a>";
    	
}
function comportamientoformatter(cellvalue, options, rowObject) {
    var cellPrefix = '';
    //alert(rowObject.name);
    if(rowObject.state!="Terminado"){
        return cellPrefix + "<a href='javascript:searchComportamientosByCurso(\"" +rowObject.idCurso+ "\",\""+rowObject.curso+"\",\""+rowObject.nombre+"\",\""+rowObject.apellidos+"\",\""+rowObject.correopadres+"\")'>Comportamientos</a>";
    }else{
    	return "";
    }
    	
}
function comidasformatter(cellvalue, options, rowObject) {
    var cellPrefix = '';
    //alert(rowObject.name);
    if(rowObject.state!="Terminado"){
        return cellPrefix + "<a href='javascript:seeComidas(\"" +rowObject.idCurso+ "\")'>Comidas</a>";
    }else{
    	return "";
    }
    	
}


var nomColsMsidn=new Array();
//columnsName -> nomCols
//nomColsMsidn= ['AC.ID', 'Nombre', 'ID', 'MSIDN', 'Cantidad','Estado','VAT','Intervalo','Frencuencia','Operador','Ref.Opera','Proveedor','Service Name','Service Category','Dar de Baja'];
nomColsMsidn= ['Seleccionar','Borrar','Comportamientos','Nombre', 'Apellidos','Curso','Correo Padres','Comedor' ];

//Declaro un array con el formato de las columnas que se mostrarán por pantalla
var columnsFormat=new Array();

columnsFormat=[
//       	{name:'rowId',index:'rowId', width:60,align:"left",key:true},//, formatter: editerformatter},

 		{name:'idCurso',index:'idCurso', width:30,align:"left", formatter: editerformatter},
 		{name:'idCurso',index:'idCurso', width:30,align:"left", formatter: deleteformatter},
 		{name:'idCurso',index:'idCurso', width:30,align:"left", formatter: comportamientoformatter},
//  		{name:'idCurso',index:'idCurso', width:30,align:"left", formatter: comidasformatter},
 		{name:'nombre',index:'nombre', width:50,align:"left"},
 		{name:'apellidos',index:'apellidos', width:50,align:"left"},
 		{name:'curso',index:'curso', width:10,align:"left"},
 		{name:'correopadres',index:'correopadres', width:50,align:"left"},
 		{name:'comedor',index:'comedor', width:30,align:"left"},
 		
];


var i=0;
var strControllerSearchLocation="<%=request.getContextPath()%>/cursos.vdc";

$(document).ready(function(){
	var idcurso="<%=idcurso%>";
	$( "#tabs3" ).tabs();
	fillIT('listCursos',1100,"Listado de Cursos",20,nomColsMsidn,columnsFormat,'<%=request.getContextPath()%>/search.vdc',<%=idcurso%>,false,true,null,false,null,"&selFiltro2=&desde=&hasta=",null,function genCSV2(strUrl){
		window.location="<%=request.getContextPath()%>/curso.vdc?event=excel&idcurso="+idcurso;
	});
	
	
});




</script>
<br/>
<div id="tabs3">
	<ul>
	    <li><a href="#tabListaCursos">Listado de Cursos para el curso:</a></li>
	    <li><a href="tabListaCursos">Agregar Curso</a></li>
	
	  </ul>
	
	<div id="tabListaCursos">
				<div align="left" id="btnlistCursos" style="margin-left:-21px"> </div>
				<table id="listCursos" align="left"></table>
			<div id="plistCursos">
			</div>
	</div>
	<div id="tabAddCurso">
		<p>
			<A href="javascript:addCurso()">Agregar Nuevo Curso</A>
		</p>
		<div id="newCurso">
		</div>
	</div>
</div>
