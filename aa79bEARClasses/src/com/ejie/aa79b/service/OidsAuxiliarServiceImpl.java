package com.ejie.aa79b.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.dao.OidsAuxiliarDao;
import com.ejie.aa79b.webservices.PIDService;

/**
 * OidsAuxiliarServiceImpl
 * 
 * @author javarona
 */

@Service(value = "oidsAuxiliarService")
public class OidsAuxiliarServiceImpl implements OidsAuxiliarService {

	@Autowired()
	private OidsAuxiliarDao oidsAuxiliarDao;

	@Autowired()
	private PIDService pidService;

	private static final Logger LOGGER = LoggerFactory.getLogger(OidsAuxiliarServiceImpl.class);

	@Override()
	@Transactional(rollbackFor = Throwable.class)
	public void add(String nuevoOID) {
		this.oidsAuxiliarDao.add(nuevoOID);
	}

	@Override()
	@Transactional(rollbackFor = Throwable.class)
	public void remove(String oid) {
		this.oidsAuxiliarDao.remove(oid);
	}

	@Override()
	@Transactional(rollbackFor = Throwable.class)
	public void limpiarPIDProcesoBatch() {
		List<String> listaOids = this.oidsAuxiliarDao.getOids24h();
		try {
			this.pidService.deleteDocuments(listaOids);
			this.oidsAuxiliarDao.removeOids24h();
		} catch (Exception e) {
			OidsAuxiliarServiceImpl.LOGGER.info("limpiarPIDProcesoBatch: Excepción borrando documentos del PID", e);
			// ¿MANDAR UN EMAIL EN EL PROCESO BATCH SI FALLA LA ELIMINACIÓN DEL
			// PID ?????
		}
	}

}