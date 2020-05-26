package example.service;

import example.model.entity.User;

public interface UserService {

    void save(User user);
    User findByEmail(String email);
    User findByNickname(String nickname);
    User findByEmailAndPassword(String email,String password);
    User findById(Long id);
}
