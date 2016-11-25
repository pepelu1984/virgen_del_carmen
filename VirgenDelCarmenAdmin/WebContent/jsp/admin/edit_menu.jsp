<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dynamicdroides.db.virgendelcarmen.Menu" %>

<%

Map model=(Map)request.getAttribute("model");
Menu menu=(Menu)model.get("menu");

%> 
<br/><br/><br/><br/>

<script>
function saveMenu(){

	if(!confirm("Esta seguro que desea editar este menu?"))return;

	var datos=$("#editMenu").serialize();

	AjaxUtils.loadIntoOnEdit("content",strControllerMenuLocation+"?event=save_edit&"+datos,null,function(dataBack) {
		
		callMenu();
		
	});
		

	
}

</script>


<div id="cursosedit">
<h3>Edicion de menú:</h3>
<hr/>
<form id="editMenu" name="editMenu">
	<input type="hidden" id="idmenu" name="idmenu" value="<%=menu.getIdmenu()%>"></input>
	<label for="nameCurso">Nombre curso:</label>
	<input type="text" id="ensalada" name="ensalada" value="<%=menu.getEnsalada()%>"></input>
	<input type="text" id="plato1" name="plato1" value="<%=menu.getPlato1()%>"></input>
	<input type="text" id="plato2" name="plato2" value="<%=menu.getPlato2()%>"></input>
	<input type="text" id="postre" name="postre" value="<%=menu.getPostre()%>"></input>
	
	<input type="button" value="Guardar Cambio" onClick="javascript:saveMenu()"></input>

</form>

</div>
