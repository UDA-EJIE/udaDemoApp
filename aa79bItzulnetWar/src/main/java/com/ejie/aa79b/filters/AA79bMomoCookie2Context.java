package com.ejie.aa79b.filters;

import com.ejie.aa79b.common.Constants;
import com.ejie.w43ta.clients.W43taMomoClientCookie2ContextFilter;
import com.ejie.w43ta.clients.W43taMomoTraceClient;

public class AA79bMomoCookie2Context extends W43taMomoClientCookie2ContextFilter {

    @Override
    public W43taMomoTraceClient getLoggerInstance() {
        return W43taMomoTraceClient.getInstance(
        		Constants.CONSTANTE_DEPARTAMENTO
                , Constants.CONSTANTE_APLICACION
                , null
                , null
                , 0
                , true);
    }
}