package com.ejie.aa79b.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.model.DatosContacto;
import com.ejie.aa79b.model.DatosTareaTrados;
import com.ejie.aa79b.model.DocumentoTarea;
import com.ejie.aa79b.model.DocumentoTareaAdjunto;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.EjecucionTareas;
import com.ejie.aa79b.model.EmpresasProveedoras;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.Facturas;
import com.ejie.aa79b.model.Fichero;
import com.ejie.aa79b.model.FicheroDocExp;
import com.ejie.aa79b.model.ObservacionesExpediente;
import com.ejie.aa79b.model.ObservacionesTareas;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TiposTarea;
import com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum;
import com.ejie.aa79b.model.webservices.Aa79bDatosContacto;
import com.ejie.aa79b.model.webservices.Aa79bDatosTareaTrados;
import com.ejie.aa79b.model.webservices.Aa79bDocumentoTarea;
import com.ejie.aa79b.model.webservices.Aa79bDocumentoTareaAdjunto;
import com.ejie.aa79b.model.webservices.Aa79bDocumentosExpediente;
import com.ejie.aa79b.model.webservices.Aa79bEjecucionTareas;
import com.ejie.aa79b.model.webservices.Aa79bEmpresasProveedoras;
import com.ejie.aa79b.model.webservices.Aa79bEntradaTarea;
import com.ejie.aa79b.model.webservices.Aa79bFicheroDocExp;
import com.ejie.aa79b.model.webservices.Aa79bLotes;
import com.ejie.aa79b.model.webservices.Aa79bNotasTarea;
import com.ejie.aa79b.model.webservices.Aa79bSalidaDetalleFactura;
import com.ejie.aa79b.model.webservices.Aa79bSalidaTarea;
import com.ejie.aa79b.model.webservices.Aa79bTiposRevision;
import com.ejie.aa79b.model.webservices.Aa79bTiposTarea;

public final class WSUtils {

	/**
	 * Constructor
	 */
	private WSUtils() {

	}

	/**
	 * parseo de bean obtenido de ws finalizar tarea a bean de aa79b
	 *
	 * @param bean Aa79bEntradaTarea
	 * @return Tareas
	 */
	public static Tareas parsearAFormatoAa79b(Aa79bEntradaTarea bean) {
		// ir anyadiendo segun vaya haciendo falta
		// comun
		Tareas tarea = new Tareas();
		tarea.setIdTarea(bean.getIdTarea());
		tarea.setAnyo(bean.getAnyo());
		tarea.setNumExp(bean.getNumExp());
		TiposTarea tipoTarea = new TiposTarea();
		tipoTarea.setId015(bean.getTipoTarea().getId());
		tarea.setTipoTarea(tipoTarea);

		if (bean.getEjecucionTareas() != null) {
			EjecucionTareas ejecucionTareas = new EjecucionTareas();
			ejecucionTareas.setHorasTarea(bean.getEjecucionTareas().getHorasTarea());
			ejecucionTareas.setPorUsoEuskera(bean.getEjecucionTareas().getPorUsoEuskera());
			if (bean.getObservacionesTareas() != null
					&& StringUtils.isNotBlank(bean.getObservacionesTareas().getObsvEjecucion())) {
				ejecucionTareas.setIndObservaciones(Constants.SI);
			}
			tarea.setEjecucionTareas(ejecucionTareas);
			PersonalIZO personaAsignada = new PersonalIZO();
			personaAsignada.setNombre(bean.getUsuario());
			personaAsignada.setDni(bean.getEjecucionTareas().getDniRecurso());
			tarea.setPersonaAsignada(personaAsignada);
		}

		Integer idTipoTarea = Utils.safeLongToInt(bean.getTipoTarea().getId());
		if (TipoTareaGestionAsociadaEnum.TRADUCIR.getValue() == idTipoTarea) {
			// tarea traduccion
			insertarObservacionesTareas(tarea, bean);
		} else if (TipoTareaGestionAsociadaEnum.REVISAR.getValue() == idTipoTarea) {
			// tarea revisar
			insertarObservacionesTareas(tarea, bean);
		} else if (TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_PROVEEDOR.getValue() == idTipoTarea) {
			// tarea no conformidad proveedor
			insertarObservacionesTareas(tarea, bean);
		} else if (TipoTareaGestionAsociadaEnum.NOTIFICAR_CORRECCIONES_PROVEEDOR.getValue() == idTipoTarea) {
			// tarea notificar correcciones proveedor
		} else if (TipoTareaGestionAsociadaEnum.INTERPRETAR.getValue() == idTipoTarea) {
			// tarea interpretar
			insertarObservacionesTareas(tarea, bean);
		}

		return tarea;
	}

