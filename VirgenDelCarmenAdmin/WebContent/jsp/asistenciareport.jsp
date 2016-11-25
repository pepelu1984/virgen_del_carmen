<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dynamicdroides.db.virgendelcarmen.*" %>
<%
request.setCharacterEncoding("UTF-8");
Map model=(Map)request.getAttribute("model");
List<Cursos> cursos=(List)model.get("cursos");
Integer idcurso=(Integer)model.get("idcurso");
//System.out.println("Buscando..."+selFiltro);
String events=""+model.get("events");
List<Usuarios> listUsers=(List)model.get("usuarios");
Integer iduser=(Integer)model.get("iduser");
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
	$( "#tabsMenu" ).tabs();
	
});
</script>

<script>

function createcalendar(){
	var options = {
			   theme: true,
			   header: {
			    left: 'prev,next today',
			    center: 'title',
			    right: 'month,agendaDay'
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

    $('#calendarAsistReport').fullCalendar(options);
	
}

createcalendar();

</script>


<br/>
<br/>
<form id="frmAsistencia" name="frmAsistencia">

<div id="tabsCalendar">
		<ul>
		    <li><a href="#tabs-menucalendarAsistReport">Asistencia</a></li>
		  </ul>
		
	
	
	<div id="tabs-menucalendarAsistReport">
		<label for="slCursosCalendar">Filtrar por curso:</label>
		<select id="slCursosCalendar" name="slCursosCalendar" style="width:300px" onChange="javascript:calendarByCurso()">
			<option>...</option>		
			<% 
			for(int i=0;i<cursos.size();i++){
				if(idcurso!=null && idcurso==cursos.get(i).getIdcurso()){
			%>
				<option selected value="<%=cursos.get(i).getIdcurso()%>"><%=cursos.get(i).getNombre()%> </option>		
			<% 				
				}else{
			%>
				<option value="<%=cursos.get(i).getIdcurso()%>"><%=cursos.get(i).getNombre()%> </option>		
			<%
				}
			}
			%> 
		</select>
	
	<label for="slUsersCalendar">Filtrar por educador:</label>
		<select id="slUsersCalendar" name="slUsersCalendar" style="width:300px" onChange="javascript:calendarByUser()">
			<option>...</option>		
			<% 
			for(int i=0;i<listUsers.size();i++){
				if(iduser!=null && iduser.equals(listUsers.get(i).getIduser())){
			%>
				<option selected value="<%=listUsers.get(i).getIduser()%>"><%=(listUsers.get(i).getNombre()+" "+listUsers.get(i).getApellidos())%> </option>		
			<% 				
				}else{
			%>
				<option value="<%=listUsers.get(i).getIduser()%>"><%=(listUsers.get(i).getNombre()+" "+listUsers.get(i).getApellidos())%> </option>		
			<%
				}
			}
			%> 
		</select>
	
	
	
	
		<div id="calendarAsistReport" style="position:relative;top:25px;left:0px;width:85%;margin-left:10px;margin:10px;">
		
		
		</div>
	

</div>


</div>

</form>
