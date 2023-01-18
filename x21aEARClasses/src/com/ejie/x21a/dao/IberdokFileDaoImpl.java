package com.ejie.x21a.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x21a.model.IberdokFile;
import com.ejie.x38.dao.RowNumResultSetExtractor;
import com.ejie.x38.dto.TableManager;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableRowDto;

@Repository
@Transactional
public class IberdokFileDaoImpl implements IberdokFileDao {

	/**
	 * StringBuilder initilization value
	 */
	public static final int STRING_BUILDER_INIT = 4099;
	
	public static final String[] ORDER_BY_WHITE_LIST = new String[] {"ID", "ID_MODELO", "SEMILLA", "ID_DOCUMENTO", "ESTADO", "NOMBRE","USUARIO","FECHA_APP","FECHA_IBERDOK"};

	private JdbcTemplate jdbcTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(IberdokFileDaoImpl.class);

	/**
	 * Method use to set the datasource.
	 *
	 * @param dataSource
	 *            DataSource
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
	 * Inserts a single row in the IberdokFile table.
	 *
	 * @param file
	 *            Pagination
	 * @return IberdokFile
	 */
	public IberdokFile add(IberdokFile file) {

		final long nextId = jdbcTemplate
				.queryForObject("SELECT IBERDOK_SEQ.NEXTVAL FROM DUAL", Long.class);
		
		String query = "INSERT INTO IBERDOK_FILES (ID, ID_MODELO, SEMILLA, ID_DOCUMENTO, ESTADO, NOMBRE, USUARIO, FECHA_APP, FECHA_IBERDOK) VALUES (?,?,?,?,?,?,?,?,?)";
		IberdokFileDaoImpl.logger.info("[IBERDOKDAO - ADD - QUERY] : " + query);
		this.jdbcTemplate.update(query, nextId, file.getIdModelo(),
				file.getSemilla(), file.getIdDocumento(), file.getDocFinalizado(),
				file.getNombre(),file.getUsuario(),file.getFechaApp(),file.getFechaIberdok());
		IberdokFileDaoImpl.logger.info("[IBERDOKDAO - ADD] : " + file.toString());
		return file;
	}

	/**
	 * Updates a single row in the IberdokFile table.
	 *
	 * @param file
	 *            Pagination
	 * @return IberdokFile
	 */
	public IberdokFile update(IberdokFile file) {
		String query = "UPDATE IBERDOK_FILES SET NOMBRE=?, ID_DOCUMENTO=?, ESTADO=?, USUARIO=?, FECHA_APP=?, FECHA_IBERDOK=? WHERE ID=?";
		this.jdbcTemplate.update(query, file.getNombre(),
				file.getIdDocumento(), file.getDocFinalizado(), file.getUsuario(), file.getFechaApp(), file.getFechaIberdok(),file.getId());
		return file;
	}

	/**
	 * Updates a single row in the IberdokFile table.
	 *
	 * @param file
	 *            Pagination
	 * @return IberdokFile
	 */
	public IberdokFile updateIdDocumento(IberdokFile file) {

		String query = "UPDATE IBERDOK_FILES SET  ID_DOCUMENTO=?, ESTADO=? WHERE ID_DOCUMENTO=?";
		this.jdbcTemplate.update(query, file.getIdDocumento(), file.getDocFinalizado(), file.getIdDocumento());
		return file;
	}

	public IberdokFile updateLastRecord(IberdokFile file) {

		final long lastId = jdbcTemplate
				.queryForObject("select MAX(id) from IBERDOK_FILES", Long.class);

		String query = "UPDATE IBERDOK_FILES SET  ID_DOCUMENTO=?, ESTADO=? WHERE ID=?";
		this.jdbcTemplate.update(query, file.getIdDocumento(),
				file.getDocFinalizado(), lastId);
		return file;
	}

	/**
	 * Finds a single row in the IberdokFile table.
	 *
	 * @param file
	 *            Pagination
	 * @return IberdokFile
	 */
	@Transactional(readOnly = true)
	public IberdokFile find(IberdokFile file) {
		String query = "SELECT t1.ID ID, t1.ID_MODELO ID_MODELO, t1.SEMILLA SEMILLA, "
				+ "t1.ID_DOCUMENTO ID_DOCUMENTO, t1.ESTADO ESTADO , t1.NOMBRE, t1.USUARIO, t1.FECHA_APP, t1.FECHA_IBERDOK FROM IBERDOK_FILES t1  WHERE ";
		String buscarCampo = "";
		if(file.getId() != null){
			query = query + "t1.ID = ?";
			buscarCampo = file.getId();
		}
		if(file.getIdDocumento() != null){
			query = query + "t1.ID_DOCUMENTO = ?";
			buscarCampo = file.getIdDocumento();
		}
		List<IberdokFile> fileList = this.jdbcTemplate.query(query, this.rwMap,buscarCampo);
		return (IberdokFile) DataAccessUtils.uniqueResult(fileList);
	}

