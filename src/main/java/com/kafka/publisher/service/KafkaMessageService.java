package com.kafka.publisher.service;

import com.kafka.publisher.request.MessageRequest;
import org.springframework.stereotype.Service;

@Service
public interface KafkaMessageService {

    String publish(MessageRequest messageRequest);
}
