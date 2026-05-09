package org.spring_project.calendar;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.jspecify.annotations.NonNull;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private  @NonNull String event;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private  @NonNull LocalDate date;

    public Event(Integer id, @NonNull  String event, @JsonFormat(pattern = "yyyy-MM-dd") @NonNull LocalDate date) {
        this.id = id;
        this.event = event;
        this.date = date;
    }

    public Event() {

    }

    public Integer getId() {
        return id;
    }

    public @NonNull String getEvent() {
        return event;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public @NonNull LocalDate getDate() {
        return date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEvent(@NonNull String event) {
        this.event = event;
    }

    public void setDate(@NonNull LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Event) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.event, that.event) &&
                Objects.equals(this.date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, event, date);
    }

    @Override
    public String toString() {
        return "Event[" +
                "id=" + id + ", " +
                "event=" + event + ", " +
                "date=" + date + ']';
    }

}
