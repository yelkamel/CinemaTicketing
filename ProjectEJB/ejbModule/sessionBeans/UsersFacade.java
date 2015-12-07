package sessionBeans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import EntityBeans.Seances;
import EntityBeans.Users;

@Stateless
public class UsersFacade implements UsersFacadeLocal
{
	@PersistenceContext(unitName="Cinema-ejbPU")
	private EntityManager entityMgr;
	
	public void create(Users user)
	{
		entityMgr.persist(user);
	}

	public void update(Users user)
	{
		entityMgr.merge(user);
	}
	
	public void remove(Users user)
	{
		entityMgr.remove(entityMgr.merge(user));
	}
	
	public Users find(Object o)
	{
		return entityMgr.find(Users.class, o);
	}
	
	public List<Users> findbyLogin(String login)
	{
		String texteRequete = "SELECT users FROM Users AS users WHERE users.login = :login";
		Query requete = entityMgr.createQuery(texteRequete);
		requete = requete.setParameter("login", login);
		return (List<Users>)requete.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Users> findAll()
	{
		return (List<Users>)entityMgr.createQuery("select object(o) from Users as o").getResultList();
	}
}