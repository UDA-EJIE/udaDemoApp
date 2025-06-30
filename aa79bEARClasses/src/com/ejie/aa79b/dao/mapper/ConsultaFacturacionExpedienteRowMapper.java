package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.CentroOrganico;
import com.ejie.aa79b.model.ConsultaFacturacionExpediente;
import com.ejie.aa79b.model.ContactoFacturacionExpediente;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.Solicitante;

public class ConsultaFacturacionExpedienteRowMapper implements RowMapper<ConsultaFacturacionExpediente> {

	@Override
	public ConsultaFacturacionExpediente mapRow(ResultSet resultSet, int arg1) throws SQLException {
		Locale locale = LocaleContextHolder.getLocale();

		ConsultaFacturacionExpediente consultaFacturacionExpediente = new ConsultaFacturacionExpediente();
		ContactoFacturacionExpediente contactoFacturacionExpediente = new ContactoFacturacionExpediente();
		Entidad entidad = new Entidad();
		entidad.setTipo(resultSet.getString("TIPOENTIDADASOCIADA"));
		entidad.setCodigo(resultSet.getInt("IDENTIDADASOCIADA"));
		entidad.setDescEu(resultSet.getString("DESCENTIDADEU"));
		entidad.setDescEs(resultSet.getString("DESCENTIDADES"));
		entidad.setIva(resultSet.getString("IVA"));
		contactoFacturacionExpediente.setEntidadSolicitante(entidad);
		Solicitante solicitante = new Solicitante();
		solicitante.setDni(resultSet.getString("DNICONTACTO"));
		solicitante.setNombre(resultSet.getString("NOMBRE"));
		solicitante.setApellido1(resultSet.getString("APEL1"));
		solicitante.setApellido2(resultSet.getString("APEL2"));
		CentroOrganico centroOrganico = new CentroOrganico();
		if (Constants.LANG_EUSKERA.equals(locale.getLanguage())) {
			centroOrganico.setDescAmp(resultSet.getString(DBConstants.CENTRO_ORGANICO_EU));
		} else {
			centroOrganico.setDescAmp(resultSet.getString(DBConstants.CENTRO_ORGANICO_ES));
		}
		solicitante.setCentroOrganico(centroOrganico);
		contactoFacturacionExpediente.setContacto(solicitante);
		consultaFacturacionExpediente.setContactoFacturacionExpediente(contactoFacturacionExpediente);
		consultaFacturacionExpediente.setExpedientesCount(resultSet.getInt("COUNTEXPEDIENTES"));
		String dniVinculado = resultSet.getString("DNIVINCULADOOBAJA");
		consultaFacturacionExpediente.setContactoVinculado(StringUtils.isNotBlank(dniVinculado));
		return consultaFacturacionExpediente;
	}

}
