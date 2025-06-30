package com.ejie.aa79b.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x38.dao.RowNumResultSetExtractor;
import com.ejie.x38.dto.JQGridManager;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.TableRowDto;

/**
 * @author vdorantes
 *
 * @param <T>
 *            Tipo
 */
@Repository()
@Transactional()
public abstract class GenericoDaoImpl<T> extends BaseDaoImpl implements GenericoDao<T> {

	private final Class<T> type;

    public static final String DEFAULT_WHERE = " WHERE 1=1 ";

    /**
     * @param type
     *            Class<T>
     */
    public GenericoDaoImpl(Class<T> type) {
        this.type = type;
    }

    /*
     * OPERACIONES CRUD
     */

    /**
     * Inserts a single row in the T table.
     *
     * @param bean
     *            T
     * @return T
     */
    @Override()
    public T add(T bean) {
        return bean;
    }

    /**
     * Updates a single row in the T table.
     *
     * @param bean
     *            T
     * @return T
     */
    @Override()
    public T update(T bean) {
        return bean;
    }

    /**
     * Removes a single row in the T table.
     *
     * @param bean
     *            T
     * @return
     */
    @Override()
    public void remove(T bean) {
    }

    /**
     * Finds a single row in the T table.
     *
     * @param bean
     *            T
     * @return T
     */
    @Override()
    @Transactional(readOnly = true)
    public T find(T bean) {
        List<Object> params = new ArrayList<Object>();
        StringBuilder query = new StringBuilder(this.getWith());
        query.append(this.getSelect());
        query.append(this.getFrom(bean, params));
        query.append(this.getWherePK(bean, params));

        List<T> beanList = this.getJdbcTemplate().query(query.toString(), this.getRwMap(), params.toArray());
        return DataAccessUtils.uniqueResult(beanList);
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
    @Override()
    @Transactional(readOnly = true)
    public List<T> findAll(T bean, JQGridRequestDto jqGridRequestDto) {
        List<Object> params = new ArrayList<Object>();
        StringBuilder query = new StringBuilder(this.getWith());
        query.append(this.getSelect());
        query.append(this.getFrom(bean, params));

        query.append(GenericoDaoImpl.DEFAULT_WHERE);
        query.append(this.getWhere(bean, params));

        if (jqGridRequestDto != null) {
            query = JQGridManager.getPaginationQuery(jqGridRequestDto, query, this.getOrderBy());
        }

        return this.getJdbcTemplate().query(query.toString(), this.getRwMap(), params.toArray());
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
    @Transactional(readOnly = true)
    public List<T> findAllLike(T bean, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
        List<Object> params = new ArrayList<Object>();
        StringBuilder query = new StringBuilder(this.getWith());
        query.append(this.getSelect());
        query.append(this.getFrom(bean, params));

        query.append(GenericoDaoImpl.DEFAULT_WHERE);
        query.append(this.getWhereLike(bean, startsWith, params, false));

        if (jqGridRequestDto != null) {
            query = JQGridManager.getPaginationQuery(jqGridRequestDto, query, this.getOrderBy());
        }

        return this.getJdbcTemplate().query(query.toString(), this.getRwMap(), params.toArray());
    }

    /*
     * OPERACIONES RUP_TABLE
     */

    /**
     * Counts rows in the T table.
     * 
     * @param bean
     *            T
     * @return Long
     */
    @Override
    @Transactional(readOnly = true)
    public Long findAllCount(T bean) {
        List<Object> params = new ArrayList<Object>();
        StringBuilder query = new StringBuilder(this.getWith());
        query.append("SELECT COUNT(1)");
        query.append(this.getFrom(bean, params));

        // Where clause & Params
        query.append(GenericoDaoImpl.DEFAULT_WHERE);
        query.append(this.getWhere(bean, params));

        return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
    }

    /**
     * Counts rows in the T table using like.
     * 
     * @param bean
     *            T
     * @param startsWith
     *            Boolean
     * @return Long
     */
    @Override
    @Transactional(readOnly = true)
    public Long findAllLikeCount(T bean, Boolean startsWith) {
        List<Object> params = new ArrayList<Object>();
        StringBuilder query = new StringBuilder(this.getWith());
        query.append("SELECT COUNT(1)");
        query.append(this.getFrom(bean, params));

        query.append(GenericoDaoImpl.DEFAULT_WHERE);
        query.append(this.getWhereLike(bean, startsWith, params, false));

        return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
    }

    /**
     * Reorder the data list of T selected for rup_table
     * 
     * @param bean
     *            T
     * @param jqGridRequestDto
     *            JQGridRequestDto
     * @param startsWith
     *            Boolean
     * @return List<TableRowDto<T>>
     */
    @Override()
    public List<TableRowDto<T>> reorderSelection(T bean, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
        
    	
    	// SELECT
        List<Object> params = new ArrayList<Object>();
        StringBuilder query = new StringBuilder(this.getWith());
        query.append(this.getSelect());
        // FROM
        query.append(this.getFrom(bean, params));
        // FILTRADO
        query.append(GenericoDaoImpl.DEFAULT_WHERE);
        query.append(this.getWhereLike(bean, startsWith, params, false));

        // SQL para la reordenación
        StringBuilder sbReorderSelectionSQL = JQGridManager.getReorderQuery(query, jqGridRequestDto, this.type, params,
                this.getPK());

        return this.getJdbcTemplate().query(sbReorderSelectionSQL.toString(),
                new RowNumResultSetExtractor<T>(this.getRwMapPK(), jqGridRequestDto), params.toArray());
    }
    
    public List<TableRowDto<T>> reorderSelectionWithRol(T bean, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
        // SELECT
        List<Object> params = new ArrayList<Object>();
        StringBuilder query = new StringBuilder(this.getWith());
        query.append(this.getSelect());
        // FROM
        query.append(this.getFrom(bean, params));
        // FILTRADO
        query.append(GenericoDaoImpl.DEFAULT_WHERE);
        query.append(this.getWhereLike(bean, startsWith, params, false));

        // SQL para la reordenación
        StringBuilder sbReorderSelectionSQL = JQGridManager.getReorderQuery(query, jqGridRequestDto, this.type, params,
                this.getPK());

        return this.getJdbcTemplate().query(sbReorderSelectionSQL.toString(),
                new RowNumResultSetExtractor<T>(this.getRwMapPK(), jqGridRequestDto), params.toArray());
    }

    /**
     * Search method for rup_table
     * 
     * @param filterParams
     *            T
     * @param searchParams
     *            T
     * @param jqGridRequestDto
     *            JQGridRequestDto
     * @param startsWith
     *            Boolean
     * @return List<TableRowDto<T>>
     */
    @Override()
    public List<TableRowDto<T>> search(T filterParams, T searchParams, JQGridRequestDto jqGridRequestDto,
            Boolean startsWith) {
        // SELECT
        List<Object> filterParamList = new ArrayList<Object>();
        StringBuilder query = new StringBuilder(this.getWith());
        query.append(this.getSelect());
        // FROM
        query.append(this.getFrom(filterParams, filterParamList));
        // TABLAS_ALIAS
        List<String> fromAlias = new ArrayList<String>();
        fromAlias.add(" t1 ");

        // FILTRADO
        query.append(GenericoDaoImpl.DEFAULT_WHERE);
        query.append(this.getWhereLike(filterParams, startsWith, filterParamList, false));

        // BUSQUEDA
        List<Object> searchParamList = new ArrayList<Object>();
        String searchSQL = this.getWhereLike(searchParams, startsWith, searchParamList, true);
        // SQL
        StringBuilder sbReorderSelectionSQL = JQGridManager.getSearchQuery(query, jqGridRequestDto, this.type,
                filterParamList, searchSQL, searchParamList, fromAlias, this.getPK());

        return this.getJdbcTemplate().query(sbReorderSelectionSQL.toString(),
                new RowNumResultSetExtractor<T>(this.getRwMapPK(), jqGridRequestDto), filterParamList.toArray());
    }

    /**
     * Remove multiple method for rup_table
     * 
     * @param filterbean
     *            T
     * @param jqGridRequestDto
     *            JQGridRequestDto
     * @param startsWith
     *            Boolean
     */
    @Override()
    public void removeMultiple(T filterbean, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
        // SELECT
        List<Object> params = new ArrayList<Object>();
        StringBuilder query = new StringBuilder(this.getWith());
        query.append(this.getSelect());
        // FROM
        query.append(this.getFrom(filterbean, params));

        // WHERE
        query.append(GenericoDaoImpl.DEFAULT_WHERE);
        query.append(this.getWhereLike(filterbean, startsWith, params, false));

        StringBuilder sbRemoveMultipleSQL = JQGridManager.getRemoveMultipleQuery(jqGridRequestDto, this.type, query,
                params, this.getPK());
        this.getJdbcTemplate().update(sbRemoveMultipleSQL.toString(), params.toArray());
    }

    /*
     * MÉTODOS PRIVADOS
     */

    /**
     * @return String
     */
    protected abstract String getSelect();

    /**
     * @return String
     */
    protected abstract String getFrom();

    /**
     * @return RowMapper<T>
     */
    protected abstract RowMapper<T> getRwMap();

    /**
     * @return String[]
     */
    protected abstract String[] getOrderBy();

    /**
     * @return String
     */
    protected abstract String getPK();

    /**
     * @return RowMapper<T>
     */
    protected abstract RowMapper<T> getRwMapPK();

    /**
     * @param bean
     *            T
     * @param params
     *            List<Object>
     * @return String
     */
    protected abstract String getWherePK(T bean, List<Object> params);

    /**
     * @param bean
     *            T
     * @param params
     *            List<Object>
     * @return String
     */
    protected abstract String getWhere(T bean, List<Object> params);

    /**
     * @param bean
     *            T
     * @param startsWith
     *            Boolean
     * @param params
     *            List<Object>
     * @return String
     */
    protected abstract String getWhereLike(T bean, Boolean startsWith, List<Object> params, Boolean search);

    protected String getFrom(T bean, List<Object> params) {
        return this.getFrom();
    }
    
    protected String getWith() {
    	return "";
    }

    /**
     * StringBuilder initilization value
     */
    public static final int STRING_BUILDER_INIT = 4096;
}
