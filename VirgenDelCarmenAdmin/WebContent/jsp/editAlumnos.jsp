<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dynamicdroides.db.virgendelcarmen.*" %>

<%

Map model=(Map)request.getAttribute("model");
List<Cursos> cursos=(List)model.get("cursos");
Alumnos alumno=(Alumnos)model.get("alumno");

String correos=alumno.getCorreopadres();
String correo1="";
String correo2="";

String[] correosArr=null;
correosArr=correos.split(",");

if(correosArr.length>0){
		correo1=correosArr[0];
}
if(correosArr.length>1){
		correo2=correosArr[1];
}
%>

<form id="frmEdit" name="frmEdit">
<input type="hidden" id="idAlumno" name="idAlumno" value="<%=alumno.getIdalumno()%>"/>
<h3><a href="javascript:goBack()"><b>Volver al listado de alumnos </b></a>---> Edicion de alumno: </h3>										
	<span style="align:center;position:relative;left:300px"><input type="button" onClick="javascript:guardaEditAlumno()" value="-                                     Guardar                                             -"></input></span>


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
		</div>
		<div class="redorfor1">
				
				<label for="txtName"><b>Nombre</b></label>
				<input type="text" id="txtName" name="txtName" value="<%=alumno.getNombre()%>"/>
				
				<label for="txtApellidos"><b>Apellidos</b></label>
				<input type="text" id="txtApellidos" name="txtApellidos" value="<%=alumno.getApellidos()%>"/>
		</div>
		<div class="redorfor1">
				<label for="mailPadres"><b>Correo de los padres /tutores </b></label>
				<input type="text" id="mailPadres" name="mailPadres" value="<%=correo1%>"/>
				<label for="mailPadres"><b>Segundo Correo de los padres /tutores </b></label>
    			<input type="text" id="mailPadres2" name="mailPadres2" value="<%=correo2%>"/>
	 					
				<label for="txtApellido1"><b>Tiene Comedor?:</b></label>
				<% if(alumno.getComedor()!=null && alumno.getComedor()){%>
					<input type="checkbox" checked id="hasComedor" name="hasComedor" value="1"/>
				<% }else{%>
						<input type="checkbox" id="hasComedor" name="hasComedor" value="1"/>
				<% }%>
			
		</div>

</fieldset>
</div>


</form>