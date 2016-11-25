<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>

<% 
String path=getServletContext().getRealPath("")+"/WEB-INF/logs/application.log";
File catalinaOut = new File(path);
if(catalinaOut.exists())
{
 
	
	BufferedReader br = new BufferedReader(new FileReader(catalinaOut));
    try {
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append('\n');
            line = br.readLine();
            out.println(line+"<br/>");
        }
        String everything = sb.toString();
    } finally {
        br.close();
    }
}else{
	out.print("Fichero no encontrado"+path);
}


%>