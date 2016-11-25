<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    

<% 
String nombreApp="WEB";
if(request.getParameter("isapp")!=null){
	nombreApp="MOBILE";
	request.getSession().setAttribute("isTablet",true);
}
%>

<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Virgen del Carmen - P&aacute;gina de login </title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.3.js"></script>

<script type="text/javascript">

	function entrar() {
		document.forms["fData"].submit();
	}

// 	function submite() {
// 		document.forms["fData"].submit();
// 	}
	
// 	$(document).ready(function() {
// 		$('#fData').keypress(function (e) {
// 		    if ((e.which && e.which == 13)) {
// 		    	entrar();
// 		    	return false;
// 		    } else {
// 		        return true;
// 		    }
// 		});
// 	});

	
</script>
<style>
body { 
	
	font-family:Verdana, Arial, Helvetica, sans-serif; 
	font-size:12px; 
	color:#000000;
	/* Note: This gradient may render differently in browsers that don't support the unprefixed gradient syntax */

	/* IE10 Consumer Preview */ 
/* 	background-image: -ms-linear-gradient(top left, #FFFFFF 0%, #96EF8F 100%); */
	
	/* Mozilla Firefox */ 
/* 	background-image: -moz-linear-gradient(top left, #FFFFFF 0%, #96EF8F 100%); */
	
	/* Opera */ 
/* 	background-image: -o-linear-gradient(top left, #FFFFFF 0%, #96EF8F 100%); */
	
	/* Webkit (Safari/Chrome 10) */ 
/* 	background-image: -webkit-gradient(linear, left top, right bottom, color-stop(0, #FFFFFF), color-stop(1, #96EF8F)); */
	
	/* Webkit (Chrome 11+) */ 
/* 	background-image: -webkit-linear-gradient(top left, #FFFFFF 0%, #96EF8F 100%); */
	
	/* W3C Markup, IE10 Release Preview */ 
/* 	background-image: linear-gradient(to bottom right, #FFFFFF 0%, #96EF8F 100%); */

	}
</style>
</head>
<body>
<div style="width:100%;height:100px;background: #EE9A00; color: #333; }">
</div>
<form name="fData" id="fData" action="<c:url value='j_spring_security_check'/>"		method="POST">		
		<table width="100%" valign="middle">
		<tr>
		<td><br/></td>
		<td><br/></td>
		</tr>
		<tr>
		<td valign="middle" align="center">
					<table width="370px" height="220px" align="center"
						style="border: solid 1px; border-radius: 30px; border-color: #CCCCCC; color: #333; background-color: #EE9A00; padding: 8px; font-family: Helvetica, Arial; font-size: 9pt; float: center;">
						<tr>
							<td colspan="2" valign="top">
								<hr color="#333" align="center"/>
								<h1>
									Colegio Virgen del Carmen C.E.I.P Administración
									<img src="http://www.colegiovirgendelcarmen.net/uploads/7/7/2/2/7722568/logos.gif" border="0"/>
								</h1>
								<h1>
									<font color="#333" face="Verdana">Introduzca su usuario y contraseña </font>
								</h1>
								<hr color="#333" align="center"/>
							</td>
						</tr>
						<tr>
							<td colspan="2" valign="top">
							
							<c:if
									test="${param.error == 1}">
									<font color="red"> EL intento de login ha fallado.<br />
									<br/> Razón: <c:out
											value="${SPRING_SECURITY_LAST_EXCEPTION.message}" /></font>
								</c:if> <c:if test="${param.error == 2}">
									<font color="red"> La sesión es invalida. </font>
								</c:if> <c:if test="${param.error == 3}">
									<font color="red"> La sesión ha caducado. </font>
								</c:if></td>
						</tr>
						<tr>
							<td>Nombre de usuario:</td>
							<td><input type='text' id="j_username" name='j_username'/>
								 
							</td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input type='password' id="j_password" name='j_password'/>
							
 							</td>
						</tr>
						
						<tr>
							<td colspan='2' align="center"><br> 
<!-- 								<input type="button" onClick="javascript:submite();" value="Aceptar" /> -->
									<input type="button" onClick="javascript:entrar();" value="Aceptar" />
								<input type="submit" style="display: none" />
								<!-- <input name="reset" id="reset" type="button" value="Restablecer"> -->
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</form>

</body>
</html>