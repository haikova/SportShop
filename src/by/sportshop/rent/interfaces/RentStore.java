package by.sportshop.rent.interfaces;

import by.sportshop.rent.entity.RentUnit;

import java.io.Serializable;
import java.util.List;


public interface RentStore extends Serializable {
    List<RentUnit> loadRentUnits();

    void store(List<RentUnit> rentUnits);
}
