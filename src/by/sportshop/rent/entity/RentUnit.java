package by.sportshop.rent.entity;

import by.sportshop.rent.entity.SportEquipment;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Olya on 25.03.2017.
 */

public class RentUnit implements Serializable
{
    private SportEquipment[] units = new SportEquipment[3];
    private int count = 0;

    public void addItem(SportEquipment unit)
    {
        units[count] = unit;
        ++count;
    }

    public void clearCart()
    {
        Arrays.fill(units, null);
        count = 0;
    }

    public int getCount()
    {
        return count;
    }


    public SportEquipment[] getUnits()
    {
        return units;
    }
}
