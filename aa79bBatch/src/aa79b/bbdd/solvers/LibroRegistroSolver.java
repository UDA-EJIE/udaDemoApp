package aa79b.bbdd.solvers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import aa79b.bbdd.BaseSql;
import aa79b.util.beans.LibroRegistro;
import aa79b.util.common.AccesoBD;

/**
 * @author javarona
 *
 */
public class LibroRegistroSolver {
	
	private static final DateFormat SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 
	 * @param baseSql
	 * @param libroregistro
	 * @return
	 * @throws Exception
	 */
	public LibroRegistro add(BaseSql baseSql, LibroRegistro libroregistro) throws Exception {
		final String query = "INSERT INTO AA79B80S01 (ID_080, ANYO_080, NUM_EXP_080, MATTER_080, TELEMATICO_080, FECHA_REGISTRO_080, NUM_REGISTRO_080) VALUES (AA79B80Q00.NEXTVAL,?,?,?,?,TO_DATE(?,'yyyy-MM-dd hh24:mi:ss'),?)";
		
		final List<Object> params = new ArrayList<Object>();
		params.add(libroregistro.getAnyo());
		params.add(libroregistro.getNumExp());
		params.add(libroregistro.getMatter());
		params.add(libroregistro.getTelematico());
		String x = SQL_DATE_FORMAT.format(libroregistro.getFechaRegistro());
		params.add(x);
		params.add(libroregistro.getNumRegistro());
		new AccesoBD().fncLanzaMovimiento(query, params.toArray(), baseSql);
		return libroregistro;
	}

}
