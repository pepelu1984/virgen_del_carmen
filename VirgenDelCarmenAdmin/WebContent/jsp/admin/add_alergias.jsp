<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dynamicdroides.db.virgendelcarmen.*" %>

<%

Map model=(Map)request.getAttribute("model");


%>

<form id="frmNewAlergia" name="frmNewAlergia">

<div class="redorprueba" style="margin-top:1px;display:block;margin-left:25px!important;padding:0px" align="left">
	<span style="align:center;position:relative;left:400px">
			<input type="button" onClick="javascript:guardaAddAlergia()" value="                          Guardar                            "></input>
		<br/><br/>
	</span>

<fieldset class="fielprueba" style="margin-top:1px;display:block;margin-left:25px!important">
				<!-- panel de formulario0-->
	<div class="redorfor1">
				
	<!--  <label for="idalergia">idalergia</label>
	<input type="text" id="idalergia" name="idalergia" value=""/>			
	-->	
	<label for="Descripcion">Descripcion</label>
	<input type="text" id="descripcion" name="descripcion" value=""/>

	<label for="txtUser">Lista comidas</label>
	<input type="text" id="listacomidas" name="listacomidas" value=""/>
	</div>

</fieldset>

</div>


</form>