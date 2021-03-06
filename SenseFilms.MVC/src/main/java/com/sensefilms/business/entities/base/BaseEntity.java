package com.sensefilms.business.entities.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity implements IBaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, unique = true)
	private int id;
	
	public int getId() 
	{
		return this.id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
}
