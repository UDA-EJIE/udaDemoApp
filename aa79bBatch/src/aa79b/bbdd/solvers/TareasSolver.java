/**
 *
 */
package aa79b.bbdd.solvers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.RowMapper;
import aa79b.util.beans.Tarea;
import aa79b.util.beans.TipoTarea;
import aa79b.util.common.AccesoBD;
import aa79b.util.common.EstadoEjecucionTareaEnum;
import aa79b.util.common.TipoTareaGestionAsociadaEnum;

/**
 * @author aresua
 *
 */
public class TareasSolver {

	private static final String ANYO = "ANYO";
	private static final String NUMEXP = "NUMEXP";
	private static final String IDTAREA = "IDTAREA";
	private static final String IDTIPOTAREA = "IDTIPOTAREA";
	private static final String FROM_81 = " FROM AA79B81S01 t1 ";
	private static final String WHERE_GENERAL =  " WHERE t1.ID_TAREA_081 = ? ";
	private static final String JOIN_51 = " JOIN AA79B51S01 t2 ON t1.ANYO_081 = t2.ANYO_051 AND t1.NUM_EXP_081 = t2.NUM_EXP_051 ";

	/*
	 * ROW_MAPPERS
	 */
	private final RowMapper<Tarea> rwMap = new RowMapper<Tarea>() {
		@Override
		public Tarea mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			final Tarea tarea = new Tarea();
			tarea.setIdTarea(resultSet.getBigDecimal(IDTAREA));
			tarea.setAnyo(resultSet.getLong(ANYO));
			tarea.setNumExp(resultSet.getInt(NUMEXP));
			tarea.setDniRecurso(resultSet.getString("DNIRECURSO"));
			tarea.setIdLote(resultSet.getLong("IDLOTE"));
			tarea.setRecursoAsignacion(resultSet.getString("RECURSOASIGNACION"));
			tarea.setIndNoConformidad(resultSet.getString("INDNOCONFORMIDAD"));
			final TipoTarea tipoTarea = new TipoTarea();
			tipoTarea.setId(resultSet.getLong("IDTIPOTAREA"));
			tipoTarea.setDescEs(resultSet.getString("TIPOTAREAES"));
			tipoTarea.setDescEu(resultSet.getString("TIPOTAREAEU"));
			tarea.setTipoTarea(tipoTarea);
			return tarea;
		}
	};

	private final RowMapper<Tarea> rwMapTipoTarea = new RowMapper<Tarea>() {
		@Override
		public Tarea mapRow(ResultSet resultSet, int rowNum) throws SQLException {

			final Tarea tarea = new Tarea();
			tarea.setAnyo(resultSet.getLong(ANYO));
			tarea.setNumExp(resultSet.getInt(NUMEXP));
			tarea.setIdTarea(resultSet.getBigDecimal(IDTAREA));
			tarea.setIdTipoTarea(resultSet.getLong(IDTIPOTAREA));
			tarea.setIndNoConformidad(resultSet.getString("INDNOCONFORMIDAD"));
			tarea.setRecursoAsignacion(resultSet.getString("RECURSOASIGNACION"));
			tarea.setDniAsignador(resultSet.getString("DNIASIGNADOR"));

			return tarea;
		}
	};

	private final RowMapper<Tarea> rwMapTareaTrados = new RowMapper<Tarea>() {
		@Override
		public Tarea mapRow(ResultSet resultSet, int rowNum) throws SQLException {

			final Tarea tarea = new Tarea();
			tarea.setIdTarea(resultSet.getBigDecimal(IDTAREA));
			tarea.setIdTipoTarea(resultSet.getLong(IDTIPOTAREA));
			tarea.setFechaEntrega(resultSet.getDate("FECHAENTREGA"));

			return tarea;
		}
	};

	private final RowMapper<Tarea> rwMapSubsanacion = new RowMapper<Tarea>() {
		@Override
		public Tarea mapRow(ResultSet resultSet, int rowNum) throws SQLException {

			final Tarea tarea = new Tarea();
			tarea.setIdTarea(resultSet.getBigDecimal(IDTAREA));
			tarea.setIdTipoTarea(resultSet.getLong(IDTIPOTAREA));
			tarea.setIndSubsanado(resultSet.getString("INDSUBSANADO"));

			return tarea;
		}
	};

	private final RowMapper<Tarea> rwMapTareaSubMail = new RowMapper<Tarea>() {
		@Override
		public Tarea mapRow(ResultSet resultSet, int rowNum) throws SQLException {

			final Tarea tarea = new Tarea();
			tarea.setIdTarea(resultSet.getBigDecimal(IDTAREA));
			tarea.setDniRecurso(resultSet.getString("DNIRECURSO"));
			tarea.setIdLote(resultSet.getLong("IDLOTE"));
			tarea.setRecursoAsignacion(resultSet.getString("RECURSOASIGNACION"));

			final TipoTarea tipoTarea = new TipoTarea();
			tipoTarea.setId(resultSet.getLong("IDTIPOTAREA"));
			tipoTarea.setDescEs(resultSet.getString("TIPOTAREAES"));
			tipoTarea.setDescEu(resultSet.getString("TIPOTAREAEU"));
			tarea.setTipoTarea(tipoTarea);

			return tarea;
		}
	};

	private final RowMapper<Tarea> rwMapInfoTarea = new RowMapper<Tarea>() {
		@Override
		public Tarea mapRow(ResultSet resultSet, int rowNum) throws SQLException {

			final Tarea tarea = new Tarea();
			tarea.setIdTarea(resultSet.getBigDecimal(IDTAREA));
			tarea.setAnyo(resultSet.getLong(ANYO));
			tarea.setNumExp(resultSet.getInt(NUMEXP));

			final TipoTarea tipoTarea = new TipoTarea();
			tipoTarea.setId(resultSet.getLong("IDTIPOTAREA"));
			tipoTarea.setDescEs(resultSet.getString("TIPOTAREAES"));
			tipoTarea.setDescEu(resultSet.getString("TIPOTAREAEU"));
			tarea.setTipoTarea(tipoTarea);

			return tarea;
		}
	};


	/**
	 * @param BaseSql baseSql
	 * @param String idTarea
	 * @return Long
	 * @throws Exception Cualquier excepción
	 */
	public Long findAllCountMismoOrden(BaseSql baseSql, String idTarea) throws Exception {
		final String query = "SELECT COUNT(1) FROM AA79B81S01 t1 JOIN "
				+ " (SELECT ANYO_081, NUM_EXP_081, ORDEN_081 FROM AA79B81S01 WHERE ID_TAREA_081 = ? ) t2 ON "
				+ " t1.ANYO_081 = t2.ANYO_081 "
				+ " AND t1.NUM_EXP_081 = t2.NUM_EXP_081 "
				+ " AND t1.ORDEN_081 = t2.ORDEN_081 "
				+ " AND t1.ID_TAREA_081 != ? "
				+ " AND t1.ESTADO_EJECUCION_081 != ?";

		final List<Object> params = new ArrayList<Object>();

		params.add(idTarea);
		params.add(idTarea);
		params.add(EstadoEjecucionTareaEnum.EJECUTADA.getValue());

		return Long.valueOf(new AccesoBD().fncConsultaEspecifica(query.toString(), params.toArray(), baseSql));
	}

	/**
	 * @param BaseSql baseSql
	 * @param String idTarea
	 * @return Long
	 * @throws Exception Cualquier excepción
	 */
	public List<Tarea> findAllTareasPosteriores(BaseSql baseSql, String idTarea) throws Exception {
		final String query = "SELECT t1.ID_TAREA_081 IDTAREA, t1.ANYO_081 ANYO, t1.NUM_EXP_081 NUMEXP, t1.IND_NO_CONFORMIDAD_081 INDNOCONFORMIDAD, "
				+ " t1.DNI_RECURSO_081 DNIRECURSO, t1.ID_LOTE_081 IDLOTE, t1.RECURSO_ASIGNACION_081 RECURSOASIGNACION "
				+ " , t1.ID_TIPO_TAREA_081 IDTIPOTAREA, t5.DESC_ES_015 TIPOTAREAES, t5.DESC_EU_015 TIPOTAREAEU FROM AA79B81S01 t1 JOIN "
				+ " (SELECT ANYO_081, NUM_EXP_081, ORDEN_081 FROM AA79B81S01 WHERE ID_TAREA_081 = ? ) t2 ON "
				+ " t1.ANYO_081 = t2.ANYO_081 "
				+ " AND t1.NUM_EXP_081 = t2.NUM_EXP_081 "
				+ " AND t1.ORDEN_081 = (SELECT MIN(DECODE(ORDEN_081,0,1,ORDEN_081)) FROM AA79B81T00 WHERE ANYO_081 = t2.ANYO_081 AND NUM_EXP_081 = t2.NUM_EXP_081 AND ORDEN_081 > t2.ORDEN_081) "
				+ " LEFT JOIN AA79B15S01 t5 ON t1.ID_TIPO_TAREA_081 = t5.ID_015 ";

		final List<Object> params = new ArrayList<Object>();

		params.add(idTarea);

		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), rwMap, baseSql);
	}

	/**
	 * @param BaseSql baseSql
	 * @param BigDecimal idTarea
	 * @return Long
	 * @throws Exception Cualquier excepción
	 */
	public List<Tarea> findTarea(BaseSql baseSql, String idTarea) throws Exception {
		final String query = "SELECT t1.ANYO_081 ANYO, t1.NUM_EXP_081 NUMEXP, t1.ID_TAREA_081 IDTAREA, t1.ID_TIPO_TAREA_081 IDTIPOTAREA, t1.IND_NO_CONFORMIDAD_081 INDNOCONFORMIDAD, t1.RECURSO_ASIGNACION_081 RECURSOASIGNACION, t2.DNI_ASIGNADOR_051 DNIASIGNADOR"
				+ FROM_81 + JOIN_51
				+ WHERE_GENERAL;

		final List<Object> params = new ArrayList<Object>();
		params.add(idTarea);

		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), rwMapTipoTarea, baseSql);
	}

	/**
	 * @param BaseSql baseSql
	 * @param BigDecimal idTarea
	 * @return Long
	 * @throws Exception Cualquier excepción
	 */
	public List<Tarea> findTareaTrados(BaseSql baseSql, String idTarea) throws Exception {
		final String query = "SELECT t1.ID_TAREA_081 IDTAREA, t1.ID_TIPO_TAREA_081 IDTIPOTAREA, t2.FECHA_ENTREGA_090 FECHAENTREGA "
				+ FROM_81
				+ " JOIN AA79B90S01 t2 ON t1.ID_TAREA_081 = t2.ID_TAREA_090 "
				+ WHERE_GENERAL;

		final List<Object> params = new ArrayList<Object>();
		params.add(idTarea);

		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), rwMapTareaTrados, baseSql);
	}

	/**
	 * @param BaseSql baseSql
	 * @param String idTarea
	 * @return Long
	 * @throws Exception Cualquier excepción
	 */
	public List<Tarea> findListaTareasSubsanacion(BaseSql baseSql, String idTarea) throws Exception {
		final String query = "SELECT DISTINCT t3.ID_TAREA_083 IDTAREA, t1.ID_TIPO_TAREA_081 IDTIPOTAREA, t1.DNI_RECURSO_081 DNIRECURSO, t1.ID_LOTE_081 IDLOTE, t1.RECURSO_ASIGNACION_081 RECURSOASIGNACION "
				+ ", t5.DESC_ES_015 TIPOTAREAES, t5.DESC_EU_015 TIPOTAREAEU "
				+ FROM_81
				+ " JOIN AA79B66S01 t2 ON t1.ID_REQUERIMIENTO_081 = t2.ID_066 AND t1.ID_TAREA_081 = ? "
				+ " JOIN AA79B83S01 t3 ON t2.ID_DOC_066 = t3.ID_DOC_083 AND t3.ID_TAREA_083 != ? "
				+ " LEFT JOIN AA79B15S01 t5 ON t1.ID_TIPO_TAREA_081 = t5.ID_015 ";

		final List<Object> params = new ArrayList<Object>();
		params.add(idTarea);
		params.add(idTarea);

		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), rwMapTareaSubMail, baseSql);
	}

	/**
	 * @param BaseSql baseSql
	 * @param String idTarea
	 * @return Long
	 * @throws Exception Cualquier excepción
	 */
	public List<Tarea> findTareaSubsanacion(BaseSql baseSql, String idTarea) throws Exception {
		final String query = "SELECT t1.ID_TAREA_081 IDTAREA, t1.ID_TIPO_TAREA_081 IDTIPOTAREA, t2.IND_SUBSANADO_064 INDSUBSANADO "
				+ FROM_81
				+ " JOIN AA79B64S01 t2 ON t1.ID_REQUERIMIENTO_081 = t2.ID_064 "
				+ WHERE_GENERAL;

		final List<Object> params = new ArrayList<Object>();
		params.add(idTarea);

		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), rwMapSubsanacion, baseSql);
	}

	/**
	 * @param BaseSql baseSql
	 * @param BigDecimal idTarea
	 * @throws Exception Cualquier excepción
	 */
	public void reabrirTarea(BaseSql baseSql, String idTarea) throws Exception {
		final String query = "UPDATE AA79B81S01 SET ESTADO_EJECUCION_081 = ? WHERE ID_TAREA_081 = ? ";

		final List<Object> params = new ArrayList<Object>();
		params.add(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue());
		params.add(idTarea);

		new AccesoBD().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
	}

	public List<Tarea> getInfoTarea(BaseSql baseSql, String idTarea) throws Exception  {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		query.append(" SELECT t1.ID_TAREA_081 IDTAREA,");
		query.append(" t1.ANYO_081 ANYO,");
		query.append(" t1.NUM_EXP_081 NUMEXP,");
		query.append(" t1.ID_TIPO_TAREA_081 IDTIPOTAREA,");
		query.append(" t2.DESC_ES_015 TIPOTAREAES,");
		query.append(" t2.DESC_EU_015 TIPOTAREAEU ");
		query.append(" FROM AA79B81S01 t1 ");
		query.append(" LEFT JOIN AA79B15S01 t2 ");
		query.append(" ON t1.ID_TIPO_TAREA_081 = t2.ID_015 ");
		query.append(" WHERE t1.ID_TAREA_081 = ? ");
		params.add(idTarea);
		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), rwMapInfoTarea, baseSql);
	}
	public String findRevisionPagoProveedores(BaseSql baseSql, String idTarea) throws Exception {

		final String query = "SELECT IND_ALBARAN_094 FROM AA79B81T00 "
			+ " JOIN AA79B94T00 ON ID_TAREA_094 = ID_TAREA_081 "
			+ " WHERE ID_TAREA_REL_081 = (Select tareaRev.ID_TAREA_081 FROM AA79B81T00 tareaEntr "
			+ " JOIN AA79B81T00 tareaRev ON tareaRev.ANYO_081 = tareaEntr.ANYO_081 AND "
			+ " tareaRev.NUM_EXP_081 = tareaEntr.NUM_EXP_081 AND "
			+ " tareaRev.ORDEN_081 < tareaEntr.ORDEN_081 AND "
			+ " tareaRev.ID_TIPO_TAREA_081 = "+ TipoTareaGestionAsociadaEnum.REVISAR.getValue() +" "
			+ " WHERE tareaEntr.ID_TAREA_081 = ? order by tarearev.orden_081 desc fetch first row only) AND ID_TIPO_TAREA_081 = " + TipoTareaGestionAsociadaEnum.REVISION_PAGO_PROVEEDOR.getValue();

		final List<Object> params = new ArrayList<Object>();
		params.add(idTarea);
		return new AccesoBD().fncConsultaEspecifica(query.toString(), params.toArray(), baseSql);
	}
	public String findIndNoConformidadTareaRel(BaseSql baseSql, String idTarea) throws Exception {
		final String query = "SELECT TAREAORIG.IND_NO_CONFORMIDAD_081 FROM AA79B81S01 TAREANOCONF "
				+ " JOIN AA79B81S01 TAREAORIG ON TAREANOCONF.ID_TAREA_REL_081 = TAREAORIG.ID_TAREA_081 WHERE TAREANOCONF.ID_TAREA_081 = ?";
		final List<Object> params = new ArrayList<Object>();
		params.add(idTarea);
		return new AccesoBD().fncConsultaEspecifica(query.toString(), params.toArray(), baseSql);
	}
	public String findObservacionesEjecucion(BaseSql baseSql, String idTarea) throws Exception {
		final String query = "SELECT OBSV_EJECUCION_085 FROM AA79B85S01 WHERE ID_TAREA_085 = ?";
		final List<Object> params = new ArrayList<Object>();
		params.add(idTarea);
		return new AccesoBD().fncConsultaEspecifica(query.toString(), params.toArray(), baseSql);
	}

	/**
	 * obtiene el titulo del expediente por el id de tarea
	 * @param tarea Tarea
	 * @return
	 * @throws Exception
	 */
	public String obtenerTituloExpediente(BaseSql baseSql,Tarea tarea) throws Exception {
		final String query = "SELECT t2.TITULO_051 TITULO FROM AA79B81S01 t1 JOIN AA79B51S01 t2 ON t1.ANYO_081 = t2.ANYO_051"
				+ " AND t1.NUM_EXP_081 = t2.NUM_EXP_051 WHERE t1.ID_TAREA_081 = ?";
		final List<Object> params = new ArrayList<Object>();
		params.add(tarea.getIdTarea());
		return new AccesoBD().fncConsultaEspecifica(query.toString(), params.toArray(), baseSql);
	}
}
