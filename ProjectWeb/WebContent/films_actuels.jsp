<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.util.List"%>
<%@ page import="EntityBeans.*" %>
<%@ page import="com.servlet.*" %>

	<%!
		List<Films> listfilms;
		List<Seances> listseances;
	%>			

	<ul>
    	<li class="films"><a href="List_Films?view=all">Tous les Films</a></li>
        <li class="films" style="background-color: #CE070A;"><a href="List_Films?view=now"><span style="color: white;">Cette semaine</span></a></li>
        <li class="films"><a href="List_Films?view=next">Semaines suivantes</a></li>
	</ul>
            	
	<%
		listfilms = (List<Films>) request.getAttribute("ListFilms");
		List<Films> films = new ArrayList<Films>();
		listseances = (List<Seances>) request.getAttribute("seances");
	%>
	
	<div id="contenu">
		<table style="font-family:tahoma; border:0px; text-align: center;"><tr>
	<!-- #E3E9FF -->
	<%
		for (Seances s : listseances)
		{
			for (Films f : listfilms)
			{
				//formatage de la date de sortie au format français
				Calendar cal = Calendar.getInstance();
				Calendar monday = (Calendar)cal.clone();
				Calendar sunday = (Calendar)cal.clone();
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				
				cal.setTime(f.getDate());			
				String date_sortie = df.format(cal.getTime());
				
				//formatage de la date du jour au format français
				Calendar cal2 = Calendar.getInstance();
				Date today = new Date();
				cal2.setTime(today);			
				String date_jour = df.format(cal2.getTime());
				
				//on fixe le 1er et dernier jour de la semaine
				monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				sunday.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				String lundi = df.format(monday.getTime());
				String dimanche = df.format(sunday.getTime());
				
				//formatage de la date de séance au format français
				Calendar cal3 = Calendar.getInstance();
				cal3.setTime(s.getDate());			
				String seance = df.format(cal3.getTime());
				
				String date = new String();
				if (f.getDate().before(today))
					date = date_jour;
				else
					date = date_sortie;
				
				//formatage de l'heure format français
				DateFormat hf = new SimpleDateFormat("HH:mm");
				String duree = hf.format(f.getDuration());
				
				StringBuffer sb = new StringBuffer(duree);
				sb.setCharAt(2, 'h');
				duree = sb.toString();
				
				if (duree.charAt(0) == '0')
					duree = duree.substring(1, 5);
	
				//sauvegarde du film si il est diffusé dans la semaine courante
				if (f.getId() == s.getFilm().getId() && (seance.equals(date_jour) || seance.equals(dimanche) || (cal3.getTime().after(today) && cal3.getTime().before(sunday.getTime()))))
				{
					if (!films.contains(f))
							films.add(f);
				}
			}
		}
			
	
		//affichage des films de la semaine
		int i = 1;
		for (Films f : films)
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