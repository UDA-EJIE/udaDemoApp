package com.ejie.x21a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.x21a.dao.DashboardDao;
import com.ejie.x21a.model.Dashboard;

@Service(value = "dashboardService")
public class DashboardServiceImpl implements DashboardService {
	
	@Autowired
	private DashboardDao dashboardDao;
	
	public List<Dashboard> getAll(){
		return dashboardDao.getAll();
	}

	@Override
	public Dashboard get(Dashboard dashboard) {
		// 
		return dashboardDao.get(dashboard);
	}

	@Override
	public Dashboard post(Dashboard dashboard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dashboard put(Dashboard dashboard) {
		return dashboardDao.put(dashboard);
	}
	
	

}
