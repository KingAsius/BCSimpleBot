package com.simplebot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplebot.entity.Info;
import com.simplebot.model.facebookpostedmessage.Message;
import com.simplebot.model.facebookpostedmessage.PostMessage;
import com.simplebot.model.facebookpostedmessage.Recipient;
import com.simplebot.repository.InfoRepository;
import com.simplebot.service.processor.MessageProcessor;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by Vladislav on 11/4/2016.
 */
@Service
public class InfoService {
    @Autowired
    private InfoRepository infoRepository;
    @Autowired
    private MessageProcessor messageProcessor;
    @Autowired
    private FacebookSender sender;

    public void processReceivedMessage(String jsonObject) {
        Info info = selectInfoFromJson(jsonObject);
        info.setHours(messageProcessor.tryGetAsSaveDateFromat(info.getText()));
        if (info.getHours() != -1) {
            infoRepository.saveAndFlush(info);
            PostMessage postMessage = new PostMessage(new Recipient(info.getUsedId()), new Message("Your time is saved: " + info.getHours() + " h"));
            sender.sendMessage(postMessage);
        }
    }

    private Info selectInfoFromJson(String json) {
        Info info = new Info();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = null;
        try {
            actualObj = mapper.readTree(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long userId = Long.parseLong(actualObj.get("entry").get(0).get("messaging").get(0).get("sender").get("id").textValue());
        String text = actualObj.get("entry").get(0).get("messaging").get(0).get("message").get("text").textValue();
        info.setUsedId(userId);
        info.setText(text);
        return info;
    }
}