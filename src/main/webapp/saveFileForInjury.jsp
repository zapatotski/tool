<%@ page import="main.java.live.Game" %>
<%@ page import="main.java.live.Player" %>
<%@ page import="main.java.injury.Parser" %>


<%!  
    public void refresh(){
       Parser.saveFileForInjury();
    } 
%>

<%
refresh();
%>