package com.ejie.aa79b.service;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.dao.ExpedienteDao;
import com.ejie.aa79b.dao.ExpedientesConfidencialesDao;
import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.dao.RegistroAccionesDao;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.RegistroAcciones;
import com.ejie.aa79b.model.enums.AccionesEnum;
import com.ejie.aa79b.model.enums.PuntosMenuEnum;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

/**
 * ExpedientesConfidencialesServiceImpl, 02-ene-2019 12:05:50.
 * 
 * @author mrodriguez
 */

@Service(value = "expedientesConfidencialesService")
public class ExpedientesConfidencialesServiceImpl extends GenericoServiceImpl<Expediente>
		implements ExpedientesConfidencialesService {

	@Autowired
	private ExpedientesConfidencialesDao expedientesConfidencialesDao;
	@Autowired()
	private ExpedienteDao expedienteDao;
	@Autowired()
	private RegistroAccionesDao registroAccionesDao;
	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	@Override
	protected GenericoDao<Expediente> getDao() {
		return this.expedientesConfidencialesDao;
	}

	@Override()
	public JQGridResponseDto<Expediente> busquedaexpconf(Expediente filter, String dni,
			JQGridRequestDto jqGridRequestDto, boolean isCount) {
		@SuppressWarnings("unchecked")
		List<Expediente> listaExpedientes = (List<Expediente>) this.expedientesConfidencialesDao.busquedaexpconf(filter,
				dni, jqGridRequestDto, false, false);

		Long recordNum = 0L;

		if (listaExpedientes != null && !listaExpedientes.isEmpty()) {
			recordNum = (Long) this.expedientesConfidencialesDao.busquedaexpconf(filter, dni, jqGridRequestDto, false,
					true);
		}

		if (jqGridRequestDto != null) {
			return new JQGridResponseDto<Expediente>(jqGridRequestDto, recordNum, listaExpedientes);
		} else {
			return new JQGridResponseDto<Expediente>(new JQGridRequestDto(), recordNum, listaExpedientes);
		}
	}

	@Override()
	public void registrarAccionDesencripFich(Integer idDoc, Long anyo, Integer numExp) {
		RegistroAcciones reg = new RegistroAcciones();
		reg.setIdPuntoMenu(PuntosMenuEnum.SERVICIO_FICHEROS_CONFIDENCIALES.getValue());
		reg.setIdAccion(AccionesEnum.ALTA.getValue());

		Locale locale = new Locale(Constants.LANG_EUSKERA);
		StringBuilder observ = new StringBuilder();
		observ.append(this.msg.getMessage("mensaje.ficheroDesencriptado", null, locale)).append(" \n");
		observ.append("id").append("=").append(idDoc);
		reg.setObserv(observ.toString());
		reg.setAnyo(anyo);
		reg.setNumExp(numExp);
		reg.setIdEstadoBitacora(expedienteDao.findEstadoBitacoraExp(anyo, numExp));

		this.registroAccionesDao.add(reg);
	}

}
