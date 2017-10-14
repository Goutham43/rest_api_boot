package io.sid.api.service;

import io.sid.api.entity.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();
    public User findOne(String id);
    public User create(User user);
    public User update(String id, User user);
    public void delete(String id);

}
