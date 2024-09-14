package med.voll.api.config;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class JacksonLocalTimeDeserializer extends JsonDeserializer<LocalTime> {

    private final List<DateTimeFormatter> dateTimeFormatters;

    public JacksonLocalTimeDeserializer(List<DateTimeFormatter> dateTimeFormatters) {
        this.dateTimeFormatters = dateTimeFormatters;
    }

    @Override
    public LocalTime deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        for (DateTimeFormatter formatter : dateTimeFormatters) {
            try {
                return LocalTime.parse(jsonParser.getText(), formatter);
            } catch (DateTimeParseException ignored) {
            }
        }
        throw new JsonParseException("Invalid date-time format: " + jsonParser.getText());
    }

}
