package com.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EntityBeans.Cinemas;
import EntityBeans.Films;
import EntityBeans.Seances;

import sessionBeans.CinemasFacadeLocal;
import sessionBeans.FilmsFacadeLocal;
import sessionBeans.SeancesFacadeLocal;

/**
 * Servlet implementation class Film
 */
public class Film extends HttpServlet
{
	private static final long serialVersionUID = 1L;
    
	@EJB
	private FilmsFacadeLocal filmFacade;
	
	@EJB
	private SeancesFacadeLocal seanceFacade;
	
	@EJB
	private CinemasFacadeLocal cinemaFacade;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Film()
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String login = (String)request.getSession().getAttribute("login");
		
		//utilisateur connecté
		if (login != null)
		{
			String id = request.getParameter("id_film");
			String date = request.getParameter("date");
			Films film = filmFacade.find(Integer.parseInt(id));
			
			List<Films>lfilms = filmFacade.findAll();
			List<Seances>lseances = seanceFacade.getSeances(film);
			List<Cinemas>cinemas = cinemaFacade.findAll();
			List<Cinemas>lcinemas = new ArrayList<Cinemas>();
			List<String>ldates = new ArrayList<String>();
			
			//formatage de la date du jour au format français
			Calendar cal = Calendar.getInstance();
			Date today = new Date();
			cal.setTime(today);		
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String date_jour = df.format(cal.getTime());
						
			for (Seances s : lseances)
			{
				//formatage de la date de la séance au format français
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(s.getDate());
				DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
				String date_seance = df2.format(cal2.getTime());
				
				//sauvegarde de la date de séance si elle n'est pas présente dans la liste et qu'elle est égale ou après la date du jour
				if (!ldates.contains(date_seance) && (date_seance.equals(date_jour) || cal2.getTime().after(cal.getTime())))
					ldates.add(date_seance);
			}
			
			for (Seances s : lseances)
			{
				//formatage de la date de la séance au format français
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(s.getDate());			
				DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
				String date_seance = df2.format(cal2.getTime());
				
				for (Cinemas c : cinemas)
				{
					//sauvegarde du cinéma de diffusion
					if (date_seance.equals(date_jour))
					{
						if (!(lcinemas.contains(c)) && s.getCinema().getId() == c.getId())
							lcinemas.add(c);
					}
				}
			}
			
			request.setAttribute("ListFilms", lfilms);
			request.setAttribute("ListSeances", lseances);
			request.setAttribute("ListCinemas", lcinemas);
			request.setAttribute("ListDates", ldates);
			request.setAttribute("film", film);
			request.setAttribute("id_film", id);
			request.setAttribute("date", date);
			
			this.getServletContext().getRequestDispatcher("/films.jsp").forward(request, response);
		}
		else
		{
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("utf-8");
		String login = (String)request.getSession().getAttribute("login");
		
		//utilisateur connecté
		if (login != null)
		{
			String id_seance = request.getParameter("seance");
			String id = request.getParameter("id_film");
			String date = request.getParameter("date");
			String horaire = request.getParameter("horaire");
			String cinema = request.getParameter("cinema");
			String quantite = request.getParameter("quantite");
			Films film = filmFacade.find(Integer.parseInt(id));
			Seances seance = new Seances();
			
			if (id_seance != null)
				seance = seanceFacade.find(Integer.parseInt(id_seance));
			
			List<Films>lfilms = filmFacade.findAll();
			List<Seances>lseances = seanceFacade.getSeances(film);
			List<Cinemas>cinemas = cinemaFacade.findAll();
			List<Cinemas>lcinemas = new ArrayList<Cinemas>();
			List<String>ldates = new ArrayList<String>();
			List<Cinemas> cines = cinemaFacade.findbyName(cinema);
			
			//formatage de la date du jour au format français
			Calendar cal = Calendar.getInstance();
			Date today = new Date();
			cal.setTime(today);			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String date_jour = df.format(cal.getTime());
				
			for (Seances s : lseances)
			{
				//formatage de la date de la séance au format français
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(s.getDate());			
				DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
				String date_seance = df2.format(cal2.getTime());
				
				//sauvegarde de la date de séance si elle n'est pas présente dans la liste et qu'elle est égale ou après la date du jour
				if (!ldates.contains(date_seance) && (date_seance.equals(date_jour) || cal2.getTime().after(cal.getTime())))
					ldates.add(date_seance);
			}
			
			for (Seances s : lseances)
			{
				//formatage de la date de la séance au format français
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(s.getDate());			
				DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
				String date_seance = df2.format(cal2.getTime());
				
				for (Cinemas c : cinemas)
				{
					//sauvegarde du cinéma de diffusion
					if (date_seance.equals(date))
					{
						if (!(lcinemas.contains(c)) && s.getCinema().getId() == c.getId())
							lcinemas.add(c);
					}
				}
			}
			
			request.setAttribute("ListFilms", lfilms);
			request.setAttribute("ListSeances", lseances);
			request.setAttribute("ListCinemas", lcinemas);
			request.setAttribute("ListDates", ldates);
			request.setAttribute("film", film);
			request.setAttribute("id_film", id);
			request.setAttribute("date", date);
			request.setAttribute("cinema", cinema);
			request.setAttribute("seance", seance);
			request.setAttribute("horaire", horaire);
			request.setAttribute("quantite", quantite);
			
			this.getServletContext().getRequestDispatcher("/films.jsp").forward(request, response);
		}
		else
		{
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}