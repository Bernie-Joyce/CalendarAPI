package org.spring_project.calendar;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/event/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Integer id) {
        Optional<Event> event = repository.findById(id);
        return event.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<Event> deleteEventById(@PathVariable Integer id) {
        Optional<Event> event = repository.findById(id);
        if (event.isPresent()) {
            repository.deleteById(id);
            return new ResponseEntity<>(event.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/event/{start}/{end}")
    public ResponseEntity<List<Event>> getEventsBetweenDates(@PathVariable LocalDate start, @PathVariable LocalDate end) {
        List<Event> events = repository.findAllByDateBetween(start, end);
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}