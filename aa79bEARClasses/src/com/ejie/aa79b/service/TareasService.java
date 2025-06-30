package com.ejie.aa79b.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import org.springframework.ui.ModelMap;

import com.ejie.aa79b.model.DatosEstimacion;
import com.ejie.aa79b.model.DocPresupuestoTraduccion;
import com.ejie.aa79b.model.EntradaDatosSolicitud;
import com.ejie.aa79b.model.EstudioEstimado;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.Fichero;
import com.ejie.aa79b.model.HorasEstimadas;
import com.ejie.aa79b.model.OrigenTareaNoConformidad;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.TareaIntPagoProveed;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TareasAsignado;
import com.ejie.aa79b.model.TiposRevision;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

/**
 * @author mrodriguez
 *
 */
public interface TareasService extends GenericoService<Tareas> {

	/**
	 * Obtiene el listado de tareas para la pantalla de configuración de tareas
	 *
	 * @param filter
	 * @param jqGridRequestDto
	 * @param startsWith
	 * @return
	 */
	JQGridResponseDto<Tareas> obtenerTareasConf(Tareas filter, JQGridRequestDto jqGridRequestDto, boolean startsWith);

	boolean isVisiblePptoInterpretacion(Expediente expediente);

	/**
	 * @param listSelectedIds List<String>
	 */
	void removeTareas(List<String> listSelectedIds);

	/**
	 * @param persona         PersonalIZO
	 * @param listSelectedIds List<String>
	 * @return int
	 */
	int reasignarTareas(PersonalIZO persona, List<String> listSelectedIds);

	JQGridResponseDto<TareasAsignado> filterAsignadoAproveedores(Tareas filter, JQGridRequestDto jqGridRequestDto,
			boolean b);

	/**
	 * @param listTareas List<Tareas>
	 * @return int
	 */
	int reordenarTareas(List<Tareas> listTareas);

	/**
	 * Comprueba si la tarea seleccionada está asignada al usuario conectado y las
	 * tareas anteriores a la seleccionada están ejecutadas
	 *
	 * @param tareas Tareas
	 * @return int, Devuelve (0/1/2): 0 - Si la tarea seleccionada está asignada al
	 *         usuario conectado y las tareas anteriores a la seleccionada están
	 *         ejecutadas; 1 - Si la tarea seleccionada no está asignada al usuario
	 *         conectado; 2 - Si existe alguna anterior a la seleccionada que esté
	 *         pendiente de ejecución
	 */
	int comprobarTareaAsignador(Tareas tareas);

	/**
	 * procCrearTareasDefectoPL, llamada a PL que crea tareas por defecto para el
	 * expedienteindicado
	 *
	 * @param anyo   Long
	 * @param numExp Integer
	 */
	void procCrearTareasDefectoPL(Long anyo, Integer numExp);

	/**
	 * Crear tarea
	 *
	 * @param tarea
	 * @param documentosSelect
	 * @return int
	 */
	int crearTarea(Tareas tarea, String documentosSelect);

	TareaIntPagoProveed getDatosTareaInt(Long idTarea);

	String getRecursoAsignado(Tareas tarea);

	/**
	 * Se calculan las horas estimadas para realizar una tarea de traducción o
	 * revisión
	 *
	 * @param expediente       Expediente
	 * @param documentosSelect String
	 * @param idTipoTarea      BigDecimal
	 * @param tarea            Tareas
	 * @return HorasEstimadas con el calculo de horas estimadas para realizar la
	 *         tarea de traducción o revisión para un recurso interno y externo
	 */
	HorasEstimadas calcularHorasPrevistasTradRev(Expediente expediente, String documentosSelect,
			BigDecimal idTipoTarea, Tareas tarea);

	/**
	 * Se calculan las horas estimadas para realizar un tarea de interpretación
	 *
	 * @param expediente Expediente
	 * @return String con el calculo de horas estimadas para realizar la tarea de
	 *         interpretacion
	 */
	String calcularHorasPrevistasInter(Expediente expediente);

	/**
	 * Funcion que pasandole el anyo, numExp, idTipoTarea y orden comprueba si el
	 * orden de la tarea indicado es correcto, mediante plsql COMPROBAR_ORDEN_TAREAS
	 *
	 * @param tarea Tareas
	 * @return Devuelve (0/1): 0 - Si el orden es correcto; 1 - en caso contrario.
	 */
	int comprobarOrdenTareas(Tareas tarea);

	/**
	 * Funcion que pasandole el anyo, numExp e idTipoTarea comprueba si es factible
	 * dar de alta el tipo de tarea indicado, mediante plsql COMPROBAR_TIPO_TAREA
	 *
	 * @param tarea Tareas
	 * @return Devuelve (0/1): 0 - Si el tipo de tarea es correcto; 1 - en caso
	 *         contrario.
	 */
	int comprobarTipoTarea(Tareas tarea);

