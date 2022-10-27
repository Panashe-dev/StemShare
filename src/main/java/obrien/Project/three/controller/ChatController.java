package obrien.Project.three.controller;


import obrien.Project.three.entity.ChatMessagePojo;
import obrien.Project.three.service.MessageService;
import obrien.Project.three.utils.SecurityUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;
import java.util.Random;

@Controller
public class ChatController {


    private String messageId=null;
    private final MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/chat")
    public String initForm(Map<String,String> model){
        model.put("username", SecurityUtils.findUsername().get());
        messageId=generateReference(6);
        model.put("id",messageId);
        return "Message/Chat";
    }
    @GetMapping("/chat/export/{id}")
    public String initExportChat(@PathVariable String id, RedirectAttributes ra){
        ra.addFlashAttribute("message",messageService.findAllById());
        return "redirect:/messages";
    }

    @GetMapping("/messages")
    public String initMessagesForm(){
        return "Message/Messages";
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessagePojo sendMessage(@Payload ChatMessagePojo chatMessagePojo) {
        messageService.createMessage(chatMessagePojo,messageId);
        return chatMessagePojo;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessagePojo addUser(@Payload ChatMessagePojo chatMessagePojo,
                                   SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessagePojo.getSender());
        return chatMessagePojo;
    }

    public static String generateReference(int len) {
        String chars = "0123456789abcdefghigklmnopqrstuvwxyz";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }


}
