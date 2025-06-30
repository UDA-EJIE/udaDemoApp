package com.ejie.aa79b.common;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

/**
 * @author dlopez2
 * 
 */
public class TokenClientHandlerResolver implements HandlerResolver {
    /**
     * handlerChain: List<Handler>.
     */
    private List<Handler> handlerChain;

    /**
     * Constructor
     */
    public TokenClientHandlerResolver() {
        this.handlerChain = new ArrayList<Handler>();
    }

    /**
     * Constructor inicializado con un handlerChain pasado como parámetro.
     * 
     * @param handlerChain
     *            Parámetro a asignar.
     */
    public TokenClientHandlerResolver(List<Handler> handlerChain) {
        this.handlerChain = handlerChain;
    }

    /**
     * handlerChain setter.
     * 
     * @param handlerChain
     *            Parámetro a asignar.
     */
    public void setHandlerChain(List<Handler> handlerChain) {
        this.handlerChain = handlerChain;
    }

    /**
     * Devuelve la lista de handlers asociados.
     * 
     * @param portInfo
     *            Permite al handler obtener la información necesaria del port.
     * @return Lista de handlers asociados.
     */
    @Override
    public List<Handler> getHandlerChain(PortInfo portInfo) {
        return this.handlerChain;
    }
}
