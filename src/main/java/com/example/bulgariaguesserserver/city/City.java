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

    public String getName() {
        return name;
    }

    public City setName(String name) {
        this.name = name;
        return this;
    }

    public City() {
    }

    public City(String name, Integer level) {
        this.name = name;
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }

    public City setLevel(Integer level) {
        this.level = level;
        return this;
    }
}
