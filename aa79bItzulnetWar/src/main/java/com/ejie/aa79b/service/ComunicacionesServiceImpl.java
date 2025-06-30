package com.ejie.aa79b.service;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.dao.ComunicacionesDao;
import com.ejie.aa79b.dao.DocumentosGeneralDao;
import com.ejie.aa79b.dao.ExpedienteDao;
import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.dao.OidsAuxiliarDao;
import com.ejie.aa79b.dao.PropiedadDao;
import com.ejie.aa79b.model.Comunicaciones;
import com.ejie.aa79b.model.DocumentoTareaAdjunto;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.enums.AplicacionOrigenExpediente;
import com.ejie.aa79b.webservices.EventosService;
import com.ejie.aa79b.webservices.PIDService;

@Service(value = "comunicacionesService")
public class ComunicacionesServiceImpl extends GenericoServiceImpl<Comunicaciones> implements ComunicacionesService {

	@Autowired
	private DocumentosGeneralDao documentosGeneralDao;
	@Autowired
	private OidsAuxiliarDao oidsAuxiliarDao;
	@Autowired
	private ComunicacionesDao comunicacionesDao;
	@Autowired()
	private EventosService eventosService;
	@Autowired
	private ExpedienteDao expedienteDao;
	@Autowired
	private PropiedadDao propiedadDao;
	@Autowired
	private PIDService pidService;


	private static final Logger LOGGER = LoggerFactory.getLogger(ComunicacionesServiceImpl.class);


	@Override
	protected GenericoDao<Comunicaciones> getDao() {
		return this.comunicacionesDao;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public Comunicaciones createNewComunicacion(Comunicaciones comunicaciones) {
		Comunicaciones comunicacionesAux;
		if ( StringUtils.isNotEmpty(comunicaciones.getOidFichero())) {
			DocumentoTareaAdjunto documentoComunicaciones = new DocumentoTareaAdjunto();
			documentoComunicaciones.setEncriptado(comunicaciones.getEncriptado());
			documentoComunicaciones.setExtension(comunicaciones.getExtension());
			documentoComunicaciones.setNombre(comunicaciones.getNombre());
			documentoComunicaciones.setOid(comunicaciones.getOidFichero());
			documentoComunicaciones.setTamano(comunicaciones.getTamano());
			documentoComunicaciones.setContentType(comunicaciones.getContentType());
			documentoComunicaciones.setTitulo(FilenameUtils.removeExtension(comunicaciones.getNombre()));
			documentoComunicaciones = this.documentosGeneralDao.add(documentoComunicaciones);
			comunicaciones.setIdFichero0D4(documentoComunicaciones.getIdFichero());
		}
		comunicacionesAux = this.getDao().add(comunicaciones);


		//------------------------
		//		Generacion y envio del evento AA79_ MESSAGE _EVENT
		Expediente exp = new Expediente();
		exp.setAnyo(comunicacionesAux.getAnyo());
		exp.setNumExp(comunicacionesAux.getNumExp());
		exp = this.expedienteDao.find(exp);

		//Comprobacion de indComunicaciones aplicacion origen permitida
		if ( StringUtils.isNotEmpty( exp.getAplicacionOrigen() ) && Boolean.TRUE.equals( this.propiedadDao.esAplicacionComunicaciones( exp.getAplicacionOrigen() ) )) {
			// Si la comunicacion tiene fichero adjunto, lo descargo del PID a la ruta PIF de la aplicacion de origen ( p.ej. /P43/comunicacionesIZO/xxx.doc )

			if ( StringUtils.isNotEmpty( comunicacionesAux.getOidFichero() )){
				String rutaPifDevuelta;
				String aplicacion=exp.getAplicacionOrigen();
				if(AplicacionOrigenExpediente.P43.getValue().equalsIgnoreCase(aplicacion)) {
					//La P43 es una aplicación especial: ellos nos mandan como aplicación origen la P43, pero en este caso el que lee los ficheros es la P43B
					aplicacion=Constants.P43B;
				}
				String rutaBaseDestinoPIFAplicacion = "/"+ aplicacion +"/"+  Constants.RUTAPIF_DOC_AA79B_MESSAGE;
				try {
					rutaPifDevuelta = this.pidService.getRutaPIF( comunicacionesAux.getOidFichero(), rutaBaseDestinoPIFAplicacion);
					comunicacionesAux.setRutaPif(rutaPifDevuelta);
					//Para subir archivos al PIF /aa79b/comunicaciones/ y simular que ha llegado una comunicacion con fichero
					//					rutaPifDevuelta = this.pidService.getRutaPIF( comunicacionesAux.getOidFichero(), "/aa79b/comunicaciones/")
				}catch (Exception e) {
					LOGGER.error("Error descargando el fichero de la comunicacion del PID a la ruta {}", rutaBaseDestinoPIFAplicacion);
					comunicacionesAux.setMensaje( comunicacionesAux.getMensaje() + "La comunicacion adjunta un fichero que no ha podido ser descargado a "+rutaBaseDestinoPIFAplicacion);
					this.getDao().update(comunicacionesAux);
				}

			}
			this.eventosService.createComunicacion(comunicacionesAux, exp);
		}
		return comunicacionesAux;
	}

	@Override
	public Comunicaciones updateComunicacion(Comunicaciones comunicaciones) {
		// Recuperar el OID viejo para borrarlo, si no es la primera vez que se sube

		if (StringUtils.isNotEmpty(comunicaciones.getOidFichero())) {
			// Hemos modificado la comunicaciones
			DocumentoTareaAdjunto documentoComunicacionesAnterior = new DocumentoTareaAdjunto();
			documentoComunicacionesAnterior.setIdFichero(comunicaciones.getIdFichero0D4());
			documentoComunicacionesAnterior = this.documentosGeneralDao.find(documentoComunicacionesAnterior);
			if (StringUtils.isNotEmpty(documentoComunicacionesAnterior.getOid())) {
				this.oidsAuxiliarDao.add(documentoComunicacionesAnterior.getOid());
			}

			DocumentoTareaAdjunto documentoComunicacionesNuevo = new DocumentoTareaAdjunto();
			documentoComunicacionesNuevo.setEncriptado(comunicaciones.getEncriptado());
			documentoComunicacionesNuevo.setExtension(comunicaciones.getExtension());
			documentoComunicacionesNuevo.setNombre(comunicaciones.getNombre());
			documentoComunicacionesNuevo.setOid(comunicaciones.getOidFichero());
			documentoComunicacionesNuevo.setTamano(comunicaciones.getTamano());
			documentoComunicacionesNuevo.setContentType(comunicaciones.getContentType());
			documentoComunicacionesNuevo.setTitulo(FilenameUtils.removeExtension(comunicaciones.getNombre()));
			documentoComunicacionesNuevo.setIdFichero(comunicaciones.getIdFichero0D4());

			documentoComunicacionesNuevo = this.documentosGeneralDao.update(documentoComunicacionesNuevo);

			// Borrar OID nuevo de tabla de eliminacion del PID
			this.oidsAuxiliarDao.remove(documentoComunicacionesNuevo.getOid());
		}

		this.getDao().update(comunicaciones);
		// Update de la tabla de comunicacioness
		return comunicaciones;
	}

}
