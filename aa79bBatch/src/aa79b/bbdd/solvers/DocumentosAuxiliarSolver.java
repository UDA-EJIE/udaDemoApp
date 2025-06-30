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
import aa79b.util.beans.DocumentosExpediente;
import aa79b.util.beans.Statement;
import aa79b.util.common.AccesoBD;

/**
 * @author aresua
 *
 */
public class DocumentosAuxiliarSolver {

	/*
	 * ROW_MAPPERS
	 */
	private final RowMapper<DocumentosExpediente> rwMap = new RowMapper<DocumentosExpediente>() {
		@Override
		public DocumentosExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {

			final DocumentosExpediente doc = new DocumentosExpediente();
			doc.setOid(resultSet.getString("OID"));
			return doc;
		}
	};

	/**
	 *
	 * @param baseSql BaseSql
	 * @return List<DocumentosExpediente>
	 * @throws Exception e
	 */
	public List<DocumentosExpediente> findABorrar(BaseSql baseSql) throws Exception{
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		query.append("SELECT OID_0A0 OID FROM AA79BA0S01 WHERE FECHA_0A0 < SYSDATE - 1");

		return new AccesoBD().fncLanzaBusqueda(query.toString(), params.toArray(), this.rwMap, baseSql);
	}

	/**
	 * @param BaseSql baseSql
	 * @param List<DocumentosExpediente> lista
	 * @throws Exception Cualquier excepción
	 */
	public void removeLista(BaseSql baseSql, List<DocumentosExpediente> lista) throws Exception {
		List<Statement> arrSQLs = new ArrayList<Statement>();

		Statement st = new Statement();
		st.setSql("DELETE FROM AA79BA0S01 WHERE OID_0A0 = ? ");
		
		for(final DocumentosExpediente doc: lista){
			List<Object> params = new ArrayList<Object>();
			params.add(doc.getOid());
			st.addParametro(params);
		}
		
		arrSQLs.add(st);

		new AccesoBD().fncLanzaMovimientosBatch(arrSQLs, baseSql);
	}

}
