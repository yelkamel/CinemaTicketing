package sessionBeans;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.ejb.Local;

import EntityBeans.Cinemas;
import EntityBeans.Films;
import EntityBeans.Seances;

@Local
public interface SeancesFacadeLocal
{
	void create(Seances seance);
	void update(Seances seance);
	void remove(Seances seance);

	Seances find(Object id);
	List<Seances> getSeances(Films film);
	List<Seances> getSeancesbyCinema(Cinemas cinema);
	List<Seances> getSeancesbyFilm(Films film);
	List<Seances> getSeancesbyDate(Date date);
	List<Seances> getSeancesbyTime(Time time);
	List<Seances> getSeancesbyMultiCriteres(Cinemas cinema, Films film, Date date, Time heure);
	List<Seances> getSeancesbyAllCriteres(Films film, Cinemas cinema, Date date, Time horaire);
	List<Seances> findAll(); 
}
