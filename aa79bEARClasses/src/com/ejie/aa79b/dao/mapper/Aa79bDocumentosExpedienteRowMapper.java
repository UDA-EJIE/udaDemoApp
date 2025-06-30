package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.webservices.Aa79bContactoDoc;
import com.ejie.aa79b.model.webservices.Aa79bDocumentoExpediente;
import com.ejie.aa79b.model.webservices.Aa79bFicheroDocExp;

/**
 * @author mrodriguez
 *
 */
public class Aa79bDocumentosExpedienteRowMapper implements RowMapper<Aa79bDocumentoExpediente> {

	@Override()
	public Aa79bDocumentoExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		List<Aa79bFicheroDocExp> listaFicheros = new ArrayList<Aa79bFicheroDocExp>();

		// Primer doc
		Aa79bDocumentoExpediente documentosExpediente = new Aa79bDocumentoExpediente();
		documentosExpediente.setIdDoc(resultSet.getBigDecimal("IDDOC"));
		documentosExpediente.setAnyo(resultSet.getLong(DBConstants.ANYO));
		documentosExpediente.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
		documentosExpediente.setIsBopv(resultSet.getString("ISBOPV"));
		documentosExpediente.setEstado(Constants.CONSULTA);

		Aa79bContactoDoc contacto = new Aa79bContactoDoc();
		contacto.setPersona(resultSet.getString("PERSONACONTACTO"));
		contacto.setEmail(resultSet.getString("EMAILCONTACTO"));
		contacto.setTelefono(resultSet.getString("TELEFONOCONTACTO"));
		contacto.setDireccion(resultSet.getString("DIRECCIONCONTACTO"));
		contacto.setEntidad(resultSet.getString("ENTIDADCONTACTO"));

		Aa79bFicheroDocExp fichero = new Aa79bFicheroDocExp();
		fichero.setOid(resultSet.getString(DBConstants.OIDFICHERO));
		fichero.setRutaPif(resultSet.getString("RUTAPIFFICHERO"));
		fichero.setExtension(resultSet.getString("EXTENSIONDOC"));
		fichero.setTamano(resultSet.getLong("TAMANODOC"));
		fichero.setEncriptado(resultSet.getString("INDENCRIPTADO"));
		Date fechaAlta = resultSet.getTimestamp(DBConstants.FECHAALTA);
		if (fechaAlta != null) {
			fichero.setFechaAlta(fechaAlta.getTime());
			fichero.setHoraAlta(resultSet.getString(DBConstants.HORAALTA));
		}
		fichero.setNombreDniAlta(resultSet.getString(DBConstants.NOMBREDOC1));
		fichero.setApel1DniAlta(resultSet.getString(DBConstants.APEL1DOC1));
		fichero.setApel2DniAlta(resultSet.getString(DBConstants.APEL2DOC1));
		fichero.setIdDocRel(resultSet.getBigDecimal("IDDOCREL"));
		fichero.setContentType(resultSet.getString("CONTENTTYPE"));
		fichero.setIdDocInsertado(resultSet.getBigDecimal("IDDOC"));
		fichero.setNombre(resultSet.getString("NOMBREFICHERO"));
		fichero.setOrigen(resultSet.getString("ORIGEN"));

		fichero.setContacto(contacto);
		listaFicheros.add(fichero);

		// //Segundo doc

		if (resultSet.getString("OIDFICHERO2") != null) {
			contacto = new Aa79bContactoDoc();
			contacto.setPersona(resultSet.getString("PERSONACONTACTO2"));
			contacto.setEmail(resultSet.getString("EMAILCONTACTO2"));
			contacto.setTelefono(resultSet.getString("TELEFONOCONTACTO2"));
			contacto.setDireccion(resultSet.getString("DIRECCIONCONTACTO2"));
			contacto.setEntidad(resultSet.getString("ENTIDADCONTACTO2"));

			fichero = new Aa79bFicheroDocExp();
			fichero.setOid(resultSet.getString("OIDFICHERO2"));
			fichero.setRutaPif(resultSet.getString("RUTAPIFFICHERO2"));
			fichero.setExtension(resultSet.getString("EXTENSIONDOC2"));
			fichero.setTamano(resultSet.getLong("TAMANODOC2"));
			fichero.setEncriptado(resultSet.getString("INDENCRIPTADO2"));
			Date fechaAlta2 = resultSet.getTimestamp("FECHAALTA2");
			if (fechaAlta2 != null) {
				fichero.setFechaAlta(fechaAlta2.getTime());
				fichero.setHoraAlta(resultSet.getString(DBConstants.HORAALTA2));
			}
			fichero.setNombreDniAlta(resultSet.getString(DBConstants.NOMBREDOC2));
			fichero.setApel1DniAlta(resultSet.getString(DBConstants.APEL1DOC2));
			fichero.setApel2DniAlta(resultSet.getString(DBConstants.APEL2DOC2));
			fichero.setIdDocRel(resultSet.getBigDecimal("IDDOCREL2"));
			fichero.setContentType(resultSet.getString("CONTENTTYPE2"));
			fichero.setIdDocInsertado(resultSet.getBigDecimal("IDDOC2"));
			fichero.setNombre(resultSet.getString("NOMBREFICHERO2"));
			fichero.setOrigen(resultSet.getString("ORIGEN2"));

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

		// DOCUMENTO FINAL

		if (resultSet.getBigDecimal("ID_FICHERO_FINAL") != null
				&& Constants.SI.contentEquals(resultSet.getString("IND_VISIBLE_FINAL"))) {
			alimentaFichFinal(resultSet, documentosExpediente);

		}

		return documentosExpediente;
	}

