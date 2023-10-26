package com.koder.parkinglotsystem.util;

import com.koder.parkinglotsystem.model.Spot;
import com.koder.parkinglotsystem.model.Vehicle;

public class ParkingLotUtils {
    public static boolean isSpotCompatible(Spot spot, Vehicle vehicle) {
        // for now we are assuming that all the spots are compatible with all the vehicles
        // in future we can add more logic here
        return true;
    }
}
