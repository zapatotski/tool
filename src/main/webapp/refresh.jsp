<%@ page import="main.java.live.Game" %>
<%@ page import="main.java.live.Player" %>
<%@ page import="main.java.injury.Parser" %>


<%! 
 
    public void refresh() throws MalformedURLException{
       Parser.refresh();
    } 
%>

<%
refresh();
%>