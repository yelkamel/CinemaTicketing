package sessionBeans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import EntityBeans.Cinemas;
import EntityBeans.Films;
import EntityBeans.Users;

/**
 * Session Bean implementation class CinemaFacade
 */
@Stateless
public class CinemasFacade implements CinemasFacadeLocal
{
	@PersistenceContext(unitName="Cinema-ejbPU")
	private EntityManager entityMgr;
	
	@Override
	public void create(Cinemas cinema)
	{
		entityMgr.persist(cinema);
		
	}

	@Override
	public void update(Cinemas cinema)
	{
		entityMgr.merge(cinema);
	}

	@Override
	public void remove(Cinemas cinema)
	{
		entityMgr.remove(entityMgr.merge(cinema));
		
	}

	@Override
	public Cinemas find(Object o)
	{
		return entityMgr.find(Cinemas.class, o);
	}

	public List<Cinemas> findbyName(String name)
	{
		String texteRequete = "SELECT cinemas FROM Cinemas AS cinemas WHERE cinemas.name = :name";
		Query requete = entityMgr.createQuery(texteRequete);
		requete = requete.setParameter("name", name);
		return (List<Cinemas>)requete.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cinemas> findAll()
	{
		return (List<Cinemas>)entityMgr.createQuery("SELECT object(o) FROM Cinemas as o").getResultList();
	}

}
