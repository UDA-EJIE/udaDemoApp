/**
 *
 */
package com.ejie.aa79b.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.dao.EntidadesLoteDao;
import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.dao.RegistroAccionesDao;
import com.ejie.aa79b.model.EntidadesLote;
import com.ejie.aa79b.model.RegistroAcciones;

/**
 * @author eaguirresarobe
 *
 */
@Service(value = "entidadesLoteService")
public class EntidadesLoteServiceImpl extends GenericoServiceImpl<EntidadesLote>
									  implements EntidadesLoteService  {

	@Autowired
	private EntidadesLoteDao entidadesLoteDao;
	@Autowired()
	private RegistroAccionesDao registroAccionesDao;
	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	@Override
	protected GenericoDao<EntidadesLote> getDao() {
		return this.entidadesLoteDao;
	}

	@Override()
	@Transactional(rollbackFor = Throwable.class)
	public EntidadesLote add(EntidadesLote bean) {
		super.add(bean);
		EntidadesLote original = this.entidadesLoteDao.find(bean);

		RegistroAcciones regLotes = new RegistroAcciones();
		regLotes.setIdPuntoMenu(Constants.PUNTO_MENU_LOTES);
		regLotes.setIdAccion(Constants.ACCION_ALTA);
		Locale locale = new Locale(Constants.LANG_EUSKERA);
		StringBuilder aux = new StringBuilder();
		aux.append(this.msg.getMessage("label.asociarEntidad", null, locale)).append(": ");
		aux.append(original.getDescEu());
		regLotes.setObserv(aux.toString());
		this.registroAccionesDao.add(regLotes);

		return bean;
	}

	@Override()
	@Transactional(rollbackFor = Throwable.class)
	public void remove(EntidadesLote bean) {
		EntidadesLote original = this.entidadesLoteDao.find(bean);
		super.remove(bean);

		RegistroAcciones regGruposTrabajo = new RegistroAcciones();
		regGruposTrabajo.setIdPuntoMenu(Constants.PUNTO_MENU_LOTES);
		regGruposTrabajo.setIdAccion(Constants.ACCION_BORRADO);
		Locale locale = new Locale(Constants.LANG_EUSKERA);
		StringBuilder aux = new StringBuilder();
		aux.append(this.msg.getMessage("label.desasociarEntidad", null, locale)).append(": ");
		aux.append(original.getDescEu());
		regGruposTrabajo.setObserv(aux.toString());
		this.registroAccionesDao.add(regGruposTrabajo);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void removeWhereID(EntidadesLote entidadesLote) {
		this.entidadesLoteDao.removeWhereID(entidadesLote);
	}

	@Override
	public Integer anyadirComentarioEntidad(EntidadesLote bean) {
		if (this.entidadesLoteDao.entidadTieneComentario(bean)) {
			return this.entidadesLoteDao.modificarComentarioEntidad(bean);
		}

		return this.entidadesLoteDao.anyadirComentarioEntidad(bean);
	}

	@Override
	public EntidadesLote obtenerComentarioEntidad(EntidadesLote bean) {
		return this.entidadesLoteDao.findComentarioEntidad(bean);
	}


}
