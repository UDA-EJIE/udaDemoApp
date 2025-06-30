package com.ejie.aa79b.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.NotasExpedientes;

@Repository
@Transactional
public class NotaExpedienteDaoImpl extends GenericoDaoImpl<NotasExpedientes> implements NotaExpedienteDao {

	public NotaExpedienteDaoImpl() {
		super(NotasExpedientes.class);
	}

	public static final String[] ORDER_BY_WHITE_LIST = new String[] { "FECHA_0B2" };

	private RowMapper<NotasExpedientes> rwMap = new RowMapper<NotasExpedientes>() {
		@Override
		public NotasExpedientes mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			NotasExpedientes notaExpediente = new NotasExpedientes();
			notaExpediente.setIdNota(resultSet.getInt("ID_NOTA_0B3"));
			notaExpediente.setAnyo(resultSet.getInt("ANYO_0B2"));
			notaExpediente.setNumExp(resultSet.getInt("NUM_EXP_0B2"));
			notaExpediente.setUsuario(resultSet.getString("DNI_USUARIO_0B2"));
			notaExpediente.setFechaNota(resultSet.getDate("FECHA_0B2"));
			notaExpediente.setHoraNota(resultSet.getString("HORA_0B2"));
			notaExpediente.setNota(SqlUtils.getClob(resultSet, "OBSV_0B2"));

			return notaExpediente;

		}
	};

	private RowMapper<NotasExpedientes> rwMapPK = new RowMapper<NotasExpedientes>() {
		@Override
		public NotasExpedientes mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			NotasExpedientes notaExpediente = new NotasExpedientes();
			notaExpediente.setIdNota(resultSet.getInt("ID_NOTA_0B3"));

			return notaExpediente;

		}
	};

	@Override
	protected String getSelect() {
		StringBuilder select = new StringBuilder();
		select.append(" SELECT 	ID_NOTA_0B3, ");
		select.append(
				" ANYO_0B2,NUM_EXP_0B2,DNI_USUARIO_0B2,FECHA_0B2,TO_CHAR(FECHA_0B2,'HH24:MI') HORA_0B2,OBSV_0B2 ");

		return select.toString();

	}

	@Override
	protected String getFrom() {
		StringBuilder from = new StringBuilder();
		from.append(" FROM AA79BB3T00");

		return from.toString();
	}

	@Override
	protected RowMapper<NotasExpedientes> getRwMap() {
		return this.rwMap;
	}

	@Override
	protected String[] getOrderBy() {
		return ORDER_BY_WHITE_LIST;
	}

	@Override
	protected String getPK() {
		return "ID_NOTA_0B3";
	}

	@Override
	protected RowMapper<NotasExpedientes> getRwMapPK() {
		return this.rwMapPK;
	}

	@Override
	protected String getWherePK(NotasExpedientes bean, List<Object> params) {
		params.add(bean.getIdNota());
		return " WHERE ID_NOTA_0B3 = ? ";
	}

	@Override
	protected String getWhere(NotasExpedientes bean, List<Object> params) {
		StringBuilder where = new StringBuilder();
		params.add(bean.getAnyo());
		params.add(bean.getNumExp());
		where.append(" AND ANYO_0B2 = ? ");
		where.append(" AND NUM_EXP_0B2 = ? ");
		return where.toString();
	}

	@Override
	protected String getWhereLike(NotasExpedientes bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder where = new StringBuilder();

		return where.toString();
	}

	@Override
	public NotasExpedientes add(NotasExpedientes bean) {

		StringBuilder queryId = new StringBuilder("SELECT AA79BB3Q00.NEXTVAL FROM dual ");
		Integer id = this.getJdbcTemplate().queryForObject(queryId.toString(), Integer.class);
		bean.setIdNota(id.intValue());

		StringBuilder query = new StringBuilder();
		query.append(" INSERT INTO AA79BB3T00 (ID_NOTA_0B3, ANYO_0B2,NUM_EXP_0B2, ");
		query.append(" DNI_USUARIO_0B2,FECHA_0B2,OBSV_0B2) VALUES (?,?,?,?,SYSDATE,?) ");

		List<Object> params = new ArrayList<Object>();
		params.add(bean.getIdNota());
		params.add(bean.getAnyo());
		params.add(bean.getNumExp());
		params.add(bean.getUsuario());
		//CLOB
		params.add(bean.getNota() != null && bean.getNota().length() > 0
				? bean.getNota()
				: null);
		this.getJdbcTemplate().update(query.toString(), params.toArray());
		return bean;
	}

	@Override
	public Boolean tieneNotasExpediente(Expediente expediente) {
		String query = "SELECT COUNT(1) FROM AA79BB3T00 WHERE ANYO_0B2 = ? and NUM_EXP_0B2 = ?";

		List<Object> params = new ArrayList<Object>();
		params.add(expediente.getAnyo());
		params.add(expediente.getNumExp());

		Long resul = this.getJdbcTemplate().queryForObject(query, params.toArray(), Long.class);
		return (resul != null && resul > 0);
	}

}
