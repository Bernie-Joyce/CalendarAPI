package org.spring_project.calendar.Event;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.jspecify.annotations.NonNull;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Event name cannot be blank")
    private String event;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    public Event(@NonNull String event, @NonNull LocalDate date) {
        this.event = event;
        this.date = date;
    }
}
