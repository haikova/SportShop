package by.sportshop.rent.interfaces;

import by.sportshop.rent.entity.RentUnit;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Olya on 26.03.2017.
 */
public interface RentStore extends Serializable {
    List<RentUnit> loadRentUnits();

    void store(List<RentUnit> rentUnits);
}
