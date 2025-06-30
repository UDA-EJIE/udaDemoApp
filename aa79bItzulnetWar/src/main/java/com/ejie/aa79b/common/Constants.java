/**
 * The file K81bConstants.java.
 */
package com.ejie.aa79b.common;

import java.math.BigDecimal;

/**
 * Clase abstracta con las constantes comunes de aplicación.
 *
 * @author llaparra
 *
 */
public abstract class Constants {

	/**
	 * Constructor.
	 */
	private Constants() {
		// Constructor
	}

	// CHECKSTYLE.OFF: La clase de constantes no cumple checkstyle
	public static final String CONSTANTE_APLICACION = "AA79B";
	public static final String CONSTANTE_APLICACION_FACTURACION = "AA79";
	public static final String CONSTANTE_APLICACION_BOLETIN = "P43";
	public static final String AA79B_PROCEDIMIENTO = "0132201";
	public static final String CONSTANTE_DEPARTAMENTO = "IVAP";

	/**
	 * LANG_CASTELLANO: 'es'.
	 */
	public static final String LANG_CASTELLANO = "es";
	/**
	 * LANG_EUSKERA: 'eu'.
	 */
	public static final String LANG_EUSKERA = "eu";

	/**
	 * The JAVA_DATE_FORMAT_ES.
	 */
	public static final String JAVA_DATE_FORMAT_ES = "dd/MM/yyyy";
	/**
	 * The JAVA_DATE_FORMAT_EU.
	 */
	public static final String JAVA_DATE_FORMAT_EU = "yyyy/MM/dd";
	/**
	 * The JAVA_DATE_HOUR_FORMAT_EU.
	 */
	public static final String JAVA_DATE_HOUR_FORMAT_EU = "yyyy/MM/dd hh24:mi:ss";
	/**
	 * The JAVA_DATE_FORMAT_ES.
	 */
	public static final String SQL_DATE_FORMAT_ES = "DD/MM/YYYY";
	/**
	 * The JAVA_DATE_FORMAT_EU.
	 */
	public static final String SQL_DATE_FORMAT_EU = "YYYY/MM/DD";

	/**
	 * The DATE_FORMAT_EU.
	 */
	public static final String DATE_FORMAT_EU = "yyyyMMdd";

	public static final String HORA_FORMAT = "HH:mm";

	public static final String HORA_LARGA_FORMAT = "HH:mm:ss";

	public static final String HORA_MINUTOS_CERO = "00:00";

	public static final String DATE_FORMAT_WS = "ddMMyyyyHHmm";

	public static final String DATE_FORMAT_SRT = "dd-MM-yyyy HH:mm:ss";

	public static final String FECHA_HORA_EU = "yyyy/MM/dd HH:mm";

	public static final String FECHA_HORA_ES = "dd/MM/yyyy HH:mm";

	/**
	 * SI: 'S'
	 */
	public static final String SI = "S";
	/**
	 * NO: 'N'
	 */
	public static final String NO = "N";
	/**
	 * INDIFERENTE: 'I'
	 */
	public static final String INDIFERENTE = "I";

	public static final String COMA = ",";
	public static final String COMA_ESPACIO = ", ";
	public static final String ESPACIO = " ";
	public static final String GUION_BAJO = "_";
	public static final String PUNTO = ".";
	public static final String ZIP = "zip";
	public static final String FIRMA_EXTENSION = "firma";

	/**
	 * ALTA: 'A'
	 */
	public static final String ALTA = "A";
	public static final String DETALLE = "D";
	public static final String MAINT = "M";
	/**
	 * BAJA: 'B'
	 */
	public static final String BAJA = "B";

