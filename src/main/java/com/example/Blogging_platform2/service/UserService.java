package com.example.Blogging_platform2.service;

import com.example.Blogging_platform2.model.User;
import com.example.Blogging_platform2.dao.UserDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
    public User registerUser(User user) {
        return userDao.save(user);
    }
    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }
    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }
    public List<User> findAll() {
        return userDao.findAll();
    }
    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }
}
