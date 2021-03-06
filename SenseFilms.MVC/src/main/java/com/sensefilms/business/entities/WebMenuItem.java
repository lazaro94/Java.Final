package com.sensefilms.business.entities;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sensefilms.business.entities.base.BaseWebItem;

@Entity
@Table(name = "web_menu_item")
public class WebMenuItem extends BaseWebItem
{	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "MenuItemID")
	private Set<WebMenuSubItem> subItemsCollection = new HashSet<WebMenuSubItem>();

	public Set<WebMenuSubItem> getSubItemsCollection() 
	{
		return subItemsCollection;
	}
}
