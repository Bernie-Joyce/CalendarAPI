package org.spring_project.calendar.User;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class App_User {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;

    String username;

    String password;

    public App_User(UserDTO user){
        this.password= user.password();
        this.username=user.username();
    }
}
