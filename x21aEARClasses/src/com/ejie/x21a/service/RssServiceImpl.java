package com.ejie.x21a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ejie.x21a.dao.RssDao;
import com.ejie.x38.rss.RssContent;

@Service(value = "rssService")
public class RssServiceImpl implements RssService {
	
	@Autowired
	private RssDao rssDao;
	
	public List<RssContent> getAll(){
		SecurityContextHolder.getContext();
		return rssDao.getAll();
	}

}
