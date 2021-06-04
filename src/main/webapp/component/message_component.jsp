<%-- 
    Document   : message_component
    Created on : 16-Dec-2020, 5:02:07 pm
    Author     : Lovepreet Singh & Purva Saxena
--%>
<%
    String message = (String)session.getAttribute("message");
    if(message!=null){
    
%>
<div class="alert alert-success alert-dismissible fade show" role="alert">
    <strong><%=message %></strong> 
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>
<%
    session.removeAttribute("message");
    }
    
%>

