package com.lucasalvess.sqslocalstack.producer;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasalvess.sqslocalstack.dto.MessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SQSEventPublisher {

    private final AmazonSQS amazonSQS;
    private final ObjectMapper objectMapper;

    @Value("${sqs.queue.endpoint}")
    private String sqsUrl;

    public void publishEvent(MessageDTO messageDTO) {
        try {
            String message = objectMapper.writeValueAsString(messageDTO);

            SendMessageRequest sendMessageRequest = new SendMessageRequest()
                    .withQueueUrl(sqsUrl)
                    .withMessageBody(message);

            amazonSQS.sendMessage(sendMessageRequest);
            log.info("Message has been published in SQS.");
        } catch (Exception e) {
            log.error("Exception occurred while pushing event to sqs : {} and stacktrace ; {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
