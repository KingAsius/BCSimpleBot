package com.simplebot.controller;

import com.simplebot.entity.PostMessage;
import com.simplebot.service.InfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

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
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void PostMessageInfo(@RequestBody String messagesRecieved) throws IOException {
        PostMessage postMessage = infoService.saveInfoAndGetPostMessage(messagesRecieved);
        if (postMessage != null) {
            try {
                MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
                headers.add("Content-Type", "application/json");
                restTemplate.postForObject(FACEBOOK_POST_URL + ACCESS_TOKEN, postMessage, PostMessage.class);
            } catch (HttpClientErrorException exc) {
                System.out.println(exc.getResponseBodyAsString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
