package com.lambdaschool.cities;

import com.lambdaschool.cities.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Random;

@Slf4j
@RestController
public class CityController
{
    private final CityRepository cityrepos;
    private final RabbitTemplate rt;

    public CityController(CityRepository cityrepos, RabbitTemplate rt)
    {
        this.cityrepos = cityrepos;
        this.rt = rt;
    }

    @GetMapping("/cities/afford")
    public void findSome()
    {
        ArrayList<City> cities = new ArrayList<City>();
        cities.addAll(cityrepos.findAll());

        for (City c : cities)
        {
            int rand = new Random().nextInt(10);
            boolean randBool = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), rand, randBool);

            log.info("Sending message...");
            if (randBool == true)
            {
                rt.convertAndSend(CitiesApplication.QUEUE_NAME_SECRET, message);
            }
            else if (c.getAffordability() < 6)
            {
                rt.convertAndSend(CitiesApplication.QUEUE_NAME_CITY1, message);
            }
            else
            {
                rt.convertAndSend(CitiesApplication.QUEUE_NAME_CITY2, message);
            }
        }
    }

    @GetMapping("/cities/homes")
    public void findOthers()
    {
        ArrayList<City> cities = new ArrayList<City>();
        cities.addAll(cityrepos.findAll());

        for (City c : cities)
        {
            int rand = new Random().nextInt(10);
            boolean randBool = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), rand, randBool);

            log.info("Sending message...");
            if (randBool == true)
            {
                rt.convertAndSend(CitiesApplication.QUEUE_NAME_SECRET, message);
            }
            else if (c.getMedianHome() < 200000)
            {
                rt.convertAndSend(CitiesApplication.QUEUE_NAME_CITY1, message);
            }
            else
            {
                rt.convertAndSend(CitiesApplication.QUEUE_NAME_CITY2, message);
            }
        }
    }

    @GetMapping("/cities/names")
    public void findStuff()
    {
        ArrayList<City> cities = new ArrayList<City>();
        cities.addAll(cityrepos.findAll());

        for (City c : cities)
        {
            int rand = new Random().nextInt(10);
            boolean randBool = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), rand, randBool);

            log.info("Sending message...");
            if (randBool == true)
            {
                rt.convertAndSend(CitiesApplication.QUEUE_NAME_SECRET, message);
            }
            else
            {
                rt.convertAndSend(CitiesApplication.QUEUE_NAME_CITY1, message);
            }
        }
    }
}
