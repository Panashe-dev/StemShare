package obrien.Project.three.service.impl;

import groovy.util.logging.Slf4j;
import obrien.Project.three.entity.ChatMessagePojo;
import obrien.Project.three.entity.Messages;
import obrien.Project.three.repository.MessageRepository;
import obrien.Project.three.service.MessageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void createMessage(ChatMessagePojo chatMessagePojo,String message) {
        Messages messages=new Messages();
        messages.setMessageId(message);
        messages.setSender(chatMessagePojo.getSender());
        messages.setContent(chatMessagePojo.getContent());
        messages.setCreatedDate(LocalDateTime.now());
        messages.setCreatedBy(chatMessagePojo.getSender());
        messages.setLastModifiedBy(null);
        messages.setLastModifiedDate(null);
        messageRepository.save(messages);
    }

    @Override
    public List<Messages> findAllMessageByMessageId(String Id) {
        return null;
    }

    @Override
    public Collection<?> findAllByMessageId(String id) {
        return messageRepository.findAllByMessageId(id);
    }

    @Override
    public Collection<?> findAllById() {
        return messageRepository.findAll();
    }
}
