package com.ejie.aa79b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.dao.ConfigCalculoPresupuestoDao;
import com.ejie.aa79b.dao.ConfigDireccionEmailDao;
import com.ejie.aa79b.dao.ConfigLibroRegistroDao;
import com.ejie.aa79b.dao.ConfigPerfilSolicitanteDao;
import com.ejie.aa79b.dao.ConfigPlazoMinimoDao;
import com.ejie.aa79b.dao.ConfigTextoEmailsDao;
import com.ejie.aa79b.dao.LimitePalConcorDao;
import com.ejie.aa79b.dao.LimitePalConcorProvExtDao;
import com.ejie.aa79b.model.ConfigCalculoPresupuesto;
import com.ejie.aa79b.model.ConfigDireccionEmail;
import com.ejie.aa79b.model.ConfigLibroRegistro;
import com.ejie.aa79b.model.ConfigPerfilSolicitante;
import com.ejie.aa79b.model.ConfigPlazoMinimo;
import com.ejie.aa79b.model.ConfigTextoEmails;
import com.ejie.aa79b.model.DatosBasicos;

@Service(value = "datosBasicosService")
public class DatosBasicosServiceImpl implements DatosBasicosService {

	@Autowired()
	private ConfigPlazoMinimoDao configPlazoMinimoDao;
	@Autowired()
	private LimitePalConcorDao limitePalConcorDao; // configHorasPrevistas
	@Autowired()
	private LimitePalConcorProvExtDao limitePalConcorProvExtDao; // configHorasPrevistasProvExt
	@Autowired()
	private ConfigCalculoPresupuestoDao configCalculoPresupuestoDao;
	@Autowired()
	private ConfigPerfilSolicitanteDao configPerfilSolicitanteDao;
	@Autowired()
	private ConfigDireccionEmailDao configDireccionEmailDao;
	@Autowired()
	private ConfigTextoEmailsDao configTextoEmailsDao;
	@Autowired()
	private ConfigLibroRegistroDao libroRegistroDao;

	@Override()
	public DatosBasicos find() {
		DatosBasicos datosBasicos = new DatosBasicos();
		datosBasicos.setUsuarioNormal(this.configPlazoMinimoDao
				.find(new ConfigPlazoMinimo(Constants.ID_DATOS_BASICOS, Constants.USUARIO_NORMAL)));
		datosBasicos.setUsuarioVIP(this.configPlazoMinimoDao
				.find(new ConfigPlazoMinimo(Constants.ID_DATOS_BASICOS, Constants.USUARIO_VIP)));
		datosBasicos.setConfigHorasPrevistasLimPalConcor(
				this.limitePalConcorDao.findLimPalConcor(Constants.ID_DATOS_BASICOS));
		datosBasicos.setConfigHorasPrevistasProvExtLimPalConcor(
				this.limitePalConcorProvExtDao.findLimPalConcor(Constants.ID_DATOS_BASICOS));
		datosBasicos.setConfigCalculoPresupuesto(
				this.configCalculoPresupuestoDao.find(new ConfigCalculoPresupuesto(Constants.ID_DATOS_BASICOS)));
		datosBasicos.setConfigPerfilSolicitante(
				this.configPerfilSolicitanteDao.find(new ConfigPerfilSolicitante(Constants.ID_DATOS_BASICOS)));
		datosBasicos.setConfigDireccionEmail(
				this.configDireccionEmailDao.find(new ConfigDireccionEmail(Constants.ID_DATOS_BASICOS)));
		datosBasicos.setConfigDireccionEmailInterpretacion(
				this.configDireccionEmailDao.find(new ConfigDireccionEmail(Constants.ID_DATOS_BASICOS_INTERPRETACION_EMAIL)));
		datosBasicos.setTextoEmails(this.configTextoEmailsDao.findAll(new ConfigTextoEmails(), null));
		datosBasicos.setLibroRegistro(this.libroRegistroDao.find(new ConfigLibroRegistro(Constants.ID_DATOS_BASICOS)));
		return datosBasicos;
	}

}
