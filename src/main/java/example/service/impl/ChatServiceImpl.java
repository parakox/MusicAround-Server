package example.service.impl;

import example.model.entity.Chat;
import example.repository.ChatRepository;
import example.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat findById(Long id) {
        return chatRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Chat chat) {
        chatRepository.save(chat);
    }
}
