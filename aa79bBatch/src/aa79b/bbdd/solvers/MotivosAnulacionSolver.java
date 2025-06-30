package aa79b.bbdd.solvers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.RowMapper;
import aa79b.util.beans.MotivosAnulacion;
import aa79b.util.common.AccesoBD;

/**
 * @author mrodriguez
 */
public class MotivosAnulacionSolver {
	
	/*
	 * ROW_MAPPERS
	 */
	private final RowMapper<MotivosAnulacion> rwMap = new RowMapper<MotivosAnulacion>() {
		@Override
		public MotivosAnulacion mapRow(ResultSet resultSet, int rowNum) throws SQLException {

			final MotivosAnulacion motivosAnulacion = new MotivosAnulacion();
			motivosAnulacion.setId012(resultSet.getLong("ID"));
			motivosAnulacion.setDescEs012(resultSet.getString("DESCES"));
			motivosAnulacion.setDescEu012(resultSet.getString("DESCEU"));
			motivosAnulacion.setEstado012(resultSet.getString("ESTADO"));

			return motivosAnulacion;
		}
	};
	
	/**
	 * @param BaseSql baseSql
	 * @return List<MotivosAnulacion>
	 * @throws Exception Cualquier excepción
	 */
	public List<MotivosAnulacion> findAll(BaseSql baseSql) throws Exception {

		final List<Object> params = new ArrayList<Object>();
		final StringBuilder query = new StringBuilder();
		query.append("SELECT  t1.ID_012 ID, "
				+ "t1.DESC_EU_012 DESCEU, "
				+ "t1.DESC_ES_012 DESCES, "
				+ "t1.ESTADO_012 ESTADO "
				+ "FROM AA79B12S01 t1 ");
		
		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), this.rwMap, baseSql);
	}

}
