package com.ejie.aa79b.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.TokenFactory;
import com.ejie.aa79b.model.SrtLibroRegistro;
import com.ejie.aa79b.utils.XMLReader;
import com.ejie.aa79b.webservices.cp.Q99RCPFacadeWSSoap;
import com.ejie.aa79b.webservices.srt.Q99DSRTFacadeWSSoap;

@Service(value = "libroRegistroSrtService")
public class LibroRegistroServiceImpl implements LibroRegistroService {

    @Autowired()
    private Q99RCPFacadeWSSoap cpWebService;
    @Autowired()
    private Q99DSRTFacadeWSSoap srtWebService;

    @Override()
	public SrtLibroRegistro registrarEntrada(String descripcionEus, String descripcionCas, String senderId, String senderName) {
		String procedureId = Constants.AA79B_PROCEDIMIENTO;
		String token = TokenFactory.getInstance().getToken(Constants.CONSTANTE_APLICACION);
		String sXmlContextRegistry = this.getContextRegistry(procedureId, descripcionEus, descripcionCas, senderId, senderName, token);
		String entryRegistry = this.srtWebService.createEntryRegistry(token, sXmlContextRegistry);

		// Obtenemos los datos del libro de registro
		XMLReader entryRegistryReader = XMLReader.getInstance(entryRegistry);
		String registryNumber = entryRegistryReader.evaluate("/contextRegistry/registryNumber");
		String registryTimestamp = entryRegistryReader.evaluate("/contextRegistry/registryTimestamp");
		String registrySubmissionTimestamp = entryRegistryReader.evaluate("/contextRegistry/registrySubmissionTimestamp");
		

		SrtLibroRegistro srtLibroRegistro = new SrtLibroRegistro();
		srtLibroRegistro.setRegistryNumber(registryNumber);
		srtLibroRegistro.setRegistryTimestamp(registryTimestamp);
		srtLibroRegistro.setRegistrySubmissionTimestamp(registrySubmissionTimestamp);
		return srtLibroRegistro;
	}
	
	private String getContextRegistry(String procedureId, String descripcionEus, String descripcionCas, String senderId, String senderName, String token) {

		String procedureFac = this.cpWebService.getProcedureFac(token, procedureId);
		
		XMLReader procedureFacReader = XMLReader.getInstance(procedureFac);
		String procedureNameCas = procedureFacReader.evaluate("/Procedure/procedureName/description/language[@locale='es']");
		String procedureNameEus = procedureFacReader.evaluate("/Procedure/procedureName/description/language[@locale='eu']");
		String managingUnitId = procedureFacReader.evaluate("/Procedure/managingUnitId");
		String managingUnitCas = procedureFacReader.evaluate("/Procedure/managingUnitName/description/language[@locale='es']");
		String managingUnitEus = procedureFacReader.evaluate("/Procedure/managingUnitName/description/language[@locale='eu']");
		String departamentoCas = procedureFacReader.evaluate("/Procedure/departmentName/description/language[@locale='es']");
		String departamentoEus = procedureFacReader.evaluate("/Procedure/departmentName/description/language[@locale='eu']");
		
		final StringBuilder buff = new StringBuilder();

		buff.append("<contextRegistry>");
		// PROCEDURE
		this.crearEntrada(buff, "procedureID", procedureId);
		// PROCEDURE NAME
		this.crearEntrada(buff, "procedureName", procedureNameEus, procedureNameCas);
		// MANAGIN UNIT
		this.crearEntrada(buff, "managingUnitID", managingUnitId);
		this.crearEntrada(buff, "managingUnitName", managingUnitEus, managingUnitCas, false);
		// DEPARTAMENTO
		this.crearEntrada(buff, "departmentName", departamentoEus, departamentoCas, false);
		// SUBMISSION TYPE
		this.crearEntrada(buff, "SubmissionTypeName", descripcionEus, descripcionCas);
		// REGISTRY TOPIC
		this.crearEntrada(buff, "registryTopicCode", procedureId);
		// SENDER
		this.crearEntrada(buff, "registrySenderId", senderId);
		this.crearEntrada(buff, "registrySenderName", null, senderName);
		// REGISTRY ADDRESSEE
		this.crearEntrada(buff, "registryAddresseeCode", managingUnitId);
		this.crearEntrada(buff, "registryAddresseeName", managingUnitEus, managingUnitCas);
		// IDIOMA
		buff.append("<language>eu</language>");
		buff.append("</contextRegistry>");
		return buff.toString();
	}
	
	private void crearEntrada(StringBuilder str, String nombre, String valor) {
		str.append("<").append(nombre).append(">");
		str.append(valor);
		str.append("</").append(nombre).append(">");
	}
	private void crearEntrada(StringBuilder str, String nombre, String valorEu, String valorEs) {
		crearEntrada(str, nombre, valorEu, valorEs, true);
	}
	private void crearEntrada(StringBuilder str, String nombre, String valorEu, String valorEs, Boolean descripcion) {
		str.append("<").append(nombre).append(">");
		if (descripcion) {
			str.append("<description>");
		}
		if (valorEu == null) {
			str.append("<language locale=\"eu\">");
			str.append(valorEu);
			str.append("</language>");
		}
		str.append("<language locale=\"es\">");
		str.append(valorEs);
		str.append("</language>");
		if (descripcion) {
			str.append("</description>");
		}
		str.append("</").append(nombre).append(">");
	}

}
