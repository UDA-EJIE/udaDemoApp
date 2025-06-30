/**
 * 
 */
package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.DatosTareaTrados;

/**
 * @author eaguirresarobe
 *
 */
public class DatosTareaTradosConsultaConsultaRowMapper implements RowMapper<DatosTareaTrados> {

	@Override
	public DatosTareaTrados mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DatosTareaTrados datosTareaTrados = new DatosTareaTrados();
		datosTareaTrados.setNumTotalPal(resultSet.getInt(DBConstants.NUM_TOTAL_PAL_090));
		datosTareaTrados.setNumPalConcor084(resultSet.getInt(DBConstants.NUMPALCONCOR084));
		datosTareaTrados.setNumPalConcor8594(resultSet.getInt(DBConstants.NUMPALCONCOR8594));
		datosTareaTrados.setNumPalConcor95100(resultSet.getInt(DBConstants.NUMPALCONCOR95100));

		return datosTareaTrados;
	}

}
