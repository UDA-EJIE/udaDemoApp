package com.ejie.aa79b.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ejie.aa79b.model.DatosEstimacion;
import com.ejie.aa79b.model.DatosPagoProveedores;
import com.ejie.aa79b.model.EntradaDatosSolicitud;
import com.ejie.aa79b.model.EstudioEstimado;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.FechaHoraIniFin;
import com.ejie.aa79b.model.OrigenTareaNoConformidad;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.ResumenGraficas;
import com.ejie.aa79b.model.TareaIntPagoProveed;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TareasAsignado;
import com.ejie.aa79b.model.TiposRevision;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.TableRowDto;

/**
 * @author mrodriguez
 *
 */
public interface TareasDao extends GenericoDao<Tareas> {

	/**
	 * @param filter
	 * @param jqGridRequestDto
	 * @param startsWith
	 * @param noRelacionados
	 * @return
	 */
	List<Tareas> obtenerTareasConf(Tareas filter, JQGridRequestDto jqGridRequestDto, boolean startsWith);

	/**
	 * @param filter
	 * @param startsWith
	 * @param noRelacionados
	 * @return
	 */
	Long obtenerTareasConfCount(Tareas filter, boolean startsWith);

	/**
	 * @param filter
	 * @param jqGridRequestDto
	 * @param startsWith
	 * @return
	 */
	List<TableRowDto<Tareas>> reorderSelectionTareasConf(Tareas filter, JQGridRequestDto jqGridRequestDto,
			boolean startsWith);

	boolean isVisiblePptoInterpretacion(Expediente expediente);

	/**
	 * @param listSelectedIds List<String>
	 */
	void removeTareas(List<String> listSelectedIds);

	/**
	 * @param listSelectedIds List<String>
	 * @return List<Tareas>
	 */
	List<Tareas> findTareasPendientesDeEjecutar(List<String> listSelectedIds);

	/**
	 *
	 * @param dni          String
	 * @param tipoConsulta int
	 * @return ResumenGraficas
	 */
	ResumenGraficas getTareasPlanificacionCount(String dni, int tipoConsulta);

	/**
	 * @param tarea           Tareas
	 * @param listSelectedIds List<String>
	 * @return int
	 */
	int reasignarTareas(Tareas tarea, List<String> listSelectedIds);

	/**
	 * @param listTareas List<Tareas>
	 */
	void reordenarTareas(List<Tareas> listTareas);

	/**
	 * Finds rows in the table using like.
	 *
	 * @param bean             T
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith       Boolean
	 * @return List
	 */
	List<TareasAsignado> findTareasAsignadasAProveedor(Tareas bean, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith);

	/**
	 *
	 * @param idTarea BigDecimal
	 */
	void updateTareaEjecutada(BigDecimal idTarea);

	/**
	 *
	 * @param idTarea        Long
	 *
	 * @param indFacturacion String
	 */
	void updateIndFacturableRelacionada(BigDecimal idTarea, String indFacturacion);

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
	 *
	 * @param tareas Tareas
	 * @return Long
	 */
	Long findAllCountInterpretacionFin(Tareas tareas);

	/**
	 *
	 * @param tarea Tareas
	 */
	void reiniciarCalculoInterpretacion(Tareas tarea);

	/**
	 *
	 * @param tarea Tareas
	 */
	void updateConformidad(Tareas tarea);

	/**
	 *
	 * @param tareas Tareas
	 * @return Long
	 */
	Long findAllCountNoReqCierreSinEjecutar(Tareas tareas);

	Long findAllCountSinEjecutar(Tareas tareas);

	/**
	 *
	 * @param tareas Tareas
	 * @return Long
	 */
	Long findAllCountMismoOrden(Tareas tareas);

	/**
	 * procCrearTareasDefectoPL, llamada a PL que crea tareas por defecto para el
	 * expediente indicado
	 *
	 * @param anyo   Long
	 * @param numExp Integer
	 */
	void procCrearTareasDefectoPL(Long anyo, Integer numExp);

	Tareas addTareaSubsanacion(Tareas tareas);

	TareaIntPagoProveed getDatosTareaInt(Long idTarea);

	String getRecursoAsignado(Tareas tarea);

