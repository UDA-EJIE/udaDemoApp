package com.ejie.aa79b.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.model.RechazoExpediente;

@Repository
@Transactional
public class RechazoExpedienteDaoImpl extends GenericoDaoImpl<RechazoExpediente> implements RechazoExpedienteDao {

	public RechazoExpedienteDaoImpl() {
		super(RechazoExpediente.class);
	}

	@Override
	protected String getSelect() {
		StringBuilder query = new StringBuilder();

		query.append("SELECT ID_067 ID067");
		query.append(", ID_MOTIVO_RECHAZO_067 IDMOTIVORECHAZO");
		return query.toString();
	}

	@Override
	protected String getFrom() {
		StringBuilder query = new StringBuilder();
		query.append("FROM AA79B67S01");
		return query.toString();
	}

	@Override
	protected RowMapper<RechazoExpediente> getRwMap() {
		return null;
	}

	@Override
	protected String[] getOrderBy() {
		return new String[0];
	}

	@Override
	protected String getPK() {
		return null;
	}

	@Override
	protected RowMapper<RechazoExpediente> getRwMapPK() {
		return null;
	}

	@Override
	protected String getWherePK(RechazoExpediente bean, List<Object> params) {
		return null;
	}

	@Override
	protected String getWhere(RechazoExpediente bean, List<Object> params) {
		return null;
	}

	@Override
	protected String getWhereLike(RechazoExpediente bean, Boolean startsWith, List<Object> params, Boolean search) {
		StringBuilder where = new StringBuilder(RechazoExpedienteDaoImpl.STRING_BUILDER_INIT);
		return where.toString();
	}

	@Override
	@Transactional(readOnly = true)
	public Long findMotivosCount() {
		List<Object> params = new ArrayList<Object>();
		StringBuilder query = new StringBuilder("SELECT nvl(max(ID_067),0) + 1 ");
		query.append("FROM AA79B67S01 ");

		return this.getJdbcTemplate().queryForObject(query.toString(), params.toArray(), Long.class);
	}

	@Override()
	public RechazoExpediente addMotivoRechazo(RechazoExpediente rechazoExpediente) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT nvl(max(ID_067),0) + 1");
		sb.append("AA79B67S01");

		String query = "INSERT INTO AA79B67S01 (ID_067, ID_MOTIVO_RECHAZO_067) VALUES (?,?)";
		this.getJdbcTemplate().update(query, rechazoExpediente.getId067(), rechazoExpediente.getIdMotivoRechazo());
		return rechazoExpediente;
	}

	@Override()
	public RechazoExpediente addDescRechazo(RechazoExpediente rechazoExpediente) {
		String query = "INSERT INTO AA79B68S01 (ID_068, OBSV_RECHAZO_068) VALUES (?,?)";
		this.getJdbcTemplate().update(query, rechazoExpediente.getId067(),
				rechazoExpediente.getObservacionesRechazo().getObsvRechazo068());
		return rechazoExpediente;
	}
}
