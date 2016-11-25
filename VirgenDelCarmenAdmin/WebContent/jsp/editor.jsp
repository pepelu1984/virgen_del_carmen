<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath()%>/js/ckeditor/ckeditor.js"></script>
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/js/ckeditor/sample.css"> --%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form id="frmPlantilla" name="frmPlantilla">

	<table>
		<tr>
		<td>Seleccione el texto que desea agregar al mensaje:</td>
		<td><textarea class="ckeditor" id="txtInptSemanal" name="txtInptSemanal" cols="50" rows="15"/></td>
		<td><input type="button" value="Cambiar Texto" onClick="javascript:cambiarTexto('semanal')"/></td>
		</tr>
	</table>	

</form>
</body>
</html>