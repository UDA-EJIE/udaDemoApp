package com.ejie.aa79b.common;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class JsonNumbersDeserializer extends JsonDeserializer<BigDecimal> {

    @Override
    public BigDecimal deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String floatString = parser.getText();
        if (floatString.contains(".")) {
            floatString = floatString.replace(".", "");
        }
        if (floatString.contains(",")) {
            floatString = floatString.replace(",", ".");
        }
        return new BigDecimal(floatString);
    }

}