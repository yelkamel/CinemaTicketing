package sessionBeans;

import java.util.List;
import javax.ejb.Local;

import EntityBeans.Users;

/*
 * Facade Locale pour les users
 */

@Local
public interface UsersFacadeLocal
{
	void create(Users user);
	void update(Users user);
	void remove(Users user);

	Users find(Object id);
	List<Users> findbyLogin(String login);
	List<Users> findAll();
}