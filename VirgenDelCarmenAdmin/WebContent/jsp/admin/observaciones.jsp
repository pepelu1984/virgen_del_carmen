<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
request.setCharacterEncoding("UTF-8");
Map model=(Map)request.getAttribute("model");

//System.out.println("Buscando..."+selFiltro);

%>

<br/>
<script>

//Declaro un array con el nombre de las columnas que se mostrarán por pantalla
function editerObservacionformatter(cellvalue, options, rowObject) {
    var cellPrefix = '';
    //alert(rowObject.name);
     return cellPrefix + "<a href='javascript:editObservacion(\"" +rowObject.idobservacion+ "\")'>Editar</a>";
    	
}

function deleteObservacionformatter(cellvalue, options, rowObject) {
    var cellPrefix = '';
    //alert(rowObject.name);
    
    return cellPrefix + "<a href='javascript:deleteObservacion(\"" +rowObject.idobservacion+ "\")'>Borrar</a>";
    	
}



var nomColsMsidn=new Array();
//columnsName -> nomCols
//nomColsMsidn= ['AC.ID', 'Nombre', 'ID', 'MSIDN', 'Cantidad','Estado','VAT','Intervalo','Frencuencia','Operador','Ref.Opera','Proveedor','Service Name','Service Category','Dar de Baja'];
nomColsMsidn= ['Editar','Borrar','Descripcion'];

//Declaro un array con el formato de las columnas que se mostrarán por pantalla
var columnsFormat=new Array();

columnsFormat=[
//       	{name:'rowId',index:'rowId', width:60,align:"left",key:true},//, formatter: editerformatter},

 		{name:'idobservacion',index:'idobservacion', width:10,align:"left", formatter: editerObservacionformatter},
 		{name:'idobservacion',index:'idobservacion', width:10,align:"left", formatter: deleteObservacionformatter},
 		{name:'observaciones',index:'observaciones', width:50,align:"left"},
 		
];


var i=0;


$(document).ready(function(){
	$( "#tabsObservaciones" ).tabs();
	
	fillIT('listObservaciones',1100,"Listado de Observaciones",20,nomColsMsidn,columnsFormat,strControllerObservacionesLocation,null,false,true,null,false,null,"&selFiltro2=&desde=&hasta=",null,function genCSV2(strUrl){
		
	});
		
	
});




</script>
<br/>

<div id="tabsObservaciones">
	<ul>
	    <li><a href="#tabListaObservaciones">Listado de Observaciones</a></li>
	    <li><a href="#tabAddObservacion" onClick="javascript:addObservacion()">Agregar Observacion</a></li>
	  </ul>
	

	
	<div id="tabListaObservaciones">
		<div align="left" id="btnlistObservaciones" style="margin-left:-21px"> </div>
			<table id="listObservaciones" align="left"></table>
			<div id="plistObservaciones">
			</div>
	</div>
	
	<div id="tabAddObservacion">
		
	</div>

</div>