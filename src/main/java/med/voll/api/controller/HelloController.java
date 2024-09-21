package med.voll.api.controller;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@RestController
@RequestMapping("/hello")
public class HelloController {


    @GetMapping
    public String olaMundo() {
        return "Hello Word Spring!";
    }

    @PostMapping
    public String olaMundo(@RequestBody Test t) {
        return "Hello Word Spring!";
    }

    public static class Test {
        public LocalTime data;
    }
}


