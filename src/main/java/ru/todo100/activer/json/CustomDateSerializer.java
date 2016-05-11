package ru.todo100.activer.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class CustomDateSerializer extends JsonSerializer<Calendar> {
    @Override
    public void serialize(Calendar value, JsonGenerator gen, SerializerProvider arg2) throws
            IOException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd H:m:s");
        String formattedDate = formatter.format(value.getTime());

        gen.writeString(formattedDate);

    }
}