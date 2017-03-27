package by.sportshop.rent.utils;

import by.sportshop.rent.entity.RentUnit;
import by.sportshop.rent.interfaces.RentStore;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Save and load informarion about all rent units
 */
public class SerializationRentStore implements RentStore {

    private String filename;

    public SerializationRentStore(String filename) {
        this.filename = filename;
    }

    /** Loads information about rent unit from a file*/
    @Override
    public List<RentUnit> loadRentUnits() {
        List<RentUnit> rentUnits = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            rentUnits = (List<RentUnit>) ois.readObject();
        } catch (IOException ex) {
            System.out.println("Rent file not found");
        } catch (ClassNotFoundException ex) {
            System.out.println("Rent units has error serializable data");
        }
        return rentUnits;
    }

    /** Saves information about rent unit in a file*/
    @Override
    public void store(List<RentUnit> rentUnits) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(rentUnits);
        } catch (IOException ex) {
            System.out.println("Rent file not found");
        }
    }
}
