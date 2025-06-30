package com.ejie.aa79b.dao;

import java.util.List;

import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.TableRowDto;

/**
 * @author vdorantes
 *
 * @param <T>
 *            Tipo
 */
public interface GenericoDao<T> {

    /**
     * Finds a single row in the table.
     *
     * @param bean
     *            T
     * @return T
     */
    T find(T bean);

    /**
     * Finds a List of rows in the table.
     *
     * @param bean
     *            T
     * @param jqGridRequestDto
     *            JQGridRequestDto
     * @return List
     */
    List<T> findAll(T bean, JQGridRequestDto jqGridRequestDto);

    /**
     * Finds rows in the table using like.
     *
     * @param bean
     *            T
     * @param jqGridRequestDto
     *            JQGridRequestDto
     * @param startsWith
     *            Boolean
     * @return List
     */
    List<T> findAllLike(T bean, JQGridRequestDto jqGridRequestDto, Boolean startsWith);

    /*
     * OPERACIONES RUP_TABLE
     */

    /**
     * Counts rows in the table using like.
     *
     * @param bean
     *            T
     * @param startsWith
     *            Boolean
     * @return Long
     */
    Long findAllCount(T bean);

    /**
     * Counts rows in the table using like.
     *
     * @param bean
     *            T
     * @param startsWith
     *            Boolean
     * @return Long
     */
    Long findAllLikeCount(T bean, Boolean startsWith);

    /**
     * Reorder selection.
     *
     * @param filter
     *            T
     * @param jqGridRequestDto
     *            JQGridRequestDto
     * @param startsWith
     *            Boolean
     * @return List<TableRowDto<T>>
     */
    List<TableRowDto<T>> reorderSelection(T filter, JQGridRequestDto jqGridRequestDto, Boolean startsWith);

    /**
     * Searches in the Usuario table.
     *
     * @param filter
     *            T
     * @param search
     *            T
     * @param jqGridRequestDto
     *            JQGridRequestDto
     * @param startsWith
     *            Boolean
     * @return List<TableRowDto<T>>
     */
    List<TableRowDto<T>> search(T filter, T search, JQGridRequestDto jqGridRequestDto, Boolean startsWith);

    /**
     * Inserts a single row in the table.
     *
     * @param bean
     *            T
     * @return T
     */
    T add(T bean);

    /**
     * Updates a single row in the table.
     *
     * @param bean
     *            T
     * @return T
     */
    T update(T bean);

    /**
     * Removes a single row in the table.
     *
     * @param bean
     *            T
     */
    void remove(T bean);

    /**
     * Filter in the table.
     *
     * @param filter
     *            T
     * @param jqGridRequestDto
     *            JQGridRequestDto
     * @param startsWith
     *            Boolean
     */
    void removeMultiple(T filter, JQGridRequestDto jqGridRequestDto, Boolean startsWith);

    /**
     * @param secuencia
     *            String
     * @return Integer
     */
    Long getNextVal(String secuencia);

}
