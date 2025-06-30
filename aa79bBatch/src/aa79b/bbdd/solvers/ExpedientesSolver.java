/**
 *
 */
package aa79b.bbdd.solvers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.BaseSqlTransaccional;
import aa79b.bbdd.RowMapper;
import aa79b.util.beans.BitacoraExpediente;
import aa79b.util.beans.DatosContacto;
import aa79b.util.beans.Expediente;
import aa79b.util.beans.ExpedienteInterpretacion;
import aa79b.util.beans.ExpedienteTradRev;
import aa79b.util.beans.GestorExpediente;
import aa79b.util.beans.SubsanacionExpediente;
import aa79b.util.common.AccesoBD;
import aa79b.util.common.AccesoBDTransaccional;
import aa79b.util.common.Constants;
import aa79b.util.common.EstadoExpedienteEnum;
import aa79b.util.common.EstadoRequerimientoEnum;
import aa79b.util.common.FaseExpedienteEnum;

/**
 * @author aresua
 *
 */
public class ExpedientesSolver {

	/*
	 * ROW_MAPPERS
	 */
	private final RowMapper<Expediente> rwMap = new RowMapper<Expediente>() {
		@Override
		public Expediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {

			final DatosContacto datosContacto = new DatosContacto();
			datosContacto.setEmail031(resultSet.getString("EMAIL031"));

			final GestorExpediente gestor = new GestorExpediente();
			gestor.setDatosContacto(datosContacto);

			final Expediente expediente = new Expediente();
			expediente.setAnyo(resultSet.getLong("ANYO51"));
			expediente.setNumExp(resultSet.getInt("NUMEXP051"));
			expediente.setTitulo(resultSet.getString("TITULO051"));
			expediente.setGestorExpediente(gestor);

			return expediente;
		}
	};

	/*
	 * ROW_MAPPERS
	 */
	private final RowMapper<Expediente> rwMapPresupuesto = new RowMapper<Expediente>() {
		@Override
		public Expediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {

			final Expediente expediente = new Expediente();
			expediente.setAnyo(resultSet.getLong("ANYO"));
			expediente.setNumExp(resultSet.getInt("NUMEXP"));
			expediente.setIndPresupuesto(resultSet.getString("INDPRESUPUESTOPENDIENTE"));
			expediente.setFechaEntrega(resultSet.getDate("FECHAFIN"));
			expediente.setTitulo(resultSet.getString("TITULO"));

			return expediente;
		}
	};

	private final RowMapper<Expediente> rwMapExpAAnular = new RowMapper<Expediente>() {
		@Override
		public Expediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {

			final Expediente expediente = new Expediente();
			expediente.setAnyo(resultSet.getLong("ANYO"));
			expediente.setNumExp(resultSet.getInt("NUMEXP"));
			expediente.setAnyoNumExpConcatenado(resultSet.getString("ANYONUMEXPCONCATENADO"));
			expediente.setIdTipoExpediente(resultSet.getString("IDTIPOEXPEDIENTE"));
			expediente.setTipoExpedienteDescEs(resultSet.getString("TIPOEXPEDIENTEDESCES"));
			expediente.setTipoExpedienteDescEu(resultSet.getString("TIPOEXPEDIENTEDESCEU"));
			expediente.setTitulo(resultSet.getString("TITULO"));

			// ExpedienteInterpretacion
			final ExpedienteInterpretacion expedienteInterpretacion = new ExpedienteInterpretacion();
			expedienteInterpretacion.setFechaIni(resultSet.getDate("FECHAINICIO"));
			expedienteInterpretacion.setHoraIni(resultSet.getString("HORAINICIO"));
			expedienteInterpretacion.setFechaFin(resultSet.getDate("FECHAFIN"));
			expedienteInterpretacion.setHoraFin(resultSet.getString("HORAFIN"));
			expediente.setExpedienteInterpretacion(expedienteInterpretacion);

			// ExpedienteTradRev
			final ExpedienteTradRev expedienteTradRev = new ExpedienteTradRev();
			expedienteTradRev.setFechaFinalIzo(resultSet.getDate("FECHAFINALIZO"));
			expedienteTradRev.setHoraFinalIzo(resultSet.getString("HORAFINALIZO"));
			expedienteTradRev.setFechaFinalSolic(resultSet.getDate("FECHAFINALSOLIC"));
			expedienteTradRev.setHoraFinalSolic(resultSet.getString("HORAFINALSOLIC"));
			expediente.setExpedienteTradRev(expedienteTradRev);

			// BitacoraExpediente
			final BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
			bitacoraExpediente.setIdEstadoExp(resultSet.getLong("IDESTADOEXP"));
			bitacoraExpediente.setIdFaseExp(resultSet.getLong("IDFASEEXPEDIENTE"));
			final SubsanacionExpediente subsanacionExpediente = new SubsanacionExpediente();
			subsanacionExpediente.setFechaLimite(resultSet.getDate("FECHALIMITE"));
			subsanacionExpediente.setHoraLimite(resultSet.getString("HORALIMITE"));
			subsanacionExpediente.setIndSubsanado(resultSet.getString("INDSUBSANADO"));
			subsanacionExpediente.setEstado(resultSet.getInt("ESTADOSUBSANACION"));
			subsanacionExpediente.setEstadoDescEs(resultSet.getString("ESTADOSUBSANACIONDESCES"));
			subsanacionExpediente.setEstadoDescEu(resultSet.getString("ESTADOSUBSANACIONDESCEU"));
			subsanacionExpediente.setTipoRequerimiento(resultSet.getLong("TIPOREQUERIMIENTO"));
			subsanacionExpediente.setTipoRequerimientoDescEs(resultSet.getString("TIPOREQUERIMIENTODESCES"));
			subsanacionExpediente.setTipoRequerimientoDescEu(resultSet.getString("TIPOREQUERIMIENTODESCEU"));
			subsanacionExpediente.setDifPlazoEntrega(resultSet.getDouble("DIFPLAZOENTREGA"));
			bitacoraExpediente.setSubsanacionExp(subsanacionExpediente);
			expediente.setBitacoraExpediente(bitacoraExpediente);

			return expediente;
		}
	};

