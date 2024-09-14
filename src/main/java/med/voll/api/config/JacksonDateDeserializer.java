package med.voll.api.config;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class JacksonDateDeserializer extends JsonDeserializer<Date> {

    private final List<SimpleDateFormat> dateFormats;

    public JacksonDateDeserializer(List<SimpleDateFormat> dateFormats) {
        this.dateFormats = dateFormats;
    }

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        for (SimpleDateFormat dateFormat : dateFormats) {
            try {
                return dateFormat.parse(jsonParser.getText());
            } catch (ParseException ignored) {
            }
        }
        throw new JsonParseException("Invalid date format: " + jsonParser.getText());
    }

}
