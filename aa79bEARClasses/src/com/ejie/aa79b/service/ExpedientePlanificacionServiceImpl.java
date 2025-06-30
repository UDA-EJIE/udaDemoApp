package com.ejie.aa79b.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.dao.BitacoraExpedienteDao;
import com.ejie.aa79b.dao.DatosPagoProveedoresDao;
import com.ejie.aa79b.dao.DatosPersonaDao;
import com.ejie.aa79b.dao.DatosTareaTradosDao;
import com.ejie.aa79b.dao.ExpedienteDao;
import com.ejie.aa79b.dao.ExpedienteTradRevDao;
import com.ejie.aa79b.dao.GestorExpedienteDao;
import com.ejie.aa79b.dao.RegistroAccionesDao;
import com.ejie.aa79b.dao.TareasDao;
import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.BitacoraSolicitante;
import com.ejie.aa79b.model.CalendarioIcsModel;
import com.ejie.aa79b.model.DatosContacto;
import com.ejie.aa79b.model.DatosEstimacion;
import com.ejie.aa79b.model.DatosPagoProveedores;
import com.ejie.aa79b.model.DatosPlanificacion;
import com.ejie.aa79b.model.DatosTareaTrados;
import com.ejie.aa79b.model.DireccionNora;
import com.ejie.aa79b.model.EstadosExpediente;
import com.ejie.aa79b.model.ExpTareasResumen;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedientePlanificacion;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.FasesExpediente;
import com.ejie.aa79b.model.GestorExpediente;
import com.ejie.aa79b.model.Persona;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.RegistroAcciones;
import com.ejie.aa79b.model.ResumenGraficas;
import com.ejie.aa79b.model.SubsanacionExpediente;
import com.ejie.aa79b.model.enums.AccionBitacoraEnum;
import com.ejie.aa79b.model.enums.AccionesEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.FaseExpedienteEnum;
import com.ejie.aa79b.model.enums.PlanifExpedientesEnum;
import com.ejie.aa79b.model.enums.PlanifTareasEnum;
import com.ejie.aa79b.model.enums.PlanifTramitesEnum;
import com.ejie.aa79b.model.enums.PuntosMenuEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoRequerimientoEnum;
import com.ejie.aa79b.security.Usuario;
import com.ejie.aa79b.utils.DateUtils;
import com.ejie.aa79b.utils.SubsanacionUtils;
import com.ejie.aa79b.utils.Utils;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

/**
 * ExpedientePlanificacionServiceImpl.
 * 
 * @author aresua
 */
@Service(value = "expedientePlanificacionService")
public class ExpedientePlanificacionServiceImpl implements ExpedientePlanificacionService {

	@Autowired
	private ExpedienteDao expedienteDao;
	@Autowired
	private ExpedienteTradRevDao expedienteTradRevDao;
	@Autowired()
	private RegistroAccionesDao registroAccionesDao;
	@Autowired
	private DatosPersonaDao datosPersonaDao;
	@Autowired
	private BitacoraExpedienteDao bitacoraExpedienteDao;
	@Autowired
	private TareasDao tareasDao;
	@Autowired()
	private DatosTareaTradosDao datosTareaTradosDao;
	@Autowired()
	private DatosPagoProveedoresDao datosPagoProveedoresDao;
	@Autowired()
	private DireccionNoraService direccionNoraService;
	@Autowired()
	private PersonalIZOService personalIZOService;
	@Autowired()
	private GestorExpedienteDao gestorExpedienteDao;
	@Autowired()
	private DatosContactoService datosContactoService;
	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	@Override()
	public DatosPlanificacion obtenerDatosConfTareasExpInterpretacion(Expediente bean) {
		DatosPlanificacion datosPlanificacion = new DatosPlanificacion();

		if (bean != null) {
			Expediente expediente = this.expedienteDao.find(this.obtenerDatosConfiguracionTareas(bean));

			asignarDatosPlanificacion(datosPlanificacion, expediente);

			if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(expediente.getIdTipoExpediente())
					&& expediente.getExpedienteInterpretacion() != null) {

				datosPlanificacion.setFechaHoraInicio(expediente.getExpedienteInterpretacion().getFechaHoraIni());
				datosPlanificacion.setFechaHoraFin(expediente.getExpedienteInterpretacion().getFechaHoraFin());
				datosPlanificacion.setGrupoTrabajo(expediente.getGrupoTrabajo());
			}

		}

