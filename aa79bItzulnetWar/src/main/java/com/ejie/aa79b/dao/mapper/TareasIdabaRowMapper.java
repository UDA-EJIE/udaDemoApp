package com.ejie.aa79b.dao.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.ObservacionesTareas;
import com.ejie.aa79b.model.TareaIdaba;

public class TareasIdabaRowMapper implements RowMapper<TareaIdaba> {

    @Override()
    public TareaIdaba mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        TareaIdaba tareaIdaba = new TareaIdaba();
        tareaIdaba.setIndPrioritario(resultSet.getString("INDPRIORITARIO"));
        tareaIdaba.setTituloExpediente(resultSet.getString("TITULOEXP"));
        tareaIdaba.setDescReles(resultSet.getString("RELES"));
        tareaIdaba.setDescReleu(resultSet.getString("RELEU"));
        tareaIdaba.setNumExpediente(resultSet.getLong("NUMEXP"));
        tareaIdaba.setNombreTradCompleto(
                resultSet.getString("NOMBRE") + " " + resultSet.getString("APE1") + " " + resultSet.getString("APE2"));
        ObservacionesTareas obs = new ObservacionesTareas();
        obs.setIdTarea(new BigDecimal(resultSet.getString("IDTAREA")));
        obs.setObsvEjecucion(resultSet.getString("OBSERVACIONES"));
        tareaIdaba.setObservaciones(obs);

        return tareaIdaba;
    }
}
