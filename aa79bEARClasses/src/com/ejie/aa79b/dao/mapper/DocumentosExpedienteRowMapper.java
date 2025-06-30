package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.Contacto;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.FicheroDocExp;

/**
 * @author javarona
 *
 */
public class DocumentosExpedienteRowMapper implements RowMapper<DocumentosExpediente> {

	@Override()
	public DocumentosExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		List<FicheroDocExp> listaFicheros = new ArrayList<FicheroDocExp>();

		// Primer doc
		DocumentosExpediente documentosExpediente = new DocumentosExpediente();
		documentosExpediente.setIdDoc(resultSet.getBigDecimal("IDDOC"));
		documentosExpediente.setAnyo(resultSet.getLong(DBConstants.ANYO));
		documentosExpediente.setNumExp(resultSet.getInt(DBConstants.NUMEXP));

		Contacto contacto = new Contacto();
		contacto.setPersona(resultSet.getString("PERSONACONTACTO"));
		contacto.setEmail(resultSet.getString("EMAILCONTACTO"));
		contacto.setTelefono(resultSet.getString("TELEFONOCONTACTO"));
		contacto.setDireccion(resultSet.getString("DIRECCIONCONTACTO"));
		contacto.setEntidad(resultSet.getString("ENTIDADCONTACTO"));

		FicheroDocExp fichero = new FicheroDocExp();
		fichero.setOid(resultSet.getString(DBConstants.OIDFICHERO));
		fichero.setExtension(resultSet.getString("EXTENSIONDOC"));
		fichero.setTamano(resultSet.getLong("TAMANODOC"));
		fichero.setEncriptado(resultSet.getString("INDENCRIPTADO"));
		fichero.setFechaAlta(resultSet.getTimestamp(DBConstants.FECHAALTA));
		fichero.setDniAlta(resultSet.getString("DNIALTA"));
		fichero.setIdDocVersion(resultSet.getBigDecimal("IDDOCVERSION"));
		fichero.setIdDocRel(resultSet.getBigDecimal("IDDOCREL"));
		fichero.setContentType(resultSet.getString("CONTENTTYPE"));
		fichero.setIdDocInsertado(resultSet.getBigDecimal("IDDOC"));
		fichero.setNombre(resultSet.getString("NOMBREFICHERO"));
		fichero.setRutaPif(resultSet.getString("RUTAPIFFICHERO"));
		fichero.setTitulo(resultSet.getString("TITULO"));
		fichero.setOrigen(resultSet.getString("ORIGEN"));

		fichero.setNombreDniAlta(resultSet.getString(DBConstants.NOMBREDOC1));
		fichero.setApel1DniAlta(resultSet.getString(DBConstants.APEL1DOC1));
		fichero.setApel2DniAlta(resultSet.getString(DBConstants.APEL2DOC1));

		fichero.setContacto(contacto);
		listaFicheros.add(fichero);

		// Segundo doc

		if (resultSet.getString("OIDFICHERO2") != null || resultSet.getString("RUTAPIFFICHERO2") != null) {
			contacto = new Contacto();
			contacto.setPersona(resultSet.getString("PERSONACONTACTO2"));
			contacto.setEmail(resultSet.getString("EMAILCONTACTO2"));
			contacto.setTelefono(resultSet.getString("TELEFONOCONTACTO2"));
			contacto.setDireccion(resultSet.getString("DIRECCIONCONTACTO2"));
			contacto.setEntidad(resultSet.getString("ENTIDADCONTACTO2"));

			fichero = new FicheroDocExp();
			fichero.setOid(resultSet.getString("OIDFICHERO2"));
			fichero.setExtension(resultSet.getString("EXTENSIONDOC2"));
			fichero.setTamano(resultSet.getLong("TAMANODOC2"));
			fichero.setEncriptado(resultSet.getString("INDENCRIPTADO2"));
			fichero.setFechaAlta(resultSet.getTimestamp("FECHAALTA2"));
			fichero.setDniAlta(resultSet.getString("DNIALTA2"));
			fichero.setIdDocVersion(resultSet.getBigDecimal("IDDOCVERSION2"));
			fichero.setIdDocRel(resultSet.getBigDecimal("IDDOCREL2"));
			fichero.setContentType(resultSet.getString("CONTENTTYPE2"));
			fichero.setIdDocInsertado(resultSet.getBigDecimal("IDDOC2"));
			fichero.setNombre(resultSet.getString("NOMBREFICHERO2"));
			fichero.setRutaPif(resultSet.getString("RUTAPIFFICHERO2"));

			fichero.setContacto(contacto);
			listaFicheros.add(fichero);
		}

		documentosExpediente.setFicheros(listaFicheros);

		documentosExpediente.setClaseDocumento(resultSet.getLong("CLASEDOCUMENTO"));
		documentosExpediente.setTipoDocumento(resultSet.getLong("TIPODOCUMENTO"));
		documentosExpediente.setTitulo(resultSet.getString(DBConstants.TITULO));
		documentosExpediente.setNumPalSolic(resultSet.getInt("NUMPALSOLIC"));
		documentosExpediente.setNumPalIzo(resultSet.getInt("NUMPALIZO"));
		documentosExpediente.setIndVisible(resultSet.getString("INDVISIBLE"));

		documentosExpediente.setClaseDocumentoDesc(resultSet.getString("CLASEDOCUMENTODESC"));
		documentosExpediente.setTipoDocumentoDesc(resultSet.getString("TIPODOCUMENTODESC"));
		documentosExpediente.setClaseDocumentoDescEs(resultSet.getString("CLASEDOCUMENTODESCES"));
		documentosExpediente.setTipoDocumentoDescEs(resultSet.getString("TIPODOCUMENTODESCES"));
		documentosExpediente.setClaseDocumentoDescEu(resultSet.getString("CLASEDOCUMENTODESCEU"));
		documentosExpediente.setTipoDocumentoDescEu(resultSet.getString("TIPODOCUMENTODESCEU"));

		documentosExpediente.setNumPalConcor084(resultSet.getInt("NUMPALCONCOR084"));
		documentosExpediente.setNumPalConcor8594(resultSet.getInt("NUMPALCONCOR8594"));
		documentosExpediente.setNumPalConcor95100(resultSet.getInt("NUMPALCONCOR95100"));
		documentosExpediente.setNumPalConcor9599(resultSet.getInt("NUMPALCONCOR9599"));
		documentosExpediente.setNumPalConcor100(resultSet.getInt("NUMPALCONCOR100"));

		return documentosExpediente;
	}

}
