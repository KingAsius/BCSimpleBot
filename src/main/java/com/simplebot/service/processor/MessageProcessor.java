package com.simplebot.service.processor;

import com.simplebot.entity.Info;
import com.simplebot.model.postedmessage.Message;
import com.simplebot.model.postedmessage.PostMessage;
import com.simplebot.model.postedmessage.Recipient;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Vladislav on 11/8/2016.
 */
@Service
public class MessageProcessor {

    public Optional<PostMessage> processMessage(Info info) {
        if (info.getText().equals("log hours")) {
            PostMessage postMessage = new PostMessage();
            Message message = new Message("How much hours you were working today?");
            Recipient recipient = new Recipient(info.getUsedId());
            postMessage.setMessage(message);
            postMessage.setRecipient(recipient);
            Optional<PostMessage> optional = Optional.of(postMessage);
            return optional;
        }
        return Optional.empty();
    }
}
