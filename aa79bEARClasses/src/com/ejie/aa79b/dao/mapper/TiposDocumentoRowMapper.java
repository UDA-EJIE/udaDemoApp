package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.TipoRelevancia;
import com.ejie.aa79b.model.TiposDocumento;

/**
 * @author mrodriguez
 *
 */
public class TiposDocumentoRowMapper implements RowMapper<TiposDocumento> {

	@Override()
	public TiposDocumento mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		final TiposDocumento tiposDocumento = new TiposDocumento();
		tiposDocumento.setId(resultSet.getLong(DBConstants.ID));
		tiposDocumento.setDescEs(resultSet.getString(DBConstants.DESCES));
		tiposDocumento.setDescEu(resultSet.getString(DBConstants.DESCEU));
		tiposDocumento.setIndFacturable(resultSet.getString("INDFACTURABLE"));
		tiposDocumento.setIndConfidencial(resultSet.getString("INDCONFIDENCIAL"));
		tiposDocumento.setIndRecargoUrgencia(resultSet.getString("INDRECARGOURGENCIA"));
		tiposDocumento.setEstado(resultSet.getString(DBConstants.ESTADO));
		tiposDocumento.setEstadoDesc(resultSet.getString(DBConstants.ESTADODESC));
		tiposDocumento.setIndFacturableDesc(resultSet.getString("INDFACTURABLEDESC"));
		tiposDocumento.setIndConfidencialDesc(resultSet.getString("INDCONFIDENCIALDESC"));
		tiposDocumento.setIndRecargoUrgenciaDesc(resultSet.getString("INDRECARGOURGENCIADESC"));
		tiposDocumento.setIndActMemoria(resultSet.getString("INDACTMEMORIA"));
		tiposDocumento.setIndActMemoriaDesc(resultSet.getString("INDACTMEMORIADESC"));
		TipoRelevancia tipoRelevancia = new TipoRelevancia(resultSet.getLong("IDTIPORELEVANCIA"));
		tipoRelevancia.setDescRelevanciaEs(resultSet.getString(DBConstants.DESCRELEVANCIA));
		tipoRelevancia.setDescRelevanciaEu(resultSet.getString(DBConstants.DESCRELEVANCIA));
		tipoRelevancia.setPrioridad(resultSet.getLong("PRIORIDAD"));

		tiposDocumento.setTipoRelevancia(tipoRelevancia);

		tiposDocumento.setTipoRelevanciaDesc(resultSet.getString("DESCRELEVANCIA"));
		tiposDocumento.setOrden(resultSet.getInt("ORDEN"));
		return tiposDocumento;
	}

}
