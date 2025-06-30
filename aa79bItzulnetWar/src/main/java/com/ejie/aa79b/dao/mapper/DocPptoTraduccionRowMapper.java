package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.DocPresupuestoTraduccionInfoExped;

public class DocPptoTraduccionRowMapper implements RowMapper<DocPresupuestoTraduccionInfoExped> {
	@Override
	public DocPresupuestoTraduccionInfoExped mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		DocPresupuestoTraduccionInfoExped docPresupuestoTraduccionInfoExped = new DocPresupuestoTraduccionInfoExped();
		docPresupuestoTraduccionInfoExped.setAnyo(resultSet.getLong("ANYO_051"));
		docPresupuestoTraduccionInfoExped.setNumExp(resultSet.getInt("NUM_EXP_051"));
		docPresupuestoTraduccionInfoExped.setIdTipoExpediente(resultSet.getString("ID_TIPO_EXPEDIENTE_051"));
		docPresupuestoTraduccionInfoExped.setTipoExpedienteDesc(resultSet.getString("TIPO_EXP_DESC"));
		docPresupuestoTraduccionInfoExped.setTipoExpDesc(resultSet.getString("TIPO_EXPEDIENTE_DESCRIPCION"));
		docPresupuestoTraduccionInfoExped.setTitulo(resultSet.getString("TITULO_051"));
		docPresupuestoTraduccionInfoExped.setFechaAlta(resultSet.getDate("FECHA_ALTA_051"));
		docPresupuestoTraduccionInfoExped.setHoraAlta(resultSet.getString("HORA_ALTA_051"));
		docPresupuestoTraduccionInfoExped.setFechaFinalIzo(resultSet.getDate("FECHA_FINAL_IZO_053"));
		docPresupuestoTraduccionInfoExped.setHoraFinalIzo(resultSet.getString("HORA_FINAL_IZO_053"));
		docPresupuestoTraduccionInfoExped.setIndPublicarBopv(resultSet.getString("IND_PUBLICAR_BOPV_053"));
		docPresupuestoTraduccionInfoExped.setPublicarBopvDesc(resultSet.getString("PUBLICAR_BOPV_DESC_053"));
		docPresupuestoTraduccionInfoExped.setIndPrevistoBopv(resultSet.getString("IND_PREVISTO_BOPV_053"));
		docPresupuestoTraduccionInfoExped.setIdIdioma(resultSet.getLong("ID_IDIOMA_053"));
		docPresupuestoTraduccionInfoExped.setIdiomaDescEs(resultSet.getString("DESC_IDIOMA_ES_009"));
		docPresupuestoTraduccionInfoExped.setIdiomaDescEu(resultSet.getString("DESC_IDIOMA_EU_009"));
		docPresupuestoTraduccionInfoExped.setIdIdioma(resultSet.getLong("ID_IDIOMA_053"));
		docPresupuestoTraduccionInfoExped.setIdRelevancia(resultSet.getLong("ID_RELEVANCIA_053"));
		docPresupuestoTraduccionInfoExped.setRelevanciaDescEs(resultSet.getString("DESC_RELEVANCIA_ES_010"));
		docPresupuestoTraduccionInfoExped.setRelevanciaDescEu(resultSet.getString("DESC_RELEVANCIA_EU_010"));
		docPresupuestoTraduccionInfoExped.setIndUrgente(resultSet.getString("IND_URGENTE_053"));
		docPresupuestoTraduccionInfoExped.setUrgenteDesc(resultSet.getString("URGENTE_DESC_053"));
		docPresupuestoTraduccionInfoExped.setIndDificil(resultSet.getString("IND_DIFICIL_053"));
		docPresupuestoTraduccionInfoExped.setDificilDesc(resultSet.getString("DIFICIL_DESC_053"));
		return docPresupuestoTraduccionInfoExped;
	}
}