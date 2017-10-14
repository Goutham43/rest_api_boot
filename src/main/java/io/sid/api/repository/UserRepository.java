package io.sid.api.repository;

import io.sid.api.entity.User;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends Repository<User, String> {

    public List<User> findAll();

    public Optional<User> findOne(String id);

    public Optional<User> findByEmail(String email);

    public User save(User user); // Update and Insert

    public void delete(User user);
}