	/**
	 * @param resultSet
	 * @param documentosExpediente
	 * @throws SQLException
	 */
	private void alimentaFichFinal(ResultSet resultSet, Aa79bDocumentoExpediente documentosExpediente)
			throws SQLException {
		Aa79bFicheroDocExp fichero;
		fichero = new Aa79bFicheroDocExp();
		fichero.setOid(resultSet.getString("OID_FICHERO_FINAL"));
		fichero.setExtension(resultSet.getString("EXTENSION_FICHERO_FINAL"));
		fichero.setTamano(resultSet.getLong("TAMANO_FICHERO_FINAL"));
		fichero.setEncriptado(resultSet.getString("IND_ENCRIPTADO_FINAL"));
		fichero.setContentType(resultSet.getString("CONTENTTYPE_FINAL"));
		fichero.setIdDocInsertado(resultSet.getBigDecimal("ID_FICHERO_FINAL"));
		fichero.setTitulo(resultSet.getString("TITULO_FICHERO_FINAL"));
		fichero.setNombre(resultSet.getString("NOMBRE_FICHERO_FINAL"));
		Date fechaAltaFicheroFinal = resultSet.getTimestamp(DBConstants.FECHAALTAFICHEROFINAL);
		if (fechaAltaFicheroFinal != null) {
			fichero.setFechaAlta(fechaAltaFicheroFinal.getTime());
			fichero.setHoraAlta(resultSet.getString(DBConstants.HORAALTAFICHEROFINAL));
		}
		documentosExpediente.setDocumentoFinal(fichero);

		fichero = new Aa79bFicheroDocExp();
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
			fichero = new Aa79bFicheroDocExp();
			fichero.setOid(resultSet.getString("OID_FICHERO_JUSTIFICANTE"));
			fichero.setExtension(resultSet.getString("EXTENSION_FICHERO_JUSTIFICANTE"));
			fichero.setTamano(resultSet.getLong("TAMANO_FICHERO_JUSTIFICANTE"));
			fichero.setEncriptado(resultSet.getString("IND_ENCRIPTADO_JUSTIFICANTE"));
			fichero.setContentType(resultSet.getString("CONTENTTYPE_JUSTIFICANTE"));
			fichero.setIdDocInsertado(resultSet.getBigDecimal("ID_FICHERO_JUSTIFICANTE"));
			fichero.setTitulo(resultSet.getString("TITULO_FICHERO_JUSTIFICANTE"));
			fichero.setNombre(resultSet.getString("NOMBRE_FICHERO_JUSTIFICANTE"));
			Date fechaAltaFicheroJustif = resultSet.getTimestamp(DBConstants.FECHAALTAJUSTIFICANTE);
			if (fechaAltaFicheroJustif != null) {
				fichero.setFechaAlta(fechaAltaFicheroJustif.getTime());
				fichero.setHoraAlta(resultSet.getString(DBConstants.HORAALTAJUSTIFICANTE));
			}
			documentosExpediente.setJustificanteRevision(fichero);
		}

		// Ficheros original y final firmados (oids)
		if (resultSet.getBigDecimal("ID_DOC_ORIG_FIRMADO") != null) {
			fichero = new Aa79bFicheroDocExp();
			fichero.setIdDocInsertado(resultSet.getBigDecimal("ID_DOC_ORIG_FIRMADO"));
			documentosExpediente.setDocumentoOriginalFirmado(fichero);
		}
		if (resultSet.getBigDecimal("ID_DOC_FINAL_FIRMADO") != null) {
			fichero = new Aa79bFicheroDocExp();
			fichero.setIdDocInsertado(resultSet.getBigDecimal("ID_DOC_FINAL_FIRMADO"));
			documentosExpediente.setDocumentoFinalFirmado(fichero);
		}
	}

}
