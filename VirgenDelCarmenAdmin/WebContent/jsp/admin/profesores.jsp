<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
request.setCharacterEncoding("UTF-8");
Map model=(Map)request.getAttribute("model");

//System.out.println("Buscando..."+selFiltro);

%>

<br/>
<script>



(function() {
    
var bar = $('.bar');
var percent = $('.percent');
var status = $('#status');

$('#frmUploadProfesores').ajaxForm({
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
		callProfesores();
		//status.html(xhr.responseText);
	}
}); 
       
})();       



//Declaro un array con el nombre de las columnas que se mostrarán por pantalla
function editerformatter(cellvalue, options, rowObject) {
    var cellPrefix = '';
    //alert(rowObject.name);
     return cellPrefix + "<a href='javascript:editProfesor(\"" +rowObject.idprofesor+ "\")'>Editar</a>";
    	
}

function deleteProfesorformatter(cellvalue, options, rowObject) {
    var cellPrefix = '';
    //alert(rowObject.name);
    
    return cellPrefix + "<a href='javascript:deleteProfesor(\"" +rowObject.idprofesor+ "\")'>Borrar</a>";
    	
}



var nomColsMsidn=new Array();
//columnsName -> nomCols
//nomColsMsidn= ['AC.ID', 'Nombre', 'ID', 'MSIDN', 'Cantidad','Estado','VAT','Intervalo','Frencuencia','Operador','Ref.Opera','Proveedor','Service Name','Service Category','Dar de Baja'];
nomColsMsidn= ['Editar','Borrar','Nombre', 'Apellidos','Email','Telefono' ];

//Declaro un array con el formato de las columnas que se mostrarán por pantalla
var columnsFormat=new Array();

columnsFormat=[
//       	{name:'rowId',index:'rowId', width:60,align:"left",key:true},//, formatter: editerformatter},

 		{name:'idprofesor',index:'idprofesor', width:10,align:"left", formatter: editerformatter},
 		{name:'idprofesor',index:'idprofesor', width:10,align:"left", formatter: deleteProfesorformatter},
 		{name:'nombre',index:'nombre', width:50,align:"left"},
 		{name:'apellidos',index:'apellidos', width:50,align:"left"},
 		{name:'email',index:'email', width:50,align:"left"},
 		{name:'telefono',index:'telefono', width:30,align:"left"},
 		
];


var i=0;


$(document).ready(function(){
	$( "#tabsProfesores" ).tabs();
	
	fillIT('listProfesores',1100,"Listado de Profesores",20,nomColsMsidn,columnsFormat,strControllerProfesoresLocation,null,false,true,null,false,null,"&selFiltro2=&desde=&hasta=",null,function genCSV2(strUrl){
		
	});
		
	
});




</script>
<br/>

<div id="tabsProfesores">
	<ul>
	    <li><a href="#tabListaProfesores">Listado de Profesores</a></li>
	    <li><a href="#tabAddProfesor" onClick="javascript:addProfesor()">Agregar Profesor</a></li>
	    <li><a href="#tabUploadProfesores">Subir Profesores Excel</a></li>
	  </ul>
	

	
	<div id="tabListaProfesores">
		<div align="left" id="btnlistProfesores" style="margin-left:-21px"> </div>
			<table id="listProfesores" align="left"></table>
			<div id="plistProfesores">
			</div>
	</div>
	
	<div id="tabAddProfesor">
		
	</div>


<div id="tabUploadProfesores">
		<p>
			<h1>Plantilla Profesores:</h1>
			<A href="javascript:downloadExcelProfesores()">Descargar Plantilla Excel Profesores</A>
		</p>
		<br/>
		<br/>
		<h1>Subir Profesores a traves de Excel:</h1>
		<form id="frmUploadProfesores" name="frmUploadProfesores" action="<%=request.getContextPath()%>/profesores.vdc?event=upload" method="POST" enctype="multipart/form-data">
			<label for="fileselect">Seleccionar archivo excel de alumnos:</label>
			<input type="file" id="upExcel" name="upExcel"/>
			<div id="submitbutton">
				<input type="submit" value="Subir Fichero Excel de Profesores"/>
			</div>
			<div class="progress">
				<div class="bar"></div >
				<div class="percent">0%</div >
			</div>
			<div id="status"></div>		
		</form>
	</div>
	
</div>
