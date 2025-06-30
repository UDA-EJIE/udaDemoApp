/**
 *
 */
package aa79b.bbdd.solvers;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.RowMapper;
import aa79b.util.beans.DatosContacto;
import aa79b.util.beans.Expediente;
import aa79b.util.common.AccesoBD;

/**
 * @author aresua
 *
 */
public class DatosContactoSolver {

	/*
	 * ROW_MAPPERS
	 */
	private final RowMapper<DatosContacto> rwMap = new RowMapper<DatosContacto>() {
		@Override
		public DatosContacto mapRow(ResultSet resultSet, int rowNum) throws SQLException {

			final DatosContacto datosContacto = new DatosContacto();
			datosContacto.setEmail031(resultSet.getString("EMAIL"));

			return datosContacto;
		}
	};

	/**
	 * @param BaseSql baseSql
	 * @param String dni
	 * @return List<DatosContacto>
	 * @throws Exception Cualquier excepción
	 */
	public List<DatosContacto> getMailRecurso(BaseSql baseSql, String dni) throws Exception {

		final StringBuilder query = new StringBuilder();

		query.append("SELECT t1.EMAIL_031 EMAIL ");
		query.append(" FROM X54JAPI_DATOS_CONTACTO t1 ");
		query.append(" WHERE t1.DNI_031 = ? ");

		final List<Object> params = new ArrayList<Object>();
		params.add(dni);

		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), this.rwMap, baseSql);
	}

	/**
	 * @param BaseSql baseSql
	 * @param BigDecimal idTarea
	 * @return List<DatosContacto>
	 * @throws Exception Cualquier excepción
	 */
	public List<DatosContacto> getReceptoresPorTarea(BaseSql baseSql, String idTarea) throws Exception {

		final StringBuilder query = new StringBuilder();

		query.append("SELECT T3.EMAIL_031 EMAIL ");
		query.append(" FROM AA79B81T00 T1 ");
		query.append(" JOIN AA79B63T00 T2 ");
		query.append(" ON T1.ANYO_081 = T2.ANYO_063 ");
		query.append(" AND T1.NUM_EXP_081 = T2.NUM_EXP_063 ");
		query.append(" JOIN X54JAPI_DATOS_CONTACTO T3 ");
		query.append(" ON T2.DNI_RECEPTOR_063 = T3.DNI_031 ");
		query.append(" WHERE T1.ID_TAREA_081 = ? ");

		final List<Object> params = new ArrayList<Object>();
		params.add(idTarea);

		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), this.rwMap, baseSql);
	}

	/**
	 * @param BaseSql baseSql
	 * @param Expediente expediente
	 * @return List<DatosContacto>
	 * @throws Exception Cualquier excepción
	 */
	public List<DatosContacto> getMailAsignadorExpediente(BaseSql baseSql, Expediente expediente) throws Exception {

		final StringBuilder query = new StringBuilder();
		query.append("SELECT T2.EMAIL_031 EMAIL FROM AA79B51T00 T1 "
				+ " JOIN X54JAPI_DATOS_CONTACTO T2 ON T1.DNI_ASIGNADOR_051 = T2.DNI_031 "
				+ " WHERE T1.ANYO_051 = ? AND T1.NUM_EXP_051 = ? ");

		final List<Object> params = new ArrayList<Object>();
		params.add(expediente.getAnyo());
		params.add(expediente.getNumExp());

		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), this.rwMap, baseSql);
	}

	/**
	 * @param BaseSql baseSql
	 * @param BigDecimal idTarea
	 * @return List<DatosContacto>
	 * @throws Exception Cualquier excepción
	 */
	public List<DatosContacto> getContactosPorLotePorTarea(BaseSql baseSql, BigDecimal idTarea) throws Exception {

		final StringBuilder query = new StringBuilder();

		query.append("SELECT T4.EMAIL_031 EMAIL ");
		query.append(" FROM AA79B81T00 T1 ");
		query.append(" JOIN AA79B29T00 T2 ");
		query.append(" ON T1.ID_LOTE_081 = T2.ID_LOTE_029 ");
		query.append(" JOIN X54JAPI_PROVEEDORES T3 ");
		query.append(" ON T2.ID_EMPRESA_PROV_029 = T3.COD_ENTIDAD ");
		query.append(" AND T2.TIPO_ENTIDAD_029 = T3.TIPO_ENTIDAD ");
		query.append(" JOIN X54JAPI_DATOS_CONTACTO T4 ");
		query.append(" ON T3.DNI = T4.DNI_031 ");
		query.append(" WHERE T1.ID_TAREA_081 = ? ");

		final List<Object> params = new ArrayList<Object>();
		params.add(idTarea);

		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), this.rwMap, baseSql);
	}

	/**
	 *
	 * @param baseSql BaseSql
	 * @param idLote Long
	 * @return List<DatosContacto>
	 * @throws Exception
	 */
	public List<DatosContacto> getEmailsDeLotePorIdLote(BaseSql baseSql, Integer idLote) throws Exception {
		final StringBuilder query = new StringBuilder();

		query.append("SELECT T3.EMAIL_031 EMAIL ");
		query.append(" FROM AA79B29T00 T1 ");
		query.append(" JOIN X54JAPI_PROVEEDORES T2 ");
		query.append(" ON T1.ID_EMPRESA_PROV_029 = T2.COD_ENTIDAD ");
		query.append(" AND T1.TIPO_ENTIDAD_029 = T2.TIPO_ENTIDAD ");
		query.append(" JOIN X54JAPI_DATOS_CONTACTO T3 ");
		query.append(" ON T2.DNI = T3.DNI_031 ");
		query.append(" WHERE T1.ID_LOTE_029 = ?");

		final List<Object> params = new ArrayList<Object>();
		params.add(idLote);

		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), this.rwMap, baseSql);
	}

}
