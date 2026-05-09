package org.spring_project.calendar;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EventController {
    EventRepository repository;

    public EventController(EventRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/event/today")
    public ResponseEntity<List<Event>> getTodayEvents() {
        List<Event> events = repository.findAllByDate(LocalDate.now());
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/event")
    public ResponseEntity<List<Event>> getEvents() {
        List<Event> events = repository.findAll();
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PostMapping("/event")
    public ResponseEntity<Event> makeEvent(@RequestBody Event event) {
        Event saved = repository.save(event);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }
}