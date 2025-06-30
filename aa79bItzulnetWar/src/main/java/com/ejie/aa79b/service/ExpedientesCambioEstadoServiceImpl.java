package com.ejie.aa79b.service;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.dao.BitacoraExpedienteDao;
import com.ejie.aa79b.dao.ExpedienteDao;
import com.ejie.aa79b.dao.ExpedientesCambioEstadoDao;
import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.dao.RegistroAccionesDao;
import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.BitacoraSolicitante;
import com.ejie.aa79b.model.EstadosExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.FasesExpediente;
import com.ejie.aa79b.model.Leyenda;
import com.ejie.aa79b.model.RegistroAcciones;
import com.ejie.aa79b.model.enums.AccionBitacoraEnum;
import com.ejie.aa79b.model.enums.AccionesEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.PuntosMenuEnum;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

/**
 * ExpedientesCambioEstadoServiceImpl
 * 
 * @author mrodriguez
 */

@Service(value = "expedienteCambioEstadoService")
public class ExpedientesCambioEstadoServiceImpl extends GenericoServiceImpl<Expediente>
		implements ExpedientesCambioEstadoService {

	@Autowired()
	private ExpedientesCambioEstadoDao expedientesCambioEstadoDao;
	@Autowired()
	private BitacoraExpedienteDao bitacoraExpedienteDao;
	@Autowired()
	private ExpedienteDao expedienteDao;
	@Autowired()
	private RegistroAccionesDao registroAccionesDao;
	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	public ExpedientesCambioEstadoServiceImpl() {
		// Constructor
	}

	@Override
	protected GenericoDao<Expediente> getDao() {
		return this.expedientesCambioEstadoDao;
	}

	@Override
	public JQGridResponseDto<Expediente> findExpedientesCambioEstado(Expediente bean,
			JQGridRequestDto jqGridRequestDto) {
		@SuppressWarnings("unchecked")
		List<Expediente> listaT = (List<Expediente>) this.expedientesCambioEstadoDao.findExpedientesCambioEstado(bean,
				jqGridRequestDto, false, false);

		Long recordNum = (Long) this.expedientesCambioEstadoDao.findExpedientesCambioEstado(bean, jqGridRequestDto,
				false, true);

		return new JQGridResponseDto<Expediente>(jqGridRequestDto, recordNum, listaT);
	}

	@Override
	public long comprobarExpFacturado(Long anyo, Integer numExp) {
		return this.expedientesCambioEstadoDao.comprobarExpFacturado(anyo, numExp);
	}

	@Override
	public void cambiarEstadoExp(Expediente expediente) {
		// Registro de acciones (Tabla 43)
		this.registrarAcciones(expediente);

		// Actualizar la bitacora (Tabla 59)
		BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
		bitacoraExpediente.setAnyo(expediente.getAnyo());
		bitacoraExpediente.setNumExp(expediente.getNumExp());
		EstadosExpediente estadosExpediente = new EstadosExpediente();
		estadosExpediente.setId(expediente.getBitacoraExpediente().getEstadoExp().getId());
		bitacoraExpediente.setEstadoExp(estadosExpediente);
		FasesExpediente fasesExpediente = new FasesExpediente();
		fasesExpediente.setId(expediente.getBitacoraExpediente().getFaseExp().getId());
		bitacoraExpediente.setFaseExp(fasesExpediente);

		BitacoraExpediente bitacoraExp = this.bitacoraExpedienteDao.add(bitacoraExpediente);
		expediente.setEstadoBitacora(bitacoraExp.getIdEstadoBitacora());

		// Actualizar el estado de la bitacora del expediente (Tabla 51)
		this.expedienteDao.updateIdEstadoBitacora(expediente);

		if (EstadoExpedienteEnum.EN_CURSO.getValue() == expediente.getBitacoraExpediente().getEstadoExp().getId()
				|| EstadoExpedienteEnum.CERRADO.getValue() == expediente.getBitacoraExpediente().getEstadoExp()
						.getId()) {

			int accionBitacora;
			if (EstadoExpedienteEnum.EN_CURSO.getValue() == expediente.getBitacoraExpediente().getEstadoExp().getId()) {
				accionBitacora = AccionBitacoraEnum.EXP_EN_CURSO.getValue();
			} else {
				// Estado expediente = CERRADO
				accionBitacora = AccionBitacoraEnum.EXP_CERRADO.getValue();
			}

			// Actualizar bitacora solicitante (Tabla 79)
			BitacoraSolicitante bitacoraSolicitante = new BitacoraSolicitante();
			bitacoraSolicitante.setAnyo(expediente.getAnyo());
			bitacoraSolicitante.setNumExp(expediente.getNumExp());
			bitacoraSolicitante.setUsuario(Constants.IZO);
			this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitante, accionBitacora);
		}

	}

	/**
	 * Se registra una nueva accion en la tabla 43 (Registro de acciones)
	 * 
	 * @param bean
	 *            Expediente
	 */
	private void registrarAcciones(Expediente bean) {
		RegistroAcciones reg = new RegistroAcciones();
		reg.setIdPuntoMenu(PuntosMenuEnum.SERVICIO_CAMBIO_ESTADO_EXP.getValue());
		reg.setIdAccion(AccionesEnum.MODIFICACION.getValue());

		Locale locale = new Locale(Constants.LANG_EUSKERA);
		StringBuilder observ = new StringBuilder();
		observ.append(this.msg.getMessage("mensaje.cambioEstadoExp", null, locale)).append(" \n");
		reg.setAnyo(bean.getAnyo());
		reg.setNumExp(bean.getNumExp());
		reg.setIdEstadoBitacora(expedienteDao.findEstadoBitacoraExp(bean.getAnyo(), bean.getNumExp()));
		reg.setObserv(observ.toString());
		this.registroAccionesDao.add(reg);
	}

	@Override
	public List<Leyenda> findConfLeyenda(Leyenda bean) {
		return this.expedientesCambioEstadoDao.findConfLeyenda(bean);
	}

}
