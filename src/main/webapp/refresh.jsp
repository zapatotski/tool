<%@ page import="main.java.live.Game" %>
<%@ page import="main.java.live.Player" %>
<%@ page import="main.java.injury.Parser" %>


<%!  
    public void refresh(String url){
       Parser.refresh(url);
    } 
%>

<%
refresh(request.getServerName());
%>