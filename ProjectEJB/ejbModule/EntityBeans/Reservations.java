package EntityBeans;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * Bean de la table RESERVATIONS
 */

@Entity
@Table(name = "CINEMA.RESERVATIONS")
public class Reservations implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@JoinColumn(name = "ID_FILM", referencedColumnName = "ID")
	@ManyToOne
	private Films film;
	
	@JoinColumn(name = "ID_SEANCE", referencedColumnName = "ID")
	@ManyToOne
	private Seances seance;
	
	@JoinColumn(name = "ID_CINEMA", referencedColumnName = "ID")
	@ManyToOne
	private Cinemas cinema;
	
	@Column(name = "DATE")
	private String date;
	
	@Column(name = "HORAIRE")
	private String horaire;
	
	@Column(name = "QUANTITE")
	private int quantite;
	
	@Column(name = "PRIX")
	private double price;
	
	@Column(name = "RECORD")
	private String record;

	@JoinColumn(name = "ID_CLIENT", referencedColumnName = "ID")
	@ManyToOne
	private Users client;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Films getFilm()
	{
		return film;
	}

	public void setFilm(Films film)
	{
		this.film = film;
	}

	public Seances getSeance()
	{
		return seance;
	}

	public void setSeance(Seances seance)
	{
		this.seance = seance;
	}

	public Cinemas getCinema()
	{
		return cinema;
	}

	public void setCinema(Cinemas cinema)
	{
		this.cinema = cinema;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getHoraire()
	{
		return horaire;
	}

	public void setHoraire(String horaire)
	{
		this.horaire = horaire;
	}

	public int getQuantite()
	{
		return quantite;
	}

	public void setQuantite(int quantite)
	{
		this.quantite = quantite;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}
	
	public String getRecord()
	{
		return record;
	}

	public void setRecord(String record)
	{
		this.record = record;
	}

	public Users getClient()
	{
		return client;
	}

	public void setClient(Users client)
	{
		this.client = client;
	}
}