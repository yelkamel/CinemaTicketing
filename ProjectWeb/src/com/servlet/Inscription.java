package com.servlet;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import sessionBeans.*;
import EntityBeans.*;
import Mail.SendMail;

/**
 * Servlet implementation class Inscription
 */
public class Inscription extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UsersFacadeLocal userFacade;
    /**
     * Default constructor. 
     */
    public Inscription()
    {
        // TODO Auto-generated constructor stub
    }
   
    //suppresion des accents
    public String sansAccent(String s)
    {
          String strTemp = Normalizer.normalize(s, Normalizer.Form.NFD);
          Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
          return pattern.matcher(strTemp).replaceAll("");
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
		//récupération des paramètres du formulaire d'inscription
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String adresse = request.getParameter("adresse");
		String password = new String();
		//String id = new String();
		String message = new String();
		
		//génération du login
		String login = sansAccent((prenom.substring(0, 1).concat(nom)).toLowerCase());
		
		//génération du mot de passge
		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_-@#&'(!?)$%?:;/.?,";
        Random rand = new Random();
        for (int i=0; i<10; i++)
        {
        	password = password.concat(Character.toString(alphabet.charAt(rand.nextInt(alphabet.length()))));
        }
        
        /*
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        rand = new Random();
        for (int i=0; i<5; i++)
        {
        	id = id.concat(Character.toString(alphabet.charAt(rand.nextInt(alphabet.length()))));
        }
		*/
        //List<Users>lusers = userFacade.findAll();
        Users user = new Users();
        Users usr = new Users();
        
        
        List<Users>users = new ArrayList<Users>();
       	String client = login;
        int i = 0;
        //login existant déjà
        while ((users = userFacade.findbyLogin(client)).size() > 0)
        {
        	i++;
        	client = login + String.valueOf(i);
		}
		
		//login inexistant
		if (i == 0)
			client = login; 

		//création de l'utilisateur
		usr.setLogin(client);
		//usr.setId(id);
		usr.setName(nom);
		usr.setFirstname(prenom);
		usr.setEmail(email);
		usr.setPassword(password);
		usr.setAdress(adresse);
		usr.setIsAdmin("no");
		userFacade.create(usr);
		
		//envoie de l'email de validation d'inscription
		String corps = new String();
		corps = "Bonjour " + prenom + " " + nom + "," + System.getProperty("line.separator") + System.getProperty("line.separator");
		corps += "Votre inscription à Cinema J2EE a bien été prise en compte. Voici vos identifiants pour vous connecter:" + System.getProperty("line.separator");
		corps += "Login: " + client + System.getProperty("line.separator");
		corps += "Mot de passe: " + password + System.getProperty("line.separator") + System.getProperty("line.separator");
		corps += "A bientôt!" + System.getProperty("line.separator") + System.getProperty("line.separator");
		corps += "--" + System.getProperty("line.separator");
		corps += "Ce message a été généré automatiquement. Il est inutile d'y répondre";
			
		SendMail mail = new SendMail();
		try
		{
			mail.sendMail("no-reply@gmail.com", usr.getEmail(), " Cinema J2EE - Inscription", corps);
		}
		catch (AddressException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (MessagingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		message="Vos identifiants vous ont été envoyés sur votre boite email." + System.getProperty("line.separator") + " Veuillez consultez vos mails";
		request.setAttribute("message", message);
		this.getServletContext().getRequestDispatcher("/accueil.jsp").forward(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	}
}