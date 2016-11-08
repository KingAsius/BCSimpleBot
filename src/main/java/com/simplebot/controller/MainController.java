package com.simplebot.controller;

import com.simplebot.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by Vladislav on 11/3/2016.
 */
@RestController
public class MainController {

    private static String FACEBOOK_POST_URL = "https://graph.facebook.com/v2.6/me/messages?access_token=";
    private static String ACCESS_TOKEN = "EAAQRCHoSZAuEBABZBaJ11S5P6JPnIFFXqWxY4ZCPc90C0X2n2ODPZB59zmmTyXmikrqoikbg6xr7vPr3WDxP6JSL3FZCVrhPENEKfzHViaOILHu9NfJ0L38QHjm5NlmtqIzFY3alyIqHsQ0EHgSo1cRqZAkiuJiQ0uZCHOb0Tal571d0NNIcusM";
    @Autowired
    private InfoService infoService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void PostMessageInfo(@RequestBody String messagesRecieved) throws IOException {
        infoService.processReceivedMessage(messagesRecieved);
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> index(@RequestParam(value = "hub.challenge", required = true) String challenge,
                                        @RequestParam(value = "hub.verify_token", required = true) String facebookToken) {
        String verificationToken = "verify_token";
        if (verificationToken != null) {
            if (verificationToken.equals(facebookToken)) {
                return new ResponseEntity<String>(challenge, HttpStatus.OK);
            }
        }
        System.err.println("Invalid verify token was received : received " + facebookToken);
        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }
}
