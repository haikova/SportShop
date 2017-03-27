package by.sportshop.rent.entity;

/**
 * Created by Olya on 25.03.2017.
 */

import by.sportshop.rent.interfaces.InventoryProvider;
import by.sportshop.rent.interfaces.RentStore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.sportshop.rent.constants.MessageConstants.NOT_CORRECT_COUNT;

public class Shop implements Serializable
{

    private Map<SportEquipment, Integer> goods = new HashMap<SportEquipment, Integer>();
    private Map<SportEquipment, Integer> availableGoods = new HashMap<SportEquipment, Integer>();
    private List<RentUnit> rentUnits;
    private RentStore rentStore;

    public Shop(InventoryProvider provider, RentStore rentStore)
    {
        goods = provider.loadInventory();
        this.rentStore = rentStore;
        rentUnits = rentStore.loadRentUnits();
        calculateAvailableGoods();
    }

    public boolean rent(RentUnit unit)
    {
        // TODO: check that unit.getUnits > 0 and <= 3
        // TODO: check availability
        // TODO: add to rentUnits and ...
        Map<SportEquipment, Integer> bufAvailableGoods = new HashMap<>();
        boolean check = true;
        if ((unit.getCount() != 0) && (unit.getCount() < 4))
        {
            for (SportEquipment equipment : unit.getUnits())
            {
                if (equipment != null)
                {
                    if (!(bufAvailableGoods.containsKey(equipment))){
                        bufAvailableGoods.put(equipment, availableGoods.get(equipment));
                    }
                    Integer newAvailableCount = (bufAvailableGoods.get(equipment) - 1);
                    bufAvailableGoods.put(equipment, newAvailableCount);
                    if (newAvailableCount < 0)
                    {
                        return false;
                    }
                }
            }
            availableGoods.putAll(bufAvailableGoods);
            rentUnits.add(unit);
            rentStore.store(rentUnits);
            rentUnits = rentStore.loadRentUnits();
        }
        return true;
    }

    private void calculateAvailableGoods()
    {
        for (SportEquipment equipment : goods.keySet())
        {
            availableGoods.put(equipment, goods.get(equipment).intValue());
        }
        if (rentUnits != null)
        {
            for (RentUnit unit : rentUnits)
            {
                for (SportEquipment equipment : unit.getUnits())
                {
                    if (equipment != null)
                    {
                        Integer newAvailableCount = (availableGoods.get(equipment) - 1);
                        availableGoods.put(equipment, newAvailableCount);
                    }
                }
            }
        }
    }

    public Map<SportEquipment, Integer> getGoods()
    {
        return goods;
    }

    public List<RentUnit> getRentUnits()
    {
        return rentUnits;
    }

    public Map<SportEquipment, Integer> getAvailableGoods()
    {
        return availableGoods;
    }
}

