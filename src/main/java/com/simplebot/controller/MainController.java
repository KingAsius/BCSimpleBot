package com.simplebot.controller;

import com.simplebot.model.facebookreceivedmessage.ReceivedMessage;
import com.simplebot.service.ReceivedMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by Vladislav on 11/3/2016.
 */
@RestController
public class MainController {

    @Value("${social.facebook.verifytoken}") private String VERIFICATION_TOKEN;
    @Autowired private ReceivedMessageService infoService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void PostMessageInfo(@RequestBody ReceivedMessage receivedMessage) throws IOException {
        infoService.processReceivedMessage(receivedMessage);

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> index(@RequestParam(value = "hub.challenge", required = true) String challenge,
                                        @RequestParam(value = "hub.verify_token", required = true) String facebookToken) {

        if (facebookToken != null) {
            if (VERIFICATION_TOKEN.equals(facebookToken)) {
                return new ResponseEntity<String>(challenge, HttpStatus.OK);
            }
        }
        System.err.println("Invalid verify token was received : received " + facebookToken);
        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }
}
