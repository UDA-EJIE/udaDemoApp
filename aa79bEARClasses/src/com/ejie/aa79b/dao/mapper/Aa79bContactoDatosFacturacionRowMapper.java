package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.DireccionNora;
import com.ejie.aa79b.model.webservices.Aa79bContacto;
import com.ejie.aa79b.model.webservices.Aa79bDescripcionIdioma;
import com.ejie.aa79b.model.webservices.Aa79bDireccionNora;
import com.ejie.aa79b.model.webservices.Aa79bEntidad;
import com.ejie.aa79b.model.webservices.Aa79bEntidadContacto;

/**
 * 
 * @author eaguirresarobe
 *
 */
public class Aa79bContactoDatosFacturacionRowMapper implements RowMapper<Aa79bEntidadContacto> {

	@Override
	public Aa79bEntidadContacto mapRow(ResultSet resultSet, int arg1) throws SQLException {
		Aa79bEntidadContacto entidadContacto = new Aa79bEntidadContacto();
		/* ID058 */
		entidadContacto.setId(resultSet.getInt(DBConstants.ID_058));
		/* ANYO */
		entidadContacto.setAnyo(resultSet.getLong(DBConstants.ANYO));
		/* NUMEXP */
		entidadContacto.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
		/* - ENTIDAD - INICIO */
		/* TIPOENTIDADASOCIADA */
		Aa79bEntidad entidad = new Aa79bEntidad();
		entidad.setTipo(resultSet.getString(DBConstants.TIPOENTIDADASOCIADA));
		/* IDENTIDADASOCIADA */
		entidad.setCodigo(resultSet.getInt(DBConstants.IDENTIDADASOCIADA));
		/* ENTIDADDESCAMPES */
		entidad.setEntidadDescAmpEs(resultSet.getString(DBConstants.DESCENTIDADES));
		/* ENTIDADDESCAMPEU */
		entidad.setEntidadDescAmpEu(resultSet.getString(DBConstants.DESCENTIDADEU));
		/* CDIRNORA */
		Aa79bDireccionNora entidadDireccionNora = new Aa79bDireccionNora();
		DireccionNora dirNoraEntidad = new DireccionNora();
		dirNoraEntidad.setDirNora(resultSet.getInt(DBConstants.CDIRNORA));
		entidadDireccionNora.setDireccion(dirNoraEntidad);
		entidad.setDirNoraEntidad(entidadDireccionNora);
		entidadContacto.setEntidad(entidad);
		/* - ENTIDAD - FIN */
		/* - CONTACTO - INICIO */
		/* DNICONTACTO */
		Aa79bContacto contacto = new Aa79bContacto();
		contacto.setDni(resultSet.getString(DBConstants.DNICONTACTO));
		/* NOMBRE */
		contacto.setNombre(resultSet.getString(DBConstants.NOMBRE));
		/* APEL1 */
		contacto.setApellido1(resultSet.getString(DBConstants.APEL1));
		/* APEL2 */
		contacto.setApellido2(resultSet.getString(DBConstants.APEL2));
		entidadContacto.setContacto(contacto);
		/* - CONTACTO - FIN */
		/* FACTURABLE */
		Aa79bDescripcionIdioma facturable = new Aa79bDescripcionIdioma();
		facturable.setCodigo(resultSet.getString(DBConstants.FACTURABLE));
		/* FACTURABLEDESCES */
		facturable.setDescEs(resultSet.getString(DBConstants.FACTURABLEDESCES));
		/* FACTURABLEDESCEU */
		facturable.setDescEu(resultSet.getString(DBConstants.FACTURABLEDESCEU));
		entidadContacto.setFacturable(facturable);
		/* IVA */
		Aa79bDescripcionIdioma iva = new Aa79bDescripcionIdioma();
		iva.setCodigo(resultSet.getString(DBConstants.IVA));
		/* IVADESCES */
		iva.setDescEs(resultSet.getString(DBConstants.IVADESCES));
		/* IVADESCEU */
		iva.setDescEu(resultSet.getString(DBConstants.IVADESCEU));
		entidadContacto.setIva(iva);
		/* NUMFACTURA */
		entidadContacto.setNumFactura(resultSet.getBigDecimal(DBConstants.NUMFACTURA));
		/* NUMFACTURA */
		entidadContacto.setCodConcatenado(resultSet.getBigDecimal("CODCONCATENADO"));
		/* FACTURADODESCES */
		Aa79bDescripcionIdioma facturado = new Aa79bDescripcionIdioma();
		facturado.setDescEs(resultSet.getString(DBConstants.FACTURADODESCES));
		/* FACTURADODESCEU */
		facturado.setDescEu(resultSet.getString(DBConstants.FACTURADODESCEU));
		entidadContacto.setFacturado(facturado);
		/* PAGADODESCES */
		Aa79bDescripcionIdioma pagado = new Aa79bDescripcionIdioma();
		pagado.setDescEs(resultSet.getString(DBConstants.PAGADODESCES));
		/* PAGADODESCEU */
		pagado.setDescEu(resultSet.getString(DBConstants.PAGADODESCEU));
		entidadContacto.setPagado(pagado);
		return entidadContacto;
	}

}