	/**
	 *
	 * @param tarea Aa79bEntradaTarea
	 * @param bean  Tareas
	 */
	private static void insertarObservacionesTareas(Tareas tarea, Aa79bEntradaTarea bean) {
		if (bean.getObservacionesTareas() != null) {
			ObservacionesTareas observaciones = new ObservacionesTareas();
			observaciones.setIdTarea(bean.getIdTarea());
			observaciones.setObsvEjecucion(bean.getObservacionesTareas().getObsvEjecucion());
			tarea.setObservacionesTareas(observaciones);
		}
	}

	/**
	 *
	 * @param tarea Tareas
	 * @return Aa79bSalidaTarea
	 */
	public static Aa79bSalidaTarea parsearAFormatoWsAa79(Tareas tarea) {

		// comun
		Aa79bSalidaTarea salidaTarea = new Aa79bSalidaTarea();
		salidaTarea.setIdTarea(tarea.getIdTarea());
		salidaTarea.setAnyo(tarea.getAnyo());
		salidaTarea.setNumExp(tarea.getNumExp());
		salidaTarea.setIdTareaRel(tarea.getIdTareaRel());

		if (tarea.getTipoRevision() != null) {
			Aa79bTiposRevision aa79bTiposRevision = new Aa79bTiposRevision();
			aa79bTiposRevision.setId018(tarea.getTipoRevision().getId018());
			aa79bTiposRevision.setDescEs018(tarea.getTipoRevision().getDescEs018());
			aa79bTiposRevision.setDescEu018(tarea.getTipoRevision().getDescEu018());

			salidaTarea.setTipoRevision(aa79bTiposRevision);
		}

		if (tarea.getTipoTarea() != null) {
			Aa79bTiposTarea aa79bTiposTarea = new Aa79bTiposTarea();
			aa79bTiposTarea.setId(tarea.getTipoTarea().getId015());
			aa79bTiposTarea.setDescEs(tarea.getTipoTarea().getDescEs015());
			aa79bTiposTarea.setDescEu(tarea.getTipoTarea().getDescEu015());

			salidaTarea.setTipoTarea(aa79bTiposTarea);
		}
		salidaTarea.setIndConfidencialExp(tarea.getIndConfidencialExp());
		salidaTarea.setFechaFin(tarea.getFechaFin().getTime());
		salidaTarea.setHoraFin(tarea.getHoraFin());
		salidaTarea.setIndMostrarNotasATrad(tarea.getIndMostrarNotasATrad());

		if (tarea.getLotes() != null) {
			Aa79bLotes aa79bLotes = new Aa79bLotes();
			aa79bLotes.setNombreLote(tarea.getLotes().getNombreLote());

			EmpresasProveedoras empresaProveedora = tarea.getLotes().getEmpresasProveedoras();
			if (empresaProveedora != null) {
				Aa79bEmpresasProveedoras aa79bEmpresasProveedoras = new Aa79bEmpresasProveedoras();
				aa79bEmpresasProveedoras.setCif(empresaProveedora.getCif());
				aa79bEmpresasProveedoras.setDescAmpEs(empresaProveedora.getDescAmpEs());
				aa79bEmpresasProveedoras.setDescAmpEu(empresaProveedora.getDescAmpEu());
				aa79bEmpresasProveedoras.setDescEs(empresaProveedora.getDescEs());
				aa79bEmpresasProveedoras.setDescEu(empresaProveedora.getDescEu());

				aa79bLotes.setEmpresasProveedoras(aa79bEmpresasProveedoras);

			}

			salidaTarea.setLotes(aa79bLotes);
		}

		salidaTarea.setEstadoEjecucionDesc(tarea.getEstadoEjecucionDesc());
		salidaTarea.setObservaciones(tarea.getObservaciones());
		Aa79bEjecucionTareas aa79bEjecucionTareas = new Aa79bEjecucionTareas();
		aa79bEjecucionTareas.setEstado(tarea.getEstadoEjecucion());
		if (tarea.getEjecucionTareas() != null && tarea.getEjecucionTareas().getFechaEjecucion() != null) {
			aa79bEjecucionTareas.setFechaEjecucion(tarea.getEjecucionTareas().getFechaEjecucion().getTime());
			aa79bEjecucionTareas.setHoraEjecucion(tarea.getEjecucionTareas().getHoraEjecucion());
		}
		salidaTarea.setEjecucionTareas(aa79bEjecucionTareas);
		salidaTarea.setIndRestrasada(tarea.getIndRetrasada());

		salidaTarea.setEstado(Constants.ESTADO_WS_OK);
		return salidaTarea;
	}

