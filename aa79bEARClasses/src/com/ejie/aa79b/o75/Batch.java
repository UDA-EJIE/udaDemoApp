package com.ejie.aa79b.o75;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.TokenFactory;
import com.ejie.aa79b.model.RdoBatch;

import o75d.exe.O75API;
import o75d.exe.O75Peticion;

/**
 * 
 * @author aresua
 * 
 */
@Component()
public class Batch {

	@Autowired()
	private Properties appConfiguration;

	private static final String BATCH = "batch";
	private static final String BUZON = "batch.buzon";
	private static final String DESCRIPCION = "batch.descripcion";
	private static final String TIPOPROCESO = "batch.tipoProceso";
	private static final String APP = "batch.app";

	private static final Logger LOGGER = LoggerFactory.getLogger(Batch.class);

	/**
	 * @param request      a
	 * @param sBuzon       a
	 * @param sApp         a
	 * @param sIdShell     a
	 * @param sParam1      Parámetros a pasar al programa a ejecutar
	 * @param sDescripcion a
	 * @param sTipoProceso a
	 * @return RdoBatch
	 */
	public RdoBatch peticionBatch(HttpServletRequest request, String sBuzon, String sApp, String sIdShell,
			String sParam1, String sDescripcion, String sTipoProceso) {
		Batch.LOGGER.info("peticionBatch: " + sBuzon + " " + sApp + " " + sIdShell);
		Batch.LOGGER.info("peticionBatch: Parametros: " + sParam1);
		O75API o75api;
		if (request != null) {
			o75api = new O75API(request);
		} else {
			// las peticiones que vienen desde un WS no tienen en la request el
			// user de xlnets
			o75api = new O75API(TokenFactory.getInstance().getToken("aa79b"));
		}
		Batch.LOGGER.info("peticionBatch: Tras creación o75api");

		O75Peticion o75petCat = new O75Peticion();
		o75petCat.SetBuzon(sBuzon);
		o75petCat.SetAplicacion(sApp);
		o75petCat.SetRuntime(O75API.RUNTIME_SH);
		o75petCat.SetIdShell(sIdShell);
		o75petCat.SetParam1(sParam1);
		o75petCat.SetDescripcion(sDescripcion);

		o75petCat.SetTipoPeticion(O75API.TIPOPET_ROLTIPO);
		o75petCat.SetPropietario("aa79bBatch");

		if ((sTipoProceso != null) && (sTipoProceso.equals(BATCH))) {
			o75petCat.SetTipo(O75API.TIPO_BATCH);
		} else {
			o75petCat.SetTipo(O75API.TIPO_DIRECTO);
		}
		o75petCat.SetSubtipo(O75API.SUBTIPO_RELANZABLE);

		int nRet = o75api.Alta(o75petCat);
		Batch.LOGGER.info("peticionBatch: nRet: " + nRet);
		final RdoBatch rdo = new RdoBatch();
		rdo.setnRet(nRet);
		if (0 == nRet) {
			Batch.LOGGER.info("peticionBatch: o75petCat.GetNumeroPeticion(): " + o75petCat.GetNumeroPeticion());
			rdo.setlNumeroPeticion(o75petCat.GetNumeroPeticion());
			rdo.setsFechaPeticion(o75petCat.GetFechaPeticion());
			rdo.setExito(true);
		} else {
			Batch.LOGGER.info("peticionBatch: Código error: " + o75api.GetCodigoError());
			Batch.LOGGER.info("peticionBatch: Dec: " + o75api.GetDec());
			Batch.LOGGER.info("peticionBatch: Motivo: " + o75api.GetMotivo());
			rdo.setlErrorCodigo(o75api.GetCodigoError());
			rdo.setsErrorDescripcion(o75api.GetDec());
			rdo.setnErrorSQL(o75api.GetCodigoErrorSQL());
			rdo.setsErrorMotivo(o75api.GetMotivo());
			rdo.setExito(false);
		}
		return rdo;
	}

