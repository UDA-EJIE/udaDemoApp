/**
 * 
 */
package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.Calle;
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

/**
 * @author eaguirresarobe
 *
 */
public class EntidadesContactosFacturarInterRowMapper implements RowMapper<ContactoFacturacionExpediente> {

	@Override
	public ContactoFacturacionExpediente mapRow(ResultSet rs, int arg1) throws SQLException {
		ContactoFacturacionExpediente contactoFacturacionExpedienteAux = new ContactoFacturacionExpediente(
				rs.getLong("ID058"), rs.getLong(DBConstants.ANYO), rs.getInt(DBConstants.NUMEXP),
				rs.getString("TIPOENTIDADASOC058"), rs.getInt("IDENTIDADASOC058"), rs.getString("DNICONTACTO058"),
				rs.getLong("PORFACTURA058"));
		Entidad entidadSolicitante = new Entidad();
		entidadSolicitante.setCodigo(rs.getInt("IDENTIDADASOC058"));
		entidadSolicitante.setTipo(rs.getString("TIPOENTIDADASOC058"));
		entidadSolicitante.setDescAmpEu(rs.getString("ENTIDADDESCAMP"));
		entidadSolicitante.setFacturable(rs.getString(DBConstants.FACTURABLE));
		entidadSolicitante.setIva(rs.getString(DBConstants.IVA));
		entidadSolicitante.setFacturableDesc(rs.getString("FACTURABLEDESC"));
		entidadSolicitante.setIvaDesc(rs.getString("IVADESC"));

		DireccionNora direccion = new DireccionNora();
		direccion.setDirNora(rs.getInt("CDIRNORA"));
		direccion.setTipoNora(rs.getString("TIPONORA"));
		Pais pais = new Pais();
		pais.setId(rs.getString("CODPAIS"));
		pais.setDsO(rs.getString("PAIS"));
		direccion.setPais(pais);
		Provincia provincia = new Provincia();
		provincia.setId(rs.getString("CODPROV"));
		provincia.setDsO(rs.getString("PROVINCIA"));
		direccion.setProvincia(provincia);
		Municipio municipio = new Municipio();
		municipio.setId(rs.getString("CODMUNI"));
		municipio.setDsO(rs.getString("MUNICIPIO"));
		direccion.setMunicipio(municipio);
		Localidad localidad = new Localidad();
		localidad.setId(rs.getLong("CODLOCAL"));
		localidad.setDsO(rs.getString("LOCALIDAD"));
		direccion.setLocalidad(localidad);
		Calle calle = new Calle();
		calle.setId(rs.getLong("CODCALLE"));
		calle.setDsO(rs.getString("CALLE"));
		direccion.setCalle(calle);
		Portal portal = new Portal();
		portal.setId(rs.getLong("CODPORTAL"));
		portal.setTxtPortal(rs.getString("PORTAL"));
		direccion.setPortal(portal);
		direccion.setCodPostal(rs.getString("CODPOSTAL"));
		direccion.setEscalera(rs.getString("ESCALERA"));
		direccion.setPiso(rs.getString("PISO"));
		direccion.setMano(rs.getString("MANO"));
		direccion.setPuerta(rs.getString("PUERTA"));
		direccion.setAprox(rs.getString("APROX"));
		direccion.setDireccion(Utils.obtenerDireccionStr(direccion));
		entidadSolicitante.setDireccion(direccion);

		contactoFacturacionExpedienteAux.setEntidadSolicitante(entidadSolicitante);

		Solicitante contacto = new Solicitante();
		contacto.setApellido1(rs.getString("CONTACTOAPEL1"));
		contacto.setApellido2(rs.getString("CONTACTOAPEL2"));
		contacto.setNombre(rs.getString("CONTACTONOMBRE"));
		contacto.setDni(rs.getString("DNICONTACTO058"));
		contactoFacturacionExpedienteAux.setContacto(contacto);

		String dniVinculado = rs.getString("DNIVINCULADOOBAJA");
		contactoFacturacionExpedienteAux.setContactoVinculado(StringUtils.isNotBlank(dniVinculado));

		DatosFacturacionExpediente datosFacturacionExpediente = new DatosFacturacionExpediente();
		datosFacturacionExpediente.setImporteBase(rs.getBigDecimal("IMPORTEBASE"));
		datosFacturacionExpediente.setImporteIva(rs.getBigDecimal("IMPORTEIVA"));
		datosFacturacionExpediente.setImporteTotal(rs.getBigDecimal("IMPORTETOTAL"));
		contactoFacturacionExpedienteAux.setDatosFacturacionExpediente(datosFacturacionExpediente);

		OrdenPrecios ordenPrecios = new OrdenPrecios();
		IvaPrecios ivaVigente = new IvaPrecios();
		ivaVigente.setPorIva(rs.getLong("PORIVA"));
		ordenPrecios.setIvaVigente(ivaVigente);
		contactoFacturacionExpedienteAux.setOrdenPrecios(ordenPrecios);

		return contactoFacturacionExpedienteAux;
	}

}
