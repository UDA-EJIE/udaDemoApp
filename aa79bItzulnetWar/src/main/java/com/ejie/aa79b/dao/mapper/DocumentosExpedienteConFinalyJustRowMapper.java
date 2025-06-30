package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.Contacto;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.FicheroDocExp;

/**
 * @author javarona
 *
 */
public class DocumentosExpedienteConFinalyJustRowMapper implements RowMapper<DocumentosExpediente> {

	@Override()
	public DocumentosExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		List<FicheroDocExp> listaFicheros = new ArrayList<FicheroDocExp>();

		// Primer doc
		DocumentosExpediente documentosExpediente = new DocumentosExpediente();
		documentosExpediente.setIdDoc(resultSet.getBigDecimal("IDDOC"));
		documentosExpediente.setAnyo(resultSet.getLong(DBConstants.ANYO));
		documentosExpediente.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
		documentosExpediente.setIsBopv(resultSet.getString("ISBOPV"));

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
		fichero.setNombreDniAlta(resultSet.getString(DBConstants.NOMBREDOC1));
		fichero.setApel1DniAlta(resultSet.getString(DBConstants.APEL1DOC1));
		fichero.setApel2DniAlta(resultSet.getString(DBConstants.APEL2DOC1));
		fichero.setDniAlta(resultSet.getString("DNIALTA"));
		fichero.setIdDocVersion(resultSet.getBigDecimal("IDDOCVERSION"));
		fichero.setIdDocRel(resultSet.getBigDecimal("IDDOCREL"));
		fichero.setContentType(resultSet.getString("CONTENTTYPE"));
		fichero.setIdDocInsertado(resultSet.getBigDecimal("IDDOC"));
		fichero.setNombre(resultSet.getString("NOMBREFICHERO"));
		fichero.setRutaPif(resultSet.getString("RUTAPIFFICHERO"));

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
			fichero.setNombreDniAlta(resultSet.getString(DBConstants.NOMBREDOC2));
			fichero.setApel1DniAlta(resultSet.getString(DBConstants.APEL1DOC2));
			fichero.setApel2DniAlta(resultSet.getString(DBConstants.APEL2DOC2));
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

		// DOCUMENTO FINAL

		if (resultSet.getBigDecimal("ID_FICHERO_FINAL") != null
				&& Constants.SI.contentEquals(resultSet.getString("IND_VISIBLE_FINAL"))) {
			fichero = new FicheroDocExp();
			fichero.setOid(resultSet.getString("OID_FICHERO_FINAL"));
			fichero.setExtension(resultSet.getString("EXTENSION_FICHERO_FINAL"));
			fichero.setTamano(resultSet.getLong("TAMANO_FICHERO_FINAL"));
			fichero.setEncriptado(resultSet.getString("IND_ENCRIPTADO_FINAL"));
			// fichero.setContentType(resultSet.getString("CONTENTTYPE2"))
			fichero.setIdDocInsertado(resultSet.getBigDecimal("ID_FICHERO_FINAL"));
			fichero.setNombre(resultSet.getString("NOMBRE_FICHERO_FINAL"));
			fichero.setFechaAlta(resultSet.getTimestamp(DBConstants.FECHAALTAFICHEROFINAL));
			documentosExpediente.setDocumentoFinal(fichero);

			fichero = new FicheroDocExp();
			fichero.setOid(resultSet.getString("OID_FICHERO_FINAL_ORIG"));
			fichero.setExtension(resultSet.getString("EXTENSION_FICHERO_FINAL_ORIG"));
			fichero.setTamano(resultSet.getLong("TAMANO_FICHERO_FINAL_ORIG"));
			fichero.setEncriptado(resultSet.getString("IND_ENCRIPTADO_FINAL_ORIG"));
			// fichero.setContentType(resultSet.getString("CONTENTTYPE2"))
			fichero.setIdDocInsertado(resultSet.getBigDecimal("ID_DOC_FINAL_ORIG"));
			fichero.setNombre(resultSet.getString("TITULO_FICHERO_FINAL_ORIG"));
			documentosExpediente.setDocumentoOriginalFinal(fichero);
			// Justificante (solo en revisión). Solo visible si el doc final es
			// visible
			if (resultSet.getBigDecimal("ID_FICHERO_JUSTIFICANTE") != null
					&& Constants.SI.contentEquals(resultSet.getString("IND_VISIBLE_JUSTIFICANTE"))) {
				fichero = new FicheroDocExp();
				fichero.setOid(resultSet.getString("OID_FICHERO_JUSTIFICANTE"));
				fichero.setExtension(resultSet.getString("EXTENSION_FICHERO_JUSTIFICANTE"));
				fichero.setTamano(resultSet.getLong("TAMANO_FICHERO_JUSTIFICANTE"));
				fichero.setEncriptado(resultSet.getString("IND_ENCRIPTADO_JUSTIFICANTE"));
				// fichero.setContentType(resultSet.getString("CONTENTTYPE2"))
				fichero.setIdDocInsertado(resultSet.getBigDecimal("ID_FICHERO_JUSTIFICANTE"));
				fichero.setNombre(resultSet.getString("TITULO_FICHERO_JUSTIFICANTE"));
				fichero.setFechaAlta(resultSet.getTimestamp(DBConstants.FECHAALTAJUSTIFICANTE));
				documentosExpediente.setJustificanteRevision(fichero);
			}
			// Ficheros original y final firmados (oids)
			if (resultSet.getBigDecimal("ID_DOC_ORIG_FIRMADO") != null) {
				fichero = new FicheroDocExp();
				fichero.setIdDocInsertado(resultSet.getBigDecimal("ID_DOC_ORIG_FIRMADO"));
				documentosExpediente.setDocumentoOriginalFirmado(fichero);
			}
			if (resultSet.getBigDecimal("ID_DOC_FINAL_FIRMADO") != null) {
				fichero = new FicheroDocExp();
				fichero.setIdDocInsertado(resultSet.getBigDecimal("ID_DOC_FINAL_FIRMADO"));
				documentosExpediente.setDocumentoFinalFirmado(fichero);
			}
		}
		documentosExpediente.setTituloDifferentFromFileName();
		return documentosExpediente;
	}

}
