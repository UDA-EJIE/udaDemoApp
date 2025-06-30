package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.DatosFacturacionCliente;
import com.ejie.aa79b.model.DatosFacturacionExpediente;
import com.ejie.aa79b.model.DatosFacturacionExpedienteInterpretacion;
import com.ejie.aa79b.model.DatosTareaTrados;
import com.ejie.aa79b.model.DireccionNora;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.ExpedienteFacturacion;
import com.ejie.aa79b.model.ExpedienteInterpretacion;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.Gestor;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoPeticionarioEnum;
import com.ejie.aa79b.utils.GeneralUtils;

public class DatosCalculoExpedienteFacturacionRowMapper implements RowMapper<ExpedienteFacturacion> {

	@Override
	public ExpedienteFacturacion mapRow(ResultSet resultSet, int arg1) throws SQLException {
		ExpedienteFacturacion expedienteFacturacion = new ExpedienteFacturacion();
		expedienteFacturacion.setAnyo(resultSet.getLong(DBConstants.ANYO));
		expedienteFacturacion.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
		expedienteFacturacion.setTitulo(resultSet.getString(DBConstants.TITULO));
		expedienteFacturacion.setIdTipoExpediente(resultSet.getString(DBConstants.IDTIPOEXPEDIENTE));
		expedienteFacturacion.setTipoExpedienteDescEs(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCES));
		expedienteFacturacion.setTipoExpedienteDescEu(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCEU));
		expedienteFacturacion.setFechaAlta(resultSet.getDate(DBConstants.FECHAALTA));
		expedienteFacturacion.setHoraAlta(resultSet.getString(DBConstants.HORAALTA));
		expedienteFacturacion.setObsvFacturacion(resultSet.getString("OBSV_FACT_051"));

		Entidad entidadSolicitante = new Entidad();
		entidadSolicitante.setTipo(resultSet.getString(DBConstants.TIPOENTIDAD));
		entidadSolicitante.setCodigo(resultSet.getInt(DBConstants.IDENTIDAD));
		entidadSolicitante.setDescEs(resultSet.getString(DBConstants.DESCENTIDADES));
		entidadSolicitante.setDescEu(resultSet.getString(DBConstants.DESCENTIDADEU));
		Solicitante solicitante = new Solicitante();
		solicitante.setDni(resultSet.getString(DBConstants.DNISOLICITANTE));
		solicitante.setNombre(resultSet.getString(DBConstants.NOMBRESOLICITANTE));
		solicitante.setApellido1(resultSet.getString(DBConstants.APEL1SOLICITANTE));
		solicitante.setApellido2(resultSet.getString(DBConstants.APEL2SOLICITANTE));
		solicitante.setGestorExpedientesVIP(resultSet.getString(DBConstants.INDVIP));
		Gestor gestor = new Gestor();
		gestor.setEntidad(entidadSolicitante);
		gestor.setSolicitante(solicitante);
		expedienteFacturacion.setGestorExpediente(gestor);
		if (!TipoExpedienteEnum.INTERPRETACION.getValue().equals(expedienteFacturacion.getIdTipoExpediente())) {
			// trad/rev
			ExpedienteTradRev datosTradRev = new ExpedienteTradRev();
			datosTradRev.setFechaFinalIzo(resultSet.getDate(DBConstants.FECHAENTREGAIZO));
			datosTradRev.setHoraFinalIzo(resultSet.getString(DBConstants.HORAENTREGAIZO));
			datosTradRev.setIndPublicarBopv(resultSet.getString(DBConstants.INDPUBLICARBOPV));
			datosTradRev.setIdIdioma(resultSet.getLong(DBConstants.IDIDIOMA));
			datosTradRev.setIdIdiomaDescEs(resultSet.getString(DBConstants.DESCIDIOMAES));
			datosTradRev.setIdIdiomaDescEu(resultSet.getString(DBConstants.DESCIDIOMAEU));
			datosTradRev.setIdRelevancia(resultSet.getLong(DBConstants.IDRELEVANCIA));
			datosTradRev.setRelevanciaDescEs(resultSet.getString(DBConstants.DESCRELEVANCIAES));
			datosTradRev.setRelevanciaDescEu(resultSet.getString(DBConstants.DESCRELEVANCIAEU));
			datosTradRev.setIndUrgente(resultSet.getString(DBConstants.INDURGENTE));
			datosTradRev.setIndDificil(resultSet.getString(DBConstants.INDDIFICIL));
			DatosFacturacionExpediente datosFacTradRev = new DatosFacturacionExpediente();
			datosFacTradRev.setTarifaPal(resultSet.getBigDecimal(DBConstants.TARIFAPALABRA));
			datosFacTradRev.setPorcentajeIva(resultSet.getBigDecimal(DBConstants.PORCENTAJEIVA));
			datosFacTradRev.setPrecioMinimo(resultSet.getBigDecimal(DBConstants.PRECIOMINIMO));
			datosFacTradRev.setNumTotalPalFacturar(resultSet.getBigDecimal(DBConstants.NUMPALABRASTARIFCONCOR));
			datosFacTradRev.setNumPalConcor084(resultSet.getBigDecimal(DBConstants.PORPALABRACONCOR084));
			datosFacTradRev.setNumPalConcor8594(resultSet.getBigDecimal(DBConstants.PORPALABRACONCOR8594));
			datosFacTradRev.setNumPalConcor95100(resultSet.getBigDecimal(DBConstants.PORPALABRACONCOR95100));
			datosTradRev.setNumTotalPalSolic(resultSet.getInt(DBConstants.NUMPALSOLIC));
			datosTradRev.setNumTotalPalIzo(resultSet.getInt(DBConstants.NUMPALIZO));
			DatosTareaTrados datosTareaTrados = new DatosTareaTrados();
			datosTareaTrados.setNumTotalPal(resultSet.getInt(DBConstants.NUMPALXML));
			datosTareaTrados.setNumPalConcor084(resultSet.getInt(DBConstants.NUMPALXMLCONCOR084));
			datosTareaTrados.setNumPalConcor8594(resultSet.getInt(DBConstants.NUMPALXMLCONCOR8594));
			datosTareaTrados.setNumPalConcor95100(resultSet.getInt(DBConstants.NUMPALXMLCONCOR95100));
			DatosFacturacionCliente datosFacturacionCliente = new DatosFacturacionCliente();
			datosFacturacionCliente.setNumTotalPal(resultSet.getInt(DBConstants.NUMTOTALPALFACTCLIENTE));
			datosFacturacionCliente.setNumPalConcor084(resultSet.getInt(DBConstants.NUMPALCONCOR084FACTCLIENTE));
			datosFacturacionCliente.setNumPalConcor8594(resultSet.getInt(DBConstants.NUMPALCONCOR8594FACTCLIENTE));
			datosFacturacionCliente.setNumPalConcor95100(resultSet.getInt(DBConstants.NUMPALCONCOR95100FACTCLIENTE));
			datosFacTradRev.setImporteBase(resultSet.getBigDecimal(DBConstants.IMPORTEBASE));
			datosFacTradRev.setImporteIva(resultSet.getBigDecimal(DBConstants.IMPORTEIVA));
			datosFacTradRev.setImporteTotal(resultSet.getBigDecimal(DBConstants.IMPORTETOTAL));
			datosTradRev.setDatosFacturacionExpediente(datosFacTradRev);
			datosTradRev.setTradosExp(datosTareaTrados);
			expedienteFacturacion.setExpedienteTradRev(datosTradRev);
			expedienteFacturacion.setDatosFacturacionCliente(datosFacturacionCliente);
			expedienteFacturacion.setPorRecargoDif(resultSet.getLong(DBConstants.PORRECARGODIF));
			expedienteFacturacion.setPorRecargoUrg(resultSet.getLong(DBConstants.PORRECARGOURG));
		} else {
			// interpretacion
			ExpedienteInterpretacion datosExpInter = new ExpedienteInterpretacion();
			datosExpInter.setTipoPeticionario(resultSet.getString(DBConstants.TIPOPETICIONARIO));
			datosExpInter.setTipoPeticionarioDescEs(resultSet.getString(DBConstants.PETICIONARIODESCES));
			datosExpInter.setTipoPeticionarioDescEu(resultSet.getString(DBConstants.PETICIONARIODESCEU));
			datosExpInter.setTipoActo(resultSet.getLong(DBConstants.TIPOACTO));
			datosExpInter.setTipoActoDescEs(resultSet.getString(DBConstants.TIPOACTODESCES));
			datosExpInter.setTipoActoDescEu(resultSet.getString(DBConstants.TIPOACTODESCEU));
			datosExpInter.setIndActoFacturable(resultSet.getString(DBConstants.TIPOFACTURACIONACTO));
			datosExpInter.setIndProgramada(resultSet.getString(DBConstants.INDPROGRAMADA));
			datosExpInter.setIndPresupuesto(resultSet.getString(DBConstants.INDPRESUPUESTO));
			DireccionNora direccionNora = new DireccionNora();
			direccionNora.setDirNora(resultSet.getInt(DBConstants.CDIRNORA));
			direccionNora.setCodProvincia(resultSet.getString(DBConstants.CODPROVINCIA));
			datosExpInter.setDirNora(direccionNora);
			DatosFacturacionExpedienteInterpretacion datosFacturacionExpedienteInterpretacion = new DatosFacturacionExpedienteInterpretacion();
			datosFacturacionExpedienteInterpretacion.setTipoFacturacion(resultSet.getString("TIPOFACTURACION0A3"));
			if (GeneralUtils.estaEnCae(direccionNora.getCodProvincia())) {
				// esta en cae
				datosFacturacionExpedienteInterpretacion.setCae(true);
			} else {
				// no esta en cae
				datosFacturacionExpedienteInterpretacion.setCae(false);
			}
			datosFacturacionExpedienteInterpretacion.setNumInterpretes(resultSet.getInt(DBConstants.NUMINTERPRETES));
			datosFacturacionExpedienteInterpretacion
					.setHorasInterpretacion(resultSet.getString(DBConstants.HORASINTERPRETACION));
			datosFacturacionExpedienteInterpretacion.setTipoIva(resultSet.getBigDecimal(DBConstants.PORCENTAJEIVA));
			// precio minimo de las interpretaciones realizadas depende de si
			// esta en cae o no
			if (datosFacturacionExpedienteInterpretacion.getCae()) {
				// esta en cae
				datosFacturacionExpedienteInterpretacion
						.setPrecioMinimoInterpRealiz(resultSet.getBigDecimal(DBConstants.PRECIOMINCAE));
			} else {
				// no esta en cae
				datosFacturacionExpedienteInterpretacion
						.setPrecioMinimoInterpRealiz(resultSet.getBigDecimal(DBConstants.PRECIOMINNOCAE));
			}

			String tipoFactInter;
			if (datosFacturacionExpedienteInterpretacion.getTipoFacturacion() != null
					&& datosFacturacionExpedienteInterpretacion.getTipoFacturacion() != "") {
				tipoFactInter = datosFacturacionExpedienteInterpretacion.getTipoFacturacion();
			} else {
				tipoFactInter = datosExpInter.getIndActoFacturable();
			}

			if (GeneralUtils.facturaPorEuroHoraInterprete(tipoFactInter)) {
				// factura por euro/hora por interprete
				if (datosFacturacionExpedienteInterpretacion.getCae()) {
					// esta en cae
					datosFacturacionExpedienteInterpretacion
							.setPrecioHoraInterprete(resultSet.getBigDecimal(DBConstants.PRECIOHORAINTERPRETECAE));
				} else {
					// no esta en cae
					datosFacturacionExpedienteInterpretacion
							.setPrecioHoraInterprete(resultSet.getBigDecimal(DBConstants.PRECIOHORAINTERPRETENOCAE));
				}

			} else {
				// factura por euro/interprete
				if (ActivoEnum.SI.getValue().equals(datosExpInter.getIndProgramada())
						&& TipoPeticionarioEnum.ADMIN_PUBLICA.getValue().equals(datosExpInter.getTipoPeticionario())) {
					// esta en cae
					datosFacturacionExpedienteInterpretacion
							.setPrecioJornadaCompleta(resultSet.getBigDecimal(DBConstants.PRECIOJORNCONGRESOEU));
					datosFacturacionExpedienteInterpretacion
							.setPrecioMediaJornada(resultSet.getBigDecimal(DBConstants.PRECIOMEDIAJORNCONGRESOEU));
					datosFacturacionExpedienteInterpretacion
							.setPrecioHoraFraccAdic(resultSet.getBigDecimal(DBConstants.PRECIOHORAJORNCONGRESOEU));
				} else {
					// no esta en cae
					datosFacturacionExpedienteInterpretacion
							.setPrecioJornadaCompleta(resultSet.getBigDecimal(DBConstants.PRECIOJORNCONGRESONOEU));
					datosFacturacionExpedienteInterpretacion
							.setPrecioMediaJornada(resultSet.getBigDecimal(DBConstants.PRECIOMEDIAJORNCONGRESONOEU));
					datosFacturacionExpedienteInterpretacion
							.setPrecioHoraFraccAdic(resultSet.getBigDecimal(DBConstants.PRECIOHORAJORNCONGRESONOEU));
				}
			}
			datosFacturacionExpedienteInterpretacion.setImporteBase(resultSet.getBigDecimal(DBConstants.IMPORTEBASE));
			datosFacturacionExpedienteInterpretacion.setImporteIva(resultSet.getBigDecimal(DBConstants.IMPORTEIVA));
			datosFacturacionExpedienteInterpretacion.setImporteTotal(resultSet.getBigDecimal(DBConstants.IMPORTETOTAL));
			datosFacturacionExpedienteInterpretacion
					.setJornadaCompletaHorasDesde(resultSet.getInt(DBConstants.JORNCOMPHORASDESDE));
			datosFacturacionExpedienteInterpretacion
					.setJornadaCompletaHorasHasta(resultSet.getInt(DBConstants.JORNCOMPHORASHASTA));
			datosFacturacionExpedienteInterpretacion
					.setMediaJornadaHorasDesde(resultSet.getInt(DBConstants.JORNMEDIAHORASDESDE));
			datosFacturacionExpedienteInterpretacion
					.setMediaJornadaHorasHasta(resultSet.getInt(DBConstants.JORNMEDIAHORASHASTA));
			expedienteFacturacion.setDatosFacturacionInterpretacion(datosFacturacionExpedienteInterpretacion);
			expedienteFacturacion.setExpedienteInterpretacion(datosExpInter);
		}
		expedienteFacturacion.setTitOrdenPreciosPublicos(resultSet.getString(DBConstants.TITULOORDENPRECIOSPUBLICOS));
		expedienteFacturacion.setPorIvaOrdenPreciosPublicos(resultSet.getLong(DBConstants.PORIVAORDENPRECIOSPUBLICOS));

		return expedienteFacturacion;
	}

}
