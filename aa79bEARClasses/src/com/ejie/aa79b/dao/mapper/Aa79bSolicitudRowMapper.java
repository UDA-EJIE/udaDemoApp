package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.DireccionNora;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.webservices.Aa79bDatosInterpretacion;
import com.ejie.aa79b.model.webservices.Aa79bDatosTradRev;
import com.ejie.aa79b.model.webservices.Aa79bDescripcionIdioma;
import com.ejie.aa79b.model.webservices.Aa79bDireccionNora;
import com.ejie.aa79b.model.webservices.Aa79bSolicitud;

public class Aa79bSolicitudRowMapper implements RowMapper<Aa79bSolicitud>{

	@Override
	public Aa79bSolicitud mapRow(ResultSet resultSet, int arg1) throws SQLException {
		Aa79bSolicitud solicitud = new Aa79bSolicitud();
        solicitud.setAnyo(resultSet.getLong("ANYO69"));
        solicitud.setNumExp(resultSet.getInt("NUMEXP69"));
        Aa79bDescripcionIdioma aa79bDescripcionTipExp = new Aa79bDescripcionIdioma();
        aa79bDescripcionTipExp.setCodigo(resultSet.getString("IDTIPOEXPEDIENTE69"));
        solicitud.setTipoExpediente(aa79bDescripcionTipExp);
        Date fechaAlta = resultSet.getTimestamp("FECHAALTA69");
		if(fechaAlta != null){
			solicitud.setFechaAlta(fechaAlta.getTime());
		}
        solicitud.setTitulo(resultSet.getString("TITULO69"));
        if(TipoExpedienteEnum.INTERPRETACION.getValue().equals(resultSet.getString("IDTIPOEXPEDIENTE69"))){
        	//DATOS INTERPRETACION
        	Aa79bDatosInterpretacion aa79bDatosInterpretacion = new Aa79bDatosInterpretacion();
        	
        	Aa79bDescripcionIdioma aa79bDescripcionModoInter = new Aa79bDescripcionIdioma();
        	aa79bDescripcionModoInter.setId(resultSet.getLong("MODOINTERPRETACION70"));
            aa79bDatosInterpretacion.setModoInterpretacion(aa79bDescripcionModoInter);
            
            Aa79bDescripcionIdioma aa79bDescripcionTipActo = new Aa79bDescripcionIdioma();
            aa79bDescripcionTipActo.setId(resultSet.getLong("TIPOACTO70"));
            aa79bDatosInterpretacion.setTipoActo(aa79bDescripcionTipActo);
            
            Aa79bDescripcionIdioma aa79bDescripcionTipPeti = new Aa79bDescripcionIdioma();
            aa79bDescripcionTipPeti.setId(resultSet.getLong("TIPOPETICIONARIO70"));
            aa79bDatosInterpretacion.setTipoPeticionario(aa79bDescripcionTipPeti);
            
            aa79bDatosInterpretacion.setIndProgramada(resultSet.getString("INDPROGRAMADA70"));
            Date fechaIni = resultSet.getTimestamp("FECHAINI70");
    		if(fechaIni != null){
    			aa79bDatosInterpretacion.setFechaIni(fechaIni.getTime());
    		}
            Date fechaFin = resultSet.getTimestamp("FECHAFIN70");
    		if(fechaFin != null){
    			aa79bDatosInterpretacion.setFechaFin(fechaFin.getTime());
    		}
    		DireccionNora direccion = new DireccionNora();
    		direccion.setDirNora(resultSet.getInt("DIRNORA70"));
    		Aa79bDireccionNora direccionNora = new Aa79bDireccionNora();
    		direccionNora.setDireccion(direccion);
            aa79bDatosInterpretacion.setDirNora(direccionNora);
            aa79bDatosInterpretacion.setPersonaContacto(resultSet.getString("PERSONACONTACTO70"));
            aa79bDatosInterpretacion.setEmailContacto(resultSet.getString("EMAILCONTACTO70"));
            aa79bDatosInterpretacion.setTelefonoContacto(resultSet.getString("TELEFONOCONTACTO70"));
        } else {
        	//DATOS TRAVREV
        	Aa79bDatosTradRev aa79bDatosTradRev = new Aa79bDatosTradRev();
            aa79bDatosTradRev.setIndPublicarBopv(resultSet.getString("INDPUBLICARBOPV71"));
            
            Aa79bDescripcionIdioma aa79bDescripcionIdioma = new Aa79bDescripcionIdioma();
            aa79bDescripcionIdioma.setId(resultSet.getLong("IDIDIOMA71"));
            aa79bDatosTradRev.setIdioma(aa79bDescripcionIdioma);
            
            aa79bDatosTradRev.setIndConfidencial(resultSet.getString("INDCONFIDENCIAL71"));
            aa79bDatosTradRev.setIndCorredaccion(resultSet.getString("INDCORREDACCION71"));
            aa79bDatosTradRev.setTexto(resultSet.getString("TEXTO71"));
            aa79bDatosTradRev.setTipoRedaccion(resultSet.getString("TIPOREDACCION71"));
            aa79bDatosTradRev.setParticipantes(resultSet.getString("PARTICIPANTES71"));
            aa79bDatosTradRev.setRefTramitagune(resultSet.getString("REFTRAMITAGUNE71"));
            aa79bDatosTradRev.setNumTotalPalSolic(resultSet.getInt("NUMTOTALPALSOLIC71"));
            Date fechaFinSol = resultSet.getTimestamp("FECHAFINALSOLIC71");
    		if(fechaFinSol != null){
    			aa79bDatosTradRev.setFechaFinalSolic(fechaFinSol.getTime());
    		}
            aa79bDatosTradRev.setIndFacturable(resultSet.getString("INDFACTURABLE71"));
            aa79bDatosTradRev.setIdRelevancia(resultSet.getLong("IDRELEVANCIA71"));
            aa79bDatosTradRev.setIndUrgente(resultSet.getString("INDURGENTE71"));
            aa79bDatosTradRev.setIndPresupuesto(resultSet.getString("INDPRESUPUESTO71"));
            
            solicitud.setDatosTradRev(aa79bDatosTradRev);
        }
        return solicitud;
	}

}
