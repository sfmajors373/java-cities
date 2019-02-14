package com.lambdaschool.cities;

import com.lambdaschool.cities.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.Random;

@Slf4j
@Service
public class CityMessageSender
{
    private final RabbitTemplate rt;
    private final CityRepository cityrepos;

    public CityMessageSender(RabbitTemplate rt, CityRepository cityrepos)
    {
        this.rt = rt;
        this.cityrepos = cityrepos;
    }

    @Scheduled(fixedDelay = 3000L)
    public void sendMessage()
    {
        ArrayList<City> cities = new ArrayList<City>();

        cities.addAll(cityrepos.findAll());

        for (City c: cities)
        {
            int rand = new Random().nextInt(10);
            boolean randBool = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), rand, randBool);

            if (randBool == true)
            {
                log.info("Sending message SECRET");
                rt.convertAndSend(CitiesApplication.QUEUE_NAME_SECRET, message);
            }
            else if (rand >= 5)
            {
                log.info("Sending message CITY1");
                rt.convertAndSend(CitiesApplication.QUEUE_NAME_CITY1, message);
            }
            else
            {
                log.info("Sending message CITY2");
                rt.convertAndSend(CitiesApplication.QUEUE_NAME_CITY2, message);
            }
        }
    }
}
