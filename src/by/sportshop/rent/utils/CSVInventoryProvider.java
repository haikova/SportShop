package by.sportshop.rent.utils;

import by.sportshop.rent.entity.SportEquipment;
import by.sportshop.rent.enums.Category;
import by.sportshop.rent.interfaces.InventoryProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static by.sportshop.rent.constants.MessageConstants.NOT_FIND_CSV_FILE;
import static by.sportshop.rent.constants.MessageConstants.NOT_READ_INVENTORY;

/**
 * Created by Olya on 26.03.2017.
 */
public class CSVInventoryProvider implements InventoryProvider {

    private File inventoryFile;

    public CSVInventoryProvider(File inventoryFile) throws IOException {
        checkFile(inventoryFile);
        this.inventoryFile = inventoryFile;
    }

    private void checkFile(File file) throws FileNotFoundException {
        if (!(file.exists() && file.isFile())) {
            throw new FileNotFoundException(NOT_FIND_CSV_FILE);
        }
    }

    @Override
    public Map<SportEquipment, Integer> loadInventory() {
        Map<SportEquipment, Integer> goods = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.inventoryFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                parseEquipment(goods, tokens);
            }
        } catch (IOException e) {
            throw new RuntimeException(NOT_READ_INVENTORY, e);
        }

        return goods;
    }

    private void parseEquipment(Map<SportEquipment, Integer> goods, String[] tokens) {
        Category category = Category.valueOf(tokens[0]);
        String title = tokens[1];
        int price = Integer.parseInt(tokens[2]);
        Integer count = Integer.parseInt(tokens[3]);
        SportEquipment sportEquipment = new SportEquipment(category, title, price);
        goods.put(sportEquipment, count);
        // TODO: parse category, price and count. If successful, add to the goods map.
    }
}
