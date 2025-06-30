package com.ejie.aa79b.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.dao.SubsanacionDao;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.SubsanacionExpediente;

@Service(value = "subsanacionService")
public class SubsanacionServiceImpl extends GenericoServiceImpl<SubsanacionExpediente> implements SubsanacionService {

	@Autowired
	private SubsanacionDao subsanacionDao;

	@Override
	public Expediente getCabeceraBitacora(Expediente expediente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected GenericoDao<SubsanacionExpediente> getDao() {
		return this.subsanacionDao;
	}

	@Override
	public SubsanacionExpediente getSubsanacionExpediente(Long anyo, Integer numExp, Long idTarea) {
		return this.subsanacionDao.getSubsanacionExpediente(anyo, numExp, idTarea);
	}

	@Override
	public Integer actualizarSubsanacionConIndSubs(SubsanacionExpediente sExp) {
		return this.subsanacionDao.actualizarSubsanacionConIndSubs(sExp);
	}

	@Override
	public BigDecimal getIdTareaConRequerimiento(Long idRequerimiento) {
		return this.subsanacionDao.getIdTareaConRequerimiento(idRequerimiento);
	}

	@Override
	public Integer comprobarSiTieneZIP(DocumentosExpediente documentosExpediente) {
		return this.subsanacionDao.comprobarSiTieneZIP(documentosExpediente);
	}

}
