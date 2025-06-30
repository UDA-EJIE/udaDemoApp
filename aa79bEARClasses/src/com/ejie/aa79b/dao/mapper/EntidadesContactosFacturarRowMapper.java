package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.Calle;
import com.ejie.aa79b.model.CentroOrganico;
import com.ejie.aa79b.model.ContactoFacturacionExpediente;
import com.ejie.aa79b.model.DatosFacturacionExpediente;
import com.ejie.aa79b.model.DireccionNora;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.IvaPrecios;
import com.ejie.aa79b.model.Localidad;
import com.ejie.aa79b.model.Municipio;
import com.ejie.aa79b.model.OrdenPrecios;
import com.ejie.aa79b.model.Pais;
import com.ejie.aa79b.model.Portal;
import com.ejie.aa79b.model.Provincia;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.utils.Utils;

public class EntidadesContactosFacturarRowMapper implements RowMapper<ContactoFacturacionExpediente> {
	@Override()
	public ContactoFacturacionExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Locale locale = LocaleContextHolder.getLocale();
		ContactoFacturacionExpediente contactoFacturacionExpedienteAux = new ContactoFacturacionExpediente(
				resultSet.getLong("ID058"), resultSet.getLong(DBConstants.ANYO), resultSet.getInt(DBConstants.NUMEXP),
				resultSet.getString("TIPOENTIDADASOC058"), resultSet.getInt("IDENTIDADASOC058"),
				resultSet.getString("DNICONTACTO058"), resultSet.getLong("PORFACTURA058"));
		Entidad entidadSolicitante = new Entidad();
		entidadSolicitante.setCodigo(resultSet.getInt("IDENTIDADASOC058"));
		entidadSolicitante.setTipo(resultSet.getString("TIPOENTIDADASOC058"));
		entidadSolicitante.setDescAmpEu(resultSet.getString("ENTIDADDESCAMP"));
		entidadSolicitante.setFacturable(resultSet.getString(DBConstants.FACTURABLE));
		entidadSolicitante.setIva(resultSet.getString(DBConstants.IVA));
		entidadSolicitante.setFacturableDesc(resultSet.getString("FACTURABLEDESC"));
		entidadSolicitante.setIvaDesc(resultSet.getString("IVADESC"));

		DireccionNora direccion = new DireccionNora();
		direccion.setDirNora(resultSet.getInt("CDIRNORA"));
		direccion.setTipoNora(resultSet.getString("TIPONORA"));
		Pais pais = new Pais();
		pais.setId(resultSet.getString("CODPAIS"));
		pais.setDsO(resultSet.getString("PAIS"));
		direccion.setPais(pais);
		Provincia provincia = new Provincia();
		provincia.setId(resultSet.getString("CODPROV"));
		provincia.setDsO(resultSet.getString("PROVINCIA"));
		direccion.setProvincia(provincia);
		Municipio municipio = new Municipio();
		municipio.setId(resultSet.getString("CODMUNI"));
		municipio.setDsO(resultSet.getString("MUNICIPIO"));
		direccion.setMunicipio(municipio);
		Localidad localidad = new Localidad();
		localidad.setId(resultSet.getLong("CODLOCAL"));
		localidad.setDsO(resultSet.getString("LOCALIDAD"));
		direccion.setLocalidad(localidad);
		Calle calle = new Calle();
		calle.setId(resultSet.getLong("CODCALLE"));
		calle.setDsO(resultSet.getString("CALLE"));
		direccion.setCalle(calle);
		Portal portal = new Portal();
		portal.setId(resultSet.getLong("CODPORTAL"));
		portal.setTxtPortal(resultSet.getString("PORTAL"));
		direccion.setPortal(portal);
		direccion.setCodPostal(resultSet.getString("CODPOSTAL"));
		direccion.setEscalera(resultSet.getString("ESCALERA"));
		direccion.setPiso(resultSet.getString("PISO"));
		direccion.setMano(resultSet.getString("MANO"));
		direccion.setPuerta(resultSet.getString("PUERTA"));
		direccion.setAprox(resultSet.getString("APROX"));
		direccion.setDireccion(Utils.obtenerDireccionStr(direccion));
		entidadSolicitante.setDireccion(direccion);

		contactoFacturacionExpedienteAux.setEntidadSolicitante(entidadSolicitante);

		Solicitante contacto = new Solicitante();
		contacto.setApellido1(resultSet.getString("CONTACTOAPEL1"));
		contacto.setApellido2(resultSet.getString("CONTACTOAPEL2"));
		contacto.setNombre(resultSet.getString("CONTACTONOMBRE"));
		contacto.setDni(resultSet.getString("DNICONTACTO058"));
		CentroOrganico centroOrganico = new CentroOrganico();
        if (Constants.LANG_EUSKERA.equals(locale.getLanguage())) {
            centroOrganico.setDescAmp(resultSet.getString("CENTROORGANICOEU"));
        } else {
            centroOrganico.setDescAmp(resultSet.getString("CENTROORGANICOES"));
        }
        contacto.setCentroOrganico(centroOrganico);
		contactoFacturacionExpedienteAux.setContacto(contacto);

		String dniVinculado = resultSet.getString("DNIVINCULADOOBAJA");
		contactoFacturacionExpedienteAux.setContactoVinculado(StringUtils.isNotBlank(dniVinculado));

		DatosFacturacionExpediente datosFacturacionExpediente = new DatosFacturacionExpediente();
		datosFacturacionExpediente.setNumTotalPalFacturar(resultSet.getBigDecimal("NUMTOTALPALFACTURAR"));
		datosFacturacionExpediente.setNumPalConcor084(resultSet.getBigDecimal("NUMPALCONCOR084"));
		datosFacturacionExpediente.setNumPalConcor8594(resultSet.getBigDecimal("NUMPALCONCOR8594"));
		datosFacturacionExpediente.setNumPalConcor95100(resultSet.getBigDecimal("NUMPALCONCOR95100"));
		datosFacturacionExpediente.setImporteUrgencia(resultSet.getBigDecimal("IMPORTEURGENCIA"));
		datosFacturacionExpediente.setImporteDificultad(resultSet.getBigDecimal("IMPORTEDIFICULTAD"));
		datosFacturacionExpediente.setImporteBase(resultSet.getBigDecimal("IMPORTEBASE"));
		datosFacturacionExpediente.setImporteIva(resultSet.getBigDecimal("IMPORTEIVA"));
		datosFacturacionExpediente.setImporteTotal(resultSet.getBigDecimal("IMPORTETOTAL"));
		contactoFacturacionExpedienteAux.setDatosFacturacionExpediente(datosFacturacionExpediente);

		OrdenPrecios ordenPrecios = new OrdenPrecios();
		IvaPrecios ivaVigente = new IvaPrecios();
		ivaVigente.setPorIva(resultSet.getLong("PORIVA"));
		ordenPrecios.setIvaVigente(ivaVigente);
		contactoFacturacionExpedienteAux.setOrdenPrecios(ordenPrecios);

		return contactoFacturacionExpedienteAux;
	}

}
