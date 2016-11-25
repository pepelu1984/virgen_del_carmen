<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*" %>
<%@ page import="com.dynamicdroides.db.virgendelcarmen.*" %>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/menudesChrome.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.transit.min.js"></script>
  <script src="<%=request.getContextPath()%>/js/ckeditor/ckeditor.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/styles_upload.css" />

<%

Map model=(Map)request.getAttribute("model");
List<Cursos> cursos=(List)model.get("cursos");
List<String> listaImagenes=(List)model.get("imagenes");

%>

<script>


$(document).ready(function() {

	$( "#tabsplantilla" ).tabs({
        activate: function (event, ui) {
			selected = ui.newTab.context.id;
        }
    });
	$( "#tabsSemanal" ).tabs({
        activate: function (event, ui) {
			selected = ui.newTab.context.id;
        }
    });
	$( "#tabsDiaria" ).tabs({
        activate: function (event, ui) {
			selected = ui.newTab.context.id;
        }
    });
	
	
	CKEDITOR.config.toolbar_Basic = [
		[ 'Source', '-', 'NewPage', 'Preview', '-', 'Templates' ],
		[ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ],
		'/',
		[ 'Bold','Italic','Underline','Strike','Subscript','Superscript','-','RemoveFormat' ],
		[ 'Styles','Format','Font','FontSize' ],
		[ 'TextColor','BGColor' ],
		[ 'Image','Table','HorizontalRule','Smiley','SpecialChar','PageBreak' ],
		[ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote','CreateDiv','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','BidiLtr','BidiRtl' ]		
	    
	];

	CKEDITOR.config.toolbar = 'Basic';

	var ckedit2=CKEDITOR.replace( 'txtInptSemanal', {
		toolbar: 'Basic',
		uiColor: '#9AB8F3'
		});
	
	var ckeditInf=CKEDITOR.replace( 'txtInptSemanalinf', {
		toolbar: 'Basic',
		uiColor: '#9AB8F3'
		});
	
});
function loadEditor(){
	
	AjaxUtils.loadIntoOnEdit("content",strControllerPlantillaLocation+"?event=ckeditor",null,function(dataBack) {
		var datos;
		var error=false;
		
		error=false;
		$("#editor").empty();
		$("#editor").html("");
		$("#editor").html(dataBack);
// 		if((""+window.location).indexOf("plantilla")==-1){
// 			window.location=window.location+"#plantilla";			
// 		}
	});
}
function cambiarTexto(tipo){
	var observaciones="";
	var datos="";
	
	if(tipo=="diaria"){
		alert("diaria");
		observaciones=encodeURI(CKEDITOR.instances["txtInptSemanalinf"].getData());
		document.forms["frmEditTexto2"]["tipo"].value=tipo;
		document.forms["frmEditTexto2"].action="<%=request.getContextPath()%>/plantilla.vdc?event=change&tipo="+tipo+"&observaciones="+observaciones;
		$('#frmEditTexto2').submit(); 
	}else{
		observaciones=encodeURI(CKEDITOR.instances["txtInptSemanal"].getData());
		document.forms["frmEditTexto"]["tipo"].value=tipo;
		document.forms["frmEditTexto"].action="<%=request.getContextPath()%>/plantilla.vdc?event=change&tipo="+tipo+"&observaciones="+observaciones;
		$('#frmEditTexto').submit(); 
		
	}
	//alert(observaciones);	
/*
	AjaxUtils.loadIntoOnEdit("content",strControllerPlantillaLocation+"?event=change&tipo="+tipo+"&observaciones="+observaciones,null,function(dataBack) {
		var datos;
		var error=false;
		var datos=eval('(' + dataBack + ')');

		if(tipo=="diaria"){
			$("#plantilla").html(datos.model.plantilla);
		}else{
			$("#plantillainf").html(datos.model.plantilla);
		}
		// 		if((""+window.location).indexOf("plantilla")==-1){
// 			window.location=window.location+"#plantilla";			
// 		}
	});
	*/
}





(function() {
    
var bar = $('.bar');
var percent = $('.percent');
var status = $('#status');

$('#frmUpload').ajaxForm({
    beforeSend: function() {
        status.empty();
        var percentVal = '0%';
        bar.width(percentVal)
        percent.html(percentVal);
    },
    uploadProgress: function(event, position, total, percentComplete) {
        var percentVal = percentComplete + '%';
        bar.width(percentVal);
        percent.html(percentVal);
    },
    success: function() {
		//alert("success");
        var percentVal = '100%';
        bar.width(percentVal);
        percent.html(percentVal);
    },
	complete: function(xhr) {
		alert("completado");
		//$("#logResult").html(datos.model.resultado);
		
		//status.html(xhr.responseText);
	}
}); 
       
})();       


(function() {
    
	var bar = $('.bar');
	var percent = $('.percent');
	var status = $('#status');

	$('#frmEditTexto').ajaxForm({
	    beforeSend: function() {
	        status.empty();
	        var percentVal = '0%';
	        bar.width(percentVal)
	        percent.html(percentVal);
	    },
	    uploadProgress: function(event, position, total, percentComplete) {
	        var percentVal = percentComplete + '%';
	        bar.width(percentVal);
	        percent.html(percentVal);
	    },
	    success: function() {
			//alert("success");
	        var percentVal = '100%';
	        bar.width(percentVal);
	        percent.html(percentVal);
	    },
		complete: function(xhr) {
			alert("completado");
			//$("#logResult").html(datos.model.resultado);
			
			//status.html(xhr.responseText);
		}
	}); 
	       
	})();       

(function() {
    
	var bar = $('.bar');
	var percent = $('.percent');
	var status = $('#status');

	$('#frmEditTexto2').ajaxForm({
	    beforeSend: function() {
	        status.empty();
	        var percentVal = '0%';
	        bar.width(percentVal)
	        percent.html(percentVal);
	    },
	    uploadProgress: function(event, position, total, percentComplete) {
	        var percentVal = percentComplete + '%';
	        bar.width(percentVal);
	        percent.html(percentVal);
	    },
	    success: function() {
			//alert("success");
	        var percentVal = '100%';
	        bar.width(percentVal);
	        percent.html(percentVal);
	    },
		complete: function(xhr) {
			alert("completado");
			//$("#logResult").html(datos.model.resultado);
			
			//status.html(xhr.responseText);
		}
	}); 
	       
	})();       

</script>

<form id="frmPlantilla" name="frmPlantilla">

</form>

<div id="tabsplantilla">
	  <ul>
	    <li><a href="#tabs-plantillaSemanal">Plantilla Primaria</a></li>
	    <li><a href="#tabs-plantillaDiaria">Plantilla Infantil</a></li>
	    
	</ul>
	<div id="tabs-plantillaSemanal" style="left:10px;margin-left:50px;height:100%">
	<b>Plantilla Primaria</b>
			
		<div id="tabsSemanal">
		
		
		   <ul>
			    <li><a href="#tabs-plantillaimagenes">Seleccionar Imagen</a></li>
			    <li><a href="#tabs-edittexto">Editar Observaciones</a></li>
			    <li><a href="#tabs-uploadimage">Subir Imagenes</a></li>
			    <li><a href="#tabs-verPlantilla" onClick="javascript:watchPlantilla()">Ver Plantilla</a></li>
			</ul>			
			<div id="tabs-plantillaimagenes">
			<h1>Seleccione la imagen a aplicar a la plantilla de primaria:</h1>
		
				<table width="40%" align="left">
				<tr>
							<% 
							int change=0;
							
							for(int i=0;i<listaImagenes.size();i++){
								if(change<4){
									out.println("<td><a href='javascript:selectImage(\""+listaImagenes.get(i)+"\")'><img src='"+request.getContextPath()+"/img/jack/"+listaImagenes.get(i)+"' border='0' height='120px'/></a></td>");
								}else{
									change=0;
									out.println("<td><a href='javascript:selectImage(\""+listaImagenes.get(i)+"\")'><img src='"+request.getContextPath()+"/img/jack/"+listaImagenes.get(i)+"' border='0' height='120px'/></a></td></tr><tr>");
								}
								change++;	
							}
							
							%>
				</tr>
				</table>
			</div>
			
			<div id="tabs-verPlantilla">
			<h3>Ver Plantilla Primaria:</h3>
				<div id="plantilla">
						
				</div>
			</div>
						
			<br/>
			<div id="tabs-edittexto">
				<form id="frmEditTexto" name="frmEditTexto"  action="<%=request.getContextPath()%>/plantilla.vdc?event=change" method="POST" enctype="multipart/form-data">
				<input type="hidden" id="tipo" name="tipo"/>
				<div id="editor1">
					<table width="100%">
						<tr>
							<td colspan="2">Seleccione el texto que desea agregar al mensaje:</td>
						</tr>
						<tr>
							<td><textarea class="ckeditor" id="txtInptSemanal" name="txtInptSemanal" cols="60" rows="20"></textarea></td>
							<td><input type="button" value="Cambiar Texto" onClick="javascript:cambiarTexto('semanal')"/></td>
						</tr>
					
					</table>	
				</div>
				</form>
				
			</div>
			<div id="tabs-uploadimage">
					
				<form id="frmUpload" name="frmUpload" action="<%=request.getContextPath()%>/upimages.vdc?event=upload" method="POST" enctype="multipart/form-data">
					<label for="fileselect">Seleccionar archivo excel de Prueba:</label>
					<input type="file" id="upImage" name="upImage"/>
					<div id="submitbutton">
						<input type="submit" value="Subir Imagen"/>
					</div>
					<div class="progress">
						<div class="bar"></div >
						<div class="percent">0%</div >
					</div>
					<div id="status"></div>		
				</form>
			</div>
			<br/><br/>
			
			
	
	</div>
			
	
	</div>

	<div id="tabs-plantillaDiaria" style="left:10px;margin-left:50px;;height:100%">
	<b>Plantilla Infantil</b>
			<div id="tabsDiaria">
			   <ul>
				    <li><a href="#tabs-plantillaimagenesinf">Seleccionar Imagen</a></li>
				    <li><a href="#tabs-edittextoinf">Editar Observaciones</a></li>
				    <li><a href="#tabs-uploadimageinf">Subir Imagenes</a></li>
				    <li><a href="#tabs-verPlantillainf" onClick="javascript:watchPlantillaDiaria()">Ver Plantilla</a></li>
				</ul>			
			<div id="tabs-plantillaimagenesinf">
			<h1>Seleccione la imagen a aplicar a la plantilla de infantil:</h1>
		
				<table width="40%" align="left">
				<tr>
							<% 
							change=0;
							
							for(int i=0;i<listaImagenes.size();i++){
								if(change<4){
									out.println("<td><a href='javascript:selectImageInf(\""+listaImagenes.get(i)+"\")'><img src='"+request.getContextPath()+"/img/jack/"+listaImagenes.get(i)+"' border='0' height='120px'/></a></td>");
								}else{
									change=0;
									out.println("<td><a href='javascript:selectImageInf(\""+listaImagenes.get(i)+"\")'><img src='"+request.getContextPath()+"/img/jack/"+listaImagenes.get(i)+"' border='0' height='120px'/></a></td></tr><tr>");
								}
								change++;	
							}
							
							%>
				</tr>
				</table>
			</div>
			
			<div id="tabs-verPlantillainf">
			<h3>Ver Plantilla Infantil:</h3>
				<div id="plantillainf">
						
				</div>
			</div>
						
			<br/>
			<div id="tabs-edittextoinf">
				<form id="frmEditTexto2" name="frmEditTexto2"   action="<%=request.getContextPath()%>/plantilla.vdc?event=change" method="POST" enctype="multipart/form-data">
				<input type="hidden" id="tipo" name="tipo"/>
				<div id="editor2">
					<table width="100%">
						<tr>
							<td colspan="2">Seleccione el texto que desea agregar al mensaje:</td>
						</tr>
						<tr>
							<td><textarea class="ckeditor" id="txtInptSemanalinf" name="txtInptSemanalinf" cols="80" rows="20"></textarea></td>
							<td><input type="button" value="Cambiar Texto" onClick="javascript:cambiarTexto('diaria')"/></td>
						</tr>
					
					</table>	
				</div>
				</form>
				
			</div>
			<div id="tabs-uploadimageinf">
					
				<form id="frmUploadinf" name="frmUploadinf" action="<%=request.getContextPath()%>/upimages.vdc?event=upload" method="POST" enctype="multipart/form-data">
					<label for="fileselect">Seleccionar archivo excel de Prueba:</label>
					<input type="file" id="upImageinf" name="upImageinf"/>
					<div id="submitbutton">
						<input type="submit" value="Subir Imagen"/>
					</div>
					<div class="progress">
						<div class="bar"></div >
						<div class="percent">0%</div >
					</div>
					<div id="status"></div>		
				</form>
			</div>
			<br/><br/>
	</div>
			

</div>

</div>


