package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.DocumentoTarea;
import com.ejie.aa79b.utils.QueryUtils;

/**
 * @author javarona
 *
 */
public class DocumentoTareaXliffRowMapper implements RowMapper<DocumentoTarea> {

	@Override()
	public DocumentoTarea mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		final QueryUtils queryUtils = new QueryUtils();
		return queryUtils.getDocumentoTareaXliffRwMp(resultSet, false);
	}
}
