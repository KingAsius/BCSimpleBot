package com.simplebot.service;

import com.simplebot.model.facebookpostedmessage.PostMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Vladislav on 11/8/2016.
 */
@Service
public class FacebookSender {
    private static String FACEBOOK_POST_URL = "https://graph.facebook.com/v2.6/me/messages?access_token=";
    private static String ACCESS_TOKEN = "EAAQa7ZBJjnHQBABQ5l5A4iF8DGtDR5xy70IuhCC0Rq3ZBbJZBz7zhf33b05HSUZCpDhBZBLEZAPA5TwJemJTfRNbHdEdqODlPUpuI2b0Raf3Qed4pVopZCNKqSlkBub13AaWRaFVd0ySK6y4jMBoiNAvncjRwNNLCBuZCqXDRNVxzGvIP9wRctTv";

    @Autowired
    private RestTemplate restTemplate;

    public void sendMessage(PostMessage postMessage) {
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
