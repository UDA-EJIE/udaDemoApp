package com.ejie.aa79b.common;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import com.ejie.aa79b.common.exceptions.AppRuntimeException;
import com.ejie.x38.json.JSONObject;
import com.ejie.x38.util.DateTimeManager;

/**
 * @author dlopez2
 *
 */
public final class Reports {

    /**
     * TIPO_CELDA_TEXTO
     */
    public static final String TIPO_CELDA_TEXTO = "texto";

    /**
     * TIPO_CELDA_IMAGEN
     */
    public static final String TIPO_CELDA_IMAGEN = "imagen";

    /**
     * RUTA_IMAGEN
     */
    public static final String RUTA_IMAGEN = "fop/logo-IVAP_nuevo.jpg";

    /**
     * LOGGER: Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Reports.class);

    /**
     * El metodo constructor.
     */
    private Reports() {
    }

    /**
     *
     * The method retrieveObjectValue.
     *
     * @param obj
     *            el objeto
     * @param property
     *            la propiedad
     * @return Object
     */
    public static Object retrieveObjectValue(Object obj, String property) {
        if (property.contains(".")) {
            // we need to recurse down to final object
            return Reports.retrieveObjectValueWithDot(obj, property.split("\\."), property);
        } else {
            // let's get the object value directly
            return Reports.retrieveObjectValueWihoutDot(obj, property);
        }
    }

    private static Object retrieveObjectValueWithDot(Object obj, String[] props, String property) {
        Object ivalue;
        try {
            if (Map.class.isAssignableFrom(obj.getClass())) {
                Map<?, ?> map = (Map<?, ?>) obj;
                ivalue = map.get(props[0]);
            } else {
                Method method = obj.getClass().getMethod(Reports.getGetterMethodName(props[0]));
                ivalue = method.invoke(obj);
            }
            if (ivalue == null) {
                return null;
            }
        } catch (Exception e) {
            throw new AppRuntimeException(e);
        }
        return Reports.retrieveObjectValue(ivalue, property.substring(props[0].length() + 1));
    }

    private static Object retrieveObjectValueWihoutDot(Object obj, String property) {
        if (Map.class.isAssignableFrom(obj.getClass())) {
            Map<?, ?> map = (Map<?, ?>) obj;
            return map.get(property);
        } else {
            return Reports.retrieveObjectValueNotMap(obj, property);
        }
    }

    private static Object retrieveObjectValueNotMap(Object obj, String property) {
        try {
            Method method;
            Object objeto;
            // Para obtener el dni formateado, mirar el historial del svn
            method = obj.getClass().getMethod(Reports.getGetterMethodName(property));
            objeto = method.invoke(obj);

            return Reports.retrieveObjectValueNotMap(obj, property, objeto);
        } catch (Exception e) {
            Reports.LOGGER.error(e.getMessage(), e);
            throw new AppRuntimeException();
        }
    }

    private static Object retrieveObjectValueDate(Object obj, String property, Object objeto) {
    	// Para obtener la fehca hora formateada, mirar el historial del svn
//            method = obj.getClass().getMethod(Reports.getGetterMethodNameHora(property))
//            return method.invoke(obj)
            Locale locale = LocaleContextHolder.getLocale();
            if (objeto instanceof Timestamp) {
                SimpleDateFormat dateFormat = DateTimeManager.getTimestampFormat(locale);
                return dateFormat.format(objeto);
            } else {
                SimpleDateFormat dateFormat = DateTimeManager.getDateTimeFormat(locale);
                return dateFormat.format(objeto);
            }
    }

    private static Object retrieveObjectValueNotMap(Object obj, String property, Object objeto) {
        if (objeto instanceof Date) {
            return Reports.retrieveObjectValueDate(obj, property, objeto);
        } else if (objeto instanceof BigDecimal) {
            Locale locale = LocaleContextHolder.getLocale();
            if (Constants.LANG_EUSKERA.equals(locale.getLanguage())) {
                locale = new Locale(Constants.LANG_CASTELLANO);
            }
            NumberFormat numberFormatter = NumberFormat.getInstance(locale);
            return numberFormatter.format(objeto);
        } else {
            return objeto;
        }
    }

    /**
     *
     * The method getGetterMethodName.
     *
     * @param name
     *            nombre del metodo
     * @return String
     */
    private static String getGetterMethodName(String name) {
        return "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    /**
     * @param celda
     *            String
     * @return int
     */
    public static int obtenerFilaActual(String celda) {
        String fila = "";
        String letra = "";
        for (int i = 0; i < celda.length(); i++) {
            letra = celda.substring(i, i + 1);
            if (StringUtils.isNumeric(letra)) {
                fila = fila.concat(letra);
            }
        }
        return Integer.parseInt(fila);
    }
	
	public static Object getValor(JSONObject bean, String propiedad) {
		String[] split = propiedad.split("\\.");
		Integer i = 0;
		Object rdo = bean.get(split[i++]);
		if (JSONObject.NULL.equals(rdo)) {
			rdo = null;
		}
		for (; i < split.length; i++) {
			if (rdo != null && rdo instanceof JSONObject) {
				rdo = ((JSONObject)rdo).get(split[i]);
				if (JSONObject.NULL.equals(rdo)) {
					rdo = null;
				}
			}
		}
		return rdo;
	}

}
