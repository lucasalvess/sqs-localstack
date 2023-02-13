package com.lucasalvess.sqslocalstack.api;

import com.lucasalvess.sqslocalstack.dto.MessageDTO;
import com.lucasalvess.sqslocalstack.producer.SQSEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/sqs")
@RequiredArgsConstructor
public class MessageController {

        private final SQSEventPublisher sqsEventPublisher;

        @PostMapping("/sendMessage")
        @ResponseStatus(HttpStatus.CREATED)
        public ResponseEntity<Void> sendMessage(@RequestBody MessageDTO message) {
            sqsEventPublisher.publishEvent(message);
            return ResponseEntity.ok().build();
        }
}
