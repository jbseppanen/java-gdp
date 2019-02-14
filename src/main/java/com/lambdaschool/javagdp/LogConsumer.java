package com.lambdaschool.javagdp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogConsumer {
    @RabbitListener(queues = JavaGdpApplication.QUEUE_NAME_LOG)
    public void consumeMessage(final Message message) {
        log.info("Received Message: {}", message.toString());
    }
}