	/**
	 * Funcion que pasandole el anyo, numExp, idTipoTarea y fecha fin de la tarea
	 * comprueba si la fecha de fin de la tarea es superior a la fecha de entrega
	 * del expediente, mediante plsql COMPROBAR_FECHA_FIN_TAREA
	 *
	 * @param tarea Tareas
	 * @return Devuelve (0/1): 0 - Si la fecha fin es correcta; 1 - en caso
	 *         contrario.
	 */
	int comprobarFechaFinTarea(Tareas tarea);

	/**
	 * Funcion que nos devuelve las 6 ultimas tareas de interpretacion ejecutadas
	 * por recursos del IZO expediente interpretar entrega del
	 *
	 * @return List<Tareas>
	 */
	JQGridResponseDto<Tareas> ultimasTareasInterpretacion(JQGridRequestDto jqGridRequestDto);

	/**
	 * Finds a single row in the Tareas table.
	 *
	 * @param bean Tareas
	 * @return Tareas
	 */
	Tareas findConfTarea(Tareas bean);

	/**
	 * Obtiene los datos asociados al estudio de la estimacion
	 *
	 * @param datosEstimacion  DatosEstimacion
	 * @param jqGridRequestDto JQGridRequestDto
	 * @return JQGridResponseDto<EstudioEstimado>
	 */
	JQGridResponseDto<EstudioEstimado> obtenerEstudio(DatosEstimacion datosEstimacion,
			JQGridRequestDto jqGridRequestDto);

	/**
	 * Funcion que permite crear la tarea notificar una correccion al proveedor
	 *
	 * @param tarea   Tareas
	 * @param fichero Fichero
	 * @return int
	 */
	int notificarCorreccionProveedor(Tareas tarea, Fichero fichero);

	/**
	 * Funcion que permite crear la tarea generar no conformidad con el trabajo
	 * entregado por el proveedor
	 *
	 * @param tarea            Tareas
	 * @param documentosSelect String
	 * @param idTareaRel       BigDecimal
	 * @param fichero          Fichero
	 * @return int
	 */
	int generarNoConformidad(Tareas tarea, String documentosSelect, BigDecimal idTareaRel, Fichero fichero);

	/**
	 * Funcion que permite crear la tarea generar no conformidad con el trabajo
	 * entregado por el proveedor para la tarea de revisión con entrega a cliente
	 *
	 * @param tarea            Tareas
	 * @param documentosSelect String
	 * @param orden            Integer
	 * @param fichero          Fichero
	 * @return int
	 */
	int noConformidadRevEntregaClte(Tareas tarea, String documentosSelect, Integer orden, Fichero fichero);

	/**
	 * Finds a single row in the Tareas table.
	 *
	 * @param bean
	 * @return int
	 */
	int findLoteTareaRevisionEjec(Tareas bean);

	Tareas getDatosDocumentoPptoInterpretacion(Long anyo, Integer numExp, Long idRequerimiento);

	DocPresupuestoTraduccion getDatosDocumentoPptoTraduccion(Long anyo, Integer numExp, Long idRequerimiento);

	DocPresupuestoTraduccion getDatosDocumentoPptoTraduccionLang(Long anyo, Integer numExp, Long idRequerimiento,
			String idioma);

	/**
	 * Funcion que obtiene el detalle de la tarea
	 *
	 * @param idTarea BigDecimal
	 * @param idioma  String
	 * @return Tareas
	 */
	Tareas findDetalleTarea(BigDecimal idTarea, String idioma);

	TiposRevision findTipoRevisionTareaRel(BigDecimal idTarea);

	/**
	 * Funcion que obtiene los datos asociados a la tarea de trados para un
	 * expediente concreto.
	 *
	 * @param anyo   Long
	 * @param numExp Integer
	 * @return Tareas
	 */
	Tareas findTareaTrados(Long anyo, Integer numExp);

	/**
	 * Obtiene el número de tareas pendientes de orden inferior a la tarea indicada.
	 *
	 * @param tareas
	 * @return long
	 */
	long comprobarTareasPendientes(Tareas tareas);

	/**
	 * Funcion que reabre la tarea indicada
	 *
	 * @param idTarea BigDecimal
	 * @return int
	 */
	int reabrirTarea(BigDecimal idTarea);

	/**
	 * Funcion que comprueba la reapertura de la tarea indicada
	 *
	 * @param idTarea BigDecimal
	 * @return int
	 */
	int preReabrirTarea(BigDecimal idTarea);

	BigDecimal calcularImportePresupuestoInter(Tareas tarea);

	BigDecimal calcularImportePrevistoLote(BigDecimal idTarea, Integer idLote);

