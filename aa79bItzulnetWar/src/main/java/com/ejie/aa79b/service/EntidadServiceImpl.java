package com.ejie.aa79b.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.dao.EntidadDao;
import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.model.Entidad;
import com.ejie.x38.dto.JQGridRequestDto;

@Service(value = "entidadService")
public class EntidadServiceImpl extends GenericoServiceImpl<Entidad> implements EntidadService {

	@Autowired()
	private EntidadDao entidadDao;

	@Override()
	protected GenericoDao<Entidad> getDao() {
		return this.entidadDao;
	}

	@Override
	public List<Entidad> getEntidadesGestorasConExpEnEstado(Entidad entidad, Integer idEstadoExp) {

		return this.entidadDao.getEntidadesGestorasConExpEnEstado(entidad, idEstadoExp);
	}

	@Override
	public List<Entidad> getEntidadesGestorasConExpEnEstado(Entidad entidad, Integer idEstadoExp, Integer idFaseExp) {

		return this.entidadDao.getEntidadesGestorasConExpEnEstado(entidad, idEstadoExp, idFaseExp, null);
	}

	@Override
	public List<Entidad> getEntidadesGestorasConExpEnEstado(Entidad entidad, Integer idEstadoExp, Integer idFaseExp,
			String entidadAFacturar) {

		return this.entidadDao.getEntidadesGestorasConExpEnEstado(entidad, idEstadoExp, idFaseExp, entidadAFacturar);
	}

	@Override
	public List<Entidad> getEntidadesConExpARelacionar(Entidad entidad) {

		return this.entidadDao.getEntidadesConExpARelacionar(entidad);
	}

	@Override
	public List<Entidad> getEntidadesReceptores(Entidad entidad) {
		return this.entidadDao.getEntidadesReceptores(entidad);
	}

	@Override
	public List<Entidad> getEntidadesSolicitantes(Entidad entidad) {
		return this.entidadDao.getEntidadesSolicitantes(entidad);
	}

	@Override
	public List<Entidad> getEntidadesConContactosSolicitantes(Entidad entidad) {
		return this.entidadDao.getEntidadesConContactosSolicitantes(entidad);
	}

	@Override
	public List<Entidad> getEntidadesConContactosSolicitantesConBajas(Entidad entidad) {
		return this.entidadDao.getEntidadesConContactosSolicitantes(entidad, false);
	}

	@Override
	public List<Entidad> findAllLikeGrupoTrabajo(Entidad bean, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		return this.entidadDao.findAllLikeGrupoTrabajo(bean, jqGridRequestDto, startsWith);
	}

	@Override
	public Entidad obtenerDatosConfeccionarFactura(Entidad entidad) {
		return this.entidadDao.obtenerDatosConfeccionarFactura(entidad);
	}

	@Override
	public List<Entidad> getEntidadesGestAnulacion(Entidad entidad) {
		return this.entidadDao.getEntidadesGestAnulacion(entidad);
	}

	@Override
	public boolean buscarEntidadAlta(Entidad entidad) {
		return this.entidadDao.buscarEntidadAlta(entidad);
	}

	@Override
	public List<Entidad> getEntidadesGestorasConExpConfidenciales(Entidad entidad, String dni) {
		return this.entidadDao.getEntidadesGestorasConExpConfidenciales(entidad, dni);
	}

	@Override
	public List<Entidad> getEntidadesGestorasActDatosFacturacion(Entidad entidad, String entidadAFacturar) {
		return this.entidadDao.getEntidadesGestorasActDatosFacturacion(entidad, entidadAFacturar);
	}

	@Override
	public List<Entidad> getEntidadesConGestorActivo(Entidad entidad) {
		return this.entidadDao.getEntidadesConGestorActivo(entidad);
	}

}