	/**
	 * TREINTAYUNO: int.
	 */
	public static final Long CEROLONG = 0L;
	public static final Long UNOLONG = 1L;
	public static final Long DOSLONG = 2L;
	public static final Integer CERO = 0;
	public static final Integer UNO = 1;
	public static final Integer DOS = 2;
	public static final Integer TRES = 3;
	public static final Integer CUATRO = 4;
	public static final Integer CINCO = 5;
	public static final Integer SEIS = 6;
	public static final Integer SIETE = 7;
	public static final Integer OCHO = 8;
	public static final Integer NUEVE = 9;
	public static final Integer DIEZ = 10;
	public static final Integer ONCE = 11;
	public static final Integer DOCE = 12;
	public static final Integer CATORCE = 14;
	public static final Integer QUINCE = 15;
	public static final Integer DIECIOCHO = 18;
	public static final Integer VENTIUNO = 21;
	public static final Integer TREINTAYUNO = 31;
	public static final Integer OCHENTAYSIETE = 87;
	public static final Integer NOVENTAIDOS = 92;
	public static final Integer NOVENTAISEIS = 96;
	public static final Integer NOVENTAYNUEVE = 99;
	public static final Integer CIEN = 100;

	/**
	 * BUFFER_SIZE: 100
	 */
	public static final int BUFFER_SIZE = 100;

	/**
	 * FILE TYPE
	 */
	public static final String FILE_PDF = "pdf";
	public static final String FILE_EXCEL = "xls";
	public static final String FILE_XML = "xml";

	/**
	 * CONTENTS TYPE
	 */
	public static final String CONTENT_TYPE_EXCEL = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	public static final String CONTENT_TYPE_PDF = "application/pdf";
	public static final String CONTENT_TYPE_HTML = "text/html; charset=ISO-8859-15";

	public static final Integer CONFIG_DEFAULT_ID = 0;

	public static final String USUARIO_NORMAL = "N";
	public static final String USUARIO_VIP = "V";

	public static final Integer ID_DATOS_BASICOS = 0;
	public static final Integer ID_DATOS_BASICOS_INTERPRETACION_EMAIL = 2;

	public static final Long PUNTO_MENU_GESTION_GRUPOS = 1L;

	public static final Long PUNTO_MENU_CALENDARIO_PERSONAL = 2L;

	public static final Long PUNTO_MENU_CALENDARIO_SERVICIO = 3L;

	public static final Long PUNTO_MENU_HORARIO_ATENCION = 4L;

	public static final Long PUNTO_MENU_HORARIO_LABORAL = 5L;

	public static final Long PUNTO_MENU_LOTES = 6L;

	public static final Long PUNTO_MENU_ORDEN_PRECIOS = 7L;

	public static final Long PUNTO_MENU_DATOS_FACTURACION = 8L;

	public static final Long PUNTO_MENU_DATOS_BASICOS = 9L;

	public static final Long PUNTO_MENU_FORMATOS_FICHERO = 10L;

	public static final Long PUNTO_MENU_TIPOS_TAREA = 12L;

	public static final Long PUNTO_MENU_TIPOS_REVISION = 13L;

	public static final Long PUNTO_MENU_AYUDA = 14L;

	public static final Long PUNTO_MENU_TIPOS_INTERPRETACION = 15L;

	public static final Long PUNTO_MENU_TRAMITACION_EXPEDIENTES = 16L;

	public static final Long PUNTO_MENU_PLANIFICACION_EXPEDIENTES = 17L;

	public static final Long ACCION_ALTA = 1L;
	public static final Long ACCION_MODIFICACION = 2L;
	public static final Long ACCION_BORRADO = 3L;

	public static final Long RANGO_PALABRAS_DESDE = 0L;
	public static final Long RANGO_PALABRAS_HASTA = 999999L;

	public static final Integer DEFAULT_HORAS_GESTION_EXP_CLA_PERSONAL = 8;

	/**
	 * ROLES PERSONA
	 *
	 */
	public static final String ROL_SOLICITANTE = "S";
	public static final String ROL_RECEPTOR_AUTORIZADO = "R";
	public static final String ROL_PERSONAL_IZO = "I";
	public static final String ROL_PROVEEDOR_EXTERNO = "P";

