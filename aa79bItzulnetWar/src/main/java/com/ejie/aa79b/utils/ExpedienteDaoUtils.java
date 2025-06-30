package com.ejie.aa79b.utils;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.common.PropertiesConstants;
import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedientePlanificacion;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.Gestor;
import com.ejie.aa79b.model.ListaExpediente;
import com.ejie.aa79b.model.enums.AportaSubsanacionEnum;
import com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum;
import com.ejie.aa79b.model.enums.TipoRequerimientoEnum;
import com.ejie.x38.dto.JQGridRequestDto;

public class ExpedienteDaoUtils {

	protected static final String I1IND_PRESUPUESTO_052 = "i1.IND_PRESUPUESTO_052";
	protected static final String R1IND_PUBLICAR_BOPV_053 = "r1.IND_PUBLICAR_BOPV_053";
	protected static final String G1DNI_SOLICITANTE_054 = "g1.DNI_SOLICITANTE_054";
	protected static final String G1TIPO_ENTIDAD_054 = "g1.TIPO_ENTIDAD_054";
	protected static final String G1ID_ENTIDAD_054 = "g1.ID_ENTIDAD_054";
	protected static final String B1ID_FASE_EXPEDIENTE_059 = "b1.ID_FASE_EXPEDIENTE_059";
	protected static final String SELECTALLFROMROW = "SELECT * FROM (SELECT rownum rnum, a.*  FROM (";
	protected static final String DDMMYY = "DD/mm/YY";
	protected static final String R1FECHA_FINAL_IZO_053 = "r1.FECHA_FINAL_IZO_053";
	protected static final String S1TIPO_REQUERIMIENTO_064 = " s1.TIPO_REQUERIMIENTO_064 ";
	protected static final String ANYO_051_AND_NUMEXP_051EQUALS = "ANYO_051 = ? and NUM_EXP_051 = ? ";
	protected static final String OBTENERGRUPOTRABAJO = " OBTENER_GRUPO_TRABAJO(t1.ANYO_051,t1.NUM_EXP_051) ";

	private ExpedienteDaoUtils() {
		// Constructor
	}

	/**
	 * 
	 * @param bean   Expediente
	 * @param params List<Object>
	 * @param where  StringBuilder
	 */
	public static void getWhereAux(Expediente bean, List<Object> params, StringBuilder where) {
		where.append(SqlUtils.generarWhereIgual(DBConstants.SUBSTR_T1ANYO_051,
				bean.getAnyo() != null ? bean.getAnyo().toString() : bean.getAnyo(), params));
		where.append(SqlUtils.generarWhereIgual(DBConstants.T1NUM_EXP_051, bean.getNumExp(), params));
		where.append(SqlUtils.generarWhereLike(DBConstants.T1ID_TIPO_EXPEDIENTE_051, bean.getIdTipoExpediente(), params,
				false));
		where.append(SqlUtils.generarWhereLike("t1.ORIGEN_051", bean.getOrigen(), params, false));
		where.append(SqlUtils.generarWhereLike(DBConstants.T1TITULO_051, bean.getTitulo(), params, false));
		where.append(SqlUtils.generarWhereIgual("t1.ESTADO_BITACORA_051", bean.getEstadoBitacora(), params));
		if (bean.getTecnico() != null) {
			where.append(SqlUtils.generarWhereLike("t1.DNI_TECNICO_051", bean.getTecnico().getDni(), params, false));
		}
		if (bean.getBitacoraExpediente() != null) {
			where.append(insertarDatosBitacora(bean, params, false));
		}
		if (bean.getExpedienteInterpretacion() != null) {
			where.append(SqlUtils.generarWhereLike(I1IND_PRESUPUESTO_052,
					bean.getExpedienteInterpretacion().getIndPresupuesto(), params, false));
		}
		if (bean.getExpedienteTradRev() != null) {
			where.append(SqlUtils.generarWhereLike(R1IND_PUBLICAR_BOPV_053,
					bean.getExpedienteTradRev().getIndPublicarBopv(), params, false));
		}
		if (bean.getGestorExpediente() != null) {
			if (solicitanteValido(bean.getGestorExpediente())) {
				where.append(SqlUtils.generarWhereLike(G1DNI_SOLICITANTE_054,
						bean.getGestorExpediente().getSolicitante().getDni(), params, false));
			}
			if (entidadValida(bean.getGestorExpediente())) {
				where.append(SqlUtils.generarWhereLike(G1TIPO_ENTIDAD_054,
						bean.getGestorExpediente().getEntidad().getTipo(), params, false));
				where.append(SqlUtils.generarWhereIgual(G1ID_ENTIDAD_054,
						bean.getGestorExpediente().getEntidad().getCodigo(), params));
			}
		}

	}

