package com.ejie.aa79b.utils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.model.DatosPersona;
import com.ejie.x38.dto.JQGridRequestDto;

/**
 * @author aresua
 * @param <E>
 * 
 */
@Repository()
@Transactional()
public class DAOUtils<E> {

	private static final DAOUtils INSTANCE = new DAOUtils();

	private static final Logger LOGGER = LoggerFactory.getLogger(DAOUtils.class);

	/**
	 * @return INSTANCE an instance of DAOUtils
	 */
	public static DAOUtils getInstance() {
		return DAOUtils.INSTANCE;
	}

	/**
	 * Private constructor
	 */
	public DAOUtils() {
		// Constructor
	}

	/**
	 * @param mapaColumnasNombreYValor
	 *            map containing names and values for every column to update
	 * @param update
	 *            StringBuilder for the query
	 * @param params
	 *            List of parameters
	 * @param hayParam
	 *            indicating if there is a condition already added
	 */
	public void updateColumns(Map<String, Object> mapaColumnasNombreYValor, StringBuilder update, List<Object> params,
			boolean hayParam) {
		boolean paramExists = hayParam;
		for (Map.Entry<String, Object> entry : mapaColumnasNombreYValor.entrySet()) {
			if (entry.getValue() != null) {
				this.querySeparator(update, paramExists, false);
				paramExists = true;
				update.append(" " + entry.getKey() + " = ?");
				params.add(entry.getValue());
			}
		}
	}

	/**
	 * @param mapaColumnasNombreYValor
	 *            map containing names and values for every column to update
	 * @param select
	 *            StringBuilder for the query
	 * @param params
	 *            List of parameters
	 * @param hayParam
	 *            indicating if there is a condition already added
	 */
	public void whereColumns(Map<String, Object> mapaColumnasNombreYValor, StringBuilder select, List<Object> params,
			boolean hayParam) {
		this.generateColumnQuery(mapaColumnasNombreYValor, select, params, hayParam, false);
	}

	/**
	 * @param mapaColumnasNombreYValor
	 *            map containing names and values for every column to update
	 * @param select
	 *            StringBuilder for the query
	 * @param params
	 *            List of parameters
	 * @param hayParam
	 *            indicating if there is a condition already added
	 */
	public void whereColumnsLike(Map<String, Object> mapaColumnasNombreYValor, StringBuilder select,
			List<Object> params, boolean hayParam) {
		this.generateColumnQuery(mapaColumnasNombreYValor, select, params, hayParam, true);
	}

	/**
	 * @param mapaColumnasNombreYValor
	 *            map containing names and values for every column to update
	 * @param query
	 *            StringBuilder for the query
	 * @param params
	 *            List of parameters
	 * @param hayParam
	 *            indicating if there is a condition already added
	 * @param isLike
	 *            indicating if there is a like condition
	 */
	private void generateColumnQuery(Map<String, Object> mapaColumnasNombreYValor, StringBuilder query,
			List<Object> params, boolean hayParam, boolean isLike) {

		boolean paramExists = hayParam;
		for (Map.Entry<String, Object> entry : mapaColumnasNombreYValor.entrySet()) {
			if (entry.getValue() != null) {
				this.querySeparator(query, paramExists, true);
				paramExists = true;
				if (entry.getKey().contains("?")) {
					String[] splittedParams = ((String) entry.getValue()).split(",");
					query.append(" " + entry.getKey());
					for (int i = 0; i < StringUtils.countMatches(entry.getKey(), "?"); i++) {
						params.add(splittedParams[i]);
					}
				} else {
					if (isLike) {
						if (query.toString().endsWith("AND ") || query.toString().endsWith("AND")) {
							query.append(" UPPER(" + entry.getKey() + ") like ? ESCAPE  '\\'");
						} else {
							query.append(" AND UPPER(" + entry.getKey() + ") like ? ESCAPE  '\\'");
						}
						params.add("%" + entry.getValue().toString().toUpperCase() + "%");
						query.append(" AND " + entry.getKey() + " IS NOT NULL");
					} else if (entry.getValue() instanceof Date) {
						query.append(" TO_DATE(TO_CHAR(" + entry.getKey()
								+ ", 'DD/MM/YYYY'), 'DD/MM/YYYY') = TO_DATE(TO_CHAR(?, 'DD/MM/YYYY'), 'DD/MM/YYYY') ");
						params.add(entry.getValue());
					} else {
						query.append(" " + entry.getKey() + " = ?");
						params.add(entry.getValue());
					}
				}
			}
		}
	}