	public static final Long MAGIC_NUMBER_MENOS_1_L = -1L;

	public static final String CONSULTA = "C";
	public static final String PLANIFICACION = "P";

	public static final Integer OTROS_MOTIVOS = 1;

	public static final String ENCRIPTAR = "E";
	public static final String DESENCRIPTAR = "D";

	public static final String ESTADO_WS_OK = "OK";
	public static final String ESTADO_WS_ERROR = "ERROR";

	public static final String TABLA_GESTOR_EXPEDIENTE = "54";
	public static final String TABLA_OBSERVACIONES_SOLICITUD = "72";

	public static final String RUTA_JASPER_JUSTIFICANTE_INTERPRETACION = "/WEB-INF/resources/reports/justificanteInterpretacion.jasper";
	public static final String RUTA_JASPER_JUSTIFICANTE_TRADREV = "/WEB-INF/resources/reports/justificanteTraducRevision.jasper";

	public static final String RUTA_JASPER_DOC_PRESUPUESTO_INTERPRETACION = "/WEB-INF/resources/reports/documentoPresupuestoInterpretacion.jasper";
	public static final String RUTA_JASPER_DOC_PRESUPUESTO_TRADUCCION = "/WEB-INF/resources/reports/documentoPresupuestoTraduccion.jasper";

	public static final String NULL = "null";
	public static final String GUION = "-";

	public static final String IZO = "IZO";

	public static final String LOCAL = "local";
	public static final String DES = "des";

	public static final String DESTINATARIO_EMAIL_LOCAL = "emailerdevelopment01@amatech-group.com";

	public static final BigDecimal MAGIC_NUMBER_0 = new BigDecimal(0);

	public static final String STRING_CERO = "0";

	public static final String CONTRABARRA = "/";

	public static final String LABEL_OBSERVACIONES_MODIFICADAS = "mensaje.observacionesModificadas";

	public static final Integer MINUS_UNO = -1;
	public static final Integer ANYO_FACTURABLE = 2018;

	public static final String TAMANO_FICHERO_MAX_2GB = "2147483648";
	public static final String TAMANO_FICHERO_MAX_500MB = "524288000";
	public static final String TAMANO_FICHERO_MAX_2MB = "2097152";
	public static final String TAMANO_1MB_EN_BYTES = "1048576";

	public static final String GANTT_EXP_CSS = "expediente";

	public static final String FACTURACION = "F";

	public static final String APP_ENTORNO = "app.entorno";

	public static final String LABEL_NUM_EXP = "label.numExp";
	public static final String LABEL_ID_TAREA = "label.tarea";
	public static final String LABEL_FECHA_FIN_NUEVA = "label.fechaFinNueva";
	public static final String LABEL_FECHA_FIN_IZO_NUEVA = "label.fechaFinIZONueva";
	public static final String LABEL_COD_APLIC = "label.codAplic";
	public static final String LABEL_OBSERVACIONES = "label.observaciones";

	public static final String LABEL_NUM_TRABAJO = "label.numTrabajo";

	public static final Long NUM_MAX_REG_REVISION = 10L;

	public static final String EMPTY = "";
	public static final String COD_PROV_BIZKAIA = "48";
	public static final String COD_PROV_ARABA = "01";
	public static final String COD_PROV_GIPUZKOA = "20";

	public static final String TIP_TAREA_TRADUZIR = "IT";
	public static final String TIP_TAREA_REVIZAR = "BE";

	public static final Integer CODIGO_CONCEPTO = 700;
	public static final Integer CODIGO_CONCEPTO_CON_IVA = 703;

	public static final String TIPO_PARTIDA_COBRO = "C";
	public static final String TIPO_PARTIDA_PAGO = "P";

	/**
	 * ID_AA79B_W05B
	 */
	public static final String ID_AA79B_W05B = "21";
	public static final String TIPO_CIF = "1";

