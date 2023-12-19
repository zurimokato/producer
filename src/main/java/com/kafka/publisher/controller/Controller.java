package com.kafka.publisher.controller;
import com.kafka.publisher.request.MessageRequest;
import com.kafka.publisher.service.KafkaMessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class Controller {

    private final KafkaMessageService kafkaMessageService;

    public Controller(KafkaMessageService kafkaMessageService) {
        this.kafkaMessageService = kafkaMessageService;
    }

    @PostMapping("publish")
    public String publishMessage(@RequestBody MessageRequest messageRequest){
        return kafkaMessageService.publish(messageRequest);
    }

}
