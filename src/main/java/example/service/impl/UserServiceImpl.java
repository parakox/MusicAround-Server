package example.service.impl;

import example.model.entity.User;
import example.repository.UserRepository;
import example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email,password);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

}
