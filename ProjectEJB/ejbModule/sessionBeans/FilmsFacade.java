package sessionBeans;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import EntityBeans.Cinemas;
import EntityBeans.Films;
import EntityBeans.Seances;

/**
 * Session Bean implementation class FilmsFacade
 */
@Stateless
public class FilmsFacade implements FilmsFacadeLocal
{
	@PersistenceContext(unitName="Cinema-ejbPU")
	private EntityManager entityMgr;
	
	public void create(Films film)
	{
		entityMgr.persist(film);
	}

	public void update(Films film)
	{
		entityMgr.merge(film);
	}
	
	public void remove(Films film)
	{
		entityMgr.remove(entityMgr.merge(film));
	}
	
	public Films find(Object o)
	{
		return entityMgr.find(Films.class, o);
	}

	/*
	 * On récupère la liste des films qui contiennent le motif passé en paramètre
	 */	
	@Override
	public List<Films> getFilms(String recherche)
	{
		String texteRequete = "SELECT films FROM Films AS films WHERE UPPER(films.title) LIKE :recherche";
		Query requete = entityMgr.createQuery(texteRequete);
		String motif = "%"+recherche+"%";
		requete = requete.setParameter("recherche", motif.toUpperCase());
		return (List<Films>)requete.getResultList();
	}

	public List<Films> findbyType(String type)
	{
		String texteRequete = "SELECT films FROM Films AS films WHERE films.type = :type";
		Query requete = entityMgr.createQuery(texteRequete);
		requete = requete.setParameter("type", type);
		return (List<Films>)requete.getResultList();
	}
	
	public List<Films>getFilmsbyMultiCriteres(String genre, Date sortie, Time duree)
	{
		String texteRequete = "SELECT films FROM Films AS films";
		Query requete = null;
		
		if (genre == null && sortie == null && duree == null)
		{
			requete = entityMgr.createQuery(texteRequete);
		}
		
		if (genre != null && sortie != null && duree != null)
		{
			texteRequete += " WHERE films.type = :genre AND films.date = :sortie AND films.duration = :duree";
			requete = entityMgr.createQuery(texteRequete);
			requete = requete.setParameter("genre", genre);
			requete = requete.setParameter("sortie", sortie);
			requete = requete.setParameter("duree", duree);
		}
		
		if (genre != null && sortie == null && duree == null)
		{
			texteRequete += " WHERE films.type = :genre";
			requete = entityMgr.createQuery(texteRequete);
			requete = requete.setParameter("genre", genre);
		}
		
		if (genre != null && sortie != null && duree == null)
		{
			texteRequete += " WHERE films.type = :genre AND films.date = :sortie";
			requete = entityMgr.createQuery(texteRequete);
			requete = requete.setParameter("genre", genre);
			requete = requete.setParameter("sortie", sortie);
		}
		
		if (genre != null && sortie == null && duree != null)
		{
			texteRequete += " WHERE films.type = :genre AND films.duration = :duree";
			requete = entityMgr.createQuery(texteRequete);
			requete = requete.setParameter("genre", genre);
			requete = requete.setParameter("duree", duree);
		}
		
		if (sortie != null && genre == null && duree == null)
		{
			texteRequete += " WHERE films.date = :sortie";
			requete = entityMgr.createQuery(texteRequete);
			requete = requete.setParameter("sortie", sortie);
		}
				
		if (sortie != null && genre == null && duree != null)
		{
			texteRequete += " WHERE films.date = :sortie AND films.duration = :duree";
			requete = entityMgr.createQuery(texteRequete);
			requete = requete.setParameter("sortie", sortie);
			requete = requete.setParameter("duree", duree);
		}
		
		if (duree != null && genre == null && sortie == null)
		{
			texteRequete += " WHERE films.duration = :duree";
			requete = entityMgr.createQuery(texteRequete);
			requete = requete.setParameter("duree", duree);
		}
		
		return (List<Films>)requete.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Films> findAll()
	{
		return (List<Films>)entityMgr.createQuery("SELECT object(o) FROM Films as o").getResultList();
	}
}
