package com.ejie.aa79b.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.dao.EmisionFacturasDao;
import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.model.ConsultaFacturacionExpediente;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.Solicitante;

@Service(value = "emisionFacturasService")
public class EmisionFacturasServiceImpl extends GenericoServiceImpl<ConsultaFacturacionExpediente> implements EmisionFacturasService {

	@Autowired()
	private EmisionFacturasDao emisionFacturasDao;
	
	@Override
	protected GenericoDao<ConsultaFacturacionExpediente> getDao() {
		return this.emisionFacturasDao;
	}

	@Override
	public List<Entidad> getEntidadesSolicitantes(Entidad entidad) {
		return this.emisionFacturasDao.getEntidadesSolicitantes(entidad);
	}

	@Override
	public List<Solicitante> getContactosSolicitantes(Solicitante contacto) {
		return this.emisionFacturasDao.getContactosSolicitantes(contacto);
	}
	
	@Override
	public List<Entidad> getEntidadesFacturar(Entidad entidad) {
		return this.emisionFacturasDao.getEntidadesFacturar(entidad);
	}
	
	@Override
	public List<Solicitante> getContactosFacturar(Solicitante contacto) {
		return this.emisionFacturasDao.getContactosFacturar(contacto);
	}

}
