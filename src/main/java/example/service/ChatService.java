package example.service;

import example.model.entity.Chat;

public interface ChatService {
    Chat findById(Long id);
    void save(Chat chat);
}
