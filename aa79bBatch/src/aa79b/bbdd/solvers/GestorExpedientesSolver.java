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
import aa79b.util.beans.DatosContacto;
import aa79b.util.beans.Expediente;
import aa79b.util.beans.GestorExpediente;
import aa79b.util.common.AccesoBD;

/**
 * @author aresua
 *
 */
public class GestorExpedientesSolver {

	/*
	 * ROW_MAPPERS
	 */
	private final RowMapper<GestorExpediente> rwMap = new RowMapper<GestorExpediente>() {
		@Override
		public GestorExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {

			final GestorExpediente gestorExpediente = new GestorExpediente();
			gestorExpediente.setAnyo054(resultSet.getString("ANYO"));
			gestorExpediente.setNumexp054(resultSet.getString("NUMEXP"));
			gestorExpediente.setDnisolicitante054(resultSet.getString("DNISOLICITANTE"));
			final DatosContacto datosContacto = new DatosContacto();
			datosContacto.setEmail031(resultSet.getString("EMAIL"));
			gestorExpediente.setDatosContacto(datosContacto);
			return gestorExpediente;
		}
	};

	private final RowMapper<GestorExpediente> rwMapGestor = new RowMapper<GestorExpediente>() {
		@Override
		public GestorExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {

			final GestorExpediente gestorExpediente = new GestorExpediente();
			gestorExpediente.setAnyo054(resultSet.getString("ANYO"));
			gestorExpediente.setNumexp054(resultSet.getString("NUMEXP"));
			final DatosContacto datosContacto = new DatosContacto();
			datosContacto.setEmail031(resultSet.getString("EMAIL"));
			gestorExpediente.setDatosContacto(datosContacto);
			return gestorExpediente;
		}
	};

	/**
	 * @param BaseSql baseSql
	 * @param BigDecimal idTarea
	 * @return List<GestorExpediente>
	 * @throws Exception Cualquier excepción
	 */
	public List<GestorExpediente> getGestorExpedientesPorTarea(BaseSql baseSql, String idTarea) throws Exception {

		final StringBuilder query = new StringBuilder();
		query.append("SELECT T1.ANYO_081 ANYO, T1.NUM_EXP_081 NUMEXP, T2.DNI_SOLICITANTE_054 DNISOLICITANTE, T3.EMAIL_031 EMAIL FROM AA79B81T00 T1 "
				+ " JOIN AA79B54T00 T2 ON T1.ANYO_081 = T2.ANYO_054 AND T1.NUM_EXP_081 = T2.NUM_EXP_054 "
				+ " JOIN X54JAPI_DATOS_CONTACTO T3 ON T2.DNI_SOLICITANTE_054 = T3.DNI_031 "
				+ " WHERE T1.ID_TAREA_081 = ?");

		final List<Object> params = new ArrayList<Object>();
		params.add(idTarea);

		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), rwMap, baseSql);
	}


	/**
	 * @param BaseSql baseSql
	 * @param Expediente expediente
	 * @return GestorExpediente
	 * @throws Exception Cualquier excepción
	 */
	public GestorExpediente getGestorExpediente(BaseSql baseSql, Expediente expediente) throws Exception {

		final StringBuilder query = new StringBuilder();
		query.append("SELECT t1.ANYO_054 ANYO, "
				+ "t1.NUM_EXP_054 NUMEXP, "
				+ "d1.EMAIL_031 EMAIL "
				+ "FROM AA79B54S01 t1 "
				+ "LEFT JOIN X54JAPI_DATOS_CONTACTO d1 "
				+ "ON t1.DNI_SOLICITANTE_054 = d1.DNI_031 "
				+ "WHERE t1.ANYO_054 = ? AND t1.NUM_EXP_054 = ?");


		final List<Object> params = new ArrayList<Object>();

		params.add(expediente.getAnyo());
		params.add(expediente.getNumExp());

		List<GestorExpediente> listGestores = new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), rwMapGestor, baseSql);

		GestorExpediente gestor = null;

		if(listGestores!=null && !listGestores.isEmpty()){
            gestor = listGestores.get(0);
        }

		return gestor;
	}

}
