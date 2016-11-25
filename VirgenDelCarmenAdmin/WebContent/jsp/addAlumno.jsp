<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dynamicdroides.db.virgendelcarmen.*" %>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/menudesChrome.css" />

<%

Map model=(Map)request.getAttribute("model");
List<Cursos> cursos=(List)model.get("cursos");

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>


</script>
</head>
<body>
<form id="frmAdd" name="frmAdd">
	<div class="redorprueba" style="margin-top:1px;display:block;margin-left:25px!important;padding:0px" align="left">
	<span style="align:left;position:relative;left:10px">
		<h1><b>Añadir Nuevo Alumno</b></h1>
	</span>
	<span style="align:center;position:relative;left:400px">
			<input type="button" onClick="javascript:guardaAlumno()" value="-                             Guardar                             -"></input>
		<br/><br/>
	</span>
	
	<fieldset class="fielprueba" style="margin-top:1px;display:block;margin-left:25px!important">
				<!-- panel de formulario0-->
			<div class="redorfor1">
				<p align="left">Curso:
				<select style="margin-left:0px" id="slCursos" name="slCursos">
					<% 
					for(int i=0;i<cursos.size();i++){
					%>
						<option value="<%=cursos.get(i).getIdcurso()%>"><%=cursos.get(i).getNombre()%></option>		
					<%
					}
					%>
				</select>
				</p><br/>
				<label for="txtName"><b>Nombre</b></label>
				<input type="text" id="txtName" name="txtName"/>
				
				<label for="txtApellido1"><b>Primer Apellido</b></label>
				<input type="text" id="txtApellido1" name="txtApellido1"/>
				
				<label for="txtApellido1"><b>Tiene Comedor?:</b></label>
				<input type="checkbox" id="hasComedor" name="hasComedor" value="1"/>
				
		</div>
		<div class="redorfor1">
				
				
				<label for="txtApellido2"><b>Segundo Apellido</b></label>
				<input type="text" id="txtApellido2" name="txtApellido2"/>
				
				<label for="mailPadres"><b>Correo de los padres /tutores </b></label>
				<input type="text" id="mailPadres" name="mailPadres"/>
				<label for="mailPadres2"><b>Segundo Correo de los padres /tutores </b></label>
				<input type="text" id="mailPadres2" name="mailPadres2"/>

		</div>
	
</fieldset>
</div>


</form>
</body>
</html>