package com.ejie.aa79b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.dao.NotaExpedienteDao;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.NotasExpedientes;

@Service(value = "notaExpedienteService")
public class NotaExpedienteServiceImpl extends GenericoServiceImpl<NotasExpedientes> implements NotaExpedienteService {

	@Autowired
	private NotaExpedienteDao notaExpedienteDao;

	@Override
	protected GenericoDao<NotasExpedientes> getDao() {
		return this.notaExpedienteDao;
	}

	@Override
	public NotasExpedientes add(NotasExpedientes bean) {
		return this.notaExpedienteDao.add(bean);
	}

	@Override
	public Boolean tieneNotasExpediente(Expediente expediente) {
		return this.notaExpedienteDao.tieneNotasExpediente(expediente);
	}

}
