package com.simplebot.service.processor;

import com.simplebot.entity.Info;
import com.simplebot.model.facebookpostedmessage.Message;
import com.simplebot.model.facebookpostedmessage.PostMessage;
import com.simplebot.model.facebookpostedmessage.Recipient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by Vladislav on 11/8/2016.
 */
@Service
public class MessageProcessor {

    public int tryGetAsSaveDateFromat(String textMessage) {
        try {
            if (textMessage.split(" ")[0].equals("logtime"))
                return Integer.parseInt(textMessage.split(" ")[1]);
        }
        catch (Exception e) {
            System.err.println("Unexpected format of received message");
        }
        return -1;
    }
}
