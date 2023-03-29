package com.example.bulgariaguesserserver.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/city")
public class CityController {

    @Autowired
    private CityService cityService;


    @GetMapping("/random")
    public CityDto getRandomCity() {
        return cityService.getRandomCity();
    }

}
