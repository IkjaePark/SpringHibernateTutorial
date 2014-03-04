package com.inpcreations.BlogEntry.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contacts")
public class Contact
{
	private int id;
	private String name;
	private String phone;
	private String email;
	private String comments;
	private Timestamp createdOn;
	
	public Contact() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", unique=true, nullable=false)	
	public int getId()
	{
		return id;
	}
	public void setId( int id )
	{
		this.id = id;
	}

	
	@Column(name="name")
	public String getName()
	{
		return name;
	}
	public void setName( String name )
	{
		this.name = name;
	}

	
	@Column(name="phone")
	public String getPhone()
	{
		return phone;
	}
	public void setPhone( String phone )
	{
		this.phone = phone;
	}

	
	@Column(name="email")
	public String getEmail() 
	{
		return email;
	}
	public void setEmail( String email )
	{
		this.email = email;
	}


	@Column(name="comments")
	public String getComments()
	{
		return comments;
	}
	public void setComments( String comments )
	{
		this.comments = comments;
	}
	
	
	@Column(name="created_date")
	public Timestamp getCreatedOn()
	{
		return createdOn;
	}
	public void setCreatedOn( Timestamp createdOn )
	{
		this.createdOn = createdOn;
	}
}