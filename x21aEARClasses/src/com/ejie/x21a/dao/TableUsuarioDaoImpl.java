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
package com.ejie.x21a.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x21a.model.Usuario;
import com.ejie.x21a.model.Usuario2;
import com.ejie.x38.dao.RowNumResultSetExtractor;
import com.ejie.x38.dto.TableManager;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableRowDto;

/**
 * UsuarioDaoImpl generated by UDA, 14-ago-2012 12:59:38.
 * @author UDA
 */
 
@Repository
@Transactional
public class TableUsuarioDaoImpl implements TableUsuarioDao {
	
	/**
	 * StringBuilder initilization value
	 */
	public static final int STRING_BUILDER_INIT = 4096;
	
	public static final String[] ORDER_BY_WHITE_LIST = new String[] {"ID", "NOMBRE", "APELLIDO1", "APELLIDO2", "EJIE", "FECHA_ALTA", "FECHA_BAJA", "ROL", "FECHA_MODIF"};
	
    private JdbcTemplate jdbcTemplate;
    

	/**
     * Method use to set the datasource.
     *
     * @param dataSource DataSource
     * @return
     */
    @Resource
    public void setDataSource(DataSource dataSource) {
    	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /*
	 * OPERACIONES CRUD
	 */
    
    /**
     * Inserts a single row in the Usuario table.
     *
     * @param usuario TableRequestDto
     * @return Usuario
     */
	public Usuario add(Usuario usuario) {
		// Obtenemos el identificador de la entidad mediante una secuencia
		final String nextId = jdbcTemplate.queryForObject("SELECT USUARIO_SEQ.NEXTVAL FROM DUAL", String.class);
		usuario.setId(nextId);
		
    	String query = "INSERT INTO USUARIO (ID, NOMBRE, APELLIDO1, APELLIDO2, EJIE, FECHA_ALTA, FECHA_BAJA, ROL, FECHA_MODIF) VALUES (?,?,?,?,?,?,?,?,sysdate)";
		this.jdbcTemplate.update(query, usuario.getId(), usuario.getNombre(), usuario.getApellido1(), usuario.getApellido2(), usuario.getEjie(), usuario.getFechaAlta(), usuario.getFechaBaja(), usuario.getRol());
		return usuario;
	}
	
	/**
     * Inserts a single row in the Usuario table.
     *
     * @param usuario TableRequestDto
     * @return Usuario2
     */
	public Usuario2 add(Usuario2 usuario) {
		// Obtenemos el identificador de la entidad mediante una secuencia
		final String nextId = jdbcTemplate.queryForObject("SELECT USUARIO_SEQ.NEXTVAL FROM DUAL", String.class);
		usuario.setId(nextId);
		
    	String query = "INSERT INTO USUARIO (ID, NOMBRE, APELLIDO1, APELLIDO2, EJIE, FECHA_ALTA, FECHA_BAJA, ROL, FECHA_MODIF) VALUES (?,?,?,?,?,?,?,?,sysdate)";
		this.jdbcTemplate.update(query, usuario.getId(), usuario.getNombre(), usuario.getApellido1(), usuario.getApellido2(), usuario.getEjie(), usuario.getFechaAlta(), usuario.getFechaBaja(), usuario.getRol());
		return usuario;
	}

    /**
     * Updates a single row in the Usuario table.
     *
     * @param usuario TableRequestDto
     * @return Usuario
     */
    public Usuario update(Usuario usuario) {
		String query = "UPDATE USUARIO SET NOMBRE=?, APELLIDO1=?, APELLIDO2=?, EJIE=?, FECHA_ALTA=?, FECHA_BAJA=?, ROL=?, FECHA_MODIF=sysdate WHERE ID=?";
		this.jdbcTemplate.update(query, usuario.getNombre(), usuario.getApellido1(), usuario.getApellido2(), usuario.getEjie(), usuario.getFechaAlta(), usuario.getFechaBaja(), usuario.getRol(), usuario.getId());
		return usuario;
	}

    /**
     * Updates a single row in the Usuario table.
     *
     * @param usuario TableRequestDto
     * @return Usuario2
     */
    public Usuario2 update(Usuario2 usuario) {
		String query = "UPDATE USUARIO SET NOMBRE=?, APELLIDO1=?, APELLIDO2=?, EJIE=?, FECHA_ALTA=?, FECHA_BAJA=?, ROL=?, FECHA_MODIF=sysdate WHERE ID=?";
		this.jdbcTemplate.update(query, usuario.getNombre(), usuario.getApellido1(), usuario.getApellido2(), usuario.getEjie(), usuario.getFechaAlta(), usuario.getFechaBaja(), usuario.getRol(), usuario.getId());
		return usuario;
	}

    /**
     * Finds a single row in the Usuario table.
     *
     * @param usuario TableRequestDto
     * @return Usuario
     */
    @Transactional (readOnly = true)
    public Usuario find(Usuario usuario) {
		String query = "SELECT t1.ID ID, t1.NOMBRE NOMBRE, t1.APELLIDO1 APELLIDO1, t1.APELLIDO2 APELLIDO2, t1.EJIE EJIE, t1.FECHA_ALTA FECHA_ALTA, t1.FECHA_BAJA FECHA_BAJA, t1.ROL ROL FROM USUARIO t1  WHERE t1.ID = ?  ";
		
		List<Usuario> usuarioList = this.jdbcTemplate.query(query, this.rwMap, usuario.getId());
		return (Usuario) DataAccessUtils.uniqueResult(usuarioList);
    }

    /**
     * Finds a single row in the Usuario table.
     *
     * @param usuario TableRequestDto
     * @return Usuario
     */
    @Transactional (readOnly = true)
    public Usuario2 find(Usuario2 usuario) {
		String query = "SELECT t1.ID ID, t1.NOMBRE NOMBRE, t1.APELLIDO1 APELLIDO1, t1.APELLIDO2 APELLIDO2, t1.EJIE EJIE, t1.FECHA_ALTA FECHA_ALTA, t1.FECHA_BAJA FECHA_BAJA, t1.ROL ROL FROM USUARIO t1  WHERE t1.ID = ?  ";
		
		List<Usuario2> usuarioList = this.jdbcTemplate.query(query, this.rwMap2, usuario.getId());
		return (Usuario2) DataAccessUtils.uniqueResult(usuarioList);
    }

    /**
     * Removes a single row in the Usuario table.
     *
     * @param usuario TableRequestDto
     * @return
     */
    public void remove(Usuario usuario) {
		String query = "DELETE FROM USUARIO WHERE ID=?";
		this.jdbcTemplate.update(query, usuario.getId());
    }

    /**
     * Removes a single row in the Usuario table.
     *
     * @param usuario2 TableRequestDto
     * @return
     */
    public void remove(Usuario2 usuario) {
		String query = "DELETE FROM USUARIO WHERE ID=?";
		this.jdbcTemplate.update(query, usuario.getId());
    }
    
   /**
    * Finds a List of rows in the Usuario table.
    * 
    * @param usuario Usuario
    * @param tableRequestDto TableRequestDto
    * @return List 
    */
	@Transactional (readOnly = true)
    public List<Usuario> findAll(Usuario usuario, TableRequestDto tableRequestDto) {
		StringBuilder query = new StringBuilder("SELECT  t1.ID ID, t1.NOMBRE NOMBRE, t1.APELLIDO1 APELLIDO1, t1.APELLIDO2 APELLIDO2, t1.EJIE EJIE, t1.FECHA_ALTA FECHA_ALTA, t1.FECHA_BAJA FECHA_BAJA, t1.ROL ROL "); 
		query.append("FROM USUARIO t1 ");
		
		//Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereMap(usuario); 
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		where.append(mapaWhere.get("query"));
		query.append(where);
		
		List<?> params = (List<?>) mapaWhere.get("params");

		if (tableRequestDto != null) {
			query = TableManager.getPaginationQuery(tableRequestDto, query, TableUsuarioDaoImpl.ORDER_BY_WHITE_LIST);
		}
		
		return (List<Usuario>) this.jdbcTemplate.query(query.toString(), this.rwMap, params.toArray());
	}
    
   /**
    * Finds a List of rows in the Usuario table.
    * 
    * @param usuario Usuario2
    * @param tableRequestDto TableRequestDto
    * @return List 
    */
	@Transactional (readOnly = true)
    public List<Usuario2> findAll(Usuario2 usuario, TableRequestDto tableRequestDto) {
		StringBuilder query = new StringBuilder("SELECT  t1.ID ID, t1.NOMBRE NOMBRE, t1.APELLIDO1 APELLIDO1, t1.APELLIDO2 APELLIDO2, t1.EJIE EJIE, t1.FECHA_ALTA FECHA_ALTA, t1.FECHA_BAJA FECHA_BAJA, t1.ROL ROL "); 
		query.append("FROM USUARIO t1 ");
		
		//Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereMap(usuario); 
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		where.append(mapaWhere.get("query"));
		query.append(where);
		
		List<?> params = (List<?>) mapaWhere.get("params");

		if (tableRequestDto != null) {
			query = TableManager.getPaginationQuery(tableRequestDto, query, TableUsuarioDaoImpl.ORDER_BY_WHITE_LIST);
		}
		
		return (List<Usuario2>) this.jdbcTemplate.query(query.toString(), this.rwMap2, params.toArray());
	}
	
   /**
    * Finds a List of rows containing the PK field values in the Usuario table.
    * 
    * @param usuario Usuario
	* @param startsWith boolean
	* 
    * @return List<Usuario>
    */
	@Transactional (readOnly = true)
    public List<Usuario> findAllIds(Usuario usuario, boolean startsWith) {
		// SELECT
		StringBuilder query = new StringBuilder("SELECT t1.ID ID FROM USUARIO t1"); 
		
		// WHERE clause & Params
		Map<String, ?> mapaWhere = this.getWhereLikeMap(usuario, startsWith); 
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		where.append(mapaWhere.get("query"));
		query.append(where);

		@SuppressWarnings("unchecked")
		List<Object> params = (List<Object>) mapaWhere.get("params");

		return this.jdbcTemplate.query(query.toString(), this.rwMapPK, params.toArray());
	}
	
	
	/**
	 * Finds rows in the Usuario table using like.
     * 
     * @param usuario Usuario
     * @param tableRequestDto TableRequestDto
     * @param startsWith Boolean
     * @return List 
     * 
     * 
     */
	@Transactional (readOnly = true)
    public List<Usuario> findAllLike(Usuario usuario, TableRequestDto tableRequestDto, Boolean startsWith) {
		StringBuilder query = new StringBuilder("SELECT  t1.ID ID,t1.NOMBRE NOMBRE,t1.APELLIDO1 APELLIDO1,t1.APELLIDO2 APELLIDO2,t1.EJIE EJIE,t1.FECHA_ALTA FECHA_ALTA,t1.FECHA_BAJA FECHA_BAJA,t1.ROL ROL "); 
        query.append("FROM USUARIO t1 ");
      	
		//Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereLikeMap(usuario,startsWith); 
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		where.append(mapaWhere.get("query"));
		query.append(where);

		@SuppressWarnings("unchecked")
		List<Object> params = (List<Object>) mapaWhere.get("params");

		if (tableRequestDto != null) {
			query = TableManager.getPaginationQuery(tableRequestDto, query, TableUsuarioDaoImpl.ORDER_BY_WHITE_LIST);
		}
		
		return (List<Usuario>) this.jdbcTemplate.query(query.toString(), this.rwMap, params.toArray());
	}
	
	
	/**
	 * Finds rows in the Usuario table using like.
     * 
     * @param usuario Usuario2
     * @param tableRequestDto TableRequestDto
     * @param startsWith Boolean
     * @return List 
     * 
     * 
     */
	@Transactional (readOnly = true)
    public List<Usuario2> findAllLike(Usuario2 usuario, TableRequestDto tableRequestDto, Boolean startsWith) {
		StringBuilder query = new StringBuilder("SELECT  t1.ID ID,t1.NOMBRE NOMBRE,t1.APELLIDO1 APELLIDO1,t1.APELLIDO2 APELLIDO2,t1.EJIE EJIE,t1.FECHA_ALTA FECHA_ALTA,t1.FECHA_BAJA FECHA_BAJA,t1.ROL ROL "); 
        query.append("FROM USUARIO t1 ");
      	
		//Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereLikeMap(usuario,startsWith); 
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		where.append(mapaWhere.get("query"));
		query.append(where);

		@SuppressWarnings("unchecked")
		List<Object> params = (List<Object>) mapaWhere.get("params");

		if (tableRequestDto != null) {
			query = TableManager.getPaginationQuery(tableRequestDto, query, TableUsuarioDaoImpl.ORDER_BY_WHITE_LIST);
		}
		
		return (List<Usuario2>) this.jdbcTemplate.query(query.toString(), this.rwMap2, params.toArray());
	}
	
	/*
	 * OPERACIONES RUP_TABLE
	 */
	
	/**
     * Counts rows in the Usuario table.
     * 
     * @param usuario Usuario
     * @return Long
     */
    @Transactional (readOnly = true)
    public Long findAllCount(Usuario usuario) {
		StringBuilder query = new StringBuilder("SELECT COUNT(1) FROM USUARIO t1 ");
		
		//Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereMap(usuario); 
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		where.append(mapaWhere.get("query"));
		query.append(where);		
		
		List<?> params = (List<?>) mapaWhere.get("params");
		
		return this.jdbcTemplate.queryForObject(query.toString(), Long.class, params.toArray());
	}
	
	/**
	 * Counts rows in the Usuario table using like.
     * 
     * @param usuario Usuario
     * @param startsWith Boolean
     * @return Long 
     */
	@Transactional (readOnly = true)
    public Long findAllLikeCount(Usuario usuario, Boolean startsWith) {
		StringBuilder query = new StringBuilder("SELECT COUNT(1) FROM USUARIO t1 ");

		//Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereLikeMap(usuario, startsWith); 
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		where.append(mapaWhere.get("query"));
		query.append(where);

		List<?> params = (List<?>) mapaWhere.get("params");

		return this.jdbcTemplate.queryForObject(query.toString(), Long.class, params.toArray());
	}
	
	/**
	 * Counts rows in the Usuario table using like.
     * 
     * @param usuario Usuario2
     * @param startsWith Boolean
     * @return Long 
     */
	@Transactional (readOnly = true)
    public Long findAllLikeCount(Usuario2 usuario, Boolean startsWith) {
		StringBuilder query = new StringBuilder("SELECT COUNT(1) FROM USUARIO t1 ");

		//Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereLikeMap(usuario, startsWith); 
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		where.append(mapaWhere.get("query"));
		query.append(where);

		List<?> params = (List<?>) mapaWhere.get("params");

		return this.jdbcTemplate.queryForObject(query.toString(), Long.class, params.toArray());
	}
	
	@Override
	public List<TableRowDto<Usuario>> reorderSelection(Usuario usuario, TableRequestDto tableRequestDto, Boolean startsWith) {
		
		// SELECT
		StringBuilder sbSQL = new StringBuilder("SELECT  t1.ID ID,t1.NOMBRE NOMBRE,t1.APELLIDO1 APELLIDO1,t1.APELLIDO2 APELLIDO2,t1.EJIE EJIE,t1.FECHA_ALTA FECHA_ALTA,t1.FECHA_BAJA FECHA_BAJA,t1.ROL ROL ");
		
		// FROM
        sbSQL.append("FROM USUARIO t1 ");
        
		// FILTRADO 
		Map<String, ?> mapaWhere = this.getWhereLikeMap(usuario, startsWith);
		// Claula where  de filtrado
		sbSQL.append(" WHERE 1=1 ").append(mapaWhere.get("query"));
		// Parámetros de filtrado
		@SuppressWarnings("unchecked")
		List<Object> filterParamList = (List<Object>) mapaWhere.get("params");		
		
		// SQL para la reordenación
		StringBuilder sbReorderSelectionSQL = TableManager.getReorderQuery(sbSQL, tableRequestDto, Usuario.class, filterParamList, "ID");
		
		return this.jdbcTemplate.query(sbReorderSelectionSQL.toString(), new RowNumResultSetExtractor<Usuario>(this.rwMapPK, tableRequestDto), filterParamList.toArray());
	}
	
	@Override
	public List<TableRowDto<Usuario2>> reorderSelection(Usuario2 usuario, TableRequestDto tableRequestDto, Boolean startsWith) {
		
		// SELECT
		StringBuilder sbSQL = new StringBuilder("SELECT  t1.ID ID,t1.NOMBRE NOMBRE,t1.APELLIDO1 APELLIDO1,t1.APELLIDO2 APELLIDO2,t1.EJIE EJIE,t1.FECHA_ALTA FECHA_ALTA,t1.FECHA_BAJA FECHA_BAJA,t1.ROL ROL ");
		
		// FROM
        sbSQL.append("FROM USUARIO t1 ");
        
		// FILTRADO 
		Map<String, ?> mapaWhere = this.getWhereLikeMap(usuario, startsWith);
		// Claula where  de filtrado
		sbSQL.append(" WHERE 1=1 ").append(mapaWhere.get("query"));
		// Parámetros de filtrado
		@SuppressWarnings("unchecked")
		List<Object> filterParamList = (List<Object>) mapaWhere.get("params");		
		
		// SQL para la reordenación
		StringBuilder sbReorderSelectionSQL = TableManager.getReorderQuery(sbSQL, tableRequestDto, Usuario2.class, filterParamList, "ID");
		
		return this.jdbcTemplate.query(sbReorderSelectionSQL.toString(), new RowNumResultSetExtractor<Usuario2>(this.rwMapPK2, tableRequestDto), filterParamList.toArray());
	}
	
	@Override
	public List<TableRowDto<Usuario>> search(Usuario filterParams, Usuario searchParams, TableRequestDto tableRequestDto, Boolean startsWith) {
		
		// SELECT 
		StringBuilder sbSQL = new StringBuilder("SELECT  t1.ID ID,t1.NOMBRE NOMBRE,t1.APELLIDO1 APELLIDO1,t1.APELLIDO2 APELLIDO2,t1.EJIE EJIE,t1.FECHA_ALTA FECHAALTA,t1.FECHA_BAJA FECHABAJA,t1.ROL ROL ");
		
		// FROM
		sbSQL.append("FROM USUARIO t1 ");
      	
		//TABLAS_ALIAS
		List<String> from_alias = new ArrayList<String>();
		from_alias.add("t1");
		
		// FILTRADO
		// Mapa de filtrado
		Map<String, Object> mapaWhereFilter = this.getWhereLikeMap(filterParams, startsWith); 
		// Claula where  de filtrado
		sbSQL.append(" WHERE 1=1 ").append(mapaWhereFilter.get("query"));
		// Parámetros de filtrado
		@SuppressWarnings("unchecked")
		List<Object> filterParamList = (List<Object>) mapaWhereFilter.get("params");
		
		// BUSQUEDA
		Map<String, Object> mapaWhereSearch = this.getWhereLikeMap(searchParams, startsWith);
		// Claula where  de búsqueda
		String searchSQL = ((StringBuffer) mapaWhereSearch.get("query")).toString();
		// Parámetros de búsqueda
		@SuppressWarnings("unchecked")
		List<Object> searchParamList = (List<Object>) mapaWhereSearch.get("params");
		

		// SQL para la busqueda
		StringBuilder sbReorderSelectionSQL = TableManager.getSearchQuery(sbSQL, tableRequestDto, Usuario.class, filterParamList, searchSQL, searchParamList, from_alias, "ID");
				
		return this.jdbcTemplate.query(sbReorderSelectionSQL.toString(), new RowNumResultSetExtractor<Usuario>(this.rwMapPK, tableRequestDto), filterParamList.toArray());
	}
	
	@Override
	public List<TableRowDto<Usuario2>> search(Usuario2 filterParams, Usuario2 searchParams, TableRequestDto tableRequestDto, Boolean startsWith) {
		// SELECT 
		StringBuilder sbSQL = new StringBuilder("SELECT  t1.ID ID,t1.NOMBRE NOMBRE,t1.APELLIDO1 APELLIDO1,t1.APELLIDO2 APELLIDO2,t1.EJIE EJIE,t1.FECHA_ALTA FECHAALTA,t1.FECHA_BAJA FECHABAJA,t1.ROL ROL ");
		
		// FROM
		sbSQL.append("FROM USUARIO t1 ");
      	
		//TABLAS_ALIAS
		List<String> from_alias = new ArrayList<String>();
		from_alias.add("t1");
		
		// FILTRADO
		// Mapa de filtrado
		Map<String, Object> mapaWhereFilter = this.getWhereLikeMap(filterParams, startsWith); 
		// Claula where  de filtrado
		sbSQL.append(" WHERE 1=1 ").append(mapaWhereFilter.get("query"));
		// Parámetros de filtrado
		@SuppressWarnings("unchecked")
		List<Object> filterParamList = (List<Object>) mapaWhereFilter.get("params");
		
		// BUSQUEDA
		Map<String, Object> mapaWhereSearch = this.getWhereLikeMap(searchParams, startsWith);
		// Claula where  de búsqueda
		String searchSQL = ((StringBuffer) mapaWhereSearch.get("query")).toString();
		// Parámetros de búsqueda
		@SuppressWarnings("unchecked")
		List<Object> searchParamList = (List<Object>) mapaWhereSearch.get("params");
		
		// SQL para la busqueda
		StringBuilder sbReorderSelectionSQL = TableManager.getSearchQuery(sbSQL, tableRequestDto, Usuario2.class, filterParamList, searchSQL, searchParamList, from_alias, "ID");
				
		return this.jdbcTemplate.query(sbReorderSelectionSQL.toString(), new RowNumResultSetExtractor<Usuario2>(this.rwMapPK2, tableRequestDto), filterParamList.toArray());
	}

	
	/*
	 * OPERACIONES RUP_TABLE
	 */
	
	/**
	 * Deletes multiple rows in the Usuario table.
	 *
	 * @param filterUsuario Usuario
	 * @param tableRequestDto TableRequestDto
	 * @param startsWith Boolean	 
	 */	
	@Override
	public void removeMultiple(Usuario filterUsuario, TableRequestDto tableRequestDto, Boolean startsWith) {
		// Like clause and params
    	Map<String, Object> mapaWhereLike = this.getWhereLikeMap(filterUsuario, startsWith);
    	
    	// Delete query
		StringBuilder sbRemoveMultipleSQL = TableManager.getRemoveMultipleQuery(mapaWhereLike, tableRequestDto, Usuario.class, "USUARIO", "t1", new String[]{"ID"});
		
		// Params list. Includes needed params for like and IN/NOT IN clauses
		@SuppressWarnings("unchecked")
		List<Object> params = (List<Object>) mapaWhereLike.get("params");
		params.addAll(tableRequestDto.getMultiselection().getSelectedIds());
		
		this.jdbcTemplate.update(sbRemoveMultipleSQL.toString(), params.toArray());
	}
	
	/**
	 * Deletes multiple rows in the Usuario table.
	 *
	 * @param filterUsuario Usuario2
	 * @param tableRequestDto TableRequestDto
	 * @param startsWith Boolean	 
	 */	
	@Override
	public void removeMultiple(Usuario2 filterUsuario, TableRequestDto tableRequestDto, Boolean startsWith) {
		// Like clause and params
    	Map<String, Object> mapaWhereLike = this.getWhereLikeMap(filterUsuario, startsWith);
    	
    	// Delete query
		StringBuilder sbRemoveMultipleSQL = TableManager.getRemoveMultipleQuery(mapaWhereLike, tableRequestDto, Usuario2.class, "USUARIO", "t1", new String[]{"ID"});
		
		// Params list. Includes needed params for like and IN/NOT IN clauses
		@SuppressWarnings("unchecked")
		List<Object> params = (List<Object>) mapaWhereLike.get("params");
		params.addAll(tableRequestDto.getMultiselection().getSelectedIds());
		
		this.jdbcTemplate.update(sbRemoveMultipleSQL.toString(), params.toArray());
	}
	
	@Override
	public List<Usuario> getMultiple(Usuario filterUsuario, TableRequestDto tableRequestDto, Boolean startsWith) {
		
		// SELECT 
		StringBuilder sbSQL = new StringBuilder("SELECT t1.ID ID, t1.NOMBRE NOMBRE, t1.APELLIDO1 APELLIDO1, t1.APELLIDO2 APELLIDO2, t1.EJIE EJIE, t1.FECHA_ALTA FECHA_ALTA, t1.FECHA_BAJA FECHA_BAJA, t1.ROL ROL ");
		
		// FROM
		sbSQL.append("FROM USUARIO t1 ");
    	//Where clause & Params
    	Map<String, Object> mapaWhere = this.getWhereLikeMap(filterUsuario, startsWith);
    	StringBuilder where = new StringBuilder(" WHERE 1=1 ");
    	where.append(mapaWhere.get("query"));
    	sbSQL.append(where);
    	
    	@SuppressWarnings("unchecked")
    	List<Object> params = (List<Object>) mapaWhere.get("params");
    	
		StringBuilder sbRemoveMultipleSQL = sbSQL.append(TableManager.getSelectMultipleQuery(tableRequestDto, Usuario.class, params, TableUsuarioDaoImpl.ORDER_BY_WHITE_LIST, "ID"));
		
		return this.jdbcTemplate.query(sbRemoveMultipleSQL.toString(), this.rwMap, params.toArray());
		
	}
	
	@Override
	public List<Usuario2> getMultiple(Usuario2 filterUsuario, TableRequestDto tableRequestDto, Boolean startsWith) {
		
		// SELECT 
		StringBuilder sbSQL = new StringBuilder("SELECT t1.ID ID, t1.NOMBRE NOMBRE, t1.APELLIDO1 APELLIDO1, t1.APELLIDO2 APELLIDO2, t1.EJIE EJIE, t1.FECHA_ALTA FECHA_ALTA, t1.FECHA_BAJA FECHA_BAJA, t1.ROL ROL ");
		
		// FROM
		sbSQL.append("FROM USUARIO t1 ");
    	//Where clause & Params
    	Map<String, Object> mapaWhere = this.getWhereLikeMap(filterUsuario, startsWith);
    	StringBuilder where = new StringBuilder(" WHERE 1=1 ");
    	where.append(mapaWhere.get("query"));
    	sbSQL.append(where);
    	
    	@SuppressWarnings("unchecked")
    	List<Object> params = (List<Object>) mapaWhere.get("params");
    	
		StringBuilder sbRemoveMultipleSQL = sbSQL.append(TableManager.getSelectMultipleQuery(tableRequestDto, Usuario2.class, params, TableUsuarioDaoImpl.ORDER_BY_WHITE_LIST, "ID"));
		
		return this.jdbcTemplate.query(sbRemoveMultipleSQL.toString(), this.rwMap2, params.toArray());
		
	}	
	
	/*
	 * MÉTODOS PRIVADOS
	 */
	
	/**
	 * Returns a map with the needed value to create the conditions to filter by 
	 * the Usuario entity 
	 * 
	 * @param usuario Usuario
	 *            Bean with the criteria values to filter by.
	 * @return Map created with two keys
	 *         key query stores the sql query syntax
	 *         key params stores the parameter values to be used in the condition sentence.
	 */
	private Map<String, ?> getWhereMap (Usuario usuario){
		
		StringBuffer where = new StringBuffer(TableUsuarioDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();

		if (usuario  != null  && usuario.getId() != null ) {
			where.append(" AND t1.ID = ?");
			params.add(usuario.getId());
		}
		if (usuario  != null  && usuario.getNombre() != null ) {
			where.append(" AND t1.NOMBRE = ?");
			params.add(usuario.getNombre());
		}
		if (usuario  != null  && usuario.getApellido1() != null ) {
			where.append(" AND t1.APELLIDO1 = ?");
			params.add(usuario.getApellido1());
		}
		if (usuario  != null  && usuario.getApellido2() != null ) {
			where.append(" AND t1.APELLIDO2 = ?");
			params.add(usuario.getApellido2());
		}
		if (usuario  != null  && usuario.getEjie() != null ) {
			where.append(" AND t1.EJIE = ?");
			params.add(usuario.getEjie());
		}
		if (usuario  != null  && usuario.getFechaAlta() != null ) {
			where.append(" AND t1.FECHA_ALTA = ?");
			params.add(usuario.getFechaAlta());
		}
		if (usuario  != null  && usuario.getFechaBaja() != null ) {
			where.append(" AND t1.FECHA_BAJA = ?");
			params.add(usuario.getFechaBaja());
		}
		if (usuario  != null  && usuario.getRol() != null ) {
			where.append(" AND t1.ROL = ?");
			params.add(usuario.getRol());
		}

		Map<String,Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);
		
		return mapWhere;		
	}
	
	/**
	 * Returns a map with the needed value to create the conditions to filter by 
	 * the Usuario2 entity 
	 * 
	 * @param usuario Usuario2
	 *            Bean with the criteria values to filter by.
	 * @return Map created with two keys
	 *         key query stores the sql query syntax
	 *         key params stores the parameter values to be used in the condition sentence.
	 */
	private Map<String, ?> getWhereMap (Usuario2 usuario){
		
		StringBuffer where = new StringBuffer(TableUsuarioDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();

		if (usuario  != null  && usuario.getId() != null ) {
			where.append(" AND t1.ID = ?");
			params.add(usuario.getId());
		}
		if (usuario  != null  && usuario.getNombre() != null ) {
			where.append(" AND t1.NOMBRE = ?");
			params.add(usuario.getNombre());
		}
		if (usuario  != null  && usuario.getApellido1() != null ) {
			where.append(" AND t1.APELLIDO1 = ?");
			params.add(usuario.getApellido1());
		}
		if (usuario  != null  && usuario.getApellido2() != null ) {
			where.append(" AND t1.APELLIDO2 = ?");
			params.add(usuario.getApellido2());
		}
		if (usuario  != null  && usuario.getEjie() != null ) {
			where.append(" AND t1.EJIE = ?");
			params.add(usuario.getEjie());
		}
		if (usuario  != null  && usuario.getFechaAlta() != null ) {
			where.append(" AND t1.FECHA_ALTA = ?");
			params.add(usuario.getFechaAlta());
		}
		if (usuario  != null  && usuario.getFechaBaja() != null ) {
			where.append(" AND t1.FECHA_BAJA = ?");
			params.add(usuario.getFechaBaja());
		}
		if (usuario  != null  && usuario.getRol() != null ) {
			where.append(" AND t1.ROL = ?");
			params.add(usuario.getRol());
		}

		Map<String,Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);
		
		return mapWhere;		
	}
	
	/**
	 * Returns a map with the needed value to create the conditions to filter by  
	 * the Usuario entity 
	 * 
	 * @param usuario Usuario
	 *            Bean with the criteria values to filter by.
     * @param startsWith Boolean	 
	 * @return Map created with two keys
	 *         key query stores the sql query syntax
	 *         key params stores the parameter values to be used in the condition sentence.
	 */
	private Map<String, Object> getWhereLikeMap (Usuario usuario, Boolean startsWith){
		
		StringBuffer where = new StringBuffer(TableUsuarioDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();

		if (usuario  != null  && usuario.getId() != null ) {
			where.append(" AND UPPER(t1.ID) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(usuario.getId().toUpperCase() +"%");
			}else{
				params.add("%"+usuario.getId().toUpperCase() +"%");
			}
			where.append(" AND t1.ID IS NOT NULL");
	     }			
		if (usuario  != null  && usuario.getNombre() != null ) {
			where.append(" AND UPPER(t1.NOMBRE) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(usuario.getNombre().toUpperCase() +"%");
			}else{
				params.add("%"+usuario.getNombre().toUpperCase() +"%");
			}
			where.append(" AND t1.NOMBRE IS NOT NULL");
	     }			
		if (usuario  != null  && usuario.getApellido1() != null ) {
			where.append(" AND UPPER(t1.APELLIDO1) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(usuario.getApellido1().toUpperCase() +"%");
			}else{
				params.add("%"+usuario.getApellido1().toUpperCase() +"%");
			}
			where.append(" AND t1.APELLIDO1 IS NOT NULL");
	     }			
		if (usuario  != null  && usuario.getApellido2() != null ) {
			where.append(" AND UPPER(t1.APELLIDO2) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(usuario.getApellido2().toUpperCase() +"%");
			}else{
				params.add("%"+usuario.getApellido2().toUpperCase() +"%");
			}
			where.append(" AND t1.APELLIDO2 IS NOT NULL");
	     }			
		if (usuario  != null  && usuario.getEjie() != null ) {
			where.append(" AND UPPER(t1.EJIE) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(usuario.getEjie().toUpperCase() +"%");
			}else{
				params.add("%"+usuario.getEjie().toUpperCase() +"%");
			}
			where.append(" AND t1.EJIE IS NOT NULL");
	     }			
		if (usuario  != null  && usuario.getFechaAlta() != null ) {
			where.append(" AND t1.FECHA_ALTA = ?");
			params.add(usuario.getFechaAlta());
	     }			
		if (usuario  != null  && usuario.getFechaBaja() != null ) {
			where.append(" AND t1.FECHA_BAJA = ?");
			params.add(usuario.getFechaBaja());
	    }		
		if (usuario  != null  && usuario.getRol() != null ) {
			where.append(" AND t1.ROL = ?");
			params.add(usuario.getRol());
	    }

		Map<String,Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);
		
		return mapWhere;		
	}
	
	/**
	 * Returns a map with the needed value to create the conditions to filter by  
	 * the Usuario2 entity 
	 * 
	 * @param usuario Usuario2
	 *            Bean with the criteria values to filter by.
     * @param startsWith Boolean	 
	 * @return Map created with two keys
	 *         key query stores the sql query syntax
	 *         key params stores the parameter values to be used in the condition sentence.
	 */
	private Map<String, Object> getWhereLikeMap (Usuario2 usuario, Boolean startsWith){
		
		StringBuffer where = new StringBuffer(TableUsuarioDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();

		if (usuario  != null  && usuario.getId() != null ) {
			where.append(" AND UPPER(t1.ID) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(usuario.getId().toUpperCase() +"%");
			}else{
				params.add("%"+usuario.getId().toUpperCase() +"%");
			}
			where.append(" AND t1.ID IS NOT NULL");
	     }			
		if (usuario  != null  && usuario.getNombre() != null ) {
			where.append(" AND UPPER(t1.NOMBRE) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(usuario.getNombre().toUpperCase() +"%");
			}else{
				params.add("%"+usuario.getNombre().toUpperCase() +"%");
			}
			where.append(" AND t1.NOMBRE IS NOT NULL");
	     }			
		if (usuario  != null  && usuario.getApellido1() != null ) {
			where.append(" AND UPPER(t1.APELLIDO1) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(usuario.getApellido1().toUpperCase() +"%");
			}else{
				params.add("%"+usuario.getApellido1().toUpperCase() +"%");
			}
			where.append(" AND t1.APELLIDO1 IS NOT NULL");
	     }			
		if (usuario  != null  && usuario.getApellido2() != null ) {
			where.append(" AND UPPER(t1.APELLIDO2) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(usuario.getApellido2().toUpperCase() +"%");
			}else{
				params.add("%"+usuario.getApellido2().toUpperCase() +"%");
			}
			where.append(" AND t1.APELLIDO2 IS NOT NULL");
	     }			
		if (usuario  != null  && usuario.getEjie() != null ) {
			where.append(" AND UPPER(t1.EJIE) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(usuario.getEjie().toUpperCase() +"%");
			}else{
				params.add("%"+usuario.getEjie().toUpperCase() +"%");
			}
			where.append(" AND t1.EJIE IS NOT NULL");
	     }			
		if (usuario  != null  && usuario.getFechaAlta() != null ) {
			where.append(" AND t1.FECHA_ALTA = ?");
			params.add(usuario.getFechaAlta());
	     }			
		if (usuario  != null  && usuario.getFechaBaja() != null ) {
			where.append(" AND t1.FECHA_BAJA = ?");
			params.add(usuario.getFechaBaja());
	    }		
		if (usuario  != null  && usuario.getRol() != null ) {
			where.append(" AND t1.ROL = ?");
			params.add(usuario.getRol());
	    }

		Map<String,Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);
		
		return mapWhere;		
	}
	
	/*
	 * ROW_MAPPERS
	 */
	
	private RowMapper<Usuario> rwMap = new RowMapper<Usuario>() {
		public Usuario mapRow(ResultSet resultSet, int rowNum) throws SQLException {
           Usuario usuario =  new Usuario(
               resultSet.getString("ID"), resultSet.getString("NOMBRE"), resultSet.getString("APELLIDO1"), resultSet.getString("APELLIDO2"), resultSet.getString("EJIE"), resultSet.getDate("FECHA_ALTA"), resultSet.getDate("FECHA_BAJA"), resultSet.getString("ROL")
           ); 
           return usuario;
          } 
	};
	
	private RowMapper<Usuario2> rwMap2 = new RowMapper<Usuario2>() {
		public Usuario2 mapRow(ResultSet resultSet, int rowNum) throws SQLException {
           Usuario2 usuario =  new Usuario2(
               resultSet.getString("ID"), resultSet.getString("NOMBRE"), resultSet.getString("APELLIDO1"), resultSet.getString("APELLIDO2"), resultSet.getString("EJIE"), resultSet.getDate("FECHA_ALTA"), resultSet.getDate("FECHA_BAJA"), resultSet.getString("ROL")
           ); 
           return usuario;
          } 
	};
	
	private RowMapper<Usuario> rwMapPK = new RowMapper<Usuario>() {
		public Usuario mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {
			return new Usuario(resultSet.getString("ID"));
		}
	};
	
	private RowMapper<Usuario2> rwMapPK2 = new RowMapper<Usuario2>() {
		public Usuario2 mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {
			return new Usuario2(resultSet.getString("ID"));
		}
	};
}