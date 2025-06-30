package com.ejie.aa79b.common;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.codehaus.jackson.JsonParseException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.ejie.x38.util.DateTimeManager;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * 
 * Used to serialize java.math.BigDecimal, which is not a common JSON type, so
 * we have to create a custom serialize method;.
 * 
 * @author Eurohelp S.L.
 * 
 */
@Component()
public class JsonFechaHoraDeserializer extends JsonDeserializer<Timestamp> {

    @Override
    public Timestamp deserialize(JsonParser par, DeserializationContext ctx)
            throws IOException, JsonProcessingException {
        try {
            Locale locale = LocaleContextHolder.getLocale();
            SimpleDateFormat format = DateTimeManager.getTimestampFormat(locale);
            SimpleDateFormat formatDate = DateTimeManager.getDateTimeFormat(locale);

            String dateText = par.getText();

            if (dateText == null || "".equals(dateText)) {
                return null;
            }
            Date date = null;
            if ("".equals(dateText.substring(Constants.DIEZ))) {
                date = formatDate.parse(dateText);

            } else {
                date = format.parse(dateText);
            }

            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            throw new JsonParseException(null, null, e);
        }
    }
}