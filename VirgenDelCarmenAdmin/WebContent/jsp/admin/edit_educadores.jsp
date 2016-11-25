<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dynamicdroides.db.virgendelcarmen.*" %>

<%

Map model=(Map)request.getAttribute("model");
List<Cursos> cursos=(List)model.get("cursos");
Usuarios user=(Usuarios )model.get("usuario");


%>

<form id="frmEditUser" name="frmEditUser">
<input type="hidden" id="idUser" name="idUser" value="<%=user.getIduser()%>"/>

	<div class="redorprueba" style="margin-top:1px;display:block;margin-left:25px!important;padding:0px" align="left">
	<fieldset class="fielprueba" style="margin-top:1px;display:block;margin-left:25px!important">
				<!-- panel de formulario0-->
	<div class="redorfor1">
				
			<br/><br/><br/>
				
			<label for="txtName">Nombre</label>
			<input type="text" id="txtName" name="txtName" value="<%=user.getNombre()%>"/>
			
			<label for="txtApellidos">Apellidos</label>
			<input type="text" id="txtApellidos" name="txtApellidos" value="<%=user.getApellidos()%>"/>
		
			<label for="txtApellidos">Nombre de Usuario:</label>
			<input type="text" id="txtUser" name="txtUser" value="<%=user.getUser()%>"/>
	</div>

	<div class="redorfor1">
		
		<label for="txtEmail">Email:</label>
		<input type="text" id="txtEmail" name="txtEmail" value="<%=user.getEmail()%>"/>
			
		<label for="pwd1">Nueva Contraseña</label>
		<input type="password" id="pwd1" name="pwd1" value=""/>
	
		<label for="pwd2">Repetir Contraseña</label>
		<input type="password" id="pwd2" name="pwd2" value=""/>
				
		<input type="button" onClick="javascript:guardaEditUser()" value="Guardar"></input>
	</div>

</fieldset>
</div>


</form>