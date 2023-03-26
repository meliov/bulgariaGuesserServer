package com.example.bulgariaguesserserver.city;

public class CityDto {
    private Long id;
    private String name;
    private Integer level;

    public CityDto() {
    }

    public Long getId() {
        return id;
    }

    public CityDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CityDto setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public CityDto setLevel(Integer level) {
        this.level = level;
        return this;
    }
}
