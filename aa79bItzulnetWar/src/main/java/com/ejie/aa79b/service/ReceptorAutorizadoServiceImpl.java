package com.ejie.aa79b.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.dao.Aa79bExpedienteWsDao;
import com.ejie.aa79b.dao.DatosPersonaDao;
import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.dao.ReceptorAutorizadoDao;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ListaReceptoresAutorizados;
import com.ejie.aa79b.model.Persona;
import com.ejie.aa79b.model.ReceptorAutorizado;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;
import com.ejie.x38.dto.TableRowDto;

@Service(value = "receptorAutorizadoService")
public class ReceptorAutorizadoServiceImpl extends GenericoServiceImpl<ReceptorAutorizado>
		implements ReceptorAutorizadoService {

	@Autowired()
	private ReceptorAutorizadoDao receptorAutorizadoDao;

	@Autowired()
	private Aa79bExpedienteWsDao aa79bExpedienteWsDao;

	@Autowired()
	private DatosPersonaDao datosPersonaDao;

	@Override()
	protected GenericoDao<ReceptorAutorizado> getDao() {
		return this.receptorAutorizadoDao;
	}

	@Override
	public ListaReceptoresAutorizados comprobarYGuardar(ListaReceptoresAutorizados listaReceptoresAutorizados) {
		ListaReceptoresAutorizados lista = listaReceptoresAutorizados;
		Long esta = 0L;
		if (lista != null && lista.getListaReceptoresAutorizados() != null
				&& !lista.getListaReceptoresAutorizados().isEmpty()) {
			for (ReceptorAutorizado receptorAutorizado : lista.getListaReceptoresAutorizados()) {
				esta = this.receptorAutorizadoDao.comprobarSiExiste(receptorAutorizado);
				if (esta == 0) {
					this.receptorAutorizadoDao.add(receptorAutorizado);
				}
			}
		}
		return lista;
	}

	@Override
	public Boolean getPermisosUsuario(Solicitante bean) {
		return this.receptorAutorizadoDao.getPermisosUsuario(bean);
	}

	@Override
	public Boolean getAccesoExpediente(Expediente bean) {
		return this.receptorAutorizadoDao.getAccesoExpediente(bean);
	}

	@Override
	public JQGridResponseDto<ReceptorAutorizado> getReceptoresAutorizados(Expediente bean,
			JQGridRequestDto jqGridRequestDto, boolean startsWith) {

		@SuppressWarnings("unchecked")
		List<ReceptorAutorizado> listaReceptores = (List<ReceptorAutorizado>) this.receptorAutorizadoDao
				.getReceptoresAutorizados(bean, jqGridRequestDto, false, false);

		Long recordNum = 0L;

		if (listaReceptores != null && !listaReceptores.isEmpty()) {
			recordNum = (Long) this.receptorAutorizadoDao.getReceptoresAutorizados(bean, jqGridRequestDto, false, true);
		}

		if (jqGridRequestDto != null && jqGridRequestDto.getMultiselection().getSelectedIds() != null) {
			List<TableRowDto<ReceptorAutorizado>> reorderSelection = this.receptorAutorizadoDao
					.reorderSelectionReceptoresAutorizados(bean, jqGridRequestDto, false);
			return new JQGridResponseDto<ReceptorAutorizado>(jqGridRequestDto, recordNum, listaReceptores,
					reorderSelection);
		}

		if (jqGridRequestDto != null) {
			return new JQGridResponseDto<ReceptorAutorizado>(jqGridRequestDto, recordNum, listaReceptores);
		} else {
			return new JQGridResponseDto<ReceptorAutorizado>(new JQGridRequestDto(), recordNum, listaReceptores);
		}

	}

	@Override
	public void guardarReceptoresAutorizados(ListaReceptoresAutorizados listaReceptoresAutorizados) {
		// Se graban los registros en la tabla 63 (Receptores Autorizados)
		this.comprobarYGuardar(listaReceptoresAutorizados);

		// Se graba el registro en la tabla 77 (Datos persona) en caso de que no
		// exista
		if (listaReceptoresAutorizados != null
				&& !listaReceptoresAutorizados.getListaReceptoresAutorizados().isEmpty()) {
			List<ReceptorAutorizado> listaReceptores = listaReceptoresAutorizados.getListaReceptoresAutorizados();

			for (ReceptorAutorizado receptor : listaReceptores) {
				boolean esta = this.datosPersonaDao.comprobarPersona(receptor.getDni());
				if (!esta) {
					Persona persona = new Persona();
					persona.setDni(receptor.getDni());
					Persona receptorAInsertar = this.aa79bExpedienteWsDao.findTrabajadorGIP(persona);

					this.aa79bExpedienteWsDao.guardarPersona(receptorAInsertar);
				}
			}

		}
	}

	@Override
	public void eliminarReceptorAutorizado(ReceptorAutorizado receptorAutorizado) {
		this.receptorAutorizadoDao.eliminarReceptorAutorizado(receptorAutorizado);
	}

	@Override
	public Integer getReceptorAutorizadoCount(String dni) {
		return this.receptorAutorizadoDao.getReceptorAutorizadoCount(dni);
	}

	@Override
	public Boolean esReceptorAutorizadoYAccesoAExpediente(String dni, Long anyo, Integer numExp) {
		return this.receptorAutorizadoDao.esReceptorAutorizadoYAccesoAExpediente(dni, anyo, numExp);
	}

}
