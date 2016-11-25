<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dynamicdroides.db.virgendelcarmen.*" %>

<%

Map model=(Map)request.getAttribute("model");
String ideducador=""+model.get("ideducador");
String nameeducador=""+model.get("nameeducador");

List<Usuarios> educadores=(List)model.get("educadores");

//System.out.println("Buscando..."+selFiltro);
List<Cursos> cursosEducadores=(List)model.get("cursos");

%>

<br/>
<script>

//Declaro un array con el nombre de las columnas que se mostrarán por pantalla
function editerformatter(cellvalue, options, rowObject) {
    var cellPrefix = '';
    //var grid=$("#listAlumnosEducadores");
    //alert(rowObject.name);
    if(rowObject.state!="Terminado"){
        return cellPrefix + "<a href='javascript:quitarAlumnoEducador(\"" +rowObject.idalumno+ "\",\""+ideducador+"\",\""+"listAlumnosEducadores"+"\")'>Desasignar</a>";
    }else{
    	return "";
    }
    	
}

var nomColsMsidn=new Array();
//columnsName -> nomCols
//nomColsMsidn= ['AC.ID', 'Nombre', 'ID', 'MSIDN', 'Cantidad','Estado','VAT','Intervalo','Frencuencia','Operador','Ref.Opera','Proveedor','Service Name','Service Category','Dar de Baja'];
nomColsMsidn= ['Desasignar','Apellidos','Nombre', 'Curso'];

//Declaro un array con el formato de las columnas que se mostrarán por pantalla
var columnsFormat=new Array();

columnsFormat=[
//       	{name:'rowId',index:'rowId', width:60,align:"left",key:true},//, formatter: editerformatter},

 		{name:'idalumno',index:'idalumno', width:10,align:"left", formatter: editerformatter},
 		{name:'apellidos',index:'apellidos', width:80,align:"left"},
 		{name:'nombre',index:'nombre', width:50,align:"left"},
 		{name:'curso',index:'curso', width:20,align:"left"},
];


var i=0;
var ideducador="<%=ideducador%>";
$(document).ready(function(){
	
	$('#tabseducador').tabs();
	fillIT('listAlumnosEducadores',1100,"Listado de alumnos para el educador:<%=nameeducador%>",10,nomColsMsidn,columnsFormat,strControllerSearchEducadoresLocation,'<%=ideducador%>',false,true,null,false,null,"&selFiltro2=&desde=&hasta=",null,function genCSV2(strUrl){
		
		
	});
	
});
function selectAll(cmbName){
	var select=document.forms["frmalumnoUsers"][cmbName];
	  var result = null;
	  var options = select && select.options;
	  var opt;

	  for (var i=0, iLen=options.length; i<iLen; i++) {
	    opt = options[i];

	    if (!opt.selected) {
	    	opt.selected=true;
	    }
	  }
	  return result;

}



</script>
<br/>
<div id="tabseducador">
  <ul>
    <li><a href="#tabs-alueduca">Listado de alumnos para el educador:<%=nameeducador%></a></li>
    <li><a href="#tabs-asign">Asignaci&oacute;n alumnos a educador:<%=nameeducador%></a></li>
    <li><a href="#tabs-reasign">Reasignacion de educadores</a></li>

</ul>
<div id="tabs-asign">
	<form id="frmalumnoUsers" name="frmalumnoUsers">
	<input type="hidden" id="ideducador" name="ideducador" value="<%=ideducador%>">
				<h1>Búsqueda de alumnos para asignar al educador :<%=nameeducador%></h1>
				
				<label for="slCursosUsers">Cursos</label>
				<select width="300" style="width: 300px" id="slCursosUsers" name="slCursosUsers">
					<% 
					for(int i=0;i<cursosEducadores.size();i++){
					%>
					<option value="<%=cursosEducadores.get(i).getIdcurso()%>"><%=cursosEducadores.get(i).getNombre()%></option>		
					<%
					}
					%>
				</select>
						
				<input type="button" onClick="javascript:searchAlumnosByCurso()" value="Ver Alumnos"/>			
				
								
				
								
	<hr></hr>
	<span id="divCmbAlumnos">
				
	</span>
	</form>
	<input type="button" onClick="javascript:selectAll('cmbAlumnosPorCurso')" id="btnAsignar2" style="visibility:hidden" value="Seleccionar todos"/><br/>
	<input type="button" onClick="javascript:asignAlumnosToEducador('listAlumnosEducadores')" id="btnAsignar" style="visibility:hidden" value="Asignar a educador"/>
	
</div>
<br/>
<br/>
<br/>
	<div  id="tabs-alueduca">
		<div align="left" id="btnlistAlumnosEducadores" style="margin-left:-21px"> </div>
		 
		<table id="listAlumnosEducadores" align="left"></table>
		
		
		<div id="plistAlumnosEducadores">
		
		</div>
	</div>
	<div  id="tabs-reasign">
			<label for="slasignEducadores">Resasignar alumnos a educador:</label>
		<select id="slasignEducadores" name="slasignEducadores">
			<% 
			for(int i=0;i<educadores.size();i++){
				if(ideducador!="null" && educadores.get(i).getIduser()!=Integer.parseInt(ideducador)){
			%>
				<option value="<%=educadores.get(i).getIduser()%>"><%=educadores.get(i).getNombre()+educadores.get(i).getApellidos()%></option>		
			<%
			}
		}
		%>
		</select>
		<input type="button" onClick="javascript:reasignAlumnos()" value="Reasignar"/>
	
	</div>

</div>
<br/>
<br/>
