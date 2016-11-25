<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
request.setCharacterEncoding("UTF-8");
Map model=(Map)request.getAttribute("model");
String idcurso=""+model.get("idcurso");
//System.out.println("Buscando..."+selFiltro);
String events=""+model.get("events");

%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.transit.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/css/styles_upload.css" />
<link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/css/fullcalendar.css' />
<script type='text/javascript' src='<%=request.getContextPath()%>/js/fullcalendar.js'></script>

<script>


(function() {
    
var bar = $('.bar');
var percent = $('.percent');
var status = $('#status');

$('#frmUploadMenu').ajaxForm({
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
		alert("complete");
				
		//status.html(xhr.responseText);
	}
}); 
       
})();       



$(document).ready(function(){
	var idcurso="<%=idcurso%>";
	$( "#tabsMenu" ).tabs();
	
});
</script>

<script>
function editMenu(idmenu){
	

	AjaxUtils.loadIntoOnEdit("content",strControllerMenuLocation+"?event=edit&idmenu="+idmenu,null,function(dataBack) {
		$("#results").html(dataBack);
	});
}
function createCalendar(){
	var options = {
			   theme: true,
			   header: {
			    left: 'prev,next today',
			    center: 'title',
			    right: 'month'
		//		    right: 'month,agendaWeek,agendaDay'
			   },
			   timeFormat: {
			    agenda: 'h(:mm)t{ - h(:mm)t}',
			    '': 'h(:mm)t{-h(:mm)t }'
			   },
			   events: [
			            <%=events%>
			            // other events here
			        ],
			        eventClick: function(event) {
			            if (event.url) {
			                //window.open(event.url);
			                return false;
			            }
		        },
			   eventClick: function(calEvent, jsEvent, view) {
			        //alert('Event '+calEvent.id+': ' + calEvent.title);
				    //editMenu(calEvent.id);
			        //alert('Event: ' + calEvent.title);
			        //alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
			        //alert('View: ' + view.name);

			        // change the border color just for fun
			        $(this).css('border-color', 'red');

			    },
			   monthNames: ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" ], 
			   monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic'],
			   dayNames: [ 'Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
			   dayNamesShort: ['Dom','Lun','Mar','Mié','Jue','Vie','Sáb'],
			   buttonText: {
			    today: 'hoy',
			    month: 'mes',
			    week: 'semana',
			    day: 'día'
			   }
			  };

    $('#calendar').fullCalendar(options);
	
}


createCalendar();

</script>


<br/>
<br/>
<div id="tabsMenu">
	<ul>
	    <li><a href="#tabs-menucalendar">Calendario</a></li>
	    <li><a href="#tabs-uploadmenu" onClick="javascript:uploadMenu()">Subir Men&uacute; desde archvo WORD</a></li>
	
	  </ul>
	


<div id="tabs-menucalendar">

	<div id="calendar" style="position:relative;top:25px;left:0px;width:85%;margin-left:10px;margin:10px;">
	
	
	</div>


</div>

<div id="tabs-uploadmenu">
		<br/>
		<h1>Subir Menu a traves de Plantilla WORD:</h1>
		<form id="frmUploadMenuMenu" name="frmUploadMenu" action="<%=request.getContextPath()%>/menu.vdc?event=upload" method="POST" enctype="multipart/form-data">
			<input type="hidden" id="idcurso" name="idcurso" value="<%=idcurso%>"/>
			<label for="fileselect">Seleccionar WORD de menú mensual:</label>
			<input type="file" id="upWord" name="upWord"/>
			<div id="submitbutton">
				<input type="submit" value="Subir Menu Mensual"/>
			</div>
			<div class="progress">
				<div class="bar"></div >
				<div class="percent">0%</div >
			</div>
			<div id="status"></div>		
		</form>

</div>

</div>

		