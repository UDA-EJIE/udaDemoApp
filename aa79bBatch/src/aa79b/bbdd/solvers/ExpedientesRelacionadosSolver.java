package aa79b.bbdd.solvers;

import aa79b.bbdd.BaseSql;
import aa79b.util.common.AccesoBD;
import aa79b.util.common.Logger;

public class ExpedientesRelacionadosSolver {

	public void addRelaciones(BaseSql baseSql) throws Exception {

		Logger.batchlog(Logger.ERROR, "Entra en el solver");

		new AccesoBD().fncLanzaProcedimiento("{call REL_EXP_REF_TRAM()}", null, baseSql);

		Logger.batchlog(Logger.ERROR, "Sale del solver");

	}

}