	public static Aa79bDatosContacto parsearDatosContactoToWS(DatosContacto datosContacto) {
		Aa79bDatosContacto aa79bDatosContacto = null;

		if (datosContacto != null) {
			aa79bDatosContacto = new Aa79bDatosContacto();
			aa79bDatosContacto.setDni(datosContacto.getDni031());
			aa79bDatosContacto.setTelfijo(datosContacto.getTelfijo031());
			aa79bDatosContacto.setTelmovil(datosContacto.getTelmovil031());
			aa79bDatosContacto.setEmail(datosContacto.getEmail031());
			aa79bDatosContacto.setNombreApellidos(datosContacto.getNombreApellidos());
		}

		return aa79bDatosContacto;
	}

	public static Aa79bDatosTareaTrados parsearDatosTareaTradosToWS(DatosTareaTrados datosTareaTrados) {
		Aa79bDatosTareaTrados aa79bDatosTareaTrados = null;

		if (datosTareaTrados != null) {
			aa79bDatosTareaTrados = new Aa79bDatosTareaTrados();
			aa79bDatosTareaTrados.setNumTotalPal(datosTareaTrados.getNumTotalPal());
			aa79bDatosTareaTrados.setNumPalConcor084(datosTareaTrados.getNumPalConcor084());
			aa79bDatosTareaTrados.setNumPalConcor8594(datosTareaTrados.getNumPalConcor8594());
			aa79bDatosTareaTrados.setNumPalConcor95100(datosTareaTrados.getNumPalConcor95100());
		}

		return aa79bDatosTareaTrados;
	}

