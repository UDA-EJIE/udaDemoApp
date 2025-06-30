package com.ejie.aa79b.service;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.dao.Aa79bExpedienteWsDao;
import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.model.BitacoraSolicitante;
import com.ejie.aa79b.model.CabeceraExpediente;
import com.ejie.aa79b.model.DireccionNora;
import com.ejie.aa79b.model.EntradaDatosExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.model.webservices.Aa79bEntidadContacto;
import com.ejie.aa79b.model.webservices.Aa79bExpediente;
import com.ejie.aa79b.model.webservices.Aa79bExpedienteRelacionado;
import com.ejie.aa79b.model.webservices.Aa79bInformeSolicitudes;
import com.ejie.aa79b.model.webservices.Aa79bInformes;
import com.ejie.aa79b.model.webservices.Aa79bSalidaDatosPresupuestoFacturacion;
import com.ejie.aa79b.model.webservices.Aa79bSolaskides;
import com.ejie.aa79b.utils.Aa79bExpedienteWSUtils;
import com.ejie.aa79b.utils.Utils;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;
import com.ejie.x38.dto.TableRowDto;

@Service(value = "aa79bExpedienteWsService")
public class Aa79bExpedienteWsServiceImpl extends GenericoServiceImpl<Aa79bExpediente>
		implements Aa79bExpedienteWsService {

	@Autowired
	private Aa79bExpedienteWsDao aa79bExpedienteWsDao;
	@Autowired
	private DireccionNoraService direccionNoraService;

	@Override
	protected GenericoDao<Aa79bExpediente> getDao() {
		return this.aa79bExpedienteWsDao;
	}

	@Override()
	public boolean tieneAccesoExpediente(Expediente bean, String numeroTabla) {
		return this.aa79bExpedienteWsDao.tieneAccesoExpediente(bean, numeroTabla);
	}

	@Override
	@SuppressWarnings("unchecked")
	public JQGridResponseDto<Aa79bExpediente> obtenerExpedientes(Solicitante solicitante, Aa79bExpediente bean,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		List<Aa79bExpediente> listaExpedientes = (List<Aa79bExpediente>) this.aa79bExpedienteWsDao
				.obtenerExpedientes(solicitante, bean, jqGridRequestDto, false, false);

		Long recordNum = 0L;

		if (listaExpedientes != null && !listaExpedientes.isEmpty()) {
			recordNum = (Long) this.aa79bExpedienteWsDao.obtenerExpedientes(solicitante, bean, jqGridRequestDto, false,
					true);
		}

		if (jqGridRequestDto != null) {
			return new JQGridResponseDto<Aa79bExpediente>(jqGridRequestDto, recordNum, listaExpedientes);
		} else {
			return new JQGridResponseDto<Aa79bExpediente>(new JQGridRequestDto(), recordNum, listaExpedientes);
		}

	}

	@Override
	public Integer eliminarExpediente(Aa79bExpediente bean) {
		return this.aa79bExpedienteWsDao.eliminarExpediente(bean);
	}

	@Override
	@SuppressWarnings("unchecked")
	public JQGridResponseDto<Aa79bExpediente> buscarExpedientesRelacionados(Solicitante solicitante,
			Aa79bExpediente bean, JQGridRequestDto jQGridRequestDto, Boolean startsWith) {

		List<Aa79bExpediente> listaT = (List<Aa79bExpediente>) this.aa79bExpedienteWsDao
				.buscarExpedientesRelacionados(solicitante, bean, jQGridRequestDto, false, false);
		Long recordNum = 0L;
		if (listaT != null && !listaT.isEmpty()) {
			recordNum = (Long) this.aa79bExpedienteWsDao.buscarExpedientesRelacionados(solicitante, bean,
					jQGridRequestDto, false, true);
		}

		if (jQGridRequestDto != null && jQGridRequestDto.getMultiselection().getSelectedIds() != null) {
			List<TableRowDto<Aa79bExpediente>> reorderSelection = this.aa79bExpedienteWsDao
					.reorderBuscarSelectionExpedientesRelacionados(solicitante.getDni(), bean, jQGridRequestDto, false);
			return new JQGridResponseDto<Aa79bExpediente>(jQGridRequestDto, recordNum, listaT, reorderSelection);
		}

		if (jQGridRequestDto != null) {
			return new JQGridResponseDto<Aa79bExpediente>(jQGridRequestDto, recordNum, listaT);
		} else {
			return new JQGridResponseDto<Aa79bExpediente>(new JQGridRequestDto(), recordNum, listaT);
		}

	}

	@Override()
	public CabeceraExpediente findCabeceraExpediente(Expediente bean) {
		return this.aa79bExpedienteWsDao.findCabeceraExpediente(bean);
	}

	@Override()
	public List<BitacoraSolicitante> findInfoBitacora(Expediente expediente) {
		return this.aa79bExpedienteWsDao.findInfoBitacora(expediente);
	}

	@Override
	public Aa79bExpediente findDatosExpediente(Expediente bean) {
		return this.aa79bExpedienteWsDao.findDatosExpediente(bean);
	}

	@Override
	@SuppressWarnings("unchecked")
	public JQGridResponseDto<Aa79bExpedienteRelacionado> findExpedientesRelacionados(Aa79bExpediente bean,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		List<Aa79bExpedienteRelacionado> lista = (List<Aa79bExpedienteRelacionado>) this.aa79bExpedienteWsDao
				.findExpedientesRelacionados(bean, jqGridRequestDto, false, false);
		Long recordNum = 0L;
		if (lista != null && !lista.isEmpty()) {
			recordNum = (Long) this.aa79bExpedienteWsDao.findExpedientesRelacionados(bean, jqGridRequestDto, false,
					true);
		}

		if (jqGridRequestDto != null && jqGridRequestDto.getMultiselection().getSelectedIds() != null) {
			List<TableRowDto<Aa79bExpedienteRelacionado>> reorderSelection = this.aa79bExpedienteWsDao
					.reorderSelectionFindExpedientesRelacionados(jqGridRequestDto, false);
			return new JQGridResponseDto<Aa79bExpedienteRelacionado>(jqGridRequestDto, recordNum, lista,
					reorderSelection);
		}

		if (jqGridRequestDto != null) {
			return new JQGridResponseDto<Aa79bExpedienteRelacionado>(jqGridRequestDto, recordNum, lista);
		} else {
			return new JQGridResponseDto<Aa79bExpedienteRelacionado>(new JQGridRequestDto(), recordNum, lista);
		}
	}

	@Override
	public List<Aa79bExpedienteRelacionado> obtenerExpedientesSeleccionados(Solicitante solicitante,
			String selectedIds) {
		return this.aa79bExpedienteWsDao.obtenerExpedientesSeleccionados(solicitante, selectedIds);
	}

	@Override
	public JQGridResponseDto<Aa79bExpediente> consultarExpReceptores(Solicitante solicitante, Aa79bExpediente bean,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		@SuppressWarnings("unchecked")
		List<Aa79bExpediente> listaExpedientes = (List<Aa79bExpediente>) this.aa79bExpedienteWsDao
				.consultarExpReceptores(solicitante, bean, jqGridRequestDto, false, false);

		Long recordNum = 0L;

		if (listaExpedientes != null && !listaExpedientes.isEmpty()) {
			recordNum = (Long) this.aa79bExpedienteWsDao.consultarExpReceptores(solicitante, bean, jqGridRequestDto,
					false, true);
		}

		if (jqGridRequestDto != null) {
			return new JQGridResponseDto<Aa79bExpediente>(jqGridRequestDto, recordNum, listaExpedientes);
		} else {
			return new JQGridResponseDto<Aa79bExpediente>(new JQGridRequestDto(), recordNum, listaExpedientes);
		}

	}

	@Override
	public Integer obtenerIdRequerimiento(EntradaDatosExpediente datosExpediente) {
		return this.aa79bExpedienteWsDao.obtenerIdRequerimiento(datosExpediente);
	}

	@Override
	public Aa79bSalidaDatosPresupuestoFacturacion datosFacturacionInterpretacion(
			EntradaDatosExpediente datosExpediente) {
		return this.aa79bExpedienteWsDao.datosFacturacionInterpretacion(datosExpediente);
	}

	@Override
	public List<Aa79bEntidadContacto> obtenerDatosEntidadContactoFacturacionInterpretacion(
			EntradaDatosExpediente datosExpediente) {
		List<Aa79bEntidadContacto> listaEntidadContacto = this.aa79bExpedienteWsDao
				.obtenerDatosEntidadContactoFacturacionInterpretacion(datosExpediente);
		this.obtenerDireccionesNora(listaEntidadContacto);
		return listaEntidadContacto;
	}

	@Override
	public Aa79bSalidaDatosPresupuestoFacturacion datosFacturacionTraduccion(EntradaDatosExpediente datosExpediente) {
		return this.aa79bExpedienteWsDao.datosFacturacionTraduccion(datosExpediente);
	}

	@Override
	public List<Aa79bEntidadContacto> obtenerDatosEntidadContactoFacturacionTraduccion(
			EntradaDatosExpediente datosExpediente) {
		List<Aa79bEntidadContacto> listaEntidadContacto = this.aa79bExpedienteWsDao
				.obtenerDatosEntidadContactoFacturacionTraduccion(datosExpediente);
		this.obtenerDireccionesNora(listaEntidadContacto);
		return listaEntidadContacto;
	}

	private void obtenerDireccionesNora(List<Aa79bEntidadContacto> listaEntidadContacto) {
		if (listaEntidadContacto != null && !listaEntidadContacto.isEmpty()) {
			for (Aa79bEntidadContacto entidadContacto : listaEntidadContacto) {
				if (Aa79bExpedienteWSUtils.tieneDireccionNora(entidadContacto.getEntidad().getDirNoraEntidad())) {
					DireccionNora direccionNora = this.direccionNoraService
							.find(entidadContacto.getEntidad().getDirNoraEntidad().getDireccion());
					DireccionNora direccion = new DireccionNora();
					direccion = Utils.obtenerDireccion(direccionNora);
					entidadContacto.getEntidad().getDirNoraEntidad().setDireccion(direccion);
				}
			}
		}
	}

	@Override
	public List<Aa79bSolaskides> findSolaskides(Aa79bInformes aa79bInformeSolaskides, Locale locale) {
		return this.aa79bExpedienteWsDao.findSolaskides(aa79bInformeSolaskides, locale);
	}

	@Override
	public List<Aa79bInformeSolicitudes> findInformeSolicitudes(Aa79bInformes aa79bInformes, Locale locale) {
		return this.aa79bExpedienteWsDao.findInformeSolicitudes(aa79bInformes, locale);
	}

}
