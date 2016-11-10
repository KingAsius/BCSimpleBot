package com.simplebot.service;

import com.simplebot.entity.User;
import com.simplebot.model.facebookpostedmessage.quickreply.Message;
import com.simplebot.model.facebookpostedmessage.quickreply.QuickRepliesRequest;
import com.simplebot.model.facebookpostedmessage.quickreply.QuickReply;
import com.simplebot.model.facebookpostedmessage.simple.SimpleMessage;
import com.simplebot.model.facebookpostedmessage.simple.SimpleMessageRequest;
import com.simplebot.model.facebookreceivedmessage.entryInner.messagingInner.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Vladislav on 11/8/2016.
 */
@Service
@PropertySource("classpath:/application.properties")
public class FacebookHelper {

    @Value("${social.facebook.posturl}") private String FACEBOOK_POST_URL;
    @Value("${social.facebook.accesstoken}")private String ACCESS_TOKEN;
    @Value("${social.facebook.getuserurl}") private String FACEBOOK_GET_USER_URL;

    @Autowired private RestTemplate restTemplate;

    public void sendQuickReplyRequest(QuickRepliesRequest quickRepliesRequest) {
        try {
            restTemplate.postForObject(FACEBOOK_POST_URL + ACCESS_TOKEN, quickRepliesRequest, QuickRepliesRequest.class);
        } catch (HttpClientErrorException exc) { System.out.println(exc.getResponseBodyAsString()); }
    }

    public void sendQuickReplyRequest(List<QuickReply> quickReplies, UserId recipient, String text) {
        Message message = new Message(text, quickReplies);
        QuickRepliesRequest repliesRequest = new QuickRepliesRequest(recipient, message);
        try {
            restTemplate.postForObject(FACEBOOK_POST_URL + ACCESS_TOKEN, repliesRequest, QuickRepliesRequest.class);
        } catch (HttpClientErrorException exc) { System.out.println(exc.getResponseBodyAsString()); }
    }

    public void sendSimpleMessage(SimpleMessageRequest simpleMessageRequest) {
        try {
            restTemplate.postForObject(FACEBOOK_POST_URL + ACCESS_TOKEN, simpleMessageRequest, SimpleMessageRequest.class);
        } catch (HttpClientErrorException exc) { System.out.println(exc.getResponseBodyAsString()); }
    }

    public void sendSimpleMessage(String messageText, UserId recipient) {
        SimpleMessageRequest request = new SimpleMessageRequest(recipient, new SimpleMessage(messageText));
        try {
            restTemplate.postForObject(FACEBOOK_POST_URL + ACCESS_TOKEN, request, SimpleMessageRequest.class);
        } catch (HttpClientErrorException exc) { System.out.println(exc.getResponseBodyAsString()); }
    }

    public User getUserById(String id) {
        User user = restTemplate.getForObject(FACEBOOK_GET_USER_URL + id + "?access_token=" + ACCESS_TOKEN, User.class);
        user.setId(Long.parseLong(id));
        return user;
    }

    public void sendMisunderstandingMessage(UserId sender) {
        SimpleMessageRequest messageRequest = new SimpleMessageRequest();
        messageRequest.setMessage(new SimpleMessage("Sorry, i didn't get it."));
        messageRequest.setRecipient(sender);
        sendSimpleMessage(messageRequest);
    }
}
