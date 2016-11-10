package com.simplebot.service;

import com.simplebot.entity.Info;
import com.simplebot.entity.User;
import com.simplebot.model.enums.Command;
import com.simplebot.model.facebookpostedmessage.quickreply.QuickReply;
import com.simplebot.model.facebookpostedmessage.simple.SimpleMessage;
import com.simplebot.model.facebookpostedmessage.simple.SimpleMessageRequest;
import com.simplebot.model.facebookreceivedmessage.ReceivedMessage;
import com.simplebot.model.facebookreceivedmessage.entryInner.messagingInner.UserId;
import com.simplebot.repository.InfoRepository;
import com.simplebot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladislav on 11/4/2016.
 */
@Service
public class ReceivedMessageService {
    @Autowired private UserRepository userRepository;

    @Autowired private InfoRepository infoRepository;

    @Autowired private FacebookHelper helper;

    public void processReceivedMessage(ReceivedMessage receivedMessage) {
        if(checkPayloads(receivedMessage)) return;

        String textFromMessage = receivedMessage.getTextFromMessage();
        UserId sender = receivedMessage.getSender();
        if (Command.ifIsACommand(textFromMessage)) {
            processCommand(Command.getCommandFromString(textFromMessage), receivedMessage);
        }
        else {
            Info info = infoRepository.findTop1ByUserIdOrderByIdDesc(Long.parseLong(receivedMessage.getSenderId()));
            if (info != null) {
                //if user don't write time to the last log yet, try to get the message as time
                if (info.getHours() == null) {
                    try {
                        info.setHours(Integer.parseInt(textFromMessage));
                        infoRepository.saveAndFlush(info);

                        helper.sendSimpleMessage("Please, write a description.", sender);
                    }
                    //if user don't write time yet and we can't get the message as integer - ask one more time
                    catch (NumberFormatException e) {
                        helper.sendMisunderstandingMessage(sender);
                        helper.sendSimpleMessage("How much time did you spend?", sender);
                    }
                    return;
                }
                else {
                    //if user don't write the description to the last log yet, get the message as description
                    if (info.getDescription() == null) {
                        info.setDescription(textFromMessage);
                        infoRepository.saveAndFlush(info);
                        //sending quick reply to the user
                        List<QuickReply> quickReplies = new ArrayList<>();
                        quickReplies.add(QuickReply.createTextQuickReply("Get log", "GET_LOG_PAYLOAD"));
                        helper.sendQuickReplyRequest(quickReplies, sender, "You can see the log");
                        return;
                    }
                }
            }
            //if can't understand user - write standart describing message
            helper.sendSimpleMessage("I can save your spent time and description to it : \n write - {log time} to start", sender);
        }
    }

    private void processCommand(Command command, ReceivedMessage receivedMessage) {
        if (command.equals(Command.LOG_TIME)) {
            //saving info and user to db
            Info info = new Info();
            User user = helper.getUserById(receivedMessage.getSenderId());
            info.setUser(user);
            userRepository.saveAndFlush(user);
            infoRepository.saveAndFlush(info);
            //sending a question about time to user
            SimpleMessageRequest messageRequest = new SimpleMessageRequest();
            messageRequest.setMessage(new SimpleMessage("How much time did you spend?"));
            messageRequest.setRecipient(receivedMessage.getSender());
            helper.sendSimpleMessage(messageRequest);
        }
    }

    private boolean checkPayloads(ReceivedMessage receivedMessage) {
        try {
            if (receivedMessage.getPayloadInQuickReply().equals("GET_LOG_PAYLOAD")) {
                List<Info> logText = infoRepository.findTop5ByUserIdOrderByIdDesc(Long.parseLong(receivedMessage.getSender().getId()));
                StringBuilder builder = new StringBuilder();
                logText.forEach((s) -> builder.append(s.getHours() + " - " + s.getDescription() + "\n"));
                helper.sendSimpleMessage(builder.toString(), receivedMessage.getSender());
                return true;
            }
        }
        catch (Exception e) { return false; }
        return false;
    }
}