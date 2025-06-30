package aa79b.bbdd.solvers;

import java.util.ArrayList;
import java.util.List;

import aa79b.bbdd.BaseSql;
import aa79b.util.beans.AnulacionExpediente;
import aa79b.util.common.AccesoBD;

/**
 * @author mrodriguez
 *
 */
public class AnulacionExpedienteSolver {
		
	/**
	 * Calcula en siguiente valor para el parametro id de la bitacora para el expediente indicado
	 *
	 * @param BaseSql baseSql
	 * @return Long
	 * @throws Exception Cualquier excepción
	 */
	public Long getNextVal(BaseSql baseSql) throws Exception {
        StringBuilder query = new StringBuilder();
        query.append("SELECT AA79BA1Q00.NEXTVAL FROM DUAL");
        return Long.valueOf(new AccesoBD().fncConsultaEspecifica(query.toString(), null, baseSql));
    }
	
	/**
	 * Obtiene el listado de expedientes a anular
	 *
	 * @param BaseSql baseSql
	 * @param AnulacionExpediente anulacionExpediente
	 * @throws Exception Cualquier excepción
	 */
	public void add(BaseSql baseSql, AnulacionExpediente anulacionExpediente) throws Exception {
		if (anulacionExpediente.getId() == null){
			Long id = this.getNextVal(baseSql);
			anulacionExpediente.setId(id);
		}
		
		final String query = "INSERT INTO AA79BA1S01 (ID_0A1, ID_MOTIVO_ANULACION_0A1) VALUES (?,?)";

		final List<Object> params = new ArrayList<Object>();

		params.add(anulacionExpediente.getId());
		params.add(anulacionExpediente.getIdMotivoAnulacion());
		
		new AccesoBD().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
	}

}