	public static List<Aa79bDocumentoTarea> parsearDocumentosTareaToWS(List<DocumentoTarea> documentos) {
		List<Aa79bDocumentoTarea> documentosTarea = null;

		if (documentos != null && !documentos.isEmpty()) {

			documentosTarea = new ArrayList<Aa79bDocumentoTarea>();

			for (DocumentoTarea documentoTarea : documentos) {

				Aa79bDocumentoTarea aa79bDocumentoTarea = new Aa79bDocumentoTarea();

				if (documentoTarea.getDocumentoOriginal() != null) {

					DocumentosExpediente documentosExpediente = documentoTarea.getDocumentoOriginal();

					Aa79bDocumentosExpediente aa79bDocumentosExpediente = new Aa79bDocumentosExpediente();
					aa79bDocumentosExpediente.setTitulo(documentosExpediente.getTitulo());
					aa79bDocumentosExpediente.setClaseDocumentoDesc(documentosExpediente.getClaseDocumentoDesc());

					List<Aa79bFicheroDocExp> aa79blistaFicheros = parsearListaFicherosToWs(documentosExpediente);

					aa79bDocumentosExpediente.setFicheros(aa79blistaFicheros);
					aa79bDocumentoTarea.setDocumentoOriginal(aa79bDocumentosExpediente);

					documentosTarea.add(aa79bDocumentoTarea);

				} else if (documentoTarea.getDocumentoAdjunto() != null) {
					DocumentoTareaAdjunto docAuxAdjunto = documentoTarea.getDocumentoAdjunto();
					Aa79bDocumentoTareaAdjunto aa79bDocAuxAdjunto = new Aa79bDocumentoTareaAdjunto();
					aa79bDocAuxAdjunto.setIdFichero(docAuxAdjunto.getIdFichero());
					aa79bDocAuxAdjunto.setContentType(docAuxAdjunto.getContentType());
					aa79bDocAuxAdjunto.setNombre(docAuxAdjunto.getNombre());
					aa79bDocumentoTarea.setDocumentoAdjunto(aa79bDocAuxAdjunto);
					documentosTarea.add(aa79bDocumentoTarea);

				}

			}

		}

		return documentosTarea;
	}

	public static Aa79bDocumentoTarea parsearDocumentoAuxTareaToWS(DocumentoTarea documentosAux) {
		Aa79bDocumentoTarea documentosAuxTarea = null;

		if (documentosAux != null && documentosAux.getDocumentoOriginal() != null
				&& documentosAux.getDocumentoOriginal().getFicheros() != null
				&& documentosAux.getDocumentoOriginal().getFicheros().size() == 1) {
			documentosAuxTarea = new Aa79bDocumentoTarea();
			FicheroDocExp fichero = documentosAux.getDocumentoOriginal().getFicheros().get(0);
			Aa79bDocumentoTareaAdjunto aa79bDocumentosAuxTareaAdjunto = new Aa79bDocumentoTareaAdjunto();
			aa79bDocumentosAuxTareaAdjunto.setIdFichero(fichero.getIdDocInsertado());
			aa79bDocumentosAuxTareaAdjunto.setContentType(fichero.getContentType());
			aa79bDocumentosAuxTareaAdjunto.setNombre(fichero.getNombre());
			documentosAuxTarea.setDocumentoAdjunto(aa79bDocumentosAuxTareaAdjunto);

		}
		return documentosAuxTarea;
	}

	/**
	 * @param documentosExpediente
	 * @return
	 */
	private static List<Aa79bFicheroDocExp> parsearListaFicherosToWs(DocumentosExpediente documentosExpediente) {
		List<Aa79bFicheroDocExp> aa79blistaFicheros = new ArrayList<Aa79bFicheroDocExp>();
		List<FicheroDocExp> ficheros = documentosExpediente.getFicheros();

		if (ficheros != null && !ficheros.isEmpty()) {

			for (FicheroDocExp ficheroDoc : ficheros) {
				if (ficheroDoc.getIdDocInsertado() != null) {
					aa79blistaFicheros.add(parsearFicheroToWs(ficheroDoc));
				}
			}

		}

		return aa79blistaFicheros;
	}

	/**
	 *
	 * @param ficheroDoc FicheroDocExp
	 * @return Aa79bFicheroDocExp
	 */
	private static Aa79bFicheroDocExp parsearFicheroToWs(FicheroDocExp ficheroDoc) {

		Aa79bFicheroDocExp aa79bFichero = new Aa79bFicheroDocExp();
		aa79bFichero.setIdDocInsertado(ficheroDoc.getIdDocInsertado());
		aa79bFichero.setContentType(ficheroDoc.getContentType());
		aa79bFichero.setNombre(ficheroDoc.getNombre());
		return aa79bFichero;
	}

