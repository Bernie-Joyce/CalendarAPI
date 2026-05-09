package org.spring_project.calendar;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class EventController {

    @GetMapping("/event/today")
    public ResponseEntity<String> getTodayEvents() {
        return new ResponseEntity<>("[]", HttpStatus.OK);
    }

    @PostMapping("/event")
    public ResponseEntity<String> makeEvent(@RequestBody String event, @RequestBody LocalDate date) {
        return new ResponseEntity<>("The event has been added!", HttpStatus.OK);
    }
}