package com.ejie.aa79b.webservices;

import com.ejie.aa79b.model.webservices.servicios.Aa79bSolicitud;
import com.ejie.aa79b.webservices.x54j.X54JRespuestaAnadirRolWS;

/**
 * The type T65bWebServices.
 *
 * @author vdorantes
 *
 */
public interface X54JPermisosService {

	public X54JRespuestaAnadirRolWS anadirRolBOPV(Integer tipIden, String preDni, String dni, String sufDni);

	public X54JRespuestaAnadirRolWS anadirRol(Aa79bSolicitud bean);

}