	public static List<Aa79bFicheroDocExp> parsearDocsTareaToWS(List<DocumentoTarea> documentos) {
		List<Aa79bFicheroDocExp> aa79bListaFicheros = null;

		if (documentos != null && !documentos.isEmpty()) {

			aa79bListaFicheros = new ArrayList<Aa79bFicheroDocExp>();

			for (DocumentoTarea documentoTarea : documentos) {

				if (documentoTarea.getDocumentoOriginal() != null) {

					DocumentosExpediente documentosExpediente = documentoTarea.getDocumentoOriginal();
					parsearFicherosDocExpToWS(aa79bListaFicheros, documentosExpediente.getFicheros());

				}

			}

		}

		return aa79bListaFicheros;
	}

	/**
	 * Se realiza el parseo del objeto List<FicheroDocExp> a
	 * List<Aa79bFicheroDocExp>
	 *
	 * @param aa79blistaFicheros List<Aa79bFicheroDocExp>
	 * @param ficheros           List<FicheroDocExp>
	 */
	public static void parsearFicherosDocExpToWS(List<Aa79bFicheroDocExp> aa79bListaFicheros,
			List<FicheroDocExp> ficheros) {
		if (ficheros != null && !ficheros.isEmpty()) {

			for (FicheroDocExp ficheroDoc : ficheros) {

				if (ficheroDoc.getIdDocInsertado() != null) {
					Aa79bFicheroDocExp aa79bFichero = new Aa79bFicheroDocExp();
					aa79bFichero.setOid(ficheroDoc.getOid());
					aa79bFichero.setNombre(ficheroDoc.getNombre());
					aa79bFichero.setContentType(ficheroDoc.getContentType());
					aa79bFichero.setTamano(ficheroDoc.getTamano());
					aa79bListaFicheros.add(aa79bFichero);
				}

			}

		}
	}

	/**
	 * Se realiza el parseo del objeto FicheroDocExp a Aa79bFicheroDocExp
	 *
	 * @param ficheroDocExp FicheroDocExp
	 * @return Aa79bFicheroDocExp
	 */
	public static Aa79bFicheroDocExp parsearFicheroDocExpToWs(FicheroDocExp ficheroDocExp) {
		Aa79bFicheroDocExp aa79bFicheroDocExp = null;

		if (ficheroDocExp != null) {
			aa79bFicheroDocExp = new Aa79bFicheroDocExp();
			aa79bFicheroDocExp.setNombre(ficheroDocExp.getNombre());
			aa79bFicheroDocExp.setOid(ficheroDocExp.getOid());
			aa79bFicheroDocExp.setRutaPif(ficheroDocExp.getRutaPif());
			aa79bFicheroDocExp.setTamano(ficheroDocExp.getTamano());
			aa79bFicheroDocExp.setContentType(ficheroDocExp.getContentType());
		}

		return aa79bFicheroDocExp;
	}

	/**
	 *
	 * @param factura Facturas
	 * @return Aa79bSalidaDetalleFactura
	 */
	public static Aa79bSalidaDetalleFactura parsearFacturaAFormatoWsAa79(Facturas factura) {
		Aa79bSalidaDetalleFactura detalleFactura = new Aa79bSalidaDetalleFactura();

		if (factura != null) {
			detalleFactura.setIdFactura(factura.getIdFactura());
			detalleFactura.setCodConcatenado(factura.getCodConcatenado());
			detalleFactura.setFechaEmision(factura.getFechaEmision());
			detalleFactura.setTipoExpFact(factura.getTipoExpFact());
			if (factura.getDatosFacturacionInterpretacion() != null) {
				if (factura.getDatosFacturacionInterpretacion().getEstadoFactura() != null) {
					detalleFactura.setIdEstadoFactura(
							factura.getDatosFacturacionInterpretacion().getEstadoFactura().getIndEstadoFactura());
				}
				detalleFactura.setImporteBase(factura.getDatosFacturacionInterpretacion().getImporteBase());
				detalleFactura.setImporteIva(factura.getDatosFacturacionInterpretacion().getImporteIva());
				detalleFactura.setImporteTotal(factura.getDatosFacturacionInterpretacion().getImporteTotal());
				detalleFactura.setTipoIva(factura.getDatosFacturacionInterpretacion().getTipoIva());
			}
		}

		detalleFactura.setEstado(Constants.ESTADO_WS_OK);

		return detalleFactura;
	}

