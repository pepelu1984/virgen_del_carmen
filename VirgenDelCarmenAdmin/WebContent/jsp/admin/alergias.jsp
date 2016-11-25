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
function editerAlergiaformatter(cellvalue, options, rowObject) {
    var cellPrefix = '';
    //alert(rowObject.name);
     return cellPrefix + "<a href='javascript:editAlergia(\"" +rowObject.idAlergia+ "\")'>Editar</a>";
    	
}

function deleteAlergiaformatter(cellvalue, options, rowObject) {
    var cellPrefix = '';
    //alert(rowObject.name);
    
    return cellPrefix + "<a href='javascript:deleteAlergia(\"" +rowObject.idAlergia+ "\")'>Borrar</a>";
    	
}



var nomColsMsidn=new Array();
//columnsName -> nomCols
//nomColsMsidn= ['AC.ID', 'Nombre', 'ID', 'MSIDN', 'Cantidad','Estado','VAT','Intervalo','Frencuencia','Operador','Ref.Opera','Proveedor','Service Name','Service Category','Dar de Baja'];
nomColsMsidn= ['Editar','Borrar','Descripcion', 'Lista de Comidas' ];

//Declaro un array con el formato de las columnas que se mostrarán por pantalla
var columnsFormat=new Array();

columnsFormat=[
//       	{name:'rowId',index:'rowId', width:60,align:"left",key:true},//, formatter: editerformatter},

 		{name:'idalergia',index:'idalergia', width:10,align:"left", formatter: editerAlergiaformatter},
 		{name:'idalergia',index:'idalergia', width:10,align:"left", formatter: deleteAlergiaformatter},
 		{name:'descripcion',index:'descripcion', width:50,align:"left"},
 		{name:'listacomidas',index:'comidas', width:50,align:"left"},
 		
];


var i=0;


$(document).ready(function(){
	$( "#tabsAlergias" ).tabs();
	
	fillIT('listAlergias',1100,"Listado de Alergias",20,nomColsMsidn,columnsFormat,strControllerAlergiasLocation,null,false,true,null,false,null,"&selFiltro2=&desde=&hasta=",null,function genCSV2(strUrl){
		
	});
		
	
});




</script>
<br/>

<div id="tabsAlergias">
	<ul>
	    <li><a href="#tabListaAlergias">Listado de Alergias</a></li>
	    <li><a href="#tabAddAlergia" onClick="javascript:addAlergia()">Agregar Alergia</a></li>
	  </ul>
	

	
	<div id="tabListaAlergias">
		<div align="left" id="btnlistAlergias" style="margin-left:-21px"> </div>
			<table id="listAlergias" align="left"></table>
			<div id="plistAlergias">
			</div>
	</div>
	
	<div id="tabAddAlergia">
		
	</div>

</div>
