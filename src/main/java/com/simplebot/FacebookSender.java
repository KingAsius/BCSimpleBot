package com.simplebot;

import com.simplebot.model.postedmessage.PostMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Vladislav on 11/8/2016.
 */
public class FacebookSender {
    private static String FACEBOOK_POST_URL = "https://graph.facebook.com/v2.6/me/messages?access_token=";
    private static String ACCESS_TOKEN = "EAAQRCHoSZAuEBABZBaJ11S5P6JPnIFFXqWxY4ZCPc90C0X2n2ODPZB59zmmTyXmikrqoikbg6xr7vPr3WDxP6JSL3FZCVrhPENEKfzHViaOILHu9NfJ0L38QHjm5NlmtqIzFY3alyIqHsQ0EHgSo1cRqZAkiuJiQ0uZCHOb0Tal571d0NNIcusM";

    @Autowired
    private static RestTemplate restTemplate;

    public static void sendMessage(PostMessage postMessage) {
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
