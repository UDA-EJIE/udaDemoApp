package com.ejie.x21a.service;

import java.util.List;

import com.ejie.x21a.model.Dashboard;

public interface DashboardService {
	
	List<Dashboard> getAll ();
	
	Dashboard get(Dashboard dashboard);
	
	Dashboard post(Dashboard dashboard);
	
	Dashboard put(Dashboard dashboard);
	
	
}
