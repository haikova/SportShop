package by.sportshop.rent.entity;

import by.sportshop.rent.interfaces.InventoryProvider;
import by.sportshop.rent.interfaces.RentStore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Stores a unit of goods, available goods and list of rent goods
 */
public class Shop implements Serializable {

    private Map<SportEquipment, Integer> goods = new HashMap<>();
    private Map<SportEquipment, Integer> availableGoods = new HashMap<>();
    private List<RentUnit> rentUnits;
    private RentStore rentStore;

    public Shop(InventoryProvider provider, RentStore rentStore) {
        goods = provider.loadInventory();
        this.rentStore = rentStore;
        rentUnits = rentStore.loadRentUnits();
        calculateAvailableGoods();
    }

    /** Represents a rent */
    public boolean rent(RentUnit unit) {
        Map<SportEquipment, Integer> bufAvailableGoods = new HashMap<>();
        if ((unit.getCount() != 0) && (unit.getCount() < 4)) {
            for (SportEquipment equipment : unit.getUnits()) {
                if (equipment != null) {
                    if (!(bufAvailableGoods.containsKey(equipment))) {
                        bufAvailableGoods.put(equipment, availableGoods.get(equipment));
                    }
                    Integer newAvailableCount = (bufAvailableGoods.get(equipment) - 1);
                    bufAvailableGoods.put(equipment, newAvailableCount);
                    if (newAvailableCount < 0) {
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

    /** Calculates all available goods for rent */
    private void calculateAvailableGoods() {
        for (SportEquipment equipment : goods.keySet()) {
            availableGoods.put(equipment, goods.get(equipment).intValue());
        }
        if (rentUnits != null) {
            for (RentUnit unit : rentUnits) {
                for (SportEquipment equipment : unit.getUnits()) {
                    if (equipment != null) {
                        Integer newAvailableCount = (availableGoods.get(equipment) - 1);
                        availableGoods.put(equipment, newAvailableCount);
                    }
                }
            }
        }
    }

    public Map<SportEquipment, Integer> getGoods() {
        return goods;
    }

    public List<RentUnit> getRentUnits() {
        return rentUnits;
    }

    public Map<SportEquipment, Integer> getAvailableGoods() {
        return availableGoods;
    }
}

