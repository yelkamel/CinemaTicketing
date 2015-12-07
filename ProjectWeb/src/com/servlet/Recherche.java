package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EntityBeans.Films;
import EntityBeans.Users;

import sessionBeans.FilmsFacadeLocal;
import sessionBeans.UsersFacadeLocal;

/**
 * Servlet implementation class Recherche
 */
public class Recherche extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private FilmsFacadeLocal filmFacade;
	
	@EJB
	private UsersFacadeLocal userFacade;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Recherche()
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
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
			//on récupère la liste des films suivant le motif recherche
			List<Films> lfilms = filmFacade.getFilms(request.getParameter("recherche"));
			
			request.setAttribute("recherche", request.getParameter("recherche"));
			request.setAttribute("films", lfilms);
			
			List<Users> users = userFacade.findbyLogin(login);
			Users user = users.get(0);
			
			if (user.getIsAdmin().equals("no"))
				this.getServletContext().getRequestDispatcher("/recherche.jsp").forward(request, response);
			else
				this.getServletContext().getRequestDispatcher("/recherche_admin.jsp").forward(request, response);
		}
		else
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}
}