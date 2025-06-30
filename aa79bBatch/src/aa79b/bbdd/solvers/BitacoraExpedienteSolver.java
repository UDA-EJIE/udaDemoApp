package aa79b.bbdd.solvers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.RowMapper;
import aa79b.util.beans.BitacoraExpediente;
import aa79b.util.common.AccesoBD;
import aa79b.util.common.EstadoExpedienteEnum;
import aa79b.util.common.FaseExpedienteEnum;

/**
 * @author mrodriguez
 *
 */
public class BitacoraExpedienteSolver {
	
	private final RowMapper<BitacoraExpediente> rwMapBitacoraTarea = new RowMapper<BitacoraExpediente>() {
		@Override
		public BitacoraExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			final BitacoraExpediente tarea = new BitacoraExpediente();
			tarea.setIdEstadoBitacora(resultSet.getLong("IDESTADOBITACORA"));
			tarea.setAnyo(resultSet.getLong("ANYO"));
			tarea.setNumExp(resultSet.getInt("NUMEXP"));
			return tarea;
		}
	};
	
	/**
	 * Inserta un nuevo registro para la bitacora del expediente
	 *
	 * @param BaseSql baseSql
	 * @param BitacoraExpediente bitacoraExpediente
	 * @throws Exception Cualquier excepción
	 */
	public void add(BaseSql baseSql, BitacoraExpediente bitacoraExpediente) throws Exception {
		final String query = "INSERT INTO AA79B59S01 (ANYO_059, NUM_EXP_059, ID_ESTADO_BITACORA_059, ID_ESTADO_EXP_059, "
				+ "ID_FASE_EXPEDIENTE_059, FECHA_ALTA_059, ID_ANULACION_059) " + "VALUES (?,?,?,?,?,SYSDATE,?)";

		final List<Object> params = new ArrayList<Object>();

		params.add(bitacoraExpediente.getAnyo());
		params.add(bitacoraExpediente.getNumExp());
		params.add(bitacoraExpediente.getIdEstadoBitacora());
		params.add(EstadoExpedienteEnum.ANULADO.getValue());
		params.add(FaseExpedienteEnum.ANULADO.getValue());
		params.add(bitacoraExpediente.getIdMotivoAnulacion());

		new AccesoBD().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
	}
	
	/**
	 * Calcula en siguiente valor para el parametro id de la bitacora para el expediente indicado
	 *
	 * @param BaseSql baseSql
	 * @param BitacoraExpediente bitacoraExpediente
	 * @return Long
	 * @throws Exception Cualquier excepción
	 */
	public Long getNextVal(BaseSql baseSql, BitacoraExpediente bitacoraExpediente) throws Exception {
		StringBuilder query = new StringBuilder();
		query.append("( SELECT nvl(max(ID_ESTADO_BITACORA_059),0) + 1");
		query.append(" FROM AA79B59S01 t1 ");
		query.append(" WHERE NUM_EXP_059 = ? ");
		query.append(" AND ANYO_059 = ? ");
		query.append(" )");
		
		final List<Object> params = new ArrayList<Object>();

		params.add(bitacoraExpediente.getNumExp());
		params.add(bitacoraExpediente.getAnyo());
		
		return Long.valueOf(new AccesoBD().fncConsultaEspecifica(query.toString(), params.toArray(), baseSql));
	}
	
	
	/**
	 * @param BaseSql baseSql
	 * @param String idTarea
	 * @return Long
	 * @throws Exception Cualquier excepción
	 */
	public List<BitacoraExpediente> getInfoBitacora(BaseSql baseSql, String idTarea) throws Exception {
		final StringBuilder query = new StringBuilder("SELECT ID_ESTADO_BITACORA_059 IDESTADOBITACORA, ANYO_051 ANYO, NUM_EXP_051 NUMEXP FROM AA79B81S01 ");
		query.append(" JOIN AA79B51S01 ON ANYO_081 = ANYO_051 AND NUM_EXP_081 = NUM_EXP_051 ");
		query.append(" JOIN AA79B59S01 ON ANYO_059 = ANYO_051 AND NUM_EXP_059 = NUM_EXP_051 AND ESTADO_BITACORA_051 = ID_ESTADO_BITACORA_059 ");
		query.append(" WHERE AA79B81S01.ID_TAREA_081 = ? ");
		
		final List<Object> params = new ArrayList<Object>();
		params.add(idTarea);

		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), this.rwMapBitacoraTarea, baseSql);
	}
	
	

}
