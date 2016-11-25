<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dynamicdroides.db.virgendelcarmen.*" %>

<%

Map model=(Map)request.getAttribute("model");
Cursos curso=(Cursos)model.get("curso");

%> 
<br/><br/><br/><br/>
<div id="cursosedit">
<h3>Edicion de curso:</h3>
<hr/>
<form id="editCursos" name="editCursos">
	<input type="hidden" id="idcurso" name="idcurso" value="<%=curso.getIdcurso()%>"></input>
	<label for="nameCurso">Nombre curso:</label>
	<input type="text" id="namecurso" name="namecurso" value="<%=curso.getNombre()%>"></input>
	<input type="button" value="Guardar Cambio" onClick="javascript:saveCurso()"></input>

</form>

</div>
