package obrien.Project.three.service;

import obrien.Project.three.entity.ChatMessagePojo;
import obrien.Project.three.entity.Messages;

import java.util.Collection;
import java.util.List;

public interface MessageService {

    void  createMessage(ChatMessagePojo chatMessagePojo,String message);
    List<Messages> findAllMessageByMessageId(String Id);

   Collection<?> findAllByMessageId(String id);

   Collection<?> findAllById();
}
