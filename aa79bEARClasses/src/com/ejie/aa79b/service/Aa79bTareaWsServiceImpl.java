package com.ejie.aa79b.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.dao.Aa79bTareaWsDao;
import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.model.Auditoria;
import com.ejie.aa79b.model.EntradaDatosDocumento;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.AuditoriaTipoCampoEnum;
import com.ejie.aa79b.model.enums.AuditoriaTipoSeccionEnum;
import com.ejie.aa79b.model.webservices.Aa79bAuditoria;
import com.ejie.aa79b.model.webservices.Aa79bAuditoriaCampoSeccionExpediente;
import com.ejie.aa79b.model.webservices.Aa79bAuditoriaDocumentoSeccionExpediente;
import com.ejie.aa79b.model.webservices.Aa79bAuditoriaSeccionExpediente;
import com.ejie.aa79b.model.webservices.Aa79bConsultaTareasReport;
import com.ejie.aa79b.model.webservices.Aa79bDocumentoTarea;
import com.ejie.aa79b.model.webservices.Aa79bEntradaAuditoria;
import com.ejie.aa79b.model.webservices.Aa79bEntradaCamposSeccion;
import com.ejie.aa79b.model.webservices.Aa79bEntradaConsultaTarea;
import com.ejie.aa79b.model.webservices.Aa79bEntradaEjecutarTarea;
import com.ejie.aa79b.model.webservices.Aa79bEntradaValidarDocumentosTarea;
import com.ejie.aa79b.model.webservices.Aa79bListLoteCombo;
import com.ejie.aa79b.model.webservices.Aa79bLoteCombo;
import com.ejie.aa79b.model.webservices.Aa79bSalidaConsultaTarea;
import com.ejie.aa79b.model.webservices.Aa79bSalidaOrigenNoConformidad;
import com.ejie.aa79b.model.webservices.Aa79bSalidaTarea;
import com.ejie.aa79b.model.webservices.Aa79bTarea;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

@Service(value = "aa79bTareaWsService")
public class Aa79bTareaWsServiceImpl extends GenericoServiceImpl<Aa79bTarea> implements Aa79bTareaWsService {

	@Autowired
	private Aa79bTareaWsDao aa79bTareaWsDao;

