<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%

Map model=(Map)request.getAttribute("model");
String idalumno=""+model.get("idalumno");
String namealumno=""+model.get("namealumno");

String fechadesde=""+model.get("fechadesde");
String fechahasta=""+model.get("fechahasta");

//System.out.println("Buscando..."+selFiltro);

%>

<br/>
<script>

var nomColsMsidn=new Array();
//columnsName -> nomCols
//nomColsMsidn= ['AC.ID', 'Nombre', 'ID', 'MSIDN', 'Cantidad','Estado','VAT','Intervalo','Frencuencia','Operador','Ref.Opera','Proveedor','Service Name','Service Category','Dar de Baja'];
nomColsMsidn= ['Seleccionar','Tipo', 'Valor', 'Fecha Desde','Fecha Hasta','Observaciones'];

//Declaro un array con el formato de las columnas que se mostrarán por pantalla
var columnsFormat=new Array();

columnsFormat=[
//       	{name:'rowId',index:'rowId', width:60,align:"left",key:true},//, formatter: editerformatter},

 		{name:'idcomportamiento',index:'idcomportamiento', width:30,align:"left", formatter: sendcomportamientoformatter},
 		{name:'tipo',index:'tipo', width:50,align:"left"},
 		{name:'valor',index:'valor', width:50,align:"left"},
 		{name:'fechadesde',fechadesde:'valor', width:50,align:"left"},
 		{name:'fechahasta',index:'fechahasta', width:30,align:"left"},
 		{name:'observaciones',index:'observaciones', width:130,align:"left"},
];


var i=0;

$(document).ready(function(){
	var idalumno="<%=idalumno%>";
	var namealumno="<%=namealumno%>";
	var desde="<%=fechadesde%>";
	var hasta="<%=fechahasta%>";
	
	fillIT('listComportamientos',900,"Comportamiento del alumno:",10,nomColsMsidn,columnsFormat,strControllerSearchComportamientosLocation,<%=idalumno%>,false,true,null,false,null,"&selFiltro2=&desde="+desde+"&hasta="+hasta,null,function genCSV2(strUrl){
		
	});
	
	
	
});




</script>
<br/>

 
 
<div align="left" id="btnlistComportamientos" style="margin-left:-21px"> </div>

 
 
 
<table id="listComportamientos" align="left"></table>


<div id="plistComportamientos">

</div>



<div id="detalleDatos" class="detalleDatos">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</div>