	/**
	 * Constructor
	 * 
	 * @param request      a
	 * @param sBuzon       a
	 * @param sApp         a
	 * @param sIdShell     a
	 * @param sParam1      Parametros a pasar al programa a ejecutar
	 * @param sParam2      Parametros extra cuando param1 exceda de 1250 caracteres
	 * @param sDescripcion a
	 * @param sTipoProceso a
	 * @return RdoBatch
	 */
	public RdoBatch peticionBatchParam2(HttpServletRequest request, String sBuzon, String sApp, String sIdShell,
			List<String> params, String sDescripcion, String sTipoProceso) {
		Batch.LOGGER.info("peticionBatchParam2 - inicio ");
		O75API o75api;
		if (request != null) {
			o75api = new O75API(request);
		} else {
			// las peticiones que vienen desde un WS no tienen en la request el
			// user de xlnets
			o75api = new O75API(TokenFactory.getInstance().getToken("aa79b"));
		}
		Batch.LOGGER.info("peticionBatchParam2 - creado el token y o75api ");
		O75Peticion o75pet = new O75Peticion();

		o75pet.SetBuzon(sBuzon);

		o75pet.SetAplicacion(sApp);

		o75pet.SetRuntime(O75API.RUNTIME_SH);
		o75pet.SetIdShell(sIdShell);
		o75pet.SetParam1(params.get(Constants.CERO));
		o75pet.SetParam2(params.get(Constants.UNO));
		o75pet.SetDescripcion(sDescripcion);

		o75pet.SetTipoPeticion(O75API.TIPOPET_ROLTIPO);
		o75pet.SetPropietario("aa79bBatch");

		if ((sTipoProceso != null) && (sTipoProceso.equals(BATCH))) {
			o75pet.SetTipo(O75API.TIPO_BATCH);
		} else {
			o75pet.SetTipo(O75API.TIPO_DIRECTO);
		}
		o75pet.SetSubtipo(O75API.SUBTIPO_RELANZABLE);

		Batch.LOGGER.info("peticionBatchParam2 - o75api.Alta inicio ");
		int nRet = o75api.Alta(o75pet);
		Batch.LOGGER.info("peticionBatchParam2 - o75api.Alta fin: " + nRet);
		final RdoBatch rdo = new RdoBatch();
		rdo.setnRet(nRet);
		if (0 == nRet) {
			Batch.LOGGER.info("peticionBatchParam2 - o75pet.GetNumeroPeticion(): " + o75pet.GetNumeroPeticion());
			rdo.setlNumeroPeticion(o75pet.GetNumeroPeticion());
			rdo.setsFechaPeticion(o75pet.GetFechaPeticion());
			rdo.setExito(true);
		} else {
			Batch.LOGGER.info("peticionBatchParam2 - o75api.GetDec(): " + o75api.GetDec());
			Batch.LOGGER.info("peticionBatchParam2 - o75api.GetMotivo(): " + o75api.GetMotivo());
			rdo.setlErrorCodigo(o75api.GetCodigoError());
			rdo.setsErrorDescripcion(o75api.GetDec());
			rdo.setnErrorSQL(o75api.GetCodigoErrorSQL());
			rdo.setsErrorMotivo(o75api.GetMotivo());
			rdo.setExito(false);
		}
		return rdo;
	}

	/**
	 * peticionBatch
	 * 
	 * @param request a
	 * @return RdoBatch
	 */
	public RdoBatch peticionBatch(HttpServletRequest request) {
		String sBuzon = (String) this.appConfiguration.get(BUZON);
		String sApp = (String) this.appConfiguration.get(APP);
		String sIdShell = (String) this.appConfiguration.get("batch.programa");
		String sParam1 = (String) this.appConfiguration.get("batch.parametros");
		String sDescripcion = (String) this.appConfiguration.get(DESCRIPCION);
		String sTipoProceso = (String) this.appConfiguration.get(TIPOPROCESO);
		return this.peticionBatch(request, sBuzon, sApp, sIdShell, sParam1, sDescripcion, sTipoProceso);
	}

	/**
	 * peticionBatch
	 * 
	 * @param request  a
	 * @param nomSh    a
	 * @param strParam a
	 * @return RdoBatch
	 */
	public RdoBatch peticionBatch(HttpServletRequest request, String nomSh, String strParam) {
		String sBuzon = (String) this.appConfiguration.get(BUZON);
		String sApp = (String) this.appConfiguration.get(APP);
		String sIdShell = nomSh;
		String sParam1 = strParam;
		String sDescripcion = (String) this.appConfiguration.get(DESCRIPCION);
		String sTipoProceso = (String) this.appConfiguration.get(TIPOPROCESO);
		return this.peticionBatch(request, sBuzon, sApp, sIdShell, sParam1, sDescripcion, sTipoProceso);
	}

	/**
	 * peticionBatch
	 * 
	 * @param request      a
	 * @param nomSh        a
	 * @param strParam     a
	 * @param descPeticion a
	 * @return RdoBatch
	 */
	public RdoBatch peticionBatch(HttpServletRequest request, String nomSh, String strParam, String descPeticion) {
		String sBuzon = (String) this.appConfiguration.get(BUZON);
		String sApp = (String) this.appConfiguration.get(APP);
		String sIdShell = nomSh;
		String sParam1 = strParam;
		String sTipoProceso = (String) this.appConfiguration.get(TIPOPROCESO);
		return this.peticionBatch(request, sBuzon, sApp, sIdShell, sParam1, descPeticion, sTipoProceso);
	}

	/**
	 * peticionBatch
	 * 
	 * @param request      a
	 * @param nomSh        a
	 * @param strParam     a
	 * @param descPeticion a
	 * @return RdoBatch
	 */
	public RdoBatch peticionBatch(HttpServletRequest request, String nomSh, String strParam, String strParam2,
			String descPeticion) {
		Batch.LOGGER.info("peticionBatch - inicio ");
		String sBuzon = (String) this.appConfiguration.get(BUZON);
		String sApp = (String) this.appConfiguration.get(APP);
		String sIdShell = nomSh;
		final List<String> params = new ArrayList<String>();
		params.add(strParam);
		params.add(strParam2);
		String sTipoProceso = (String) this.appConfiguration.get(TIPOPROCESO);
		return this.peticionBatchParam2(request, sBuzon, sApp, sIdShell, params, descPeticion, sTipoProceso);
	}

	/**
	 * peticionBatch
	 * 
	 * @param request  a
	 * @param strParam a
	 * @return boolean
	 */
	public RdoBatch peticionBatch(HttpServletRequest request, String strParam) {
		String sBuzon = (String) this.appConfiguration.get(BUZON);
		String sApp = (String) this.appConfiguration.get(APP);
		String sIdShell = (String) this.appConfiguration.get("batch.programa");
		String sParam1 = strParam;
		String sDescripcion = (String) this.appConfiguration.get(DESCRIPCION);
		String sTipoProceso = (String) this.appConfiguration.get(TIPOPROCESO);
		return this.peticionBatch(request, sBuzon, sApp, sIdShell, sParam1, sDescripcion, sTipoProceso);
	}
}