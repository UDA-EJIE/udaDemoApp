/*
 * Copyright 2012 E.J.I.E., S.A.
 *
 * Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 * Solo podrá usarse esta obra si se respeta la Licencia.
 * Puede obtenerse una copia de la Licencia en
 *
 * http://ec.europa.eu/idabc/eupl.html
 *
 * Salvo cuando lo exija la legislación aplicable o se acuerde por escrito,
 * el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 * SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 * Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 * que establece la Licencia.
 */
package com.ejie.x21a.control;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.x21a.model.NoraCalle;
import com.ejie.x21a.model.NoraMunicipio;
import com.ejie.x21a.model.NoraProvincia;
import com.ejie.x21a.model.RandomForm;
import com.ejie.x21a.service.NoraCalleService;
import com.ejie.x21a.service.NoraMunicipioService;
import com.ejie.x21a.service.NoraProvinciaService;
import com.ejie.x38.hdiv.annotation.UDALink;
import com.ejie.x38.hdiv.annotation.UDALinkAllower;

/**
 * IntegracionController
 *
 * @author UDA
 */
@Controller
@RequestMapping(value = "/integracion")
public class IntegracionController {

    //private static final Logger logger = LoggerFactory.getLogger(IntegracionController.class);


    /**
     * NORA
     */

    @Autowired
    private NoraProvinciaService provinciaService;
    @Autowired
    private NoraMunicipioService municipioService;
    @Autowired
    private NoraCalleService calleService;

    //visor geoEuskadi
    @UDALink(name = "getVisorGeoEuskadi", linkTo = { @UDALinkAllower(name = "getVisorGeoEuskadiLayer" )})
    @GetMapping(value = "geoEuskadi")
    public String getVisorGeoEuskadi() {
        return "geoEuskadi";
    }
    
    @UDALink(name = "getVisorGeoEuskadiLayer")
    @GetMapping(value = "geoEuskadiLayer.kml")
    public @ResponseBody
    void getVisorGeoEuskadiLayer(HttpServletResponse response) {
        BufferedInputStream in;
        try {
            in = new BufferedInputStream(new URL("http://www.geo.euskadi.eus/bisorea/v3/demos/ficheros_datos/hoteles.kml").openStream());
            byte[] targetArray = new byte[in.available()];
            in.read(targetArray);
            response.getOutputStream().write(targetArray);
        } catch (IOException e) {

        }
    }


    //z-index
    @GetMapping(value = "z-index")
    public String getZIndex(Model model) {
        return "z-index";
    }

    //Nora
    @GetMapping(value = "nora")
    public String getNora(Model model) {
        model.addAttribute("randomForm", new RandomForm());
        return "nora";
    }

    //Tiny
    @GetMapping(value = "tiny")
    public String getTiny(Model model) {
        return "tiny";
    }

    //Webdav
    @GetMapping(value = "webdav")
    public String getWebdav(Model model) {
        return "webdav";
    }

    //PIF
    @GetMapping(value = "pif")
    public String getPIf(Model model) {
        model.addAttribute("randomForm", new RandomForm());
        return "pif";
    }

    @GetMapping(value = "comboEnlazado/remoteEnlazadoProvincia")
    public @ResponseBody
    List<NoraProvincia> getEnlazadoProvincia() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<NoraProvincia> findAll = provinciaService.findAll(null, null);
        return findAll;
    }

    @GetMapping(value = "comboEnlazado/remoteEnlazadoMunicipio")
    public @ResponseBody
    List<NoraMunicipio> getEnlazadoMunicipio(
            @RequestParam(value = "provincia", required = false) String provincia_code) {
        //Convertir parámetros en entidad para búsqueda
        NoraMunicipio municipio = new NoraMunicipio();
        municipio.setProvinciaId(provincia_code);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return municipioService.findAll(municipio, null);
    }

    @GetMapping(value = "autocomplete/calleRemote")
    public @ResponseBody
    List<NoraCalle> getCalleRemoteAutocomplete(
            @RequestParam(value = "q", required = true) String q,
            @RequestParam(value = "c", required = true) Boolean c,
            @RequestParam(value = "provinciaId", required = false) String provinciaId,
            @RequestParam(value = "municipioId", required = false) String municipioId) {
        // Filtro
        NoraCalle calle = new NoraCalle();
        calle.setDsO(q);
        calle.setMunicipioId(municipioId);
        calle.setProvinciaId(provinciaId);
        List<NoraCalle> findAllLike = calleService.findAllLike(calle, null, !c);
        return findAllLike;
    }
}