	/**
	 * Removes a single row in the IberdokFile table.
	 *
	 * @param file
	 *            Pagination
	 * @return
	 */
	public void remove(IberdokFile file) {
		String query = "DELETE FROM IBERDOK_FILES WHERE ID=?";
		this.jdbcTemplate.update(query, file.getId());
	}

	/**
	 * Finds a List of rows in the IberdokFile table.
	 * 
	 * @param file
	 *            IberdokFile
	 * @param tableRequestDto
	 *            TableRequestDto
	 * @return List
	 */
	@Transactional(readOnly = true)
	public List<IberdokFile> findAll(IberdokFile file, TableRequestDto tableRequestDto) {
		StringBuilder query = new StringBuilder("SELECT t1.ID ID, t1.ID_MODELO ID_MODELO, t1.SEMILLA SEMILLA, t1.ID_DOCUMENTO ID_DOCUMENTO, "
				+ "t1.ESTADO ESTADO , t1.NOMBRE NOMBRE, t1.USUARIO USUARIO, t1.FECHA_APP FECHA_APP, t1.FECHA_IBERDOK FECHA_IBERDOK");
		query.append("FROM IBERDOK_FILES t1 ");

		// Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereMap(file);
		StringBuilder where = new StringBuilder(" WHERE t1.ID_DOCUMENTO IS NOT NULL ");
		where.append(mapaWhere.get("query"));
		query.append(where);

		List<?> params = (List<?>) mapaWhere.get("params");

		if (tableRequestDto != null) {
			query = TableManager.getPaginationQuery(tableRequestDto, query, IberdokFileDaoImpl.ORDER_BY_WHITE_LIST);
		}

		return (List<IberdokFile>) this.jdbcTemplate.query(query.toString(),
				this.rwMap, params.toArray());
	}

