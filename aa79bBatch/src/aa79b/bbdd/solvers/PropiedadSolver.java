package aa79b.bbdd.solvers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.RowMapper;
import aa79b.util.beans.Propiedad;
import aa79b.util.common.AccesoBD;

/**
 * @author mrodriguez
 */
public class PropiedadSolver {
	
	/*
	 * ROW_MAPPERS
	 */
	private RowMapper<Propiedad> rwMap = new RowMapper<Propiedad>() {
        @Override
        public Propiedad mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            final Propiedad bloqueo = new Propiedad();
            bloqueo.setId(resultSet.getInt("ID"));
            bloqueo.setValor(resultSet.getString("VALOR"));
            return bloqueo;
        }
    };
	
	
	/**
	 * Obtiene el listado de expedientes a anular
	 *
	 * @param BaseSql baseSql
	 * @param Integer id
	 * @return Propiedad
	 * @throws Exception Cualquier excepción
	 */
	public Propiedad find(BaseSql baseSql, Integer id) throws Exception {
		final String query = "SELECT t1.ID_000 ID, "
				+ "t1.VALOR_000 VALOR  "
				+ "FROM AA79B00S01 t1 "
				+ "WHERE t1.ID_000 = ? ";

		final List<Object> params = new ArrayList<Object>();

		params.add(id);

		final List<Propiedad> listPropiedades = new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), this.rwMap, baseSql);
		
		if(listPropiedades==null || listPropiedades.isEmpty()){
            return null;
        } else {
            return listPropiedades.get(0);
        }
	}

}
