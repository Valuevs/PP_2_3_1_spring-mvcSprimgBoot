package web.service;

import web.model.User;

import java.util.List;

public interface UserService {


    List<User> getAllUsers();

    User getByIdUser(int id);


    void save(User user);


    void update(User user);


    void delete(User user);
}
