/*
* Copyright 2012 E.J.I.E., S.A.
*
* Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
* Solo podrá usarse esta obra si se respeta la Licencia.
* Puede obtenerse una copia de la Licencia en
*
* http://ec.europa.eu/idabc/eupl.html
*
* Salvo cuando lo exija la legislación aplicable o se acuerde por escrito,
* el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
* SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
* Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
* que establece la Licencia.
*/
package com.ejie.x21a.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x21a.dao.JQGridComarcaDao;
import com.ejie.x21a.model.Comarca;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;
import com.ejie.x38.dto.Pagination;
import com.ejie.x38.dto.TableRowDto;

/**
 * ComarcaServiceImpl generated by UDA, 14-ago-2012 12:59:39.
 * @author UDA
 */

@Service(value = "jqGridComarcaService")
public class JQGridComarcaServiceImpl implements JQGridComarcaService {

	@Autowired
	private JQGridComarcaDao jqGridComarcaDao;

	/**
	 * Inserts a single row in the Comarca table.
	 *
	 * @param comarca Comarca
	 * @return Comarca
	 */
	@Transactional(rollbackFor = Throwable.class)
	public Comarca add(Comarca comarca) {
		return this.jqGridComarcaDao.add(comarca);
	}

	/**
	 * Updates a single row in the Comarca table.
	 *
	 * @param comarca Comarca
	 * @return Comarca
	 */
	@Transactional(rollbackFor = Throwable.class)
	public Comarca update(Comarca comarca) {
		return this.jqGridComarcaDao.update(comarca);
	 }

	/**
	 * Finds a single row in the Comarca table.
	 *
	 * @param comarca Comarca
	 * @return Comarca
	 */
	public Comarca find(Comarca comarca) {
		return (Comarca) this.jqGridComarcaDao.find(comarca);
	}

	/**
	 * Finds a List of rows in the Comarca table.
	 *
	 * @param comarca Comarca
	 * @param pagination Pagination
	 * @return List
	 */
	public List<Comarca> findAll(Comarca comarca, Pagination pagination) {
		return (List<Comarca>) this.jqGridComarcaDao.findAll(comarca, pagination);
	}
    
	/**
	 * Counts rows in the Comarca table.
	 *
	 * @param comarca Comarca
	 * @return Long
	 */
	public Long findAllCount(Comarca comarca) {        
		return  this.jqGridComarcaDao.findAllCount(comarca);
	}

	/**
	 * Finds rows in the Comarca table using like.
	 *
	 * @param comarca Comarca
	 * @param pagination Pagination
	 * @param startsWith Boolean
	 * @return List
	 */
	public List<Comarca> findAllLike(Comarca comarca, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		return (List<Comarca>) this.jqGridComarcaDao.findAllLike(comarca, jqGridRequestDto, startsWith);
	}

	/**
	 * Counts rows in the Comarca table using like.
	 *
	 * @param comarca Comarca
	 * @param startsWith Boolean
	 * @return Long
	 */
	public Long findAllLikeCount(Comarca comarca, Boolean startsWith) {
		return this.jqGridComarcaDao.findAllLikeCount(comarca, startsWith);
	}
	    
	/**
	 * Deletes a single row in the Comarca table.
	 *
	 * @param comarca Comarca
	 * @return
	 */
	@Transactional(rollbackFor = Throwable.class)
	public void remove(Comarca comarca) {
		this.jqGridComarcaDao.remove(comarca);
	}
	
	/**
	 * Deletes multiple rows in the Comarca table.
	 *
	 * @param comarcaList List
	 * @return
	 */
	@Transactional(rollbackFor = Throwable.class)
	public void removeMultiple(List<Comarca> comarcaList) {
		for (Comarca  comarcaAux:comarcaList) {
			this.jqGridComarcaDao.remove(comarcaAux);
		}
	}
	
	@Override
	public Object reorderSelection(Comarca comarca, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith) {
		return this.jqGridComarcaDao.reorderSelection(comarca, jqGridRequestDto, startsWith);
	}

	@Override
	public List<TableRowDto<Comarca>> search(Comarca filterParams, Comarca searchParams, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		return this.jqGridComarcaDao.search(filterParams, searchParams, jqGridRequestDto, startsWith);
	}

	public JQGridResponseDto<Comarca> filter(Comarca comarca, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		List<Comarca> comarcas =  this.jqGridComarcaDao.findAllLike(comarca, jqGridRequestDto, false);
		Long recordNum =  this.jqGridComarcaDao.findAllLikeCount(comarca != null ? comarca: new Comarca (),false);
		if (jqGridRequestDto.getMultiselection().getSelectedIds()!=null){
			List<TableRowDto<Comarca>> reorderSelection = this.jqGridComarcaDao.reorderSelection(comarca, jqGridRequestDto, startsWith);
			return new JQGridResponseDto<Comarca>(jqGridRequestDto, recordNum, comarcas, reorderSelection);
		}
		return new JQGridResponseDto<Comarca>(jqGridRequestDto, recordNum, comarcas);  
	}
}
