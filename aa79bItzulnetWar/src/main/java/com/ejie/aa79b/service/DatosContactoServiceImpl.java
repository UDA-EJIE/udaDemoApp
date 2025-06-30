package com.ejie.aa79b.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.dao.DatosContactoDao;
import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.model.DatosContacto;

/**
 * DatosContactoServiceImpl, 04-sep-2018 13:25:45.
 * 
 * @author mrodriguez
 */

@Service(value = "datosContactoService")
public class DatosContactoServiceImpl extends GenericoServiceImpl<DatosContacto> implements DatosContactoService {

	@Autowired
	private DatosContactoDao datosContactoDao;

	@Override
	protected GenericoDao<DatosContacto> getDao() {
		return this.datosContactoDao;
	}

	@Override
	public DatosContacto findDatosContacto(DatosContacto bean) {
		return this.datosContactoDao.findDatosContacto(bean);
	}

	@Override
	public List<DatosContacto> getMailsProveedoresPorIdTarea(BigDecimal idTarea) {
		return this.datosContactoDao.getMailsProveedoresPorIdTarea(idTarea);
	}

	@Override
	public List<DatosContacto> getMailRecursoTareaAuditoria(BigDecimal idAuditoria) {
		return this.datosContactoDao.getMailRecursoTareaAuditoria(idAuditoria);
	}

}