	/**
	 * Finds a single row in the Expediente table.
	 *
	 * @param BaseSql baseSql
	 * @return List<Expediente>
	 * @throws Exception Cualquier excepción
	 */
	public List<Expediente> findAvisoPlazoExpiracion(BaseSql baseSql) throws Exception {
		final String query = "SELECT t1.ANYO_051 ANYO51, "
				+ "t1.NUM_EXP_051 NUMEXP051, "
				+ "t1.TITULO_051 TITULO051, "
				+ "d1.EMAIL_031 EMAIL031 "
				+ "FROM AA79B51S01 t1 "
				+ "LEFT JOIN AA79B59S01 b1 "
				+ "ON t1.ANYO_051 = b1.ANYO_059 AND "
				+ "t1.NUM_EXP_051 = b1.NUM_EXP_059 AND "
				+ "t1.ESTADO_BITACORA_051 = b1.ID_ESTADO_BITACORA_059 "
				+ "LEFT JOIN AA79B64T00 s "
				+ "ON b1.ID_REQUERIMIENTO_059 = s.ID_064 "
				+ "LEFT JOIN AA79B54T00 g1 "
				+ "ON t1.ANYO_051 = g1.ANYO_054 AND "
				+ "t1.NUM_EXP_051 = g1.NUM_EXP_054 "
				+ "LEFT JOIN X54JAPI_DATOS_CONTACTO d1 "
				+ "ON g1.DNI_SOLICITANTE_054 = d1.DNI_031 "
				+ "WHERE s.ESTADO_SUBSANACION_064 = ? AND "
				+ "s.FECHA_LIMITE_064 BETWEEN SYSDATE AND SYSDATE+1 AND "
				+ "s.IND_SUBSANADO_064 = ? AND "
				+ "((b1.ID_ESTADO_EXP_059 = ? AND "
				+ "b1.ID_FASE_EXPEDIENTE_059 = ?) OR (b1.ID_ESTADO_EXP_059 = ? AND "
				+ "b1.ID_FASE_EXPEDIENTE_059 = ?))";

		final List<Object> params = new ArrayList<Object>();

		params.add(Long.valueOf(EstadoRequerimientoEnum.PENDIENTE.getValue()));
		params.add(String.valueOf(Constants.IND_NO));
		params.add(Long.valueOf(EstadoExpedienteEnum.EN_ESTUDIO.getValue()));
		params.add(Long.valueOf(FaseExpedienteEnum.PDTE_TRAM_SUBSANACION.getValue()));
		params.add(Long.valueOf(EstadoExpedienteEnum.EN_CURSO.getValue()));
		params.add(Long.valueOf(FaseExpedienteEnum.PDTE_TRAM_CLIENTE.getValue()));

		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), rwMap, baseSql);
	}

	/**
	 *
	 * @param baseSql BaseSql
	 * @param idTarea BigDecimal
	 * @return List<Expediente>
	 * @throws Exception e
	 */
	public List<Expediente> findPresupuestoExpInterpPorTarea(BaseSql baseSql, String idTarea) throws Exception{
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		query.append("SELECT t2.ANYO_052 ANYO, t2.NUM_EXP_052 NUMEXP, t3.TITULO_051 TITULO, ");
		query.append(" DECODE(t2.IND_PRESUPUESTO_052 ,'").append(Constants.IND_SI).append("',DECODE(nvl(t3.IND_SUBSANADO_064,'")
		.append(Constants.IND_NO).append("'),'").append(Constants.IND_NO).append("','").append(Constants.IND_SI).append("','")
		.append(Constants.IND_NO).append("'),t2.IND_PRESUPUESTO_052) INDPRESUPUESTOPENDIENTE, ");
		query.append(" t2.FECHA_FIN_052 FECHAFIN ");
		query.append(" FROM AA79B81T00 t1 ");
		query.append(" JOIN AA79B51T00 t3 ON t1.ANYO_081 = t3.ANYO_051 ");
		query.append(" AND t1.NUM_EXP_081 = t3.NUM_EXP_051 ");
		query.append(" JOIN AA79B52T00 t2 ON t3.ANYO_051 = t2.ANYO_052 ");
		query.append(" AND t3.NUM_EXP_051 = t2.NUM_EXP_052 ");
		query.append(" LEFT JOIN AA79B64T00 t3 ON t1.ID_REQUERIMIENTO_081 = t3.ID_064 ");
		query.append(" WHERE t1.ID_TAREA_081 = ? ");

		params.add(idTarea);

		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), rwMapPresupuesto, baseSql);
	}

	/**
	 *
	 * @param baseSql BaseSql
	 * @param idTarea BigDecimal
	 * @return List<Expediente>
	 * @throws Exception e
	 */
	public List<Expediente> findPresupuestoExpTradRevPorTarea(BaseSql baseSql, String idTarea) throws Exception{
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		query.append("SELECT t2.ANYO_053 ANYO, ");
		query.append(" t2.NUM_EXP_053 NUMEXP, t3.TITULO_051 TITULO,");
		query.append(" DECODE(t2.IND_PRESUPUESTO_053 ,'").append(Constants.IND_SI).append("',DECODE(nvl(t3.IND_SUBSANADO_064,'")
		.append(Constants.IND_NO).append("'),'").append(Constants.IND_NO).append("','").append(Constants.IND_SI).append("','")
		.append(Constants.IND_NO).append("'),t2.IND_PRESUPUESTO_053) INDPRESUPUESTOPENDIENTE, ");
		query.append(" t2.FECHA_FINAL_IZO_053 FECHAFIN ");
		query.append(" FROM AA79B81T00 t1 ");
		query.append(" JOIN AA79B51T00 t3 ON t1.ANYO_081 = t3.ANYO_051 ");
		query.append(" AND t1.NUM_EXP_081 = t3.NUM_EXP_051 ");
		query.append(" JOIN AA79B53T00 t2 ON t3.ANYO_051 = t2.ANYO_053 ");
		query.append(" AND t3.NUM_EXP_051 = t2.NUM_EXP_053 ");
		query.append(" LEFT JOIN AA79B64T00 t3 ON t1.ID_REQUERIMIENTO_081 = t3.ID_064 ");
		query.append(" WHERE t1.ID_TAREA_081 = ? ");

		params.add(idTarea);

		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), rwMapPresupuesto, baseSql);
	}

	/**
	 * Obtiene el listado de expedientes a anular
	 *
	 * @param BaseSql baseSql
	 * @param int plazoEspera
	 * @return List<Expediente>
	 * @throws Exception Cualquier excepción
	 */
	public List<Expediente> findExpAAnular(BaseSql baseSql, double plazoEspera) throws Exception {
		final String query = "SELECT DISTINCT t1.ANYO_051 ANYO, "
				+ "t1.NUM_EXP_051 NUMEXP, "
				+ "SUBSTR(t1.ANYO_051,2,4) || '/' || LPAD(t1.NUM_EXP_051,6,'0') ANYONUMEXPCONCATENADO, "
				+ "t1.ID_TIPO_EXPEDIENTE_051 IDTIPOEXPEDIENTE, "
				+ "DECODE(t1.ID_TIPO_EXPEDIENTE_051, 'I', 'IN', 'T', 'IT', 'R', 'BE') AS TIPOEXPEDIENTEDESCES, "
				+ "DECODE(t1.ID_TIPO_EXPEDIENTE_051, 'I', 'IN', 'T', 'IT', 'R', 'BE') AS TIPOEXPEDIENTEDESCEU, "
				+ "t1.TITULO_051 TITULO, "
				+ "r1.FECHA_FINAL_SOLIC_053 FECHAFINALSOLIC, "
				+ "TO_CHAR(r1.FECHA_FINAL_SOLIC_053,'HH24:MI') HORAFINALSOLIC, "
				+ "r1.FECHA_FINAL_IZO_053 FECHAFINALIZO, "
				+ "TO_CHAR(r1.FECHA_FINAL_IZO_053,'HH24:MI') HORAFINALIZO, "
				+ "i1.FECHA_INI_052 FECHAINICIO, "
				+ "i1.FECHA_INI_052 FECHAINICIO, TO_CHAR(i1.FECHA_INI_052,'HH24:MI') HORAINICIO, "
				+ "i1.FECHA_FIN_052 FECHAFIN, "
				+ "i1.FECHA_FIN_052 FECHAFIN, TO_CHAR(i1.FECHA_FIN_052,'HH24:MI') HORAFIN, "
				+ "ESTADO_ACTUAL.ID_ESTADO_EXP_059 IDESTADOEXP, "
				+ "ESTADO_ACTUAL.ID_FASE_EXPEDIENTE_059 IDFASEEXPEDIENTE, "
				+ "s1.TIPO_REQUERIMIENTO_064 TIPOREQUERIMIENTO, "
				+ "DECODE(s1.TIPO_REQUERIMIENTO_064, '1', 'Zuzenketa', '2', 'Aurrekontua', '3', 'Azken data', '4', 'Aurrekontuaren amaiera-data') AS TIPOREQUERIMIENTODESCEU, "
				+ "DECODE(s1.TIPO_REQUERIMIENTO_064, '1', 'Subsanación', '2', 'Presupuesto', '3', 'Fecha final', '4', 'Presupuesto fecha fin') AS TIPOREQUERIMIENTODESCES, "
				+ "s1.FECHA_LIMITE_064 FECHALIMITE, "
				+ "TO_CHAR(s1.FECHA_LIMITE_064 ,'HH24:MI') HORALIMITE, "
				+ "SYSDATE - s1.FECHA_LIMITE_064 DIFPLAZOENTREGA, "
				+ "s1.IND_SUBSANADO_064 INDSUBSANADO, "
				+ "s1.ESTADO_SUBSANACION_064 ESTADOSUBSANACION, "
				+ "DECODE(s1.ESTADO_SUBSANACION_064, '1', 'Egin gabe', '2', 'Onartuta', '3', 'Baztertua') AS ESTADOSUBSANACIONDESCEU, "
				+ "DECODE(s1.ESTADO_SUBSANACION_064, '1', 'Pendiente', '2', 'Aceptado', '3', 'Rechazado') AS ESTADOSUBSANACIONDESCES, "
				+ "NVL(r1.FECHA_FINAL_SOLIC_053, i1.FECHA_INI_052) AS FECHAENTREGA "
				+ "FROM AA79B51S01 t1 "
				+ "LEFT JOIN AA79B52S01 i1 ON t1.ANYO_051 = i1.ANYO_052 AND t1.NUM_EXP_051 = i1.NUM_EXP_052 "
				+ "LEFT JOIN AA79B53S01 r1 ON t1.ANYO_051 = r1.ANYO_053 AND t1.NUM_EXP_051 = r1.NUM_EXP_053 "
				+ "JOIN AA79B59S01 ESTADO_ACTUAL ON t1.NUM_EXP_051 = ESTADO_ACTUAL.NUM_EXP_059 AND t1.ANYO_051 = ESTADO_ACTUAL.ANYO_059 AND t1.ESTADO_BITACORA_051 = ESTADO_ACTUAL.ID_ESTADO_BITACORA_059 "
				+ "JOIN AA79B64S01 s1 ON ESTADO_ACTUAL.ID_REQUERIMIENTO_059 = s1.ID_064 "
				+ "LEFT JOIN AA79B54S01 h1 ON t1.ANYO_051     = h1.ANYO_054 AND t1.NUM_EXP_051 = h1.NUM_EXP_054 "
				+ "WHERE 1=1  AND t1.ESTADO_BAJA_051 = ? AND "
				+ "( "
				+ "( "
				+ "(ESTADO_ACTUAL.ID_ESTADO_EXP_059 = 2 AND ESTADO_ACTUAL.ID_FASE_EXPEDIENTE_059 = 4) "
				+ "AND  "
				+ "(IND_SUBSANADO_064 = 'N' AND ESTADO_SUBSANACION_064 = 1 AND sysdate - FECHA_LIMITE_064 > ? ) "
				+ ") "
				+ "OR "
				+ "( "
				+ "(ESTADO_ACTUAL.ID_ESTADO_EXP_059 = 3 AND ESTADO_ACTUAL.ID_FASE_EXPEDIENTE_059 = 8) "
				+ "AND "
				+ "( "
				+ "(IND_SUBSANADO_064 = 'N' AND ESTADO_SUBSANACION_064 = 1 AND sysdate - FECHA_LIMITE_064 > ? )  "
				+ "OR "
				+ "(IND_SUBSANADO_064 = 'S' AND ESTADO_SUBSANACION_064 = 3) "
				+ ") "
				+ ") "
				+ ") ";

		final List<Object> params = new ArrayList<Object>();

		params.add(Constants.ALTA);
		params.add(plazoEspera);
		params.add(plazoEspera);

		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), rwMapExpAAnular, baseSql);
	}

	/**
	 * @param BaseSql baseSql
	 * @param Expediente expediente
	 * @throws Exception Cualquier excepción
	 */
	public void updateIdEstadoBitacora(BaseSql baseSql, Expediente expediente) throws Exception {
		final String query = "UPDATE AA79B51S01 SET ESTADO_BITACORA_051=? WHERE ANYO_051=? AND NUM_EXP_051=?";

		final List<Object> params = new ArrayList<Object>();
		params.add(expediente.getEstadoBitacora());
		params.add(expediente.getAnyo());
		params.add(expediente.getNumExp());

		new AccesoBD().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
	}
	/**
	 * @param BaseSql baseSql
	 * @param Expediente expediente
	 * @throws Exception Cualquier excepción
	 */
	public void updateIdEstadoBitacoraTransaccional(BaseSqlTransaccional baseSql, Expediente expediente) throws Exception {
		final String query = "UPDATE AA79B51S01 SET ESTADO_BITACORA_051=? WHERE ANYO_051=? AND NUM_EXP_051=?";

		final List<Object> params = new ArrayList<Object>();
		params.add(expediente.getEstadoBitacora());
		params.add(expediente.getAnyo());
		params.add(expediente.getNumExp());

		new AccesoBDTransaccional().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
	}

	/**
	 *
	 * @param baseSql BaseSql
	 * @param idTarea BigDecimal
	 * @return List<Expediente>
	 * @throws Exception e
	 */
	public List<Expediente> findTitulo(BaseSql baseSql, Expediente expediente) throws Exception{
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		query.append("SELECT ANYO_051 ANYO51, NUM_EXP_051 NUMEXP051, TITULO_051 TITULO051, '' EMAIL031 ");
		query.append(" FROM AA79B51S01 t1 WHERE ANYO_051 = ? AND NUM_EXP_051 = ?");
		params.add(expediente.getAnyo());
		params.add(expediente.getNumExp());
		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), rwMap, baseSql);
	}

	/**
	 *
	 * @param baseSql
	 * @param expediente
	 * @return
	 * @throws Exception
	 */
	public String hayFicheroObservaciones(BaseSqlTransaccional baseSql, Expediente expediente) throws Exception {
        StringBuilder query = new StringBuilder();
        final List<Object> params = new ArrayList<Object>();
        query.append("SELECT OID_FICHERO_055 FROM AA79B55S01 WHERE ANYO_055 = ? AND NUM_EXP_055 = ?");
        params.add(expediente.getAnyo());
		params.add(expediente.getNumExp());
        return new AccesoBDTransaccional().fncConsultaEspecifica(query.toString(), params.toArray(), baseSql);
    }
	public void updateOidObservaciones(BaseSqlTransaccional baseSql, Expediente expediente, String nuevoOid) throws Exception {
		StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		query.append("UPDATE AA79B55S01 SET OID_FICHERO_055 =? WHERE ANYO_055 = ? AND NUM_EXP_055 = ?");
		params.add(nuevoOid);
		params.add(expediente.getAnyo());
		params.add(expediente.getNumExp());
		new AccesoBDTransaccional().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
	}


}