		return datosPlanificacion;
	}

	@Override()
	public DatosPlanificacion obtenerDatosConfTareasExpTradRev(Expediente bean) {
		DatosPlanificacion datosPlanificacion = new DatosPlanificacion();

		if (bean != null) {
			Expediente expediente = this.expedienteDao.findConTareasEntrega(this.obtenerDatosConfiguracionTareas(bean));

			asignarDatosPlanificacion(datosPlanificacion, expediente);

			if ((TipoExpedienteEnum.TRADUCCION.getValue().equals(expediente.getIdTipoExpediente())
					|| TipoExpedienteEnum.REVISION.getValue().equals(expediente.getIdTipoExpediente()))
					&& expediente.getExpedienteTradRev() != null) {

				DatosTareaTrados datosTareaTrados = obtenerDatosTareaTrados(expediente);

				datosPlanificacion.setDatosTareaTrados(datosTareaTrados);
				datosPlanificacion.setFechaHoraEntregaIZO(expediente.getExpedienteTradRev().getFechaHoraFinalIZO());
				datosPlanificacion.setIndTareasEntrega(expediente.getExpedienteTradRev().getIndTareasEntrega());
				datosPlanificacion.setGrupoTrabajo(expediente.getGrupoTrabajo());

				DatosPagoProveedores datosPagoProveedores = this.datosPagoProveedoresDao.getDatosTarea(expediente);
				datosPlanificacion.setDatosPagoProveedores(datosPagoProveedores);

			}

		}

		return datosPlanificacion;
	}

	/**
	 * @param datosPlanificacion
	 * @param expediente
	 */
	private void asignarDatosPlanificacion(DatosPlanificacion datosPlanificacion, Expediente expediente) {
		if (expediente.getBitacoraExpediente() != null) {
			if (expediente.getBitacoraExpediente().getEstadoExp() != null) {
				datosPlanificacion.setIdEstadoExpediente(expediente.getBitacoraExpediente().getEstadoExp().getId());
			}
			if (expediente.getBitacoraExpediente().getFaseExp() != null) {
				datosPlanificacion.setIdFaseExpediente(expediente.getBitacoraExpediente().getFaseExp().getId());
			}
		}
	}

	/**
	 * Obtiene los datos de tarea trados
	 * 
	 * @param expediente Expediente
	 * @return DatosTareaTrados
	 */
	private DatosTareaTrados obtenerDatosTareaTrados(Expediente expediente) {
		DatosTareaTrados datosTareaTrados = this.datosTareaTradosDao.findDatosTareaTrados(expediente);

		if (datosTareaTrados == null && expediente.getExpedienteTradRev() != null) {
			datosTareaTrados = new DatosTareaTrados();
			datosTareaTrados.setNumTotalPal(expediente.getExpedienteTradRev().getNumTotalPalIzo());
		}

		return datosTareaTrados;
	}

	/**
	 * Actualiza los datos del asignador y obtiene los datos del expediente. El dni
	 * del asignador solamente se graba
	 * 
	 * @param bean Expediente
	 * @return Expediente
	 */
	private Expediente obtenerDatosConfiguracionTareas(Expediente bean) {

		Expediente expediente = null;

		if (bean != null) {
			this.expedienteDao.updateAsignador(bean);

			// insertamos en la foto al asignador si no existe.
			boolean esta = this.datosPersonaDao.comprobarPersona(bean.getAsignador().getDni());
			if (!esta) {
				PersonalIZO personalIZO = new PersonalIZO();
				personalIZO.setDni(bean.getAsignador().getDni());
				PersonalIZO personalAInsertar = this.personalIZOService.find(personalIZO);
				this.expedienteDao.guardarPersona(personalAInsertar);
			}
			expediente = this.expedienteDao.find(bean);

		}

		return expediente;
	}

	@Override
	public List<List<ResumenGraficas>> getChartsData(String dni) {
		final List<List<ResumenGraficas>> lista = new ArrayList<List<ResumenGraficas>>();
		final List<ResumenGraficas> listaExpedientes = new ArrayList<ResumenGraficas>();
		final List<ResumenGraficas> listaTareas = new ArrayList<ResumenGraficas>();
		final List<ResumenGraficas> listaTramites = new ArrayList<ResumenGraficas>();

		// Se recupera el primer gráfico: Expedientes
		for (PlanifExpedientesEnum planifExpedientesEnum : PlanifExpedientesEnum.values()) {
			listaExpedientes.add(this.expedienteDao.getExpPlanificacionCount(dni, planifExpedientesEnum.getId()));
		}
		lista.add(listaExpedientes);
		// Se recupera el segundo gráfico: Tareas
		for (PlanifTareasEnum planifTareasEnum : PlanifTareasEnum.values()) {
			listaTareas.add(this.tareasDao.getTareasPlanificacionCount(dni, planifTareasEnum.getId()));
		}
		lista.add(listaTareas);
		// Se recupera el tercer gráfico: Trámites
		for (PlanifTramitesEnum planifTramitesEnum : PlanifTramitesEnum.values()) {
			listaTramites.add(this.expedienteDao.getTramitesPlanificacionCount(dni, planifTramitesEnum.getId()));
		}
		lista.add(listaTramites);

		return lista;
	}

	/**
	 * Filter method in the ExpTareasResumen table.
	 *
	 * @param expTareasResumen ExpTareasResumen
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith       Boolean
	 * @return JQGridResponseDto<ExpTareasResumen>
	 */
	@Override
	public JQGridResponseDto<ExpTareasResumen> filterResumen(ExpTareasResumen expTareasResumen,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith) {

		List<ExpTareasResumen> listaT = this.expedienteDao.findAllExpTareasResumen(expTareasResumen, jqGridRequestDto,
				false);
		Long recordNum = this.expedienteDao.findAllExpTareasResumenCount(expTareasResumen, false);

		return new JQGridResponseDto<ExpTareasResumen>(jqGridRequestDto, recordNum, listaT);
	}

	/**
	 * 
	 */
	@Override
	public String cambiarPrioridadExpediente(Long anyo, Integer numExp) {
		return this.expedienteDao.cambiarPrioridadExpediente(anyo, numExp);
	}

	/**
	 * Filter method in the DesgloseTareas table.
	 *
	 * @param expTareasResumen ExpTareasResumen
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith       Boolean
	 * @return JQGridResponseDto<ExpTareasResumen>
	 */
	@Override
	public JQGridResponseDto<ExpTareasResumen> filterDesgloseTareasExpedientes(String idsExpedientes,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith) {

		@SuppressWarnings("unchecked")
		List<ExpTareasResumen> listaT = (List<ExpTareasResumen>) this.expedienteDao
				.findAllTareasExpedientes(idsExpedientes, jqGridRequestDto, false, false);

		Long recordNum = 0L;

		if (listaT != null && !listaT.isEmpty()) {
			recordNum = (Long) this.expedienteDao.findAllTareasExpedientes(idsExpedientes, jqGridRequestDto, false,
					true);
		}

		if (jqGridRequestDto != null) {
			return new JQGridResponseDto<ExpTareasResumen>(jqGridRequestDto, recordNum, listaT);
		} else {
			return new JQGridResponseDto<ExpTareasResumen>(new JQGridRequestDto(), recordNum, listaT);
		}

	}

	@Override()
	public DatosEstimacion obtenerDatosEstimacion(Expediente bean) {
		DatosEstimacion datosEstimacion = new DatosEstimacion();

		if (bean != null) {
			Expediente expediente = this.expedienteDao.find(bean);

			if (expediente.getExpedienteTradRev() != null) {

				DatosTareaTrados datosTareaTrados = obtenerDatosTareaTrados(expediente);

				datosEstimacion.setDatosTareaTrados(datosTareaTrados);
				Date fechaInicio = new Date();
				datosEstimacion.setFechaInicio(fechaInicio);
				datosEstimacion.setHoraInicio(DateUtils.obtHoraFormateada(fechaInicio));
				datosEstimacion.setFechaFin(expediente.getExpedienteTradRev().getFechaFinalIzo());
				datosEstimacion.setHoraFin(expediente.getExpedienteTradRev().getHoraFinalIzo());
			}

		}

		return datosEstimacion;
	}

	@Override
	public List<CalendarioIcsModel> obtenerDatosExpedientes(String expedientesSeleccionados, Integer iTipoExp) {
		List<CalendarioIcsModel> lista = new ArrayList<CalendarioIcsModel>();
		lista = this.expedienteDao.obtenerDatosExpedientes(expedientesSeleccionados, iTipoExp);
		if (Constants.CERO == iTipoExp) {
			// interpretacion
			DireccionNora dirNora = new DireccionNora();
			for (CalendarioIcsModel expCal : lista) {
				dirNora.setDirNora(expCal.getDirNora().getDirNora());
				DireccionNora direccionNora = this.direccionNoraService.find(dirNora);
				DireccionNora direccion = new DireccionNora();
				direccion = Utils.obtenerDireccion(direccionNora);
				expCal.setDirNora(direccion);
			}
		} else {
			// tradRev
		}
		return lista;
	}

	@Override
	public void addRequerimiento(ExpedientePlanificacion bean) {

		SubsanacionExpediente subsanacionExpediente = new SubsanacionExpediente();
		Long idSub = this.expedienteDao.getNextVal("AA79B64Q00");
		subsanacionExpediente.setId(idSub);
		subsanacionExpediente.setTipoRequerimiento(new Long(TipoRequerimientoEnum.ACEPTACION_FECHA_FIN.getValue()));
		subsanacionExpediente.setFechaEntrega(bean.getFechaEntrega());
		subsanacionExpediente.setHoraEntrega(bean.getHoraEntrega());
		subsanacionExpediente.setFechaLimite(bean.getFechaLimite());
		subsanacionExpediente.setHoraLimite(bean.getHoraLimite());
		this.expedienteDao.registrarSubsanacion(subsanacionExpediente);

		Expediente expFilter = new Expediente();
		expFilter.setAnyo(bean.getAnyo());
		expFilter.setNumExp(bean.getNumExp());
		Expediente exp = this.expedienteDao.find(expFilter);

		BitacoraExpediente bExp = new BitacoraExpediente();
		Locale locale = new Locale(Constants.LANG_EUSKERA);
		bExp.setDatoAdic(this.msg.getMessage("label.aceptacionFechaEntrega", null, locale));

		bExp.setAnyo(bean.getAnyo());
		bExp.setNumExp(bean.getNumExp());

		SubsanacionExpediente subExp = new SubsanacionExpediente();
		subExp.setId(idSub);
		bExp.setSubsanacionExp(subExp);

		EstadosExpediente estado = new EstadosExpediente();
		estado.setId(Long.valueOf(EstadoExpedienteEnum.EN_CURSO.getValue()));
		bExp.setEstadoExp(estado);

		FasesExpediente fases = new FasesExpediente();
		fases.setId(Long.valueOf(FaseExpedienteEnum.PDTE_TRAM_CLIENTE.getValue()));
		bExp.setFaseExp(fases);

		bExp.setIdMotivoRechazo(null);
		exp.setBitacoraExpediente(bExp);

		this.bitacoraExpedienteDao.add(bExp);
		this.expedienteDao.modificarEstado(exp);

		// anyadimos bitacora solicitante
		final Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();

		DatosContacto datosContacto = new DatosContacto();
		datosContacto.setDni031(credentials.getNif());
		datosContacto = datosContactoService.findDatosContacto(datosContacto);

		BitacoraSolicitante bitacoraSolicitante = new BitacoraSolicitante();
		bitacoraSolicitante.setAnyo(bean.getAnyo());
		bitacoraSolicitante.setNumExp(bean.getNumExp());
		bitacoraSolicitante.setUsuario(Constants.IZO);
		this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitante,
				AccionBitacoraEnum.REQ_ACEPT_RECHAZ_FECHA_PROP_IZO.getValue());

		// Asignamos el asignador al expediente si es que no lo tiene ya
		Persona asignador = new Persona();
		asignador.setDni(credentials.getNif());
		exp.setAsignador(asignador);

		this.expedienteDao.updateAsignador(exp);

		// insertamos en la foto al asignador si no existe.
		boolean esta = this.datosPersonaDao.comprobarPersona(exp.getAsignador().getDni());
		if (!esta) {
			PersonalIZO personalIZO = new PersonalIZO();
			personalIZO.setDni(bean.getAsignador().getDni());
			PersonalIZO personalAInsertar = this.personalIZOService.find(personalIZO);
			this.expedienteDao.guardarPersona(personalAInsertar);
		}

		// enviar email
		GestorExpediente gestor = this.gestorExpedienteDao.getDatosContacto(expFilter);

		SubsanacionUtils subsanacionUtils = new SubsanacionUtils();

		subsanacionUtils.enviarEmailNegociarFecha(expFilter, subsanacionExpediente, gestor);
	}

	@Override
	public void modificarFechaEntrega(ExpedientePlanificacion bean) {

		ExpedienteTradRev expedienteTradRev = new ExpedienteTradRev();
		expedienteTradRev.setAnyo(bean.getAnyo());
		expedienteTradRev.setNumExp(bean.getNumExp());
		expedienteTradRev.setFechaFinalIzo(bean.getFechaEntrega());
		expedienteTradRev.setHoraFinalIzo(bean.getHoraEntrega());
		ExpedienteTradRev original = this.expedienteTradRevDao.find(expedienteTradRev);

		expedienteTradRevDao.updateFechaEntregaIZO(expedienteTradRev, bean.getFechaEntrega(), bean.getHoraEntrega());

		// registro de acciones
		RegistroAcciones reg = new RegistroAcciones();
		reg.setIdPuntoMenu(PuntosMenuEnum.PLANIFICACION_EXPEDIENTES.getValue());
		reg.setIdAccion(AccionesEnum.MODIFICACION.getValue());
		reg.setAnyo(bean.getAnyo());
		reg.setNumExp(bean.getNumExp());
		reg.setIdEstadoBitacora(this.expedienteDao.findEstadoBitacoraExp(bean.getAnyo(), bean.getNumExp()));
//
		Locale locale = new Locale(Constants.LANG_EUSKERA);
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.JAVA_DATE_FORMAT_EU);
		StringBuilder observ = new StringBuilder();
		observ.append(this.msg.getMessage("mensaje.expedienteModificado", null, locale)).append(". ");
		observ.append(msg.getMessage("label.fechaHoraEntregaIZO", null, locale)).append(": ");
		observ.append(sdf.format(original.getFechaFinalIzo())).append(" ").append(original.getHoraFinalIzo())
				.append(" -> ").append(sdf.format(expedienteTradRev.getFechaFinalIzo())).append(" ")
				.append(expedienteTradRev.getHoraFinalIzo());
		observ.append("\n");
		reg.setObserv(observ.toString());
		this.registroAccionesDao.add(reg);

	}

	@Override
	public Boolean getComprobarEstadoExpedientesEnCurso(List<String> expedientesSeleccionados) {
		return this.expedienteDao.getComprobarEstadoExpedientesEnCurso(expedientesSeleccionados);
	}

	@Override
	public Integer comprobarNecesidadFirmaDocs(Expediente expediente) {
		return this.expedienteDao.comprobarNecesidadFirmaDocs(expediente);
	}
}