	/**
	 * Obtiene el número de tareas automáticas seleccionadas
	 *
	 * @param listSelectedIds List<String>
	 * @param tipoExpediente  String
	 * @return int, indicando el número de tareas automáticas seleccionadas
	 */
	int findTareasAutomaticasCount(List<String> listSelectedIds, String tipoExpediente);

	/**
	 * Obtiene la tarea de revision externa para el expediente indicado
	 *
	 * @param anyo   Long
	 * @param numExp Integer
	 * @return Tareas
	 */
	Tareas findTareaRevisionExterna(Long anyo, Integer numExp);

	/**
	 * Funcion que permite crear la tarea notificar una correccion al proveedor
	 *
	 * @param tarea   Tareas
	 * @param fichero Fichero
	 * @return int
	 */
	int notificarCorreccionProvTradExt(Tareas tarea, Fichero fichero);

	/**
	 *
	 * @param bean bean
	 */
	Integer actualizarFechaFinalIzo(EntradaDatosSolicitud bean);

	/**
	 * Devuelve una lista con dos enteros. Primera posición: nº de tareas de
	 * interpretación en el rango. Segunda posición: nº de tareas de cualquier tipo
	 * excepto interpretación en el rango
	 *
	 * @param tareas
	 * @return
	 */
	List<Integer> findCountTareasRangoFechas(Tareas tareas);

	/**
	 *
	 * @param tareas
	 * @return
	 */
	List<Tareas> findTareasRangoFechas(Tareas tareas);

	/**
	 * Se calculan las horas estimadas para realizar un tarea de interpretación
	 *
	 * @param tarea Tareas
	 * @return String con el cálculo de horas estimadas para realizar la tarea de
	 *         interpretación
	 */
	String calcularHorasEntreFechas(Tareas tarea);

	/**
	 *
	 * @param tareaFilter
	 * @param tipoTarea
	 * @return
	 */
	int enviarEmailsAsignadoresModifCalendario(Tareas tareaFilter, int tipoTarea);

	/**
	 * Comprueba si existen tareas que no se pueden eliminar
	 *
	 * @param listSelectedIds
	 * @return long
	 */
	long comprobarTareasAEliminar(List<String> listSelectedIds);

	/**
	 * Obtiene el número de tareas sin ejecutar
	 *
	 * @param tareas Tareas
	 * @return Long
	 */
	Long findAllCountSinEjecutar(Tareas tareas);

	/**
	 *
	 * @param tareas
	 * @return
	 */
	List<String> obtenerDestinatarios(Tareas tareas);

	/**
	 * Obtiene el número de tareas asignadas
	 *
	 * @param tareas Tareas
	 * @return long
	 */
	long findAllCountTareasAsignadas(Tareas tareas);

	/**
	 * Obtiene datos asociados a una tarea de notificación de correcciones al
	 * proveedor
	 *
	 * @param bean Tareas
	 * @return Tareas
	 */
	Tareas findDatosCorreccionesProv(Tareas bean);

	void getmodelMapDocumentoTraduccion(Locale locale, ModelMap modelMap, List<DocPresupuestoTraduccion> lDatos);

	void getmodelMapDocumentoInterpretacion(Locale locale, ModelMap modelMap, List<Tareas> lDatos);

	int enviarEmailReaperturaTarea(Tareas tareas);

	Long calcularFechaIni(Expediente expediente, String orden, BigDecimal idTarea);

	/**
	 *
	 * @param tarea Tareas
	 * @return Long
	 */
	Long calcularFechaFinTarea(Tareas tarea);

	/**
	 *
	 * @param tarea Tareas
	 * @return Tareas
	 */
	Tareas findEjecTareasPrevInterpretacion(Tareas tarea);

	/**
	 *
	 * @param tarea Tareas
	 * @return Integer
	 */
	Integer actualizarTareasPrevInterpretacion(Tareas tarea);

	/**
	 *
	 * @param idTarea BigDecimal
	 * @return OrigenTareaNoConformidad
	 */
	OrigenTareaNoConformidad obtenerOrigenNoConformidad(BigDecimal idTarea);

	/**
	 *
	 * @param tarea            Tareas
	 * @param documentosSelect String
	 * @param idTareaRel       BigDecimal
	 * @return int
	 */
	int generarNoConformidadRevInterna(Tareas tarea, String documentosSelect, BigDecimal idTareaRel);

	/**
	 *
	 * @param expediente Expediente
	 * @return Integer
	 */
	Integer comprobarFechaFinTareasNoSuperaFechaFinExp(Expediente expediente);

	/**
	 *
	 * @param tarea Tareas
	 * @return Tareas
	 */
	Tareas obtenerEstadoEjecucionTarea(Tareas tarea);

	/**
	 *
	 * @param tarea Tareas
	 * @param tiposTarea List<Integer>
	 * @return Tareas
	 */
	Tareas getAsignadoTareaRevisionAnterior(Tareas tarea, List<Integer> tiposTarea);

}
