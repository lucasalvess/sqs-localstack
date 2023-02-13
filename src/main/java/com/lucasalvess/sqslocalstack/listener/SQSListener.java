package com.lucasalvess.sqslocalstack.listener;

import com.lucasalvess.sqslocalstack.dto.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SQSListener {

        @SqsListener("${cloud.aws.queue.name}")
        public void receiveMessage(MessageDTO message) {
            log.info("SQS Message Received : {}", message);
        }
}
