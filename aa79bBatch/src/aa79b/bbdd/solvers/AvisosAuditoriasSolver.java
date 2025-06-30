package aa79b.bbdd.solvers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.RowMapper;
import aa79b.util.beans.Lotes;
import aa79b.util.beans.Persona;
import aa79b.util.beans.TareaAuditoria;
import aa79b.util.beans.TipoTarea;
import aa79b.util.common.AccesoBD;
import aa79b.util.common.EstadoAuditoriaEnum;
import aa79b.util.common.EstadoEjecucionTareaEnum;
import aa79b.util.common.PropertiesConstants;
import aa79b.util.common.TipoRecursoEnum;
import aa79b.util.common.TipoTareaGestionAsociadaEnum;

/**
 *
 * @author eaguirresarobe
 *
 */
public class AvisosAuditoriasSolver {


	private final RowMapper<TareaAuditoria> rwMapTarea = new RowMapper<TareaAuditoria>() {
		@Override
		public TareaAuditoria mapRow(ResultSet rs, int rowNum) throws SQLException {

			TareaAuditoria tarea = new TareaAuditoria();
			tarea.setIdTarea(rs.getBigDecimal("IDTAREA"));
			TipoTarea tipotarea = new TipoTarea();
			tipotarea.setDescEu(rs.getString("TIPOTAREADESCEU"));
			tipotarea.setDescEs(rs.getString("TIPOTAREADESCES"));
			tarea.setTipoTarea(tipotarea);
			tarea.setAnyo(rs.getLong("ANYO"));
			tarea.setNumExp(rs.getInt("NUMEXP"));
			tarea.setTituloExp(rs.getString("TITULOEXP"));
			Persona auditor = new Persona();
			auditor.setDni(rs.getString("DNIAUDITOR"));
			auditor.setNombre(rs.getString("NOMBREAUDITOR"));
			auditor.setApellido1(rs.getString("APEL1AUDITOR"));
			auditor.setApellido2(rs.getString("APEL2AUDITOR"));
			tarea.setAuditor(auditor);
			tarea.setIdTareaAuditar(rs.getBigDecimal("TAREAAUDITAR"));
			Lotes lote = new Lotes();
			lote.setIdLote(rs.getInt("IDLOTE"));
			lote.setDescLoteEu(rs.getString("DESCLOTEEU"));
			tarea.setLote(lote);
			tarea.setIdAuditoria(rs.getBigDecimal("ESTADOAUDITORIA"));
			tarea.setEstadoAuditoria(rs.getInt("ESTADOAUDITORIA"));
			return tarea;
		}
	};

	/**
	 *
	 * @return String
	 */
	private String getSelect() {
		final StringBuilder query = new StringBuilder();
		query.append(" SELECT ");
		query.append(" tr81.ID_TAREA_081 IDTAREA, ");
		query.append(" tr15.DESC_EU_015 TIPOTAREADESCEU, ");
		query.append(" tr15.DESC_ES_015 TIPOTAREADESCES, ");
		query.append(" tr51.NUM_EXP_051 NUMEXP, ");
		query.append(" tr51.ANYO_051 ANYO, ");
		query.append(" tr51.TITULO_051 TITULOEXP, ");
		query.append(" ta77.DNI_077 DNIAUDITOR, ");
		query.append(" ta77.NOMBRE_077 NOMBREAUDITOR, ");
		query.append(" ta77.APEL1_077 APEL1AUDITOR, ");
		query.append(" ta77.APEL2_077 APEL2AUDITOR, ");
		query.append(" ta77.NOMBRE_077 || ' ' || ta77.APEL1_077 || ' ' || ta77.APEL2_077 AUDITOR, ");
		query.append(" ta81.ID_TAREA_081 TAREAAUDITAR, ");
		query.append(" ta29.ID_LOTE_029 IDLOTE, ");
		query.append(" ta29.DESC_LOTE_EU_029 DESCLOTEEU, ");
		query.append(" tac2.ID_0C2 IDAUDITORIA, ");
		query.append(" NVL(tac2.ESTADO_AUDITORIA_0C2, "+EstadoAuditoriaEnum.SIN_ENVIAR.getValue()+") ESTADOAUDITORIA ");
		return query.toString();
	}

