package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.DatosTareaTrados;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.ExpedienteFacturacion;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.Gestor;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.utils.Utils;

public class ExpFacturacionBuscadorRevisionRowMapper implements RowMapper<ExpedienteFacturacion> {

	@Override
	public ExpedienteFacturacion mapRow(ResultSet resultSet, int arg1) throws SQLException {
		ExpedienteFacturacion expFacturacion = new ExpedienteFacturacion();
		expFacturacion.setAnyo(resultSet.getLong(DBConstants.ANYO));
		expFacturacion.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
		// anyoconcatenado??

		expFacturacion.setIdTipoExpediente(resultSet.getString(DBConstants.IDTIPOEXPEDIENTE));
		expFacturacion.setTipoExpedienteDescEs(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCES));
		expFacturacion.setTipoExpedienteDescEu(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCEU));
		expFacturacion.setTitulo(resultSet.getString(DBConstants.TITULO));

		// TradRev
		ExpedienteTradRev tradRev = new ExpedienteTradRev();

		tradRev.setNumTotalPalIzo(resultSet.getInt(DBConstants.NUMTOTALPALIZO));
		tradRev.setIndPublicarBopv(resultSet.getString(DBConstants.INDPUBLICARBOPV));
		tradRev.setPublicarBopvDescEs(resultSet.getString(DBConstants.PUBLICARBOPVDESCES));
		tradRev.setPublicarBopvDescEu(resultSet.getString(DBConstants.PUBLICARBOPVDESCEU));
		tradRev.setIndPublicadoBoe(resultSet.getString("INDPUBLICADOBOE"));
		tradRev.setIndPublicadoBoeDescEs(resultSet.getString("INDPUBLICADOBOEDESCES"));
		tradRev.setIndPublicadoBoeDescEu(resultSet.getString("INDPUBLICADOBOEDESCEU"));
		DatosTareaTrados tradosExp = new DatosTareaTrados();
		tradosExp.setNumTotalPal(resultSet.getInt(DBConstants.NUMTOTALPALTRADOS));
		tradosExp.setNumPalConcor084(resultSet.getInt(DBConstants.NUMPALCONCOR084090));
		tradosExp.setNumPalConcor8594(resultSet.getInt(DBConstants.NUMPALCONCOR8594090));
		tradosExp.setNumPalConcor95100(resultSet.getInt(DBConstants.NUMPALCONCOR95100090));
		tradosExp.setNumPalConcor9599(resultSet.getInt(DBConstants.NUMPALCONCOR9599090));
		tradosExp.setNumPalConcor100(resultSet.getInt(DBConstants.NUMPALCONCOR100090));
		tradRev.setTradosExp(tradosExp);

		expFacturacion.setExpedienteTradRev(tradRev);

		// gestor
		Gestor gestorExpediente = new Gestor();
		Solicitante solicitanteAux = new Solicitante();
		solicitanteAux.setDni(resultSet.getString(DBConstants.DNIGESTOR));
		solicitanteAux.setPreDni(resultSet.getString(DBConstants.PREDNIGESTOR));
		solicitanteAux.setSufDni(resultSet.getString(DBConstants.SUFDNIGESTOR));
		solicitanteAux.setDniCompleto(resultSet.getString(DBConstants.DNICOMPLETOGESTOR));
		solicitanteAux.setGestorExpedientesVIP(resultSet.getString(DBConstants.INDVIPGESTOR));
		solicitanteAux.setGestorExpedientesVIPDescEs(resultSet.getString(DBConstants.GESTOREXPEDIENTESVIPDESCES));
		solicitanteAux.setGestorExpedientesVIPDescEu(resultSet.getString(DBConstants.GESTOREXPEDIENTESVIPDESCEU));
		solicitanteAux.setNombre(resultSet.getString(DBConstants.NOMBREGESTOR));
		solicitanteAux.setApellido1(resultSet.getString(DBConstants.APEL1GESTOR));
		solicitanteAux.setApellido2(resultSet.getString(DBConstants.APEL2GESTOR));
		gestorExpediente.setSolicitante(solicitanteAux);
		Entidad entidadAux = new Entidad();
		entidadAux.setTipo(resultSet.getString(DBConstants.TIPOENTIDADGESTOR));
		entidadAux.setTipoDesc(Utils.getTipoEntidadDescLabel(entidadAux.getTipo()));
		entidadAux.setCodigo(resultSet.getInt(DBConstants.IDENTIDADGESTOR));
		if (null != resultSet.getString(DBConstants.CIFENTIDADGESTOR)) {
			entidadAux.setCif(resultSet.getString(DBConstants.CIFENTIDADGESTOR));
		}
		entidadAux.setDescEs(resultSet.getString(DBConstants.DESCENTIDADESGESTOR));
		entidadAux.setDescEu(resultSet.getString(DBConstants.DESCENTIDADEUGESTOR));
		entidadAux.setDescAmpEs(resultSet.getString(DBConstants.DESCAMPENTIDADESGESTOR));
		entidadAux.setDescAmpEu(resultSet.getString(DBConstants.DESCAMPENTIDADEUGESTOR));
		gestorExpediente.setEntidad(entidadAux);
		expFacturacion.setGestorExpediente(gestorExpediente);

		expFacturacion
				.setEntidadYContactoFacturacionConcatenadosEs(resultSet.getString("DATOSFACTURACIONCONCATENADOSES"));
		expFacturacion
				.setEntidadYContactoFacturacionConcatenadosEu(resultSet.getString("DATOSFACTURACIONCONCATENADOSEU"));

		if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(expFacturacion.getIdTipoExpediente())) {
			expFacturacion.setNumPalabras(0);
		} else if (expFacturacion.getExpedienteTradRev().getTradosExp().getNumTotalPal() > 0) {
			expFacturacion.setNumPalabras(expFacturacion.getExpedienteTradRev().getTradosExp().getNumTotalPal());
		} else {
			expFacturacion.setNumPalabras(expFacturacion.getExpedienteTradRev().getNumTotalPalIzo());
		}

		return expFacturacion;
	}

}