	public static final String ABRIR_CORCHETE = "[";
	public static final String CERRAR_CORCHETE = "]";

	public static final Integer DOSCIENTOS = 200;

	public static final Integer CINCUENTA = 50;

	public static final String LABEL = "label";
	public static final String NAME = "name";

	public static final int VEINTE = 20;

	public static final Integer TRESCIENTOS = 300;

	public static final int DOSCIENTOSCUARENTAYDOS = 242;

	public static final String LABEL_MODIFICACION_DOC_TAREA = "mensaje.modificadionDocTarea";
	public static final String STRING_UNO = "1";
	public static final String STRING_DOS = "2";
	public static final String STRING_CUATRO = "4";
	public static final String STRING_CINCO = "5";
	public static final String STRING_SEIS = "6";
	public static final String STRING_SIETE = "7";

	public static final String DOS_PUNTOS = ":";
	public static final String GUION_CON_ESPACIOS = ESPACIO + GUION + ESPACIO;
	public static final String ABRIR_PARENTESIS = " ( ";
	public static final String CERRAR_PARENTESIS = " ) ";
	public static final String SALTO_DE_LINEA = " <br> ";

	public static final String LABEL_DNI = "label.dni";
	public static final String LABEL_NOMBREAPELLIDOS = "label.nombreapellidos";
	public static final String LABEL_ENTIDAD = "label.entidad";
	public static final String LABEL_GESTOR_VIP = "label.gestorVip";
	public static final String LABEL_NUM_PALABRAS = "label.numpalavras";
	public static final String LABEL_PAL_CONCOR_1 = "label.palconcor1";
	public static final String LABEL_PAL_CONCOR_2 = "label.palconcor2";
	public static final String LABEL_PAL_CONCOR_3 = "label.palconcor3";

	public static final String EXTENSION_EXCEL = ".xlsx";
	public static final String EXTENSION_PDF = ".pdf";
	public static final String TEMPLATE_EXCEL = "printExcel";
	public static final String TEMPLATE_PDF = "defaultPDF";
	public static final int MOTIVO_RECHAZO_OTROS = 1;
	public static final int IDIOMA_EUSKERA = 2;

	public static final String LABEL_NO_CONFORMIDAD_CLIENTE = "label.noConformidadCliente";

	public static final int TIPO_REVISION_SENCILLA = 1;

	public static final String IGUAL = "=";

	public static final int SB_INIT = 4096;
	public static final Integer ID_TIPO_DOC_NOTA_PRENSA = 38;

	public static final String COD_ERROR_IZOBERRI_YA_ENVIADO = "202";

	public static final String EVENT_TIPOLOGY = "AA79_IZO_STATUS";
	public static final String EVENT_WHAT = "AA79_STATUS_CHANGED";
	public static final String CHANGE_RESULT_DELIVERED = "RESULT_DELIVERED";
	public static final String CHANGE_DATE = "DATE_CHANGED";

	public static final String EVENT_TIPOLOGY_AA79B_MESSAGE = "AA79_IZO_MESSAGE";
	public static final String EVENT_WHAT_AA79B_MESSAGE = "AA79_MESSAGE_CREATED";
	public static final String CHANGE_TYPE_AA79B_MESSAGE = "MESSAGE_CREATED";
	public static final String RUTAPIF_DOC_AA79B_MESSAGE = "comunicacionesIZO";


	public static final String CONSTANTE_APLICACION_TRAMITAGUNE = "R02T";

	public static final String ORIGEN_WEB_SERVICE = "W";

	public static final Integer EMAIL_GENERAL_IZO = 1;
	public static final Integer EMAIL_RESPONSABLES_INTERPRETACION = 2;

	public static final String AD55B = "ad55b";
	public static final String P43B = "P43B";

	public static final String COMUNICACION_RECIBIDA = "R";
	public static final String COMUNICACION_ENVIADA = "E";

}
