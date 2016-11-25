<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dynamicdroides.db.virgendelcarmen.*" %>

<%

Map model=(Map)request.getAttribute("model");


%>

<form id="frmEditObservacion" name="frmEditObservacion">
<input type="hidden" id="idobservacion" name="idobservacion"/>

<div class="redorprueba" style="margin-top:1px;display:block;margin-left:25px!important;padding:0px" align="left">
	<span style="align:center;position:relative;left:400px">
			<input type="button" onClick="javascript:guardaEditObservacion()" value="                          Guardar                            "></input>
		<br/><br/>
	</span>

<fieldset class="fielprueba" style="margin-top:1px;display:block;margin-left:25px!important">
				<!-- panel de formulario0-->
	<div class="redorfor1">
					
		<label for="txtName">Observacion:</label>
		<textarea id="txtObservacion" name="txtObservacion" rows="20" cols="30"></textarea>
		
	</div>

</fieldset>
</div>

</form>