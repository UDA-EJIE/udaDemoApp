package com.ejie.aa79b.dao;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.model.ExpedientePlanificacionReport;

@Repository()
@Transactional
public class ExpedientePlanificacionReportDaoImpl extends GenericoDaoImpl<ExpedientePlanificacionReport>
		implements ExpedientePlanificacionReportDao {

	public ExpedientePlanificacionReportDaoImpl() {
		super(ExpedientePlanificacionReport.class);
	}

	@Override
	protected String getSelect() {
		return null;
	}

	@Override
	protected String getFrom() {
		return null;
	}

	@Override
	protected RowMapper<ExpedientePlanificacionReport> getRwMap() {
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
	protected RowMapper<ExpedientePlanificacionReport> getRwMapPK() {
		return null;
	}

	@Override
	protected String getWherePK(ExpedientePlanificacionReport bean, List<Object> params) {
		return null;
	}

	@Override
	protected String getWhere(ExpedientePlanificacionReport bean, List<Object> params) {
		return null;
	}

	@Override
	protected String getWhereLike(ExpedientePlanificacionReport bean, Boolean startsWith, List<Object> params,
			Boolean search) {
		return null;
	}

}
