package EntityBeans;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Bean de la table FILMS
 */

@Entity
@Table(name = "CINEMA.FILMS")
public class Films implements Serializable
{
	private static final long serialVersionUID = 1090667079676059273L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "DIRECTOR")
	private String director;
	
	@Column(name = "ACTORS")
	private String actors;
	
	@Column(name = "DUREE")
	private Time duration;
	
	@Column(name = "DURATION")
	private String str;
	
	@Column(name = "PICTURE")
	private String picture;
	
	@Column(name = "RELEASE")
	private Date date;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	public Films()
	{
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getDirector()
	{
		return director;
	}

	public void setDirector(String director)
	{
		this.director = director;
	}

	public String getActors()
	{
		return actors;
	}

	public void setActors(String actors)
	{
		this.actors = actors;
	}

	public Time getDuration()
	{
		return duration;
	}

	public void setDuration(Time duration)
	{
		this.duration = duration;
	}

	public String getStr()
	{
		return str;
	}

	public void setStr(String str)
	{
		this.str = str;
	}
	
	public String getPicture()
	{
		return picture;
	}

	public void setPicture(String picture)
	{
		this.picture = picture;
	}
	
	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}
	
	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
}
