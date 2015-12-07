package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EntityBeans.Films;
import EntityBeans.Seances;

import sessionBeans.FilmsFacadeLocal;
import sessionBeans.SeancesFacadeLocal;

/**
 * Servlet implementation class Film
 */
public class List_Films extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private FilmsFacadeLocal filmFacade;
	
	@EJB
	private SeancesFacadeLocal seanceFacade;
	
	private List<Films>lfilms = new ArrayList<Films>();
	private List<Seances>lseances = new ArrayList<Seances>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_Films()
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
			// on récupère tous les films et toutes les séances associées
			lfilms = filmFacade.findAll();
			lseances = seanceFacade.findAll();
			request.setAttribute("ListFilms", lfilms);
			request.setAttribute("seances", lseances);
			this.getServletContext().getRequestDispatcher("/films.jsp").forward(request, response);
		}
		//utilisateur non connecté
		else
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}
}
