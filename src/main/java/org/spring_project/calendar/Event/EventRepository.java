package org.spring_project.calendar.Event;

import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findAllByDate(LocalDate date);
    List<Event> findAllByDateBetween(LocalDate start, LocalDate end);
    Optional<Event> findByEvent(String name);
}