	/**
	 * 
	 * @param gestorExpediente Gestor
	 * @return boolean
	 */
	public static boolean solicitanteValido(Gestor gestorExpediente) {
		if (gestorExpediente.getSolicitante() != null) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param bean       Expediente
	 * @param params     List<Object>
	 * @param startsWith Boolean
	 * @return String
	 */
	public static String insertarDatosBitacora(Expediente bean, List<Object> params, Boolean startsWith) {
		StringBuilder whereBitacora = new StringBuilder();
		if (estadoValido(bean.getBitacoraExpediente())) {
			whereBitacora.append(SqlUtils.generarWhereIgual("b1.ID_ESTADO_EXP_059",
					bean.getBitacoraExpediente().getEstadoExp().getId(), params));
		}
		if (faseValida(bean.getBitacoraExpediente())) {
			whereBitacora.append(SqlUtils.generarWhereIgual(B1ID_FASE_EXPEDIENTE_059,
					bean.getBitacoraExpediente().getFaseExp().getId(), params));
		}
		if (subsanacionValida(bean.getBitacoraExpediente())) {
			if (AportaSubsanacionEnum.NO_REQUERIDA.getValue()
					.equals(bean.getBitacoraExpediente().getSubsanacionExp().getIndSubsanado())) {
				whereBitacora.append(
						" AND (UPPER(s1.IND_SUBSANADO_064) LIKE 'NR' ESCAPE '\\' OR s1.IND_SUBSANADO_064 IS NULL) ");
			} else {
				whereBitacora.append(SqlUtils.generarWhereLike("s1.IND_SUBSANADO_064",
						bean.getBitacoraExpediente().getSubsanacionExp().getIndSubsanado(), params, startsWith));
			}
		}
		return whereBitacora.toString();
	}

	/**
	 * 
	 * @param gestorExpediente Gestor
	 * @return boolean
	 */
	public static boolean entidadValida(Gestor gestorExpediente) {
		if (gestorExpediente.getEntidad() != null && !"null".equals(gestorExpediente.getEntidad().getTipo())) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param bitacoraExpediente BitacoraExpediente
	 * @return boolean
	 */
	public static boolean estadoValido(BitacoraExpediente bitacoraExpediente) {
		if (bitacoraExpediente != null && bitacoraExpediente.getEstadoExp() != null) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param bitacoraExpediente BitacoraExpediente
	 * @return boolean
	 */
	public static boolean faseValida(BitacoraExpediente bitacoraExpediente) {
		if (bitacoraExpediente != null && bitacoraExpediente.getFaseExp() != null
				&& bitacoraExpediente.getFaseExp().getId() != null && bitacoraExpediente.getFaseExp().getId() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param bitacoraExpediente BitacoraExpediente
	 * @return boolean
	 */
	public static boolean subsanacionValida(BitacoraExpediente bitacoraExpediente) {
		if (bitacoraExpediente.getSubsanacionExp() != null) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param bean   Expediente
	 * @param params List<Object>
	 * @param where  StringBuilder
	 */
	public static void getWhereJustificanteSolicitudAux(Expediente bean, List<Object> params, StringBuilder where) {
		where.append(SqlUtils.generarWhereIgual("SUBSTR(t1.ANYO_069,3,4)",
				bean.getAnyo() != null ? bean.getAnyo().toString() : bean.getAnyo(), params));
		where.append(SqlUtils.generarWhereIgual("t1.NUM_EXP_069", bean.getNumExp(), params));
		where.append(SqlUtils.generarWhereLike("t1.ID_TIPO_EXPEDIENTE_069", bean.getIdTipoExpediente(), params, false));
		where.append(SqlUtils.generarWhereLike("t1.TITULO_069", bean.getTitulo(), params, false));
		if (bean.getExpedienteInterpretacion() != null) {
			where.append(SqlUtils.generarWhereLike("i1.IND_PRESUPUESTO_070",
					bean.getExpedienteInterpretacion().getIndPresupuesto(), params, false));
		}
		if (bean.getExpedienteTradRev() != null) {
			where.append(SqlUtils.generarWhereLike("r1.IND_PUBLICAR_BOPV_071",
					bean.getExpedienteTradRev().getIndPublicarBopv(), params, false));
		}
		if (bean.getGestorExpediente() != null) {
			if (ExpedienteDaoUtils.solicitanteValido(bean.getGestorExpediente())) {
				where.append(SqlUtils.generarWhereLike(G1DNI_SOLICITANTE_054,
						bean.getGestorExpediente().getSolicitante().getDni(), params, false));
			}
			if (ExpedienteDaoUtils.entidadValida(bean.getGestorExpediente())) {
				where.append(SqlUtils.generarWhereLike(G1TIPO_ENTIDAD_054,
						bean.getGestorExpediente().getEntidad().getTipo(), params, false));
				where.append(SqlUtils.generarWhereIgual(G1ID_ENTIDAD_054,
						bean.getGestorExpediente().getEntidad().getCodigo(), params));
			}
		}
	}

	/**
	 * 
	 * @param bean       Expediente
	 * @param startsWith Boolean
	 * @param params     List<Object>
	 * @param where      StringBuilder
	 */
	public static void getWhereLikeAux(Expediente bean, Boolean startsWith, List<Object> params, StringBuilder where) {
		where.append(DaoConstants.AND + " t1.ESTADO_BAJA_051 " + DaoConstants.SIGNOIGUAL + "'"
				+ EstadoEnum.ALTA.getValue() + "' ");
		where.append(SqlUtils.generarWhereIgual(DBConstants.SUBSTR_T1ANYO_051,
				bean.getAnyo() != null ? bean.getAnyo().toString() : bean.getAnyo(), params));
		where.append(SqlUtils.generarWhereIgual(DBConstants.T1NUM_EXP_051, bean.getNumExp(), params));
		where.append(SqlUtils.generarWhereLike(DBConstants.T1ID_TIPO_EXPEDIENTE_051, bean.getIdTipoExpediente(), params,
				startsWith));
		where.append(SqlUtils.generarWhereLike("t1.ORIGEN_051", bean.getOrigen(), params, startsWith));
		where.append(SqlUtils.generarWhereLike(DBConstants.T1TITULO_051, bean.getTitulo(), params, startsWith));
		where.append(SqlUtils.generarWhereIgual("t1.ESTADO_BITACORA_051", bean.getEstadoBitacora(), params));
		if (bean.getTecnico() != null) {
			where.append(
					SqlUtils.generarWhereLike("t1.DNI_TECNICO_051", bean.getTecnico().getDni(), params, startsWith));
		}
		if (bean.getBitacoraExpediente() != null) {
			where.append(ExpedienteDaoUtils.insertarDatosBitacora(bean, params, startsWith));
		}
		if (bean.getExpedienteInterpretacion() != null) {
			where.append(SqlUtils.generarWhereLike(I1IND_PRESUPUESTO_052,
					bean.getExpedienteInterpretacion().getIndPresupuesto(), params, startsWith));
		}
		if (bean.getExpedienteTradRev() != null) {
			where.append(SqlUtils.generarWhereLike(R1IND_PUBLICAR_BOPV_053,
					bean.getExpedienteTradRev().getIndPublicarBopv(), params, startsWith));
		}
		if (bean.getGestorExpediente() != null) {
			if (ExpedienteDaoUtils.solicitanteValido(bean.getGestorExpediente())) {
				where.append(SqlUtils.generarWhereLike(G1DNI_SOLICITANTE_054,
						bean.getGestorExpediente().getSolicitante().getDni(), params, startsWith));
			}
			if (ExpedienteDaoUtils.entidadValida(bean.getGestorExpediente())) {
				where.append(SqlUtils.generarWhereLike(G1TIPO_ENTIDAD_054,
						bean.getGestorExpediente().getEntidad().getTipo(), params, startsWith));
				where.append(SqlUtils.generarWhereIgual(G1ID_ENTIDAD_054,
						bean.getGestorExpediente().getEntidad().getCodigo(), params));
			}
		}
	}

	/**
	 * 
	 * @param jqGridRequestDtoAux JQGridRequestDto
	 * @param paginatedQueryAux   StringBuilder
	 * @param queryAux            StringBuilder
	 */
	public static void filtroExpedienteEstadoEnCursoAux(JQGridRequestDto jqGridRequestDtoAux,
			StringBuilder paginatedQueryAux, StringBuilder queryAux) {
		if (jqGridRequestDtoAux != null) {
			if (jqGridRequestDtoAux.getSidx() != null) {
				if (jqGridRequestDtoAux.getSidx().indexOf(',') > 0) {
					queryAux.append(" ORDER BY ID_FASE_EXPEDIENTE_059 ASC, FECHA_FIN_052 ASC, "
							+ "IND_VIP_054 DESC, IND_PUBLICAR_BOPV_053 DESC, IND_SUBSANADO_064 DESC, "
							+ "FECHA_LIMITE_064 ASC");
				} else {
					queryAux.append(" ORDER BY " + jqGridRequestDtoAux.getSidx() + " ");
					queryAux.append(jqGridRequestDtoAux.getSord());
				}

			}

			Long rowsAux = jqGridRequestDtoAux.getRows();
			Long pageAux = jqGridRequestDtoAux.getPage();
			if ((pageAux != null) && (rowsAux != null)) {
				paginatedQueryAux.append(SELECTALLFROMROW + queryAux + ")a) where rnum > "
						+ rowsAux.longValue() * (pageAux.longValue() - 1L) + " and rnum < "
						+ (rowsAux.longValue() * pageAux.longValue() + 1L));
			} else if (rowsAux != null) {
				paginatedQueryAux.append(
						SELECTALLFROMROW + queryAux + ")a) where rnum > 0 and rnum < " + (rowsAux.longValue() + 1L));
			} else {
				paginatedQueryAux.append(queryAux);
			}
		}
	}

	/**
	 * 
	 * @param filterExpediente Expediente
	 * @param startsWith       boolean
	 * @param params           List<Object>
	 * @param noRelacionados   boolean
	 * @param where            StringBuilder
	 */
	public static void getWhereLikeRelacionadosAux(Expediente filterExpediente, boolean startsWith, List<Object> params,
			boolean noRelacionados, StringBuilder where) {
		where.append(DaoConstants.WHERE_1_1);

		// modificacion Jose para exped NO relacionados
		if (!noRelacionados) {
			where.append(DaoConstants.AND);
			where.append(DaoConstants.ABRIR_PARENTESIS);
			where.append(" t1.ID_TIPO_EXPEDIENTE_051 = '" + TipoExpedienteEnum.REVISION.getValue()
					+ "' OR t1.ID_TIPO_EXPEDIENTE_051 = '" + TipoExpedienteEnum.TRADUCCION.getValue()
					+ "' OR t1.ID_TIPO_EXPEDIENTE_051 = '" + TipoExpedienteEnum.INTERPRETACION.getValue() + "'");
			where.append(DaoConstants.CERRAR_PARENTESIS);
		}

		if (filterExpediente.getAnyo() != null && filterExpediente.getNumExp() != null) {
			where.append(" AND (");
			if (noRelacionados) {
				where.append("NOT ");
			}
			where.append("EXISTS(");
			where.append("SELECT 1 FROM AA79B57S01 j1 ");
			where.append("WHERE (t1.ANYO_051 = j1.ANYO_EXP_REL_057 AND t1.NUM_EXP_051 = j1.NUM_EXP_REL_057 ");
			where.append("AND(j1.ANYO_057=? AND j1.NUM_EXP_057=?))))");
			where.append(" AND NOT (t1.ANYO_051 = ? AND t1.NUM_EXP_051 = ?)");
			params.add(filterExpediente.getAnyo());
			params.add(filterExpediente.getNumExp());
			params.add(filterExpediente.getAnyo());
			params.add(filterExpediente.getNumExp());
		}
		where.append(
				SqlUtils.generarWhereLike(DBConstants.T1TITULO_051, filterExpediente.getTitulo(), params, startsWith));
		if (filterExpediente.getExpedienteRelacionado() != null) {

			where.append(SqlUtils.generarWhereIgual(DBConstants.SUBSTR_T1ANYO_051,
					filterExpediente.getExpedienteRelacionado().getAnyoExpRel() != null
							? filterExpediente.getExpedienteRelacionado().getAnyoExpRel().toString()
							: filterExpediente.getExpedienteRelacionado().getAnyoExpRel(),
					params));
			where.append(SqlUtils.generarWhereIgual(DBConstants.T1NUM_EXP_051,
					filterExpediente.getExpedienteRelacionado().getNumExpRel(), params));
			where.append(SqlUtils.generarWhereMayorIgualFecha("t1.FECHA_ALTA_051", DDMMYY,
					filterExpediente.getExpedienteRelacionado().getFechaDesdeSolExp(), params));
			where.append(SqlUtils.generarWhereMenorIgualFecha("t1.FECHA_ALTA_051", DDMMYY,
					filterExpediente.getExpedienteRelacionado().getFechaHastaSolExp(), params));
			where.append(SqlUtils.generarWhereMayorIgualFecha(R1FECHA_FINAL_IZO_053, DDMMYY,
					filterExpediente.getExpedienteRelacionado().getFechaDesdeEntrega(), params));
			where.append(SqlUtils.generarWhereMenorIgualFecha(R1FECHA_FINAL_IZO_053, DDMMYY,
					filterExpediente.getExpedienteRelacionado().getFechaHastaEntrega(), params));

			getWhereLikeRelacionadosAuxBOPV(filterExpediente, startsWith, params, noRelacionados, where);
		}
		if (filterExpediente.getGestorExpediente() != null) {
			if (ExpedienteDaoUtils.solicitanteValido(filterExpediente.getGestorExpediente())) {
				where.append(SqlUtils.generarWhereLike(G1DNI_SOLICITANTE_054,
						filterExpediente.getGestorExpediente().getSolicitante().getDni(), params, startsWith));
			}
			if (ExpedienteDaoUtils.entidadValida(filterExpediente.getGestorExpediente())) {
				where.append(SqlUtils.generarWhereLike(G1TIPO_ENTIDAD_054,
						filterExpediente.getGestorExpediente().getEntidad().getTipo(), params, false));
				where.append(SqlUtils.generarWhereIgual(G1ID_ENTIDAD_054,
						filterExpediente.getGestorExpediente().getEntidad().getCodigo(), params));
			}
		}
	}

	/**
	 * @param filterExpediente
	 * @param startsWith
	 * @param params
	 * @param noRelacionados
	 * @param where
	 */
	private static void getWhereLikeRelacionadosAuxBOPV(Expediente filterExpediente, boolean startsWith,
			List<Object> params, boolean noRelacionados, StringBuilder where) {
		if (filterExpediente.getExpedienteRelacionado().getPermisoBOPV() != null) {
			if (noRelacionados) {
				where.append(
						" AND (UPPER(" + R1IND_PUBLICAR_BOPV_053 + ") LIKE ? OR R1.IND_PUBLICAR_BOPV_053 IS NULL) ");
				params.add("%" + filterExpediente.getExpedienteRelacionado().getPermisoBOPV().toUpperCase() + "%");
			} else {
				where.append(SqlUtils.generarWhereLike(R1IND_PUBLICAR_BOPV_053,
						filterExpediente.getExpedienteRelacionado().getPermisoBOPV(), params, startsWith));
			}

		}
	}

	/**
	 * 
	 * @param indPresupuesto    String
	 * @param filter            ExpedientePlanificacion
	 * @param where             StringBuilder
	 * @param params            List<Object>
	 * @param filtroPresupuesto boolean
	 */
	public static void generarWherePresupuestoONegociacionSi(String indPresupuesto, ExpedientePlanificacion filter,
			StringBuilder where, List<Object> params, boolean filtroPresupuesto) {
		if (Constants.SI.equals(indPresupuesto)
				&& (filter.getEstadoPresupuesto() != null || filter.getEstadoNegociacion() != null)) {
			where.append(DaoConstants.AND);
			where.append(" d1.ID_REQUERIMIENTO_081 " + DaoConstants.SIGNOIGUAL + " s1.ID_064 ");
			where.append(DaoConstants.AND + DaoConstants.ABRIR_PARENTESIS);
			if (filtroPresupuesto) {
				// filtro presupuesto
				where.append(S1TIPO_REQUERIMIENTO_064 + DaoConstants.SIGNOIGUAL
						+ TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO.getValue());
			} else {
				// filtro negociacion
				where.append(S1TIPO_REQUERIMIENTO_064 + DaoConstants.SIGNOIGUAL
						+ TipoRequerimientoEnum.ACEPTACION_FECHA_FIN.getValue());
			}
			where.append(DaoConstants.OR);
			where.append(S1TIPO_REQUERIMIENTO_064 + DaoConstants.SIGNOIGUAL
					+ TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO_FECHA_FIN.getValue());
			where.append(DaoConstants.CERRAR_PARENTESIS);
			if (filtroPresupuesto) {
				where.append(SqlUtils.generarWhereIgual(" s1.ESTADO_SUBSANACION_064 ", filter.getEstadoPresupuesto(),
						params));
			} else {
				where.append(SqlUtils.generarWhereIgual(" s1.ESTADO_SUBSANACION_064 ", filter.getEstadoNegociacion(),
						params));
			}

		}
	}

	/**
	 * 
	 * @param indPresupuesto    String
	 * @param filter            ExpedientePlanificacion
	 * @param where             StringBuilder
	 * @param params            List<Object>
	 * @param filtroPresupuesto boolean
	 */
	public static void generarWhereNegociacionSi(String indNegociacion, ExpedientePlanificacion filter,
			StringBuilder where, List<Object> params, boolean filtroPresupuesto) {
		if (Constants.SI.equals(indNegociacion) && filter.getEstadoNegociacion() != null) {
			where.append(DaoConstants.AND);
			where.append("(( d1.ID_REQUERIMIENTO_081 " + DaoConstants.SIGNOIGUAL + " s1.ID_064 ");
			where.append(DaoConstants.AND + DaoConstants.ABRIR_PARENTESIS);
			// filtro negociacion
			where.append(S1TIPO_REQUERIMIENTO_064 + DaoConstants.SIGNOIGUAL
					+ TipoRequerimientoEnum.ACEPTACION_FECHA_FIN.getValue());
			where.append(DaoConstants.OR);
			where.append(S1TIPO_REQUERIMIENTO_064 + DaoConstants.SIGNOIGUAL
					+ TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO_FECHA_FIN.getValue());
			where.append(DaoConstants.CERRAR_PARENTESIS);

			where.append(
					SqlUtils.generarWhereIgual(" s1.ESTADO_SUBSANACION_064 ", filter.getEstadoNegociacion(), params));

			where.append(") OR (");
			where.append(SqlUtils.generarWhereIgualSinAnd(" OBTENER_ESTADO_NEGOCIACION(ANYO_051, NUM_EXP_051) ",
					filter.getEstadoNegociacion(), params));
			where.append("))");

		}
	}

	/**
	 * 
	 * @param filter           ExpedientePlanificacion
	 * @param where            StringBuilder
	 * @param esInterpretacion
	 */
	public static void getWhereGruposTrabajo(ExpedientePlanificacion filter, StringBuilder where, List<Object> params,
			boolean excluirBOE) {
		if (filter != null && StringUtils.isNotBlank(filter.getIdsGrupoTrabajo())) {
			where.append(DaoConstants.AND + OBTENERGRUPOTRABAJO + DaoConstants.IN + DaoConstants.ABRIR_PARENTESIS
					+ filter.getIdsGrupoTrabajo() + DaoConstants.CERRAR_PARENTESIS);
		} else if (excluirBOE) {
			where.append(DaoConstants.AND + OBTENERGRUPOTRABAJO + DaoConstants.NOT_IN + DaoConstants.ABRIR_PARENTESIS
					+ DaoConstants.SELECT);
			where.append(" id_grupo_027 FROM AA79B27T00 WHERE ");
			where.append(" id_entidad_027 =  (select valor_000 from AA79B00T00 where id_000 = ?) ");
			where.append(" AND TIPO_ENTIDAD_027 ='B' ");

			where.append(DaoConstants.CERRAR_PARENTESIS);

			params.add(PropertiesConstants.COD_ENTIDAD_BOE);
		}

	}

	public static void getWhereFasesExpedientes(ExpedientePlanificacion filter, StringBuilder where) {
		if (filter != null && StringUtils.isNotBlank(filter.getIdsFaseExpediente())) {
			where.append(DaoConstants.AND + B1ID_FASE_EXPEDIENTE_059 + DaoConstants.IN + DaoConstants.ABRIR_PARENTESIS
					+ filter.getIdsFaseExpediente() + DaoConstants.CERRAR_PARENTESIS);
		}
	}

	public static void obtenerSelectTareasExpedientesAux(boolean isCount, StringBuilder query,
			PlanificacionExpedienteUtils planificacionExpedienteUtils) {
		if (isCount) {
			query.append(DaoConstants.SELECT_COUNT);
		} else {
			query.append(planificacionExpedienteUtils
					.getSelectResumen(PlanificacionExpedienteUtils.TAREAS_FILTRO_SIN_CONDICIONES));
		}

	}

	public static boolean listaExpedientesValido(ListaExpediente listaExpedientes) {
		if (listaExpedientes != null && listaExpedientes.getListaExpediente() != null
				&& !listaExpedientes.getListaExpediente().isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * @param filter
	 * @return boolean
	 */
	public static boolean isSolicitanteNulo(Expediente filter) {
		return filter.getGestorExpediente().getSolicitante() != null
				&& filter.getGestorExpediente().getSolicitante().getDni() != null;
	}

	/**
	 * @param filter
	 * @return boolean
	 */
	public static boolean isEntidadNula(Expediente filter) {

		return filter.getGestorExpediente().getEntidad() != null
				&& (esTipoEntidadNoNula(filter) || (filter.getGestorExpediente().getEntidad().getCodigo() != null
						&& filter.getGestorExpediente().getEntidad().getCodigo() != 0));

	}

	/**
	 * @param filter
	 * @return
	 */
	private static boolean esTipoEntidadNoNula(Expediente filter) {
		return filter.getGestorExpediente().getEntidad().getTipo() != null
				&& !filter.getGestorExpediente().getEntidad().getTipo().equals(Constants.MINUS_UNO.toString());
	}

	public static void getJoinsGruposTrabajo(ExpedientePlanificacion filter, StringBuilder select,
			boolean esInterpretacion) {
		if (filter != null && StringUtils.isNotBlank(filter.getIdsGrupoTrabajo())) {
			// casos: 0 Gainerako solo ; 1 Gainerako y mas grupos de trabajo ; 2
			// solo grupos de trabajo
			int casoGruposTrabajo = obtenerCasoGruposDeTrabajoSeleccionados(filter.getIdsGrupoTrabajo());
			generarJoinsGruposDeTrabajo(casoGruposTrabajo, filter, select, esInterpretacion);

		}

	}

	private static void generarJoinsGruposDeTrabajo(int casoGruposTrabajo, ExpedientePlanificacion filter,
			StringBuilder select, boolean esInterpretacion) {
		if (Constants.UNO == casoGruposTrabajo) {
			// seleccionado Gainerako y uno o mas grupos de trabajo
			select.append(DaoConstants.JOIN).append(" AA79B26S01 ").append(" t26 ");
			select.append(" ON t26.ESTADO_026 = " + DaoConstants.SIGNO_APOSTROFO + EstadoEnum.ALTA.getValue()
					+ DaoConstants.SIGNO_APOSTROFO);
			select.append(DaoConstants.LEFT_JOIN).append(" AA79B27S01 ").append(" t27 ");
			select.append(" ON t27.TIPO_ENTIDAD_027 = g1.TIPO_ENTIDAD_054 ");
			select.append(" AND t27.ID_ENTIDAD_027  = g1.ID_ENTIDAD_054 ");
			select.append(" AND t26.ID_026 = T27.ID_GRUPO_027 ");
		} else if (Constants.DOS == casoGruposTrabajo) {
			// Gainerako NO seleccionado y uno o mas grupos de trabajo
			// seleccionados
			select.append(DaoConstants.JOIN).append(" AA79B26S01 ").append(" t26 ");
			select.append(" ON t26.ID_TIPO_EXPEDIENTE_026 = DECODE (").append(" t1")
					.append(".ID_TIPO_EXPEDIENTE_051, " + DaoConstants.SIGNO_APOSTROFO
							+ TipoExpedienteEnum.INTERPRETACION.getValue() + DaoConstants.SIGNO_APOSTROFO + ",0,1) ");
			select.append(" AND (((t26.ID_TIPO_EXPEDIENTE_026 = "
					+ TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getValue() + ")");
			select.append(
					" OR (t26.ID_TIPO_EXPEDIENTE_026 = " + TipoExpedienteGrupoEnum.INTERPRETACION.getValue() + ")) ");
			select.append(" OR t26.DNI_RESPONSABLE_026 IS NUll)");
			select.append(" AND t26.ESTADO_026 = " + DaoConstants.SIGNO_APOSTROFO + EstadoEnum.ALTA.getValue()
					+ DaoConstants.SIGNO_APOSTROFO);
			if (esInterpretacion) {
				select.append(DaoConstants.LEFT_JOIN);
			} else {
				select.append(DaoConstants.JOIN);
			}
			select.append(" AA79B27S01 ").append(" t27 ");
			select.append(" ON t27.TIPO_ENTIDAD_027 = g1.TIPO_ENTIDAD_054 ");
			select.append(" AND t27.ID_ENTIDAD_027  = g1.ID_ENTIDAD_054 ");
			select.append(" AND t26.ID_026 = T27.ID_GRUPO_027 ");
		}
	}

	public static int obtenerCasoGruposDeTrabajoSeleccionados(String idsGrupoTrabajo) {
		final String[] gruposTrabajo = idsGrupoTrabajo.split(Constants.COMA);
		boolean gainerakoSeleccionada = false;
		boolean gainerakoYMasSeleccionados = false;
		for (String id : gruposTrabajo) {
			if (Constants.CERO.toString().equals(id)) {
				gainerakoSeleccionada = true;
				if (gruposTrabajo.length > Constants.UNO) {
					// seleccionado Gainerako y algun grupo mas
					gainerakoYMasSeleccionados = true;
				}
			}
		}
		int valorADevolver = Constants.DOS;
		if (gainerakoYMasSeleccionados) {
			valorADevolver = Constants.UNO;
		} else if (gainerakoSeleccionada) {
			valorADevolver = Constants.CERO;
		}
		return valorADevolver;
	}

	public static void whereIndicesBopv(StringBuilder where, ExpedientePlanificacion filter, List<Object> params) {
		if (filter.getIndicesBopv() != null) {
			boolean bOr = false;
			if (filter.getExpedienteTradRev() == null) {
				ExpedienteTradRev tradRev = new ExpedienteTradRev();
				filter.setExpedienteTradRev(tradRev);
			}
			if (Constants.SI.equals(filter.getIndicesBopv())) {
				filter.getExpedienteTradRev().setIndPublicarBopv(Constants.SI);
				filter.getExpedienteTradRev().setIndPrevistoBopv(Constants.SI);
				bOr = true;
			} else if (Constants.NO.equals(filter.getIndicesBopv())) {
				filter.getExpedienteTradRev().setIndPublicarBopv(Constants.NO);
				filter.getExpedienteTradRev().setIndPrevistoBopv(Constants.NO);
			}

			where.append(DaoConstants.AND);
			where.append(DaoConstants.ABRIR_PARENTESIS);
			where.append(SqlUtils.generarWhereLikeWithoutAnd(R1IND_PUBLICAR_BOPV_053,
					filter.getExpedienteTradRev().getIndPublicarBopv(), params, false));
			if (bOr) {
				where.append(DaoConstants.OR);
			} else {
				where.append(DaoConstants.AND);
			}
			where.append(SqlUtils.generarWhereLikeWithoutAnd("r1.IND_PREVISTO_BOPV_053",
					filter.getExpedienteTradRev().getIndPrevistoBopv(), params, false));
			where.append(DaoConstants.CERRAR_PARENTESIS);
		}

	}

	public static void getWhereBusquedaGeneralFilterTareaAux(ExpedientePlanificacion filter, List<Object> params,
			StringBuilder where) {
		if (filter.getFilterTarea() != null) {
			where.append(DaoConstants.AND);
			where.append(Constants.CERO + DaoConstants.SIGNO_MENOR_QUE + DaoConstants.ABRIR_PARENTESIS);
			where.append(DaoConstants.SELECT_COUNT);
			where.append(DaoConstants.FROM);
			where.append(DBConstants.TABLA_81 + " z1 ");
			where.append(DaoConstants.WHERE);
			where.append(" z1.ANYO_081 " + DaoConstants.SIGNOIGUAL + " t1.ANYO_051 ");
			where.append(DaoConstants.AND);
			where.append(" z1.NUM_EXP_081 " + DaoConstants.SIGNOIGUAL + " t1.NUM_EXP_051 ");
			where.append(SqlUtils.generarWhereIgual(" z1.ID_TIPO_TAREA_081 ", filter.getFilterTarea().getTipoTarea(),
					params));
			// ESTADO ACEPTACION TAREAS PLANIFICADAS
			where.append(SqlUtils.generarWhereIgual(" z1.ESTADO_ASIGNACION_081 ",
					filter.getFilterTarea().getEstadoAceptTarea(), params));
			// ESTADO EJECUCION TAREAS PLANIFICADAS
			if (filter.getFilterTarea().getEstadoEjecTarea() != null
					&& EstadoEjecucionTareaEnum.RETRASADA.getValue() == filter.getFilterTarea().getEstadoEjecTarea()) {
				where.append(SqlUtils.generarWhereIgual(" z1.ESTADO_EJECUCION_081 ",
						EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue(), params));
				where.append(
						DaoConstants.AND + " z1.FECHA_FIN_081 " + DaoConstants.SIGNO_MENOR_QUE + DaoConstants.SYSDATE);
			} else {
				where.append(SqlUtils.generarWhereIgual(" z1.ESTADO_EJECUCION_081 ",
						filter.getFilterTarea().getEstadoEjecTarea(), params));
			}

			// FECHA PLANIFICACION TAREAS
			where.append(SqlUtils.generarWhereMayorIgualFecha("z1.FECHA_ASIGNACION_081", DDMMYY,
					filter.getFilterTarea().getFechaDesdePlanifTarea(), params));
			where.append(SqlUtils.generarWhereMenorIgualFecha("z1.FECHA_ASIGNACION_081", DDMMYY,
					filter.getFilterTarea().getFechaHastaPlanifTarea(), params));
			// PERSONAL IZO ASIGNADO A TAREA
			where.append(SqlUtils.generarWhereLike(" z1.DNI_RECURSO_081",
					filter.getFilterTarea().getPersIzoAsignTarea(), params, false));
			// LOTE ASIGNADO A TAREA
			where.append(SqlUtils.generarWhereIgual(" z1.ID_LOTE_081", filter.getFilterTarea().getIdLoteAsignTarea(),
					params));
			where.append(DaoConstants.CERRAR_PARENTESIS);
		}
	}

	public static void getWherePlanificacionExpedientesFilterMetadatosAux(ExpedientePlanificacion filter,
			StringBuilder where) {
		if (StringUtils.isNotBlank(filter.getIdsMetadatosBusqueda())) {
			final String[] idsMetadatosBusqueda = filter.getIdsMetadatosBusqueda().split(Constants.COMA);
			where.append(DaoConstants.AND + DaoConstants.T14_MINUSCULA + DaoConstants.SIGNO_PUNTO
					+ DBConstants.ID_METADATO_062 + DaoConstants.IN + DaoConstants.ABRIR_PARENTESIS);
			for (int i = Constants.CERO; i < idsMetadatosBusqueda.length; i++) {
				where.append(idsMetadatosBusqueda[i]);
				if (i != idsMetadatosBusqueda.length - 1) {
					where.append(DaoConstants.SIGNO_COMA);
				}
			}
			where.append(DaoConstants.CERRAR_PARENTESIS);
		}
	}

	public static void getSelectedIdsAux(List<Object> params, StringBuilder query, List<String> listSelectedIds) {
		for (int i = 0; i < listSelectedIds.size(); i++) {
			if (i >= 1) {
				query.append(DaoConstants.OR + " ");
			}
			if (i == 0) {
				query.append(DaoConstants.AND + " ");
			}
			String idCombinada = listSelectedIds.get(i);
			String[] parts = idCombinada.split(",");
			query.append(ANYO_051_AND_NUMEXP_051EQUALS);
			params.add(Long.parseLong(parts[0], 10));
			params.add(Integer.parseInt(parts[1]));
		}
	}

	public static void paginatedQueryAux(JQGridRequestDto jqGridRequestDto, StringBuilder paginatedQuery,
			StringBuilder query) {
		if (jqGridRequestDto != null) {
			if (jqGridRequestDto.getSidx() != null) {
				if (jqGridRequestDto.getSidx().indexOf(',') > 0) {
					query.append(" ORDER BY ID_FASE_EXPEDIENTE_059 ASC, FECHA_FIN_052 ASC, "
							+ "IND_VIP_054 DESC, IND_PUBLICAR_BOPV_053 DESC, IND_SUBSANADO_064 DESC, "
							+ "FECHA_LIMITE_064 ASC, NUM_EXP_051 ASC");
				} else {
					query.append(" ORDER BY " + jqGridRequestDto.getSidx() + " ");
					query.append(jqGridRequestDto.getSord());
				}

			}

			Long rows = jqGridRequestDto.getRows();
			Long page = jqGridRequestDto.getPage();
			if ((page != null) && (rows != null)) {
				paginatedQuery.append(
						SELECTALLFROMROW + query + ")a) where rnum > " + rows.longValue() * (page.longValue() - 1L)
								+ " and rnum < " + (rows.longValue() * page.longValue() + 1L));
			} else if (rows != null) {
				paginatedQuery
						.append(SELECTALLFROMROW + query + ")a) where rnum > 0 and rnum < " + (rows.longValue() + 1L));
			} else {
				paginatedQuery.append(query);
			}
		}
	}

	public static void getWhereGruposTrabajoBOE(ExpedientePlanificacion filter, StringBuilder where,
			List<Object> params) {
		where.append(DaoConstants.AND + OBTENERGRUPOTRABAJO + DaoConstants.IN + DaoConstants.ABRIR_PARENTESIS
				+ DaoConstants.SELECT);
		where.append(" id_grupo_027 FROM AA79B27T00 WHERE ");
		where.append(" id_entidad_027 =  (select valor_000 from AA79B00T00 where id_000 = ?) ");
		where.append(" AND TIPO_ENTIDAD_027 ='B' ");

		where.append(DaoConstants.CERRAR_PARENTESIS);

		params.add(PropertiesConstants.COD_ENTIDAD_BOE);

	}

}