	@Override
	protected GenericoDao<Aa79bTarea> getDao() {
		return this.aa79bTareaWsDao;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public Aa79bListLoteCombo obtenerBuscadorProveedor(String dni) {
		List<Aa79bLoteCombo> lotes = this.aa79bTareaWsDao.obtenerBuscadorProveedor(dni);
		Aa79bListLoteCombo listLoteComboObject = new Aa79bListLoteCombo();
		listLoteComboObject.setListaLoteCombo(lotes);
		listLoteComboObject.setEstado(Constants.ESTADO_WS_OK);
		return listLoteComboObject;
	}

	@Override
	public JQGridResponseDto<Aa79bSalidaConsultaTarea> consultaTareasProveedor(Aa79bEntradaConsultaTarea bean,
			JQGridRequestDto jqGridRequestDto, boolean b) {

		@SuppressWarnings("unchecked")
		List<Aa79bSalidaConsultaTarea> listaTareas = (List<Aa79bSalidaConsultaTarea>) this.aa79bTareaWsDao
				.consultaTareasProveedor(bean, jqGridRequestDto, false, false);

		Long recordNum = 0L;
		if (listaTareas != null && !listaTareas.isEmpty()) {
			recordNum = (Long) this.aa79bTareaWsDao.consultaTareasProveedor(bean, jqGridRequestDto, false, true);
		}

		if (jqGridRequestDto != null) {
			return new JQGridResponseDto<Aa79bSalidaConsultaTarea>(jqGridRequestDto, recordNum, listaTareas);
		} else {
			return new JQGridResponseDto<Aa79bSalidaConsultaTarea>(new JQGridRequestDto(), recordNum, listaTareas);
		}
	}

	@Override
	public JQGridResponseDto<Aa79bDocumentoTarea> obtenerDocumentosTraduccion(Aa79bEntradaEjecutarTarea bean,
			JQGridRequestDto jqGridRequestDto, boolean b) {
		@SuppressWarnings("unchecked")
		List<Aa79bDocumentoTarea> listaTareas = (List<Aa79bDocumentoTarea>) this.aa79bTareaWsDao
				.obtenerDocumentosTraduccion(bean, jqGridRequestDto, false, false);

		Long recordNum = 0L;
		if (listaTareas != null && !listaTareas.isEmpty()) {
			recordNum = (Long) this.aa79bTareaWsDao.obtenerDocumentosTraduccion(bean, jqGridRequestDto, false, true);
		}

		if (jqGridRequestDto != null) {
			return new JQGridResponseDto<Aa79bDocumentoTarea>(jqGridRequestDto, recordNum, listaTareas);
		} else {
			return new JQGridResponseDto<Aa79bDocumentoTarea>(new JQGridRequestDto(), recordNum, listaTareas);
		}
	}

	@Override
	public Boolean getAccesoTarea(Aa79bEntradaEjecutarTarea bean) {
		return this.aa79bTareaWsDao.getAccesoTarea(bean);
	}

	@Override
	public Tareas findTareaEntregaCliente(Tareas tarea) {
		return this.aa79bTareaWsDao.findTareaEntregaCliente(tarea);
	}

	@Override
	public Boolean getAccesoFicheroTarea(EntradaDatosDocumento bean) {
		return this.aa79bTareaWsDao.getAccesoFicheroTarea(bean);
	}

	@Override
	public JQGridResponseDto<Aa79bDocumentoTarea> obtenerDocumentosXliffWS(Aa79bEntradaEjecutarTarea bean,
			JQGridRequestDto jqGridRequestDto, boolean b) {
		@SuppressWarnings("unchecked")
		List<Aa79bDocumentoTarea> listaTareas = (List<Aa79bDocumentoTarea>) this.aa79bTareaWsDao
				.obtenerDocumentosXliffWS(bean, jqGridRequestDto, false, false);

		Long recordNum = 0L;
		if (listaTareas != null && !listaTareas.isEmpty()) {
			recordNum = (Long) this.aa79bTareaWsDao.obtenerDocumentosXliffWS(bean, jqGridRequestDto, false, true);
		}

		if (jqGridRequestDto != null) {
			return new JQGridResponseDto<Aa79bDocumentoTarea>(jqGridRequestDto, recordNum, listaTareas);
		} else {
			return new JQGridResponseDto<Aa79bDocumentoTarea>(new JQGridRequestDto(), recordNum, listaTareas);
		}
	}

	@Override
	public JQGridResponseDto<Aa79bDocumentoTarea> obtenerDocumentosRevision(Aa79bEntradaEjecutarTarea bean,
			JQGridRequestDto jqGridRequestDto, boolean b) {
		@SuppressWarnings("unchecked")
		List<Aa79bDocumentoTarea> listaTareas = (List<Aa79bDocumentoTarea>) this.aa79bTareaWsDao
				.obtenerDocumentosRevision(bean, jqGridRequestDto, false, false);

		Long recordNum = 0L;
		if (listaTareas != null && !listaTareas.isEmpty()) {
			recordNum = (Long) this.aa79bTareaWsDao.obtenerDocumentosRevision(bean, jqGridRequestDto, false, true);
		}

		if (jqGridRequestDto != null) {
			return new JQGridResponseDto<Aa79bDocumentoTarea>(jqGridRequestDto, recordNum, listaTareas);
		} else {
			return new JQGridResponseDto<Aa79bDocumentoTarea>(new JQGridRequestDto(), recordNum, listaTareas);
		}
	}

	@Override
	public Aa79bSalidaOrigenNoConformidad obtenerOrigenNoConformidad(Aa79bEntradaEjecutarTarea bean) {
		return this.aa79bTareaWsDao.obtenerOrigenNoConformidad(bean);
	}

	@Override
	public JQGridResponseDto<Aa79bDocumentoTarea> obtenerDocumentosTraduccionNoConformidad(
			Aa79bEntradaEjecutarTarea bean, JQGridRequestDto jqGridRequestDto, boolean b) {
		@SuppressWarnings("unchecked")
		List<Aa79bDocumentoTarea> listaTareas = (List<Aa79bDocumentoTarea>) this.aa79bTareaWsDao
				.obtenerDocumentosTraduccionNoConformidad(bean, jqGridRequestDto, false, false);

		Long recordNum = 0L;
		if (listaTareas != null && !listaTareas.isEmpty()) {
			recordNum = (Long) this.aa79bTareaWsDao.obtenerDocumentosTraduccionNoConformidad(bean, jqGridRequestDto,
					false, true);
		}

		if (jqGridRequestDto != null) {
			return new JQGridResponseDto<Aa79bDocumentoTarea>(jqGridRequestDto, recordNum, listaTareas);
		} else {
			return new JQGridResponseDto<Aa79bDocumentoTarea>(new JQGridRequestDto(), recordNum, listaTareas);
		}
	}

	@Override
	public JQGridResponseDto<Aa79bDocumentoTarea> obtenerDocumentosRevisionNoConformidad(Aa79bEntradaEjecutarTarea bean,
			JQGridRequestDto jqGridRequestDto, boolean b) {
		@SuppressWarnings("unchecked")
		List<Aa79bDocumentoTarea> listaTareas = (List<Aa79bDocumentoTarea>) this.aa79bTareaWsDao
				.obtenerDocumentosRevisionNoConformidad(bean, jqGridRequestDto, false, false);

		Long recordNum = 0L;
		if (listaTareas != null && !listaTareas.isEmpty()) {
			recordNum = (Long) this.aa79bTareaWsDao.obtenerDocumentosRevisionNoConformidad(bean, jqGridRequestDto,
					false, true);
		}

		if (jqGridRequestDto != null) {
			return new JQGridResponseDto<Aa79bDocumentoTarea>(jqGridRequestDto, recordNum, listaTareas);
		} else {
			return new JQGridResponseDto<Aa79bDocumentoTarea>(new JQGridRequestDto(), recordNum, listaTareas);
		}
	}

	@Override
	public Boolean validarDocumentosTareaFinalizar(Aa79bEntradaValidarDocumentosTarea bean) {
		Boolean valido = false;
		Integer count = this.aa79bTareaWsDao.validarDocumentosTareaFinalizar(bean);
		if (count == 0) {
			valido = true;
		}
		return valido;
	}

	@Override
	public JQGridResponseDto<Aa79bConsultaTareasReport> consultaTareasInformeProveedor(Aa79bEntradaConsultaTarea bean,
			JQGridRequestDto jqGridRequestDto, boolean b) {
		List<Aa79bConsultaTareasReport> listaTareas = this.aa79bTareaWsDao
				.consultaTareasInformeProveedor(bean, jqGridRequestDto, false);
		Long recordNum = 0L;
		if (!listaTareas.isEmpty()) {
			recordNum = (long) listaTareas.size();
		}
		if (jqGridRequestDto != null && !recordNum.equals(0L)) {
			return new JQGridResponseDto<Aa79bConsultaTareasReport>(jqGridRequestDto, recordNum, listaTareas);
		} else {
			return new JQGridResponseDto<Aa79bConsultaTareasReport>(new JQGridRequestDto(), recordNum, listaTareas);
		}
	}

	@Override
	public Aa79bSalidaTarea getDatosTareaEjecutada(BigDecimal idTarea) {
		return this.aa79bTareaWsDao.getDatosTareaEjecutada(idTarea);
	}

	@Override
	public JQGridResponseDto<Aa79bAuditoria> obtenerAuditorias(Aa79bEntradaAuditoria bean,
			JQGridRequestDto jqGridRequestDto, boolean b) {
		@SuppressWarnings("unchecked")
		List<Aa79bAuditoria> listaAuditorias = (List<Aa79bAuditoria>) this.aa79bTareaWsDao.obtenerAuditorias(bean,
				jqGridRequestDto, false, false);

		Long recordNum = 0L;
		if (listaAuditorias != null && !listaAuditorias.isEmpty()) {
			recordNum = (Long) this.aa79bTareaWsDao.obtenerAuditorias(bean, jqGridRequestDto, false, true);
		}

		if (jqGridRequestDto != null) {
			return new JQGridResponseDto<Aa79bAuditoria>(jqGridRequestDto, recordNum, listaAuditorias);
		} else {
			return new JQGridResponseDto<Aa79bAuditoria>(new JQGridRequestDto(), recordNum, listaAuditorias);
		}
	}

	@Override
	public Aa79bAuditoria getDatosGeneralesAuditoria(Auditoria auditoria) {
		return this.aa79bTareaWsDao.getDatosGeneralesAuditoria(auditoria);
	}

	@Override
	public Aa79bAuditoria guardarDatosAuditoria(Aa79bAuditoria auditoria) {
		auditoria = this.aa79bTareaWsDao.confirmarAuditoria(auditoria);
		this.guardarDatosSeccionesYCampos(auditoria);
		return auditoria;
	}

	private Aa79bAuditoria guardarDatosSeccionesYCampos(Aa79bAuditoria auditoria) {
		for (Aa79bAuditoriaSeccionExpediente seccion : auditoria.getlAuditoriaSeccionExpediente()) {
			if (AuditoriaTipoSeccionEnum.VALORACION.getValue().equals(seccion.getTipoSeccion())
					|| ActivoEnum.SI.getValue().equalsIgnoreCase(seccion.getIndObservaciones())) {
				this.aa79bTareaWsDao.guardarDatosSeccion(seccion);
			}
			for (Aa79bAuditoriaCampoSeccionExpediente campo : seccion.getlCamposSeccion()) {
				if (AuditoriaTipoCampoEnum.VALORACION.getValue().equals(campo.getTipoCampo())
						|| AuditoriaTipoCampoEnum.CONDICION.getValue().equals(campo.getTipoCampo())) {
					this.aa79bTareaWsDao.guardarDatosCampo(campo);
				}
			}
		}
		return auditoria;
	}

	@Override
	public List<Aa79bAuditoriaSeccionExpediente> getSeccionesExpedienteAuditoria(Auditoria auditoria) {
		return this.aa79bTareaWsDao.getSeccionesExpedienteAuditoria(auditoria);
	}

	@Override
	public JQGridResponseDto<Aa79bAuditoriaCampoSeccionExpediente> filterCamposSeccion(
			Aa79bEntradaCamposSeccion seccionFilter, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		List<Aa79bAuditoriaCampoSeccionExpediente> listaT = this.aa79bTareaWsDao.filterCamposSeccion(seccionFilter,
				jqGridRequestDto, false);

		Long recordNum = this.aa79bTareaWsDao.filterCamposSeccionCount(seccionFilter, false);

		return new JQGridResponseDto<Aa79bAuditoriaCampoSeccionExpediente>(jqGridRequestDto, recordNum, listaT);
	}

	@Override
	public JQGridResponseDto<Aa79bAuditoriaDocumentoSeccionExpediente> filterDocumentosSeccion(
			Aa79bEntradaCamposSeccion filter, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		List<Aa79bAuditoriaDocumentoSeccionExpediente> listaT = this.aa79bTareaWsDao.filterDocumentosSeccion(filter,
				jqGridRequestDto, false);

		Long recordNum = this.aa79bTareaWsDao.filterDocumentosSeccionCount(filter, false);

		return new JQGridResponseDto<Aa79bAuditoriaDocumentoSeccionExpediente>(jqGridRequestDto, recordNum, listaT);
	}
}
