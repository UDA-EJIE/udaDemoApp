/*
* Copyright 2011 E.J.I.E., S.A.
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
package com.ejie.aa79b.common.jackson;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


/**
 * 
 * Used to serialize java.math.BigDecimal, which is not a common JSON
 * type, so we have to create a custom serialize method;.
 *
 * @author Eurohelp S.L.
 * 
 */
@Component()
public class JsonMonedaSerializer extends JsonSerializer<BigDecimal>{

	@Override()
	public void serialize(BigDecimal number, JsonGenerator jsonGenerator,
			SerializerProvider paramSerializerProvider) throws IOException {
		if (number != null) {
			NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);
			jsonGenerator.writeString(currencyFormatter.format(number));
		}
	}
}