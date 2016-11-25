<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dynamicdroides.db.virgendelcarmen.*" %>

<%

Map model=(Map)request.getAttribute("model");
List<Cursos> cursos=(List)model.get("cursos");
Alumnos alumno=(Alumnos)model.get("alumno");


%>

<form id="frmEdit" name="frmEdit">
<input type="hidden" id="idAlumno" name="idAlumno" value="<%=alumno.getIdalumno()%>"/>

	<div class="redorprueba" style="margin-top:1px;display:block;margin-left:25px!important;padding:0px" align="left">
	<fieldset class="fielprueba" style="margin-top:1px;display:block;margin-left:25px!important">
				<!-- panel de formulario0-->
	<div class="redorfor1">
				
	<p align="left">Curso:
	<select style="margin-left:0px" id="slCursos" name="slCursos">
		<% 
		for(int i=0;i<cursos.size();i++){
		if(alumno.getCurso().equals(cursos.get(i).getIdcurso()+"")){
		%>
			<option selected value="<%=cursos.get(i).getIdcurso()%>"><%=cursos.get(i).getNombre()%></option>		
		<%
		}else{
			%>
			<option value="<%=cursos.get(i).getIdcurso()%>"><%=cursos.get(i).getNombre()%></option>		
		<%
			
		}
		}
		%>
	</select>
	</p><br/><br/><br/>
		
	<label for="txtName">Nombre</label>
	<input type="text" id="txtName" name="txtName" value="<%=alumno.getNombre()%>"/>
	
	<label for="txtApellidos">Apellidos</label>
	<input type="text" id="txtApellidos" name="txtApellidos" value="<%=alumno.getApellidos()%>"/>
		
	<label for="mailPadres">Correo de los padres /tutores </label>
	<input type="text" id="mailPadres" name="mailPadres" value="<%=alumno.getCorreopadres()%>"/>
	<label for="mailPadres2">Segundo Correo de los padres /tutores </label>
	<input type="text" id="mailPadres2" name="mailPadres2"/>

	<input type="button" onClick="javascript:guardaEditAlumno()" value="Guardar"></input>
	</div>

</fieldset>
</div>


</form>