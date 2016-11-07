package com.simplebot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplebot.entity.Info;
import com.simplebot.entity.Message;
import com.simplebot.entity.PostMessage;
import com.simplebot.entity.Recipient;
import com.simplebot.repository.InfoRepository;
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

    public PostMessage saveInfoAndGetPostMessage(String jsonObject) {
        String userId = getUserIdFromReceivedJsonObject(jsonObject);
        String text = getUserMessageFromReceivedJsonObject(jsonObject);
        Info info = new Info();
        info.setId(userId);
        if (infoRepository.exists(userId)) {
                Info oldInfo = infoRepository.findOne(userId);
                info.setMessage(oldInfo.getMessage());
            try {
                info.setHours(Integer.parseInt(text));
                infoRepository.saveAndFlush(info);
            } catch (NumberFormatException e) {
                System.out.println("Unavailable input format");
            }
            return null;
        } else {
            info.setMessage(text);
            infoRepository.saveAndFlush(info);
            info.setMessage("But how much hours you were working yesterday?");
            return new PostMessage(new Recipient(info.getId()), new Message(info.getMessage()));
        }
    }

    private String getUserIdFromReceivedJsonObject(String jsonObject) {
        return getNodeFromJsonString(jsonObject).get("entry").get(0).get("messaging").get(0).get("sender").get("id").textValue();
    }

    private String getUserMessageFromReceivedJsonObject(String jsonObject) {
        return getNodeFromJsonString(jsonObject).get("entry").get(0).get("messaging").get(0).get("message").get("text").textValue();
    }

    private JsonNode getNodeFromJsonString(String jsonObject) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = null;
        try {
            actualObj = mapper.readTree(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return actualObj;
    }
}