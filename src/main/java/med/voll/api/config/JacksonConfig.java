package med.voll.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

@Configuration
public class JacksonConfig {

    @Value("${spring.jackson.locale}")
    private String locale;

    @Value("${spring.jackson.time-zone}")
    private String timeZone;

    @Value("${spring.jackson.format.date}")
    private String dateFormat;

    @Value("${spring.jackson.format.time}")
    private String timeFormat;

    @Value("${spring.jackson.format.time-second}")
    private String timeSecondFormat;

    @Value("${spring.jackson.format.date-time}")
    private String dateTimeFormat;

    @Value("${spring.jackson.format.date-time-second}")
    private String dateTimeSecondFormat;

    @Bean
    public ObjectMapper objectMapper() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        SimpleModule module = new SimpleModule();
        module.addDeserializer(Date.class, new JacksonDateDeserializer(List.of(
                createDateFormat(dateTimeSecondFormat),
                createDateFormat(dateTimeFormat),
                createDateFormat(dateFormat),
                createDateFormat(timeSecondFormat),
                createDateFormat(timeFormat)
        )));
        module.addSerializer(Date.class, new DateSerializer(false, createDateFormat(dateTimeFormat)));
        mapper.registerModule(module);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat, Locale.of(locale));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat, Locale.of(locale));
        DateTimeFormatter dateTimeSecondFormatter = DateTimeFormatter.ofPattern(dateTimeSecondFormat, Locale.of(locale));
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(timeFormat, Locale.of(locale));
        DateTimeFormatter timeSecondFormatter = DateTimeFormatter.ofPattern(timeSecondFormat, Locale.of(locale));

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        javaTimeModule.addDeserializer(LocalDateTime.class, new JacksonLocalDateTimeDeserializer(List.of(
                dateTimeSecondFormatter,
                dateTimeFormatter
        )));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeSecondFormatter));
        javaTimeModule.addDeserializer(LocalTime.class, new JacksonLocalTimeDeserializer(List.of(
                timeSecondFormatter,
                timeFormatter
        )));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(timeSecondFormatter));
        mapper.registerModule(javaTimeModule);

        return mapper;
    }

    private SimpleDateFormat createDateFormat(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.of(locale));
        sdf.setLenient(false);
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        return sdf;
    }
}
