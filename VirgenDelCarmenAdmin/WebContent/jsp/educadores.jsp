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
     return cellPrefix + "<a href='javascript:editEducador(\"" +rowObject.iduser+ "\")'>Editar</a>";
    	
}

function deleteEducadorformatter(cellvalue, options, rowObject) {
    var cellPrefix = '';
    //alert(rowObject.name);
    
    return cellPrefix + "<a href='javascript:deleteEducador(\"" +rowObject.iduser+ "\")'>Borrar</a>";
    	
}

function asistenciaProfformatter(cellvalue, options, rowObject) {
    var cellPrefix = '';
    //alert(rowObject.name);
     return cellPrefix + "<a href='javascript:seeAsistencia(\"" +rowObject.iduser+ "\",false)'>Asistencia</a>";
    	
}

var nomColsMsidn=new Array();
//columnsName -> nomCols
//nomColsMsidn= ['AC.ID', 'Nombre', 'ID', 'MSIDN', 'Cantidad','Estado','VAT','Intervalo','Frencuencia','Operador','Ref.Opera','Proveedor','Service Name','Service Category','Dar de Baja'];
nomColsMsidn= ['Editar','Borrar','Asistencia','Usuario','Nombre', 'Apellidos','Email','Es Administrador' ];

//Declaro un array con el formato de las columnas que se mostrarán por pantalla
var columnsFormat=new Array();

columnsFormat=[
//       	{name:'rowId',index:'rowId', width:60,align:"left",key:true},//, formatter: editerformatter},

 		{name:'iduser',index:'iduser', width:10,align:"left", formatter: editerformatter},
 		{name:'iduser',index:'iduser', width:10,align:"left", formatter: deleteEducadorformatter},
 		{name:'iduser',index:'iduser', width:30,align:"left", formatter: asistenciaProfformatter},
		{name:'user',index:'user', width:50,align:"left"},
 		{name:'nombre',index:'nombre', width:50,align:"left"},
 		{name:'apellidos',index:'apellidos', width:50,align:"left"},
 		{name:'email',index:'email', width:50,align:"left"},
 		{name:'isadmin',index:'isadmin', width:30,align:"left"},
 		
];


var i=0;


$(document).ready(function(){
	$( "#tabsEducadores" ).tabs();
	
	fillIT('listEducadores',1100,"Listado de educadores",20,nomColsMsidn,columnsFormat,strControllerSearchEducadoresLocation,null,false,true,null,false,null,"&selFiltro2=&desde=&hasta=",null,function genCSV2(strUrl){
		
	});
		
	
});




</script>
<br/>

<div id="tabsEducadores">
	<ul>
	    <li><a href="#tabListaEducadores">Listado de Educadores</a></li>
	    <li><a href="#tabAddEducador" onClick="javascript:addEducador()">Agregar Educador</a></li>
	  </ul>
	


<div id="tabListaEducadores">
	<div align="left" id="btnlistEducadores" style="margin-left:-21px"> </div>
		<table id="listEducadores" align="left"></table>
		<div id="plistEducadores">
		</div>
</div>

<div id="tabAddEducador">
	
</div>