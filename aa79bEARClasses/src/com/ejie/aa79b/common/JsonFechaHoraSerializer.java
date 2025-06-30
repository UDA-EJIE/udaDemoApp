package com.ejie.aa79b.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.ejie.x38.util.DateTimeManager;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 *
 * Used to serialize java.math.BigDecimal, which is not a common JSON type, so
 * we have to create a custom serialize method;.
 *
 * @author Eurohelp S.L.
 *
 */
@Component()
public class JsonFechaHoraSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        Locale locale = LocaleContextHolder.getLocale();

        SimpleDateFormat dateFormat = DateTimeManager.getTimestampFormat(locale);

        String formattedDate = dateFormat.format(date);

        gen.writeString(formattedDate);
    }

}