	public static Aa79bEmpresasProveedoras parsearDatosEmpresaProveedoraToWS(Entidad entidad) {
		Aa79bEmpresasProveedoras aa79bEmpresasProveedoras = new Aa79bEmpresasProveedoras();

		if (entidad != null) {
			aa79bEmpresasProveedoras.setCif(entidad.getCif());
			aa79bEmpresasProveedoras.setDescAmpEs(entidad.getDescAmpEs());
			aa79bEmpresasProveedoras.setDescAmpEu(entidad.getDescAmpEu());
			aa79bEmpresasProveedoras.setDescEs(entidad.getDescEs());
			aa79bEmpresasProveedoras.setDescEu(entidad.getDescEu());
			aa79bEmpresasProveedoras.setCodigo((long) entidad.getCodigo());
			aa79bEmpresasProveedoras.setTipo(entidad.getTipo());
		}

		return aa79bEmpresasProveedoras;

	}

	/**
	 * Se realiza el parseo del objeto Fichero a Aa79bFicheroDocExp
	 *
	 * @param fichero Fichero
	 * @return Aa79bFicheroDocExp
	 */
	public static Aa79bFicheroDocExp parsearFicheroAFormatoWsAa79(Fichero fichero) {
		Aa79bFicheroDocExp aa79bFicheroDocExp = null;

		if (fichero != null) {
			aa79bFicheroDocExp = new Aa79bFicheroDocExp();
			aa79bFicheroDocExp.setOid(fichero.getOid());
			aa79bFicheroDocExp.setNombre(fichero.getNombre());
			aa79bFicheroDocExp.setContentType(fichero.getContentType());
		}

		return aa79bFicheroDocExp;
	}

	public static boolean esTipoTarea(Tareas tareaAux, int tipotarea) {
		return tareaAux != null && tareaAux.getTipoTarea() != null && (tipotarea == tareaAux.getTipoTarea().getId015());
	}

	public static StringBuilder obtenerNumExpConCeros(Integer numExpRelDigits) {
		StringBuilder numExpFormated = new StringBuilder();
		Integer numExpRelFinal;
		if (numExpRelDigits <= Constants.SEIS) {
			numExpRelFinal = Constants.SEIS - numExpRelDigits;
			numExpFormated.setLength(Constants.CERO);
			for (int j = Constants.CERO; j < numExpRelFinal; j++) {
				numExpFormated.append("0");
			}
		}
		return numExpFormated;
	}

	/**
	 * @param tarea
	 * @return
	 */
	public static boolean existenDatosTareaNulos(Tareas tarea) {
		return tarea != null && tarea.getTipoTarea() != null && tarea.getAnyo() != null && tarea.getNumExp() != null;
	}

	public static Aa79bNotasTarea parsearNotasTareaToWs(ObservacionesExpediente obsExp) {
		Aa79bNotasTarea notasTarea = new Aa79bNotasTarea();
		if(obsExp!=null){
			notasTarea.setContentType(obsExp.getContentType());
			notasTarea.setExtension(obsExp.getExtension());
			notasTarea.setNombre(obsExp.getNombre());
			notasTarea.setObservaciones(obsExp.getObservaciones());
			notasTarea.setOidFichero(obsExp.getOidFichero());
			notasTarea.setTamano(obsExp.getTamano());
		}
		return notasTarea;
	}

}
