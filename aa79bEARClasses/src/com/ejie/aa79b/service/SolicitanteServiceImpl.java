package com.ejie.aa79b.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.dao.SolicitanteDao;
import com.ejie.aa79b.model.Gestor;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.x38.dto.JQGridRequestDto;

@Service(value = "solicitanteService")
public class SolicitanteServiceImpl extends GenericoServiceImpl<Solicitante> implements SolicitanteService {

	@Autowired()
	private SolicitanteDao solicitanteDao;

	@Override()
	protected GenericoDao<Solicitante> getDao() {
		return this.solicitanteDao;
	}

	@Override()
	public List<Solicitante> findGestoresDeExp(Solicitante solicitante) {
		return this.solicitanteDao.findGestoresDeExp(solicitante);
	}

	@Override()
	public List<Gestor> findGestoresRespresentante(Solicitante bean) {
		return this.solicitanteDao.findGestoresRespresentante(bean);
	}

	@Override
	public Solicitante findGestorCoordinador(Solicitante bean) {
		return this.solicitanteDao.findGestorCoordinador(bean);
	}

	@Override
	public List<Gestor> findGestoresConsulta(Solicitante bean) {
		return this.solicitanteDao.findGestoresConsulta(bean);
	}

	@Override
	public List<Solicitante> findGestoresDeExpCEntidad(Solicitante solicitanteFilter) {
		return this.solicitanteDao.findGestoresDeExpCEntidad(solicitanteFilter);
	}

	@Override
	public List<Solicitante> findRevisionContactoFacturarConExpedientes(Solicitante solicitanteFilter) {
		return this.solicitanteDao.findRevisionContactoFacturarConExpedientes(solicitanteFilter);
	}

	@Override
	public Solicitante find77(Solicitante bean) {
		return this.solicitanteDao.find77(bean);
	}

	@Override()
	public List<Solicitante> findGestoresExpConfidenciales(Solicitante solicitante) {
		return this.solicitanteDao.findGestoresExpConfidenciales(solicitante);
	}

	@Override
	public List<Solicitante> findSolicitantesGestAnul(Solicitante filterSolicitante) {
		return this.solicitanteDao.findSolicitantesGestAnul(filterSolicitante);
	}

	@Override
	public List<Solicitante> findSolicitanteAFacturarActDatosFact(Solicitante solicitanteFilter) {
		return this.solicitanteDao.findSolicitanteAFacturarActDatosFact(solicitanteFilter);
	}

	@Override
	public List<Solicitante> findSolicitanteActDatosFact(Solicitante solicitanteFilter) {
		return this.solicitanteDao.findSolicitanteActDatosFact(solicitanteFilter);
	}

	@Override
	public List<Solicitante> findGestores(Solicitante bean) {
		return this.solicitanteDao.findGestores(bean);
	}

	@Override
	public List<Solicitante> findEntidadCentroOrganico(Solicitante bean, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		return this.solicitanteDao.findEntidadCentroOrganico(bean, jqGridRequestDto, startsWith);
	}

}
