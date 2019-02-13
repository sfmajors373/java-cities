package com.lambdaschool.cities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CityMessageListener
{
    @RabbitListener(queues = CitiesApplication.QUEUE_NAME_SECRET)
    public void receiveMessage(CityMessage c)
    {
        log.info("Received Message: {}", c.toString());
    }
}
