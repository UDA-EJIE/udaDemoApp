package com.ejie.aa79b.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.dao.mapper.PlantillasRowMapper;
import com.ejie.aa79b.model.EntradaPlantilla;
import com.ejie.aa79b.model.FicheroWS;
import com.ejie.aa79b.model.Plantillas;

@Repository
@Transactional
public class PlantillasDaoImpl extends GenericoDaoImpl<Plantillas> implements PlantillasDao {

	public PlantillasDaoImpl() {
		super(Plantillas.class);
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(PlantillasDaoImpl.class);

	protected static final String[] ORDER_BY_WHITE_LIST = new String[] { DBConstants.ID0A7,
			DBConstants.NOMBREPLANTILLA0A7, DBConstants.VARPLANTILLA0A7, DBConstants.IDFICHEROPLANTILLA0A7,
			DBConstants.OIDFICHERO088 };

	/*
	 * ROW_MAPPERS
	 */
	private RowMapper<Plantillas> rwMap = new PlantillasRowMapper();

	private RowMapper<Plantillas> rwMapPK = new PlantillasRowMapper();

	private RowMapper<FicheroWS> rwFichero = new RowMapper<FicheroWS>() {
		@Override()
		public FicheroWS mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			FicheroWS fichero = new FicheroWS();
			fichero.setNombre(resultSet.getString("NOMBREFICHERO"));
			fichero.setOid(resultSet.getString("OIDFICHERO"));
			fichero.setContentType(resultSet.getString("CONTENTTYPEFICHERO"));
			fichero.setExtension(resultSet.getString("EXTENSIONFICHERO"));
			fichero.setTamano(resultSet.getLong("TAMANOFICHERO"));
			return fichero;
		}
	};

	/*
	 * OPERACIONES CRUD
	 */

	@Override
	public Plantillas add(Plantillas plantillas) {
		final Long elId = this.getNextVal("AA79BA7Q00");
		StringBuilder queryAP = new StringBuilder(PlantillasDaoImpl.STRING_BUILDER_INIT);
		List<Object> paramsAP = new ArrayList<Object>();
		plantillas.setId0A7(elId);
		paramsAP.add(plantillas.getId0A7());
		paramsAP.add(plantillas.getNombrePlantilla0A7());
		paramsAP.add(plantillas.getVarPlantilla0A7());
		paramsAP.add(plantillas.getIdFicheroPlantilla0A7());
		queryAP.append(
				"INSERT INTO AA79BA7S01 (ID_0A7,NOMBRE_PLANTILLA_0A7,VAR_PLANTILLA_0A7,ID_FICHERO_PLANTILLA_0A7) VALUES (?,?,?,?)");
		this.getJdbcTemplate().update(queryAP.toString(), paramsAP.toArray());
		return plantillas;

	}

	/**
	 * Updates a single row in the Plantillas table.S
	 *
	 * @param motivosrechazo Plantillas
	 * @return Plantillas
	 */
	@Override
	public Plantillas update(Plantillas plantillas) {
		String query = "UPDATE AA79BA7T00 SET NOMBRE_PLANTILLA_0A7=? ,VAR_PLANTILLA_0A7=?,  ID_FICHERO_PLANTILLA_0A7=? WHERE ID_0A7=?";
		this.getJdbcTemplate().update(query, plantillas.getNombrePlantilla0A7(), plantillas.getVarPlantilla0A7(),
				plantillas.getIdFicheroPlantilla0A7(), plantillas.getId0A7());
		return plantillas;
	}

	/**
	 * Finds a single row in the Plantillas table.
	 *
	 * @param motivosrechazo Plantillas
	 * @return Plantillas
	 */
	@Override()
	protected String getSelect() {

		StringBuilder selectPlantillas = new StringBuilder();

		selectPlantillas.append("SELECT  t1.ID_0A7 ID0A7");
		selectPlantillas.append(",  t1.NOMBRE_PLANTILLA_0A7 NOMBREPLANTILLA0A7");
		selectPlantillas.append(", t1.VAR_PLANTILLA_0A7 VARPLANTILLA0A7");
		selectPlantillas.append(", t1.ID_FICHERO_PLANTILLA_0A7 IDFICHEROPLANTILLA0A7");

		selectPlantillas.append(", t2.ID_FICHERO_088 IDFICHERO088");
		selectPlantillas.append(", t2.TITULO_FICHERO_088 TITULOFICHERO088");
		selectPlantillas.append(", t2.EXTENSION_FICHERO_088 EXTENSIONFICHERO088");
		selectPlantillas.append(", t2.CONTENT_TYPE_FICHERO_088 CONTENTTYPEFICHERO088");
		selectPlantillas.append(", t2.TAMANO_FICHERO_088 TAMANOFICHERO088");
		selectPlantillas.append(", t2.IND_ENCRIPTADO_088 INDENCRIPTADO088");
		selectPlantillas.append(", t2.RUTA_PIF_FICHERO_088 RUTAPIFFICHERO088");
		selectPlantillas.append(", t2.OID_FICHERO_088 OIDFICHERO088");
		selectPlantillas.append(", t2.NOMBRE_FICHERO_088 NOMBREFICHERO088");

		return selectPlantillas.toString();
	}

