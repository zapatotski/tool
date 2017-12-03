<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.io.File" %>
<%@ page import="java.io.*" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.FileOutputStream" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.ObjectInputStream" %>
<%@ page import="java.io.ObjectOutputStream" %>
<%@ page import="java.io.Serializable" %>
<%@ page import="java.util.*" %>  

<%! 
File f=new File("logStart.data");

public List<String> deserialization(File f){
	FileInputStream fInput=null;
	ObjectInputStream oInput=null;
	List<String> q=null;
	try{
		fInput=new FileInputStream(f);
		oInput=new ObjectInputStream(fInput);
		q=(List<String>) oInput.readObject();
	}
	catch(Exception e){
		throw new RuntimeException(e);
	}
	return q;
}
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
List<String> ls=deserialization(f);
for(String k:ls)
	out.println(k+"<br>");

%>
</body>
</html>