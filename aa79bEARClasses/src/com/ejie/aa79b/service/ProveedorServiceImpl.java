package com.ejie.aa79b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.dao.ProveedorDao;
import com.ejie.aa79b.model.Proveedor;

@Service(value = "proveedorService")
public class ProveedorServiceImpl extends GenericoServiceImpl<Proveedor> implements ProveedorService {

	@Autowired()
	private ProveedorDao proveedorDao;
	
	@Override()
	protected GenericoDao<Proveedor> getDao() {
		return this.proveedorDao;
	}

}
