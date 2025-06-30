package aa79b.bbdd.solvers;

import java.util.ArrayList;
import java.util.List;

import aa79b.bbdd.BaseSql;
import aa79b.util.common.AccesoBD;
import aa79b.util.common.ParametroSQL;

/**
 *
 * @author eaguirresarobe
 *
 */
public class CrearCalendarioAnyoSigSolver {


	/**
	 *
	 * @return String
	 */
	private String getSelect() {
		final StringBuilder query = new StringBuilder();
		query.append(" SELECT COUNT(1) ");
		return query.toString();
	}

	/**
	 *
	 * @return String
	 */
	private String getFrom() {
		final StringBuilder query = new StringBuilder();
		query.append(" FROM AA79B22S01 t22 ");
		return query.toString();
	}

	/**
	 *
	 * @return String
	 */
	private String getWhere() {
		final StringBuilder query = new StringBuilder();
		query.append(" WHERE 1=1 ");
		query.append(" AND t22.ANYO_CALENDARIO_022 = ?");
		return query.toString();
	}

	/**
	 * @param BaseSql baseSql
	 * @param String idTarea
	 * @return Long
	 * @throws Exception Cualquier excepción
	 */
	public Long findNextYearCount(BaseSql baseSql, int year) throws Exception {
		StringBuilder query = new StringBuilder();
		query.append(this.getSelect());
		query.append(this.getFrom());
		query.append(this.getWhere());

		final List<Object> params = new ArrayList<Object>();

		params.add(year);

		return Long.valueOf(new AccesoBD().fncConsultaEspecifica(query.toString(), params.toArray(), baseSql));
	}

	public void crearCalendario(BaseSql baseSql, int year) throws Exception {
		final List<Object> params = new ArrayList<Object>();
		ParametroSQL param = new ParametroSQL(ParametroSQL.TIPO_SQL_INTEGER, ParametroSQL.TIPO_PARAMETRO_IN, String.valueOf(year));
		params.add(param);
		new AccesoBD().fncLanzaProcedimiento("{call CARGAR_CALENDARIO_SERVICIO(?)}", params.toArray(), baseSql);

	}
}
