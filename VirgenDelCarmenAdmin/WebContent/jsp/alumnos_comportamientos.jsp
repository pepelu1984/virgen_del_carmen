<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/css/fullcalendar.css' />
<script type='text/javascript' src='<%=request.getContextPath()%>/js/fullcalendar.js'></script>
<% 



Map model=(Map)request.getAttribute("model");
String idalumno=""+model.get("idalumno");
String namealumno=""+model.get("namealumno");
String events=""+model.get("events");




%>


<script>

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

			        alert('Event: ' + calEvent.title);
			        alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
			        alert('View: ' + view.name);

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
<h1>Alumno: </h1>

<div id="calendar" style="position:relative;top:25px;left:0px;width:85%;margin-left:10px;margin:10px;">


</div>
calendario