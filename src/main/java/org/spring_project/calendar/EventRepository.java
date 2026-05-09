package org.spring_project.calendar;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findAllByDate(LocalDate date);
    List<Event> findAllByDateBetween(LocalDate start, LocalDate end);
}
