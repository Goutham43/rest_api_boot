package io.sid.api.service;

import io.sid.api.entity.User;
import io.sid.api.exception.BadRequestException;
import io.sid.api.exception.UserNotFoundException;
import io.sid.api.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findOne(String id) {
        return repository.findOne(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " does not exists"));
    }

    @Override
    @Transactional
    public User create(User user) {
        Optional<User> mayExists = repository.findByEmail(user.getEmail());
        if(mayExists.isPresent()) {
            throw new BadRequestException("User with email " + user.getEmail() + " already exist");
        }
        return repository.save(user);
    }

    @Override
    @Transactional
    public User update(String id, User user) {
        repository.findOne(id);
        return repository.save(user);
    }

    @Override
    @Transactional
    public void delete(String id) {
        User existing = repository.findOne(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " does not exists"));
        repository.delete(existing);
    }
}
