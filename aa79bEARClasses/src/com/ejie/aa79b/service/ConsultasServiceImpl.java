package com.ejie.aa79b.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.dao.ConsultasDao;
import com.ejie.aa79b.dao.DatosPersonaDao;
import com.ejie.aa79b.dao.ExpedienteDao;
import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.dao.RegistroAccionesDao;
import com.ejie.aa79b.dao.SolicitanteDao;
import com.ejie.aa79b.model.DatosPagoProveedores;
import com.ejie.aa79b.model.DetalleExpedienteVisionCliente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteConsulta;
import com.ejie.aa79b.model.ListaExpediente;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.enums.EstadoGestorEnum;
import com.ejie.aa79b.utils.ConsultaExpedienteUtils;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

@Service(value = "consultasService")
public class ConsultasServiceImpl extends GenericoServiceImpl<ExpedienteConsulta> implements ConsultasService {

	@Autowired()
	private ConsultasDao consultasDao;
	@Autowired
	private ExpedienteDao expedienteDao;
	@Autowired()
	private PersonalIZOService personalIZOService;
	@Autowired()
	private RegistroAccionesDao registroAccionesdao;
	@Autowired()
	private DatosPersonaDao datosPersonaDao;
	@Autowired()
	private SolicitanteDao solicitanteDao;

	@Override
	protected GenericoDao<ExpedienteConsulta> getDao() {
		return this.consultasDao;
	}

	@Override
	public JQGridResponseDto<ExpedienteConsulta> consultaexpedientes(ExpedienteConsulta filter,
			JQGridRequestDto jqGridRequestDto) {
		@SuppressWarnings("unchecked")
		List<ExpedienteConsulta> listaExpedientes = (List<ExpedienteConsulta>) this.consultasDao
				.consultaexpedientes(filter, jqGridRequestDto, false, false);

		Long recordNum = 0L;

		if (listaExpedientes != null && !listaExpedientes.isEmpty()) {
			recordNum = (Long) this.consultasDao.consultaexpedientes(filter, jqGridRequestDto, false, true);
		}

		if (jqGridRequestDto != null) {
			return new JQGridResponseDto<ExpedienteConsulta>(jqGridRequestDto, recordNum, listaExpedientes);
		} else {
			return new JQGridResponseDto<ExpedienteConsulta>(new JQGridRequestDto(), recordNum, listaExpedientes);
		}
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public Integer asignarGestorAExpedientes(ListaExpediente listaExpedientes) {
		// si no está, guardar gestor en 77
		if (ConsultaExpedienteUtils.listaExpYGestorValidos(listaExpedientes)) {
			PersonalIZO personalIZO = new PersonalIZO();
			personalIZO.setDni(
					listaExpedientes.getListaExpediente().get(0).getGestorExpediente().getSolicitante().getDni());
			PersonalIZO personalAInsertar = this.solicitanteDao.getDatosGestor(personalIZO);
			boolean esta = this.datosPersonaDao.comprobarPersona(
					listaExpedientes.getListaExpediente().get(0).getGestorExpediente().getSolicitante().getDni());
			if (!esta) {
				this.expedienteDao.guardarPersona(personalAInsertar);
			}

			// Se actualiza el gestor
			this.consultasDao.asignarGestorAExpedientes(listaExpedientes, personalAInsertar);

			// Se actualiza el registro de acciones
			return this.registroAccionesdao.addRegistroListaExpedientesConsultaModificarGestor(listaExpedientes);
		}
		return Constants.CERO;

	}

	@Override
	public String getEstadoGestor(String dni) {
		Integer iEstado = this.consultasDao.getEstadoGestor(dni);
		String resultado = new String();
		if (iEstado == null || Constants.CERO.equals(iEstado)) {
			resultado = EstadoGestorEnum.BAJA.getValue();
		} else {
			resultado = EstadoGestorEnum.ALTA.getValue();
		}
		return resultado;
	}

	@Override
	public Integer comprobarDatosPagoAProveedores(Expediente expediente) {
		return this.consultasDao.comprobarDatosPagoAProveedores(expediente);
	}

	@Override
	public JQGridResponseDto<DatosPagoProveedores> datosPagoProveedoresConsulta(Expediente filter,
			JQGridRequestDto jqGridRequestDto, boolean startsWith) {
		@SuppressWarnings("unchecked")
		List<DatosPagoProveedores> listaExpedientes = (List<DatosPagoProveedores>) this.consultasDao
				.datosPagoProveedoresConsulta(filter, jqGridRequestDto, false, false);

		Long recordNum = 0L;

		if (listaExpedientes != null && !listaExpedientes.isEmpty()) {
			recordNum = (Long) this.consultasDao.datosPagoProveedoresConsulta(filter, jqGridRequestDto, false, true);
		}

		if (jqGridRequestDto != null) {
			return new JQGridResponseDto<DatosPagoProveedores>(jqGridRequestDto, recordNum, listaExpedientes);
		} else {
			return new JQGridResponseDto<DatosPagoProveedores>(new JQGridRequestDto(), recordNum, listaExpedientes);
		}
	}

	@Override
	public Integer comprobarDatosFacturacionExpedienteConsulta(Expediente expediente) {
		return this.consultasDao.comprobarDatosFacturacionExpedienteConsulta(expediente);
	}

	@Override
	public Integer comprobarEsSolicitud(Expediente expediente) {
		return this.consultasDao.comprobarEsSolicitud(expediente);
	}

	@Override
	public DetalleExpedienteVisionCliente findDatosDetalleExpedienteDesdeClienteConsulta(Expediente expediente) {
		return this.consultasDao.findDatosDetalleExpedienteDesdeClienteConsulta(expediente);
	}

	@Override
	public List<ExpedienteConsulta> consultaexpedientesGetSelectedIds(ExpedienteConsulta filterExpedienteConsulta,
			JQGridRequestDto tableData) {
		return this.consultasDao.consultaexpedientesGetSelectedIds(filterExpedienteConsulta, tableData);
	}

}
