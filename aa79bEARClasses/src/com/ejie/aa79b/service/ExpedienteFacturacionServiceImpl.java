package com.ejie.aa79b.service;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.dao.BitacoraExpedienteDao;
import com.ejie.aa79b.dao.ContactoFacturacionExpedienteDao;
import com.ejie.aa79b.dao.CuantiaRevDao;
import com.ejie.aa79b.dao.CuantiaTradDao;
import com.ejie.aa79b.dao.DatosFacturacionExpedienteDao;
import com.ejie.aa79b.dao.DatosTareaTradosDao;
import com.ejie.aa79b.dao.DireccionNoraDao;
import com.ejie.aa79b.dao.ExpedienteDao;
import com.ejie.aa79b.dao.ExpedienteFacturacionDao;
import com.ejie.aa79b.dao.ExpedienteTradRevDao;
import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.dao.OrdenPreciosDao;
import com.ejie.aa79b.dao.RegistroAccionesDao;
import com.ejie.aa79b.dao.TareasDao;
import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.BitacoraSolicitante;
import com.ejie.aa79b.model.ContactoFacturacionExpediente;
import com.ejie.aa79b.model.CuantiaRev;
import com.ejie.aa79b.model.CuantiaTrad;
import com.ejie.aa79b.model.DatosFacturacionExpediente;
import com.ejie.aa79b.model.DatosFacturacionExpedienteInterpretacion;
import com.ejie.aa79b.model.DatosTareaTrados;
import com.ejie.aa79b.model.DetalleDatosFacturacion;
import com.ejie.aa79b.model.DireccionNora;
import com.ejie.aa79b.model.EstadosExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteFacturacion;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.FasesExpediente;
import com.ejie.aa79b.model.ListaExpediente;
import com.ejie.aa79b.model.OrdenPrecios;
import com.ejie.aa79b.model.RegistroAcciones;
import com.ejie.aa79b.model.RupTableMultiselectionModel;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TarifExpTradRev;
import com.ejie.aa79b.model.enums.AccionBitacoraEnum;
import com.ejie.aa79b.model.enums.AccionesEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.FaseExpedienteEnum;
import com.ejie.aa79b.model.enums.PuntosMenuEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum;
import com.ejie.aa79b.utils.Utils;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service(value = "expedienteFacturacionService")
public class ExpedienteFacturacionServiceImpl extends GenericoServiceImpl<ExpedienteFacturacion>
		implements ExpedienteFacturacionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExpedienteFacturacionServiceImpl.class);
	@Autowired()
	private Properties appConfiguration;
	@Autowired()
	private ExpedienteFacturacionDao expedienteFacturacionDao;
	@Autowired()
	private DatosTareaTradosDao datosTareaTradosDao;
	@Autowired()
	private ExpedienteDao expedienteDao;
	@Autowired()
	private DatosFacturacionExpedienteDao datosFacturacionExpedienteDao;
	@Autowired()
	private OrdenPreciosDao ordenPreciosDao;
	@Autowired()
	private CuantiaRevDao cuantiaRevDao;
	@Autowired()
	private CuantiaTradDao cuantiaTradDao;
	@Autowired()
	private TarifExpTradRevService tarifExpTradRevService;
	@Autowired()
	private ExpedienteTradRevDao expedienteTradRevDao;
	@Autowired()
	private BitacoraExpedienteDao bitacoraExpedienteDao;
	@Autowired()
	private DireccionNoraDao direccionNoraDao;
	@Autowired()
	private ContactoFacturacionExpedienteDao contactoFacturacionExpedienteDao;
	@Autowired()
	private TareasDao tareasDao;
	@Autowired()
	private DireccionNoraService direccionNoraService;
	@Autowired()
	private RegistroAccionesDao registroAccionesDao;
	@Autowired()
	private ReloadableResourceBundleMessageSource msg;
	private static final String COD_APLICACION_W05U = "APLIC_ORIGEN_051";
	private static final String WSDL = "webservice.evento.wsdl";
	private static final String NAME_SPACE_URI = "qname.evento.namespaceURI";
	private static final String LOCAL_PART = "qname.evento.localpart";

	public ExpedienteFacturacionServiceImpl() {
		// Constructor
	}

	@Override
	protected GenericoDao<ExpedienteFacturacion> getDao() {
		return this.expedienteFacturacionDao;
	}

	@Override
	public JQGridResponseDto<ExpedienteFacturacion> confeccionarFacturaExpedientesFilter(
			ExpedienteFacturacion filterExpedienteFacturacion, JQGridRequestDto jqGridRequestDto, boolean startsWith) {
		List<ExpedienteFacturacion> listaT = this.expedienteFacturacionDao
				.confeccionarFacturaExpedientesFilter(filterExpedienteFacturacion, jqGridRequestDto, false);
		Long recordNum = this.expedienteFacturacionDao
				.confeccionarFacturaExpedientesFilterCount(filterExpedienteFacturacion, false);
		return new JQGridResponseDto<ExpedienteFacturacion>(jqGridRequestDto, recordNum, listaT);
	}

	@Override
	public JQGridResponseDto<ExpedienteFacturacion> revisionFacturacionExpedientesFilter(
			ExpedienteFacturacion filterExpedienteFacturacion, JQGridRequestDto jqGridRequestDto) {
		@SuppressWarnings("unchecked")
		List<ExpedienteFacturacion> listaT = (List<ExpedienteFacturacion>) this.expedienteFacturacionDao
				.revisionFacturacionExpedientesFilter(filterExpedienteFacturacion, jqGridRequestDto, false, false);

		Long recordNum = (Long) this.expedienteFacturacionDao
				.revisionFacturacionExpedientesFilter(filterExpedienteFacturacion, jqGridRequestDto, false, true);

		return new JQGridResponseDto<ExpedienteFacturacion>(jqGridRequestDto, recordNum, listaT);
	}

	@Override
	public void finRevisionFactura(DetalleDatosFacturacion detalleDatosFacturacion) {
		Long anyo = detalleDatosFacturacion.getExpediente().getExpedienteTradRev().getAnyo();
		Integer numExp = detalleDatosFacturacion.getExpediente().getExpedienteTradRev().getNumExp();

		this.expedienteTradRevDao
				.updateRevisionDatosFacturacion(detalleDatosFacturacion.getExpediente().getExpedienteTradRev());
		if (Constants.SI.equals(detalleDatosFacturacion.getExpediente().getExpedienteTradRev().getIndFacturable())) {
			detalleDatosFacturacion.getExpediente().getExpedienteTradRev().getDatosFacturacionExpediente()
					.setIndRevisado(Constants.SI);
			int update = this.datosFacturacionExpedienteDao
					.updateReturnInt(detalleDatosFacturacion.getExpediente().getExpedienteTradRev());
			if (Constants.CERO == update) {
				this.datosFacturacionExpedienteDao.add(detalleDatosFacturacion.getExpediente().getExpedienteTradRev());
			}
		}

		accionesFinalizarRevDatosFact(anyo, numExp);

	}

	/**
	 * @param anyo
	 * @param numExp
	 */
	private void accionesFinalizarRevDatosFact(Long anyo, Integer numExp) {
		BitacoraExpediente bitacoraExpedienteFilter = new BitacoraExpediente();
		bitacoraExpedienteFilter.setAnyo(anyo);
		bitacoraExpedienteFilter.setNumExp(numExp);

		EstadosExpediente estadoExp = new EstadosExpediente();
		estadoExp.setId(new Long(EstadoExpedienteEnum.CERRADO.getValue()));
		bitacoraExpedienteFilter.setEstadoExp(estadoExp);

		FasesExpediente faseExp = new FasesExpediente();
		faseExp.setId(new Long(FaseExpedienteEnum.CERRADO.getValue()));
		bitacoraExpedienteFilter.setFaseExp(faseExp);
		BitacoraExpediente bitacoraExpediente = this.bitacoraExpedienteDao.add(bitacoraExpedienteFilter);

		Expediente expFilter = new Expediente();
		expFilter.setAnyo(anyo);
		expFilter.setNumExp(numExp);
		expFilter.setBitacoraExpediente(bitacoraExpediente);
		this.expedienteDao.modificarEstado(expFilter);

		BitacoraSolicitante bitacoraSolicitante = new BitacoraSolicitante();
		bitacoraSolicitante.setAnyo(bitacoraExpediente.getAnyo());
		bitacoraSolicitante.setNumExp(bitacoraExpediente.getNumExp());
		bitacoraSolicitante.setUsuario(Constants.IZO);
		this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitante,
				AccionBitacoraEnum.EXP_CERRADO.getValue());

		this.registrarAccionEstadoExp(anyo, numExp, bitacoraExpediente.getIdEstadoBitacora(), "mensaje.expCerrado");

		Tareas tareasFilter = new Tareas();
		tareasFilter.setAnyo(bitacoraExpedienteFilter.getAnyo());
		tareasFilter.setNumExp(bitacoraExpedienteFilter.getNumExp());
		Long tareas = this.tareasDao.findAllCountSinEjecutar(tareasFilter);
		if (Constants.CERO == (int) (long) tareas) {
			estadoExp.setId(new Long(EstadoExpedienteEnum.FINALIZADO.getValue()));
			bitacoraExpediente.setEstadoExp(estadoExp);

			faseExp.setId(new Long(FaseExpedienteEnum.FINALIZADO.getValue()));
			bitacoraExpediente.setFaseExp(faseExp);
			BitacoraExpediente bitacoraExpFinal = this.bitacoraExpedienteDao.add(bitacoraExpediente);

			Expediente expFilterFinal = new Expediente();
			expFilterFinal.setAnyo(anyo);
			expFilterFinal.setNumExp(numExp);
			expFilterFinal.setBitacoraExpediente(bitacoraExpFinal);
			this.expedienteDao.modificarEstado(expFilterFinal);

			this.registrarAccionEstadoExp(anyo, numExp, bitacoraExpediente.getIdEstadoBitacora(),
					"mensaje.expFinalizado");
		}
	}

	@Override
	public void finRevisionContactosEntidades(List<ContactoFacturacionExpediente> contactoFactuExpList) {
		for (ContactoFacturacionExpediente bean : contactoFactuExpList) {
			DireccionNora direccionNora = this.direccionNoraDao
					.obtenerDatosX54JNoraConDirNora(bean.getEntidadSolicitante().getDireccion().getDirNora());
			if (direccionNora != null) {
				DireccionNora direccionEntidad = this.direccionNoraDao.add(direccionNora);
				bean.getEntidadSolicitante().getDireccion().setDirNora(direccionEntidad.getDirNora());
			}
			int updateRst = this.contactoFacturacionExpedienteDao.updateContactosEntidadesFacturacion(bean);
			if (Constants.CERO == updateRst) {
				this.contactoFacturacionExpedienteDao.addContactosEntidadesFacturacion(bean);
			}
		}
	}

	@Override
	public DetalleDatosFacturacion getDatosDetalle(Expediente expedienteFilter) {
		return this.getDatosFacturacion(expedienteFilter);
	}

	private DetalleDatosFacturacion getDatosFacturacion(Expediente expedienteFilter) {
		DetalleDatosFacturacion salida = new DetalleDatosFacturacion();
		Expediente expediente = this.expedienteDao.find(expedienteFilter);
		DatosTareaTrados trados = this.datosTareaTradosDao.findDatosTareaTrados(expedienteFilter);

		ExpedienteFacturacion expedienteFacturacion = new ExpedienteFacturacion();
		expedienteFacturacion.setAnyo(expedienteFilter.getAnyo());
		expedienteFacturacion.setNumExp(expedienteFilter.getNumExp());
		ExpedienteTradRev datosTrav = this.datosFacturacionExpedienteDao.find(expediente.getExpedienteTradRev());

		OrdenPrecios ordenPreciosFilter = new OrdenPrecios();
		OrdenPrecios ordenPreciosEnVigor = new OrdenPrecios();

		if (datosTrav != null) {
			expediente.getExpedienteTradRev().setDatosFacturacionExpediente(datosTrav.getDatosFacturacionExpediente());
			ordenPreciosFilter.setId(datosTrav.getDatosFacturacionExpediente().getIdOrden());
			if (Constants.CEROLONG == ordenPreciosFilter.getId()) {
				ordenPreciosEnVigor.setIndVigor(Constants.SI);
				ordenPreciosEnVigor = this.ordenPreciosDao.getOrdenPreciosVigente(ordenPreciosEnVigor);
				if (ordenPreciosEnVigor != null) {
					ordenPreciosFilter.setId(ordenPreciosEnVigor.getId());
				}
			}
		} else {
			ordenPreciosEnVigor.setIndVigor(Constants.SI);
			ordenPreciosEnVigor = this.ordenPreciosDao.getOrdenPreciosVigente(ordenPreciosEnVigor);
			if (ordenPreciosEnVigor != null) {
				ordenPreciosFilter.setId(ordenPreciosEnVigor.getId());
			}
		}

		if (ordenPreciosEnVigor != null) {
			OrdenPrecios ordenPrecios = this.getOrdenPrecios(ordenPreciosFilter);
			salida.setOrdenPrecios(ordenPrecios);
		}

		expediente.getExpedienteTradRev().setPresupuestoAceptado(this.expedienteTradRevDao
				.isExpConPptoAceptado(expedienteFilter.getAnyo(), expedienteFilter.getNumExp()));
		expediente.getExpedienteTradRev().setTradosExp(trados);
		salida.setExpediente(expediente);

		return salida;
	}

	@Override
	public JQGridResponseDto<ExpedienteFacturacion> borradorFacturaTablaFilter(
			ExpedienteFacturacion filterBorradorFacturaTabla, JQGridRequestDto jqGridRequestDto,
			String sListaExpedientes, Boolean startsWith) throws IOException {
		// obtenemos los expedientes seleccionados del String
		ObjectMapper mapper = new ObjectMapper();
		List<Expediente> expedientesList = mapper.readValue(sListaExpedientes, new TypeReference<List<Expediente>>() {
		});
		List<ExpedienteFacturacion> listaT = this.expedienteFacturacionDao
				.borradorFacturaTablaFilter(filterBorradorFacturaTabla, jqGridRequestDto, expedientesList, false);

		Long recordNum = this.expedienteFacturacionDao.borradorFacturaTablaFilterCount(filterBorradorFacturaTabla,
				expedientesList, false);
		return new JQGridResponseDto<ExpedienteFacturacion>(jqGridRequestDto, recordNum, listaT);
	}

	@Override
	public ExpedienteFacturacion obtenerDatosCalculoExpedienteFacturacion(ExpedienteFacturacion expedienteFacturacion,
			boolean filtroEstado) {
		ExpedienteFacturacion expedienteFacturacionRes = new ExpedienteFacturacion();
		expedienteFacturacionRes = this.expedienteFacturacionDao
				.obtenerDatosCalculoExpedienteFacturacion(expedienteFacturacion, filtroEstado);
		// DIRNORA - aparte del String direccion tambien necesitamos el codProv
		if (TipoExpedienteGrupoEnum.INTERPRETACION.getCode().equals(expedienteFacturacion.getIdTipoExpediente())) {
			DireccionNora direccion = new DireccionNora();
			if (expedienteFacturacionRes.getExpedienteInterpretacion() != null) {
				direccion.setDirNora(expedienteFacturacionRes.getExpedienteInterpretacion().getDirNora().getDirNora());
				direccion = this.direccionNoraService.find(direccion);
				expedienteFacturacionRes.getExpedienteInterpretacion().setDirNora(Utils.obtenerDireccion(direccion));
			}
		}
		return expedienteFacturacionRes;
	}

	@Override
	public JQGridResponseDto<ExpedienteFacturacion> calculoExpedienteFacturacionTablaFilter(
			ExpedienteFacturacion expedienteFacturacionFilter, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		List<ExpedienteFacturacion> listaT = this.expedienteFacturacionDao
				.calculoExpedienteFacturacionTablaFilter(expedienteFacturacionFilter, jqGridRequestDto, startsWith);
		Long recordNum = this.expedienteFacturacionDao
				.calculoExpedienteFacturacionTablaFilterCount(expedienteFacturacionFilter, false);
		return new JQGridResponseDto<ExpedienteFacturacion>(jqGridRequestDto, recordNum, listaT);
	}

	@Override
	public void realizarCalculoInterpretacion(ExpedienteFacturacion expedienteFacturacion) {
		this.expedienteFacturacionDao.realizarCalculoInterpretacion(expedienteFacturacion);
	}

	private OrdenPrecios getOrdenPrecios(OrdenPrecios ordenPreciosFilter) {
		OrdenPrecios ordenPrecios = this.ordenPreciosDao.find(ordenPreciosFilter);

		TarifExpTradRev tarifExpTradRevFilter = new TarifExpTradRev();
		tarifExpTradRevFilter.setIdOrden(ordenPreciosFilter.getId());
		TarifExpTradRev tarifExpTradRev = this.tarifExpTradRevService.find(tarifExpTradRevFilter);

		if (ordenPrecios != null) {
			ordenPrecios.setTarifExpTradRev(tarifExpTradRev);

			CuantiaRev cuantiaRevFilter = new CuantiaRev();
			cuantiaRevFilter.setIdOrden(ordenPrecios.getId());
			List<CuantiaRev> cuantiaRevList = this.cuantiaRevDao.findAllLike(cuantiaRevFilter, null, false);
			ordenPrecios.setListaCuantiaRev(cuantiaRevList);

			CuantiaTrad cuantiaTradFilter = new CuantiaTrad();
			cuantiaTradFilter.setIdOrden(ordenPrecios.getId());
			List<CuantiaTrad> cuantiaTradList = this.cuantiaTradDao.findAllLike(cuantiaTradFilter, null, false);
			ordenPrecios.setListaCuantiaTrad(cuantiaTradList);
		}
		return ordenPrecios;
	}

	@Override
	public JQGridResponseDto<ExpedienteFacturacion> actDatosFacturacionExpedientesFilter(
			ExpedienteFacturacion filterExpedienteFacturacion, JQGridRequestDto jqGridRequestDto) {
		@SuppressWarnings("unchecked")
		List<ExpedienteFacturacion> listaT = (List<ExpedienteFacturacion>) this.expedienteFacturacionDao
				.actDatosFacturacionExpedientesFilter(filterExpedienteFacturacion, jqGridRequestDto, false, false);

		Long recordNum = (Long) this.expedienteFacturacionDao
				.actDatosFacturacionExpedientesFilter(filterExpedienteFacturacion, jqGridRequestDto, false, true);

		return new JQGridResponseDto<ExpedienteFacturacion>(jqGridRequestDto, recordNum, listaT);
	}

	@Override
	public long comprobarDatosFactExpInter(Long anyo, Integer numExp) {
		return this.expedienteFacturacionDao.comprobarDatosFactExpInter(anyo, numExp);
	}

	@Override
	public void finRevDatosFact(DetalleDatosFacturacion detalleDatosFacturacion) {
		// Actualizamos los datos de facturación para un expediente de
		// interpretación (tabla A3)
		Long anyo = detalleDatosFacturacion.getExpediente().getAnyo();
		Integer numExp = detalleDatosFacturacion.getExpediente().getNumExp();
		detalleDatosFacturacion.getExpediente().getExpedienteInterpretacion().getDatosFacturacion()
				.setIndRevisado(Constants.SI);
		this.datosFacturacionExpedienteDao.updateDatosFactInter(
				detalleDatosFacturacion.getExpediente().getExpedienteInterpretacion(), anyo, numExp);

		this.registrarAccionActDatosFact(anyo, numExp,
				detalleDatosFacturacion.getExpediente().getExpedienteInterpretacion().getDatosFacturacion());

		accionesFinalizarRevDatosFact(anyo, numExp);

	}

	@Override
	public void finRevContactosEntidadesExpInter(List<ContactoFacturacionExpediente> contactoFactuExpList) {
		for (ContactoFacturacionExpediente bean : contactoFactuExpList) {
			DireccionNora direccionNora = this.direccionNoraDao
					.obtenerDatosX54JNoraConDirNora(bean.getEntidadSolicitante().getDireccion().getDirNora());
			DireccionNora direccionEntidad = this.direccionNoraDao.add(direccionNora);
			bean.getEntidadSolicitante().getDireccion().setDirNora(direccionEntidad.getDirNora());
			int updateRst = this.contactoFacturacionExpedienteDao.updateContactosEntidadesFactExpInter(bean);
			if (Constants.CERO == updateRst) {
				this.contactoFacturacionExpedienteDao.addContactosEntidadesFactExpInter(bean);
			}
		}
	}

	private void registrarAccionActDatosFact(Long anyo, Integer numExp,
			DatosFacturacionExpedienteInterpretacion datosFacturacion) {
		RegistroAcciones reg = new RegistroAcciones();
		reg.setIdPuntoMenu(PuntosMenuEnum.SERVICIO_ACT_DATOS_FACTURACION.getValue());
		reg.setIdAccion(AccionesEnum.ALTA.getValue());

		Locale locale = new Locale(Constants.LANG_EUSKERA);
		StringBuilder observ = new StringBuilder();
		observ.append(this.msg.getMessage("mensaje.actDatosFacturacion", null, locale)).append(" \n");
		observ.append(this.msg.getMessage("label.numInterpretes", null, locale)).append("=")
				.append(datosFacturacion.getNumInterpretes()).append(" \n");
		observ.append(this.msg.getMessage("label.horasRealesInterpPorInterprete", null, locale)).append("=")
				.append(datosFacturacion.getHorasInterpretacion()).append(" \n");
		reg.setObserv(observ.toString());
		reg.setAnyo(anyo);
		reg.setNumExp(numExp);
		reg.setIdEstadoBitacora(this.expedienteDao.findEstadoBitacoraExp(anyo, numExp));

		this.registroAccionesDao.add(reg);
	}

	public void registrarAccionEstadoExp(Long anyo, Integer numExp, Long idEstadoBitacora, String mensaje) {
		RegistroAcciones reg = new RegistroAcciones();
		reg.setIdPuntoMenu(PuntosMenuEnum.SERVICIO_ACT_DATOS_FACTURACION.getValue());
		reg.setIdAccion(AccionesEnum.ALTA.getValue());

		Locale locale = new Locale(Constants.LANG_EUSKERA);
		StringBuilder observ = new StringBuilder();
		observ.append(this.msg.getMessage(mensaje, null, locale)).append(" \n");
		reg.setObserv(observ.toString());
		reg.setAnyo(anyo);
		reg.setNumExp(numExp);
		reg.setIdEstadoBitacora(idEstadoBitacora);

		this.registroAccionesDao.add(reg);
	}

	@Override
	public DatosFacturacionExpediente obtenerImportesExpedientes(
			RupTableMultiselectionModel rupTableMultiselectionModel, String tipoExpediente) {
		if (String.valueOf(TipoExpedienteGrupoEnum.INTERPRETACION.getValue()).equals(tipoExpediente)) {
			return this.expedienteFacturacionDao.obtenerImportesExpedientes(rupTableMultiselectionModel, true);
		} else {
			return this.expedienteFacturacionDao.obtenerImportesExpedientes(rupTableMultiselectionModel, false);
		}
	}

	@Override
	public List<DatosFacturacionExpediente> obtenerExpedientesSeleccionados(
			RupTableMultiselectionModel rupTableMultiselectionModel, String tipoExpediente) {
		if (String.valueOf(TipoExpedienteGrupoEnum.INTERPRETACION.getValue()).equals(tipoExpediente)) {
			return this.expedienteFacturacionDao.obtenerExpedientesSeleccionados(rupTableMultiselectionModel, true);
		} else {
			return this.expedienteFacturacionDao.obtenerExpedientesSeleccionados(rupTableMultiselectionModel, false);
		}
	}

	@Override
	public Boolean comprobarEstadoPresupuestos(Integer estado, List<Expediente> expedientesSeleccionados) {
		return this.expedienteFacturacionDao.comprobarEstadoPresupuestos(estado, expedientesSeleccionados);
	}

	@Override
	public List<ExpedienteFacturacion> revisionFacturacionExpedientesFilterGetSelectedIds(
			ExpedienteFacturacion filterExpedienteFacturacion, JQGridRequestDto tableData) {
		return this.expedienteFacturacionDao
				.revisionFacturacionExpedientesFilterGetSelectedIds(filterExpedienteFacturacion, tableData);
	}

	@Override
	public Expediente obtenerDatosBoeExp(Expediente expediente) {
		return this.expedienteFacturacionDao.obtenerDatosBoeExp(expediente);
	}

	@Override
	public Integer guardarDatosBoe(Expediente expediente, String urlBoe, String indBoe) {
		return this.expedienteFacturacionDao.guardarDatosBoe(expediente, urlBoe, indBoe);
	}

	@Override
	public Integer guardarDatosBoe(ListaExpediente listaExpediente) {
		return this.expedienteFacturacionDao.guardarDatosBoe(listaExpediente);
	}

	@Override
	public List<ExpedienteFacturacion> actDatosFacturacionExpedientesFilterGetSelectedIds(
			ExpedienteFacturacion filterExpedienteFacturacion, JQGridRequestDto tableData) {
		return this.expedienteFacturacionDao
				.actDatosFacturacionExpedientesFilterGetSelectedIds(filterExpedienteFacturacion, tableData);
	}

	@Override
	public Integer comprobarExpSelBoeANo(ListaExpediente listaExpedientes) {
		return this.expedienteFacturacionDao.comprobarExpSelBoeANo(listaExpedientes);
	}

}