	@Override()
	protected String getFrom() {
		StringBuilder query = new StringBuilder();
		query.append(" FROM AA79BA7T00 t1");
		query.append(" JOIN AA79B88T00 t2 ON t1.ID_FICHERO_PLANTILLA_0A7 = t2.ID_FICHERO_088 ");
		return query.toString();
	}

	@Override()
	protected RowMapper<Plantillas> getRwMap() {
		return this.rwMap;
	}

	@Override()
	protected String[] getOrderBy() {
		return PlantillasDaoImpl.ORDER_BY_WHITE_LIST;
	}

	@Override()
	protected String getPK() {
		return DBConstants.ID_0A7;
	}

	@Override()
	protected RowMapper<Plantillas> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override()
	protected String getWherePK(Plantillas bean, List<Object> params) {
		params.add(bean.getId0A7());
		return " WHERE t1.ID_0A7 = ?";
	}

	@Override
	protected String getWhere(Plantillas bean, List<Object> params) {
		StringBuilder where = new StringBuilder(PlantillasDaoImpl.STRING_BUILDER_INIT);

		where.append(SqlUtils.generarWhereIgual("t1.ID_0A7", bean.getId0A7(), params));

		where.append(SqlUtils.generarWhereLikeNormalizado("t1.NOMBRE_PLANTILLA_0A7", bean.getNombrePlantilla0A7(),
				params, false));

		where.append(
				SqlUtils.generarWhereLikeNormalizado("t1.VAR_PLANTILLA_0A7", bean.getVarPlantilla0A7(), params, false));

		where.append(
				SqlUtils.generarWhereIgual("t1.ID_FICHERO_PLANTILLA_0A7", bean.getIdFicheroPlantilla0A7(), params));
		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);
		return where.toString();

	}

	@Override
	protected String getWhereLike(Plantillas bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder where = new StringBuilder(PlantillasDaoImpl.STRING_BUILDER_INIT);

		where.append(SqlUtils.generarWhereIgual("t1.ID_0A7", bean.getId0A7(), params));

		where.append(SqlUtils.generarWhereLikeNormalizado("t1.NOMBRE_PLANTILLA_0A7", bean.getNombrePlantilla0A7(),
				params, startsWith));

		where.append(SqlUtils.generarWhereLikeNormalizado("t1.VAR_PLANTILLA_0A7", bean.getVarPlantilla0A7(), params,
				startsWith));

		where.append(
				SqlUtils.generarWhereIgual("t1.ID_FICHERO_PLANTILLA_0A7", bean.getIdFicheroPlantilla0A7(), params));
		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return where.toString();
	}

	@Override
	public FicheroWS obtenerDatosDocPlantilla(EntradaPlantilla bean) {
		StringBuilder queryODDP = new StringBuilder(PlantillasDaoImpl.STRING_BUILDER_INIT);
		List<Object> paramsODDP = new ArrayList<Object>();
		queryODDP.append("SELECT t2.OID_FICHERO_088 OIDFICHERO, ");
		queryODDP.append("t2.NOMBRE_FICHERO_088 NOMBREFICHERO, ");
		queryODDP.append("t2.CONTENT_TYPE_FICHERO_088 CONTENTTYPEFICHERO, ");
		queryODDP.append("t2.EXTENSION_FICHERO_088 EXTENSIONFICHERO, ");
		queryODDP.append("t2.TAMANO_FICHERO_088 TAMANOFICHERO ");
		queryODDP.append(this.getFrom());
		queryODDP.append(SqlUtils.generarWhereIgual("t1.ID_0A7", bean.getIdPlantilla(), paramsODDP));
		try {
			return this.getJdbcTemplate().queryForObject(queryODDP.toString(), this.rwFichero, paramsODDP.toArray());
		} catch (EmptyResultDataAccessException e) {
			PlantillasDaoImpl.LOGGER.info("Error: ", e);
			return null;
		}
	}
}
