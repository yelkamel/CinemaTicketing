<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.util.List"%>
<%@ page import="EntityBeans.*" %>
<%@ page import="com.servlet.*" %>

	<%!
		List<Films> list_films;
		List<Seances> list_seances;
	%>			

	<ul>
    	<li class="films"><a href="List_Films?view=all">Tous les Films</a></li>
        <li class="films"><a href="List_Films?view=now">Cette semaine</a></li>
        <li class="films" style="background-color: #CE070A;"><a href="List_Films?view=next"><span style="color: white;">Semaines suivantes</span></a></li>
    </ul>
            	
	<%
		list_films = (List<Films>) request.getAttribute("ListFilms");
		list_seances = (List<Seances>) request.getAttribute("seances");
		List<Films> list = new ArrayList<Films>();
	%>
	
	<div id="contenu">
		<table style="font-family:tahoma; border:0px; text-align: center;"><tr>
		<!-- #E3E9FF -->
	<%
		for (Seances s : list_seances)
		{
			for (Films f : list_films)
			{				
				//formatage de la date de sortie au format français
				Calendar cal = Calendar.getInstance();
				Calendar sunday = (Calendar)cal.clone();
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				
				cal.setTime(f.getDate());			
				String date_sortie = df.format(cal.getTime());
				
				//formatage de la date du jour au format français
				Calendar cal2 = Calendar.getInstance();
				Date today = new Date();
				cal2.setTime(today);			
				String date_jour = df.format(cal2.getTime());
				
				//on fixe les 1er et dernier jours de la semaine
				sunday.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
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
				
				//formatage de l'heure au format français		
				DateFormat hf = new SimpleDateFormat("HH:mm");
				String duree = hf.format(f.getDuration());
				
				StringBuffer sb = new StringBuffer(duree);
				sb.setCharAt(2, 'h');
				duree = sb.toString();
				
				if (duree.charAt(0) == '0')
					duree = duree.substring(1, 5);

				//sauvegarde du film si il est dans les semaines suivantes
				if (f.getId() == s.getFilm().getId() && cal3.getTime().after(sunday.getTime()))
				{
					if (!list.contains(f))
						list.add(f);
				}
			}
		}
		
	
		//affichage des films
		int i = 1;
		for (Films f : list)
		{
			Inscription inscrip = new Inscription();
			String str = inscrip.sansAccent(f.getTitle());
			if ((i % 4) == 0)
			{
	 	%>		<td class="titre" style="border:0px; padding-left: 30px; padding-bottom: 30px;"><a href="Film?id_film=<%= f.getId() %>"><img width=180px; height=250px; src="<%=f.getPicture()%>"/></br><%= str.toUpperCase() %></a></td>
				</tr><tr>
		<%	}
			else
			{
		%>		<td class="titre" style="border:0px; padding-left: 30px; padding-bottom: 30px;"><a href="Film?id_film=<%= f.getId() %>"><img width=180px; height=250px; src="<%=f.getPicture()%>"/></br><%= str.toUpperCase() %></a></td>
		<%
			}
			i++;			
		}
		%>					
		</tr></table>
	</div>            	