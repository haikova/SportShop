package by.sportshop.rent.interfaces;

import by.sportshop.rent.entity.SportEquipment;

import java.util.Map;


public interface InventoryProvider {
    Map<SportEquipment, Integer> loadInventory();
}
