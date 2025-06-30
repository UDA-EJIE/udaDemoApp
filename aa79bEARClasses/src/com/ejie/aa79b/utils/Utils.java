package com.ejie.aa79b.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.common.Reports;
import com.ejie.aa79b.common.exceptions.AppRuntimeException;
import com.ejie.aa79b.dao.Aa79bExpedienteWsDaoImpl;
import com.ejie.aa79b.dao.ExpedienteDaoImpl;
import com.ejie.aa79b.model.Calle;
import com.ejie.aa79b.model.DireccionNora;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.Localidad;
import com.ejie.aa79b.model.Municipio;
import com.ejie.aa79b.model.Portal;
import com.ejie.aa79b.model.Provincia;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TareasTrabajo;
import com.ejie.aa79b.model.enums.Aa06aEstadoFaseExpedienteEnum;
import com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.FaseExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoEntidadEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum;
import com.ejie.aa79b.model.webservices.Aa79bDescripcionIdioma;
import com.ejie.aa79b.model.webservices.Aa79bTablaWebService;
import com.ejie.aa79b.security.Usuario;
import com.ejie.x38.dto.JQGridRequestDto;

/**
 * The type v85juUtils.
 *
 * @author vdorantes
 *
 */
public final class Utils {

	private static final String AA79B_TRADUCTOR = "AA79B_TRADUCTOR";
	private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

	/**
	 *
	 */
	private Utils() {

	}

	/**
	 * The method quitarLetrasDni.
	 *
	 * @param cadena String
	 * @return String
	 */
	public static String quitarLetrasDni(String cadena) {
		String dni = "";
		if (StringUtils.isNotBlank(cadena) && cadena.length() > 1) {
			final Pattern patron = Pattern.compile("\\D");
			final Matcher matcher = patron.matcher(cadena);
			dni = StringUtils.deleteWhitespace(matcher.replaceAll(""));

			try {
				final int dniInt = Integer.parseInt(dni);
				dni = Integer.toString(dniInt);
			} catch (Exception e) {
				throw new AppRuntimeException(e);
			}

			if (StringUtils.isBlank(dni)) {
				dni = "-1";
			}
		}

		if ("".equals(dni)) {
			dni = cadena;
		}

		return dni;
	}

