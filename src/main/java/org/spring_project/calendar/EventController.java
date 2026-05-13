package org.spring_project.calendar;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


/**
 * This controller allows the user to interact with in memory data. This means that all events are being stored in
 * the memory so each user that uses this API will store their own events.
 */
@RestController
@RequestMapping(value = "/event")
public class EventController {
    EventRepository repository;

    /**
     * This controller has an event repository as a dependency so it can directly affect the database.
     *
     *
     */
    public EventController(EventRepository repository) {
        this.repository = repository;
    }

    /**
     *
     * @return Either a No content HttpStatus or A 200 status code with all events in the response body
     */
    @Tag(name = "Get all events")
    @GetMapping
    public ResponseEntity<List<EventDTO>> getEvents() {
        return new ResponseEntity<>(convertListToDTO(repository.findAll()), HttpStatus.OK);
    }

    /**
     *
     * @return Either a No content HttpStatus or a 200 status code with events that are set for today
     */
    @Tag(name = "Get today's events")
    @GetMapping("/today")
    public ResponseEntity<List<EventDTO>> getTodayEvents() {
        return new ResponseEntity<>(convertListToDTO(repository.findAllByDate(LocalDate.now())), HttpStatus.OK);
    }

    /**
     *
     * @param id the id of the Event to get
     * @return either a Not found HttpStatus or a 200 status code with the event in the request body
     */
    @Tag(name = "Get an event by Id")
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Integer id) {
        Optional<Event> event = repository.findById(id);
        return event.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     *
     * @param start First date in the range to be compared
     * @param end   Second date in the range to be compared
     * @return Events between the range or a not found status
     */
    @Tag(name = "Get events between two dates")
    @GetMapping("/{start}/{end}")
    public ResponseEntity<List<EventDTO>> getEventsBetweenDates(@PathVariable LocalDate start, @PathVariable LocalDate end) {
        if (start.isAfter(end)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<EventDTO> events = convertListToDTO(repository.findAllByDateBetween(start, end));
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }


    /**
     *
     * @param event will be created in the database
     */
    @Tag(name = "Create a new event")
    @PostMapping
    public void makeEvent(@RequestBody Event event) {
        repository.save(event);
    }

    /**
     *
     * @param id id of the entity to be deleted
     * @return The event that has been deleted and a 200 status code or a not found status code
     */
    @Tag(name = "Delete an event")
    @DeleteMapping("/{id}")
    public ResponseEntity<Event> deleteEventById(@PathVariable Integer id) {
        Optional<Event> event = repository.findById(id);
        if (event.isPresent()) {
            repository.deleteById(id);
            return new ResponseEntity<>(event.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     *
     * @param id    The id of the event to be updated
     * @param event The new event details in the request body
     * @return A not found code or the event that has been saved
     */
    @Tag(name = "Update an event")
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvents(@PathVariable Integer id, @RequestBody Event event) {
        return repository.findById(id).map(e -> {
            e.setEvent(event.getEvent());
            e.setDate(event.getDate());
            return new ResponseEntity<>(repository.save(e), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{name}")
    public ResponseEntity<EventDTO> getEventByName(@PathVariable @RequestBody String name){
        Optional<Event> optionalEvent = repository.findByEvent(name);
        return optionalEvent.map(
                event -> new ResponseEntity<>(convertToEventDTO(event), HttpStatus.OK))
                .orElseGet(() ->
                        new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public EventDTO convertToEventDTO(Event event){
        return new EventDTO(event.getEvent(),event.getDate());
    }

    public List<EventDTO> convertListToDTO(List<Event> events){
        return events.stream().map(this::convertToEventDTO).toList();
    }
}
