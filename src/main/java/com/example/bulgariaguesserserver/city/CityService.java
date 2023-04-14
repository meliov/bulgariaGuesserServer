package com.example.bulgariaguesserserver.city;

import com.example.bulgariaguesserserver.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CityService {
    private static List<City> cities;
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    private static final String[] cityNames = {"Sofia", "Plovdiv", "Varna", "Burgas", "Ruse", "Stara Zagora", "Pleven", "Sliven", "Dobrich", "Shumen",
            "Haskovo", "Pazardzhik", "Yambol", "Blagoevgrad", "Veliko Tarnovo", "Vidin", "Montana", "Gabrovo", "Asenovgrad", "Kazanlak",
            "Kyustendil", "Pernik", "Dimitrovgrad", "Svishtov", "Nova Zagora", "Targovishte", "Dupnitsa", "Lovech", "Silistra", "Razgrad",
            "Gorna Oryahovitsa", "Petrich", "Sandanski", "Samokov", "Sevlievo", "Lom", "Karlovo", "Troyan", "Svilengrad", "Harmanli",
            "Karnobat", "Popovo", "Gotse Delchev", "Peshtera", "Chirpan", "Radomir", "Parvomay", "Panagyurishte", "Botevgrad", "Radnevo",
            "Byala Slatina", "Cherven Bryag", "Omurtag", "Kubrat", "Rakovski", "Knezha", "Balchik", "Rakitovo", "Septemvri", "Pomorie",
            "Belene", "Provadia", "Nessebar", "Dryanovo", "Devnya", "Lyaskovets", "Hisarya", "Kavarna", "Tutrakan", "Elin Pelin",
            "Dolni Chiflik", "Sredets", "Kostenets", "Tsarevo", "Sozopol", "Tervel", "Aksakovo", "Pavlikeni", "Belogradchik", "Krichim",
            "Straldzha", "Zlatitsa", "Kuklen", "Simeonovgrad", "Suvorovo", "Kosten", "Slivnitsa", "Vetovo", "Sungurlare", "Isperih", "Devin",
            "Devetaki", "Lyubimets", "Letnitsa"};
    @PostConstruct
    public void mockCityData(){
        if(cityRepository.findAll().isEmpty()) {
            var mappedCities = Arrays.asList(cityNames).parallelStream().map(name -> new City(name)).collect(Collectors.toList());
            cityRepository.saveAll(mappedCities);
            cities = cityRepository.findAll();
        }else{
            cities =  cityRepository.findAll();
        }
    }

    public CityDto getRandomCity() {
        System.out.println("In getRandomCity()");
        Random random = new Random();
        List<City> userLevelMatchingCitiesLevel = getFilteredCitiesByUserLevelWhichAreNotPresentInTheLoggedInUser();
        int randomCityIndex = random.nextInt(userLevelMatchingCitiesLevel.size()) + 1;
        Long randomCityId = userLevelMatchingCitiesLevel.get(randomCityIndex).getId();
        var city = cityRepository.findById(randomCityId);
        if (city.isEmpty()) {
            throw new RuntimeException("City with id = " + randomCityId + "does not exist.");
        }
        userService.addCityForLoggedInUser(city.get());
        return modelMapper.map(city.get(), CityDto.class);
    }

    /**
     * extracts cities that are not present in the user and match his level
     * @return
     */
    private List<City> getFilteredCitiesByUserLevelWhichAreNotPresentInTheLoggedInUser(){
        return cities
                .stream()
                .filter(city -> city.getLevel().equals(userService.getLoggedInUser().getCurrentUser().getLevel()))
                .filter(city -> !userService.getLoggedInUserCitiesIds().contains(city.getId()))
                .collect(Collectors.toList());
    }

}
