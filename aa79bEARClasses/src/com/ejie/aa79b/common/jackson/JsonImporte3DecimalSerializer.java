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
 * @author eaguirresarobe
 *
 */
@Component()
public class JsonImporte3DecimalSerializer extends JsonSerializer<BigDecimal> {

	@Override
	public void serialize(BigDecimal number, JsonGenerator jsonGenerator, SerializerProvider paramSerializerProvider)
			throws IOException {
		Locale locale = new Locale("es");
		NumberFormat numberFormatter = NumberFormat.getInstance(locale);
		numberFormatter.setMaximumFractionDigits(3);
		numberFormatter.setMinimumFractionDigits(3);
		jsonGenerator.writeString(numberFormatter.format(number));
	}

}
