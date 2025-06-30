package com.ejie.aa79b.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.model.Informe;
import com.ejie.aa79b.model.InformeInterpretacion;
import com.ejie.aa79b.model.InformeTradRev;
import com.ejie.aa79b.model.TradosExp;
import com.ejie.aa79b.utils.DAOUtils;

@Repository()
@Transactional()
public class InformesDaoImpl extends BaseDaoImpl implements InformesDao {

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	private RowMapper<InformeTradRev> rwMapInformeTradRev = new RowMapper<InformeTradRev>() {
		@Override
		public InformeTradRev mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			InformeTradRev informeTradRev = new InformeTradRev();
			informeTradRev.setAnyo(resultSet.getLong("URTEA"));
			informeTradRev.setNumExp(resultSet.getInt("ESPEDIENTEA"));
			informeTradRev.setTipoExpediente(resultSet.getString("ESPEDIENTE_MOTA"));
			informeTradRev.setTitulo(resultSet.getString("IZENBURUA"));
			informeTradRev.setIndPublicarBopv(resultSet.getString("EHAA"));
			informeTradRev.setSolicitante(resultSet.getString("ESKATZAILEA"));
			informeTradRev.setTipoSolicitante(resultSet.getString("ESKATZAILE_MOTA"));
			informeTradRev.setContacto(resultSet.getString("ESKATZAILE_HARREMANETARAKO"));
			informeTradRev.setContactoDni(resultSet.getString("ESKATZAILE_HARREMANETARAKO_NAN"));
			informeTradRev.setTarea(resultSet.getString("ATAZA"));
			informeTradRev.setCodAsignadoA(resultSet.getString("NORI_ESLEITUA_ZENBAKIA"));
			informeTradRev.setAsignadoA(resultSet.getString("NORI_ESLEITUA"));
			informeTradRev.setFechaSolicitud(resultSet.getTimestamp("ESKAERA_DATA"));
			informeTradRev.setFechaFin(resultSet.getTimestamp("AMAIERA_DATA"));
			informeTradRev.setNumPalabrasSolic(resultSet.getInt("HITZ_KOPURUA_BEZEROA"));
			informeTradRev.setNumPalabrasIzo(resultSet.getInt("HITZ_KOPURUA_IZO"));
			informeTradRev.setTarifaPalabras(resultSet.getBigDecimal("TARIFA_HITZAK"));
			informeTradRev.setFechaPrevista(resultSet.getTimestamp("AURREKUSITAKO_DATA"));
			informeTradRev.setFechaEjecTarea(resultSet.getTimestamp("EGIKARATZE_DATA"));
			informeTradRev.setFechaEntregaReal(resultSet.getTimestamp("BENETAKO_ENTREGA_DATA"));
			informeTradRev.setTerminado(resultSet.getString("AMAITUTA"));
			informeTradRev.setTipoTraduccion(resultSet.getString("ITZULPEN_MOTA"));
			informeTradRev.setTipoTraduccion0(resultSet.getString("ITZULPEN_MOTA_0"));
			informeTradRev.setIngreso(resultSet.getBigDecimal("DIRU_SARRERA"));
			informeTradRev.setIva(resultSet.getString("BEZ"));
			informeTradRev.setImporteConIVA(resultSet.getBigDecimal("ZENBATEKOA_BEZ_BARNE"));
			informeTradRev.setCodTarea(resultSet.getBigDecimal("ATAZAREN_KODEA"));
			informeTradRev.setRechazado(resultSet.getString("BAZTERTUTA"));
			informeTradRev.setMotivoRechazo(resultSet.getString("BAZTERTZEKO_ARRAZOIA"));
			informeTradRev.setAnulado(resultSet.getString("EZEZTATUTA"));
			informeTradRev.setMotivoAnulado(resultSet.getString("EZEZTATZEKO_ARRAZOIA"));
			informeTradRev.setSede(resultSet.getString("HIZKUNTZA_XEDEA"));
			informeTradRev.setImportePenalizacionCalidad(resultSet.getBigDecimal("KALITATE_ZENBATEKOA"));
			informeTradRev.setPenalizacionCalidad(resultSet.getString("KALITATE_PENALIZAZIOA"));

			TradosExp tradosExp = new TradosExp();
			tradosExp.setNumPalConcor084090(resultSet.getInt("TRADOS_EXPEDIENTE_0_84"));
			tradosExp.setNumPalConcor8594090(resultSet.getInt("TRADOS_EXPEDIENTE_85_94"));
			tradosExp.setNumPalConcor95100090(resultSet.getInt("TRADOS_EXPEDIENTE_95_100"));
			tradosExp.setNumPalConcor9599090(resultSet.getInt("TRADOS_EXPEDIENTE_95_99"));
			tradosExp.setNumPalConcor100090(resultSet.getInt("TRADOS_EXPEDIENTE_100"));
			informeTradRev.setTradosExp(tradosExp);

			TradosExp tradosTarea = new TradosExp();
			tradosTarea.setNumPalConcor084090(resultSet.getInt("TRADOS_TAREA_0_84"));
			tradosTarea.setNumPalConcor8594090(resultSet.getInt("TRADOS_TAREA_85_94"));
			tradosTarea.setNumPalConcor95100090(resultSet.getInt("TRADOS_TAREA_95_100"));
			tradosTarea.setNumPalConcor9599090(resultSet.getInt("TRADOS_TAREA_95_99"));
			tradosTarea.setNumPalConcor100090(resultSet.getInt("TRADOS_TAREA_100"));
			informeTradRev.setTradosTarea(tradosTarea);

			return informeTradRev;
		}
	};

	@Override
	public List<InformeTradRev> informeTradRev(Informe bean) {
		StringBuilder query = new StringBuilder("SELECT ");
		List<Object> params = new ArrayList<Object>();
		Locale locale = LocaleContextHolder.getLocale();

		query.append("URTEA, ESPEDIENTEA, ESPEDIENTE_MOTA,  IZENBURUA, EHAA, ESKATZAILEA ");
		query.append(
				DAOUtils.getDecodeAcciones("ESKATZAILE_MOTA", "ESKATZAILE_MOTA", this.msg, "TipoEntidadEnum", locale));
		query.append(", ESKATZAILE_HARREMANETARAKO, ESKATZAILE_HARREMANETARAKO_NAN, ");
		query.append("ATAZA, NORI_ESLEITUA_ZENBAKIA, NORI_ESLEITUA, ESKAERA_DATA, AMAIERA_DATA, ");
		query.append("HITZ_KOPURUA_BEZEROA, HITZ_KOPURUA_IZO, TARIFA_HITZAK, ");
		query.append(
				"AURREKUSITAKO_DATA, EGIKARATZE_DATA, BENETAKO_ENTREGA_DATA, AMAITUTA, ITZULPEN_MOTA, ITZULPEN_MOTA_0, ");
		query.append("DIRU_SARRERA, BEZ, ZENBATEKOA_BEZ_BARNE, ATAZAREN_KODEA, BAZTERTUTA, ");
		query.append("BAZTERTZEKO_ARRAZOIA, EZEZTATUTA, EZEZTATZEKO_ARRAZOIA, HIZKUNTZA_XEDEA, ");
		query.append("TRADOS_EXPEDIENTE_0_84, TRADOS_EXPEDIENTE_85_94, TRADOS_EXPEDIENTE_95_100, TRADOS_EXPEDIENTE_95_99,  TRADOS_EXPEDIENTE_100,  ");
		query.append("TRADOS_TAREA_0_84, TRADOS_TAREA_85_94, TRADOS_TAREA_95_100, TRADOS_TAREA_95_99, TRADOS_TAREA_100, ");
		query.append("KALITATE_PENALIZAZIOA, KALITATE_ZENBATEKOA ");
		query.append("FROM INF_SITUACION_TRAD_REV ");
		query.append(" WHERE 1 = 1 ");

		if (!bean.getAnyo().equals(Constants.STRING_CERO)) {
			query.append(SqlUtils.generarWhereIgual("SUBSTR(URTEA,3,4)",
					bean.getAnyo() != null ? bean.getAnyo().toString() : bean.getAnyo(), params));
		}

		if (!bean.getMes().equals(Constants.STRING_CERO)) {
			query.append(SqlUtils.generarQueryFechaWhereString(DaoConstants.SIGNOIGUAL, "ESKAERA_DATA", DaoConstants.MM,
					bean.getMes() != null ? bean.getMes().toString() : bean.getMes(), params));
		}

		query.append(" ORDER BY ESPEDIENTEA DESC ");

		return this.getJdbcTemplate().query(query.toString(), this.rwMapInformeTradRev, params.toArray());

	}

	private RowMapper<InformeInterpretacion> rwMapInformeInter = new RowMapper<InformeInterpretacion>() {
		@Override
		public InformeInterpretacion mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			InformeInterpretacion informeInter = new InformeInterpretacion();
			informeInter.setAnyo(resultSet.getLong("URTEA"));
			informeInter.setNumExp(resultSet.getInt("ESPEDIENTEA"));
			informeInter.setTipoExpediente(resultSet.getString("ESPEDIENTE_MOTA"));
			informeInter.setTitulo(resultSet.getString("IZENBURUA"));
			informeInter.setIndPublicarBopv(resultSet.getString("EHAA"));
			informeInter.setSolicitante(resultSet.getString("ESKATZAILEA"));
			informeInter.setTipoSolicitante(resultSet.getString("ESKATZAILE_MOTA"));
			informeInter.setContacto(resultSet.getString("ESKATZAILE_HARREMANETARAKO"));
			informeInter.setContactoDni(resultSet.getString("ESKATZAILE_HARREMANETARAKO_NAN"));
			informeInter.setTarea(resultSet.getString("ATAZA"));
			informeInter.setCodAsignadoA(resultSet.getString("NORI_ESLEITUA_ZENBAKIA"));
			informeInter.setAsignadoA(resultSet.getString("NORI_ESLEITUA"));
			informeInter.setFechaSolicitud(resultSet.getTimestamp("ESKAERA_DATA"));
			informeInter.setFechaFin(resultSet.getTimestamp("AMAIERA_DATA"));
			informeInter.setFechaIni(resultSet.getTimestamp("HASIERA_DATA"));
			informeInter.setNumInterpretesFact(resultSet.getString("FAKTURATUTAKO_INTERPR_KOPURUA"));
			informeInter.setHorasFacturadas(resultSet.getString("FAKTURATUTAKO_ORDUAK"));
			informeInter.setFechaPrevista(resultSet.getDate("AURREKUSITAKO_DATA"));
			informeInter.setFechaEjecTarea(resultSet.getTimestamp("EGIKARATZE_DATA"));
			informeInter.setTerminado(resultSet.getString("AMAITUTA"));
			informeInter.setTipoActoDesc(resultSet.getString("EKITALDI_MOTA"));
			informeInter.setIngreso(resultSet.getBigDecimal("DIRU_SARRERA"));
			informeInter.setIva(resultSet.getString("BEZ"));
			informeInter.setImporteConIVA(resultSet.getBigDecimal("ZENBATEKOA_BEZ_BARNE"));
			informeInter.setCodTarea(resultSet.getBigDecimal("ATAZAREN_KODEA"));
			informeInter.setRechazado(resultSet.getString("BAZTERTUTA"));
			informeInter.setMotivoRechazo(resultSet.getString("BAZTERTZEKO_ARRAZOIA"));
			informeInter.setAnulado(resultSet.getString("EZEZTATUTA"));
			informeInter.setMotivoAnulado(resultSet.getString("EZEZTATZEKO_ARRAZOIA"));
			return informeInter;
		}
	};

	@Override
	public List<InformeInterpretacion> informeInterpretacion(Informe bean) {
		StringBuilder query = new StringBuilder("SELECT ");
		List<Object> params = new ArrayList<Object>();
		Locale locale = LocaleContextHolder.getLocale();

		query.append("URTEA, ESPEDIENTEA, ESPEDIENTE_MOTA, IZENBURUA, EHAA, ESKATZAILEA ");
		query.append(
				DAOUtils.getDecodeAcciones("ESKATZAILE_MOTA", "ESKATZAILE_MOTA", this.msg, "TipoEntidadEnum", locale));
		query.append(", ESKATZAILE_HARREMANETARAKO, ESKATZAILE_HARREMANETARAKO_NAN, ");
		query.append("ATAZA, NORI_ESLEITUA_ZENBAKIA, NORI_ESLEITUA, ESKAERA_DATA, AMAIERA_DATA, ");
		query.append("HASIERA_DATA, FAKTURATUTAKO_INTERPR_KOPURUA, FAKTURATUTAKO_ORDUAK, ");
		query.append("AURREKUSITAKO_DATA, EGIKARATZE_DATA, AMAITUTA, EKITALDI_MOTA, ");
		query.append("DIRU_SARRERA, BEZ, ZENBATEKOA_BEZ_BARNE, ATAZAREN_KODEA, BAZTERTUTA, ");
		query.append("BAZTERTZEKO_ARRAZOIA, EZEZTATUTA, EZEZTATZEKO_ARRAZOIA ");
		query.append("FROM INF_SITUACION_INTER ");
		query.append(" WHERE 1 = 1 ");

		if (!bean.getAnyo().equals(Constants.STRING_CERO)) {
			query.append(SqlUtils.generarWhereIgual("SUBSTR(URTEA,3,4)",
					bean.getAnyo() != null ? bean.getAnyo().toString() : bean.getAnyo(), params));
		}

		if (!bean.getMes().equals(Constants.STRING_CERO)) {
			query.append(SqlUtils.generarQueryFechaWhereString(DaoConstants.SIGNOIGUAL, "ESKAERA_DATA", DaoConstants.MM,
					bean.getMes() != null ? bean.getMes().toString() : bean.getMes(), params));
		}

		query.append(" ORDER BY ESPEDIENTEA DESC ");

		return this.getJdbcTemplate().query(query.toString(), this.rwMapInformeInter, params.toArray());

	}

}
