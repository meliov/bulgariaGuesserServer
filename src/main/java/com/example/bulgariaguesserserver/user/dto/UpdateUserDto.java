package com.example.bulgariaguesserserver.user.dto;

public class UpdateUserDto {
    private Long id;
    private Integer level;
    private Double points;
    private byte[] image;

    public UpdateUserDto() {
    }

    public Long getId() {
        return id;
    }

    public UpdateUserDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public UpdateUserDto setLevel(Integer level) {
        this.level = level;
        return this;
    }

    public Double getPoints() {
        return points;
    }

    public UpdateUserDto setPoints(Double points) {
        this.points = points;
        return this;
    }

    public byte[] getImage() {
        return image;
    }

    public UpdateUserDto setImage(byte[] image) {
        this.image = image;
        return this;
    }


}
