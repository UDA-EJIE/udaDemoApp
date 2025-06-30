package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.DatosTareaTrados;
import com.ejie.aa79b.model.EstadosExpediente;
import com.ejie.aa79b.model.ExpedientePlanificacion;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.FasesExpediente;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;

public class ConsultaPlanifExpRowMapper implements RowMapper<ExpedientePlanificacion> {

	@Override
	public ExpedientePlanificacion mapRow(ResultSet resultSet, int arg1) throws SQLException {

		ExpedientePlanificacion expPlanificacion = new ExpedientePlanificacion();
		expPlanificacion.setAnyo(resultSet.getLong(DBConstants.ANYO));
		expPlanificacion.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
		expPlanificacion.setIdTipoExpediente(resultSet.getString(DBConstants.IDTIPOEXPEDIENTE));
		expPlanificacion.setTipoExpedienteDescEs(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCES));
		expPlanificacion.setTipoExpedienteDescEu(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCEU));
		expPlanificacion.setTitulo(resultSet.getString(DBConstants.TITULO));
		expPlanificacion.setFechaAlta(resultSet.getDate(DBConstants.FECHAALTA));
		expPlanificacion.setHoraAlta(resultSet.getString(DBConstants.HORAALTA));
		BitacoraExpediente bitacora = new BitacoraExpediente();
		EstadosExpediente estado = new EstadosExpediente();
		estado.setId(resultSet.getLong(DBConstants.IDESTADOEXP));
		estado.setDescEu(resultSet.getString(DBConstants.ESTADOEXPEDIENTEDESCEU));
		estado.setDescEs(resultSet.getString(DBConstants.ESTADOEXPEDIENTEDESCES));
		estado.setDescAbrEu(resultSet.getString(DBConstants.ESTADOEXPEDIENTEDESCABREU));
		estado.setDescAbrEs(resultSet.getString(DBConstants.ESTADOEXPEDIENTEDESCABRES));
		bitacora.setEstadoExp(estado);
		FasesExpediente fase = new FasesExpediente();
		fase.setId(resultSet.getLong(DBConstants.IDFASEEXPEDIENTE));
		bitacora.setFaseExp(fase);
		expPlanificacion.setBitacoraExpediente(bitacora);
		if (!(TipoExpedienteEnum.INTERPRETACION.getValue().equals(expPlanificacion.getIdTipoExpediente()))) {
			ExpedienteTradRev tradRev = new ExpedienteTradRev();
			tradRev.setFechaFinalIzo(resultSet.getDate(DBConstants.FECHAFINALIZO));
			tradRev.setHoraFinalIzo(resultSet.getString(DBConstants.HORAFINALIZO));
			tradRev.setNumTotalPalIzo(resultSet.getInt(DBConstants.NUMTOTALPALIZO));

			DatosTareaTrados tradosExp = new DatosTareaTrados();
			tradosExp.setNumTotalPal(resultSet.getInt(DBConstants.NUMTOTALPALTRADOS));
			tradosExp.setNumPalConcor084(resultSet.getInt(DBConstants.NUMPALCONCOR084090));
			tradosExp.setNumPalConcor8594(resultSet.getInt(DBConstants.NUMPALCONCOR8594090));
			tradosExp.setNumPalConcor95100(resultSet.getInt(DBConstants.NUMPALCONCOR95100090));
			tradosExp.setNumPalConcor9599(resultSet.getInt(DBConstants.NUMPALCONCOR9599090));
			tradosExp.setNumPalConcor100(resultSet.getInt(DBConstants.NUMPALCONCOR100090));
			tradRev.setTradosExp(tradosExp);
			tradRev.setFechaEntregaReal(resultSet.getDate(DBConstants.FECHAENTREGAREAL));
			tradRev.setHoraEntregaReal(resultSet.getString(DBConstants.HORAENTREGAREAL));
			expPlanificacion.setExpedienteTradRev(tradRev);
		} else {

			expPlanificacion.setFechaPrevistaInicio(resultSet.getDate(DBConstants.FECHAINICIOPREVISTA));
			expPlanificacion.setHoraPrevistaInicio(resultSet.getString(DBConstants.HORAINICIOPREVISTA));
			expPlanificacion.setFechaPrevistaFin(resultSet.getDate(DBConstants.FECHAFINPREVISTA));
			expPlanificacion.setHoraPrevistaFin(resultSet.getString(DBConstants.HORAFINPREVISTA));
		}

		return expPlanificacion;
	}

}
