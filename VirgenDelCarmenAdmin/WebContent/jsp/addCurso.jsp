<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dynamicdroides.db.virgendelcarmen.*" %>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/menudesChrome.css" />

<%

Map model=(Map)request.getAttribute("model");

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
	<span style="align:center;position:relative;left:300px"><input type="button" onClick="javascript:guardaAlumno()" value="-                             Guardar                             -"></input><br/></span>
	
	<fieldset class="fielprueba" style="margin-top:1px;display:block;margin-left:25px!important">
				<!-- panel de formulario0-->
			<div class="redorfor1">
				<label for="txtName"><b>Nombre</b></label>
				<input type="text" id="txtName" name="txtName"/>
				
				
		</div>
		<div class="redorfor1">
				
				
				<label for="txtApellido2">Segundo Apellido</label>
				<input type="text" id="txtApellido2" name="txtApellido2"/>
				
				<label for="mailPadres">Correo de los padres /tutores </label>
				<input type="text" id="mailPadres" name="mailPadres"/>
				<label for="mailPadres2">Segundo Correo de los padres /tutores </label>
				<input type="text" id="mailPadres2" name="mailPadres2"/>

		</div>
	
</fieldset>
</div>


</form>
</body>
</html>