	/**
	 * Adds a separator to a query
	 * 
	 * @param query
	 *            StringBuilder for the query
	 * @param hayParam
	 *            indicating if there is a condition already added
	 * @param andBool
	 *            indicating if AND is needed or comma instead
	 */
	private void querySeparator(StringBuilder query, boolean hayParam, boolean andBool) {
		if (hayParam) {
			if (andBool) {
				query.append(" AND ");
			} else {
				query.append(", ");
			}
		}
	}

	/**
	 * obtenerNextValSecuencia.
	 * 
	 * @param secuencia
	 *            formato X53JI14Q00.NextVal
	 * @return query
	 */
	public final String obtenerNextValSecuencia(String secuencia) {

		final StringBuilder query = new StringBuilder();
		query.append("SELECT ");
		query.append(secuencia);
		query.append(" FROM DUAL ");

		return query.toString();
	}

	/**
	 * 
	 * @param campo
	 *            String
	 * @param campoResultado
	 *            String
	 * @param msg
	 *            ReloadableResourceBundleMessageSource
	 * @param locale
	 *            Locale
	 * @return String
	 */
	public static String getDecodeAcciones(String campo, String campoResultado,
			ReloadableResourceBundleMessageSource msg, String enumerator, Locale locale) {

		final StringBuilder query = new StringBuilder();

		// Reflexión
		final String classPackage = "com.ejie.aa79b.model.enums." + enumerator;
		try {
			final Class<?> c = Class.forName(classPackage);
			final Method method = c.getDeclaredMethod("values");

			final Object[] lista = (Object[]) method.invoke(c.getClass());

			query.append(", DECODE(").append(campo);

			final Method getValueMethod = c.getDeclaredMethod("getValue");
			final Method getLabelMethod = c.getDeclaredMethod("getLabel");

			for (Object enumeradoObj : lista) {
				query.append(", '");

				final Object value = getValueMethod.invoke(enumeradoObj);
				final String label = (String) getLabelMethod.invoke(enumeradoObj);

				query.append(value).append("', '").append(msg.getMessage(label, null, locale)).append("'");
			}
			query.append(") ");
			if (campoResultado != null) {
				query.append(" AS ").append(campoResultado);
			}

		} catch (Exception e) {
			DAOUtils.LOGGER.info("Error: ", e);
		}

		return query.toString();
	}

	public static StringBuilder getReorderQuery(JQGridRequestDto jqGridRequestDto, List<Object> params,
			StringBuilder subQuery, String pk, Integer caso, Object filtros) {
		StringBuilder query = new StringBuilder();
		query.append(" select " + pk + ", page, pageLine, tableLine from" + " ( select " + pk + " , ceil(rownum/"
				+ jqGridRequestDto.getRows() + ") page, case when (mod(rownum," + jqGridRequestDto.getRows() + ")=0) "
				+ "then '" + jqGridRequestDto.getRows() + "' else TO_CHAR(mod(rownum," + jqGridRequestDto.getRows()
				+ ")) end as pageLine, rownum as tableLine from (");
		query.append(subQuery.toString());
		query.append(")" + DaoConstants.WHERE_1_1);
		List<String> listSelectedIds = jqGridRequestDto.getMultiselection().getSelectedIds();
		for (int i = 0; i < listSelectedIds.size(); i++) {
			if (i >= 1) {
				query.append(DaoConstants.OR + " ");
			}
			if (i == 0) {
				query.append(DaoConstants.AND + " ");
			}
			query.append(pk + "= ? ");
			if (Constants.CERO == caso) {
				// IDTAREA cargaTrabajo
				params.add(new BigDecimal(listSelectedIds.get(i)));
			} else if (Constants.UNO == caso) {
				// DNI receptorautorizado y datospersona simple
				params.add(listSelectedIds.get(i));
			} else if (Constants.DOS == caso) {
				// datospersona con rol
				DatosPersona filterDatosPersona = (DatosPersona) filtros;
				String dni = listSelectedIds.get(i);
				if (filterDatosPersona != null && filterDatosPersona.getRol() != null) {
					if (Constants.ROL_SOLICITANTE.equals(filterDatosPersona.getRol())) {
						params.add(dni);
					} else if (Constants.ROL_RECEPTOR_AUTORIZADO.equals(filterDatosPersona.getRol())) {
						params.add(Integer.parseInt(dni));
					} else if (Constants.ROL_PERSONAL_IZO.equals(filterDatosPersona.getRol())) {
						params.add(dni);
					} else if (Constants.ROL_PROVEEDOR_EXTERNO.equals(filterDatosPersona.getRol())) {
						params.add(dni);
					}
				}
			}

		}
		query.append(")");
		return query;
	}
}