	/**
	 *
	 * @return String
	 */
	private String getFromIZO() {
		final StringBuilder query = new StringBuilder();
		query.append(" FROM AA79B81S01 tr81 ");
		query.append(" JOIN AA79B82S01 tr82 ON tr81.ID_TAREA_081 = tr82.ID_TAREA_082 ");
		query.append(" JOIN AA79B15S01 tr15 ON tr81.ID_TIPO_TAREA_081 = tr15.ID_015 ");
		query.append(" JOIN AA79B51S01 tr51 ON tr81.NUM_EXP_081 = tr51.NUM_EXP_051 ");
		query.append(" AND tr81.ANYO_081 = tr51.ANYO_051 ");
		query.append(" JOIN AA79B81S01 ta81 ON ( ( tr81.ID_TAREA_REL_081 = ta81.ID_TAREA_081 ");
		query.append(" OR ( tr81.ANYO_081 = ta81.ANYO_081 ");
		query.append(" AND tr81.NUM_EXP_081 = ta81.NUM_EXP_081 ");
		query.append(" AND ta81.ID_TIPO_TAREA_081 = " + TipoTareaGestionAsociadaEnum.REVISAR.getValue());
		query.append(" AND ta81.ORDEN_081 < tr81.ORDEN_081 ) ) ");
		query.append(" AND ta81.RECURSO_ASIGNACION_081 = '"+TipoRecursoEnum.EXTERNO.getValue()+"' ");
		query.append(" AND ta81.ESTADO_EJECUCION_081 = "+EstadoEjecucionTareaEnum.EJECUTADA.getValue()+" ) ");
		query.append(" JOIN AA79B29S01 ta29 ON ta81.ID_LOTE_081 = ta29.ID_LOTE_029 ");
		query.append(" LEFT JOIN AA79BC2S01 tac2 ON ta81.NUM_EXP_081 = tac2.NUM_EXP_0C2 ");
		query.append(" AND ta81.ANYO_081 = tac2.ANYO_0C2 ");
		query.append(" AND ta81.ID_TAREA_081 = tac2.ID_TAREA_AUDITAR_0C2 ");
		query.append(" JOIN AA79B77S01 ta77 ON  NVL(tac2.DNI_AUDITOR_0C2, tr81.DNI_RECURSO_081) = ta77.DNI_077 ");
		return query.toString();
	}


	/**
	 *
	 * @return String
	 */
	private String getFromProveedor() {
		final StringBuilder query = new StringBuilder();
		query.append(" FROM AA79B81S01 tr81 ");
		query.append(" JOIN AA79B82S01 tr82 ON tr81.ID_TAREA_081 = tr82.ID_TAREA_082 ");
		query.append(" JOIN AA79B51S01 tr51 ON tr81.NUM_EXP_081 = tr51.NUM_EXP_051 ");
		query.append(" AND tr81.ANYO_081 = tr51.ANYO_051 ");
		query.append(" JOIN AA79B81S01 ta81 ON ( ( tr81.ID_TAREA_REL_081 = ta81.ID_TAREA_081 ");
		query.append(" OR ( tr81.ANYO_081 = ta81.ANYO_081 ");
		query.append(" AND tr81.NUM_EXP_081 = ta81.NUM_EXP_081 ");
		query.append(" AND ta81.ID_TIPO_TAREA_081 = " + TipoTareaGestionAsociadaEnum.REVISAR.getValue());
		query.append(" AND ta81.ORDEN_081 < tr81.ORDEN_081 ) ) ");
		query.append(" AND ta81.RECURSO_ASIGNACION_081 = '"+TipoRecursoEnum.EXTERNO.getValue()+"' ");
		query.append(" AND ta81.ESTADO_EJECUCION_081 = "+EstadoEjecucionTareaEnum.EJECUTADA.getValue()+" ) ");
		query.append(" JOIN AA79B15S01 tr15 ON ta81.ID_TIPO_TAREA_081 = tr15.ID_015 ");
		query.append(" JOIN AA79B29S01 ta29 ON ta81.ID_LOTE_081 = ta29.ID_LOTE_029 ");
		query.append(" LEFT JOIN AA79BC2S01 tac2 ON ta81.NUM_EXP_081 = tac2.NUM_EXP_0C2 ");
		query.append(" AND ta81.ANYO_081 = tac2.ANYO_0C2 ");
		query.append(" AND ta81.ID_TAREA_081 = tac2.ID_TAREA_AUDITAR_0C2 ");
		query.append(" JOIN AA79B77S01 ta77 ON  NVL(tac2.DNI_AUDITOR_0C2, tr81.DNI_RECURSO_081) = ta77.DNI_077 ");
		return query.toString();
	}

