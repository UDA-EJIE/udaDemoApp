package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.ExpedienteTradRev;

/**
 * @author mrodriguez
 *
 */
public class ExpedienteTradRevRowMapper implements RowMapper<ExpedienteTradRev> {
		
	@Override()
    public ExpedienteTradRev mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		  ExpedienteTradRev expedienteTradRev = new ExpedienteTradRev();
		  
		  expedienteTradRev.setAnyo(resultSet.getLong("ANYO_053"));
		  expedienteTradRev.setNumExp(resultSet.getInt("NUM_EXP_053"));
          expedienteTradRev.setIndPublicarBopv(resultSet.getString("IND_PUBLICAR_BOPV_053"));
          expedienteTradRev.setPublicarBopvDescEs(resultSet.getString("PUBLICARBOPVDESCES"));
          expedienteTradRev.setPublicarBopvDescEu(resultSet.getString("PUBLICARBOPVDESCEU"));
          expedienteTradRev.setIndPrevistoBopv(resultSet.getString("IND_PREVISTO_BOPV_053"));
          expedienteTradRev.setIdIdioma(resultSet.getLong("ID_IDIOMA_053"));
          expedienteTradRev.setIndConfidencial(resultSet.getString("IND_CONFIDENCIAL_053"));
          expedienteTradRev.setIndCorredaccion(resultSet.getString("IND_CORREDACCION_053"));
          expedienteTradRev.setTexto(resultSet.getString("TEXTO_053"));
          expedienteTradRev.setTipoRedaccion(resultSet.getString("TIPO_REDACCION_053"));
          expedienteTradRev.setParticipantes(resultSet.getString("PARTICIPANTES_053"));
          expedienteTradRev.setRefTramitagune(resultSet.getString("REF_TRAMITAGUNE_053"));
          expedienteTradRev.setNumTotalPalIzo(resultSet.getInt("NUM_TOTAL_PAL_IZO_053"));
          expedienteTradRev.setFechaFinalIzo(resultSet.getDate("FECHA_FINAL_IZO_053"));	          
          expedienteTradRev.setHoraFinalIzo(resultSet.getString("HORA_FINAL_IZO_053"));
          expedienteTradRev.setIndFacturable(resultSet.getString("IND_FACTURABLE_053"));
          expedienteTradRev.setIdRelevancia(resultSet.getLong("ID_RELEVANCIA_053"));
          expedienteTradRev.setIndUrgente(resultSet.getString("IND_URGENTE_053"));
          expedienteTradRev.setFechaFinalSolic(resultSet.getTimestamp("FECHA_FINAL_SOLIC_053"));     
          expedienteTradRev.setFechaFinalProp(resultSet.getTimestamp("FECHA_FINAL_PROP_053"));     
          expedienteTradRev.setIndPresupuesto(resultSet.getString("IND_PRESUPUESTO_053"));
          expedienteTradRev.setNumTotalPalSolic(resultSet.getInt("NUM_TOTAL_PAL_SOLIC_053"));
          expedienteTradRev.setIndObservaciones(resultSet.getString("IND_OBSERVACIONES_053"));
          expedienteTradRev.setIndDificil(resultSet.getString("IND_DIFICIL_053"));
          return expedienteTradRev;
       } 

}
