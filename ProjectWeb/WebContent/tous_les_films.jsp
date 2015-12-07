<%@ page import="java.util.Date"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.util.List"%>
<%@ page import="EntityBeans.*" %>
<%@ page import="com.servlet.*" %>

<%!
	List<Films> lfilms;
%>			

	<ul>
  		<li class="films" style="background-color: #CE070A;"><a href="List_Films?view=all"><span style="color: white;">Tous les Films</span></a></li>
   		<li class="films"><a href="List_Films?view=now">Cette semaine</a></li>
   		<li class="films"><a href="List_Films?view=next">Semaines suivantes</a></li>
   	</ul>

	<%
		lfilms =(List<Films>) request.getAttribute("ListFilms");
	%>
	<div id="contenu">
		<table style="font-family:tahoma; border:0px; text-align: center;"><tr>
	<%
		int i = 1;
		//affichage de tous les films
		for (Films f : lfilms)
		{
			Inscription inscrip = new Inscription(); 
			String str = inscrip.sansAccent(f.getTitle());
				
			if ((i % 4) == 0)
			{
	 %>
				<td class="titre" style="border:0px; padding-left: 30px; padding-bottom: 30px;"><a href="Film?id_film=<%= f.getId() %>"><img width=180px; height=250px; src="<%=f.getPicture()%>"/></br><%= str.toUpperCase() %></a></td>
				</tr><tr>
	<%
			}
			else
			{
	%>
				<td class="titre" style="border:0px; padding-left: 30px; padding-bottom: 30px;"><a href="Film?id_film=<%= f.getId() %>"><img width=180px; height=250px; src="<%=f.getPicture()%>"/></br><%= str.toUpperCase() %></a></td>
	<%
			}
			i++;
		}
%>					
		</tr></table>
	</div>