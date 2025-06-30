package com.ejie.aa79b.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;
import com.ejie.x38.dto.TableRowDto;

/**
 * @author vdorantes
 *
 * @param <T>
 *            Tipo
 */
public abstract class GenericoServiceImpl<T> implements GenericoService<T> {

	/**
	 * @return v85juGenericoDao<T>
	 */
	protected abstract GenericoDao<T> getDao();

	/**
	 * Inserts a single row in the T table.
	 *
	 * @param bean
	 *            T
	 * @return T
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public T add(T bean) {
		return this.getDao().add(bean);
	}

	/**
	 * Updates a single row in the T table.
	 *
	 * @param bean
	 *            T
	 * @return T
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public T update(T bean) {
		return this.getDao().update(bean);
	}

	/**
	 * Finds a single row in the T table.
	 *
	 * @param bean
	 *            T
	 * @return T
	 */
	@Override
	public T find(T bean) {
		return this.getDao().find(bean);
	}

	/**
	 * Deletes a single row in the T table.
	 *
	 * @param bean
	 *            T
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void remove(T bean) {
		this.getDao().remove(bean);
	}

	/**
	 * Finds a list of rows in the T table.
	 *
	 * @param bean
	 *            T
	 * @param jqGridRequestDto
	 *            JQGridRequestDto
	 * @return List<T>
	 */
	@Override
	public List<T> findAll(T bean, JQGridRequestDto jqGridRequestDto) {
		return this.getDao().findAll(bean, jqGridRequestDto);
	}

	/**
	 * Finds rows in the T table using like.
	 *
	 * @param bean
	 *            T
	 * @param jqGridRequestDto
	 *            JQGridRequestDto
	 * @param startsWith
	 *            Boolean
	 * @return List<T>
	 */
	@Override
	public List<T> findAllLike(T bean, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		return this.getDao().findAllLike(bean, jqGridRequestDto, startsWith);
	}

	/*
	 * OPERACIONES RUP_TABLE
	 */

	/**
	 * Removes rows from the T table.
	 *
	 * @param filterT
	 *            T
	 * @param jqGridRequestDto
	 *            JQGridRequestDto
	 * @param startsWith
	 *            Boolean
	 */
	@Override
	public void removeMultiple(T filterT, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		this.getDao().removeMultiple(filterT, jqGridRequestDto, startsWith);
	}

	/**
	 * Filter method in the T table.
	 *
	 * @param filter
	 *            T
	 * @param jqGridRequestDto
	 *            JQGridRequestDto
	 * @param startsWith
	 *            Boolean
	 * @return JQGridResponseDto<T>
	 */
	@Override
	public JQGridResponseDto<T> filter(T filter, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		List<T> listaT = this.getDao().findAllLike(filter, jqGridRequestDto, false);
		Long recordNum = this.getDao().findAllLikeCount(filter, false);
		if (jqGridRequestDto.getMultiselection().getSelectedIds() != null) {
			List<TableRowDto<T>> reorderSelection = this.getDao().reorderSelection(filter, jqGridRequestDto,
					startsWith);
			return new JQGridResponseDto<T>(jqGridRequestDto, recordNum, listaT, reorderSelection);
		}
		return new JQGridResponseDto<T>(jqGridRequestDto, recordNum, listaT);
	}

	/**
	 * Searches rows in the T table.
	 *
	 * @param filterT
	 *            T
	 * @param searchT
	 *            T
	 * @param jqGridRequestDto
	 *            JQGridRequestDto
	 * @param startsWith
	 *            Boolean
	 * @return List<TableRowDto<T>>
	 */
	@Override
	public List<TableRowDto<T>> search(T filterT, T searchT, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		return this.getDao().search(filterT, searchT, jqGridRequestDto, startsWith);
	}

	/**
	 * Reorder the selection made in T table.
	 *
	 * @param filterT
	 *            T
	 * @param jqGridRequestDto
	 *            JQGridRequestDto
	 * @param startsWith
	 *            Boolean
	 * @return Object
	 */
	@Override()
	public Object reorderSelection(T filterT, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		return this.getDao().reorderSelection(filterT, jqGridRequestDto, startsWith);
	}
	
	@Override()
	public Long findAllCount(T bean) {
		return this.getDao().findAllCount(bean);
	}

	@Override()
	public Long findAllLikeCount(T bean, Boolean startsWith) {
		return this.getDao().findAllLikeCount(bean, startsWith);
	}
	
	
}
