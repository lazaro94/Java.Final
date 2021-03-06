package com.sensefilms.repositories.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sensefilms.business.entities.WebMenuItem;
import com.sensefilms.common.utils.CastUtils;
import com.sensefilms.repositories.base.BaseRepository;
import com.sensefilms.repositories.contracts.IWebSupportRepository;

@Repository
@Transactional
public class WebSupportRepository extends BaseRepository implements IWebSupportRepository 
{

	@Override
	public ArrayList<WebMenuItem> getAllWebMenuItems()
	{
		List<WebMenuItem> castedList = CastUtils.castList(WebMenuItem.class, getCriteria(WebMenuItem.class).list());
		return new ArrayList<WebMenuItem>(castedList);	
	}

}
