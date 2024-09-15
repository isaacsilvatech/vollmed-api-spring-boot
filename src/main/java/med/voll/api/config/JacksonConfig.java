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
import jakarta.annotation.PostConstruct;
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

    @Value("${spring.jackson.locale:pt-BR}")
    private String localeConf;

    @Value("${spring.jackson.time-zone:America/Sao_Paulo}")
    private String timeZoneConf;

    private Locale locale;
    private TimeZone timeZone;

    private static final String DATE_TIME_SECOND_FORMAT = "dd/MM/yyyy HH:mm:ss";
    private static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String TIME_SECOND_FORMAT = "HH:mm:ss";
    private static final String TIME_FORMAT = "HH:mm";

    private DateTimeFormatter dateTimeSecondFormatter;
    private DateTimeFormatter dateTimeFormatter;
    private DateTimeFormatter dateFormatter;
    private DateTimeFormatter timeSecondFormatter;
    private DateTimeFormatter timeFormatter;

    private SimpleDateFormat simpleDateTimeSecondFormat;
    private SimpleDateFormat simpleDateTimeFormat;
    private SimpleDateFormat simpleDateFormat;
    private SimpleDateFormat simpleTimeSecondFormat;
    private SimpleDateFormat simpleTimeFormat;

    @PostConstruct
    public void init() {
        locale = Locale.forLanguageTag(localeConf);
        timeZone = TimeZone.getTimeZone(timeZoneConf);
        TimeZone.setDefault(timeZone);

        dateTimeSecondFormatter = createFormatter(DATE_TIME_SECOND_FORMAT);
        dateTimeFormatter = createFormatter(DATE_TIME_FORMAT);
        dateFormatter = createFormatter(DATE_FORMAT);
        timeSecondFormatter = createFormatter(TIME_SECOND_FORMAT);
        timeFormatter = createFormatter(TIME_FORMAT);

        simpleDateTimeSecondFormat = createDateFormat(DATE_TIME_SECOND_FORMAT);
        simpleDateTimeFormat = createDateFormat(DATE_TIME_FORMAT);
        simpleDateFormat = createDateFormat(DATE_FORMAT);
        simpleTimeSecondFormat = createDateFormat(TIME_SECOND_FORMAT);
        simpleTimeFormat = createDateFormat(TIME_FORMAT);
    }

    @Bean
    public ObjectMapper objectMapper() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        SimpleModule module = new SimpleModule();
        module.addDeserializer(Date.class, new JacksonDateDeserializer(List.of(
                simpleDateTimeSecondFormat,
                simpleDateTimeFormat,
                simpleDateFormat,
                simpleTimeSecondFormat,
                simpleTimeFormat
        )));
        module.addSerializer(Date.class, new DateSerializer(false, simpleDateTimeFormat));
        mapper.registerModule(module);

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
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
        sdf.setLenient(false);
        sdf.setTimeZone(timeZone);
        return sdf;
    }

    private DateTimeFormatter createFormatter(String pattern) {
        return DateTimeFormatter.ofPattern(pattern, locale);
    }
}
