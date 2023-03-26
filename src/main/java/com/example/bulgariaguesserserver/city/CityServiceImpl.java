package com.example.bulgariaguesserserver.city;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {
    private final String[] cityNames = {"Sofia", "Plovdiv", "Varna", "Burgas", "Ruse", "Stara Zagora", "Pleven", "Sliven", "Dobrich", "Shumen",
            "Haskovo", "Pazardzhik", "Yambol", "Blagoevgrad", "Veliko Tarnovo", "Vidin", "Montana", "Gabrovo", "Asenovgrad", "Kazanlak",
            "Kyustendil", "Pernik", "Dimitrovgrad", "Svishtov", "Nova Zagora", "Targovishte", "Dupnitsa", "Lovech", "Silistra", "Razgrad",
            "Gorna Oryahovitsa", "Petrich", "Sandanski", "Samokov", "Sevlievo", "Lom", "Karlovo", "Troyan", "Svilengrad", "Harmanli",
            "Karnobat", "Popovo", "Gotse Delchev", "Peshtera", "Chirpan", "Radomir", "Parvomay", "Panagyurishte", "Botevgrad", "Radnevo",
            "Byala Slatina", "Cherven Bryag", "Omurtag", "Kubrat", "Rakovski", "Knezha", "Balchik", "Rakitovo", "Septemvri", "Pomorie",
            "Belene", "Provadia", "Nessebar", "Dryanovo", "Devnya", "Lyaskovets", "Hisarya", "Kavarna", "Tutrakan", "Elin Pelin",
            "Dolni Chiflik", "Sredets", "Kostenets", "Tsarevo", "Sozopol", "Tervel", "Aksakovo", "Pavlikeni", "Belogradchik", "Krichim",
            "Straldzha", "Zlatitsa", "Kuklen", "Simeonovgrad", "Suvorovo", "Kosten", "Slivnitsa", "Vetovo", "Sungurlare", "Isperih", "Devin",
            "Devetaki", "Lyubimets", "Letnitsa"};
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CityDto getRandomCity() {
        Random random = new Random();
        Integer randomCityIdFrom1to94 = random.nextInt(cityNames.length) + 1;
        var city = cityRepository.findById(randomCityIdFrom1to94.longValue());
        if (city.isEmpty()) {
            throw new RuntimeException("City with id = " + randomCityIdFrom1to94 + "does not exist.");
        }
        return modelMapper.map(city.get(), CityDto.class);
    }



    @Override
    @PostConstruct
    public void mockData(){
       var citiesAreEmpty = cityRepository.findAll().isEmpty();
       if(citiesAreEmpty) {
           var cities = Arrays.asList(cityNames).parallelStream().map(name -> new City(name, 1)).collect(Collectors.toList());
           cityRepository.saveAll(cities);
       }
    }

}
