package com.koder.parkinglotsystem.model;

import com.koder.parkinglotsystem.enums.SpotType;
import lombok.Data;

import java.util.UUID;

@Data
public class Spot {
    String spotId;
    SpotType spotType;
    boolean isFree;
    Vehicle vehicle;

    public Spot(SpotType spotType) {
        this.spotId = "S-" + UUID.randomUUID();
        this.spotType = spotType;
        this.isFree = true;
    }

}
