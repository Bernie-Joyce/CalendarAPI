package org.spring_project.calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalendarApplicationTests {

    @LocalServerPort
    private int port;
    private EventRepository event;
    private RestTemplate template = new RestTemplate();


    @Autowired
    public CalendarApplicationTests(EventRepository event) {
        this.event = event;

    }

    @Test
    void contextLoads() {
    }


    @Test
    void testing_Get() {
        String BASE_URL = "http://localhost:" + port + "/event/";
        Event tempEvent = new Event("Test", LocalDate.parse("2026-05-12"));
        Event savedEvent = event.save(tempEvent);
        ResponseEntity<Event> response = template.getForEntity(BASE_URL + savedEvent.getId(), Event.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testing_Post(){
        String BASE_URL = "http://localhost:" + port + "/event";
        Event tempEvent = new Event("Test", LocalDate.parse("2026-05-12"));
        ResponseEntity<Void> response = template.postForEntity(BASE_URL, tempEvent, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}