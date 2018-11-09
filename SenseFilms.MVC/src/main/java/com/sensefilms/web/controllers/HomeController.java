package com.sensefilms.web.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sensefilms.core.exceptions.UiException;
import com.sensefilms.core.utilities.IAuthenticationContext;
import com.sensefilms.services.contracts.IWebSupportService;
import com.sensefilms.web.controllers.base.BaseAjaxController;
import com.sensefilms.web.support.ViewsResources;

@Controller
public class HomeController extends BaseAjaxController
{	
	private IWebSupportService webSupportService;
	
	@Autowired
	public HomeController(IWebSupportService webSupportService, IAuthenticationContext authenticationContext) 
	{
		super(HomeController.class, authenticationContext);	
		this.webSupportService = webSupportService;
	}
		
	@RequestMapping(value = "/signIn")
	public ModelAndView home(Locale locale, Model model) 
	{
		getLogger().info("We're alive! {}.", locale);
		
		return new ModelAndView(isUserAuthenticated() ? ViewsResources.INDEX_VIEW : ViewsResources.HOME_VIEW);
	}	
	
	@RequestMapping(value = "/HomeController/index", method = RequestMethod.GET)
	public ModelAndView getIndexView(Model model) 
	{		
		return new ModelAndView(ViewsResources.INDEX_VIEW);
	}
	
	@RequestMapping(value = "/HomeController/getMenuItems", method = RequestMethod.GET)
	@ResponseBody
	public String getHeaderMenuItems(Model model) 
	{		
		try
		{
			return jsonResult(webSupportService.getAllowedWebMenuItems(getLoggedUserName()));
		} 
		catch (UiException cEx)
		{
			return String.format("Server Error Ocurred: %s", cEx.getMessage());
		}
	}
}
