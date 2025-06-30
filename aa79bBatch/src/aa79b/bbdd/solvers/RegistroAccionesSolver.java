package aa79b.bbdd.solvers;

import java.util.ArrayList;
import java.util.List;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.BaseSqlTransaccional;
import aa79b.util.beans.RegistroAcciones;
import aa79b.util.common.AccesoBD;
import aa79b.util.common.AccesoBDTransaccional;
import aa79b.util.common.Constants;

/**
 * @author mrodriguez
 *
 */
public class RegistroAccionesSolver {
	
	/**
	 * Inserta un nuevo registro para la bitacora del expediente
	 *
	 * @param BaseSql baseSql
	 * @param RegistroAcciones registroAcciones
	 * @throws Exception Cualquier excepción
	 */
	public void add(BaseSql baseSql, RegistroAcciones registroAcciones) throws Exception {
		final String query = "INSERT INTO AA79B43S01 (ID_REGISTRO_ACCION_043, ID_PUNTO_MENU_043, ID_ACCION_043, IP_043, "
				+ "FECHA_REGISTRO_043, USUARIO_REGISTRO_043, ANYO_EXP_043, NUM_EXP_043, "
				+ "OBSERV_043, ID_ESTADO_BITACORA_043) VALUES (AA79B43Q00.NEXTVAL,?,?,?,SYSDATE,?,?,?,?,?)";

		final List<Object> params = new ArrayList<Object>();

		params.add(registroAcciones.getIdPuntoMenu());
		params.add(registroAcciones.getIdAccion());
		// TODO Revisar Ip puede ser nula en BBDD. Envio null
		// ¿Es necesario cargar algún dato en este campo para un proceso batch?
		params.add(null);
		// TODO Revisar el usuario a enviar
		params.add(Constants.IZO);
		params.add(registroAcciones.getAnyoExp());
		params.add(registroAcciones.getNumExp());
		params.add(registroAcciones.getObserv());
		params.add(registroAcciones.getIdEstadoBitacora());

		new AccesoBD().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
	}
	public void add(BaseSqlTransaccional baseSql, RegistroAcciones registroAcciones) throws Exception {
		final String query = "INSERT INTO AA79B43S01 (ID_REGISTRO_ACCION_043, ID_PUNTO_MENU_043, ID_ACCION_043, IP_043, "
				+ "FECHA_REGISTRO_043, USUARIO_REGISTRO_043, ANYO_EXP_043, NUM_EXP_043, "
				+ "OBSERV_043, ID_ESTADO_BITACORA_043) VALUES (AA79B43Q00.NEXTVAL,?,?,?,SYSDATE,?,?,?,?,?)";
		
		final List<Object> params = new ArrayList<Object>();
		
		params.add(registroAcciones.getIdPuntoMenu());
		params.add(registroAcciones.getIdAccion());
		// TODO Revisar Ip puede ser nula en BBDD. Envio null
		// ¿Es necesario cargar algún dato en este campo para un proceso batch?
		params.add(null);
		// TODO Revisar el usuario a enviar
		params.add(Constants.IZO);
		params.add(registroAcciones.getAnyoExp());
		params.add(registroAcciones.getNumExp());
		params.add(registroAcciones.getObserv());
		params.add(registroAcciones.getIdEstadoBitacora());
		
		new AccesoBDTransaccional().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
	}

}
