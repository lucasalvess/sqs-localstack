package com.lucasalvess.sqslocalstack.listener;

import com.lucasalvess.sqslocalstack.dto.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SQSListener {

        @SqsListener(value = "${cloud.aws.queue.name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
        public void receiveMessage(MessageDTO message, @Header("ApproximateReceiveCount") final int receiveCount) {
            log.info("SQS Message Received : {}", message);
            log.info("Attempt count : {}", receiveCount);
            throw new RuntimeException("Ops, an error occurs");
        }

        @SqsListener("${cloud.aws.dlq.name}")
        public void receiveDLQMessage(String message) {
            log.info("DLQ Message Received : {}", message);
        }
}
