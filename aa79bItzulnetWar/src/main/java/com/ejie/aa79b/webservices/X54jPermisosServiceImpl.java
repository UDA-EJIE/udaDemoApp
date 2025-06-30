package com.ejie.aa79b.webservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.model.webservices.servicios.Aa79bSolicitud;
import com.ejie.aa79b.webservices.x54j.ArrayOfX54JPermisosWS;
import com.ejie.aa79b.webservices.x54j.ObjectFactory;
import com.ejie.aa79b.webservices.x54j.X54JPermisosWS;
import com.ejie.aa79b.webservices.x54j.X54JPermisosWSPort;
import com.ejie.aa79b.webservices.x54j.X54JPeticionAnadirRolWS;
import com.ejie.aa79b.webservices.x54j.X54JRespuestaAnadirRolWS;
import com.ejie.aa79b.webservices.x54j.X54JUsuarioWS;

@Service(value = "x54JPermisosService")
public class X54jPermisosServiceImpl implements X54JPermisosService {

    @Autowired()
    private X54JPermisosWSPort x54JPermisosWSPort;

	private static final Logger LOGGER = LoggerFactory.getLogger(X54jPermisosServiceImpl.class);
	private static final String WEB_SERVICE = "WEBSERVICE";


	@Override
	public X54JRespuestaAnadirRolWS anadirRolBOPV(Integer tipIden, String preDni, String dni, String sufDni) {
		X54jPermisosServiceImpl.LOGGER.info("X54jPermisosServiceImpl.guardarFicherosAnexos");

		// Establecimiento del endpoint real. Si no, por defecto se utilizara el que marque el port del WSDL
		X54JPeticionAnadirRolWS x54JPeticionAnadirRolWS = this.prepararPeticion(dni, Constants.STRING_SIETE, Constants.STRING_UNO,
				Constants.STRING_CINCO);
		X54JUsuarioWS x54JUsuarioWS = this.prepararUsuario(Constants.CONSTANTE_APLICACION, String.valueOf(tipIden), preDni, dni, sufDni, WEB_SERVICE,
				Constants.LANG_CASTELLANO);

		return this.x54JPermisosWSPort.anadirRol(x54JPeticionAnadirRolWS, x54JUsuarioWS);
	}

	@Override
	public X54JRespuestaAnadirRolWS anadirRol(Aa79bSolicitud bean) {
		X54JPeticionAnadirRolWS x54JPeticionAnadirRolWS = this.prepararPeticion(bean.getDatosSolicitante().getNifSolicitante(), Constants.STRING_SIETE,
				Constants.STRING_UNO, Constants.STRING_SEIS);
		X54JUsuarioWS x54JUsuarioWS = this.prepararUsuario(Constants.CONSTANTE_APLICACION, bean.getDatosSolicitante().getTipoDocumento(),
				bean.getDatosSolicitante().getPrefijoDocumento(), bean.getDatosSolicitante().getNifSolicitante(),
				bean.getDatosSolicitante().getSufijoDocumento(), WEB_SERVICE, Constants.LANG_CASTELLANO);

		X54JRespuestaAnadirRolWS x54JRespuestaAnadirRolWS = this.x54JPermisosWSPort.anadirRol(x54JPeticionAnadirRolWS, x54JUsuarioWS);
		return x54JRespuestaAnadirRolWS;
	}

	private X54JPeticionAnadirRolWS prepararPeticion(String dni, String aplicacion, String rol, String codigoPermiso) {
		X54JPeticionAnadirRolWS x54JPeticionAnadirRolWS = new X54JPeticionAnadirRolWS();
		x54JPeticionAnadirRolWS.setDni(dni);
		x54JPeticionAnadirRolWS.setAplicacion(aplicacion);
		x54JPeticionAnadirRolWS.setRol(rol);
		X54JPermisosWS permiso = new X54JPermisosWS();
		permiso.setCodigo(codigoPermiso);
		ObjectFactory objectFactory = new ObjectFactory();
		ArrayOfX54JPermisosWS permisos = objectFactory.createArrayOfX54JPermisosWS();
		permisos.getX54JPermisosWS().add(permiso);
		x54JPeticionAnadirRolWS.setPermisos(objectFactory.createX54JPeticionAnadirRolWSPermisos(permisos));
		return x54JPeticionAnadirRolWS;
	}

	private X54JUsuarioWS prepararUsuario(String codAplic, String tipIden, String preDni, String dni, String sufDni, String ipPuesto, String idioma) {
		X54JUsuarioWS x54JUsuarioWS = new X54JUsuarioWS();
		x54JUsuarioWS.setCodAplic(codAplic);
		if (tipIden != null) {
			x54JUsuarioWS.setTipIden(tipIden);
		}
		x54JUsuarioWS.setPreDni(preDni);
		x54JUsuarioWS.setDni(dni);
		x54JUsuarioWS.setSufDni(sufDni);
		x54JUsuarioWS.setUserXlnets(dni);
		x54JUsuarioWS.setIpPuesto(ipPuesto);
		x54JUsuarioWS.setIdioma(idioma);
		return x54JUsuarioWS;
	}


}
