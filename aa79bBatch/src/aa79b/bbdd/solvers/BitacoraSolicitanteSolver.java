package aa79b.bbdd.solvers;

import java.util.ArrayList;
import java.util.List;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.BaseSqlTransaccional;
import aa79b.util.beans.BitacoraSolicitante;
import aa79b.util.common.AccesoBD;
import aa79b.util.common.AccesoBDTransaccional;

/**
 * @author mrodriguez
 *
 */
public class BitacoraSolicitanteSolver {
	
	/**
	 * Calcula en siguiente valor para el parametro id de la bitacora para el expediente indicado
	 *
	 * @param BaseSql baseSql
	 * @return Long
	 * @throws Exception Cualquier excepción
	 */
	public Long getNextVal(BaseSql baseSql) throws Exception {
        StringBuilder query = new StringBuilder();
        query.append("SELECT AA79B79Q00.NEXTVAL FROM DUAL");
        return Long.valueOf(new AccesoBD().fncConsultaEspecifica(query.toString(), null, baseSql));
    }
	
	public Long getNextValTransaccional(BaseSqlTransaccional baseSql) throws Exception {
		StringBuilder query = new StringBuilder();
		query.append("SELECT AA79B79Q00.NEXTVAL FROM DUAL");
		return Long.valueOf(new AccesoBDTransaccional().fncConsultaEspecifica(query.toString(), null, baseSql));
	}
	
	/**
	 * Inserta un nuevo registro para la bitacora del solicitante
	 *
	 * @param BaseSql baseSql
	 * @param BitacoraSolicitante bitacoraSolicitante
	 * @throws Exception Cualquier excepción
	 */
	public void add(BaseSql baseSql, BitacoraSolicitante bitacoraSolicitante) throws Exception {
		Long id = this.getNextVal(baseSql);
		bitacoraSolicitante.setId(id);
		
		final String query = "INSERT INTO AA79B79S01 (ID_079, ANYO_079, NUM_EXP_079,"
				+ "ID_ACCION_BITACORA_079, FECHA_ALTA_079, USUARIO_079) " + "VALUES (?,?,?,?,SYSDATE,?)";

		final List<Object> params = new ArrayList<Object>();

		params.add(bitacoraSolicitante.getId());
		params.add(bitacoraSolicitante.getAnyo());
		params.add(bitacoraSolicitante.getNumExp());
		params.add(bitacoraSolicitante.getIdAccion());
		params.add(bitacoraSolicitante.getUsuario());

		new AccesoBD().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
	}
	public void add(BaseSqlTransaccional baseSql, BitacoraSolicitante bitacoraSolicitante) throws Exception {
		Long id = this.getNextValTransaccional(baseSql);
		bitacoraSolicitante.setId(id);
		
		final String query = "INSERT INTO AA79B79S01 (ID_079, ANYO_079, NUM_EXP_079,"
				+ "ID_ACCION_BITACORA_079, FECHA_ALTA_079, USUARIO_079) " + "VALUES (?,?,?,?,SYSDATE,?)";
		
		final List<Object> params = new ArrayList<Object>();
		
		params.add(bitacoraSolicitante.getId());
		params.add(bitacoraSolicitante.getAnyo());
		params.add(bitacoraSolicitante.getNumExp());
		params.add(bitacoraSolicitante.getIdAccion());
		params.add(bitacoraSolicitante.getUsuario());
		
		new AccesoBDTransaccional().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
	}
	
}