	/**
	 *
	 * @return String
	 */
	private String getWhere() {
		final StringBuilder query = new StringBuilder();
		query.append(" WHERE 1=1 ");
		query.append(" AND tr81.ID_TIPO_TAREA_081 IN ( "+TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_REVISION.getValue());
		query.append(" , "+TipoTareaGestionAsociadaEnum.REVISAR_TRADUCCION.getValue()+" ) ");
		query.append(" AND tr81.ESTADO_EJECUCION_081 = "+EstadoEjecucionTareaEnum.EJECUTADA.getValue()+" ");
		return query.toString();
	}

	private String getOrderBy() {
		final StringBuilder query = new StringBuilder();
		query.append(" ORDER BY ta77.DNI_077  ASC, tr51.ANYO_051 ASC, tr51.NUM_EXP_051 ASC");
		return query.toString();
	}

	/**
	 *
	 * @param baseSql BaseSql
	 * @param numMaxDiasAvisoAuditoria Long
	 * @return List<Tarea>
	 * @throws Exception
	 */
	public List<TareaAuditoria> findAuditoriasSinInfoSinEnviar(BaseSql baseSql, Long numMaxDiasAvisoAuditoria) throws Exception {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		query.append(this.getSelect());
		query.append(this.getFromIZO());
		query.append(this.getWhere());
		query.append(" AND TRUNC(tr82.FECHA_EJECUCION_082) <= TRUNC(SYSDATE) - ? ");
		query.append(" AND NVL(tac2.ESTADO_AUDITORIA_0C2, "+EstadoAuditoriaEnum.SIN_ENVIAR.getValue()+") NOT IN  (?,?) ");
		query.append(this.getOrderBy());
		params.add(numMaxDiasAvisoAuditoria);
		params.add(EstadoAuditoriaEnum.ENVIADA.getValue());
		params.add(EstadoAuditoriaEnum.CONFIRMADA.getValue());
		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), this.rwMapTarea, baseSql);
	}

	/**
	 *
	 * @param baseSql
	 * @param numMaxDiasAvisoAuditoria
	 * @return
	 * @throws Exception
	 */
	public List<TareaAuditoria> findAuditoriasSinConfirmar(BaseSql baseSql, Long numMaxDiasAvisoAuditoria) throws Exception {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		query.append(this.getSelect());
		query.append(this.getFromProveedor());
		query.append(this.getWhere());
		query.append(" AND TRUNC(tac2.FECHA_AUDITORIA_0C2) <= TRUNC(SYSDATE) - ? ");
		query.append(" AND tac2.ESTADO_AUDITORIA_0C2 = ? ");
		query.append(this.getOrderBy());
		params.add(numMaxDiasAvisoAuditoria);
		params.add(EstadoAuditoriaEnum.ENVIADA.getValue());
		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), this.rwMapTarea, baseSql);
	}




	/**
	 *
	 * @param baseSql BaseSql
	 * @return Long
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	public Long findNumMaxDiasAvisoAuditoria(BaseSql baseSql) throws NumberFormatException, Exception {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		query.append(" SELECT VALOR_000 ");
		query.append(" FROM AA79B00T00 ");
		query.append(" WHERE ID_000 = ? ");
		params.add(PropertiesConstants.DIAS_PLAZO_AVISO_AUDITORIA);

		return  Long.valueOf(new AccesoBD().fncConsultaEspecifica(query.toString(), params.toArray(), baseSql));
	}

}
