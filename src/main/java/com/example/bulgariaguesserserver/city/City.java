package com.example.bulgariaguesserserver.city;

import com.example.bulgariaguesserserver.util.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class City extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer level;

    private static Integer LEVEL_INCREASE = 0;
    public String getName() {
        return name;
    }

    public City setName(String name) {
        this.name = name;
        return this;
    }

    public City() {
    }


    public City(String name) {
        this.name = name;
        this.level = ++LEVEL_INCREASE;
        if(LEVEL_INCREASE == 10){
            LEVEL_INCREASE = 0;
        }
    }

    public Integer getLevel() {
        return level;
    }

    public City setLevel(Integer level) {
        this.level = level;
        return this;
    }
}
