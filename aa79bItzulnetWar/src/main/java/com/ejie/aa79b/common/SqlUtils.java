package com.ejie.aa79b.common;

import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.ejie.aa79b.model.enums.IdiomaEnum;
import com.ejie.aa79b.utils.Utils;

/**
 * @author vdorantes
 *
 */
public final class SqlUtils {

	/**
	 * El metodo constructor.
	 */
	private SqlUtils() {
	}

	private static final String AND = " AND ";
	/**
	 * Wildcard de Oracle utilizado para referenciar a varios caracteres.
	 */
	private static final String ORACLE_WILDCARD = "%";

	/**
	 * @param campoSql String
	 * @return String
	 */

	public static String normalizarCampoSql(String campoSql) {
		final StringBuilder sqlBuffer = new StringBuilder(Constants.BUFFER_SIZE);

		sqlBuffer.append(" TRANSLATE(UPPER(");
		sqlBuffer.append(campoSql);
		sqlBuffer.append("),'");
		sqlBuffer.append(SqlConstantes.CARACTERES_A_TRADUCIR);
		sqlBuffer.append("','");
		sqlBuffer.append(SqlConstantes.CARACTERES_TRADUCIDOS);
		sqlBuffer.append("') ");
		return sqlBuffer.toString();
	}

	/**
	 * The method generarWhereIgual.
	 *
	 * @param campo  String
	 * @param valor  Object
	 * @param params List<Object>
	 * @return String
	 */
	public static String generarWhereIgual(String campo, Object valor, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valor != null) {
			if (valor instanceof String) {
				String str = (String) valor;
				if (StringUtils.isNotBlank(str)) {
					where.append(SqlUtils.AND).append(campo).append(" = ?");
					params.add(str);
				}
			} else {
				where.append(SqlUtils.AND).append(campo).append(" = ?");
				params.add(valor);
			}
		}
		return where.toString();
	}

	/**
	 * The method generarWhereIgualSinAnd.
	 *
	 * @param campo  String
	 * @param valor  Object
	 * @param params List<Object>
	 * @return String
	 */
	public static String generarWhereIgualSinAnd(String campo, Object valor, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valor != null) {
			where.append(campo).append(" = ?");
			params.add(valor);
		}
		return where.toString();
	}

	/**
	 * The method generarWhereIgual case insensitive.
	 *
	 * @param campo  String
	 * @param valor  Object
	 * @param params List<Object>
	 * @return String
	 */
	public static String generarWhereIgualCaseInsensitive(String campo, Object valor, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valor != null) {
			if (valor instanceof String) {
				String str = (String) valor;
				if (StringUtils.isNotBlank(str)) {
					where.append(SqlUtils.AND).append(" UPPER(").append(campo).append(") = ?");
					params.add(str);
				}
			} else {
				where.append(SqlUtils.AND).append(campo).append(" = ?");
				params.add(valor);
			}
		}
		return where.toString();
	}

	/**
	 * The method generarWhereIsNull.
	 *
	 * @param campo String
	 * @return String
	 */
	public static String generarWhereIsNull(String campo, boolean esAnd) {
		final StringBuilder where = new StringBuilder();
		where.append(esAnd ? DaoConstants.AND : DaoConstants.OR).append(campo).append(DaoConstants.IS_NULL);
		return where.toString();
	}

	/**
	 * The method generarWhereIsNotNull.
	 *
	 * @param campo String
	 * @return String
	 */
	public static String generarWhereIsNotNull(String campo) {
		final StringBuilder where = new StringBuilder();
		where.append(SqlUtils.AND).append(campo).append(DaoConstants.IS_NOT_NULL);
		return where.toString();
	}

	/**
	 * The method generarWhereIgual.
	 *
	 * @param campo  String
	 * @param valor  Object
	 * @param params List<Object>
	 * @return String
	 */

	public static String generarWhereIgualDni(String campo, String valor, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (StringUtils.isNotBlank(valor)) {
			where.append(SqlUtils.AND).append(campo).append(" = ?");
			params.add(Utils.quitarLetrasDni(valor));
		}
		return where.toString();
	}

	/**
	 * The method generarWhereIgualTrim.
	 *
	 * @param campo  String
	 * @param valor  Object
	 * @param params List<Object>
	 * @return String
	 */
	public static String generarWhereIgualTrim(String campo, Object valor, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valor != null) {
			where.append(" AND LTRIM(").append(campo).append(",0) = LTRIM(?,0)");
			params.add(valor);
		}
		return where.toString();
	}

	/**
	 * The method generarWhereDistinto.
	 *
	 * @param campo  String
	 * @param valor  Object
	 * @param params List<Object>
	 * @return String
	 */
	public static String generarWhereDistinto(String campo, Object valor, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valor != null) {
			where.append(SqlUtils.AND).append(campo).append(" != ?");
			params.add(valor);
		}
		return where.toString();
	}

	/**
	 * The method generarWhereMayor.
	 *
	 * @param campo  String
	 * @param valor  Object
	 * @param params List<Object>
	 * @return String
	 */
	public static String generarWhereMayor(String campo, Object valor, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valor != null) {
			where.append(SqlUtils.AND).append(campo).append(" > ?");
			params.add(valor);
		}
		return where.toString();
	}

	/**
	 * The method generarWhereMayorIgual.
	 *
	 * @param campo  String
	 * @param valor  Object
	 * @param params List<Object>
	 * @return String
	 */
	public static String generarWhereMayorIgual(String campo, Object valor, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valor != null) {
			where.append(SqlUtils.AND).append(campo).append(" >= ?");
			params.add(valor);
		}
		return where.toString();
	}

	/**
	 * The method generarWhereMayorIgualFecha.
	 *
	 * @param campo   String
	 * @param formato String
	 * @param valor   Object
	 * @param params  List<Object>
	 * @return String
	 */
	public static String generarWhereMayorIgualFecha(String campo, String formato, Object valor, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valor != null) {
			where.append(" AND TO_DATE(");
			where.append(campo).append(", '");
			where.append(formato).append("')");
			where.append(" >= ?");
			params.add(valor);
		}
		return where.toString();
	}

	public static String generarWhereMayorIgualFecha(String campo, Object valor, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valor != null) {
			where.append(" AND TRUNC(");
			where.append(campo).append(")");
			where.append(" >= ?");
			params.add(valor);
		}
		return where.toString();
	}

	/**
	 * The method generarWhereIgualFecha.
	 *
	 * @param campo   String
	 * @param formato String
	 * @param valor   Object
	 * @param params  List<Object>
	 * @return String
	 */
	public static String generarWhereIgualFecha(String campo, String formato, String valor, List<Object> params) {
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat(formato);
		try {
			if (StringUtils.isNotBlank(valor)) {
				return SqlUtils.generarWhereIgual(campo, formatoDeFecha.parse(valor), params);
			}
		} catch (ParseException e) {
			return "";
		}
		return "";
	}

	/**
	 * The method generarWhereMenor.
	 *
	 * @param campo  String
	 * @param valor  Object
	 * @param params List<Object>
	 * @return String
	 */
	public static String generarWhereMenor(String campo, Object valor, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valor != null) {
			where.append(SqlUtils.AND).append(campo).append(" < ?");
			params.add(valor);
		}
		return where.toString();
	}

	//
	/**
	 * The method generarWhereMenorIgual.
	 *
	 * @param campo  String
	 * @param valor  Object
	 * @param params List<Object>
	 * @return String
	 */
	public static String generarWhereMenorIgual(String campo, Object valor, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valor != null) {
			where.append(SqlUtils.AND).append(campo).append(" <= ?");
			params.add(valor);
		}
		return where.toString();
	}

	//
	/**
	 * The method generarWhereMenorIgual.
	 *
	 * @param formato String
	 * @param campo   String
	 * @param valor   Object
	 * @param params  List<Object>
	 * @return String
	 */
	public static String generarWhereMenorIgualFecha(String campo, String formato, Object valor, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valor != null) {
			where.append(" AND TO_DATE(");
			where.append(campo).append(", '");
			where.append(formato).append("')");
			where.append(" <= ?");
			params.add(valor);
		}
		return where.toString();
	}

	public static String generarWhereMenorIgualFecha(String campo, Object valor, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valor != null) {
			where.append(" AND TRUNC(").append(campo).append(") <= ?");
			params.add(valor);
		}
		return where.toString();
	}

	/**
	 * The method generarWhereLike.
	 *
	 * @param campo      String
	 * @param valor      String
	 * @param params     List<Object>
	 * @param startsWith Boolean
	 * @return String
	 */
	public static String generarWhereLike(String campo, String valor, List<Object> params, Boolean startsWith) {
		final StringBuilder where = new StringBuilder();
		if (StringUtils.isNotBlank(valor)) {
			where.append(" AND UPPER(").append(campo).append(") like ? ESCAPE  '\\'");
			if (startsWith) {
				params.add(valor.toUpperCase() + "%");
			} else {
				params.add("%" + valor.toUpperCase() + "%");
			}
			where.append(SqlUtils.AND).append(campo).append(" IS NOT NULL");
		}
		return where.toString();
	}

	/**
	 * The method generarWhereLike.
	 *
	 * @param campo      String
	 * @param valor      String
	 * @param params     List<Object>
	 * @param startsWith Boolean
	 * @return String
	 */
	public static String generarWhereLikeWithoutAnd(String campo, String valor, List<Object> params,
			Boolean startsWith) {
		final StringBuilder where = new StringBuilder();
		if (StringUtils.isNotBlank(valor)) {
			where.append(" UPPER(").append(campo).append(") like ? ESCAPE  '\\'");
			if (startsWith) {
				params.add(valor.toUpperCase() + "%");
			} else {
				params.add("%" + valor.toUpperCase() + "%");
			}
			where.append(SqlUtils.AND).append(campo).append(" IS NOT NULL");
		}
		return where.toString();
	}

	/**
	 * The method generarWhereLikeNormalizado.
	 *
	 * @param campo      String
	 * @param valor      String
	 * @param params     List<Object>
	 * @param startsWith Boolean
	 * @return String
	 */

	public static String generarWhereLikeNormalizado(String campo, String valor, List<Object> params,
			Boolean startsWith) {
		final StringBuilder where = new StringBuilder();
		if (StringUtils.isNotBlank(valor)) {
			where.append(SqlUtils.AND).append(SqlUtils.normalizarCampoSql(campo)).append(" like ")
					.append(SqlUtils.normalizarCampoSql("?")).append(" ESCAPE '\\'");
			if (startsWith) {
				params.add(valor.replaceAll("\\\\", "\\\\\\\\").toUpperCase() + "%");
			} else {
				params.add("%" + valor.replaceAll("\\\\", "\\\\\\\\").toUpperCase() + "%");
			}
			where.append(SqlUtils.AND).append(campo).append(DaoConstants.IS_NOT_NULL);
		}
		return where.toString();
	}

	/**
	 * The method generarWhereSearch.
	 *
	 * @param campo      String
	 * @param valor      String
	 * @param params     List<Object>
	 * @param startsWith Boolean
	 * @param search     Boolean
	 * @return String
	 */

	public static String generarWhereSearch(String campo, String valor, List<Object> params, Boolean startsWith,
			Boolean search) {
		if (search) {
			return SqlUtils.generarWhereLikeNormalizado(campo, valor, params, startsWith);
		} else {
			return SqlUtils.generarWhereIgual(campo, valor, params);
		}
	}

	/**
	 * The method generarWhereIn.*
	 *
	 * @param campo   String*
	 * @param valores List<?>*
	 * @param params  List<Object>*@return String
	 */

	public static String generarWhereIn(String campo, List<?> valores, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valores != null && !valores.isEmpty() && !(valores.size() == 1 && "[]".equals(valores.get(0)))) {
			where.append(SqlUtils.AND).append(campo).append(" IN (");
			for (Integer i = 0; i < valores.size(); i++) {
				if (i > 0) {
					where.append(", ");
				}
				where.append("?");
			}
			where.append(")");
			params.addAll(valores);
		}
		return where.toString();
	}

	/**
	 * The method generarWhereIn.*
	 *
	 * @param campo   String*
	 * @param valores List<?>*
	 * @param params  List<Object>*@return String
	 */

	public static String generarWhereIn(String campo, Object[] valores, List<Object> params) {
		final StringBuilder where = generarWhereInNotIn(campo, valores, params, true);
		return where.toString();
	}

	/**
	 * The method generarWhereIn.*
	 *
	 * @param campo   String*
	 * @param valores List<?>*
	 * @param params  List<Object>*@return String
	 */

	public static String generarWhereInWithOutAnd(String campo, Object[] valores, List<Object> params) {
		final StringBuilder where = generarWhereInNotInWithOutAnd(campo, valores, params, true);
		return where.toString();
	}

	/**
	 * The method generarWhereNotIn.*
	 *
	 * @param campo   String*
	 * @param valores List<?>*
	 * @param params  List<Object>*@return String
	 */

	public static String generarWhereNotIn(String campo, Object[] valores, List<Object> params) {
		final StringBuilder where = generarWhereInNotIn(campo, valores, params, false);
		return where.toString();
	}

	private static StringBuilder generarWhereInNotIn(String campo, Object[] valores, List<Object> params,
			Boolean tipo) {
		final StringBuilder where = new StringBuilder();
		if (valores != null && !(valores.length == 1 && "[]".equals(valores[0]))) {
			if (tipo) {
				where.append(SqlUtils.AND).append(campo).append(" IN (");
			} else {
				where.append(SqlUtils.AND).append(campo).append(" NOT IN (");
			}
			for (Integer i = 0; i < valores.length; i++) {
				if (i > 0) {
					where.append(", ");
				}
				where.append("?");
			}
			where.append(")");
			for (Integer i = 0; i < valores.length; i++) {
				params.add(valores[i]);
			}
		}
		return where;
	}

	private static StringBuilder generarWhereInNotInWithOutAnd(String campo, Object[] valores, List<Object> params,
			Boolean tipo) {
		final StringBuilder where = new StringBuilder();
		if (valores != null && !(valores.length == 1 && "[]".equals(valores[0]))) {
			if (tipo) {
				where.append(campo).append(" IN (");
			} else {
				where.append(campo).append(" NOT IN (");
			}
			for (Integer i = 0; i < valores.length; i++) {
				if (i > 0) {
					where.append(", ");
				}
				where.append("?");
			}
			where.append(")");
			for (Integer i = 0; i < valores.length; i++) {
				params.add(valores[i]);
			}
		}
		return where;
	}

	/**
	 * The method generarWhereNotIn.
	 *
	 * @param campo   String
	 * @param valores List<?>
	 * @param params  List<Object>
	 * @return String
	 */
	public static String generarWhereNotIn(String campo, List<?> valores, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valores != null && !valores.isEmpty()) {
			where.append(SqlUtils.AND).append(campo).append(" NOT IN (");
			for (Integer i = 0; i < valores.size(); i++) {
				if (i > 0) {
					where.append(", ");
				}
				where.append("?");
			}
			where.append(")");
			params.addAll(valores);
		}
		return where.toString();
	}

	/**
	 * The method getInteger.
	 *
	 * @param rs         ResultSet
	 * @param strColName String
	 * @return Integer
	 * @throws SQLException e
	 */
	public static Integer getInteger(ResultSet rs, String strColName) throws SQLException {
		int nValue = rs.getInt(strColName);
		return rs.wasNull() ? null : nValue;
	}

	/**
	 * The method getDouble.
	 *
	 * @param rs         ResultSet
	 * @param strColName String
	 * @return Double
	 * @throws SQLException e
	 */
	public static Double getDouble(ResultSet rs, String strColName) throws SQLException {
		double nValue = rs.getDouble(strColName);
		return rs.wasNull() ? null : nValue;
	}

	/**
	 * The method getLong.
	 *
	 * @param rs         ResultSet
	 * @param strColName String
	 * @return Long
	 * @throws SQLException e
	 */
	public static Long getLong(ResultSet rs, String strColName) throws SQLException {
		Long nValue = rs.getLong(strColName);
		return rs.wasNull() ? null : nValue;
	}

	/**
	 * @param rs         ResultSet
	 * @param strColName String
	 * @return String
	 * @throws SQLException e
	 */
	public static String getClob(ResultSet rs, String strColName) throws SQLException {
		Clob clobEs = rs.getClob(strColName);
		if (clobEs != null) {
			return clobEs.getSubString(1, (int) clobEs.length());
		} else {
			return "";
		}
	}

	public static void comprobarPermisos(String indicador, String campo, List<String> permisos) {
		if (Constants.SI.equals(indicador)) {
			permisos.add(campo);
		}
	}

	public static String generarWherePermisos(List<String> permisos, List<Object> params) {
		final StringBuilder where = new StringBuilder();

		if (permisos.size() == 1) {
			where.append(SqlUtils.AND).append(permisos.get(0)).append(" = ?");
			params.add(Constants.SI);
		} else if (permisos.size() > 1) {
			// Montamos la clausula con IN en lugar de OR para mejorar el
			// rendimiento
			where.append(SqlUtils.AND).append("'").append(Constants.SI).append("' IN (");
			for (Integer i = 0; i < permisos.size(); i++) {
				String permiso = permisos.get(i);
				if (i > 0) {
					where.append(",");
				}
				where.append(permiso);
			}
			where.append(")");
		}

		return where.toString();
	}

	public static String generarDecodeSN(String campo, String nombre, ReloadableResourceBundleMessageSource msg,
			Locale locale) {
		StringBuilder decode = new StringBuilder();
		decode.append(", DECODE(").append(campo);
		decode.append(", '").append(Constants.SI).append("', '").append(msg.getMessage("comun.si", null, locale))
				.append("'");
		decode.append(", '").append(Constants.NO).append("', '").append(msg.getMessage("comun.no", null, locale))
				.append("'");
		decode.append(") AS ").append(nombre);
		return decode.toString();
	}

	public static String generarDecodeEstado(String campo, String nombre, ReloadableResourceBundleMessageSource msg,
			Locale locale) {
		StringBuilder decode = new StringBuilder();
		decode.append(", DECODE(").append(campo);
		decode.append(", '").append(Constants.ALTA).append("', '").append(msg.getMessage("estado.alta", null, locale))
				.append("'");
		decode.append(", '").append(Constants.BAJA).append("', '").append(msg.getMessage("estado.baja", null, locale))
				.append("'");
		decode.append(") AS ").append(nombre);
		return decode.toString();
	}

	/**
	 * 
	 * @param campo  String
	 * @param nombre String
	 * @param msg    ReloadableResourceBundleMessageSource
	 * @param locale Locale
	 * @return String
	 */
	public static String generarDecodeIdioma(String campo, String nombre, ReloadableResourceBundleMessageSource msg,
			Locale locale) {
		StringBuilder decode = new StringBuilder();
		decode.append(", DECODE(").append(campo);
		decode.append(", ").append(IdiomaEnum.CASTELLANO.getValue()).append(", '")
				.append(msg.getMessage(IdiomaEnum.CASTELLANO.getLabel(), null, locale)).append("'");
		decode.append(", ").append(IdiomaEnum.EUSKERA.getValue()).append(", '")
				.append(msg.getMessage(IdiomaEnum.EUSKERA.getLabel(), null, locale)).append("'");
		decode.append(") AS ").append(nombre);
		return decode.toString();
	}

	/**
	 * Devuelve para una cadena de caracteres dada el resultado de realizar sobre
	 * ella las siguientes modificaciones. <br>
	 * - Eliminacion de todos los espacios en blanco.<br>
	 * - Conversion a minusculas. <br>
	 * - Conversion de sus caracteres.
	 * 
	 * @param cadena Cadena a normalizar.
	 * @return Cadena normalizada.
	 */
	public static String normalizarCadenaLike(String cadena) {

		final StringBuilder likeBuffer = new StringBuilder();
		likeBuffer.append(SqlUtils.ORACLE_WILDCARD).append(SqlUtils.normalizarCadena(cadena))
				.append(SqlUtils.ORACLE_WILDCARD);

		return likeBuffer.toString();
	}

	/**
	 * Devuelve para una cadena de caracteres dada el resultado de realizar sobre
	 * ella las siguientes modificaciones. <br>
	 * - Eliminacion de todos los espacios en blanco.<br>
	 * - Conversion a minusculas. <br>
	 * - Conversion de sus caracteres.
	 * 
	 * @param cadena Cadena a normalizar.
	 * @return Cadena normalizada.
	 */
	public static String normalizarCadena(String cadena) {
		if (StringUtils.isNotBlank(cadena)) {
			return StringUtils.replaceChars(StringUtils.replaceChars(cadena, " ", "").toLowerCase(),
					SqlConstantes.CARACTERES_A_TRADUCIR, SqlConstantes.CARACTERES_TRADUCIDOS);
		}
		return cadena;
	}

	/**
	 * The method generarUpdate.
	 *
	 * @param campo  String
	 * @param valor  Object
	 * @param params List<Object>
	 * @return String
	 */
	public static String generarUpdate(String campo, Object valor, List<Object> params, boolean isFirst) {
		final StringBuilder update = new StringBuilder();
		if (valor != null) {
			if (!isFirst) {
				update.append(Constants.COMA);
			}
			if (valor instanceof String) {
				String str = (String) valor;
				if (StringUtils.isNotBlank(str)) {
					update.append(campo).append(" = ?");
					params.add(str);
				}
			} else {
				update.append(campo).append(" = ?");
				params.add(valor);
			}
		}
		return update.toString();
	}

	/**
	 * The method generarOnCampos.
	 *
	 * @param campo1  String
	 * @param String  Object
	 * @param isFirst boolean
	 * @return String
	 */
	public static String generarOnCampos(String campo1, String campo2, boolean isFirst) {
		final StringBuilder where = new StringBuilder();
		if (!isFirst) {
			where.append(SqlUtils.AND);
		}
		where.append(campo1).append(DaoConstants.SIGNOIGUAL).append(campo2);
		return where.toString();
	}

	public static String generarRupTableSelectedIds(List<String> camposIds, List<String> valoresIds,
			Boolean isSelectAll, String multiplePkToken) {

		StringBuilder where = new StringBuilder();
		boolean esPrimero = true;
		if (valoresIds != null && valoresIds.size() > Constants.CERO) {
			for (String sId : valoresIds) {
				String[] lId = sId.split(multiplePkToken);
				if (esPrimero) {
					esPrimero = false;
					if (isSelectAll) {
						where.append(" AND NOT (");
					} else {
						where.append(" AND  (");
					}
				} else {
					where.append(" OR ");
				}
				where.append(" ( ");
				boolean esPrimerId = true;
				for (int i = 0; i < camposIds.size(); i++) {
					if (esPrimerId) {
						esPrimerId = false;
					} else {
						where.append(" AND ");
					}
					where.append(camposIds.get(i));
					where.append(" = ");
					where.append(lId[i]);
				}
				where.append(" ) ");
			}
			where.append(" )");
		}
		return where.toString();
	}

	public static String generarWhereMayorIgualFechaHora(String campo, String formato, Object valor,
			List<Object> params) {

		return generarQueryFechaWhere(" >= ", campo, formato, valor, params);
	}

	public static String generarWhereMenorIgualFechaHora(String campo, String formato, Object valor,
			List<Object> params) {

		return generarQueryFechaWhere(" <= ", campo, formato, valor, params);
	}

	public static String generarQueryFechaWhere(String signo, String campo, String formato, Object valor,
			List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valor != null) {
			where.append(" AND TO_CHAR(");
			where.append(campo).append(", '");
			where.append(formato).append("')");
			where.append(signo);
			where.append(" TO_CHAR(?,'");
			where.append(formato).append("')");
			params.add(valor);
		}
		return where.toString();
	}

	public static String generarQueryFechaWhereString(String signo, String campo, String formato, Object valor,
			List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valor != null) {
			where.append(" AND TO_CHAR(");
			where.append(campo).append(", '");
			where.append(formato).append("')");
			where.append(signo);
			where.append(" ? ");
			params.add(valor);
		}
		return where.toString();
	}

}
