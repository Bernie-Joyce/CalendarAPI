package org.spring_project.calendar.User;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<App_User, Integer> {
    Optional<App_User> findByUsername(String username);
}