	/**
	 * Se calculan las horas estimadas para realizar una tarea de traducción o
	 * revisión
	 *
	 * @param indRecurso  String
	 * @param expediente  Expediente
	 * @param idDocs      String
	 * @param idTipoTarea BigDecimal
	 * @param tarea       Tareas
	 * @return String con el calculo de horas estimadas para realizar la tarea de
	 *         traducción o revisión
	 */
	String calcularHorasPrevistasTradRev(String indRecurso, Expediente expediente, String idDocs,
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
	 * @return Devuelve (0/1): 0 - Si el orden es correcto; 1 - en caso contrario.
	 */
	int comprobarTipoTarea(Tareas tarea);

	/**
	 * Funcion que nos devuelve las 6 ultimas tareas de interpretacion ejecutadas
	 * por recursos del IZO expediente interpretar entrega del
	 *
	 * @return List<Tareas>
	 */
	Object ultimasTareasInterpretacion(JQGridRequestDto jqGridRequestDto, Boolean isCount);

	/**
	 * Funcion que pasandole el anyo, numExp, idTipoTarea y fecha fin de la tarea
	 * comprueba si la fecha de fin de la tarea es superior a la fecha de entrega
	 * del expediente, mediante plsql COMPROBAR_FECHA_FIN_TAREA
	 *
	 * @param tarea Tareas
	 * @return Devuelve (0/1): 0 - Si el orden es correcto; 1 - en caso contrario.
	 */
	int comprobarFechaFinTarea(Tareas tarea);

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
	 * @return List<EstudioEstimado>
	 */
	List<EstudioEstimado> obtenerEstudio(DatosEstimacion datosEstimacion, JQGridRequestDto jqGridRequestDto);

	/**
	 * @param datosEstimacion
	 * @return
	 */
	Long obtenerEstudioCount(DatosEstimacion datosEstimacion);

	/**
	 * Finds a single row in the Tareas table.
	 *
	 * @param bean Tareas
	 * @return Integer: Nº de lote de la tarea de traducción en estado ejecutada
	 */
	Integer findLoteTareaTradEjec(Tareas bean);

	/**
	 *
	 * @param tarea Tareas
	 * @return Tareas
	 */
	Tareas updateIdRequerimiento(Tareas tarea);

	/**
	 * Finds a single row in the Tareas table.
	 *
	 * @param bean
	 * @return int
	 */
	int findLoteTareaRevisionEjec(Tareas bean);

	FechaHoraIniFin getDocumentoPresupuestoInterpretacionFechaIniFin(Long anyo, Integer numExp);

	/**
	 * Finds a single row in the Tareas table.
	 *
	 * @param idTarea BigDecimal
	 * @param idioma  String
	 * @return Tareas
	 */
	Tareas findDetalleTarea(BigDecimal idTarea, String idioma);

	TiposRevision findTipoRevisionTareaRel(BigDecimal idTarea);

	/**
	 * Finds a single row in the Tareas table.
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
	 */
	void reabrirTarea(BigDecimal idTarea);

	BigDecimal calcularImportePresupuestoInter(Tareas tarea);

	BigDecimal calcularImportePrevistoLote(BigDecimal idTarea, Integer idLote);

	/**
	 * Obtiene las tareas partiendo de un listado de identificadores de tarea
	 *
	 * @param listSelectedIds List<String>
	 * @return List<Tareas>
	 */
	List<Tareas> findTareas(List<String> listSelectedIds);

	/**
	 * Obtiene los datos de una tarea
	 *
	 * @param bean
	 * @return Tareas
	 */
	Tareas findTarea(Tareas bean);

	/**
	 * Obtiene las tareas relacionadas con la tarea indicada.
	 *
	 * @param idTarea BigDecimal
	 * @return List<Tareas>
	 */
	List<Tareas> findTareasRelEjecutadas(BigDecimal idTarea);

	void updateIdSubsanacionTareaEjecutada(BigDecimal idTarea, Long idSubsanacion);

	Long findTareasAsignadasAProveedorCount(Tareas bean);

	/**
	 * Obtiene el número de tareas automáticas seleccionadas
	 *
	 * @param listSelectedIds List<String>
	 * @param tipoExpediente  String
	 * @return int, indicando el número de tareas automáticas seleccionadas
	 */
	int findTareasAutomaticasCount(List<String> listSelectedIds, String tipoExpediente);

	/**
	 * Elimina las tareas dependientes de las tareas seleccionadas
	 *
	 * @param listTareas List<Tareas>
	 */
	void removeTareasDependientes(List<Tareas> listTareas);

	/**
	 * Elimina la relación de las tareas dependientes con las tareas seleccionadas
	 *
	 * @param listTareas List<Tareas>
	 */
	void removeRelacionTareasDependientes(List<Tareas> listTareas);

	/**
	 * Obtiene la tarea de revision externa para el expediente indicado
	 *
	 * @param anyo   Long
	 * @param numExp Integer
	 * @return Tareas
	 */
	Tareas findTareaRevisionExterna(Long anyo, Integer numExp);

	/**
	 * Obtiene el id de la tarea de revisión externa ejecutada
	 *
	 * @param bean Tareas
	 * @return BigDecimal
	 */
	BigDecimal findTareaRevisionExtEjec(Tareas bean);

	/**
	 * Obtiene el id de la tarea de traducción externa ejecutada
	 *
	 * @param bean Tareas
	 * @return BigDecimal
	 */
	BigDecimal findTareaTraduccionExtEjec(Tareas bean);

	/**
	 *
	 * @param idTarea       BigDecimal
	 * @param intvalueTabla Integer
	 */
	Integer eliminar(BigDecimal idTarea, Integer intvalueTabla);

	/**
	 * @param listSelectedIds
	 * @return List<Tareas>
	 */
	List<Tareas> findTareasAEliminar(List<String> listSelectedIds);

	/**
	 * @param listSelectedIds
	 * @return List<Tareas>
	 */
	List<Tareas> findTareasDependientesAEliminar(List<Tareas> listTareas);

	/**
	 * Finds a single row in the Tareas table.
	 *
	 * @param listTareas List<Tareas>
	 * @return Tareas
	 */
	Tareas findTareaTrados(List<Tareas> listTareas);

	/**
	 * Devuelve el id de la tarea relacionada
	 *
	 * @param tarea
	 * @return
	 */
	BigDecimal findTareaRelacionada(Tareas tarea);

	/**
	 * Elimina las observaciones de rechazo para la tarea indicada.
	 *
	 * @param idTarea
	 */
	void removeObsrvRechazo(BigDecimal idTarea);

	/**
	 * Elimina las observaciones de rechazo para el listado de tareas
	 * correspondiente.
	 *
	 * @param listTareas
	 */
	void removeObsrvRechazo(List<Tareas> listTareas);

	/**
	 * Se obtiene el listado de tareas asociadas a la subsanación de la
	 * documentación, en caso de que se haya modificado algún documento al realizar
	 * dicha subsanación.
	 *
	 * @param idRequerimiento Long
	 * @return List<Tareas>
	 */
	List<Tareas> tareasAsociadasASubDoc(Long idRequerimiento);

	/**
	 *
	 * @param bean EntradaDatosSolicitud
	 * @return Integer
	 */
	Integer actualizarFechaFinalIzo(EntradaDatosSolicitud bean);

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
	 * @param tarea
	 * @return
	 */
	List<Tareas> findTareasRangoFechas(Tareas tarea);

	/**
	 * Comprueba si existen tareas que no se pueden eliminar
	 *
	 * @param listSelectedIds
	 * @return long
	 */
	long comprobarTareasAEliminar(List<String> listSelectedIds);

	/**
	 * Actualiza la fecha de aceptación en caso de que el estado de la tarea sea
	 * pendiente de aceptación o en caso de que la tarea esté aceptada y haya un
	 * cambio de tipo de recurso
	 *
	 * @param tareas            Tareas
	 * @param cambioTipoRecurso boolean
	 * @return Tareas
	 */
	Tareas updateFechaAceptacion(Tareas tareas, boolean cambioTipoRecurso);

	/**
	 *
	 * @param anyo
	 * @param numExp
	 */
	void desasignarTareasPdtesEjec(Long anyo, Integer numExp);

	/**
	 *
	 * @param tarea
	 * @param esInterpretacion: 0-sin tipo. 1- tipo interpretación. 2- todos los
	 *                          tipos menos interpretación
	 * @return
	 */
	List<Tareas> findTareasRangoFechas(Tareas tarea, int esInterpretacion);

	/**
	 * Obtiene el número de tareas ejecutadas
	 *
	 * @param tareas Tareas
	 * @return long
	 */
	long findAllCountTareasEjecutadas(Tareas tareas);

	/**
	 *
	 * @param anyo
	 * @param numExp
	 * @return List<Tareas>
	 */
	List<Tareas> getTareasAsignadasNoEjecutadas(Long anyo, Integer numExp);

	/**
	 * Obtiene el número de tareas asignadas
	 *
	 * @param tareas Tareas
	 * @return long
	 */
	long findAllCountTareasAsignadas(Tareas tareas);

	/**
	 * Obtiene el número de tareas de revisión externa
	 *
	 * @param tareas Tareas
	 * @return int
	 */
	int findCountTareasRevExt(Tareas tareas);

	/**
	 * Obtiene el número de tareas de no conformidad con el proveedor sin ejecutar y
	 * asociadas a alguno de los documentos seleccionados
	 *
	 * @param tareas           Tareas
	 * @param documentosSelect String
	 * @return int
	 */
	int countTareasNoConfProvSinEjec(Tareas tareas, String documentosSelect);

	/**
	 * Obtiene el número de tareas de no conformidad con el proveedor sin ejecutar y
	 * asociadas a alguno de los documentos seleccionados
	 *
	 * @param tareas           Tareas
	 * @param documentosSelect String
	 * @return int
	 */
	int countTareasNoConfClienteSinEjec(Expediente expediente);

	/**
	 * Recupera el idTarea a partir de un idRequerimiento
	 *
	 * @param idRequerimiento
	 * @return
	 */
	BigDecimal findIdTareaPorRequerimiento(Long idRequerimiento);

	/**
	 *
	 * @param tareas
	 * @return
	 */
	BigDecimal findTareaEjecutadaPosttraduccionBOE(Expediente exp);

	void procActDatosPagoProvPL(final DatosPagoProveedores datosPagoProveedores);

	/**
	 * procCrearTareasRelacionadasPL, llamada a PL que crea tareas tareas
	 * relacionadas para el expediente indicado
	 *
	 * @param idTarea
	 * @return
	 */
	Integer procCrearTareasRelacionadasPL(final BigDecimal idTarea);

	/**
	 * Comprueba si la tarea se puede reabrir
	 *
	 * @param idTarea BigDecimal
	 * @return int, Devuelve (0/1): 0 - Si la tarea se puede reabrir; 1 - En caso
	 *         contrario
	 */
	int comprobarReaperturaTarea(BigDecimal idTarea);

	/**
	 * Obtiene datos asociados a una tarea de notificación de correcciones al
	 * proveedor
	 *
	 * @param bean Tareas
	 * @return Tareas
	 */
	Tareas findDatosCorreccionesProv(Tareas bean);

	/**
	 *
	 * @param expediente
	 * @param orden
	 * @param idTarea
	 * @return
	 */
	Long calcularFechaIni(Expediente expediente, String orden, BigDecimal idTarea);

	/**
	 *
	 * @param tarea Tareas
	 * @return Long
	 */
	Long calcularFechaFinTarea(Tareas tarea);

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
	 * @return int
	 */
	int countTareasNoConfRevInternaSinEjec(Tareas tarea, String documentosSelect);

	/**
	 *
	 * @param bean Tareas
	 * @return PersonalIZO
	 */
	PersonalIZO findPersonaAsignadaTareaTradEjec(Tareas bean);

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
	Tareas comprobarEstadoEjecucionTarea(Tareas tarea);


	/**
	 * Obtiene el número de tareas pendientes de orden inferior a la tarea indicada.
	 *
	 * @param tareas
	 * @return long
	 */
	Integer comprobarTareasPendientesTradRevUsuario(Tareas tareas);

	/**
	 * Obtiene el número de tareas pendientes de orden inferior a la tarea indicada.
	 *
	 * @param tareas
	 * @return long
	 */
	Integer comprobarTareasPendientesInterpretacion(Tareas tareas);

	/**
	 *
	 * @param tarea Tareas
	 * @param tiposTarea List<Integer>
	 * @return Tareas
	 */
	Tareas getAsignadoTareaRevisionAnterior(Tareas tarea, List<Integer> tiposTarea);

	/**
	 *
	 * @param tareaFilter Tareas
	 * @return Tareas
	 */
	Tareas obtenerUltimaTareaEjecutadaConDocXliff(Tareas tareaFilter);

}
