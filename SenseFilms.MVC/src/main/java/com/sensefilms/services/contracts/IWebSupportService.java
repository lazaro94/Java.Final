package com.sensefilms.services.contracts;

import java.util.List;

import com.sensefilms.business.entities.WebMenuItem;
import com.sensefilms.core.exceptions.UiException;

public interface IWebSupportService 
{
	public List<WebMenuItem> getAllowedWebMenuItems(String userName) throws UiException;
}