	/**
	 * The method blobToByteArray.
	 *
	 * @param documento Blob
	 * @return byte[]
	 */
	public static byte[] blobToByteArray(Blob documento) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buf = new byte[(int) documento.length()];
			InputStream in = documento.getBinaryStream();
			int n;
			while ((n = in.read(buf)) >= 0) {
				baos.write(buf, 0, n);
			}
			in.close();
			return baos.toByteArray();
		} catch (Exception e) {
			throw new AppRuntimeException(e);
		}
	}

	/**
	 * Este metodo sirve para devolver el año actual.
	 *
	 * @author azabalasalterain
	 * @return String
	 */
	public static String getCursoLectivoActual() {
		final StringBuilder curso = new StringBuilder();
		final Calendar fecha = Calendar.getInstance();
		int anyo1 = fecha.get(Calendar.YEAR);
		int anyo2 = anyo1;
		final int mes = fecha.get(Calendar.MONTH);
		if (mes < Calendar.OCTOBER) {
			anyo1--;
		} else {
			anyo2++;
		}
		curso.append(anyo1).append(anyo2);
		return curso.toString();
	}

	/**
	 * @param lista     List<Object>
	 * @param separador String
	 * @return String
	 */
	public static String concatenarLista(List<String> lista, String separador) {
		StringBuilder str = new StringBuilder();
		for (Integer i = 0; i < lista.size(); i++) {
			String elemento = lista.get(i);
			if (i > 0) {
				str.append(separador);
			}
			str.append(elemento);
		}
		return str.toString();
	}

	/**
	 * The method blobToByteArray.
	 *
	 * @param documento Blob
	 * @return byte[]
	 */
	public static Clob stringToClob(String cadena) {

		Clob clobObject = null;

		if (cadena != null) {
			char[] chars = cadena.toCharArray();
			try {
				clobObject = new SerialClob(chars);
			} catch (SerialException e) {
				throw new AppRuntimeException(e);
			} catch (SQLException e) {
				throw new AppRuntimeException(e);
			}
		}

		return clobObject;
	}

	public static String observacionesRegistro(Object beanActual, Object beanOriginal, Map<String, String> propiedades,
			ReloadableResourceBundleMessageSource msg, Locale locale) {
		StringBuilder str = new StringBuilder();
		for (String propiedad : propiedades.keySet()) {
			Object aux = Reports.retrieveObjectValue(beanActual, propiedad);
			String actual = aux == null ? "" : aux.toString();
			if (beanOriginal == null) {
				str.append(msg.getMessage(propiedades.get(propiedad), null, locale)).append(": ");
				str.append(actual);
				str.append("\n");
			} else {
				aux = Reports.retrieveObjectValue(beanOriginal, propiedad);
				String original = aux == null ? "" : aux.toString();
				if (!StringUtils.equals(original, actual)) {
					str.append(msg.getMessage(propiedades.get(propiedad), null, locale)).append(": ");
					str.append(original).append(" -> ").append(actual);
					str.append("\n");
				}
			}
		}
		return str.toString();
	}

	public static String literalSiNo(ReloadableResourceBundleMessageSource msg, String valor, Locale locale) {
		if (Constants.SI.equals(valor)) {
			return msg.getMessage("comun.si", null, locale);
		} else {
			return msg.getMessage("comun.no", null, locale);
		}
	}

	public static String literalTipoExpediente(ReloadableResourceBundleMessageSource msg, Integer valor,
			Locale locale) {
		for (TipoExpedienteGrupoEnum tipoExpediente : TipoExpedienteGrupoEnum.values()) {
			if (tipoExpediente.getValue() == valor) {
				return msg.getMessage(tipoExpediente.getLabel(), null, locale);
			}
		}
		return "";
	}

	public static String literalEstado(ReloadableResourceBundleMessageSource msg, String valor, Locale locale) {
		if (Constants.ALTA.equals(valor)) {
			return msg.getMessage("estado.alta", null, locale);
		} else {
			return msg.getMessage("estado.baja", null, locale);
		}
	}

	/**
	 *
	 * Lee a String un InputStream
	 *
	 * @param is InputStream
	 * @return String
	 *
	 * @author xbarba
	 */
	public static String convertStreamToString(InputStream is) {
		final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		final StringBuilder sb = new StringBuilder(50);
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}
		} catch (IOException e) {
			Utils.LOGGER.error("Error: " + e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				Utils.LOGGER.error("Error: " + e);
			}
		}
		return sb.toString();
	}

	public static <T extends Object> String concatenarListaBean(List<T> lista, String propiedad, String separador) {
		StringBuilder str = new StringBuilder();
		if (lista != null) {
			for (Integer i = 0; i < lista.size(); i++) {
				T bean = lista.get(i);
				Object valor = Reports.retrieveObjectValue(bean, propiedad);
				if (i > 0) {
					str.append(separador);
				}
				str.append(valor);
			}
		}
		return str.toString();
	}

	public static <T extends Object> List<List<T>> separarLista(List<T> lista, String propiedad) {
		Map<String, List<T>> datos = new LinkedHashMap<String, List<T>>();

		for (T bean : lista) {
			Object valor = Reports.retrieveObjectValue(bean, propiedad);
			String valorStr = valor != null ? String.valueOf(valor) : "";
			List<T> aux = datos.get(valorStr);
			if (aux == null) {
				aux = new ArrayList<T>();
				datos.put(valorStr, aux);
			}
			aux.add(bean);
		}
		List<List<T>> listas = new ArrayList<List<T>>();
		for (String key : datos.keySet()) {
			listas.add(datos.get(key));
		}
		return listas;
	}

	/**
	 * Funcion que pasandole el tipo de entidad, nos devuelve el label de la
	 * descripcion del mismo
	 *
	 * @param tipo
	 * @return
	 */
	public static String getTipoEntidadDescLabel(String tipo) {
		for (TipoEntidadEnum tipoEntidad : TipoEntidadEnum.values()) {
			if (tipoEntidad.getValue().equals(tipo)) {
				return tipoEntidad.getLabel();
			}
		}
		return "";
	}

	/**
	 * @param ind
	 * @return String
	 */
	public static String getValueInd(String ind) {
		return ind == null ? Constants.NO : ind;
	}

	/**
	 * @param str
	 * @return String
	 */
	public static String getString(String str) {
		return str == null ? "" : str;
	}

	/**
	 * Obtiene un valor de tipo boolean partiendo de un String
	 *
	 * @param value String
	 * @return boolean
	 */
	public static boolean booleanValue(String value) {
		boolean result = false;

		if (Constants.SI.equals(value)) {
			result = true;
		}

		return result;
	}

	/**
	 * funcion que devuelve el tipo de usuario al que pertenece Tipos de usuario: 1
	 * : Gestor 2 : Coordinador 3 : Gestor y Coordinador 0 : no es ni coordinador ni
	 * gestor
	 *
	 * @param solicitante
	 * @return Integer
	 *
	 */
	public static Integer getTipoUsuario(Solicitante solicitante) {
		if (solicitante != null) {
			if (esGestor(solicitante)) {
				if (esCoordinador(solicitante)) {
					return 3;
				} else {
					return 1;
				}
			} else if (esCoordinador(solicitante)) {
				return 2;
			}
		}
		return 0;
	}

	/**
	 * Funcion para obtener si el Usuario es Coordinador
	 *
	 * @param solicitante
	 * @return boolean
	 */
	public static boolean esGestor(Solicitante solicitante) {
		if ((booleanValue(solicitante.getGestorExpedientes())) || (booleanValue(solicitante.getGestorExpedientesBOPV()))
				|| (booleanValue(solicitante.getGestorExpedientesVIP()))) {
			return true;
		}
		return false;
	}

	/**
	 * Funcion para obtener si el Usuario es gestor
	 *
	 * @param solicitante
	 * @return boolean
	 */
	public static boolean esCoordinador(Solicitante solicitante) {
		if (booleanValue(solicitante.getCoordinador())) {
			return true;
		}
		return false;
	}

	/**
	 * Funcion para obtener si el Usuario es gestor y Coordinador
	 *
	 * @param solicitante
	 * @return boolean
	 */
	public static boolean esGestorCoordinador(Solicitante solicitante) {
		if (esGestor(solicitante) && esCoordinador(solicitante)) {
			return true;
		}
		return false;
	}

	/**
	 * Funcion que devuelve la query de busqueda de estado/fase de Itzulnet en
	 * funcion del estado/fase de aa06a pasada como parametro
	 *
	 * @param aa06aFase
	 * @return
	 */
	public static String getAa79baEstadoFaseQuery(Aa79bDescripcionIdioma aa79bDescripcionIdioma) {
		StringBuilder select = new StringBuilder(Aa79bExpedienteWsDaoImpl.STRING_BUILDER_INIT);
		if (aa79bDescripcionIdioma != null) {

			Integer fase = safeLongToInt(aa79bDescripcionIdioma.getId());

			comprobarCasos1A4(fase, select);

		}
		return select.toString();
	}

	private static void comprobarCasos5A8(Integer fase, StringBuilder select) {
		if (Constants.CINCO == fase) {
			// AA79B --> ESTADO : 6 , FASE : 13 o FASE
			select.append(
					DaoConstants.AND + " ID_ESTADO_EXP_059 = " + new Long(EstadoExpedienteEnum.CERRADO.getValue()));
			select.append(DaoConstants.AND + " (ID_FASE_EXPEDIENTE_059 = " + FaseExpedienteEnum.CERRADO.getValue()
					+ " OR ID_FASE_EXPEDIENTE_059 = " + FaseExpedienteEnum.PDTE_REV_FACTURACION.getValue() + ")");
		} else if (Constants.SEIS == fase) {
			// AA79B --> ESTADO : 7 , FASE : 14
			select.append(
					DaoConstants.AND + " ID_ESTADO_EXP_059 = " + new Long(EstadoExpedienteEnum.FINALIZADO.getValue()));
			select.append(DaoConstants.AND + " ID_FASE_EXPEDIENTE_059 = " + FaseExpedienteEnum.FINALIZADO.getValue());
		} else if (Constants.SIETE == fase) {
			// AA79B --> ESTADO : 4 , FASE : 11
			select.append(
					DaoConstants.AND + " ID_ESTADO_EXP_059 = " + new Long(EstadoExpedienteEnum.RECHAZADO.getValue()));
			select.append(DaoConstants.AND + " ID_FASE_EXPEDIENTE_059 = " + FaseExpedienteEnum.RECHAZADO.getValue());
		} else if (Constants.OCHO == fase) {
			// AA79B --> ESTADO : 5 , FASE : 12
			select.append(
					DaoConstants.AND + " ID_ESTADO_EXP_059 = " + new Long(EstadoExpedienteEnum.ANULADO.getValue()));
			select.append(DaoConstants.AND + " ID_FASE_EXPEDIENTE_059 = " + FaseExpedienteEnum.ANULADO.getValue());
		}
	}

	private static void comprobarCasos1A4(Integer fase, StringBuilder select) {
		if (Constants.UNO == fase) {
			// AA79B --> ESTADO : 2 , FASE : 2
			select.append(
					DaoConstants.AND + " ID_ESTADO_EXP_059 = " + new Long(EstadoExpedienteEnum.EN_ESTUDIO.getValue()));
			select.append(
					DaoConstants.AND + " ID_FASE_EXPEDIENTE_059 = " + FaseExpedienteEnum.PENDIENTE_ESTUDIO.getValue());
		} else if (Constants.DOS == fase) {
			// AA79B --> ESTADO : 2 , FASE : 3
			select.append(
					DaoConstants.AND + " ID_ESTADO_EXP_059 = " + new Long(EstadoExpedienteEnum.EN_ESTUDIO.getValue()));
			select.append(
					DaoConstants.AND + " ID_FASE_EXPEDIENTE_059 = " + FaseExpedienteEnum.ESTUDIO_EXPEDIENTE.getValue());
		} else if (Constants.TRES == fase) {
			// AA79B --> ESTADO : 2 , FASE : 4 && ESTADO : 3 , FASE : 8
			select.append(getAa79baEstadoFaseTresQuery());
		} else if (Constants.CUATRO == fase) {
			// AA79B --> ESTADO : 3 , FASE : 5 , 6 , 7 , 9 , 10
			select.append(getAa79baEstadoFaseCuatroQuery());
		} else {
			comprobarCasos5A8(fase, select);
		}
	}

	/**
	 * Funcion auxiliar que devuelve la query relativa al estadoFase 3 de aa06a
	 *
	 * @return
	 */
	public static String getAa79baEstadoFaseTresQuery() {
		return DaoConstants.AND + DaoConstants.ABRIR_PARENTESIS + DaoConstants.ABRIR_PARENTESIS
				+ " ID_ESTADO_EXP_059 = " + new Long(EstadoExpedienteEnum.EN_ESTUDIO.getValue()) + DaoConstants.AND
				+ " ID_FASE_EXPEDIENTE_059 = " + FaseExpedienteEnum.PDTE_TRAM_SUBSANACION.getValue()
				+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.OR + DaoConstants.ABRIR_PARENTESIS
				+ " ID_ESTADO_EXP_059 = " + new Long(EstadoExpedienteEnum.EN_CURSO.getValue()) + DaoConstants.AND
				+ " ID_FASE_EXPEDIENTE_059 = " + FaseExpedienteEnum.PDTE_TRAM_CLIENTE.getValue()
				+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.CERRAR_PARENTESIS;
	}

	/**
	 * Funcion auxiliar que devuelve la query relativa al estadoFase 4 de aa06a
	 *
	 * @return
	 */
	public static String getAa79baEstadoFaseCuatroQuery() {
		return DaoConstants.AND + DaoConstants.ABRIR_PARENTESIS + " ID_ESTADO_EXP_059 = "
				+ new Long(EstadoExpedienteEnum.EN_CURSO.getValue()) + DaoConstants.AND + DaoConstants.ABRIR_PARENTESIS
				+ " ID_FASE_EXPEDIENTE_059 = " + FaseExpedienteEnum.PDTE_PROYECTO_TRADOS.getValue() + DaoConstants.OR
				+ " ID_FASE_EXPEDIENTE_059 = " + FaseExpedienteEnum.PDTE_EJECT_TAREAS.getValue()
				+ DaoConstants.CERRAR_PARENTESIS + DaoConstants.CERRAR_PARENTESIS;
	}

	/**
	 * @param longValue
	 * @return
	 */
	public static int safeLongToInt(Long longValue) {
		if (longValue != null) {
			long longVal = longValue;
			if (longVal < Integer.MIN_VALUE || longVal > Integer.MAX_VALUE) {
				throw new IllegalArgumentException(longVal + " cannot be cast to int without changing its value.");
			}
			return (int) longVal;
		}
		return 0;
	}

	/**
	 * Funcion que devuelve el mapeo de la descripcion del estadoFase para la query
	 *
	 * @param String                                estado
	 * @param String                                fase
	 * @param ReloadableResourceBundleMessageSource msg
	 * @param Locale                                locale
	 * @return String
	 */
	public static String obtenerAa06aEstadoFaseDesc(String estado, String fase,
			ReloadableResourceBundleMessageSource msg, Locale locale) {
		StringBuilder select = new StringBuilder();
		select.append(", DECODE(OBTENER_ESTADO_SOLICITANTE(" + estado + ", " + fase + "), '"
				+ Aa06aEstadoFaseExpedienteEnum.PENDIENTE_ESTUDIO.getValue() + "', '")
				.append(msg.getMessage(Aa06aEstadoFaseExpedienteEnum.PENDIENTE_ESTUDIO.getLabel(), null, locale))
				.append("', '" + Aa06aEstadoFaseExpedienteEnum.ESTUDIO_EXPEDIENTE.getValue() + "', '")
				.append(msg.getMessage(Aa06aEstadoFaseExpedienteEnum.ESTUDIO_EXPEDIENTE.getLabel(), null, locale))
				.append("', '" + Aa06aEstadoFaseExpedienteEnum.PDTE_TRAM_CLIENTE.getValue() + "', '")
				.append(msg.getMessage(Aa06aEstadoFaseExpedienteEnum.PDTE_TRAM_CLIENTE.getLabel(), null, locale))
				.append("', '" + Aa06aEstadoFaseExpedienteEnum.EN_CURSO.getValue() + "', '")
				.append(msg.getMessage(Aa06aEstadoFaseExpedienteEnum.EN_CURSO.getLabel(), null, locale))
				.append("', '" + Aa06aEstadoFaseExpedienteEnum.CERRADO.getValue() + "', '")
				.append(msg.getMessage(Aa06aEstadoFaseExpedienteEnum.CERRADO.getLabel(), null, locale))
				.append("', '" + Aa06aEstadoFaseExpedienteEnum.FINALIZADO.getValue() + "', '")
				.append(msg.getMessage(Aa06aEstadoFaseExpedienteEnum.FINALIZADO.getLabel(), null, locale))
				.append("', '" + Aa06aEstadoFaseExpedienteEnum.RECHAZADO.getValue() + "', '")
				.append(msg.getMessage(Aa06aEstadoFaseExpedienteEnum.RECHAZADO.getLabel(), null, locale))
				.append("', '" + Aa06aEstadoFaseExpedienteEnum.ANULADO.getValue() + "', '")
				.append(msg.getMessage(Aa06aEstadoFaseExpedienteEnum.ANULADO.getLabel(), null, locale))
				.append("') AS ESTADOFASEAA06ADESC" + locale.getLanguage().toUpperCase());

		return select.toString();

	}

	/**
	 * Funcion que devuelve true si los campos relativos a la creacion de un
	 * JQGridRequestDto no son nulos
	 *
	 * @param aa79bTablaWebService
	 * @return boolean
	 */
	public static boolean isValidAa79bTablaWebService(Aa79bTablaWebService<?> aa79bTablaWebService) {
		if (!(aa79bTablaWebService.getRows() == null) && !(aa79bTablaWebService.getPage() == null)
				&& !(aa79bTablaWebService.getSidx() == null) && !(aa79bTablaWebService.getSord() == null)) {
			return true;
		}
		return false;
	}

	/**
	 * @param direccion DireccionNora
	 * @return DireccionNora
	 */
	public static DireccionNora obtenerDireccion(DireccionNora direccion) {
		DireccionNora dir = new DireccionNora();
		if (direccion != null) {
			StringBuilder txtDirec = componerDireccion(direccion);

			direccion.setTxtDirec(txtDirec.toString());

			dir = direccion;
		}
		return dir;
	}

	/**
	 * @param direccion
	 * @return
	 */
	private static StringBuilder componerDireccion(DireccionNora direccion) {
		StringBuilder txtDirec = new StringBuilder();
		comprobarTextoYAsignarADireccion(txtDirec, getDsO(direccion.getCalle()), Constants.EMPTY);
		if (!String.valueOf(Constants.TRES).equals(direccion.getTipoNora())) {
			comprobarTextoYAsignarADireccion(txtDirec, getDsO(direccion.getPortal()), Constants.COMA);
			comprobarTextoYAsignarADireccion(txtDirec, Utils.getString(direccion.getEscalera()), Constants.COMA);
			comprobarTextoYAsignarADireccion(txtDirec, Utils.getString(direccion.getPiso()), Constants.ESPACIO);
			comprobarTextoYAsignarADireccion(txtDirec, Utils.getString(direccion.getMano()), Constants.ESPACIO);
			comprobarTextoYAsignarADireccion(txtDirec, Utils.getString(direccion.getPuerta()), Constants.ESPACIO);
			comprobarTextoYAsignarADireccion(txtDirec, Utils.getString(direccion.getAprox()), Constants.ESPACIO);
		}
		comprobarTextoYAsignarADireccion(txtDirec, getDsO(direccion.getLocalidad()), Constants.ESPACIO);
		comprobarTextoYAsignarADireccion(txtDirec, Utils.getString(direccion.getCodPostal()), Constants.COMA);
		comprobarTextoYAsignarADireccion(txtDirec, getDsO(direccion.getMunicipio()), Constants.ESPACIO);
		comprobarTextoYAsignarADireccion(txtDirec, getDsO(direccion.getProvincia()), Constants.COMA);

		if (String.valueOf(Constants.TRES).equals(direccion.getTipoNora())) {
			comprobarTextoYAsignarADireccion(txtDirec, direccion.getPais().getDsO(),
					Constants.PUNTO + Constants.ESPACIO);
		}
		return txtDirec;
	}

	public static void comprobarTextoYAsignarADireccion(StringBuilder txtDirec, String text, String textAnt) {
		if (textoValido(text)) {
			if (!StringUtils.isBlank(txtDirec.toString())) {
				txtDirec.append(textAnt).append(text);
			} else {
				txtDirec.append(text);
			}
		}
	}

	private static boolean textoValido(String dsO) {
		return dsO != null && !"".equals(dsO) && !"null".equals(dsO);
	}

	/**
	 * @param direccion DireccionNora
	 * @return String
	 */
	public static String obtenerDireccionStr(DireccionNora direccion) {
		if (direccion != null) {
			StringBuilder txtDirec = componerDireccion(direccion);

			return txtDirec.toString();

		}
		return "";
	}

	/**
	 * @param obj Object
	 * @return String
	 */
	public static String getDsO(Object obj) {
		String str = "";

		if (obj != null) {
			if (obj instanceof Calle) {
				str = Utils.getString(((Calle) obj).getDsO());
			} else if (obj instanceof Localidad) {
				str = Utils.getString(((Localidad) obj).getDsO());
			} else if (obj instanceof Provincia) {
				str = Utils.getString(((Provincia) obj).getDsO());
			} else if (obj instanceof Portal) {
				str = Utils.getString(((Portal) obj).getTxtPortal());
			} else if (obj instanceof Municipio) {
				str = Utils.getString(((Municipio) obj).getDsO());
			}
		}

		return str;
	}

	/**
	 * Obtiene la concatenación del nombre y los apellidos
	 *
	 * @param nombre
	 * @param apellido1
	 * @param apellido2
	 * @return String con el nombre y los apellidos
	 */
	public static String getNombreApellidos(String nombre, String apellido1, String apellido2) {
		final StringBuilder apeNom = new StringBuilder();
		if (apellido1 != null) {
			apeNom.append(apellido1);
			if (apellido2 != null) {
				apeNom.append(" ");
			} else if (nombre != null) {
				apeNom.append(", ");
			}
		}
		if (apellido2 != null) {
			apeNom.append(apellido2);
			if (nombre != null) {
				apeNom.append(", ");
			}
		}
		if (nombre != null) {
			apeNom.append(nombre);
		}
		return apeNom.toString();
	}

	/**
	 * Obtiene el dni con el prefijo y el sufijo
	 *
	 * @param preDni
	 * @param dni
	 * @param sufDni
	 * @return String con el dni completo
	 */
	public static String getDniCompleto(String preDni, String dni, String sufDni) {
		StringBuilder str = new StringBuilder();
		str.append(StringUtils.defaultString(preDni));
		str.append(StringUtils.defaultString(dni));
		str.append(StringUtils.defaultString(sufDni));
		return str.toString();
	}

	/**
	 *
	 * @param jQGridRequestDto JQGridRequestDto
	 * @param isCount          Boolean
	 * @param select           StringBuilder
	 * @return String
	 */
	public static String getPaginationQuery(JQGridRequestDto jQGridRequestDto, Boolean isCount, StringBuilder select) {
		StringBuilder paginatedQuery = new StringBuilder(ExpedienteDaoImpl.STRING_BUILDER_INIT);
		if (jQGridRequestDto != null && !isCount) {
			if (jQGridRequestDto.getSidx() != null) {
				select.append(" ORDER BY " + jQGridRequestDto.getSidx() + " ");
				select.append(jQGridRequestDto.getSord());

			}

			Long rows = jQGridRequestDto.getRows();
			Long page = jQGridRequestDto.getPage();
			if ((page != null) && (rows != null)) {
				paginatedQuery.append("SELECT * FROM (SELECT rownum rnum, a.*  FROM (" + select + ")a) where rnum > "
						+ rows.longValue() * (page.longValue() - 1L) + " and rnum < "
						+ (rows.longValue() * page.longValue() + 1L));
			} else if (rows != null) {
				paginatedQuery.append("SELECT * FROM (SELECT rownum rnum, a.*  FROM (" + select
						+ ")a) where rnum > 0 and rnum < " + (rows.longValue() + 1L));
			} else {
				paginatedQuery.append(select);
			}
		} else {
			paginatedQuery.append(select);
		}
		return paginatedQuery.toString();
	}

	/**
	 *
	 * @param accion String
	 * @param valor  String
	 * @return boolean
	 */
	public static boolean comprobarAccion(String accion, String valor) {
		if (accion != null && valor.equalsIgnoreCase(accion)) {
			return true;
		}
		return false;
	}

	/**
	 * Obtiene el año y el número de expediente concatenados
	 *
	 * @param anyo
	 * @param numExp
	 * @return String
	 */
	public static String getAnyoNumExpConcatenado(Long anyo, Integer numExp) {
		if (anyo != null && numExp != null) {
			if (anyo != 0 && numExp != 0) {
				return "" + String.valueOf(anyo).substring(2) + "/" + String.format("%06d", numExp);
			} else {
				return "";
			}
		}
		return null;
	}

	/**
	 * @param idEstadoAsignacion
	 * @return
	 */
	public static int getEstadoAsignacion(int idEstadoAsignacion) {
		return idEstadoAsignacion == 0 ? EstadoAceptacionTareaEnum.PENDIENTE_ASIGNACION.getValue() : idEstadoAsignacion;
	}

	/**
	 * @param idEstadoEjecucion
	 * @return
	 */
	public static int getEstadoEjecucion(int idEstadoEjecucion) {
		return idEstadoEjecucion == 0 ? EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue() : idEstadoEjecucion;
	}

	/**
	 * comprobacion de si, el num palabras extraido del xml es mayor que el num
	 * palabras definido por el IZO, si esa diferencia esta por encima o no del
	 * desfase asumible
	 *
	 * @param numTotalPal      Integer
	 * @param numPalIzo        Integer
	 * @param iDesfaseAsumible Integer
	 * @return boolean
	 */
	public static boolean comprobarDesfaseAsumible(Integer numTotalPal, Integer numPalIzo, Integer iDesfaseAsumible) {
		if (numTotalPal > numPalIzo) {
			Integer iDiferencia = numTotalPal - numPalIzo;
			Double porcentaje = (double) (((double) iDiferencia / (double) numTotalPal) * 100);
			return porcentaje > iDesfaseAsumible;
		}
		return false;
	}

	/**
	 *
	 * @param documentosExpediente DocumentosExpediente
	 * @return boolean
	 */
	public static boolean comprobarRutaPifFichero(DocumentosExpediente documentosExpediente) {
		boolean tieneRuta = false;
		if (documentosExpediente != null && documentosExpediente.getFicheros() != null
				&& !documentosExpediente.getFicheros().isEmpty()
				&& StringUtils.isNotBlank(documentosExpediente.getFicheros().get(Constants.CERO).getRutaPif())) {
			tieneRuta = true;
		}

		return tieneRuta;
	}

	/**
	 * Obtiene ids concatenados por el caracter ','
	 *
	 * @param listSelectedIds List<String>
	 * @return String
	 */
	public static String obtenerIdsConcatenados(List<String> listSelectedIds) {
		StringBuilder query = new StringBuilder();

		if (listSelectedIds != null && !listSelectedIds.isEmpty()) {

			for (int i = 0; i < listSelectedIds.size(); i++) {
				if (i != 0) {
					query.append(",");
				}
				query.append(listSelectedIds.get(i));
			}

		}

		return query.toString();
	}

	public static List<String> obtenerListIds(String selectedIds) {
		List<String> idsSeleccionados = new ArrayList<String>();
		if (selectedIds != null) {
			String[] ids = selectedIds.split(",");
			for (int i = 0; i < ids.length; i++) {
				idsSeleccionados.add(ids[i]);
			}
		}

		return idsSeleccionados;
	}

	/**
	 * Obtiene ids concatenados por el caracter ','
	 *
	 * @param listTareas List<Tareas>
	 * @return String
	 */
	public static String obtenerListIds(List<Tareas> listTareas) {
		StringBuilder query = new StringBuilder();

		if (listTareas != null && !listTareas.isEmpty()) {

			for (int i = 0; i < listTareas.size(); i++) {
				if (i != 0) {
					query.append(",");
				}
				query.append(listTareas.get(i).getIdTarea());
			}

		}

		return query.toString();
	}

	/**
	 * Obtiene ids concatenados por el caracter ','
	 *
	 * @param listTareas List<Tareas>
	 * @return String
	 */
	public static String obtenerListIdsTareasTrabajo(List<TareasTrabajo> listTareas) {
		StringBuilder query = new StringBuilder();
		if (listTareas != null && !listTareas.isEmpty()) {
			for (int i = 0; i < listTareas.size(); i++) {
				if (i != 0) {
					query.append(",");
				}
				query.append(listTareas.get(i).getIdTarea());
			}

		}

		return query.toString();
	}

	public static Object sustituirStringMinusPorNull(String tipoEntidadAsoc058) {
		if (null != tipoEntidadAsoc058 && "-1".equals(tipoEntidadAsoc058)) {
			return null;
		}
		return tipoEntidadAsoc058;
	}

	private static long round(double d) {
		double e = d - (long) d;
		if (e < 0.5) {
			return (long) d;
		}
		return (long) d + 1;
	}

	/** Rounds a double to x decimals. */
	public static double roundTo(double d, int x) {
		return round(Math.pow(10, x) * d) / (Math.pow(10, x));
	}

	public static String getFechaHora(Date fechaPrevistaEntrega, String horaPrevistaEntrega) {
		StringBuilder fechaHora = new StringBuilder();
		if (fechaPrevistaEntrega != null) {
			fechaHora.append(fechaPrevistaEntrega.toString());
		}
		if (horaPrevistaEntrega != null) {
			fechaHora.append(Constants.ESPACIO + horaPrevistaEntrega);
		}
		return fechaHora.toString();
	}

	/**
	 * Elimina valores duplicados
	 *
	 * @param documentosSelect String con valores separados por ","
	 * @return Set<String>
	 */
	public static Set<String> eliminarDuplicados(String str) {
		List<String> lst = new ArrayList<String>();
		lst = new ArrayList<String>(Arrays.asList(str.split(",")));
		Set<String> hsDocs = new HashSet<String>();
		hsDocs.addAll(lst);

		return hsDocs;
	}

	public static String getExtension(String nombreFichero) {
		if (StringUtils.isNotBlank(nombreFichero)) {
			return nombreFichero.substring(nombreFichero.lastIndexOf(".") + 1);
		}
		return null;
	}

	public static String getNumExpedienteParameter(Expediente expediente) {
		StringBuilder numExpParameter = new StringBuilder();
		numExpParameter.append("[EXP ZK: ");
		numExpParameter.append(expediente.getAnyoNumExpConcatenado());
		numExpParameter.append("] ");
		return numExpParameter.toString();
	}



	public static String getNumExpedienteParameter(Tareas tarea) {
		StringBuilder numExpParameter = new StringBuilder();
		numExpParameter.append("[EXP ZK: ");
		numExpParameter.append(tarea.getAnyoNumExpConcatenado());
		numExpParameter.append("] ");
		return numExpParameter.toString();
	}

	public static String getNumTrabajoAsunto(TareasTrabajo tarea) {
		StringBuilder numExpParameter = new StringBuilder();
		numExpParameter.append("[EGINKIZUN ZK: ");
		numExpParameter.append(tarea.getTrabajo().getIdTrabajo());
		numExpParameter.append("] ");
		return numExpParameter.toString();
	}

	/**
	 *
	 * @param usuario Usuario
	 * @return Boolean
	 */
	public static Boolean esTraductor(Usuario usuario) {
		for (String profile : usuario.getUserProfiles()) {
			if (AA79B_TRADUCTOR.equalsIgnoreCase(profile)) {
				return true;
			}
		}
		return false;
	}

}
