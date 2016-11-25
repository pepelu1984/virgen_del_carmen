<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dynamicdroides.db.virgendelcarmen.*" %>

<%


Map model=(Map)request.getAttribute("model");
String ideducador=""+model.get("ideducador");
String nameeducador=""+model.get("nameeducador");

List<Usuarios> educadores=(List)model.get("educadores");

//System.out.println("Buscando..."+selFiltro);
List<Cursos> cursosEducadores=(List)model.get("cursos");





//Map model=(Map)request.getAttribute("model");

//List<Cursos> cursosEducadores=(List)model.get("cursos");


%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <script src="<%=request.getContextPath()%>/js/ckeditor/ckeditor.js"></script>
 
  
<script>


$(document).ready(function() {

	
	
	
	CKEDITOR.config.toolbar_Basic = [
		[ 'Source', '-', 'NewPage', 'Preview', '-', 'Templates' ],
		[ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ],
		'/',
		[ 'Bold','Italic','Underline','Strike','Subscript','Superscript','-','RemoveFormat' ],
		[ 'Styles','Format','Font','FontSize' ],
		[ 'TextColor','BGColor' ],
		[ 'Image','Table','HorizontalRule','Smiley','SpecialChar','PageBreak' ],
		[ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote','CreateDiv','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','BidiLtr','BidiRtl' ]		
	    
	];

	CKEDITOR.config.toolbar = 'Basic';

	var ckedit2=CKEDITOR.replace( 'txtMensaje', {
		toolbar: 'Basic',
		uiColor: '#9AB8F3'
		});
	
	
});


function loadEditor(){
	
	AjaxUtils.loadIntoOnEdit("content",strControllerPlantillaLocation+"?event=ckeditor",null,function(dataBack) {
		var datos;
		var error=false;
		
		error=false;
		$("#editor").empty();
		$("#editor").html("");
		$("#editor").html(dataBack);
// 		if((""+window.location).indexOf("plantilla")==-1){
// 			window.location=window.location+"#plantilla";			
// 		}
	});
}


function selectAll(cmbName){
	var select=document.forms["sendMessageForm"][cmbName];
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
<div id="tabs-asign">
<form id="sendMessageForm" name="sendMessageForm">
<input type="hidden" id="ideducador" name="ideducador" value="<%=ideducador%>">
<table width="90%">
		  <tr>
			<td><input type="hidden" id="ideducador" name="ideducador" value="<%=ideducador%>"></td>
		</tr>
		<!--  
		<tr>
				<td><h1>Búsqueda de alumnos para asignar al educador:<%=nameeducador%></h1></td>
		</tr>	
		-->	
		<tr>		
				<td><label for="slCursosUsers">Cursos</label></td>
		</tr>	
		<tr>	
				<td>
				<select width="300" style="width: 300px" id="slCursosUsers" name="slCursosUsers">
					<% 
					for(int i=0;i<cursosEducadores.size();i++){
					%>
					<option value="<%=cursosEducadores.get(i).getIdcurso()%>"><%=cursosEducadores.get(i).getNombre()%></option>		
					<%
					}
					%>
				</select>
				<input type="button" onClick="javascript:searchAlumnosByCurso2()" value="Ver destinatarios"/>
				</td>
				
		</tr>			
				
		<tr>		
		<td colspan="2">
			<span id="divCmbAlumnos"></span>	
			<input type="button" onClick="javascript:selectAll('cmbAlumnosPorCurso')" id="btnAsignar2" style="visibility:hidden" value="Seleccionar todos"/><br/>
			<!--  <input type="button" onClick="javascript:asignAlumnosToEducador('listAlumnosEducadores')" id="btnAsignar" style="visibility:hidden" value="Asignar a educador"/>-->
		</td>
		</tr>
		</table>	
<hr></hr>
<div id="tabs-edittexto">
<!-- <form id="frmEditTexto" name="frmEditTexto"  action="<%=request.getContextPath()%>/mensajes.vdc?event=send" method="POST" enctype="multipart/form-data">-->  
<input type="hidden" id="tipo" name="tipo"/>
<div id="editor1">
	<table width="90%">		
		<tr>			
			<td><h1>Asunto del mensaje</h1><textarea  id="txtSubject" name="txtSubject" cols="20" rows="1"></textarea></td>
		</tr>
		<tr>
			<td >Seleccione el texto que desea agregar al mensaje:</td>
		</tr>
		<tr>			
			<td><textarea class="ckeditor" id="txtMensaje" name="txtMensaje" cols="500" rows="200"></textarea></td>			
			<!--<td><input type="button" value="Enviar mensaje" onClick="javascript:"/></td>-->
		</tr>
		<tr>
			<td><input type="button" onClick="javascript:sendMenssenger()" value="Enviar mensaje"/></td>	
		</tr>
	</table>	
</div>
 <!--  </form>--> 
<!--  <input type="button" onClick="javascript:sendMenssenger()" value="Enviar mensaje"/>-->
</div>
	
</form>		
</div>