	/**
	 * Finds rows in the IberdokFile table using like.
	 * 
	 * @param file
	 *            IberdokFile
	 * @param tableRequestDto
	 *            TableRequestDto
	 * @param startsWith
	 *            Boolean
	 * @return List
	 * 
	 * 
	 */
	@Transactional(readOnly = true)
	public List<IberdokFile> findAllLike(IberdokFile file, TableRequestDto tableRequestDto, Boolean startsWith) {
		StringBuilder query = new StringBuilder("SELECT t1.ID ID, t1.ID_MODELO ID_MODELO, t1.SEMILLA SEMILLA, "
				+ "t1.ID_DOCUMENTO ID_DOCUMENTO, t1.ESTADO ESTADO , t1.NOMBRE NOMBRE , t1.USUARIO USUARIO, t1.FECHA_APP FECHA_APP, t1.FECHA_IBERDOK FECHA_IBERDOK ");
		query.append("FROM IBERDOK_FILES t1 ");

		// Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereLikeMap(file, startsWith);
		StringBuilder where = new StringBuilder(" WHERE t1.ID_DOCUMENTO IS NOT NULL ");
		where.append(mapaWhere.get("query"));
		query.append(where);

		List<?> params = (List<?>) mapaWhere.get("params");

		if (tableRequestDto != null) {
			query = TableManager.getPaginationQuery(tableRequestDto, query, IberdokFileDaoImpl.ORDER_BY_WHITE_LIST);
		}
		List<IberdokFile> aux = null;
		try {
			aux = (List<IberdokFile>) this.jdbcTemplate.query(query.toString(),
					this.rwMap, params.toArray());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// return (List<IberdokFile>) this.jdbcTemplate.query(query.toString(),
		// this.rwMap, params.toArray());
		return aux;
	}

	/*
	 * OPERACIONES RUP_TABLE
	 */

	/**
	 * Counts rows in the IberdokFile table.
	 * 
	 * @param file
	 *            IberdokFile
	 * @return Long
	 */
	@Transactional(readOnly = true)
	public Long findAllCount(IberdokFile file) {
		StringBuilder query = new StringBuilder("SELECT COUNT(1) FROM IBERDOK_FILES t1 ");

		// Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereMap(file);
		StringBuilder where = new StringBuilder(" WHERE t1.ID_DOCUMENTO IS NOT NULL");
		where.append(mapaWhere.get("query"));
		query.append(where);

		List<?> params = (List<?>) mapaWhere.get("params");

		return this.jdbcTemplate.queryForObject(query.toString(), Long.class, params.toArray());
	}

	/**
	 * Counts rows in the IberdokFile table using like.
	 * 
	 * @param file
	 *            IberdokFile
	 * @param startsWith
	 *            Boolean
	 * @return Long
	 */
	@Transactional(readOnly = true)
	public Long findAllLikeCount(IberdokFile file, Boolean startsWith) {
		StringBuilder query = new StringBuilder("SELECT COUNT(1) FROM IBERDOK_FILES t1 ");

		// Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereLikeMap(file, startsWith);
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		where.append(mapaWhere.get("query"));
		query.append(where);

		List<?> params = (List<?>) mapaWhere.get("params");

		return this.jdbcTemplate.queryForObject(query.toString(), Long.class, params.toArray());
	}

	@Override
	public List<TableRowDto<IberdokFile>> reorderSelection(IberdokFile file, TableRequestDto tableRequestDto, Boolean startsWith) {

		// SELECT
		StringBuilder sbSQL = new StringBuilder("SELECT t1.ID ID, t1.ID_MODELO ID_MODELO, t1.SEMILLA SEMILLA, t1.ID_DOCUMENTO ID_DOCUMENTO,"
				+ " t1.ESTADO ESTADO , t1.USUARIO USUARIO, t1.FECHA_APP FECHA_APP, t1.FECHA_IBERDOK FECHA_IBERDOK");

		// FROM
		sbSQL.append("FROM IBERDOK_FILES t1 ");

		// FILTRADO
		Map<String, ?> mapaWhere = this.getWhereLikeMap(file, startsWith);
		// Claula where de filtrado
		sbSQL.append(" WHERE 1=1 ").append(mapaWhere.get("query"));
		// ParÃ¡metros de filtrado
		@SuppressWarnings("unchecked")
		List<Object> filterParamList = (List<Object>) mapaWhere.get("params");
		
		// SQL para la reordenación
		StringBuilder sbReorderSelectionSQL = TableManager.getReorderQuery(sbSQL, tableRequestDto, IberdokFile.class, filterParamList, "ID");

		return this.jdbcTemplate.query(sbReorderSelectionSQL.toString(), new RowNumResultSetExtractor<IberdokFile>(this.rwMapPK, tableRequestDto), filterParamList.toArray());
	}

	@Override
	public List<TableRowDto<IberdokFile>> search(IberdokFile filterParams, IberdokFile searchParams, TableRequestDto tableRequestDto, Boolean startsWith) {

		// SELECT
		StringBuilder sbSQL = new StringBuilder("SELECT t1.ID ID, t1.ID_MODELO ID_MODELO, t1.SEMILLA SEMILLA, "
				+ "t1.ID_DOCUMENTO ID_DOCUMENTO, t1.ESTADO ESTADO , t1.USUARIO USUARIO, t1.FECHA_APP FECHA_APP, t1.FECHA_IBERDOK FECHA_IBERDOK");

		// FROM
		sbSQL.append("FROM IBERDOK_FILES t1 ");

		// TABLAS_ALIAS
		List<String> from_alias = new ArrayList<String>();
		from_alias.add("t1");

		// FILTRADO
		// Mapa de filtrado
		Map<String, Object> mapaWhereFilter = this.getWhereLikeMap(filterParams, startsWith);
		// Claula where de filtrado
		sbSQL.append(" WHERE 1=1 ").append(mapaWhereFilter.get("query"));
		// ParÃ¡metros de filtrado
		@SuppressWarnings("unchecked")
		List<Object> filterParamList = (List<Object>) mapaWhereFilter.get("params");

		// BUSQUEDA
		Map<String, Object> mapaWhereSearch = this.getWhereLikeMap(searchParams, startsWith);
		// Claula where de bÃºsqueda
		String searchSQL = ((StringBuffer) mapaWhereSearch.get("query")).toString();
		// ParÃ¡metros de bÃºsqueda
		@SuppressWarnings("unchecked")
		List<Object> searchParamList = (List<Object>) mapaWhereSearch.get("params");

		// SQL para la busqueda
		StringBuilder sbReorderSelectionSQL = TableManager.getSearchQuery(sbSQL, tableRequestDto, IberdokFile.class, filterParamList, searchSQL, searchParamList, from_alias, "ID");

		return this.jdbcTemplate.query(sbReorderSelectionSQL.toString(), new RowNumResultSetExtractor<IberdokFile>(this.rwMapPK, tableRequestDto), filterParamList.toArray());
	}

	/*
	 * OPERACIONES RUP_TABLE
	 */

	@Override
	public void removeMultiple(IberdokFile filterIberdokFile, TableRequestDto tableRequestDto, Boolean startsWith) {		
		// Like clause and params
    	Map<String, Object> mapaWhereLike = this.getWhereLikeMap(filterIberdokFile, startsWith);

		StringBuilder sbRemoveMultipleSQL = TableManager.getRemoveMultipleQuery(mapaWhereLike, tableRequestDto, IberdokFile.class, "IBERDOK_FILES", "t1", new String[]{"ID"});

		// Params list. Includes needed params for like and IN/NOT IN clauses
		@SuppressWarnings("unchecked")
		List<Object> params = (List<Object>) mapaWhereLike.get("params");
		params.addAll(tableRequestDto.getMultiselection().getSelectedIds());
		
		this.jdbcTemplate.update(sbRemoveMultipleSQL.toString(), params.toArray());

	}

	/*
	 * MÃ‰TODOS PRIVADOS
	 */

	/**
	 * Returns a map with the needed value to create the conditions to filter by
	 * the IberdokFile entity
	 * 
	 * @param file
	 *            IberdokFile Bean with the criteria values to filter by.
	 * @return Map created with two keys key query stores the sql query syntax
	 *         key params stores the parameter values to be used in the
	 *         condition sentence.
	 */
	// CHECKSTYLE:OFF CyclomaticComplexity - GeneraciÃ³n de cÃ³digo de UDA
	private Map<String, ?> getWhereMap(IberdokFile file) {

		StringBuffer where = new StringBuffer(
				IberdokFileDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();

		if (file != null && file.getId() != null) {
			where.append(" AND t1.ID = ?");
			params.add(file.getId());
		}
		if (file != null && file.getIdModelo() != null) {
			where.append(" AND t1.ID_MODELO = ?");
			params.add(file.getIdModelo());
		}
		if (file != null && file.getSemilla() != null) {
			where.append(" AND t1.SEMILLA = ?");
			params.add(file.getSemilla());
		}
		if (file != null && file.getIdDocumento() != null) {
			where.append(" AND t1.ID_DOCUMENTO = ?");
			params.add(file.getIdDocumento());
		}
		if (file != null && file.getDocFinalizado() != null) {
			if(file.getDocFinalizado() == 1){
				params.add("1");	
			}else if(file.getDocFinalizado() == 2 ){
				params.add("2");
			}else{
				params.add("0");
			}
			where.append(" AND t1.ESTADO = ?");
			
		}
		if (file != null && file.getNombre() != null) {
			where.append(" AND t1.NOMBRE = ?");
			params.add(file.getNombre());
		}

		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return mapWhere;
	}

	// CHECKSTYLE:ON CyclomaticComplexity - GeneraciÃ³n de cÃ³digo de UDA

	/**
	 * Returns a map with the needed value to create the conditions to filter by
	 * the IberdokFile entity
	 * 
	 * @param file
	 *            IberdokFile Bean with the criteria values to filter by.
	 * @param startsWith
	 *            Boolean
	 * @return Map created with two keys key query stores the sql query syntax
	 *         key params stores the parameter values to be used in the
	 *         condition sentence.
	 */
	// CHECKSTYLE:OFF CyclomaticComplexity - GeneraciÃ³n de cÃ³digo de UDA
	private Map<String, Object> getWhereLikeMap(IberdokFile file,
			Boolean startsWith) {

		StringBuffer where = new StringBuffer(
				IberdokFileDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();

		if (file != null && file.getId() != null) {
			where.append(" AND UPPER(t1.ID) like ? ESCAPE  '\\'");
			if (startsWith) {
				params.add(file.getId().toUpperCase() + "%");
			} else {
				params.add("%" + file.getId().toUpperCase() + "%");
			}
			where.append(" AND t1.ID IS NOT NULL");
		}
		if (file != null && file.getIdModelo() != null) {
			where.append(" AND UPPER(t1.ID_MODELO) like ? ESCAPE  '\\'");
			if (startsWith) {
				params.add(file.getIdModelo().toUpperCase() + "%");
			} else {
				params.add("%" + file.getIdModelo().toUpperCase() + "%");
			}
			where.append(" AND t1.ID_MODELO IS NOT NULL");
		}
		if (file != null && file.getSemilla() != null) {
			where.append(" AND UPPER(t1.SEMILLA) like ? ESCAPE  '\\'");
			if (startsWith) {
				params.add(file.getSemilla().toUpperCase() + "%");
			} else {
				params.add("%" + file.getSemilla().toUpperCase() + "%");
			}
			where.append(" AND t1.SEMILLA IS NOT NULL");
		}
		if (file != null && file.getIdDocumento() != null) {
			where.append(" AND UPPER(t1.ID_DOCUMENTO) like ? ESCAPE  '\\'");
			if (startsWith) {
				params.add(file.getIdDocumento().toUpperCase() + "%");
			} else {
				params.add("%" + file.getIdDocumento().toUpperCase() + "%");
			}
			where.append(" AND t1.ID_DOCUMENTO IS NOT NULL");
		}
		if (file != null && file.getDocFinalizado() != null) {
			if (file != null && file.getDocFinalizado() != null) {
				if(file.getDocFinalizado() == 1){
					params.add("1");	
				}else if(file.getDocFinalizado() == 2 ){
					params.add("2");
				}else{
					params.add("0");
				}
				where.append(" AND t1.ESTADO = ?");
				
			}
			where.append(" AND t1.ESTADO = ?");
		}
		if (file != null && file.getNombre()!= null) {
			where.append(" AND UPPER(t1.NOMBRE) like ? ESCAPE  '\\'");
			if (startsWith) {
				params.add(file.getNombre().toUpperCase() + "%");
			} else {
				params.add("%" + file.getNombre().toUpperCase() + "%");
			}
			where.append(" AND t1.NOMBRE IS NOT NULL");
		}

		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return mapWhere;
	}

	// CHECKSTYLE:ON CyclomaticComplexity - GeneraciÃ³n de cÃ³digo de UDA

	/*
	 * ROW_MAPPERS
	 */

	private RowMapper<IberdokFile> rwMap = new RowMapper<IberdokFile>() {
		public IberdokFile mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {
			IberdokFile file = new IberdokFile();
			file.setId(resultSet.getString("ID"));
			file.setIdModelo(resultSet.getString("ID_MODELO"));
			file.setSemilla(resultSet.getString("SEMILLA"));
			file.setIdDocumento(resultSet.getString("ID_DOCUMENTO"));
			file.setDocFinalizado(resultSet.getInt("ESTADO"));
			file.setNombre(resultSet.getString("NOMBRE"));
			file.setUsuario(resultSet.getString("USUARIO"));
			file.setFechaApp(resultSet.getTimestamp("FECHA_APP"));
			file.setFechaIberdok(resultSet.getTimestamp("FECHA_IBERDOK"));

			return file;
		}
	};

	private RowMapper<IberdokFile> rwMapPK = new RowMapper<IberdokFile>() {
		public IberdokFile mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {
			return new IberdokFile(resultSet.getString("ID"));
		}
	};

	
	public IberdokFile findLastByIdCorrelacion(String idModelo, String idCorrelacion) {
		String query = "SELECT t1.ID ID, t1.ID_MODELO ID_MODELO, t1.SEMILLA SEMILLA, t1.ID_DOCUMENTO ID_DOCUMENTO, "
				+ "t1.ESTADO ESTADO , t1.NOMBRE, t1.USUARIO USUARIO, t1.FECHA_APP FECHA_APP, t1.FECHA_IBERDOK FECHA_IBERDOK FROM IBERDOK_FILES t1  WHERE ";
		String and = "";
		List<Object> params = new ArrayList<Object>();
		if(idModelo != null){
			query = query + "t1.ID_MODELO = ?";
			params.add(idModelo);
			and = " AND ";
		}
		if(idCorrelacion != null){
			query = query + and+" t1.NOMBRE = ?";
			params.add(idCorrelacion);
		}
		
		query = query + " ORDER BY t1.id DESC";
		
		List<IberdokFile> fileList = this.jdbcTemplate.query(query, this.rwMap,params.toArray());
		IberdokFile file = null;
		if(fileList != null && fileList.size() > 0){
			file = fileList.get(0);
		}
		return file;
	}

}