package com.ejie.aa79b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.dao.RangoAprobacionAuditoriaDao;
import com.ejie.aa79b.model.RangoAprobacionAuditoria;

@Service(value = "rangoAprobacionAuditoriaService")
public class RangoAprobacionAuditoriaServiceImpl extends GenericoServiceImpl<RangoAprobacionAuditoria>
		implements RangoAprobacionAuditoriaService {

	@Autowired
	private RangoAprobacionAuditoriaDao rangoAprobacionAuditoriaDao;

	@Override
	protected GenericoDao<RangoAprobacionAuditoria> getDao() {
		return this.rangoAprobacionAuditoriaDao;
	}

	@Override
	public RangoAprobacionAuditoria find() {
		return rangoAprobacionAuditoriaDao.find();
	}

	@Override()
	@Transactional(rollbackFor = Throwable.class)
	public RangoAprobacionAuditoria update(RangoAprobacionAuditoria bean) {
		return this.rangoAprobacionAuditoriaDao.update(bean);
	}
}
