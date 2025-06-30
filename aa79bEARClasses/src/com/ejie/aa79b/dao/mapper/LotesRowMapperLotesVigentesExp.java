package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.EmpresasProveedoras;
import com.ejie.aa79b.model.Lotes;
import com.ejie.aa79b.model.Persona;

public class LotesRowMapperLotesVigentesExp implements RowMapper<Lotes> {

	@Override()
	public Lotes mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		final Lotes lotes = new Lotes();
		lotes.setIdLote(resultSet.getInt(DBConstants.IDLOTE));

		EmpresasProveedoras empresasProveedoras = new EmpresasProveedoras();
		empresasProveedoras.setTipo(resultSet.getString(DBConstants.TIPOENTIDAD));
		empresasProveedoras.setCodigo(resultSet.getLong("IDEMPRESAPROV"));
		empresasProveedoras.setDescAmpEs(resultSet.getString(DBConstants.DESCAMPES));
		empresasProveedoras.setDescAmpEu(resultSet.getString(DBConstants.DESCAMPEU));
		empresasProveedoras.setDescEs(resultSet.getString(DBConstants.DESCES));
		empresasProveedoras.setDescEu(resultSet.getString(DBConstants.DESCEU));
		lotes.setEmpresasProveedoras(empresasProveedoras);

		lotes.setNombreLote(resultSet.getString(DBConstants.NOMBRELOTE));
		lotes.setDescLoteEu(resultSet.getString("DESCLOTEEU"));
		lotes.setDescLoteEs(resultSet.getString("DESCLOTEES"));

		Persona contacto = new Persona();
		contacto.setDni(resultSet.getString("DNICONTACTO"));
		lotes.setContacto(contacto);

		lotes.setFechaInicio(resultSet.getDate("FECHAINICIO"));
		lotes.setFechaFin(resultSet.getDate(DBConstants.FECHAFIN));
		lotes.setImporteAsignado(resultSet.getBigDecimal("IMPORTEASIGNADO"));
		lotes.setImporteConsumido(resultSet.getBigDecimal("IMPORTECONSUMIDO"));
		lotes.setIdIdiomaDestino(resultSet.getLong("IDIDIOMADESTINO"));
		lotes.setImportePalabra(resultSet.getBigDecimal("IMPORTEPALABRA"));
		lotes.setPorPagoPalConcor8594(resultSet.getLong("PORPAGOPALCONCOR8594"));
		lotes.setPorPagoPalConcor95100(resultSet.getLong("PORPAGOPALCONCOR95100"));
		lotes.setPorRevision(resultSet.getLong("PORREVISION"));
		lotes.setPorRecargoFormato(resultSet.getLong("PORRECARGOFORMATO"));
		lotes.setIndRecargoApremio(resultSet.getString("INDRECARGOAPREMIO"));
		lotes.setPorRecargoApremio(resultSet.getLong("PORRECARGOAPREMIO"));
		lotes.setIdTipoPenalizacion(resultSet.getString("IDTIPOPENALIZACION"));
		lotes.setPorPenalizacion(resultSet.getLong("PORPENALIZACION"));
		lotes.setResolucion(resultSet.getString("RESOLUCION"));
		lotes.setImportePrevisto(resultSet.getBigDecimal("IMPORTEPREVISTO"));
		lotes.setImporteRestante(resultSet.getBigDecimal("IMPORTERESTANTE"));
		lotes.setImporteDisponible(resultSet.getBigDecimal("IMPORTEDISPONIBLE"));
		lotes.setCosteExpediente(resultSet.getBigDecimal("COSTEEXPEDIENTE"));

		return lotes;
	}

}
