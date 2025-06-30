package com.ejie.aa79b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.dao.PesosValoracionAuditoriaDao;
import com.ejie.aa79b.model.PesosValoracionAuditoria;

@Service(value = "pesosValoracionAuditoriaService")
public class PesosValoracionAuditoriaServiceImpl extends GenericoServiceImpl<PesosValoracionAuditoria>
		implements PesosValoracionAuditoriaService {

	@Autowired
	private PesosValoracionAuditoriaDao pesosValoracionAuditoriaDao;

	@Override
	protected GenericoDao<PesosValoracionAuditoria> getDao() {
		return this.pesosValoracionAuditoriaDao;
	}

	@Override()
	@Transactional(rollbackFor = Throwable.class)
	public PesosValoracionAuditoria update(PesosValoracionAuditoria bean) {
		return this.pesosValoracionAuditoriaDao.update(bean);
	}
}
