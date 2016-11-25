<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dynamicdroides.db.virgendelcarmen.*" %>

<%

Map model=(Map)request.getAttribute("model");


%>

<form id="frmNewUser" name="frmNewUser">

<div class="redorprueba" style="margin-top:1px;display:block;margin-left:25px!important;padding:0px" align="left">
	<span style="align:center;position:relative;left:400px">
			<input type="button" onClick="javascript:guardaAddUser()" value="                          Guardar                            "></input>
		<br/><br/>
	</span>

<fieldset class="fielprueba" style="margin-top:1px;display:block;margin-left:25px!important">
				<!-- panel de formulario0-->
<div class="redorfor1">
				
	<label for="txtName">Nombre</label>
	<input type="text" id="txtName" name="txtName" value=""/>
	
	<label for="txtApellidos">Apellidos</label>
	<input type="text" id="txtApellidos" name="txtApellidos" value=""/>

	<label for="txtUser">Nombre de Usuario:</label>
	<input type="text" id="txtUser" name="txtUser" value=""/>
	</div>
<div class="redorfor1">
	
	<label for="txtEmail">Email:</label>
	<input type="text" id="txtEmail" name="txtEmail" value=""/>
		
	<label for="pwd1">Contraseña</label>
	<input type="password" id="pwd1" name="pwd1"/>

	<label for="pwd2">Repetir Contraseña</label>
	<input type="password" id="pwd2" name="pwd2"/>
			
	
	</div>

</fieldset>

</div>


</form>