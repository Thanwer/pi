package com.thanwer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.UnknownHostException;

/**
 * Created by Thanwer on 13/05/2017.
 */
@Service
public class MessageUtil {

    private static PeerRepository peerRepository;

    @Autowired
    public MessageUtil(PeerRepository peerRepository){
        MessageUtil.peerRepository = peerRepository;
    }



    public static void sendMessage(String id, String text) throws JsonProcessingException,ResourceAccessException {


        ObjectMapper mapper = new ObjectMapper();

        Peer peer = peerRepository.findByName(id);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http:/"+peer.getIpLAN()+":8080/messages";
        Message test = new Message(text);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String jsonMessage = mapper.writeValueAsString(test);
        HttpEntity<String> entity = new HttpEntity<String>(jsonMessage,headers);
        restTemplate.postForObject(url, entity, String.class);


    }
}