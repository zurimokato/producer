package com.kafka.publisher.service.imp;

import com.kafka.publisher.request.MessageRequest;
import com.kafka.publisher.service.KafkaMessageService;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class MessageServiceImp implements KafkaMessageService {

    static final String TOPIC = "my_topic";


    private final KafkaTemplate<String, MessageRequest> createOrderKafkaTemplate;

    private final String mensajeTopic = "my_topic";

    public MessageServiceImp(KafkaTemplate<String, MessageRequest> createOrderKafkaTemplate) {
        this.createOrderKafkaTemplate = createOrderKafkaTemplate;
    }

    @Override
    public String publish(MessageRequest messageRequest) {
        try {
            SendResult<String, MessageRequest> sendResult = createOrderKafkaTemplate.send(mensajeTopic, messageRequest).get();

            return sendResult.toString();

        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }


    }
}
