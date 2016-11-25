<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dynamicdroides.db.virgendelcarmen.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%
Map model=(Map)request.getAttribute("model");
String ideducador=""+model.get("ideducador");
String nameeducador=""+model.get("nameeducador");

List<Usuarios> educadores=(List)model.get("educadores");

//System.out.println("Buscando..."+selFiltro);
List<Cursos> cursosEducadores=(List)model.get("cursos");
//Map model=(Map)request.getAttribute("model");

//List<Cursos> cursosEducadores=(List)model.get("cursos");
//onload="javascript:searchCursos()"

%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">



	<form	id="PromocionesForm" name="PromocionesForm">
	<input type="hidden" id="ideducador" name="ideducador" value="<%=ideducador%>">
		<h6> </h6><br>
		<table width="90%" style="background-color:ffffff; margin-left:80px">
		 <tr>
				 <td>Curso a promocionar<br>
				 		<select width="300" style="width: 300px" id="slCursos" name="slCursos" onchange="javascript:searchCursos();searchAlumnosByCurso3()">							
							<option value="Seleccione un registro">Seleccione el curso que desea promocionar:</option>								
							<%
							for(int i=0;i<cursosEducadores.size();i++){
							%>
							<option value="<%=cursosEducadores.get(i).getIdcurso()%>"><%=cursosEducadores.get(i).getNombre()%></option>		
							<%
							}
							%>
						</select>
				</td>				
				 <td>
				 		<span id="divCmbCursos" >			
						</span>	
				</td>			
		</tr>
		<tr>
				<td>
						<span id="divCmbAlumnos" >		
						</span>	
				</td>				
				 <td>
				 		<span id="divCmbAlumnos2" >		
						</span>			<br></br><br></br>				
				 </td>
		</tr>
		
		</table>			
	</form>

